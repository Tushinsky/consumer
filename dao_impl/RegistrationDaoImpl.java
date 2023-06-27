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
public class RegistrationDaoImpl extends TableDaoImpl{

    
    public RegistrationDaoImpl(int idOrganization) {
        super(idOrganization);
        setTablename("REGISTRATION");
        setCriteria("idorganization");
        getEntities();// получаем данные
    }
    
    @Override
    public Class[] getColumnClass() {
        Class[] retval = new Class[7];
        retval[0] = Integer.class;
        retval[0] = String.class;
        retval[0] = Date.class;
        retval[0] = String.class;
        retval[0] = String.class;
        retval[0] = String.class;
        retval[0] = String.class;
        return retval;
    }

    @Override
    public String[] getColumnName() {
        String[] retval = new String[7];
        retval[0] = "№";
        retval[0] = "Свидетельство";
        retval[0] = "Дата";
        retval[0] = "КГРЮЛ";
        retval[0] = "Юридический адрес";
        retval[0] = "Директор";
        retval[0] = "Учредитель";
        return retval;
    }
    
        
}
