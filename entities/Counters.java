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
public class Counters extends TableEntity {
    // "ID")
//    private int id;
    // "COUNTERNUMBER")
    private String counternumber;
    // "PRODUCTDATE")
    private Date productdate;
    // "TESTDATE")
    private Date testdate;
    // "INSTALDATE")
    private Date instaldate;
    // "PERIOD")
    private short period;
    // "ABONPROPERTY")
    private short abonproperty;
    // "IDCOUNTER"
    private int idcounter;
    // "idinstalplace"
    private int idinstalplace;
    private final String tablename = "COUNTERS";
    
    public Counters(int id) {
        super(id);
        getEntity();// получаем данные
    }

    public Counters(int id, int index) {
        super(id, index);
        super.setTablename(tablename);
        getEntity();// получаем данные
    }

    public String getCounternumber() {
        return counternumber;
    }

    public void setCounternumber(String counternumber) {
        if(updateEntity("COUNTERNUMBER", counternumber) == true)
            this.counternumber = counternumber;
    }

    public Date getProductdate() {
        return productdate;
    }

    public void setProductdate(Date productdate) {
        if(updateEntity("PRODUCTDATE", "'" + productdate + "'") == true)
            this.productdate = productdate;
    }

    public Date getTestdate() {
        return testdate;
    }

    public void setTestdate(Date testdate) {
        if(updateEntity("TESTDATE", "'" + testdate + "'") == true)
            this.testdate = testdate;
    }

    public Date getInstaldate() {
        return instaldate;
    }

    public void setInstaldate(Date instaldate) {
        if(updateEntity("INSTALDATE", "'" + instaldate + "'") == true)
            this.instaldate = instaldate;
    }

    public short getPeriod() {
        return period;
    }

    public void setPeriod(short period) {
        if(updateEntity("PERIOD", period) == true)
            this.period = period;
    }

    public boolean getAbonproperty() {
        return (abonproperty != 0);
    }

    public void setAbonproperty(boolean abonproperty) {
        short retval = (short) (abonproperty == true ? 1 : 0);
        if(updateEntity("ABONPROPERTY", retval) == true)
            this.abonproperty = retval;
    }

    public int getIdcounter() {
        return idcounter;
    }

    public void setIdcounter(int idcounter) {
        if(updateEntity("IDCOUNTER", idcounter) == true)
            this.idcounter = idcounter;
    }

    public int getidinstalplace() {
        return idinstalplace;
    }

    public void setidinstalplace(int idinstalplace) {
        if(updateEntity("IDINSTALPLACE", idinstalplace) == true)
            this.idinstalplace = idinstalplace;
    }

    @Override
    public Object[] toDataArray() {
        Object[] retval = new Object[9];
        
        retval[0] = getIndex();
        retval[1] = getCounter();
        retval[2] = counternumber;
        retval[3] = productdate;
        retval[4] = instaldate;
        retval[5] = testdate;
        retval[6] = period;
        retval[7] = idinstalplace;
        retval[8] = (abonproperty != 0);
        return retval;
    }

    @Override
    public boolean updateEntity(int col, Object fieldvalue) {
        String fieldname = null;
        switch(col){
            case 2:
                fieldname="COUNTERNUMBER";
                break;
            case 3:
                fieldname="PRODUCTDATE";
                break;
            case 4:
                fieldname="INSTALDATE";
                break;
            case 5:
                fieldname="TESTDATE";
                break;
            case 6:
                fieldname="PERIOD";
                break;
            case 7:
                fieldname="IDINSTALPLACE";
                break;
            case 8:
                fieldname="ABONPROPERTY";
                break;
        }
        return super.updateEntity(fieldname, fieldvalue);
    }
    
    

    private void getEntity(){
        String sqlQuery = "SELECT B.IDCOUNTER, " +
                "B.COUNTERNUMBER, B.PRODUCTDATE, B.INSTALDATE, " +
                "B.TESTDATE, B.PERIOD, B.IDINSTALPLACE, B.ABONPROPERTY FROM " +
                tablename + " B " + "WHERE B.ID=" + getId() +";";
        Object[] retval = getFieldValues(sqlQuery);
        idcounter = (int) retval[0];
        counternumber = (String) retval[1];
        productdate = (Date) retval[2];
        instaldate = (Date) retval[3];
        testdate = (Date) retval[4];
        period = (short) retval[5];
        idinstalplace = (int) retval[6];
        abonproperty = (short) retval[7];
    }
    
    private String getCounter(){
        Sprcounter counter = new Sprcounter(idcounter);
        return counter.toString();
    }
}
