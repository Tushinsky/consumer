/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Сергей
 */
public class Spryear extends TableEntity {
//    private int id;
    private short nameYear;
    private final String tablename = "SPRYEAR";
    
    public Spryear(int id) {
        super(id);
        super.setTablename(tablename);
        getEntity();
        
    }

    public Spryear(int id, int index) {
        super(id, index);
        super.setTablename(tablename);
        getEntity();
        
    }

    public short getNameYear() {
        return nameYear;
    }

    public void setNameYear(short nameYear) {
        if(updateEntity("NAME_YEAR", nameYear) == true)
            this.nameYear = nameYear;
    }

    @Override
    public Object[] toDataArray() {
        Object[] retval = new Object[2];
        retval[0] = getIndex();
        retval[1] = nameYear;
        return retval;

    }
    
    private void getEntity(){
        String sqlQuery = "SELECT B.NAME_YEAR FROM " + 
                tablename + " B " + "WHERE B.ID=" + getId() +";";
        Object[] retval = getFieldValues(sqlQuery);
        // устанавливаем знаачения полей
        nameYear = (short) retval[0];
        
    }
    
}
