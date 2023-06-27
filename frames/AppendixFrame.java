/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Sergii.Tushinskyi
 */
public class AppendixFrame extends JFrame {
    private JTable table;// таблица для вывода данных выбранного приложения
    public enum AppendixType {UUGType, LimitType, LoseType, JournalType};// перечисление типов приложений
    private AppendixType appendixType;

    public AppendixFrame() throws HeadlessException {
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent we) {
                super.windowOpened(we);
                /* 
                при открытии проверяем какой тип приложения создаётся
                и создаём соответствующие элементы пользовательского интерфейса
                */
                initComponents();
                
                // определяем размеры экрана и задаём размеры формы в половину экрана
                Toolkit kit = getToolkit();
                Dimension size = kit.getScreenSize();
                setSize((int)size.getWidth() / 2, (int)size.getHeight() / 2);
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                setLocationRelativeTo(null);
            }
            
        });
    }
    
    private void initComponents() {
        // создаём элементы интерфейса в зависимости от типа приложения
        switch(appendixType){
            case UUGType:
                getUUGInfo();
                break;
            case LimitType:
                getLimitInfo();
                break;
            case LoseType:
                getLoseInfo();
                break;
            case JournalType:
                getJournalInfo();
        }
        
    }

    /**
     * Формирует данные по узлам учета и газопотребляющему оборудованию по 
     * объектам газопотребления
     */
    private void getUUGInfo() {
        Object[] columnName = new Object[]{"№ п/п",
            "Наименование и адрес объекта Потребителя",
            "Наименование газопотребляющего оборудования",
            "Количество газопотребляющего оборудования",
            "Номинальное потребление газа на одно газопотребляющее оборудование (м3/ч)",
            "Тип и номер прибора учёта газа (заводской номер)"};
        table = new JTable(new Object[][]{}, columnName);
        MDIObject.addColumnSelectionListener(table);// добавляем для таблицы слушатель выделения
        // добавляем таблицу на панель прокрутки и вставляем их в панель содержимого
        JScrollPane pane = new JScrollPane(table);
        getContentPane().add(pane);
    }
    
    /**
     * Формирует данные по заявленным лимитам потребления
     */
    private void getLimitInfo() {
        Object[] columnName = new Object[]{"Месяц","Объём",
            "Месяц","Объём","Месяц","Объём","Месяц","Объём"};
        table = new JTable(new Object[][]{}, columnName);
        // добавляем таблицу на панель прокрутки и вставляем их в панель содержимого
        JScrollPane pane = new JScrollPane(table);
        getContentPane().add(pane);
    }
    
    /**
     * Формирует данные по потерям на объектах газопотребления
     */
    private void getLoseInfo() {
        // создаём дополнительные элементы интерфейса
        JLabel lblObjectName = new JLabel("Наименование объекта:");
        JLabel lblObjectLocation = new JLabel("Местонахождение объекта газопотребления:");
        JLabel lblUUGLocation = new JLabel("Место установки коммерческого узла учёта:");
        JLabel lblLoseData = new JLabel("Исходные данные для расчёта потерь:");
        final JTextField txtObjectName = new JTextField(50);
        final JTextField txtObjectLocation = new JTextField(50);
        final JTextField txtUUGLocation = new JTextField("на стороне", 50);
        final JTextField txtLoseData = new JTextField(50);
        // ложим их на форму
        Box lblBox = Box.createVerticalBox();// контейнер для меток
        Box txtBox = Box.createVerticalBox();// контейнер для полей ввода
        // ложим метки
        lblBox.add(lblObjectName);
        lblBox.add(Box.createVerticalStrut(5));
        lblBox.add(lblObjectLocation);
        lblBox.add(Box.createVerticalStrut(5));
        lblBox.add(lblUUGLocation);
        lblBox.add(Box.createVerticalStrut(5));
        lblBox.add(lblLoseData);
        // ложим поля ввода
        txtBox.add(txtObjectName);
        txtBox.add(Box.createVerticalStrut(3));
        txtBox.add(txtObjectLocation);
        txtBox.add(Box.createVerticalStrut(3));
        txtBox.add(txtUUGLocation);
        txtBox.add(Box.createVerticalStrut(3));
        txtBox.add(txtLoseData);
        Box box = Box.createHorizontalBox();
        box.add(Box.createHorizontalStrut(5));
        box.add(lblBox);
        box.add(Box.createHorizontalStrut(10));
        box.add(txtBox);
        box.add(Box.createHorizontalStrut(5));
        box.add(Box.createHorizontalGlue());
        
        Object[] columnName = new Object[]{"№ п/п",
            "Наименование и адрес объекта Потребителя",
            "Наименование газопотребляющего оборудования",
            "Количество газопотребляющего оборудования",
            "Номинальное потребление газа на одно газопотребляющее оборудование (м3/ч)",
            "Тип и номер прибора учёта газа (заводской номер)"};
        table = new JTable(new Object[][]{}, columnName);
        // добавляем таблицу на панель прокрутки и вставляем их в панель содержимого
        JScrollPane pane = new JScrollPane(table);
        Box mainBox = Box.createVerticalBox();
        mainBox.add(Box.createVerticalStrut(5));
        mainBox.add(box);
        //mainBox.add(Box.createVerticalStrut(5));
        mainBox.add(Box.createVerticalGlue());
        mainBox.add(pane);
        mainBox.add(Box.createVerticalStrut(5));
        mainBox.add(Box.createVerticalGlue());
        getContentPane().add(mainBox);
    }
    
    /**
     * Формирует форму журнала учёта природного газа
     */
    private void getJournalInfo() {
        Object[] columnName = new Object[]{"Дата",
            "Текущие показания счётчика, м3",
            "Разность показаний счётчика газа за предыдущие сутки, м3",
            "Объём производственно - технологических потерь за месяц, м3",
            "Объём газа за сутки в стандартных условиях, м3",
            "Объём газа в стандартных условиях с нарастающим итогом с начала месяца, м3",
            "Подпись ответственного лица Потребителя",
            "Итоговый месячный объём потребления газа и подпись Поставщика"};
        Object[] content = new Object[]{"","","","","","","",""};
        Object[][] rowContent = new Object[4][];
        for (int i = 0; i < rowContent.length; i++)
            rowContent[i] = content;
        table = new JTable(rowContent, columnName);
        // добавляем таблицу на панель прокрутки и вставляем их в панель содержимого
        JScrollPane pane = new JScrollPane(table);
        getContentPane().add(pane);
    }
    
    /**
     * Задаёт тип формируемого приложения : приложение №1, приложение №2,
     * приложение №3, приложение №4. В зависимости от типа формируются элементы 
     * пользовательского интерфейса
     * @param appendixType the appendixType to set
     */
    public void setAppendixType(AppendixType appendixType) {
        this.appendixType = appendixType;
    }
    
    
    private void createMenuItem(){
        
    }
    
}
