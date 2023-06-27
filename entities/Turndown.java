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
public class Turndown extends TableEntity {
//    private int id;
    private String actnumber;
    private Date downdate;
    private String plombnumber;
    private String status;
    private int idinstalplace;
    private int idcontroler;
    private int idcase;
    private String tablename = "TURNDOWN";
    
    public Turndown() {
        super();
        setTablename(tablename);
        getEntity();
    }

    public Turndown(int id, int index) {
        super(id, index);
        setTablename(tablename);
        getEntity();
    }

    public String getActnumber() {
        return actnumber;
    }

    public void setActnumber(String actnumber) {
        if(updateEntity("ACTNUMBER", actnumber) == true)
            this.actnumber = actnumber;
    }

    public Date getDowndate() {
        return downdate;
    }

    public void setDowndate(Date downdate) {
        if(updateEntity("DOWNDATE", downdate) == true)
            this.downdate = downdate;
    }

    public String getPlombnumber() {
        return plombnumber;
    }

    public void setPlombnumber(String plombnumber) {
        if(updateEntity("PLOMBNUMBER", plombnumber) == true)
            this.plombnumber = plombnumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if(updateEntity("STATUS", status) == true)
            this.status = status;
    }

    public int getIdinstalplace() {
        return idinstalplace;
    }

    public void setIdinstalplace(int idinstalplace) {
        if(updateEntity("IDINSTALPLACE", idinstalplace) == true)
            this.idinstalplace = idinstalplace;
    }

    public int getIdcontroler() {
        return idcontroler;
    }

    public void setIdcontroler(int idcontroler) {
        if(updateEntity("IDCONTROLER", idcontroler) == true)
            this.idcontroler = idcontroler;
    }

    public int getIdcase() {
        return idcase;
    }

    public void setIdcase(int idcase) {
        if(updateEntity("IDCASE", idcase) == true)
            this.idcase = idcase;
    }

    @Override
    public Object[] toDataArray() {
        Object[] retval = new Object[8];
        
        retval[0] = getIndex();
        retval[1] = actnumber;
        retval[2] = downdate;
        retval[3] = plombnumber;
        retval[4] = getControler();
        retval[5] = getInstalplace();
        retval[6] = status;
        retval[7] = getCase();
        return retval;
    }
    
    private void getEntity(){
        String sqlQuery = "SELECT B.IDCONTROLER, B.IDCASE," + 
                " B.IDINSTALPLACE, B.PLOMBNUMBER, B.DOWNDATE, " +
                "B.STATUS, B.ACTNUMBER FROM " + 
                tablename + " B " + "WHERE B.ID=" + getId() +";";
        Object[] retval = getFieldValues(sqlQuery);
        // устанавливаем знаачени€ полей
        idcontroler = (int) retval[0];
        idcase = (int) retval[1];
        idinstalplace = (int) retval[2];
        plombnumber = (String) retval[3];
        downdate = (Date) retval[4];
        status = (String) retval[5];
        actnumber = (String) retval[6];
        
    }
    
    private String getControler(){
        Sprcontrolers controler = new Sprcontrolers(idcontroler);
        // возвращаем данные
        return controler.getControlerName();
    }
    
    private String getCase(){
        Sprcase sprcase = new Sprcase(idcase);
        // возвращаем данные
        return sprcase.getCaseName();
    }
    
    private String getInstalplace(){
        Sprplumbsinstalplace instalplace = new Sprplumbsinstalplace(idinstalplace);
        return instalplace.getPlaceName();
    }

    @Override
    public boolean updateEntity(int Col, Object value) {
        String fieldName = null;
        switch(Col){
            case 1:
                fieldName = "ACTNUMBER";
                break;
            case 2:
                fieldName = "DOWNDATE";
                break;
            case 3:
                fieldName = "PLOMBNUMBER";
                break;
            case 4:
                fieldName = "IDCONTROLER";
                break;
            case 5:
                fieldName = "IDINSTALPLACE";
                break;
            case 6:
                fieldName = "STATUS";
                break;
            case 7:
                fieldName = "IDCASE";
                break;
        }
        return super.updateEntity(fieldName, value);
    }
}
