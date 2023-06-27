/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

import entities.TableEntity;
import entities.Turndown;
import java.util.Date;

/**
 *
 * @author ������
 */
public class TurndownDaoImpl extends TableDaoImpl{
    
    private String tableName = "TURNDOWN";

    public TurndownDaoImpl(int idObject) {
        super(idObject);
        setTablename(tableName);
        setCriteria("idobject");
//        setIdCriteria(idObject);
        getEntities();// �������� ������
    }

    @Override
    public Class[] getColumnClass() {
        Class[] retval = new Class[8];
        retval[0] = Integer.class;
        retval[1] = String.class;
        retval[2] = String.class;
        retval[3] = String.class;
        retval[4] = String.class;
        retval[5] = String.class;
        retval[6] = String.class;
        retval[7] = String.class;
        return retval;
    }

    @Override
    public String[] getColumnName() {
        String[] retval = new String[8];
        retval[0] = "�";
        retval[1] = "� ����";
        retval[2] = "����";
        retval[3] = "� ������";
        retval[4] = "��������";
        retval[5] = "����� ���������";
        retval[6] = "������";
        retval[7] = "�������";
        return retval;
    }

    @Override
    public Turndown getItem(int ID) {
        TableEntity entity = super.getItem(ID);
        Turndown turn = new Turndown(entity.getId(), entity.getIndex());
        return turn;
    }
    
        
}
