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

    private JTable table;// таблица для вывода перечня предприятий 
    private JComboBox categoryCombo;// список категорий предприятий
    private JComboBox warningCombo;// список типов уведомлений
    private JLabel contentLabel;// метка для вывода структуры соответствующего файла уведомлений
    
    public WarningFrame() throws HeadlessException {
        setTitle("Уведомления");// заголовок окна
        // создаём компоненты пользовательского интерфейса
        initComponents();
        // определяем размеры экрана и задаём размеры формы в половину экрана
        Toolkit kit = getToolkit();
        Dimension size = kit.getScreenSize();
        setSize((int)size.getWidth() / 2, (int)size.getHeight() / 2);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    /**
     * Инициализация компонентов пользовательского интерфейса
     */
    private void initComponents(){
        // создаём меню
        addMenuItem();
        
        JPanel mainPanel = new JPanel(new BorderLayout());// главная панель для расположения компонентов
        JPanel comboPanel = new JPanel(new BorderLayout());// на этой панели размещаем списки и метку
        comboPanel.setBorder(new TitledBorder(new LineBorder(Color.DARK_GRAY, 1), "Уведомления"));
        Box listBox = Box.createHorizontalBox();
        JLabel lblCategory = new JLabel("Категория");
        JLabel lblType  = new JLabel("Тип уведомления");
        
        categoryCombo = new JComboBox(categoryModel());
        // добавляем слушатель
        categoryCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                contentLabel.setText(getContent());
            }
        });
        
        Object[] warning = new Object[]{"о госповерке","о задолженности","об отключении"};
        warningCombo = new JComboBox(warning);
        // добавляем слушатель
        warningCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                contentLabel.setText(getContent());
            }
        });
        
        // добавляем списки и метки на панель
        listBox.add(Box.createHorizontalStrut(10));
        listBox.add(lblCategory);
        listBox.add(Box.createHorizontalStrut(10));
        listBox.add(categoryCombo);
        listBox.add(Box.createHorizontalStrut(20));
        listBox.add(lblType);
        listBox.add(Box.createHorizontalStrut(10));
        listBox.add(warningCombo);
        listBox.add(Box.createHorizontalStrut(30));
        listBox.add(contentLabel = new JLabel("Структура:"));
        comboPanel.add(listBox, BorderLayout.WEST);
        
        // добавляем таблицу
        JScrollPane pane = new JScrollPane(table = new JTable(4, 4));
        Box vertBox = Box.createVerticalBox();
        vertBox.add(Box.createVerticalStrut(10));
        vertBox.add(pane);
        
        // добавляем компоненты на главную панель
        mainPanel.add(comboPanel, BorderLayout.NORTH);
        mainPanel.add(vertBox, BorderLayout.CENTER);
        getContentPane().add(mainPanel);
    }
    
    /**
     * Добавление и определение действий для меню
     */
    private void addMenuItem(){
        // создаём меню Файл
        JMenu mnuFile = new JMenu("Файл");
        JMenuItem mnuFileOpen = new JMenuItem("Открыть");
        // добавляем слушатель
        mnuFileOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                openFile(); //To change body of generated methods, choose Tools | Templates.
            }
        });
        JMenuItem mnuFilePrint = new JMenuItem("Печать");
        JMenuItem mnuFileExit = new JMenuItem("Выход");
        // добавляем слушатель
        mnuFileExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);// закрываем форму
            }
        });
        mnuFile.add(mnuFileOpen);
        mnuFile.add(mnuFilePrint);
        mnuFile.add(mnuFileExit);
        
        // Создаём меню Правка
        JMenu mnuEdit = new JMenu("Правка");
        JMenu mnuEditRow = new JMenu("Строка");
        JMenu mnuEditColumn = new JMenu("Столбевц");
        JMenuItem mnuRowInsert = new JMenuItem("Вставить");
        JMenuItem mnuRowRemove = new JMenuItem("Удалить строку");
        JMenuItem mnuColumnInsertBefore = new JMenuItem("Вставить перед");
        JMenuItem mnuColumnInsertAfter = new JMenuItem("Вставить после");
        JMenuItem mnuColumnRemove = new JMenuItem("Удалить столбец");
        JMenu mnuPreview = new JMenu("Предварительный просмотр");
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
     * Создаёт модель списка категорий предприятий
     * @return модель для заполнения списка
     */
    private ComboBoxModel categoryModel(){
        SprcategoryDaoImpl sdi = new SprcategoryDaoImpl();// объект доступа к списку категорий
        Object[] content = new Object[sdi.getCount()];
        for(int i = 0; i < content.length; i++)
            content[i] = sdi.getItem(i).getCategoryName();
        ComboBoxModel model = new DefaultComboBoxModel(content);
        return model;
    }
    
    
    /**
     * Отображает окно для выбора файла загрузки
     */
    private void openFile(){
        JFileChooser chooser  = new JFileChooser();// окно аыбора файла
        chooser.setDialogType(JFileChooser.OPEN_DIALOG);
        // chooser.showOpenDialog(this);
        if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
            System.out.println(chooser.getSelectedFile().getName());
    }
    
    /**
     * Возвращает структуру файла уведомления в зависимости от индекса
     * @return строку, представляющую структуру файла уведомления
     */
    private String getContent() {
        return "Структура: " + categoryCombo.getSelectedItem().toString() + " " +
                warningCombo.getSelectedItem().toString();
        
    }
}
