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
public class OrghistoryDaoImpl extends TableDaoImpl{

    private String tablename = "ORGHISTORY";

    public OrghistoryDaoImpl(int idOrganization) {
        super(idOrganization);
        setTablename(tablename);
        setCriteria("idorganization");
        getEntities();// получаем данные
    }

    
    @Override
    public Class[] getColumnClass() {
        Class[] retval = new Class[5];
        retval[0] = Integer.class;
        retval[1] = String.class;
        retval[2] = String.class;
        retval[3] = Date.class;
        retval[4] = String.class;
        return retval;
    }

    @Override
    public String[] getColumnName() {
        String[] retval = new String[5];
        retval[0] = "№";
        retval[1] = "Наименовавние";
        retval[2] = "Значение";
        retval[3] = "Дата";
        retval[4] = "Пользователь";
        return retval;
    }
    
}
