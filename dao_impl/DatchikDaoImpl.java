/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

import entities.Datchik;
import entities.TableEntity;
import java.util.Date;

/**
 *
 * @author Сергей
 */
public class DatchikDaoImpl extends TableDaoImpl{

    private String tableName = "DATCHIK";

    
    public DatchikDaoImpl(int idObject) {
        super(idObject);
        setTablename(tableName);
        setCriteria("idobject");
        getEntities();// получаем данные
    }

    @Override
    public Class[] getColumnClass() {
        Class[] retval = new Class[5];
        retval[0] = Integer.class;
        retval[1] = String.class;
        retval[2] = String.class;
        retval[3] = String.class;
        retval[4] = Short.class;
        return retval;
    }

    @Override
    public String[] getColumnName() {
        String[] retval = new String[5];
        retval[0] = "№";
        retval[1] = "Наименование";
        retval[2] = "Номер";
        retval[3] = "Дата поверки";
        retval[4] = "Период";
        return retval;
    }

    @Override
    public Datchik getItem(int ID) {
        TableEntity entity = super.getItem(ID);
        Datchik datchik = new Datchik(entity.getId(), entity.getIndex());
        return datchik;
    }
        
}
