/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Сергей
 */
public class Sprarmatura extends TableEntity {
//    private int id;
    private String armaturaName;
    private String tablename = "SPRARMATURA";
    
    public Sprarmatura(int id) {
        super(id);
        setTablename(tablename);
        getEntity();
    }

    public Sprarmatura(int id, int index) {
        super(id, index);
        setTablename(tablename);
        getEntity();
    }

    public String getArmaturaName() {
        return armaturaName;
    }

    public void setArmaturaName(String armaturaName) {
        if(updateEntity("ARMATURA_NAME", armaturaName) == true)
        this.armaturaName = armaturaName;
    }

    @Override
    public Object[] toDataArray() {
        Object[] retval = new Object[2];
        retval[0] = getIndex();
        retval[1] = armaturaName;
        return retval;
    }

    public boolean updateEntity(int col, Object fieldvalue) {
        if(col == 1){
            return super.updateEntity("ARMATURA_NAME", fieldvalue);
        } else {
            return false;
        }
    }
    
    private void getEntity(){
        String sqlQuery = "SELECT B.ARMATURA_NAME FROM " +
                tablename + " B " + "WHERE B.ID=" + getId() +";";
        Object[] retval = getFieldValues(sqlQuery);
        armaturaName = (String) retval[0];
        
    }
}
