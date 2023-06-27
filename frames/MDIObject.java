/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import abonentgaz.JDBCConnection;
import abonentgaz.TableProperty;
import abonentgaz.UserProperties;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyVetoException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author ������
 */
public class MDIObject extends JFrame{

    private JDBCConnection connect;
    private String fileNameProperties="fbdatabase.properties";
    private JMenu mnuFile;
    private JMenu mnuEdit;
    private JMenu mnuDocuments;
    private JMenu mnuSetup;
    private JMenu mnuWindows;
//    private JMenu mnuAdminExit;
    private JMenu mnuData;
//    private JMenu mnuDataBase;
//    private JMenu mnuExecute;
    private JMenu mnuFileSpravochnik;
    private JDesktopPane dtPane;
    private JMenuItem[] mnuDispatchItem;
    private JMenuItem mnuFileConnectionOpen;
    private JMenuItem mnuFileConnectionClose;
    private JMenuItem mnuFileCard;
    private JMenuItem mnuFileFind;
    private MainFrame mainframe;// ������� ���� �����������
    private FindFrame findframe;// ���� ������
    private ArrayList mnuWindowItem;// ������ ��� �������� ������� ����,
                                      // ��������������� �������� �������� �����
    private StatusBar tbrAdminbar;
    private ConnectOptions connOptions;
    
    public MDIObject() {
        // ��������� ������� ��� �����
        WindowListener winListener;
        winListener = new WindowAdapter() {
            
            @Override
            public void windowOpened(WindowEvent we) {
                super.windowOpened(we);
                //����� ������ ��� ������
                URL url;
                url = MDIObject.class.getClassLoader().getResource("Images/book.png");
                Image image;
                image = new ImageIcon(url).getImage();
                if (image != null) setIconImage(image);
                
                // ������������� ���������� ���
                setExtendedState(JFrame.MAXIMIZED_BOTH);
                
                // �������������� �������
                mnuWindowItem = new ArrayList();
                
                // ������ ������ ��� �������� ����
                createDesktopPane();
                add(dtPane);
                
                // ������ ������ ����
                JMenuBar menuBar = new JMenuBar();
                
                // ������ ���� ����
                createMenuFile();
                
                // ������ ���� ������
                createMenuEdit();
                
                // ������ ���� �������� ���������
                createMenuDocuments();
                
                // ������ ���� ���������
                createMenuSetup();
                
                // ������ ���� ������
                mnuData = new JMenu("������");
                mnuData.setMnemonic('�');// ������� �������� �������
                // ��������� ��� �������� ����
                Action importAction = new Menu_Action("������");
                Action exportAction = new Menu_Action("�������");
                Action updateAction = new Menu_Action("����������");
                JMenuItem mnuDataImport = mnuData.add(importAction);
                JMenuItem mnuDataExport = mnuData.add(exportAction);
                JMenuItem mnuDataUpdate = mnuData.add(updateAction);

                // ������ ���� ����
                createMenuWindow();

                menuBar.add(mnuFile);
                menuBar.add(mnuEdit);
                menuBar.add(mnuDocuments);
                menuBar.add(mnuSetup);
//                menuBar.add(mnuData);
                menuBar.add(mnuWindows);

                // ������ � ��������� ������ ������������
                tbrAdminbar = new StatusBar(SwingConstants.HORIZONTAL);
                MDIObject.this.setJMenuBar(menuBar);

                // ������� ������ ��������� ��� ����������� ���������� �� �����������
                MDIObject.this.getContentPane().add(tbrAdminbar, BorderLayout.SOUTH);

                setVisibleMenuItem(true);// ������������� ����������� ������� ����
                setEnabledMenu(false);// ������������� ������������� ������� ����
                
                // ������ ����� ��� ��������� ������� � ���� ������
                connOptions = new ConnectOptions();
            }
            
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                closingWindow();
            }

            
        };
        addWindowListener(winListener);
        
    }
    
    //��������� �������� ����
    private void closingWindow() {
        int button = JOptionPane.showConfirmDialog(this, "������ �����?", 
                "AbonentGaz", JOptionPane.YES_NO_OPTION);
        if(button == JOptionPane.YES_OPTION){
            closeConnection();
            System.exit(0);
        }
    }

    // ��������� ��������� ������� ����
    private void setVisibleMenuItem(boolean iVisible){
        mnuFile.setVisible(iVisible);
        mnuEdit.setVisible(iVisible);
        mnuDocuments.setVisible(iVisible);
        mnuSetup.setVisible(iVisible);
//        mnuAdminExit.setVisible(!iVisible);
//        mnuData.setVisible(!iVisible);
//        mnuDataBase.setVisible(!iVisible);
//        mnuExecute.setVisible(!iVisible);
        System.out.println(iVisible);
    }
    /**
     * @return the connect
     */
    public JDBCConnection getConnect() {
        return connect;
    }

    private boolean openConnection() throws FileNotFoundException, 
            IOException, SQLException, ClassNotFoundException {
        // ��������� ���������� � ����� ������
        //������ ���� ������� ��� �������� �������� � ������ ����������
//        Properties props= new Properties();
        String message = "Connection is not opened!";
        boolean retval;
        if(connOptions.isAccessOpen() == false){
            connOptions.showLoginframe();
        }
        if(connOptions.getAliasName() != null){
            try {
                // set drivername
                String driver = "org.firebirdsql.jdbc.FBDriver";//props.getProperty("jdbc.driver");
    //                System.out.println("driver = " + driver);
                String url = "jdbc:firebirdsql://" + connOptions.getHostIP() + ":" +
                    connOptions.getServerPort() + "/" + 
                    connOptions.getDatabaseName();//props.getProperty("jdbc.url");
//                    System.out.println("url = " + url);
                // ��������� �������������� ���������� � ����� ������
                connect = new JDBCConnection(driver, url, connOptions.getUsername(),
                        connOptions.getPassword());
                if (connect.isClosedConn() != true){
                    message = "Connection is opening!";
                    retval = true;
                } else{
                    retval = false;
    //                System.exit(0);
                }
                connOptions.setAccessOpen(retval);
                // ���� ��������� �� ����������� ����������
                getInformDialog(this, message, InformDialog.InformType.CONNECT);
                tbrAdminbar.setDatabaseName(connOptions.getAliasName());
                return retval;
            } catch (SQLException | ClassNotFoundException ex){
                // ���� ��������� �� ����������� ����������
                getInformDialog(this, message, InformDialog.InformType.CONNECT);
                return false;
            }
        } else {
            // ���� ��������� �� ����������� ����������
            getInformDialog(this, message, InformDialog.InformType.CONNECT);
            return false;
        }

            
    }
    
    private void closeConnection(){
        try {
            if (connect != null && !connect.isClosedConn()) {
            JDBCConnection.getConn().close();
            if(connect.isClosedConn()){
                // ����� ��������� ���������� ��������� �������� ������� ����� �����������
                // � ��������� �, ���� ��� ���������
                if(mainframe != null) mainframe.setVisible(false);
                
                connect = null;
                mnuFileConnectionClose.setEnabled(false);
                mnuFileConnectionOpen.setEnabled(true);
                // ���������� ���� ��������� �������� ����������
//                InformFrame iframe = new InformFrame("Connection is closed!");
                getInformDialog(this, "Connection is closed!", InformDialog.InformType.CONNECT);
                tbrAdminbar.setStateConnection(false);
//                System.out.println("Connection is closed!");
            }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MDIObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    class Menu_Action extends AbstractAction{
        public Menu_Action(String name){
            super(name);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()){
                case "�������":
                    try {
                        boolean open = openConnection();
                        setEnabledMenu(open);
                        // ���������� �� ������ ��� ��������� ������ � ���������
                        // ����������
                        tbrAdminbar.setStateConnection(open);
                        if(open == true)
                            createMenuDispatch();// ��������� �����������
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(MDIObject.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException | SQLException | ClassNotFoundException ex) {
                        Logger.getLogger(MDIObject.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case "�������":
                    closeConnection();
                    setEnabledMenu(false);
                    
                    break;
                case "�����������������":
                    setVisibleMenuItem(false);
                    break;
                case "�����":
                    setVisibleMenuItem(true);
                    break;
                case "������������":
                    break;
                case "��������":
                    // ���������� ������� ���� - ��������
                    showMainFrame(0);
                    break;
                case "�����":
                    // ���������� ���� ������
                    showFindFrame();
                    break;
                case "�����":
                    closingWindow();
                    break;
                case "�������������":
                    AlignInternalFrame(1);
                    break;
                case "�����������":
                    AlignInternalFrame(2);
                    break;
                case "��������":
                    AlignInternalFrame(3);
                    break;
                case "������":
                    OperateFrame frmImport = new OperateFrame();
                    frmImport.setIdOperation(0);// ������������� �������� - ������
                    frmImport.setParentFrame(MDIObject.this);
                    frmImport.setLocationRelativeTo(null);// ���������� �����
                    frmImport.setVisible(true);
                    break;
                case "�������":
                    OperateFrame frmExport = new OperateFrame();
                    frmExport.setIdOperation(1);// ������������� �������� - ������
                    frmExport.setParentFrame(MDIObject.this);
                    frmExport.setLocationRelativeTo(null);// ���������� �����
                    frmExport.setVisible(true);
                    break;
                case "����������":
                    OperateFrame frmUpdate = new OperateFrame();
                    frmUpdate.setIdOperation(2);// ������������� �������� - ������
                    frmUpdate.setParentFrame(MDIObject.this);
                    frmUpdate.setLocationRelativeTo(null);// ���������� �����
                    frmUpdate.setVisible(true);
                    break;
                default:
                    break;
            }
//            System.out.println(e.getActionCommand());
        }
    }
    
    class Dispatch_Action extends AbstractAction{
        private String tableName;
        private String[] columnName = null;
        private Class[] columnClass = null;
        private int Index;
        
        public Dispatch_Action(String name, String[] columnName, 
                Class[] columnClass, int index){
            super(name);
            tableName = name;
            this.columnName = columnName;
            this.columnClass = columnClass;
            this.Index = index;
        }

        @Override
        public void actionPerformed(final ActionEvent e) {
            
//            try {
//                ResultSet rs = connect.ExecuteQuery("SELECT * FROM " + tableName);
                final DispatchFrame dframe = new DispatchFrame();
                dframe.setTitle(e.getActionCommand());
                dframe.setName(e.getActionCommand());
                dframe.setTableName(tableName);
                dframe.setColumnName(columnName);
                dframe.setColumnClass(columnClass);
//                dframe.setParentFrame(MDIObject.this);
                dframe.addInternalFrameListener(new InternalFrameAdapter() {

                    
                    @Override
                    public void internalFrameActivated(InternalFrameEvent ife) {
                        super.internalFrameActivated(ife);
                        // �������� ��������������� ����� ����
                        selectedMNUWinItem(e.getActionCommand());
                    }

                    @Override
                    public void internalFrameOpened(InternalFrameEvent e) {
                        super.internalFrameOpened(e); //To change body of generated methods, choose Tools | Templates.
                        // �������� ��������������� ����� ���� ������������
//                        mnuDispatchItem[Index].setSelected(true);
                        
                        // � ������ ��� �����������
                        mnuDispatchItem[Index].setEnabled(false);
                    }

                    @Override
                    public void internalFrameClosing(InternalFrameEvent e) {
                        super.internalFrameClosing(e); //To change body of generated methods, choose Tools | Templates.
                        // ������� ������� � ���������������� ������ ���� ������������
//                        mnuDispatchItem[Index].setSelected(false);
                        
                        // � ������ ��� ���������
                        mnuDispatchItem[Index].setEnabled(true);
                    }
                    
                    
                });
//                dframe.setResultSet(rs);
                dframe.setEditable(true);// ����� ��������������
                
                // ��������� ������� ������ ���� �� ������
                JInternalFrame[] infArray = dtPane.getAllFrames();
                int count = infArray.length;
//                System.out.println("count=" + count);
                if(count > 0){
                    Point p = infArray[0].getLocation();// ���������� �������������� ��������� �����
                    Point p1 = new Point();// ���������� ����� �����
                    p1.move(p.x + 30, p.y + 30);// ����� ����� ���������� ��� �����
                    dframe.setLocation(p1);
                }
                
                dframe.setVisible(true);// ������ �������
                dtPane.add(dframe);// ��������� �� ������
                
//            } catch (SQLException ex) {
//                Logger.getLogger(MDIObject.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }

        /**
         * @param columnName the columnName to set
         */
        public void setColumnName(String[] columnName) {
            this.columnName = columnName;
        }

        /**
         * @param columnClass the columnClass to set
         */
        public void setColumnClass(Class[] columnClass) {
            this.columnClass = columnClass;
        }
    }
    
    // �������������� �������� ����
    private void AlignInternalFrame(int choice){
        JInternalFrame[] allFrames = dtPane.getAllFrames();
        // ��������� ������ ������� : ���� � �� ���� �������� � �� ����������
        // ������ 1 (���������� ������ ���� ����), ����� ��������� ��������������
        if(allFrames.length > 1) {
            switch (choice) {
                case 1:
                    {
                        // ������������ �������� ���� �� �����������
                        // ������ ���� ���� ������������� �� ������ ������ �������� �����
                        // ������ ���� ���� ����������� ������ ������ �������� �����
                        int dWidth = (int) dtPane.getWidth() / allFrames.length;
                        int dHeight = dtPane.getHeight();
                        int dX = 0;// ��������� ���������� ������ �� �����������
                    for (JInternalFrame allFrame : allFrames) {
                        allFrame.setBounds(dX, 0, dWidth, dHeight);
                        dX = dX + dWidth;// ����������� ���������� ������ �� �����������
                    }
                    break;
                    }
                case 2:
                    {
                        // ������������ �������� ���� �� ���������
                        // ������ ���� ���� ������������� �� ������ ������ �������� �����
                        // ������ ���� ���� ����������� ������ ������ �������� �����
                        int dHeight = (int) dtPane.getHeight() / allFrames.length;
                        int dWidth = dtPane.getWidth();
                        int dY = 0;// ��������� ���������� ������ �� �����������
                    for (JInternalFrame allFrame : allFrames) {
                        allFrame.setBounds(0, dY, dWidth, dHeight);
                        dY = dY + dHeight;// ����������� ���������� ������ �� ���������
                    }
                    break;
                    }
                default:
                    {
                        // ������������ �������� ���� ��������
                        // ��������� ���� ���� �������������� ��� �� ������, ��� � ��
                        // ����������� ���, ����� ��� ��� ������������ �� ������� �����
                        int dHeight = (int) (dtPane.getHeight() - 30 * allFrames.length);
                        int dWidth = (int) (dtPane.getWidth() - 30 * allFrames.length);
                        int dX = 0;// ��������� ���������� ������ �� �����������
                        int dY = 0;// ��������� ���������� ������ �� �����������
                        for(int i = allFrames.length - 1; i >= 0; i--){
                            allFrames[i].setBounds(dX, dY, dWidth, dHeight);
                            dX = dX + 30;// ����������� ���������� ������ �� �����������
                            dY = dY + 30;// ����������� ���������� ������ �� ���������
                        }       break;
                    }
            }
        }
    }
    
    /**
     * @param frame
     */
//    private class InformFrame extends JDialog {
//        public InformFrame(String message){
//            super(MDIObject.this);
//            setUndecorated(true);// ���������� ��� �����
//            setAlwaysOnTop(true);// ������ ���� ����
//            setLayout(new BorderLayout());
//            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//            setModal(true);
//            Toolkit kit = getToolkit();
//            Dimension screenSize = kit.getScreenSize();
//            int screenHeight = screenSize.height;
//            int screenWidth = screenSize.width;
//            // ������� ���� ���������
//            setSize(200, 50);
//            // ���������� ����� ���������� ������
//            setLocation((screenWidth - 200)/2, (screenHeight - 50)/2);
//            //��������� ������ ��� ������ ���������
//            JPanel messagePanel = new JPanel();
//            messagePanel.setLayout(new BorderLayout());
////            // ���� ���� ������
////            messagePanel.setBackground(dtPane.getBackground());
//            JLabel messageLabel= new JLabel(message);
//            
//            // ������ ����� �� ������� ������
//            messageLabel.setSize(messagePanel.getWidth(), messagePanel.getHeight());
//            
//            // ������������� ������ �� ������ �����
//            messageLabel.setHorizontalAlignment(JLabel.CENTER);
//            messageLabel.setFont(new Font("Serif", Font.BOLD, 14));// �����
//            
//            // ���� ����������
//            messageLabel.setBorder(BorderFactory.createLineBorder(Color.magenta));
//            
//            messagePanel.add(messageLabel, BorderLayout.CENTER);// ��������� �����
//            getContentPane().add(messagePanel, BorderLayout.CENTER);// � ������
////            pack();
//            // ��������� ������
//            Timer t = new Timer(750, new ActionListener() {
//
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    InformFrame.this.dispose();
//                }
//            });
//            t.start();// ��������� ������
//        }
//    }
    
    
    
    public void addInternalFrame(JInternalFrame frame){
        dtPane.add(frame);
        frame.setVisible(true);
    }
    
    private void setFocusableInternalFrame(String name) throws PropertyVetoException{
        JInternalFrame[] iframeList = dtPane.getAllFrames();
        int j = 0;
        for (int i = 0; i < iframeList.length; i++){
            if (iframeList[i].getName().equals(name)) j = i;
            iframeList[i].setFocusable(false);
        }
        iframeList[j].setSelected(true);
        // �������� ��������� ����� ����
        selectedMNUWinItem(name);
//        iframeList[j].setFocusable(true);
    }
    
    private void selectedMNUWinItem(String name){
        // �������� ��������� ����� ����
        for (int i = 4; i < mnuWindows.getItemCount(); i++){
            if(mnuWindows.getItem(i).getText().equals(name)){
                mnuWindows.getItem(i).setSelected(true);
            } else {
                mnuWindows.getItem(i).setSelected(false);
            }
        }
    }
    
    /**
     * ��������� ����������� ��������� ������� ����
     * @param iEnabled 
     */
    private void setEnabledMenu(boolean iEnabled){
        mnuFileCard.setEnabled(iEnabled);
        mnuFileFind.setEnabled(iEnabled);
//        mnuFileAdmin.setEnabled(iEnabled);
        mnuFileSpravochnik.setEnabled(iEnabled);
        mnuFileConnectionOpen.setEnabled(!iEnabled);
        mnuFileConnectionClose.setEnabled(iEnabled);
        mnuDocuments.setEnabled(iEnabled);
    }
    
    /**
     * ������ ������ ��� ���������� �������� ����
     */
    private void createDesktopPane(){
        dtPane = new JDesktopPane();// ������ ��� ���������� �������� ����
        dtPane.setBackground(SystemColor.inactiveCaption);
        // ��������� � ��� ���������� �������
        dtPane.addContainerListener(new ContainerAdapter() {

            @Override
            public void componentAdded(ContainerEvent e) {
                super.componentAdded(e);
                // ��� ���������� �������� ����� ��������� �������-
                // �������� ����� ���� � � ������
                JCheckBoxMenuItem mnuwinItem = 
                        new JCheckBoxMenuItem(e.getChild().getName());

                mnuwinItem.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            // ������������� ����� �� ��������� �����
                            setFocusableInternalFrame(e.getActionCommand());

                        } catch (PropertyVetoException ex) {
                            Logger.getLogger(MDIObject.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                mnuWindowItem.add(mnuwinItem);
                mnuWindows.add(mnuwinItem);
                try {
                    setFocusableInternalFrame(mnuwinItem.getText());
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(MDIObject.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void componentRemoved(ContainerEvent e) {
                super.componentRemoved(e);
                // ��� �������� �������� ����� ������� ���������������
                // �� ����� ����
                for (int i = 4; i < mnuWindows.getItemCount(); i++){
                    if(mnuWindows.getItem(i).getText().equals(e.getChild().getName())){
                        mnuWindows.remove(mnuWindows.getItem(i));
                        break;
                    }
                }
            }
        });
    }
    
    /**
     * �������� � ���������� ���� ����
     */
    private void createMenuFile(){
        // ������ ���� ����
        mnuFile = new JMenu("����");
        mnuFile.setMnemonic('�');// ������� �������� �������
        // ��������� � ���� ���������
        JMenu mnuFileConnection = new JMenu("����������");
        Action openAction = new Menu_Action("�������");
        mnuFileConnectionOpen = mnuFileConnection.add(openAction);
        mnuFileConnectionOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, 
                InputEvent.CTRL_MASK));// ������� �������� ������� Ctrl+O
        mnuFileConnectionOpen.setIcon(new ImageIcon(MDIObject.class.getClassLoader().getResource("Images/online16.png")));

        Action closeAction = new Menu_Action("�������");
        mnuFileConnectionClose = mnuFileConnection.add(closeAction);
        mnuFileConnectionClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, 
                InputEvent.CTRL_MASK));// ������� �������� ������� Ctrl+T
        mnuFileConnectionClose.setIcon(new ImageIcon(MDIObject.class.getClassLoader().getResource("Images/padlock16.png")));
        mnuFileConnectionClose.setEnabled(false);// ���� ������� ����������
        mnuFile.add(mnuFileConnection);

        Action cardAction = new Menu_Action("��������");
        Action findAction = new Menu_Action("�����");
        Action exitAction = new Menu_Action("�����");

//        mnuFileUser = mnuFile.add(userAction);
        mnuFileCard = mnuFile.add(cardAction);
        mnuFileCard.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, 
                InputEvent.CTRL_MASK));// ������� �������� �������
        mnuFileCard.setIcon(new ImageIcon(MDIObject.class.getClassLoader().getResource("Images/book.png")));
        mnuFileFind = mnuFile.add(findAction);
        mnuFileFind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, 
                InputEvent.CTRL_MASK));// ������� �������� �������
        mnuFileFind.setIcon(new ImageIcon(MDIObject.class.getClassLoader().getResource("Images/camera.png")));
        mnuFileSpravochnik = new JMenu("�����������");
        mnuFile.add(mnuFileSpravochnik);

        mnuFile.add(exitAction);
                
    }
    
    /**
     * ������ ���� ������
     */
    private void createMenuEdit(){
        mnuEdit = new JMenu("������");
        mnuEdit.setMnemonic('�');// ������� �������� �������

        JMenuItem mnuEditAddNewRecord = new JMenuItem("�������� ������");
        JMenuItem mnuEditDeleteRecord = new JMenuItem("������� ������");
        JMenuItem mnuEditSaveRecord = new JMenuItem("���������");
        JMenuItem mnuEditRefreshRecord = new JMenuItem("�������� ������");
        mnuEdit.add(mnuEditAddNewRecord);
        mnuEdit.add(mnuEditDeleteRecord);
        mnuEdit.add(mnuEditSaveRecord);
        mnuEdit.add(mnuEditRefreshRecord);
    }
    
    /**
     * ������ ���� �������� ���������
     */
    private void createMenuDocuments(){
        mnuDocuments = new JMenu("�������� ���������");
        mnuDocuments.setMnemonic('�');// ������� �������� �������
        JMenuItem mnuDocReports = new JMenuItem("������");
        JMenuItem mnuDocWarning = new JMenuItem("�����������");
        JMenuItem mnuDocPrevLose = new JMenuItem("��������������� ������ ������");
        
        // ��������� �������� � ����
        mnuDocReports.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
//                throw new UnsupportedOperationException("Not supported yet.");
                ReportFrame reportframe = new ReportFrame();
                reportframe.setVisible(true);
            }
        });
        mnuDocReports.setIcon(new ImageIcon(MDIObject.class.getClassLoader().getResource("Images/report.png")));
        mnuDocWarning.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                WarningFrame frame = new WarningFrame();
                frame.setVisible(true);
            }
        });
        mnuDocWarning.setIcon(new ImageIcon(MDIObject.class.getClassLoader().getResource("Images/letter.png")));
        mnuDocPrevLose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                PrevloseFrame prevloseFrame = new PrevloseFrame();
                prevloseFrame.setVisible(true);
            }
        });
        // ��������� ������ � ����
        mnuDocuments.add(mnuDocReports);
        mnuDocuments.add(mnuDocWarning);
        mnuDocuments.add(mnuDocPrevLose);
    }
    
    /**
     * ������ ���� ���������
     */
    private void createMenuSetup(){
        mnuSetup = new JMenu("���������");
        mnuSetup.setMnemonic('�');// ������� �������� �������
        JMenuItem mnuSetupOpen = new JMenuItem("�������");
        mnuSetupOpen.setIcon(new ImageIcon(MDIObject.class.getClassLoader().getResource("Images/settings16.png")));
        
        JMenuItem mnuSetupClear = new JMenuItem("����� ��������");
        mnuSetupClear.setIcon(new ImageIcon(MDIObject.class.getClassLoader().getResource("Images/letter.png")));
        
        // ��������� ���������
        mnuSetupOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ���������� ���� �������� ����������
                SetupDialog dialog = new SetupDialog();
                boolean retval = dialog.showDialog(MDIObject.this);
            }
        });
        mnuSetupClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        mnuSetup.add(mnuSetupOpen);
        mnuSetup.add(mnuSetupClear);
    }
    
    /**
     * ������ ���� ����
     */
    private void createMenuWindow(){
        mnuWindows = new JMenu("����");
        Action horAction = new Menu_Action("�������������");
        Action vertAction = new Menu_Action("�����������");
        Action cascAction = new Menu_Action("��������");
        mnuWindows.add(horAction);
        mnuWindows.add(vertAction);
        mnuWindows.add(cascAction);
        JPopupMenu.Separator jSeparator = new JPopupMenu.Separator();
        mnuWindows.add(jSeparator);// ��������� ����������� � ���� ����
                
    }
    
    /**
     * ������ � ��������� ���� ������������
     */
    private void createMenuDispatch(){
        
        // ��������� ���� ������������ �� ���������
        JMenu mnuDevices = new JMenu("��� � ������������");
        JMenu mnuLoses = new JMenu("������ �� ������������");
        JMenu mnuTowns = new JMenu("��������� ������");
        JMenu mnuOther = new JMenu("������");
        
        // ������ �������� ���������� ����
        String[] nameItem = {"�������", "����������", "�������������",
        "������������", "��������", "�������� ��������",
        "�������� ��������", "����������������� ������� ��������",
        "�������� ������� ��������", "����������", "������", "���", "�����",
        "�������� ��������", "����", "���", "���������", "���������", 
        "����� ��������� �����", "���������", "�������"};
        
        // ������ ��������������� �������� ������ ���� ������
        String[] tableItem = {"sprdatchik", "sprcorrector", "sprmaker",
        "sprequipment", "sprcounter", "sprgazlinerhipress",
        "sprgazlinermidpress", "sprgazlinerrlowpress",
        "sprgazlinerdlowpress", "sprgazregulator", "sprcity", "sprgrs", "sprstreet",
        "sprarmatura", "sprbank", "spryear", "sprcategory", "sprcontrolers", 
        "sprplumbsinstalplace", "sprpodryadchik", "sprcase"};
        
        // ������ ������������ ��������, ���������� �������� �������� ������
        // �� ���� ������
        String[][] columnname = {{"�","������������","��� �������������",
            "��������","�����"},{"�","������������","��� �������������",
            "����� ��������"},{"�","������������"},{"�","������������","���",
            "��� �������������","��������, ���","������"},{"�","���","������������",
            "��� �������������","������","Q min","Q nom","Q max","DN","�����"},
            {"�","�������, ��","������"},{"�","�������, ��","������"},
            {"�","�������, ��","������"},{"�","�������, ��","������"},
            {"�","������������","������"},{"�","������������","��� ���"},
            {"�","������������"},{"�","������������"},{"�","������������"},
            {"�","������������"},{"�","������������"},{"�","������������"},
            {"�","������������"},{"�","������������"},{"�","������������"},
            {"�","������������"}};
        Class[][] columnclass = {{Integer.class,String.class,Integer.class,
            String.class,BigDecimal.class},{Integer.class,String.class,Integer.class,
            BigDecimal.class},{Integer.class,String.class},{Integer.class,String.class,String.class,
            Integer.class,BigDecimal.class,BigDecimal.class},
            {Integer.class,String.class,String.class,Integer.class,String.class,
            BigDecimal.class,BigDecimal.class,BigDecimal.class,Short.class,
            Short.class},{Integer.class,Short.class,BigDecimal.class},{Integer.class,
            Short.class,BigDecimal.class},{Integer.class,Short.class,BigDecimal.class},
            {Integer.class,Short.class,BigDecimal.class},{Integer.class,Short.class,
            BigDecimal.class},{Integer.class,String.class,BigDecimal.class},
            {Integer.class,String.class,Integer.class},{Integer.class,String.class},
            {Integer.class,String.class},{Integer.class,String.class},
            {Integer.class,String.class},{Integer.class,String.class},
            {Integer.class,String.class},{Integer.class,String.class},
            {Integer.class,String.class},{Integer.class,String.class}};
        // ��������� ������ ���������� ����
        mnuDispatchItem = new JMenuItem[nameItem.length];// ������ ������� ������� ����
        for(int i = 0; i < nameItem.length; i++){
            mnuDispatchItem[i] = new JMenuItem(tableItem[i]);
            Action mnudispAction = new Dispatch_Action(tableItem[i], 
                    columnname[i], columnclass[i], i);
            mnuDispatchItem[i] = mnuFileSpravochnik.add(mnudispAction);
            mnuDispatchItem[i].setText(nameItem[i]);
        }
        
        // ��������� �� � ������� ����
        for(int i = 0; i < 5; i++)
            mnuDevices.add(mnuDispatchItem[i]);
        for(int i = 5; i < 10; i++)
            mnuLoses.add(mnuDispatchItem[i]);
        for(int i = 10; i < 13; i++)
            mnuTowns.add(mnuDispatchItem[i]);
        for(int i = 13; i < nameItem.length; i++)
            mnuOther.add(mnuDispatchItem[i]);
        
        // ��������� ������ � ����
        mnuFileSpravochnik.add(mnuDevices);
        mnuFileSpravochnik.add(mnuLoses);
        mnuFileSpravochnik.add(mnuTowns);
        mnuFileSpravochnik.add(mnuOther);

        
    }
    
    /**
     * �����, ����������� ������ ������������, �� ������� ������������� �����
     * � ����������� � ����� ������������ ���� ������, ��������� �����������
     * � ��
     */
    private class StatusBar extends JToolBar{
        private String databaseName;
        private boolean stateConnection;
        private JLabel dbNameLabel;
        private JLabel stateLabel;

        public StatusBar(int alignment) {
            super(alignment);
            
            dbNameLabel = new JLabel(new 
                    ImageIcon(MDIObject.class.getClassLoader().getResource("Images/base.png")));
            stateLabel = new JLabel();
            stateLabel.setOpaque(true);
            // ��������� ����� �� ������, � ����� ���� ��������� �����������
            add(dbNameLabel);
            addSeparator();
            add(stateLabel);
        }

        /**
         * @return the databaseName
         */
        public String getDatabaseName() {
            return databaseName;
        }

        /**
         * @param databaseName the databaseName to set
         */
        public void setDatabaseName(String databaseName) {
            this.databaseName = databaseName;
            dbNameLabel.setText(databaseName);
        }

        /**
         * @return the stateConnection
         */
        public boolean getStateConnection() {
            return stateConnection;
        }

        /**
         * @param stateConnection the stateConnection to set
         */
        public void setStateConnection(boolean stateConnection) {
            this.stateConnection = stateConnection;
            
            // �������� ���� ���� �����
            if(stateConnection == true){
                stateLabel.setBackground(Color.yellow);
                stateLabel.setText("Connection is open");
            } else {
                stateLabel.setBackground(Color.red);
                stateLabel.setText("Connection is closed");
            }
        }
        
        
    }
    
    private class ConnectOptions{

        private String databaseName;
        private String hostIP;
        private String serverPort;
        private String username;
        private String Password;
        private String aliasName;
        private boolean accessOpen;// ���� �������� ������� � ���� ������
        
        public ConnectOptions() {
            accessOpen = false;// ������ ���� ������
        }

        /**
         * @return the databaseName
         */
        public String getDatabaseName() {
            return databaseName;
        }

        /**
         * @param databaseName the databaseName to set
         */
        public void setDatabaseName(String databaseName) {
            this.databaseName = databaseName;
        }

        /**
         * @return the hostIP
         */
        public String getHostIP() {
            return hostIP;
        }

        /**
         * @param hostIP the hostIP to set
         */
        public void setHostIP(String hostIP) {
            this.hostIP = hostIP;
        }

        /**
         * @return the serverPort
         */
        public String getServerPort() {
            return serverPort;
        }

        /**
         * @param serverPort the serverPort to set
         */
        public void setServerPort(String serverPort) {
            this.serverPort = serverPort;
        }

        /**
         * @return the username
         */
        public String getUsername() {
            return username;
        }

        /**
         * @param username the username to set
         */
        public void setUsername(String username) {
            this.username = username;
        }

        /**
         * @return the Password
         */
        public String getPassword() {
            return Password;
        }

        /**
         * @param Password the Password to set
         */
        public void setPassword(String Password) {
            this.Password = Password;
        }

        /**
         * @return the accessOpen
         */
        public boolean isAccessOpen() {
            return accessOpen;
        }

        /**
         * @param accessOpen the accessOpen to set
         */
        public void setAccessOpen(boolean accessOpen) {
            this.accessOpen = accessOpen;
        }
        
        /**
         * ���������� ���� ������� ��� ����������� � ���� ������
         */
        public void showLoginframe(){
            LoginFrame logframe = new LoginFrame();// ������ ��������� �����
            logframe.showDialog(MDIObject.this);// ���������� ���
            hostIP = logframe.getHostIP();
            serverPort = logframe.getServerPort();
            databaseName = logframe.getDatabaseName();
            username = logframe.getUserName();
            Password = logframe.getPassword();
            aliasName = logframe.getAliasName();
        }

        /**
         * @return the aliasName
         */
        public String getAliasName() {
            return aliasName;
        }

        /**
         * @param aliasName the aliasName to set
         */
        public void setAliasName(String aliasName) {
            this.aliasName = aliasName;
        }
    }
    
    /**
     * ���������� ���� �� ������
     * @param Width ������ ����
     * @param Height ������ ����
     * @return ����� � ������������, ���������������� ������������ ���� � ������ ������
     */
    public Point centerPoint(int Width, int Height){
        
        // �������� �������������� ��� ���������� �������� ������
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();// ���������� ������
        int screenWidth = (int) screenSize.getWidth();// ������ ������
        int screenHeight = (int) screenSize.getHeight();// ������ ������
        System.out.println("screenwidth=" + screenWidth + " screenheight=" + screenHeight);
        int left = (screenWidth - Width) / 2;
        int top = (screenHeight - Height) / 2;
        Point p = new Point(left, top);
        return p;
    }
    
    /**
     * ���������� �������� �����������
     */
    private void showMainFrame(int ID){
        if (mainframe == null){
            mainframe = new MainFrame();
            mainframe.setAlwaysOnTop(true);
            mainframe.setParentFrame(MDIObject.this);
            mainframe.setIdCompany(ID);
//            mainframe.setVisible(true);
            
//        }
        mainframe.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                super.windowOpened(e); //To change body of generated methods, choose Tools | Templates.
                // ��������� ����� ���� ���� ��������
                mnuFileCard.setEnabled(false);
//                    if(findframe != null){
//                        mainframe.getJMenuBar().getMenu(0).getItem(0).setEnabled(false);
//                    }
            }

            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e); //To change body of generated methods, choose Tools | Templates.
                // ������������ ����� ���� ���� ��������
                mnuFileCard.setEnabled(true);
                mainframe = null;
            }


        });
        mainframe.setVisible(true);
        } else
            mainframe.findCompany(ID);
        
    }
    
    /**
     * ���������� ���� ������
     */
    public void showFindFrame(){
        if (findframe == null){
            findframe = new FindFrame();
            findframe.setParentFrame(this);
            findframe.setAlwaysOnTop(true);
            findframe.setLocationRelativeTo(MDIObject.this);
//            findframe.setVisible(true);
//            findframe.addWindowListener(new WindowAdapter() {
//                @Override
//                public void windowOpened(WindowEvent e) {
//                    super.windowOpened(e); //To change body of generated methods, choose Tools | Templates.
//                    // ��������� ����� ���� ���� �����
//                    mnuFileFind.setEnabled(false);
//                    if(mainframe != null){
//                        mainframe.getJMenuBar().getMenu(0).getItem(0).setEnabled(false);
//                    }
//                }
//
//                @Override
//                public void windowClosing(WindowEvent e) {
//                    super.windowClosing(e); //To change body of generated methods, choose Tools | Templates.
//                    // ������������ ����� ���� ���� �����
//                    mnuFileFind.setEnabled(true);
//                    if(mainframe != null){
//                        mainframe.getJMenuBar().getMenu(0).getItem(0).setEnabled(true);
//                    }
//                }
//
//
//            });
            
        }
        findframe.setVisible(true);
    }
    
    /**
     * ������������ ����� ����������� �� � ����
     * @param ID ��� �����������, ����� ������� ��������������
     */
    public void findOrganization(int ID){
//        if (mainframe == null)
            showMainFrame(ID);// ���������� ������� �����, ���� ��� �� ����������
//        else        
//            mainframe.findCompany(ID);

        
    }
    
    /**
     * ���������� �����, ����������� ������ ������� � ������������ ��������������
     * ������� ������� � �������� ��������������� ��������� � ���� ������
     */
    public static class MyTableModelImpl extends MyTableModel {

        public MyTableModelImpl(ResultSet resultset) throws SQLException {
            super(resultset);
        }

        public MyTableModelImpl(Object[][] content, String[] columnName, Class[] columnClass) {
            super(content, columnName, columnClass);
        }

        public MyTableModelImpl(Object[][] content) {
            super(content);
        }
        
    }
    
    
     public static void fullTableData(int[] colIndex, final MyTableModel Model, 
            final JTable table){
        Model.setCellNoEditableList(colIndex);
        table.setModel(Model);
        Model.setTableColumnIdentifiers(table.getColumnModel());
    }
    
    public static void setTablecolwidth(UserProperties props, String propertyName, JTable table){
        // ��������� �� ����� ������� ������� �������� ������
        String colWidth = props.getProperty(propertyName);
//        System.out.println("name= " + propertyName + ", colwidth=" + colWidth);
        
        if(colWidth != null){
            // ������ ����� ��� ��������� �������� �������� ������
            TableProperty tprops = new TableProperty(colWidth, table);
            tprops.setDefaultWidth("75,75,75,75");
            tprops.setColWidth();// ����� ������ ��������
        }
    }
    
    public static void getInformDialog(JFrame owner, String message, InformDialog.InformType type){
        InformDialog idialog;
        if(owner != null){
            idialog = new InformDialog(owner);
        } else {
            idialog = new InformDialog();
        }
        idialog.setMessage(message);
        idialog.setType(type);
        idialog.setVisible(true);
    }
    
    public static JPopupMenu createPopupMenu(JTable table){
        JPopupMenu popupMenu = new JPopupMenu();
        
        // ��������� � ���� ������
        JMenuItem mnuAddNew = new JMenuItem("�������� ������");
        JMenuItem mnuRemove = new JMenuItem("������� ������");
        JMenuItem mnuUpdate = new JMenuItem("�������� ������");
        JMenuItem mnuExportDataXLS = new JMenuItem("������� ������� � ���� XLS");
        JMenuItem mnuExportDataODS = new JMenuItem("������� ������� � ���� ODS");
        JMenuItem mnuExportDataCSV = new JMenuItem("������� ������� � ���� CSV");
        JMenu mnuExportData = new JMenu("������� �������");
        JSeparator separator = new JSeparator(JSeparator.HORIZONTAL);
        
        
        // ��������� ������ � ����
        mnuExportData.add(mnuExportDataXLS);
        mnuExportData.add(mnuExportDataODS);
        mnuExportData.add(mnuExportDataCSV);
        popupMenu.add(mnuAddNew);
        popupMenu.add(mnuRemove);
        popupMenu.add(mnuUpdate);
        popupMenu.add(separator);
        popupMenu.add(mnuExportData);
        
        // ��������� ���� ��� �������
        table.setComponentPopupMenu(popupMenu);
        return popupMenu;
    }
    
    public static void addColumnSelectionListener(final JTable table){
        table.getColumnModel().getSelectionModel().addListSelectionListener(
                new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(table.getSelectedColumn() == 0)
                    table.setColumnSelectionInterval(1, 1);
            }
        });
    }
    
    public static Image getImage(String path) {
        URL url = MDIObject.class.getClassLoader().getResource("Images/" + path);
        Image image = Toolkit.getDefaultToolkit().createImage(url);
        return image;
    }
}
