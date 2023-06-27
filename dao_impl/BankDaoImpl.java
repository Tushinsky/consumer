/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

import entities.Bank;
import entities.TableEntity;

/**
 *
 * @author Сергей
 */
public class BankDaoImpl extends TableDaoImpl{

    private String tableName = "BANK";

    public BankDaoImpl(int idOrganization) {
        super(idOrganization);
        setTablename(tableName);
        setCriteria("IDORGANIZATION");
//        setIdCriteria(idOrganization);
        getEntities();
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
        retval[0] = "№";
        retval[1] = "Наименование банка";
        retval[2] = "Расчётный счёт";
        return retval;
    }

    @Override
    public Bank getItem(int ID) {
        TableEntity entity = super.getItem(ID);
        Bank bank = new Bank(entity.getId(), entity.getIndex());
        return bank;
    }
    
    
}
