/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

import java.util.Date;

/**
 *
 * @author Сергей
 */
public class CounterstatisticsDaoImpl extends TableDaoImpl{

    private String tableName = "COUNTERSTATISTICS";

    public CounterstatisticsDaoImpl(int idObject) {
        super(idObject);
        setTablename(tableName);
        setCriteria("idobject");
        getEntities();// получаем данные
    }

    @Override
    public Class[] getColumnClass() {
        Class[] retval = new Class[5];
        retval[0] = Integer.class;
        retval[1] = String.class;
        retval[2] = String.class;
        retval[3] = String.class;
        retval[4] = Date.class;
        return retval;
    }

    @Override
    public String[] getColumnName() {
        String[] retval = new String[5];
        retval[0] = "№";
        retval[1] = "Тип счётчика";
        retval[2] = "№ счётчика";
        retval[3] = "Операция";
        retval[4] = "Дата проведения";
        return retval;
    }
    
        
}
