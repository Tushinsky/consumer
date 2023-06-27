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
public class Registration extends TableEntity {
//    private int id;
    private String svidetelstvo;
    private Date dateofreg;
    private String egrul;
    private String addres;
    private String director;
    private String uchreditel;
    private String tablename = "REGISTRATION";
    
    public Registration() {
        super();
        setTablename(tablename);
    }

    public Registration(int id, int index) {
        super(id, index);
        setTablename(tablename);
    }

    public String getSvidetelstvo() {
        return svidetelstvo;
    }

    public void setSvidetelstvo(String svidetelstvo) {
        if(updateEntity(tablename, tablename) == true)
            this.svidetelstvo = svidetelstvo;
    }

    public Date getDateofreg() {
        return dateofreg;
    }

    public void setDateofreg(Date dateofreg) {
        if(updateEntity(tablename, tablename) == true)
            this.dateofreg = dateofreg;
    }

    public String getEgrul() {
        return egrul;
    }

    public void setEgrul(String egrul) {
        if(updateEntity(tablename, tablename) == true)
            this.egrul = egrul;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        if(updateEntity(tablename, tablename) == true)
            this.addres = addres;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        if(updateEntity(tablename, tablename) == true)
            this.director = director;
    }

    public String getUchreditel() {
        return uchreditel;
    }

    public void setUchreditel(String uchreditel) {
        if(updateEntity(tablename, tablename) == true)
            this.uchreditel = uchreditel;
    }

    public Object[] toDataArray() {
        Object[] retval = new Object[7];
        getEntity();
        retval[0] = getIndex();
        retval[1] = svidetelstvo;
        retval[2] = dateofreg;
        retval[3] = egrul;
        retval[4] = addres;
        retval[5] = director;
        retval[6] = uchreditel;
        return retval;
    }
    
    private void getEntity(){
        String sqlQuery = "SELECT B.SVIDETELSTVO, B.DATEOFREG, B.EGRUL, " + 
                "B.ADDRES, B.DIRECTOR, B.UCHREDITEL FROM " + tablename + 
                " B WHERE B.ID=" + getId() + ";";
        Object[] retval = getFieldValues(sqlQuery);
        svidetelstvo = (String) retval[0];
        dateofreg = (Date) retval[1];
        egrul = (String) retval[2];
        addres = (String) retval[3];
        director = (String) retval[4];
        uchreditel = (String) retval[5];
        
    }
}
