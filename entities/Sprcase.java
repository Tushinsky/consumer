/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Сергей
 */
public class Sprcase extends TableEntity {
//    private int id;
    private String caseName;
    private String tablename = "SPRCASE";
    
    public Sprcase(int id) {
        super(id);
        setTablename(tablename);
        getEntity();
    }

    public Sprcase(int id, int index) {
        super(id, index);
        setTablename(tablename);
        getEntity();
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        if(updateEntity("CASE_NAME", caseName) == true)
            this.caseName = caseName;
    }

    @Override
    public Object[] toDataArray() {
        Object[] retval = new Object[2];
        
        retval[0] = getIndex();
        retval[1] = caseName;
        return retval;
    }
    
    private void getEntity(){
        String sqlQuery = "SELECT B.CASE_NAME FROM " +
                tablename + " B " + "WHERE B.ID=" + getId() +";";
        Object[] retval = getFieldValues(sqlQuery);
        caseName = (String) retval[0];
        
    }
}
