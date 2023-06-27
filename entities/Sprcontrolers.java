/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Сергей
 */
public class Sprcontrolers extends TableEntity {
//    private int id;
    private String controlerName;
    private String tablename = "SPRCONTROLERS";
    
    public Sprcontrolers(int id) {
        super(id);
        setTablename(tablename);
        getEntity();
    }

    public Sprcontrolers(int id, int index) {
        super(id, index);
        setTablename(tablename);
        getEntity();
    }

    public String getControlerName() {
        return controlerName;
    }

    public void setControlerName(String controlerName) {
        if(updateEntity("CONTROLER_NAME", controlerName) == true)
            this.controlerName = controlerName;
    }

    @Override
    public Object[] toDataArray() {
        Object[] retval = new Object[2];
        
        retval[0] = getIndex();
        retval[1] = controlerName;
        return retval;
    }
    
    private void getEntity(){
        String sqlQuery = "SELECT B.CONTROLER_NAME FROM " +
                tablename + " B " + "WHERE B.ID=" + getId() +";";
        Object[] retval = getFieldValues(sqlQuery);
        controlerName = (String) retval[0];
        
    }
    
}
