/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

import entities.Projects;
import entities.TableEntity;

/**
 *
 * @author ������
 */
public class ProjectsDaoImpl extends TableDaoImpl{

    private String tableName = "PROJECTS";

    public ProjectsDaoImpl(int idObject) {
        super(idObject);
        setTablename(tableName);
        setCriteria("idobject");
        getEntities();// �������� ������
    }

    @Override
    public Class[] getColumnClass() {
        Class[] retval = new Class[7];
        retval[0] = Integer.class;
        retval[1] = String.class;
        retval[2] = Short.class;
        retval[3] = String.class;
        retval[4] = String.class;
        retval[5] = String.class;
        retval[6] = String.class;
        return retval;
    }

    @Override
    public String[] getColumnName() {
        String[] retval = new String[7];
        retval[0] = "�";
        retval[1] = "�����������";
        retval[2] = "���";
        retval[3] = "���������";
        retval[4] = "���� ������";
        retval[5] = "���� �����";
        retval[6] = "������������";
        return retval;
    }

    @Override
    public Projects getItem(int ID) {
        TableEntity entity = super.getItem(ID);
        Projects projects = new Projects(entity.getId(), entity.getIndex());
        return projects;
    }
    
        
}
