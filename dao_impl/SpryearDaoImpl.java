/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

import entities.Spryear;
import entities.TableEntity;

/**
 *
 * @author Сергей
 */
public class SpryearDaoImpl extends TableDaoImpl{

    private final String tableName = "SPRYEAR";

    public SpryearDaoImpl() {
        super();
        super.setTablename(tableName);
        super.getEntities();// получаем данные
    }

    @Override
    public Class[] getColumnClass() {
        Class[] retval = new Class[2];
        retval[0] = Integer.class;
        retval[1] = Short.class;
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
    public Spryear getItem(int ID) {
        TableEntity entity = super.getItem(ID); //To change body of generated methods, choose Tools | Templates.
        Spryear year = new Spryear(entity.getId(), entity.getIndex());
        return year;
    }
    
        
}
