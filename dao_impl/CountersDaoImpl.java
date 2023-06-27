/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

import entities.Counters;
import entities.TableEntity;

/**
 *
 * @author Сергей
 */
public class CountersDaoImpl extends TableDaoImpl{

    private String tableName = "COUNTERS";

    public CountersDaoImpl(int idObject) {
        super(idObject);
        setTablename(tableName);
        setCriteria("idobject");
        getEntities();// получаем данные
    }

    @Override
    public Class[] getColumnClass() {
        Class[] retval = new Class[9];
        retval[0] = Integer.class;
        retval[1] = String.class;
        retval[2] = String.class;
        retval[3] = String.class;
        retval[4] = String.class;
        retval[5] = String.class;
        retval[6] = Short.class;
        retval[7] = Integer.class;
        retval[8] = Boolean.class;
        return retval;
    }

    @Override
    public String[] getColumnName() {
        String[] retval = new String[9];
        retval[0] = "№";
        retval[1] = "Наименование";
        retval[2] = "№ счётчика";
        retval[3] = "Дата выпуска";
        retval[4] = "Дата установки";
        retval[5] = "Дата поверки";
        retval[6] = "Период";
        retval[7] = "Код установки";
        retval[8] = "Собственность абон";
        return retval;
    }

    @Override
    public Counters getItem(int ID) {
        TableEntity entity = super.getItem(ID);
        Counters counter = new Counters(entity.getId(), entity.getIndex());
        return counter;
    }
        
}
