/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

import abonentgaz.JDBCConnection;
import entities.Sprcity;
import entities.TableEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Сергей
 */
public class SprcityDaoImpl extends TableDaoImpl{

    private String tableName = "SPRCITY";

    public SprcityDaoImpl() {
        super();
        setTablename(tableName);
        getEntities();// получаем данные
    }

    @Override
    public Class[] getColumnClass() {
        Class[] retval = new Class[3];
        retval[0] = Integer.class;
        retval[1] = String.class;
        retval[2] = String.class;
        return retval;
    }

    @Override
    public String[] getColumnName() {
        String[] retval = new String[3];
        retval[0] = "№";
        retval[1] = "Наименование";
        retval[2] = "ГРС";
        return retval;
    }

    @Override
    public Sprcity getItem(int ID) {
        TableEntity entity = super.getItem(ID);
        Sprcity city = new Sprcity(entity.getId(), entity.getIndex());
        return city;
    }
    
    
}
