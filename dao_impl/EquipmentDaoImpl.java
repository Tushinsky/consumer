/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

import entities.Equipment;
import entities.TableEntity;
import java.util.Date;

/**
 *
 * @author ������
 */
public class EquipmentDaoImpl extends TableDaoImpl{

    private String tableName = "EQUIPMENT";

    public EquipmentDaoImpl(int idObject) {
        super(idObject);
        setTablename(tableName);
        setCriteria("idobject");
        getEntities();// �������� ������
    }

    @Override
    public Class[] getColumnClass() {
        Class[] retval = new Class[5];
        retval[0]=Integer.class;
        retval[1]=String.class;
        retval[2]=String.class;
        retval[3]=Short.class;
        retval[4]=Boolean.class;
        return retval;
    }

    @Override
    public String[] getColumnName() {
        String[] retval = new String[5];
        retval[0]="�";
        retval[1]="������������";
        retval[2]="���� �����";
        retval[3]="����������";
        retval[4]="�������";
        return retval;
    }

    @Override
    public Equipment getItem(int ID) {
        TableEntity entity = super.getItem(ID);
        Equipment equipment = new Equipment(entity.getId(), entity.getIndex());
        return equipment;
    }
    
}
