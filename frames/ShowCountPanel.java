/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import abonentgaz.ColumnModelListener;
import abonentgaz.FrameLayoutManager;
import abonentgaz.TableCell_Renderer;
import abonentgaz.TableProperty;
import abonentgaz.UserProperties;
import dao_impl.MonthclosingDaoImpl;
import dao_impl.QueriesDao;
import dao_impl.SprcodeinstalDaoImpl;
import entities.Monthclosing;
import entities.SprcodeInstal;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import runqueries.Runquery;

/**
 *
 * @author lera
 */
public class ShowCountPanel extends javax.swing.JPanel {

    /**
     * @return the autoCalc
     */
    public boolean isAutoCalc() {
        return autoCalc;
    }

    /**
     * @param autoCalc the autoCalc to set
     */
    public void setAutoCalc(boolean autoCalc) {
        this.autoCalc = autoCalc;
    }

    /**
     * @return the autoSave
     */
    public boolean isAutoSave() {
        return autoSave;
    }

    /**
     * @param autoSave the autoSave to set
     */
    public void setAutoSave(boolean autoSave) {
        this.autoSave = autoSave;
    }

    private MonthclosingDaoImpl mcDao;// объект доступа к данным по закрытию месяца
    private final String[] criterias;
    private final String[] columnName;// массив имён столбцов
    private final Class[] columnClass;
    private final ColumnModelListener Showlistener;// слушатель изменений размеров столбцов таблицы
    private final String filename;// имя файла свойств для записи изменений размеров столбцов
    private final UserProperties props;
    private int[] cols;// массив нередактируемых столбцов
    private Object[][] content;// массив данных
    private MyTableModel model;// модель данных таблицы
    private final TableProperty tprops;
    private final SprcodeinstalDaoImpl cidi;
    private int reportMonth;// значение текущего месяца
    private int reportYear;// значение текущего года
    private JPopupMenu countMenu;// всплывающее меню для вставки новой или удаления существующей строки в таблице
    private final QueriesDao QDao;// объект получения данных о счётчиках предприятия
    private final String sqlQuery;// строка-запрос на получение данных о счётчиках
    private final ClosingDaoImpl cdi;
    private int idCompany;
    private CalculateListaner calcListener;
    private boolean changeData = false;
    private boolean autoCalc = true;// флаг автовычисления показаний
    private boolean autoSave = false;// флаг автосохранения показаний
    private int flowRate;// итоговый объём потребления
    
    /**
     * Creates new form ShowCountPanel
     */
    public ShowCountPanel() {
        initComponents();
        criterias = new String[]{"idorganization","isreport"};
//        columnName = new String[]{"Наименование и адрес объекта",
//            "№ счётчика",
//            "Показания счётчика на конец месяца",
//            "Показания счётчика на начало месяца",
//            "Коэф-т привед к с у",
//            "ПТП",
//            "Исполь-ный объём всего за месяц"};
        columnName = new String[]{"Наименование и адрес объекта",
            "№ счётчика",
            "<html><body><p align='center'>Показания счётчика на конец месяца</body></html>",
            "<html><body><p align='center'>Показания счётчика на начало месяца</body></html>",
            "<html><body><p align='center'>Коэф-т привед к с у</body></html>",
            "<html><body><p align='center'>ПТП</body></html>",
            "<html><body><p align='center'>Исполь-ный объём всего за месяц</body></html>"};
        columnClass = new Class[]{String.class,String.class,Integer.class,
            Integer.class,Double.class,Integer.class,Integer.class};// и типов данных
        filename = "actframe.properties";// имя файла свойств
        
        // создаём класс для чтения файла свойств
        props = new UserProperties(filename);
        tprops = new TableProperty(jTable1);
        
        // заполняем таблицу пустыми данными и задаём размеры
        setNullableData();
        calcListener = new CalculateListaner();// создаём слушателя изменений модели
        fullTable();
        
        // настройка заголовков таблицы
        JTableHeader header = jTable1.getTableHeader();
        header.setReorderingAllowed(false);
//        header.getColumnModel().getColumn(0).setHeaderRenderer(new HeaderRenderer(0, 0));
//        header.getColumnModel().getColumn(1).setHeaderRenderer(new HeaderRenderer(0, 0));
//        header.getColumnModel().getColumn(5).setHeaderRenderer(new HeaderRenderer(0, 0));
//        header.getColumnModel().getColumn(2).setHeaderRenderer(new 
//            HeaderRenderer(SwingConstants.CENTER, SwingConstants.TOP));
//        header.getColumnModel().getColumn(3).setHeaderRenderer(new 
//            HeaderRenderer(SwingConstants.CENTER, SwingConstants.TOP));
//        header.getColumnModel().getColumn(4).setHeaderRenderer(new 
//            HeaderRenderer(SwingConstants.CENTER, SwingConstants.TOP));
//        header.getColumnModel().getColumn(6).setHeaderRenderer(new 
//            HeaderRenderer(SwingConstants.CENTER, SwingConstants.TOP));
            
//        Dimension d = header.getPreferredSize();
//        d.height = 32;
        header.setDefaultRenderer(new HeaderRenderer(SwingConstants.CENTER, 
            SwingConstants.TOP));
        TableCell_Renderer.setStringTableCellRenderer(jTable1, null, null, null);
        TableCell_Renderer.setIntegerTableCellRenderer(jTable1, null);
        TableCell_Renderer.setDoubleTableCellRenderer(jTable1, null);
//        header.setPreferredSize(d);
        JViewport viewport = new JViewport(){
            @Override
            public Dimension getPreferredSize() {
                Dimension d = super.getPreferredSize(); //To change body of generated methods, choose Tools | Templates.
                d.height = 56;
                return d;
            }
            
        };
        jScrollPane1.setColumnHeader(viewport);
        
        // создаём класс для получения данных о значениях коэффициента
        // приведения, соответствующего заданному месяцу
        cidi = new SprcodeinstalDaoImpl();
        
        // выравнивание в столбцах по центру
//        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
//        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
//        Enumeration<TableColumn> count = jTable1.getColumnModel().getColumns();
//        while(count.hasMoreElements()){
//            TableColumn column = count.nextElement();
//            column.setCellRenderer(centerRenderer);
//        }

        // устанавливаем размеры столбцов
        MDIObject.setTablecolwidth(props, "colwidth", jTable1);
        
        // устанавливаем слушателя столбцов
        Showlistener = new ColumnModelListener(jTable1, props, "colwidth");
        
        // создаём всплывающее меню для добавления и удаления строк из таблицы
        createPopupMenu();
        
        // определяем строку-запрос на получение данных по счётчикам
        sqlQuery = "SELECT Obj.ID, SPRC.CITY_NAME || ', ' || SPRS.STREET_NAME " + 
                "|| ', ' || Obj.ADDRES AS ADRESS, " +
            "Co.COUNTERNUMBER, Co.IDINSTALPLACE AS CODE_ FROM SprStreet SPRS INNER JOIN " +
            "Objects Obj ON SPRS.ID = Obj.IDSTREET INNER JOIN SPRCity SPRC ON SPRC.ID = Obj.IDCITY " +
            "INNER JOIN COUNTERS Co ON Obj.ID = Co.IDOBJECT WHERE Obj.IDORGANIZATION=?;";
        
        // создаём объект доступа к данным по счётчикам
        QDao = new QueriesDao();
        QDao.setSqlQuery(sqlQuery);// передаём ему строку-запрос
        cdi = new ClosingDaoImpl();
    }
    
    public void Showcount(int idCompany){
        this.idCompany = idCompany;
        int[] idCriterias = new int[]{idCompany,1};
        mcDao = new MonthclosingDaoImpl(criterias, idCriterias);
        Object[] criteria = new Object[]{idCompany};// критерий отбора данных
        QDao.setCriteria(criteria);
        cols = new int[]{0};// адрес
        String colwidth = tprops.getColWidth();
        
        // проверяем полученные данные
        if(mcDao.getCount() > 0){
            cdi.clearEntity();// на всякий случай удаляем старые данные
            
            // данные есть
            // определяем размер массива данных
            content = new Object[mcDao.getCount() + 1][];
            
            // для заполнения нам нужны лишь некоторые поля из полученных
            for(int i = 0; i < getContent().length - 1; i++){
                Monthclosing closing = mcDao.getItem(i);
                
                // получаем данные в виде массива
                Object[] dataArray;
                dataArray = closing.toDataArray();
                Object[] e;// массив для записи данных
                e = new Object[7];
                e[0] = dataArray[1];// адрес
                e[1] = dataArray[4];// № счётчика
                e[2] = 0;// текущие показания
                e[3] = dataArray[5];// предыдущие показания
                e[4] = (double)1.0;// коэффициент по умолчанию для заполнения таблицы
                e[5] = 0;// птп по умолчанию для заполнения таблицы
                e[6] = 0;// итого
                content[i] = e;// передаём данные в массив содержимого
                
                // создаём объект для сохранения изменений
                Closing cl = new Closing(closing.getId());
                cl.setIdCompany(idCompany);cl.setIdObject(closing.getIdobject());
                cl.setCountNumber(closing.getCounternumber());cl.setCurrentShow(0);
                cl.setResult(0);
                cl.setReport((short)1);
                cl.setYear((short) dataArray[2]);
                cl.setMonth((short) dataArray[3]);
                cl.setReportMonth(reportMonth);
                cl.setReportYear(reportYear);
                // и добавляем его в объект доступа
                boolean addItem = cdi.addItem(cl);
            }
            System.out.println("cdi.count=" + cdi.getCount());
            // последняя строка данных
            Object[] e = new Object[]{"Итого","",null,null,null,null,0};
            content[getContent().length - 1] = e;
        } else {
            // данных нет
            // получаем данные
            if(QDao.getEntities()){
                // временный массив для хранения данных
                // (№, код объекта, адрес, № счётчика, код установки)
                Object[][] tmp = QDao.getContent();
                
                content = new Object[tmp.length + 1][];// определяем размер массива данных
                // заполняем его в цикле
                for(int i = 0; i < tmp.length; i++){
                    Object[] e = new Object[7];
                    e[0] = (String)tmp[i][2];// адрес
                    e[1] = (String)tmp[i][3];// номер счётчика
                    e[2] = (int)0;// текущие показания
                    e[3] = (int)0;// предыдущие показания
                    e[4] = getKoefvalue((Integer.parseInt((String)tmp[i][4]) - 1));// коэффициент
                    e[5] = (int)0;// птп
                    e[6] = (int)0;// итого
                    content[i] = e;

                    // создаём объект для сохранения изменений
                    Closing cl = new Closing(0);
                    cl.setIdCompany(idCompany);
                    cl.setIdObject(Integer.parseInt((String)tmp[i][1]));
                    cl.setCountNumber((String)tmp[i][3]);
                    cl.setCurrentShow(0);
                    cl.setResult(0);
                    cl.setReport((short)1);
                    cl.setYear((short)reportYear);
                    cl.setMonth((short)reportMonth);
                    cl.setReportMonth(reportMonth);
                    cl.setReportYear(reportYear);
                    // и добавляем его в объект доступа
                    boolean addItem = cdi.addItem(cl);
                }
                // последняя строка данных
                Object[] e = new Object[]{"Итого","",null,null,null,null,0};
                content[getContent().length - 1] = e;
                model = new MDIObject.MyTableModelImpl(getContent(), 
                        columnName, columnClass);


            } else {
                setNullableData();
            }
            
        }
        fullTable();// заполняем таблицу
        tprops.setColWidth();// восстанавливаем размеры столбцов
        // задаём слушателя изменений размеров столбцов
        jTable1.getColumnModel().addColumnModelListener(Showlistener);
    }
    
    /**
     * определяет данные для таблицы в случае их отсутствия
     */
    private void setNullableData(){
        
        cols = new int[]{0,1,2,3,4,5,6};
        Object[] e = new Object[]{"Итого","",0,0,0,0,0};
        content = new Object[][]{e};
//        fullTable();
        
        
    }
    
    private void fullTable(){
        // заполняем модель и таблицу
        model = new MDIObject.MyTableModelImpl(getContent(), columnName, columnClass);
        MDIObject.fullTableData(cols, model, jTable1);
        
        // добавляем слушателя изменений модели
        model.addTableModelListener(calcListener);
        // выделяем первую строку
        jTable1.setRowSelectionInterval(0, 0);
        jTable1.setColumnSelectionInterval(0, 0);
    }
    
    private void calculateFlow(int selectRow){
        // проверяем чтобы строка была не последняя
        if(selectRow != jTable1.getRowCount() - 1){
            int x1 =  (int) jTable1.getValueAt(selectRow, 2);// текущие показания
            int x2 =  (int) jTable1.getValueAt(selectRow, 3);// предыдущие показания
            double koef = (double) jTable1.getValueAt(selectRow, 4);// коэффициент
            int ptp = (int) jTable1.getValueAt(selectRow, 5);// птп
            int flow;// расход за месяц
            flow = (int) Math.round((x1 - x2) * koef + ptp);
            jTable1.setValueAt(flow, selectRow, 6);

            // подсчитываем общий расход
            commonFlow();
        }
    }
    
    /**
     * расчёт общего расхода по данным таблицы
     */
    private void commonFlow(){
        int common = 0;
        for(int i= 0; i < jTable1.getRowCount() - 1; i++){
            common = common + (int)jTable1.getValueAt(i, 6);
        }
        // выводим значение в последней строке
        jTable1.setValueAt(common, jTable1.getRowCount() - 1, 6);
        flowRate = common;// объём потребления
        setName(Integer.toString(common));
    }
    
    private void createPopupMenu(){
        countMenu = new JPopupMenu();
        URL url;
        url = MDIObject.class.getClassLoader().getResource("Images/NewDocumentHS.png");
        
        // создаём пункты меню для добавления и удаления строк
        JMenuItem addItem = new JMenuItem("вставить строку выше", new ImageIcon(url));
        url = MDIObject.class.getClassLoader().getResource("Images/DeleteHS.png");
        JMenuItem removeItem = new JMenuItem("удалить строку", new ImageIcon(url));
        
        // добавляем действия для пунктов меню
        addItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertRow();
            }
        });
        
        removeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // проверяем выбрана ли строка для удаления
                if(jTable1.getSelectedRowCount() > 0){
                    int row = jTable1.getSelectedRow();
                    // проверяем, чтобы строка для удаления не была последней
                    if(row != (jTable1.getRowCount() - 1)){
                        removeRow();
                    } else {
                        
                        JOptionPane.showMessageDialog(ShowCountPanel.this.getParent().getParent(), 
                                "Последнюю строку удалять нельзя!", "AbonentGaz", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
        
        // добавляем их в меню
        countMenu.add(addItem);
        countMenu.add(removeItem);
        
        // назначаем меню для таблицы
        jTable1.setComponentPopupMenu(countMenu);
    }

    /**
     * Устанавливает код текущего года отчёта
     * @param reportYear the reportYear to set
     */
    public void setReportYear(int reportYear) {
        this.reportYear = reportYear;
        
        // для всех экземпляров коллекции устанавливаем код отчёта
        for(int i = 0; i < cdi.getCount(); i++)
            cdi.getItem(i).setReportYear(reportYear);
    }
    
    /**
     * Сохраняет введённые данные в базе данных
     */
    public void saveShow(){
        // проверяем установку флага автосохранения показаний, если установлен
        // то сохраняем сразу, иначе запрашиваем пользователя на проведение этой операции
        short count;
        if(autoSave){
            count = cdi.saveEntities();
            if(count > 0)
                MDIObject.getInformDialog(null, "Сохранено " + count + " записей.", InformDialog.InformType.SAVING);
        } else {
            // запроашиваем пользователя
            int button = JOptionPane.showConfirmDialog(null, "Сохранить изменения?", "AbonentGaz", JOptionPane.YES_NO_OPTION);
            if(button == JOptionPane.YES_OPTION){
                count = cdi.saveEntities();
                if(count > 0)
                    MDIObject.getInformDialog(null, "Сохранено " + count + " записей.", InformDialog.InformType.SAVING);
                else
                    JOptionPane.showMessageDialog(null, "Произошли ошибки во время операции сохранения.\n" +
                            "Обратитесь к разработчику.", "AbonentGaz", JOptionPane.ERROR_MESSAGE);
            }
        }
        changeData = false;// после сохранения сбрасываем флаг изменений данных
    }
    
    class CalculateListaner implements TableModelListener{

        @Override
        public void tableChanged(TableModelEvent e) {
            if(autoCalc){
                // определяем столбец и строку, в которых произсходит изменение
                int Row = e.getFirstRow();
                int Col = e.getColumn();

                // если изменения происходят не в последнем столбце таблицы
                // то выполняем расчёт расхода
                if(Col != (jTable1.getColumnCount() - 1)){
                    calculateFlow(Row);
                }
                if(Row != (jTable1.getRowCount() - 1)){
                    Closing cl = cdi.getItem(Row);

                    // изменяем её данные
                    cl.updateValue(Col, jTable1.getValueAt(Row, Col));
                }
            }
            changeData = true;// устанавливаем флаг произошедших изменений данных
        }
        
    }
    
    // модифицируемый заголовок таблицы
    class HeaderRenderer extends DefaultTableCellRenderer{
        
        // границы заголовков колонок
        private final Border border = BorderFactory.createBevelBorder(BevelBorder.RAISED);
        private final int horAlign;
        private final int vertAlign;

        public HeaderRenderer(int horAlign, int vertAlign) {
            this.horAlign = horAlign;
            this.vertAlign = vertAlign;
        }
        
        // метод возвращает визуальный компонент для прорисовки ячейки (заголовка)

        @Override
        public Component getTableCellRendererComponent(JTable table, 
            Object value, boolean isSelected, boolean hasFocus, int row, 
            int column) {
            
            // надпись из базового класса
            JLabel label = (JLabel) super.getTableCellRendererComponent(table, 
                    value, isSelected, hasFocus, row, column);
            
            // выравнивание строки заголовка
            label.setHorizontalAlignment(horAlign);
            label.setVerticalAlignment(vertAlign);
            
            // настройка цвета фона метки
//            float[] hsb = Color.RGBtoHSB(124, 124, 124, null);
            float[] hsb1 = Color.RGBtoHSB(224, 224, 224, null);
//            Color color = Color.getHSBColor(hsb[0], hsb[1], hsb[2]);
            Color color1 = Color.getHSBColor(hsb1[0], hsb1[1], hsb1[2]);
//            Border border = BorderFactory.createMatteBorder(8,1,8,1,color);
            label.setBackground(color1);
            label.setBorder(border);
//            label.setSize(label.getWidth(), 28);
            return label;
        }
        
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstKoef = new javax.swing.JList();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Адрес газопотребления", "№ счётчика", "Показания счётчика на конец месяца", "Показания счётчика на начало месяца", "Коэф-т привед к с у", "Произ-тех. потери", "Использован. объём газа всего за месяц"
            }
        ));
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable1.setCellSelectionEnabled(true);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jTable1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTable1PropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        lstKoef.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lstKoef.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        lstKoef.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstKoefMouseClicked(evt);
            }
        });
        lstKoef.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lstKoefKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(lstKoef);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTable1PropertyChange
        // при изменении модели данных удаляем слушателя изменений столбцов
//        System.out.println("property - " + evt.getPropertyName());
        if(evt.getPropertyName().equals("model"))
            jTable1.getColumnModel().removeColumnModelListener(Showlistener);
    }//GEN-LAST:event_jTable1PropertyChange

    private void lstKoefMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstKoefMouseClicked
        /*
        при двойном щелчке по выбранному элементу списка в столбец
        "Коэффициент приведения" ставим значение коэффициента из списка
        */
        if(evt.getClickCount() == 2){
            insertKoef();
        }
    }//GEN-LAST:event_lstKoefMouseClicked

    private void lstKoefKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lstKoefKeyPressed
        // при нажатии клавиши ВВода вставляем значение коэффициента
        // в таблицу
        
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            insertKoef();
        }
    }//GEN-LAST:event_lstKoefKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JList lstKoef;
    // End of variables declaration//GEN-END:variables

    /**
     * Представляет коэффициенты приведения, соответствующие выбранному месяцу
     * @param month целое, соответствующее выбранному месяцу от 0 (январь) до 11 (декабрь)
     */
    public void setReportMonth(int month) {
        reportMonth = month;
        
        // для всех экземпляров коллекции устанавливаем месяц отчёта
        for(int i = 0; i < cdi.getCount(); i++)
            cdi.getItem(i).setReportMonth(month);
        
        // модель данных для заполнения списка
        DefaultListModel dlm = new DefaultListModel();
        
        Object[] coef = new Object[cidi.getCount()];// массив для хранения данных
        for(int i = 0; i < coef.length; i++){
            // получаем данные
            SprcodeInstal codeinstal = cidi.getItem(i);
            coef[i] = String.valueOf(i + 1) + ": " + codeinstal.getMonth(reportMonth).doubleValue();
            dlm.addElement(coef[i]);// добавляем их в модель
        }
        
        // передаём модель списку для отображения
        lstKoef.setModel(dlm);

    }
    
    /**
     * Выполняет вставку значения коэффициента коррекции в выбранную строку таблицы
     */
    private void insertKoef(){
        // проверяем выбрана ли строка в таблице
        if(jTable1.getSelectedRowCount() > 0){
            // получаем значение коэффициента коррекции
            double koef = getKoefvalue(lstKoef.getSelectedIndex());

            // передаём значение коэффициента в таблицу
            jTable1.setValueAt(koef, jTable1.getSelectedRow(), 4);

            // выполняем расчёт
            calculateFlow(jTable1.getSelectedRow());
        } else {
            // если строка не выбрана
            JOptionPane.showInternalMessageDialog(this.getParent().getParent(), 
                    "Выберите строку для вставки значения!", "Abonentgaz", 
                    JOptionPane.WARNING_MESSAGE);

        }
    }
    
    /**
     * Функция определяет коэффициент коррекции из справочника по указанному индексу
     * @param index индекс элемента из справочника
     * @return значение коэффициента коррекции
     */
    private double getKoefvalue(int index){
        // получаем экземпляр справочной записи с коэффициентом
        SprcodeInstal instal = cidi.getItem(index);
        return instal.getMonth(reportMonth).doubleValue();
    }
    
    /**
     * Вставляет новую строку в таблицу выше выделенной строкой
     */
    private void insertRow(){
        // выводим диалоговое окно выбора счётчиков
        ObjectCountDialog dialog = new ObjectCountDialog();
        
        boolean retval = dialog.showDialog(ShowCountPanel.this);
        if(retval == true){
            Object[] values;
            int idObject = dialog.getIdObject();
            String count = dialog.getNumbercount();
            double code = getKoefvalue(dialog.getKoef() - 1);
            values = new Object[]{dialog.getAdress(),count,
                0,0,code,0,0};
            int row = jTable1.getSelectedRow();
            System.out.println("select row = " + row);
            // удаляем слушателя модели перед вставкой строки
            model.removeTableModelListener(calcListener);
            
            // добавляем данные в объект записи и сохранения
            Closing cl = new Closing(0);
            cl.setIdCompany(idCompany);
            cl.setIdObject(idObject);
            cl.setCountNumber(count);
            cl.setCurrentShow(0);
            cl.setResult(0);
            cl.setReport((short)1);
            cl.setYear((short) reportYear);
            cl.setMonth((short) reportMonth);
            cl.setReportMonth(reportMonth);
            cl.setReportYear(reportYear);
            cdi.addItem(cl, row);
            
            model.insertRow(row - 1, values);
            
            // добавляем слшателя модели
            model.addTableModelListener(calcListener);
            
            // переопределяем нередактируемые столбцы в модели таблицы
            cols = new int[]{0};
            model.setCellNoEditableList(cols);
            
            
            // выделяем вставленную строку
            jTable1.setRowSelectionInterval(row, row);
            jTable1.setColumnSelectionInterval(0, 0);
        }
    }
    
    /**
     * Удаляет выбранную строку из таблицы
     */
    private void removeRow(){
        // перед удалением строки удаляем слушателя изменений модели
        model.removeTableModelListener(calcListener);
        int Row = jTable1.getSelectedRow();
        model.removeRow(Row);// удаляем строку
        cdi.removeItem(Row);// удаляем элемент из коллекции
        
        // проверяем количество оставшихся строк таблицы
        if(model.getRowCount() == 1){
            // если осталась только одна строка с надписью Итого, то
            // запрещаем редактировать все столбцы
            cols = new int[]{0,1,2,3,4,5,6};
            model.setCellNoEditableList(cols);
        } else {
            // после удаления пересчитываем общий расход
            commonFlow();
        }
        // добавляем слушателя изменений модели
        model.addTableModelListener(calcListener);
    }
    

    /**
     * Отображает информацию по объектам потребителя и установленным счётчикам
     */
    private class ObjectCountDialog extends JPanel{

        private final JTable table;// таблица с данными
        private JButton okButton;// кнопка подтверждения выбора
        private JButton cancelButton;// кнопка отмены действия
        private boolean ok;
        private JDialog dialog;
        private String adress;
        private int idObject;
        private String numbercount;
        private int koef;
        private final ColumnModelListener columnListener;// слушатель изменений размеров столбцов таблицы
        private final UserProperties props;
        private final FrameLayoutManager flManager;// менеджер расположения окна

        public ObjectCountDialog() {
            super.setLayout(new BorderLayout());
            JPanel panel = new JPanel(new BorderLayout());// панель для размещения содержимого
            JPanel buttonPanel = new JPanel();
            // создаём таблицу и кнопки
            table = new JTable();
//            table.setModel(new javax.swing.table.DefaultTableModel(
//                new Object [][] {
//                    {null, null, null},
//                    {null, null, null},
//                    {null, null, null},
//                    {null, null, null}
//                },
//                new String [] {
//                    "Адрес газопотребления", "№ счётчика", "Коэф-т"
//                }
//            ));
            table.setCellSelectionEnabled(true);
            table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
            table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            
            // помещаем таблицу в панель прокрутки
            JScrollPane scrollPane = new JScrollPane(table);
            
            getData();// получаем данные и заполняем таблицу
            
            // класс для чтения данных из файла свойств
            props = new UserProperties("countpanel.properties");
            
            
            flManager = new FrameLayoutManager("countpanel.properties");
            
            // устанавливаем размеры столбцов
            MDIObject.setTablecolwidth(props, "countcolwidth", table);
        
            // создаём слушателя изменений размеров столбцов таблицы и регистрируем его
            columnListener = new ColumnModelListener(table, props, "countcolwidth");
            table.getColumnModel().addColumnModelListener(columnListener);
             
            createButton();// создаём кнопки и действия для них
            
            // размещаем кнопки на панели кнопок
            buttonPanel.add(okButton);
            buttonPanel.add(cancelButton);
            
            // добавляем панель с кнопками и таблицу
            panel.add(scrollPane, BorderLayout.CENTER);
            panel.add(buttonPanel, BorderLayout.SOUTH);
            super.add(panel, BorderLayout.CENTER);
        }
        
        /**
         * Выводит панель для выбора в диалоговом окне.
         * @param owner компонент в собственном фрейме или null
         */
        public boolean showDialog(Component owner){
            ok = false;
            
            // находим собственный фрейм
            Frame parent;
            if(owner instanceof Frame)
                parent = (Frame) owner;
            else
                parent = (Frame) SwingUtilities.getAncestorOfClass(Frame.class, owner);
            
            // если фрейм только что создан или был изменён,
            // создаём новое диалоговое окно
            if(dialog == null || dialog.getOwner() != parent){
                dialog = new JDialog(parent, true);
                dialog.getContentPane().add(this);
                
//                dialog.pack();
            }
            
            // создаём слушателя изменений размеров окна
            dialog.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    super.componentResized(e); //To change body of generated methods, choose Tools | Templates.
                    // записываем размеры окна диалога
                    flManager.setWidth(dialog.getWidth());
                    flManager.setHeight(dialog.getHeight());
                }
                
            });
            
            // задаём заголовок и выводим диалог на экран
            dialog.setTitle("Счетчики");
            
            // задаём размеры
            dialog.setSize(flManager.getWidth(), flManager.getHeight());
            
            // задаём расположение в центре вызывающей формы
            dialog.setLocationRelativeTo(parent);
            dialog.setVisible(true);
            return ok;// возвращаем результат выбора
        }
        
        /**
         * Получает данные запроса и заполняет таблицу
         */
        private void getData(){
            
            // получаем данные
            if(QDao.getEntities()){
                // переопределяем наименования полей (столбцов)
                String[] columnName = new String[]{"№","Код",
                    "Адрес объекта","№ счётчика","Код уст-ки"};
                MyTableModel model = new MDIObject.MyTableModelImpl(QDao.getContent(), 
                        columnName, QDao.getColumnClass());
                
                // заполняем таблицу
                MDIObject.fullTableData(null, model, table);
                
                // выделяем первую строку
                table.setRowSelectionInterval(0, 0);
                table.setColumnSelectionInterval(0, 0);
            }
            
        }
        
        /**
         * Создаёт кнопки и определяет действия для них
         */
        private void createButton(){
            okButton = new JButton("OK");
            okButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = table.getSelectedRow();
                    idObject = Integer.parseInt((String)table.getValueAt(row, 1));
                    adress = (String) table.getValueAt(row, 2);
                    numbercount = (String)table.getValueAt(row, 3);
                    koef = Integer.parseInt((String)table.getValueAt(row, 4));
//                    System.out.println(idObject + " " + adress + " " + 
//                            numbercount + " " + koef);
                    ok = true;
                    dialog.setVisible(false);
                }
            });
            
            cancelButton = new JButton("Отмена");
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dialog.setVisible(false);
                }
            });
        }
        
        
        /**
         * @return the adress
         */
        public String getAdress() {
            return adress;
        }

        /**
         * @return the idObject
         */
        public int getIdObject() {
            return idObject;
        }

        /**
         * @return the numbercount
         */
        public String getNumbercount() {
            return numbercount;
        }

        /**
         * @return the koef
         */
        public int getKoef() {
            return koef;
        }
    }
    /**
     * Хранит данные, полученные из таблицы Закрытия без реализации методов
     * записи изменений в базу данных
     */
    private class Closing extends entities.TableEntity{

        private int id;// идентификатор записи из таблицы базы данных
        private int idCompany;// код организации
        private int idObject;// код объекта
        private String countNumber;// № счётчика
        private int currentShow;// текущие показания
        private int result;// результат
        private short report;
        private String reportDate;// дата отчёта
        private String user;// имя пользователя
        private int year;// год
        private int month;// месяц
        private int reportYear;// год отчёта
        private int reportMonth;// месяц отчёта
        private final String tableName = "MONTHCLOSING";

        public Closing(int id) {
            super(id);
            super.setTablename(tableName);
        }

        /**
         * @return the reportYear
         */
        public int getReportYear() {
            return reportYear;
        }

        /**
         * @param reportYear the reportYear to set
         */
        public void setReportYear(int reportYear) {
            this.reportYear = reportYear;
        }

        /**
         * @return the reportMonth
         */
        public int getReportMonth() {
            return reportMonth;
        }

        /**
         * @param reportMonth the reportMonth to set
         */
        public void setReportMonth(int reportMonth) {
            this.reportMonth = reportMonth;
        }
        
        /**
         * @return the idCompany
         */
        public int getIdCompany() {
            return idCompany;
        }

        /**
         * @param idCompany the idCompany to set
         */
        public void setIdCompany(int idCompany) {
            this.idCompany = idCompany;
        }

        /**
         * @return the idObject
         */
        public int getIdObject() {
            return idObject;
        }

        /**
         * @param idObject the idObject to set
         */
        public void setIdObject(int idObject) {
            this.idObject = idObject;
        }

        /**
         * @return the countNumber
         */
        public String getCountNumber() {
            return countNumber;
        }

        /**
         * @param countNumber the countNumber to set
         */
        public void setCountNumber(String countNumber) {
            this.countNumber = countNumber;
        }

        /**
         * @return the currentShow
         */
        public int getCurrentShow() {
            return currentShow;
        }

        /**
         * @param currentShow the currentShow to set
         */
        public void setCurrentShow(int currentShow) {
            this.currentShow = currentShow;
        }

        /**
         * @return the result
         */
        public int getResult() {
            return result;
        }

        /**
         * @param result the result to set
         */
        public void setResult(int result) {
            this.result = result;
        }

        /**
         * @return the reportDate
         */
        public String getReportDate() {
            return reportDate;
        }

        /**
         * @param reportDate the reportDate to set
         */
        public void setReportDate(String reportDate) {
            this.reportDate = reportDate;
        }

        /**
         * @return the user
         */
        public String getUser() {
            return user;
        }

        /**
         * @param user the user to set
         */
        public void setUser(String user) {
            this.user = user;
        }

        /**
         * @return the report
         */
        public short getReport() {
            return report;
        }

        /**
         * @param report the report to set
         */
        public void setReport(short report) {
            this.report = report;
        }
        
        /**
         * Обновляет данные в выбранном поле (свойстве)
         * @param col номер поля (свойства)
         * @param value устанавливаемое значение
         */
        public void updateValue(int col, Object value){
            switch(col){
                case 1:
                    // номер счётчика
                    countNumber = (String) value;
                    break;
                case 2:
                    // текущие показания
                    currentShow = (int) value;
                    break;
                case 6:
                    // результат
                    result = (int) value;
                    break;
                default:
                    break;
            }
        }
        
        /**
         * Обновляет у записи флаг отчёта для дальнейшего использования:
         * 0 - не использовать, 1 - запись используется в дальнейшем
         */
        public boolean updateReport(){
            return updateEntity("isreport", 0);
        }

        /**
         * @return the year
         */
        public int getYear() {
            return year;
        }

        /**
         * @param year the year to set
         */
        public void setYear(short year) {
            this.year = year;
        }

        /**
         * @return the month
         */
        public int getMonth() {
            return month;
        }

        /**
         * @param month the month to set
         */
        public void setMonth(short month) {
            this.month = month;
        }

        /**
         * @return the id
         */
        @Override
        public int getId() {
            return id;
        }
        
        /**
         * сохранение данных записи в таблице базы данных
         */
        public short saveEntity(){
            short index;
            // проверяем наличие данных в поле CountNumber
            if(countNumber.equals(""))
                index = (short) (saveOnPower() == true ? 1 : 0);// если данных нет, значит расчёт по мощности
            else
                index = (short) (saveOnShowing() == true ? 1 : 0);// если данные есть, значит расчёт по показаниям
            return index;
        }
        
        /**
         * сохранение данных в случае закрытия по мощности
         */
        private boolean saveOnPower(){
            boolean retval = false;
            // проверяем наличие данных в поле ID
            if(id == 0){
                // идентификатор записи равен нулю (записи в таблице нет)
                // поэтому добавляем новую запись в таблицу, предварительно
                // устанавливая у неё значение поля isReport = false, чтобы
                // в дальнейшем эту запись не использовать
                report = 0;
                retval = insertRecord();
            } else {
                // это существующая запись из таблицы базы данных
                // сравниваем год и месяц отчета с последними годом и месяцем, которые были закрыты
                if(year == reportYear && month == reportMonth){
                    // если года и месяца равны, значит обновляется уже существующая запись
                    // с другими данными, поэтому просто перезаписываем эти данные
                    retval = updateRecord();
                } else {
                    // устанавливаем у неё значение поля isReport = false
                    // и добавляем новую запись
                    if(updateReportvalue()){
                        report = 0;
                        retval = insertRecord();
                    }
                }
            }
            return retval;
        }
        
        /**
         * сохранение данных в случае закрытия по показаниям счётчика
         */
        private boolean saveOnShowing(){
            boolean retval = false;
            // проверяем наличие данных в поле ID
            if(id == 0){
                // идентификатор записи равен нулю (записи в таблице нет)
                // поэтому добавляем новую запись в таблицу, предварительно
                // устанавливая у неё значение поля isReport = true
                report = 1;
                retval = insertRecord();
            } else {
                // это существующая запись из таблицы базы данных
                // сравниваем год и месяц отчета с последними годом и месяцем, которые были закрыты
                if(year == reportYear && month == reportMonth){
                    // если года и месяца равны, значит обновляется уже существующая запись
                    // с другими данными, поэтому просто перезаписываем эти данные
                    retval = updateRecord();
                } else {
                    // устанавливаем у неё значение поля isReport = false
                    // и добавляем новую запись
                    if(updateReportvalue()){
                        report = 1;
                        retval = insertRecord();
                    }
                }
            }
            return retval;
        }
        
        /**
         * добавление новой записи в таблицу базы данных
         */
        private boolean insertRecord(){
            String fieldname = "IDOBJECT, IDORGANIZATION, " +
                    "IDYEAR, COUNTERNUMBER, COUNTERSHOW, " +
                    "MONTHNUMBER, FLOW, ISREPORT, REPORTDATE, USERNAME";// строка, содержащаая наименование полей для добавления
            Object[] param = new Object[]{idObject,idCompany,
                year,countNumber,currentShow,month,result,report,
                reportDate,user};// массив значений
            String fieldvalue = "?,?,?,?,?,?,?,?,?,?";// строка, содержащаая знаки подстановки для добавления
            Class[] classValue = new Class[]{Integer.class,
                Integer.class,Integer.class,String.class,Integer.class,
                Short.class,Integer.class,Short.class,String.class,
                String.class} ;
            Runquery rq = new Runquery(tableName);// объект для выполнения запросов
            
            return rq.addEntity(fieldname, fieldvalue, classValue, param);// возвращаем результат выполнения запроса
        }
        
        /**
         * обновление существующей записи в таблице базы данных
         */
        private boolean updateRecord(){
            String sqlQuery = "UPDATE MONTHCLOSING B SET B.COUNTERNUMBER" +
                "='" + countNumber + "', B.COUNTERSHOW=" + currentShow +
                    "B.FLOW=" + result + ", B.REPORTDATE='" + reportDate +
                    "', B.USERNAME='" + user + "' WHERE B.ID=" + id + ";";
        System.out.println("sql=" + sqlQuery);
        Runquery rq = new Runquery();
        return rq.updateFieldValue(sqlQuery);
        }
        
        /**
         * сбрасывает значение флага isReport, устанавливая его в значение false
         */
        private boolean updateReportvalue(){
            return super.updateEntity("ISREPORT", 0);// возвращаем результат выполнения запроса
        }
    }
    
    private class ClosingDaoImpl{

        private final List<Closing> entity;
        public ClosingDaoImpl() {
            entity = new ArrayList<>();
        }

        /**
         * 
         * @param ID
         * @return 
         */
        public Closing getItem(int ID) {
            return entity.get(ID);
        }
        
        /**
         * 
         * @param closing 
         */
        public boolean addItem(Closing closing){
            return entity.add(closing);
        }

        /**
         * 
         * @param closing
         * @param pos 
         */
        public void addItem(Closing closing, int pos) {
            entity.add(pos, closing);
        }
        
        /**
         * 
         * @param pos 
         */
        public void removeItem(int pos){
            entity.remove(pos);
        }

        /**
         * 
         * @return 
         */
        public int getCount() {
            return entity.size();
        }
        
        /**
         * Сохраняет записи в базе данных
         * @return true в случае успеха, в противном случае возвращате false
         */
        public short saveEntities(){
            // для всех элементов коллекции выполняем метод сохранения данных
            short index = 0;// счётчик сохранённых записей
            for(int i = 0; i < entity.size(); i++){
                Closing closing = entity.get(i);
                index = (short) (index + closing.saveEntity());
            }
            return index;
            
        }
        
        /**
         * 
         */
        public void clearEntity(){
            entity.clear();
        }
    }

    /**
     * @return the changeData
     */
    public boolean isChangeData() {
        return changeData;
    }

    /**
     * @return the content
     */
    public Object[][] getContent() {
        return content;
    }

    /**
     * @return the flowRate
     */
    public int getFlowRate() {
        return flowRate;
    }
}
