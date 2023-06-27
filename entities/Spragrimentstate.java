/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Сергей
 */
public class Spragrimentstate extends TableEntity {
//    private int id;
    private String agrimentName;
    private String tablename = "SPRAGRIMENTSTATE";
    
    public Spragrimentstate(int id) {
        super(id);
        setTablename(tablename);
        getEntity();
    }

    public Spragrimentstate(int id, int index) {
        super(id, index);
        setTablename(tablename);
        getEntity();
    }

    public String getAgrimentName() {
        return agrimentName;
    }

    public void setAgrimentName(String agrimentName) {
        if(updateEntity("AGRIMENT_NAME", "'" + agrimentName + "'") == true)
            this.agrimentName = agrimentName;
    }

    @Override
    public Object[] toDataArray() {
        Object[] retval = new Object[2];
//        getEntity();
        retval[0]=getIndex();
        retval[1]=agrimentName;
        return retval;
    }
    
    private void getEntity(){
        String sqlQuery = "SELECT B.AGRIMENT_NAME FROM " +
                tablename + " B " + "WHERE B.ID=" + getId() +";";
//        System.out.println("sqlQuery - " + sqlQuery);
        Object[] retval = getFieldValues(sqlQuery);
        agrimentName = (String) retval[0];
    }

}
