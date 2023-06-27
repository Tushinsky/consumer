/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import abonentgaz.TableCell_Renderer;
import dao_impl.*;
import entities.*;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.*;
import runqueries.Runquery;

/**
 * Создаёт диалоговое окно для добавления нового элемента базы данных
 * @author Sergii.Tushinskyi
 */
public class NewItemDialog extends JPanel {
    public enum NewItemType{NewObject,NewCount,NewCorrector,NewDatchik,NewEquipment,
    NewHipress,NewMidpress,NewRlowpress,NewDlowpress,NewRegulator,NewBorderBalance,
    ExistBorderBalance};
    private final NewItemType nit;
    private final JPanel panel;
    private final JButton okButton;// кнопка подтверждения выбора
    private final JButton cancelButton;// кнопка отмены действия
    private boolean ok;// флаг подтверждения или отмены выбора
    private JDialog dialog;// окно диалога, в котором будут отображаться элементы
    private String dialogTitle;// заоголовок окна
    private int indentity;// идентификатор записи (значение внешнего ключа)

    public NewItemDialog(NewItemType t) {
        super.setLayout(new BorderLayout());
        panel = new JPanel(new BorderLayout());// панель для размещения содержимого
        nit = t;// получаем тип добавляемого объекта
        
        // создаём кнопки
        okButton = new JButton("OK");// действие для этой кнопки определяем отдельно
        cancelButton = new JButton("Отмена");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
            }
        });
        
        
    }
    
    public void createNewItem(){
        // в зависимости от того, какой объект создаётся вызываем соответсвующие методы
        switch(nit){
            case NewObject:
                createNewObject();
                break;
            case NewCount:
                createNewCount();
                break;
            case NewCorrector:
                createNewCorrector();
                break;
            case NewDatchik:
                createNewDatchik();
                break;
            case NewEquipment:
                createNewEquipment();
                break;
            case NewHipress:
                createNewHilose();
                break;
            case NewMidpress:
                createNewMidlose();
                break;
            case NewRlowpress:
                createNewRlowlose();
                break;
            case NewDlowpress:
                createNewDlowlose();
                break;
            case NewRegulator:
                createNewRegulator();
                break;
            case NewBorderBalance:
                createNewBorderBalance();
                break;
            case ExistBorderBalance:
                createExistBorderBalance();
                break;
        }
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
        }

 
        // задаём заголовок и выводим диалог на экран
        dialog.setTitle(dialogTitle);

        // задаём размеры
 //            dialog.setSize(flManager.getWidth(), flManager.getHeight());

        // задаём расположение в центре вызывающей формы
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
        return ok;// возвращаем результат выбора
    }
    
    /**
     * 
     * @return Box
     */
    private Box commandButtonBox(){
        // размещаем кнопки на панели кнопок
        Box buttonBox = Box.createHorizontalBox();
        buttonBox.add(Box.createHorizontalGlue());
        buttonBox.add(okButton);
        buttonBox.add(Box.createHorizontalStrut(40));// вставляем между ними распорку
        buttonBox.add(cancelButton);
        buttonBox.add(Box.createHorizontalGlue());
        return buttonBox;
    }
    
    /**
     * создаёт элементы интерфейса для ввода данных при создании нового объекта
     */
    private void createNewObject(){
        JLabel lblContent;// метка для имени объекта
        JLabel lblCity;// метка для города
        JLabel lblStreet;// метка для улицы
        JLabel lblAddres;// метка для адреса объекта
        final JTextField txtName;// наименование объекта
        final JComboBox cmbCity;// список населённых пунктов
        final JComboBox cmbStreet;// список улиц
        final JTextField txtAddres;// адрес объекта
        final SprcityDaoImpl cdi;// справочник городов
        final SprstreetDaoImpl sdi;// справочник улиц
        
        // определяем заголовок окна
        dialogTitle = "Новый объект";
        // создаём компоненты интерфейса
        lblContent = new JLabel("Название");
        lblCity = new JLabel("Город");
        lblStreet = new JLabel("Улица");
        lblAddres = new JLabel("Адрес");

        // создаём и заполняем списки городов и улиц
        cdi = new SprcityDaoImpl();
        cdi.getEntities();// получаем данные
        Object[] content = new Object[cdi.getCount()];

        // получаем данные
        for(int i = 0; i < content.length; i++)
            content[i] = cdi.getItem(i).getCityName();
        ComboBoxModel citymodel = new DefaultComboBoxModel<>(content);
        cmbCity = new JComboBox(citymodel);
        cmbCity.setMaximumSize(cmbCity.getPreferredSize());

        // создаём и заполняем список улиц
        sdi = new SprstreetDaoImpl();
        sdi.getEntities();// получаем данные
        content = new Object[sdi.getCount()];

        // получаем данные
        for(int i = 0; i < content.length; i++)
            content[i] = sdi.getItem(i).getStreetName();
        ComboBoxModel streetmodel = new DefaultComboBoxModel<>(content);
        cmbStreet = new JComboBox(streetmodel);
        cmbStreet.setMaximumSize(cmbStreet.getPreferredSize());

        // создаём текстовые поля
        txtName = new JTextField("new name",30);
        txtName.setMaximumSize(txtName.getPreferredSize());
        txtAddres = new JTextField("",10);
        txtAddres.setMaximumSize(txtAddres.getPreferredSize());

        // при получении фокуса в поле ввода будет выделяться весь текст
        txtName.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent fe) {
                super.focusGained(fe); //To change body of generated methods, choose Tools | Templates.
                txtName.setSelectionStart(0);
                txtName.setSelectionEnd(txtName.getText().length());
            }

        });

        txtAddres.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent fe) {
                super.focusGained(fe); //To change body of generated methods, choose Tools | Templates.
                txtAddres.setSelectionStart(0);
                txtAddres.setSelectionEnd(txtAddres.getText().length());
            }

        });
        
        // определяем действия для кнопоки ОК
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idCity = cdi.getItem(cmbCity.getSelectedIndex()).getId();// код города
                int idStreet = sdi.getItem(cmbStreet.getSelectedIndex()).getId();// код улицы
                String nameObject = "'" + txtName.getText() + "'";// наименовавние объекта
                String addresObject = "'" + txtAddres.getText() + "'";// адрес объекта
                
                String fieldname = "IDORGANIZATION,NAMEOBJECT,IDCITY,IDSTREET,ADDRES";
                String fieldvalue = "?,?,?,?,?";
                Class[] classValue = new Class[]{Integer.class, String.class,
                    Integer.class, Integer.class, String.class};
                Object[] param = new Object[]{indentity, nameObject,
                    idCity, idStreet, addresObject};
                
                // создаём объект для выполнения запросов
                Runquery rq = new Runquery("OBJECTS");
                ok = rq.addEntity(fieldname, fieldvalue, classValue, param);
                dialog.setVisible(false);
            }
        });

//            flManager = new FrameLayoutManager("countpanel.properties");

        // размещаем метки, поля ввода и списки в вертикальном блоке
        Box nameBox = Box.createHorizontalBox();
        nameBox.add(Box.createHorizontalStrut(15));
        nameBox.add(lblContent);
        nameBox.add(Box.createHorizontalGlue());
        nameBox.add(txtName);
        nameBox.add(Box.createHorizontalStrut(15));

        Box addresBox = Box.createHorizontalBox();
        addresBox.add(Box.createHorizontalStrut(15));
        addresBox.add(lblAddres);
        addresBox.add(Box.createHorizontalGlue());
        addresBox.add(txtAddres);
        addresBox.add(Box.createHorizontalStrut(15));

        Box cityBox = Box.createHorizontalBox();
        cityBox.add(Box.createHorizontalStrut(15));
        cityBox.add(lblCity);
        cityBox.add(Box.createHorizontalGlue());
        cityBox.add(cmbCity);
        cityBox.add(Box.createHorizontalStrut(15));

        Box streetBox = Box.createHorizontalBox();
        streetBox.add(Box.createHorizontalStrut(15));
        streetBox.add(lblStreet);
        streetBox.add(Box.createHorizontalGlue());
        streetBox.add(cmbStreet);
        streetBox.add(Box.createHorizontalStrut(15));

        // размещаем кнопки на панели кнопок
        Box buttonBox = commandButtonBox();

        Box vertBox = Box.createVerticalBox();
        vertBox.add(nameBox);
        vertBox.add(Box.createVerticalStrut(10));
        vertBox.add(cityBox);
        vertBox.add(Box.createVerticalStrut(10));
        vertBox.add(streetBox);
        vertBox.add(Box.createVerticalStrut(10));
        vertBox.add(addresBox);
        vertBox.add(Box.createVerticalStrut(10));

        // добавляем элементы на панель
        panel.add(vertBox, BorderLayout.CENTER);

        panel.add(buttonBox, BorderLayout.SOUTH);
        super.add(panel, BorderLayout.CENTER);
    }
    
    /**
     * создаёт элементы интерфейса для ввода данных при создании нового счётчика
     */
    private void createNewCount(){
        final SprcounterDaoImpl sdi;// объект получения данных по справочнику счётчиков
        String[] columnName;// наименования столбцов таблицы
        Class[] columnClass;// типы данных таблицы
        final JTable countTable = new JTable();
        
        
        // определяем заголовок окна
        dialogTitle = "Новый счётчик";
        
        // создаём объект доступа к данным
        sdi = new SprcounterDaoImpl();
        
        // получаем данные и заполняем модель таблицы
        int count = sdi.getCount();
        Object[][] content = new Object[count][];
        for(int i = 0; i < count; i++){
            Sprcounter counter = sdi.getItem(i);
            content[i] = counter.toDataArray();
        }
        columnName = sdi.getColumnName();
        columnClass = sdi.getColumnClass();
        
        // создаём таблицу и заполняем таблицу
        createNewItem(countTable, content, columnName, columnClass, BorderLayout.CENTER);
        // добавляем элементы на панель окна
        addButtons_and_Panel();
        
        // определяем действие для кнопки ОК
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // добавление нового счётчика
                int row = countTable.getSelectedRow();
                System.out.println("row=" + row);
                int idCount = sdi.getItem(row).getId();
                System.out.println("idcount=" + idCount);
                ok = addNewCount(idCount);
                dialog.setVisible(false);
                
            }
        });
        
    }
    
    /**
     * создаёт элмементы интерфейса для ввода данных при создании нового корректора
     */
    private void createNewCorrector(){
        final JTable itemTable = new JTable();//таблица счётчиков
        final SprcorrectorDaoImpl sdi;// объект получения данных по справочнику счётчиков
        String[] columnName;// наименования столбцов таблицы
        Class[] columnClass;// типы данных таблицы
        
        // определяем заголовок окна
        dialogTitle = "Новый корректор";
        
        // создаём объект доступа к данным
        sdi = new SprcorrectorDaoImpl();
        
        // получаем данные и заполняем модель таблицы
        int count = sdi.getCount();
        Object[][] content = new Object[count][];
        for(int i = 0; i < count; i++){
            Sprcorrector item = sdi.getItem(i);
            content[i] = item.toDataArray();
        }
        columnName = sdi.getColumnName();
        columnClass = sdi.getColumnClass();
        
        // создаём таблицу и заполняем таблицу данными
        createNewItem(itemTable, content, columnName, columnClass, BorderLayout.CENTER);
        
        // добавляем элементы на панель окна
        addButtons_and_Panel();
        
        // определяем действие для кнопки ОК
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // добавление нового корректора
                
                ok = addNewCorrector(sdi.getItem(itemTable.getSelectedRow()).getId());
                dialog.setVisible(false);
                
            }
        });
        
    }
    
    
    /**
     * создаёт элмементы интерфейса для ввода данных при создании нового датчика
     */
    private void createNewDatchik(){
        final JTable itemTable = new JTable();//таблица счётчиков
        final SprdatchikDaoImpl sdi;// объект получения данных по справочнику счётчиков
        String[] columnName;// наименования столбцов таблицы
        Class[] columnClass;// типы данных таблицы
        
        // определяем заголовок окна
        dialogTitle = "Новый датчик";
        
        // создаём объект доступа к данным
        sdi = new SprdatchikDaoImpl();
        
        // получаем данные и заполняем модель таблицы
        int count = sdi.getCount();
        Object[][] content = new Object[count][];
        for(int i = 0; i < count; i++){
            Sprdatchik item = sdi.getItem(i);
            content[i] = item.toDataArray();
        }
        columnName = sdi.getColumnName();
        columnClass = sdi.getColumnClass();
        
        // создаём таблицу и заполняем таблицу данными
        createNewItem(itemTable, content, columnName, columnClass, BorderLayout.CENTER);
        // добавляем элементы на панель окна
        addButtons_and_Panel();
        
        // определяем действие для кнопки ОК
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // добавление нового датчика
                
                ok = addNewDatchik(sdi.getItem(itemTable.getSelectedRow()).getId());
                dialog.setVisible(false);
                
            }
        });
        
    }
    
    /**
     * создаёт элмементы интерфейса для ввода данных при создании нового оборудования
     */
    private void createNewEquipment(){
        final JTable itemTable = new JTable();//таблица оборудования
        final SprequipmentDaoImpl sdi;// объект получения данных по справочнику оборудования
        final JTable counttable = new JTable();// таблица счётиков
        final JComboBox countBox = new JComboBox();
        final CountersDaoImpl cdi;// данные по счётчикам
        String[] columnName;// наименования столбцов таблицы
        Class[] columnClass;// типы данных таблицы
        cdi = new CountersDaoImpl(indentity);// создаём объект
        if(cdi.getCount() > 0){
            // если счётчики есть
            // определяем заголовок окна
            dialogTitle = "Новое оборудование";

            int count = cdi.getCount();
            Object[] boxModel = new Object[count];// модель данных для списка
            for(int i = 0; i < count; i++){
                Counters item = cdi.getItem(i);
                Object[] data = item.toDataArray();
                boxModel[i] = data[1] + " №" + data[2];// заполняем модель
            }
            ComboBoxModel cbm = new DefaultComboBoxModel(boxModel);
            countBox.setModel(cbm);
            // добавляем список на панель
            JLabel label = new JLabel("Счётчики");
            JPanel countpanel = new JPanel();
            countpanel.add(label);
            countpanel.add(countBox);
            panel.add(countpanel, BorderLayout.NORTH);
            
//            // создаём и заполняем таблицу счётчиков
//            Object[][] content = new Object[count][3];
//            for(int i = 0; i < count; i++){
//                Counters item = cdi.getItem(i);
//                Object[] data = item.toDataArray();
//                content[i][0] = i + 1;
//                content[i][1] = data[1];
//                content[i][2] = data[2];
//            }
//            columnName = cdi.getColumnName();
//            columnClass = cdi.getColumnClass();
//            // создаём таблицу и заполняем данными
//            createNewItem(counttable, content, columnName, columnClass, 
//                    BorderLayout.NORTH);
//            // задаём размер таблицы
//            counttable.setPreferredSize(new Dimension((int) 
//                    counttable.getPreferredSize().getWidth(), 300));
//            // выделяем первую строку таблицы
//            counttable.setColumnSelectionInterval(1, 1);
//            counttable.setRowSelectionInterval(0, 0);
            
            // создаём объект доступа к данным
            sdi = new SprequipmentDaoImpl();

            // получаем данные и заполняем модель таблицы
            count = sdi.getCount();
            Object[][] content = new Object[count][];
            for(int i = 0; i < count; i++){
                Sprequipment item = sdi.getItem(i);
                content[i] = item.toDataArray();
            }
            columnName = sdi.getColumnName();
            columnClass = sdi.getColumnClass();

            // создаём таблицу и заполняем таблицу данными
            createNewItem(itemTable, content, columnName, columnClass, 
                    BorderLayout.CENTER);
            // добавляем элементы на панель окна
            addButtons_and_Panel();
            
            // выделяем первую строку таблицы
            itemTable.setColumnSelectionInterval(1, 1);
            itemTable.setRowSelectionInterval(0, 0);
            
            // определяем действие для кнопки ОК
            okButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    // добавление нового оборудования
                    int idCount = cdi.getItem(countBox.getSelectedIndex()).getId();
                    int idEquipment = sdi.getItem(itemTable.getSelectedRow()).getId();
                    ok = addNewEquipment(idCount,idEquipment);
                    dialog.setVisible(false);

                }
            });
        } else {
            // если счётчиков нет уведомляем пользователя и закрываем окно
            ok = false;
            dialog.setVisible(ok);
        }
        
    }
    
    /**
     * создаёт элмементы интерфейса для ввода данных при создании нового оборудования
     */
    private void createNewHilose(){
        final JTable itemTable = new JTable();//таблица счётчиков
        final SprgazlinerhipressDaoImpl sdi;// объект получения данных по справочнику счётчиков
        String[] columnName;// наименования столбцов таблицы
        Class[] columnClass;// типы данных таблицы
        // определяем заголовок окна
        dialogTitle = "Потери высокого давления";
        
        // создаём объект доступа к данным
        sdi = new SprgazlinerhipressDaoImpl();
        
        // получаем данные и заполняем модель таблицы
        int count = sdi.getCount();
        Object[][] content = new Object[count][];
        for(int i = 0; i < count; i++){
            Sprgazlinerhipress item = sdi.getItem(i);
            content[i] = item.toDataArray();
        }
        columnName = sdi.getColumnName();
        columnClass = sdi.getColumnClass();
        
        // создаём таблицу и заполняем таблицу данными
        createNewItem(itemTable, content, columnName, columnClass, BorderLayout.CENTER);
        
        // добавляем элементы на панель окна
        addButtons_and_Panel();
        
        // определяем действие для кнопки ОК
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // добавление нового оборудования
                
                ok = addNewHilose(sdi.getItem(itemTable.getSelectedRow()).getId());
                dialog.setVisible(false);
                
            }
        });
        dialogTitle = "";
    }
    
    /**
     * создаёт элмементы интерфейса для ввода данных при создании нового оборудования
     */
    private void createNewMidlose(){
        final JTable itemTable = new JTable();//таблица счётчиков
        final SprgazlinermidpressDaoImpl sdi;// объект получения данных по справочнику счётчиков
        String[] columnName;// наименования столбцов таблицы
        Class[] columnClass;// типы данных таблицы
        
        // определяем заголовок окна
        dialogTitle = "Потери среднего давления";
        
        // создаём объект доступа к данным
        sdi = new SprgazlinermidpressDaoImpl();
        
        // получаем данные и заполняем модель таблицы
        int count = sdi.getCount();
        Object[][] content = new Object[count][];
        for(int i = 0; i < count; i++){
            Sprgazlinermidpress item = sdi.getItem(i);
            content[i] = item.toDataArray();
        }
        columnName = sdi.getColumnName();
        columnClass = sdi.getColumnClass();
        
        // добавляем элементы на панель окна
        addButtons_and_Panel();
        // создаём таблицу и заполняем таблицу данными
        createNewItem(itemTable, content, columnName, columnClass, BorderLayout.CENTER);
        
        // определяем действие для кнопки ОК
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // добавление нового оборудования
                
                ok = addNewMidlose(sdi.getItem(itemTable.getSelectedRow()).getId());
                dialog.setVisible(false);
                
            }
        });
        dialogTitle = "";
    }
    
    /**
     * создаёт элмементы интерфейса для ввода данных при создании нового оборудования
     */
    private void createNewRlowlose(){
        final JTable itemTable = new JTable();//таблица счётчиков
        final SprgazlinerrlowpressDaoImpl sdi;// объект получения данных по справочнику счётчиков
        String[] columnName;// наименования столбцов таблицы
        Class[] columnClass;// типы данных таблицы
        
        // определяем заголовок окна
        dialogTitle = "Потери распределительные низкого давления";
        
        // создаём объект доступа к данным
        sdi = new SprgazlinerrlowpressDaoImpl();
        
        // получаем данные и заполняем модель таблицы
        int count = sdi.getCount();
        Object[][] content = new Object[count][];
        for(int i = 0; i < count; i++){
            Sprgazlinerrlowpress item = sdi.getItem(i);
            content[i] = item.toDataArray();
        }
        columnName = sdi.getColumnName();
        columnClass = sdi.getColumnClass();
        
        // создаём таблицу и заполняем таблицу данными
        createNewItem(itemTable, content, columnName, columnClass, BorderLayout.CENTER);
        
        // добавляем элементы на панель окна
        addButtons_and_Panel();
        // определяем действие для кнопки ОК
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // добавление нового оборудования
                
                ok = addNewRlowlose(sdi.getItem(itemTable.getSelectedRow()).getId());
                dialog.setVisible(false);
                
            }
        });
        dialogTitle = "";
    }
    
    /**
     * создаёт элмементы интерфейса для ввода данных при создании нового оборудования
     */
    private void createNewDlowlose(){
        final JTable itemTable = new JTable();//таблица счётчиков
        final SprgazlinerdlowpressDaoImpl sdi;// объект получения данных по справочнику счётчиков
        String[] columnName;// наименования столбцов таблицы
        Class[] columnClass;// типы данных таблицы
        
        // определяем заголовок окна
        dialogTitle = "Потери дворовые низкого давления";
        
        // создаём объект доступа к данным
        sdi = new SprgazlinerdlowpressDaoImpl();
        
        // получаем данные и заполняем модель таблицы
        int count = sdi.getCount();
        Object[][] content = new Object[count][];
        for(int i = 0; i < count; i++){
            Sprgazlinerdlowpress item = sdi.getItem(i);
            content[i] = item.toDataArray();
        }
        columnName = sdi.getColumnName();
        columnClass = sdi.getColumnClass();
        
        // создаём таблицу и заполняем таблицу данными
        createNewItem(itemTable, content, columnName, columnClass, BorderLayout.CENTER);
        // добавляем элементы на панель окна
        addButtons_and_Panel();
        
        // определяем действие для кнопки ОК
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // добавление нового оборудования
                
                ok = addNewDlowlose(sdi.getItem(itemTable.getSelectedRow()).getId());
                dialog.setVisible(false);
                
            }
        });
        dialogTitle = "";
    }
    
    /**
     * создаёт элмементы интерфейса для ввода данных при создании нового оборудования
     */
    private void createNewRegulator(){
        final JTable itemTable = new JTable();//таблица счётчиков
        final SprgazregulatorDaoImpl sdi;// объект получения данных по справочнику счётчиков
        String[] columnName;// наименования столбцов таблицы
        Class[] columnClass;// типы данных таблицы
        
        // определяем заголовок окна
        dialogTitle = "Потери на регуляторах";
        
        // создаём объект доступа к данным
        sdi = new SprgazregulatorDaoImpl();
        
        // получаем данные и заполняем модель таблицы
        int count = sdi.getCount();
        Object[][] content = new Object[count][];
        for(int i = 0; i < count; i++){
            Sprgazregulator item = sdi.getItem(i);
            content[i] = item.toDataArray();
        }
        columnName = sdi.getColumnName();
        columnClass = sdi.getColumnClass();
        
        // создаём таблицу и заполняем таблицу данными
        createNewItem(itemTable, content, columnName, columnClass, BorderLayout.CENTER);
        // добавляем элементы на панель окна
        addButtons_and_Panel();
        
        // определяем действие для кнопки ОК
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // добавление нового оборудования
                
                ok = addNewRegulator(sdi.getItem(itemTable.getSelectedRow()).getId());
                dialog.setVisible(false);
                
            }
        });
        dialogTitle = "";
    }
    
    /**
     * создаёт элмементы интерфейса для ввода данных при записи нового акта разграничения
     */
    private void createNewBorderBalance(){
        JLabel lblNumber;
        JLabel lblDate;
        JLabel lblContent;// метка для имени объекта
        final JTextField txtActnumber;// номер акта
        final JTextField txtActdate;// дата акта
        final JTextField txtContent;// наименование объекта
        final JCheckBox chkBackup;// признак возврата акта расчёта
        
        // определяем заголовок окна
        dialogTitle = "Новый акт разграничения";
        // создаём компоненты интерфейса
        lblNumber = new JLabel("№ акта");
        lblDate = new JLabel("Дата акта");
        lblContent = new JLabel("Содержание");
        // задаём предпочтительные размеры меток
        lblNumber.setPreferredSize(new Dimension((int) 
                lblContent.getPreferredSize().getWidth(), 
                (int) lblNumber.getPreferredSize().getHeight()));
        lblDate.setPreferredSize(new Dimension((int) 
                lblContent.getPreferredSize().getWidth(), 
                (int) lblDate.getPreferredSize().getHeight()));
        
        // создаём текстовые поля и флажок
        txtActnumber = new JTextField("",10);
        txtActdate = new JTextField("",10);
        txtContent = new JTextField("",50);
        chkBackup = new JCheckBox("Возврат", false);
        txtActnumber.setMaximumSize(txtActnumber.getPreferredSize());
        txtActdate.setMaximumSize(txtActdate.getPreferredSize());
        txtContent.setMaximumSize(txtContent.getPreferredSize());
        
        // определяем действия для кнопоки ОК
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                short number = Short.valueOf(txtActnumber.getText());
                String date = "'" + txtActdate.getText() + "'";
                String nameBalance = "'" + txtContent.getText() + "'";// наименовавние объекта
                short backup = (short) (chkBackup.isSelected() ? 1 : 0);
                String fieldname = "IDOBJECT,ACTNUMBER,ACTDATE,CONTENT,BACKUP";
                String fieldvalue = "?,?,?,?,?";
                Class[] classValue = new Class[]{Integer.class, Short.class,
                    String.class, String.class, Short.class};
                Object[] param = new Object[]{indentity, number,
                    date, nameBalance, backup};
                
                // создаём объект для выполнения запросов
                Runquery rq = new Runquery("BORDERBALANCE");
                ok = rq.addEntity(fieldname, fieldvalue, classValue, param);
                dialog.setVisible(false);
            }
        });

//            flManager = new FrameLayoutManager("countpanel.properties");

        // размещаем метки, поля ввода и списки в вертикальном блоке
        Box actBox = Box.createHorizontalBox();
        actBox.add(Box.createHorizontalStrut(15));
        actBox.add(lblNumber);
        actBox.add(Box.createHorizontalStrut(20));
        actBox.add(txtActnumber);
        actBox.add(Box.createHorizontalStrut(50));
        actBox.add(lblDate);
        actBox.add(Box.createHorizontalStrut(10));
        actBox.add(txtActdate);
        actBox.add(Box.createHorizontalGlue());
        
        Box nameBox = Box.createHorizontalBox();
        nameBox.add(Box.createHorizontalStrut(15));
        nameBox.add(lblContent);
        nameBox.add(Box.createHorizontalStrut(20));
        nameBox.add(txtContent);
        nameBox.add(Box.createHorizontalGlue());

        Box backupBox = Box.createHorizontalBox();
        backupBox.add(Box.createHorizontalStrut(15));
        backupBox.add(chkBackup);
        backupBox.add(Box.createHorizontalGlue());
        
        // размещаем кнопки на панели кнопок
        Box buttonBox = commandButtonBox();

        // размещаем элементы интерфейса
        Box vertBox = Box.createVerticalBox();
        vertBox.add(actBox);
        vertBox.add(Box.createVerticalStrut(10));
        vertBox.add(nameBox);
        vertBox.add(Box.createVerticalStrut(10));
        vertBox.add(backupBox);
        vertBox.add(Box.createVerticalStrut(10));

        // добавляем элементы на панель
        panel.add(vertBox, BorderLayout.CENTER);

        panel.add(buttonBox, BorderLayout.SOUTH);
        super.add(panel, BorderLayout.CENTER);
    }
    
    private void createExistBorderBalance(){
        JLabel lblNumber;
        JLabel lblDate;
        JLabel lblContent;// метка для имени объекта
        final JTextField txtActnumber;// номер акта
        final JTextField txtActdate;// дата акта
        final JTextField txtContent;// наименование объекта
        final JCheckBox chkBackup;// признак возврата акта расчёта
        final BorderbalanceDaoImpl bdi = new BorderbalanceDaoImpl(indentity);// объект доступа к данным
        
        // получаем данные
        final Borderbalance balance = bdi.getItem(0);
        
        // определяем заголовок окна
        dialogTitle = "Акт разграничения";
        // создаём компоненты интерфейса
        lblNumber = new JLabel("№ акта");
        lblDate = new JLabel("Дата акта");
        lblContent = new JLabel("Содержание");
        // задаём предпочтительные размеры меток
        lblNumber.setPreferredSize(new Dimension((int) 
                lblContent.getPreferredSize().getWidth(), 
                (int) lblNumber.getPreferredSize().getHeight()));
        lblDate.setPreferredSize(new Dimension((int) 
                lblContent.getPreferredSize().getWidth(), 
                (int) lblDate.getPreferredSize().getHeight()));
        
        // создаём текстовые поля и флажок
        txtActnumber = new JTextField(String.valueOf(balance.getActnumber()),10);
        txtActdate = new JTextField(String.valueOf(balance.getActdate()),10);
        txtContent = new JTextField(balance.getContent(),50);
        chkBackup = new JCheckBox("Возврат", balance.getBackup());
        txtActnumber.setMaximumSize(txtActnumber.getPreferredSize());
        txtActdate.setMaximumSize(txtActdate.getPreferredSize());
        txtContent.setMaximumSize(txtContent.getPreferredSize());
        
        // определяем действия для кнопоки ОК
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                short number = Short.valueOf(txtActnumber.getText());
                String date = "'" + txtActdate.getText() + "'";
                String content = "'" + txtContent.getText() + "'";// наименовавние объекта
                // обновляем данные
                balance.setActnumber(number);
                balance.setActdate(date);
                balance.setContent(content);
                balance.setBackup(chkBackup.isSelected());
                ok = true;
                dialog.setVisible(false);
            }
        });

//            flManager = new FrameLayoutManager("countpanel.properties");

        // размещаем метки, поля ввода и списки в вертикальном блоке
        Box actBox = Box.createHorizontalBox();
        actBox.add(Box.createHorizontalStrut(15));
        actBox.add(lblNumber);
        actBox.add(Box.createHorizontalStrut(20));
        actBox.add(txtActnumber);
        actBox.add(Box.createHorizontalStrut(50));
        actBox.add(lblDate);
        actBox.add(Box.createHorizontalStrut(10));
        actBox.add(txtActdate);
        actBox.add(Box.createHorizontalGlue());
        
        Box nameBox = Box.createHorizontalBox();
        nameBox.add(Box.createHorizontalStrut(15));
        nameBox.add(lblContent);
        nameBox.add(Box.createHorizontalStrut(20));
        nameBox.add(txtContent);
        nameBox.add(Box.createHorizontalGlue());

        Box backupBox = Box.createHorizontalBox();
        backupBox.add(Box.createHorizontalStrut(15));
        backupBox.add(chkBackup);
        backupBox.add(Box.createHorizontalGlue());
        
        // размещаем кнопки на панели кнопок
        Box buttonBox = commandButtonBox();

        // размещаем элементы интерфейса
        Box vertBox = Box.createVerticalBox();
        vertBox.add(actBox);
        vertBox.add(Box.createVerticalStrut(10));
        vertBox.add(nameBox);
        vertBox.add(Box.createVerticalStrut(10));
        vertBox.add(backupBox);
        vertBox.add(Box.createVerticalStrut(10));

        // добавляем элементы на панель
        panel.add(vertBox, BorderLayout.CENTER);

        panel.add(buttonBox, BorderLayout.SOUTH);
        super.add(panel, BorderLayout.CENTER);
    }
    
    private void createNewItem(JTable itemTable, Object[][] content, String[] columnName, 
            Class[] columnClass, String layout){
        MyTableModel model;// модель данных для заполнения таблицы
        int[] colIndex;// массив нередактируемых столбцов
        JPanel itemPanel;// панель для размещения таблицы
        
        model = new MDIObject.MyTableModelImpl(content, columnName, columnClass);
        
        // получаем массив нередактируемых столбцов таблицы
        colIndex = new int[columnName.length];
        for(int i = 0; i < colIndex.length; i++)
            colIndex[i] = i;
        
        // создаём таблицу
        itemTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        itemTable.setCellSelectionEnabled(true);
        itemTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        // заполняем таблицу данными
        MDIObject.fullTableData(colIndex, model, itemTable);
        TableCell_Renderer.setIntegerTableCellRenderer(itemTable, null);
        MDIObject.addColumnSelectionListener(itemTable);
        // создаём панель прокрутки таблицы
        JScrollPane bar = new JScrollPane(itemTable);
        
        // создаём панель для размещения таблицы данных
        itemPanel = new JPanel();
        itemPanel.add(bar);// добавляем на неё панель прокрутки с таблицей данных
        
        // добавляем все элементы на панель окна
        panel.add(itemPanel,layout);
        
    }
    
    private void addButtons_and_Panel(){
        Box buttonBox = commandButtonBox();// размещаем кнопки
        
        // добавляем все элементы на панель окна
        panel.add(buttonBox,BorderLayout.SOUTH);
        super.add(panel, BorderLayout.CENTER);
    }
    
    /**
     * Добавление нового счётчика
     * @param id идентификатор счётчика из справочника
     * @return TRUE в случае удачного добавления новой записи
     */
    private boolean addNewCount(int id){
        // добавление нового счётчика
        int[] param = new int[]{indentity,id,1};
        System.out.println("indentity=" + indentity + " id=" + id);
//        return false;
        // создаём объект для добавления записи
        Runquery rq = new Runquery("COUNTERS");
        return rq.addEntity("IDOBJECT,IDCOUNTER,IDINSTALPLACE", "?,?,?", param);
    }
    
    /**
     * Добавление нового корректора
     * @param id идентификатор корректора из справочника
     * @return TRUE в случае удачного добавления новой записи
     */
    private boolean addNewCorrector(int id){
        // добавление нового корректора
        int[] param = new int[]{indentity,id};
        // создаём объект для добавления записи
        Runquery rq = new Runquery("CORRECTOR");
        return rq.addEntity("IDOBJECT,IDCORRECTOR", "?,?", param);
    }
    
    /**
     * Добавление нового датчика
     * @param id идентификатор датчика из справочника
     * @return TRUE в случае удачного добавления новой записи
     */
    private boolean addNewDatchik(int id){
       // добавление нового датчика
        int[] param = new int[]{indentity,id};
        // создаём объект для добавления записи
        Runquery rq = new Runquery("DATCHIK");
        return rq.addEntity("IDOBJECT,IDDATCHIK", "?,?", param);
    }
    
    /**
     * Добавление нового оборудования
     * @param id идентификатор оборудования из справочника
     * @return TRUE в случае удачного добавления новой записи
     */
    private boolean addNewEquipment(int idcounter, int idequipment){
        // добавление нового оборудования
        int[] param = new int[]{indentity,idcounter,idequipment};
        // создаём объект для добавления записи
        Runquery rq = new Runquery("EQUIPMENT");
        return rq.addEntity("IDOBJECT,IDCOUNTER,IDEQUIPMENT", "?,?,?", param);
    }
    
    /**
     * Добавление нового оборудования
     * @param id идентификатор оборудования из справочника
     * @return TRUE в случае удачного добавления новой записи
     */
    private boolean addNewHilose(int id){
        // добавление нового оборудования
        int[] param = new int[]{indentity,id};
        // создаём объект для добавления записи
        Runquery rq = new Runquery("HIPRESSLOSE");
        return rq.addEntity("IDOBJECT,IDGAZHIPRESS", "?,?", param);
    }
    
    /**
     * Добавление нового оборудования
     * @param id идентификатор оборудования из справочника
     * @return TRUE в случае удачного добавления новой записи
     */
    private boolean addNewMidlose(int id){
        // добавление нового оборудования
        int[] param = new int[]{indentity,id};
        // создаём объект для добавления записи
        Runquery rq = new Runquery("MIDPRESSLOSE");
        return rq.addEntity("IDOBJECT,IDGAZMIDPRESS", "?,?", param);
    }
    
    /**
     * Добавление нового оборудования
     * @param id идентификатор оборудования из справочника
     * @return TRUE в случае удачного добавления новой записи
     */
    private boolean addNewRlowlose(int id){
        // добавление нового оборудования
        int[] param = new int[]{indentity,id};
        // создаём объект для добавления записи
        Runquery rq = new Runquery("RLOWPRESSLOSE");
        return rq.addEntity("IDOBJECT,IDGAZRLOWPRESS", "?,?", param);
    }
    
    /**
     * Добавление нового оборудования
     * @param id идентификатор оборудования из справочника
     * @return TRUE в случае удачного добавления новой записи
     */
    private boolean addNewDlowlose(int id){
        // добавление нового оборудования
        int[] param = new int[]{indentity,id};
        // создаём объект для добавления записи
        Runquery rq = new Runquery("DLOWLOSE");
        return rq.addEntity("IDOBJECT,IDGAZDLOWPRESS", "?,?", param);
    }
    
    /**
     * Добавление нового оборудования
     * @param id идентификатор оборудования из справочника
     * @return TRUE в случае удачного добавления новой записи
     */
    private boolean addNewRegulator(int id){
        // добавление нового оборудования
        int[] param = new int[]{indentity,id};
        // создаём объект для добавления записи
        Runquery rq = new Runquery("REGLOSE");
        return rq.addEntity("IDOBJECT,IDREGULATOR", "?,?", param);
    }
    

    /**
     * @param indentity the indentity to set
     */
    public void setIndentity(int indentity) {
        this.indentity = indentity;
    }

        
}
