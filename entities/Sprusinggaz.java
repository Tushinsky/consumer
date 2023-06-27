/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Сергей
 */
public class Sprusinggaz extends TableEntity {
//    private int id;
    private String usingName;
    private String tablename = "SPRUSINGGAZ";
    
    public Sprusinggaz(int id) {
        super(id);
        setTablename(tablename);
        getEntity();
    }

    public Sprusinggaz(int id, int index) {
        super(id, index);
        setTablename(tablename);
        getEntity();
    }

    public String getUsingName() {
        return usingName;
    }

    public void setUsingName(String usingName) {
        if(updateEntity("USING_NAME", "'" + usingName + "'") == true)
            this.usingName = usingName;
    }

    @Override
    public Object[] toDataArray() {
        Object[] retval = new Object[2];
        retval[0] = getIndex();
        retval[1] = usingName;
        return retval;

    }

    public boolean updateEntity(int col, Object fieldvalue) {
        if(col == 1){
            return super.updateEntity("USING_NAME", fieldvalue);
        } else {
            return false;
        }
    }
    
    private void getEntity(){
        String sqlQuery = "SELECT B.USING_NAME FROM " + 
                tablename + " B " + "WHERE B.ID=" + getId() +";";
        Object[] retval = getFieldValues(sqlQuery);
        // устанавливаем знаачения полей
        usingName = (String) retval[0];
        
    }
    
}
