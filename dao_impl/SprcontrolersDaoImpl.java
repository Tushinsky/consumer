/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

import abonentgaz.JDBCConnection;
import entities.Sprcontrolers;
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
public class SprcontrolersDaoImpl extends TableDaoImpl{

    private String tableName = "SPRCONTROLERS";

    public SprcontrolersDaoImpl() {
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
    public Sprcontrolers getItem(int ID) {
        TableEntity entity = super.getItem(ID);
        Sprcontrolers controler = new Sprcontrolers(entity.getId(), entity.getIndex());
        return controler;
    }
    
}
