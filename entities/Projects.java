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
public class Projects extends TableEntity {
//    private int id;
    private int idPodryadchik;
    private Short yearnumber;
    private String inspektor;
    private Date desinedate;
    private String uug;
    private String equipment;
    private String tablename = "PROJECTS";
    private String podryadchik;
    
    public Projects(int id) {
        super(id);
        setTablename(tablename);
        getEntity();
    }

    public Projects(int id, int index) {
        super(id, index);
        setTablename(tablename);
        getEntity();
    }

    public int getIDPodryadchik() {
        return idPodryadchik;
    }

    public void setIDPodryadchik(int idPodryadchik) {
        if(updateEntity("idpodryadchik", idPodryadchik) == true)
            this.idPodryadchik = idPodryadchik;
    }

    public Short getYearnumber() {
        return yearnumber;
    }

    public void setYearnumber(Short yearnumber) {
        if(updateEntity("yearnumber", yearnumber) == true)
            this.yearnumber = yearnumber;
    }

    public String getInspektor() {
        return inspektor;
    }

    public void setInspektor(String inspektor) {
        if(updateEntity("inspektor", inspektor) == true)
            this.inspektor = inspektor;
    }

    public Date getDesinedate() {
        return desinedate;
    }

    public void setDesinedate(Date desinedate) {
        if(updateEntity("desinedate", desinedate) == true)
            this.desinedate = desinedate;
    }

    public String getUug() {
        return uug;
    }

    public void setUug(String uug) {
        if(updateEntity("uug", uug) == true)
            this.uug = uug;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        if(updateEntity("equipment", equipment) == true)
            this.equipment = equipment;
    }

    @Override
    public Object[] toDataArray() {
        Object[] retval = new Object[7];
        retval[0] = getIndex();
        retval[1] = podryadchik;
        retval[2] = yearnumber;
        retval[3] = inspektor;
        retval[4] = desinedate;
        retval[5] = uug;
        retval[6] = equipment;
        return retval;
    }

    @Override
    public boolean updateEntity(int col, Object fieldvalue) {
        String fieldname = null;
        switch(col){
            case 1:
                fieldname = "IDPODRYADCHIK";
                break;
            case 2:
                fieldname = "YEARNUMBER";
                break;
            case 3:
                fieldname = "INSPEKTOR";
                break;
            case 4:
                fieldname = "DESINEDATE";
                break;
            case 5:
                fieldname = "UUG";
                break;
            case 6:
                fieldname = "EQUIPMENT";
                break;
        }
        return super.updateEntity(fieldname, fieldvalue);
    }
    
    private void getEntity(){
        String sqlQuery = "SELECT B.IDPODRYADCHIK, B.YEARNUMBER, B.INSPEKTOR, " + 
                "B.DESINEDATE, B.UUG, B.EQUIPMENT FROM " + tablename + 
                " B WHERE B.ID=" + getId() + ";";
        Object[] retval = getFieldValues(sqlQuery);
        idPodryadchik = (int) retval[0];
        yearnumber = (Short) retval[1];
        inspektor = (String) retval[2];
        desinedate = (Date) retval[3];
        uug = (String) retval[4];
        equipment = (String) retval[5];
        podryadchik = getPodryadchik();
    }
    
    private String getPodryadchik(){
        // создаём сущность
        Sprpodryadchik sprpodryadchik = new Sprpodryadchik(idPodryadchik);
        return sprpodryadchik.getPodryadchikName();
        
    }

    /**
     * @return the podrydchik
     */
    public String getPodrydchikName() {
        return podryadchik;
    }
}
