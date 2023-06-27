/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

import entities.TableEntity;
import entities.Warning;

/**
 *
 * @author Сергей
 */
public class WarningDaoImpl extends TableDaoImpl{

    
    public WarningDaoImpl(int idOrganization) {
        super(idOrganization);
        setTablename("WARNING");
        setCriteria("idorganization");
        setIdCriteria(idOrganization);
        getEntities();// получаем данные
    }
    
    @Override
    public Class[] getColumnClass() {
        Class[] retval = new Class[3];
        retval[0] = Integer.class;
        retval[1] = String.class;
        retval[2] = Boolean.class;
        return retval;
    }

    @Override
    public String[] getColumnName() {
        String[] retval = new String[3];
        retval[0] = "№";
        retval[1] = "Содержание";
        retval[2] = "Выполнено";
        return retval;
    }

    @Override
    public Warning getItem(int ID) {
        TableEntity entity = super.getItem(ID);
        Warning warning = new Warning(entity.getId(), entity.getIndex());
        return warning;
    }
    
        
}
