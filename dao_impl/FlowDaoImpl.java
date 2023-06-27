/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

import entities.Flow;
import entities.TableEntity;

/**
 *
 * @author ������
 */
public class FlowDaoImpl extends TableDaoImpl{

    private String tableName = "FLOW";


    public FlowDaoImpl(int idOrganization) {
        super(idOrganization);
        setTablename(tableName);
        setCriteria("idorganization");
        setIdCriteria(idOrganization);
        getEntities();
    }

    public FlowDaoImpl(String[] criterias, int[] idCriterias) {
        super();
        setCriterias(criterias);
        setIdCriterias(idCriterias);
        setTablename(tableName);
        getEntities();
    }

    @Override
    public Class[] getColumnClass() {
        Class[] retval = new Class[6];
        retval[0] = Integer.class;
        retval[1] = Short.class;
        retval[2] = Short.class;
        retval[3] = Integer.class;
        retval[4] = Short.class;
        retval[5] = String.class;
        return retval;
    }

    @Override
    public String[] getColumnName() {
        String[] retval = new String[6];
        retval[0] = "�";
        retval[1] = "���";
        retval[2] = "�����";
        retval[3] = "������";
        retval[4] = "���";
        retval[5] = "����������";
        return retval;
    }

    @Override
    public Flow getItem(int ID) {
        TableEntity entity = super.getItem(ID);
        Flow flow = new Flow(entity.getId(), entity.getIndex());
        return flow;
    }
    
}
