/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

import entities.Sprplumbsinstalplace;
import entities.TableEntity;

/**
 *
 * @author ������
 */
public class SprplumbsinstalplaseDaoImpl extends TableDaoImpl{

    private String tableName = "SPRPLUMBSINSTALPLACE";

    public SprplumbsinstalplaseDaoImpl() {
        super();
        setTablename(tableName);
        getEntities();// �������� ������
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
    public Sprplumbsinstalplace getItem(int ID) {
        TableEntity entity = super.getItem(ID);
        Sprplumbsinstalplace instal = new Sprplumbsinstalplace(entity.getId(), entity.getIndex());
        return instal;
    }
    
        
}
