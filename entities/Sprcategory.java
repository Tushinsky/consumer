/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import runqueries.Runquery;

/**
 *
 * @author Сергей
 */
public class Sprcategory extends TableEntity {
//    private int id;
    private String categoryName;
    private String tablename = "SPRCATEGORY";
    
    public Sprcategory() {
        super();
        setTablename(tablename);
        getEntity();
    }

    public Sprcategory(int id) {
        super(id);
        setTablename(tablename);
        getEntity();
    }
    
    

    public Sprcategory(int id, int index) {
        super(id, index);
        setTablename(tablename);
        getEntity();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        if(updateEntity("CATEGORY_NAME", "'" + categoryName + "'") == true)
        this.categoryName = categoryName;
    }

    @Override
    public Object[] toDataArray() {
        Object[] retval = new Object[2];
//        getEntity();
        retval[0] = getIndex();
        retval[1] = categoryName;
        return retval;
    }
    
    private void getEntity(){
        String sqlQuery = "SELECT B.CATEGORY_NAME FROM " +
                tablename + " B " + "WHERE B.ID=" + getId() +";";
        Object[] retval = getFieldValues(sqlQuery);
        categoryName = (String) retval[0];
        
    }
}
