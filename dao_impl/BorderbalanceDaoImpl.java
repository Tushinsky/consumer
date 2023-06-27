/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

import entities.Borderbalance;
import entities.TableEntity;
import java.util.Date;

/**
 *
 * @author ������
 */
public class BorderbalanceDaoImpl extends TableDaoImpl{

    private String tableName = "BORDERBALANCE";

    public BorderbalanceDaoImpl(int idObject) {
        super(idObject);
        setTablename(tableName);
        setCriteria("IDOBJECT");
        getEntities();// �������� ������
    }

    @Override
    public Class[] getColumnClass() {
        Class[] retval = new Class[5];
        retval[0] = Integer.class;
        retval[1] = Short.class;
        retval[2] = Date.class;
        retval[3] = String.class;
        retval[4] = Boolean.class;
        return retval;
    }

    @Override
    public String[] getColumnName() {
        String[] retval = new String[5];
        retval[0] = "�";
        retval[1] = "� ����";
        retval[2] = "����";
        retval[3] = "����������";
        retval[4] = "�������";
        return retval;
    }

    @Override
    public Borderbalance getItem(int ID) {
        TableEntity entity = super.getItem(ID);
        Borderbalance balance = new Borderbalance(entity.getId(), entity.getIndex());
        return balance;
    }
    
}
