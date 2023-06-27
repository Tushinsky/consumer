/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import abonentgaz.UserProperties;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import javax.swing.*;

/**
 * ���� ����������� � ��������� ���� ������
 * @author Sergii.Tushinskyi
 */
public class LoginFrame extends JPanel{

    // �������� ����������, ��������������� �� �����
    private final JLabel lblUsername;
    private final JLabel lblPassword;
    private final JLabel lblDatabase;
    private final JButton okButton;// ������ ������������� �����
    private final JButton cancelButton;// ������ ������ �����
    private final JTextField txtUsername;
    private final JPasswordField txtPassword;
    private final JComboBox cmbDatabase;
    private final JPanel panel;
    private JDialog dialog;// ���������� ���� ��� ����������� ��������� ����������
    private String hostIP;// ����� ����� �������
    private String serverPort;// ����� ����� �������
    private String userName;// ��� ��������������� ������������
    private String password;// ������ ������������
    private String databaseName;// ��� ���� ������, � ������� ������������ ������������
    private String aliasName;// ��������� ���� ������, ������� ����� ������������ � ������ ���������
    
    public LoginFrame(){
        super.setLayout(new BorderLayout());
        panel = new JPanel(new BorderLayout());// ������ ��� ���������� �����������
        aliasName = null;
        // ������ ������
        okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                ok = true;
                userName = txtUsername.getText();
                
                char[] passchar = txtPassword.getPassword();// �������� ������ �������� ������
                // ��������������� ��� � ������
                password = "";
                for(int i = 0; i < passchar.length; i++)
                    password = password + passchar[i];
                
//                System.out.println("database - " + databaseName);
//                System.out.println("host - " + hostIP);
//                System.out.println("serverport - " + serverPort);
//                System.out.println("user - " + userName);
//                System.out.println("password - " + password);
                aliasName = cmbDatabase.getSelectedItem().toString();
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
        
        
        // ��������� �����, ���� ����� � ������ ������ �� ������, ������� �� � �����
        Box nameBox = Box.createHorizontalBox();
        nameBox.add(Box.createHorizontalStrut(10));
        nameBox.add(lblUsername = new JLabel("User name"));
        nameBox.add(Box.createHorizontalStrut(5));
        nameBox.add(txtUsername = new JTextField("sysdba",15));
        nameBox.add(Box.createHorizontalStrut(10));

        Box passwordBox = Box.createHorizontalBox();
        passwordBox.add(Box.createHorizontalStrut(10));
        passwordBox.add(lblPassword = new JLabel("Password"));
        passwordBox.add(Box.createHorizontalStrut(10));
        passwordBox.add(txtPassword = new JPasswordField("masterke",15));
        passwordBox.add(Box.createHorizontalStrut(10));

        Box databaseBox = Box.createHorizontalBox();
        databaseBox.add(Box.createHorizontalStrut(10));
        databaseBox.add(lblDatabase = new JLabel("Database name"));
        databaseBox.add(Box.createHorizontalStrut(10));
        databaseBox.add(cmbDatabase = new JComboBox());
        databaseBox.add(Box.createHorizontalStrut(10));
        // ��������� ������ �� ������ ������
        Box buttonBox = commandButtonBox();

        Box vertBox = Box.createVerticalBox();
        vertBox.add(nameBox);
        vertBox.add(Box.createVerticalStrut(10));
        vertBox.add(passwordBox);
        vertBox.add(Box.createVerticalStrut(10));
        vertBox.add(databaseBox);
        vertBox.add(Box.createVerticalStrut(10));
        // ������� ��������� ������� ������ � ��������� ���� ����� ����� ������������
        // � ����� ������
        KeyAdapter ka = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                super.keyPressed(ke); //To change body of generated methods, choose Tools | Templates.
                // ���� ������ ������� �����, �� ���������� ��������
                // ��� ������� OK, ���� ������, �� ���������� �������� ��� ������� Cancel
                if(ke.getKeyCode() == KeyEvent.VK_ENTER)
                    okButton.doClick();
                if(ke.getKeyCode() == KeyEvent.VK_ESCAPE)
                    cancelButton.doClick();
            }
            
        };
        txtPassword.addKeyListener(ka);
//        txtPassword.setFocusable(true);// ������������� ����� �� ���� ����� ������
        txtUsername.addKeyListener(ka);
        // ��������� �������� �� ������
        panel.add(vertBox, BorderLayout.CENTER);

        panel.add(buttonBox, BorderLayout.SOUTH);
        
        super.add(panel, BorderLayout.CENTER);
        
        // ������ ���� ������� � ���������� ��� ������
        readAliasname();
    }
    
    /**
     * ������� ������ ��� ������ � ���������� ����.
     * @param owner ��������� � ����������� ������ ��� null 
     */
    public void showDialog(Component owner){
//        ok = false;

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
            dialog.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            
            //����� ������ ��� ������
            URL url;
            url = LoginFrame.class.getClassLoader().getResource("Images/padlock.png");
            Image image;
            image = new ImageIcon(url).getImage();
            if (image != null) dialog.setIconImage(image);
            
        }

        // ����� ��������� � ������� ������ �� �����
        dialog.setTitle("�����������");

        // ����� ������������ � ������ ���������� �����
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
//        return ok;// ���������� ��������� ������
    }
    
    /**
     * 
     * @return Box
     */
    private Box commandButtonBox(){
        // ��������� ������ �� ������ ������
        Box buttonBox = Box.createHorizontalBox();
        buttonBox.add(Box.createHorizontalGlue());
        buttonBox.add(okButton);
        buttonBox.add(Box.createHorizontalStrut(40));// ��������� ����� ���� ��������
        buttonBox.add(cancelButton);
        buttonBox.add(Box.createHorizontalGlue());
        Box vertBox = Box.createVerticalBox();
        vertBox.add(Box.createVerticalStrut(5));
        vertBox.add(buttonBox);
        vertBox.add(Box.createVerticalStrut(10));
        return vertBox;
    }
    
    /**
     * ������ ������������ ���� ��� ������, � ������� ����� ������������ ������������
     */
    private void readAliasname(){
        // ������� ����� ��� ������ �� ����� �������
        UserProperties props = new UserProperties("aliases.properties");
        
        // �������� ��������� ������������ ��� ������, ����������� ";"
        String[] alias = props.getProperty("aliasname").split(";");
        
        // ������ ������ ��� ������ � ��������� � ������� ���
        ComboBoxModel model = new DefaultComboBoxModel(alias);
        
        // ������������� � ��� ������ ������
        cmbDatabase.setModel(model);
        
        // ���������� �������� ��� ������ ��������� ��������
        cmbDatabase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                readConnectProperties(cmbDatabase.getSelectedItem().toString());
            }
        });
        
        // �������� ������ ������� � ������
        cmbDatabase.setSelectedIndex(0);
    }
    
    /**
     * ��������� ��������� ���������� �� ����� �������
     * @param pathname ������������ ������� ��� ������
     */
    private void readConnectProperties(String pathname){
        // ������� ����� ��� ������ �� ����� �������
        UserProperties props = new UserProperties("connectproperties.properties");
        databaseName = props.getProperty(pathname + ".database");
        hostIP = props.getProperty(pathname + ".hostIP");
        serverPort = props.getProperty(pathname + ".serverPort");
        userName = props.getProperty(pathname + ".user");
        password = "";
        txtUsername.setText(userName);
        txtPassword.setText(password);
    }

    /**
     * @return the hostIP
     */
    public String getHostIP() {
        return hostIP;
    }

    /**
     * @return the serverPort
     */
    public String getServerPort() {
        return serverPort;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the databaseName
     */
    public String getDatabaseName() {
        return databaseName;
    }

    /**
     * @return the aliasName
     */
    public String getAliasName() {
        return aliasName;
    }
}
