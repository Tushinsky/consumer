/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Сергей
 */
public class Sprgrs extends TableEntity {
//    private int id;
    private String grsName;
    private String tablename = "SPRGRS";
    
    public Sprgrs(int id) {
        super(id);
        setTablename(tablename);
        getEntity();
    }

    public Sprgrs(int id, int index) {
        super(id, index);
        setTablename(tablename);
        getEntity();
    }

    public String getGrsName() {
        return grsName;
    }

    public void setGrsName(String grsName) {
        if(updateEntity("GRS_NAME", "'" + grsName + "'") == true)
            this.grsName = grsName;
    }

    @Override
    public Object[] toDataArray() {
        Object[] retval = new Object[2];
        
        retval[0] = getIndex();
        retval[1] = grsName;
        return retval;

    }
    
    private void getEntity(){
        String sqlQuery = "SELECT B.GRS_NAME FROM " + 
                tablename + " B " + "WHERE B.ID=" + getId() +";";
        Object[] retval = getFieldValues(sqlQuery);
        // устанавливаем знаачения полей
        grsName = (String) retval[0];
        
    }
    
}
