/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

import entities.Letter;
import entities.TableEntity;
import java.sql.Date;

/**
 *
 * @author Сергей
 */
public class LetterDaoImpl extends TableDaoImpl{

    private String tableName = "LETTER";
    
    public LetterDaoImpl(int idOrganization) {
        super(idOrganization);
        setTablename(tableName);
        setCriteria("idorganization");
        getEntities();// получаем данные
    }

    
    @Override
    public Class[] getColumnClass() {
        Class[] retval = new Class[4];
        retval[0] = Integer.class;
        retval[1] = String.class;
        retval[2] = String.class;
        retval[3] = String.class;
        return retval;
    }

    @Override
    public String[] getColumnName() {
        String[] retval = new String[4];
        retval[0] = "№";
        retval[1] = "№ акта";
        retval[2] = "Дата";
        retval[3] = "Содержание";
        return retval;
    }

    @Override
    public Letter getItem(int ID) {
        TableEntity entity = super.getItem(ID);
        Letter letter = new Letter(entity.getId(), entity.getIndex());
        return letter;
    }
    
}
