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
 * ��������������� �������� � ����� �� ������ ����� �� �������� ��������
 * @author Sergii.Tushinskyi
 */
public class PreviewActFrame {
    
    private PreviewPanel prevPanel;// ������ ��� ���������������� ���������, �� ��� ����� ��������
    public enum ActMode{ActInvestigate, ActPP, ActLose, ActPlumbs};// ������������ ����� ����������� �����
    public enum PaperOrient{LandscapeOrient, PortraitOrient};// ���������� ������
    private final PaperOrient orient;
    private final ActMode actMode;
    private Object[][] tableContent;// ���������� �������
    private PrintPreviewFrame prevFrame;// ���� ���������������� ���������
    private Object[] parameters;// ������ ���������� ��� ������������ ����
    private int copies;// ���������� ����������� ���� �� �����
    private boolean fullAct;// ������� ������ �������� ���� ��� ������ ������
    private Component viewComponent;// ����������, ������� ����� ������������ �� ������
    
    /**
     * ������ ��������� ������ ��� ����������� ���� ���������������� ���������
     * � ������ �� ������
     * @param orient ���������� ������ ��� ������
     * @param actMode ��� ���������� ���� ��� ������
     */
    public PreviewActFrame(PaperOrient orient, ActMode actMode) {
        this.orient = orient;
        this.actMode = actMode;
        copies = 1;// �� ����� ������������� ������ ���� ��������� ����
        fullAct = true;// ��-��������� ��������� ������ ������ ����� ����
    }
    
    /**
     * @param parameters the parameters to set
     */
    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }
        
    
    /**
    * ������� ���� ���������������� ���������
     * @param visible
    */
    public void setVisiblePreview(boolean visible) {
        if (visible) {
            // ������ ���� ���������������� ���������
            createFrame(false);
        } else {
            if(prevFrame != null) prevFrame.setVisible(false);
            prevPanel = null;
            prevFrame = null;
        }
    }
    

    /**
     * ������� �� ������ ���
     */
    public void doPrintAct() {
        // ������ ���� ���������������� ���������
        createFrame(true);

        
 //            if (pj.printDialog()) {
 //                try {
 ////                    System.out.println("attrname - " + attributes.toString());
 //                    pj.print();// �������� ��������
 //                } catch (PrinterException ex) {
 //                    Logger.getLogger(ActFrame.class.getName()).log(Level.SEVERE, null, ex);
 //                }
 //            }

    }
    
    
    /**
     * �������� ���� ���������������� ���������
     * @param immediatePrint ���� TRUE, ���� ������ ���������� ����������
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
            // ��������� ������ �� �����
            if(prevPanel.getComponentCount() > 0) prevPanel.removeAll();
            prevPanel.add(viewComponent);
//            prevPanel.setContent(getContentString((int)prevPanel.getPreferredSize().getWidth() - 30));
//            System.out.println("update lblViewHeight=" + prevPanel.lblView.getSize().getHeight());
        }
        // ��������� ���� ������ ���� ������
        if(immediatePrint) {
            // ���� �� ����������, �� �������� ���� ������
            prevFrame.doPrint();
        }
    }

    /**
     * �������� ����������� ����������������� ����������
    */
    private void initComponent() {
        Dimension dmnsn = null;
        int width;// 595 - ������ ����� ������� �4 � ������� � ������� ����������
        int height;
        switch (orient) {
            case LandscapeOrient :
                width = 595;// 842 - ������ ����� ������� �4 � ������� � ��������� ����������
                height = 842;
                dmnsn = new Dimension(width, height);// ������ ����� ������ ������� �4 ��������� ����������
                break;
            case PortraitOrient :
                width = 595;
                height = 842;
                dmnsn = new Dimension(width, height);// ������ ����� ������ ������� �4 ���������� �����������
                break;
        }
        prevPanel = new PreviewPanel();// ������ ������
        prevPanel.setPreferredSize(dmnsn);// ����� ������� ������
        prevPanel.setSize(dmnsn);
        prevPanel.setMinimumSize(dmnsn);
        prevPanel.setMaximumSize(dmnsn);
        prevPanel.add(viewComponent);
//        prevPanel.setContent(getContentString(width - 30));
    }
        
    
    /**
    * ��������� ����������� ����� ��� ������ �� ������ � ���� ��������� HTML
    * @param pWidth ������ ����������� �������
    * @return �������������� ���������� �����
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
                        "<td colspan=\"2\" align=\"center\">���</td>" +
                    "</tr>" +
                    "<tr height=\"20\"><td></td><td></td></tr>" +
                    "<tr>" +
                        "<td colspan=\"2\" align=\"left\">�����-�������� " +
                        "������������� � ���������������������� ���������� ���� �� �������� " +
                        parameters[0] + "</td>" +
                    "</tr>" +
                    "<tr>" +
                        "<td align=\"left\">�. �������</td><td align=\"right\">" + parameters[1] + "</td>" +
                    "</tr>" +
                    "<tr>" +
                        "<td colspan=\"2\" align=\"justify\">" +
                            "��������� ������ ������ ��� \"����������\" (����� - ���������) � " +
                            "���� �������� �������� ���������� ������ ������� ������ ���������, ������� ��������� �� " +
                            "��������� ������������ �424 �� 12.11.2019, ������������� " +
                            parameters[2] +
                            ", ����� �����������, � ���� " +
                            parameters[3] +
                            ", ��������� ������ ��� � ���, ��� � " +
                            parameters[4] + " ��������� �������� � ������������������ " + 
                            parameters[5] +
                            " ��� �, ����������� ������ � ����������� ��������� ��� ������� " +
                            parameters[5] + " (" + parameters[6] +
                            ") ��� �. ������ ��� ��������� � 2-� ����������� �� 1 ������ �������. " +
                            "��� ���������� ��������� � ����� ����������� ����������� ����." +
                        "</td>" +
                    "</tr>" +
                "</table>" +
                "<p></p>" +
                getActPPTableData(pWidth) +
                "<table border=\"0\" align=\"center\" cols=\"2\" width=\"" + pWidth + "\">" +
                    "<tr>" +
                        "<td width=\"50%\" align=\"center\">������������� �����������</td>" +
                        "<td align=\"center\">������������� ����������</td>" +
                    "</tr>" +
                    "<tr height=\"20\"><td></td><td></td></tr>" +
                    "<tr>" +
                        "<td width=\"50%\"></td>" +
                        "<td align=\"center\">_________/�. �. ������/</td>" +
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
     * ��������� ������ HTML ���� �� ������ �������
     * @return ������ � ������� HTML
     */
    private String getActPPTableData(int pWidth) {
        // ��������� ��������� ����� fullAct
        if(fullAct == false) {
            // ����������� ������ ������ ����� ����
            // �������� ������ � ���������� ������� � ��������� ������ HTML
            if(tableContent != null) {
                
                String result = "<table border=\"1\" cellspacing=\"0\" cellpadding=\"5\" align=\"center\" cols=\"6\" width=\"" +
                            (pWidth - 10) + "\">" +
                            "<thead>" +
                            "<th width=\"40%\">����� ������� ���������������</th>" +
                            "<th width=\"14%\">���-��� �� ����� ������</th>" +
                            "<th width=\"14%\">���-��� �� ������ ������</th>" +
                            "<th width=\"12%\">����-� ������. � �/�</th>" +
                            "<th width=\"5%\">���</th>" +
                            "<th>������-��� ����� ����� �� �����, �3</th>" +
                        "</thead>" + getTableData();
                Object[] tableContent2 = tableContent[tableContent.length - 1];
                String resString = "<tr><td colspan=\"5\" align=\"left\">�����, �3</td><td align=\"center\">" + 
                        tableContent2[5] + "</td></tr>" + "</table>" +
                "<p></p>";
                result = result + resString;// ��������� ���������� ������� �� ������� � �������� ������
                return result;
            } else return "";
        } else {
            return "";
        }
    }
    
    /**
     * ��������� ������ HTML - ���� �� ������ �������
     * @return ���������� ������ � ������� HTML � ������� �������
     */
    private String getTableData() {
        // �������� ������ � ���������� ������� � ��������� ������ HTML
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
    * ��������� � ���������� ���� ���������������� ���������
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
         * �������� ��������� ����������������� ����������
         */
        private void initComponents(){
            JMenuBar bar = new JMenuBar();// ��������� ������ ����
            final JMenu mnuFile = new JMenu("File");
            final JMenuItem mnuFilePrint = new JMenuItem("Print");
            mnuFilePrint.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, 
                    InputEvent.CTRL_MASK));// ������� �������� ������
            // �������� ��� ���� ������
            mnuFilePrint.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    doPrint();
                }
            });
            mnuFile.add(mnuFilePrint);
            bar.add(mnuFile);
            setJMenuBar(bar);
            // ���������� ��������� � �����
//            prevPanel.setContent(getContentString(565));
            
            Box box = Box.createHorizontalBox();
            box.add(Box.createHorizontalGlue());
            box.add(prevPanel);
            box.add(Box.createHorizontalGlue());
            JScrollPane pane = new JScrollPane(box);// ��������� ���������
            getContentPane().add(pane);// ��������� � ����������� ����
            
            pack();// ����������� ����������
            
        }

        private void doPrint() {
            // ������ ������ ��� ������ � ���������
            PrinterJob pj = PrinterJob.getPrinterJob();
            PageFormat pf = new PageFormat();
            pf.setOrientation(orient == PaperOrient.PortraitOrient ? 1 : 0);// ���������� ������
            
            Paper paper = new Paper();// ������ � ������
            paper.setSize(prevPanel.getPreferredSize().getWidth(), prevPanel.getPreferredSize().getHeight());
            paper.setImageableArea(15, 15, 565, 812);// ����� ���������� � ����������� �� ����������
            pf.setPaper(paper);
            pj.setPrintable(prevPanel, pf);
            pj.setCopies(copies);// ���������� �����
            if (pj.printDialog()) {
                try {
                    pj.print();// �������� ��������
                } catch (PrinterException ex) {
                    Logger.getLogger(ActFrame.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, ex.getMessage(), 
                            "Consumer", JOptionPane.ERROR_MESSAGE);// ��������� �� ������
                }
            }
        }


    }

    
    /**
    * ������ � ������, ���������� ����� � ������� HTML
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
//            Font font = lblView.getFont();// �������� �����, ������������ ������
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
//            Font font = lblView.getFont();// �������� �����, ������������ ������
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
