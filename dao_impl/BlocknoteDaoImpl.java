/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

import entities.Blocknote;
import entities.TableEntity;

/**
 *
 * @author Сергей
 */
public class BlocknoteDaoImpl extends TableDaoImpl{

    private String tableName = "BLOCKNOTE";

    public BlocknoteDaoImpl(int idObject) {
        super(idObject);
        setTablename(tableName);
        setCriteria("IDOBJECT");
        getEntities();// получаем данные
    }

    @Override
    public Class[] getColumnClass() {
        Class[] retval = new Class[6];
        retval[0] = Integer.class;
        retval[1] = String.class;
        retval[2] = String.class;
        retval[3] = String.class;
        retval[4] = String.class;
        retval[5] = String.class;
        return retval;
    }

    @Override
    public String[] getColumnName() {
        String[] retval = new String[6];
        retval[0] = "№";
        retval[1] = "№ акта";
        retval[2] = "Дата";
        retval[3] = "Содержание";
        retval[4] = "Контролёр";
        retval[5] = "Дата ввода";
        return retval;
    }

    @Override
    public Blocknote getItem(int ID) {
        TableEntity entity = super.getItem(ID);
        Blocknote note = new Blocknote(entity.getId(), entity.getIndex());
        return note;
    }
    
}
