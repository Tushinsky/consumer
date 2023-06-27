/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import abonentgaz.SplitLayoutManager;
import abonentgaz.TableCell_Renderer;
import dao_impl.SprcategoryDaoImpl;
import dao_impl.SprcityDaoImpl;
import dao_impl.SpryearDaoImpl;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import runqueries.Runquery;

/**
 * Форма для формирования и печати отчётов
 * @author Sergii.Tushinskyi
 */
public class ReportFrame extends JFrame {
    private JTree reportTree;// дерево, содержащее виды отчётных форм
    private JButton okButton;// кнопка, запускающая формирование выбранного отчёта
    private JButton closeButton;// кнопка, закрываюлщая панель с дополнительными условиями формирования выбранного отчёта
    private JSplitPane reportSplit;// разделительная панель
    private JScrollPane treePane;// панель прокрутки для дерева отчётов
    private JTable reportTable;// таблица для вывода данных формирования выбранного отчёта
    private JScrollPane tablePane;// панель прокрутки для таблицы отчётов
    private JPanel treePanel;// панель для размещения дерева отчётов
    private JPanel reportPanel;// панель для размещения элементов управления для выбора дополнительных условий отчёта
    private JPanel filterPanel;// панель для размещения кнопок управления
    private JPanel commonPanel;// панель для размещения всего содержимого формы
    private final String fileNameProperty = "reportframe.properties";
    private JComboBox categoryCombo;// список, содержащий наименования категорий
    private JComboBox yearCombo;// список лет
    private JComboBox monthCombo;// список месяцев
    private JComboBox townCombo;// список населённых пунктов
    private JFormattedTextField txtFromField;
    private JFormattedTextField txtToField;
    
    // флаги, отмечающие, создавались ли соответствующие элементы управления
    private boolean categoryFlag = false;
    private boolean townFlag = false;
    private boolean dateFlag = false;
    private boolean yearFlag = false;
    private boolean monthFlag = false;
    private boolean statusFlag = false;
    private boolean noneFlag = false;
    
    private String viewName;// имя представления
    private QueryFilter queryFilter = new QueryFilter();// класс для сборки строки - фильтра
    
    public ReportFrame() throws HeadlessException {
        super.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent we) {
                super.windowOpened(we); //To change body of generated methods, choose Tools | Templates.
                setTitle("Отчёты");// заголовок окна
                //задаём иконку для фрейма
                URL url;
                url = ReportFrame.class.getClassLoader().getResource("Images/book.png");
                Image image;
                image = new ImageIcon(url).getImage();
                if (image != null) setIconImage(image);
                
                initComponents();// создаём компоненты и размещаем их на форме
                
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// при закрытии формы освобождаем ресурсы
                setExtendedState(JFrame.MAXIMIZED_BOTH);// устанавливаем развернутый вид окна
                setModalExclusionType(Dialog.ModalExclusionType.NO_EXCLUDE);// тип окна - модальный
                
                try{
                    // считыаваем расположение разделителей из файла свойств
                    SplitLayoutManager slm = new SplitLayoutManager(fileNameProperty);
                    slm.setKeyName(reportSplit.getName());
                    int location = slm.getLocation();
                    if(location != 0)
                        reportSplit.setDividerLocation(location);

                } catch(Exception ex){
                    reportSplit.setDividerLocation(300);
                }
            }

            @Override
            public void windowClosing(WindowEvent we) {
                super.windowClosing(we); //To change body of generated methods, choose Tools | Templates.
            }
            
        });
    }
    
    /**
     * создание компонентов пользовательского интерфейса и размещение их на форме
     */
    private void initComponents(){
        // создаём элементы интерфейса
        // дерево отчётов
        reportTree = new JTree(treeModel());
        // добавляем слушателя выбора элементов дерева
        reportTree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                // получаем путь к выбранной ветви
                TreePath selectionPath = reportTree.getSelectionPath();
                // получаем выбранный узел ветви
                if(selectionPath == null) return;
                Views node = (Views) 
                        selectionPath.getLastPathComponent();
                if(node.isLeaf()){
                    // если это ветвь, выводим её название в сообщении
                    viewName = node.getViewName();
                    dropFilters();// сбрасываем все фильтры
                    CreateLayoutElements(node.getIndex());
                }
            }
        });
        // добавляем слушателья развертывания - свертывания дерева
        reportTree.addTreeExpansionListener(new TreeExpansionListener() {
            // развёртывание дерева
            @Override
            public void treeExpanded(TreeExpansionEvent tee) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            // свёртывание дерева
            @Override
            public void treeCollapsed(TreeExpansionEvent tee) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                // при сворачивании узла дерева скрываем панель с кнопками
                if(filterPanel.isVisible()){
                    filterPanel.setVisible(false);
                    // а также сбрасываем все фильтры отбора записей
                    dropFilters();
                }
                
                
                
            }
        });
        reportTree.setShowsRootHandles(true);// отображение ручки свёртывания корня дерева
        reportTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        treePane = new JScrollPane(reportTree);// полоса прокрутки дерева
        treePanel = new JPanel(new BorderLayout());
        treePanel.add(treePane, BorderLayout.CENTER);// добавляем дерево на панель
        
        // панель для размещения кнопок
        fullCategoryCombo();// создаем и заполняем список категорий
        reportPanel = new JPanel(new BorderLayout(10, 5));
        okButton = new JButton("OK");
        okButton.setPreferredSize(new Dimension(75, 26));
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sqlQuery;
                String filter = queryFilter.getStringfilter();
                if(filter.equals(""))
                    sqlQuery = "SELECT * FROM " + viewName + ";";
                else
                    sqlQuery = "SELECT * FROM " + viewName + 
                            " A WHERE A." + filter + ";";
                System.out.println("sqlquery=" + sqlQuery);
                setData(sqlQuery);
            }
        });
        closeButton = new JButton("Close");
        closeButton.setPreferredSize(new Dimension(75, 26));
        filterPanel = new JPanel(new BorderLayout());
        filterPanel.setVisible(false);// делаем панель невидимой
//        filterPanel.add(new JLabel("категория"));
//        filterPanel.add(categoryCombo);
//        filterPanel.add(okButton);
//        filterPanel.add(closeButton);
        
        // таблица и панель для её размещения
        reportTable = new JTable();
        setDefaultTableModel();// заполняем таблицу данными по умолчанию
//        reportTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        reportTable.setCellSelectionEnabled(true);
        reportTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        TableCell_Renderer.setIntegerTableCellRenderer(reportTable, Color.YELLOW);
        TableCell_Renderer.setShortTableCellRenderer(reportTable, new Color(255, 102, 102));
        TableCell_Renderer.setBigDecimalTableCellRenderer(reportTable, new Color(51, 204, 255));
        MDIObject.addColumnSelectionListener(reportTable);
//        TableCell_Renderer.setWordWrapCellRenderer(reportTable);
                
//        reportTable.setDefaultRenderer(Character.class, new WordWrapCellRenderer());
//        reportTable.setDefaultRenderer(Color.class, new ColorCellRenderer(0, Color.white));
        tablePane = new JScrollPane(reportTable);
        reportPanel.add(filterPanel, BorderLayout.NORTH);
        reportPanel.add(tablePane, BorderLayout.CENTER);
        
        // разделитель панелей
        reportSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        reportSplit.setLeftComponent(treePanel);
        reportSplit.setRightComponent(reportPanel);
        reportSplit.setName("reportSplit");
        // добавляем обработчик событий для разделителя
        reportSplit.addPropertyChangeListener("dividerLocation", 
                new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
//                throw new UnsupportedOperationException("Not supported yet.");
                // класс для чтения свойств
                SplitLayoutManager slManager = new 
                        SplitLayoutManager(fileNameProperty);
                // передаём ему разделитель, для которого нужно изменить положение
                slManager.setKeyName(reportSplit.getName());
                slManager.setLocation(reportSplit.getDividerLocation());
            }
        });
        

        // панель для размещения разделителя
        commonPanel = new JPanel(new BorderLayout());
        commonPanel.add(reportSplit, BorderLayout.CENTER);
        // добавляем её на панель содержимого
        this.getContentPane().add(commonPanel);
        
        
    }
    
    private void fullCategoryCombo(){
        SprcategoryDaoImpl cdi = new SprcategoryDaoImpl();// доступ к справочнику категорий
        String[] model = new String[cdi.getCount() + 1];// модель для заполнения списка
        model[0] = "Все категории";
        for(int i = 1; i < model.length; i++){
            model[i] = cdi.getItem(i - 1).getCategoryName();// заполняем данными
        }
        ComboBoxModel comboModel = new DefaultComboBoxModel(model);
        categoryCombo = new JComboBox(comboModel);// заполняем список
        categoryCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // в зависимости от выбранного элемента задаём значение
                // фильтра по категории
                if(categoryCombo.getSelectedIndex() == 0){
                    // выбран первый элемент в списке (все категории)
                    queryFilter.setCategoryfilter("");
                } else {
                    // выбран другой элемент в списке
                    queryFilter.setCategoryfilter('"' + "Категория" + '"' + "='" + 
                            categoryCombo.getSelectedItem().toString() + "'");
                }
                
            }
        });
        
    }
    
    private void dropFilters(){
        queryFilter.dropFilter();
    }
    
    private DefaultTreeModel treeModel(){
        int index = 0;// начальное значение
        // задаём данные для модели дерева
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Отчёты");
        DefaultMutableTreeNode consumerInfo = new Views();
        consumerInfo.setUserObject("Информация по потребителю");
        DefaultMutableTreeNode poverkaInfo = new Views();
        poverkaInfo.setUserObject("Поверка СИТ");
        DefaultMutableTreeNode loseInfo = new Views();
        loseInfo.setUserObject("ПТП");
        DefaultMutableTreeNode objectInfo = new Views();
        objectInfo.setUserObject("Информация по объектам потребления");
        DefaultMutableTreeNode flowInfo = new Views("QUERYCATEGORY_FLOWYEAR", 1);
        flowInfo.setUserObject("Объёмы потребления");
        DefaultMutableTreeNode turnInfo = new Views();
        turnInfo.setUserObject("Отключение/подключение");
        DefaultMutableTreeNode plombsInfo = new Views();
        plombsInfo.setUserObject("Пломбы");
//        DefaultMutableTreeNode sitInfo = new Views();
//        sitInfo.setUserObject("КИП");
        
        DefaultMutableTreeNode categoryInfo = new Views("CATEGORY_VIEW", 0);
        categoryInfo.setUserObject("Категория");
        
        // потребление
//        index++;
//        DefaultMutableTreeNode itemInfo = new Views("QUERYCATEGORY_FLOWYEAR", 1);
//        itemInfo.setUserObject("Годовое");
//        flowInfo.add(itemInfo);
//        index++;
//        itemInfo = new Views("QUERYCATEGORY_FLOWMONTH", 1);
//        itemInfo.setUserObject("Месячное");
//        flowInfo.add(itemInfo);
        
        index++;
        DefaultMutableTreeNode limitInfo = new Views("QUERYCATEGORY_LIMIT", 6);
        limitInfo.setUserObject("Лимиты");
        
        // поверка
        index++;
        DefaultMutableTreeNode itemInfo = new Views("QUERYTEST_COUNT", 2);
        itemInfo.setUserObject("Счётчики");
        poverkaInfo.add(itemInfo);
        index++;
        itemInfo = new Views("QUERYTEST_CORRECTOR", 2);
        itemInfo.setUserObject("Корректора");
        poverkaInfo.add(itemInfo);
        index++;
        itemInfo = new Views("QUERYTEST_DATCHIK", 2);
        itemInfo.setUserObject("Датчики");
        poverkaInfo.add(itemInfo);
        
        index++;
        DefaultMutableTreeNode monthInfo = new Views("QUERYCATEGORY_MONTH", 3);
        monthInfo.setUserObject("Месяц");
        index++;
        DefaultMutableTreeNode cvartalInfo = new Views("QUERYCATEGORY_CVARTAL", 3);
        cvartalInfo.setUserObject("Квартал");
        
        // потери
        index++;
        itemInfo = new Views("QUERYCATEGORY_HIPRESS", 0);
        itemInfo.setUserObject("Высокого давления");
        loseInfo.add(itemInfo);
        index++;
        itemInfo = new Views("QUERYCATEGORY_MIDPRESS", 0);
        itemInfo.setUserObject("Среднего давления");
        loseInfo.add(itemInfo);
        index++;
        itemInfo = new Views("QUERYCATEGORY_RLOWPRESS", 0);
        itemInfo.setUserObject("Распределительные низкого давления");
        loseInfo.add(itemInfo);
        index++;
        itemInfo = new Views("QUERYCATEGORY_DLOWPRESS", 0);
        itemInfo.setUserObject("Дворовые низкого давления");
        loseInfo.add(itemInfo);
        index++;
        itemInfo = new Views("QUERYCATEGORY_REGULATOR", 0);
        itemInfo.setUserObject("Регуляторы");
        loseInfo.add(itemInfo);
        
        // отключение
        index++;
        itemInfo = new Views("QUERYCATEGORY_TURNOBJECT", 4);
        itemInfo.setUserObject("Объекты");
        turnInfo.add(itemInfo);
        index++;
        itemInfo = new Views("QUERYCATEGORY_TURNEQUIPMENT", 4);
        itemInfo.setUserObject("Оборудование");
        turnInfo.add(itemInfo);
        
        // пломбы
        index++;
        itemInfo = new Views("Пломбы", index);
        itemInfo.setUserObject("Всех типов");
        plombsInfo.add(itemInfo);
        index++;
        itemInfo = new Views("Пломбы", index);
        itemInfo.setUserObject("ИМП");
        plombsInfo.add(itemInfo);
        
        // КИП
//        index++;
//        itemInfo = new Views("Счетчики", index);
//        itemInfo.setUserObject("Счётчики");
//        sitInfo.add(itemInfo);
//        index++;
//        itemInfo = new Views("Корректора", index);
//        itemInfo.setUserObject("Корректора");
//        sitInfo.add(itemInfo);
        
        index++;
        DefaultMutableTreeNode seasonInfo = new Views("Отопительный сезон", index);
        seasonInfo.setUserObject("Отопительный сезон");
        
        index++;
        DefaultMutableTreeNode twocountInfo = 
                new Views("QUERYCATEGORY_COUNTTWO_ANDMORE", 5);
        twocountInfo.setUserObject("Объекты с 2-мя и более счётчиками");
        
        index++;
        DefaultMutableTreeNode equipmentInfo = 
                new Views("QUERYCATEGORY_EQUIPMENT", 5);
        equipmentInfo.setUserObject("Оборудование");
        
        index++;
        DefaultMutableTreeNode datelaunchInfo = 
                new Views("QUERYCATEGORY_GAZLAUNCH", 5);
        datelaunchInfo.setUserObject("Дата пуска газа");
        
        index++;
        DefaultMutableTreeNode commonobjectInfo = 
                new Views("Сводная информация по объектам", index);
        commonobjectInfo.setUserObject("Сводная информация");
        
        index++;
        DefaultMutableTreeNode listshowInfo = new Views("Ведомость показаний", index);
        listshowInfo.setUserObject("Ведомость показаний");
        
        index++;
        DefaultMutableTreeNode gazusingInfo = new Views("QUERY_GAZUSING", 5);
        gazusingInfo.setUserObject("Область использования природного газа");
        
        
        
        
        // заполняем узлы дерева
        consumerInfo.add(categoryInfo);
        consumerInfo.add(flowInfo);
        consumerInfo.add(limitInfo);
        objectInfo.add(turnInfo);
        objectInfo.add(plombsInfo);
//        objectInfo.add(sitInfo);
        objectInfo.add(seasonInfo);
        objectInfo.add(twocountInfo);
        objectInfo.add(equipmentInfo);
        objectInfo.add(datelaunchInfo);
        objectInfo.add(commonobjectInfo);
        objectInfo.add(listshowInfo);
        objectInfo.add(gazusingInfo);
        root.add(consumerInfo);
        root.add(poverkaInfo);
        root.add(monthInfo);
        root.add(cvartalInfo);
        root.add(loseInfo);
        root.add(objectInfo);
        
        // создаём и возвращаем модель
        DefaultTreeModel model = new DefaultTreeModel(root);
        return model;
    }
    
    /**
     * Создаёт элементы управления для выбора данных
     * @param index 
     */
    private void CreateLayoutElements(int index){
        switch(index){
            case 0:
                // категория, потери высокого давления,
                // потери среднего давления, потери распред низкого давления
                // потери дворов низкого давления, потери на регуляторах
                // фильтр по категории
                createFilter_Category();
                break;
            case 1:// годовое потребление, месячное потребление
                // фильтр по категории, году и месяцу
                createFiter_CategoryYearMonth();
                break;
            case 2:// поверка счётчиков, поверка корректоров, датчиков
                // фильтр по категории  и дате
                createFilter_CategoryDate();
                break;
            case 3:// месячный отчёт, квартальный отчёт
                // без фильтра
                createFilterNone();
                break;
            case 4:// отключение объёктов, отключение оборудования
                // фильтр по категории, дате, городу и статусу
                createFilter_CategoryDateTownStatus();
                break;
            case 5:// 2 и более счётчиков, оборудование, дата пуска, область использования
                // фильтр по категории и городу
                createFilter_CategoryTown();
                break;
            case 6:// лимиты
                // фильтр по категории и месяцу
                createFilter_CategoryMonth();
                break;
        }
    }
    
    /**
     * Сбрасывает настройки панели, на которой размещаются кнопки и элементы
     * управления для выбора записей
     */
    private void dropFilterPanel(){
        if(filterPanel.isVisible()) filterPanel.setVisible(false);
        filterPanel.removeAll();
        filterPanel.setLayout(new BorderLayout(5, 5));
    }
    
    /**
     * Создаёт элементы управления для выбора фильтра по категории
     */
    private void createFilter_Category(){
        // проверяем состояние флага создания элементов управления ранее
        if(categoryFlag == false){
            // скрываем панель кнопок и удаляем все элементы с неё
            dropFilterPanel();
            categoryCombo.setSelectedIndex(0);// выбираем первый элемент в списке
            /*
             если ранее элементы управления не создавались, то создаём их и
             размещаем их на панели кнопок
            */
            // список с меткой размещаем на панели кнопок слева
            JPanel panel = new JPanel(new FlowLayout(10));
            panel.add(new JLabel("Категория"));
            panel.add(categoryCombo);
            panel.add(okButton);
            panel.add(closeButton);
            filterPanel.add(panel, BorderLayout.WEST);
            filterPanel.add(new JPanel(), BorderLayout.CENTER);// в центре размещаем пустую панель
            categoryFlag = true;// задаём флаг
            
            // снимаем все остальные флаги
            dateFlag = false;
            townFlag = false;
            yearFlag = false;
            monthFlag = false;
            noneFlag = false;
            statusFlag = false;
        } else {
            categoryCombo.setSelectedIndex(0);// выбираем первый элемент в списке
        }
        // делаем панель кнопок видимой, если она скрыта
        if(filterPanel.isVisible() == false) filterPanel.setVisible(true);
    }
    
    /**
     * Создаёт элементы управления для выбора фильтра по категории, году и месяцу
     */
    private void createFiter_CategoryYearMonth(){
        // проверяем состояние флага создания элементов управления ранее
        if(yearFlag == false){
            // скрываем панель кнопок и удаляем все элементы с неё
            dropFilterPanel();
            categoryCombo.setSelectedIndex(0);// выбираем первый элемент в списке
            /*
             если ранее элементы управления не создавались, то создаём их и
             размещаем их на панели кнопок
            */
            // список с меткой размещаем на панели кнопок слева
            JPanel panel = new JPanel(new GridLayout(3,2,5,5));
            panel.add(new JLabel("  Категория"));
            panel.add(categoryCombo);
            
            // получаем данные по годам
            SpryearDaoImpl ydi = new SpryearDaoImpl();
            Object[] content = new Object[ydi.getCount()];
            for(int i = 0; i < ydi.getCount(); i++)
                content[i] = ydi.getItem(i).getNameYear();
            yearCombo = new JComboBox(content);// создаём список с годами
            yearCombo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    queryFilter.setYearfilter('"' + "Год" + '"' + "=" + 
                            yearCombo.getSelectedItem().toString());
                }
            });
            // выбираем последний элемент в списке
            yearCombo.setSelectedIndex(yearCombo.getItemCount() - 1);
            panel.add(new JLabel("  Год"));
            panel.add(yearCombo);// добавляем его на панель
            
            // создаём и заполняем список с назаниями месяцев года
            content = new Object[]{"за год","январь","февраль","март","апрель","май",
                "июнь","июль","август","сентябрь","октябрь","ноябрь","декабрь"};
            monthCombo = new JComboBox(content);
            monthCombo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    if(monthCombo.getSelectedIndex() == 0){
                        queryFilter.setMonthfilter("");
                        viewName = "QUERYCATEGORY_FLOWYEAR";
                    } else {
                        queryFilter.setMonthfilter('"' + "Месяц" + '"' + "=" + 
                                monthCombo.getSelectedIndex());
                        viewName = "QUERYCATEGORY_FLOWMONTH";
                    }
                }
            });
            monthCombo.setSelectedIndex(0);// выбираем первый элемент в списке
            
            
            panel.add(new JLabel("  Месяц"));
            panel.add(monthCombo);
            
            JPanel panel1 = new JPanel();
            panel1.add(okButton);
            panel1.add(closeButton);
            filterPanel.add(panel, BorderLayout.WEST);
//            filterPanel.add(new JPanel(), BorderLayout.CENTER);// в центре размещаем пустую панель
            filterPanel.add(panel1, BorderLayout.CENTER);
            yearFlag = true;// задаём флаг
            
            // снимаем все остальные флаги
            dateFlag = false;
            townFlag = false;
            categoryFlag = false;
            monthFlag = false;
            noneFlag = false;
            statusFlag = false;
        } else {
            categoryCombo.setSelectedIndex(0);// выбираем первый элемент в списке
            yearCombo.setSelectedIndex(0);
            monthCombo.setSelectedIndex(0);
        }
        // делаем панель кнопок видимой, если она скрыта
        if(filterPanel.isVisible() == false) filterPanel.setVisible(true);
    }
    
    /**
     * Создаёт элементы управления для выбора фильтра по категории и дате
     */
    private void createFilter_CategoryDate(){
        // проверяем состояние флага создания элементов управления ранее
        if(dateFlag == false){
            // скрываем панель кнопок и удаляем все элементы с неё
            dropFilterPanel();
            categoryCombo.setSelectedIndex(0);// выбираем первый элемент в списке
            /*
             если ранее элементы управления не создавались, то создаём их и
             размещаем их на панели кнопок
            */
            // список с меткой размещаем на панели кнопок слева
            JPanel panel = new JPanel();
            
            // создаём контейнер для размещения элементов
            Box vertBox = Box.createVerticalBox();
            vertBox.add(Box.createVerticalStrut(3));
            vertBox.add(new JLabel("Категория", JLabel.LEFT));
            vertBox.add(Box.createVerticalStrut(5));
            vertBox.add(categoryCombo);
            
            Box vertBox1 = Box.createVerticalBox();
            
            // поля для ввода даты
            txtFromField = new 
                JFormattedTextField(DateFormat.getDateInstance(DateFormat.MEDIUM));
            txtFromField.setValue(new Date());
            txtFromField.setColumns(10);
            txtToField = new 
                JFormattedTextField(DateFormat.getDateInstance(DateFormat.MEDIUM));
            txtToField.setColumns(10);
            txtToField.setValue(new Date());
            // добавляем обработку фокуса полями ввода
            txtFromField.addFocusListener(new FocusListener() {
                @Override
                public void focusLost(FocusEvent e) {
                    if(!txtFromField.isEditValid()){
                        // неверное значение даты в поле ввода
                        JOptionPane.showMessageDialog(ReportFrame.this, 
                                "Неверный ввод даты в поле", "AbonentGaz", JOptionPane.ERROR_MESSAGE);
//                        txtFromField.setFocusable(true);
                        
                    } else {
                        try {
                            // дата введена верно, проверяем её на то, чтобы она не была
                            // больше конечной даты отбора
                            txtFromField.commitEdit();
                        } catch (ParseException ex) {
                            Logger.getLogger(ReportFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        Date fromDate = (Date) txtFromField.getValue();
                        Date toDate = (Date) txtToField.getValue();
                        if(fromDate.after(toDate)){
                            JOptionPane.showMessageDialog(ReportFrame.this, 
                                    "Начальная дата больше конечной", "AbonentGaz", JOptionPane.ERROR_MESSAGE);
                            txtFromField.setValue(txtToField.getValue());
                            try {
                                txtFromField.commitEdit();
                            } catch (ParseException ex) {
                                Logger.getLogger(ReportFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else
                            queryFilter.setDatefilter('"' + "След_поверка" + '"' + " BETWEEN '" + 
                                txtFromField.getText() + "' AND '" + 
                                    txtToField.getText() + "'");
                    }
                }

                @Override
                public void focusGained(FocusEvent e) {
                    // при получении фокуса выделяем весь текст в поле ввода
                    txtFromField.setSelectionStart(0);
                    txtFromField.setSelectionEnd(txtFromField.getText().length() - 1);
                }
                
            });
            
            txtToField.addFocusListener(new FocusListener() {
                @Override
                public void focusLost(FocusEvent e) {
                    if(!txtToField.isValid()){
                        JOptionPane.showMessageDialog(ReportFrame.this, 
                                "Неверный ввод даты в поле", "AbonentGaz", JOptionPane.ERROR_MESSAGE);
//                        txtToField.setFocusable(true);
                        
                    } else {
                        try {
                            txtToField.commitEdit();
                        } catch (ParseException ex) {
                            Logger.getLogger(ReportFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        Date fromDate = (Date) txtFromField.getValue();
                        Date toDate = (Date) txtToField.getValue();
                        if(toDate.before(fromDate)){
                            JOptionPane.showMessageDialog(ReportFrame.this, 
                                    "Конечная  дата меньше начальной", "AbonentGaz", JOptionPane.ERROR_MESSAGE);
                            txtToField.setValue(txtFromField.getValue());
                            try {
                                txtToField.commitEdit();
                            } catch (ParseException ex) {
                                Logger.getLogger(ReportFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else
                            queryFilter.setDatefilter('"' + "След_поверка" + '"' + " BETWEEN '" + 
                                txtFromField.getText() + "' AND '" + 
                                    txtToField.getText() + "'");
                    }
                }

                @Override
                public void focusGained(FocusEvent e) {
                    // при получении фокуса выделяем весь текст в поле ввода
                    txtToField.setSelectionStart(0);
                    txtToField.setSelectionEnd(txtToField.getText().length() - 1);
                }
                
            });
            
            // задаём начальное значение фильтра по дате
            queryFilter.setDatefilter('"' + "След_поверка" + '"' + " BETWEEN '" + 
                                txtFromField.getText() + "' AND '" + 
                                    txtToField.getText() + "'");
            
            
            // в этот контейнер добавляем флажок для установки/снятия выбора по дате
            final JCheckBox checkBox = new JCheckBox("фильтр по дате", true);
            // добавляем к нему слушатель
            checkBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    // при установке флажка поля даты делаем доступными и наоборот
                    txtFromField.setEnabled(checkBox.isSelected());
                    txtToField.setEnabled(checkBox.isSelected());
                    if(!checkBox.isSelected())
                        queryFilter.setDatefilter("");
                    else
                        queryFilter.setDatefilter('"' + "След_поверка" + '"' + " BETWEEN '" + 
                                txtFromField.getText() + "' AND '" + 
                                    txtToField.getText() + "'");
                }
            });
            Box horBox = Box.createHorizontalBox();
            // добавляем их в контейнер
            horBox.add(new JLabel("с"));
            horBox.add(Box.createHorizontalStrut(5));
            horBox.add(txtFromField);
            horBox.add(Box.createHorizontalStrut(15));
            horBox.add(new JLabel("по"));
            horBox.add(Box.createHorizontalStrut(5));
            horBox.add(txtToField);
            
            vertBox1.add(Box.createVerticalStrut(3));
            vertBox1.add(checkBox);
//            vertBox1.add(Box.createVerticalStrut(5));
            vertBox1.add(horBox);
            
            Box horBox1 = Box.createHorizontalBox();
            horBox1.add(vertBox);
            horBox1.add(Box.createHorizontalStrut(15));
            horBox1.add(vertBox1);
            
            panel.add(horBox1);
            JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
            panel1.add(okButton);
            panel1.add(closeButton);
            filterPanel.add(panel, BorderLayout.WEST);
//            filterPanel.add(new JPanel(), BorderLayout.CENTER);// в центре размещаем пустую панель
            filterPanel.add(panel1, BorderLayout.CENTER);
            dateFlag = true;// задаём флаг
            
            // снимаем все остальные флаги
            categoryFlag = false;
            townFlag = false;
            yearFlag = false;
            monthFlag = false;
            noneFlag = false;
            statusFlag = false;
        } else {
            categoryCombo.setSelectedIndex(0);// выбираем первый элемент в списке
        }
        // делаем панель кнопок видимой, если она скрыта
        if(filterPanel.isVisible() == false) filterPanel.setVisible(true);
    }
    
    /**
     * Создаёт элементы управления для выбора фильтра по категории, городу и статусу
     */
    private void createFilter_CategoryDateTownStatus(){
        // проверяем состояние флага создания элементов управления ранее
        if(statusFlag == false){
            // скрываем панель кнопок и удаляем все элементы с неё
            dropFilterPanel();
            categoryCombo.setSelectedIndex(0);// выбираем первый элемент в списке
            /*
             если ранее элементы управления не создавались, то создаём их и
             размещаем их на панели кнопок
            */
            // поля для ввода даты
            txtFromField = new 
                JFormattedTextField(DateFormat.getDateInstance(DateFormat.MEDIUM));
            txtFromField.setValue(new Date());
            txtFromField.setColumns(10);
            txtToField = new 
                JFormattedTextField(DateFormat.getDateInstance(DateFormat.MEDIUM));
            txtToField.setColumns(10);
            txtToField.setValue(new Date());
            // добавляем обработку фокуса полями ввода
            txtFromField.addFocusListener(new FocusListener() {
                @Override
                public void focusLost(FocusEvent e) {
                    if(!txtFromField.isEditValid()){
                        // неверное значение даты в поле ввода
                        JOptionPane.showMessageDialog(ReportFrame.this, 
                                "Неверный ввод даты в поле", "AbonentGaz", JOptionPane.ERROR_MESSAGE);
//                        txtFromField.setFocusable(true);
                        
                    } else {
                        try {
                            // дата введена верно, проверяем её на то, чтобы она не была
                            // больше конечной даты отбора
                            txtFromField.commitEdit();
                        } catch (ParseException ex) {
                            Logger.getLogger(ReportFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        Date fromDate = (Date) txtFromField.getValue();
                        Date toDate = (Date) txtToField.getValue();
                        if(fromDate.after(toDate)){
                            JOptionPane.showMessageDialog(ReportFrame.this, 
                                    "Начальная дата больше конечной", "AbonentGaz", JOptionPane.ERROR_MESSAGE);
                            txtFromField.setValue(txtToField.getValue());
                            try {
                                txtFromField.commitEdit();
                            } catch (ParseException ex) {
                                Logger.getLogger(ReportFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else
                            queryFilter.setDatefilter('"' + "Дата" + '"' + " BETWEEN '" + 
                                txtFromField.getText() + "' AND '" + 
                                    txtToField.getText() + "'");
                    }
                }

                @Override
                public void focusGained(FocusEvent e) {
                    // при получении фокуса выделяем весь текст в поле ввода
                    txtFromField.setSelectionStart(0);
                    txtFromField.setSelectionEnd(txtFromField.getText().length() - 1);
                }
                
            });
            
            txtToField.addFocusListener(new FocusListener() {
                @Override
                public void focusLost(FocusEvent e) {
                    if(!txtToField.isValid()){
                        JOptionPane.showMessageDialog(ReportFrame.this, 
                                "Неверный ввод даты в поле", "AbonentGaz", JOptionPane.ERROR_MESSAGE);
//                        txtToField.setFocusable(true);
                        
                    } else {
                        try {
                            txtToField.commitEdit();
                        } catch (ParseException ex) {
                            Logger.getLogger(ReportFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        Date fromDate = (Date) txtFromField.getValue();
                        Date toDate = (Date) txtToField.getValue();
                        if(toDate.before(fromDate)){
                            JOptionPane.showMessageDialog(ReportFrame.this, 
                                    "Конечная  дата меньше начальной", "AbonentGaz", JOptionPane.ERROR_MESSAGE);
                            txtToField.setValue(txtFromField.getValue());
                            try {
                                txtToField.commitEdit();
                            } catch (ParseException ex) {
                                Logger.getLogger(ReportFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else
                            queryFilter.setDatefilter('"' + "Дата" + '"' + " BETWEEN '" + 
                                txtFromField.getText() + "' AND '" + 
                                    txtToField.getText() + "'");
                    }
                }

                @Override
                public void focusGained(FocusEvent e) {
                    // при получении фокуса выделяем весь текст в поле ввода
                    txtToField.setSelectionStart(0);
                    txtToField.setSelectionEnd(txtToField.getText().length() - 1);
                }
                
            });
            
            // задаём начальное значение фильтра по дате
            queryFilter.setDatefilter('"' + "Дата" + '"' + " BETWEEN '" + 
                                txtFromField.getText() + "' AND '" + 
                                    txtToField.getText() + "'");
            
            
            // список с меткой размещаем на панели кнопок слева
            SprcityDaoImpl cdi = new SprcityDaoImpl();// данные по городам
            Object[] content = new Object[cdi.getCount() + 1];
            content[0] = "Все города";
            for(int i = 0; i < cdi.getCount(); i++)
                content[i + 1] = cdi.getItem(i).getCityName();// получаем данные
            ComboBoxModel model = new DefaultComboBoxModel(content);
            townCombo = new JComboBox(model);
            // действие для списка городов
            townCombo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    if(townCombo.getSelectedIndex() == 0)
                        // все города
                        queryFilter.setTownfilter("");
                    else
                        queryFilter.setTownfilter('"' + "Город" + '"' + "='" + 
                            townCombo.getSelectedItem().toString() + "'");
                }
            });
            townCombo.setSelectedIndex(0);// выбираем первый элемент в списке
            
            // создаём флажок для выбора состояния вкл / откл
            final JCheckBox checkBox = new JCheckBox("включены");
            // добавляем действия
            checkBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // при установке флажка поля даты делаем доступными и наоборот
                    txtFromField.setEnabled(checkBox.isSelected());
                    txtToField.setEnabled(checkBox.isSelected());
                    if(checkBox.isSelected()){
                        // флажок установлен
                        checkBox.setText("включены");
                        queryFilter.setStatusfilter('"' + "Статус" + '"' + 
                                "='вкл'");
                    } else {
                        checkBox.setText("отключены");
                        queryFilter.setStatusfilter('"' + "Статус" + '"' + 
                                "='откл'");
                    }
                }
            });
            checkBox.setSelected(true);
            
            Box vertBox = Box.createVerticalBox();// создаём вертикальный контейнер
            
            Box horBox1 = Box.createHorizontalBox();// контейнер для списка категорий
            horBox1.add(new JLabel("Категория"));
            horBox1.add(Box.createHorizontalStrut(5));
            horBox1.add(categoryCombo);
            
            // контейнер для списка населённых пунктов и флажка
            Box horBox2 = Box.createHorizontalBox();
            horBox2.add(new JLabel("Населённый пункт"));
            horBox2.add(townCombo);
            vertBox.add(horBox1);
            vertBox.add(Box.createVerticalStrut(5));
            vertBox.add(horBox2);
            
            // контейнер для размещения флажка и полей для ввода даты
            Box horBox = Box.createHorizontalBox();
            horBox.add(new JLabel("с"));
            horBox.add(Box.createHorizontalStrut(5));
            horBox.add(txtFromField);
            horBox.add(Box.createHorizontalStrut(10));
            horBox.add(new JLabel("по"));
            horBox.add(Box.createHorizontalStrut(5));
            horBox.add(txtToField);
            Box vertBox1 = Box.createVerticalBox();
            vertBox1.add(checkBox);
            vertBox1.add(horBox);
//            Box horBox2 = Box.createHorizontalBox();
            
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT,10, 0));
            panel.add(vertBox);
            panel.add(vertBox1);
            panel.add(okButton);
            panel.add(closeButton);
            filterPanel.add(panel, BorderLayout.WEST);
            filterPanel.add(new JPanel(), BorderLayout.CENTER);// в центре размещаем пустую панель
            statusFlag = true;// задаём флаг
            
            // снимаем все остальные флаги
            dateFlag = false;
            townFlag = false;
            yearFlag = false;
            monthFlag = false;
            noneFlag = false;
            categoryFlag = false;
        } else {
            categoryCombo.setSelectedIndex(0);// выбираем первый элемент в списке
            townCombo.setSelectedIndex(0);
        }
        // делаем панель кнопок видимой, если она скрыта
        if(filterPanel.isVisible() == false) filterPanel.setVisible(true);
    }
    
    /**
     * Создаёт элементы управления для выбора фильтра по категории и городу
     */
    private void createFilter_CategoryTown(){
        // проверяем состояние флага создания элементов управления ранее
        if(townFlag == false){
            // скрываем панель кнопок и удаляем все элементы с неё
            dropFilterPanel();
            categoryCombo.setSelectedIndex(0);// выбираем первый элемент в списке
            /*
             если ранее элементы управления не создавались, то создаём их и
             размещаем их на панели кнопок
            */
            SprcityDaoImpl cdi = new SprcityDaoImpl();// данные по городам
            Object[] content = new Object[cdi.getCount() + 1];
            content[0] = "Все города";
            for(int i = 0; i < cdi.getCount(); i++)
                content[i + 1] = cdi.getItem(i).getCityName();// получаем данные
            final JComboBox cityCombo = new JComboBox(content);// создаём и заполняем список городов
            // действие для списка городов
            cityCombo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    if(cityCombo.getSelectedIndex() == 0)
                        // все города
                        queryFilter.setTownfilter("");
                    else
                        queryFilter.setTownfilter('"' + "Город" + '"' + "='" + 
                            cityCombo.getSelectedItem().toString() + "'");
                }
            });
            cityCombo.setSelectedIndex(0);// выбираем первый элемент в списке
            
            // список с меткой размещаем на панели кнопок слева
            JPanel panel = new JPanel(new FlowLayout(10));
            panel.add(new JLabel("Категория"));
            panel.add(categoryCombo);
            panel.add(new JLabel("          "));
            panel.add(new JLabel("Населённый пункт"));
            panel.add(cityCombo);
            panel.add(okButton);
            panel.add(closeButton);
            filterPanel.add(panel, BorderLayout.WEST);
            filterPanel.add(new JPanel(), BorderLayout.CENTER);// в центре размещаем пустую панель
            townFlag = true;// задаём флаг
            
            // снимаем все остальные флаги
            dateFlag = false;
            categoryFlag = false;
            yearFlag = false;
            monthFlag = false;
            noneFlag = false;
            statusFlag = false;
        } else {
            categoryCombo.setSelectedIndex(0);// выбираем первый элемент в списке
            townCombo.setSelectedIndex(0);
        }
        // делаем панель кнопок видимой, если она скрыта
        if(filterPanel.isVisible() == false) filterPanel.setVisible(true);
    }
    
    /**
     * Создаёт элементы управления для выбора фильтра по категории и месяцу
     */
    private void createFilter_CategoryMonth(){
        // проверяем состояние флага создания элементов управления ранее
        if(monthFlag == false){
            // скрываем панель кнопок и удаляем все элементы с неё
            dropFilterPanel();
            categoryCombo.setSelectedIndex(0);// выбираем первый элемент в списке
            /*
             если ранее элементы управления не создавались, то создаём их и
             размещаем их на панели кнопок
            */
            // список с меткой размещаем на панели кнопок слева
            JPanel panel = new JPanel(new GridLayout(2,2,5,5));
            panel.add(new JLabel("  Категория"));
            panel.add(categoryCombo);
            
            // создаём и заполняем список с назаниями месяцев года
            Object[] content = new Object[]{"январь","февраль","март","апрель","май",
                "июнь","июль","август","сентябрь","октябрь","ноябрь","декабрь"};
            monthCombo = new JComboBox(content);
            monthCombo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    queryFilter.setMonthfilter('"' + "Месяц" + '"' + "=" + 
                            (monthCombo.getSelectedIndex() + 1));
                    
                }
            });
            monthCombo.setSelectedIndex(0);// выбираем первый элемент в списке
            panel.add(new JLabel("  Месяц"));
            panel.add(monthCombo);
            
            JPanel panel1 = new JPanel();
            panel1.add(okButton);
            panel1.add(closeButton);
            filterPanel.add(panel, BorderLayout.WEST);
//            filterPanel.add(new JPanel(), BorderLayout.CENTER);// в центре размещаем пустую панель
            filterPanel.add(panel1, BorderLayout.CENTER);
            monthFlag = true;// задаём флаг
            
            // снимаем все остальные флаги
            dateFlag = false;
            townFlag = false;
            categoryFlag = false;
            yearFlag = false;
            noneFlag = false;
            statusFlag = false;
        } else {
            categoryCombo.setSelectedIndex(0);// выбираем первый элемент в списке
            monthCombo.setSelectedIndex(0);
        }
        // делаем панель кнопок видимой, если она скрыта
        if(filterPanel.isVisible() == false) filterPanel.setVisible(true);
    }
    
    /**
     * создание элементов управления для получения данных без условий выбора
     */
    private void createFilterNone(){
        // проверяем состояние флага создания элементов управления ранее
        if(noneFlag == false){
            // скрываем панель кнопок и удаляем все элементы с неё
            dropFilterPanel();
            
            /*
             если ранее элементы управления не создавались, то создаём их и
             размещаем их на панели кнопок
            */
            // список с меткой размещаем на панели кнопок слева
            JPanel panel = new JPanel(new FlowLayout(10));
            panel.add(okButton);
            panel.add(closeButton);
            filterPanel.add(panel, BorderLayout.CENTER);
            filterPanel.add(new JPanel(), BorderLayout.WEST);// в центре размещаем пустую панель
            noneFlag = true;// задаём флаг
            
            // снимаем все остальные флаги
            dateFlag = false;
            categoryFlag = false;
            yearFlag = false;
            monthFlag = false;
            townFlag = false;
            statusFlag = false;
        }
        queryFilter.dropFilter();
        // делаем панель кнопок видимой, если она скрыта
        if(filterPanel.isVisible() == false) filterPanel.setVisible(true);
    }
    
    /**
     * Получает данные из представления и заполняет ими таблицу
     * @param viewName имя представления
     */
    private void setData(String sqlQuery){
        runqueries.Runquery rq = new Runquery();
        List<Object[]> entities = rq.getQueryEntities(sqlQuery);
        // проверяем наличие записей в наборе
        if(entities.size() > 0){
            // записи есть
            String[] columnName = rq.getColumnName();// получаем наименования полей
            Class[] columnClass = rq.getColumnClass();// получаем имена классов данных
            Object[][] content = new Object[entities.size()][];// массив для данных
            for(int i = 0; i < content.length; i++)
                content[i] = entities.get(i);// формируем данные для вставки в таблицу
            MyTableModel model = new MDIObject.MyTableModelImpl(content, columnName, columnClass);
            int[] colIndex = new int[columnName.length];// массив нередактируемых столбцов
            for(int i = 0; i < colIndex.length; i++)
                colIndex[i] = i;
            // заполняем таблицу данными
            MDIObject.fullTableData(colIndex, model, reportTable);

            // перенос текста в ячейках таблицы по словам
            TableCell_Renderer.setWordWrapCellRenderer(reportTable, columnClass);
        } else {
            // записей нет
            JOptionPane.showMessageDialog(ReportFrame.this, 
                    "Искомых записей не найдено.", "AbonentGaz", 
                    JOptionPane.INFORMATION_MESSAGE);
            setDefaultTableModel();
        }
    }
    
    private void setDefaultTableModel(){
        Object[][] content;
        String[] columnName;
        Class[] columnClass;
        MyTableModel model;
        int[] colIndex;
        // модель таблицы по-умолчанию
        columnName = new String[]{"A","B","C","D"};// получаем наименования полей
        columnClass = new Class[]{Object.class,Object.class,Object.class,Object.class};// получаем имена классов данных
        content = new Object[][]{new Object[]{"","","",""}};// массив для данных
        colIndex = new int[]{0,1,2,3};
        model = new MDIObject.MyTableModelImpl(content, columnName, columnClass);
        // заполняем таблицу данными
        MDIObject.fullTableData(colIndex, model, reportTable);
    }
    
    class Views extends DefaultMutableTreeNode {
        private final String viewName;
        private final int index;
        
        public Views(String viewName, int index) {
            this.viewName = viewName;
            this.index = index;
        }

        public Views() {
            this.viewName = null;
            this.index = 0;
        }

        
        /**
         * @return the viewName
         */
        public String getViewName() {
            return viewName;
        }

        @Override
        public void setUserObject(Object userObject) {
            super.setUserObject(userObject); //To change body of generated methods, choose Tools | Templates.
        }

        /**
         * @return the index
         */
        public int getIndex() {
            return index;
        }
        
    }
    
    
    class QueryFilter {
        private String categoryfilter = "";
        private String datefilter = "";
        private String monthfilter = "";
        private String townfilter = "";
        private String yearfilter = "";
        private String statusfilter = "";
        private String stringfilter;

        public QueryFilter() {
        }

        /**
         * задаёт значение фильтра по категории
         * @param categoryfilter the categoryfilter to set
         */
        public void setCategoryfilter(String categoryfilter) {
            this.categoryfilter = categoryfilter;
        }

        /**
         * задаёт значение фильтра по дате
         * @param datefilter the datefilter to set
         */
        public void setDatefilter(String datefilter) {
            this.datefilter = datefilter;
        }

        /**
         * задаёт значение фильтра по месяцу
         * @param monthfilter the monthfilter to set
         */
        public void setMonthfilter(String monthfilter) {
            this.monthfilter = monthfilter;
        }

        /**
         * задаёт значение фильтра по населённым пунктам
         * @param townfilter the townfilter to set
         */
        public void setTownfilter(String townfilter) {
            this.townfilter = townfilter;
        }

        /**
         * задаёт значение фильтра по годам
         * @param yearfilter the yearfilter to set
         */
        public void setYearfilter(String yearfilter) {
            this.yearfilter = yearfilter;
        }

        /**
         * задаёт значение фильтра по состоянию
         * @param statusfilter the statusfilter to set
         */
        public void setStatusfilter(String statusfilter) {
            this.statusfilter = statusfilter;
        }

        /**
         * Возвращает строку - фильтр записей
         * @return the stringfilter
         */
        public String getStringfilter() {
            // собираем строку - фильтр
            stringfilter = retFilter(categoryfilter) + retFilter(monthfilter) +
                    retFilter(yearfilter) + retFilter(datefilter) +
                    retFilter(townfilter) + retFilter(statusfilter);
            if(stringfilter.equals(""))
                return "";
            else
                return stringfilter = stringfilter.substring(7);

        }
        
        /**
         * сбрасывает значение фильтра записей
         */
        public void dropFilter(){
            categoryfilter = "";
            datefilter = "";
            monthfilter = "";
            townfilter = "";
            yearfilter = "";
            statusfilter = "";
            stringfilter = "";
        }
        
        /**
         * Возвращает составную часть строки - фильтра
         * @param str часть строки - фильтра
         * @return преобразованную часть строки - фильтра
         */
        private String retFilter(String str){
            String retval;
            if(str.equals(""))
                retval = "";
            else
                retval = " AND A." + str;
            return retval;
        }
        
    }
    
//    private class WordWrapCellRenderer extends JTextArea implements TableCellRenderer {
//        
//        public WordWrapCellRenderer() {
//            super.setLineWrap(true);
//            super.setWrapStyleWord(true);
//            super.setOpaque(true);
//            
//        }
//
//        @Override
//        public Component getTableCellRendererComponent(JTable table, Object value, 
//                boolean isSelected, boolean hasFocus, int row, int column) {
////            System.out.println("row=" + row + " column=" + column + " value=" + value.toString());
//            setText(value.toString());
//            setFont(table.getFont());
//            setSize(table.getColumnModel().getColumn(column).getWidth(), getPreferredSize().height);
//            if (table.getRowHeight(row) != getPreferredSize().height) {
//                table.setRowHeight(row, getPreferredSize().height);
//            }
//            if(isSelected){
//                setBackground(table.getSelectionBackground());
//                setForeground(table.getSelectionForeground());
////                setBorder(new EtchedBorder(1));
//            } else {
//                setBackground(table.getBackground());
//                setForeground(table.getForeground());
////                setBorder(new BevelBorder(1));
//            }
//            return this;
//        }
//    }
}
