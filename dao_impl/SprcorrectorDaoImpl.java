/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

import entities.Sprcorrector;
import entities.TableEntity;
import java.math.BigDecimal;

/**
 *
 * @author ������
 */
public class SprcorrectorDaoImpl extends TableDaoImpl{

    private String tableName = "SPRCORRECTOR";

    public SprcorrectorDaoImpl() {
        super();
        setTablename(tableName);
        getEntities();// �������� ������
    }

    @Override
    public Class[] getColumnClass() {
        Class[] retval = new Class[4];
        retval[0] = Integer.class;
        retval[1] = String.class;
        retval[2] = String.class;
        retval[3] = BigDecimal.class;
        return retval;
    }

    @Override
    public String[] getColumnName() {
        String[] retval = new String[4];
        retval[0] = "�";
        retval[1] = "������������";
        retval[2] = "�������������";
        retval[3] = "����� ��������";
        return retval;
    }

    @Override
    public Sprcorrector getItem(int ID) {
        TableEntity entity = super.getItem(ID);
        Sprcorrector corrector = new Sprcorrector(entity.getId(), entity.getIndex());
        return corrector;
    }
    
}
