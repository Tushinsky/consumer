/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import abonentgaz.UserProperties;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * ���������� ���� �������� ���������
 * @author ������
 */
public class SetupDialog extends JPanel {

    private boolean ok;
    private JDialog dialog;// ���� �������, � ������� ����� ������������ ��������
    private boolean textChange = false;// ���� ���� ����� ����������� ��������� � ����� �����
    private UserProperties props;// ����� ��� ������ �� ����� �������
    
    public SetupDialog() {
        // ������ �������� ����������
        JPanel panel = new JPanel();// ������ ��� ���������� ��������� ���������
//        panel.setBackground(Color.yellow);
        // �����
        JLabel hostLabel = new JLabel("IP ����� �������");
        JLabel portLabel = new JLabel("����� �����");
        JLabel databaseLabel = new JLabel("���� ������");
        JLabel templateLabel = new JLabel("�������");
        JLabel loginLabel = new JLabel("�����");
        
        // ��������� ��������� ����������� ����� �����
        final TextFieldListener listener = new TextFieldListener();
        
        // ���� ����� ������ �������
        final JTextField txtHostIP = new JTextField(10);
        txtHostIP.getDocument().addDocumentListener(listener);
        // ���� ����� ������ ����� �������
        final JTextField txtServerPort = new JTextField(10);
        txtServerPort.getDocument().addDocumentListener(new TextFieldListener());
        // ���� ����� ������ ������������
        final JTextField txtUserLogin = new JTextField(15);
        txtUserLogin.getDocument().addDocumentListener(new TextFieldListener());
        // ������ ��� ������
        final JComboBox databaseCombo = new JComboBox(readAliasName());
        databaseCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ��� ������ �������� ������ ��������� �������� ����������� �� ����� �������
                String[] connectprops = readConnectProperties(databaseCombo.getSelectedItem().toString());
                txtHostIP.setText(connectprops[0]);
                txtServerPort.setText(connectprops[1]);
                txtUserLogin.setText(connectprops[2]);
                
                textChange = false;// ���������� ���� ���������
            }
        });
        props = new UserProperties("connectproperties.properties");
        
        // ���� ����� ������������ ��������
        final JTextField txtTemplate = new JTextField(40);
        
        // ������ ������������� � ������ �����
        JButton okButton = new JButton("��");// ��������� ������ ��������� � ����
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                JOptionPane.showMessageDialog(dialog, 
                        "textChange = " + textChange, "AbonentGaz", 
                        JOptionPane.OK_OPTION);
                // ���� ��������� �����������, ���������� ��������� � ���� �������
                if(textChange) {
                    // ������ �������
                    String[] properties = new String[4];
                    properties[0] = txtHostIP.getText();
                    properties[1] = txtServerPort.getText();
                    properties[2] = txtUserLogin.getText();
                    properties[3] = txtTemplate.getText();
                    // ���������� �� � ����
                    writeConnectProperties(databaseCombo.getSelectedItem().toString(), 
                            properties);
                }
//                dialog.setVisible(false);
            }
        });
        
        JButton cancelButton = new JButton("�������");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
            }
        });
        
        // ������ ��� ����������� ���� ������ �����
        JButton openFileButton = new JButton("�������", new ImageIcon(MDIObject.class.getClassLoader().getResource("Images/OpenFile.png")));
        openFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ���������� ���� ������ �����
                JFileChooser chooser = new JFileChooser(new File("."));
                // �������� ������ �����
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int select = chooser.showOpenDialog(dialog);
                // ��������� ����� ������������
                if(select == JFileChooser.APPROVE_OPTION){
                        // ���� ������������ ������ �����, ������� ���� � ���� �����
                        txtTemplate.setText(chooser.getSelectedFile().getPath());
                    
                }
            }
        });
        // ������ ������
//        openFileButton.setSize(openFileButton.getPreferredSize());
        
        // ����������� �������� �� ������
        // ����� � ���� ����� ��� ������ ����� � ������ �������
        Box serverLabelBox = Box.createVerticalBox();
        serverLabelBox.add(hostLabel);
        serverLabelBox.add(Box.createVerticalStrut(10));
        serverLabelBox.add(portLabel);
        Box serverTextBox = Box.createVerticalBox();
        serverTextBox.add(txtHostIP);
        serverTextBox.add(Box.createVerticalStrut(10));
        serverTextBox.add(txtServerPort);
        Box serverBox = Box.createHorizontalBox();
        serverBox.add(serverLabelBox);
        serverBox.add(Box.createHorizontalStrut(10));
        serverBox.add(serverTextBox);
        
        Box baseLabelBox = Box.createVerticalBox();
        Box baseBox = Box.createHorizontalBox();
        Box baseTextBox = Box.createVerticalBox();
        Box templateBox = Box.createHorizontalBox();
        Box buttonBox = Box.createHorizontalBox();
        
        // �����, ������ � ������� ��� ������ � ���� ����� ����� ������������
        baseLabelBox.add(databaseLabel);
        baseLabelBox.add(Box.createVerticalStrut(10));
        baseLabelBox.add(loginLabel);
        baseTextBox.add(databaseCombo);
        baseTextBox.add(Box.createVerticalStrut(10));
        baseTextBox.add(txtUserLogin);
        baseBox.add(baseLabelBox);
        baseBox.add(Box.createHorizontalStrut(10));
        baseBox.add(baseTextBox);
        
        // �����, ���� ����� � ������ ��� ����� ���� � ��������
        templateBox.add(templateLabel);
        templateBox.add(Box.createHorizontalStrut(10));
        templateBox.add(txtTemplate);
        templateBox.add(Box.createHorizontalStrut(10));
        templateBox.add(openFileButton);
        
        // ������ ������ � �������� �����
        buttonBox.add(okButton);
        buttonBox.add(Box.createHorizontalStrut(20));
        buttonBox.add(cancelButton);
        
        // ��������� ��� ������������
        Box horBox = Box.createHorizontalBox();
        horBox.add(baseBox);
        horBox.add(Box.createHorizontalStrut(10));
        horBox.add(serverBox);
        Box box = Box.createVerticalBox();
        box.add(horBox);
        box.add(Box.createVerticalStrut(10));
        box.add(templateBox);
        box.add(Box.createVerticalStrut(25));
        box.add(buttonBox);
        // ��������� ��� �� ������
        panel.add(box);
        super.add(panel);
        
        // �������� ������ ������� � ������ ��� ������
        databaseCombo.setSelectedIndex(0);
        txtTemplate.setText(props.getProperty("template"));
        txtTemplate.getDocument().addDocumentListener(new TextFieldListener());
    }
    
    /**
     * ������� ������ ��� ������ � ���������� ����.
     * @param owner ��������� � ����������� ������ ��� null
     * @return 
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
            dialog.setType(Window.Type.POPUP);// ��� ���� - �����������
            dialog.setAlwaysOnTop(true);// ����������� ������ ���� ����
            dialog.pack();// ����������� �� ����������
            dialog.setResizable(false);// ��������� �������� �� �����������
            // ����� ������ ��� ����
            dialog.setIconImage(MDIObject.getImage("settings16.png"));
        }

 
        // ����� ��������� � ������� ������ �� �����
        dialog.setTitle("���������");

        // ����� �������
 //            dialog.setSize(flManager.getWidth(), flManager.getHeight());

        // ����� ������������ � ������ ���������� �����
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
        return ok;// ���������� ��������� ������
    }
    
    private Object[] readAliasName(){
        // ������� ����� ��� ������ �� ����� �������
        props = new UserProperties("aliases.properties");
        
        // �������� ��������� ������������ ��� ������, ����������� ";"
        String[] alias = props.getProperty("aliasname").split(";");
        
        return alias;
    }
    
    /**
     * ��������� ��������� ���������� �� ����� �������
     * @param pathname ������������ ������� ��� ������
     */
    private String[] readConnectProperties(String pathname){
        System.out.println("pathname=" + pathname);
        String [] properties = new String[4];
        properties[0] = props.getProperty(pathname + ".hostIP");
        properties[1] = props.getProperty(pathname + ".serverPort");
        properties[2] = props.getProperty(pathname + ".user");
        return properties;
    }
    
    /**
     * 
     * @param pathname 
     */
    private void writeConnectProperties(String pathname, String[] properties) {
        try {
            props.setProperty(pathname + ".hostIP", properties[0]);
            props.setProperty(pathname + ".serverPort", properties[1]);
            props.setProperty(pathname + ".user", properties[2]);
            props.setProperty("template", properties[3]);
            props.writeProperties();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SetupDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * ����� ��������� ��������� ��������� ��� ������������ ��������� � ����� �����
     */
    private class TextFieldListener implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
            // ���������� TRUE ���� ���������� ������� � ���� �����
            textChange = true;
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            // ���������� TRUE ���� ���������� �������� �� ���� �����
            textChange = true;
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            
        }
        
    }
}
