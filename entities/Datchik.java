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
public class Datchik extends TableEntity {
//    private int id;
    private String datchiknumber;
    private Date poverkadate;
    private Short period;
    private int iddatchik;
    private String datchikname;
    private String diapazon;
    private String pressition;
    
    private final String tablename = "DATCHIK";
    
    public Datchik(int id) {
        super(id);
        super.setTablename(tablename);
        getEntity();
    }

    public Datchik(int id, int index) {
        super(id, index);
        super.setTablename(tablename);
        getEntity();
    }

    public String getDatchiknumber() {
        return datchiknumber;
    }

    public void setDatchiknumber(String datchiknumber) {
        if(updateEntity("DATCHIKNUMBER", datchiknumber))
            this.datchiknumber = datchiknumber;
    }

    public Date getPoverkadate() {
        return poverkadate;
    }

    public void setPoverkadate(Date poverkadate) {
        if(updateEntity("POVERKADATE", poverkadate))
            this.poverkadate = poverkadate;
    }

    public Short getPeriod() {
        return period;
    }

    public void setPeriod(Short period) {
        if(updateEntity("PERIOD", period))
            this.period = period;
    }

    public int getIddatchik() {
        return iddatchik;
    }

    public void setIddatchik(int iddatchik) {
        if(updateEntity("IDDATCHIK", iddatchik))
            this.iddatchik = iddatchik;
    }

    @Override
    public Object[] toDataArray() {
        Object[] retval = new Object[5];
        retval[0] = getIndex();
        retval[1] = datchikname + " " + diapazon;
        retval[2] = datchiknumber;
        retval[3] = poverkadate;
        retval[4] = period;
        return retval;
    }

    @Override
    public boolean updateEntity(int col, Object fieldvalue){
        String fieldname = null;
        switch(col){
            case 1:
                fieldname = "IDDATCHIK";
                break;
            case 2:
                fieldname = "DATCHIKNUMBER";
                break;
            case 3:
                fieldname = "POVERKADATE";
                break;
            case 4:
                fieldname = "PERIOD";
                break;
        }
        return super.updateEntity(fieldname, fieldvalue);
    }
    
    private void getEntity(){
        String sqlQuery = "SELECT B.IDDATCHIK, " +
                "B.DATCHIKNUMBER, B.POVERKADATE, B.PERIOD FROM " +
                tablename + " B " + "WHERE B.ID=" + getId() +";";
        Object[] retval = getFieldValues(sqlQuery);
        iddatchik = (int) retval[0];
        datchiknumber = (String) retval[1];
        poverkadate = (Date) retval[2];
        period = (Short) retval[3];
        getDatchik();
    }
    
    private void getDatchik(){
        Sprdatchik datchik = new Sprdatchik(iddatchik);
        datchikname = datchik.getDatchikName();
        diapazon = datchik.getDiapazon();
        pressition = datchik.getClass1().toPlainString();
    }
}
