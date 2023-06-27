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
public class Monthclosing extends TableEntity {
//    private int id;
    private String counternumber;
    private int countershow;
    private short monthnumber;
    private int flow;
    private short isreport;
    private Date reportdate;
    private String username;
    private int idyear;
    private int idobject;
    private final String tablename = "MONTHCLOSING";
    
    public Monthclosing(int id) {
        super(id);
        super.setTablename(tablename);
        getEntity();
    }

    public Monthclosing(int id, int index) {
        super(id, index);
        super.setTablename(tablename);
        getEntity();
    }

    public String getCounternumber() {
        return counternumber;
    }

    public void setCounternumber(String counternumber) {
        if(updateEntity("counternumber", "'" + counternumber + "'") == true)
            this.counternumber = counternumber;
    }

    public int getCountershow() {
        return countershow;
    }

    public void setCountershow(int countershow) {
        if(updateEntity("countershow", countershow) == true)
            this.countershow = countershow;
    }

    public short getMonthnumber() {
        return monthnumber;
    }

    public void setMonthnumber(short monthnumber) {
        if(updateEntity("monthnumber", monthnumber) == true)
            this.monthnumber = monthnumber;
    }

    public int getFlow() {
        return flow;
    }

    public void setFlow(int flow) {
        if(updateEntity("flow", flow) == true)
            this.flow = flow;
    }

    public boolean getIsreport() {
        return (isreport != 0);
    }

    public void setIsreport(boolean isreport) {
        short retval = (short) (isreport == true ? 1 : 0);
        if(updateEntity("isreport", retval) == true)
            this.isreport = retval;
    }

    public Date getReportdate() {
        return reportdate;
    }

    public void setReportdate(Date reportdate) {
        if(updateEntity("reportdate", "'" + reportdate + "'") == true)
            this.reportdate = reportdate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if(updateEntity("username", "'" + username + "'") == true)
            this.username = username;
    }

    @Override
    public Object[] toDataArray() {
        Object[] retval = new Object[10];
        
        retval[0] = getIndex();
        retval[1] = getObject();
        retval[2] = getYear();
        retval[3] = monthnumber;
        retval[4] = counternumber;
        retval[5] = countershow;
        retval[6] = flow;
        retval[7] = reportdate;
        retval[8] = (isreport != 0);
        retval[9] = username;
        return retval;
    }

    private void getEntity(){
        String sqlQuery = "SELECT B.IDOBJECT, B.IDYEAR, B.MONTHNUMBER, " +""
                + "B.COUNTERNUMBER, B.COUNTERSHOW, B.FLOW, " +""
                + "B.REPORTDATE, B.ISREPORT, B.USERNAME FROM " +
                tablename + " B " + "WHERE B.ID=" + getId() +";";
        Object[] retval = getFieldValues(sqlQuery);// получаем массив данных полей
        idobject = (int) retval[0];
        idyear = (int) retval[1];
        monthnumber = (short) retval[2];
        counternumber = (String) retval[3];
        countershow = (int) retval[4];
        flow = (int) retval[5];
        reportdate = (Date) retval[6];
        isreport = (short) retval[7];
        username = (String) retval[8];
    }
    
    private short getYear(){
        Spryear year = new Spryear(idyear);
        return year.getNameYear();
    }
    
    private String getObject(){
        OrgObjects object = new OrgObjects(idobject);
        return getCity(object.getIdcity()) + ", " + getStreet(object.getIdstreet()) +
                ", " + object.getAddres();
    }
    
    private String getCity(int idCity){
        Sprcity city = new Sprcity(idCity);
        return city.getCityName();
    }
    
    private String getStreet(int idStreet){
        Sprstreet street = new Sprstreet(idStreet);
        return street.getStreetName();
    }

    public int getIdobject() {
        return idobject;
    }
}
