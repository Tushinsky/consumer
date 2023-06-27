/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

import entities.Sprcase;
import entities.TableEntity;

/**
 *
 * @author Сергей
 */
public class SprcaseDaoImpl extends TableDaoImpl{

    private String tableName = "SPRCASE";

    public SprcaseDaoImpl() {
        super();
        setTablename(tableName);
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
    public Sprcase getItem(int ID) {
        TableEntity entity = super.getItem(ID);
        Sprcase sprcase = new Sprcase(entity.getId(), entity.getIndex());
        return sprcase;
    }
    
        
}
