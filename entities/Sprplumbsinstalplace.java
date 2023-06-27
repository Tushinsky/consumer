/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Сергей
 */
public class Sprplumbsinstalplace extends TableEntity {
//    private int id;
    private String placeName;
    private String tablename = "SPRPLUMBSINSTALPLACE";
    
    public Sprplumbsinstalplace(int id) {
        super(id);
        setTablename(tablename);
        getEntity();
    }

    public Sprplumbsinstalplace(int id, int index) {
        super(id, index);
        setTablename(tablename);
        getEntity();
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        if(updateEntity("PLACE_NAME", placeName) == true)
            this.placeName = placeName;
    }

    @Override
    public Object[] toDataArray() {
        Object[] retval = new Object[2];
        retval[0] = getIndex();
        retval[1] = placeName;
        return retval;

    }
    
    private void getEntity(){
        String sqlQuery = "SELECT B.PLACE_NAME FROM " + 
                tablename + " B " + "WHERE B.ID=" + getId() +";";
        Object[] retval = getFieldValues(sqlQuery);
        // устанавливаем знаачения полей
        placeName = (String) retval[0];
        
    }
    
}
