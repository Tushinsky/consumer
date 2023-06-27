/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Сергей
 */
public class Sprfinance extends TableEntity {
//    private int id;
    private String financeName;
    private String tablename = "SPRFINANCE";
    
    public Sprfinance(int id) {
        super(id);
        setTablename(tablename);
    }

    public Sprfinance(int id, int index) {
        super(id, index);
        setTablename(tablename);
    }

    public String getFinanceName() {
        return financeName;
    }

    public void setFinanceName(String financeName) {
        if(updateEntity("FINANCE_NAME", "'" + financeName + "'") == true)
            this.financeName = financeName;
    }

    public Object[] toDataArray() {
        Object[] retval = new Object[2];
        getEntity();
        retval[0] = getIndex();
        retval[1] = financeName;
        return retval;
    }
    
    
    
    private void getEntity(){
        String sqlQuery = "SELECT B.FINANCE_NAME FROM " + 
                tablename + " B " + "WHERE B.ID=" + getId() +";";
        Object[] retval = getFieldValues(sqlQuery);
        // устанавливаем знаачения полей
        financeName = (String) retval[0];
        
    }
    
}
