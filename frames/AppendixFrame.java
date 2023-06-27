/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Sergii.Tushinskyi
 */
public class AppendixFrame extends JFrame {
    private JTable table;// ������� ��� ������ ������ ���������� ����������
    public enum AppendixType {UUGType, LimitType, LoseType, JournalType};// ������������ ����� ����������
    private AppendixType appendixType;

    public AppendixFrame() throws HeadlessException {
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent we) {
                super.windowOpened(we);
                /* 
                ��� �������� ��������� ����� ��� ���������� ��������
                � ������ ��������������� �������� ����������������� ����������
                */
                initComponents();
                
                // ���������� ������� ������ � ����� ������� ����� � �������� ������
                Toolkit kit = getToolkit();
                Dimension size = kit.getScreenSize();
                setSize((int)size.getWidth() / 2, (int)size.getHeight() / 2);
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                setLocationRelativeTo(null);
            }
            
        });
    }
    
    private void initComponents() {
        // ������ �������� ���������� � ����������� �� ���� ����������
        switch(appendixType){
            case UUGType:
                getUUGInfo();
                break;
            case LimitType:
                getLimitInfo();
                break;
            case LoseType:
                getLoseInfo();
                break;
            case JournalType:
                getJournalInfo();
        }
        
    }

    /**
     * ��������� ������ �� ����� ����� � ����������������� ������������ �� 
     * �������� ���������������
     */
    private void getUUGInfo() {
        Object[] columnName = new Object[]{"� �/�",
            "������������ � ����� ������� �����������",
            "������������ ����������������� ������������",
            "���������� ����������������� ������������",
            "����������� ����������� ���� �� ���� ���������������� ������������ (�3/�)",
            "��� � ����� ������� ����� ���� (��������� �����)"};
        table = new JTable(new Object[][]{}, columnName);
        MDIObject.addColumnSelectionListener(table);// ��������� ��� ������� ��������� ���������
        // ��������� ������� �� ������ ��������� � ��������� �� � ������ �����������
        JScrollPane pane = new JScrollPane(table);
        getContentPane().add(pane);
    }
    
    /**
     * ��������� ������ �� ���������� ������� �����������
     */
    private void getLimitInfo() {
        Object[] columnName = new Object[]{"�����","�����",
            "�����","�����","�����","�����","�����","�����"};
        table = new JTable(new Object[][]{}, columnName);
        // ��������� ������� �� ������ ��������� � ��������� �� � ������ �����������
        JScrollPane pane = new JScrollPane(table);
        getContentPane().add(pane);
    }
    
    /**
     * ��������� ������ �� ������� �� �������� ���������������
     */
    private void getLoseInfo() {
        // ������ �������������� �������� ����������
        JLabel lblObjectName = new JLabel("������������ �������:");
        JLabel lblObjectLocation = new JLabel("��������������� ������� ���������������:");
        JLabel lblUUGLocation = new JLabel("����� ��������� ������������� ���� �����:");
        JLabel lblLoseData = new JLabel("�������� ������ ��� ������� ������:");
        final JTextField txtObjectName = new JTextField(50);
        final JTextField txtObjectLocation = new JTextField(50);
        final JTextField txtUUGLocation = new JTextField("�� �������", 50);
        final JTextField txtLoseData = new JTextField(50);
        // ����� �� �� �����
        Box lblBox = Box.createVerticalBox();// ��������� ��� �����
        Box txtBox = Box.createVerticalBox();// ��������� ��� ����� �����
        // ����� �����
        lblBox.add(lblObjectName);
        lblBox.add(Box.createVerticalStrut(5));
        lblBox.add(lblObjectLocation);
        lblBox.add(Box.createVerticalStrut(5));
        lblBox.add(lblUUGLocation);
        lblBox.add(Box.createVerticalStrut(5));
        lblBox.add(lblLoseData);
        // ����� ���� �����
        txtBox.add(txtObjectName);
        txtBox.add(Box.createVerticalStrut(3));
        txtBox.add(txtObjectLocation);
        txtBox.add(Box.createVerticalStrut(3));
        txtBox.add(txtUUGLocation);
        txtBox.add(Box.createVerticalStrut(3));
        txtBox.add(txtLoseData);
        Box box = Box.createHorizontalBox();
        box.add(Box.createHorizontalStrut(5));
        box.add(lblBox);
        box.add(Box.createHorizontalStrut(10));
        box.add(txtBox);
        box.add(Box.createHorizontalStrut(5));
        box.add(Box.createHorizontalGlue());
        
        Object[] columnName = new Object[]{"� �/�",
            "������������ � ����� ������� �����������",
            "������������ ����������������� ������������",
            "���������� ����������������� ������������",
            "����������� ����������� ���� �� ���� ���������������� ������������ (�3/�)",
            "��� � ����� ������� ����� ���� (��������� �����)"};
        table = new JTable(new Object[][]{}, columnName);
        // ��������� ������� �� ������ ��������� � ��������� �� � ������ �����������
        JScrollPane pane = new JScrollPane(table);
        Box mainBox = Box.createVerticalBox();
        mainBox.add(Box.createVerticalStrut(5));
        mainBox.add(box);
        //mainBox.add(Box.createVerticalStrut(5));
        mainBox.add(Box.createVerticalGlue());
        mainBox.add(pane);
        mainBox.add(Box.createVerticalStrut(5));
        mainBox.add(Box.createVerticalGlue());
        getContentPane().add(mainBox);
    }
    
    /**
     * ��������� ����� ������� ����� ���������� ����
     */
    private void getJournalInfo() {
        Object[] columnName = new Object[]{"����",
            "������� ��������� ��������, �3",
            "�������� ��������� �������� ���� �� ���������� �����, �3",
            "����� ��������������� - ��������������� ������ �� �����, �3",
            "����� ���� �� ����� � ����������� ��������, �3",
            "����� ���� � ����������� �������� � ����������� ������ � ������ ������, �3",
            "������� �������������� ���� �����������",
            "�������� �������� ����� ����������� ���� � ������� ����������"};
        Object[] content = new Object[]{"","","","","","","",""};
        Object[][] rowContent = new Object[4][];
        for (int i = 0; i < rowContent.length; i++)
            rowContent[i] = content;
        table = new JTable(rowContent, columnName);
        // ��������� ������� �� ������ ��������� � ��������� �� � ������ �����������
        JScrollPane pane = new JScrollPane(table);
        getContentPane().add(pane);
    }
    
    /**
     * ����� ��� ������������ ���������� : ���������� �1, ���������� �2,
     * ���������� �3, ���������� �4. � ����������� �� ���� ����������� �������� 
     * ����������������� ����������
     * @param appendixType the appendixType to set
     */
    public void setAppendixType(AppendixType appendixType) {
        this.appendixType = appendixType;
    }
    
    
    private void createMenuItem(){
        
    }
    
}
