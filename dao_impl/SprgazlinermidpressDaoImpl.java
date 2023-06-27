/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

import entities.Sprgazlinermidpress;
import entities.TableEntity;
import java.math.BigDecimal;

/**
 *
 * @author Сергей
 */
public class SprgazlinermidpressDaoImpl extends TableDaoImpl{

    private String tableName = "SPRGAZLINERMIDPRESS";

    public SprgazlinermidpressDaoImpl() {
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
    public Sprgazlinermidpress getItem(int ID) {
        TableEntity entity = super.getItem(ID);
        Sprgazlinermidpress midpress = new Sprgazlinermidpress(entity.getId(), entity.getIndex());
        return midpress;
    }
    
        
}
