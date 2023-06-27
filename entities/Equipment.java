/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author Сергей
 */
public class Equipment extends TableEntity {
//    private int id;
    private short countvalue;
    private short turnon;
    private Date launchdate;
    private int idequipment;
    private int idcounter;
    private String tablename = "EQUIPMENT";

    public Equipment(int id) {
        super(id);
        setTablename(tablename);
        getEntity();
    }

    public Equipment(int id, int index) {
        super(id, index);
        setTablename(tablename);
        getEntity();
    }

    public short getCountvalue() {
        return countvalue;
    }

    public void setCountvalue(short countvalue) {
        if(updateEntity("COUNTVALUE", countvalue))
            this.countvalue = countvalue;
    }

    public boolean getTurnon() {
        return (turnon == 0 ? false : true);
    }

    public void setTurnon(boolean turnon) {
        short retval = (short) (turnon == true ? 1 : 0);
        if(updateEntity("TURNON", retval))
            this.turnon = retval;
    }

    public Date getLaunchdate() {
        return launchdate;
    }

    public void setLaunchdate(Date launchdate) {
        if(updateEntity("LAUNCHDATE", launchdate))
            this.launchdate = launchdate;
    }

    public int getIdequipment() {
        return idequipment;
    }

    public void setIdequipment(int idequipment) {
        if(updateEntity("IDEQUIPMENT", idequipment))
            this.idequipment = idequipment;
    }

    public int getIdcounter() {
        return idcounter;
    }

    public void setIdcounter(int idcounter) {
        if(updateEntity("IDCOUNTER", idcounter))
            this.idcounter = idcounter;
    }

    @Override
    public Object[] toDataArray() {
        Object[] retval = new Object[5];
        retval[0]=getIndex();
        retval[1]=getEquipment();
        retval[2]=launchdate;
        retval[3]=countvalue;
        retval[4]=(turnon != 0);
        return retval;
    }

    @Override
    public boolean updateEntity(int col, Object fieldvalue) {
        String fieldname = null;
        switch(col){
            case 1:
                fieldname = "IDEQUIPMENT";
                break;
            case 2:
                fieldname = "LAUNCHDATE";
                break;
            case 3:
                fieldname = "COUNTVALUE";
                break;
            case 4:
                fieldname = "TURNON";
                break;
        }
        return super.updateEntity(fieldname, fieldvalue);
    }

    private void getEntity(){
        String sqlQuery = "SELECT B.IDEQUIPMENT, " +
                "B.IDCOUNTER, B.LAUNCHDATE, B.COUNTVALUE, B.TURNON FROM " +
                tablename + " B " + "WHERE B.ID=" + getId() +";";
        Object[] retval = getFieldValues(sqlQuery);
        idequipment = (int) retval[0];
        idcounter = (int) retval[1];
        launchdate = (Date) retval[2];
        countvalue = (short) retval[3];
        turnon = (short) retval[4];
        
    }
    
//    private boolean updateValue(String fieldname, Object fieldvalue){
//        String sqlQuery = "UPDATE " + tablename + " B SET B." + fieldname +
//                "=" + fieldvalue + " WHERE B.ID=" + getId() + ";";
//        Runquery rq = new Runquery();
//        return rq.updateFieldValue(sqlQuery);
//    }
    
    private String getEquipment(){
        Sprequipment equipment = new Sprequipment(idequipment);
        return equipment.toString();
    }
}
