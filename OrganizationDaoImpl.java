/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

import entities.Organization;
import entities.TableEntity;
import java.util.Date;

/**
 *
 * @author ������
 */
public class OrganizationDaoImpl extends TableDaoImpl{

    private final String tableName = "ORGANIZATION";// ��� ������� ��� �������� ����������
    
    public OrganizationDaoImpl(int idCategory) {
        super(idCategory);
        super.setTablename(tableName);
        super.setCriteria("idcategory");
        super.getEntities();
    }
    
    @Override
    public Class[] getColumnClass() {
        Class retval[] = new Class[9];
        retval[0] = Integer.class;
        retval[1] = String.class;
        retval[2] = String.class;
        retval[3] = String.class;
        retval[4] = Date.class;
        retval[5] = String.class;
        retval[6] = String.class;
        retval[7] = String.class;
        retval[8] = Boolean.class;
        return retval;
    }

    @Override
    public String[] getColumnName() {
        String retval[] = new String[9];
        retval[0] = "�";
        retval[1] = "���������";
        retval[2] = "������������";
        retval[3] = "�������";
        retval[4] = "���� ����������";
        retval[5] = "������������";
        retval[6] = "��� ������";
        retval[7] = "�������� ��������";
        retval[8] = "������";
        return retval;
    }

    @Override
    public Organization getItem(int ID) {
        TableEntity entity = super.getItem(ID);
        Organization organization = new Organization(entity.getId(), entity.getIndex());
        return organization;
    }
    
    
}
