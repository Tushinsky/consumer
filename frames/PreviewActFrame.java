/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.border.LineBorder;

/**
 * Предварительный просмотр и вывод на печать актов по заданным шаблонам
 * @author Sergii.Tushinskyi
 */
public class PreviewActFrame {
    
    private PreviewPanel prevPanel;// панель для предварительного просмотра, на ней будем рисовать
    public enum ActMode{ActInvestigate, ActPP, ActLose, ActPlumbs};// перечисление типов создаваемых актов
    public enum PaperOrient{LandscapeOrient, PortraitOrient};// ориентация бумаги
    private final PaperOrient orient;
    private final ActMode actMode;
    private Object[][] tableContent;// содержимое таблицы
    private PrintPreviewFrame prevFrame;// окно предварительного просмотра
    private Object[] parameters;// массив параметров для формирования акта
    private int copies;// количество экземпляров акта на листе
    private boolean fullAct;// признак печати половины акта или полной версии
    private Component viewComponent;// компоненет, который будет отображаться на панели
    
    /**
     * Создаёт экземпляр класса для отображения окна предварительного просмотра
     * и вывода на печать
     * @param orient ориентация бумаги для печати
     * @param actMode тип выводимого акта для печати
     */
    public PreviewActFrame(PaperOrient orient, ActMode actMode) {
        this.orient = orient;
        this.actMode = actMode;
        copies = 1;// на листе располагается только один экземпляр акта
        fullAct = true;// по-умолчанию принимаем печать полной формы акта
    }
    
    /**
     * @param parameters the parameters to set
     */
    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }
        
    
    /**
    * выводит окно предварительного просмотра
     * @param visible
    */
    public void setVisiblePreview(boolean visible) {
        if (visible) {
            // создаём окно предварительного просмотра
            createFrame(false);
        } else {
            if(prevFrame != null) prevFrame.setVisible(false);
            prevPanel = null;
            prevFrame = null;
        }
    }
    

    /**
     * выводит на печать Акт
     */
    public void doPrintAct() {
        // создаём окно предварительного просмотра
        createFrame(true);

        
 //            if (pj.printDialog()) {
 //                try {
 ////                    System.out.println("attrname - " + attributes.toString());
 //                    pj.print();// пытаемся печатать
 //                } catch (PrinterException ex) {
 //                    Logger.getLogger(ActFrame.class.getName()).log(Level.SEVERE, null, ex);
 //                }
 //            }

    }
    
    
    /**
     * Создание окна предварительного просмотра
     * @param immediatePrint если TRUE, окно печати вызывается немедленно
     */
    private void createFrame(boolean immediatePrint) {
//        System.out.println("prevFrame is " + (prevFrame == null));
        if(prevFrame == null){
            initComponent();
            prevFrame = new PrintPreviewFrame();
            prevFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent we) {
                    super.windowClosed(we); //To change body of generated methods, choose Tools | Templates.
                    prevFrame = null;
                }
                
            });
            prevFrame.setVisible(true);
        } else {
            // обновляем данные на метке
            if(prevPanel.getComponentCount() > 0) prevPanel.removeAll();
            prevPanel.add(viewComponent);
//            prevPanel.setContent(getContentString((int)prevPanel.getPreferredSize().getWidth() - 30));
//            System.out.println("update lblViewHeight=" + prevPanel.lblView.getSize().getHeight());
        }
        // проверяем флаг вывода окна печати
        if(immediatePrint) {
            // если он установлен, то вызываем окно печати
            prevFrame.doPrint();
        }
    }

    /**
     * создание компонентов пользовательского интерфейса
    */
    private void initComponent() {
        Dimension dmnsn = null;
        int width;// 595 - ширина листа Формата А4 в пунктах в книжной ориентации
        int height;
        switch (orient) {
            case LandscapeOrient :
                width = 595;// 842 - ширина листа Формата А4 в пунктах в альбомной ориентации
                height = 842;
                dmnsn = new Dimension(width, height);// размер листа бумаги формата А4 альбомная ориентация
                break;
            case PortraitOrient :
                width = 595;
                height = 842;
                dmnsn = new Dimension(width, height);// размер листа бумаги формата А4 портретная ориентациия
                break;
        }
        prevPanel = new PreviewPanel();// создаём панель
        prevPanel.setPreferredSize(dmnsn);// задаём размеры панели
        prevPanel.setSize(dmnsn);
        prevPanel.setMinimumSize(dmnsn);
        prevPanel.setMaximumSize(dmnsn);
        prevPanel.add(viewComponent);
//        prevPanel.setContent(getContentString(width - 30));
    }
        
    
    /**
    * формирует содерждимое метки для вывода на печать в виде документа HTML
    * @param pWidth ширина формируемой таблицы
    * @return сформированное содержимое метки
    */
    private String getContentString(int pWidth){
        String string = null;
        switch (actMode) {
            case ActInvestigate:
                string = getActInvestigate(pWidth);
                break;
            case ActLose:
                string = getActLose(pWidth);
                break;
            case ActPP:
                string = getActPP(pWidth);
                break;
            case ActPlumbs:
                string = getActPlumbs(pWidth);
                break;
        }
        return string;
    }
        
    /**
     * 
     * @param pWidth
     * @return 
     */
    private String getActInvestigate(int pWidth) {
        return null;
    }

    /**
     * 
     * @param pWidth
     * @return 
     */
    private String getActLose(int pWidth) {
        return null;
    }

    /**
     * 
     * @param pWidth
     * @return 
     */
    private String getActPP(int pWidth) {
        String string = "<html>" +
            "<head>" +
                "<title>TODO supply a title</title>\n" +
                "<meta charset=\"windows-1251\">\n" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
            "</head>" +
            "<body>" +
                "<p></p>" +
                "<p></p>" +
                "<table border=\"0\" cellspacing=\"0\" cellpadding=\"5\" align=\"center\" cols=\"2\" width=\"" +
                    pWidth + "\">" +
                    "<tr>" +
                        "<td colspan=\"2\" align=\"center\">АКТ</td>" +
                    "</tr>" +
                    "<tr height=\"20\"><td></td><td></td></tr>" +
                    "<tr>" +
                        "<td colspan=\"2\" align=\"left\">Приёма-передачи " +
                        "поставленного и протранспортированного природного газа по договору " +
                        parameters[0] + "</td>" +
                    "</tr>" +
                    "<tr>" +
                        "<td align=\"left\">г. Алчевск</td><td align=\"right\">" + parameters[1] + "</td>" +
                    "</tr>" +
                    "<tr>" +
                        "<td colspan=\"2\" align=\"justify\">" +
                            "Алчевское МРУЭГХ филиал ГУП \"Луганскгаз\" (далее - Поставщик) в " +
                            "лице Главного инженера Алчевского МРУЭГХ Громика Андрея Игоревича, который действует на " +
                            "основании доверенности №424 от 12.11.2019, представитель " +
                            parameters[2] +
                            ", далее Потребитель, в лице " +
                            parameters[3] +
                            ", составили данный акт в том, что в " +
                            parameters[4] + " Поставщик поставил и протранспортировал " + 
                            parameters[5] +
                            " куб м, Потребитель принял и использовал природный газ объёмом " +
                            parameters[5] + " (" + parameters[6] +
                            ") куб м. Данный акт составлен в 2-х экземплярах по 1 каждой стороне. " +
                            "Все экземпляры идентичны и имеют однинаковую юридическую силу." +
                        "</td>" +
                    "</tr>" +
                "</table>" +
                "<p></p>" +
                getActPPTableData(pWidth) +
                "<table border=\"0\" align=\"center\" cols=\"2\" width=\"" + pWidth + "\">" +
                    "<tr>" +
                        "<td width=\"50%\" align=\"center\">Представитель Потребителя</td>" +
                        "<td align=\"center\">Представитель Поставщика</td>" +
                    "</tr>" +
                    "<tr height=\"20\"><td></td><td></td></tr>" +
                    "<tr>" +
                        "<td width=\"50%\"></td>" +
                        "<td align=\"center\">_________/А. И. Громик/</td>" +
                    "</tr>" +
                "</table>" +
            "</body>" +
        "</html>";
        return string;
    }

    /**
     * 
     * @param pWidth
     * @return 
     */
    private String getActPlumbs(int pWidth) {
        return null;
    }

    /**
     * Формирует строку HTML кода из данных таблицы
     * @return строку в формате HTML
     */
    private String getActPPTableData(int pWidth) {
        // проверяем установку флага fullAct
        if(fullAct == false) {
            // установлена печать полной формы акта
            // получаем данные о содержании таблицы и формируем строку HTML
            if(tableContent != null) {
                
                String result = "<table border=\"1\" cellspacing=\"0\" cellpadding=\"5\" align=\"center\" cols=\"6\" width=\"" +
                            (pWidth - 10) + "\">" +
                            "<thead>" +
                            "<th width=\"40%\">Адрес объекта газопотребления</th>" +
                            "<th width=\"14%\">Пок-ние на конец месяца</th>" +
                            "<th width=\"14%\">Пок-ние на начало месяца</th>" +
                            "<th width=\"12%\">Коэф-т привед. к с/у</th>" +
                            "<th width=\"5%\">ПТП</th>" +
                            "<th>Исполь-ный объём всего за месяц, м3</th>" +
                        "</thead>" + getTableData();
                Object[] tableContent2 = tableContent[tableContent.length - 1];
                String resString = "<tr><td colspan=\"5\" align=\"left\">Всего, м3</td><td align=\"center\">" + 
                        tableContent2[5] + "</td></tr>" + "</table>" +
                "<p></p>";
                result = result + resString;// соединяем полученные даннные из таблицы и итоговую строку
                return result;
            } else return "";
        } else {
            return "";
        }
    }
    
    /**
     * Формирует строку HTML - кода по данным таблицы
     * @return полученная строка в формате HTML с данными таблицы
     */
    private String getTableData() {
        // получаем данные о содержании таблицы и формируем строку HTML
        String result = "";
        for (int i = 0; i < tableContent.length - 1; i ++) {
            Object[] tableContent1 = tableContent[i];
            String rowString = "";
            for (Object tableContent11 : tableContent1) {
                rowString = rowString + "<td align=\"center\">" + tableContent11 + "</td>";
            }
            String htmlString = "<tr>" + rowString + "</tr>";
//                System.out.println("html- " + htmlString);
            result = result + htmlString;
        }
        return result;
    }
    
    /**
    * формирует и отображает окно предварительного просмотра
    */
    private class PrintPreviewFrame extends JFrame {
 //        private final Component component;



        public PrintPreviewFrame() throws HeadlessException {

            super.addWindowListener(new WindowAdapter() {
                @Override
                public void windowOpened(WindowEvent we) {
                    super.windowOpened(we); //To change body of generated methods, choose Tools | Templates.
                    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    setTitle("Preview");
                    initComponents();
                    setSize(670, 500);
                    setLocation(100, 100);
                }

            });
        }

        /**
         * создание элементов пользовательского интерфейса
         */
        private void initComponents(){
            JMenuBar bar = new JMenuBar();// добавляем строку меню
            final JMenu mnuFile = new JMenu("File");
            final JMenuItem mnuFilePrint = new JMenuItem("Print");
            mnuFilePrint.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, 
                    InputEvent.CTRL_MASK));// клавиши быстрого вызова
            // действие для меню Печать
            mnuFilePrint.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    doPrint();
                }
            });
            mnuFile.add(mnuFilePrint);
            bar.add(mnuFile);
            setJMenuBar(bar);
            // компоненты размещаем в Боксе
//            prevPanel.setContent(getContentString(565));
            
            Box box = Box.createHorizontalBox();
            box.add(Box.createHorizontalGlue());
            box.add(prevPanel);
            box.add(Box.createHorizontalGlue());
            JScrollPane pane = new JScrollPane(box);// добавляем прокрутку
            getContentPane().add(pane);// добавляем к содержимому окна
            
            pack();// упаковываем содержимое
            
        }

        private void doPrint() {
            // создаём объект для работы с принтером
            PrinterJob pj = PrinterJob.getPrinterJob();
            PageFormat pf = new PageFormat();
            pf.setOrientation(orient == PaperOrient.PortraitOrient ? 1 : 0);// ориентация бумаги
            
            Paper paper = new Paper();// бумага и размер
            paper.setSize(prevPanel.getPreferredSize().getWidth(), prevPanel.getPreferredSize().getHeight());
            paper.setImageableArea(15, 15, 565, 812);// нужно определять в зависимости от ориентации
            pf.setPaper(paper);
            pj.setPrintable(prevPanel, pf);
            pj.setCopies(copies);// количество копий
            if (pj.printDialog()) {
                try {
                    pj.print();// пытаемся печатать
                } catch (PrinterException ex) {
                    Logger.getLogger(ActFrame.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, ex.getMessage(), 
                            "Consumer", JOptionPane.ERROR_MESSAGE);// сообщение об ошибке
                }
            }
        }


    }

    
    /**
    * панель с меткой, содержащей текст в формате HTML
    */
    private class PreviewPanel extends JPanel implements Printable{

//        private final JLabel lblView;
        public PreviewPanel() {
            super.setBorder(new LineBorder(Color.BLACK, 1));
            super.setBackground(Color.WHITE);
//            lblView = new JLabel();
//                lblView.setPreferredSize(dmnsn);
//                lblView.setMaximumSize(dmnsn);
//            lblView.setBorder(new LineBorder(Color.BLACK));
//            lblView.setHorizontalAlignment(SwingConstants.CENTER);
//            lblView.setVerticalAlignment(SwingConstants.TOP);
//            Font font = lblView.getFont();// получаем шрифт, используемый меткой
//            lblView.setFont(new Font(font.getFontName(), font.getStyle(), 9));
////                lblView.setText(getContentString(getWidth()));
//            JScrollPane pane = new JScrollPane(lblView);
//            add(pane);
        }

        @Override
        public int print(Graphics grphcs, PageFormat pf, int i) throws PrinterException {
            //To change body of generated methods, choose Tools | Templates.
            Graphics2D g2 = (Graphics2D) grphcs;
            this.print(g2);
            return i;
        }


        public void setContent(String string) {
            if(this.getComponentCount() > 0) this.removeAll();
//            JLabel lblView = new JLabel();
////                lblView.setPreferredSize(dmnsn);
////                lblView.setMaximumSize(dmnsn);
////            lblView.setBorder(new LineBorder(Color.BLACK));
//            lblView.setHorizontalAlignment(SwingConstants.CENTER);
//            lblView.setVerticalAlignment(SwingConstants.TOP);
//            Font font = lblView.getFont();// получаем шрифт, используемый меткой
//            lblView.setFont(new Font(font.getFontName(), font.getStyle(), 9));
//            lblView.setText(string);
//            System.out.println("lblViewHeight=" + lblView.getPreferredSize().getHeight());
            this.add(viewComponent);
            
            System.out.println("ComponentCount=" + this.getComponentCount());
        }

    }

    /**
     * @param tableContent the tableContent to set
     */
    public void setTableContent(Object[][] tableContent) {
        this.tableContent = tableContent;
    }

    /**
     * @param copies the copies to set
     */
    public void setCopies(int copies) {
        this.copies = copies;
    }

    
    /**
     * @param viewComponent the viewComponent to set
     */
    public void setViewComponent(Component viewComponent) {
        this.viewComponent = viewComponent;
    }
    
    
        
        
        
}
