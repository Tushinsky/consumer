/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import abonentgaz.ColumnModelListener;
import abonentgaz.UserProperties;
import dao_impl.SprgazlinerdlowpressDaoImpl;
import dao_impl.SprgazlinerhipressDaoImpl;
import dao_impl.SprgazlinermidpressDaoImpl;
import dao_impl.SprgazlinerrlowpressDaoImpl;
import dao_impl.SprgazregulatorDaoImpl;
import dao_impl.TableDaoImpl;
import entities.Sprgazlinerdlowpress;
import entities.Sprgazlinerhipress;
import entities.Sprgazlinermidpress;
import entities.Sprgazlinerrlowpress;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import multispancell.AttributiveCellTableModel;
import multispancell.CellSpan;

/**
 *
 * @author Sergii.Tushinskyi
 */
public class PrevloseFrame extends JFrame {

    private JRadioButton gazButton;
    private JRadioButton regButton;
    private JTable table;
    private JPanel groupPanel;
    private JPanel gazPanel;
    private JPanel gazlinePanel;// ������ � ���������� ���������� ��� ������ ������������
    private JPanel gazregPanel;// ������ � ���������� ���������� ��� ������ �����������
    private CardLayout cardLO;// �������� ��������� ����������
    private JButton addButton;// ������ ���������� ����� ������ � �������
    private JButton removeButton;// ������ �������� ������� ������ �� �������
    private JButton calcButton;// ������ ������� ������
    private JButton prevButton;// ������ ���������������� ��������� ����
    private final GazLiner gazLiner;
    private final GazRegulator gazRegulator;
    private final List<Object[]> ResultLose;
    private final Object[] lineTitleContent = new Object[]{"�����������","�������, ��",
        "������, �3/(��� ��)","�����, ��","������� �� 25 ��� (��/���)",
        "������ ����-��� (���/���)","�����, �3/���"};// ��������� ��� ������������
    private final Object[] regTitleContent = new Object[]{"��� ����������","",
        "","","������, �3/���", "����������, ��","�����, �3/���"};// ��������� ��� �����������
    private boolean isLoseAdded = false;// ���� �� ���������� ����������� ������
    // ������ ������� � ���������� ������� ����������� �����
    private AttributiveCellTableModel attrModel;
    // ���������� ��� ���������� � �������
    private final Object[] tableContent;
    private JComboBox lineCombo;
    private JComboBox diametrCombo;
    private JTextField txtLose;// ���� ��� ������ ������
    private JCheckBox ageCheck;
    private JCheckBox territCheck;
    private JTextField txtLenght;
    private JTextField txtResult;
    private JComboBox regCombo;
    private JTextField txtRegLose;
    private JTextField txtCount;// ���� ��� ����� ���������� �����������
    private JTextField txtRegResult;
    private final String filename = "prevloseframe.properties";
    private ColumnModelListener columnLis6tener;
    
    public PrevloseFrame() throws HeadlessException {
        this.tableContent = new Object[7];
        setTitle("��������������� ������ ��������������� ������");// ��������� ����
        gazLiner = new GazLiner();
        gazRegulator = new GazRegulator();
        ResultLose = new ArrayList<>();
        // ������ ���������� ����������������� ����������
        initComponents();
        
        // ���������� ������� ������ � ����� ������� ����� � �������� ������
        Toolkit kit = getToolkit();
        Dimension size = kit.getScreenSize();
        setSize((int)size.getWidth() / 2, (int)size.getHeight() / 2);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    /**
     * ������������� ����������� ����������������� ����������,
     * ���� ����� ������������ �����������,
     * ���� ����� ������������ � ������ �������,
     * ���� ����� �������� ������ ��� ������� ������,
     * ������������� ��� ������ ������������ ��� �����������,
     * ������ ��� ������ ����� ������������ ��� �����������,
     * ������ ������ ��������� ������������,
     * ������ ��� ������� �������� ������������ � ��������������,
     * ���� ��� ������ ������,
     * ������ ������� ������, ���������� ����� ������ � �������,
     * �������� ������ �� �������, ����������������� ���������,
     * ������� ��� ������ ������,
     * ���� ����� ����� ����������� ��� ���������� �����������
     */
    private void initComponents() {
        // ������������, ������, �������� ������
        // ��������� �� �� �������
        Box box = Box.createHorizontalBox();
        box.add(Box.createHorizontalStrut(10));
        box.add(createLabelNamePanel());
        box.add(Box.createHorizontalStrut(10));
        box.add(createTextNamePanel());
        box.add(Box.createHorizontalGlue());
        box.add(Box.createHorizontalStrut(10));
//        box.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        
        // ������ ��� ���������� �������������� � ��������� ���������� ��� ������
        // ������������ ��� �����������
        groupPanel = new JPanel(new BorderLayout(5, 5));
//        groupPanel.add(groupBox, BorderLayout.WEST);
        gazPanel = new JPanel();
        cardLO = new CardLayout();// �������� ��������� ����������
        gazPanel.setLayout(cardLO);// ��������� ��� ��� ������
        gazregPanel = createGazRegPanel();
        gazlinePanel = createGazLinePanel();
        gazPanel.add(gazlinePanel, "GazLiner");
        gazPanel.add(gazregPanel, "Regulator");
        
        // �������������
        Box groupBox = createRadioButtonBox();
        
        // ������
        JPanel buttonPanel = createCommandButtonPanel();// ������ ��� ���������� ������
        
        Box horbox = Box.createHorizontalBox();
        horbox.add(Box.createHorizontalStrut(5));
        horbox.add(groupBox);
        horbox.add(Box.createHorizontalStrut(10));
//        horbox.add(Box.createHorizontalGlue());
        horbox.add(gazPanel);
        horbox.add(Box.createHorizontalStrut(10));
        horbox.add(Box.createHorizontalGlue());
        horbox.add(buttonPanel);
        groupPanel.add(horbox, BorderLayout.WEST);
        groupPanel.add(Box.createHorizontalGlue(), BorderLayout.EAST);
        groupPanel.setBorder(new LineBorder(Color.BLACK));
        
        // ������� ��� ������ ������
        attrModel = new AttributiveCellTableModel(1, 7);
        table = new JTable();
        table.setModel(attrModel);
        setColumnIdentifiers();// ����� �������������� �������� �������
        table.setCellSelectionEnabled(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane scrollpane = new JScrollPane(table);
        // ��������� ������ �������� ������� �� ����� �������
        UserProperties props = new UserProperties(filename);
        MDIObject.setTablecolwidth(props, "colwidth", table);
        // ��������� ��������� �������� �������� �������
        columnLis6tener = new ColumnModelListener(table, props, "colwidth");
        table.getColumnModel().addColumnModelListener(columnLis6tener);
        
        // ������� ������ �����������
        Box mainBox = Box.createVerticalBox();
        mainBox.add(Box.createVerticalStrut(10));
        mainBox.add(box);
        mainBox.add(Box.createVerticalStrut(10));
        mainBox.add(groupPanel);
        mainBox.add(Box.createVerticalStrut(10));
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(mainBox, BorderLayout.NORTH);
        mainPanel.add(scrollpane, BorderLayout.CENTER);
        getContentPane().add(mainPanel);// ��������� � �� ������ ����������� �����
        
        gazButton.doClick();
        
    }
    
    /**
         * ����� �������������� ��� �������� �������
         */
        private void setColumnIdentifiers(){
            TableColumnModel colmodel = table.getColumnModel();
            for (int i = 0; i < colmodel.getColumnCount(); i++) {
                TableColumn tc = colmodel.getColumn(i);
                tc.setIdentifier((Object) i);
            }
        }
    
    /**
     * ����� �������� ��� ������������� ����������
     * @return ��������� ��������� �������� ��� �������������
     */
    private ActionListener regButtonListener(){
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                cardLO.show(gazPanel, "Regulator");
                
                // ����� �������� ������ ��� �������, ������� ����� ����������� �������
                tableContent[0] = regCombo.getSelectedItem();// ������������
                tableContent[1] = "";// ��� ������
                tableContent[2] = "";// ����� ������������
                tableContent[3] = "";// � ������
                tableContent[4] = txtRegLose.getText();// ������
                tableContent[5] = txtCount.getText();// ����������
                tableContent[6] = txtRegResult.getText();// ����� ������
                
                // ��������� ��������� ����� �� ���������� ������
                testLoseAdded(regTitleContent);
                
                // ���������� ������ � ���������� ����������
                CellSpan cellAtt;
                cellAtt = (CellSpan)attrModel.getCellAttribute();
                int[] row = new int[]{table.getRowCount() - 1};
                int[] cols = new int[]{0,1,2,3};
                cellAtt.combine(row, cols);
                table.revalidate();
                table.repaint();
                // ���������� ����
                isLoseAdded = false;
            }
        };
        return listener;
    }
    
    /**
     * ����� �������� ��� ������������� �����������
     * @return ��������� ��������� �������� ��� �������������
     */
    private ActionListener lineButtonListener(){
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                cardLO.show(gazPanel, "GazLiner");// ���������� ������
                
                // ����� �������� ������ ��� �������, ������� ����� ����������� �������
                tableContent[0] = lineCombo.getSelectedItem();// ������������
                tableContent[1] = diametrCombo.getSelectedItem();// �������
                tableContent[2] = txtLose.getText();// ������
                tableContent[3] = txtLenght.getText();// �����
                tableContent[4] = ageCheck.isSelected() ? "��" : "���";// �������
                tableContent[5] = territCheck.isSelected() ? "��" : "���";// ����������
                tableContent[6] = txtResult.getText();// ����� ������
                
                // ��������� ��������� ����� �� ���������� ������
                testLoseAdded(lineTitleContent);
                // ������� ����������� �����
                // ���������� ������ � ���������� ����������
                CellSpan cellAtt;
                cellAtt = (CellSpan)attrModel.getCellAttribute();
                cellAtt.split(table.getRowCount() - 1, 1);
                table.revalidate();
                table.repaint();
                // ���������� ����
                isLoseAdded = false;
            }
        };
        return listener;
    }
    
    /**
     * ������ �������� ����������������� ����������
     * @return ������ � ���������� ����������������� ����������
     */
    private JPanel createLabelNamePanel(){
        // ������������, ������, �������� ������
        JLabel orgLabel = new JLabel("������������ �����������");
        orgLabel.setMaximumSize(orgLabel.getPreferredSize());
        JLabel objLabel = new JLabel("������������ � ����� �������");
        objLabel.setMaximumSize(objLabel.getPreferredSize());
        JLabel dataLabel = new JLabel("�������� ������");
        dataLabel.setMaximumSize(dataLabel.getPreferredSize());
        // ��������� �� �� �������
        JPanel namePanel = new JPanel(new GridLayout(3, 1, 0, 5));
        namePanel.add(orgLabel);
        namePanel.add(objLabel);
        namePanel.add(dataLabel);
//        namePanel.setBorder(BorderFactory.createLineBorder(Color.yellow, 2));
        namePanel.setMaximumSize(namePanel.getPreferredSize());
        return namePanel;
    }
    
    /**
     * ������ �������� ����������������� ����������
     * @return ������ � ���������� ����������������� ����������
     */
    private JPanel createTextNamePanel(){
        // ������������, ������, �������� ������
        final JTextField txtOrg = new JTextField(70);
        final JTextField txtObject = new JTextField(70);
        final JTextField txtData = new JTextField(70);
        
        // ��������� �� �� �������
        JPanel txtNamePanel = new JPanel(new GridLayout(3, 1, 0, 5));
        txtNamePanel.add(txtOrg);
        txtNamePanel.add(txtObject);
        txtNamePanel.add(txtData);
//        txtNamePanel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
        return txtNamePanel;
    }
    
    /**
     * ������ ������ � ��������������� � ���������� ��� ��� ��������
     * @return ��������� ������ � ���������������
     */
    private Box createRadioButtonBox(){
        // �������������
        ButtonGroup btnGroup = new ButtonGroup();
        gazButton = new JRadioButton("�����������");
        regButton = new JRadioButton("����������");
        
        // ���������� ��������
        gazButton.addActionListener(lineButtonListener());
        regButton.addActionListener(regButtonListener());
        
        
        // ���������� �� � ��������� � ���������
        btnGroup.add(gazButton);
        btnGroup.add(regButton);
        Box groupBox = Box.createVerticalBox();
        groupBox.add(Box.createVerticalStrut(5));
        groupBox.add(gazButton);
//        groupBox.add(Box.createVerticalStrut(5));
        groupBox.add(regButton);
        groupBox.add(Box.createVerticalGlue());
//        groupBox.setBorder(new LineBorder(Color.yellow));
        return groupBox;
    }
    
    /**
     * ������ ������ � �������� � ���������� ��� ��� ��������
     * @return ��������� ������ � ��������
     */
    private JPanel createCommandButtonPanel(){
        // ������
        addButton = new JButton("��������");
         // ��������� ��������� ��� ������ addButton
        addButton.addActionListener(AddListener());
        
        removeButton = new JButton("�������");
        removeButton.addActionListener(RemoveListener());
        
        calcButton = new JButton("������");
        calcButton.addActionListener(CalcListener());
        
        prevButton = new JButton("��������");
        prevButton.addActionListener(PreviewListener());
        
        JPanel buttonPanel = new JPanel(new GridLayout(4, 2, 5, 5));// ������ ��� ���������� ������
        buttonPanel.add(addButton);
        buttonPanel.add(Box.createHorizontalBox());
        buttonPanel.add(removeButton);
        buttonPanel.add(Box.createHorizontalBox());
        buttonPanel.add(calcButton);
        buttonPanel.add(Box.createHorizontalBox());
        buttonPanel.add(prevButton);
        buttonPanel.add(Box.createHorizontalBox());
        return buttonPanel;
    }
    
    /**
     * ������ ������ � ���������� ����������������� ���������� ��� ������ ������������
     * @return ��������� ������ � ���������� ����������
     */
    private JPanel createGazLinePanel(){
        /*
        ������ ������ � ��������� ����� ������������, ������ ��� ������ ���������,
        ������ ��� ��������� ��� ������ �������� �����������, ��������������� ����������
        ���� ����� ��� ����� �����������
        */
        gazLiner.setAge(true);// ������ ����� ��������
        gazLiner.setTerritory(false);// � ���������� �����������
        JLabel lineLabel = new JLabel("�����������");
        lineCombo = new JComboBox(new Object[]{"�������� ��������",
            "�������� ��������","������������������ ������� ��������",
            "�������� ������� ��������"});
        lineCombo.setToolTipText("�������� ��� �����������");
        JLabel diameterLabel = new JLabel("�������");
        diametrCombo = new JComboBox();
        diametrCombo.setToolTipText("�������� ������� �����������");
        txtLose = new JTextField(5);// ���� ��� ������ ������
        txtLose.setEnabled(false);// ������ ��� ����������� ��� ��������������
        JLabel loseLabel = new JLabel("������, � ���/(��� ��)");
        ageCheck = new JCheckBox("�� 25 ��� (��/���)", true);
        territCheck = new JCheckBox("������ ������ (��/���)", false);
        JLabel lenghtLabel = new JLabel("�����");
        txtLenght = new JTextField("0", 5);
        txtLenght.setToolTipText("����� �����������, � �������� ����������� �������� ����������� �����");
        JLabel resultLabel = new JLabel("�����, � ���/���");
        resultLabel.setPreferredSize(loseLabel.getPreferredSize());
        txtResult = new JTextField("0", 10);// ���� ��� ������ ���������� �������� ������
        txtLenght.setMaximumSize(txtLenght.getPreferredSize());
        txtResult.setMaximumSize(txtResult.getPreferredSize());
        
        // ����� ���������� ��� �������
        lineCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // ��� ������ �������� ������ ������������ ��������� ������ ���������
                tableContent[0] = lineCombo.getSelectedItem();// ������������ �����������
                gazLiner.setLineIndex(lineCombo.getSelectedIndex());
                ComboBoxModel model = new DefaultComboBoxModel(gazLiner.diameter());
                diametrCombo.setModel(model);
                diametrCombo.setSelectedIndex(0);
            }
        });
        lineCombo.setSelectedIndex(0);
        diametrCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // ��� ������ �������� ������ ��������� ��������� ���� ����� ������
                String lose = gazLiner.getLose(diametrCombo.getSelectedIndex() + 1);
                txtResult.setText(gazLiner.getResultLose().toPlainString());
                txtLose.setText(lose);
                tableContent[1] = diametrCombo.getSelectedItem();// ������� �����������
                tableContent[2] = lose;// ������ �� �����������
                tableContent[6] = txtResult.getText();// ��������� ������ �� �����������
            }
        });
        diametrCombo.setSelectedIndex(0);
        
        // ��������� ��������� ��� �������
        ageCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                gazLiner.setAge(ageCheck.isSelected());
                txtResult.setText(gazLiner.getResultLose().toPlainString());
                tableContent[6] = txtResult.getText();// ��������� ������ �� �����������
                tableContent[4] = ageCheck.isSelected() ? "��" : "���";// ���� �������� �����������
            }
        });
        territCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                gazLiner.setTerritory(territCheck.isSelected());
                txtResult.setText(gazLiner.getResultLose().toPlainString());
                tableContent[6] = txtResult.getText();// ��������� ������ �� �����������
                tableContent[5] = territCheck.isSelected() ? "��" : "���";// ���� ���������� �����������
            }
        });
        
        // ��������� ���������� ��� ���� ����� ����� �����������
        txtLenght.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                super.keyReleased(ke);
                // ��� ������� ������� ����� ��������� ������ ������
                if(ke.getKeyCode() == KeyEvent.VK_ENTER){
                    // ����������� ������, �������������� �����, � ���� BigDecimal
                    BigDecimal lenght = BigDecimal.valueOf(Double.valueOf(txtLenght.getText()));
                    gazLiner.setLenght(lenght);
                    txtResult.setText(gazLiner.getResultLose().toPlainString());
                    tableContent[6] = txtResult.getText();// ��������� ������ �� �����������
                    tableContent[3] = lenght;// �����
                }
            }
            
        });
        
        
        
        // ������ ������, ��������� �������� - ����� � ������
        Box fBox = Box.createVerticalBox();
        fBox.add(lineLabel);
        fBox.add(Box.createVerticalStrut(15));
        fBox.add(diameterLabel);
        fBox.add(Box.createVerticalStrut(15));
        fBox.add(lenghtLabel);
        
        Box sBox = Box.createVerticalBox();
        sBox.add(lineCombo);// ������ ������������
        sBox.add(Box.createVerticalStrut(10));
        Box horBox = Box.createHorizontalBox();
        horBox.add(diametrCombo);// ������ ��������� ������������
        horBox.add(Box.createHorizontalStrut(10));
        horBox.add(loseLabel);
        horBox.add(Box.createHorizontalStrut(5));
        horBox.add(txtLose);// ���� ������ ������ �� ��������� �����������
        Box hBox = Box.createHorizontalBox();
        hBox.add(txtLenght);// ���� ��� ����� ����� �����������
        hBox.add(Box.createHorizontalStrut(13));
        hBox.add(resultLabel);
        hBox.add(Box.createHorizontalStrut(5));
        hBox.add(txtResult);// ���� ��� ������ ���������� ���������� ������
        hBox.add(Box.createHorizontalGlue());
        //hBox.add(Box.createHorizontalStrut(5));
        sBox.add(horBox);
        sBox.add(Box.createVerticalStrut(10));
        sBox.add(hBox);
        
        Box horBox_1 = Box.createHorizontalBox();
        horBox_1.add(fBox);
        horBox_1.add(Box.createHorizontalStrut(5));
        horBox_1.add(sBox);
        
        // ������ ������, ��������� ������ � ����� � ����� ����� �����
        JPanel threePanel = new JPanel(new GridLayout(2, 1, 0, 5));
        threePanel.add(ageCheck);
        threePanel.add(territCheck);
        
        Box box = Box.createHorizontalBox();
        box.add(Box.createHorizontalGlue());
        box.add(horBox_1);
        box.add(Box.createHorizontalStrut(15));
        box.add(threePanel);
        
        // ������ �������� ������
        JPanel mainPanel = new JPanel();
        mainPanel.add(box);
        return mainPanel;
    }
    
    /**
     * ������ ������ � ���������� ����������������� ���������� ��� ������ �����������
     * @return ��������� ������ � ���������� ����������
     */
    private JPanel createGazRegPanel(){
        /*
        ������ ������ � ������ �����������
        ���� ����� ��� ����������� ������
        ���� ��� ����� ���������� �����������
        */
        regCombo = new JComboBox(gazRegulator.regNames());
        JLabel regLabel = new JLabel("���� �����������");
        JLabel loseLabel = new JLabel("������, � ���/���");
        txtRegLose = new JTextField(5);
        JLabel countLabel = new JLabel("����������");
        txtCount = new JTextField("0", 5);// ���� ��� ����� ���������� �����������
        JLabel resultLabel = new JLabel("�����, � ���/���");
        txtRegResult = new JTextField(10);// ���� ��� ������ ����������� �������
        txtRegResult.setMaximumSize(txtRegResult.getPreferredSize());
        gazRegulator.setRegcount(Integer.parseInt(txtCount.getText()));
        
        // � ������ ��������� ���������
        regCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                tableContent[0] = regCombo.getSelectedItem();// ������������ ����������
                String lose = gazRegulator.getLose(regCombo.getSelectedIndex());
                txtRegResult.setText(gazRegulator.getResultLose().toPlainString());
                txtRegLose.setText(lose);
                tableContent[4] = lose;// ������ �� ����������
                tableContent[5] = txtCount.getText();// ���������� �����������
                tableContent[6] = txtRegResult.getText();// ����� ��������� ������
            }
        });
        regCombo.setSelectedIndex(0);// ������ ������� � ������
        
        // ��������� ���������� ��� ���� ����� ����������
        txtCount.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                super.keyReleased(ke);
                // ��� ������� ������� ����� ��������� ������ ������
                if(ke.getKeyCode() == KeyEvent.VK_ENTER){
                    gazRegulator.setRegcount(Integer.parseInt(txtCount.getText()));
                    txtRegResult.setText(gazRegulator.getResultLose().toPlainString());
                    tableContent[5] = txtCount.getText();// ���������� �����������
                    tableContent[6] = txtRegResult.getText();// ����� ��������� ������
                }
            }
            
        });
        // ������ ��������� ����� �� �������
        Box regBox = Box.createVerticalBox();
        regBox.add(regLabel);
        regBox.add(Box.createVerticalStrut(15));
        regBox.add(loseLabel);
        regBox.add(Box.createVerticalStrut(15));
        regBox.add(countLabel);
        regBox.add(Box.createVerticalStrut(10));
        
        // ����� ��������� ����� � ���� �����
        Box txtBox = Box.createVerticalBox();
        txtBox.add(Box.createVerticalStrut(10));
        txtBox.add(regCombo);
        txtBox.add(Box.createVerticalStrut(10));
        JPanel losePanel = new JPanel(new BorderLayout());
        losePanel.add(txtRegLose, BorderLayout.WEST);
        losePanel.add(new JPanel(), BorderLayout.CENTER);
        txtBox.add(losePanel);
        txtBox.add(Box.createVerticalStrut(10));
        JPanel countPanel = new JPanel(new BorderLayout());
        countPanel.add(txtCount, BorderLayout.WEST);
        Box b = Box.createHorizontalBox();
        b.add(Box.createHorizontalStrut(15));
        b.add(resultLabel);
        b.add(Box.createHorizontalStrut(5));
        b.add(txtRegResult);
        b.add(Box.createHorizontalGlue());
        b.add(Box.createHorizontalStrut(15));
        countPanel.add(b, BorderLayout.CENTER);
        txtBox.add(countPanel);
        txtBox.add(Box.createVerticalStrut(20));
        
        Box box = Box.createHorizontalBox();
        box.add(Box.createHorizontalStrut(20));
        box.add(regBox);
        box.add(Box.createHorizontalStrut(20));
        box.add(txtBox);
        box.add(Box.createHorizontalGlue());
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(box, BorderLayout.CENTER);
        mainPanel.add(new JPanel(), BorderLayout.EAST);
        return mainPanel;
    }
    
    /**
     * ���������� ��������� ��� ������ addButton ��� ���������� ������ �� �����������
     * @return ���������
     */
    private ActionListener AddListener() {
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // ��������� ��������� ������ �� ����������� � ���������
                isLoseAdded = true;// ������������� ���� �� ���������� ������
                testLoseAdded(tableContent);
                // ������, ���������� ����� ����������� ������ � ������� � ����������� ������
                Object[] content = new Object[]{table.getRowCount() - 1, tableContent[6]};
                ResultLose.add(content);
                MDIObject.getInformDialog(PrevloseFrame.this, "���������...", InformDialog.InformType.SAVING);
            }
        };
        return listener;
    }
    
    private ActionListener CalcListener() {
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // ��������� ������ ��������������� ������
                // ��������� ������� ��������� � ���������
                if(ResultLose.size() > 0){
                    System.out.println("size=" + ResultLose.size());
                    BigDecimal result = BigDecimal.ZERO;// ��������� �������� ����������
                    for (Object[] ResultLose1 : ResultLose) {
                        double addition = Double.parseDouble((String) ResultLose1[1]);
                        result = result.add(BigDecimal.valueOf(addition));
                    }
                    // ��������� �������������� ������ � �������
                    Object[] content = new Object[7];
                    testLoseAdded(content);
                    // ��������� ������ � ������������ �������
                    content[0] = "����� ������, � ���/���";
                    content[6] = result.toPlainString();
                    testLoseAdded(content);
                    
                    JOptionPane.showMessageDialog(PrevloseFrame.this, 
                            "������ ��������� " + result.toPlainString() + " � ���/���", 
                            "AbonentGaz", JOptionPane.INFORMATION_MESSAGE);
                    
                } else
                    JOptionPane.showMessageDialog(PrevloseFrame.this, 
                            "��� ������ ��� �������!", 
                            "AbonentGaz", JOptionPane.ERROR_MESSAGE);
            }
        };
        return listener;
    }
    
    /**
     * ���������� ��������� ��� ������ removeButton, ��������� ��������� ��� ������ �� ���������
     * @return ��������� ���������
     */
    private ActionListener RemoveListener() {
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // ������� ��������� ������ � �������
                if(JOptionPane.showConfirmDialog(PrevloseFrame.this, 
                        "������� ��������� ������?", "AbonentGaz", 
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                    /*
                    ��������� ������ ������, ������� ��������� : ���� ������
                    ����� ���������� �����, �� ���������� ������������, ��� ��� ������
                    ������� ������
                    */
                    int row = table.getSelectedRow();// ������ ��������� ������
                    if(table.getRowCount() != 1){
                        // ���� ������ �� ����� ���������� ����� � ���������� ����� �� ����� !
                        // �.�. � ������� ������ ���� ������
                        DefaultTableModel model = (DefaultTableModel) table.getModel();
                        model.removeRow(row);// ������� ������ �� ������
                        // ������� ��������������� ������� ������ �  �������� ������� ����� � ��������� ���������
                        int j = 0;
                        for (int i = 0; i < ResultLose.size(); i++) {
                            // ��������� ����� ������ � �������� ������
                            if((int)ResultLose.get(i)[0] == row){
                                ResultLose.remove(i);
                                j = i;// ���������� ������ ���������� ��������
                                break;// ����� �� �����
                            }
                        }
                        // �������� ������� ���������, ���������� �� ���������
                        for (int i = j; i < ResultLose.size(); i++){
                            int rownumber = (int)ResultLose.get(i)[0];
                            ResultLose.get(i)[0] = rownumber - 1;
                        }
                    } else {
                        // ���� � ������� ������ ���� ������, �� ������� ���������
                        JOptionPane.showMessageDialog(PrevloseFrame.this, 
                                "������ ������� ��������� ������ � �������!");
                    }
                }
            }
        };
        return listener;
    }
    
    /**
     * ���������� ��������� ��� ������ prevButton, ����������� ���� ���������������� ���������
     * @return ��������� ���������
     */
    private ActionListener PreviewListener() {
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        return listener;
    }
    
    /**
     * ���������� ��������� ����� �� ���������� ������ � 
     * @param rowContent 
     */
    private void testLoseAdded(Object[] rowContent){
        // ��������� ��������� ����� �� ���������� ������
        if(isLoseAdded == false){
            /*
            ������ ��� �� �����������, ������� �������� ����������
            ������� ������ �������
            */
            int row = table.getRowCount() - 1;// ����� ������������� ������
            for(int i = 0; i < table.getColumnCount(); i++)
                table.setValueAt(rowContent[i], row, i);
        } else {
            /*
            ������ �����������, ������� ��������� ����� ������
            � ������� � ���������� ���� �� ���������� ������
            */
            attrModel.addRow(new Vector(7));
            int row = table.getRowCount() - 1;// ����� ������������� ������
            for(int i = 0; i < table.getColumnCount(); i++)
                table.setValueAt(rowContent[i], row, i);
        }

    }
    
    /**
     * �����, ��������������� ���������� �� ������� �� ��������� ������������
     */
    private class GazLiner {

        /**
         * @return the lenght
         */
        public BigDecimal getLenght() {
            return lenght;
        }

        /**
         * @param lenght the lenght to set
         */
        public void setLenght(BigDecimal lenght) {
            this.lenght = lenght;
        }

        /**
         * @return the age
         */
        public boolean isAge() {
            return age;
        }

        /**
         * @param age the age to set
         */
        public void setAge(boolean age) {
            this.age = age;
        }

        /**
         * @return the territory
         */
        public boolean isTerritory() {
            return territory;
        }

        /**
         * @param territory the territory to set
         */
        public void setTerritory(boolean territory) {
            this.territory = territory;
        }

        /**
         * ���������� ��������� ������ �� ����������� ���������� �������� � �����
         * @return the resultLose
         */
        public BigDecimal getResultLose() {
            calculateLose();
            return resultLose;
        }
        
        private void calculateLose(){
            BigDecimal Lose = linelose.multiply(lenght);// �������� ������ � ����������� �� ����� �����������
            BigDecimal delta = Lose.multiply(BigDecimal.valueOf(0.25));// 25% �� ������
            resultLose = Lose;// ��������� �������� ����������
            // ��������� ����� �������� � ����������
            if(age == false) resultLose = resultLose.add(delta);
            if(territory == true) resultLose = resultLose.add(delta);
        }

        private int lineIndex;
        private TableDaoImpl tdi;
        private BigDecimal lenght = BigDecimal.ZERO;
        private boolean age = false;
        private boolean territory = true;
        private BigDecimal resultLose = BigDecimal.ZERO;
        private BigDecimal linelose = BigDecimal.ZERO;
        
        public GazLiner() {
            tdi = null;
        }
        
        public Object[] diameter(){
            
            switch(lineIndex){
                case 0:// ����������� �������� ��������
                    tdi = new SprgazlinerhipressDaoImpl();
                    break;
                case 1:// ����������� �������� ��������
                    tdi = new SprgazlinermidpressDaoImpl();
                    break;
                case 2:// ����������� ����������������� ������� ��������
                    tdi = new SprgazlinerrlowpressDaoImpl();
                    break;
                case 3:// ����������� �������� ������� ��������
                    tdi = new SprgazlinerdlowpressDaoImpl();
                    break;
            }
            Object[] content = new Object[tdi.getCount()];// ������ �����������
            // ��������� ������ �����������
            for(int i = 0; i < content.length; i++){
//                Object[] array = tdi.getItem(i).toDataArray();
                content[i] = tdi.getItem(i).toString();// ������� �����������
            }
            return content;
        }
        
        public String getLose(int index){
            switch(lineIndex){
                case 0:
                    Sprgazlinerhipress hte = new Sprgazlinerhipress(index);
                    linelose = hte.getLose();
                    break;
                case 1:
                    Sprgazlinermidpress mte = new Sprgazlinermidpress(index);
                    linelose = mte.getLose();
                    break;
                case 2:
                    Sprgazlinerrlowpress rte = new Sprgazlinerrlowpress(index);
                    linelose = rte.getLose();
                    break;
                case 3:
                    Sprgazlinerdlowpress dte = new Sprgazlinerdlowpress(index);
                    linelose = dte.getLose();
                    break;
            }
            return linelose.toPlainString();
        }

        /**
         * @param lineIndex the lineIndex to set
         */
        public void setLineIndex(int lineIndex) {
            this.lineIndex = lineIndex;
        }
        
    }
    
    private class GazRegulator {

        private final SprgazregulatorDaoImpl sgr;
        private int regcount = 0;
        private BigDecimal reglose;
        private BigDecimal resultLose;

        public GazRegulator() {
            this.reglose = BigDecimal.ZERO;
            sgr = new SprgazregulatorDaoImpl();
        }

        /**
         * ���������� ��������� ������������� ������ �� ��������� ����������
         * @param index ������ ���������� �� �����������
         * @return the linelose ��������� ������������� ������
         */
        public String getLose(int index) {
            reglose = sgr.getItem(index).getLose();
            return reglose.toPlainString();
        }
        
        /**
         * ���������� ������ � �������������� �����������
         * @return ������, ���������� ������������ ����������� �� �����������
         */
        public Object[] regNames(){
            Object[] content = new Object[sgr.getCount()];
            // �������� ������
            for(int i = 0; i < content.length; i++){
                content[i] = sgr.getItem(i).getName();
            }
            return content;
        }

        /**
         * @return the regcount
         */
        public int getRegcount() {
            return regcount;
        }

        /**
         * @param regcount the regcount to set
         */
        public void setRegcount(int regcount) {
            this.regcount = regcount;
        }

        /**
         * @return the reglose
         */
        public BigDecimal getReglose() {
            return reglose;
        }

        /**
         * @return the resultLose
         */
        public BigDecimal getResultLose() {
            calculateLose();
            return resultLose;
        }
        
        private void calculateLose(){
            resultLose = reglose.multiply(BigDecimal.valueOf(regcount));
        }
    }
}
