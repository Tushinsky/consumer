/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

/**
 *
 * @author ������
 */
public class SprarmaturaDaoImpl extends TableDaoImpl{

    private String tableName = "SPRARMATURA";

    public SprarmaturaDaoImpl() {
        super();
        setTablename(tableName);
        getEntities();// �������� ������
    }

    @Override
    public Class[] getColumnClass() {
        Class[] retval = new Class[2];
        retval[0] = Integer.class;
        retval[1] = String.class;
        return retval;
    }

    @Override
    public String[] getColumnName() {
        String[] retval = new String[2];
        retval[0] = "�";
        retval[1] = "������������";
        return retval;
    }
    
        
}
