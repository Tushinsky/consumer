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

    private MonthclosingDaoImpl mcDao;// ������ ������� � ������ �� �������� ������
    private final String[] criterias;
    private final String[] columnName;// ������ ��� ��������
    private final Class[] columnClass;
    private final ColumnModelListener Showlistener;// ��������� ��������� �������� �������� �������
    private final String filename;// ��� ����� ������� ��� ������ ��������� �������� ��������
    private final UserProperties props;
    private int[] cols;// ������ ��������������� ��������
    private Object[][] content;// ������ ������
    private MyTableModel model;// ������ ������ �������
    private final TableProperty tprops;
    private final SprcodeinstalDaoImpl cidi;
    private int reportMonth;// �������� �������� ������
    private int reportYear;// �������� �������� ����
    private JPopupMenu countMenu;// ����������� ���� ��� ������� ����� ��� �������� ������������ ������ � �������
    private final QueriesDao QDao;// ������ ��������� ������ � ��������� �����������
    private final String sqlQuery;// ������-������ �� ��������� ������ � ���������
    private final ClosingDaoImpl cdi;
    private int idCompany;
    private CalculateListaner calcListener;
    private boolean changeData = false;
    private boolean autoCalc = true;// ���� �������������� ���������
    private boolean autoSave = false;// ���� �������������� ���������
    private int flowRate;// �������� ����� �����������
    
    /**
     * Creates new form ShowCountPanel
     */
    public ShowCountPanel() {
        initComponents();
        criterias = new String[]{"idorganization","isreport"};
//        columnName = new String[]{"������������ � ����� �������",
//            "� ��������",
//            "��������� �������� �� ����� ������",
//            "��������� �������� �� ������ ������",
//            "����-� ������ � � �",
//            "���",
//            "������-��� ����� ����� �� �����"};
        columnName = new String[]{"������������ � ����� �������",
            "� ��������",
            "<html><body><p align='center'>��������� �������� �� ����� ������</body></html>",
            "<html><body><p align='center'>��������� �������� �� ������ ������</body></html>",
            "<html><body><p align='center'>����-� ������ � � �</body></html>",
            "<html><body><p align='center'>���</body></html>",
            "<html><body><p align='center'>������-��� ����� ����� �� �����</body></html>"};
        columnClass = new Class[]{String.class,String.class,Integer.class,
            Integer.class,Double.class,Integer.class,Integer.class};// � ����� ������
        filename = "actframe.properties";// ��� ����� �������
        
        // ������ ����� ��� ������ ����� �������
        props = new UserProperties(filename);
        tprops = new TableProperty(jTable1);
        
        // ��������� ������� ������� ������� � ����� �������
        setNullableData();
        calcListener = new CalculateListaner();// ������ ��������� ��������� ������
        fullTable();
        
        // ��������� ���������� �������
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
        
        // ������ ����� ��� ��������� ������ � ��������� ������������
        // ����������, ���������������� ��������� ������
        cidi = new SprcodeinstalDaoImpl();
        
        // ������������ � �������� �� ������
//        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
//        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
//        Enumeration<TableColumn> count = jTable1.getColumnModel().getColumns();
//        while(count.hasMoreElements()){
//            TableColumn column = count.nextElement();
//            column.setCellRenderer(centerRenderer);
//        }

        // ������������� ������� ��������
        MDIObject.setTablecolwidth(props, "colwidth", jTable1);
        
        // ������������� ��������� ��������
        Showlistener = new ColumnModelListener(jTable1, props, "colwidth");
        
        // ������ ����������� ���� ��� ���������� � �������� ����� �� �������
        createPopupMenu();
        
        // ���������� ������-������ �� ��������� ������ �� ���������
        sqlQuery = "SELECT Obj.ID, SPRC.CITY_NAME || ', ' || SPRS.STREET_NAME " + 
                "|| ', ' || Obj.ADDRES AS ADRESS, " +
            "Co.COUNTERNUMBER, Co.IDINSTALPLACE AS CODE_ FROM SprStreet SPRS INNER JOIN " +
            "Objects Obj ON SPRS.ID = Obj.IDSTREET INNER JOIN SPRCity SPRC ON SPRC.ID = Obj.IDCITY " +
            "INNER JOIN COUNTERS Co ON Obj.ID = Co.IDOBJECT WHERE Obj.IDORGANIZATION=?;";
        
        // ������ ������ ������� � ������ �� ���������
        QDao = new QueriesDao();
        QDao.setSqlQuery(sqlQuery);// ������� ��� ������-������
        cdi = new ClosingDaoImpl();
    }
    
    public void Showcount(int idCompany){
        this.idCompany = idCompany;
        int[] idCriterias = new int[]{idCompany,1};
        mcDao = new MonthclosingDaoImpl(criterias, idCriterias);
        Object[] criteria = new Object[]{idCompany};// �������� ������ ������
        QDao.setCriteria(criteria);
        cols = new int[]{0};// �����
        String colwidth = tprops.getColWidth();
        
        // ��������� ���������� ������
        if(mcDao.getCount() > 0){
            cdi.clearEntity();// �� ������ ������ ������� ������ ������
            
            // ������ ����
            // ���������� ������ ������� ������
            content = new Object[mcDao.getCount() + 1][];
            
            // ��� ���������� ��� ����� ���� ��������� ���� �� ����������
            for(int i = 0; i < getContent().length - 1; i++){
                Monthclosing closing = mcDao.getItem(i);
                
                // �������� ������ � ���� �������
                Object[] dataArray;
                dataArray = closing.toDataArray();
                Object[] e;// ������ ��� ������ ������
                e = new Object[7];
                e[0] = dataArray[1];// �����
                e[1] = dataArray[4];// � ��������
                e[2] = 0;// ������� ���������
                e[3] = dataArray[5];// ���������� ���������
                e[4] = (double)1.0;// ����������� �� ��������� ��� ���������� �������
                e[5] = 0;// ��� �� ��������� ��� ���������� �������
                e[6] = 0;// �����
                content[i] = e;// ������� ������ � ������ �����������
                
                // ������ ������ ��� ���������� ���������
                Closing cl = new Closing(closing.getId());
                cl.setIdCompany(idCompany);cl.setIdObject(closing.getIdobject());
                cl.setCountNumber(closing.getCounternumber());cl.setCurrentShow(0);
                cl.setResult(0);
                cl.setReport((short)1);
                cl.setYear((short) dataArray[2]);
                cl.setMonth((short) dataArray[3]);
                cl.setReportMonth(reportMonth);
                cl.setReportYear(reportYear);
                // � ��������� ��� � ������ �������
                boolean addItem = cdi.addItem(cl);
            }
            System.out.println("cdi.count=" + cdi.getCount());
            // ��������� ������ ������
            Object[] e = new Object[]{"�����","",null,null,null,null,0};
            content[getContent().length - 1] = e;
        } else {
            // ������ ���
            // �������� ������
            if(QDao.getEntities()){
                // ��������� ������ ��� �������� ������
                // (�, ��� �������, �����, � ��������, ��� ���������)
                Object[][] tmp = QDao.getContent();
                
                content = new Object[tmp.length + 1][];// ���������� ������ ������� ������
                // ��������� ��� � �����
                for(int i = 0; i < tmp.length; i++){
                    Object[] e = new Object[7];
                    e[0] = (String)tmp[i][2];// �����
                    e[1] = (String)tmp[i][3];// ����� ��������
                    e[2] = (int)0;// ������� ���������
                    e[3] = (int)0;// ���������� ���������
                    e[4] = getKoefvalue((Integer.parseInt((String)tmp[i][4]) - 1));// �����������
                    e[5] = (int)0;// ���
                    e[6] = (int)0;// �����
                    content[i] = e;

                    // ������ ������ ��� ���������� ���������
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
                    // � ��������� ��� � ������ �������
                    boolean addItem = cdi.addItem(cl);
                }
                // ��������� ������ ������
                Object[] e = new Object[]{"�����","",null,null,null,null,0};
                content[getContent().length - 1] = e;
                model = new MDIObject.MyTableModelImpl(getContent(), 
                        columnName, columnClass);


            } else {
                setNullableData();
            }
            
        }
        fullTable();// ��������� �������
        tprops.setColWidth();// ��������������� ������� ��������
        // ����� ��������� ��������� �������� ��������
        jTable1.getColumnModel().addColumnModelListener(Showlistener);
    }
    
    /**
     * ���������� ������ ��� ������� � ������ �� ����������
     */
    private void setNullableData(){
        
        cols = new int[]{0,1,2,3,4,5,6};
        Object[] e = new Object[]{"�����","",0,0,0,0,0};
        content = new Object[][]{e};
//        fullTable();
        
        
    }
    
    private void fullTable(){
        // ��������� ������ � �������
        model = new MDIObject.MyTableModelImpl(getContent(), columnName, columnClass);
        MDIObject.fullTableData(cols, model, jTable1);
        
        // ��������� ��������� ��������� ������
        model.addTableModelListener(calcListener);
        // �������� ������ ������
        jTable1.setRowSelectionInterval(0, 0);
        jTable1.setColumnSelectionInterval(0, 0);
    }
    
    private void calculateFlow(int selectRow){
        // ��������� ����� ������ ���� �� ���������
        if(selectRow != jTable1.getRowCount() - 1){
            int x1 =  (int) jTable1.getValueAt(selectRow, 2);// ������� ���������
            int x2 =  (int) jTable1.getValueAt(selectRow, 3);// ���������� ���������
            double koef = (double) jTable1.getValueAt(selectRow, 4);// �����������
            int ptp = (int) jTable1.getValueAt(selectRow, 5);// ���
            int flow;// ������ �� �����
            flow = (int) Math.round((x1 - x2) * koef + ptp);
            jTable1.setValueAt(flow, selectRow, 6);

            // ������������ ����� ������
            commonFlow();
        }
    }
    
    /**
     * ������ ������ ������� �� ������ �������
     */
    private void commonFlow(){
        int common = 0;
        for(int i= 0; i < jTable1.getRowCount() - 1; i++){
            common = common + (int)jTable1.getValueAt(i, 6);
        }
        // ������� �������� � ��������� ������
        jTable1.setValueAt(common, jTable1.getRowCount() - 1, 6);
        flowRate = common;// ����� �����������
        setName(Integer.toString(common));
    }
    
    private void createPopupMenu(){
        countMenu = new JPopupMenu();
        URL url;
        url = MDIObject.class.getClassLoader().getResource("Images/NewDocumentHS.png");
        
        // ������ ������ ���� ��� ���������� � �������� �����
        JMenuItem addItem = new JMenuItem("�������� ������ ����", new ImageIcon(url));
        url = MDIObject.class.getClassLoader().getResource("Images/DeleteHS.png");
        JMenuItem removeItem = new JMenuItem("������� ������", new ImageIcon(url));
        
        // ��������� �������� ��� ������� ����
        addItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertRow();
            }
        });
        
        removeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ��������� ������� �� ������ ��� ��������
                if(jTable1.getSelectedRowCount() > 0){
                    int row = jTable1.getSelectedRow();
                    // ���������, ����� ������ ��� �������� �� ���� ���������
                    if(row != (jTable1.getRowCount() - 1)){
                        removeRow();
                    } else {
                        
                        JOptionPane.showMessageDialog(ShowCountPanel.this.getParent().getParent(), 
                                "��������� ������ ������� ������!", "AbonentGaz", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
        
        // ��������� �� � ����
        countMenu.add(addItem);
        countMenu.add(removeItem);
        
        // ��������� ���� ��� �������
        jTable1.setComponentPopupMenu(countMenu);
    }

    /**
     * ������������� ��� �������� ���� ������
     * @param reportYear the reportYear to set
     */
    public void setReportYear(int reportYear) {
        this.reportYear = reportYear;
        
        // ��� ���� ����������� ��������� ������������� ��� ������
        for(int i = 0; i < cdi.getCount(); i++)
            cdi.getItem(i).setReportYear(reportYear);
    }
    
    /**
     * ��������� �������� ������ � ���� ������
     */
    public void saveShow(){
        // ��������� ��������� ����� �������������� ���������, ���� ����������
        // �� ��������� �����, ����� ����������� ������������ �� ���������� ���� ��������
        short count;
        if(autoSave){
            count = cdi.saveEntities();
            if(count > 0)
                MDIObject.getInformDialog(null, "��������� " + count + " �������.", InformDialog.InformType.SAVING);
        } else {
            // ������������ ������������
            int button = JOptionPane.showConfirmDialog(null, "��������� ���������?", "AbonentGaz", JOptionPane.YES_NO_OPTION);
            if(button == JOptionPane.YES_OPTION){
                count = cdi.saveEntities();
                if(count > 0)
                    MDIObject.getInformDialog(null, "��������� " + count + " �������.", InformDialog.InformType.SAVING);
                else
                    JOptionPane.showMessageDialog(null, "��������� ������ �� ����� �������� ����������.\n" +
                            "���������� � ������������.", "AbonentGaz", JOptionPane.ERROR_MESSAGE);
            }
        }
        changeData = false;// ����� ���������� ���������� ���� ��������� ������
    }
    
    class CalculateListaner implements TableModelListener{

        @Override
        public void tableChanged(TableModelEvent e) {
            if(autoCalc){
                // ���������� ������� � ������, � ������� ����������� ���������
                int Row = e.getFirstRow();
                int Col = e.getColumn();

                // ���� ��������� ���������� �� � ��������� ������� �������
                // �� ��������� ������ �������
                if(Col != (jTable1.getColumnCount() - 1)){
                    calculateFlow(Row);
                }
                if(Row != (jTable1.getRowCount() - 1)){
                    Closing cl = cdi.getItem(Row);

                    // �������� � ������
                    cl.updateValue(Col, jTable1.getValueAt(Row, Col));
                }
            }
            changeData = true;// ������������� ���� ������������ ��������� ������
        }
        
    }
    
    // �������������� ��������� �������
    class HeaderRenderer extends DefaultTableCellRenderer{
        
        // ������� ���������� �������
        private final Border border = BorderFactory.createBevelBorder(BevelBorder.RAISED);
        private final int horAlign;
        private final int vertAlign;

        public HeaderRenderer(int horAlign, int vertAlign) {
            this.horAlign = horAlign;
            this.vertAlign = vertAlign;
        }
        
        // ����� ���������� ���������� ��������� ��� ���������� ������ (���������)

        @Override
        public Component getTableCellRendererComponent(JTable table, 
            Object value, boolean isSelected, boolean hasFocus, int row, 
            int column) {
            
            // ������� �� �������� ������
            JLabel label = (JLabel) super.getTableCellRendererComponent(table, 
                    value, isSelected, hasFocus, row, column);
            
            // ������������ ������ ���������
            label.setHorizontalAlignment(horAlign);
            label.setVerticalAlignment(vertAlign);
            
            // ��������� ����� ���� �����
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
                "����� ���������������", "� ��������", "��������� �������� �� ����� ������", "��������� �������� �� ������ ������", "����-� ������ � � �", "�����-���. ������", "�����������. ����� ���� ����� �� �����"
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
        // ��� ��������� ������ ������ ������� ��������� ��������� ��������
//        System.out.println("property - " + evt.getPropertyName());
        if(evt.getPropertyName().equals("model"))
            jTable1.getColumnModel().removeColumnModelListener(Showlistener);
    }//GEN-LAST:event_jTable1PropertyChange

    private void lstKoefMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstKoefMouseClicked
        /*
        ��� ������� ������ �� ���������� �������� ������ � �������
        "����������� ����������" ������ �������� ������������ �� ������
        */
        if(evt.getClickCount() == 2){
            insertKoef();
        }
    }//GEN-LAST:event_lstKoefMouseClicked

    private void lstKoefKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lstKoefKeyPressed
        // ��� ������� ������� ����� ��������� �������� ������������
        // � �������
        
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
     * ������������ ������������ ����������, ��������������� ���������� ������
     * @param month �����, ��������������� ���������� ������ �� 0 (������) �� 11 (�������)
     */
    public void setReportMonth(int month) {
        reportMonth = month;
        
        // ��� ���� ����������� ��������� ������������� ����� ������
        for(int i = 0; i < cdi.getCount(); i++)
            cdi.getItem(i).setReportMonth(month);
        
        // ������ ������ ��� ���������� ������
        DefaultListModel dlm = new DefaultListModel();
        
        Object[] coef = new Object[cidi.getCount()];// ������ ��� �������� ������
        for(int i = 0; i < coef.length; i++){
            // �������� ������
            SprcodeInstal codeinstal = cidi.getItem(i);
            coef[i] = String.valueOf(i + 1) + ": " + codeinstal.getMonth(reportMonth).doubleValue();
            dlm.addElement(coef[i]);// ��������� �� � ������
        }
        
        // ������� ������ ������ ��� �����������
        lstKoef.setModel(dlm);

    }
    
    /**
     * ��������� ������� �������� ������������ ��������� � ��������� ������ �������
     */
    private void insertKoef(){
        // ��������� ������� �� ������ � �������
        if(jTable1.getSelectedRowCount() > 0){
            // �������� �������� ������������ ���������
            double koef = getKoefvalue(lstKoef.getSelectedIndex());

            // ������� �������� ������������ � �������
            jTable1.setValueAt(koef, jTable1.getSelectedRow(), 4);

            // ��������� ������
            calculateFlow(jTable1.getSelectedRow());
        } else {
            // ���� ������ �� �������
            JOptionPane.showInternalMessageDialog(this.getParent().getParent(), 
                    "�������� ������ ��� ������� ��������!", "Abonentgaz", 
                    JOptionPane.WARNING_MESSAGE);

        }
    }
    
    /**
     * ������� ���������� ����������� ��������� �� ����������� �� ���������� �������
     * @param index ������ �������� �� �����������
     * @return �������� ������������ ���������
     */
    private double getKoefvalue(int index){
        // �������� ��������� ���������� ������ � �������������
        SprcodeInstal instal = cidi.getItem(index);
        return instal.getMonth(reportMonth).doubleValue();
    }
    
    /**
     * ��������� ����� ������ � ������� ���� ���������� �������
     */
    private void insertRow(){
        // ������� ���������� ���� ������ ���������
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
            // ������� ��������� ������ ����� �������� ������
            model.removeTableModelListener(calcListener);
            
            // ��������� ������ � ������ ������ � ����������
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
            
            // ��������� �������� ������
            model.addTableModelListener(calcListener);
            
            // �������������� ��������������� ������� � ������ �������
            cols = new int[]{0};
            model.setCellNoEditableList(cols);
            
            
            // �������� ����������� ������
            jTable1.setRowSelectionInterval(row, row);
            jTable1.setColumnSelectionInterval(0, 0);
        }
    }
    
    /**
     * ������� ��������� ������ �� �������
     */
    private void removeRow(){
        // ����� ��������� ������ ������� ��������� ��������� ������
        model.removeTableModelListener(calcListener);
        int Row = jTable1.getSelectedRow();
        model.removeRow(Row);// ������� ������
        cdi.removeItem(Row);// ������� ������� �� ���������
        
        // ��������� ���������� ���������� ����� �������
        if(model.getRowCount() == 1){
            // ���� �������� ������ ���� ������ � �������� �����, ��
            // ��������� ������������� ��� �������
            cols = new int[]{0,1,2,3,4,5,6};
            model.setCellNoEditableList(cols);
        } else {
            // ����� �������� ������������� ����� ������
            commonFlow();
        }
        // ��������� ��������� ��������� ������
        model.addTableModelListener(calcListener);
    }
    

    /**
     * ���������� ���������� �� �������� ����������� � ������������� ���������
     */
    private class ObjectCountDialog extends JPanel{

        private final JTable table;// ������� � �������
        private JButton okButton;// ������ ������������� ������
        private JButton cancelButton;// ������ ������ ��������
        private boolean ok;
        private JDialog dialog;
        private String adress;
        private int idObject;
        private String numbercount;
        private int koef;
        private final ColumnModelListener columnListener;// ��������� ��������� �������� �������� �������
        private final UserProperties props;
        private final FrameLayoutManager flManager;// �������� ������������ ����

        public ObjectCountDialog() {
            super.setLayout(new BorderLayout());
            JPanel panel = new JPanel(new BorderLayout());// ������ ��� ���������� �����������
            JPanel buttonPanel = new JPanel();
            // ������ ������� � ������
            table = new JTable();
//            table.setModel(new javax.swing.table.DefaultTableModel(
//                new Object [][] {
//                    {null, null, null},
//                    {null, null, null},
//                    {null, null, null},
//                    {null, null, null}
//                },
//                new String [] {
//                    "����� ���������������", "� ��������", "����-�"
//                }
//            ));
            table.setCellSelectionEnabled(true);
            table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
            table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            
            // �������� ������� � ������ ���������
            JScrollPane scrollPane = new JScrollPane(table);
            
            getData();// �������� ������ � ��������� �������
            
            // ����� ��� ������ ������ �� ����� �������
            props = new UserProperties("countpanel.properties");
            
            
            flManager = new FrameLayoutManager("countpanel.properties");
            
            // ������������� ������� ��������
            MDIObject.setTablecolwidth(props, "countcolwidth", table);
        
            // ������ ��������� ��������� �������� �������� ������� � ������������ ���
            columnListener = new ColumnModelListener(table, props, "countcolwidth");
            table.getColumnModel().addColumnModelListener(columnListener);
             
            createButton();// ������ ������ � �������� ��� ���
            
            // ��������� ������ �� ������ ������
            buttonPanel.add(okButton);
            buttonPanel.add(cancelButton);
            
            // ��������� ������ � �������� � �������
            panel.add(scrollPane, BorderLayout.CENTER);
            panel.add(buttonPanel, BorderLayout.SOUTH);
            super.add(panel, BorderLayout.CENTER);
        }
        
        /**
         * ������� ������ ��� ������ � ���������� ����.
         * @param owner ��������� � ����������� ������ ��� null
         */
        public boolean showDialog(Component owner){
            ok = false;
            
            // ������� ����������� �����
            Frame parent;
            if(owner instanceof Frame)
                parent = (Frame) owner;
            else
                parent = (Frame) SwingUtilities.getAncestorOfClass(Frame.class, owner);
            
            // ���� ����� ������ ��� ������ ��� ��� ������,
            // ������ ����� ���������� ����
            if(dialog == null || dialog.getOwner() != parent){
                dialog = new JDialog(parent, true);
                dialog.getContentPane().add(this);
                
//                dialog.pack();
            }
            
            // ������ ��������� ��������� �������� ����
            dialog.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    super.componentResized(e); //To change body of generated methods, choose Tools | Templates.
                    // ���������� ������� ���� �������
                    flManager.setWidth(dialog.getWidth());
                    flManager.setHeight(dialog.getHeight());
                }
                
            });
            
            // ����� ��������� � ������� ������ �� �����
            dialog.setTitle("��������");
            
            // ����� �������
            dialog.setSize(flManager.getWidth(), flManager.getHeight());
            
            // ����� ������������ � ������ ���������� �����
            dialog.setLocationRelativeTo(parent);
            dialog.setVisible(true);
            return ok;// ���������� ��������� ������
        }
        
        /**
         * �������� ������ ������� � ��������� �������
         */
        private void getData(){
            
            // �������� ������
            if(QDao.getEntities()){
                // �������������� ������������ ����� (��������)
                String[] columnName = new String[]{"�","���",
                    "����� �������","� ��������","��� ���-��"};
                MyTableModel model = new MDIObject.MyTableModelImpl(QDao.getContent(), 
                        columnName, QDao.getColumnClass());
                
                // ��������� �������
                MDIObject.fullTableData(null, model, table);
                
                // �������� ������ ������
                table.setRowSelectionInterval(0, 0);
                table.setColumnSelectionInterval(0, 0);
            }
            
        }
        
        /**
         * ������ ������ � ���������� �������� ��� ���
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
            
            cancelButton = new JButton("������");
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
     * ������ ������, ���������� �� ������� �������� ��� ���������� �������
     * ������ ��������� � ���� ������
     */
    private class Closing extends entities.TableEntity{

        private int id;// ������������� ������ �� ������� ���� ������
        private int idCompany;// ��� �����������
        private int idObject;// ��� �������
        private String countNumber;// � ��������
        private int currentShow;// ������� ���������
        private int result;// ���������
        private short report;
        private String reportDate;// ���� ������
        private String user;// ��� ������������
        private int year;// ���
        private int month;// �����
        private int reportYear;// ��� ������
        private int reportMonth;// ����� ������
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
         * ��������� ������ � ��������� ���� (��������)
         * @param col ����� ���� (��������)
         * @param value ��������������� ��������
         */
        public void updateValue(int col, Object value){
            switch(col){
                case 1:
                    // ����� ��������
                    countNumber = (String) value;
                    break;
                case 2:
                    // ������� ���������
                    currentShow = (int) value;
                    break;
                case 6:
                    // ���������
                    result = (int) value;
                    break;
                default:
                    break;
            }
        }
        
        /**
         * ��������� � ������ ���� ������ ��� ����������� �������������:
         * 0 - �� ������������, 1 - ������ ������������ � ����������
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
         * ���������� ������ ������ � ������� ���� ������
         */
        public short saveEntity(){
            short index;
            // ��������� ������� ������ � ���� CountNumber
            if(countNumber.equals(""))
                index = (short) (saveOnPower() == true ? 1 : 0);// ���� ������ ���, ������ ������ �� ��������
            else
                index = (short) (saveOnShowing() == true ? 1 : 0);// ���� ������ ����, ������ ������ �� ����������
            return index;
        }
        
        /**
         * ���������� ������ � ������ �������� �� ��������
         */
        private boolean saveOnPower(){
            boolean retval = false;
            // ��������� ������� ������ � ���� ID
            if(id == 0){
                // ������������� ������ ����� ���� (������ � ������� ���)
                // ������� ��������� ����� ������ � �������, ��������������
                // ������������ � �� �������� ���� isReport = false, �����
                // � ���������� ��� ������ �� ������������
                report = 0;
                retval = insertRecord();
            } else {
                // ��� ������������ ������ �� ������� ���� ������
                // ���������� ��� � ����� ������ � ���������� ����� � �������, ������� ���� �������
                if(year == reportYear && month == reportMonth){
                    // ���� ���� � ������ �����, ������ ����������� ��� ������������ ������
                    // � ������� �������, ������� ������ �������������� ��� ������
                    retval = updateRecord();
                } else {
                    // ������������� � �� �������� ���� isReport = false
                    // � ��������� ����� ������
                    if(updateReportvalue()){
                        report = 0;
                        retval = insertRecord();
                    }
                }
            }
            return retval;
        }
        
        /**
         * ���������� ������ � ������ �������� �� ���������� ��������
         */
        private boolean saveOnShowing(){
            boolean retval = false;
            // ��������� ������� ������ � ���� ID
            if(id == 0){
                // ������������� ������ ����� ���� (������ � ������� ���)
                // ������� ��������� ����� ������ � �������, ��������������
                // ������������ � �� �������� ���� isReport = true
                report = 1;
                retval = insertRecord();
            } else {
                // ��� ������������ ������ �� ������� ���� ������
                // ���������� ��� � ����� ������ � ���������� ����� � �������, ������� ���� �������
                if(year == reportYear && month == reportMonth){
                    // ���� ���� � ������ �����, ������ ����������� ��� ������������ ������
                    // � ������� �������, ������� ������ �������������� ��� ������
                    retval = updateRecord();
                } else {
                    // ������������� � �� �������� ���� isReport = false
                    // � ��������� ����� ������
                    if(updateReportvalue()){
                        report = 1;
                        retval = insertRecord();
                    }
                }
            }
            return retval;
        }
        
        /**
         * ���������� ����� ������ � ������� ���� ������
         */
        private boolean insertRecord(){
            String fieldname = "IDOBJECT, IDORGANIZATION, " +
                    "IDYEAR, COUNTERNUMBER, COUNTERSHOW, " +
                    "MONTHNUMBER, FLOW, ISREPORT, REPORTDATE, USERNAME";// ������, ����������� ������������ ����� ��� ����������
            Object[] param = new Object[]{idObject,idCompany,
                year,countNumber,currentShow,month,result,report,
                reportDate,user};// ������ ��������
            String fieldvalue = "?,?,?,?,?,?,?,?,?,?";// ������, ����������� ����� ����������� ��� ����������
            Class[] classValue = new Class[]{Integer.class,
                Integer.class,Integer.class,String.class,Integer.class,
                Short.class,Integer.class,Short.class,String.class,
                String.class} ;
            Runquery rq = new Runquery(tableName);// ������ ��� ���������� ��������
            
            return rq.addEntity(fieldname, fieldvalue, classValue, param);// ���������� ��������� ���������� �������
        }
        
        /**
         * ���������� ������������ ������ � ������� ���� ������
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
         * ���������� �������� ����� isReport, ������������ ��� � �������� false
         */
        private boolean updateReportvalue(){
            return super.updateEntity("ISREPORT", 0);// ���������� ��������� ���������� �������
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
         * ��������� ������ � ���� ������
         * @return true � ������ ������, � ��������� ������ ���������� false
         */
        public short saveEntities(){
            // ��� ���� ��������� ��������� ��������� ����� ���������� ������
            short index = 0;// ������� ���������� �������
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
