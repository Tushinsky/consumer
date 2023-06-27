/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

import entities.Corrector;
import entities.TableEntity;
import java.util.Date;

/**
 *
 * @author ������
 */
public class CorrectorDaoImpl extends TableDaoImpl{

    private final String tableName = "CORRECTOR";

    public CorrectorDaoImpl(int idObject) {
        super(idObject);
        super.setTablename(tableName);
        super.setCriteria("idobject");
        super.getEntities();// �������� ������
    }

    
    @Override
    public Class[] getColumnClass() {
        Class[] retval = new Class[10];
        retval[0] = Integer.class;
        retval[1] = String.class;
        retval[2] = String.class;
        retval[3] = String.class;
        retval[4] = String.class;
        retval[5] = String.class;
        retval[6] = Short.class;
        retval[7] = String.class;
        retval[8] = String.class;
        retval[9] = Boolean.class;
        return retval;
    }

    @Override
    public String[] getColumnName() {
        String[] retval = new String[10];
        retval[0] = "�";
        retval[1] = "������������";
        retval[2] = "�����";
        retval[3] = "���� �������";
        retval[4] = "���� ���������";
        retval[5] = "���� �������";
        retval[6] = "������";
        retval[7] = "�����";
        retval[8] = "�����";
        retval[9] = "������������� ����";
        return retval;
    }

    @Override
    public Corrector getItem(int ID) {
        TableEntity entity = super.getItem(ID);
        Corrector corrector = new Corrector(entity.getId(), entity.getIndex());
        return corrector;
    }
    
}
