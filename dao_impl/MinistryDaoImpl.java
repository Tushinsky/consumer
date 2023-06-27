/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

import entities.Ministry;
import entities.TableEntity;

/**
 *
 * @author Сергей
 */
public class MinistryDaoImpl extends TableDaoImpl{

    private final String tableName = "MINISTRY";// наименование таблицы
    
    public MinistryDaoImpl(int idOrganization) {
        super(idOrganization);
        super.setTablename(tableName);
        super.setCriteria("idorganization");
        super.setIdCriteria(idOrganization);
        super.getEntities();// получаем данные
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
    public Ministry getItem(int ID) {
        TableEntity entity = super.getItem(ID);
        Ministry ministry = new Ministry(entity.getId(), entity.getIndex());
        return ministry;
    }
    
    
}
