/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

import entities.OrgObjects;
import entities.TableEntity;

/**
 *
 * @author Сергей
 */
public class ObjectsDaoImpl extends TableDaoImpl{

    private String tableName = "OBJECTS";

    public ObjectsDaoImpl(int idOrganization) {
        super(idOrganization);
        setCriteria("idorganization");
        setTablename(tableName);
        getEntities();
    }

    @Override
    public Class[] getColumnClass() {
        Class[] retval = new Class[9];
        retval[0] = Integer.class;
        retval[1] = String.class;
        retval[2] = String.class;
        retval[3] = String.class;
        retval[4] = String.class;
        retval[5] = String.class;
        retval[6] = String.class;
        retval[7] = String.class;
        retval[8] = Boolean.class;
        return retval;
    }

    @Override
    public String[] getColumnName() {
        String[] retval = new String[9];
        retval[0] = "№";
        retval[1] = "Наименование";
        retval[2] = "Населённый пункт";
        retval[3] = "Улица";
        retval[4] = "Адрес";
        retval[5] = "Ответственный";
        retval[6] = "Телефон";
        retval[7] = "Дата пуска";
        retval[8] = "Включен";
        return retval;
    }

    @Override
    public OrgObjects getItem(int ID) {
        TableEntity entity = super.getItem(ID);
        OrgObjects objects = new OrgObjects(entity.getId(), entity.getIndex());
        return objects;
    }
    
    
}
