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
 * @author ������
 */
public class SprcodeinstalDaoImpl extends TableDaoImpl {

    private final String tableName = "SPRCODE_INSTAL";

    public SprcodeinstalDaoImpl() {
        super();
        super.setTablename(tableName);
        super.getEntities();// �������� ������
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
        retval[0] = "�";
        retval[1] = "������";
        retval[2] = "�������";
        retval[3] = "����";
        retval[4] = "������";
        retval[5] = "���";
        retval[6] = "����";
        retval[7] = "����";
        retval[8] = "������";
        retval[9] = "��������";
        retval[10] = "�������";
        retval[11] = "������";
        retval[12] = "�������";
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
