/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

import entities.Book;
import entities.TableEntity;

/**
 *
 * @author Сергей
 */
public class BookDaoImpl extends TableDaoImpl{

    private String tableName = "BOOK";

    public BookDaoImpl(int idOrganization) {
        super(idOrganization);
        setTablename(tableName);
        setCriteria("IDORGANIZATION");
        getEntities();
    }
    
    
    @Override
    public Class[] getColumnClass() {
        Class[] retval = new Class[3];
        retval[0] = Integer.class;
        retval[1] = Short.class;
        retval[2] = Short.class;
        return retval;
    }

    @Override
    public String[] getColumnName() {
        String[] retval = new String[3];
        retval[0] = "№";
        retval[1] = "Книга";
        retval[2] = "Страница";
        return retval;
    }

    @Override
    public Book getItem(int ID) {
        TableEntity entity = super.getItem(ID);
        Book book = new Book(entity.getId(), entity.getIndex());
        return book;
    }
    
}
