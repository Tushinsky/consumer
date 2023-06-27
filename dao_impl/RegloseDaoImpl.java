/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

import entities.Reglose;
import entities.TableEntity;
import java.math.BigDecimal;

/**
 *
 * @author Сергей
 */
public class RegloseDaoImpl extends TableDaoImpl{

    private String tableName = "REGLOSE";

    public RegloseDaoImpl(int idObject) {
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
        retval[2] = BigDecimal.class;
        retval[3] = Short.class;
        retval[4] = BigDecimal.class;
        return retval;
    }

    @Override
    public String[] getColumnName() {
        String[] retval = new String[5];
        retval[0] = "№";
        retval[1] = "Тип потерь";
        retval[2] = "Потери";
        retval[3] = "Количество";
        retval[4] = "Итого";
        return retval;
    }

    @Override
    public Reglose getItem(int ID) {
        TableEntity entity = super.getItem(ID);
        Reglose reglose = new Reglose(entity.getId(), entity.getIndex());
        return reglose;
    }
    
        
}
