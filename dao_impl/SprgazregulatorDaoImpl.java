/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

import entities.Sprgazregulator;
import entities.TableEntity;
import java.math.BigDecimal;

/**
 *
 * @author ������
 */
public class SprgazregulatorDaoImpl extends TableDaoImpl{

    private String tableName = "SPRGAZREGULATOR";

    public SprgazregulatorDaoImpl() {
        super();
        setTablename(tableName);
        getEntities();// �������� ������
    }

    @Override
    public Class[] getColumnClass() {
        Class[] retval = new Class[3];
        retval[0] = Integer.class;
        retval[1] = String.class;
        retval[2] = BigDecimal.class;
        return retval;
    }

    @Override
    public String[] getColumnName() {
        String[] retval = new String[3];
        retval[0] = "�";
        retval[1] = "������������";
        retval[2] = "������";
        return retval;
    }

    @Override
    public Sprgazregulator getItem(int ID) {
        TableEntity entity = super.getItem(ID);
        Sprgazregulator regulator = new Sprgazregulator(entity.getId(), entity.getIndex());
        return regulator;
    }
    
        
}
