/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

import entities.Agreement;
import entities.TableEntity;
import java.util.Date;

/**
 *
 * @author Сергей
 */
public class AgreementDaoImpl extends TableDaoImpl{

    private final String tableName = "AGREEMENT";// наименование таблицы
    
    public AgreementDaoImpl(int idOrganization) {
        super(idOrganization);
        super.setCriteria("IDORGANIZATION");
        super.setTablename(tableName);
        super.getEntities();
    }

    
    @Override
    public Class[] getColumnClass() {
        Class[] retval = new Class[5];
        retval[0] = Integer.class;
        retval[1] = String.class;
        retval[2] = Date.class;
        retval[3] = String.class;
        retval[4] = String.class;
        return retval;
    }

    @Override
    public String[] getColumnName() {
        String[] retval = new String[5];
        retval[0] = "№";
        retval[1] = "№ договора";
        retval[2] = "Дата";
        retval[3] = "Предмет договора";
        retval[4] = "Организация";
        return retval;
    }

    @Override
    public Agreement getItem(int ID) {
        TableEntity entity = super.getItem(ID);
        Agreement agreement = new Agreement(entity.getId(), entity.getIndex());
        return agreement;
    }
    

}
