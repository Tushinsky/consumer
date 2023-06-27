/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.math.BigDecimal;

/**
 *
 * @author Сергей
 */
public class Sprgazregulator extends TableEntity {
//    private int id;
    private String name;
    private BigDecimal lose;
    private String tablename = "SPRGAZREGULATOR";
    
    public Sprgazregulator(int id) {
        super(id);
        setTablename(tablename);
        getEntity();
    }

    public Sprgazregulator(int id, int index) {
        super(id, index);
        setTablename(tablename);
        getEntity();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(updateEntity("NAME", "'" + name + "'") == true)
            this.name = name;
    }

    public BigDecimal getLose() {
        return lose;
    }

    public void setLose(BigDecimal lose) {
        if(updateEntity("LOSE", lose) == true)
            this.lose = lose;
    }

    @Override
    public Object[] toDataArray() {
        Object[] retval = new Object[3];
        retval[0] = getIndex();
        retval[1] = name;
        retval[2] = lose;
        return retval;

    }

    @Override
    public boolean updateEntity(String fieldname, Object fieldvalue) {
        return super.updateEntity(fieldname, fieldvalue);
    }
    
    
    private void getEntity(){
        String sqlQuery = "SELECT B.NAME, B.LOSE FROM " + 
                tablename + " B " + "WHERE B.ID=" + getId() +";";
        Object[] retval = getFieldValues(sqlQuery);
        // устанавливаем знаачения полей
        name = (String) retval[0];
        lose = (BigDecimal) retval[1];
        
    }
}
