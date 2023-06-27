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
    private JPanel gazlinePanel;// панель с элементами интерфейса для выбора газопроводов
    private JPanel gazregPanel;// панель с элементами интерфейса для выбора регуляторов
    private CardLayout cardLO;// менеджер карточной компоновки
    private JButton addButton;// кнопка добавления новой записи в таблицу
    private JButton removeButton;// кнопка удаления текущей записи из таблицы
    private JButton calcButton;// кнопка расчёта потерь
    private JButton prevButton;// кнопка предварительного просмотра акта
    private final GazLiner gazLiner;
    private final GazRegulator gazRegulator;
    private final List<Object[]> ResultLose;
    private final Object[] lineTitleContent = new Object[]{"Газопроводы","Диаметр, мм",
        "Потери, м3/(сут км)","Длина, км","Возраст до 25 лет (да/нет)",
        "Подраб терр-рии (дат/нет)","Всего, м3/сут"};// заголовки для газопроводов
    private final Object[] regTitleContent = new Object[]{"Тип регулятора","",
        "","","Потери, м3/сут", "Количество, шт","Всего, м3/сут"};// заголовки для регуляторов
    private boolean isLoseAdded = false;// флаг на добавление расчитанных потерь
    // модель таблицы с поддержкой функции объединения ячеек
    private AttributiveCellTableModel attrModel;
    // содержимое для добавления в таблицу
    private final Object[] tableContent;
    private JComboBox lineCombo;
    private JComboBox diametrCombo;
    private JTextField txtLose;// поле для вывода потерь
    private JCheckBox ageCheck;
    private JCheckBox territCheck;
    private JTextField txtLenght;
    private JTextField txtResult;
    private JComboBox regCombo;
    private JTextField txtRegLose;
    private JTextField txtCount;// поле для ввода количества регуляторов
    private JTextField txtRegResult;
    private final String filename = "prevloseframe.properties";
    private ColumnModelListener columnLis6tener;
    
    public PrevloseFrame() throws HeadlessException {
        this.tableContent = new Object[7];
        setTitle("Предварительный расчёт технологических потерь");// заголовок окна
        gazLiner = new GazLiner();
        gazRegulator = new GazRegulator();
        ResultLose = new ArrayList<>();
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
     * инициализация компонентов пользовательского интерфейса,
     * поле ввода наименования предприятия,
     * поле ввода наименования и адреса объекта,
     * поле ввода исходный данных для расчёта потерь,
     * переключатели для выбора газопроводов или регуляторов,
     * списки для выбора типов газопроводов или регуляторов,
     * список выбора диаметров газопроводов,
     * флажки для отметки возраста газопроводов и местоположения,
     * поле для вывода потерь,
     * кнопки расчёта потерь, добавления новой записи в таблицу,
     * удаления записи из таблицы, предваврительного просмотра,
     * таблица для вывода потерь,
     * поле ввода длины газопровода или количества регуляторов
     */
    private void initComponents() {
        // наименование, объект, исходные данные
        // размещаем их на панелях
        Box box = Box.createHorizontalBox();
        box.add(Box.createHorizontalStrut(10));
        box.add(createLabelNamePanel());
        box.add(Box.createHorizontalStrut(10));
        box.add(createTextNamePanel());
        box.add(Box.createHorizontalGlue());
        box.add(Box.createHorizontalStrut(10));
//        box.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        
        // панель для размещения переключателей и элементов интерфейса для выбора
        // газопроводов или регуляторов
        groupPanel = new JPanel(new BorderLayout(5, 5));
//        groupPanel.add(groupBox, BorderLayout.WEST);
        gazPanel = new JPanel();
        cardLO = new CardLayout();// менеджер карточной компоновки
        gazPanel.setLayout(cardLO);// назначаем его для панели
        gazregPanel = createGazRegPanel();
        gazlinePanel = createGazLinePanel();
        gazPanel.add(gazlinePanel, "GazLiner");
        gazPanel.add(gazregPanel, "Regulator");
        
        // переключатели
        Box groupBox = createRadioButtonBox();
        
        // кнопки
        JPanel buttonPanel = createCommandButtonPanel();// панель для размещения кнопок
        
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
        
        // таблица для вывода потерь
        attrModel = new AttributiveCellTableModel(1, 7);
        table = new JTable();
        table.setModel(attrModel);
        setColumnIdentifiers();// задаём идентификаторы столбцов таблицы
        table.setCellSelectionEnabled(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane scrollpane = new JScrollPane(table);
        // считываем ширину столбцов таблицы из файла свойств
        UserProperties props = new UserProperties(filename);
        MDIObject.setTablecolwidth(props, "colwidth", table);
        // слушатель изменений размеров столбцов таблицы
        columnLis6tener = new ColumnModelListener(table, props, "colwidth");
        table.getColumnModel().addColumnModelListener(columnLis6tener);
        
        // главная панель содержимого
        Box mainBox = Box.createVerticalBox();
        mainBox.add(Box.createVerticalStrut(10));
        mainBox.add(box);
        mainBox.add(Box.createVerticalStrut(10));
        mainBox.add(groupPanel);
        mainBox.add(Box.createVerticalStrut(10));
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(mainBox, BorderLayout.NORTH);
        mainPanel.add(scrollpane, BorderLayout.CENTER);
        getContentPane().add(mainPanel);// добавляем её на панель содержимого формы
        
        gazButton.doClick();
        
    }
    
    /**
         * задаёт идентификаторы для столбцов таблицы
         */
        private void setColumnIdentifiers(){
            TableColumnModel colmodel = table.getColumnModel();
            for (int i = 0; i < colmodel.getColumnCount(); i++) {
                TableColumn tc = colmodel.getColumn(i);
                tc.setIdentifier((Object) i);
            }
        }
    
    /**
     * Задаёт действие для переключателя Регуляторы
     * @return экземпляр слушателя действий для переключателя
     */
    private ActionListener regButtonListener(){
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                cardLO.show(gazPanel, "Regulator");
                
                // задаём исходные данные для массива, которым будет заполняться таблица
                tableContent[0] = regCombo.getSelectedItem();// наименование
                tableContent[1] = "";// эти ячейки
                tableContent[2] = "";// будут объединяться
                tableContent[3] = "";// с первой
                tableContent[4] = txtRegLose.getText();// потери
                tableContent[5] = txtCount.getText();// количество
                tableContent[6] = txtRegResult.getText();// всего потерь
                
                // проверяем установку флага на добавление потерь
                testLoseAdded(regTitleContent);
                
                // объединяем ячейки с одинаковым содержимым
                CellSpan cellAtt;
                cellAtt = (CellSpan)attrModel.getCellAttribute();
                int[] row = new int[]{table.getRowCount() - 1};
                int[] cols = new int[]{0,1,2,3};
                cellAtt.combine(row, cols);
                table.revalidate();
                table.repaint();
                // сбрасываем флаг
                isLoseAdded = false;
            }
        };
        return listener;
    }
    
    /**
     * Задаёт действие для переключателя Газопроводы
     * @return экземпляр слушателя действий для переключателя
     */
    private ActionListener lineButtonListener(){
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                cardLO.show(gazPanel, "GazLiner");// показываем панель
                
                // задаём исходные данные для массива, которым будет заполняться таблица
                tableContent[0] = lineCombo.getSelectedItem();// наименование
                tableContent[1] = diametrCombo.getSelectedItem();// диаметр
                tableContent[2] = txtLose.getText();// потери
                tableContent[3] = txtLenght.getText();// длина
                tableContent[4] = ageCheck.isSelected() ? "да" : "нет";// возраст
                tableContent[5] = territCheck.isSelected() ? "да" : "нет";// территория
                tableContent[6] = txtResult.getText();// всего потерь
                
                // проверяем установку флага на добавление потерь
                testLoseAdded(lineTitleContent);
                // снимаем объединение ячеек
                // объединяем ячейки с одинаковым содержимым
                CellSpan cellAtt;
                cellAtt = (CellSpan)attrModel.getCellAttribute();
                cellAtt.split(table.getRowCount() - 1, 1);
                table.revalidate();
                table.repaint();
                // сбрасываем флаг
                isLoseAdded = false;
            }
        };
        return listener;
    }
    
    /**
     * Создаёт элементы пользовательского интерфейса
     * @return панель с элементами пользовательского интерфейса
     */
    private JPanel createLabelNamePanel(){
        // наименование, объект, исходные данные
        JLabel orgLabel = new JLabel("Наименование предприятия");
        orgLabel.setMaximumSize(orgLabel.getPreferredSize());
        JLabel objLabel = new JLabel("Наименование и адрес объекта");
        objLabel.setMaximumSize(objLabel.getPreferredSize());
        JLabel dataLabel = new JLabel("Исходные данные");
        dataLabel.setMaximumSize(dataLabel.getPreferredSize());
        // размещаем их на панелях
        JPanel namePanel = new JPanel(new GridLayout(3, 1, 0, 5));
        namePanel.add(orgLabel);
        namePanel.add(objLabel);
        namePanel.add(dataLabel);
//        namePanel.setBorder(BorderFactory.createLineBorder(Color.yellow, 2));
        namePanel.setMaximumSize(namePanel.getPreferredSize());
        return namePanel;
    }
    
    /**
     * Создаёт элементы пользовательского интерфейса
     * @return панель с элементами пользовательского интерфейсм
     */
    private JPanel createTextNamePanel(){
        // наименование, объект, исходные данные
        final JTextField txtOrg = new JTextField(70);
        final JTextField txtObject = new JTextField(70);
        final JTextField txtData = new JTextField(70);
        
        // размещаем их на панелях
        JPanel txtNamePanel = new JPanel(new GridLayout(3, 1, 0, 5));
        txtNamePanel.add(txtOrg);
        txtNamePanel.add(txtObject);
        txtNamePanel.add(txtData);
//        txtNamePanel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
        return txtNamePanel;
    }
    
    /**
     * Создаёт панель с переключателями и определяет для них действия
     * @return созданную панель с переключателями
     */
    private Box createRadioButtonBox(){
        // переключатели
        ButtonGroup btnGroup = new ButtonGroup();
        gazButton = new JRadioButton("газопроводы");
        regButton = new JRadioButton("регуляторы");
        
        // определяем действия
        gazButton.addActionListener(lineButtonListener());
        regButton.addActionListener(regButtonListener());
        
        
        // группируем их и добавляем в контейнер
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
     * Создаёт панель с кнопками и определяет для них действия
     * @return созданную панель с кнопками
     */
    private JPanel createCommandButtonPanel(){
        // кнопки
        addButton = new JButton("Добавить");
         // добавляем слушатель для кнопки addButton
        addButton.addActionListener(AddListener());
        
        removeButton = new JButton("Удалить");
        removeButton.addActionListener(RemoveListener());
        
        calcButton = new JButton("Расчёт");
        calcButton.addActionListener(CalcListener());
        
        prevButton = new JButton("Просмотр");
        prevButton.addActionListener(PreviewListener());
        
        JPanel buttonPanel = new JPanel(new GridLayout(4, 2, 5, 5));// панель для размещения кнопок
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
     * создаёт панель с элементами пользовательского интерфейса для выбора газопроводов
     * @return созданную панель с элементами интерфейса
     */
    private JPanel createGazLinePanel(){
        /*
        создаём список с указанием типов газопроводов, список для выбора диаметров,
        флажки для установки или снятия возраста газопровода, подрабатываемой территории
        поле ввода для длины газопровода
        */
        gazLiner.setAge(true);// задлаём флаги возраста
        gazLiner.setTerritory(false);// и территории газопровода
        JLabel lineLabel = new JLabel("Газопроводы");
        lineCombo = new JComboBox(new Object[]{"высокого давления",
            "среднего давления","распределительлные низкого давления",
            "дворовые низкого давления"});
        lineCombo.setToolTipText("выберите тип газопровода");
        JLabel diameterLabel = new JLabel("Диаметр");
        diametrCombo = new JComboBox();
        diametrCombo.setToolTipText("выберите диаметр газопровода");
        txtLose = new JTextField(5);// поле для вывода потерь
        txtLose.setEnabled(false);// делаем его недоступным для редактирования
        JLabel loseLabel = new JLabel("Потери, м куб/(сут км)");
        ageCheck = new JCheckBox("до 25 лет (да/нет)", true);
        territCheck = new JCheckBox("подраб террит (да/нет)", false);
        JLabel lenghtLabel = new JLabel("Длина");
        txtLenght = new JTextField("0", 5);
        txtLenght.setToolTipText("длина газопровода, в качестве разделителя разрядов используйте точку");
        JLabel resultLabel = new JLabel("Итого, м куб/сут");
        resultLabel.setPreferredSize(loseLabel.getPreferredSize());
        txtResult = new JTextField("0", 10);// поле для вывода результата расчётов потерь
        txtLenght.setMaximumSize(txtLenght.getPreferredSize());
        txtResult.setMaximumSize(txtResult.getPreferredSize());
        
        // задаём слушателей для списков
        lineCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // при выборе элемента списка газопроводов заполняем список диаметров
                tableContent[0] = lineCombo.getSelectedItem();// наименование газопровода
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
                // при выборе элемента списка диаметров заполняем поле ввода Потери
                String lose = gazLiner.getLose(diametrCombo.getSelectedIndex() + 1);
                txtResult.setText(gazLiner.getResultLose().toPlainString());
                txtLose.setText(lose);
                tableContent[1] = diametrCombo.getSelectedItem();// диаметр газопровода
                tableContent[2] = lose;// потери на газопроводе
                tableContent[6] = txtResult.getText();// расчётные потери на газопроводе
            }
        });
        diametrCombo.setSelectedIndex(0);
        
        // добавляем слушатели для флажков
        ageCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                gazLiner.setAge(ageCheck.isSelected());
                txtResult.setText(gazLiner.getResultLose().toPlainString());
                tableContent[6] = txtResult.getText();// расчётные потери на газопроводе
                tableContent[4] = ageCheck.isSelected() ? "да" : "нет";// флаг возраста газопровода
            }
        });
        territCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                gazLiner.setTerritory(territCheck.isSelected());
                txtResult.setText(gazLiner.getResultLose().toPlainString());
                tableContent[6] = txtResult.getText();// расчётные потери на газопроводе
                tableContent[5] = territCheck.isSelected() ? "да" : "нет";// флаг территории газопровода
            }
        });
        
        // добавляем слушателей для поля ввода длины газопровода
        txtLenght.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                super.keyReleased(ke);
                // при нажатии клавиши ввода выполняем расчёт потерь
                if(ke.getKeyCode() == KeyEvent.VK_ENTER){
                    // преобразуем строку, представляющую длину, к типу BigDecimal
                    BigDecimal lenght = BigDecimal.valueOf(Double.valueOf(txtLenght.getText()));
                    gazLiner.setLenght(lenght);
                    txtResult.setText(gazLiner.getResultLose().toPlainString());
                    tableContent[6] = txtResult.getText();// расчётные потери на газопроводе
                    tableContent[3] = lenght;// длина
                }
            }
            
        });
        
        
        
        // создаём панели, размещаем элементы - метки и списки
        Box fBox = Box.createVerticalBox();
        fBox.add(lineLabel);
        fBox.add(Box.createVerticalStrut(15));
        fBox.add(diameterLabel);
        fBox.add(Box.createVerticalStrut(15));
        fBox.add(lenghtLabel);
        
        Box sBox = Box.createVerticalBox();
        sBox.add(lineCombo);// список газопроводов
        sBox.add(Box.createVerticalStrut(10));
        Box horBox = Box.createHorizontalBox();
        horBox.add(diametrCombo);// список диаметров газопроводов
        horBox.add(Box.createHorizontalStrut(10));
        horBox.add(loseLabel);
        horBox.add(Box.createHorizontalStrut(5));
        horBox.add(txtLose);// поле вывода потерь на выбранном газопроводе
        Box hBox = Box.createHorizontalBox();
        hBox.add(txtLenght);// поле для ввода длины газопровода
        hBox.add(Box.createHorizontalStrut(13));
        hBox.add(resultLabel);
        hBox.add(Box.createHorizontalStrut(5));
        hBox.add(txtResult);// поле для вывода результата вычисления потерь
        hBox.add(Box.createHorizontalGlue());
        //hBox.add(Box.createHorizontalStrut(5));
        sBox.add(horBox);
        sBox.add(Box.createVerticalStrut(10));
        sBox.add(hBox);
        
        Box horBox_1 = Box.createHorizontalBox();
        horBox_1.add(fBox);
        horBox_1.add(Box.createHorizontalStrut(5));
        horBox_1.add(sBox);
        
        // создаём панель, размещаем флажки и метку с полем ввода длины
        JPanel threePanel = new JPanel(new GridLayout(2, 1, 0, 5));
        threePanel.add(ageCheck);
        threePanel.add(territCheck);
        
        Box box = Box.createHorizontalBox();
        box.add(Box.createHorizontalGlue());
        box.add(horBox_1);
        box.add(Box.createHorizontalStrut(15));
        box.add(threePanel);
        
        // создаём основную панель
        JPanel mainPanel = new JPanel();
        mainPanel.add(box);
        return mainPanel;
    }
    
    /**
     * создаёт панель с элементами пользовательского интерфейса для выбора регуляторов
     * @return созданную панель с элементами интерфейса
     */
    private JPanel createGazRegPanel(){
        /*
        создаём список с типами регуляторов
        поле ввода для отображения потерь
        поле для ввода количества регуляторов
        */
        regCombo = new JComboBox(gazRegulator.regNames());
        JLabel regLabel = new JLabel("Типы регуляторов");
        JLabel loseLabel = new JLabel("Потери, м куб/сут");
        txtRegLose = new JTextField(5);
        JLabel countLabel = new JLabel("Количество");
        txtCount = new JTextField("0", 5);// поле для ввода количества регуляторов
        JLabel resultLabel = new JLabel("Итого, м куб/сут");
        txtRegResult = new JTextField(10);// поле для вывода результатов расчёта
        txtRegResult.setMaximumSize(txtRegResult.getPreferredSize());
        gazRegulator.setRegcount(Integer.parseInt(txtCount.getText()));
        
        // к списку добавляем слушатель
        regCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                tableContent[0] = regCombo.getSelectedItem();// наименование регулятора
                String lose = gazRegulator.getLose(regCombo.getSelectedIndex());
                txtRegResult.setText(gazRegulator.getResultLose().toPlainString());
                txtRegLose.setText(lose);
                tableContent[4] = lose;// потери на регуляторе
                tableContent[5] = txtCount.getText();// количество регуляторов
                tableContent[6] = txtRegResult.getText();// всего расчётных потерь
            }
        });
        regCombo.setSelectedIndex(0);// первый элемент в списке
        
        // добавляем обработчик для поля ввода количества
        txtCount.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                super.keyReleased(ke);
                // при нажатии клавиши Ввода выполняем расчёт потерь
                if(ke.getKeyCode() == KeyEvent.VK_ENTER){
                    gazRegulator.setRegcount(Integer.parseInt(txtCount.getText()));
                    txtRegResult.setText(gazRegulator.getResultLose().toPlainString());
                    tableContent[5] = txtCount.getText();// количество регуляторов
                    tableContent[6] = txtRegResult.getText();// всего расчётных потерь
                }
            }
            
        });
        // здлесь размещаем метку со списком
        Box regBox = Box.createVerticalBox();
        regBox.add(regLabel);
        regBox.add(Box.createVerticalStrut(15));
        regBox.add(loseLabel);
        regBox.add(Box.createVerticalStrut(15));
        regBox.add(countLabel);
        regBox.add(Box.createVerticalStrut(10));
        
        // здесь размещаем метки и поля ввода
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
     * Определяет слушатель для кнопки addButton при добавлении потерь на газопроводе
     * @return слушатель
     */
    private ActionListener AddListener() {
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // добавляем экземпляр потерь на газопроводе в коллекцию
                isLoseAdded = true;// устанавливаем флаг на добавление потерь
                testLoseAdded(tableContent);
                // массив, содержищий номер добавленной строки в таблице и расчитанные потери
                Object[] content = new Object[]{table.getRowCount() - 1, tableContent[6]};
                ResultLose.add(content);
                MDIObject.getInformDialog(PrevloseFrame.this, "Добавлено...", InformDialog.InformType.SAVING);
            }
        };
        return listener;
    }
    
    private ActionListener CalcListener() {
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // выполняем расчёт технологических потерь
                // проверяем наличие элементов в коллекции
                if(ResultLose.size() > 0){
                    System.out.println("size=" + ResultLose.size());
                    BigDecimal result = BigDecimal.ZERO;// начальное значение результата
                    for (Object[] ResultLose1 : ResultLose) {
                        double addition = Double.parseDouble((String) ResultLose1[1]);
                        result = result.add(BigDecimal.valueOf(addition));
                    }
                    // добавляем разделительную строку в таблицу
                    Object[] content = new Object[7];
                    testLoseAdded(content);
                    // добавляем строку с результатами расчёта
                    content[0] = "Итого потери, м куб/сут";
                    content[6] = result.toPlainString();
                    testLoseAdded(content);
                    
                    JOptionPane.showMessageDialog(PrevloseFrame.this, 
                            "Потери составили " + result.toPlainString() + " м куб/сут", 
                            "AbonentGaz", JOptionPane.INFORMATION_MESSAGE);
                    
                } else
                    JOptionPane.showMessageDialog(PrevloseFrame.this, 
                            "Нет данных для расчёта!", 
                            "AbonentGaz", JOptionPane.ERROR_MESSAGE);
            }
        };
        return listener;
    }
    
    /**
     * Возвращает слушатель для кнопки removeButton, удаляющий выбранный тип потерь из коллекции
     * @return созданный слушатель
     */
    private ActionListener RemoveListener() {
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // удаляем выбранную строку в таблице
                if(JOptionPane.showConfirmDialog(PrevloseFrame.this, 
                        "Удалить выбранную строку?", "AbonentGaz", 
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                    /*
                    проверяем индекс строки, которая удаляется : если индекс
                    равен количеству строк, то уведомляем пользователя, что эту строку
                    удалять нельзя
                    */
                    int row = table.getSelectedRow();// индекс удаляемой строки
                    if(table.getRowCount() != 1){
                        // если индекс не равен количеству строк и количество строк не равно !
                        // т.е. в таблице только одна строка
                        DefaultTableModel model = (DefaultTableModel) table.getModel();
                        model.removeRow(row);// удаляем строку из модели
                        // удаляем соответствующий элемент списка и  изменяем индексы строк в остальных элементах
                        int j = 0;
                        for (int i = 0; i < ResultLose.size(); i++) {
                            // проверяем номер строки в элементе списка
                            if((int)ResultLose.get(i)[0] == row){
                                ResultLose.remove(i);
                                j = i;// запоминаем индекс удаляемого элемента
                                break;// выход из цикла
                            }
                        }
                        // изменяем индексы элементов, следуюлщих за удаленным
                        for (int i = j; i < ResultLose.size(); i++){
                            int rownumber = (int)ResultLose.get(i)[0];
                            ResultLose.get(i)[0] = rownumber - 1;
                        }
                    } else {
                        // если в таблице только одна строка, то выводим сообщение
                        JOptionPane.showMessageDialog(PrevloseFrame.this, 
                                "Нельзя удалить последнюю строку в таблице!");
                    }
                }
            }
        };
        return listener;
    }
    
    /**
     * Возвращает слушатель для кнопки prevButton, запускающий окно предварительного просмотра
     * @return созданный слушатель
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
     * Проеверяет установку флага на добавление потерь и 
     * @param rowContent 
     */
    private void testLoseAdded(Object[] rowContent){
        // проверяем установку флага на добавление потерь
        if(isLoseAdded == false){
            /*
            потери ещё не добавлялись, поэтому заменяем содержание
            текущей строки таблицы
            */
            int row = table.getRowCount() - 1;// номер редактируемой строки
            for(int i = 0; i < table.getColumnCount(); i++)
                table.setValueAt(rowContent[i], row, i);
        } else {
            /*
            потери добавлялись, поэтому добавляем новую строку
            в таблицу и сбрасываем флаг на добавление потерь
            */
            attrModel.addRow(new Vector(7));
            int row = table.getRowCount() - 1;// номер редактируемой строки
            for(int i = 0; i < table.getColumnCount(); i++)
                table.setValueAt(rowContent[i], row, i);
        }

    }
    
    /**
     * Класс, предоставляющий информацию по потерям на выбранных газопроводах
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
         * Возвращает расчётные потери на газопроводе выбранного диаметра и длины
         * @return the resultLose
         */
        public BigDecimal getResultLose() {
            calculateLose();
            return resultLose;
        }
        
        private void calculateLose(){
            BigDecimal Lose = linelose.multiply(lenght);// получаем потери в зависимости от длины газопровода
            BigDecimal delta = Lose.multiply(BigDecimal.valueOf(0.25));// 25% от потерь
            resultLose = Lose;// начальное значение результата
            // проверяем флаги возраста и территории
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
                case 0:// газопроводы высокого давления
                    tdi = new SprgazlinerhipressDaoImpl();
                    break;
                case 1:// газопроводы среднего давления
                    tdi = new SprgazlinermidpressDaoImpl();
                    break;
                case 2:// газопроводы распределительные низкого давления
                    tdi = new SprgazlinerrlowpressDaoImpl();
                    break;
                case 3:// газопроводы дворовые низкого давления
                    tdi = new SprgazlinerdlowpressDaoImpl();
                    break;
            }
            Object[] content = new Object[tdi.getCount()];// массив содержимого
            // заполняем массив содержимого
            for(int i = 0; i < content.length; i++){
//                Object[] array = tdi.getItem(i).toDataArray();
                content[i] = tdi.getItem(i).toString();// диаметр газопровода
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
         * Возвращает строковое представление потерь на выбранном регуляторе
         * @param index индекс регулятора из справочника
         * @return the linelose строковое представление потерь
         */
        public String getLose(int index) {
            reglose = sgr.getItem(index).getLose();
            return reglose.toPlainString();
        }
        
        /**
         * Возвращает массив с наименованиями регуляторов
         * @return массив, содержащий наименования регуляторов из справочника
         */
        public Object[] regNames(){
            Object[] content = new Object[sgr.getCount()];
            // получаем данные
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
