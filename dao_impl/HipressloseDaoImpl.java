/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

import entities.Hipresslose;
import entities.TableEntity;
import java.math.BigDecimal;

/**
 *
 * @author Сергей
 */
public class HipressloseDaoImpl extends TableDaoImpl{

    private final String tableName = "HIPRESSLOSE";

    public HipressloseDaoImpl(int idObject) {
        super(idObject);
        super.setTablename(tableName);
        super.setCriteria("idobject");
        super.getEntities();// получаем данные
    }

    @Override
    public Class[] getColumnClass() {
        Class[] retval = new Class[7];
        retval[0] = Integer.class;
        retval[1] = Short.class;
        retval[2] = BigDecimal.class;
        retval[3] = BigDecimal.class;
        retval[4] = Boolean.class;
        retval[5] = Boolean.class;
        retval[6] = BigDecimal.class;
        return retval;
    }

    @Override
    public String[] getColumnName() {
        String[] retval = new String[7];
        retval[0] = "№";
        retval[1] = "Диаметр, мм";
        retval[2] = "Потери, м куб";
        retval[3] = "Длина, км";
        retval[4] = "До 25 лет";
        retval[5] = "Подраб террит";
        retval[6] = "Итого";
        return retval;
    }

    @Override
    public Hipresslose getItem(int ID) {
        TableEntity entity = super.getItem(ID);
        Hipresslose hipresslose = new Hipresslose(entity.getId(), entity.getIndex());
        return hipresslose;
    }
    
    
}
