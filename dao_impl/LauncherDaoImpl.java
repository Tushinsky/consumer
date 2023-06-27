/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

import abonentgaz.JDBCConnection;
import entities.Launcher;
import entities.TableEntity;
import java.util.Date;

/**
 *
 * @author Сергей
 */
public class LauncherDaoImpl extends TableDaoImpl{

    private String tableName = "LAUNCHER";

    public LauncherDaoImpl(int idObject) {
        super(idObject);
        setTablename(tableName);
        setCriteria("idobject");
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
        retval[1] = "Дата";
        retval[2] = "Содержание";
        return retval;
    }

    @Override
    public Launcher getItem(int ID) {
        TableEntity entity = super.getItem(ID);
        Launcher launcher = new Launcher(entity.getId(), entity.getIndex());
        return launcher;
    }
    
}
