/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

import abonentgaz.JDBCConnection;
import entities.Registration;
import entities.Uvedomlenie;
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
public class UvedomlenieDaoImpl extends TableDaoImpl{

    private List<Uvedomlenie> entities;

    public UvedomlenieDaoImpl(int idOrganization) {
        super(idOrganization);
        setTablename("UVEDOMLENIE");
        setCriteria("idorganization");
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
        retval[1] = "№ акта";
        retval[2] = "Содержание";
        return retval;
    }
    
        
}
