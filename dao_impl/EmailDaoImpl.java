/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

import entities.Email;
import entities.TableEntity;

/**
 *
 * @author Сергей
 */
public class EmailDaoImpl extends TableDaoImpl{

    private String tableName = "EMAIL";

    public EmailDaoImpl(int idOrganization) {
        super(idOrganization);
        setTablename(tableName);
        setCriteria("idorganization");
        getEntities();
    }
    
    

    @Override
    public Class[] getColumnClass() {
        Class[] retval = new Class[2];
        retval[1] = Integer.class;
        retval[1] = String.class;
        return retval;
    }

    @Override
    public String[] getColumnName() {
        String[] retval = new String[2];
        retval[0] = "№";
        retval[1] = "Адрес электронной почты";
        return retval;
    }

    @Override
    public Email getItem(int ID) {
        TableEntity entity = super.getItem(ID);
        Email mail = new Email(entity.getId(), entity.getIndex());
        return mail;
    }
    
}
