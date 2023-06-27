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
public class Sprdatchik extends TableEntity {
//    private int id;
    private String datchikName;
    private String diapazon;
    private BigDecimal class1;
    private int idmaker;
    private String tablename = "SPRDATCHIK";
    
    public Sprdatchik(int id) {
        super(id);
        setTablename(tablename);
        getEntity();
    }

    public Sprdatchik(int id, int index) {
        super(id, index);
        setTablename(tablename);
        getEntity();
    }

    public String getDatchikName() {
        return datchikName;
    }

    public void setDatchikName(String datchikName) {
        if(updateEntity("DATCHIK_NAME", datchikName) == true)
            this.datchikName = datchikName;
    }

    public String getDiapazon() {
        return diapazon;
    }

    public void setDiapazon(String diapazon) {
        if(updateEntity("DIAPAZON", diapazon) == true)
            this.diapazon = diapazon;
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
        retval[1] = getMaker();
        retval[2] = datchikName;
        retval[3] = diapazon;
        retval[4] = class1;
        return retval;
    }

    @Override
    public String toString() {
        return getMaker() + " " + datchikName + " " + diapazon + "-" + 
                class1.toString();
    }
    
    

    public boolean updateEntity(int col, Object fieldvalue) {
        String fieldname = null;
        switch(col){
            case 1:
                fieldname = "IDMAKER";
                break;
            case 2:
                fieldname = "DATCHIK_NAME";
                break;
            case 3:
                fieldname = "DIAPAZON";
                break;
            case 4:
                fieldname = "CLASS";
                break;
        }
        return super.updateEntity(fieldname, fieldvalue);
    }
    
    private void getEntity(){
        String sqlQuery = "SELECT B.IDMAKER, B.DATCHIK_NAME, B.DIAPAZON, " +
                "B.CLASS FROM " + tablename + " B " + "WHERE B.ID=" + getId() +";";
        Object[] retval = getFieldValues(sqlQuery);
        idmaker = (int) retval[0];
        datchikName = (String) retval[1];
        diapazon = (String) retval[2];
        class1 = (BigDecimal) retval[3];
        
    }

    private String getMaker(){
        Sprmaker maker = new Sprmaker(idmaker);
        // возвращаем производителя
        return maker.getMakerName();
    }


}
