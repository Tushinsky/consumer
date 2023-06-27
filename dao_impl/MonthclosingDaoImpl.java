/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

import entities.Monthclosing;
import entities.TableEntity;

/**
 *
 * @author ������
 */
public class MonthclosingDaoImpl extends TableDaoImpl{

    private final String tableName = "MONTHCLOSING";
    
    public MonthclosingDaoImpl(int idOrganization) {
        super(idOrganization);
        super.setTablename(tableName);
        super.setCriteria("idorganization");
        super.getEntities();// �������� ������
    }

    public MonthclosingDaoImpl() {
        super();
        super.setTablename(tableName);
        super.getEntities();// �������� ������
    }

    public MonthclosingDaoImpl(String[] criterias, int[] idCriterias){
        super(criterias, idCriterias);
        super.setTablename(tableName);
        super.getEntities();// �������� ������
    }
    

    @Override
    public Class[] getColumnClass() {
        Class[] retval = new Class[10];
        retval[0] = Integer.class;
        retval[1] = String.class;
        retval[2] = Short.class;
        retval[3] = Short.class;
        retval[4] = String.class;
        retval[5] = Integer.class;
        retval[6] = Integer.class;
        retval[7] = String.class;
        retval[8] = Boolean.class;
        retval[9] = String.class;
        return retval;
    }

    @Override
    public String[] getColumnName() {
        String[] retval = new String[10];
        retval[0] = "�";
        retval[1] = "������������ �������";
        retval[2] = "���";
        retval[3] = "�����";
        retval[4] = "� ��������";
        retval[5] = "���������";
        retval[6] = "������";
        retval[7] = "���� ������";
        retval[8] = "�����";
        retval[9] = "��� ������������";
        return retval;
    }

    @Override
    public Monthclosing getItem(int ID) {
        TableEntity entity = super.getItem(ID);
        Monthclosing closing = new Monthclosing(entity.getId(), entity.getIndex());
        return closing; //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
