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
public class Sprgazlinerrlowpress extends TableEntity {
//    private int id;
    private short dimeter;
    private BigDecimal lose;
    private String tablename = "SPRGAZLINERRLOWPRESS";
    
    public Sprgazlinerrlowpress(int id) {
        super(id);
        setTablename(tablename);
        getEntity();
    }

    public Sprgazlinerrlowpress(int id, int index) {
        super(id, index);
        setTablename(tablename);
        getEntity();
    }

    public short getDimeter() {
        return dimeter;
        
    }

    public void setDimeter(short dimeter) {
        if(updateEntity("DIMETER", dimeter) == true)
            this.dimeter = dimeter;
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
        retval[1] = dimeter;
        retval[2] = lose;
        return retval;

    }

    
    public boolean updateEntity(int Col, Object fieldvalue) {
        String fieldname = null;
        switch(Col){
            case 1:
                fieldname = "DIMETER";
                break;
            case 2:
                fieldname = "LOSE";
                break;
        }
        return super.updateEntity(fieldname, fieldvalue);
    }

    @Override
    public String toString() {
        return Short.toString(dimeter); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    private void getEntity(){
        String sqlQuery = "SELECT B.DIMETER, B.LOSE FROM " + 
                tablename + " B " + "WHERE B.ID=" + getId() +";";
        Object[] retval = getFieldValues(sqlQuery);
        // устанавливаем знаачения полей
        dimeter = (short) retval[0];
        lose = (BigDecimal) retval[1];
        
    }
    
    
}
