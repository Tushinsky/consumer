/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

/**
 *
 * @author ������
 */
public class ServiceDaoImpl extends TableDaoImpl{

    private String tableName = "SERVICE";

    public ServiceDaoImpl(int idObject) {
        super(idObject);
        setTablename(tableName);
        setCriteria("idobject");
        getEntities();// �������� ������
    }

    @Override
    public Class[] getColumnClass() {
        Class[] retval = new Class[4];
        retval[0] = Integer.class;
        retval[0] = String.class;
        retval[0] = String.class;
        retval[0] = String.class;
        return retval;
    }

    @Override
    public String[] getColumnName() {
        String[] retval = new String[4];
        retval[0] = "�";
        retval[1] = "���������";
        retval[2] = "������������";
        retval[3] = "��������������";
        return retval;
    }
    
        
}
