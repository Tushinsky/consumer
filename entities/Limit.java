/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author —ергей
 */
public class Limit extends TableEntity {
//    private int id;
    private short monthnumber;
    private int limit;
    private String tablename = "LIMIT";
    
    public Limit() {
        super();
        setTablename(tablename);
    }

    public Limit(int id, int index) {
        super(id, index);
        setTablename(tablename);
    }

    public short getMonthnumber() {
        return monthnumber;
    }

    public void setMonthnumber(short monthnumber) {
        this.monthnumber = monthnumber;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public Object[] toDataArray() {
        Object[] retval = new Object[3];
        getEntity();
        retval[0] = getIndex();
        retval[1] = monthnumber;
        retval[2] = limit;
        return retval;
    }

    public boolean updateValue(int col, Object fieldvalue) {
        String fieldname = null;
        switch(col){
            case 1:
                fieldname = "MONTHNUMBER";
                break;
            case 2:
                fieldname = "LIMIT";
                break;
        }
        return super.updateEntity(fieldname, fieldvalue);
    }
    
    
    private void getEntity(){
        String sqlQuery = "SELECT B.MONTHNUMBER, B.LIMIT FROM " +
                tablename + " B " + "WHERE B.ID=" + getId() +";";
        Object[] retval = getFieldValues(sqlQuery);// получаем массив данных полей
        monthnumber = (short) retval[0];
        limit = (int) retval[1];
    }
}
