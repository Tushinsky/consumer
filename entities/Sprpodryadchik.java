/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Сергей
 */
public class Sprpodryadchik extends TableEntity {
//    private int id;
    private String podryadchikName;
    private String tablename = "SPRPODRYADCHIK";
    
    public Sprpodryadchik(int id) {
        super(id);
        setTablename(tablename);
        getEntity();
    }

    public Sprpodryadchik(int id, int index) {
        super(id, index);
        setTablename(tablename);
        getEntity();
    }

    public String getPodryadchikName() {
        return podryadchikName;
    }

    public void setPodryadchikName(String podryadchikName) {
        if(updateEntity("PODRYADCHIK_NAME", "'" + podryadchikName + "'") == true)
            this.podryadchikName = podryadchikName;
    }

    @Override
    public Object[] toDataArray() {
        Object[] retval = new Object[2];
        retval[0] = getIndex();
        retval[1] = podryadchikName;
        return retval;

    }

    public boolean updateEntity(int col, Object fieldvalue) {
        if(col == 1){
        return super.updateEntity("PODRYADCHIK_NAME", fieldvalue);
        } else {
            return false;
        }
    }
    
    private void getEntity(){
        String sqlQuery = "SELECT B.PODRYADCHIK_NAME FROM " + 
                tablename + " B " + "WHERE B.ID=" + getId() +";";
        Object[] retval = getFieldValues(sqlQuery);
        // устанавливаем знаачения полей
        podryadchikName = (String) retval[0];
        
    }
    
}
