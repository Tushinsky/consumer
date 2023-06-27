/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

import entities.Phone;
import entities.TableEntity;

/**
 *
 * @author Сергей
 */
public class PhoneDaoImpl extends TableDaoImpl{
    
    
    public PhoneDaoImpl(int idOrganization) {
        super(idOrganization);
        setTablename("PHONE");
        setCriteria("idorganization");
        setIdCriteria(idOrganization);
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
        retval[1] = "Телефон";
        return retval;
    }

    @Override
    public Phone getItem(int ID) {
        TableEntity entity = super.getItem(ID);
        Phone phone = new Phone(entity.getId(), entity.getIndex());
        return phone;
    }
    
    
}
