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
 * @author Сергей
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
    private MainFrame mainframe;// главное окно предприятия
    private FindFrame findframe;// окно поиска
    private ArrayList mnuWindowItem;// массив для хранения пунктов меню,
                                      // соответствующих открытым дочерним окнам
    private StatusBar tbrAdminbar;
    private ConnectOptions connOptions;
    
    public MDIObject() {
        // добавляем события для формы
        WindowListener winListener;
        winListener = new WindowAdapter() {
            
            @Override
            public void windowOpened(WindowEvent we) {
                super.windowOpened(we);
                //задаём иконку для фрейма
                URL url;
                url = MDIObject.class.getClassLoader().getResource("Images/book.png");
                Image image;
                image = new ImageIcon(url).getImage();
                if (image != null) setIconImage(image);
                
                // устанавливаем развёрнутый вид
                setExtendedState(JFrame.MAXIMIZED_BOTH);
                
                // инициализируем массивы
                mnuWindowItem = new ArrayList();
                
                // создаём панель для дочерних форм
                createDesktopPane();
                add(dtPane);
                
                // создаём панель меню
                JMenuBar menuBar = new JMenuBar();
                
                // создаём меню Файл
                createMenuFile();
                
                // создаём меню Правка
                createMenuEdit();
                
                // создаём меню Выходные документы
                createMenuDocuments();
                
                // создаём меню Настройка
                createMenuSetup();
                
                // создаём меню Данные
                mnuData = new JMenu("Данные");
                mnuData.setMnemonic('Д');// клавиша быстрого доступа
                // заполняем его пунктами меню
                Action importAction = new Menu_Action("Импорт");
                Action exportAction = new Menu_Action("Экспорт");
                Action updateAction = new Menu_Action("Обновление");
                JMenuItem mnuDataImport = mnuData.add(importAction);
                JMenuItem mnuDataExport = mnuData.add(exportAction);
                JMenuItem mnuDataUpdate = mnuData.add(updateAction);

                // создаём меню Окна
                createMenuWindow();

                menuBar.add(mnuFile);
                menuBar.add(mnuEdit);
                menuBar.add(mnuDocuments);
                menuBar.add(mnuSetup);
//                menuBar.add(mnuData);
                menuBar.add(mnuWindows);

                // создаём и заполняем панель инструментов
                tbrAdminbar = new StatusBar(SwingConstants.HORIZONTAL);
                MDIObject.this.setJMenuBar(menuBar);

                // добавим строку состояния для отображения информации по подключению
                MDIObject.this.getContentPane().add(tbrAdminbar, BorderLayout.SOUTH);

                setVisibleMenuItem(true);// устанавливаем невидимость пунктов меню
                setEnabledMenu(false);// устанавливаем недоступность пунктов меню
                
                // создаём класс для получения доступа к базе данных
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
    
    //обработка закрытия окна
    private void closingWindow() {
        int button = JOptionPane.showConfirmDialog(this, "Хотите выйти?", 
                "AbonentGaz", JOptionPane.YES_NO_OPTION);
        if(button == JOptionPane.YES_OPTION){
            closeConnection();
            System.exit(0);
        }
    }

    // установка видимости пунктов меню
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
        // открываем соединение с базой данных
        //читаем файл свойств для загрузки драйвера и других параметров
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
                // открываем первоначальное соединение с базой данных
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
                // окно сообщения по результатам соединения
                getInformDialog(this, message, InformDialog.InformType.CONNECT);
                tbrAdminbar.setDatabaseName(connOptions.getAliasName());
                return retval;
            } catch (SQLException | ClassNotFoundException ex){
                // окно сообщения по результатам соединения
                getInformDialog(this, message, InformDialog.InformType.CONNECT);
                return false;
            }
        } else {
            // окно сообщения по результатам соединения
            getInformDialog(this, message, InformDialog.InformType.CONNECT);
            return false;
        }

            
    }
    
    private void closeConnection(){
        try {
            if (connect != null && !connect.isClosedConn()) {
            JDBCConnection.getConn().close();
            if(connect.isClosedConn()){
                // перед закрытием соединения проверяем загрузку главной формы организаций
                // и закрываем её, если она загружена
                if(mainframe != null) mainframe.setVisible(false);
                
                connect = null;
                mnuFileConnectionClose.setEnabled(false);
                mnuFileConnectionOpen.setEnabled(true);
                // отображаем окно сообщения закрытия соединения
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
                case "Открыть":
                    try {
                        boolean open = openConnection();
                        setEnabledMenu(open);
                        // отображаем на панели имя источника данных и состояние
                        // соединения
                        tbrAdminbar.setStateConnection(open);
                        if(open == true)
                            createMenuDispatch();// заполняем справочники
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(MDIObject.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException | SQLException | ClassNotFoundException ex) {
                        Logger.getLogger(MDIObject.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case "Закрыть":
                    closeConnection();
                    setEnabledMenu(false);
                    
                    break;
                case "Администрирование":
                    setVisibleMenuItem(false);
                    break;
                case "Выйти":
                    setVisibleMenuItem(true);
                    break;
                case "Пользователи":
                    break;
                case "Карточка":
                    // отображаем главное окно - карточку
                    showMainFrame(0);
                    break;
                case "Найти":
                    // отображаем окно поиска
                    showFindFrame();
                    break;
                case "Выход":
                    closingWindow();
                    break;
                case "Горизонтально":
                    AlignInternalFrame(1);
                    break;
                case "Вертикально":
                    AlignInternalFrame(2);
                    break;
                case "Каскадом":
                    AlignInternalFrame(3);
                    break;
                case "Импорт":
                    OperateFrame frmImport = new OperateFrame();
                    frmImport.setIdOperation(0);// идентификатор операции - импорт
                    frmImport.setParentFrame(MDIObject.this);
                    frmImport.setLocationRelativeTo(null);// центрируем форму
                    frmImport.setVisible(true);
                    break;
                case "Экспорт":
                    OperateFrame frmExport = new OperateFrame();
                    frmExport.setIdOperation(1);// идентификатор операции - импорт
                    frmExport.setParentFrame(MDIObject.this);
                    frmExport.setLocationRelativeTo(null);// центрируем форму
                    frmExport.setVisible(true);
                    break;
                case "Обновление":
                    OperateFrame frmUpdate = new OperateFrame();
                    frmUpdate.setIdOperation(2);// идентификатор операции - импорт
                    frmUpdate.setParentFrame(MDIObject.this);
                    frmUpdate.setLocationRelativeTo(null);// центрируем форму
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
                        // помечаем соответствующий пункт меню
                        selectedMNUWinItem(e.getActionCommand());
                    }

                    @Override
                    public void internalFrameOpened(InternalFrameEvent e) {
                        super.internalFrameOpened(e); //To change body of generated methods, choose Tools | Templates.
                        // помечаем соответствующий пункт меню справочников
//                        mnuDispatchItem[Index].setSelected(true);
                        
                        // и делаем его недоступным
                        mnuDispatchItem[Index].setEnabled(false);
                    }

                    @Override
                    public void internalFrameClosing(InternalFrameEvent e) {
                        super.internalFrameClosing(e); //To change body of generated methods, choose Tools | Templates.
                        // снимаем пометку с соответствующего пункта меню справочников
//                        mnuDispatchItem[Index].setSelected(false);
                        
                        // и делаем его доступным
                        mnuDispatchItem[Index].setEnabled(true);
                    }
                    
                    
                });
//                dframe.setResultSet(rs);
                dframe.setEditable(true);// режим редактирования
                
                // проверяем наличие других форм на панели
                JInternalFrame[] infArray = dtPane.getAllFrames();
                int count = infArray.length;
//                System.out.println("count=" + count);
                if(count > 0){
                    Point p = infArray[0].getLocation();// координаты местоположения последней формы
                    Point p1 = new Point();// координаты новой формы
                    p1.move(p.x + 30, p.y + 30);// задаём новые координаты для точки
                    dframe.setLocation(p1);
                }
                
                dframe.setVisible(true);// делаем видимим
                dtPane.add(dframe);// добавляем на панель
                
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
    
    // упорядочивание дочерних окон
    private void AlignInternalFrame(int choice){
        JInternalFrame[] allFrames = dtPane.getAllFrames();
        // проверяем размер массива : если в нём есть элементы и их количество
        // больше 1 (отображено только одно окно), тогда выполняем упорядочивание
        if(allFrames.length > 1) {
            switch (choice) {
                case 1:
                    {
                        // выравнивание дочерних окон по горизонтали
                        // ширина всех окон корректируеся по ширине панели рабочего стола
                        // высота всех окон принимается равной высоте рабочего стола
                        int dWidth = (int) dtPane.getWidth() / allFrames.length;
                        int dHeight = dtPane.getHeight();
                        int dX = 0;// начальнфя координата сдвига по горизонтали
                    for (JInternalFrame allFrame : allFrames) {
                        allFrame.setBounds(dX, 0, dWidth, dHeight);
                        dX = dX + dWidth;// увелививаем координату сдвига по горизонтали
                    }
                    break;
                    }
                case 2:
                    {
                        // выравнивание дочерних окон по вертикали
                        // высота всех окон корректируеся по высоте панели рабочего стола
                        // ширина всех окон принимается равной ширине рабочего стола
                        int dHeight = (int) dtPane.getHeight() / allFrames.length;
                        int dWidth = dtPane.getWidth();
                        int dY = 0;// начальнфя координата сдвига по горизонтали
                    for (JInternalFrame allFrame : allFrames) {
                        allFrame.setBounds(0, dY, dWidth, dHeight);
                        dY = dY + dHeight;// увелививаем координату сдвига по вертикали
                    }
                    break;
                    }
                default:
                    {
                        // выравнивание дочерних окон каскадом
                        // положение всех окон корректируется как по высоте, так и по
                        // горизонтали так, чтобы они все поместилисть на рабочем столе
                        int dHeight = (int) (dtPane.getHeight() - 30 * allFrames.length);
                        int dWidth = (int) (dtPane.getWidth() - 30 * allFrames.length);
                        int dX = 0;// начальнфя координата сдвига по горизонтали
                        int dY = 0;// начальнфя координата сдвига по горизонтали
                        for(int i = allFrames.length - 1; i >= 0; i--){
                            allFrames[i].setBounds(dX, dY, dWidth, dHeight);
                            dX = dX + 30;// увелививаем координату сдвига по горизонтали
                            dY = dY + 30;// увелививаем координату сдвига по вертикали
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
//            setUndecorated(true);// отображаем без рамки
//            setAlwaysOnTop(true);// поверх всех окон
//            setLayout(new BorderLayout());
//            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//            setModal(true);
//            Toolkit kit = getToolkit();
//            Dimension screenSize = kit.getScreenSize();
//            int screenHeight = screenSize.height;
//            int screenWidth = screenSize.width;
//            // размеры окна сообщения
//            setSize(200, 50);
//            // отображаем форму посередине экрана
//            setLocation((screenWidth - 200)/2, (screenHeight - 50)/2);
//            //добавляем панель для вывода сообщения
//            JPanel messagePanel = new JPanel();
//            messagePanel.setLayout(new BorderLayout());
////            // цвет фона панели
////            messagePanel.setBackground(dtPane.getBackground());
//            JLabel messageLabel= new JLabel(message);
//            
//            // размер метки по размеру панели
//            messageLabel.setSize(messagePanel.getWidth(), messagePanel.getHeight());
//            
//            // выравниваание текста по центру метки
//            messageLabel.setHorizontalAlignment(JLabel.CENTER);
//            messageLabel.setFont(new Font("Serif", Font.BOLD, 14));// шрифт
//            
//            // цвет обрамления
//            messageLabel.setBorder(BorderFactory.createLineBorder(Color.magenta));
//            
//            messagePanel.add(messageLabel, BorderLayout.CENTER);// добавляем метку
//            getContentPane().add(messagePanel, BorderLayout.CENTER);// и панель
////            pack();
//            // добавляем таймер
//            Timer t = new Timer(750, new ActionListener() {
//
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    InformFrame.this.dispose();
//                }
//            });
//            t.start();// запускаем таймер
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
        // помечаем выбранный пункт меню
        selectedMNUWinItem(name);
//        iframeList[j].setFocusable(true);
    }
    
    private void selectedMNUWinItem(String name){
        // помечаем выбранный пункт меню
        for (int i = 4; i < mnuWindows.getItemCount(); i++){
            if(mnuWindows.getItem(i).getText().equals(name)){
                mnuWindows.getItem(i).setSelected(true);
            } else {
                mnuWindows.getItem(i).setSelected(false);
            }
        }
    }
    
    /**
     * установка доступности некоторых пунктов меню
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
     * создаёт панель для размещения дочерних окон
     */
    private void createDesktopPane(){
        dtPane = new JDesktopPane();// панель для размещения дочерних окон
        dtPane.setBackground(SystemColor.inactiveCaption);
        // добавляем к ней обработчик событий
        dtPane.addContainerListener(new ContainerAdapter() {

            @Override
            public void componentAdded(ContainerEvent e) {
                super.componentAdded(e);
                // при добавлении дочерней формы добавляем соответ-
                // ствующий пункт меню с её именем
                JCheckBoxMenuItem mnuwinItem = 
                        new JCheckBoxMenuItem(e.getChild().getName());

                mnuwinItem.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            // устанавливаем фокус на выбранной форме
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
                // при удалении дочерней формы удаляем соответствующий
                // ей пункт меню
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
     * создание и заполнение меню Файл
     */
    private void createMenuFile(){
        // создаём меню Файл
        mnuFile = new JMenu("Файл");
        mnuFile.setMnemonic('Ф');// клавиша быстрого доступа
        // добавляем в него подпункты
        JMenu mnuFileConnection = new JMenu("Соединение");
        Action openAction = new Menu_Action("Открыть");
        mnuFileConnectionOpen = mnuFileConnection.add(openAction);
        mnuFileConnectionOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, 
                InputEvent.CTRL_MASK));// клавиши быстрого доступа Ctrl+O
        mnuFileConnectionOpen.setIcon(new ImageIcon(MDIObject.class.getClassLoader().getResource("Images/online16.png")));

        Action closeAction = new Menu_Action("Закрыть");
        mnuFileConnectionClose = mnuFileConnection.add(closeAction);
        mnuFileConnectionClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, 
                InputEvent.CTRL_MASK));// клавиши быстрого доступа Ctrl+T
        mnuFileConnectionClose.setIcon(new ImageIcon(MDIObject.class.getClassLoader().getResource("Images/padlock16.png")));
        mnuFileConnectionClose.setEnabled(false);// меню Закрыть недоступно
        mnuFile.add(mnuFileConnection);

        Action cardAction = new Menu_Action("Карточка");
        Action findAction = new Menu_Action("Найти");
        Action exitAction = new Menu_Action("Выход");

//        mnuFileUser = mnuFile.add(userAction);
        mnuFileCard = mnuFile.add(cardAction);
        mnuFileCard.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, 
                InputEvent.CTRL_MASK));// клавиши быстрого доступа
        mnuFileCard.setIcon(new ImageIcon(MDIObject.class.getClassLoader().getResource("Images/book.png")));
        mnuFileFind = mnuFile.add(findAction);
        mnuFileFind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, 
                InputEvent.CTRL_MASK));// клавиши быстрого доступа
        mnuFileFind.setIcon(new ImageIcon(MDIObject.class.getClassLoader().getResource("Images/camera.png")));
        mnuFileSpravochnik = new JMenu("Справочники");
        mnuFile.add(mnuFileSpravochnik);

        mnuFile.add(exitAction);
                
    }
    
    /**
     * создаёт меню Правка
     */
    private void createMenuEdit(){
        mnuEdit = new JMenu("Правка");
        mnuEdit.setMnemonic('П');// клавиша быстрого доступа

        JMenuItem mnuEditAddNewRecord = new JMenuItem("Добавить запись");
        JMenuItem mnuEditDeleteRecord = new JMenuItem("Удалить запись");
        JMenuItem mnuEditSaveRecord = new JMenuItem("Сохранить");
        JMenuItem mnuEditRefreshRecord = new JMenuItem("Обновить записи");
        mnuEdit.add(mnuEditAddNewRecord);
        mnuEdit.add(mnuEditDeleteRecord);
        mnuEdit.add(mnuEditSaveRecord);
        mnuEdit.add(mnuEditRefreshRecord);
    }
    
    /**
     * создаёт меню Выходные документы
     */
    private void createMenuDocuments(){
        mnuDocuments = new JMenu("Выходные документы");
        mnuDocuments.setMnemonic('к');// клавиша быстрого доступа
        JMenuItem mnuDocReports = new JMenuItem("Отчёты");
        JMenuItem mnuDocWarning = new JMenuItem("Уведомления");
        JMenuItem mnuDocPrevLose = new JMenuItem("Предварительный расчёт потерь");
        
        // добавляем действия к меню
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
        // добавляем пункты к меню
        mnuDocuments.add(mnuDocReports);
        mnuDocuments.add(mnuDocWarning);
        mnuDocuments.add(mnuDocPrevLose);
    }
    
    /**
     * создаёт меню Настройки
     */
    private void createMenuSetup(){
        mnuSetup = new JMenu("Настройка");
        mnuSetup.setMnemonic('Н');// клавиша быстрого доступа
        JMenuItem mnuSetupOpen = new JMenuItem("Открыть");
        mnuSetupOpen.setIcon(new ImageIcon(MDIObject.class.getClassLoader().getResource("Images/settings16.png")));
        
        JMenuItem mnuSetupClear = new JMenuItem("Сброс настроек");
        mnuSetupClear.setIcon(new ImageIcon(MDIObject.class.getClassLoader().getResource("Images/letter.png")));
        
        // добавляем слушатели
        mnuSetupOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // показываем окно настроек приложения
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
     * создаёт меню Окна
     */
    private void createMenuWindow(){
        mnuWindows = new JMenu("Окна");
        Action horAction = new Menu_Action("Горизонтально");
        Action vertAction = new Menu_Action("Вертикально");
        Action cascAction = new Menu_Action("Каскадом");
        mnuWindows.add(horAction);
        mnuWindows.add(vertAction);
        mnuWindows.add(cascAction);
        JPopupMenu.Separator jSeparator = new JPopupMenu.Separator();
        mnuWindows.add(jSeparator);// добавляем разделитель в меню Окна
                
    }
    
    /**
     * создаёт и заполняет меню справочников
     */
    private void createMenuDispatch(){
        
        // разделяем меню справочников на категории
        JMenu mnuDevices = new JMenu("КИП и оборудование");
        JMenu mnuLoses = new JMenu("Потери на газопроводах");
        JMenu mnuTowns = new JMenu("Населённые пункты");
        JMenu mnuOther = new JMenu("Прочее");
        
        // массив названий подпунктов меню
        String[] nameItem = {"Датчики", "Корректора", "Производитель",
        "Оборудование", "Счётчики", "высокого давления",
        "среднего давления", "распределительных низкого давления",
        "дворовых низкого давления", "регуляторы", "Города", "ГРС", "Улицы",
        "Запорная арматура", "Банк", "Год", "Категория", "Контролёры", 
        "Место установки пломб", "Подрядчик", "Причина"};
        
        // массив соответствующих названий таблиц базы данных
        String[] tableItem = {"sprdatchik", "sprcorrector", "sprmaker",
        "sprequipment", "sprcounter", "sprgazlinerhipress",
        "sprgazlinermidpress", "sprgazlinerrlowpress",
        "sprgazlinerdlowpress", "sprgazregulator", "sprcity", "sprgrs", "sprstreet",
        "sprarmatura", "sprbank", "spryear", "sprcategory", "sprcontrolers", 
        "sprplumbsinstalplace", "sprpodryadchik", "sprcase"};
        
        // массив наименований столбцов, заменяющих названия столбцов таблиц
        // из базы данных
        String[][] columnname = {{"№","Наименование","Код Производителя",
            "Диапазон","Класс"},{"№","Наименование","Код Производителя",
            "Класс точности"},{"№","Наименование"},{"№","Наименование","Тип",
            "Код Производителя","Мощность, кВт","Расход"},{"№","Тип","Наименование",
            "Код Производителя","Регион","Q min","Q nom","Q max","DN","Класс"},
            {"№","Диаметр, мм","Потери"},{"№","Диаметр, мм","Потери"},
            {"№","Диаметр, мм","Потери"},{"№","Диаметр, мм","Потери"},
            {"№","Наименование","Потери"},{"№","Наименование","Код ГРС"},
            {"№","Наименование"},{"№","Наименование"},{"№","Наименование"},
            {"№","Наименование"},{"№","Наименование"},{"№","Наименование"},
            {"№","Наименование"},{"№","Наименование"},{"№","Наименование"},
            {"№","Наименование"}};
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
        // заполняем массив подпунктов меню
        mnuDispatchItem = new JMenuItem[nameItem.length];// размер массива пунктов меню
        for(int i = 0; i < nameItem.length; i++){
            mnuDispatchItem[i] = new JMenuItem(tableItem[i]);
            Action mnudispAction = new Dispatch_Action(tableItem[i], 
                    columnname[i], columnclass[i], i);
            mnuDispatchItem[i] = mnuFileSpravochnik.add(mnudispAction);
            mnuDispatchItem[i].setText(nameItem[i]);
        }
        
        // добавляем их к пунктам меню
        for(int i = 0; i < 5; i++)
            mnuDevices.add(mnuDispatchItem[i]);
        for(int i = 5; i < 10; i++)
            mnuLoses.add(mnuDispatchItem[i]);
        for(int i = 10; i < 13; i++)
            mnuTowns.add(mnuDispatchItem[i]);
        for(int i = 13; i < nameItem.length; i++)
            mnuOther.add(mnuDispatchItem[i]);
        
        // добавляем пункты в меню
        mnuFileSpravochnik.add(mnuDevices);
        mnuFileSpravochnik.add(mnuLoses);
        mnuFileSpravochnik.add(mnuTowns);
        mnuFileSpravochnik.add(mnuOther);

        
    }
    
    /**
     * класс, расширяющий панель инструментов, на которой располагаются метки
     * с информацией о имени подключенной базы данных, состоянии подключения
     * и др
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
            // размещаем метки на панели, а между ними размещаем разделитель
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
            
            // изменяем цвет фона метки
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
        private boolean accessOpen;// флаг открытия доступа к базе данных
        
        public ConnectOptions() {
            accessOpen = false;// доступ пока закрыт
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
         * отображает окно доступа для подключения к базе данных
         */
        public void showLoginframe(){
            LoginFrame logframe = new LoginFrame();// создаём экземпляр формы
            logframe.showDialog(MDIObject.this);// показываем его
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
     * центрирует окно на экране
     * @param Width ширина окна
     * @param Height высота окна
     * @return точку с координатами, соответствующими расположению окна в центре экрана
     */
    public Point centerPoint(int Width, int Height){
        
        // получаем инструментарий для вычисления размеров экрана
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();// разрешение экрана
        int screenWidth = (int) screenSize.getWidth();// ширина экрана
        int screenHeight = (int) screenSize.getHeight();// высота экрана
        System.out.println("screenwidth=" + screenWidth + " screenheight=" + screenHeight);
        int left = (screenWidth - Width) / 2;
        int top = (screenHeight - Height) / 2;
        Point p = new Point(left, top);
        return p;
    }
    
    /**
     * показываем карточку организации
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
                // блокируем пункт меню Файл Карточка
                mnuFileCard.setEnabled(false);
//                    if(findframe != null){
//                        mainframe.getJMenuBar().getMenu(0).getItem(0).setEnabled(false);
//                    }
            }

            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e); //To change body of generated methods, choose Tools | Templates.
                // разблокируем пункт меню Файл Карточка
                mnuFileCard.setEnabled(true);
                mainframe = null;
            }


        });
        mainframe.setVisible(true);
        } else
            mainframe.findCompany(ID);
        
    }
    
    /**
     * отображает окно поиска
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
//                    // блокируем пункт меню Файл Найти
//                    mnuFileFind.setEnabled(false);
//                    if(mainframe != null){
//                        mainframe.getJMenuBar().getMenu(0).getItem(0).setEnabled(false);
//                    }
//                }
//
//                @Override
//                public void windowClosing(WindowEvent e) {
//                    super.windowClosing(e); //To change body of generated methods, choose Tools | Templates.
//                    // разблокируем пункт меню Файл Найти
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
     * осуществляет поиск организации по её коду
     * @param ID код организации, поиск которой осуществляется
     */
    public void findOrganization(int ID){
//        if (mainframe == null)
            showMainFrame(ID);// показываем главную форму, если она не отображена
//        else        
//            mainframe.findCompany(ID);

        
    }
    
    /**
     * внутренний класс, реализующий модель таблицы с созможностью редактирования
     * записей таблицы и внесения соответствующих изменений в базу данных
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
        // считываем из файла свойств размеры столбцов таблиц
        String colWidth = props.getProperty(propertyName);
//        System.out.println("name= " + propertyName + ", colwidth=" + colWidth);
        
        if(colWidth != null){
            // создаём класс для установки размеров столбцов таблиц
            TableProperty tprops = new TableProperty(colWidth, table);
            tprops.setDefaultWidth("75,75,75,75");
            tprops.setColWidth();// задаём ширину столбцов
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
        
        // добавляем к нему пункты
        JMenuItem mnuAddNew = new JMenuItem("Добавить запись");
        JMenuItem mnuRemove = new JMenuItem("Удалить запись");
        JMenuItem mnuUpdate = new JMenuItem("Обновить данные");
        JMenuItem mnuExportDataXLS = new JMenuItem("экспорт таблицы в файл XLS");
        JMenuItem mnuExportDataODS = new JMenuItem("экспорт таблицы в файл ODS");
        JMenuItem mnuExportDataCSV = new JMenuItem("экспорт таблицы в файл CSV");
        JMenu mnuExportData = new JMenu("Экспорт таблицы");
        JSeparator separator = new JSeparator(JSeparator.HORIZONTAL);
        
        
        // добавляем пункты к меню
        mnuExportData.add(mnuExportDataXLS);
        mnuExportData.add(mnuExportDataODS);
        mnuExportData.add(mnuExportDataCSV);
        popupMenu.add(mnuAddNew);
        popupMenu.add(mnuRemove);
        popupMenu.add(mnuUpdate);
        popupMenu.add(separator);
        popupMenu.add(mnuExportData);
        
        // назначаем меню для таблицы
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
