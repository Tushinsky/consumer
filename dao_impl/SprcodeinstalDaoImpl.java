/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

import entities.SprcodeInstal;
import entities.TableEntity;
import java.math.BigDecimal;

/**
 *
 * @author —ергей
 */
public class SprcodeinstalDaoImpl extends TableDaoImpl {

    private final String tableName = "SPRCODE_INSTAL";

    public SprcodeinstalDaoImpl() {
        super();
        super.setTablename(tableName);
        super.getEntities();// получаем данные
    }
    
    @Override
    public Class[] getColumnClass() {
        Class[] retval = new Class[13];
        retval[0] = Integer.class;
        retval[1] = BigDecimal.class;
        retval[2] = BigDecimal.class;
        retval[3] = BigDecimal.class;
        retval[4] = BigDecimal.class;
        retval[5] = BigDecimal.class;
        retval[6] = BigDecimal.class;
        retval[7] = BigDecimal.class;
        retval[8] = BigDecimal.class;
        retval[9] = BigDecimal.class;
        retval[10] = BigDecimal.class;
        retval[11] = BigDecimal.class;
        retval[12] = BigDecimal.class;
        return retval;
    }

    @Override
    public String[] getColumnName() {
        String[] retval = new String[13];
        retval[0] = "є";
        retval[1] = "€нварь";
        retval[2] = "февраль";
        retval[3] = "март";
        retval[4] = "апрель";
        retval[5] = "май";
        retval[6] = "июнь";
        retval[7] = "июль";
        retval[8] = "август";
        retval[9] = "сент€брь";
        retval[10] = "окт€брь";
        retval[11] = "но€брь";
        retval[12] = "декабрь";
        return retval;
    }

    @Override
    public SprcodeInstal getItem(int ID) {
        TableEntity entity = super.getItem(ID);
        SprcodeInstal codeinstal = new SprcodeInstal(entity.getId(), 
                entity.getIndex());
        return codeinstal;
    }
    
    
}
