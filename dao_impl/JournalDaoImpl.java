/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

import entities.Journal;
import entities.TableEntity;

/**
 *
 * @author ������
 */
public class JournalDaoImpl extends TableDaoImpl{

    private String tableName = "JOURNAL";
    
    public JournalDaoImpl(int idOrganization) {
        super(idOrganization);
        setTablename(tableName);
        setCriteria("idorganization");
        getEntities();// �������� ������
    }

    
    @Override
    public Class[] getColumnClass() {
        Class[] retval = new Class[3];
        retval[0] = Integer.class;
        retval[1] = String.class;
        retval[2] = String.class;
        return retval;
    }

    @Override
    public String[] getColumnName() {
        String[] retval = new String[3];
        retval[0] = "�";
        retval[1] = "������";
        retval[2] = "��������";
        return retval;
    }

    @Override
    public Journal getItem(int ID) {
        TableEntity entity = super.getItem(ID);
        Journal journal = new Journal(entity.getId(), entity.getIndex());
        return journal;
    }
    
}
