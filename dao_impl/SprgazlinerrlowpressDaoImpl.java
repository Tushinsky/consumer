/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

import entities.Sprgazlinerrlowpress;
import entities.TableEntity;
import java.math.BigDecimal;

/**
 *
 * @author Сергей
 */
public class SprgazlinerrlowpressDaoImpl extends TableDaoImpl{

    private String tableName = "SPRGAZLINERRLOWPRESS";

    public SprgazlinerrlowpressDaoImpl() {
        super();
        setTablename(tableName);
        getEntities();// получаем данные
    }

    @Override
    public Class[] getColumnClass() {
        Class[] retval = new Class[3];
        retval[0] = Integer.class;
        retval[1] = Short.class;
        retval[2] = BigDecimal.class;
        return retval;
    }

    @Override
    public String[] getColumnName() {
        String[] retval = new String[3];
        retval[0] = "№";
        retval[1] = "Диаметр, мм";
        retval[2] = "Потери";
        return retval;
    }

    @Override
    public Sprgazlinerrlowpress getItem(int ID) {
        TableEntity entity = super.getItem(ID);
        Sprgazlinerrlowpress rlowpress = new Sprgazlinerrlowpress(entity.getId(), entity.getIndex());
        return rlowpress;
    }
    
        
}
