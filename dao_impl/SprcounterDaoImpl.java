/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

import entities.Sprcounter;
import entities.TableEntity;
import java.math.BigDecimal;

/**
 *
 * @author ������
 */
public class SprcounterDaoImpl extends TableDaoImpl{

    private String tableName = "SPRCOUNTER";

    public SprcounterDaoImpl() {
        super();
        setTablename(tableName);
        getEntities();// �������� ������
    }

    @Override
    public Class[] getColumnClass() {
        Class[] retval = new Class[10];
        retval[0] = Integer.class;
        retval[1] = String.class;
        retval[2] = String.class;
        retval[3] = String.class;
        retval[4] = String.class;
        retval[5] = BigDecimal.class;
        retval[6] = BigDecimal.class;
        retval[7] = BigDecimal.class;
        retval[8] = Short.class;
        retval[9] = Short.class;
        return retval;
    }

    @Override
    public String[] getColumnName() {
        String[] retval = new String[10];
        retval[0] = "�";
        retval[1] = "�������������";
        retval[2] = "������";
        retval[3] = "������������";
        retval[4] = "���";
        retval[5] = "Q min";
        retval[6] = "Q nom";
        retval[7] = "Q max";
        retval[8] = "DN";
        retval[9] = "�����";
        return retval;
    }

    @Override
    public Sprcounter getItem(int ID) {
        TableEntity entity = super.getItem(ID);
        Sprcounter counter = new Sprcounter(entity.getId(), entity.getIndex());
        return counter;
    }
    
        
}
