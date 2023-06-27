/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

import entities.Equipturndown;
import entities.TableEntity;
import java.util.Date;

/**
 *
 * @author ������
 */
public class EquipturndownDaoImpl extends TableDaoImpl{

    private String tableName = "EQUIPTURNDOWN";

    public EquipturndownDaoImpl(int idEquipment) {
        super(idEquipment);
        setTablename(tableName);
        setCriteria("idequipment");
        getEntities();// �������� ������
    }

    @Override
    public Class[] getColumnClass() {
        Class[] retval = new Class[7];
        retval[0] = Integer.class;
        retval[1] = String.class;
        retval[2] = String.class;
        retval[3] = String.class;
        retval[4] = String.class;
        retval[5] = String.class;
        retval[6] = String.class;
        return retval;
    }

    @Override
    public String[] getColumnName() {
        String[] retval = new String[7];
        retval[0] = "�";
        retval[1] = "� ����";
        retval[2] = "����";
        retval[3] = "� ������";
        retval[4] = "������";
        retval[5] = "��������";
        retval[6] = "�������";
        return retval;
    }

    @Override
    public Equipturndown getItem(int ID) {
        TableEntity entity = super.getItem(ID);
        Equipturndown turndown = new Equipturndown(entity.getId(), entity.getIndex());
        return turndown;
    }
    
}
