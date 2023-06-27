/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

import entities.Sprcategory;
import entities.TableEntity;

/**
 *
 * @author Сергей
 */
public class SprcategoryDaoImpl extends TableDaoImpl{

    private String tableName = "SPRCATEGORY";

    public SprcategoryDaoImpl() {
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
    public Sprcategory getItem(int ID) {
        TableEntity entity = super.getItem(ID);
        Sprcategory category = new Sprcategory(entity.getId(), entity.getIndex());
        return category;
    }
    
}
