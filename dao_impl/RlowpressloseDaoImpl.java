/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

import entities.Rlowpresslose;
import entities.TableEntity;
import java.math.BigDecimal;

/**
 *
 * @author ������
 */
public class RlowpressloseDaoImpl extends TableDaoImpl{

    private String tableName = "RLOWPRESSLOSE";

    public RlowpressloseDaoImpl(int idObject) {
        super(idObject);
        setTablename(tableName);
        setCriteria("idobject");
        getEntities();// �������� ������
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
         retval[0] = "�";
         retval[1] = "�������, ��";
         retval[2] = "������";
         retval[3] = "�����, ��";
         retval[4] = "�� 25 ���";
         retval[5] = "������ ������";
         retval[6] = "�����";
         return retval;
    }

    @Override
    public Rlowpresslose getItem(int ID) {
        TableEntity entity = super.getItem(ID);
        Rlowpresslose rlowpress = new Rlowpresslose(entity.getId(), entity.getIndex());
        return rlowpress;
    }
    
        
}
