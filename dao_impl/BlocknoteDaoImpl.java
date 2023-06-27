/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

import entities.Blocknote;
import entities.TableEntity;

/**
 *
 * @author ������
 */
public class BlocknoteDaoImpl extends TableDaoImpl{

    private String tableName = "BLOCKNOTE";

    public BlocknoteDaoImpl(int idObject) {
        super(idObject);
        setTablename(tableName);
        setCriteria("IDOBJECT");
        getEntities();// �������� ������
    }

    @Override
    public Class[] getColumnClass() {
        Class[] retval = new Class[6];
        retval[0] = Integer.class;
        retval[1] = String.class;
        retval[2] = String.class;
        retval[3] = String.class;
        retval[4] = String.class;
        retval[5] = String.class;
        return retval;
    }

    @Override
    public String[] getColumnName() {
        String[] retval = new String[6];
        retval[0] = "�";
        retval[1] = "� ����";
        retval[2] = "����";
        retval[3] = "����������";
        retval[4] = "��������";
        retval[5] = "���� �����";
        return retval;
    }

    @Override
    public Blocknote getItem(int ID) {
        TableEntity entity = super.getItem(ID);
        Blocknote note = new Blocknote(entity.getId(), entity.getIndex());
        return note;
    }
    
}
