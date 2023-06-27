/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

import entities.Plombs;
import entities.TableEntity;
import java.util.Date;

/**
 *
 * @author Сергей
 */
public class PlombsDaoImpl extends TableDaoImpl{

    private String tableName = "PLOMBS";

    public PlombsDaoImpl(int idObject) {
        super(idObject);
        setTablename(tableName);
        setCriteria("idobject");
        getEntities();// получаем данные
    }
    

    @Override
    public Class[] getColumnClass() {
        Class[] retval = new Class[6];
        retval[0] = Integer.class;
        retval[1] = String.class;
        retval[2] = String.class;
        retval[3] = String.class;
        retval[4] = String.class;
        retval[5] = String.class;
        return retval;
    }

    @Override
    public String[] getColumnName() {
        String[] retval = new String[6];
        retval[0] = "№";
        retval[1] = "№ пломбы";
        retval[2] = "Место установки";
        retval[3] = "Контролёр";
        retval[4] = "Дата";
        retval[5] = "Дополнительно";
        return retval;
    }

    @Override
    public Plombs getItem(int ID) {
        TableEntity entity = super.getItem(ID);
        Plombs plombs = new Plombs(entity.getId(), entity.getIndex());
        return plombs;
    }
    
    
    
}
