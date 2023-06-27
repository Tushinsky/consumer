/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Сергей
 */
public class Book extends TableEntity {
    // "ID")
//    private int id;
    // "BOOK")
    private short book;
    // "PAGENUMBER")
    private Short pagenumber;
    private String tablename = "BOOK";

    public Book() {
        super();
        super.setTablename(tablename);
    }

    public Book(int id, int index) {
        super(id, index);
        super.setTablename(tablename);
    }

    public short getBook() {
        return book;
    }

    public void setBook(short book) {
        if(updateEntity("BOOK", book))
            this.book = book;
    }

    public Short getPagenumber() {
        return pagenumber;
    }

    public void setPagenumber(Short pagenumber) {
        if(updateEntity("PAGENUMBER", pagenumber))
            this.pagenumber = pagenumber;
    }

    @Override
    public Object[] toDataArray() {
        Object[] retval = new Object[3];
        getEntity();// получаем данные
        retval[0] = getIndex();
        retval[1] = book;
        retval[2] = pagenumber;
        return retval;
    }

    public boolean updateValue(int col, Object fieldvalue) {
        String fieldname = null;
        switch(col){
            case 1:
                fieldname = "book";
                break;
            case 2:
                fieldname = "pagenumber";
                break;
        }
        return super.updateEntity(fieldname, fieldvalue);
    }
    

    private void getEntity(){
        String sqlQuery = "SELECT B.BOOK, B.PAGENUMBER " +
                "FROM BOOK B WHERE B.ID=" + getId() + ";";
        Object[] retval = getFieldValues(sqlQuery);
        book = (short) retval[0];
        pagenumber = (Short) retval[1];
    }
}
