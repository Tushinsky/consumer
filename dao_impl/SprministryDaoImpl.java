/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

import entities.Sprministry;
import entities.TableEntity;

/**
 *
 * @author ������
 */
public class SprministryDaoImpl extends TableDaoImpl{

    private final String tableName = "SPRMINISTRY";

    public SprministryDaoImpl() {
        super();
        super.setTablename(tableName);
        super.getEntities();// �������� ������
    }

    @Override
    public Class[] getColumnClass() {
        Class[] retval = new Class[2];
        retval[0] = Integer.class;
        retval[1] = String.class;
        return retval;
    }

    @Override
    public String[] getColumnName() {
        String[] retval = new String[2];
        retval[0] = "�";
        retval[1] = "������������";
        return retval;
    }

    @Override
    public Sprministry getItem(int ID) {
        TableEntity entity = super.getItem(ID);
        Sprministry ministry = new Sprministry(entity.getId(), entity.getIndex());
        return ministry;
    }
    
        
}
