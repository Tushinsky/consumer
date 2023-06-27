/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

import entities.Gazusing;
import entities.TableEntity;

/**
 *
 * @author Сергей
 */
public class GazusingDaoImpl extends TableDaoImpl{

    private String tableName = "GAZUSING";

    public GazusingDaoImpl(int idObject) {
        super(idObject);
        setTablename(tableName);
        setCriteria("idobject");
        getEntities();// получаем данные
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
        retval[0] = "№";
        retval[1] = "Наименование";
        return retval;
    }

    @Override
    public Gazusing getItem(int ID) {
        TableEntity entity = super.getItem(ID);
        Gazusing using = new Gazusing(entity.getId(), entity.getIndex());
        return using;
    }
    
    
}
