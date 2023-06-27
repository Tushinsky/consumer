/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Сергей
 */
public class Sprministry extends TableEntity {
//    private int id;
    private String ministryName;
    private String tablename = "SPRMINISTRY";
    
    public Sprministry() {
        super();
        setTablename(tablename);
        getEntity();
    }

    public Sprministry(int id) {
        super(id);
        setTablename(tablename); 
        getEntity();
    }
    

    public Sprministry(int id, int index) {
        super(id, index);
        setTablename(tablename);
        getEntity();
    }

    public String getMinistryName() {
        return ministryName;
    }

    public void setMinistryName(String ministryName) {
        if(updateEntity("MINISTRY_NAME", "'" + ministryName + "'") == true)
            this.ministryName = ministryName;
    }

    @Override
    public Object[] toDataArray() {
        Object[] retval = new Object[2];
        
        retval[0] = getIndex();
        retval[1] = ministryName;
        return retval;

    }
    
    private void getEntity(){
        String sqlQuery = "SELECT B.MINISTRY_NAME FROM " + 
                tablename + " B " + "WHERE B.ID=" + getId() +";";
        Object[] retval = getFieldValues(sqlQuery);
        // устанавливаем знаачения полей
        ministryName = (String) retval[0];
        
    }
    
}
