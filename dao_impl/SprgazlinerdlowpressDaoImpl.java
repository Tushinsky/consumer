/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

import entities.Sprgazlinerdlowpress;
import entities.TableEntity;
import java.math.BigDecimal;

/**
 *
 * @author Сергей
 */
public class SprgazlinerdlowpressDaoImpl extends TableDaoImpl{

    private String tableName = "SPRGAZLINERDLOWPRESS";

    public SprgazlinerdlowpressDaoImpl() {
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
    public Sprgazlinerdlowpress getItem(int ID) {
        TableEntity entity = super.getItem(ID);
        Sprgazlinerdlowpress dlowpress = new Sprgazlinerdlowpress(entity.getId(), entity.getIndex());
        return dlowpress;
    }
    
        
}
