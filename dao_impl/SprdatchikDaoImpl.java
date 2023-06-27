/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

import entities.Sprdatchik;
import entities.TableEntity;
import java.math.BigDecimal;

/**
 *
 * @author ������
 */
public class SprdatchikDaoImpl extends TableDaoImpl{

    private String tableName = "SPRDATCHIK";

    public SprdatchikDaoImpl() {
        super();
        setTablename(tableName);
        getEntities();// �������� ������
    }

    @Override
    public Class[] getColumnClass() {
        Class[] retval = new Class[5];
        retval[0] = Integer.class;
        retval[1] = String.class;
        retval[2] = String.class;
        retval[3] = String.class;
        retval[4] = BigDecimal.class;
        return retval;
    }

    @Override
    public String[] getColumnName() {
        String[] retval = new String[5];
        retval[0] = "�";
        retval[1] = "�������������";
        retval[2] = "������������";
        retval[3] = "��������";
        retval[4] = "�����";
        return retval;
    }

    @Override
    public Sprdatchik getItem(int ID) {
        TableEntity entity = super.getItem(ID);
        Sprdatchik datchik = new Sprdatchik(entity.getId(), entity.getIndex());
        return datchik;
    }
    
        
}
