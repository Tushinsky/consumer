/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import abonentgaz.ColumnModelListener;
import abonentgaz.Printer_Job;
import abonentgaz.SplitLayoutManager;
import abonentgaz.TableCell_Renderer;
import abonentgaz.TableProperty;
import abonentgaz.UserProperties;
import dao_impl.*;
import entities.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import multispancell.AttributiveCellTableModel;
import multispancell.CellSpan;
import multispancell.MultiSpanCellTableEx;


/**
 *
 * @author lera
 */
public class ObjectFrame extends javax.swing.JInternalFrame {

    private int idCompany;// идентификатор компании (код)
    private int idObject;// идентификатор (код) объекта
    private final String fileNameProperty = "objectframe.properties";
    private SprcityDaoImpl sprCityDao;
    private SprstreetDaoImpl sprStreetDao;
    private ObjectsDaoImpl objectsDao;// доступ к данным по объектам
    private TurndownDaoImpl objectTurnDao;
    private TableDaoImpl firstItemDao;
    private TableDaoImpl secondItemDao;
//    private EquipmentDaoImpl equipmentDao;
    private SprcontrolersDaoImpl sprControlerDao;
    private SprcaseDaoImpl sprCaseDao;
    private SprplumbsinstalplaseDaoImpl sprInstalDao;
//    private ProjectsDaoImpl projectsDao;
//    private PlombsDaoImpl plombsDao;
//    private BlocknoteDaoImpl blocknoteDao;
//    private EquipturndownDaoImpl equipTurnDao;
    private TableDaoImpl sprTableDao;// объект доступа для справочников контролёров, КИП и оборудования
    private TableDaoImpl anothersprTableDao;// объект доступа для справочников мест установки пломб и причин отключения
    private SprequipmentDaoImpl sprEquipDao;
    private final UserProperties props = new UserProperties(fileNameProperty);
    private final TableProperty tprops = new TableProperty();// класс для установки размеров столбцов таблиц
    private ColumnModelListener ObjectcolumnListener;
    private ColumnModelListener ObjectturncolumnListener;
    private ColumnModelListener UzelcolumnListener;
    private ColumnModelListener LosecolumnListener;
    private ColumnModelListener CommoncolumnListener;
    private final JComboBox cityBox;// списки населённых пунктов
    private final JComboBox streetBox;// и улиц для таблицы объектов
    private final JComboBox controlerBox;// список контролёров в таблице отключения объектов
    private final JComboBox caseBox;// список причин отключения в таблице отключения объектов
    private final JComboBox instalBox;// список мест установки пломб в таблице отключения объектов
    private final JComboBox otherSPRBox;// список контролёров, справочников КИП и оборудования
    private final JComboBox anotherSPRBox;// список мест установки пломб и причин отключения
    private final JComboBox sprEquipBox;// справочник оборудования
    private final ObjectInformQuery oiq;
    /**
     * Creates new form ObjectFrame
     */
    public ObjectFrame() {
        initComponents();
        cityBox = new JComboBox();// списки населённых пунктов
        streetBox = new JComboBox();// и улиц для таблицы объектов
        controlerBox = new JComboBox();// список контролёров в таблице отключения объектов
        caseBox = new JComboBox();// список причин отключения в таблице отключения объектов
        instalBox = new JComboBox();// список мест установки пломб в таблице отключения объектов
        otherSPRBox = new JComboBox();// список контролёров, справочников КИП и оборудования
        anotherSPRBox = new JComboBox();// список мест установки пломб и причин отключения
        sprEquipBox = new JComboBox();
        tblCommon = new MultiSpanCellTableEx();
        Object[][] content = new Object[1][7];
        String[] colName = new String[]{"№","A","B","C","D","E","F"};
        tblCommon.setModel(new AttributiveCellTableModel(content, colName));
        jScrollPane3 = new JScrollPane(tblCommon);
        jPanel1.setLayout(new BorderLayout());
        jPanel1.add(jScrollPane3, BorderLayout.CENTER);
        tblCommon.revalidate();
        tblCommon.repaint();
        jPanel1.revalidate();
        jPanel1.repaint();
        tblCommon.getTableHeader().setAlignmentX(CENTER_ALIGNMENT);
        tblCommon.getTableHeader().setAlignmentY(TOP_ALIGNMENT);
        oiq = new ObjectInformQuery();
//        tblCommon.setUI(new MultiSpanCellTableUI());
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        objectPopupMenu = new javax.swing.JPopupMenu();
        mnuAddnewobject = new javax.swing.JMenuItem();
        mnuRemoveobject = new javax.swing.JMenuItem();
        mnuUpdateobject = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mnuExportobject = new javax.swing.JMenu();
        mnuExportObjectExcel = new javax.swing.JMenuItem();
        mnuExportObjectODS = new javax.swing.JMenuItem();
        mnuExportObjectCSV = new javax.swing.JMenuItem();
        turnObjectPopupMenu = new javax.swing.JPopupMenu();
        mnuTurnAddNew = new javax.swing.JMenuItem();
        mnuTurnRemove = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        mnuExportTurn = new javax.swing.JMenu();
        mnuExportTurnExcel = new javax.swing.JMenuItem();
        mnuExportTurnODS = new javax.swing.JMenuItem();
        mnuExportTurnCSV = new javax.swing.JMenuItem();
        vertSplitPane = new javax.swing.JSplitPane();
        horSplitPane = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTurnObject = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblObject = new javax.swing.JTable();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCommon = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        uzelCombo = new javax.swing.JComboBox();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblUzel = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblNote = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblPlomb = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblLose = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        loseCombo = new javax.swing.JComboBox();
        losePanel = new javax.swing.JPanel();
        lblLose = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        usingCombo = new javax.swing.JComboBox();
        equipSplitPane = new javax.swing.JSplitPane();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblEquipment = new javax.swing.JTable();
        jScrollPane12 = new javax.swing.JScrollPane();
        tblTurnEquipment = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        projectSplitPane = new javax.swing.JSplitPane();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblProject = new javax.swing.JTable();
        jScrollPane13 = new javax.swing.JScrollPane();
        tblLauncher = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblAct = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        cmbAct = new javax.swing.JComboBox<>();

        mnuAddnewobject.setText("Добавить новый объект");
        mnuAddnewobject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuAddnewobjectActionPerformed(evt);
            }
        });
        objectPopupMenu.add(mnuAddnewobject);

        mnuRemoveobject.setText("Удалить объект");
        mnuRemoveobject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuRemoveobjectActionPerformed(evt);
            }
        });
        objectPopupMenu.add(mnuRemoveobject);

        mnuUpdateobject.setText("Обновить данные");
        mnuUpdateobject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuUpdateobjectActionPerformed(evt);
            }
        });
        objectPopupMenu.add(mnuUpdateobject);
        objectPopupMenu.add(jSeparator1);

        mnuExportobject.setText("Экспорт таблицы");

        mnuExportObjectExcel.setText("экспорт таблицы в файл XLS");
        mnuExportobject.add(mnuExportObjectExcel);

        mnuExportObjectODS.setText("экспорт таблицы в файл ODS");
        mnuExportobject.add(mnuExportObjectODS);

        mnuExportObjectCSV.setText("экспорт таблицы в файл CSV");
        mnuExportobject.add(mnuExportObjectCSV);

        objectPopupMenu.add(mnuExportobject);

        mnuTurnAddNew.setText("Добавить новую запись");
        mnuTurnAddNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuTurnAddNewActionPerformed(evt);
            }
        });
        turnObjectPopupMenu.add(mnuTurnAddNew);

        mnuTurnRemove.setText("Удалить запись");
        mnuTurnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuTurnRemoveActionPerformed(evt);
            }
        });
        turnObjectPopupMenu.add(mnuTurnRemove);
        turnObjectPopupMenu.add(jSeparator4);

        mnuExportTurn.setText("экспорт таблицы");

        mnuExportTurnExcel.setText("экспорт таблицы в файл XLS");
        mnuExportTurn.add(mnuExportTurnExcel);

        mnuExportTurnODS.setText("экспорт таблицы в файл ODS");
        mnuExportTurn.add(mnuExportTurnODS);

        mnuExportTurnCSV.setText("экспорт таблицы в файл CSV");
        mnuExportTurn.add(mnuExportTurnCSV);

        turnObjectPopupMenu.add(mnuExportTurn);

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Объекты");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFrameIcon(null);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        vertSplitPane.setDividerLocation(200);
        vertSplitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        vertSplitPane.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        vertSplitPane.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                vertSplitPanePropertyChange(evt);
            }
        });

        horSplitPane.setDividerLocation(500);
        horSplitPane.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        horSplitPane.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                horSplitPanePropertyChange(evt);
            }
        });

        tblTurnObject.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblTurnObject.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblTurnObject.setCellSelectionEnabled(true);
        tblTurnObject.setComponentPopupMenu(turnObjectPopupMenu);
        tblTurnObject.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jScrollPane1.setViewportView(tblTurnObject);

        horSplitPane.setRightComponent(jScrollPane1);

        tblObject.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblObject.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblObject.setCellSelectionEnabled(true);
        tblObject.setComponentPopupMenu(objectPopupMenu);
        tblObject.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        tblObject.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblObjectMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblObjectMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblObjectMouseReleased(evt);
            }
        });
        tblObject.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblObjectKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tblObject);

        horSplitPane.setLeftComponent(jScrollPane2);

        vertSplitPane.setLeftComponent(horSplitPane);

        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });
        jTabbedPane1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTabbedPane1PropertyChange(evt);
            }
        });

        tblCommon.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblCommon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblCommon.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblCommon.setCellSelectionEnabled(true);
        tblCommon.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jScrollPane3.setViewportView(tblCommon);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 831, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Общая", jPanel1);

        uzelCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "выберите КИП", "счётчик", "корректор", "датчики" }));
        uzelCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uzelComboActionPerformed(evt);
            }
        });

        tblUzel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblUzel.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblUzel.setCellSelectionEnabled(true);
        tblUzel.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jScrollPane4.setViewportView(tblUzel);

        jLabel1.setText("Узел учёта");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(uzelCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(590, Short.MAX_VALUE))
            .addComponent(jScrollPane4)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(uzelCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Узел учёта", jPanel2);

        tblNote.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblNote.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblNote.setCellSelectionEnabled(true);
        tblNote.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jScrollPane5.setViewportView(tblNote);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 831, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Блокнот", jPanel3);

        tblPlomb.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Заголовок 1", "Заголовок 2", "Заголовок 3", "Заголовок 4"
            }
        ));
        tblPlomb.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblPlomb.setCellSelectionEnabled(true);
        tblPlomb.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jScrollPane6.setViewportView(tblPlomb);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 831, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Пломбы", jPanel4);

        tblLose.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblLose.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblLose.setCellSelectionEnabled(true);
        tblLose.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane7.setViewportView(tblLose);

        jLabel2.setText("тип потерь");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        loseCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "выберите тип потерь", "газопровод ВД", "газопровод СД", "газопровод РНД", "газопровод ДНД", "регуляторы" }));
        loseCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loseComboActionPerformed(evt);
            }
        });

        losePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Исходные данные"), "Исходные данные"));
        losePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                losePanelMouseClicked(evt);
            }
        });

        lblLose.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblLose.setText("jLabel3");
        lblLose.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout losePanelLayout = new javax.swing.GroupLayout(losePanel);
        losePanel.setLayout(losePanelLayout);
        losePanelLayout.setHorizontalGroup(
            losePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblLose, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        losePanelLayout.setVerticalGroup(
            losePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(losePanelLayout.createSequentialGroup()
                .addComponent(lblLose)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(loseCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(losePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 831, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(losePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(loseCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Потери", jPanel5);

        jLabel3.setText("область использования");

        usingCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        equipSplitPane.setDividerLocation(200);
        equipSplitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        equipSplitPane.setName("equipSplitPane"); // NOI18N
        equipSplitPane.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                equipSplitPanePropertyChange(evt);
            }
        });

        tblEquipment.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblEquipment.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblEquipment.setCellSelectionEnabled(true);
        tblEquipment.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        tblEquipment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEquipmentMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblEquipmentMouseReleased(evt);
            }
        });
        tblEquipment.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblEquipmentKeyReleased(evt);
            }
        });
        jScrollPane8.setViewportView(tblEquipment);

        equipSplitPane.setLeftComponent(jScrollPane8);

        tblTurnEquipment.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblTurnEquipment.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblTurnEquipment.setCellSelectionEnabled(true);
        tblTurnEquipment.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jScrollPane12.setViewportView(tblTurnEquipment);

        equipSplitPane.setRightComponent(jScrollPane12);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(usingCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(408, Short.MAX_VALUE))
            .addComponent(equipSplitPane, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(usingCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(equipSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Оборудование", jPanel6);

        projectSplitPane.setDividerLocation(200);
        projectSplitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        projectSplitPane.setName("projectSplitPane"); // NOI18N
        projectSplitPane.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                projectSplitPanePropertyChange(evt);
            }
        });

        tblProject.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblProject.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblProject.setCellSelectionEnabled(true);
        tblProject.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jScrollPane9.setViewportView(tblProject);

        projectSplitPane.setLeftComponent(jScrollPane9);

        tblLauncher.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblLauncher.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblLauncher.setCellSelectionEnabled(true);
        tblLauncher.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jScrollPane13.setViewportView(tblLauncher);

        projectSplitPane.setRightComponent(jScrollPane13);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(projectSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 831, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(projectSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Проект/пусковой", jPanel7);

        tblAct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblAct.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblAct.setColumnSelectionAllowed(true);
        tblAct.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tblAct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblActMousePressed(evt);
            }
        });
        jScrollPane10.setViewportView(tblAct);

        jLabel4.setText("наименование акта");

        cmbAct.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "акты/уведомления", "акт обследования УУГ", "акт опломбировки УУГ", "акт расчёта потерь", "уведомление о поверке" }));
        cmbAct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbActActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 831, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(cmbAct, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbAct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Акты/уведомления", jPanel8);

        vertSplitPane.setRightComponent(jTabbedPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(vertSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 838, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(vertSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        
        // считыаваем расположение разделителей из файла свойств
        SplitLayoutManager slm = new SplitLayoutManager(fileNameProperty);
        slm.setKeyName("vdivider");
        int location = slm.getLocation();
        if(location != 0)
            vertSplitPane.setDividerLocation(location);// расположение вертикального разделителя
        slm.setKeyName("hdivider");
        location = slm.getLocation();
        if(location != 0)
            horSplitPane.setDividerLocation(location);
        
        slm.setKeyName(equipSplitPane.getName());
        location = slm.getLocation();
        if(location != 0)
            equipSplitPane.setDividerLocation(location);// расположение разделителя оборудования
        
        slm.setKeyName(projectSplitPane.getName());
        location = slm.getLocation();
        if(location != 0)
            projectSplitPane.setDividerLocation(location);// расположение разделителя проекта
        
        // заполняем списки городов и улиц
        addListItem();
        
        // добавляем слушателей моделей столбцов таблиц
        setColumnListener();
        
        // возвращаем курсор в исходный вид
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        
        // назначаем классы - рисовальщики для первого столбца таблиц
        TableCell_Renderer.setIntegerTableCellRenderer(tblEquipment, null);
        TableCell_Renderer.setIntegerTableCellRenderer(tblLauncher, null);
        TableCell_Renderer.setIntegerTableCellRenderer(tblLose, null);
        TableCell_Renderer.setIntegerTableCellRenderer(tblNote, null);
        TableCell_Renderer.setIntegerTableCellRenderer(tblObject, null);
        TableCell_Renderer.setIntegerTableCellRenderer(tblPlomb, null);
        TableCell_Renderer.setIntegerTableCellRenderer(tblProject, null);
        TableCell_Renderer.setIntegerTableCellRenderer(tblTurnEquipment, null);
        TableCell_Renderer.setIntegerTableCellRenderer(tblTurnObject, null);
        TableCell_Renderer.setIntegerTableCellRenderer(tblUzel, null);
        
        
        ObjectTableSpanCellRenderer spanRenderer = new ObjectTableSpanCellRenderer();
        tblCommon.setDefaultRenderer(Object.class, spanRenderer);
        
        // назначаем классы - рисовальщики для столбцов с логическими данными
        TableCell_Renderer.setBooleanTablecellRenderer(tblObject);
        TableCell_Renderer.setBooleanTablecellRenderer(tblEquipment);
        TableCell_Renderer.setBooleanTablecellRenderer(tblUzel);
        TableCell_Renderer.setBooleanTablecellRenderer(tblLose);
        
        // назначаем слушателей для контроля за доступом к первому столбцу таблиц
        MDIObject.addColumnSelectionListener(tblEquipment);
        MDIObject.addColumnSelectionListener(tblLauncher);
        MDIObject.addColumnSelectionListener(tblLose);
        MDIObject.addColumnSelectionListener(tblNote);
        MDIObject.addColumnSelectionListener(tblObject);
        MDIObject.addColumnSelectionListener(tblPlomb);
        MDIObject.addColumnSelectionListener(tblProject);
        MDIObject.addColumnSelectionListener(tblTurnEquipment);
        MDIObject.addColumnSelectionListener(tblTurnObject);
        MDIObject.addColumnSelectionListener(tblUzel);
        MDIObject.addColumnSelectionListener(tblCommon);
        
        // всплывающее меню для метки с общей информацией
        JPopupMenu commonMenu = new JPopupMenu();
        JMenuItem previewMenu = new JMenuItem("Show preview");
        JMenuItem printMenu = new JMenuItem("Print table");
        previewMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // создаём класс для предварительного просмотра и печати
                Printer_Job pjob = new Printer_Job();
                pjob.setAttributes(new HashPrintRequestAttributeSet());
                pjob.setGraphics(getGraphics());
                pjob.Page_Setup();
                pjob.Print_Preview();// отображаем окно предварительного просмотра
                
            }
        });
        printMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        commonMenu.add(printMenu);
        tblCommon.setComponentPopupMenu(commonMenu);
        
//        MergeTableCellRenderer renderer = new MergeTableCellRenderer(1, 2, 1);
//        tblCommon.setDefaultRenderer(Object.class, renderer);
    }//GEN-LAST:event_formInternalFrameOpened

    private void vertSplitPanePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_vertSplitPanePropertyChange
        // при изменении свойства dividerLocation сохраняем его значение в файле свойств
//        System.out.println("vsplit " + evt.getPropertyName());
        if(evt.getPropertyName().equals("dividerLocation")){
            // класс для чтения свойств
            SplitLayoutManager slManager = new 
                    SplitLayoutManager(fileNameProperty);
            // передаём ему разделитель, для которого нужно изменить положение
            slManager.setKeyName("vdivider");
            slManager.setLocation(vertSplitPane.getDividerLocation());
        }
    }//GEN-LAST:event_vertSplitPanePropertyChange

    private void horSplitPanePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_horSplitPanePropertyChange
        // при изменении свойства dividerLocation сохраняем его значение в файле свойств
//        System.out.println("hsplit " + evt.getPropertyName());
        if(evt.getPropertyName().equals("dividerLocation")){
            // класс для чтения свойств
            SplitLayoutManager slManager = new 
                    SplitLayoutManager(fileNameProperty);
            // передаём ему разделитель, для которого нужно изменить положение
            slManager.setKeyName("hdivider");
            slManager.setLocation(horSplitPane.getDividerLocation());
        }
    }//GEN-LAST:event_horSplitPanePropertyChange

    private void tblObjectMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblObjectMousePressed
        // TODO add your handling code here:
//        System.out.println("row pressed=" + tblObject.getSelectedRow());
    }//GEN-LAST:event_tblObjectMousePressed

    private void tblObjectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblObjectMouseClicked
        // получаем выделенную строку
        if(objectsDao.getCount() > 0){
            if(tblObject.getSelectedRowCount() > 0){
                int rowID;
                // если есть выделенная строка, получаем выбранный объект
                // и его код
                rowID = objectsDao.getItem(tblObject.getSelectedRow()).getId();
                
                // сравниваем его с полученным ранее, если различен, изменяем
                // объект и информацию по нему
                if(rowID != idObject){
                    idObject = rowID;
                    setObjectsFilter();
                }
            }
        }
    }//GEN-LAST:event_tblObjectMouseClicked

    private void tblObjectKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblObjectKeyReleased
        // при перемещении по строкам таблицы соответственно изменяем и код объекта
        // только при наличии данных в таблице
//        System.out.println("keyReleased row=" + tblObject.getSelectedRow());
        if(objectsDao.getCount() > 0){
            
            if(evt.getKeyCode() == KeyEvent.VK_DOWN || 
                    evt.getKeyCode() == KeyEvent.VK_UP){
                int row = tblObject.getSelectedRow();
                idObject = objectsDao.getItem(row).getId();
                setObjectsFilter();
            }
            
            
        }
    }//GEN-LAST:event_tblObjectKeyReleased

    private void jTabbedPane1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane1PropertyChange

    private void uzelComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uzelComboActionPerformed
        // в зависимости от выбора КИП отображаем данные
        int index = uzelCombo.getSelectedIndex();
        switch(index){
            case 0:
                // сбрасываем данные
                String[] columnNames = new String[]{"Title 1","Title 2","Title 3","Title 4"};
                DefaultTableModel model = new DefaultTableModel(columnNames, 1);
                
                // удаляем слушателя модели столбцов
                tblUzel.getColumnModel().removeColumnModelListener(UzelcolumnListener);
                tblUzel.setModel(model);
                break;
            case 1:
                // информация по счётчикам
                getCounter();
                // добавляем слушателя модели столбцов
                UzelcolumnListener = new ColumnModelListener(tblUzel, props, "counterwidth");
//                tblUzel.getColumnModel().addColumnModelListener(UzelcolumnListener);
                break;
            case 2:
                // информация по корректорам
                getCorrector();
                // добавляем слушателя модели столбцов
                UzelcolumnListener = new ColumnModelListener(tblUzel, props, "correctorwidth");
//                tblUzel.getColumnModel().addColumnModelListener(UzelcolumnListener);
                break;
            case 3:
                // информация по датчикам
                getDatchik();
                // добавляем слушателя модели столбцов
                UzelcolumnListener = new ColumnModelListener(tblUzel, props, "datchikwidth");
//                tblUzel.getColumnModel().addColumnModelListener(UzelcolumnListener);
                break;
        }
        
    }//GEN-LAST:event_uzelComboActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        // в зависимости от выбранной вкладки обновляем информацию по объекту
        switch(jTabbedPane1.getSelectedIndex()){
            case 1:
                // узел учёта: возвращаем данные таблицы в исходное состояние
                uzelCombo.setSelectedIndex(0);
                break;
            case 2:
                // блокнот
                getBlocknote();
                break;
            case 3:
                // пломбы
                getPlombs();
                break;
            case 4:
                // потери
                // получаем исходные данные, если они есть
                lblLose.setText(getBorderBalance());
                loseCombo.setSelectedIndex(0);// сбрасываем настройки таблицы
                break;
            case 5:
                // оборудование
                // получаем данные по оборудованию
                getEquipment();
                
                break;
            case 6:
                // проекты и пусковой
                getProject();
                getLaunch();
                break;
            case 7:
                // акты и уведомления
                cmbAct.setSelectedIndex(0);// выбираем первый элемент в списке
                break;
        }
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void loseComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loseComboActionPerformed
        // в зависимости от выбора отображаем выбранные потери или
        // возвращаемся в исходное состояние
        
        switch(loseCombo.getSelectedIndex()){
            case 0:
                // сбрасываем данные
                String[] columnNames = new String[]{"Title 1","Title 2","Title 3","Title 4"};
                DefaultTableModel model = new DefaultTableModel(columnNames, 1);
                // удаляем слушатель столбцов
                tblLose.getColumnModel().removeColumnModelListener(LosecolumnListener);
                tblLose.setModel(model);
                break;
            case 1:
                // удаляем слушатель столбцов
                tblLose.getColumnModel().removeColumnModelListener(LosecolumnListener);
                // получаем данные
                getHiLose();
                break;
            case 2:
                // удаляем слушатель столбцов
                tblLose.getColumnModel().removeColumnModelListener(LosecolumnListener);
                // получаем данные
                getMidLose();
                break;
            case 3:
                // удаляем слушатель столбцов
                tblLose.getColumnModel().removeColumnModelListener(LosecolumnListener);
                // получаем данные
                getRLowLose();
                break;
            case 4:
                // удаляем слушатель столбцов
                tblLose.getColumnModel().removeColumnModelListener(LosecolumnListener);
                // получаем данные
                getDLowLose();
                break;
            case 5:
                // удаляем слушатель столбцов
                tblLose.getColumnModel().removeColumnModelListener(LosecolumnListener);
                // получаем данные
                getRegLose();
                break;
        }
    }//GEN-LAST:event_loseComboActionPerformed

    private void tblEquipmentKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblEquipmentKeyReleased
        if(firstItemDao.getCount() > 0){
            int row;
            if(evt.getKeyCode() == KeyEvent.VK_DOWN || 
                    evt.getKeyCode() == KeyEvent.VK_UP){
                
                row = tblEquipment.getSelectedRow();
                getTurnEquipmentData(firstItemDao.getItem(row).getId());
            }
            
        }
    }//GEN-LAST:event_tblEquipmentKeyReleased

    private void tblEquipmentMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEquipmentMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tblEquipmentMouseReleased

    private void tblEquipmentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEquipmentMouseClicked
        if(firstItemDao.getCount() > 0){
            int row;
            if(tblEquipment.getSelectedRowCount() > 0){
                
                row = tblEquipment.getSelectedRow();
                getTurnEquipmentData(firstItemDao.getItem(row).getId());
            }
            
        }
    }//GEN-LAST:event_tblEquipmentMouseClicked

    private void equipSplitPanePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_equipSplitPanePropertyChange
        // отслеживаем изменение расположения разделителя
        if(evt.getPropertyName().equals("dividerLocation")){
            // записываем новое расположение разделителя
            // класс для чтения свойств
            SplitLayoutManager slManager = new 
                    SplitLayoutManager(fileNameProperty);
            // передаём ему разделитель, для которого нужно изменить положение
            slManager.setKeyName(equipSplitPane.getName());
            slManager.setLocation(equipSplitPane.getDividerLocation());
        }
    }//GEN-LAST:event_equipSplitPanePropertyChange

    private void projectSplitPanePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_projectSplitPanePropertyChange
        // отслеживаем изменение расположения разделителя
        if(evt.getPropertyName().equals("dividerLocation")){
            // записываем новое расположение разделителя
            // класс для чтения свойств
            SplitLayoutManager slManager = new 
                    SplitLayoutManager(fileNameProperty);
            // передаём ему разделитель, для которого нужно изменить положение
            slManager.setKeyName(projectSplitPane.getName());
            slManager.setLocation(projectSplitPane.getDividerLocation());
        }
    }//GEN-LAST:event_projectSplitPanePropertyChange

    private void mnuAddnewobjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuAddnewobjectActionPerformed
        // спрашиваем пользователя, действительно ли он хочет добавить новый объект
        // или изменить принадлежность существующего объекта, принадлежащего другой организации
        addNewObject();
    }//GEN-LAST:event_mnuAddnewobjectActionPerformed

    private void mnuRemoveobjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuRemoveobjectActionPerformed
        // удаление объекта
        removeObject();
    }//GEN-LAST:event_mnuRemoveobjectActionPerformed

    private void mnuUpdateobjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuUpdateobjectActionPerformed
        // обновляем данные по объектам
        getObjects();
    }//GEN-LAST:event_mnuUpdateobjectActionPerformed

    /**
     * Добавляет новую запись по отключению или подключению объекта
     * @param evt 
     */
    private void mnuTurnAddNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuTurnAddNewActionPerformed
        addNewTurnObject();
        
    }//GEN-LAST:event_mnuTurnAddNewActionPerformed

    /**
     * Удаляет текущую запись по отключению/подключению объекта
     * @param evt 
     */
    private void mnuTurnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuTurnRemoveActionPerformed
        removeTurnObject();
    }//GEN-LAST:event_mnuTurnRemoveActionPerformed

    private void tblObjectMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblObjectMouseReleased
        // TODO add your handling code here:
//        System.out.println("row reliased=" + tblObject.getSelectedRow());
    }//GEN-LAST:event_tblObjectMouseReleased

    private void losePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_losePanelMouseClicked
        /*
        при двойном клике по панели выводим данные по акту разграничения
        если этих данных нет, то предлагаем пользователю добавить данные
        */
        // проверяем наличие данных в метке
        if(lblLose.getText().equals("")){
            // если данных нет, то предлагаем пользователю ввести
            addNewBorderBalance();// если данные добавлены, обновляем информацию
        } else {
            // если данные есть, то отображаем их для внесения изменений
            showBorderBalance();
        }
    }//GEN-LAST:event_losePanelMouseClicked

    private void cmbActActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbActActionPerformed
        // в зависимости от выбора пользователя выводим соответствующую таблицу с данными
        
        // типы данных таблицы
        Class[] columnClass;// тпи данных столбцов таблицы
        String[] columnName;// наименование столбцов
        String[] columns = new String[]{"","A","B","C","D","E","F","G","H"};
        Class[] className = new Class[]{Integer.class,String.class,String.class,
                    String.class,String.class,String.class,String.class,String.class,String.class};
        switch(cmbAct.getSelectedIndex()){
            case 0:// сброс данных таблицы
                // объявляем массивы
                columnName = new String[4];
                columnClass = new Class[4];
                
                System.arraycopy(columns, 0, columnName, 0, 4); // формируем наименования столбцов таблицы
                System.arraycopy(className, 0, columnClass, 0, 4);// формируем типы данных таблицы
                // модель таблицы по умолчанию
                tblAct.setModel(new DefaultTableModel(1, 4));
                break;
            case 1:// акт обследования УУГ
                // объявляем массивы
                columnName = new String[9];
                columnClass = new Class[9];
                
                System.arraycopy(columns, 0, columnName, 0, 9); // формируем наименования столбцов таблицы
                System.arraycopy(className, 0, columnClass, 0, 9);// формируем типы данных таблицы
                getUUGInform(columnName, columnClass);
                break;
            case 2:// акт опломбировки УУГ
                // объявляем массивы
                columnName = new String[6];
                columnClass = new Class[6];
                
                System.arraycopy(columns, 0, columnName, 0, 6); // формируем наименования столбцов таблицы
                System.arraycopy(className, 0, columnClass, 0, 6);// формируем типы данных таблицы
                getPlombsInform(columnName, columnClass);
                break;
            case 3:// акт расчёта потерь
                // объявляем массивы
                columnName = new String[8];
                columnClass = new Class[8];
                
                System.arraycopy(columns, 0, columnName, 0, 8); // формируем наименования столбцов таблицы
                System.arraycopy(className, 0, columnClass, 0, 8);// формируем типы данных таблицы
                getLoseInform(columnName, columnClass);
                break;
            case 4:// уведомление о поверке
                // объявляем массивы
                columnName = new String[5];
                columnClass = new Class[5];
                
                System.arraycopy(columns, 0, columnName, 0, 5); // формируем наименования столбцов таблицы
                System.arraycopy(className, 0, columnClass, 0, 5);// формируем типы данных таблицы
                getPoverkaInform(columnName, columnClass);
                break;
        }
        
    }//GEN-LAST:event_cmbActActionPerformed

    private void tblActMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblActMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tblActMousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbAct;
    private javax.swing.JSplitPane equipSplitPane;
    private javax.swing.JSplitPane horSplitPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblLose;
    private javax.swing.JComboBox loseCombo;
    private javax.swing.JPanel losePanel;
    private javax.swing.JMenuItem mnuAddnewobject;
    private javax.swing.JMenuItem mnuExportObjectCSV;
    private javax.swing.JMenuItem mnuExportObjectExcel;
    private javax.swing.JMenuItem mnuExportObjectODS;
    private javax.swing.JMenu mnuExportTurn;
    private javax.swing.JMenuItem mnuExportTurnCSV;
    private javax.swing.JMenuItem mnuExportTurnExcel;
    private javax.swing.JMenuItem mnuExportTurnODS;
    private javax.swing.JMenu mnuExportobject;
    private javax.swing.JMenuItem mnuRemoveobject;
    private javax.swing.JMenuItem mnuTurnAddNew;
    private javax.swing.JMenuItem mnuTurnRemove;
    private javax.swing.JMenuItem mnuUpdateobject;
    private javax.swing.JPopupMenu objectPopupMenu;
    private javax.swing.JSplitPane projectSplitPane;
    private javax.swing.JTable tblAct;
    private javax.swing.JTable tblCommon;
    private javax.swing.JTable tblEquipment;
    private javax.swing.JTable tblLauncher;
    private javax.swing.JTable tblLose;
    private javax.swing.JTable tblNote;
    private javax.swing.JTable tblObject;
    private javax.swing.JTable tblPlomb;
    private javax.swing.JTable tblProject;
    private javax.swing.JTable tblTurnEquipment;
    private javax.swing.JTable tblTurnObject;
    private javax.swing.JTable tblUzel;
    private javax.swing.JPopupMenu turnObjectPopupMenu;
    private javax.swing.JComboBox usingCombo;
    private javax.swing.JComboBox uzelCombo;
    private javax.swing.JSplitPane vertSplitPane;
    // End of variables declaration//GEN-END:variables

    /**
     * @param idCompany the idCompany to set
     */
    public void setIdCompany(int idCompany) {
        this.idCompany = idCompany;
        // получаем информацию по объектам выбранной организации
        getObjects();
    }
    
    /**
     * получает информацию по объектам данной организации
     */
    private void getObjects(){
        objectsDao = new ObjectsDaoImpl(idCompany);
        final MyTableModel mtModel;
//        oiq.Clear();
        // запоминаем ширину столбцов таблицы
        TableProperty tp = new TableProperty(tblObject);
        String colWidth = tp.getColWidth();
        
        // получаем данные и заполняем таблицу
        if(objectsDao.getCount() > 0)
            // получаем код первого объекта
            idObject = objectsDao.getItem(0).getId();
        else
            idObject = 0;
        
        
        mtModel = getItemData(objectsDao, new int[]{0}, new int[]{0,1,2,3,4,5,6,7,8}, 
                new Object[][]{new Object[]{"","","","","","","","",false}}, 
                tblObject, "tblobjectwidth", ObjectcolumnListener);

        // добавляем слушетеля изменений таблицы
        mtModel.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                int Row = e.getFirstRow();// номер редактируемой строки
                int Col = e.getColumn();// номер редактируемого столбца
                Object value;
                // проверяем в каком столбце выполнялось редактирование
                switch(Col){
                    case 2:
                        // город
                        value = getIdCity(cityBox.getSelectedIndex());
                        break;
                    case 3:
                        // улица
                        value = getIdStreet(streetBox.getSelectedIndex());
                        break;
                    default:
                        // остальные поля
                        // столбец не город и не улица
                        value = mtModel.upgradeCellValue(mtModel.getValueAt(Row,
                                Col), Col);
                        break;
                }
                OrgObjects objects = objectsDao.getItem(Row);
                objects.updateEntity(Col, value);

            }
        });

        // задаём ширину столбцов таблицы
        tp.setColWidth();
        
        // назначаем списки в качестве выбора для таблицы объектов в столбцах
        // выбора городов и улиц
        setObjectCellEditor();
        
        // выделяем первую строку таблицы
        tblObject.setRowSelectionInterval(0, 0);
        tblObject.setColumnSelectionInterval(1, 1);
        tblObject.setFocusable(true);// передаём фокус в таблицу объектов
//        tblObject.getColumnModel().getSelectionModel().addListSelectionListener(
//                new ListSelectionListener() {
//
//            @Override
//            public void valueChanged(ListSelectionEvent e) {
//                if(tblObject.getSelectedColumn() == 0)
//                    tblObject.setColumnSelectionInterval(1, 1);
//            }
//        });
        
        
        // получаем информацию по объекту
        setObjectsFilter();
    }
    
    private void setObjectsFilter(){
        if(idObject != 0){
            oiq.getInform();
        } else {
            oiq.Clear();
            
        }
        jTabbedPane1.setSelectedIndex(0);// переходим на вкладку Общая
        getTurnObjects();// информация по отключению объекта
    }
    
    /**
     * возвращает информацию по отключению данного объекта
     */
    private void getTurnObjects(){
//        System.out.println("getturnObjects idObject=" + idObject);
        if(idObject != 0){
            objectTurnDao = new TurndownDaoImpl(idObject);
            final MyTableModel mtModel;

            // запоминаем ширину столбцов таблицы
            tprops.setTable(tblTurnObject);
            String colWidth = tprops.getColWidth();

            mtModel = getItemData(objectTurnDao, new int[]{0}, new int[]{0,1,2,3,4,5,6,7}, 
                    new Object[][]{new Object[]{"","","","","","","",""}}, 
                    tblTurnObject, "tblturnobjectwidth", ObjectturncolumnListener);

            // добавляем слушетель
            mtModel.addTableModelListener(new TableModelListener() {

                @Override
                public void tableChanged(TableModelEvent e) {
                    // получаем номер редактируемой ячейки
                    int Row = e.getFirstRow();
                    int Col = e.getColumn();
                    Object value;
                    // проверяем в каком столбце выполнялось редактирование
                    switch(Col){
                        case 4:
                            // контролёр
                            value = getIdControler(controlerBox.getSelectedIndex());
                            break;
                        case 5:
                            // место установки
                            value = getIdInstalPlace(instalBox.getSelectedIndex());
                            break;
                        case 7:
                            // причина
                            value = getIdCase(caseBox.getSelectedIndex());
                            break;
                        default:
                            // остальные столбцы
                            value = mtModel.upgradeCellValue(mtModel.getValueAt(Row,
                                Col), Col);
                    }
                    Turndown turn = objectTurnDao.getItem(Row);
                    turn.updateEntity(Col, value);

                }
            });

            // задаём ширину столбцов таблицы
            tprops.setColWidth();

            // добавляем списки выбора
            setTurnObjectCellEditor();
        
        }
    }
    
    /**
     * получает информацию по счётчикам
     */
    private void getCounter(){
        
        JPopupMenu counterMenu = MDIObject.createPopupMenu(tblUzel);
        JMenuItem mnuAddNew = (JMenuItem) counterMenu.getComponent(0);
        JMenuItem mnuRemove = (JMenuItem) counterMenu.getComponent(1);
        JMenuItem mnuUpdate = (JMenuItem) counterMenu.getComponent(2);
        // добавляем слушатель действий для меню
        mnuAddNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addNewUzelItem(1);
            }
        });

        mnuRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // получаем номер строки для удаления
                int index = tblUzel.getSelectedRow();
                if(removeItem(index, firstItemDao)){
                    // если всё удачно прошло, извещаем поьзователя
                    MDIObject.getInformDialog(null, "Delete...", InformDialog.InformType.SAVING);
                    // обновляем данные
                    getCounterData();
                }

            }
        });

        mnuUpdate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                updateUzelItem(1);
            }
        });
        mnuAddNew.setText("Добавить счётчик");
        mnuRemove.setText("Удалить счётчик");
        getCounterData();// получает данные и заполняет ими таблицу
//        if(firstItemDao.getCount() == 0) mnuRemove.setEnabled(false);
    }
    
    private void getCounterData(){
        firstItemDao = new CountersDaoImpl(idObject);
        final MyTableModel mtModel;// модель данных
        mtModel = getItemData(firstItemDao, new int[]{0}, new int[]{0,1,2,3,4,5,6,7,8}, 
                new Object[][]{new Object[]{"","","","","","","","",false}}, 
                tblUzel, "counterwidth", UzelcolumnListener);
        // добавляем слушатель
        mtModel.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                int Col = e.getColumn();
                int row = e.getFirstRow();
                if(Col != 1){
                    // получаем сущность
                    Object value = mtModel.upgradeCellValue(mtModel.getValueAt(row, Col), Col);
                    Counters counter = (Counters) firstItemDao.getItem(row);
                    counter.updateEntity(Col, value);
                }
            }
        });
        
        setCounterCellEditor();
    }
    
    /**
     * получает информацию по корректорам
     */
    private void getCorrector(){
        
        JPopupMenu correctorMenu = MDIObject.createPopupMenu(tblUzel);
        JMenuItem mnuAddNew = (JMenuItem) correctorMenu.getComponent(0);
        JMenuItem mnuRemove = (JMenuItem) correctorMenu.getComponent(1);
        JMenuItem mnuUpdate = (JMenuItem) correctorMenu.getComponent(2);
        // добавляем слушатель действий для меню
        mnuAddNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addNewUzelItem(2);
            }
        });

        mnuRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // получаем номер строки для удаления
                int index = tblUzel.getSelectedRow();
                if(removeItem(index, firstItemDao)){
                    // если всё удачно прошло, извещаем поьзователя
                    MDIObject.getInformDialog(null, "Delete...", InformDialog.InformType.SAVING);
                    // обновляем данные
                    getCorrectorData();
                }

            }
        });

        mnuUpdate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                updateUzelItem(2);
            }
        });
        
        mnuAddNew.setText("Добавить корректор");
        mnuRemove.setText("Удалить корректор");
        getCorrectorData();// получаем данные и заполняем ими таблицу
        
    }
    
    private void getCorrectorData(){
        firstItemDao = new CorrectorDaoImpl(idObject);
        final MyTableModel mtModel;// модель данных
        mtModel = getItemData(firstItemDao, new int[]{0}, new int[]{0,1,2,3,4,5,6,7,8,9}, 
                new Object[][]{new Object[]{"","","","","","","","","",false}}, 
                tblUzel, "correctorwidth", UzelcolumnListener);
        // добавляем слушатель
        mtModel.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                int Col = e.getColumn();
                int row = e.getFirstRow();
                if(Col != 1){
                    // получаем сущность
                    Object value = mtModel.upgradeCellValue(mtModel.getValueAt(row, Col), Col);
                    Corrector corrector = (Corrector) firstItemDao.getItem(row);
                    corrector.updateEntity(Col, value);
                }
            }
        });
            
        setCorrectorCellEditor();
    }
    
    /**
     * получает информацию по датчикам
     */
    private void getDatchik(){
        JPopupMenu datchikpopupMenu = MDIObject.createPopupMenu(tblUzel);
        JMenuItem mnuAddNew = (JMenuItem) datchikpopupMenu.getComponent(0);
        JMenuItem mnuRemove = (JMenuItem) datchikpopupMenu.getComponent(1);
        JMenuItem mnuUpdate = (JMenuItem) datchikpopupMenu.getComponent(2);
        // добавляем слушатель действий для меню
        mnuAddNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addNewUzelItem(3);
            }
        });

        mnuRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // получаем номер строки для удаления
                int index = tblUzel.getSelectedRow();
                if(removeItem(index, firstItemDao)){
                    // если всё удачно прошло, извещаем поьзователя
                    MDIObject.getInformDialog(null, "Delete...", InformDialog.InformType.SAVING);
                    // обновляем данные
                    getDatchikData();
                }

            }
        });

        mnuUpdate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                updateUzelItem(3);
            }
        });
        
        mnuAddNew.setText("Добавить датчик");
        mnuRemove.setText("Удалить датчик");
        // получаем данные и заполняем таблицу
        getDatchikData();
        
    }
    
    private void getDatchikData(){
        firstItemDao = new DatchikDaoImpl(idObject);
        final MyTableModel mtModel;// модель данных
        mtModel = getItemData(firstItemDao, new int[]{0}, new int[]{0,1,2,3,4}, 
                new Object[][]{new Object[]{"","","","",""}}, tblUzel, 
                "datchikwidth", UzelcolumnListener);
        // добавляем слушатель
        mtModel.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                int Col = e.getColumn();
                int row = e.getFirstRow();
                if(Col != 0 && Col != 1){
                    // получаем сущность
                    Object value = mtModel.upgradeCellValue(mtModel.getValueAt(row, Col), Col);
                    Datchik datchik = (Datchik) firstItemDao.getItem(row);
                    datchik.updateEntity(Col, value);
                }
            }
        });

        setDatchikCellEditor();
        
    }
    
    /**
     * получает информацию из блокнота
     */
    private void getBlocknote(){
        // создаём меню для добавления и удаления записей
        JPopupMenu blocknoteMenu = MDIObject.createPopupMenu(tblNote);
        getBlocknoteData();// получение данных и заполнение ими таблицы
        // определяем действия для пунктов меню
        JMenuItem mnuAddNew = (JMenuItem) blocknoteMenu.getComponent(0);
        mnuAddNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addNewNote();
            }
        });
        
        JMenuItem mnuRemove = (JMenuItem) blocknoteMenu.getComponent(1);
        mnuRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                removeNote();

            }
        });
        JMenuItem mnuUpdate = (JMenuItem) blocknoteMenu.getComponent(2);
        mnuUpdate.setEnabled(false);
        mnuAddNew.setText("Добавить в блокнот");
        mnuRemove.setText("Удалить из блокнота");
    }
    
    private void getBlocknoteData(){
        firstItemDao = new BlocknoteDaoImpl(idObject);
        final MyTableModel mtModel;// модель данных
        
        // задаём слушатель модели столбцов
        final ColumnModelListener noteColumnListener = new ColumnModelListener(tblNote, props, "tblnotewidth");
        mtModel = getItemData(firstItemDao, new int[]{0,5}, new int[]{0,1,2,3,4,5}, 
                new Object[][]{new Object[]{"","","","","",""}}, tblNote, 
                "tblnotewidth", noteColumnListener);
        mtModel.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                int Col = e.getColumn();
                int row = e.getFirstRow();
                Object value;
                if(Col != 4){
                    // получаем сущность
                    value = mtModel.upgradeCellValue(mtModel.getValueAt(row, Col), Col);

                } else {
                    value = getIdControler(otherSPRBox.getSelectedIndex());
                }
                Blocknote blocknote = (Blocknote) firstItemDao.getItem(row);
                blocknote.updateEntity(Col, value);
            }
        });
        
        setBlocknoteCellEditor();
    }
    
    /**
     * получает информацию по установленным пломбам
     */
     private void getPlombs(){
         
        JPopupMenu plombspopupMenu = MDIObject.createPopupMenu(tblPlomb);
        JMenuItem mnuAddNew = (JMenuItem) plombspopupMenu.getComponent(0);
        JMenuItem mnuRemove = (JMenuItem) plombspopupMenu.getComponent(1);
        JMenuItem mnuUpdate = (JMenuItem) plombspopupMenu.getComponent(2);
        // добавляем слушатель действий для меню
        mnuAddNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addNewPlomb();
            }
        });

        mnuRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // получаем номер строки для удаления
                int index = tblPlomb.getSelectedRow();
                if(removeItem(index, firstItemDao)){
                    // если всё удачно прошло, извещаем поьзователя
                    MDIObject.getInformDialog(null, "Delete...", InformDialog.InformType.SAVING);
                    // обновляем данные
                    getPlombsData();
                }

            }
        });
        
        mnuAddNew.setText("Добавить пломбу");
        mnuRemove.setText("Удалить пломбу");
        mnuUpdate.setEnabled(false);
        getPlombsData();
        
    }
    
    private void getPlombsData(){
        firstItemDao = new PlombsDaoImpl(idObject);
        final MyTableModel mtModel;// модель данных
        
        // задаём слушатель модели столбцов
        final ColumnModelListener plombColumnListener = 
                new ColumnModelListener(tblPlomb, props, "tblplumbwidth");

        mtModel = getItemData(firstItemDao, new int[]{0}, new int[]{0,1,2,3,4,5}, 
                new Object[][]{new Object[]{"","","","","",""}}, tblPlomb, 
                "tblplumbwidth", plombColumnListener);
        // добавляем слушатель
        mtModel.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                int Col = e.getColumn();
                int row = e.getFirstRow();
                Object value;
                switch(Col){
                    case 2:
                        // место установки
                        value = getIdInstalPlace(anotherSPRBox.getSelectedIndex());
                        break;
                    case 3:
                        // контролёр
                        value = getIdControler(otherSPRBox.getSelectedIndex());
                        break;
                    default:
                        // остальные поля
                        value = mtModel.upgradeCellValue(mtModel.getValueAt(row, Col), Col);
                        break;
                }
                // получаем сущность
                Plombs plombs = (Plombs) firstItemDao.getItem(row);
                plombs.updateEntity(Col, value);

            }
        });

        setPlombCellEditor();
        
    }
    
    
    private String getBorderBalance(){
        String retval = "";
        // получаем данные по разграничению зон обслуживания
        BorderbalanceDaoImpl balanceDao = new BorderbalanceDaoImpl(idObject);
        // если данные есть, получаем первую запись
        if(balanceDao.getCount() > 0){
            Borderbalance balance = balanceDao.getItem(0);
            retval = balance.getContent();
        }
        return retval;
    }
    
    private void getHiLose(){
        JPopupMenu losepopupMenu = MDIObject.createPopupMenu(tblLose);
        JMenuItem mnuAddNew = (JMenuItem) losepopupMenu.getComponent(0);
        JMenuItem mnuRemove = (JMenuItem) losepopupMenu.getComponent(1);
        JMenuItem mnuUpdate = (JMenuItem) losepopupMenu.getComponent(2);
        
        // добавляем слушатель действий для меню
        mnuAddNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addNewLose(1);
            }
        });

        mnuRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // получаем номер строки для удаления
                int index = tblLose.getSelectedRow();
                if(removeItem(index, firstItemDao)){
                    // если всё удачно прошло, извещаем поьзователя
                    MDIObject.getInformDialog(null, "Delete...", InformDialog.InformType.SAVING);
                    // обновляем данные
                    getHiLoseData();
                }

            }
        });

        mnuAddNew.setText("Добавить потери в/д");
        mnuRemove.setText("Удалить потери в/д");
        mnuUpdate.setEnabled(false);
        // получаем данные и заполняем таблицу
        getHiLoseData();
        
    }
    
    private void getHiLoseData(){
        firstItemDao = new HipressloseDaoImpl(idObject);
        final MyTableModel mtModel;// модель данных
        
        // задаём слушатель модели столбцов
        LosecolumnListener = 
                new ColumnModelListener(tblLose, props, "hilosewidth");
        mtModel = getItemData(firstItemDao, new int[]{0,2}, new int[]{0,1,2,3,4,5,6}, 
                new Object[][]{new Object[]{"","","","",false,false,""}}, 
                tblLose, "hilosewidth", LosecolumnListener);
        // добавляем слушатель
        mtModel.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                int Col = e.getColumn();
                int row = e.getFirstRow();
                Object value = getLoseValue(mtModel, row, Col);
                BigDecimal result;
                // получаем сущность
                Hipresslose hipresslose = (Hipresslose) firstItemDao.getItem(row);
                // проверим столбцы, в которых происходили изменения
                switch(Col){
                    case 1:// диаметр
                        hipresslose.updateEntity(Col, value);// обновляем данные
                        // создаём справочную сущность
                        Sprgazlinerhipress sprhipress = new Sprgazlinerhipress((int) value);
                        result = calculateLose(row);
                        mtModel.setValueAt(result, row, 6);
                        break;
                    case 3:// длина
                    case 4:// возраст
                    case 5:// территория
                        // при изменении данных в этих столбцах выполняем
                        // расчёт технологических потерь
                        result = calculateLose(row);
                        hipresslose.updateEntity(6, result);// обновляем данные
                        break;
                    case 6:// итого
                        hipresslose.updateEntity(Col, value);// обновляем данные
                        break;
                }
            }
        });

        setHiPressCellEditor();
        
    }
    
    private void getMidLose(){
        
        JPopupMenu losepopupMenu = MDIObject.createPopupMenu(tblLose);
        JMenuItem mnuAddNew = (JMenuItem) losepopupMenu.getComponent(0);
        JMenuItem mnuRemove = (JMenuItem) losepopupMenu.getComponent(1);
        JMenuItem mnuUpdate = (JMenuItem) losepopupMenu.getComponent(2);
        // добавляем слушатель действий для меню
        mnuAddNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addNewLose(2);
            }
        });

        mnuRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // получаем номер строки для удаления
                int index = tblLose.getSelectedRow();
                if(removeItem(index, firstItemDao)){
                    // если всё удачно прошло, извещаем поьзователя
                    MDIObject.getInformDialog(null, "Delete...", InformDialog.InformType.SAVING);
                    // обновляем данные
                    getMidLoseData();
                }

            }
        });

        mnuAddNew.setText("Добавить потери с/Д");
        mnuRemove.setText("Удалить потери с/д");
        mnuUpdate.setEnabled(false);
        getMidLoseData();
        
    }
    
    private void getMidLoseData(){
        firstItemDao = new MidpressloseDaoImpl(idObject);
        final MyTableModel mtModel;// модель данных
        
        // задаём слушатель модели столбцов
        LosecolumnListener = 
                new ColumnModelListener(tblLose, props, "midlosewidth");
        mtModel = getItemData(firstItemDao, new int[]{0,2}, new int[]{0,1,2,3,4,5,6}, 
                new Object[][]{new Object[]{"","","","",false,false,""}}, 
                tblLose, "midlosewidth", LosecolumnListener);
        // добавляем слушатель
        mtModel.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                int Col = e.getColumn();
                int row = e.getFirstRow();
                Object value = getLoseValue(mtModel, row, Col);
                BigDecimal result;
                
                // получаем сущность
                Midpresslose midpress = (Midpresslose) firstItemDao.getItem(row);
                // проверим столбцы, в которых происходили изменения
                switch(Col){
                    case 1:// диаметр
                        midpress.updateEntity(Col, value);// обновляем данные
                        // создаём справочную сущность
                        Sprgazlinermidpress sprmidpress = new Sprgazlinermidpress((int) value);
                        // изменяем значение потерь в таблице
                        tblLose.setValueAt(sprmidpress.getLose(), row, 2);
                        break;
                    case 3:// длина
                    case 4:// возраст
                    case 5:// территория
                        // при изменении данных в этих столбцах выполняем
                        // расчёт технологических потерь
                        result = calculateLose(row);
                        midpress.updateEntity(6, result);// обновляем данные
                        break;
                    case 6:// итого
                        midpress.updateEntity(Col, value);// обновляем данные
                        break;
                }
            }
        });

        setMidPressCellEditor();
    }
    
    
    private void getRLowLose(){
        JPopupMenu losepopupMenu = MDIObject.createPopupMenu(tblLose);
        JMenuItem mnuAddNew = (JMenuItem) losepopupMenu.getComponent(0);
        JMenuItem mnuRemove = (JMenuItem) losepopupMenu.getComponent(1);
        JMenuItem mnuUpdate = (JMenuItem) losepopupMenu.getComponent(2);
        
        // добавляем слушатель действий для меню
        mnuAddNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addNewLose(3);
            }
        });

        mnuRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // получаем номер строки для удаления
                int index = tblLose.getSelectedRow();
                if(removeItem(index, firstItemDao)){
                    // если всё удачно прошло, извещаем поьзователя
                    MDIObject.getInformDialog(null, "Delete...", InformDialog.InformType.SAVING);
                    // обновляем данные
                    getRLowLoseData();
                }

            }
        });

        mnuAddNew.setText("Добавить потери рн/Д");
        mnuRemove.setText("Удалить потери рн/д");
        mnuUpdate.setEnabled(false);
        getRLowLoseData();
        
    }
    
    private void getRLowLoseData(){
        firstItemDao = new RlowpressloseDaoImpl(idObject);
        final MyTableModel mtModel;// модель данных
        
        // задаём слушатель модели столбцов
        LosecolumnListener = 
                new ColumnModelListener(tblLose, props, "rlowlosewidth");
        
        mtModel = getItemData(firstItemDao, new int[]{0,2}, new int[]{0,1,2,3,4,5,6}, 
                new Object[][]{new Object[]{"","","","",false,false,""}}, 
                tblLose, "rlowlosewidth", LosecolumnListener);
        // добавляем слушатель
        mtModel.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                int Col = e.getColumn();
                int row = e.getFirstRow();
                BigDecimal result;
                BigDecimal lose;
                BigDecimal lenth;
                boolean age;
                boolean territory;
                Object value = getLoseValue(mtModel, row, Col);
                Rlowpresslose rlowpress = (Rlowpresslose) firstItemDao.getItem(row);
                rlowpress.updateEntity(Col, value);
            }
        });

        setRlowPressCellEditor();
        // добавляем для списка слушатель
        otherSPRBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // получаем сущность
                int value = sprTableDao.getItem(otherSPRBox.getSelectedIndex()).getId();
                Rlowpresslose rlowpress = (Rlowpresslose) 
                        firstItemDao.getItem(tblLose.getSelectedRow());
                rlowpress.updateEntity(1, value);
            }
        });
    }
    
    private void getDLowLose(){
        JPopupMenu losepopupMenu = MDIObject.createPopupMenu(tblLose);
        JMenuItem mnuAddNew = (JMenuItem) losepopupMenu.getComponent(0);
        JMenuItem mnuRemove = (JMenuItem) losepopupMenu.getComponent(1);
        JMenuItem mnuUpdate = (JMenuItem) losepopupMenu.getComponent(2);
        // добавляем слушатель действий для меню
        mnuAddNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addNewLose(4);
            }
        });

        mnuRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // получаем номер строки для удаления
                int index = tblLose.getSelectedRow();
                if(removeItem(index, firstItemDao)){
                    // если всё удачно прошло, извещаем поьзователя
                    MDIObject.getInformDialog(null, "Delete...", InformDialog.InformType.SAVING);
                    // обновляем данные
                    getDLowLoseData();
                }

            }
        });

        mnuUpdate.setEnabled(false);
        mnuAddNew.setText("Добавить потери дн/д");
        mnuRemove.setText("Удалить потери дн/д");
        getDLowLoseData();
    }
    
    private void getDLowLoseData(){
        firstItemDao = new DlowloseDaoImpl(idObject);
        final MyTableModel mtModel;// модель данных
        
        // задаём слушатель модели столбцов
        LosecolumnListener = 
                new ColumnModelListener(tblLose, props, "dlowlosewidth");
        
        mtModel = getItemData(firstItemDao, new int[]{0,2}, new int[]{0,1,2,3,4,5,6}, 
                new Object[][]{new Object[]{"","","","",false,false,""}}, tblLose, 
                "dlowlosewidth", LosecolumnListener);
        
        // добавляем слушатель
        mtModel.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                int Col = e.getColumn();
                int row = e.getFirstRow();
                Object value = getLoseValue(mtModel, row, Col);
                BigDecimal result;
                // получаем сущность
                Dlowlose dlowpress = (Dlowlose) firstItemDao.getItem(row);
                // проверим столбцы, в которых происходили изменения
                switch(Col){
                    case 1:// диаметр
                        dlowpress.updateEntity(Col, value);// обновляем данные
                        // создаём справочную сущность
                        Sprgazlinerdlowpress sprdlowpress = new Sprgazlinerdlowpress((int) value);
                        // изменяем значение потерь в таблице
                        mtModel.setValueAt(sprdlowpress.getLose(), row, 2);
                        // расчёт технологических потерь
                        result = calculateLose(row);
                        mtModel.setValueAt(result, row, 6);
                        break;
                    case 3:// длина
                    case 4:// возраст
                    case 5:// территория
                        dlowpress.updateEntity(Col, value);// обновляем данные
                        // при изменении данных в этих столбцах выполняем
                        // расчёт технологических потерь
                        result = calculateLose(row);
                        mtModel.setValueAt(result, row, 6);
                        break;
                    case 6:// итого
                        dlowpress.updateEntity(Col, value);// обновляем данные
                        break;
                }

            }
        });

        setDlowPressCellEditor();
    }
    
    
    private void getRegLose(){
        
        JPopupMenu losepopupMenu = MDIObject.createPopupMenu(tblLose);
        JMenuItem mnuAddNew = (JMenuItem) losepopupMenu.getComponent(0);
        JMenuItem mnuRemove = (JMenuItem) losepopupMenu.getComponent(1);
        JMenuItem mnuUpdate = (JMenuItem) losepopupMenu.getComponent(2);
        // добавляем слушатель действий для меню
        mnuAddNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addNewLose(5);
            }
        });

        mnuRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // получаем номер строки для удаления
                int index = tblLose.getSelectedRow();
                if(removeItem(index, firstItemDao)){
                    // если всё удачно прошло, извещаем поьзователя
                    MDIObject.getInformDialog(null, "Delete...", InformDialog.InformType.SAVING);
                    // обновляем данные
                    getRegLoseData();
                }

            }
        });

        mnuAddNew.setText("Добавить потери на регуляторах");
        mnuRemove.setText("Удалить потери на регуляторах");
        mnuUpdate.setEnabled(false);// закрываем доступ к меню Обновить
        getRegLoseData();
        
    }
    
    private void getRegLoseData(){
        firstItemDao = new RegloseDaoImpl(idObject);
        final MyTableModel mtModel;// модель данных
        // задаём слушатель модели столбцов
        LosecolumnListener = 
                new ColumnModelListener(tblLose, props, "reglosewidth");
        
        mtModel = getItemData(firstItemDao, new int[]{0,2}, new int[]{0,1,2,3,4}, 
                new Object[][]{new Object[]{"","","","",""}}, tblLose, 
                "reglosewidth", LosecolumnListener);
        // добавляем слушатель
        mtModel.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                int Col = e.getColumn();
                int row = e.getFirstRow();
                Object value = getLoseValue(mtModel, row, Col);
                
                // получаем сущность
                Reglose reglose = (Reglose) firstItemDao.getItem(row);
                reglose.updateEntity(Col, value);
            }
        });

        setRegulatorCellEditor();
    }
    
    private String getUsingGaz(){
        String retval;
        final GazusingDaoImpl gazUsingDao = new GazusingDaoImpl(idObject);
        // если данные есть
        if(gazUsingDao.getCount() > 0){
            // получаем сущность
            Gazusing using = gazUsingDao.getItem(0);
            retval = using.getUsingType();
        } else {
            retval = "";
        }
        System.out.println("usingtype = " + retval);
        return retval;
    }
    
    
    private void getEquipment(){
        JPopupMenu equippopupMenu = MDIObject.createPopupMenu(tblEquipment);
        JMenuItem mnuAddNew = (JMenuItem) equippopupMenu.getComponent(0);
        JMenuItem mnuRemove = (JMenuItem) equippopupMenu.getComponent(1);
        JMenuItem mnuUpdate = (JMenuItem) equippopupMenu.getComponent(2);
        
        // получаем данные по области применения газа
        String usinggaz = getUsingGaz();
        // проверяем полученные данные
        if(usinggaz.equals(""))
            // если данных нет
            usingCombo.setSelectedIndex(-1);
        else
            // если данные есть
            usingCombo.setSelectedItem(usinggaz);
        
        // определяем действия для пунктов меню
        // добавляем слушатель действий для меню
        mnuAddNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addNewEquipment();
            }
        });

        mnuRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // получаем номер строки для удаления
                int index = tblEquipment.getSelectedRow();
                if(removeItem(index, firstItemDao)){
                    // если всё удачно прошло, извещаем поьзователя
                    MDIObject.getInformDialog(null, "Delete...", InformDialog.InformType.SAVING);
                    // обновляем данные
                    getEquipmentData();
                }

            }
        });

        mnuUpdate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                updateEquipment();
            }
        });
        
        mnuAddNew.setText("Добавить оборудование");
        mnuRemove.setText("Удалить оборудование");
        
        getTurnEquipment();
        getEquipmentData();
        
        
    }
    
    private void getEquipmentData(){
        int idEquipment;
        firstItemDao = new EquipmentDaoImpl(idObject);
        final MyTableModel mtModel;// модель данных
        final ColumnModelListener equipmentListener = new 
                ColumnModelListener(tblEquipment, props, "equipmentwidth");
        
        mtModel = getItemData(firstItemDao, new int[]{0}, new int[]{0,1,2,3,4}, 
                new Object[][]{new Object[]{"","","","",false}}, 
                tblEquipment, "equipmentwidth", equipmentListener);
        // добавляем слушатель
        mtModel.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                int Col = e.getColumn();
                int row = e.getFirstRow();
                if(Col != 1){
                    // получаем сущность
                    Object value = 
                            mtModel.upgradeCellValue(mtModel.getValueAt(row, 
                            Col), Col);
                    Equipment equipment = (Equipment) firstItemDao.getItem(row);
                    equipment.updateEntity(Col, value);
                }
            }
        });
            
        setEquipmentCellEditor();
        
        if(firstItemDao.getCount() > 0){
            // код первого оборудованвия
            idEquipment = firstItemDao.getItem(0).getId();
            getTurnEquipmentData(idEquipment);
        }
        
    }
    
    private void getTurnEquipment(){
        JPopupMenu popupMenu = MDIObject.createPopupMenu(tblTurnEquipment);
        JMenuItem mnuAddNew = (JMenuItem) popupMenu.getComponent(0);
        JMenuItem mnuRemove = (JMenuItem) popupMenu.getComponent(1);
        JMenuItem mnuUpdate = (JMenuItem) popupMenu.getComponent(2);
        
        mnuAddNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addNewTurnEquipment();
            }
        });

        mnuRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                removeTurnEquipment();

            }
        });
        mnuAddNew.setText("Добавить отключение оборудования");
        mnuRemove.setText("Удалить отключение оборудования");
        mnuUpdate.setEnabled(false);
//        if(secondItemDao.getCount() == 0) mnuRemove.setEnabled(false);
    }
    
    private void getTurnEquipmentData(int idEquipment){
        secondItemDao = new EquipturndownDaoImpl(idEquipment);
        final MyTableModel mtModel;// модель данных
        
        // создаём и добавляем слушателя модели столбцов
        final ColumnModelListener turnEquipmentListener = new 
                ColumnModelListener(tblTurnEquipment, props, "tblturnequipmentwidth");
        mtModel = getItemData(secondItemDao, new int[]{0}, new int[]{0,1,2,3,4,5,6}, 
                new Object[][]{new Object[]{"","","","","","",""}}, 
                tblTurnEquipment, "tblturnequipmentwidth", turnEquipmentListener);
        // добавляем слушатель
        mtModel.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                int Col = e.getColumn();
                int row = e.getFirstRow();
                Object value;
                switch(Col){
                    case 5:
                        // получаем код контролёра
                        value = getIdControler(otherSPRBox.getSelectedIndex());
                        break;
                    case 6:
                        // получаем код причины
                        value = getIdCase(anotherSPRBox.getSelectedIndex());
                        break;
                    default:
                        // остальные поля
                        value = mtModel.upgradeCellValue(mtModel.getValueAt(row, Col), Col);
                        break;
                }
                // получаем сущность
                Equipturndown turndown = (Equipturndown) secondItemDao.getItem(row);
                turndown.updateEntity(Col, value);
            }
        });

        setTurnEquipmentCellEditor();
        
        // блокируем пункт меню Удалить, если в наборе нет данных
        if(secondItemDao.getCount() == 0) 
            tblTurnEquipment.getComponentPopupMenu().getComponent(2).setEnabled(false);
    }
    
    private void getProject(){
        
        JPopupMenu popupMenu = MDIObject.createPopupMenu(tblProject);
        JMenuItem mnuAddNew = (JMenuItem) popupMenu.getComponent(0);
        JMenuItem mnuRemove = (JMenuItem) popupMenu.getComponent(1);
        JMenuItem mnuUpdate = (JMenuItem) popupMenu.getComponent(2);
        // добавляем слушатель действий для меню
        mnuAddNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addNewProject();
            }
        });

        mnuRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                removeProject();
                
            }
        });

        mnuAddNew.setText("Добавить проект");
        mnuRemove.setText("Удалить проект");
        mnuUpdate.setEnabled(false);
        getProjectData();
        
    }
    
    private void getProjectData(){
        firstItemDao = new ProjectsDaoImpl(idObject);
        final MyTableModel mtModel;// модель данных
        final ColumnModelListener projectListener = new 
                ColumnModelListener(tblProject, props, "tblprojectwidth");
        mtModel = getItemData(firstItemDao, new int[]{0}, new int[]{0,1,2,3,4,5,6}, 
                new Object[][]{new Object[]{"","","","","","",""}}, 
                tblProject, "tblprojectwidth", projectListener);
        // добавляем слушатель
        mtModel.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                int Col = e.getColumn();
                int row = e.getFirstRow();
                Object value;
                if(Col != 1){
                    value = mtModel.upgradeCellValue(mtModel.getValueAt(row, Col), Col);
                } else {
                    value = getIdProject(otherSPRBox.getSelectedIndex());
                }
                // получаем сущность
                Projects projects = (Projects) firstItemDao.getItem(row);
                projects.updateEntity(Col, value);

            }
        });

        setProjectCellEditor();
    }
    
    private void getLaunch(){
        
        // создаём всплывающее меню для добавления, удаления записей, экспорта данных
        JPopupMenu launchpopupMenu = MDIObject.createPopupMenu(tblLauncher);
        JMenuItem mnuAddNew = (JMenuItem) launchpopupMenu.getComponent(0);
        JMenuItem mnuRemove = (JMenuItem) launchpopupMenu.getComponent(1);
        JMenuItem mnuUpdate = (JMenuItem) launchpopupMenu.getComponent(2);
        
        mnuAddNew.setText("Добавить в пусковой");
        mnuRemove.setText("Удалить из пускового");
        mnuUpdate.setEnabled(false);
        getLaunchData();

        // добавляем слушатель действий для меню
        mnuAddNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addNewLaunch();
            }
        });

        mnuRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                removeLaunch();
                
            }
        });

        
        
    }
    
    private void getLaunchData(){
        secondItemDao = new LauncherDaoImpl(idObject);
        final MyTableModel mtModel;// модель данных
        // создаём и добавляем слушателя модели столбцов
        final ColumnModelListener launcherListener = new 
                ColumnModelListener(tblLauncher, props, "tbllauncherwidth");
        
        mtModel = getItemData(secondItemDao, new int[]{0}, new int[]{0,1,2}, 
                new Object[][]{new Object[]{"","",""}}, 
                tblLauncher, "tbllauncherwidth", launcherListener);
        // добавляем слушатель
        mtModel.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                int Col = e.getColumn();
                int row = e.getFirstRow();
                // получаем сущность
                Object value = mtModel.upgradeCellValue(mtModel.getValueAt(row, Col), Col);
                Launcher launcher = (Launcher) secondItemDao.getItem(row);
                launcher.updateEntity(Col, value);

            }
        });
    }
    
    private void setColumnListener(){
        
        MDIObject.setTablecolwidth(props, "tblobjectwidth", tblObject);
        MDIObject.setTablecolwidth(props, "tblturnobjectwidth", tblTurnObject);
        MDIObject.setTablecolwidth(props, "tblcommonwidth", tblCommon);
        
        // создаём слушателей моделей столбцов таблиц
        ObjectcolumnListener = new ColumnModelListener(tblObject, props, "tblobjectwidth");
        tblObject.getColumnModel().addColumnModelListener(ObjectcolumnListener);
        
        ObjectturncolumnListener = new ColumnModelListener(tblTurnObject, props, "tblturnobjectwidth");
        tblTurnObject.getColumnModel().addColumnModelListener(ObjectturncolumnListener);
        
        CommoncolumnListener = new ColumnModelListener(tblCommon, props, "tblcommonwidth");
        tblCommon.getColumnModel().addColumnModelListener(CommoncolumnListener);
    }
    
    
    private void addListItem(){
        // данные для заполнения моделей списков
        sprCityDao = new SprcityDaoImpl();
        sprStreetDao = new SprstreetDaoImpl();
        sprControlerDao = new SprcontrolersDaoImpl();
        sprCaseDao = new SprcaseDaoImpl();
        sprInstalDao = new SprplumbsinstalplaseDaoImpl();
        final SprusinggazDaoImpl usingDao = new SprusinggazDaoImpl();
        
        // заполняем списки
        Object[] content = new Object[sprCityDao.getCount()];
        for(int i = 0; i < sprCityDao.getCount(); i++){
            Sprcity city = sprCityDao.getItem(i);
            content[i] = city.getCityName();
        }
        ComboBoxModel cityModel = new DefaultComboBoxModel(content);
        cityBox.setModel(cityModel);
        
        content = new Object[sprStreetDao.getCount()];
        for(int i = 0; i < sprStreetDao.getCount(); i++){
            Sprstreet street = sprStreetDao.getItem(i);
            content[i] = street.getStreetName();
        }
        ComboBoxModel streetModel = new DefaultComboBoxModel(content);
        streetBox.setModel(streetModel);
        
        content = new Object[sprControlerDao.getCount()];
        for(int i = 0; i < content.length; i++){
            Sprcontrolers controler = sprControlerDao.getItem(i);
            content[i] = controler.getControlerName();
        }
        ComboBoxModel controlerModel = new DefaultComboBoxModel(content);
        controlerBox.setModel(controlerModel);
        
        content = new Object[sprCaseDao.getCount()];
        for(int i = 0; i < content.length; i++){
            Sprcase sprcase = sprCaseDao.getItem(i);
            content[i] = sprcase.getCaseName();
        }
        ComboBoxModel caseModel = new DefaultComboBoxModel(content);
        caseBox.setModel(caseModel);
        
        content = new Object[sprInstalDao.getCount()];
        for(int i = 0; i < content.length; i++){
            Sprplumbsinstalplace instal = sprInstalDao.getItem(i);
            content[i] = instal.getPlaceName();
        }
        ComboBoxModel instalModel = new DefaultComboBoxModel(content);
        instalBox.setModel(instalModel);
        
        content = new Object[usingDao.getCount()];
        for(int i = 0; i < content.length; i++){
            Sprusinggaz using = usingDao.getItem(i);
            content[i] = using.getUsingName();
        }
        ComboBoxModel usingModel = new DefaultComboBoxModel(content);
        usingCombo.setModel(usingModel);
        // добавляем слушатель к списку использования
        usingCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // получаем выбор пользователя из справочника
                if(usingCombo.getSelectedIndex() != -1){
                    // получаем экземпляр сущности из списка использования
                    Sprusinggaz sug = usingDao.getItem(usingCombo.getSelectedIndex());
                    // получаем данные по использованию
                    GazusingDaoImpl udi = new GazusingDaoImpl(idObject);
                    if(udi.getCount() > 0){
                        // если данные есть, то изменяем существующие
                        Gazusing gu = udi.getItem(0);
                        // сравниваем коды использоания, если они не равны
                        // тогда обновляем запись
                        if(gu.getIdusingtype() != sug.getId())
                            gu.setIdusingtype(sug.getId());
                    } else {
                        // если данных нет, то добавляем новую запись
                        udi.addItem("IDOBJECT,IDUSINGTYPE", "?,?", 
                            new int[]{idObject, sug.getId()});

                        
                    }
                }
            }
        });
        
        sprEquipDao = new SprequipmentDaoImpl();
        content = new Object[sprEquipDao.getCount()];
        for(int i = 0; i < sprEquipDao.getCount(); i++){
            Sprequipment equipment = (Sprequipment) sprEquipDao.getItem(i);
            content[i] = equipment.toString();
        }
        ComboBoxModel equipModel = new DefaultComboBoxModel(content);
        sprEquipBox.setModel(equipModel);
        
        setObjectCellEditor();
        setTurnObjectCellEditor();
    }
    
    /**
     * устанавливает редакторы ячеек таблицы объектов
     */
    private void setObjectCellEditor(){
        if(objectsDao.getCount() > 0){
            // назначаем списки в качестве выбора для таблицы объектов в столбцах
            // выбора городов и улиц
            DefaultCellEditor cityEditor = new DefaultCellEditor(cityBox);
            DefaultCellEditor streetEditor = new DefaultCellEditor(streetBox);
            tblObject.getColumnModel().getColumn(2).setCellEditor(cityEditor);
            tblObject.getColumnModel().getColumn(3).setCellEditor(streetEditor);
//            tblObject.setDefaultRenderer(Boolean.class, new IntegerTableCellRenderer());
        }
    }
    
    /**
     * устанавливает редакторы ячеек таблицы отключения объектов
     */
    private void setTurnObjectCellEditor(){
        DefaultCellEditor controlerEditor = new DefaultCellEditor(controlerBox);
        DefaultCellEditor caseEditor = new DefaultCellEditor(caseBox);
        DefaultCellEditor instalEditor = new DefaultCellEditor(instalBox);
        tblTurnObject.getColumnModel().getColumn(4).setCellEditor(controlerEditor);
        tblTurnObject.getColumnModel().getColumn(7).setCellEditor(caseEditor);
        tblTurnObject.getColumnModel().getColumn(5).setCellEditor(instalEditor);
    }
    
    private void setBlocknoteCellEditor(){
        sprTableDao = new SprcontrolersDaoImpl();
        Object[] content = new Object[sprTableDao.getCount()];
        for(int i = 0; i < content.length; i++){
            Sprcontrolers controler = (Sprcontrolers) sprTableDao.getItem(i);
            content[i] = controler.getControlerName();
        }
        ComboBoxModel controlerModel = new DefaultComboBoxModel(content);
        otherSPRBox.setModel(controlerModel);
        DefaultCellEditor controlerNoteEditor = new DefaultCellEditor(otherSPRBox);
        tblNote.getColumnModel().getColumn(4).setCellEditor(controlerNoteEditor);
    }
    
    private void setPlombCellEditor(){
        sprTableDao = new SprcontrolersDaoImpl();
        Object[] content = new Object[sprTableDao.getCount()];
        for(int i = 0; i < content.length; i++){
            Sprcontrolers controler = sprControlerDao.getItem(i);
            content[i] = controler.getControlerName();
        }
        ComboBoxModel controlerModel = new DefaultComboBoxModel(content);
        otherSPRBox.setModel(controlerModel);

        anothersprTableDao = new SprplumbsinstalplaseDaoImpl();
        content = new Object[anothersprTableDao.getCount()];
        for(int i = 0; i < content.length; i++){
            Sprplumbsinstalplace instal = (Sprplumbsinstalplace) anothersprTableDao.getItem(i);
            content[i] = instal.getPlaceName();
        }
        ComboBoxModel instalModel = new DefaultComboBoxModel(content);
        anotherSPRBox.setModel(instalModel);
        DefaultCellEditor controlerPlombEditor = new DefaultCellEditor(otherSPRBox);
        DefaultCellEditor instalPlombEditor = new DefaultCellEditor(anotherSPRBox);
        tblPlomb.getColumnModel().getColumn(2).setCellEditor(instalPlombEditor);
        tblPlomb.getColumnModel().getColumn(3).setCellEditor(controlerPlombEditor);
    }
    
    private void setTurnEquipmentCellEditor(){
        sprTableDao = new SprcontrolersDaoImpl();
        Object[] content = new Object[sprTableDao.getCount()];
        for(int i = 0; i < sprTableDao.getCount(); i++){
            Sprcontrolers controler = (Sprcontrolers) sprTableDao.getItem(i);
            content[i] = controler.getControlerName();
        }
        ComboBoxModel controlerModel = new DefaultComboBoxModel(content);
        otherSPRBox.setModel(controlerModel);
        
        anothersprTableDao = new SprcaseDaoImpl();
        content = new Object[anothersprTableDao.getCount()];
        for(int i = 0; i < content.length; i++){
            Sprcase sprcase = (Sprcase) anothersprTableDao.getItem(i);
            content[i] = sprcase.getCaseName();
        }
        ComboBoxModel sprcaseModel = new DefaultComboBoxModel(content);
        anotherSPRBox.setModel(sprcaseModel);
        
        DefaultCellEditor equipControlerEditor = new DefaultCellEditor(otherSPRBox);
        DefaultCellEditor equipCaseEditor = new DefaultCellEditor(anotherSPRBox);
        tblTurnEquipment.getColumnModel().getColumn(5).setCellEditor(equipControlerEditor);
        tblTurnEquipment.getColumnModel().getColumn(6).setCellEditor(equipCaseEditor);
    }
    
    private void setEquipmentCellEditor(){
        
        DefaultCellEditor equipEditor = new DefaultCellEditor(sprEquipBox);
        tblEquipment.getColumnModel().getColumn(1).setCellEditor(equipEditor);
    }
    
    private void setCounterCellEditor(){
        // заполняем списки
        sprTableDao = new SprcounterDaoImpl();
        Object[] content = new Object[sprTableDao.getCount()];
        for(int i = 0; i < sprTableDao.getCount(); i++){
            Sprcounter counter = (Sprcounter) sprTableDao.getItem(i);
            content[i] = counter.toString();
        }
        ComboBoxModel counterModel = new DefaultComboBoxModel(content);
        otherSPRBox.setModel(counterModel);
        DefaultCellEditor counterEditor = new DefaultCellEditor(otherSPRBox);
        tblUzel.getColumnModel().getColumn(1).setCellEditor(counterEditor);
    }
    
    private void setCorrectorCellEditor(){
        // заполняем списки
        sprTableDao = new SprcorrectorDaoImpl();
        Object[] content = new Object[sprTableDao.getCount()];
        for(int i = 0; i < sprTableDao.getCount(); i++){
            Sprcorrector corrector = (Sprcorrector) sprTableDao.getItem(i);
            content[i] = corrector.toString();
        }
        ComboBoxModel correctorModel = new DefaultComboBoxModel(content);
        otherSPRBox.setModel(correctorModel);
        DefaultCellEditor correctorEditor = new DefaultCellEditor(otherSPRBox);
        tblUzel.getColumnModel().getColumn(1).setCellEditor(correctorEditor);
    }
    
    private void setDatchikCellEditor(){
        // заполняем списки
        sprTableDao = new SprdatchikDaoImpl();
        Object[] content = new Object[sprTableDao.getCount()];
        for(int i = 0; i < sprTableDao.getCount(); i++){
            Sprdatchik datchik = (Sprdatchik) sprTableDao.getItem(i);
            content[i] = datchik.toString();
        }
        ComboBoxModel datchikModel = new DefaultComboBoxModel(content);
        otherSPRBox.setModel(datchikModel);
        DefaultCellEditor datchikEditor = new DefaultCellEditor(otherSPRBox);
        tblUzel.getColumnModel().getColumn(1).setCellEditor(datchikEditor);
    }
    
    private void setHiPressCellEditor(){
        // заполняем списки
        sprTableDao = new SprgazlinerhipressDaoImpl();
        Object[] content = new Object[sprTableDao.getCount()];
        for(int i = 0; i < sprTableDao.getCount(); i++){
            Sprgazlinerhipress hipress = (Sprgazlinerhipress) sprTableDao.getItem(i);
            content[i] = Short.toString(hipress.getDimeter());
        }
        ComboBoxModel hipressModel = new DefaultComboBoxModel(content);
        otherSPRBox.setModel(hipressModel);
        DefaultCellEditor hipressEditor = new DefaultCellEditor(otherSPRBox);
        tblLose.getColumnModel().getColumn(1).setCellEditor(hipressEditor);
    }
    
    private void setMidPressCellEditor(){
        // заполняем списки
        sprTableDao = new SprgazlinermidpressDaoImpl();
        Object[] content = new Object[sprTableDao.getCount()];
        for(int i = 0; i < sprTableDao.getCount(); i++){
            Sprgazlinermidpress midpress = (Sprgazlinermidpress) sprTableDao.getItem(i);
            content[i] = Short.toString(midpress.getDimeter());
        }
        ComboBoxModel midpressModel = new DefaultComboBoxModel(content);
        otherSPRBox.setModel(midpressModel);
        DefaultCellEditor midpressEditor = new DefaultCellEditor(otherSPRBox);
        tblLose.getColumnModel().getColumn(1).setCellEditor(midpressEditor);
    }
    
    private void setRlowPressCellEditor(){
        // заполняем списки
        sprTableDao = new SprgazlinerrlowpressDaoImpl();
        Object[] content = new Object[sprTableDao.getCount()];
        for(int i = 0; i < sprTableDao.getCount(); i++){
            Sprgazlinerdlowpress rlowpress = (Sprgazlinerdlowpress) sprTableDao.getItem(i);
            content[i] = Short.toString(rlowpress.getDimeter());
        }
        ComboBoxModel rlowpressModel = new DefaultComboBoxModel(content);
        otherSPRBox.setModel(rlowpressModel);
        DefaultCellEditor rlowpressEditor = new DefaultCellEditor(otherSPRBox);
        tblLose.getColumnModel().getColumn(1).setCellEditor(rlowpressEditor);
    }
    
    private void setDlowPressCellEditor(){
        // заполняем списки
        sprTableDao = new SprgazlinerdlowpressDaoImpl();
        Object[] content = new Object[sprTableDao.getCount()];
        for(int i = 0; i < sprTableDao.getCount(); i++){
            Sprgazlinerdlowpress dlowpress = (Sprgazlinerdlowpress) sprTableDao.getItem(i);
            content[i] = Short.toString(dlowpress.getDimeter());
        }
        ComboBoxModel dlowpressModel = new DefaultComboBoxModel(content);
        otherSPRBox.setModel(dlowpressModel);
        DefaultCellEditor dlowpressEditor = new DefaultCellEditor(otherSPRBox);
        tblLose.getColumnModel().getColumn(1).setCellEditor(dlowpressEditor);
    }
    
    private void setRegulatorCellEditor(){
        // заполняем списки
        sprTableDao = new SprgazregulatorDaoImpl();
        Object[] content = new Object[sprTableDao.getCount()];
        for(int i = 0; i < sprTableDao.getCount(); i++){
            Sprgazregulator regulator = (Sprgazregulator) sprTableDao.getItem(i);
            content[i] = regulator.getName();
        }
        ComboBoxModel regulatorModel = new DefaultComboBoxModel(content);
        otherSPRBox.setModel(regulatorModel);
        DefaultCellEditor regulatorEditor = new DefaultCellEditor(otherSPRBox);
        tblLose.getColumnModel().getColumn(1).setCellEditor(regulatorEditor);
    }
    
    private void setProjectCellEditor(){
        // заполняем списки
        sprTableDao = new SprpodryadchikDaoImpl();
        Object[] content = new Object[sprTableDao.getCount()];
        for(int i = 0; i < sprTableDao.getCount(); i++){
            Sprpodryadchik podrydchik = (Sprpodryadchik) sprTableDao.getItem(i);
            content[i] = podrydchik.getPodryadchikName();
        }
        ComboBoxModel podrydchikModel = new DefaultComboBoxModel(content);
        otherSPRBox.setModel(podrydchikModel);
        DefaultCellEditor podrydchikEditor = new DefaultCellEditor(otherSPRBox);
        tblProject.getColumnModel().getColumn(1).setCellEditor(podrydchikEditor);
    }
    
    private int getIdControler(int Index){
        return sprControlerDao.getItem(Index).getId();
    }
    
    private int getIdCase(int Index){
        return sprCaseDao.getItem(Index).getId();
    }
    
    private int getIdInstalPlace(int Index){
        return sprInstalDao.getItem(Index).getId();
    }
    
    private int getIdCity(int Index){
        return sprCityDao.getItem(Index).getId();
    }
    
    private int getIdStreet(int Index){
        return sprStreetDao.getItem(Index).getId();
    }
    
    private int getIdLose(int Index){
        return sprTableDao.getItem(Index).getId();
    }
    
    private int getIdProject(int Index){
        return sprTableDao.getItem(Index).getId();
    }
    
    private void updateUzelItem(int index){
        switch(index){
            case 1:
                // обновление информации по счётчикам
                getCounterData();
                break;
            case 2:
                // обновление информации по корректорам
                getCorrectorData();
                break;
            case 3:
                // обновление информации по датчикам
                getDatchikData();
                break;
        }
    }
    
    /**
     * 
     */
    private void addNewObject(){
        // спрашиваем пользователя, действительно ли он хочет добавить новый объект
        // или изменить принадлежность существующего объекта, принадлежащего другой организации
        int button = JOptionPane.showConfirmDialog(this, 
                "Для добавления нового объекта нажмите ОК. Для изменения" + 
                        " принадлежности \n существующего объекта " + 
                        "нажмите НЕТ. Добавить новый объект?", 
                "AbonentGaz", JOptionPane.YES_NO_CANCEL_OPTION);
        
        // проверяем выбор пользователя
        switch (button) {
            case JOptionPane.YES_OPTION:
                // перед добавлением нового объекта ещё раз спрашиваем пользователя
                if(JOptionPane.showConfirmDialog(this, "Добавить новый объект?", 
                        "AbonentGaz", JOptionPane.YES_NO_OPTION, 
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                    // добавление нового объекта к текущей организации
                    NewItemDialog newObjectDialog = new NewItemDialog(NewItemDialog.NewItemType.NewObject);
                    newObjectDialog.setIndentity(idCompany);
                    newObjectDialog.createNewItem();// создаём элементы интерфейса
                    if(newObjectDialog.showDialog(this)){
                        MDIObject.getInformDialog(null, "Новый объект", 
                                InformDialog.InformType.SAVING);
                        // обновляем данные по объектам
                        getObjects();
                    }
                }
//                    MDIObject.getInformDialog(null, "Новый объект", InformDialog.InformType.SAVING);
                break;
            case JOptionPane.NO_OPTION:
                // добавление существующего объекта к текущей организации
                ViewobjectFrame frame = new ViewobjectFrame();
                frame.setIdOrganization(idCompany);
                frame.setTitle("Перечень объектов");
                frame.setLocationRelativeTo(null);
                frame.setModalExclusionType(Dialog.ModalExclusionType.NO_EXCLUDE);
                frame.setVisible(true);
                // проверяем резултат действий пользователя
                if(frame.isChange())
                    getObjects();// обновляем данные по объектам
                break;
            default:
                break;
        }
    }
    
    /**
     * 
     */
    private void removeObject(){
        // запрашиваем пользователя об удалении существующего объекта
        int button = JOptionPane.showConfirmDialog(this, "Удалить запись?", "AbonentGaz", 
                JOptionPane.YES_NO_OPTION, JOptionPane.NO_OPTION);
        
        // проверяем, что он выбрал
        if(button == JOptionPane.YES_OPTION){
            // получаем строку в таблице с выбранным объектом
            int row = tblObject.getSelectedRow();
            
            // удаляем выбранный объект из набора
//            boolean deleteItem = objectsDao.deleteItem(row);
            if(objectsDao.deleteItem(row)){
                MDIObject.getInformDialog(null, "Deleting...", 
                        InformDialog.InformType.SAVING);
                // обновляем данные по объектам
                getObjects();
            }
            else
                JOptionPane.showMessageDialog(this, "Произошли ошибки во " + 
                        "время операции удаления!", "AbonentGaz", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * 
     * @param index
     */
    private void addNewUzelItem(int index){
        // в зависимости от выбранного типа КИП создаём соответствующее окно выбора
        // нового средства измерения
        NewItemDialog.NewItemType nit = null;
        switch(index){
            case 1:
                // новый счётчик
                nit = NewItemDialog.NewItemType.NewCount;
//                dialog = new NewItemDialog(NewItemDialog.NewItemType.NewCount);
                break;
            case 2:
                // новый корректор
                nit = NewItemDialog.NewItemType.NewCorrector;
//                dialog = new NewItemDialog(NewItemDialog.NewItemType.NewCorrector);
                break;
            case 3:
                // новый датчик
                nit = NewItemDialog.NewItemType.NewDatchik;
//                dialog = new NewItemDialog(NewItemDialog.NewItemType.NewDatchik);
                break;
        }
        NewItemDialog dialog = new NewItemDialog(nit);
        dialog.setIndentity(idObject);
        dialog.createNewItem();// создаём элементы интерфейса
        boolean retval = dialog.showDialog(this);
        
        // проверяем результат действий пользователя
        if(retval)
            updateUzelItem(index);// обновляем данные
//        dialog = null;
    }
    
    /**
     * Отображает диалоговое окно для выбора и добавления новой единицы оборудования
     */
    private void addNewEquipment(){
        // запрос на добавление нового оборудования
        int button = JOptionPane.showConfirmDialog(null, 
                "Добавить запись?", "AbonentGaz", JOptionPane.YES_NO_OPTION);
        
        // проверяем выбор пользователя
        if(button == JOptionPane.YES_OPTION){
            NewItemDialog.NewItemType nit = NewItemDialog.NewItemType.NewEquipment;
            NewItemDialog dialog = new NewItemDialog(nit);// создаём диалоговое окно
            dialog.setIndentity(idObject);// код объекта для добавления
            dialog.createNewItem();// создаём элементы интерфейса
            boolean retval = dialog.showDialog(this);// получаем результат выбора пользоателя
            
            // проверяем сделанный пользователем выбор
            if(retval) getEquipmentData();// обновляем данные по оборудованию
        }
    }
    
    /**
     * удаляет запись из таблицы оборудования
     */
    private void removeEquipment(){
        // запрос на удаление
        int button = JOptionPane.showConfirmDialog(null, 
                "Удалить запись?", "AbonentGaz", JOptionPane.YES_NO_OPTION);
        
        // проверяем выбор пользователя
        if(button == JOptionPane.YES_OPTION){
            // получаем номер строки для удаления
            int index = tblEquipment.getSelectedRow();
                
            boolean retval = firstItemDao.deleteItem(index);
            
            if(retval) getEquipmentData();// обновляем данные по оборудованию
        }
    }
    
    /**
     * 
     */
    private void updateEquipment(){
        getEquipmentData();
    }
    
    /**
     * 
     */
    private void addNewNote(){
        // запрос на добавление
        int button = JOptionPane.showConfirmDialog(null, 
                "Добавить запись?", "AbonentGaz", JOptionPane.YES_NO_OPTION);
        
        // проверяем выбор пользователя
        if(button == JOptionPane.YES_OPTION){
            // получаем код контролёра
            int idControler = getIdControler(0);
            int[] param = new int[]{idObject,idControler};
            String fieldName = "IDOBJECT,IDCONTROLER";
            String escField = "?,?";
            // добавляем запись, если добавление удачно, обновляем данные
            if(firstItemDao.addItem(fieldName, escField, param))
                getBlocknote();
            else
                JOptionPane.showMessageDialog(null, 
                        "Произошли ошибки во время операции добавления. \n" +
                        "Обратитесь к администратору!", "AbonentGaz", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * 
     */
    private void removeNote(){
        // запрос на удаление
        int button = JOptionPane.showConfirmDialog(null, 
                "Удалить запись?", "AbonentGaz", JOptionPane.YES_NO_OPTION);
        
        // проверяем выбор пользователя
        if(button == JOptionPane.YES_OPTION){
            // получаем номер строки (записи)
            int row = tblNote.getSelectedRow();
            
            // удаляем запись, если удаление удачно, обновляем данные
            if(firstItemDao.deleteItem(row))
                getBlocknote();
            else
                JOptionPane.showMessageDialog(null, 
                        "Произошли ошибки во время операции удаления. \n" +
                        "Обратитесь к администратору!", "AbonentGaz", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * 
     */
    private void addNewPlomb(){
        // запрос на добавление
        int button = JOptionPane.showConfirmDialog(null, 
                "Добавить запись?", "AbonentGaz", JOptionPane.YES_NO_OPTION);
        
        // проверяем выбор пользователя
        if(button == JOptionPane.YES_OPTION){
            // получаем код контролёра и код места установки пломбы
            int idControler = getIdControler(0);
            int idInstalplace = getIdInstalPlace(0);
            int[] param = new int[]{idObject,idControler,idInstalplace};
            String fieldName = "IDOBJECT,IDCONTROLER,IDINSTALPLACE";
            String escField = "?,?,?";
            // добавляем запись, если добавление удачно, обновляем данные
            if(firstItemDao.addItem(fieldName, escField, param))
                getPlombsData();
            else
                JOptionPane.showMessageDialog(null, 
                        "Произошли ошибки во время операции добавления. \n" +
                        "Обратитесь к администратору!", "AbonentGaz", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * 
     */
    private void removePlomb(){
        // запрос на добавление нового оборудования
        int button = JOptionPane.showConfirmDialog(null, 
                "Удалить запись?", "AbonentGaz", JOptionPane.YES_NO_OPTION);
        
        // проверяем выбор пользователя
        if(button == JOptionPane.YES_OPTION){
            // получаем номер строки (записи)
            int row = tblPlomb.getSelectedRow();
            
            // удаляем запись, если удаление удачно, обновляем данные
            if(firstItemDao.deleteItem(row))
                getPlombs();
            else
                JOptionPane.showMessageDialog(null, 
                        "Произошли ошибки во время операции удаления. \n" +
                        "Обратитесь к администратору!", "AbonentGaz", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * 
     */
    private void addNewLose(int index){
        // запрос на добавление нового оборудования
        int button = JOptionPane.showConfirmDialog(null, 
                "Добавить запись?", "AbonentGaz", JOptionPane.YES_NO_OPTION);
        
        // проверяем выбор пользователя
        if(button == JOptionPane.YES_OPTION){
            // в зависимости от типа потерь создаём соответствующее окно выбора
            // нового типа потерь
            NewItemDialog dialog = null;
            switch(index){
                case 1:
                    // высокое давление
                    dialog = new NewItemDialog(NewItemDialog.NewItemType.NewHipress);
                    break;
                case 2:
                    // среднее давление
                    dialog = new NewItemDialog(NewItemDialog.NewItemType.NewMidpress);
                    break;
                case 3:
                    // распределительные низкого давения
                    dialog = new NewItemDialog(NewItemDialog.NewItemType.NewRlowpress);
                    break;
                case 4:
                    // дворовые низкого давления
                    dialog = new NewItemDialog(NewItemDialog.NewItemType.NewDlowpress);
                    break;
                case 5:
                    // регуляторы
                    dialog = new NewItemDialog(NewItemDialog.NewItemType.NewRegulator);
                    break;
            }
            dialog.setIndentity(idObject);
            dialog.createNewItem();// создаём элементы интерфейса
            boolean retval = dialog.showDialog(this);
            // проверяем результат действий пользователя
            if(retval){
                switch(index){
                    case 1:// газопроводы высокого давления
                        getHiLoseData();
                        break;
                    case 2:// газопроводы среднего давления
                        getMidLoseData();
                        break;
                    case 3:// газопроводы распределительные низкого давления
                        getRLowLoseData();
                        break;
                    case 4:// газопроводы дворовые низкого давления
                        getDLowLoseData();
                        break;
                    case 5:// регуляторы
                        getRegLose();
                        break;
                }
            }
//            else
//                JOptionPane.showMessageDialog(null, 
//                        "Произошли ошибки во время операции добавления. \n" +
//                        "Обратитесь к администратору!", "AbonentGaz", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Удаляет запись из выбранного типа потерь
     * @return true в случае успеха, в противном случае - false
     * @param tdi объект, содержащий данные, которые нужно удалить
     * 
     */
    private boolean removeLose(TableDaoImpl tdi){
        // запрос на добавление нового оборудования
        int button = JOptionPane.showConfirmDialog(null, 
                "Удалить запись?", "AbonentGaz", JOptionPane.YES_NO_OPTION);
        
        // проверяем выбор пользователя
        if(button == JOptionPane.YES_OPTION){
            // получаем номер строки (записи)
            int row = tblLose.getSelectedRow();
            
            // удаляем запись, если удаление удачно, обновляем данные
            if(tdi.deleteItem(row))
                return true;
            else {
                JOptionPane.showMessageDialog(null, 
                        "Произошли ошибки во время операции удаления. \n" +
                        "Обратитесь к администратору!", "AbonentGaz", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else return false;
    }
    
    /**
     * 
     */
    private void addNewTurnEquipment(){
        // проверяем выбрана ли строка в таблице
        if(tblEquipment.getSelectedRow() != -1){
            // запрос на добавление нового оборудования
            int button = JOptionPane.showConfirmDialog(null, 
                    "Добавить запись?", "AbonentGaz", JOptionPane.YES_NO_OPTION);

            // проверяем выбор пользователя
            if(button == JOptionPane.YES_OPTION){
                // получаем код контролёра, код оборудования и код причины добавления
                int idControler = getIdControler(0);
                int idEquipment = firstItemDao.getItem(tblEquipment.getSelectedRow()).getId();
                int idCase = getIdCase(0);
                int[] param = new int[]{idControler,idEquipment,idCase};
                String fieldName = "IDCONTROLER,IDEQUIPMENT,IDCASE";
                String escField = "?,?,?";
                // добавляем запись, если добавление удачно, обновляем данные
                if(secondItemDao.addItem(fieldName, escField, param))
                    getTurnEquipmentData(idEquipment);
                else
                    JOptionPane.showMessageDialog(null, 
                            "Произошли ошибки во время операции добавления. \n" +
                            "Обратитесь к администратору!", "AbonentGaz", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // уведомляем пользователя о необъодимости выбрать строку в таблице
            JOptionPane.showMessageDialog(null, 
                    "Выберите оборудование в таблице, для которого \n" +
                    "хотите добавить запись.", "AbonentGaz", JOptionPane.WARNING_MESSAGE);
            // устанавливаем фокус в таблице на первую строку
            tblEquipment.setRowSelectionInterval(0, 0);
            tblEquipment.setColumnSelectionInterval(1, 1);

        }
    }
    
    /**
     * 
     */
    private void removeTurnEquipment(){
        // запрос на добавление нового оборудования
        int button = JOptionPane.showConfirmDialog(null, 
                "Удалить запись?", "AbonentGaz", JOptionPane.YES_NO_OPTION);
        
        // проверяем выбор пользователя
        if(button == JOptionPane.YES_OPTION){
            // получаем номер строки (записи)
            int row = tblTurnEquipment.getSelectedRow();
            
            int idEquipment = firstItemDao.getItem(tblEquipment.getSelectedRow()).getId();
            // удаляем запись, если удаление удачно, обновляем данные
            if(secondItemDao.deleteItem(row))
                getTurnEquipmentData(idEquipment);
            else
                JOptionPane.showMessageDialog(null, 
                        "Произошли ошибки во время операции удаления. \n" +
                        "Обратитесь к администратору!", "AbonentGaz", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * 
     */
    private void addNewProject(){
        // запрос на добавление нового оборудования
        int button = JOptionPane.showConfirmDialog(null, 
                "Добавить запись?", "AbonentGaz", JOptionPane.YES_NO_OPTION);
        
        // проверяем выбор пользователя
        if(button == JOptionPane.YES_OPTION){
            int idPodryadchik = getIdProject(0);
            int[] param = new int[]{idObject,idPodryadchik};
            String fieldName = "IDOBJECT,IDPODRYADCHIK";
            String escField = "?,?";
            // добавляем запись, если добавление удачно, обновляем данные
            if(firstItemDao.addItem(fieldName, escField, param))
                getProjectData();
            else
                JOptionPane.showMessageDialog(null, 
                        "Произошли ошибки во время операции добавления. \n" +
                        "Обратитесь к администратору!", "AbonentGaz", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * 
     */
    private void removeProject(){
        // запрос на добавление нового оборудования
        int button = JOptionPane.showConfirmDialog(null, 
                "Удалить запись?", "AbonentGaz", JOptionPane.YES_NO_OPTION);
        
        // проверяем выбор пользователя
        if(button == JOptionPane.YES_OPTION){
            // получаем номер строки (записи)
            int row = tblProject.getSelectedRow();
            
            // удаляем запись, если удаление удачно, обновляем данные
            if(firstItemDao.deleteItem(row))
                getProjectData();
            else
                JOptionPane.showMessageDialog(null, 
                        "Произошли ошибки во время операции удаления. \n" +
                        "Обратитесь к администратору!", "AbonentGaz", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * 
     */
    private void addNewLaunch(){
        // запрос на добавление новой записи в пусковой
        int button = JOptionPane.showConfirmDialog(null, 
                "Добавить запись?", "AbonentGaz", JOptionPane.YES_NO_OPTION);
        
        // проверяем выбор пользователя
        if(button == JOptionPane.YES_OPTION){
            // получаем код контролёра
            int[] param = new int[]{idObject};
            String fieldName = "IDOBJECT";
            String escField = "?";
            // добавляем запись, если добавление удачно, обновляем данные
            if(secondItemDao.addItem(fieldName, escField, param))
                getLaunchData();
            else
                JOptionPane.showMessageDialog(null, 
                        "Произошли ошибки во время операции добавления. \n" +
                        "Обратитесь к администратору!", "AbonentGaz", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * 
     */
    private void removeLaunch(){
        // запрос на добавление нового оборудования
        int button = JOptionPane.showConfirmDialog(null, 
                "Удалить запись?", "AbonentGaz", JOptionPane.YES_NO_OPTION);
        
        // проверяем выбор пользователя
        if(button == JOptionPane.YES_OPTION){
            // получаем номер строки (записи)
            int row = tblLauncher.getSelectedRow();
            
            // удаляем запись, если удаление удачно, обновляем данные
            if(secondItemDao.deleteItem(row))
                getLaunchData();
            else
                JOptionPane.showMessageDialog(null, 
                        "Произошли ошибки во время операции удаления. \n" +
                        "Обратитесь к администратору!", "AbonentGaz", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private boolean removeItem(int index, TableDaoImpl tdi){
        // запрос на удаление выбранной записи
        int button = JOptionPane.showConfirmDialog(null, 
                "Удалить запись?", "AbonentGaz", JOptionPane.YES_NO_OPTION);
        
        // проверяем выбор пользователя
        if(button == JOptionPane.YES_OPTION){
            // удаляем запись, если удаление удачно, обновляем данные
            if(tdi.deleteItem(index))
                return true;
            else {
                JOptionPane.showMessageDialog(null, 
                        "Произошли ошибки во время операции удаления. \n" +
                        "Обратитесь к администратору!", "AbonentGaz", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else return false;
    }
    
    /**
     * Добавление записи в таблицу отключения объектов
     */
    private void addNewTurnObject(){
        // запрос на добавление нового оборудования
        int button = JOptionPane.showConfirmDialog(null, 
                "Добавить запись?", "AbonentGaz", JOptionPane.YES_NO_OPTION);
        
        // проверяем выбор пользователя
        if(button == JOptionPane.YES_OPTION){
            // получаем код контролёра, код причины отключения/подключения
            // и код места установки пломбы
            int idControler = getIdControler(0);
            int idCase = getIdCase(0);
            int idInstalplace = getIdInstalPlace(0);
            int[] param = new int[]{idObject,idControler,idCase,idInstalplace};
            String fieldName = "IDOBJECT,IDCONTROLER,IDCASE,IDINSTALPLACE";
            String escField = "?,?,?,?";
            // добавляем запись, если добавление удачно, обновляем данные
            if(objectTurnDao.addItem(fieldName, escField, param))
                getTurnObjects();
            else
                JOptionPane.showMessageDialog(null, 
                        "Произошли ошибки во время операции добавления. \n" +
                        "Обратитесь к администратору!", "AbonentGaz", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void removeTurnObject(){
        // получаем номер строки для удаления
        int index = tblTurnObject.getSelectedRow();
        if(removeItem(index, objectTurnDao)){
            // если всё удачно прошло, извещаем поьзователя
            MDIObject.getInformDialog(null, "Delete...", InformDialog.InformType.SAVING);
            // обновляем данные
            getTurnObjects();
        }
    }
    
//    private Object[][] getItem(TableDaoImpl tdi){
//        Object[][] content = new Object[tdi.getCount()][];
//        for(int i = 0; i < content.length; i++){
//            TableEntity entity = tdi.getItem(i);
//            content[i] = entity.toDataArray();// заполняем массив
//        }
//        return content;
//    }
    
    private MyTableModel getItemData(TableDaoImpl tdi, int[] colIndex,
            int[] defaultcolIndex, Object[][] defaultContent, JTable table, 
            String propertyname,
            ColumnModelListener colListener){
        String[] columnName = tdi.getColumnName();// наименования и типы
        Class[] columnClass = tdi.getColumnClass();// полей данных
        int[] cols;// массив нередактируемых столбцов
        MyTableModel mtModel;// модель данных
        Object[][] content = new Object[tdi.getCount()][];// данные
        
        
        if(tdi.getCount() > 0){
            // если есть данные, заполняем таблицу
            for(int i = 0; i < content.length; i++){
                TableEntity entity = tdi.getItem(i);
                content[i] = entity.toDataArray();// заполняем массив
            }

            
            // определяем перечень нередактируемых столбцов
            cols = colIndex;
            // открываем доступ к пунктам меню
            table.getComponentPopupMenu().getComponent(0).setEnabled(true);
            table.getComponentPopupMenu().getComponent(1).setEnabled(true);
            
        } else {
            // если данных нет, выводим данные по-умолчанию
            content = defaultContent;
            cols = defaultcolIndex;
            // закрываем доступ к пунктам меню
            table.getComponentPopupMenu().getComponent(0).setEnabled(true);
            table.getComponentPopupMenu().getComponent(1).setEnabled(false);
        }
        
        mtModel = new MDIObject.MyTableModelImpl(content, columnName, columnClass);// заполняем модель
        
        // заполняем таблицу
        MDIObject.fullTableData(cols, mtModel, table);
        // задаём размеры столбцов таблицы
        MDIObject.setTablecolwidth(props, propertyname, table);
        
        // добавляем слушатель модели столбцов
        table.getColumnModel().addColumnModelListener(colListener);
        
        // возвращаем модель данных
        return mtModel;
        
    }
    
    private Object getLoseValue(MyTableModel mtModel, int row, int Col){
        Object value;
        
        if(Col != 1){
                    
            value = mtModel.upgradeCellValue(mtModel.getValueAt(row, Col), Col);

        } else {
            value = getIdLose(otherSPRBox.getSelectedIndex());
        }
        return value;
    }
    
    /**
     * Отображает диалоговое окно для ввода данных по акту разграничения
     * @return в случае внесения данных - true, в противном случае - false
     */
    private void addNewBorderBalance(){
        // запрос на добавление нового акта разграничения
        int button = JOptionPane.showConfirmDialog(null, 
                "Добавить данные по акту разграничения?", "AbonentGaz", JOptionPane.YES_NO_OPTION);
        
        // проверяем выбор пользователя
        if(button == JOptionPane.YES_OPTION){
            NewItemDialog.NewItemType nit = NewItemDialog.NewItemType.NewBorderBalance;
            NewItemDialog dialog = new NewItemDialog(nit);// создаём диалоговое окно
            dialog.setIndentity(idObject);// код объекта для добавления
            dialog.createNewItem();// создаём элементы интерфейса
            boolean retval = dialog.showDialog(this);// получаем результат выбора пользоателя
            
            // проверяем сделанный пользователем выбор
            if(retval) lblLose.setText(getBorderBalance());// обновляем данные по акту разграничения
        }
    }
    
    /**
     * отображает диалоговое окно для изменения данных по акту разграничения
     * @return в случае изменения данных - true, в противном случае - false
     */
    private void showBorderBalance(){
        // отображаем диыалоговое окно для редактирования данных по акту разграничения
        NewItemDialog.NewItemType nit = NewItemDialog.NewItemType.ExistBorderBalance;
        NewItemDialog dialog = new NewItemDialog(nit);// создаём диалоговое окно
        dialog.setIndentity(idObject);// код объекта для добавления
        dialog.createNewItem();// создаём элементы интерфейса
        boolean retval = dialog.showDialog(this);// получаем результат выбора пользоателя
        
        // проверяем сделанный пользователем выбор
        if(retval) lblLose.setText(getBorderBalance());// обновляем данные по акту разграничения
        
    }
    
    private class ObjectInformQuery{

        private TableDaoImpl tdi;
        private final List<Object[]> dataArray;
        private final TableProperty tp;
        private final List<Integer> rows;
        private int numRow = 0;
        // задаём наименования столбцов таблицы
        private final String[] colName;
        private final int[] columns;
        
        public ObjectInformQuery() {
            this.colName = new String[]{"№","A","B","C","D","E","F"};
            this.columns = new int[]{2,3,4,5,6};
            dataArray = new ArrayList<>();
            rows = new ArrayList<>();// начальный размер массива - один элемент
            tp = new TableProperty(tblCommon);
        
        }
        
        /**
         * Очишает метку от имеющихся данных. Отображает таблицу, имеющую по
         * умолчанию 2 строки и 8 столбцов
         */
        public void Clear(){
            dataArray.clear();// очищаем массив
            rows.clear();
            numRow = 0;// начальное значение строк с объединёнными ячейками
            Object[][] content = new Object[1][7];
            AttributiveCellTableModel model = new AttributiveCellTableModel(content, colName);
            String width = tp.getColWidth();
            tblCommon.setModel(model);
            //задаём идентификаторы для столбцов таблицы
            setColumnIdentifiers();
            tp.setColWidth();
        }
        
        public void getInform(){
            dataArray.clear();// очищаем массив
            rows.clear();
            numRow = 0;// начальное значение строк с объединёнными ячейками
            for(int i = 0; i < objectsDao.getCount(); i++){
                OrgObjects orgobjects = objectsDao.getItem(i);
                int Id = orgobjects.getId();
                getObjectName(orgobjects);
                getCounter(Id);
                getCorrector(Id);
                getDatchik(Id);
                getEquipment(Id);
                getBorderBalance(Id);
                getProjects(Id);
                getLaunch(Id);
                getGazUsing(Id);
                numRow++;
            }
            
            // получаем данные для заполнения модели таблицы
            Object[][] content = new Object[dataArray.size()][7];
            for(int i = 0; i < content.length; i++)
                content[i] = dataArray.get(i);
            String width = tp.getColWidth();
            AttributiveCellTableModel model = new AttributiveCellTableModel(content, colName);
            CellSpan cellAtt;
            cellAtt = (CellSpan)model.getCellAttribute();
            tblCommon.setModel(model);
            //задаём идентификаторы для столбцов таблицы
            setColumnIdentifiers();
            tp.setColWidth();
            for(int i = 0; i < rows.size(); i++){
                int[] r = new int[1];
                r[0] = (int)rows.get(i);
                cellAtt.combine(r,columns);
                
            }
            tblCommon.clearSelection();
            tblCommon.revalidate();
            tblCommon.repaint();
            
        }
        
        
        /**
         * задаёт идентификаторы для столбцов таблицы
         */
        private void setColumnIdentifiers(){
            TableColumnModel colmodel = tblCommon.getColumnModel();
            for (int i = 0; i < colmodel.getColumnCount(); i++) {
                TableColumn tc = colmodel.getColumn(i);
                tc.setIdentifier((Object) i);
            }
        }
        
        private void getObjectName(OrgObjects orgobjects){
            rows.add(numRow);
            Object[] item = getArray(numRow + 1, new String[]{"Объект",orgobjects.getNameobject() + 
                        ", " + orgobjects.getNameCity() + ", " +
                        orgobjects.getNameStreet() + ", " + 
                        orgobjects.getAddres(),"","","",""});
            dataArray.add(item);
            
        }
        
        /**
         * Получает данные из базы данных о составляющих узла учёта
         * @param id код объекта, для которого выбираются данные
         */
        private void getCounter(int id){
//            String counters = "";
            tdi = new CountersDaoImpl(id);// получаем информацию по счётчикам
            // проверяем наличие полученных данных
            int count = tdi.getCount();
            if(count > 0){
                numRow++;
                Object[] item = getArray(numRow + 1, new String[]{"Счётчик","","","","",""});
                dataArray.add(item);
                numRow++;
                item = getArray(numRow + 1, new String[]{"Наименование","Номер","Дата поверки",
                    "Период","Код устан","Собст-ть"});
                dataArray.add(item);
                // получаем данные
                Object[][] data = new Object[count][7];// размер массива
                for(int i = 0; i < count; i++){
                    numRow++;
                    Counters c = (Counters) tdi.getItem(i);
                    Object[] itemData = c.toDataArray();
                    String prop = itemData[8].equals(true) ? "да" : "нет" ;
//                    String bgColor = prop.equals("да") ? "#00FF00" : "#C0C0C0";
                    data[i][0] = numRow + 1;
                    data[i][1] = itemData[1];// тип счётчика
                    data[i][2] = itemData[2];// номер
                    data[i][3] = itemData[3];// дата поверки
                    data[i][4] = itemData[6];// период
                    data[i][5] = itemData[7];// код установки
                    data[i][6] = prop;// собственность
                    dataArray.add(data[i]);
                }
                    
            }
//            return counters;
        }
        
        private void getCorrector(int id){
//            String corrector = "";
            tdi = new CorrectorDaoImpl(id);// получаем информацию по корректорам
            // проверяем наличие полученных данных
            int count = tdi.getCount();
            if(count > 0){
                numRow++;
                Object[] item = getArray(numRow + 1, new String[]{"Корректор","","","","",""});
                dataArray.add(item);
                numRow++;
                item = getArray(numRow + 1, new String[]{"Наименование","Номер","Дата поверки",
                    "Период","Собст-ть",""});
                dataArray.add(item);
                // получаем данные
                Object[][] data = new Object[count][7];// размер массива
                for(int i = 0; i < count; i++){
                    numRow++;
                    Corrector c = (Corrector) tdi.getItem(i);
                    Object[] itemData = c.toDataArray();
                    String prop = itemData[9].equals(true) ? "да" : "нет" ;
                    data[i][0] = numRow + 1;
                    data[i][1] = itemData[1];// тип корректора
                    data[i][2] = itemData[2];// номер
                    data[i][3] = itemData[5];// дата поверки
                    data[i][4] = itemData[6];// период
                    data[i][5] = prop;// собственность абонента
                    data[i][6] = "";
                    dataArray.add(data[i]);
                }
                    
            }
//            return corrector;
        }
        
        private void getDatchik(int id){
            tdi = new DatchikDaoImpl(id);// получаем информацию по датчикам
            // проверяем наличие полученных данных
            int count = tdi.getCount();
            if(count > 0){
                numRow++;
                Object[] item = getArray(numRow + 1, new String[]{"Датчики","","","","",""});
                dataArray.add(item);
                numRow++;
                item = getArray(numRow + 1, new String[]{"Наименование","Номер","Дата поверки",
                    "Период","",""});
                dataArray.add(item);
                
                // получаем данные
                Object[][] data = new Object[count][7];// размер массива
                for(int i = 0; i < count; i++){
                    numRow++;
                    Datchik c = (Datchik) tdi.getItem(i);
                    Object[] itemData = c.toDataArray();
                    data[i][0] = numRow + 1;
                    data[i][1] = itemData[1];// тип датчика
                    data[i][2] = itemData[2];// номер
                    data[i][3] = itemData[3];// дата поверки
                    data[i][4] = itemData[4];// период
                    data[i][5] = "";
                    data[i][6] = "";
                    dataArray.add(data[i]);
                }
                    
            }
//            return datchik;
        }
        
        /**
         * Получает данные из базы данных об оборудовании
         * @param id код объекта, для которого выбираются данные
         */
        private void getEquipment(int id){
            tdi = new EquipmentDaoImpl(id);// получаем информацию по счётчикам
            // проверяем наличие полученных данных
            int count = tdi.getCount();
            if(count > 0){
                numRow++;
                Object[] item = getArray(numRow + 1, new String[]{"Оборудование","","","","",""});
                dataArray.add(item);
                numRow++;
                item = getArray(numRow + 1, new String[]{"Наименование","Количество",
                    "Включено","","",""});
                dataArray.add(item);
                
                // получаем данные
                Object[][] data = new Object[count][7];// размер массива
                for(int i = 0; i < count; i++){
                    numRow++;
                    Equipment c = (Equipment) tdi.getItem(i);
                    Object[] itemData = c.toDataArray();
                    String prop = itemData[4].equals(true) ? "да" : "нет" ;
                    data[i][0] = numRow + 1;
                    data[i][1] = itemData[1];// наименование оборудования
                    data[i][2] = itemData[3];// количество
                    data[i][3] = prop;// включено
                    data[i][4] = "";
                    data[i][5] = "";
                    data[i][6] = "";
                    dataArray.add(data[i]);
                }
                    
            }
//            return equipment;
        }
        
        /**
         * Получает данные из базы данных о данных балансового разграничения
         * @param id код объекта, для которого выбираются данные
         */
        private void getBorderBalance(int id){
            // получаем данные по разграничению зон обслуживания
            BorderbalanceDaoImpl balanceDao = new BorderbalanceDaoImpl(id);
            // если данные есть, получаем первую запись
            if(balanceDao.getCount() > 0){
                numRow++;
                Borderbalance balance = balanceDao.getItem(0);
                String prop = balance.getBackup().equals(true) ? "да" : "нет";
                Object[] item = getArray(numRow + 1, new String[]{"Потери","","","","",""});
                dataArray.add(item);
                numRow++;
                item = getArray(numRow + 1, new String[]{"Предмет","Возврат","",
                    "","",""});
                dataArray.add(item);
                numRow++;
                item = getArray(numRow + 1, new String[]{balance.getContent(),prop,"",
                    "","",""});
                dataArray.add(item);
            }
            
        }
        
        /**
         * Получает данные из базы данных о проекте
         * @param id код объекта, для которого выбираются данные
         */
        private void getProjects(int id){
                    
            tdi = new ProjectsDaoImpl(id);// получаем информацию по счётчикам
            // проверяем наличие полученных данных
            int count = tdi.getCount();
            if(count > 0){
                numRow++;
                Object[] item = getArray(numRow + 1, new String[]{"Проект","","","","",""});
                dataArray.add(item);
                numRow++;
                item = getArray(numRow + 1, new String[]{"Описание","Год","",
                    "","",""});
                dataArray.add(item);
                
                // получаем данные
                Object[][] data = new Object[count][7];// размер массива
                for(int i = 0; i < count; i++){
                    numRow++;
                    Projects c = (Projects) tdi.getItem(i);
                    Object[] itemData = c.toDataArray();
                    data[i][0] = numRow + 1;
                    data[i][1] = (String) (itemData[5] + ", " + itemData[6]);// описание проекта
                    data[i][2] = itemData[2].toString();// год изменений
                    data[i][3] = "";
                    data[i][4] = "";
                    data[i][5] = "";
                    data[i][6] = "";
                    dataArray.add(data[i]);
                }
                    
            }
        }
        
        /**
         * Получает данные из базы данных об области использоания газа
         * @param id код объекта, для которого выбираются данные
         */
        private void getGazUsing(int id){
            final GazusingDaoImpl gazUsingDao = new GazusingDaoImpl(idObject);
            // если данные есть
            if(gazUsingDao.getCount() > 0){
                // получаем сущность
                Gazusing using = gazUsingDao.getItem(0);
                numRow++;
                Object[] item = getArray(numRow + 1, new String[]{"Применение","","","","",""});
                dataArray.add(item);
                numRow++;
                item = getArray(numRow + 1, new String[]{using.getUsingType(),"","",
                    "","",""});
                dataArray.add(item);
                
            }
        }
        
        /**
         * Получает данные из базы данных о пуске газа
         * @param id код объекта, для которого выбираются данные
         */
        private void getLaunch(int id){
            tdi = new LauncherDaoImpl(id);// получаем информацию по счётчикам
            // проверяем наличие полученных данных
            int count = tdi.getCount();
            if(count > 0){
                numRow++;
                Object[] item = getArray(numRow + 1, new String[]{"Пусковой","","","","",""});
                dataArray.add(item);
                numRow++;
                item = getArray(numRow + 1, new String[]{"Содержание","Дата","",
                    "","",""});
                dataArray.add(item);
                
                // получаем данные
                Object[][] data = new Object[count][7];// размер массива
                for(int i = 0; i < count; i++){
                    numRow++;
                    Launcher c = (Launcher) tdi.getItem(i);
                    Object[] itemData = c.toDataArray();
                    data[i][0] = numRow + 1;
                    data[i][1] = (String) itemData[2];// содержание
                    data[i][2] = itemData[1].toString();// дата
                    data[i][3] = "";
                    data[i][4] = "";
                    data[i][5] = "";
                    data[i][6] = "";
                    dataArray.add(data[i]);
                }
                    
            }
        }
        
        private Object[] getArray(int index, String[] str){
            Object[] retval = new Object[7];
            retval[0] = index;
            for(int i = 1; i < 7; i++)
                retval[i] = str[i-1];
            return retval;
        }
    }
    
    private static class ObjectTableSpanCellRenderer extends DefaultTableCellRenderer{

        
        public ObjectTableSpanCellRenderer() {
        }
        
        
        @Override
        public Component getTableCellRendererComponent(JTable table, 
                Object value, boolean isSelected, boolean hasFocus, int row, 
                int column){
            super.getTableCellRendererComponent(table, value, isSelected, 
                    hasFocus, row, column);
                // выравнивание содержимого в первом столбце таблицы по центру
            if(value == null)
                setText("");
            else
                setText(value.toString());
            if(column == 0) {
                setHorizontalAlignment(SwingConstants.CENTER);
                setBackground(table.getTableHeader().getBackground());
                setBorder(table.getTableHeader().getBorder());
                setFont(new Font(table.getTableHeader().getFont().getFontName(), 
                        Font.BOLD, table.getTableHeader().getFont().getSize()));
            } else {
                Font f = table.getFont();
                setFont(f);
                if(column == 1){
                    setHorizontalAlignment(SwingConstants.LEADING);
                    if(isSelected){
                       setBackground(table.getSelectionBackground());
                       setForeground(table.getSelectionForeground());
                   } else {
                       setBackground(table.getBackground());
                       setForeground(table.getForeground());
                   }
                }
                else {
                    setHorizontalAlignment(SwingConstants.CENTER);
                    // проверим ширину ячейки во втором столбце
                    if(table.getCellRect(row, column, false).getWidth() > 
                            table.getColumnModel().getColumn(column).getPreferredWidth()){
                        setFont(new Font(f.getFontName(), Font.BOLD, f.getSize()));
                        if(isSelected){
                           setBackground(table.getSelectionBackground());
                           setForeground(table.getSelectionForeground());
                       } else {
                           setBackground(new Color(255, 255, 102));
                           setForeground(table.getForeground());
                       }       
                        
                    } else {
                        if(isSelected){
                           setBackground(table.getSelectionBackground());
                           setForeground(table.getSelectionForeground());
                       } else {
                           setBackground(table.getBackground());
                           setForeground(table.getForeground());
                       }
                    }
                }
            }
            
            
            return this;
        }
    }
    
    /**
     * Выполняет расчёт технологических потерь
     * @param lose потери на газопроводе
     * @param lenth длина газопровода
     * @param age возраст газопровода до 25 лет (дат/нет)
     * @param territory подрабатываемые территории (да/нет)
     * @return результат вычислений птп
     */
    private BigDecimal calculateLose(int row){
        BigDecimal lose = (BigDecimal) tblLose.getValueAt(row, 2);
        BigDecimal lenth = (BigDecimal)tblLose.getValueAt(row, 3);
        boolean age = (boolean)tblLose.getValueAt(row, 4);
        boolean territory = (boolean)tblLose.getValueAt(row, 5);
        BigDecimal deltaAge = BigDecimal.ZERO;
        BigDecimal deltaTerritory = BigDecimal.ZERO;
        BigDecimal retLose;
        BigDecimal Lose = lose.multiply(lenth);// получаем потери в зависимости от длины газопровода
        // проверяем значение возраста газопровода : если до 25 лет - потери не изменяются
        // иначе увеличиваются на 25%
        if(age == false)
            deltaAge = Lose.multiply(BigDecimal.valueOf(0.25));
        
        // проверем значение территории : если на подрабатываемых территориях, то
        // потери увеличиваюстя на 25%, иначе остаются неизменными
        if(territory == true)
            deltaTerritory = Lose.multiply(BigDecimal.valueOf(0.25));
//        System.out.println("lose=" + lose + ", lenth=" + lenth + ", age=" + 
//                age + ", territory=" + territory + ", Lose=" + Lose + 
//                ", deltaAge=" + deltaAge + " deltaTerritory=" + deltaTerritory);
        
        retLose = Lose.add(deltaAge);
        retLose = retLose.add(deltaTerritory);
        System.out.println("retlose=" + retLose);
        return retLose;// возвращаем результат вычислений
    }
    
    /**
     * Получает информацию по узлу учёта газа
     * @param columnName массив наименований столбцов
     * @param columnClass массив данных таблицы
     */
    private void getUUGInform(String[] columnname, Class[] columnClass){
        
        int numRow = 1;// начальное значение строк таблицы
        // содержимое первой строки таблицы - наименование столбцов
        Object[][] str = new Object[][]{{numRow,"Наименование",
            "Тип КИП","Диапазон измерения","Кл. т.","Дата поверки",
            "Дата след. поверки","",""}};
        
        // создаём и заполняем модель таблицы
        MyTableModel mtModel = new MDIObject.MyTableModelImpl(str, columnname, columnClass);
        // заполняем таблицу данными
        MDIObject.fullTableData(new int[]{0}, mtModel, tblAct);
        
        int recCount;// количество записей в наборах данных
        // массив данных для заполнения ячеек таблицы
        
        TableDaoImpl tdi = new CountersDaoImpl(idObject);// получаем данные по счётчикам
        recCount = tdi.getCount();// количество полученных записей
        if(recCount > 0){
            // если данные есть, заносим их в модель
            for(int i = 0; i < recCount; i++){
                // увеличиваем счётчик строк
                numRow++;
                Object[] content = new Object[9];
                // Получаем сущность
                Counters counter = (Counters) tdi.getItem(i);
                Sprcounter co = new Sprcounter(counter.getIdcounter());
                Short period = counter.getPeriod();// межповерочный период
                content[0] = numRow;// номер строки
                content[1] = "Счётчик";// тип КИП
                content[2] = co.getCounterName()+ " G" + co.getQnom().toPlainString() + 
                        " №" + counter.getCounternumber();// наименование
                content[3] = co.getQmin().toPlainString() + "-" + co.getQmax().toPlainString();// диапазон
                content[4] = co.getClass1().toPlainString();// класс точности
                String testDate = counter.getTestdate().toString();// дата поверки
                content[5] = testDate;// дата поверки
                short year = getTestYear(testDate);// получаем год поверки
                content[6] = getNextTestDate(year, period, testDate);// дата следующей поверки
                content[7] = "Vk, м3=";
                content[8] = "Wk, м3=";
                // добавляем строку в модель данных
                mtModel.addRow(content);
            }
            mtModel.setValueAt("Тел. модема: ", 0, 7);
            mtModel.setValueAt("Код корректора: ", 0, 8);
        }
        
        // получаем данные по корректорам
        tdi = new CorrectorDaoImpl(idObject);
        String[][] modem;// массив данных по номеру модема и сетевому адресу корректора
        recCount = tdi.getCount();
        if(recCount > 0){
            modem = new String[recCount][2];// задаём размер массива
            // если данные есть, заносим их в модель
            for(int i = 0; i < recCount; i++){
                // увеличиваем счётчик строк
                numRow++;
                Object[] content = new Object[9];
                // Получаем сущность
                Corrector corrector = (Corrector) tdi.getItem(i);
                Sprcorrector cor = new Sprcorrector(corrector.getIdcorrector());
                content[0] = numRow;
                content[1] = "Корректор";
                content[2] = cor.getCorrectorName()+ " №" + corrector.getCorrectornumber();// наименование
                content[3] = "-";// диапазон измерения
                content[4] = cor.getClass1().toPlainString();// класс точности
                String testDate = corrector.getTestdate().toString();// дата поверки
                content[5] = testDate;
                short year = getTestYear(testDate);// получаем год поверки
                short period = corrector.getPeriod();// получаем период
                content[6] = getNextTestDate(year, period, testDate);// получаем дату следующей поверки
                modem[i][0] = corrector.getModem();// номер модема
                modem[i][1] = corrector.getAddres();// сетевой адрес
                content[7] = "Vc, м3=";
                content[8] = "Qc, м3/ч=";
                // добавляем строку в модель данных
                mtModel.addRow(content);
            }
            // заносим в модель номера модема и сетевого адреса
            String modemNumber = "";
            String corAdres = "";
            for (String[] modem1 : modem) {
                modemNumber = modemNumber + modem1[0] + ", ";
                corAdres = corAdres + modem1[1] + ", ";
            }
            if(modemNumber.length() != 0) modemNumber = modemNumber.substring(0, modemNumber.length() - 2);
            if(corAdres.length() != 0) corAdres = corAdres.substring(0, corAdres.length() - 2);
            mtModel.setValueAt("Тел. модема: " + modemNumber, 0, 7);
            mtModel.setValueAt("Код корректора: " + corAdres, 0, 8);
        }
        
        // получаем данные по датчикам
        tdi = new DatchikDaoImpl(idObject);
        recCount = tdi.getCount();
        if(recCount > 0){
            // если данные есть, заносим их в модель
            for(int i = 0; i < recCount; i++){
                // увеличиваем счётчик строк
                numRow++;
                Object[] content = new Object[9];
                // Получаем сущность
                Datchik datchik = (Datchik) tdi.getItem(i);
                Sprdatchik sd = new Sprdatchik(datchik.getIddatchik());
                content[0] = numRow;
                content[1] = "Преобразователь";
                content[2] = sd.getDatchikName()+ " №" + datchik.getDatchiknumber();// наименование
                content[3] = sd.getDiapazon();// диапазон
                content[4] = sd.getClass1().toPlainString();// класс точности
                String testDate = datchik.getPoverkadate().toString();// дата поверки
                content[5] = testDate;
                short year = getTestYear(testDate);// получаем год поверки
                short period = datchik.getPeriod();// период поверки
                content[6] = getNextTestDate(year, period, testDate);// получаем дату следуюлщей поверки
                content[7] = "";
                content[8] = "";
                // добавляем строку в модель данных
                mtModel.addRow(content);
            }
            mtModel.setValueAt("Qр, м3/ч=", numRow - 2, 7);
            mtModel.setValueAt("P, кПа=", numRow - 2, 8);
            mtModel.setValueAt("К=", numRow - 1, 7);
            mtModel.setValueAt("Т, С=", numRow - 1, 8);
        }
        setPropertyActTable("uuginform_width", columnClass);
    }
    
    /**
     * Вычисляет год поверки прибора из строки даты поверки
     * @param testDate дата поверки прибора
     * @return Возвращает год поверки прибора
     */
    private short getTestYear(String testDate){
        if(testDate.equals("")) return 0;
        // первые четыре символа из строки даты представляют собой год поверки
        String year = testDate.substring(0, 4);
        return Short.valueOf(year);
    }
    
    /**
     * Вычисляет следующий год поверки прибора
     * @param year текущий год поверки
     * @param period период поверки
     * @return следующий год поверки прибора
     */
    private String getNextTestDate(short year, short period, String testDate){
        
        String nextYear = String.valueOf(year + period);
        return testDate.replaceFirst(testDate.substring(0, 4), nextYear);
    }
    
    /**
     * Получает информацию по установленным пломбам на узле учёта газа
     * @param columnName массив наименований столбцов
     * @param columnClass массив данных таблицы
     */
    private void getPlombsInform(String[] columnname, Class[] columnClass){
        int numRow = 1;// начальное значение строк таблицы
        // содержимое первой строки таблицы - наименование столбцов
        Object[][] str = new Object[][]{new Object[]{numRow,"№ п/п","Место установки пломб",
            "№ пломбы/защитного элемента","Дата","Дополнительно"}};
        
        // создаём и заполняем модель данных таблицы
        MyTableModel mtModel = new MDIObject.MyTableModelImpl(str, columnname, columnClass);
        MDIObject.fullTableData(new int[]{0}, mtModel, tblAct);// заполняем таблицу
        int recCount;// количество записей в наборах данных
        // массив данных для заполнения ячеек таблицы
        TableDaoImpl tdi = new PlombsDaoImpl(idObject);// получаем данные
        recCount = tdi.getCount();
        if(recCount > 0){
            // если данные есть, заполняем массив
            for(int i = 0; i < recCount; i++){
                numRow++;// увеичиваем счётчик строк
                Plombs plombs = (Plombs) tdi.getItem(i);// получаем сущность
                Sprplumbsinstalplace spip = new Sprplumbsinstalplace(plombs.getIdinstalplace());
                Object[] content = new Object[6];// размер массива данных
                content[0] = numRow;
                content[1] = numRow - 1;
                content[2] = spip.getPlaceName();
                content[3] = plombs.getPlombnumber();
                content[4] = plombs.getInstaldate().toString();
                content[5] = plombs.getAddility();
                // добавляем массив в список данных
                mtModel.addRow(content);
            }
            
        }
        setPropertyActTable("plombinform_width", columnClass);
    }
    
    /**
     * Получает информацию по данным о производственном - технологических потерях
     * и вычисляет общие потери на газопроводе
     * @param columnName массив наименований столбцов
     * @param columnClass массив данных таблицы
     */
    private void getLoseInform(String[] columnname, Class[] columnClass){
        
        int numRow = 1;// начальное значение строк таблицы
        // содержимое первой строки таблицы - наименование столбцов
        Object[][] str = new Object[][]{new Object[]{numRow,"Газопроводы","Диаметр, мм",
            "Потери, м3/(сут км)","Длина, км","Возраст до 25 лет, (да/нет)",
                    "Подраб. территории, (да/нет)","Всего, м3/сут"}};
        
        // создаём и заполняем модель данных таблицы
        MyTableModel mtModel = new MDIObject.MyTableModelImpl(str, columnname, columnClass);
        MDIObject.fullTableData(new int[]{0}, mtModel, tblAct);// заполняем таблицу
        
        int recCount;// количество записей в наборах данных
        
        // массив данных для заполнения ячеек таблицы
        TableDaoImpl tdi = new HipressloseDaoImpl(idObject);// получаем данные по высокому давлению
        recCount = tdi.getCount();
        if(recCount > 0){
            // если данные есть, заполняем модель
            for(int i = 0; i < recCount; i++){
                numRow++;// увеичиваем счётчик строк
                Hipresslose hpl = (Hipresslose) tdi.getItem(i);
                Sprgazlinerhipress shpl = new Sprgazlinerhipress(hpl.getIdgazhipress());
                Object[] content = new Object[8];
                content[0] = numRow;
                content[1] = "Газопроводы в/д";
                content[2] = shpl.getDimeter();
                content[3] = shpl.getLose().toPlainString();
                content[4] = hpl.getHplenght().toPlainString();
                content[5] = hpl.getTo25year() == true ? "да" : "нет";
                content[6] = hpl.getPodrabterritory() == true ? "да" : "нет";
                content[7] = hpl.getResult().toPlainString();
                mtModel.addRow(content);
            }
        }
        tdi = new MidpressloseDaoImpl(idObject);// получаем данные по среднему давлению
        recCount = tdi.getCount();
        if(recCount > 0){
            // если данные есть, заполняем модель
            for(int i = 0; i < recCount; i++){
                numRow++;// увеичиваем счётчик строк
                Midpresslose mpl = (Midpresslose) tdi.getItem(i);
                Sprgazlinermidpress smpl = new Sprgazlinermidpress(mpl.getIdgazmidpress());
                Object[] content = new Object[8];
                content[0] = numRow;
                content[1] = "Газопроводы с/д";
                content[2] = smpl.getDimeter();
                content[3] = smpl.getLose().toPlainString();
                content[4] = mpl.getMplenght().toPlainString();
                content[5] = mpl.getTo25year() == true ? "да" : "нет";
                content[6] = mpl.getPodrabterritory() == true ? "да" : "нет";
                content[7] = mpl.getResult().toPlainString();
                mtModel.addRow(content);
            }
        }
        tdi = new RlowpressloseDaoImpl(idObject);// получаем данные по высокому давлению
        recCount = tdi.getCount();
        if(recCount > 0){
            // если данные есть, заполняем модель
            for(int i = 0; i < recCount; i++){
                numRow++;// увеичиваем счётчик строк
                Rlowpresslose rpl = (Rlowpresslose) tdi.getItem(i);
                Sprgazlinerrlowpress srpl = new Sprgazlinerrlowpress(rpl.getIdgazrlowpress());
                Object[] content = new Object[8];
                content[0] = numRow;
                content[1] = "Газопроводы распределительные н/д";
                content[2] = srpl.getDimeter();
                content[3] = srpl.getLose().toPlainString();
                content[4] = rpl.getRlowlenght().toPlainString();
                content[5] = rpl.getTo25year() == true ? "да" : "нет";
                content[6] = rpl.getPodrabterritory() == true ? "да" : "нет";
                content[7] = rpl.getResult().toPlainString();
                mtModel.addRow(content);
            }
        }
        tdi = new DlowloseDaoImpl(idObject);// получаем данные по низкому давлению
        recCount = tdi.getCount();
        if(recCount > 0){
            // если данные есть, заполняем модель
            for(int i = 0; i < recCount; i++){
                numRow++;// увеичиваем счётчик строк
                Dlowlose dpl = (Dlowlose) tdi.getItem(i);
                Sprgazlinerdlowpress sdpl = new Sprgazlinerdlowpress(dpl.getIdgazdlowpress());
                Object[] content = new Object[8];
                content[0] = numRow;
                content[1] = "Газопроводы дворовые н/д";
                content[2] = sdpl.getDimeter();
                content[3] = sdpl.getLose().toPlainString();
                content[4] = dpl.getDlowlenght().toPlainString();
                content[5] = dpl.getTo25year() == true ? "да" : "нет";
                content[6] = dpl.getPodrabterritory() == true ? "да" : "нет";
                content[7] = dpl.getResult().toPlainString();
                mtModel.addRow(content);
            }
        }
        tdi = new RegloseDaoImpl(idObject);// полуучаем данные по регуляторам
        recCount = tdi.getCount();
        if(recCount > 0){
            numRow++;// увеичиваем счётчик строк
            // если данные есть, заполняем модель
            mtModel.addRow(new Object[]{numRow,"Регуляторы","Тип регулятора",
                "","","Потери, м3/сут","Количество, шт","Всего, м3/сут"});
            for(int i = 0; i < recCount; i++){
                numRow++;// увеичиваем счётчик строк
                Reglose rl = (Reglose) tdi.getItem(i);
                Sprgazregulator sgr = new Sprgazregulator(rl.getIdregulator());
                Object[] content = new Object[8];
                content[0] = numRow;
                content[1] = "ГРП, БК ГРП, ШРП, ДКРТ";
                content[2] = sgr.getName();
                content[3] = "";
                content[4] = "";
                content[5] = sgr.getLose().toPlainString();
                content[6] = rl.getRegcount();
                content[7] = rl.getResult().toPlainString();
                mtModel.addRow(content);
            }
        }
        setPropertyActTable("loseinform_width", columnClass);
    }
    
    /**
     * Получает информацию по данным о поверке составляющих узла учёта газа
     * для уведомления о поверке
     * @param columnName массив наименований столбцов
     * @param columnClass массив данных таблицы
     */
    private void getPoverkaInform(String[] columnname, Class[] columnClass){
        int numRow = 1;// начальное значение строк таблицы
        // содержимое первой строки таблицы - наименование столбцов
        Object[][] str = new Object[][]{new Object[]{numRow,"Наименование","Тип СИТ",
            "Дата поверки","Дата следующей поверки"}};
        
        // создаём и заполняем модель данных таблицы
        MyTableModel mtModel = new MDIObject.MyTableModelImpl(str, columnname, columnClass);
        MDIObject.fullTableData(new int[]{0}, mtModel, tblAct);// заполняем таблицу
        
        int recCount;// количество записей в наборах данных
        
        // массив данных для заполнения ячеек таблицы
        TableDaoImpl tdi = new CountersDaoImpl(idObject);// получаем данные по счётчикам
        recCount = tdi.getCount();// количество полученных записей
        if(recCount > 0){
            // если данные есть, заносим их в модель
            for(int i = 0; i < recCount; i++){
                // увеличиваем счётчик строк
                numRow++;
                Object[] content = new Object[5];
                // Получаем сущность
                Counters counter = (Counters) tdi.getItem(i);
                Sprcounter co = new Sprcounter(counter.getIdcounter());
                Short period = counter.getPeriod();// межповерочный период
                content[0] = numRow;// номер строки
                content[1] = "Счётчик";// тип КИП
                content[2] = co.getCounterName()+ " G" + co.getQnom().toPlainString() + 
                        " №" + counter.getCounternumber();// наименование
                String testDate = counter.getTestdate().toString();// дата поверки
                content[3] = testDate;// дата поверки
                short year = getTestYear(testDate);// получаем год поверки
                content[4] = getNextTestDate(year, period, testDate);// дата следующей поверки
                // добавляем строку в модель данных
                mtModel.addRow(content);
            }
        }
        
        // получаем данные по корректорам
        tdi = new CorrectorDaoImpl(idObject);
        String[][] modem;// массив данных по номеру модема и сетевому адресу корректора
        recCount = tdi.getCount();
        if(recCount > 0){
            modem = new String[recCount][];// задаём размер массива
            // если данные есть, заносим их в модель
            for(int i = 0; i < recCount; i++){
                // увеличиваем счётчик строк
                numRow++;
                Object[] content = new Object[5];
                // Получаем сущность
                Corrector corrector = (Corrector) tdi.getItem(i);
                Sprcorrector cor = new Sprcorrector(corrector.getIdcorrector());
                content[0] = numRow;
                content[1] = "Корректор";
                content[2] = cor.getCorrectorName()+ " №" + corrector.getCorrectornumber();// наименование
                String testDate = corrector.getTestdate().toString();// дата поверки
                content[3] = testDate;
                short year = getTestYear(testDate);// получаем год поверки
                short period = corrector.getPeriod();// получаем период
                content[4] = getNextTestDate(year, period, testDate);// получаем дату следующей поверки
                // добавляем строку в модель данных
                mtModel.addRow(content);
            }
            
        }
        
        // получаем данные по датчикам
        tdi = new DatchikDaoImpl(idObject);
        recCount = tdi.getCount();
        if(recCount > 0){
            // если данные есть, заносим их в модель
            for(int i = 0; i < recCount; i++){
                // увеличиваем счётчик строк
                numRow++;
                Object[] content = new Object[5];
                // Получаем сущность
                Datchik datchik = (Datchik) tdi.getItem(i);
                Sprdatchik sd = new Sprdatchik(datchik.getIddatchik());
                content[0] = numRow;
                content[1] = "Датчик";
                content[2] = sd.getDatchikName()+ " №" + datchik.getDatchiknumber();// наименование
                String testDate = datchik.getPoverkadate().toString();// дата поверки
                content[3] = testDate;
                short year = getTestYear(testDate);// получаем год поверки
                short period = datchik.getPeriod();// период поверки
                content[4] = getNextTestDate(year, period, testDate);// получаем дату следуюлщей поверки
                // добавляем строку в модель данных
                mtModel.addRow(content);
            }
        }
        setPropertyActTable("poverkainform_width", columnClass);
    }
    
    /**
     * Задаёт свойства для таблицы Актов и уведомлений
     * @param propWidth имя свойства в файле свойств для установки ширины столбцов
     * @param columnClass массив типов данных столбцов таблицы
     */
    private void setPropertyActTable(String propWidth, Class[] columnClass){
        // создаём слушатель модели столбцов таблицы
        ColumnModelListener colListener = new ColumnModelListener(tblAct, props, propWidth);
        MDIObject.setTablecolwidth(props, propWidth, tblAct);// задаём ширину таблицы
        
        // добавляем слушатель к модели столбцов
        tblAct.getColumnModel().addColumnModelListener(colListener);
        
        // назначаем рисовальщиков для столбцов
        TableCell_Renderer.setWordWrapCellRenderer(tblAct, columnClass);
        TableCell_Renderer.setIntegerTableCellRenderer(tblAct, Color.LIGHT_GRAY);
        
        MDIObject.addColumnSelectionListener(tblAct);
    }
}
