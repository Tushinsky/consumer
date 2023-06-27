/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

import entities.Sprgazlinerhipress;
import entities.TableEntity;
import java.math.BigDecimal;

/**
 *
 * @author ������
 */
public class SprgazlinerhipressDaoImpl extends TableDaoImpl{

    private final String tableName = "SPRGAZLINERHIPRESS";

    public SprgazlinerhipressDaoImpl() {
        super();
        super.setTablename(tableName);
        super.getEntities();// �������� ������
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
        retval[0] = "�";
        retval[1] = "�������, ��";
        retval[2] = "������";
        return retval;
    }

    @Override
    public Sprgazlinerhipress getItem(int ID) {
        TableEntity entity = super.getItem(ID);
        Sprgazlinerhipress hipress = new Sprgazlinerhipress(entity.getId(), entity.getIndex());
        return hipress;
    }
    
        
}
