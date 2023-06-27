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
public class Plombs extends TableEntity {
//    private int id;
    private String plombnumber;
    private Date instaldate;
    private String addility;
    private int idinstalplace;
    private int idcontroler;
    private final String tablename = "PLOMBS";
    private String instalPlace;
    
    public Plombs(int id) {
        super(id);
        super.setTablename(tablename);
        getEntity();
    }

    public Plombs(int id, int index) {
        super(id, index);
        super.setTablename(tablename);
        getEntity();
    }

    public String getPlombnumber() {
        return plombnumber;
    }

    public void setPlombnumber(String plombnumber) {
        if(updateEntity("plombnumber", plombnumber) == true)
            this.plombnumber = plombnumber;
    }

    public Date getInstaldate() {
        return instaldate;
    }

    public void setInstaldate(Date instaldate) {
        if(updateEntity("instaldate", instaldate) == true)
            this.instaldate = instaldate;
    }

    public String getAddility() {
        return addility;
    }

    public void setAddility(String addility) {
        if(updateEntity("addility", addility) == true)
            this.addility = addility;
    }

    public int getIdinstalplace() {
        return idinstalplace;
    }

    public void setIdinstalplace(int idinstalplace) {
        if(updateEntity("idinstalplace", idinstalplace) == true)
            this.idinstalplace = idinstalplace;
    }

    public int getIdcontroler() {
        return idcontroler;
    }

    public void setIdcontroler(int idcontroler) {
        if(updateEntity("idcontroler", idcontroler) == true)
            this.idcontroler = idcontroler;
    }

    @Override
    public Object[] toDataArray() {
        Object[] retval = new Object[6];
        
        retval[0] = getIndex();
        retval[1] = plombnumber;
        retval[2] = instalPlace;
        retval[3] = getControler();
        retval[4] = instaldate;
        retval[5] = addility;
        return retval;
    }

    @Override
    public boolean updateEntity(int Col, Object value) {
        String fieldname = null;
        switch(Col){
            case 1:
                fieldname = "PLOMBNUMBER";
                break;
            case 2:
                fieldname = "IDINSTALPLACE";
                break;
            case 3:
                fieldname = "IDCONTROLER";
                break;
            case 4:
                fieldname = "INSTALDATE";
                break;
            case 5:
                fieldname = "ADDILITY";
                break;
        }
        return super.updateEntity(fieldname, value);
    }
    
    private void getEntity(){
        String sqlQuery = "SELECT B.IDCONTROLER, B.IDINSTALPLACE, " +
                "B.PLOMBNUMBER, B.INSTALDATE, B.ADDILITY FROM " + 
                tablename + " B " + "WHERE B.ID=" + getId() +";";
        Object[] retval = getFieldValues(sqlQuery);// получаем массив данных полей
        idcontroler = (int) retval[0];
        idinstalplace = (int) retval[1];
        plombnumber = (String) retval[2];
        instaldate = (Date) retval[3];
        addility = (String) retval[4];
        getInstalplace();
    }
    
    private String getControler(){
        Sprcontrolers controler = new Sprcontrolers(idcontroler);
        return controler.getControlerName();
    }
    
    private void getInstalplace(){
        Sprplumbsinstalplace plumbinstal = new Sprplumbsinstalplace(idinstalplace);
        instalPlace = plumbinstal.getPlaceName();
    }
}
