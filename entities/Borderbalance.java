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
public class Borderbalance extends TableEntity {
    // "ID")
//    private int id;
    // "ACTNUMBER")
    private short actnumber;
    // "ACTDATE")
    private String actdate;
    // "CONTENT")
    private String content;
    // "BACKUP")
    private short backup;
    
    private final String tablename = "BORDERBALANCE";
    
    public Borderbalance(int id) {
        super(id);
        super.setTablename(tablename);
        getEntity();
    }

    public Borderbalance(int id, int index) {
        super(id, index);
        super.setTablename(tablename);
        getEntity();
    }

    public short getActnumber() {
        return actnumber;
    }

    public void setActnumber(short actnumber) {
        if(updateEntity("ACTNUMBER", actnumber) == true)
            this.actnumber = actnumber;
    }

    public String getActdate() {
        return actdate;
    }

    public void setActdate(String actdate) {
        if(updateEntity("ACTDATE", actdate) == true)
            this.actdate = actdate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        if(updateEntity("CONTENT", content) == true)
            this.content = content;
    }

    public Boolean getBackup() {
        boolean retval = backup == 1;
        return retval;
    }

    public void setBackup(Boolean backup) {
        short retval = (short) (backup == true ? 1 : 0);
        if(updateEntity("BACKUP", retval) == true)
            this.backup = retval;
    }

    @Override
    public Object[] toDataArray() {
        Object[] retval = new Object[5];
        retval[0] = getIndex();
        retval[1] = actnumber;
        retval[2] = actdate;
        retval[3] = content;
        retval[4] = backup;
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
                fieldname = "ACTDATE";
                break;
            case 3:
                fieldname = "CONTENT";
                break;
            case 4:
                fieldname = "BACKUP";
                break;
        }
        return super.updateEntity(fieldname, fieldvalue);
    }

    private void getEntity(){
        String sqlQuery = "SELECT B.ACTNUMBER, " +
                "B.ACTDATE, B.CONTENT, B.BACKUP FROM BORDERBALANCE B " +
                "WHERE B.ID=" + getId() +";";
        Object[] retval = getFieldValues(sqlQuery);
        actnumber = (short) retval[0];
        actdate = retval[1].toString();
        content = (String) retval[2];
        backup = (short) retval[3];
    }
}
