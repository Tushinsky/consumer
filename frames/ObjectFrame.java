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

    private int idCompany;// ������������� �������� (���)
    private int idObject;// ������������� (���) �������
    private final String fileNameProperty = "objectframe.properties";
    private SprcityDaoImpl sprCityDao;
    private SprstreetDaoImpl sprStreetDao;
    private ObjectsDaoImpl objectsDao;// ������ � ������ �� ��������
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
    private TableDaoImpl sprTableDao;// ������ ������� ��� ������������ ����������, ��� � ������������
    private TableDaoImpl anothersprTableDao;// ������ ������� ��� ������������ ���� ��������� ����� � ������ ����������
    private SprequipmentDaoImpl sprEquipDao;
    private final UserProperties props = new UserProperties(fileNameProperty);
    private final TableProperty tprops = new TableProperty();// ����� ��� ��������� �������� �������� ������
    private ColumnModelListener ObjectcolumnListener;
    private ColumnModelListener ObjectturncolumnListener;
    private ColumnModelListener UzelcolumnListener;
    private ColumnModelListener LosecolumnListener;
    private ColumnModelListener CommoncolumnListener;
    private final JComboBox cityBox;// ������ ��������� �������
    private final JComboBox streetBox;// � ���� ��� ������� ��������
    private final JComboBox controlerBox;// ������ ���������� � ������� ���������� ��������
    private final JComboBox caseBox;// ������ ������ ���������� � ������� ���������� ��������
    private final JComboBox instalBox;// ������ ���� ��������� ����� � ������� ���������� ��������
    private final JComboBox otherSPRBox;// ������ ����������, ������������ ��� � ������������
    private final JComboBox anotherSPRBox;// ������ ���� ��������� ����� � ������ ����������
    private final JComboBox sprEquipBox;// ���������� ������������
    private final ObjectInformQuery oiq;
    /**
     * Creates new form ObjectFrame
     */
    public ObjectFrame() {
        initComponents();
        cityBox = new JComboBox();// ������ ��������� �������
        streetBox = new JComboBox();// � ���� ��� ������� ��������
        controlerBox = new JComboBox();// ������ ���������� � ������� ���������� ��������
        caseBox = new JComboBox();// ������ ������ ���������� � ������� ���������� ��������
        instalBox = new JComboBox();// ������ ���� ��������� ����� � ������� ���������� ��������
        otherSPRBox = new JComboBox();// ������ ����������, ������������ ��� � ������������
        anotherSPRBox = new JComboBox();// ������ ���� ��������� ����� � ������ ����������
        sprEquipBox = new JComboBox();
        tblCommon = new MultiSpanCellTableEx();
        Object[][] content = new Object[1][7];
        String[] colName = new String[]{"�","A","B","C","D","E","F"};
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

        mnuAddnewobject.setText("�������� ����� ������");
        mnuAddnewobject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuAddnewobjectActionPerformed(evt);
            }
        });
        objectPopupMenu.add(mnuAddnewobject);

        mnuRemoveobject.setText("������� ������");
        mnuRemoveobject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuRemoveobjectActionPerformed(evt);
            }
        });
        objectPopupMenu.add(mnuRemoveobject);

        mnuUpdateobject.setText("�������� ������");
        mnuUpdateobject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuUpdateobjectActionPerformed(evt);
            }
        });
        objectPopupMenu.add(mnuUpdateobject);
        objectPopupMenu.add(jSeparator1);

        mnuExportobject.setText("������� �������");

        mnuExportObjectExcel.setText("������� ������� � ���� XLS");
        mnuExportobject.add(mnuExportObjectExcel);

        mnuExportObjectODS.setText("������� ������� � ���� ODS");
        mnuExportobject.add(mnuExportObjectODS);

        mnuExportObjectCSV.setText("������� ������� � ���� CSV");
        mnuExportobject.add(mnuExportObjectCSV);

        objectPopupMenu.add(mnuExportobject);

        mnuTurnAddNew.setText("�������� ����� ������");
        mnuTurnAddNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuTurnAddNewActionPerformed(evt);
            }
        });
        turnObjectPopupMenu.add(mnuTurnAddNew);

        mnuTurnRemove.setText("������� ������");
        mnuTurnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuTurnRemoveActionPerformed(evt);
            }
        });
        turnObjectPopupMenu.add(mnuTurnRemove);
        turnObjectPopupMenu.add(jSeparator4);

        mnuExportTurn.setText("������� �������");

        mnuExportTurnExcel.setText("������� ������� � ���� XLS");
        mnuExportTurn.add(mnuExportTurnExcel);

        mnuExportTurnODS.setText("������� ������� � ���� ODS");
        mnuExportTurn.add(mnuExportTurnODS);

        mnuExportTurnCSV.setText("������� ������� � ���� CSV");
        mnuExportTurn.add(mnuExportTurnCSV);

        turnObjectPopupMenu.add(mnuExportTurn);

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("�������");
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

        jTabbedPane1.addTab("�����", jPanel1);

        uzelCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "�������� ���", "�������", "���������", "�������" }));
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

        jLabel1.setText("���� �����");

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

        jTabbedPane1.addTab("���� �����", jPanel2);

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

        jTabbedPane1.addTab("�������", jPanel3);

        tblPlomb.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "��������� 1", "��������� 2", "��������� 3", "��������� 4"
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

        jTabbedPane1.addTab("������", jPanel4);

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

        jLabel2.setText("��� ������");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        loseCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "�������� ��� ������", "���������� ��", "���������� ��", "���������� ���", "���������� ���", "����������" }));
        loseCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loseComboActionPerformed(evt);
            }
        });

        losePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("�������� ������"), "�������� ������"));
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

        jTabbedPane1.addTab("������", jPanel5);

        jLabel3.setText("������� �������������");

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

        jTabbedPane1.addTab("������������", jPanel6);

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

        jTabbedPane1.addTab("������/��������", jPanel7);

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

        jLabel4.setText("������������ ����");

        cmbAct.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "����/�����������", "��� ������������ ���", "��� ������������ ���", "��� ������� ������", "����������� � �������" }));
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

        jTabbedPane1.addTab("����/�����������", jPanel8);

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
        
        // ���������� ������������ ������������ �� ����� �������
        SplitLayoutManager slm = new SplitLayoutManager(fileNameProperty);
        slm.setKeyName("vdivider");
        int location = slm.getLocation();
        if(location != 0)
            vertSplitPane.setDividerLocation(location);// ������������ ������������� �����������
        slm.setKeyName("hdivider");
        location = slm.getLocation();
        if(location != 0)
            horSplitPane.setDividerLocation(location);
        
        slm.setKeyName(equipSplitPane.getName());
        location = slm.getLocation();
        if(location != 0)
            equipSplitPane.setDividerLocation(location);// ������������ ����������� ������������
        
        slm.setKeyName(projectSplitPane.getName());
        location = slm.getLocation();
        if(location != 0)
            projectSplitPane.setDividerLocation(location);// ������������ ����������� �������
        
        // ��������� ������ ������� � ����
        addListItem();
        
        // ��������� ���������� ������� �������� ������
        setColumnListener();
        
        // ���������� ������ � �������� ���
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        
        // ��������� ������ - ������������ ��� ������� ������� ������
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
        
        // ��������� ������ - ������������ ��� �������� � ����������� �������
        TableCell_Renderer.setBooleanTablecellRenderer(tblObject);
        TableCell_Renderer.setBooleanTablecellRenderer(tblEquipment);
        TableCell_Renderer.setBooleanTablecellRenderer(tblUzel);
        TableCell_Renderer.setBooleanTablecellRenderer(tblLose);
        
        // ��������� ���������� ��� �������� �� �������� � ������� ������� ������
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
        
        // ����������� ���� ��� ����� � ����� �����������
        JPopupMenu commonMenu = new JPopupMenu();
        JMenuItem previewMenu = new JMenuItem("Show preview");
        JMenuItem printMenu = new JMenuItem("Print table");
        previewMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ������ ����� ��� ���������������� ��������� � ������
                Printer_Job pjob = new Printer_Job();
                pjob.setAttributes(new HashPrintRequestAttributeSet());
                pjob.setGraphics(getGraphics());
                pjob.Page_Setup();
                pjob.Print_Preview();// ���������� ���� ���������������� ���������
                
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
        // ��� ��������� �������� dividerLocation ��������� ��� �������� � ����� �������
//        System.out.println("vsplit " + evt.getPropertyName());
        if(evt.getPropertyName().equals("dividerLocation")){
            // ����� ��� ������ �������
            SplitLayoutManager slManager = new 
                    SplitLayoutManager(fileNameProperty);
            // ������� ��� �����������, ��� �������� ����� �������� ���������
            slManager.setKeyName("vdivider");
            slManager.setLocation(vertSplitPane.getDividerLocation());
        }
    }//GEN-LAST:event_vertSplitPanePropertyChange

    private void horSplitPanePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_horSplitPanePropertyChange
        // ��� ��������� �������� dividerLocation ��������� ��� �������� � ����� �������
//        System.out.println("hsplit " + evt.getPropertyName());
        if(evt.getPropertyName().equals("dividerLocation")){
            // ����� ��� ������ �������
            SplitLayoutManager slManager = new 
                    SplitLayoutManager(fileNameProperty);
            // ������� ��� �����������, ��� �������� ����� �������� ���������
            slManager.setKeyName("hdivider");
            slManager.setLocation(horSplitPane.getDividerLocation());
        }
    }//GEN-LAST:event_horSplitPanePropertyChange

    private void tblObjectMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblObjectMousePressed
        // TODO add your handling code here:
//        System.out.println("row pressed=" + tblObject.getSelectedRow());
    }//GEN-LAST:event_tblObjectMousePressed

    private void tblObjectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblObjectMouseClicked
        // �������� ���������� ������
        if(objectsDao.getCount() > 0){
            if(tblObject.getSelectedRowCount() > 0){
                int rowID;
                // ���� ���� ���������� ������, �������� ��������� ������
                // � ��� ���
                rowID = objectsDao.getItem(tblObject.getSelectedRow()).getId();
                
                // ���������� ��� � ���������� �����, ���� ��������, ��������
                // ������ � ���������� �� ����
                if(rowID != idObject){
                    idObject = rowID;
                    setObjectsFilter();
                }
            }
        }
    }//GEN-LAST:event_tblObjectMouseClicked

    private void tblObjectKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblObjectKeyReleased
        // ��� ����������� �� ������� ������� �������������� �������� � ��� �������
        // ������ ��� ������� ������ � �������
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
        // � ����������� �� ������ ��� ���������� ������
        int index = uzelCombo.getSelectedIndex();
        switch(index){
            case 0:
                // ���������� ������
                String[] columnNames = new String[]{"Title 1","Title 2","Title 3","Title 4"};
                DefaultTableModel model = new DefaultTableModel(columnNames, 1);
                
                // ������� ��������� ������ ��������
                tblUzel.getColumnModel().removeColumnModelListener(UzelcolumnListener);
                tblUzel.setModel(model);
                break;
            case 1:
                // ���������� �� ���������
                getCounter();
                // ��������� ��������� ������ ��������
                UzelcolumnListener = new ColumnModelListener(tblUzel, props, "counterwidth");
//                tblUzel.getColumnModel().addColumnModelListener(UzelcolumnListener);
                break;
            case 2:
                // ���������� �� �����������
                getCorrector();
                // ��������� ��������� ������ ��������
                UzelcolumnListener = new ColumnModelListener(tblUzel, props, "correctorwidth");
//                tblUzel.getColumnModel().addColumnModelListener(UzelcolumnListener);
                break;
            case 3:
                // ���������� �� ��������
                getDatchik();
                // ��������� ��������� ������ ��������
                UzelcolumnListener = new ColumnModelListener(tblUzel, props, "datchikwidth");
//                tblUzel.getColumnModel().addColumnModelListener(UzelcolumnListener);
                break;
        }
        
    }//GEN-LAST:event_uzelComboActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        // � ����������� �� ��������� ������� ��������� ���������� �� �������
        switch(jTabbedPane1.getSelectedIndex()){
            case 1:
                // ���� �����: ���������� ������ ������� � �������� ���������
                uzelCombo.setSelectedIndex(0);
                break;
            case 2:
                // �������
                getBlocknote();
                break;
            case 3:
                // ������
                getPlombs();
                break;
            case 4:
                // ������
                // �������� �������� ������, ���� ��� ����
                lblLose.setText(getBorderBalance());
                loseCombo.setSelectedIndex(0);// ���������� ��������� �������
                break;
            case 5:
                // ������������
                // �������� ������ �� ������������
                getEquipment();
                
                break;
            case 6:
                // ������� � ��������
                getProject();
                getLaunch();
                break;
            case 7:
                // ���� � �����������
                cmbAct.setSelectedIndex(0);// �������� ������ ������� � ������
                break;
        }
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void loseComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loseComboActionPerformed
        // � ����������� �� ������ ���������� ��������� ������ ���
        // ������������ � �������� ���������
        
        switch(loseCombo.getSelectedIndex()){
            case 0:
                // ���������� ������
                String[] columnNames = new String[]{"Title 1","Title 2","Title 3","Title 4"};
                DefaultTableModel model = new DefaultTableModel(columnNames, 1);
                // ������� ��������� ��������
                tblLose.getColumnModel().removeColumnModelListener(LosecolumnListener);
                tblLose.setModel(model);
                break;
            case 1:
                // ������� ��������� ��������
                tblLose.getColumnModel().removeColumnModelListener(LosecolumnListener);
                // �������� ������
                getHiLose();
                break;
            case 2:
                // ������� ��������� ��������
                tblLose.getColumnModel().removeColumnModelListener(LosecolumnListener);
                // �������� ������
                getMidLose();
                break;
            case 3:
                // ������� ��������� ��������
                tblLose.getColumnModel().removeColumnModelListener(LosecolumnListener);
                // �������� ������
                getRLowLose();
                break;
            case 4:
                // ������� ��������� ��������
                tblLose.getColumnModel().removeColumnModelListener(LosecolumnListener);
                // �������� ������
                getDLowLose();
                break;
            case 5:
                // ������� ��������� ��������
                tblLose.getColumnModel().removeColumnModelListener(LosecolumnListener);
                // �������� ������
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
        // ����������� ��������� ������������ �����������
        if(evt.getPropertyName().equals("dividerLocation")){
            // ���������� ����� ������������ �����������
            // ����� ��� ������ �������
            SplitLayoutManager slManager = new 
                    SplitLayoutManager(fileNameProperty);
            // ������� ��� �����������, ��� �������� ����� �������� ���������
            slManager.setKeyName(equipSplitPane.getName());
            slManager.setLocation(equipSplitPane.getDividerLocation());
        }
    }//GEN-LAST:event_equipSplitPanePropertyChange

    private void projectSplitPanePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_projectSplitPanePropertyChange
        // ����������� ��������� ������������ �����������
        if(evt.getPropertyName().equals("dividerLocation")){
            // ���������� ����� ������������ �����������
            // ����� ��� ������ �������
            SplitLayoutManager slManager = new 
                    SplitLayoutManager(fileNameProperty);
            // ������� ��� �����������, ��� �������� ����� �������� ���������
            slManager.setKeyName(projectSplitPane.getName());
            slManager.setLocation(projectSplitPane.getDividerLocation());
        }
    }//GEN-LAST:event_projectSplitPanePropertyChange

    private void mnuAddnewobjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuAddnewobjectActionPerformed
        // ���������� ������������, ������������� �� �� ����� �������� ����� ������
        // ��� �������� �������������� ������������� �������, �������������� ������ �����������
        addNewObject();
    }//GEN-LAST:event_mnuAddnewobjectActionPerformed

    private void mnuRemoveobjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuRemoveobjectActionPerformed
        // �������� �������
        removeObject();
    }//GEN-LAST:event_mnuRemoveobjectActionPerformed

    private void mnuUpdateobjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuUpdateobjectActionPerformed
        // ��������� ������ �� ��������
        getObjects();
    }//GEN-LAST:event_mnuUpdateobjectActionPerformed

    /**
     * ��������� ����� ������ �� ���������� ��� ����������� �������
     * @param evt 
     */
    private void mnuTurnAddNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuTurnAddNewActionPerformed
        addNewTurnObject();
        
    }//GEN-LAST:event_mnuTurnAddNewActionPerformed

    /**
     * ������� ������� ������ �� ����������/����������� �������
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
        ��� ������� ����� �� ������ ������� ������ �� ���� �������������
        ���� ���� ������ ���, �� ���������� ������������ �������� ������
        */
        // ��������� ������� ������ � �����
        if(lblLose.getText().equals("")){
            // ���� ������ ���, �� ���������� ������������ ������
            addNewBorderBalance();// ���� ������ ���������, ��������� ����������
        } else {
            // ���� ������ ����, �� ���������� �� ��� �������� ���������
            showBorderBalance();
        }
    }//GEN-LAST:event_losePanelMouseClicked

    private void cmbActActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbActActionPerformed
        // � ����������� �� ������ ������������ ������� ��������������� ������� � �������
        
        // ���� ������ �������
        Class[] columnClass;// ��� ������ �������� �������
        String[] columnName;// ������������ ��������
        String[] columns = new String[]{"","A","B","C","D","E","F","G","H"};
        Class[] className = new Class[]{Integer.class,String.class,String.class,
                    String.class,String.class,String.class,String.class,String.class,String.class};
        switch(cmbAct.getSelectedIndex()){
            case 0:// ����� ������ �������
                // ��������� �������
                columnName = new String[4];
                columnClass = new Class[4];
                
                System.arraycopy(columns, 0, columnName, 0, 4); // ��������� ������������ �������� �������
                System.arraycopy(className, 0, columnClass, 0, 4);// ��������� ���� ������ �������
                // ������ ������� �� ���������
                tblAct.setModel(new DefaultTableModel(1, 4));
                break;
            case 1:// ��� ������������ ���
                // ��������� �������
                columnName = new String[9];
                columnClass = new Class[9];
                
                System.arraycopy(columns, 0, columnName, 0, 9); // ��������� ������������ �������� �������
                System.arraycopy(className, 0, columnClass, 0, 9);// ��������� ���� ������ �������
                getUUGInform(columnName, columnClass);
                break;
            case 2:// ��� ������������ ���
                // ��������� �������
                columnName = new String[6];
                columnClass = new Class[6];
                
                System.arraycopy(columns, 0, columnName, 0, 6); // ��������� ������������ �������� �������
                System.arraycopy(className, 0, columnClass, 0, 6);// ��������� ���� ������ �������
                getPlombsInform(columnName, columnClass);
                break;
            case 3:// ��� ������� ������
                // ��������� �������
                columnName = new String[8];
                columnClass = new Class[8];
                
                System.arraycopy(columns, 0, columnName, 0, 8); // ��������� ������������ �������� �������
                System.arraycopy(className, 0, columnClass, 0, 8);// ��������� ���� ������ �������
                getLoseInform(columnName, columnClass);
                break;
            case 4:// ����������� � �������
                // ��������� �������
                columnName = new String[5];
                columnClass = new Class[5];
                
                System.arraycopy(columns, 0, columnName, 0, 5); // ��������� ������������ �������� �������
                System.arraycopy(className, 0, columnClass, 0, 5);// ��������� ���� ������ �������
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
        // �������� ���������� �� �������� ��������� �����������
        getObjects();
    }
    
    /**
     * �������� ���������� �� �������� ������ �����������
     */
    private void getObjects(){
        objectsDao = new ObjectsDaoImpl(idCompany);
        final MyTableModel mtModel;
//        oiq.Clear();
        // ���������� ������ �������� �������
        TableProperty tp = new TableProperty(tblObject);
        String colWidth = tp.getColWidth();
        
        // �������� ������ � ��������� �������
        if(objectsDao.getCount() > 0)
            // �������� ��� ������� �������
            idObject = objectsDao.getItem(0).getId();
        else
            idObject = 0;
        
        
        mtModel = getItemData(objectsDao, new int[]{0}, new int[]{0,1,2,3,4,5,6,7,8}, 
                new Object[][]{new Object[]{"","","","","","","","",false}}, 
                tblObject, "tblobjectwidth", ObjectcolumnListener);

        // ��������� ��������� ��������� �������
        mtModel.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                int Row = e.getFirstRow();// ����� ������������� ������
                int Col = e.getColumn();// ����� �������������� �������
                Object value;
                // ��������� � ����� ������� ����������� ��������������
                switch(Col){
                    case 2:
                        // �����
                        value = getIdCity(cityBox.getSelectedIndex());
                        break;
                    case 3:
                        // �����
                        value = getIdStreet(streetBox.getSelectedIndex());
                        break;
                    default:
                        // ��������� ����
                        // ������� �� ����� � �� �����
                        value = mtModel.upgradeCellValue(mtModel.getValueAt(Row,
                                Col), Col);
                        break;
                }
                OrgObjects objects = objectsDao.getItem(Row);
                objects.updateEntity(Col, value);

            }
        });

        // ����� ������ �������� �������
        tp.setColWidth();
        
        // ��������� ������ � �������� ������ ��� ������� �������� � ��������
        // ������ ������� � ����
        setObjectCellEditor();
        
        // �������� ������ ������ �������
        tblObject.setRowSelectionInterval(0, 0);
        tblObject.setColumnSelectionInterval(1, 1);
        tblObject.setFocusable(true);// ������� ����� � ������� ��������
//        tblObject.getColumnModel().getSelectionModel().addListSelectionListener(
//                new ListSelectionListener() {
//
//            @Override
//            public void valueChanged(ListSelectionEvent e) {
//                if(tblObject.getSelectedColumn() == 0)
//                    tblObject.setColumnSelectionInterval(1, 1);
//            }
//        });
        
        
        // �������� ���������� �� �������
        setObjectsFilter();
    }
    
    private void setObjectsFilter(){
        if(idObject != 0){
            oiq.getInform();
        } else {
            oiq.Clear();
            
        }
        jTabbedPane1.setSelectedIndex(0);// ��������� �� ������� �����
        getTurnObjects();// ���������� �� ���������� �������
    }
    
    /**
     * ���������� ���������� �� ���������� ������� �������
     */
    private void getTurnObjects(){
//        System.out.println("getturnObjects idObject=" + idObject);
        if(idObject != 0){
            objectTurnDao = new TurndownDaoImpl(idObject);
            final MyTableModel mtModel;

            // ���������� ������ �������� �������
            tprops.setTable(tblTurnObject);
            String colWidth = tprops.getColWidth();

            mtModel = getItemData(objectTurnDao, new int[]{0}, new int[]{0,1,2,3,4,5,6,7}, 
                    new Object[][]{new Object[]{"","","","","","","",""}}, 
                    tblTurnObject, "tblturnobjectwidth", ObjectturncolumnListener);

            // ��������� ���������
            mtModel.addTableModelListener(new TableModelListener() {

                @Override
                public void tableChanged(TableModelEvent e) {
                    // �������� ����� ������������� ������
                    int Row = e.getFirstRow();
                    int Col = e.getColumn();
                    Object value;
                    // ��������� � ����� ������� ����������� ��������������
                    switch(Col){
                        case 4:
                            // ��������
                            value = getIdControler(controlerBox.getSelectedIndex());
                            break;
                        case 5:
                            // ����� ���������
                            value = getIdInstalPlace(instalBox.getSelectedIndex());
                            break;
                        case 7:
                            // �������
                            value = getIdCase(caseBox.getSelectedIndex());
                            break;
                        default:
                            // ��������� �������
                            value = mtModel.upgradeCellValue(mtModel.getValueAt(Row,
                                Col), Col);
                    }
                    Turndown turn = objectTurnDao.getItem(Row);
                    turn.updateEntity(Col, value);

                }
            });

            // ����� ������ �������� �������
            tprops.setColWidth();

            // ��������� ������ ������
            setTurnObjectCellEditor();
        
        }
    }
    
    /**
     * �������� ���������� �� ���������
     */
    private void getCounter(){
        
        JPopupMenu counterMenu = MDIObject.createPopupMenu(tblUzel);
        JMenuItem mnuAddNew = (JMenuItem) counterMenu.getComponent(0);
        JMenuItem mnuRemove = (JMenuItem) counterMenu.getComponent(1);
        JMenuItem mnuUpdate = (JMenuItem) counterMenu.getComponent(2);
        // ��������� ��������� �������� ��� ����
        mnuAddNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addNewUzelItem(1);
            }
        });

        mnuRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // �������� ����� ������ ��� ��������
                int index = tblUzel.getSelectedRow();
                if(removeItem(index, firstItemDao)){
                    // ���� �� ������ ������, �������� �����������
                    MDIObject.getInformDialog(null, "Delete...", InformDialog.InformType.SAVING);
                    // ��������� ������
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
        mnuAddNew.setText("�������� �������");
        mnuRemove.setText("������� �������");
        getCounterData();// �������� ������ � ��������� ��� �������
//        if(firstItemDao.getCount() == 0) mnuRemove.setEnabled(false);
    }
    
    private void getCounterData(){
        firstItemDao = new CountersDaoImpl(idObject);
        final MyTableModel mtModel;// ������ ������
        mtModel = getItemData(firstItemDao, new int[]{0}, new int[]{0,1,2,3,4,5,6,7,8}, 
                new Object[][]{new Object[]{"","","","","","","","",false}}, 
                tblUzel, "counterwidth", UzelcolumnListener);
        // ��������� ���������
        mtModel.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                int Col = e.getColumn();
                int row = e.getFirstRow();
                if(Col != 1){
                    // �������� ��������
                    Object value = mtModel.upgradeCellValue(mtModel.getValueAt(row, Col), Col);
                    Counters counter = (Counters) firstItemDao.getItem(row);
                    counter.updateEntity(Col, value);
                }
            }
        });
        
        setCounterCellEditor();
    }
    
    /**
     * �������� ���������� �� �����������
     */
    private void getCorrector(){
        
        JPopupMenu correctorMenu = MDIObject.createPopupMenu(tblUzel);
        JMenuItem mnuAddNew = (JMenuItem) correctorMenu.getComponent(0);
        JMenuItem mnuRemove = (JMenuItem) correctorMenu.getComponent(1);
        JMenuItem mnuUpdate = (JMenuItem) correctorMenu.getComponent(2);
        // ��������� ��������� �������� ��� ����
        mnuAddNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addNewUzelItem(2);
            }
        });

        mnuRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // �������� ����� ������ ��� ��������
                int index = tblUzel.getSelectedRow();
                if(removeItem(index, firstItemDao)){
                    // ���� �� ������ ������, �������� �����������
                    MDIObject.getInformDialog(null, "Delete...", InformDialog.InformType.SAVING);
                    // ��������� ������
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
        
        mnuAddNew.setText("�������� ���������");
        mnuRemove.setText("������� ���������");
        getCorrectorData();// �������� ������ � ��������� ��� �������
        
    }
    
    private void getCorrectorData(){
        firstItemDao = new CorrectorDaoImpl(idObject);
        final MyTableModel mtModel;// ������ ������
        mtModel = getItemData(firstItemDao, new int[]{0}, new int[]{0,1,2,3,4,5,6,7,8,9}, 
                new Object[][]{new Object[]{"","","","","","","","","",false}}, 
                tblUzel, "correctorwidth", UzelcolumnListener);
        // ��������� ���������
        mtModel.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                int Col = e.getColumn();
                int row = e.getFirstRow();
                if(Col != 1){
                    // �������� ��������
                    Object value = mtModel.upgradeCellValue(mtModel.getValueAt(row, Col), Col);
                    Corrector corrector = (Corrector) firstItemDao.getItem(row);
                    corrector.updateEntity(Col, value);
                }
            }
        });
            
        setCorrectorCellEditor();
    }
    
    /**
     * �������� ���������� �� ��������
     */
    private void getDatchik(){
        JPopupMenu datchikpopupMenu = MDIObject.createPopupMenu(tblUzel);
        JMenuItem mnuAddNew = (JMenuItem) datchikpopupMenu.getComponent(0);
        JMenuItem mnuRemove = (JMenuItem) datchikpopupMenu.getComponent(1);
        JMenuItem mnuUpdate = (JMenuItem) datchikpopupMenu.getComponent(2);
        // ��������� ��������� �������� ��� ����
        mnuAddNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addNewUzelItem(3);
            }
        });

        mnuRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // �������� ����� ������ ��� ��������
                int index = tblUzel.getSelectedRow();
                if(removeItem(index, firstItemDao)){
                    // ���� �� ������ ������, �������� �����������
                    MDIObject.getInformDialog(null, "Delete...", InformDialog.InformType.SAVING);
                    // ��������� ������
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
        
        mnuAddNew.setText("�������� ������");
        mnuRemove.setText("������� ������");
        // �������� ������ � ��������� �������
        getDatchikData();
        
    }
    
    private void getDatchikData(){
        firstItemDao = new DatchikDaoImpl(idObject);
        final MyTableModel mtModel;// ������ ������
        mtModel = getItemData(firstItemDao, new int[]{0}, new int[]{0,1,2,3,4}, 
                new Object[][]{new Object[]{"","","","",""}}, tblUzel, 
                "datchikwidth", UzelcolumnListener);
        // ��������� ���������
        mtModel.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                int Col = e.getColumn();
                int row = e.getFirstRow();
                if(Col != 0 && Col != 1){
                    // �������� ��������
                    Object value = mtModel.upgradeCellValue(mtModel.getValueAt(row, Col), Col);
                    Datchik datchik = (Datchik) firstItemDao.getItem(row);
                    datchik.updateEntity(Col, value);
                }
            }
        });

        setDatchikCellEditor();
        
    }
    
    /**
     * �������� ���������� �� ��������
     */
    private void getBlocknote(){
        // ������ ���� ��� ���������� � �������� �������
        JPopupMenu blocknoteMenu = MDIObject.createPopupMenu(tblNote);
        getBlocknoteData();// ��������� ������ � ���������� ��� �������
        // ���������� �������� ��� ������� ����
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
        mnuAddNew.setText("�������� � �������");
        mnuRemove.setText("������� �� ��������");
    }
    
    private void getBlocknoteData(){
        firstItemDao = new BlocknoteDaoImpl(idObject);
        final MyTableModel mtModel;// ������ ������
        
        // ����� ��������� ������ ��������
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
                    // �������� ��������
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
     * �������� ���������� �� ������������� �������
     */
     private void getPlombs(){
         
        JPopupMenu plombspopupMenu = MDIObject.createPopupMenu(tblPlomb);
        JMenuItem mnuAddNew = (JMenuItem) plombspopupMenu.getComponent(0);
        JMenuItem mnuRemove = (JMenuItem) plombspopupMenu.getComponent(1);
        JMenuItem mnuUpdate = (JMenuItem) plombspopupMenu.getComponent(2);
        // ��������� ��������� �������� ��� ����
        mnuAddNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addNewPlomb();
            }
        });

        mnuRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // �������� ����� ������ ��� ��������
                int index = tblPlomb.getSelectedRow();
                if(removeItem(index, firstItemDao)){
                    // ���� �� ������ ������, �������� �����������
                    MDIObject.getInformDialog(null, "Delete...", InformDialog.InformType.SAVING);
                    // ��������� ������
                    getPlombsData();
                }

            }
        });
        
        mnuAddNew.setText("�������� ������");
        mnuRemove.setText("������� ������");
        mnuUpdate.setEnabled(false);
        getPlombsData();
        
    }
    
    private void getPlombsData(){
        firstItemDao = new PlombsDaoImpl(idObject);
        final MyTableModel mtModel;// ������ ������
        
        // ����� ��������� ������ ��������
        final ColumnModelListener plombColumnListener = 
                new ColumnModelListener(tblPlomb, props, "tblplumbwidth");

        mtModel = getItemData(firstItemDao, new int[]{0}, new int[]{0,1,2,3,4,5}, 
                new Object[][]{new Object[]{"","","","","",""}}, tblPlomb, 
                "tblplumbwidth", plombColumnListener);
        // ��������� ���������
        mtModel.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                int Col = e.getColumn();
                int row = e.getFirstRow();
                Object value;
                switch(Col){
                    case 2:
                        // ����� ���������
                        value = getIdInstalPlace(anotherSPRBox.getSelectedIndex());
                        break;
                    case 3:
                        // ��������
                        value = getIdControler(otherSPRBox.getSelectedIndex());
                        break;
                    default:
                        // ��������� ����
                        value = mtModel.upgradeCellValue(mtModel.getValueAt(row, Col), Col);
                        break;
                }
                // �������� ��������
                Plombs plombs = (Plombs) firstItemDao.getItem(row);
                plombs.updateEntity(Col, value);

            }
        });

        setPlombCellEditor();
        
    }
    
    
    private String getBorderBalance(){
        String retval = "";
        // �������� ������ �� ������������� ��� ������������
        BorderbalanceDaoImpl balanceDao = new BorderbalanceDaoImpl(idObject);
        // ���� ������ ����, �������� ������ ������
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
        
        // ��������� ��������� �������� ��� ����
        mnuAddNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addNewLose(1);
            }
        });

        mnuRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // �������� ����� ������ ��� ��������
                int index = tblLose.getSelectedRow();
                if(removeItem(index, firstItemDao)){
                    // ���� �� ������ ������, �������� �����������
                    MDIObject.getInformDialog(null, "Delete...", InformDialog.InformType.SAVING);
                    // ��������� ������
                    getHiLoseData();
                }

            }
        });

        mnuAddNew.setText("�������� ������ �/�");
        mnuRemove.setText("������� ������ �/�");
        mnuUpdate.setEnabled(false);
        // �������� ������ � ��������� �������
        getHiLoseData();
        
    }
    
    private void getHiLoseData(){
        firstItemDao = new HipressloseDaoImpl(idObject);
        final MyTableModel mtModel;// ������ ������
        
        // ����� ��������� ������ ��������
        LosecolumnListener = 
                new ColumnModelListener(tblLose, props, "hilosewidth");
        mtModel = getItemData(firstItemDao, new int[]{0,2}, new int[]{0,1,2,3,4,5,6}, 
                new Object[][]{new Object[]{"","","","",false,false,""}}, 
                tblLose, "hilosewidth", LosecolumnListener);
        // ��������� ���������
        mtModel.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                int Col = e.getColumn();
                int row = e.getFirstRow();
                Object value = getLoseValue(mtModel, row, Col);
                BigDecimal result;
                // �������� ��������
                Hipresslose hipresslose = (Hipresslose) firstItemDao.getItem(row);
                // �������� �������, � ������� ����������� ���������
                switch(Col){
                    case 1:// �������
                        hipresslose.updateEntity(Col, value);// ��������� ������
                        // ������ ���������� ��������
                        Sprgazlinerhipress sprhipress = new Sprgazlinerhipress((int) value);
                        result = calculateLose(row);
                        mtModel.setValueAt(result, row, 6);
                        break;
                    case 3:// �����
                    case 4:// �������
                    case 5:// ����������
                        // ��� ��������� ������ � ���� �������� ���������
                        // ������ ��������������� ������
                        result = calculateLose(row);
                        hipresslose.updateEntity(6, result);// ��������� ������
                        break;
                    case 6:// �����
                        hipresslose.updateEntity(Col, value);// ��������� ������
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
        // ��������� ��������� �������� ��� ����
        mnuAddNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addNewLose(2);
            }
        });

        mnuRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // �������� ����� ������ ��� ��������
                int index = tblLose.getSelectedRow();
                if(removeItem(index, firstItemDao)){
                    // ���� �� ������ ������, �������� �����������
                    MDIObject.getInformDialog(null, "Delete...", InformDialog.InformType.SAVING);
                    // ��������� ������
                    getMidLoseData();
                }

            }
        });

        mnuAddNew.setText("�������� ������ �/�");
        mnuRemove.setText("������� ������ �/�");
        mnuUpdate.setEnabled(false);
        getMidLoseData();
        
    }
    
    private void getMidLoseData(){
        firstItemDao = new MidpressloseDaoImpl(idObject);
        final MyTableModel mtModel;// ������ ������
        
        // ����� ��������� ������ ��������
        LosecolumnListener = 
                new ColumnModelListener(tblLose, props, "midlosewidth");
        mtModel = getItemData(firstItemDao, new int[]{0,2}, new int[]{0,1,2,3,4,5,6}, 
                new Object[][]{new Object[]{"","","","",false,false,""}}, 
                tblLose, "midlosewidth", LosecolumnListener);
        // ��������� ���������
        mtModel.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                int Col = e.getColumn();
                int row = e.getFirstRow();
                Object value = getLoseValue(mtModel, row, Col);
                BigDecimal result;
                
                // �������� ��������
                Midpresslose midpress = (Midpresslose) firstItemDao.getItem(row);
                // �������� �������, � ������� ����������� ���������
                switch(Col){
                    case 1:// �������
                        midpress.updateEntity(Col, value);// ��������� ������
                        // ������ ���������� ��������
                        Sprgazlinermidpress sprmidpress = new Sprgazlinermidpress((int) value);
                        // �������� �������� ������ � �������
                        tblLose.setValueAt(sprmidpress.getLose(), row, 2);
                        break;
                    case 3:// �����
                    case 4:// �������
                    case 5:// ����������
                        // ��� ��������� ������ � ���� �������� ���������
                        // ������ ��������������� ������
                        result = calculateLose(row);
                        midpress.updateEntity(6, result);// ��������� ������
                        break;
                    case 6:// �����
                        midpress.updateEntity(Col, value);// ��������� ������
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
        
        // ��������� ��������� �������� ��� ����
        mnuAddNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addNewLose(3);
            }
        });

        mnuRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // �������� ����� ������ ��� ��������
                int index = tblLose.getSelectedRow();
                if(removeItem(index, firstItemDao)){
                    // ���� �� ������ ������, �������� �����������
                    MDIObject.getInformDialog(null, "Delete...", InformDialog.InformType.SAVING);
                    // ��������� ������
                    getRLowLoseData();
                }

            }
        });

        mnuAddNew.setText("�������� ������ ��/�");
        mnuRemove.setText("������� ������ ��/�");
        mnuUpdate.setEnabled(false);
        getRLowLoseData();
        
    }
    
    private void getRLowLoseData(){
        firstItemDao = new RlowpressloseDaoImpl(idObject);
        final MyTableModel mtModel;// ������ ������
        
        // ����� ��������� ������ ��������
        LosecolumnListener = 
                new ColumnModelListener(tblLose, props, "rlowlosewidth");
        
        mtModel = getItemData(firstItemDao, new int[]{0,2}, new int[]{0,1,2,3,4,5,6}, 
                new Object[][]{new Object[]{"","","","",false,false,""}}, 
                tblLose, "rlowlosewidth", LosecolumnListener);
        // ��������� ���������
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
        // ��������� ��� ������ ���������
        otherSPRBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // �������� ��������
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
        // ��������� ��������� �������� ��� ����
        mnuAddNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addNewLose(4);
            }
        });

        mnuRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // �������� ����� ������ ��� ��������
                int index = tblLose.getSelectedRow();
                if(removeItem(index, firstItemDao)){
                    // ���� �� ������ ������, �������� �����������
                    MDIObject.getInformDialog(null, "Delete...", InformDialog.InformType.SAVING);
                    // ��������� ������
                    getDLowLoseData();
                }

            }
        });

        mnuUpdate.setEnabled(false);
        mnuAddNew.setText("�������� ������ ��/�");
        mnuRemove.setText("������� ������ ��/�");
        getDLowLoseData();
    }
    
    private void getDLowLoseData(){
        firstItemDao = new DlowloseDaoImpl(idObject);
        final MyTableModel mtModel;// ������ ������
        
        // ����� ��������� ������ ��������
        LosecolumnListener = 
                new ColumnModelListener(tblLose, props, "dlowlosewidth");
        
        mtModel = getItemData(firstItemDao, new int[]{0,2}, new int[]{0,1,2,3,4,5,6}, 
                new Object[][]{new Object[]{"","","","",false,false,""}}, tblLose, 
                "dlowlosewidth", LosecolumnListener);
        
        // ��������� ���������
        mtModel.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                int Col = e.getColumn();
                int row = e.getFirstRow();
                Object value = getLoseValue(mtModel, row, Col);
                BigDecimal result;
                // �������� ��������
                Dlowlose dlowpress = (Dlowlose) firstItemDao.getItem(row);
                // �������� �������, � ������� ����������� ���������
                switch(Col){
                    case 1:// �������
                        dlowpress.updateEntity(Col, value);// ��������� ������
                        // ������ ���������� ��������
                        Sprgazlinerdlowpress sprdlowpress = new Sprgazlinerdlowpress((int) value);
                        // �������� �������� ������ � �������
                        mtModel.setValueAt(sprdlowpress.getLose(), row, 2);
                        // ������ ��������������� ������
                        result = calculateLose(row);
                        mtModel.setValueAt(result, row, 6);
                        break;
                    case 3:// �����
                    case 4:// �������
                    case 5:// ����������
                        dlowpress.updateEntity(Col, value);// ��������� ������
                        // ��� ��������� ������ � ���� �������� ���������
                        // ������ ��������������� ������
                        result = calculateLose(row);
                        mtModel.setValueAt(result, row, 6);
                        break;
                    case 6:// �����
                        dlowpress.updateEntity(Col, value);// ��������� ������
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
        // ��������� ��������� �������� ��� ����
        mnuAddNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addNewLose(5);
            }
        });

        mnuRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // �������� ����� ������ ��� ��������
                int index = tblLose.getSelectedRow();
                if(removeItem(index, firstItemDao)){
                    // ���� �� ������ ������, �������� �����������
                    MDIObject.getInformDialog(null, "Delete...", InformDialog.InformType.SAVING);
                    // ��������� ������
                    getRegLoseData();
                }

            }
        });

        mnuAddNew.setText("�������� ������ �� �����������");
        mnuRemove.setText("������� ������ �� �����������");
        mnuUpdate.setEnabled(false);// ��������� ������ � ���� ��������
        getRegLoseData();
        
    }
    
    private void getRegLoseData(){
        firstItemDao = new RegloseDaoImpl(idObject);
        final MyTableModel mtModel;// ������ ������
        // ����� ��������� ������ ��������
        LosecolumnListener = 
                new ColumnModelListener(tblLose, props, "reglosewidth");
        
        mtModel = getItemData(firstItemDao, new int[]{0,2}, new int[]{0,1,2,3,4}, 
                new Object[][]{new Object[]{"","","","",""}}, tblLose, 
                "reglosewidth", LosecolumnListener);
        // ��������� ���������
        mtModel.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                int Col = e.getColumn();
                int row = e.getFirstRow();
                Object value = getLoseValue(mtModel, row, Col);
                
                // �������� ��������
                Reglose reglose = (Reglose) firstItemDao.getItem(row);
                reglose.updateEntity(Col, value);
            }
        });

        setRegulatorCellEditor();
    }
    
    private String getUsingGaz(){
        String retval;
        final GazusingDaoImpl gazUsingDao = new GazusingDaoImpl(idObject);
        // ���� ������ ����
        if(gazUsingDao.getCount() > 0){
            // �������� ��������
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
        
        // �������� ������ �� ������� ���������� ����
        String usinggaz = getUsingGaz();
        // ��������� ���������� ������
        if(usinggaz.equals(""))
            // ���� ������ ���
            usingCombo.setSelectedIndex(-1);
        else
            // ���� ������ ����
            usingCombo.setSelectedItem(usinggaz);
        
        // ���������� �������� ��� ������� ����
        // ��������� ��������� �������� ��� ����
        mnuAddNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addNewEquipment();
            }
        });

        mnuRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // �������� ����� ������ ��� ��������
                int index = tblEquipment.getSelectedRow();
                if(removeItem(index, firstItemDao)){
                    // ���� �� ������ ������, �������� �����������
                    MDIObject.getInformDialog(null, "Delete...", InformDialog.InformType.SAVING);
                    // ��������� ������
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
        
        mnuAddNew.setText("�������� ������������");
        mnuRemove.setText("������� ������������");
        
        getTurnEquipment();
        getEquipmentData();
        
        
    }
    
    private void getEquipmentData(){
        int idEquipment;
        firstItemDao = new EquipmentDaoImpl(idObject);
        final MyTableModel mtModel;// ������ ������
        final ColumnModelListener equipmentListener = new 
                ColumnModelListener(tblEquipment, props, "equipmentwidth");
        
        mtModel = getItemData(firstItemDao, new int[]{0}, new int[]{0,1,2,3,4}, 
                new Object[][]{new Object[]{"","","","",false}}, 
                tblEquipment, "equipmentwidth", equipmentListener);
        // ��������� ���������
        mtModel.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                int Col = e.getColumn();
                int row = e.getFirstRow();
                if(Col != 1){
                    // �������� ��������
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
            // ��� ������� �������������
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
        mnuAddNew.setText("�������� ���������� ������������");
        mnuRemove.setText("������� ���������� ������������");
        mnuUpdate.setEnabled(false);
//        if(secondItemDao.getCount() == 0) mnuRemove.setEnabled(false);
    }
    
    private void getTurnEquipmentData(int idEquipment){
        secondItemDao = new EquipturndownDaoImpl(idEquipment);
        final MyTableModel mtModel;// ������ ������
        
        // ������ � ��������� ��������� ������ ��������
        final ColumnModelListener turnEquipmentListener = new 
                ColumnModelListener(tblTurnEquipment, props, "tblturnequipmentwidth");
        mtModel = getItemData(secondItemDao, new int[]{0}, new int[]{0,1,2,3,4,5,6}, 
                new Object[][]{new Object[]{"","","","","","",""}}, 
                tblTurnEquipment, "tblturnequipmentwidth", turnEquipmentListener);
        // ��������� ���������
        mtModel.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                int Col = e.getColumn();
                int row = e.getFirstRow();
                Object value;
                switch(Col){
                    case 5:
                        // �������� ��� ���������
                        value = getIdControler(otherSPRBox.getSelectedIndex());
                        break;
                    case 6:
                        // �������� ��� �������
                        value = getIdCase(anotherSPRBox.getSelectedIndex());
                        break;
                    default:
                        // ��������� ����
                        value = mtModel.upgradeCellValue(mtModel.getValueAt(row, Col), Col);
                        break;
                }
                // �������� ��������
                Equipturndown turndown = (Equipturndown) secondItemDao.getItem(row);
                turndown.updateEntity(Col, value);
            }
        });

        setTurnEquipmentCellEditor();
        
        // ��������� ����� ���� �������, ���� � ������ ��� ������
        if(secondItemDao.getCount() == 0) 
            tblTurnEquipment.getComponentPopupMenu().getComponent(2).setEnabled(false);
    }
    
    private void getProject(){
        
        JPopupMenu popupMenu = MDIObject.createPopupMenu(tblProject);
        JMenuItem mnuAddNew = (JMenuItem) popupMenu.getComponent(0);
        JMenuItem mnuRemove = (JMenuItem) popupMenu.getComponent(1);
        JMenuItem mnuUpdate = (JMenuItem) popupMenu.getComponent(2);
        // ��������� ��������� �������� ��� ����
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

        mnuAddNew.setText("�������� ������");
        mnuRemove.setText("������� ������");
        mnuUpdate.setEnabled(false);
        getProjectData();
        
    }
    
    private void getProjectData(){
        firstItemDao = new ProjectsDaoImpl(idObject);
        final MyTableModel mtModel;// ������ ������
        final ColumnModelListener projectListener = new 
                ColumnModelListener(tblProject, props, "tblprojectwidth");
        mtModel = getItemData(firstItemDao, new int[]{0}, new int[]{0,1,2,3,4,5,6}, 
                new Object[][]{new Object[]{"","","","","","",""}}, 
                tblProject, "tblprojectwidth", projectListener);
        // ��������� ���������
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
                // �������� ��������
                Projects projects = (Projects) firstItemDao.getItem(row);
                projects.updateEntity(Col, value);

            }
        });

        setProjectCellEditor();
    }
    
    private void getLaunch(){
        
        // ������ ����������� ���� ��� ����������, �������� �������, �������� ������
        JPopupMenu launchpopupMenu = MDIObject.createPopupMenu(tblLauncher);
        JMenuItem mnuAddNew = (JMenuItem) launchpopupMenu.getComponent(0);
        JMenuItem mnuRemove = (JMenuItem) launchpopupMenu.getComponent(1);
        JMenuItem mnuUpdate = (JMenuItem) launchpopupMenu.getComponent(2);
        
        mnuAddNew.setText("�������� � ��������");
        mnuRemove.setText("������� �� ���������");
        mnuUpdate.setEnabled(false);
        getLaunchData();

        // ��������� ��������� �������� ��� ����
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
        final MyTableModel mtModel;// ������ ������
        // ������ � ��������� ��������� ������ ��������
        final ColumnModelListener launcherListener = new 
                ColumnModelListener(tblLauncher, props, "tbllauncherwidth");
        
        mtModel = getItemData(secondItemDao, new int[]{0}, new int[]{0,1,2}, 
                new Object[][]{new Object[]{"","",""}}, 
                tblLauncher, "tbllauncherwidth", launcherListener);
        // ��������� ���������
        mtModel.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                int Col = e.getColumn();
                int row = e.getFirstRow();
                // �������� ��������
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
        
        // ������ ���������� ������� �������� ������
        ObjectcolumnListener = new ColumnModelListener(tblObject, props, "tblobjectwidth");
        tblObject.getColumnModel().addColumnModelListener(ObjectcolumnListener);
        
        ObjectturncolumnListener = new ColumnModelListener(tblTurnObject, props, "tblturnobjectwidth");
        tblTurnObject.getColumnModel().addColumnModelListener(ObjectturncolumnListener);
        
        CommoncolumnListener = new ColumnModelListener(tblCommon, props, "tblcommonwidth");
        tblCommon.getColumnModel().addColumnModelListener(CommoncolumnListener);
    }
    
    
    private void addListItem(){
        // ������ ��� ���������� ������� �������
        sprCityDao = new SprcityDaoImpl();
        sprStreetDao = new SprstreetDaoImpl();
        sprControlerDao = new SprcontrolersDaoImpl();
        sprCaseDao = new SprcaseDaoImpl();
        sprInstalDao = new SprplumbsinstalplaseDaoImpl();
        final SprusinggazDaoImpl usingDao = new SprusinggazDaoImpl();
        
        // ��������� ������
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
        // ��������� ��������� � ������ �������������
        usingCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // �������� ����� ������������ �� �����������
                if(usingCombo.getSelectedIndex() != -1){
                    // �������� ��������� �������� �� ������ �������������
                    Sprusinggaz sug = usingDao.getItem(usingCombo.getSelectedIndex());
                    // �������� ������ �� �������������
                    GazusingDaoImpl udi = new GazusingDaoImpl(idObject);
                    if(udi.getCount() > 0){
                        // ���� ������ ����, �� �������� ������������
                        Gazusing gu = udi.getItem(0);
                        // ���������� ���� ������������, ���� ��� �� �����
                        // ����� ��������� ������
                        if(gu.getIdusingtype() != sug.getId())
                            gu.setIdusingtype(sug.getId());
                    } else {
                        // ���� ������ ���, �� ��������� ����� ������
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
     * ������������� ��������� ����� ������� ��������
     */
    private void setObjectCellEditor(){
        if(objectsDao.getCount() > 0){
            // ��������� ������ � �������� ������ ��� ������� �������� � ��������
            // ������ ������� � ����
            DefaultCellEditor cityEditor = new DefaultCellEditor(cityBox);
            DefaultCellEditor streetEditor = new DefaultCellEditor(streetBox);
            tblObject.getColumnModel().getColumn(2).setCellEditor(cityEditor);
            tblObject.getColumnModel().getColumn(3).setCellEditor(streetEditor);
//            tblObject.setDefaultRenderer(Boolean.class, new IntegerTableCellRenderer());
        }
    }
    
    /**
     * ������������� ��������� ����� ������� ���������� ��������
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
        // ��������� ������
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
        // ��������� ������
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
        // ��������� ������
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
        // ��������� ������
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
        // ��������� ������
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
        // ��������� ������
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
        // ��������� ������
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
        // ��������� ������
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
        // ��������� ������
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
                // ���������� ���������� �� ���������
                getCounterData();
                break;
            case 2:
                // ���������� ���������� �� �����������
                getCorrectorData();
                break;
            case 3:
                // ���������� ���������� �� ��������
                getDatchikData();
                break;
        }
    }
    
    /**
     * 
     */
    private void addNewObject(){
        // ���������� ������������, ������������� �� �� ����� �������� ����� ������
        // ��� �������� �������������� ������������� �������, �������������� ������ �����������
        int button = JOptionPane.showConfirmDialog(this, 
                "��� ���������� ������ ������� ������� ��. ��� ���������" + 
                        " �������������� \n ������������� ������� " + 
                        "������� ���. �������� ����� ������?", 
                "AbonentGaz", JOptionPane.YES_NO_CANCEL_OPTION);
        
        // ��������� ����� ������������
        switch (button) {
            case JOptionPane.YES_OPTION:
                // ����� ����������� ������ ������� ��� ��� ���������� ������������
                if(JOptionPane.showConfirmDialog(this, "�������� ����� ������?", 
                        "AbonentGaz", JOptionPane.YES_NO_OPTION, 
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                    // ���������� ������ ������� � ������� �����������
                    NewItemDialog newObjectDialog = new NewItemDialog(NewItemDialog.NewItemType.NewObject);
                    newObjectDialog.setIndentity(idCompany);
                    newObjectDialog.createNewItem();// ������ �������� ����������
                    if(newObjectDialog.showDialog(this)){
                        MDIObject.getInformDialog(null, "����� ������", 
                                InformDialog.InformType.SAVING);
                        // ��������� ������ �� ��������
                        getObjects();
                    }
                }
//                    MDIObject.getInformDialog(null, "����� ������", InformDialog.InformType.SAVING);
                break;
            case JOptionPane.NO_OPTION:
                // ���������� ������������� ������� � ������� �����������
                ViewobjectFrame frame = new ViewobjectFrame();
                frame.setIdOrganization(idCompany);
                frame.setTitle("�������� ��������");
                frame.setLocationRelativeTo(null);
                frame.setModalExclusionType(Dialog.ModalExclusionType.NO_EXCLUDE);
                frame.setVisible(true);
                // ��������� �������� �������� ������������
                if(frame.isChange())
                    getObjects();// ��������� ������ �� ��������
                break;
            default:
                break;
        }
    }
    
    /**
     * 
     */
    private void removeObject(){
        // ����������� ������������ �� �������� ������������� �������
        int button = JOptionPane.showConfirmDialog(this, "������� ������?", "AbonentGaz", 
                JOptionPane.YES_NO_OPTION, JOptionPane.NO_OPTION);
        
        // ���������, ��� �� ������
        if(button == JOptionPane.YES_OPTION){
            // �������� ������ � ������� � ��������� ��������
            int row = tblObject.getSelectedRow();
            
            // ������� ��������� ������ �� ������
//            boolean deleteItem = objectsDao.deleteItem(row);
            if(objectsDao.deleteItem(row)){
                MDIObject.getInformDialog(null, "Deleting...", 
                        InformDialog.InformType.SAVING);
                // ��������� ������ �� ��������
                getObjects();
            }
            else
                JOptionPane.showMessageDialog(this, "��������� ������ �� " + 
                        "����� �������� ��������!", "AbonentGaz", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * 
     * @param index
     */
    private void addNewUzelItem(int index){
        // � ����������� �� ���������� ���� ��� ������ ��������������� ���� ������
        // ������ �������� ���������
        NewItemDialog.NewItemType nit = null;
        switch(index){
            case 1:
                // ����� �������
                nit = NewItemDialog.NewItemType.NewCount;
//                dialog = new NewItemDialog(NewItemDialog.NewItemType.NewCount);
                break;
            case 2:
                // ����� ���������
                nit = NewItemDialog.NewItemType.NewCorrector;
//                dialog = new NewItemDialog(NewItemDialog.NewItemType.NewCorrector);
                break;
            case 3:
                // ����� ������
                nit = NewItemDialog.NewItemType.NewDatchik;
//                dialog = new NewItemDialog(NewItemDialog.NewItemType.NewDatchik);
                break;
        }
        NewItemDialog dialog = new NewItemDialog(nit);
        dialog.setIndentity(idObject);
        dialog.createNewItem();// ������ �������� ����������
        boolean retval = dialog.showDialog(this);
        
        // ��������� ��������� �������� ������������
        if(retval)
            updateUzelItem(index);// ��������� ������
//        dialog = null;
    }
    
    /**
     * ���������� ���������� ���� ��� ������ � ���������� ����� ������� ������������
     */
    private void addNewEquipment(){
        // ������ �� ���������� ������ ������������
        int button = JOptionPane.showConfirmDialog(null, 
                "�������� ������?", "AbonentGaz", JOptionPane.YES_NO_OPTION);
        
        // ��������� ����� ������������
        if(button == JOptionPane.YES_OPTION){
            NewItemDialog.NewItemType nit = NewItemDialog.NewItemType.NewEquipment;
            NewItemDialog dialog = new NewItemDialog(nit);// ������ ���������� ����
            dialog.setIndentity(idObject);// ��� ������� ��� ����������
            dialog.createNewItem();// ������ �������� ����������
            boolean retval = dialog.showDialog(this);// �������� ��������� ������ �����������
            
            // ��������� ��������� ������������� �����
            if(retval) getEquipmentData();// ��������� ������ �� ������������
        }
    }
    
    /**
     * ������� ������ �� ������� ������������
     */
    private void removeEquipment(){
        // ������ �� ��������
        int button = JOptionPane.showConfirmDialog(null, 
                "������� ������?", "AbonentGaz", JOptionPane.YES_NO_OPTION);
        
        // ��������� ����� ������������
        if(button == JOptionPane.YES_OPTION){
            // �������� ����� ������ ��� ��������
            int index = tblEquipment.getSelectedRow();
                
            boolean retval = firstItemDao.deleteItem(index);
            
            if(retval) getEquipmentData();// ��������� ������ �� ������������
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
        // ������ �� ����������
        int button = JOptionPane.showConfirmDialog(null, 
                "�������� ������?", "AbonentGaz", JOptionPane.YES_NO_OPTION);
        
        // ��������� ����� ������������
        if(button == JOptionPane.YES_OPTION){
            // �������� ��� ���������
            int idControler = getIdControler(0);
            int[] param = new int[]{idObject,idControler};
            String fieldName = "IDOBJECT,IDCONTROLER";
            String escField = "?,?";
            // ��������� ������, ���� ���������� ������, ��������� ������
            if(firstItemDao.addItem(fieldName, escField, param))
                getBlocknote();
            else
                JOptionPane.showMessageDialog(null, 
                        "��������� ������ �� ����� �������� ����������. \n" +
                        "���������� � ��������������!", "AbonentGaz", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * 
     */
    private void removeNote(){
        // ������ �� ��������
        int button = JOptionPane.showConfirmDialog(null, 
                "������� ������?", "AbonentGaz", JOptionPane.YES_NO_OPTION);
        
        // ��������� ����� ������������
        if(button == JOptionPane.YES_OPTION){
            // �������� ����� ������ (������)
            int row = tblNote.getSelectedRow();
            
            // ������� ������, ���� �������� ������, ��������� ������
            if(firstItemDao.deleteItem(row))
                getBlocknote();
            else
                JOptionPane.showMessageDialog(null, 
                        "��������� ������ �� ����� �������� ��������. \n" +
                        "���������� � ��������������!", "AbonentGaz", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * 
     */
    private void addNewPlomb(){
        // ������ �� ����������
        int button = JOptionPane.showConfirmDialog(null, 
                "�������� ������?", "AbonentGaz", JOptionPane.YES_NO_OPTION);
        
        // ��������� ����� ������������
        if(button == JOptionPane.YES_OPTION){
            // �������� ��� ��������� � ��� ����� ��������� ������
            int idControler = getIdControler(0);
            int idInstalplace = getIdInstalPlace(0);
            int[] param = new int[]{idObject,idControler,idInstalplace};
            String fieldName = "IDOBJECT,IDCONTROLER,IDINSTALPLACE";
            String escField = "?,?,?";
            // ��������� ������, ���� ���������� ������, ��������� ������
            if(firstItemDao.addItem(fieldName, escField, param))
                getPlombsData();
            else
                JOptionPane.showMessageDialog(null, 
                        "��������� ������ �� ����� �������� ����������. \n" +
                        "���������� � ��������������!", "AbonentGaz", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * 
     */
    private void removePlomb(){
        // ������ �� ���������� ������ ������������
        int button = JOptionPane.showConfirmDialog(null, 
                "������� ������?", "AbonentGaz", JOptionPane.YES_NO_OPTION);
        
        // ��������� ����� ������������
        if(button == JOptionPane.YES_OPTION){
            // �������� ����� ������ (������)
            int row = tblPlomb.getSelectedRow();
            
            // ������� ������, ���� �������� ������, ��������� ������
            if(firstItemDao.deleteItem(row))
                getPlombs();
            else
                JOptionPane.showMessageDialog(null, 
                        "��������� ������ �� ����� �������� ��������. \n" +
                        "���������� � ��������������!", "AbonentGaz", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * 
     */
    private void addNewLose(int index){
        // ������ �� ���������� ������ ������������
        int button = JOptionPane.showConfirmDialog(null, 
                "�������� ������?", "AbonentGaz", JOptionPane.YES_NO_OPTION);
        
        // ��������� ����� ������������
        if(button == JOptionPane.YES_OPTION){
            // � ����������� �� ���� ������ ������ ��������������� ���� ������
            // ������ ���� ������
            NewItemDialog dialog = null;
            switch(index){
                case 1:
                    // ������� ��������
                    dialog = new NewItemDialog(NewItemDialog.NewItemType.NewHipress);
                    break;
                case 2:
                    // ������� ��������
                    dialog = new NewItemDialog(NewItemDialog.NewItemType.NewMidpress);
                    break;
                case 3:
                    // ����������������� ������� �������
                    dialog = new NewItemDialog(NewItemDialog.NewItemType.NewRlowpress);
                    break;
                case 4:
                    // �������� ������� ��������
                    dialog = new NewItemDialog(NewItemDialog.NewItemType.NewDlowpress);
                    break;
                case 5:
                    // ����������
                    dialog = new NewItemDialog(NewItemDialog.NewItemType.NewRegulator);
                    break;
            }
            dialog.setIndentity(idObject);
            dialog.createNewItem();// ������ �������� ����������
            boolean retval = dialog.showDialog(this);
            // ��������� ��������� �������� ������������
            if(retval){
                switch(index){
                    case 1:// ����������� �������� ��������
                        getHiLoseData();
                        break;
                    case 2:// ����������� �������� ��������
                        getMidLoseData();
                        break;
                    case 3:// ����������� ����������������� ������� ��������
                        getRLowLoseData();
                        break;
                    case 4:// ����������� �������� ������� ��������
                        getDLowLoseData();
                        break;
                    case 5:// ����������
                        getRegLose();
                        break;
                }
            }
//            else
//                JOptionPane.showMessageDialog(null, 
//                        "��������� ������ �� ����� �������� ����������. \n" +
//                        "���������� � ��������������!", "AbonentGaz", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * ������� ������ �� ���������� ���� ������
     * @return true � ������ ������, � ��������� ������ - false
     * @param tdi ������, ���������� ������, ������� ����� �������
     * 
     */
    private boolean removeLose(TableDaoImpl tdi){
        // ������ �� ���������� ������ ������������
        int button = JOptionPane.showConfirmDialog(null, 
                "������� ������?", "AbonentGaz", JOptionPane.YES_NO_OPTION);
        
        // ��������� ����� ������������
        if(button == JOptionPane.YES_OPTION){
            // �������� ����� ������ (������)
            int row = tblLose.getSelectedRow();
            
            // ������� ������, ���� �������� ������, ��������� ������
            if(tdi.deleteItem(row))
                return true;
            else {
                JOptionPane.showMessageDialog(null, 
                        "��������� ������ �� ����� �������� ��������. \n" +
                        "���������� � ��������������!", "AbonentGaz", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else return false;
    }
    
    /**
     * 
     */
    private void addNewTurnEquipment(){
        // ��������� ������� �� ������ � �������
        if(tblEquipment.getSelectedRow() != -1){
            // ������ �� ���������� ������ ������������
            int button = JOptionPane.showConfirmDialog(null, 
                    "�������� ������?", "AbonentGaz", JOptionPane.YES_NO_OPTION);

            // ��������� ����� ������������
            if(button == JOptionPane.YES_OPTION){
                // �������� ��� ���������, ��� ������������ � ��� ������� ����������
                int idControler = getIdControler(0);
                int idEquipment = firstItemDao.getItem(tblEquipment.getSelectedRow()).getId();
                int idCase = getIdCase(0);
                int[] param = new int[]{idControler,idEquipment,idCase};
                String fieldName = "IDCONTROLER,IDEQUIPMENT,IDCASE";
                String escField = "?,?,?";
                // ��������� ������, ���� ���������� ������, ��������� ������
                if(secondItemDao.addItem(fieldName, escField, param))
                    getTurnEquipmentData(idEquipment);
                else
                    JOptionPane.showMessageDialog(null, 
                            "��������� ������ �� ����� �������� ����������. \n" +
                            "���������� � ��������������!", "AbonentGaz", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // ���������� ������������ � ������������� ������� ������ � �������
            JOptionPane.showMessageDialog(null, 
                    "�������� ������������ � �������, ��� �������� \n" +
                    "������ �������� ������.", "AbonentGaz", JOptionPane.WARNING_MESSAGE);
            // ������������� ����� � ������� �� ������ ������
            tblEquipment.setRowSelectionInterval(0, 0);
            tblEquipment.setColumnSelectionInterval(1, 1);

        }
    }
    
    /**
     * 
     */
    private void removeTurnEquipment(){
        // ������ �� ���������� ������ ������������
        int button = JOptionPane.showConfirmDialog(null, 
                "������� ������?", "AbonentGaz", JOptionPane.YES_NO_OPTION);
        
        // ��������� ����� ������������
        if(button == JOptionPane.YES_OPTION){
            // �������� ����� ������ (������)
            int row = tblTurnEquipment.getSelectedRow();
            
            int idEquipment = firstItemDao.getItem(tblEquipment.getSelectedRow()).getId();
            // ������� ������, ���� �������� ������, ��������� ������
            if(secondItemDao.deleteItem(row))
                getTurnEquipmentData(idEquipment);
            else
                JOptionPane.showMessageDialog(null, 
                        "��������� ������ �� ����� �������� ��������. \n" +
                        "���������� � ��������������!", "AbonentGaz", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * 
     */
    private void addNewProject(){
        // ������ �� ���������� ������ ������������
        int button = JOptionPane.showConfirmDialog(null, 
                "�������� ������?", "AbonentGaz", JOptionPane.YES_NO_OPTION);
        
        // ��������� ����� ������������
        if(button == JOptionPane.YES_OPTION){
            int idPodryadchik = getIdProject(0);
            int[] param = new int[]{idObject,idPodryadchik};
            String fieldName = "IDOBJECT,IDPODRYADCHIK";
            String escField = "?,?";
            // ��������� ������, ���� ���������� ������, ��������� ������
            if(firstItemDao.addItem(fieldName, escField, param))
                getProjectData();
            else
                JOptionPane.showMessageDialog(null, 
                        "��������� ������ �� ����� �������� ����������. \n" +
                        "���������� � ��������������!", "AbonentGaz", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * 
     */
    private void removeProject(){
        // ������ �� ���������� ������ ������������
        int button = JOptionPane.showConfirmDialog(null, 
                "������� ������?", "AbonentGaz", JOptionPane.YES_NO_OPTION);
        
        // ��������� ����� ������������
        if(button == JOptionPane.YES_OPTION){
            // �������� ����� ������ (������)
            int row = tblProject.getSelectedRow();
            
            // ������� ������, ���� �������� ������, ��������� ������
            if(firstItemDao.deleteItem(row))
                getProjectData();
            else
                JOptionPane.showMessageDialog(null, 
                        "��������� ������ �� ����� �������� ��������. \n" +
                        "���������� � ��������������!", "AbonentGaz", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * 
     */
    private void addNewLaunch(){
        // ������ �� ���������� ����� ������ � ��������
        int button = JOptionPane.showConfirmDialog(null, 
                "�������� ������?", "AbonentGaz", JOptionPane.YES_NO_OPTION);
        
        // ��������� ����� ������������
        if(button == JOptionPane.YES_OPTION){
            // �������� ��� ���������
            int[] param = new int[]{idObject};
            String fieldName = "IDOBJECT";
            String escField = "?";
            // ��������� ������, ���� ���������� ������, ��������� ������
            if(secondItemDao.addItem(fieldName, escField, param))
                getLaunchData();
            else
                JOptionPane.showMessageDialog(null, 
                        "��������� ������ �� ����� �������� ����������. \n" +
                        "���������� � ��������������!", "AbonentGaz", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * 
     */
    private void removeLaunch(){
        // ������ �� ���������� ������ ������������
        int button = JOptionPane.showConfirmDialog(null, 
                "������� ������?", "AbonentGaz", JOptionPane.YES_NO_OPTION);
        
        // ��������� ����� ������������
        if(button == JOptionPane.YES_OPTION){
            // �������� ����� ������ (������)
            int row = tblLauncher.getSelectedRow();
            
            // ������� ������, ���� �������� ������, ��������� ������
            if(secondItemDao.deleteItem(row))
                getLaunchData();
            else
                JOptionPane.showMessageDialog(null, 
                        "��������� ������ �� ����� �������� ��������. \n" +
                        "���������� � ��������������!", "AbonentGaz", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private boolean removeItem(int index, TableDaoImpl tdi){
        // ������ �� �������� ��������� ������
        int button = JOptionPane.showConfirmDialog(null, 
                "������� ������?", "AbonentGaz", JOptionPane.YES_NO_OPTION);
        
        // ��������� ����� ������������
        if(button == JOptionPane.YES_OPTION){
            // ������� ������, ���� �������� ������, ��������� ������
            if(tdi.deleteItem(index))
                return true;
            else {
                JOptionPane.showMessageDialog(null, 
                        "��������� ������ �� ����� �������� ��������. \n" +
                        "���������� � ��������������!", "AbonentGaz", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else return false;
    }
    
    /**
     * ���������� ������ � ������� ���������� ��������
     */
    private void addNewTurnObject(){
        // ������ �� ���������� ������ ������������
        int button = JOptionPane.showConfirmDialog(null, 
                "�������� ������?", "AbonentGaz", JOptionPane.YES_NO_OPTION);
        
        // ��������� ����� ������������
        if(button == JOptionPane.YES_OPTION){
            // �������� ��� ���������, ��� ������� ����������/�����������
            // � ��� ����� ��������� ������
            int idControler = getIdControler(0);
            int idCase = getIdCase(0);
            int idInstalplace = getIdInstalPlace(0);
            int[] param = new int[]{idObject,idControler,idCase,idInstalplace};
            String fieldName = "IDOBJECT,IDCONTROLER,IDCASE,IDINSTALPLACE";
            String escField = "?,?,?,?";
            // ��������� ������, ���� ���������� ������, ��������� ������
            if(objectTurnDao.addItem(fieldName, escField, param))
                getTurnObjects();
            else
                JOptionPane.showMessageDialog(null, 
                        "��������� ������ �� ����� �������� ����������. \n" +
                        "���������� � ��������������!", "AbonentGaz", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void removeTurnObject(){
        // �������� ����� ������ ��� ��������
        int index = tblTurnObject.getSelectedRow();
        if(removeItem(index, objectTurnDao)){
            // ���� �� ������ ������, �������� �����������
            MDIObject.getInformDialog(null, "Delete...", InformDialog.InformType.SAVING);
            // ��������� ������
            getTurnObjects();
        }
    }
    
//    private Object[][] getItem(TableDaoImpl tdi){
//        Object[][] content = new Object[tdi.getCount()][];
//        for(int i = 0; i < content.length; i++){
//            TableEntity entity = tdi.getItem(i);
//            content[i] = entity.toDataArray();// ��������� ������
//        }
//        return content;
//    }
    
    private MyTableModel getItemData(TableDaoImpl tdi, int[] colIndex,
            int[] defaultcolIndex, Object[][] defaultContent, JTable table, 
            String propertyname,
            ColumnModelListener colListener){
        String[] columnName = tdi.getColumnName();// ������������ � ����
        Class[] columnClass = tdi.getColumnClass();// ����� ������
        int[] cols;// ������ ��������������� ��������
        MyTableModel mtModel;// ������ ������
        Object[][] content = new Object[tdi.getCount()][];// ������
        
        
        if(tdi.getCount() > 0){
            // ���� ���� ������, ��������� �������
            for(int i = 0; i < content.length; i++){
                TableEntity entity = tdi.getItem(i);
                content[i] = entity.toDataArray();// ��������� ������
            }

            
            // ���������� �������� ��������������� ��������
            cols = colIndex;
            // ��������� ������ � ������� ����
            table.getComponentPopupMenu().getComponent(0).setEnabled(true);
            table.getComponentPopupMenu().getComponent(1).setEnabled(true);
            
        } else {
            // ���� ������ ���, ������� ������ ��-���������
            content = defaultContent;
            cols = defaultcolIndex;
            // ��������� ������ � ������� ����
            table.getComponentPopupMenu().getComponent(0).setEnabled(true);
            table.getComponentPopupMenu().getComponent(1).setEnabled(false);
        }
        
        mtModel = new MDIObject.MyTableModelImpl(content, columnName, columnClass);// ��������� ������
        
        // ��������� �������
        MDIObject.fullTableData(cols, mtModel, table);
        // ����� ������� �������� �������
        MDIObject.setTablecolwidth(props, propertyname, table);
        
        // ��������� ��������� ������ ��������
        table.getColumnModel().addColumnModelListener(colListener);
        
        // ���������� ������ ������
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
     * ���������� ���������� ���� ��� ����� ������ �� ���� �������������
     * @return � ������ �������� ������ - true, � ��������� ������ - false
     */
    private void addNewBorderBalance(){
        // ������ �� ���������� ������ ���� �������������
        int button = JOptionPane.showConfirmDialog(null, 
                "�������� ������ �� ���� �������������?", "AbonentGaz", JOptionPane.YES_NO_OPTION);
        
        // ��������� ����� ������������
        if(button == JOptionPane.YES_OPTION){
            NewItemDialog.NewItemType nit = NewItemDialog.NewItemType.NewBorderBalance;
            NewItemDialog dialog = new NewItemDialog(nit);// ������ ���������� ����
            dialog.setIndentity(idObject);// ��� ������� ��� ����������
            dialog.createNewItem();// ������ �������� ����������
            boolean retval = dialog.showDialog(this);// �������� ��������� ������ �����������
            
            // ��������� ��������� ������������� �����
            if(retval) lblLose.setText(getBorderBalance());// ��������� ������ �� ���� �������������
        }
    }
    
    /**
     * ���������� ���������� ���� ��� ��������� ������ �� ���� �������������
     * @return � ������ ��������� ������ - true, � ��������� ������ - false
     */
    private void showBorderBalance(){
        // ���������� ����������� ���� ��� �������������� ������ �� ���� �������������
        NewItemDialog.NewItemType nit = NewItemDialog.NewItemType.ExistBorderBalance;
        NewItemDialog dialog = new NewItemDialog(nit);// ������ ���������� ����
        dialog.setIndentity(idObject);// ��� ������� ��� ����������
        dialog.createNewItem();// ������ �������� ����������
        boolean retval = dialog.showDialog(this);// �������� ��������� ������ �����������
        
        // ��������� ��������� ������������� �����
        if(retval) lblLose.setText(getBorderBalance());// ��������� ������ �� ���� �������������
        
    }
    
    private class ObjectInformQuery{

        private TableDaoImpl tdi;
        private final List<Object[]> dataArray;
        private final TableProperty tp;
        private final List<Integer> rows;
        private int numRow = 0;
        // ����� ������������ �������� �������
        private final String[] colName;
        private final int[] columns;
        
        public ObjectInformQuery() {
            this.colName = new String[]{"�","A","B","C","D","E","F"};
            this.columns = new int[]{2,3,4,5,6};
            dataArray = new ArrayList<>();
            rows = new ArrayList<>();// ��������� ������ ������� - ���� �������
            tp = new TableProperty(tblCommon);
        
        }
        
        /**
         * ������� ����� �� ��������� ������. ���������� �������, ������� ��
         * ��������� 2 ������ � 8 ��������
         */
        public void Clear(){
            dataArray.clear();// ������� ������
            rows.clear();
            numRow = 0;// ��������� �������� ����� � ������������ ��������
            Object[][] content = new Object[1][7];
            AttributiveCellTableModel model = new AttributiveCellTableModel(content, colName);
            String width = tp.getColWidth();
            tblCommon.setModel(model);
            //����� �������������� ��� �������� �������
            setColumnIdentifiers();
            tp.setColWidth();
        }
        
        public void getInform(){
            dataArray.clear();// ������� ������
            rows.clear();
            numRow = 0;// ��������� �������� ����� � ������������ ��������
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
            
            // �������� ������ ��� ���������� ������ �������
            Object[][] content = new Object[dataArray.size()][7];
            for(int i = 0; i < content.length; i++)
                content[i] = dataArray.get(i);
            String width = tp.getColWidth();
            AttributiveCellTableModel model = new AttributiveCellTableModel(content, colName);
            CellSpan cellAtt;
            cellAtt = (CellSpan)model.getCellAttribute();
            tblCommon.setModel(model);
            //����� �������������� ��� �������� �������
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
         * ����� �������������� ��� �������� �������
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
            Object[] item = getArray(numRow + 1, new String[]{"������",orgobjects.getNameobject() + 
                        ", " + orgobjects.getNameCity() + ", " +
                        orgobjects.getNameStreet() + ", " + 
                        orgobjects.getAddres(),"","","",""});
            dataArray.add(item);
            
        }
        
        /**
         * �������� ������ �� ���� ������ � ������������ ���� �����
         * @param id ��� �������, ��� �������� ���������� ������
         */
        private void getCounter(int id){
//            String counters = "";
            tdi = new CountersDaoImpl(id);// �������� ���������� �� ���������
            // ��������� ������� ���������� ������
            int count = tdi.getCount();
            if(count > 0){
                numRow++;
                Object[] item = getArray(numRow + 1, new String[]{"�������","","","","",""});
                dataArray.add(item);
                numRow++;
                item = getArray(numRow + 1, new String[]{"������������","�����","���� �������",
                    "������","��� �����","�����-��"});
                dataArray.add(item);
                // �������� ������
                Object[][] data = new Object[count][7];// ������ �������
                for(int i = 0; i < count; i++){
                    numRow++;
                    Counters c = (Counters) tdi.getItem(i);
                    Object[] itemData = c.toDataArray();
                    String prop = itemData[8].equals(true) ? "��" : "���" ;
//                    String bgColor = prop.equals("��") ? "#00FF00" : "#C0C0C0";
                    data[i][0] = numRow + 1;
                    data[i][1] = itemData[1];// ��� ��������
                    data[i][2] = itemData[2];// �����
                    data[i][3] = itemData[3];// ���� �������
                    data[i][4] = itemData[6];// ������
                    data[i][5] = itemData[7];// ��� ���������
                    data[i][6] = prop;// �������������
                    dataArray.add(data[i]);
                }
                    
            }
//            return counters;
        }
        
        private void getCorrector(int id){
//            String corrector = "";
            tdi = new CorrectorDaoImpl(id);// �������� ���������� �� �����������
            // ��������� ������� ���������� ������
            int count = tdi.getCount();
            if(count > 0){
                numRow++;
                Object[] item = getArray(numRow + 1, new String[]{"���������","","","","",""});
                dataArray.add(item);
                numRow++;
                item = getArray(numRow + 1, new String[]{"������������","�����","���� �������",
                    "������","�����-��",""});
                dataArray.add(item);
                // �������� ������
                Object[][] data = new Object[count][7];// ������ �������
                for(int i = 0; i < count; i++){
                    numRow++;
                    Corrector c = (Corrector) tdi.getItem(i);
                    Object[] itemData = c.toDataArray();
                    String prop = itemData[9].equals(true) ? "��" : "���" ;
                    data[i][0] = numRow + 1;
                    data[i][1] = itemData[1];// ��� ����������
                    data[i][2] = itemData[2];// �����
                    data[i][3] = itemData[5];// ���� �������
                    data[i][4] = itemData[6];// ������
                    data[i][5] = prop;// ������������� ��������
                    data[i][6] = "";
                    dataArray.add(data[i]);
                }
                    
            }
//            return corrector;
        }
        
        private void getDatchik(int id){
            tdi = new DatchikDaoImpl(id);// �������� ���������� �� ��������
            // ��������� ������� ���������� ������
            int count = tdi.getCount();
            if(count > 0){
                numRow++;
                Object[] item = getArray(numRow + 1, new String[]{"�������","","","","",""});
                dataArray.add(item);
                numRow++;
                item = getArray(numRow + 1, new String[]{"������������","�����","���� �������",
                    "������","",""});
                dataArray.add(item);
                
                // �������� ������
                Object[][] data = new Object[count][7];// ������ �������
                for(int i = 0; i < count; i++){
                    numRow++;
                    Datchik c = (Datchik) tdi.getItem(i);
                    Object[] itemData = c.toDataArray();
                    data[i][0] = numRow + 1;
                    data[i][1] = itemData[1];// ��� �������
                    data[i][2] = itemData[2];// �����
                    data[i][3] = itemData[3];// ���� �������
                    data[i][4] = itemData[4];// ������
                    data[i][5] = "";
                    data[i][6] = "";
                    dataArray.add(data[i]);
                }
                    
            }
//            return datchik;
        }
        
        /**
         * �������� ������ �� ���� ������ �� ������������
         * @param id ��� �������, ��� �������� ���������� ������
         */
        private void getEquipment(int id){
            tdi = new EquipmentDaoImpl(id);// �������� ���������� �� ���������
            // ��������� ������� ���������� ������
            int count = tdi.getCount();
            if(count > 0){
                numRow++;
                Object[] item = getArray(numRow + 1, new String[]{"������������","","","","",""});
                dataArray.add(item);
                numRow++;
                item = getArray(numRow + 1, new String[]{"������������","����������",
                    "��������","","",""});
                dataArray.add(item);
                
                // �������� ������
                Object[][] data = new Object[count][7];// ������ �������
                for(int i = 0; i < count; i++){
                    numRow++;
                    Equipment c = (Equipment) tdi.getItem(i);
                    Object[] itemData = c.toDataArray();
                    String prop = itemData[4].equals(true) ? "��" : "���" ;
                    data[i][0] = numRow + 1;
                    data[i][1] = itemData[1];// ������������ ������������
                    data[i][2] = itemData[3];// ����������
                    data[i][3] = prop;// ��������
                    data[i][4] = "";
                    data[i][5] = "";
                    data[i][6] = "";
                    dataArray.add(data[i]);
                }
                    
            }
//            return equipment;
        }
        
        /**
         * �������� ������ �� ���� ������ � ������ ����������� �������������
         * @param id ��� �������, ��� �������� ���������� ������
         */
        private void getBorderBalance(int id){
            // �������� ������ �� ������������� ��� ������������
            BorderbalanceDaoImpl balanceDao = new BorderbalanceDaoImpl(id);
            // ���� ������ ����, �������� ������ ������
            if(balanceDao.getCount() > 0){
                numRow++;
                Borderbalance balance = balanceDao.getItem(0);
                String prop = balance.getBackup().equals(true) ? "��" : "���";
                Object[] item = getArray(numRow + 1, new String[]{"������","","","","",""});
                dataArray.add(item);
                numRow++;
                item = getArray(numRow + 1, new String[]{"�������","�������","",
                    "","",""});
                dataArray.add(item);
                numRow++;
                item = getArray(numRow + 1, new String[]{balance.getContent(),prop,"",
                    "","",""});
                dataArray.add(item);
            }
            
        }
        
        /**
         * �������� ������ �� ���� ������ � �������
         * @param id ��� �������, ��� �������� ���������� ������
         */
        private void getProjects(int id){
                    
            tdi = new ProjectsDaoImpl(id);// �������� ���������� �� ���������
            // ��������� ������� ���������� ������
            int count = tdi.getCount();
            if(count > 0){
                numRow++;
                Object[] item = getArray(numRow + 1, new String[]{"������","","","","",""});
                dataArray.add(item);
                numRow++;
                item = getArray(numRow + 1, new String[]{"��������","���","",
                    "","",""});
                dataArray.add(item);
                
                // �������� ������
                Object[][] data = new Object[count][7];// ������ �������
                for(int i = 0; i < count; i++){
                    numRow++;
                    Projects c = (Projects) tdi.getItem(i);
                    Object[] itemData = c.toDataArray();
                    data[i][0] = numRow + 1;
                    data[i][1] = (String) (itemData[5] + ", " + itemData[6]);// �������� �������
                    data[i][2] = itemData[2].toString();// ��� ���������
                    data[i][3] = "";
                    data[i][4] = "";
                    data[i][5] = "";
                    data[i][6] = "";
                    dataArray.add(data[i]);
                }
                    
            }
        }
        
        /**
         * �������� ������ �� ���� ������ �� ������� ������������ ����
         * @param id ��� �������, ��� �������� ���������� ������
         */
        private void getGazUsing(int id){
            final GazusingDaoImpl gazUsingDao = new GazusingDaoImpl(idObject);
            // ���� ������ ����
            if(gazUsingDao.getCount() > 0){
                // �������� ��������
                Gazusing using = gazUsingDao.getItem(0);
                numRow++;
                Object[] item = getArray(numRow + 1, new String[]{"����������","","","","",""});
                dataArray.add(item);
                numRow++;
                item = getArray(numRow + 1, new String[]{using.getUsingType(),"","",
                    "","",""});
                dataArray.add(item);
                
            }
        }
        
        /**
         * �������� ������ �� ���� ������ � ����� ����
         * @param id ��� �������, ��� �������� ���������� ������
         */
        private void getLaunch(int id){
            tdi = new LauncherDaoImpl(id);// �������� ���������� �� ���������
            // ��������� ������� ���������� ������
            int count = tdi.getCount();
            if(count > 0){
                numRow++;
                Object[] item = getArray(numRow + 1, new String[]{"��������","","","","",""});
                dataArray.add(item);
                numRow++;
                item = getArray(numRow + 1, new String[]{"����������","����","",
                    "","",""});
                dataArray.add(item);
                
                // �������� ������
                Object[][] data = new Object[count][7];// ������ �������
                for(int i = 0; i < count; i++){
                    numRow++;
                    Launcher c = (Launcher) tdi.getItem(i);
                    Object[] itemData = c.toDataArray();
                    data[i][0] = numRow + 1;
                    data[i][1] = (String) itemData[2];// ����������
                    data[i][2] = itemData[1].toString();// ����
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
                // ������������ ����������� � ������ ������� ������� �� ������
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
                    // �������� ������ ������ �� ������ �������
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
     * ��������� ������ ��������������� ������
     * @param lose ������ �� �����������
     * @param lenth ����� �����������
     * @param age ������� ����������� �� 25 ��� (���/���)
     * @param territory ��������������� ���������� (��/���)
     * @return ��������� ���������� ���
     */
    private BigDecimal calculateLose(int row){
        BigDecimal lose = (BigDecimal) tblLose.getValueAt(row, 2);
        BigDecimal lenth = (BigDecimal)tblLose.getValueAt(row, 3);
        boolean age = (boolean)tblLose.getValueAt(row, 4);
        boolean territory = (boolean)tblLose.getValueAt(row, 5);
        BigDecimal deltaAge = BigDecimal.ZERO;
        BigDecimal deltaTerritory = BigDecimal.ZERO;
        BigDecimal retLose;
        BigDecimal Lose = lose.multiply(lenth);// �������� ������ � ����������� �� ����� �����������
        // ��������� �������� �������� ����������� : ���� �� 25 ��� - ������ �� ����������
        // ����� ������������� �� 25%
        if(age == false)
            deltaAge = Lose.multiply(BigDecimal.valueOf(0.25));
        
        // �������� �������� ���������� : ���� �� ��������������� �����������, ��
        // ������ ������������� �� 25%, ����� �������� �����������
        if(territory == true)
            deltaTerritory = Lose.multiply(BigDecimal.valueOf(0.25));
//        System.out.println("lose=" + lose + ", lenth=" + lenth + ", age=" + 
//                age + ", territory=" + territory + ", Lose=" + Lose + 
//                ", deltaAge=" + deltaAge + " deltaTerritory=" + deltaTerritory);
        
        retLose = Lose.add(deltaAge);
        retLose = retLose.add(deltaTerritory);
        System.out.println("retlose=" + retLose);
        return retLose;// ���������� ��������� ����������
    }
    
    /**
     * �������� ���������� �� ���� ����� ����
     * @param columnName ������ ������������ ��������
     * @param columnClass ������ ������ �������
     */
    private void getUUGInform(String[] columnname, Class[] columnClass){
        
        int numRow = 1;// ��������� �������� ����� �������
        // ���������� ������ ������ ������� - ������������ ��������
        Object[][] str = new Object[][]{{numRow,"������������",
            "��� ���","�������� ���������","��. �.","���� �������",
            "���� ����. �������","",""}};
        
        // ������ � ��������� ������ �������
        MyTableModel mtModel = new MDIObject.MyTableModelImpl(str, columnname, columnClass);
        // ��������� ������� �������
        MDIObject.fullTableData(new int[]{0}, mtModel, tblAct);
        
        int recCount;// ���������� ������� � ������� ������
        // ������ ������ ��� ���������� ����� �������
        
        TableDaoImpl tdi = new CountersDaoImpl(idObject);// �������� ������ �� ���������
        recCount = tdi.getCount();// ���������� ���������� �������
        if(recCount > 0){
            // ���� ������ ����, ������� �� � ������
            for(int i = 0; i < recCount; i++){
                // ����������� ������� �����
                numRow++;
                Object[] content = new Object[9];
                // �������� ��������
                Counters counter = (Counters) tdi.getItem(i);
                Sprcounter co = new Sprcounter(counter.getIdcounter());
                Short period = counter.getPeriod();// ������������� ������
                content[0] = numRow;// ����� ������
                content[1] = "�������";// ��� ���
                content[2] = co.getCounterName()+ " G" + co.getQnom().toPlainString() + 
                        " �" + counter.getCounternumber();// ������������
                content[3] = co.getQmin().toPlainString() + "-" + co.getQmax().toPlainString();// ��������
                content[4] = co.getClass1().toPlainString();// ����� ��������
                String testDate = counter.getTestdate().toString();// ���� �������
                content[5] = testDate;// ���� �������
                short year = getTestYear(testDate);// �������� ��� �������
                content[6] = getNextTestDate(year, period, testDate);// ���� ��������� �������
                content[7] = "Vk, �3=";
                content[8] = "Wk, �3=";
                // ��������� ������ � ������ ������
                mtModel.addRow(content);
            }
            mtModel.setValueAt("���. ������: ", 0, 7);
            mtModel.setValueAt("��� ����������: ", 0, 8);
        }
        
        // �������� ������ �� �����������
        tdi = new CorrectorDaoImpl(idObject);
        String[][] modem;// ������ ������ �� ������ ������ � �������� ������ ����������
        recCount = tdi.getCount();
        if(recCount > 0){
            modem = new String[recCount][2];// ����� ������ �������
            // ���� ������ ����, ������� �� � ������
            for(int i = 0; i < recCount; i++){
                // ����������� ������� �����
                numRow++;
                Object[] content = new Object[9];
                // �������� ��������
                Corrector corrector = (Corrector) tdi.getItem(i);
                Sprcorrector cor = new Sprcorrector(corrector.getIdcorrector());
                content[0] = numRow;
                content[1] = "���������";
                content[2] = cor.getCorrectorName()+ " �" + corrector.getCorrectornumber();// ������������
                content[3] = "-";// �������� ���������
                content[4] = cor.getClass1().toPlainString();// ����� ��������
                String testDate = corrector.getTestdate().toString();// ���� �������
                content[5] = testDate;
                short year = getTestYear(testDate);// �������� ��� �������
                short period = corrector.getPeriod();// �������� ������
                content[6] = getNextTestDate(year, period, testDate);// �������� ���� ��������� �������
                modem[i][0] = corrector.getModem();// ����� ������
                modem[i][1] = corrector.getAddres();// ������� �����
                content[7] = "Vc, �3=";
                content[8] = "Qc, �3/�=";
                // ��������� ������ � ������ ������
                mtModel.addRow(content);
            }
            // ������� � ������ ������ ������ � �������� ������
            String modemNumber = "";
            String corAdres = "";
            for (String[] modem1 : modem) {
                modemNumber = modemNumber + modem1[0] + ", ";
                corAdres = corAdres + modem1[1] + ", ";
            }
            if(modemNumber.length() != 0) modemNumber = modemNumber.substring(0, modemNumber.length() - 2);
            if(corAdres.length() != 0) corAdres = corAdres.substring(0, corAdres.length() - 2);
            mtModel.setValueAt("���. ������: " + modemNumber, 0, 7);
            mtModel.setValueAt("��� ����������: " + corAdres, 0, 8);
        }
        
        // �������� ������ �� ��������
        tdi = new DatchikDaoImpl(idObject);
        recCount = tdi.getCount();
        if(recCount > 0){
            // ���� ������ ����, ������� �� � ������
            for(int i = 0; i < recCount; i++){
                // ����������� ������� �����
                numRow++;
                Object[] content = new Object[9];
                // �������� ��������
                Datchik datchik = (Datchik) tdi.getItem(i);
                Sprdatchik sd = new Sprdatchik(datchik.getIddatchik());
                content[0] = numRow;
                content[1] = "���������������";
                content[2] = sd.getDatchikName()+ " �" + datchik.getDatchiknumber();// ������������
                content[3] = sd.getDiapazon();// ��������
                content[4] = sd.getClass1().toPlainString();// ����� ��������
                String testDate = datchik.getPoverkadate().toString();// ���� �������
                content[5] = testDate;
                short year = getTestYear(testDate);// �������� ��� �������
                short period = datchik.getPeriod();// ������ �������
                content[6] = getNextTestDate(year, period, testDate);// �������� ���� ���������� �������
                content[7] = "";
                content[8] = "";
                // ��������� ������ � ������ ������
                mtModel.addRow(content);
            }
            mtModel.setValueAt("Q�, �3/�=", numRow - 2, 7);
            mtModel.setValueAt("P, ���=", numRow - 2, 8);
            mtModel.setValueAt("�=", numRow - 1, 7);
            mtModel.setValueAt("�, �=", numRow - 1, 8);
        }
        setPropertyActTable("uuginform_width", columnClass);
    }
    
    /**
     * ��������� ��� ������� ������� �� ������ ���� �������
     * @param testDate ���� ������� �������
     * @return ���������� ��� ������� �������
     */
    private short getTestYear(String testDate){
        if(testDate.equals("")) return 0;
        // ������ ������ ������� �� ������ ���� ������������ ����� ��� �������
        String year = testDate.substring(0, 4);
        return Short.valueOf(year);
    }
    
    /**
     * ��������� ��������� ��� ������� �������
     * @param year ������� ��� �������
     * @param period ������ �������
     * @return ��������� ��� ������� �������
     */
    private String getNextTestDate(short year, short period, String testDate){
        
        String nextYear = String.valueOf(year + period);
        return testDate.replaceFirst(testDate.substring(0, 4), nextYear);
    }
    
    /**
     * �������� ���������� �� ������������� ������� �� ���� ����� ����
     * @param columnName ������ ������������ ��������
     * @param columnClass ������ ������ �������
     */
    private void getPlombsInform(String[] columnname, Class[] columnClass){
        int numRow = 1;// ��������� �������� ����� �������
        // ���������� ������ ������ ������� - ������������ ��������
        Object[][] str = new Object[][]{new Object[]{numRow,"� �/�","����� ��������� �����",
            "� ������/��������� ��������","����","�������������"}};
        
        // ������ � ��������� ������ ������ �������
        MyTableModel mtModel = new MDIObject.MyTableModelImpl(str, columnname, columnClass);
        MDIObject.fullTableData(new int[]{0}, mtModel, tblAct);// ��������� �������
        int recCount;// ���������� ������� � ������� ������
        // ������ ������ ��� ���������� ����� �������
        TableDaoImpl tdi = new PlombsDaoImpl(idObject);// �������� ������
        recCount = tdi.getCount();
        if(recCount > 0){
            // ���� ������ ����, ��������� ������
            for(int i = 0; i < recCount; i++){
                numRow++;// ���������� ������� �����
                Plombs plombs = (Plombs) tdi.getItem(i);// �������� ��������
                Sprplumbsinstalplace spip = new Sprplumbsinstalplace(plombs.getIdinstalplace());
                Object[] content = new Object[6];// ������ ������� ������
                content[0] = numRow;
                content[1] = numRow - 1;
                content[2] = spip.getPlaceName();
                content[3] = plombs.getPlombnumber();
                content[4] = plombs.getInstaldate().toString();
                content[5] = plombs.getAddility();
                // ��������� ������ � ������ ������
                mtModel.addRow(content);
            }
            
        }
        setPropertyActTable("plombinform_width", columnClass);
    }
    
    /**
     * �������� ���������� �� ������ � ���������������� - ��������������� �������
     * � ��������� ����� ������ �� �����������
     * @param columnName ������ ������������ ��������
     * @param columnClass ������ ������ �������
     */
    private void getLoseInform(String[] columnname, Class[] columnClass){
        
        int numRow = 1;// ��������� �������� ����� �������
        // ���������� ������ ������ ������� - ������������ ��������
        Object[][] str = new Object[][]{new Object[]{numRow,"�����������","�������, ��",
            "������, �3/(��� ��)","�����, ��","������� �� 25 ���, (��/���)",
                    "������. ����������, (��/���)","�����, �3/���"}};
        
        // ������ � ��������� ������ ������ �������
        MyTableModel mtModel = new MDIObject.MyTableModelImpl(str, columnname, columnClass);
        MDIObject.fullTableData(new int[]{0}, mtModel, tblAct);// ��������� �������
        
        int recCount;// ���������� ������� � ������� ������
        
        // ������ ������ ��� ���������� ����� �������
        TableDaoImpl tdi = new HipressloseDaoImpl(idObject);// �������� ������ �� �������� ��������
        recCount = tdi.getCount();
        if(recCount > 0){
            // ���� ������ ����, ��������� ������
            for(int i = 0; i < recCount; i++){
                numRow++;// ���������� ������� �����
                Hipresslose hpl = (Hipresslose) tdi.getItem(i);
                Sprgazlinerhipress shpl = new Sprgazlinerhipress(hpl.getIdgazhipress());
                Object[] content = new Object[8];
                content[0] = numRow;
                content[1] = "����������� �/�";
                content[2] = shpl.getDimeter();
                content[3] = shpl.getLose().toPlainString();
                content[4] = hpl.getHplenght().toPlainString();
                content[5] = hpl.getTo25year() == true ? "��" : "���";
                content[6] = hpl.getPodrabterritory() == true ? "��" : "���";
                content[7] = hpl.getResult().toPlainString();
                mtModel.addRow(content);
            }
        }
        tdi = new MidpressloseDaoImpl(idObject);// �������� ������ �� �������� ��������
        recCount = tdi.getCount();
        if(recCount > 0){
            // ���� ������ ����, ��������� ������
            for(int i = 0; i < recCount; i++){
                numRow++;// ���������� ������� �����
                Midpresslose mpl = (Midpresslose) tdi.getItem(i);
                Sprgazlinermidpress smpl = new Sprgazlinermidpress(mpl.getIdgazmidpress());
                Object[] content = new Object[8];
                content[0] = numRow;
                content[1] = "����������� �/�";
                content[2] = smpl.getDimeter();
                content[3] = smpl.getLose().toPlainString();
                content[4] = mpl.getMplenght().toPlainString();
                content[5] = mpl.getTo25year() == true ? "��" : "���";
                content[6] = mpl.getPodrabterritory() == true ? "��" : "���";
                content[7] = mpl.getResult().toPlainString();
                mtModel.addRow(content);
            }
        }
        tdi = new RlowpressloseDaoImpl(idObject);// �������� ������ �� �������� ��������
        recCount = tdi.getCount();
        if(recCount > 0){
            // ���� ������ ����, ��������� ������
            for(int i = 0; i < recCount; i++){
                numRow++;// ���������� ������� �����
                Rlowpresslose rpl = (Rlowpresslose) tdi.getItem(i);
                Sprgazlinerrlowpress srpl = new Sprgazlinerrlowpress(rpl.getIdgazrlowpress());
                Object[] content = new Object[8];
                content[0] = numRow;
                content[1] = "����������� ����������������� �/�";
                content[2] = srpl.getDimeter();
                content[3] = srpl.getLose().toPlainString();
                content[4] = rpl.getRlowlenght().toPlainString();
                content[5] = rpl.getTo25year() == true ? "��" : "���";
                content[6] = rpl.getPodrabterritory() == true ? "��" : "���";
                content[7] = rpl.getResult().toPlainString();
                mtModel.addRow(content);
            }
        }
        tdi = new DlowloseDaoImpl(idObject);// �������� ������ �� ������� ��������
        recCount = tdi.getCount();
        if(recCount > 0){
            // ���� ������ ����, ��������� ������
            for(int i = 0; i < recCount; i++){
                numRow++;// ���������� ������� �����
                Dlowlose dpl = (Dlowlose) tdi.getItem(i);
                Sprgazlinerdlowpress sdpl = new Sprgazlinerdlowpress(dpl.getIdgazdlowpress());
                Object[] content = new Object[8];
                content[0] = numRow;
                content[1] = "����������� �������� �/�";
                content[2] = sdpl.getDimeter();
                content[3] = sdpl.getLose().toPlainString();
                content[4] = dpl.getDlowlenght().toPlainString();
                content[5] = dpl.getTo25year() == true ? "��" : "���";
                content[6] = dpl.getPodrabterritory() == true ? "��" : "���";
                content[7] = dpl.getResult().toPlainString();
                mtModel.addRow(content);
            }
        }
        tdi = new RegloseDaoImpl(idObject);// ��������� ������ �� �����������
        recCount = tdi.getCount();
        if(recCount > 0){
            numRow++;// ���������� ������� �����
            // ���� ������ ����, ��������� ������
            mtModel.addRow(new Object[]{numRow,"����������","��� ����������",
                "","","������, �3/���","����������, ��","�����, �3/���"});
            for(int i = 0; i < recCount; i++){
                numRow++;// ���������� ������� �����
                Reglose rl = (Reglose) tdi.getItem(i);
                Sprgazregulator sgr = new Sprgazregulator(rl.getIdregulator());
                Object[] content = new Object[8];
                content[0] = numRow;
                content[1] = "���, �� ���, ���, ����";
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
     * �������� ���������� �� ������ � ������� ������������ ���� ����� ����
     * ��� ����������� � �������
     * @param columnName ������ ������������ ��������
     * @param columnClass ������ ������ �������
     */
    private void getPoverkaInform(String[] columnname, Class[] columnClass){
        int numRow = 1;// ��������� �������� ����� �������
        // ���������� ������ ������ ������� - ������������ ��������
        Object[][] str = new Object[][]{new Object[]{numRow,"������������","��� ���",
            "���� �������","���� ��������� �������"}};
        
        // ������ � ��������� ������ ������ �������
        MyTableModel mtModel = new MDIObject.MyTableModelImpl(str, columnname, columnClass);
        MDIObject.fullTableData(new int[]{0}, mtModel, tblAct);// ��������� �������
        
        int recCount;// ���������� ������� � ������� ������
        
        // ������ ������ ��� ���������� ����� �������
        TableDaoImpl tdi = new CountersDaoImpl(idObject);// �������� ������ �� ���������
        recCount = tdi.getCount();// ���������� ���������� �������
        if(recCount > 0){
            // ���� ������ ����, ������� �� � ������
            for(int i = 0; i < recCount; i++){
                // ����������� ������� �����
                numRow++;
                Object[] content = new Object[5];
                // �������� ��������
                Counters counter = (Counters) tdi.getItem(i);
                Sprcounter co = new Sprcounter(counter.getIdcounter());
                Short period = counter.getPeriod();// ������������� ������
                content[0] = numRow;// ����� ������
                content[1] = "�������";// ��� ���
                content[2] = co.getCounterName()+ " G" + co.getQnom().toPlainString() + 
                        " �" + counter.getCounternumber();// ������������
                String testDate = counter.getTestdate().toString();// ���� �������
                content[3] = testDate;// ���� �������
                short year = getTestYear(testDate);// �������� ��� �������
                content[4] = getNextTestDate(year, period, testDate);// ���� ��������� �������
                // ��������� ������ � ������ ������
                mtModel.addRow(content);
            }
        }
        
        // �������� ������ �� �����������
        tdi = new CorrectorDaoImpl(idObject);
        String[][] modem;// ������ ������ �� ������ ������ � �������� ������ ����������
        recCount = tdi.getCount();
        if(recCount > 0){
            modem = new String[recCount][];// ����� ������ �������
            // ���� ������ ����, ������� �� � ������
            for(int i = 0; i < recCount; i++){
                // ����������� ������� �����
                numRow++;
                Object[] content = new Object[5];
                // �������� ��������
                Corrector corrector = (Corrector) tdi.getItem(i);
                Sprcorrector cor = new Sprcorrector(corrector.getIdcorrector());
                content[0] = numRow;
                content[1] = "���������";
                content[2] = cor.getCorrectorName()+ " �" + corrector.getCorrectornumber();// ������������
                String testDate = corrector.getTestdate().toString();// ���� �������
                content[3] = testDate;
                short year = getTestYear(testDate);// �������� ��� �������
                short period = corrector.getPeriod();// �������� ������
                content[4] = getNextTestDate(year, period, testDate);// �������� ���� ��������� �������
                // ��������� ������ � ������ ������
                mtModel.addRow(content);
            }
            
        }
        
        // �������� ������ �� ��������
        tdi = new DatchikDaoImpl(idObject);
        recCount = tdi.getCount();
        if(recCount > 0){
            // ���� ������ ����, ������� �� � ������
            for(int i = 0; i < recCount; i++){
                // ����������� ������� �����
                numRow++;
                Object[] content = new Object[5];
                // �������� ��������
                Datchik datchik = (Datchik) tdi.getItem(i);
                Sprdatchik sd = new Sprdatchik(datchik.getIddatchik());
                content[0] = numRow;
                content[1] = "������";
                content[2] = sd.getDatchikName()+ " �" + datchik.getDatchiknumber();// ������������
                String testDate = datchik.getPoverkadate().toString();// ���� �������
                content[3] = testDate;
                short year = getTestYear(testDate);// �������� ��� �������
                short period = datchik.getPeriod();// ������ �������
                content[4] = getNextTestDate(year, period, testDate);// �������� ���� ���������� �������
                // ��������� ������ � ������ ������
                mtModel.addRow(content);
            }
        }
        setPropertyActTable("poverkainform_width", columnClass);
    }
    
    /**
     * ����� �������� ��� ������� ����� � �����������
     * @param propWidth ��� �������� � ����� ������� ��� ��������� ������ ��������
     * @param columnClass ������ ����� ������ �������� �������
     */
    private void setPropertyActTable(String propWidth, Class[] columnClass){
        // ������ ��������� ������ �������� �������
        ColumnModelListener colListener = new ColumnModelListener(tblAct, props, propWidth);
        MDIObject.setTablecolwidth(props, propWidth, tblAct);// ����� ������ �������
        
        // ��������� ��������� � ������ ��������
        tblAct.getColumnModel().addColumnModelListener(colListener);
        
        // ��������� ������������� ��� ��������
        TableCell_Renderer.setWordWrapCellRenderer(tblAct, columnClass);
        TableCell_Renderer.setIntegerTableCellRenderer(tblAct, Color.LIGHT_GRAY);
        
        MDIObject.addColumnSelectionListener(tblAct);
    }
}
