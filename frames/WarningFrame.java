/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import dao_impl.SprcategoryDaoImpl;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Sergii.Tushinskyi
 */
public class WarningFrame extends JFrame {

    private JTable table;// ������� ��� ������ ������� ����������� 
    private JComboBox categoryCombo;// ������ ��������� �����������
    private JComboBox warningCombo;// ������ ����� �����������
    private JLabel contentLabel;// ����� ��� ������ ��������� ���������������� ����� �����������
    
    public WarningFrame() throws HeadlessException {
        setTitle("�����������");// ��������� ����
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
     * ������������� ����������� ����������������� ����������
     */
    private void initComponents(){
        // ������ ����
        addMenuItem();
        
        JPanel mainPanel = new JPanel(new BorderLayout());// ������� ������ ��� ������������ �����������
        JPanel comboPanel = new JPanel(new BorderLayout());// �� ���� ������ ��������� ������ � �����
        comboPanel.setBorder(new TitledBorder(new LineBorder(Color.DARK_GRAY, 1), "�����������"));
        Box listBox = Box.createHorizontalBox();
        JLabel lblCategory = new JLabel("���������");
        JLabel lblType  = new JLabel("��� �����������");
        
        categoryCombo = new JComboBox(categoryModel());
        // ��������� ���������
        categoryCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                contentLabel.setText(getContent());
            }
        });
        
        Object[] warning = new Object[]{"� ����������","� �������������","�� ����������"};
        warningCombo = new JComboBox(warning);
        // ��������� ���������
        warningCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                contentLabel.setText(getContent());
            }
        });
        
        // ��������� ������ � ����� �� ������
        listBox.add(Box.createHorizontalStrut(10));
        listBox.add(lblCategory);
        listBox.add(Box.createHorizontalStrut(10));
        listBox.add(categoryCombo);
        listBox.add(Box.createHorizontalStrut(20));
        listBox.add(lblType);
        listBox.add(Box.createHorizontalStrut(10));
        listBox.add(warningCombo);
        listBox.add(Box.createHorizontalStrut(30));
        listBox.add(contentLabel = new JLabel("���������:"));
        comboPanel.add(listBox, BorderLayout.WEST);
        
        // ��������� �������
        JScrollPane pane = new JScrollPane(table = new JTable(4, 4));
        Box vertBox = Box.createVerticalBox();
        vertBox.add(Box.createVerticalStrut(10));
        vertBox.add(pane);
        
        // ��������� ���������� �� ������� ������
        mainPanel.add(comboPanel, BorderLayout.NORTH);
        mainPanel.add(vertBox, BorderLayout.CENTER);
        getContentPane().add(mainPanel);
    }
    
    /**
     * ���������� � ����������� �������� ��� ����
     */
    private void addMenuItem(){
        // ������ ���� ����
        JMenu mnuFile = new JMenu("����");
        JMenuItem mnuFileOpen = new JMenuItem("�������");
        // ��������� ���������
        mnuFileOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                openFile(); //To change body of generated methods, choose Tools | Templates.
            }
        });
        JMenuItem mnuFilePrint = new JMenuItem("������");
        JMenuItem mnuFileExit = new JMenuItem("�����");
        // ��������� ���������
        mnuFileExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);// ��������� �����
            }
        });
        mnuFile.add(mnuFileOpen);
        mnuFile.add(mnuFilePrint);
        mnuFile.add(mnuFileExit);
        
        // ������ ���� ������
        JMenu mnuEdit = new JMenu("������");
        JMenu mnuEditRow = new JMenu("������");
        JMenu mnuEditColumn = new JMenu("��������");
        JMenuItem mnuRowInsert = new JMenuItem("��������");
        JMenuItem mnuRowRemove = new JMenuItem("������� ������");
        JMenuItem mnuColumnInsertBefore = new JMenuItem("�������� �����");
        JMenuItem mnuColumnInsertAfter = new JMenuItem("�������� �����");
        JMenuItem mnuColumnRemove = new JMenuItem("������� �������");
        JMenu mnuPreview = new JMenu("��������������� ��������");
        mnuEditRow.add(mnuRowInsert);
        mnuEditRow.add(mnuRowRemove);
        mnuEditColumn.add(mnuColumnInsertAfter);
        mnuEditColumn.add(mnuColumnInsertBefore);
        mnuEditColumn.add(mnuColumnRemove);
        mnuEdit.add(mnuEditRow);
        mnuEdit.add(mnuEditColumn);
        
        JMenuBar bar = new JMenuBar();
        bar.add(mnuFile);
        bar.add(mnuEdit);
        bar.add(mnuPreview);
        setJMenuBar(bar);
    }
    
    /**
     * ������ ������ ������ ��������� �����������
     * @return ������ ��� ���������� ������
     */
    private ComboBoxModel categoryModel(){
        SprcategoryDaoImpl sdi = new SprcategoryDaoImpl();// ������ ������� � ������ ���������
        Object[] content = new Object[sdi.getCount()];
        for(int i = 0; i < content.length; i++)
            content[i] = sdi.getItem(i).getCategoryName();
        ComboBoxModel model = new DefaultComboBoxModel(content);
        return model;
    }
    
    
    /**
     * ���������� ���� ��� ������ ����� ��������
     */
    private void openFile(){
        JFileChooser chooser  = new JFileChooser();// ���� ������ �����
        chooser.setDialogType(JFileChooser.OPEN_DIALOG);
        // chooser.showOpenDialog(this);
        if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
            System.out.println(chooser.getSelectedFile().getName());
    }
    
    /**
     * ���������� ��������� ����� ����������� � ����������� �� �������
     * @return ������, �������������� ��������� ����� �����������
     */
    private String getContent() {
        return "���������: " + categoryCombo.getSelectedItem().toString() + " " +
                warningCombo.getSelectedItem().toString();
        
    }
}
