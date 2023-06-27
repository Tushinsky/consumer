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
public class Sprcorrector extends TableEntity {
//    private int id;
    private String correctorName;
    private BigDecimal class1;
    private int idmaker;
    private final String tablename = "SPRCORRECTOR";
    
    public Sprcorrector(int id) {
        super(id);
        super.setTablename(tablename);
        getEntity();
    }

    public Sprcorrector(int id, int index) {
        super(id, index);
        super.setTablename(tablename);
        getEntity();
    }

    public String getCorrectorName() {
        return correctorName;
    }

    public void setCorrectorName(String correctorName) {
        if(updateEntity("CORRECTOR_NAME", "'" + correctorName + "'") == true)
            this.correctorName = correctorName;
    }

    public BigDecimal getClass1() {
        return class1;
    }

    public void setClass1(BigDecimal class1) {
        if(updateEntity("CLASS", class1) == true)
            this.class1 = class1;
    }

    public int getIdmaker() {
        return idmaker;
    }

    public void setIdmaker(int idmaker) {
        if(updateEntity("IDMAKER", idmaker) == true)
            this.idmaker = idmaker;
    }

    @Override
    public Object[] toDataArray() {
        Object[] retval = new Object[4];
        
        retval[0] = getIndex();
        retval[1] = correctorName;
        retval[2] = getMaker();
        retval[3] = class1;
        return retval;
    }

    @Override
    public String toString() {
        return getMaker() + " " + correctorName;
    }
    
    
    private void getEntity(){
        String sqlQuery = "SELECT B.IDMAKER, B.CORRECTOR_NAME, " +
                "B.CLASS FROM " + tablename + " B " + "WHERE B.ID=" + getId() +";";
        Object[] retval = getFieldValues(sqlQuery);
        idmaker = (int) retval[0];
        correctorName = (String) retval[1];
        class1 = (BigDecimal) retval[2];
        
    }
    
    private String getMaker(){
        Sprmaker maker = new Sprmaker(idmaker);
        // возвращаем производителя
        return maker.getMakerName();
    }

}
