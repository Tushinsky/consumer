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
 * Диалоговое окно настроек программы
 * @author Сергей
 */
public class SetupDialog extends JPanel {

    private boolean ok;
    private JDialog dialog;// окно диалога, в котором будут отображаться элементы
    private boolean textChange = false;// этот флаг будет отслеживать изменения в полях ввода
    private UserProperties props;// класс для чтения из файла свойств
    
    public SetupDialog() {
        // создаём элементы интерфейса
        JPanel panel = new JPanel();// панель для размещения элементов упрвления
//        panel.setBackground(Color.yellow);
        // метки
        JLabel hostLabel = new JLabel("IP адрес сервера");
        JLabel portLabel = new JLabel("Номер порта");
        JLabel databaseLabel = new JLabel("База данных");
        JLabel templateLabel = new JLabel("Шаблоны");
        JLabel loginLabel = new JLabel("Логин");
        
        // слушатель изменений содержимого полей ввода
        final TextFieldListener listener = new TextFieldListener();
        
        // поле ввода адреса сервера
        final JTextField txtHostIP = new JTextField(10);
        txtHostIP.getDocument().addDocumentListener(listener);
        // поле ввода номера порта сервера
        final JTextField txtServerPort = new JTextField(10);
        txtServerPort.getDocument().addDocumentListener(new TextFieldListener());
        // поле ввода логина пользователя
        final JTextField txtUserLogin = new JTextField(15);
        txtUserLogin.getDocument().addDocumentListener(new TextFieldListener());
        // список баз данных
        final JComboBox databaseCombo = new JComboBox(readAliasName());
        databaseCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // при выборе элемента списка считываем свойства подключения из файла свойств
                String[] connectprops = readConnectProperties(databaseCombo.getSelectedItem().toString());
                txtHostIP.setText(connectprops[0]);
                txtServerPort.setText(connectprops[1]);
                txtUserLogin.setText(connectprops[2]);
                
                textChange = false;// сбрасываем флаг изменений
            }
        });
        props = new UserProperties("connectproperties.properties");
        
        // поле ввода расположения шаблонов
        final JTextField txtTemplate = new JTextField(40);
        
        // кнопки подтверждения и отмены ввода
        JButton okButton = new JButton("ОК");// выполняет запись изменений в файл
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                JOptionPane.showMessageDialog(dialog, 
                        "textChange = " + textChange, "AbonentGaz", 
                        JOptionPane.OK_OPTION);
                // если изменения проводились, записываем изменения в файл свойств
                if(textChange) {
                    // массив свойств
                    String[] properties = new String[4];
                    properties[0] = txtHostIP.getText();
                    properties[1] = txtServerPort.getText();
                    properties[2] = txtUserLogin.getText();
                    properties[3] = txtTemplate.getText();
                    // записываем их в файл
                    writeConnectProperties(databaseCombo.getSelectedItem().toString(), 
                            properties);
                }
//                dialog.setVisible(false);
            }
        });
        
        JButton cancelButton = new JButton("Закрыть");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
            }
        });
        
        // кнопка для отображения окна выбора файла
        JButton openFileButton = new JButton("Открыть", new ImageIcon(MDIObject.class.getClassLoader().getResource("Images/OpenFile.png")));
        openFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // показываем окно выбора файла
                JFileChooser chooser = new JFileChooser(new File("."));
                // выбираем только папки
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int select = chooser.showOpenDialog(dialog);
                // проверяем выбор пользователя
                if(select == JFileChooser.APPROVE_OPTION){
                        // если пользователь сделал выбор, выводим путь в поле ввода
                        txtTemplate.setText(chooser.getSelectedFile().getPath());
                    
                }
            }
        });
        // размер кнопки
//        openFileButton.setSize(openFileButton.getPreferredSize());
        
        // располагаем элементы на панели
        // метки и поля ввода для номера порта и адреса сервера
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
        
        // метки, список с именами баз данных и поле ввода имени пользователя
        baseLabelBox.add(databaseLabel);
        baseLabelBox.add(Box.createVerticalStrut(10));
        baseLabelBox.add(loginLabel);
        baseTextBox.add(databaseCombo);
        baseTextBox.add(Box.createVerticalStrut(10));
        baseTextBox.add(txtUserLogin);
        baseBox.add(baseLabelBox);
        baseBox.add(Box.createHorizontalStrut(10));
        baseBox.add(baseTextBox);
        
        // метка, поле ввода и кнопка для ввода пути к шаблонам
        templateBox.add(templateLabel);
        templateBox.add(Box.createHorizontalStrut(10));
        templateBox.add(txtTemplate);
        templateBox.add(Box.createHorizontalStrut(10));
        templateBox.add(openFileButton);
        
        // кнопки записи и закрытия формы
        buttonBox.add(okButton);
        buttonBox.add(Box.createHorizontalStrut(20));
        buttonBox.add(cancelButton);
        
        // контейнер для расположения
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
        // добавляем его на панель
        panel.add(box);
        super.add(panel);
        
        // выделяем первый элемент в списке баз данных
        databaseCombo.setSelectedIndex(0);
        txtTemplate.setText(props.getProperty("template"));
        txtTemplate.getDocument().addDocumentListener(new TextFieldListener());
    }
    
    /**
     * Выводит панель для выбора в диалоговом окне.
     * @param owner компонент в собственном фрейме или null
     * @return 
     */
    public boolean showDialog(Component owner){
        ok = false;

        // находим собственный фрейм
        Frame parent;
        if(owner instanceof Frame)
            parent = (Frame) owner;
        else
            parent = (Frame) SwingUtilities.getAncestorOfClass(Frame.class, owner);

        // если фрейм только что создан или был изменён,
        // создаём новое диалоговое окно
        if(dialog == null || dialog.getOwner() != parent){
            dialog = new JDialog(parent, true);
            dialog.getContentPane().add(this);
            dialog.setType(Window.Type.POPUP);// тип окна - всплывающий
            dialog.setAlwaysOnTop(true);// отображение поверх всех окон
            dialog.pack();// упаковываем всё содержимое
            dialog.setResizable(false);// изменение размеров не допускается
            // задаём иконку для окна
            dialog.setIconImage(MDIObject.getImage("settings16.png"));
        }

 
        // задаём заголовок и выводим диалог на экран
        dialog.setTitle("Настройки");

        // задаём размеры
 //            dialog.setSize(flManager.getWidth(), flManager.getHeight());

        // задаём расположение в центре вызывающей формы
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
        return ok;// возвращаем результат выбора
    }
    
    private Object[] readAliasName(){
        // создаем класс для чтения из файла свойств
        props = new UserProperties("aliases.properties");
        
        // пытаемся прочитать наименования баз данных, разделитель ";"
        String[] alias = props.getProperty("aliasname").split(";");
        
        return alias;
    }
    
    /**
     * Считывает параметры соединения из файла свойств
     * @param pathname наименование раздела для чтения
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
     * Класс реализует слушатель документа для отселживания изменений в полях ввода
     */
    private class TextFieldListener implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
            // возвращаем TRUE если происходит вставка в поле ввода
            textChange = true;
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            // возвращаем TRUE если происходит удаление из поля ввода
            textChange = true;
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            
        }
        
    }
}
