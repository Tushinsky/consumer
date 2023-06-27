/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

/**
 *
 * @author Sergii.Tushinskyi
 */
public class InformDialog extends JDialog {

    public enum InformType{CONNECT, LOADING, SAVING};
    private JLabel messageLabel;
    private Timer t;
    
    public InformDialog() {
        super();
        setParameters();
    }
    
    
    public InformDialog(Frame owner) {
        super(owner);
        setParameters();
    }
    
    /**
     * ������������� ��������� ����
     */
    private void setParameters(){
        setUndecorated(true);// ���������� ��� �����
        setAlwaysOnTop(true);// ������ ���� ����
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

//        Toolkit kit = getToolkit();
//        Dimension screenSize = kit.getScreenSize();
//        int screenHeight = screenSize.height;
//        int screenWidth = screenSize.width;
        // ������� ���� ���������
        setSize(200, 50);
        
         // ���������� ����� ���������� ������
        setLocationRelativeTo(this.getParent());
//        setLocation((screenWidth - 200)/2, (screenHeight - 50)/2);
        //��������� ������ ��� ������ ���������
        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new BorderLayout());
//            // ���� ���� ������
//            messagePanel.setBackground(dtPane.getBackground());
        messageLabel = new JLabel();

        // ������ ����� �� ������� ������
        messageLabel.setSize(messagePanel.getWidth(), messagePanel.getHeight());

        // ������������� ������ �� ������ �����
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        messageLabel.setFont(new Font("Serif", Font.BOLD, 14));// �����

        // ���� ����������
        messageLabel.setBorder(BorderFactory.createLineBorder(Color.magenta));

        messagePanel.add(messageLabel, BorderLayout.CENTER);// ��������� �����
        getContentPane().add(messagePanel, BorderLayout.CENTER);// � ������
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                // ������������� ������
                t.stop();
                t = null;
            }
        });
    }
    
    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        messageLabel.setText(message);
    }
    
    /**
     * @param type the type to set
     */
    public void setType(InformType type) {
        switch (type) {
            case CONNECT:
                // �������� ���������� � ����� ������
                setModal(true);// ��������� �����
                // ��������� ������
                t = new Timer(750, new ActionListener() {
                    
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                }); t.start();// ��������� ������
                break;
            case LOADING:
                // �������� �������� �����������
                setModal(false);// ����������� ����
                // ��������� ������
                t = new Timer(1500, new ActionListener() {
                    
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                }); t.start();// ��������� ������
                break;
            default:
                // �������� ���������� �����������
                // �������� ���������� � ����� ������
                setModal(true);// ��������� �����
                // ��������� ������
                t = new Timer(500, new ActionListener() {
                    
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                }); t.start();// ��������� ������
                break;
        }
    }

    
}
