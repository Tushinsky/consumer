/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

import entities.Sprequipment;
import entities.TableEntity;
import java.math.BigDecimal;

/**
 *
 * @author ������
 */
public class SprequipmentDaoImpl extends TableDaoImpl{

    private String tableName = "SPREQUIPMENT";

    public SprequipmentDaoImpl() {
        super();
        setTablename(tableName);
        getEntities();// �������� ������
    }

    @Override
    public Class[] getColumnClass() {
        Class[] retval = new Class[6];
        retval[0] = Integer.class;
        retval[1] = String.class;
        retval[2] = String.class;
        retval[3] = String.class;
        retval[4] = BigDecimal.class;
        retval[5] = BigDecimal.class;
        return retval;
    }

    @Override
    public String[] getColumnName() {
        String[] retval = new String[6];
        retval[0] = "�";
        retval[1] = "�������������";
        retval[2] = "������������";
        retval[3] = "���";
        retval[4] = "��������, ���";
        retval[5] = "������";
        return retval;
    }

    @Override
    public Sprequipment getItem(int ID) {
        TableEntity entity = super.getItem(ID);
        Sprequipment equipment = new Sprequipment(entity.getId(), entity.getIndex());
        return equipment;
    }
    
        
}
