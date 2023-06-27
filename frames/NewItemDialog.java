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
 * ������ ���������� ���� ��� ���������� ������ �������� ���� ������
 * @author Sergii.Tushinskyi
 */
public class NewItemDialog extends JPanel {
    public enum NewItemType{NewObject,NewCount,NewCorrector,NewDatchik,NewEquipment,
    NewHipress,NewMidpress,NewRlowpress,NewDlowpress,NewRegulator,NewBorderBalance,
    ExistBorderBalance};
    private final NewItemType nit;
    private final JPanel panel;
    private final JButton okButton;// ������ ������������� ������
    private final JButton cancelButton;// ������ ������ ��������
    private boolean ok;// ���� ������������� ��� ������ ������
    private JDialog dialog;// ���� �������, � ������� ����� ������������ ��������
    private String dialogTitle;// ���������� ����
    private int indentity;// ������������� ������ (�������� �������� �����)

    public NewItemDialog(NewItemType t) {
        super.setLayout(new BorderLayout());
        panel = new JPanel(new BorderLayout());// ������ ��� ���������� �����������
        nit = t;// �������� ��� ������������ �������
        
        // ������ ������
        okButton = new JButton("OK");// �������� ��� ���� ������ ���������� ��������
        cancelButton = new JButton("������");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
            }
        });
        
        
    }
    
    public void createNewItem(){
        // � ����������� �� ����, ����� ������ �������� �������� �������������� ������
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
     * ������� ������ ��� ������ � ���������� ����.
     * @param owner ��������� � ����������� ������ ��� null
     * @return 
     */
    public boolean showDialog(Component owner){
        ok = false;

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
        }

 
        // ����� ��������� � ������� ������ �� �����
        dialog.setTitle(dialogTitle);

        // ����� �������
 //            dialog.setSize(flManager.getWidth(), flManager.getHeight());

        // ����� ������������ � ������ ���������� �����
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
        return ok;// ���������� ��������� ������
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
        return buttonBox;
    }
    
    /**
     * ������ �������� ���������� ��� ����� ������ ��� �������� ������ �������
     */
    private void createNewObject(){
        JLabel lblContent;// ����� ��� ����� �������
        JLabel lblCity;// ����� ��� ������
        JLabel lblStreet;// ����� ��� �����
        JLabel lblAddres;// ����� ��� ������ �������
        final JTextField txtName;// ������������ �������
        final JComboBox cmbCity;// ������ ��������� �������
        final JComboBox cmbStreet;// ������ ����
        final JTextField txtAddres;// ����� �������
        final SprcityDaoImpl cdi;// ���������� �������
        final SprstreetDaoImpl sdi;// ���������� ����
        
        // ���������� ��������� ����
        dialogTitle = "����� ������";
        // ������ ���������� ����������
        lblContent = new JLabel("��������");
        lblCity = new JLabel("�����");
        lblStreet = new JLabel("�����");
        lblAddres = new JLabel("�����");

        // ������ � ��������� ������ ������� � ����
        cdi = new SprcityDaoImpl();
        cdi.getEntities();// �������� ������
        Object[] content = new Object[cdi.getCount()];

        // �������� ������
        for(int i = 0; i < content.length; i++)
            content[i] = cdi.getItem(i).getCityName();
        ComboBoxModel citymodel = new DefaultComboBoxModel<>(content);
        cmbCity = new JComboBox(citymodel);
        cmbCity.setMaximumSize(cmbCity.getPreferredSize());

        // ������ � ��������� ������ ����
        sdi = new SprstreetDaoImpl();
        sdi.getEntities();// �������� ������
        content = new Object[sdi.getCount()];

        // �������� ������
        for(int i = 0; i < content.length; i++)
            content[i] = sdi.getItem(i).getStreetName();
        ComboBoxModel streetmodel = new DefaultComboBoxModel<>(content);
        cmbStreet = new JComboBox(streetmodel);
        cmbStreet.setMaximumSize(cmbStreet.getPreferredSize());

        // ������ ��������� ����
        txtName = new JTextField("new name",30);
        txtName.setMaximumSize(txtName.getPreferredSize());
        txtAddres = new JTextField("",10);
        txtAddres.setMaximumSize(txtAddres.getPreferredSize());

        // ��� ��������� ������ � ���� ����� ����� ���������� ���� �����
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
        
        // ���������� �������� ��� ������� ��
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idCity = cdi.getItem(cmbCity.getSelectedIndex()).getId();// ��� ������
                int idStreet = sdi.getItem(cmbStreet.getSelectedIndex()).getId();// ��� �����
                String nameObject = "'" + txtName.getText() + "'";// ������������� �������
                String addresObject = "'" + txtAddres.getText() + "'";// ����� �������
                
                String fieldname = "IDORGANIZATION,NAMEOBJECT,IDCITY,IDSTREET,ADDRES";
                String fieldvalue = "?,?,?,?,?";
                Class[] classValue = new Class[]{Integer.class, String.class,
                    Integer.class, Integer.class, String.class};
                Object[] param = new Object[]{indentity, nameObject,
                    idCity, idStreet, addresObject};
                
                // ������ ������ ��� ���������� ��������
                Runquery rq = new Runquery("OBJECTS");
                ok = rq.addEntity(fieldname, fieldvalue, classValue, param);
                dialog.setVisible(false);
            }
        });

//            flManager = new FrameLayoutManager("countpanel.properties");

        // ��������� �����, ���� ����� � ������ � ������������ �����
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

        // ��������� ������ �� ������ ������
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

        // ��������� �������� �� ������
        panel.add(vertBox, BorderLayout.CENTER);

        panel.add(buttonBox, BorderLayout.SOUTH);
        super.add(panel, BorderLayout.CENTER);
    }
    
    /**
     * ������ �������� ���������� ��� ����� ������ ��� �������� ������ ��������
     */
    private void createNewCount(){
        final SprcounterDaoImpl sdi;// ������ ��������� ������ �� ����������� ���������
        String[] columnName;// ������������ �������� �������
        Class[] columnClass;// ���� ������ �������
        final JTable countTable = new JTable();
        
        
        // ���������� ��������� ����
        dialogTitle = "����� �������";
        
        // ������ ������ ������� � ������
        sdi = new SprcounterDaoImpl();
        
        // �������� ������ � ��������� ������ �������
        int count = sdi.getCount();
        Object[][] content = new Object[count][];
        for(int i = 0; i < count; i++){
            Sprcounter counter = sdi.getItem(i);
            content[i] = counter.toDataArray();
        }
        columnName = sdi.getColumnName();
        columnClass = sdi.getColumnClass();
        
        // ������ ������� � ��������� �������
        createNewItem(countTable, content, columnName, columnClass, BorderLayout.CENTER);
        // ��������� �������� �� ������ ����
        addButtons_and_Panel();
        
        // ���������� �������� ��� ������ ��
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // ���������� ������ ��������
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
     * ������ ��������� ���������� ��� ����� ������ ��� �������� ������ ����������
     */
    private void createNewCorrector(){
        final JTable itemTable = new JTable();//������� ���������
        final SprcorrectorDaoImpl sdi;// ������ ��������� ������ �� ����������� ���������
        String[] columnName;// ������������ �������� �������
        Class[] columnClass;// ���� ������ �������
        
        // ���������� ��������� ����
        dialogTitle = "����� ���������";
        
        // ������ ������ ������� � ������
        sdi = new SprcorrectorDaoImpl();
        
        // �������� ������ � ��������� ������ �������
        int count = sdi.getCount();
        Object[][] content = new Object[count][];
        for(int i = 0; i < count; i++){
            Sprcorrector item = sdi.getItem(i);
            content[i] = item.toDataArray();
        }
        columnName = sdi.getColumnName();
        columnClass = sdi.getColumnClass();
        
        // ������ ������� � ��������� ������� �������
        createNewItem(itemTable, content, columnName, columnClass, BorderLayout.CENTER);
        
        // ��������� �������� �� ������ ����
        addButtons_and_Panel();
        
        // ���������� �������� ��� ������ ��
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // ���������� ������ ����������
                
                ok = addNewCorrector(sdi.getItem(itemTable.getSelectedRow()).getId());
                dialog.setVisible(false);
                
            }
        });
        
    }
    
    
    /**
     * ������ ��������� ���������� ��� ����� ������ ��� �������� ������ �������
     */
    private void createNewDatchik(){
        final JTable itemTable = new JTable();//������� ���������
        final SprdatchikDaoImpl sdi;// ������ ��������� ������ �� ����������� ���������
        String[] columnName;// ������������ �������� �������
        Class[] columnClass;// ���� ������ �������
        
        // ���������� ��������� ����
        dialogTitle = "����� ������";
        
        // ������ ������ ������� � ������
        sdi = new SprdatchikDaoImpl();
        
        // �������� ������ � ��������� ������ �������
        int count = sdi.getCount();
        Object[][] content = new Object[count][];
        for(int i = 0; i < count; i++){
            Sprdatchik item = sdi.getItem(i);
            content[i] = item.toDataArray();
        }
        columnName = sdi.getColumnName();
        columnClass = sdi.getColumnClass();
        
        // ������ ������� � ��������� ������� �������
        createNewItem(itemTable, content, columnName, columnClass, BorderLayout.CENTER);
        // ��������� �������� �� ������ ����
        addButtons_and_Panel();
        
        // ���������� �������� ��� ������ ��
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // ���������� ������ �������
                
                ok = addNewDatchik(sdi.getItem(itemTable.getSelectedRow()).getId());
                dialog.setVisible(false);
                
            }
        });
        
    }
    
    /**
     * ������ ��������� ���������� ��� ����� ������ ��� �������� ������ ������������
     */
    private void createNewEquipment(){
        final JTable itemTable = new JTable();//������� ������������
        final SprequipmentDaoImpl sdi;// ������ ��������� ������ �� ����������� ������������
        final JTable counttable = new JTable();// ������� ��������
        final JComboBox countBox = new JComboBox();
        final CountersDaoImpl cdi;// ������ �� ���������
        String[] columnName;// ������������ �������� �������
        Class[] columnClass;// ���� ������ �������
        cdi = new CountersDaoImpl(indentity);// ������ ������
        if(cdi.getCount() > 0){
            // ���� �������� ����
            // ���������� ��������� ����
            dialogTitle = "����� ������������";

            int count = cdi.getCount();
            Object[] boxModel = new Object[count];// ������ ������ ��� ������
            for(int i = 0; i < count; i++){
                Counters item = cdi.getItem(i);
                Object[] data = item.toDataArray();
                boxModel[i] = data[1] + " �" + data[2];// ��������� ������
            }
            ComboBoxModel cbm = new DefaultComboBoxModel(boxModel);
            countBox.setModel(cbm);
            // ��������� ������ �� ������
            JLabel label = new JLabel("��������");
            JPanel countpanel = new JPanel();
            countpanel.add(label);
            countpanel.add(countBox);
            panel.add(countpanel, BorderLayout.NORTH);
            
//            // ������ � ��������� ������� ���������
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
//            // ������ ������� � ��������� �������
//            createNewItem(counttable, content, columnName, columnClass, 
//                    BorderLayout.NORTH);
//            // ����� ������ �������
//            counttable.setPreferredSize(new Dimension((int) 
//                    counttable.getPreferredSize().getWidth(), 300));
//            // �������� ������ ������ �������
//            counttable.setColumnSelectionInterval(1, 1);
//            counttable.setRowSelectionInterval(0, 0);
            
            // ������ ������ ������� � ������
            sdi = new SprequipmentDaoImpl();

            // �������� ������ � ��������� ������ �������
            count = sdi.getCount();
            Object[][] content = new Object[count][];
            for(int i = 0; i < count; i++){
                Sprequipment item = sdi.getItem(i);
                content[i] = item.toDataArray();
            }
            columnName = sdi.getColumnName();
            columnClass = sdi.getColumnClass();

            // ������ ������� � ��������� ������� �������
            createNewItem(itemTable, content, columnName, columnClass, 
                    BorderLayout.CENTER);
            // ��������� �������� �� ������ ����
            addButtons_and_Panel();
            
            // �������� ������ ������ �������
            itemTable.setColumnSelectionInterval(1, 1);
            itemTable.setRowSelectionInterval(0, 0);
            
            // ���������� �������� ��� ������ ��
            okButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    // ���������� ������ ������������
                    int idCount = cdi.getItem(countBox.getSelectedIndex()).getId();
                    int idEquipment = sdi.getItem(itemTable.getSelectedRow()).getId();
                    ok = addNewEquipment(idCount,idEquipment);
                    dialog.setVisible(false);

                }
            });
        } else {
            // ���� ��������� ��� ���������� ������������ � ��������� ����
            ok = false;
            dialog.setVisible(ok);
        }
        
    }
    
    /**
     * ������ ��������� ���������� ��� ����� ������ ��� �������� ������ ������������
     */
    private void createNewHilose(){
        final JTable itemTable = new JTable();//������� ���������
        final SprgazlinerhipressDaoImpl sdi;// ������ ��������� ������ �� ����������� ���������
        String[] columnName;// ������������ �������� �������
        Class[] columnClass;// ���� ������ �������
        // ���������� ��������� ����
        dialogTitle = "������ �������� ��������";
        
        // ������ ������ ������� � ������
        sdi = new SprgazlinerhipressDaoImpl();
        
        // �������� ������ � ��������� ������ �������
        int count = sdi.getCount();
        Object[][] content = new Object[count][];
        for(int i = 0; i < count; i++){
            Sprgazlinerhipress item = sdi.getItem(i);
            content[i] = item.toDataArray();
        }
        columnName = sdi.getColumnName();
        columnClass = sdi.getColumnClass();
        
        // ������ ������� � ��������� ������� �������
        createNewItem(itemTable, content, columnName, columnClass, BorderLayout.CENTER);
        
        // ��������� �������� �� ������ ����
        addButtons_and_Panel();
        
        // ���������� �������� ��� ������ ��
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // ���������� ������ ������������
                
                ok = addNewHilose(sdi.getItem(itemTable.getSelectedRow()).getId());
                dialog.setVisible(false);
                
            }
        });
        dialogTitle = "";
    }
    
    /**
     * ������ ��������� ���������� ��� ����� ������ ��� �������� ������ ������������
     */
    private void createNewMidlose(){
        final JTable itemTable = new JTable();//������� ���������
        final SprgazlinermidpressDaoImpl sdi;// ������ ��������� ������ �� ����������� ���������
        String[] columnName;// ������������ �������� �������
        Class[] columnClass;// ���� ������ �������
        
        // ���������� ��������� ����
        dialogTitle = "������ �������� ��������";
        
        // ������ ������ ������� � ������
        sdi = new SprgazlinermidpressDaoImpl();
        
        // �������� ������ � ��������� ������ �������
        int count = sdi.getCount();
        Object[][] content = new Object[count][];
        for(int i = 0; i < count; i++){
            Sprgazlinermidpress item = sdi.getItem(i);
            content[i] = item.toDataArray();
        }
        columnName = sdi.getColumnName();
        columnClass = sdi.getColumnClass();
        
        // ��������� �������� �� ������ ����
        addButtons_and_Panel();
        // ������ ������� � ��������� ������� �������
        createNewItem(itemTable, content, columnName, columnClass, BorderLayout.CENTER);
        
        // ���������� �������� ��� ������ ��
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // ���������� ������ ������������
                
                ok = addNewMidlose(sdi.getItem(itemTable.getSelectedRow()).getId());
                dialog.setVisible(false);
                
            }
        });
        dialogTitle = "";
    }
    
    /**
     * ������ ��������� ���������� ��� ����� ������ ��� �������� ������ ������������
     */
    private void createNewRlowlose(){
        final JTable itemTable = new JTable();//������� ���������
        final SprgazlinerrlowpressDaoImpl sdi;// ������ ��������� ������ �� ����������� ���������
        String[] columnName;// ������������ �������� �������
        Class[] columnClass;// ���� ������ �������
        
        // ���������� ��������� ����
        dialogTitle = "������ ����������������� ������� ��������";
        
        // ������ ������ ������� � ������
        sdi = new SprgazlinerrlowpressDaoImpl();
        
        // �������� ������ � ��������� ������ �������
        int count = sdi.getCount();
        Object[][] content = new Object[count][];
        for(int i = 0; i < count; i++){
            Sprgazlinerrlowpress item = sdi.getItem(i);
            content[i] = item.toDataArray();
        }
        columnName = sdi.getColumnName();
        columnClass = sdi.getColumnClass();
        
        // ������ ������� � ��������� ������� �������
        createNewItem(itemTable, content, columnName, columnClass, BorderLayout.CENTER);
        
        // ��������� �������� �� ������ ����
        addButtons_and_Panel();
        // ���������� �������� ��� ������ ��
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // ���������� ������ ������������
                
                ok = addNewRlowlose(sdi.getItem(itemTable.getSelectedRow()).getId());
                dialog.setVisible(false);
                
            }
        });
        dialogTitle = "";
    }
    
    /**
     * ������ ��������� ���������� ��� ����� ������ ��� �������� ������ ������������
     */
    private void createNewDlowlose(){
        final JTable itemTable = new JTable();//������� ���������
        final SprgazlinerdlowpressDaoImpl sdi;// ������ ��������� ������ �� ����������� ���������
        String[] columnName;// ������������ �������� �������
        Class[] columnClass;// ���� ������ �������
        
        // ���������� ��������� ����
        dialogTitle = "������ �������� ������� ��������";
        
        // ������ ������ ������� � ������
        sdi = new SprgazlinerdlowpressDaoImpl();
        
        // �������� ������ � ��������� ������ �������
        int count = sdi.getCount();
        Object[][] content = new Object[count][];
        for(int i = 0; i < count; i++){
            Sprgazlinerdlowpress item = sdi.getItem(i);
            content[i] = item.toDataArray();
        }
        columnName = sdi.getColumnName();
        columnClass = sdi.getColumnClass();
        
        // ������ ������� � ��������� ������� �������
        createNewItem(itemTable, content, columnName, columnClass, BorderLayout.CENTER);
        // ��������� �������� �� ������ ����
        addButtons_and_Panel();
        
        // ���������� �������� ��� ������ ��
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // ���������� ������ ������������
                
                ok = addNewDlowlose(sdi.getItem(itemTable.getSelectedRow()).getId());
                dialog.setVisible(false);
                
            }
        });
        dialogTitle = "";
    }
    
    /**
     * ������ ��������� ���������� ��� ����� ������ ��� �������� ������ ������������
     */
    private void createNewRegulator(){
        final JTable itemTable = new JTable();//������� ���������
        final SprgazregulatorDaoImpl sdi;// ������ ��������� ������ �� ����������� ���������
        String[] columnName;// ������������ �������� �������
        Class[] columnClass;// ���� ������ �������
        
        // ���������� ��������� ����
        dialogTitle = "������ �� �����������";
        
        // ������ ������ ������� � ������
        sdi = new SprgazregulatorDaoImpl();
        
        // �������� ������ � ��������� ������ �������
        int count = sdi.getCount();
        Object[][] content = new Object[count][];
        for(int i = 0; i < count; i++){
            Sprgazregulator item = sdi.getItem(i);
            content[i] = item.toDataArray();
        }
        columnName = sdi.getColumnName();
        columnClass = sdi.getColumnClass();
        
        // ������ ������� � ��������� ������� �������
        createNewItem(itemTable, content, columnName, columnClass, BorderLayout.CENTER);
        // ��������� �������� �� ������ ����
        addButtons_and_Panel();
        
        // ���������� �������� ��� ������ ��
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // ���������� ������ ������������
                
                ok = addNewRegulator(sdi.getItem(itemTable.getSelectedRow()).getId());
                dialog.setVisible(false);
                
            }
        });
        dialogTitle = "";
    }
    
    /**
     * ������ ��������� ���������� ��� ����� ������ ��� ������ ������ ���� �������������
     */
    private void createNewBorderBalance(){
        JLabel lblNumber;
        JLabel lblDate;
        JLabel lblContent;// ����� ��� ����� �������
        final JTextField txtActnumber;// ����� ����
        final JTextField txtActdate;// ���� ����
        final JTextField txtContent;// ������������ �������
        final JCheckBox chkBackup;// ������� �������� ���� �������
        
        // ���������� ��������� ����
        dialogTitle = "����� ��� �������������";
        // ������ ���������� ����������
        lblNumber = new JLabel("� ����");
        lblDate = new JLabel("���� ����");
        lblContent = new JLabel("����������");
        // ����� ���������������� ������� �����
        lblNumber.setPreferredSize(new Dimension((int) 
                lblContent.getPreferredSize().getWidth(), 
                (int) lblNumber.getPreferredSize().getHeight()));
        lblDate.setPreferredSize(new Dimension((int) 
                lblContent.getPreferredSize().getWidth(), 
                (int) lblDate.getPreferredSize().getHeight()));
        
        // ������ ��������� ���� � ������
        txtActnumber = new JTextField("",10);
        txtActdate = new JTextField("",10);
        txtContent = new JTextField("",50);
        chkBackup = new JCheckBox("�������", false);
        txtActnumber.setMaximumSize(txtActnumber.getPreferredSize());
        txtActdate.setMaximumSize(txtActdate.getPreferredSize());
        txtContent.setMaximumSize(txtContent.getPreferredSize());
        
        // ���������� �������� ��� ������� ��
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                short number = Short.valueOf(txtActnumber.getText());
                String date = "'" + txtActdate.getText() + "'";
                String nameBalance = "'" + txtContent.getText() + "'";// ������������� �������
                short backup = (short) (chkBackup.isSelected() ? 1 : 0);
                String fieldname = "IDOBJECT,ACTNUMBER,ACTDATE,CONTENT,BACKUP";
                String fieldvalue = "?,?,?,?,?";
                Class[] classValue = new Class[]{Integer.class, Short.class,
                    String.class, String.class, Short.class};
                Object[] param = new Object[]{indentity, number,
                    date, nameBalance, backup};
                
                // ������ ������ ��� ���������� ��������
                Runquery rq = new Runquery("BORDERBALANCE");
                ok = rq.addEntity(fieldname, fieldvalue, classValue, param);
                dialog.setVisible(false);
            }
        });

//            flManager = new FrameLayoutManager("countpanel.properties");

        // ��������� �����, ���� ����� � ������ � ������������ �����
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
        
        // ��������� ������ �� ������ ������
        Box buttonBox = commandButtonBox();

        // ��������� �������� ����������
        Box vertBox = Box.createVerticalBox();
        vertBox.add(actBox);
        vertBox.add(Box.createVerticalStrut(10));
        vertBox.add(nameBox);
        vertBox.add(Box.createVerticalStrut(10));
        vertBox.add(backupBox);
        vertBox.add(Box.createVerticalStrut(10));

        // ��������� �������� �� ������
        panel.add(vertBox, BorderLayout.CENTER);

        panel.add(buttonBox, BorderLayout.SOUTH);
        super.add(panel, BorderLayout.CENTER);
    }
    
    private void createExistBorderBalance(){
        JLabel lblNumber;
        JLabel lblDate;
        JLabel lblContent;// ����� ��� ����� �������
        final JTextField txtActnumber;// ����� ����
        final JTextField txtActdate;// ���� ����
        final JTextField txtContent;// ������������ �������
        final JCheckBox chkBackup;// ������� �������� ���� �������
        final BorderbalanceDaoImpl bdi = new BorderbalanceDaoImpl(indentity);// ������ ������� � ������
        
        // �������� ������
        final Borderbalance balance = bdi.getItem(0);
        
        // ���������� ��������� ����
        dialogTitle = "��� �������������";
        // ������ ���������� ����������
        lblNumber = new JLabel("� ����");
        lblDate = new JLabel("���� ����");
        lblContent = new JLabel("����������");
        // ����� ���������������� ������� �����
        lblNumber.setPreferredSize(new Dimension((int) 
                lblContent.getPreferredSize().getWidth(), 
                (int) lblNumber.getPreferredSize().getHeight()));
        lblDate.setPreferredSize(new Dimension((int) 
                lblContent.getPreferredSize().getWidth(), 
                (int) lblDate.getPreferredSize().getHeight()));
        
        // ������ ��������� ���� � ������
        txtActnumber = new JTextField(String.valueOf(balance.getActnumber()),10);
        txtActdate = new JTextField(String.valueOf(balance.getActdate()),10);
        txtContent = new JTextField(balance.getContent(),50);
        chkBackup = new JCheckBox("�������", balance.getBackup());
        txtActnumber.setMaximumSize(txtActnumber.getPreferredSize());
        txtActdate.setMaximumSize(txtActdate.getPreferredSize());
        txtContent.setMaximumSize(txtContent.getPreferredSize());
        
        // ���������� �������� ��� ������� ��
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                short number = Short.valueOf(txtActnumber.getText());
                String date = "'" + txtActdate.getText() + "'";
                String content = "'" + txtContent.getText() + "'";// ������������� �������
                // ��������� ������
                balance.setActnumber(number);
                balance.setActdate(date);
                balance.setContent(content);
                balance.setBackup(chkBackup.isSelected());
                ok = true;
                dialog.setVisible(false);
            }
        });

//            flManager = new FrameLayoutManager("countpanel.properties");

        // ��������� �����, ���� ����� � ������ � ������������ �����
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
        
        // ��������� ������ �� ������ ������
        Box buttonBox = commandButtonBox();

        // ��������� �������� ����������
        Box vertBox = Box.createVerticalBox();
        vertBox.add(actBox);
        vertBox.add(Box.createVerticalStrut(10));
        vertBox.add(nameBox);
        vertBox.add(Box.createVerticalStrut(10));
        vertBox.add(backupBox);
        vertBox.add(Box.createVerticalStrut(10));

        // ��������� �������� �� ������
        panel.add(vertBox, BorderLayout.CENTER);

        panel.add(buttonBox, BorderLayout.SOUTH);
        super.add(panel, BorderLayout.CENTER);
    }
    
    private void createNewItem(JTable itemTable, Object[][] content, String[] columnName, 
            Class[] columnClass, String layout){
        MyTableModel model;// ������ ������ ��� ���������� �������
        int[] colIndex;// ������ ��������������� ��������
        JPanel itemPanel;// ������ ��� ���������� �������
        
        model = new MDIObject.MyTableModelImpl(content, columnName, columnClass);
        
        // �������� ������ ��������������� �������� �������
        colIndex = new int[columnName.length];
        for(int i = 0; i < colIndex.length; i++)
            colIndex[i] = i;
        
        // ������ �������
        itemTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        itemTable.setCellSelectionEnabled(true);
        itemTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        // ��������� ������� �������
        MDIObject.fullTableData(colIndex, model, itemTable);
        TableCell_Renderer.setIntegerTableCellRenderer(itemTable, null);
        MDIObject.addColumnSelectionListener(itemTable);
        // ������ ������ ��������� �������
        JScrollPane bar = new JScrollPane(itemTable);
        
        // ������ ������ ��� ���������� ������� ������
        itemPanel = new JPanel();
        itemPanel.add(bar);// ��������� �� �� ������ ��������� � �������� ������
        
        // ��������� ��� �������� �� ������ ����
        panel.add(itemPanel,layout);
        
    }
    
    private void addButtons_and_Panel(){
        Box buttonBox = commandButtonBox();// ��������� ������
        
        // ��������� ��� �������� �� ������ ����
        panel.add(buttonBox,BorderLayout.SOUTH);
        super.add(panel, BorderLayout.CENTER);
    }
    
    /**
     * ���������� ������ ��������
     * @param id ������������� �������� �� �����������
     * @return TRUE � ������ �������� ���������� ����� ������
     */
    private boolean addNewCount(int id){
        // ���������� ������ ��������
        int[] param = new int[]{indentity,id,1};
        System.out.println("indentity=" + indentity + " id=" + id);
//        return false;
        // ������ ������ ��� ���������� ������
        Runquery rq = new Runquery("COUNTERS");
        return rq.addEntity("IDOBJECT,IDCOUNTER,IDINSTALPLACE", "?,?,?", param);
    }
    
    /**
     * ���������� ������ ����������
     * @param id ������������� ���������� �� �����������
     * @return TRUE � ������ �������� ���������� ����� ������
     */
    private boolean addNewCorrector(int id){
        // ���������� ������ ����������
        int[] param = new int[]{indentity,id};
        // ������ ������ ��� ���������� ������
        Runquery rq = new Runquery("CORRECTOR");
        return rq.addEntity("IDOBJECT,IDCORRECTOR", "?,?", param);
    }
    
    /**
     * ���������� ������ �������
     * @param id ������������� ������� �� �����������
     * @return TRUE � ������ �������� ���������� ����� ������
     */
    private boolean addNewDatchik(int id){
       // ���������� ������ �������
        int[] param = new int[]{indentity,id};
        // ������ ������ ��� ���������� ������
        Runquery rq = new Runquery("DATCHIK");
        return rq.addEntity("IDOBJECT,IDDATCHIK", "?,?", param);
    }
    
    /**
     * ���������� ������ ������������
     * @param id ������������� ������������ �� �����������
     * @return TRUE � ������ �������� ���������� ����� ������
     */
    private boolean addNewEquipment(int idcounter, int idequipment){
        // ���������� ������ ������������
        int[] param = new int[]{indentity,idcounter,idequipment};
        // ������ ������ ��� ���������� ������
        Runquery rq = new Runquery("EQUIPMENT");
        return rq.addEntity("IDOBJECT,IDCOUNTER,IDEQUIPMENT", "?,?,?", param);
    }
    
    /**
     * ���������� ������ ������������
     * @param id ������������� ������������ �� �����������
     * @return TRUE � ������ �������� ���������� ����� ������
     */
    private boolean addNewHilose(int id){
        // ���������� ������ ������������
        int[] param = new int[]{indentity,id};
        // ������ ������ ��� ���������� ������
        Runquery rq = new Runquery("HIPRESSLOSE");
        return rq.addEntity("IDOBJECT,IDGAZHIPRESS", "?,?", param);
    }
    
    /**
     * ���������� ������ ������������
     * @param id ������������� ������������ �� �����������
     * @return TRUE � ������ �������� ���������� ����� ������
     */
    private boolean addNewMidlose(int id){
        // ���������� ������ ������������
        int[] param = new int[]{indentity,id};
        // ������ ������ ��� ���������� ������
        Runquery rq = new Runquery("MIDPRESSLOSE");
        return rq.addEntity("IDOBJECT,IDGAZMIDPRESS", "?,?", param);
    }
    
    /**
     * ���������� ������ ������������
     * @param id ������������� ������������ �� �����������
     * @return TRUE � ������ �������� ���������� ����� ������
     */
    private boolean addNewRlowlose(int id){
        // ���������� ������ ������������
        int[] param = new int[]{indentity,id};
        // ������ ������ ��� ���������� ������
        Runquery rq = new Runquery("RLOWPRESSLOSE");
        return rq.addEntity("IDOBJECT,IDGAZRLOWPRESS", "?,?", param);
    }
    
    /**
     * ���������� ������ ������������
     * @param id ������������� ������������ �� �����������
     * @return TRUE � ������ �������� ���������� ����� ������
     */
    private boolean addNewDlowlose(int id){
        // ���������� ������ ������������
        int[] param = new int[]{indentity,id};
        // ������ ������ ��� ���������� ������
        Runquery rq = new Runquery("DLOWLOSE");
        return rq.addEntity("IDOBJECT,IDGAZDLOWPRESS", "?,?", param);
    }
    
    /**
     * ���������� ������ ������������
     * @param id ������������� ������������ �� �����������
     * @return TRUE � ������ �������� ���������� ����� ������
     */
    private boolean addNewRegulator(int id){
        // ���������� ������ ������������
        int[] param = new int[]{indentity,id};
        // ������ ������ ��� ���������� ������
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
