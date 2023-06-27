/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Сергей
 */
public class Sprstreet extends TableEntity {
//    private int id;
    private String streetName;
    private final String tablename = "SPRSTREET";
    
    public Sprstreet() {
        super();
        super.setTablename(tablename);
        getEntity();
    }

    public Sprstreet(int id) {
        super(id);
        super.setTablename(tablename);
        getEntity();
    }
    

    public Sprstreet(int id, int index) {
        super(id, index);
        super.setTablename(tablename);
        getEntity();
    }

    public String getStreetName() {
        return streetName;
        
    }

    public void setStreetName(String streetName) {
        if(updateEntity("STREET_NAME", "'" + streetName + "'") == true)
            this.streetName = streetName;
    }

    @Override
    public Object[] toDataArray() {
        Object[] retval = new Object[2];
        retval[0] = getIndex();
        retval[1] = streetName;
        return retval;

    }
    
    private void getEntity(){
        String sqlQuery = "SELECT B.STREET_NAME FROM " + 
                tablename + " B " + "WHERE B.ID=" + getId() +";";
        Object[] retval = getFieldValues(sqlQuery);
        // устанавливаем знаачения полей
        streetName = (String) retval[0];
        
    }
    
}
