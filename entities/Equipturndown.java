/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author —ергей
 */
public class Equipturndown extends TableEntity {
//    private int id;
    private String actnumber;
    private Date downdate;
    private String plombnumber;
    private String status;
    private int idequipment;
    private int idcontroler;
    private int idcase;
    private String tablename = "EQUIPTURNDOWN";

    public Equipturndown(int id) {
        super(id);
        setTablename(tablename);
        getEntity();
    }

    public Equipturndown(int id, int index) {
        super(id, index);
        setTablename(tablename);
        getEntity();
    }

    public String getActnumber() {
        return actnumber;
    }

    public void setActnumber(String actnumber) {
        if(updateEntity("ACTNUMBER", actnumber))
            this.actnumber = actnumber;
    }

    public Date getDowndate() {
        return downdate;
    }

    public void setDowndate(Date downdate) {
        if(updateEntity("DOWNDATE", downdate))
            this.downdate = downdate;
    }

    public String getPlombnumber() {
        return plombnumber;
    }

    public void setPlombnumber(String plombnumber) {
        if(updateEntity("PLOMBNUMBER", plombnumber))
            this.plombnumber = plombnumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if(updateEntity("STATUS", status))
            this.status = status;
    }

    public int getIdequipment() {
        return idequipment;
    }

    public void setIdequipment(int idequipment) {
        if(updateEntity("IDEQUIPMENT", idequipment))
            this.idequipment = idequipment;
    }

    public int getIdcontroler() {
        return idcontroler;
    }

    public void setIdcontroler(int idcontroler) {
        if(updateEntity("IDCONTROLER", idcontroler))
            this.idcontroler = idcontroler;
    }

    public int getIdcase() {
        return idcase;
    }

    public void setIdcase(int idcase) {
        if(updateEntity("IDCASE", idcase))
            this.idcase = idcase;
    }

    @Override
    public Object[] toDataArray() {
        Object[] retval = new Object[7];
        retval[0] = getIndex();
        retval[1] = actnumber;
        retval[2] = downdate;
        retval[3] = plombnumber;
        retval[4] = status;
        retval[5] = getControler();
        retval[6] = getCase();
        return retval;
    }

    @Override
    public boolean updateEntity(int col, Object fieldvalue) {
        String fieldname = null;
        switch(col){
            case 1:
                fieldname = "ACTNUMBER";
                break;
            case 2:
                fieldname = "DOWNDATE";
                break;
            case 3:
                fieldname = "PLOMBNUMBER";
                break;
            case 4:
                fieldname = "STATUS";
                break;
            case 5:
                fieldname = "IDCONTROLER";
                break;
            case 6:
                fieldname = "IDCASE";
                break;
        }
        return super.updateEntity(fieldname, fieldvalue);
    }
    

    private void getEntity(){
        String sqlQuery = "SELECT B.IDCONTROLER, B.IDCASE, B.ACTNUMBER, " +""
                + "B.DOWNDATE, B.PLOMBNUMBER, B.STATUS FROM " + 
                tablename + " B " + "WHERE B.ID=" + getId() +";";
        Object[] retval = getFieldValues(sqlQuery);
        idcontroler = (int) retval[0];
        idcase = (int) retval[1];
        actnumber = (String) retval[2];
        downdate = (Date) retval[3];
        plombnumber = (String) retval[4];
        status = (String) retval[5];
        
    }
    
//    private boolean updateValue(String fieldname, Object fieldvalue){
//        String sqlQuery = "UPDATE " + tablename + " B SET B." + fieldname +
//                "=" + fieldvalue + " WHERE B.ID=" + getId() + ";";
//        Runquery rq = new Runquery();
//        return rq.updateFieldValue(sqlQuery);
//    }
    
    private String getControler(){
        // создаЄм контролера по его коду
        Sprcontrolers controler = new Sprcontrolers(idcontroler);
        Object[] toDataArray = controler.toDataArray();// получаем данные
        return controler.getControlerName();// возвращаем его им€
    }
    
    private String getCase(){
        // создаЄм причину
        Sprcase sprcase = new Sprcase(idcase);
        Object[] toDataArray = sprcase.toDataArray();// получаем данные
        return sprcase.getCaseName();// возвращаем причину
    }
}
