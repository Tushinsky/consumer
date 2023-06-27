/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

import entities.Agreementstate;
import entities.TableEntity;

/**
 *
 * @author Сергей
 */
public class AgreementstateDaoImpl extends TableDaoImpl {

    private String tableName = "AGREEMENTSTATE";// наименование таблицы
    
    public AgreementstateDaoImpl(int idOrganization) {
        super(idOrganization);
        super.setTablename(tableName);
        super.setCriteria("IDORGANIZATION");
        super.setIdCriteria(idOrganization);
        super.getEntities();// получаем данные
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
        retval[0] = "№";
        retval[1] = "Состояние";
        return retval;
    }

    @Override
    public Agreementstate getItem(int ID) {
        TableEntity entity = super.getItem(ID);
        Agreementstate state = new Agreementstate(entity.getId(), entity.getIndex());
        return state;
    }
    
    
}
