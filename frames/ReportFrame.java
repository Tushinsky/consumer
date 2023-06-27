/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import abonentgaz.SplitLayoutManager;
import abonentgaz.TableCell_Renderer;
import dao_impl.SprcategoryDaoImpl;
import dao_impl.SprcityDaoImpl;
import dao_impl.SpryearDaoImpl;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import runqueries.Runquery;

/**
 * ����� ��� ������������ � ������ �������
 * @author Sergii.Tushinskyi
 */
public class ReportFrame extends JFrame {
    private JTree reportTree;// ������, ���������� ���� �������� ����
    private JButton okButton;// ������, ����������� ������������ ���������� ������
    private JButton closeButton;// ������, ������������ ������ � ��������������� ��������� ������������ ���������� ������
    private JSplitPane reportSplit;// �������������� ������
    private JScrollPane treePane;// ������ ��������� ��� ������ �������
    private JTable reportTable;// ������� ��� ������ ������ ������������ ���������� ������
    private JScrollPane tablePane;// ������ ��������� ��� ������� �������
    private JPanel treePanel;// ������ ��� ���������� ������ �������
    private JPanel reportPanel;// ������ ��� ���������� ��������� ���������� ��� ������ �������������� ������� ������
    private JPanel filterPanel;// ������ ��� ���������� ������ ����������
    private JPanel commonPanel;// ������ ��� ���������� ����� ����������� �����
    private final String fileNameProperty = "reportframe.properties";
    private JComboBox categoryCombo;// ������, ���������� ������������ ���������
    private JComboBox yearCombo;// ������ ���
    private JComboBox monthCombo;// ������ �������
    private JComboBox townCombo;// ������ ��������� �������
    private JFormattedTextField txtFromField;
    private JFormattedTextField txtToField;
    
    // �����, ����������, ����������� �� ��������������� �������� ����������
    private boolean categoryFlag = false;
    private boolean townFlag = false;
    private boolean dateFlag = false;
    private boolean yearFlag = false;
    private boolean monthFlag = false;
    private boolean statusFlag = false;
    private boolean noneFlag = false;
    
    private String viewName;// ��� �������������
    private QueryFilter queryFilter = new QueryFilter();// ����� ��� ������ ������ - �������
    
    public ReportFrame() throws HeadlessException {
        super.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent we) {
                super.windowOpened(we); //To change body of generated methods, choose Tools | Templates.
                setTitle("������");// ��������� ����
                //����� ������ ��� ������
                URL url;
                url = ReportFrame.class.getClassLoader().getResource("Images/book.png");
                Image image;
                image = new ImageIcon(url).getImage();
                if (image != null) setIconImage(image);
                
                initComponents();// ������ ���������� � ��������� �� �� �����
                
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// ��� �������� ����� ����������� �������
                setExtendedState(JFrame.MAXIMIZED_BOTH);// ������������� ����������� ��� ����
                setModalExclusionType(Dialog.ModalExclusionType.NO_EXCLUDE);// ��� ���� - ���������
                
                try{
                    // ���������� ������������ ������������ �� ����� �������
                    SplitLayoutManager slm = new SplitLayoutManager(fileNameProperty);
                    slm.setKeyName(reportSplit.getName());
                    int location = slm.getLocation();
                    if(location != 0)
                        reportSplit.setDividerLocation(location);

                } catch(Exception ex){
                    reportSplit.setDividerLocation(300);
                }
            }

            @Override
            public void windowClosing(WindowEvent we) {
                super.windowClosing(we); //To change body of generated methods, choose Tools | Templates.
            }
            
        });
    }
    
    /**
     * �������� ����������� ����������������� ���������� � ���������� �� �� �����
     */
    private void initComponents(){
        // ������ �������� ����������
        // ������ �������
        reportTree = new JTree(treeModel());
        // ��������� ��������� ������ ��������� ������
        reportTree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                // �������� ���� � ��������� �����
                TreePath selectionPath = reportTree.getSelectionPath();
                // �������� ��������� ���� �����
                if(selectionPath == null) return;
                Views node = (Views) 
                        selectionPath.getLastPathComponent();
                if(node.isLeaf()){
                    // ���� ��� �����, ������� � �������� � ���������
                    viewName = node.getViewName();
                    dropFilters();// ���������� ��� �������
                    CreateLayoutElements(node.getIndex());
                }
            }
        });
        // ��������� ���������� ������������� - ����������� ������
        reportTree.addTreeExpansionListener(new TreeExpansionListener() {
            // ������������ ������
            @Override
            public void treeExpanded(TreeExpansionEvent tee) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            // ���������� ������
            @Override
            public void treeCollapsed(TreeExpansionEvent tee) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                // ��� ������������ ���� ������ �������� ������ � ��������
                if(filterPanel.isVisible()){
                    filterPanel.setVisible(false);
                    // � ����� ���������� ��� ������� ������ �������
                    dropFilters();
                }
                
                
                
            }
        });
        reportTree.setShowsRootHandles(true);// ����������� ����� ���������� ����� ������
        reportTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        treePane = new JScrollPane(reportTree);// ������ ��������� ������
        treePanel = new JPanel(new BorderLayout());
        treePanel.add(treePane, BorderLayout.CENTER);// ��������� ������ �� ������
        
        // ������ ��� ���������� ������
        fullCategoryCombo();// ������� � ��������� ������ ���������
        reportPanel = new JPanel(new BorderLayout(10, 5));
        okButton = new JButton("OK");
        okButton.setPreferredSize(new Dimension(75, 26));
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sqlQuery;
                String filter = queryFilter.getStringfilter();
                if(filter.equals(""))
                    sqlQuery = "SELECT * FROM " + viewName + ";";
                else
                    sqlQuery = "SELECT * FROM " + viewName + 
                            " A WHERE A." + filter + ";";
                System.out.println("sqlquery=" + sqlQuery);
                setData(sqlQuery);
            }
        });
        closeButton = new JButton("Close");
        closeButton.setPreferredSize(new Dimension(75, 26));
        filterPanel = new JPanel(new BorderLayout());
        filterPanel.setVisible(false);// ������ ������ ���������
//        filterPanel.add(new JLabel("���������"));
//        filterPanel.add(categoryCombo);
//        filterPanel.add(okButton);
//        filterPanel.add(closeButton);
        
        // ������� � ������ ��� � ����������
        reportTable = new JTable();
        setDefaultTableModel();// ��������� ������� ������� �� ���������
//        reportTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        reportTable.setCellSelectionEnabled(true);
        reportTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        TableCell_Renderer.setIntegerTableCellRenderer(reportTable, Color.YELLOW);
        TableCell_Renderer.setShortTableCellRenderer(reportTable, new Color(255, 102, 102));
        TableCell_Renderer.setBigDecimalTableCellRenderer(reportTable, new Color(51, 204, 255));
        MDIObject.addColumnSelectionListener(reportTable);
//        TableCell_Renderer.setWordWrapCellRenderer(reportTable);
                
//        reportTable.setDefaultRenderer(Character.class, new WordWrapCellRenderer());
//        reportTable.setDefaultRenderer(Color.class, new ColorCellRenderer(0, Color.white));
        tablePane = new JScrollPane(reportTable);
        reportPanel.add(filterPanel, BorderLayout.NORTH);
        reportPanel.add(tablePane, BorderLayout.CENTER);
        
        // ����������� �������
        reportSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        reportSplit.setLeftComponent(treePanel);
        reportSplit.setRightComponent(reportPanel);
        reportSplit.setName("reportSplit");
        // ��������� ���������� ������� ��� �����������
        reportSplit.addPropertyChangeListener("dividerLocation", 
                new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
//                throw new UnsupportedOperationException("Not supported yet.");
                // ����� ��� ������ �������
                SplitLayoutManager slManager = new 
                        SplitLayoutManager(fileNameProperty);
                // ������� ��� �����������, ��� �������� ����� �������� ���������
                slManager.setKeyName(reportSplit.getName());
                slManager.setLocation(reportSplit.getDividerLocation());
            }
        });
        

        // ������ ��� ���������� �����������
        commonPanel = new JPanel(new BorderLayout());
        commonPanel.add(reportSplit, BorderLayout.CENTER);
        // ��������� � �� ������ �����������
        this.getContentPane().add(commonPanel);
        
        
    }
    
    private void fullCategoryCombo(){
        SprcategoryDaoImpl cdi = new SprcategoryDaoImpl();// ������ � ����������� ���������
        String[] model = new String[cdi.getCount() + 1];// ������ ��� ���������� ������
        model[0] = "��� ���������";
        for(int i = 1; i < model.length; i++){
            model[i] = cdi.getItem(i - 1).getCategoryName();// ��������� �������
        }
        ComboBoxModel comboModel = new DefaultComboBoxModel(model);
        categoryCombo = new JComboBox(comboModel);// ��������� ������
        categoryCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // � ����������� �� ���������� �������� ����� ��������
                // ������� �� ���������
                if(categoryCombo.getSelectedIndex() == 0){
                    // ������ ������ ������� � ������ (��� ���������)
                    queryFilter.setCategoryfilter("");
                } else {
                    // ������ ������ ������� � ������
                    queryFilter.setCategoryfilter('"' + "���������" + '"' + "='" + 
                            categoryCombo.getSelectedItem().toString() + "'");
                }
                
            }
        });
        
    }
    
    private void dropFilters(){
        queryFilter.dropFilter();
    }
    
    private DefaultTreeModel treeModel(){
        int index = 0;// ��������� ��������
        // ����� ������ ��� ������ ������
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("������");
        DefaultMutableTreeNode consumerInfo = new Views();
        consumerInfo.setUserObject("���������� �� �����������");
        DefaultMutableTreeNode poverkaInfo = new Views();
        poverkaInfo.setUserObject("������� ���");
        DefaultMutableTreeNode loseInfo = new Views();
        loseInfo.setUserObject("���");
        DefaultMutableTreeNode objectInfo = new Views();
        objectInfo.setUserObject("���������� �� �������� �����������");
        DefaultMutableTreeNode flowInfo = new Views("QUERYCATEGORY_FLOWYEAR", 1);
        flowInfo.setUserObject("������ �����������");
        DefaultMutableTreeNode turnInfo = new Views();
        turnInfo.setUserObject("����������/�����������");
        DefaultMutableTreeNode plombsInfo = new Views();
        plombsInfo.setUserObject("������");
//        DefaultMutableTreeNode sitInfo = new Views();
//        sitInfo.setUserObject("���");
        
        DefaultMutableTreeNode categoryInfo = new Views("CATEGORY_VIEW", 0);
        categoryInfo.setUserObject("���������");
        
        // �����������
//        index++;
//        DefaultMutableTreeNode itemInfo = new Views("QUERYCATEGORY_FLOWYEAR", 1);
//        itemInfo.setUserObject("�������");
//        flowInfo.add(itemInfo);
//        index++;
//        itemInfo = new Views("QUERYCATEGORY_FLOWMONTH", 1);
//        itemInfo.setUserObject("��������");
//        flowInfo.add(itemInfo);
        
        index++;
        DefaultMutableTreeNode limitInfo = new Views("QUERYCATEGORY_LIMIT", 6);
        limitInfo.setUserObject("������");
        
        // �������
        index++;
        DefaultMutableTreeNode itemInfo = new Views("QUERYTEST_COUNT", 2);
        itemInfo.setUserObject("��������");
        poverkaInfo.add(itemInfo);
        index++;
        itemInfo = new Views("QUERYTEST_CORRECTOR", 2);
        itemInfo.setUserObject("����������");
        poverkaInfo.add(itemInfo);
        index++;
        itemInfo = new Views("QUERYTEST_DATCHIK", 2);
        itemInfo.setUserObject("�������");
        poverkaInfo.add(itemInfo);
        
        index++;
        DefaultMutableTreeNode monthInfo = new Views("QUERYCATEGORY_MONTH", 3);
        monthInfo.setUserObject("�����");
        index++;
        DefaultMutableTreeNode cvartalInfo = new Views("QUERYCATEGORY_CVARTAL", 3);
        cvartalInfo.setUserObject("�������");
        
        // ������
        index++;
        itemInfo = new Views("QUERYCATEGORY_HIPRESS", 0);
        itemInfo.setUserObject("�������� ��������");
        loseInfo.add(itemInfo);
        index++;
        itemInfo = new Views("QUERYCATEGORY_MIDPRESS", 0);
        itemInfo.setUserObject("�������� ��������");
        loseInfo.add(itemInfo);
        index++;
        itemInfo = new Views("QUERYCATEGORY_RLOWPRESS", 0);
        itemInfo.setUserObject("����������������� ������� ��������");
        loseInfo.add(itemInfo);
        index++;
        itemInfo = new Views("QUERYCATEGORY_DLOWPRESS", 0);
        itemInfo.setUserObject("�������� ������� ��������");
        loseInfo.add(itemInfo);
        index++;
        itemInfo = new Views("QUERYCATEGORY_REGULATOR", 0);
        itemInfo.setUserObject("����������");
        loseInfo.add(itemInfo);
        
        // ����������
        index++;
        itemInfo = new Views("QUERYCATEGORY_TURNOBJECT", 4);
        itemInfo.setUserObject("�������");
        turnInfo.add(itemInfo);
        index++;
        itemInfo = new Views("QUERYCATEGORY_TURNEQUIPMENT", 4);
        itemInfo.setUserObject("������������");
        turnInfo.add(itemInfo);
        
        // ������
        index++;
        itemInfo = new Views("������", index);
        itemInfo.setUserObject("���� �����");
        plombsInfo.add(itemInfo);
        index++;
        itemInfo = new Views("������", index);
        itemInfo.setUserObject("���");
        plombsInfo.add(itemInfo);
        
        // ���
//        index++;
//        itemInfo = new Views("��������", index);
//        itemInfo.setUserObject("��������");
//        sitInfo.add(itemInfo);
//        index++;
//        itemInfo = new Views("����������", index);
//        itemInfo.setUserObject("����������");
//        sitInfo.add(itemInfo);
        
        index++;
        DefaultMutableTreeNode seasonInfo = new Views("������������ �����", index);
        seasonInfo.setUserObject("������������ �����");
        
        index++;
        DefaultMutableTreeNode twocountInfo = 
                new Views("QUERYCATEGORY_COUNTTWO_ANDMORE", 5);
        twocountInfo.setUserObject("������� � 2-�� � ����� ����������");
        
        index++;
        DefaultMutableTreeNode equipmentInfo = 
                new Views("QUERYCATEGORY_EQUIPMENT", 5);
        equipmentInfo.setUserObject("������������");
        
        index++;
        DefaultMutableTreeNode datelaunchInfo = 
                new Views("QUERYCATEGORY_GAZLAUNCH", 5);
        datelaunchInfo.setUserObject("���� ����� ����");
        
        index++;
        DefaultMutableTreeNode commonobjectInfo = 
                new Views("������� ���������� �� ��������", index);
        commonobjectInfo.setUserObject("������� ����������");
        
        index++;
        DefaultMutableTreeNode listshowInfo = new Views("��������� ���������", index);
        listshowInfo.setUserObject("��������� ���������");
        
        index++;
        DefaultMutableTreeNode gazusingInfo = new Views("QUERY_GAZUSING", 5);
        gazusingInfo.setUserObject("������� ������������� ���������� ����");
        
        
        
        
        // ��������� ���� ������
        consumerInfo.add(categoryInfo);
        consumerInfo.add(flowInfo);
        consumerInfo.add(limitInfo);
        objectInfo.add(turnInfo);
        objectInfo.add(plombsInfo);
//        objectInfo.add(sitInfo);
        objectInfo.add(seasonInfo);
        objectInfo.add(twocountInfo);
        objectInfo.add(equipmentInfo);
        objectInfo.add(datelaunchInfo);
        objectInfo.add(commonobjectInfo);
        objectInfo.add(listshowInfo);
        objectInfo.add(gazusingInfo);
        root.add(consumerInfo);
        root.add(poverkaInfo);
        root.add(monthInfo);
        root.add(cvartalInfo);
        root.add(loseInfo);
        root.add(objectInfo);
        
        // ������ � ���������� ������
        DefaultTreeModel model = new DefaultTreeModel(root);
        return model;
    }
    
    /**
     * ������ �������� ���������� ��� ������ ������
     * @param index 
     */
    private void CreateLayoutElements(int index){
        switch(index){
            case 0:
                // ���������, ������ �������� ��������,
                // ������ �������� ��������, ������ ������� ������� ��������
                // ������ ������ ������� ��������, ������ �� �����������
                // ������ �� ���������
                createFilter_Category();
                break;
            case 1:// ������� �����������, �������� �����������
                // ������ �� ���������, ���� � ������
                createFiter_CategoryYearMonth();
                break;
            case 2:// ������� ���������, ������� �����������, ��������
                // ������ �� ���������  � ����
                createFilter_CategoryDate();
                break;
            case 3:// �������� �����, ����������� �����
                // ��� �������
                createFilterNone();
                break;
            case 4:// ���������� ��������, ���������� ������������
                // ������ �� ���������, ����, ������ � �������
                createFilter_CategoryDateTownStatus();
                break;
            case 5:// 2 � ����� ���������, ������������, ���� �����, ������� �������������
                // ������ �� ��������� � ������
                createFilter_CategoryTown();
                break;
            case 6:// ������
                // ������ �� ��������� � ������
                createFilter_CategoryMonth();
                break;
        }
    }
    
    /**
     * ���������� ��������� ������, �� ������� ����������� ������ � ��������
     * ���������� ��� ������ �������
     */
    private void dropFilterPanel(){
        if(filterPanel.isVisible()) filterPanel.setVisible(false);
        filterPanel.removeAll();
        filterPanel.setLayout(new BorderLayout(5, 5));
    }
    
    /**
     * ������ �������� ���������� ��� ������ ������� �� ���������
     */
    private void createFilter_Category(){
        // ��������� ��������� ����� �������� ��������� ���������� �����
        if(categoryFlag == false){
            // �������� ������ ������ � ������� ��� �������� � ��
            dropFilterPanel();
            categoryCombo.setSelectedIndex(0);// �������� ������ ������� � ������
            /*
             ���� ����� �������� ���������� �� �����������, �� ������ �� �
             ��������� �� �� ������ ������
            */
            // ������ � ������ ��������� �� ������ ������ �����
            JPanel panel = new JPanel(new FlowLayout(10));
            panel.add(new JLabel("���������"));
            panel.add(categoryCombo);
            panel.add(okButton);
            panel.add(closeButton);
            filterPanel.add(panel, BorderLayout.WEST);
            filterPanel.add(new JPanel(), BorderLayout.CENTER);// � ������ ��������� ������ ������
            categoryFlag = true;// ����� ����
            
            // ������� ��� ��������� �����
            dateFlag = false;
            townFlag = false;
            yearFlag = false;
            monthFlag = false;
            noneFlag = false;
            statusFlag = false;
        } else {
            categoryCombo.setSelectedIndex(0);// �������� ������ ������� � ������
        }
        // ������ ������ ������ �������, ���� ��� ������
        if(filterPanel.isVisible() == false) filterPanel.setVisible(true);
    }
    
    /**
     * ������ �������� ���������� ��� ������ ������� �� ���������, ���� � ������
     */
    private void createFiter_CategoryYearMonth(){
        // ��������� ��������� ����� �������� ��������� ���������� �����
        if(yearFlag == false){
            // �������� ������ ������ � ������� ��� �������� � ��
            dropFilterPanel();
            categoryCombo.setSelectedIndex(0);// �������� ������ ������� � ������
            /*
             ���� ����� �������� ���������� �� �����������, �� ������ �� �
             ��������� �� �� ������ ������
            */
            // ������ � ������ ��������� �� ������ ������ �����
            JPanel panel = new JPanel(new GridLayout(3,2,5,5));
            panel.add(new JLabel("  ���������"));
            panel.add(categoryCombo);
            
            // �������� ������ �� �����
            SpryearDaoImpl ydi = new SpryearDaoImpl();
            Object[] content = new Object[ydi.getCount()];
            for(int i = 0; i < ydi.getCount(); i++)
                content[i] = ydi.getItem(i).getNameYear();
            yearCombo = new JComboBox(content);// ������ ������ � ������
            yearCombo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    queryFilter.setYearfilter('"' + "���" + '"' + "=" + 
                            yearCombo.getSelectedItem().toString());
                }
            });
            // �������� ��������� ������� � ������
            yearCombo.setSelectedIndex(yearCombo.getItemCount() - 1);
            panel.add(new JLabel("  ���"));
            panel.add(yearCombo);// ��������� ��� �� ������
            
            // ������ � ��������� ������ � ��������� ������� ����
            content = new Object[]{"�� ���","������","�������","����","������","���",
                "����","����","������","��������","�������","������","�������"};
            monthCombo = new JComboBox(content);
            monthCombo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    if(monthCombo.getSelectedIndex() == 0){
                        queryFilter.setMonthfilter("");
                        viewName = "QUERYCATEGORY_FLOWYEAR";
                    } else {
                        queryFilter.setMonthfilter('"' + "�����" + '"' + "=" + 
                                monthCombo.getSelectedIndex());
                        viewName = "QUERYCATEGORY_FLOWMONTH";
                    }
                }
            });
            monthCombo.setSelectedIndex(0);// �������� ������ ������� � ������
            
            
            panel.add(new JLabel("  �����"));
            panel.add(monthCombo);
            
            JPanel panel1 = new JPanel();
            panel1.add(okButton);
            panel1.add(closeButton);
            filterPanel.add(panel, BorderLayout.WEST);
//            filterPanel.add(new JPanel(), BorderLayout.CENTER);// � ������ ��������� ������ ������
            filterPanel.add(panel1, BorderLayout.CENTER);
            yearFlag = true;// ����� ����
            
            // ������� ��� ��������� �����
            dateFlag = false;
            townFlag = false;
            categoryFlag = false;
            monthFlag = false;
            noneFlag = false;
            statusFlag = false;
        } else {
            categoryCombo.setSelectedIndex(0);// �������� ������ ������� � ������
            yearCombo.setSelectedIndex(0);
            monthCombo.setSelectedIndex(0);
        }
        // ������ ������ ������ �������, ���� ��� ������
        if(filterPanel.isVisible() == false) filterPanel.setVisible(true);
    }
    
    /**
     * ������ �������� ���������� ��� ������ ������� �� ��������� � ����
     */
    private void createFilter_CategoryDate(){
        // ��������� ��������� ����� �������� ��������� ���������� �����
        if(dateFlag == false){
            // �������� ������ ������ � ������� ��� �������� � ��
            dropFilterPanel();
            categoryCombo.setSelectedIndex(0);// �������� ������ ������� � ������
            /*
             ���� ����� �������� ���������� �� �����������, �� ������ �� �
             ��������� �� �� ������ ������
            */
            // ������ � ������ ��������� �� ������ ������ �����
            JPanel panel = new JPanel();
            
            // ������ ��������� ��� ���������� ���������
            Box vertBox = Box.createVerticalBox();
            vertBox.add(Box.createVerticalStrut(3));
            vertBox.add(new JLabel("���������", JLabel.LEFT));
            vertBox.add(Box.createVerticalStrut(5));
            vertBox.add(categoryCombo);
            
            Box vertBox1 = Box.createVerticalBox();
            
            // ���� ��� ����� ����
            txtFromField = new 
                JFormattedTextField(DateFormat.getDateInstance(DateFormat.MEDIUM));
            txtFromField.setValue(new Date());
            txtFromField.setColumns(10);
            txtToField = new 
                JFormattedTextField(DateFormat.getDateInstance(DateFormat.MEDIUM));
            txtToField.setColumns(10);
            txtToField.setValue(new Date());
            // ��������� ��������� ������ ������ �����
            txtFromField.addFocusListener(new FocusListener() {
                @Override
                public void focusLost(FocusEvent e) {
                    if(!txtFromField.isEditValid()){
                        // �������� �������� ���� � ���� �����
                        JOptionPane.showMessageDialog(ReportFrame.this, 
                                "�������� ���� ���� � ����", "AbonentGaz", JOptionPane.ERROR_MESSAGE);
//                        txtFromField.setFocusable(true);
                        
                    } else {
                        try {
                            // ���� ������� �����, ��������� � �� ��, ����� ��� �� ����
                            // ������ �������� ���� ������
                            txtFromField.commitEdit();
                        } catch (ParseException ex) {
                            Logger.getLogger(ReportFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        Date fromDate = (Date) txtFromField.getValue();
                        Date toDate = (Date) txtToField.getValue();
                        if(fromDate.after(toDate)){
                            JOptionPane.showMessageDialog(ReportFrame.this, 
                                    "��������� ���� ������ ��������", "AbonentGaz", JOptionPane.ERROR_MESSAGE);
                            txtFromField.setValue(txtToField.getValue());
                            try {
                                txtFromField.commitEdit();
                            } catch (ParseException ex) {
                                Logger.getLogger(ReportFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else
                            queryFilter.setDatefilter('"' + "����_�������" + '"' + " BETWEEN '" + 
                                txtFromField.getText() + "' AND '" + 
                                    txtToField.getText() + "'");
                    }
                }

                @Override
                public void focusGained(FocusEvent e) {
                    // ��� ��������� ������ �������� ���� ����� � ���� �����
                    txtFromField.setSelectionStart(0);
                    txtFromField.setSelectionEnd(txtFromField.getText().length() - 1);
                }
                
            });
            
            txtToField.addFocusListener(new FocusListener() {
                @Override
                public void focusLost(FocusEvent e) {
                    if(!txtToField.isValid()){
                        JOptionPane.showMessageDialog(ReportFrame.this, 
                                "�������� ���� ���� � ����", "AbonentGaz", JOptionPane.ERROR_MESSAGE);
//                        txtToField.setFocusable(true);
                        
                    } else {
                        try {
                            txtToField.commitEdit();
                        } catch (ParseException ex) {
                            Logger.getLogger(ReportFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        Date fromDate = (Date) txtFromField.getValue();
                        Date toDate = (Date) txtToField.getValue();
                        if(toDate.before(fromDate)){
                            JOptionPane.showMessageDialog(ReportFrame.this, 
                                    "��������  ���� ������ ���������", "AbonentGaz", JOptionPane.ERROR_MESSAGE);
                            txtToField.setValue(txtFromField.getValue());
                            try {
                                txtToField.commitEdit();
                            } catch (ParseException ex) {
                                Logger.getLogger(ReportFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else
                            queryFilter.setDatefilter('"' + "����_�������" + '"' + " BETWEEN '" + 
                                txtFromField.getText() + "' AND '" + 
                                    txtToField.getText() + "'");
                    }
                }

                @Override
                public void focusGained(FocusEvent e) {
                    // ��� ��������� ������ �������� ���� ����� � ���� �����
                    txtToField.setSelectionStart(0);
                    txtToField.setSelectionEnd(txtToField.getText().length() - 1);
                }
                
            });
            
            // ����� ��������� �������� ������� �� ����
            queryFilter.setDatefilter('"' + "����_�������" + '"' + " BETWEEN '" + 
                                txtFromField.getText() + "' AND '" + 
                                    txtToField.getText() + "'");
            
            
            // � ���� ��������� ��������� ������ ��� ���������/������ ������ �� ����
            final JCheckBox checkBox = new JCheckBox("������ �� ����", true);
            // ��������� � ���� ���������
            checkBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    // ��� ��������� ������ ���� ���� ������ ���������� � ��������
                    txtFromField.setEnabled(checkBox.isSelected());
                    txtToField.setEnabled(checkBox.isSelected());
                    if(!checkBox.isSelected())
                        queryFilter.setDatefilter("");
                    else
                        queryFilter.setDatefilter('"' + "����_�������" + '"' + " BETWEEN '" + 
                                txtFromField.getText() + "' AND '" + 
                                    txtToField.getText() + "'");
                }
            });
            Box horBox = Box.createHorizontalBox();
            // ��������� �� � ���������
            horBox.add(new JLabel("�"));
            horBox.add(Box.createHorizontalStrut(5));
            horBox.add(txtFromField);
            horBox.add(Box.createHorizontalStrut(15));
            horBox.add(new JLabel("��"));
            horBox.add(Box.createHorizontalStrut(5));
            horBox.add(txtToField);
            
            vertBox1.add(Box.createVerticalStrut(3));
            vertBox1.add(checkBox);
//            vertBox1.add(Box.createVerticalStrut(5));
            vertBox1.add(horBox);
            
            Box horBox1 = Box.createHorizontalBox();
            horBox1.add(vertBox);
            horBox1.add(Box.createHorizontalStrut(15));
            horBox1.add(vertBox1);
            
            panel.add(horBox1);
            JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
            panel1.add(okButton);
            panel1.add(closeButton);
            filterPanel.add(panel, BorderLayout.WEST);
//            filterPanel.add(new JPanel(), BorderLayout.CENTER);// � ������ ��������� ������ ������
            filterPanel.add(panel1, BorderLayout.CENTER);
            dateFlag = true;// ����� ����
            
            // ������� ��� ��������� �����
            categoryFlag = false;
            townFlag = false;
            yearFlag = false;
            monthFlag = false;
            noneFlag = false;
            statusFlag = false;
        } else {
            categoryCombo.setSelectedIndex(0);// �������� ������ ������� � ������
        }
        // ������ ������ ������ �������, ���� ��� ������
        if(filterPanel.isVisible() == false) filterPanel.setVisible(true);
    }
    
    /**
     * ������ �������� ���������� ��� ������ ������� �� ���������, ������ � �������
     */
    private void createFilter_CategoryDateTownStatus(){
        // ��������� ��������� ����� �������� ��������� ���������� �����
        if(statusFlag == false){
            // �������� ������ ������ � ������� ��� �������� � ��
            dropFilterPanel();
            categoryCombo.setSelectedIndex(0);// �������� ������ ������� � ������
            /*
             ���� ����� �������� ���������� �� �����������, �� ������ �� �
             ��������� �� �� ������ ������
            */
            // ���� ��� ����� ����
            txtFromField = new 
                JFormattedTextField(DateFormat.getDateInstance(DateFormat.MEDIUM));
            txtFromField.setValue(new Date());
            txtFromField.setColumns(10);
            txtToField = new 
                JFormattedTextField(DateFormat.getDateInstance(DateFormat.MEDIUM));
            txtToField.setColumns(10);
            txtToField.setValue(new Date());
            // ��������� ��������� ������ ������ �����
            txtFromField.addFocusListener(new FocusListener() {
                @Override
                public void focusLost(FocusEvent e) {
                    if(!txtFromField.isEditValid()){
                        // �������� �������� ���� � ���� �����
                        JOptionPane.showMessageDialog(ReportFrame.this, 
                                "�������� ���� ���� � ����", "AbonentGaz", JOptionPane.ERROR_MESSAGE);
//                        txtFromField.setFocusable(true);
                        
                    } else {
                        try {
                            // ���� ������� �����, ��������� � �� ��, ����� ��� �� ����
                            // ������ �������� ���� ������
                            txtFromField.commitEdit();
                        } catch (ParseException ex) {
                            Logger.getLogger(ReportFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        Date fromDate = (Date) txtFromField.getValue();
                        Date toDate = (Date) txtToField.getValue();
                        if(fromDate.after(toDate)){
                            JOptionPane.showMessageDialog(ReportFrame.this, 
                                    "��������� ���� ������ ��������", "AbonentGaz", JOptionPane.ERROR_MESSAGE);
                            txtFromField.setValue(txtToField.getValue());
                            try {
                                txtFromField.commitEdit();
                            } catch (ParseException ex) {
                                Logger.getLogger(ReportFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else
                            queryFilter.setDatefilter('"' + "����" + '"' + " BETWEEN '" + 
                                txtFromField.getText() + "' AND '" + 
                                    txtToField.getText() + "'");
                    }
                }

                @Override
                public void focusGained(FocusEvent e) {
                    // ��� ��������� ������ �������� ���� ����� � ���� �����
                    txtFromField.setSelectionStart(0);
                    txtFromField.setSelectionEnd(txtFromField.getText().length() - 1);
                }
                
            });
            
            txtToField.addFocusListener(new FocusListener() {
                @Override
                public void focusLost(FocusEvent e) {
                    if(!txtToField.isValid()){
                        JOptionPane.showMessageDialog(ReportFrame.this, 
                                "�������� ���� ���� � ����", "AbonentGaz", JOptionPane.ERROR_MESSAGE);
//                        txtToField.setFocusable(true);
                        
                    } else {
                        try {
                            txtToField.commitEdit();
                        } catch (ParseException ex) {
                            Logger.getLogger(ReportFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        Date fromDate = (Date) txtFromField.getValue();
                        Date toDate = (Date) txtToField.getValue();
                        if(toDate.before(fromDate)){
                            JOptionPane.showMessageDialog(ReportFrame.this, 
                                    "��������  ���� ������ ���������", "AbonentGaz", JOptionPane.ERROR_MESSAGE);
                            txtToField.setValue(txtFromField.getValue());
                            try {
                                txtToField.commitEdit();
                            } catch (ParseException ex) {
                                Logger.getLogger(ReportFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else
                            queryFilter.setDatefilter('"' + "����" + '"' + " BETWEEN '" + 
                                txtFromField.getText() + "' AND '" + 
                                    txtToField.getText() + "'");
                    }
                }

                @Override
                public void focusGained(FocusEvent e) {
                    // ��� ��������� ������ �������� ���� ����� � ���� �����
                    txtToField.setSelectionStart(0);
                    txtToField.setSelectionEnd(txtToField.getText().length() - 1);
                }
                
            });
            
            // ����� ��������� �������� ������� �� ����
            queryFilter.setDatefilter('"' + "����" + '"' + " BETWEEN '" + 
                                txtFromField.getText() + "' AND '" + 
                                    txtToField.getText() + "'");
            
            
            // ������ � ������ ��������� �� ������ ������ �����
            SprcityDaoImpl cdi = new SprcityDaoImpl();// ������ �� �������
            Object[] content = new Object[cdi.getCount() + 1];
            content[0] = "��� ������";
            for(int i = 0; i < cdi.getCount(); i++)
                content[i + 1] = cdi.getItem(i).getCityName();// �������� ������
            ComboBoxModel model = new DefaultComboBoxModel(content);
            townCombo = new JComboBox(model);
            // �������� ��� ������ �������
            townCombo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    if(townCombo.getSelectedIndex() == 0)
                        // ��� ������
                        queryFilter.setTownfilter("");
                    else
                        queryFilter.setTownfilter('"' + "�����" + '"' + "='" + 
                            townCombo.getSelectedItem().toString() + "'");
                }
            });
            townCombo.setSelectedIndex(0);// �������� ������ ������� � ������
            
            // ������ ������ ��� ������ ��������� ��� / ����
            final JCheckBox checkBox = new JCheckBox("��������");
            // ��������� ��������
            checkBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // ��� ��������� ������ ���� ���� ������ ���������� � ��������
                    txtFromField.setEnabled(checkBox.isSelected());
                    txtToField.setEnabled(checkBox.isSelected());
                    if(checkBox.isSelected()){
                        // ������ ����������
                        checkBox.setText("��������");
                        queryFilter.setStatusfilter('"' + "������" + '"' + 
                                "='���'");
                    } else {
                        checkBox.setText("���������");
                        queryFilter.setStatusfilter('"' + "������" + '"' + 
                                "='����'");
                    }
                }
            });
            checkBox.setSelected(true);
            
            Box vertBox = Box.createVerticalBox();// ������ ������������ ���������
            
            Box horBox1 = Box.createHorizontalBox();// ��������� ��� ������ ���������
            horBox1.add(new JLabel("���������"));
            horBox1.add(Box.createHorizontalStrut(5));
            horBox1.add(categoryCombo);
            
            // ��������� ��� ������ ��������� ������� � ������
            Box horBox2 = Box.createHorizontalBox();
            horBox2.add(new JLabel("��������� �����"));
            horBox2.add(townCombo);
            vertBox.add(horBox1);
            vertBox.add(Box.createVerticalStrut(5));
            vertBox.add(horBox2);
            
            // ��������� ��� ���������� ������ � ����� ��� ����� ����
            Box horBox = Box.createHorizontalBox();
            horBox.add(new JLabel("�"));
            horBox.add(Box.createHorizontalStrut(5));
            horBox.add(txtFromField);
            horBox.add(Box.createHorizontalStrut(10));
            horBox.add(new JLabel("��"));
            horBox.add(Box.createHorizontalStrut(5));
            horBox.add(txtToField);
            Box vertBox1 = Box.createVerticalBox();
            vertBox1.add(checkBox);
            vertBox1.add(horBox);
//            Box horBox2 = Box.createHorizontalBox();
            
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT,10, 0));
            panel.add(vertBox);
            panel.add(vertBox1);
            panel.add(okButton);
            panel.add(closeButton);
            filterPanel.add(panel, BorderLayout.WEST);
            filterPanel.add(new JPanel(), BorderLayout.CENTER);// � ������ ��������� ������ ������
            statusFlag = true;// ����� ����
            
            // ������� ��� ��������� �����
            dateFlag = false;
            townFlag = false;
            yearFlag = false;
            monthFlag = false;
            noneFlag = false;
            categoryFlag = false;
        } else {
            categoryCombo.setSelectedIndex(0);// �������� ������ ������� � ������
            townCombo.setSelectedIndex(0);
        }
        // ������ ������ ������ �������, ���� ��� ������
        if(filterPanel.isVisible() == false) filterPanel.setVisible(true);
    }
    
    /**
     * ������ �������� ���������� ��� ������ ������� �� ��������� � ������
     */
    private void createFilter_CategoryTown(){
        // ��������� ��������� ����� �������� ��������� ���������� �����
        if(townFlag == false){
            // �������� ������ ������ � ������� ��� �������� � ��
            dropFilterPanel();
            categoryCombo.setSelectedIndex(0);// �������� ������ ������� � ������
            /*
             ���� ����� �������� ���������� �� �����������, �� ������ �� �
             ��������� �� �� ������ ������
            */
            SprcityDaoImpl cdi = new SprcityDaoImpl();// ������ �� �������
            Object[] content = new Object[cdi.getCount() + 1];
            content[0] = "��� ������";
            for(int i = 0; i < cdi.getCount(); i++)
                content[i + 1] = cdi.getItem(i).getCityName();// �������� ������
            final JComboBox cityCombo = new JComboBox(content);// ������ � ��������� ������ �������
            // �������� ��� ������ �������
            cityCombo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    if(cityCombo.getSelectedIndex() == 0)
                        // ��� ������
                        queryFilter.setTownfilter("");
                    else
                        queryFilter.setTownfilter('"' + "�����" + '"' + "='" + 
                            cityCombo.getSelectedItem().toString() + "'");
                }
            });
            cityCombo.setSelectedIndex(0);// �������� ������ ������� � ������
            
            // ������ � ������ ��������� �� ������ ������ �����
            JPanel panel = new JPanel(new FlowLayout(10));
            panel.add(new JLabel("���������"));
            panel.add(categoryCombo);
            panel.add(new JLabel("          "));
            panel.add(new JLabel("��������� �����"));
            panel.add(cityCombo);
            panel.add(okButton);
            panel.add(closeButton);
            filterPanel.add(panel, BorderLayout.WEST);
            filterPanel.add(new JPanel(), BorderLayout.CENTER);// � ������ ��������� ������ ������
            townFlag = true;// ����� ����
            
            // ������� ��� ��������� �����
            dateFlag = false;
            categoryFlag = false;
            yearFlag = false;
            monthFlag = false;
            noneFlag = false;
            statusFlag = false;
        } else {
            categoryCombo.setSelectedIndex(0);// �������� ������ ������� � ������
            townCombo.setSelectedIndex(0);
        }
        // ������ ������ ������ �������, ���� ��� ������
        if(filterPanel.isVisible() == false) filterPanel.setVisible(true);
    }
    
    /**
     * ������ �������� ���������� ��� ������ ������� �� ��������� � ������
     */
    private void createFilter_CategoryMonth(){
        // ��������� ��������� ����� �������� ��������� ���������� �����
        if(monthFlag == false){
            // �������� ������ ������ � ������� ��� �������� � ��
            dropFilterPanel();
            categoryCombo.setSelectedIndex(0);// �������� ������ ������� � ������
            /*
             ���� ����� �������� ���������� �� �����������, �� ������ �� �
             ��������� �� �� ������ ������
            */
            // ������ � ������ ��������� �� ������ ������ �����
            JPanel panel = new JPanel(new GridLayout(2,2,5,5));
            panel.add(new JLabel("  ���������"));
            panel.add(categoryCombo);
            
            // ������ � ��������� ������ � ��������� ������� ����
            Object[] content = new Object[]{"������","�������","����","������","���",
                "����","����","������","��������","�������","������","�������"};
            monthCombo = new JComboBox(content);
            monthCombo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    queryFilter.setMonthfilter('"' + "�����" + '"' + "=" + 
                            (monthCombo.getSelectedIndex() + 1));
                    
                }
            });
            monthCombo.setSelectedIndex(0);// �������� ������ ������� � ������
            panel.add(new JLabel("  �����"));
            panel.add(monthCombo);
            
            JPanel panel1 = new JPanel();
            panel1.add(okButton);
            panel1.add(closeButton);
            filterPanel.add(panel, BorderLayout.WEST);
//            filterPanel.add(new JPanel(), BorderLayout.CENTER);// � ������ ��������� ������ ������
            filterPanel.add(panel1, BorderLayout.CENTER);
            monthFlag = true;// ����� ����
            
            // ������� ��� ��������� �����
            dateFlag = false;
            townFlag = false;
            categoryFlag = false;
            yearFlag = false;
            noneFlag = false;
            statusFlag = false;
        } else {
            categoryCombo.setSelectedIndex(0);// �������� ������ ������� � ������
            monthCombo.setSelectedIndex(0);
        }
        // ������ ������ ������ �������, ���� ��� ������
        if(filterPanel.isVisible() == false) filterPanel.setVisible(true);
    }
    
    /**
     * �������� ��������� ���������� ��� ��������� ������ ��� ������� ������
     */
    private void createFilterNone(){
        // ��������� ��������� ����� �������� ��������� ���������� �����
        if(noneFlag == false){
            // �������� ������ ������ � ������� ��� �������� � ��
            dropFilterPanel();
            
            /*
             ���� ����� �������� ���������� �� �����������, �� ������ �� �
             ��������� �� �� ������ ������
            */
            // ������ � ������ ��������� �� ������ ������ �����
            JPanel panel = new JPanel(new FlowLayout(10));
            panel.add(okButton);
            panel.add(closeButton);
            filterPanel.add(panel, BorderLayout.CENTER);
            filterPanel.add(new JPanel(), BorderLayout.WEST);// � ������ ��������� ������ ������
            noneFlag = true;// ����� ����
            
            // ������� ��� ��������� �����
            dateFlag = false;
            categoryFlag = false;
            yearFlag = false;
            monthFlag = false;
            townFlag = false;
            statusFlag = false;
        }
        queryFilter.dropFilter();
        // ������ ������ ������ �������, ���� ��� ������
        if(filterPanel.isVisible() == false) filterPanel.setVisible(true);
    }
    
    /**
     * �������� ������ �� ������������� � ��������� ��� �������
     * @param viewName ��� �������������
     */
    private void setData(String sqlQuery){
        runqueries.Runquery rq = new Runquery();
        List<Object[]> entities = rq.getQueryEntities(sqlQuery);
        // ��������� ������� ������� � ������
        if(entities.size() > 0){
            // ������ ����
            String[] columnName = rq.getColumnName();// �������� ������������ �����
            Class[] columnClass = rq.getColumnClass();// �������� ����� ������� ������
            Object[][] content = new Object[entities.size()][];// ������ ��� ������
            for(int i = 0; i < content.length; i++)
                content[i] = entities.get(i);// ��������� ������ ��� ������� � �������
            MyTableModel model = new MDIObject.MyTableModelImpl(content, columnName, columnClass);
            int[] colIndex = new int[columnName.length];// ������ ��������������� ��������
            for(int i = 0; i < colIndex.length; i++)
                colIndex[i] = i;
            // ��������� ������� �������
            MDIObject.fullTableData(colIndex, model, reportTable);

            // ������� ������ � ������� ������� �� ������
            TableCell_Renderer.setWordWrapCellRenderer(reportTable, columnClass);
        } else {
            // ������� ���
            JOptionPane.showMessageDialog(ReportFrame.this, 
                    "������� ������� �� �������.", "AbonentGaz", 
                    JOptionPane.INFORMATION_MESSAGE);
            setDefaultTableModel();
        }
    }
    
    private void setDefaultTableModel(){
        Object[][] content;
        String[] columnName;
        Class[] columnClass;
        MyTableModel model;
        int[] colIndex;
        // ������ ������� ��-���������
        columnName = new String[]{"A","B","C","D"};// �������� ������������ �����
        columnClass = new Class[]{Object.class,Object.class,Object.class,Object.class};// �������� ����� ������� ������
        content = new Object[][]{new Object[]{"","","",""}};// ������ ��� ������
        colIndex = new int[]{0,1,2,3};
        model = new MDIObject.MyTableModelImpl(content, columnName, columnClass);
        // ��������� ������� �������
        MDIObject.fullTableData(colIndex, model, reportTable);
    }
    
    class Views extends DefaultMutableTreeNode {
        private final String viewName;
        private final int index;
        
        public Views(String viewName, int index) {
            this.viewName = viewName;
            this.index = index;
        }

        public Views() {
            this.viewName = null;
            this.index = 0;
        }

        
        /**
         * @return the viewName
         */
        public String getViewName() {
            return viewName;
        }

        @Override
        public void setUserObject(Object userObject) {
            super.setUserObject(userObject); //To change body of generated methods, choose Tools | Templates.
        }

        /**
         * @return the index
         */
        public int getIndex() {
            return index;
        }
        
    }
    
    
    class QueryFilter {
        private String categoryfilter = "";
        private String datefilter = "";
        private String monthfilter = "";
        private String townfilter = "";
        private String yearfilter = "";
        private String statusfilter = "";
        private String stringfilter;

        public QueryFilter() {
        }

        /**
         * ����� �������� ������� �� ���������
         * @param categoryfilter the categoryfilter to set
         */
        public void setCategoryfilter(String categoryfilter) {
            this.categoryfilter = categoryfilter;
        }

        /**
         * ����� �������� ������� �� ����
         * @param datefilter the datefilter to set
         */
        public void setDatefilter(String datefilter) {
            this.datefilter = datefilter;
        }

        /**
         * ����� �������� ������� �� ������
         * @param monthfilter the monthfilter to set
         */
        public void setMonthfilter(String monthfilter) {
            this.monthfilter = monthfilter;
        }

        /**
         * ����� �������� ������� �� ��������� �������
         * @param townfilter the townfilter to set
         */
        public void setTownfilter(String townfilter) {
            this.townfilter = townfilter;
        }

        /**
         * ����� �������� ������� �� �����
         * @param yearfilter the yearfilter to set
         */
        public void setYearfilter(String yearfilter) {
            this.yearfilter = yearfilter;
        }

        /**
         * ����� �������� ������� �� ���������
         * @param statusfilter the statusfilter to set
         */
        public void setStatusfilter(String statusfilter) {
            this.statusfilter = statusfilter;
        }

        /**
         * ���������� ������ - ������ �������
         * @return the stringfilter
         */
        public String getStringfilter() {
            // �������� ������ - ������
            stringfilter = retFilter(categoryfilter) + retFilter(monthfilter) +
                    retFilter(yearfilter) + retFilter(datefilter) +
                    retFilter(townfilter) + retFilter(statusfilter);
            if(stringfilter.equals(""))
                return "";
            else
                return stringfilter = stringfilter.substring(7);

        }
        
        /**
         * ���������� �������� ������� �������
         */
        public void dropFilter(){
            categoryfilter = "";
            datefilter = "";
            monthfilter = "";
            townfilter = "";
            yearfilter = "";
            statusfilter = "";
            stringfilter = "";
        }
        
        /**
         * ���������� ��������� ����� ������ - �������
         * @param str ����� ������ - �������
         * @return ��������������� ����� ������ - �������
         */
        private String retFilter(String str){
            String retval;
            if(str.equals(""))
                retval = "";
            else
                retval = " AND A." + str;
            return retval;
        }
        
    }
    
//    private class WordWrapCellRenderer extends JTextArea implements TableCellRenderer {
//        
//        public WordWrapCellRenderer() {
//            super.setLineWrap(true);
//            super.setWrapStyleWord(true);
//            super.setOpaque(true);
//            
//        }
//
//        @Override
//        public Component getTableCellRendererComponent(JTable table, Object value, 
//                boolean isSelected, boolean hasFocus, int row, int column) {
////            System.out.println("row=" + row + " column=" + column + " value=" + value.toString());
//            setText(value.toString());
//            setFont(table.getFont());
//            setSize(table.getColumnModel().getColumn(column).getWidth(), getPreferredSize().height);
//            if (table.getRowHeight(row) != getPreferredSize().height) {
//                table.setRowHeight(row, getPreferredSize().height);
//            }
//            if(isSelected){
//                setBackground(table.getSelectionBackground());
//                setForeground(table.getSelectionForeground());
////                setBorder(new EtchedBorder(1));
//            } else {
//                setBackground(table.getBackground());
//                setForeground(table.getForeground());
////                setBorder(new BevelBorder(1));
//            }
//            return this;
//        }
//    }
}
