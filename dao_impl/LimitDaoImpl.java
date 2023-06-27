/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

import entities.Limit;
import entities.TableEntity;

/**
 *
 * @author Сергей
 */
public class LimitDaoImpl extends TableDaoImpl{

    private String tableName = "LIMIT";

    public LimitDaoImpl(int idOrganization) {
        super(idOrganization);
        setTablename(tableName);
        setCriteria("idorganization");
        getEntities();// получаем данные
    }
    
    @Override
    public Class[] getColumnClass() {
        Class[] retval = new Class[3];
        retval[0] = Integer.class;
        retval[1] = Short.class;
        retval[2] = Integer.class;
        return retval;
    }

    @Override
    public String[] getColumnName() {
        String[] retval = new String[3];
        retval[0] = "№";
        retval[1] = "Месяц";
        retval[2] = "Лимит";
        return retval;
    }

    @Override
    public Limit getItem(int ID) {
        TableEntity entity = super.getItem(ID);
        Limit limit = new Limit(entity.getId(), entity.getIndex());
        return limit;
    }
    
    
}
