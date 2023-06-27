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
public class Launcher extends TableEntity {
    private Date launchdate;
    private String content;
    private String tablename = "LAUNCHER";

    public Launcher(int id) {
        super(id);
        setTablename(tablename);
        getEntity();
    }

    public Launcher(int id, int index) {
        super(id, index);
        setTablename(tablename);
        getEntity();
    }

    public Date getLaunchdate() {
        return launchdate;
    }

    public void setLaunchdate(Date launchdate) {
        if(updateEntity("launchdate", launchdate) == true)
            this.launchdate = launchdate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        if(updateEntity("content", content) == true)
            this.content = content;
    }

    @Override
    public Object[] toDataArray() {
        Object[] retval = new Object[3];
        
        retval[0] = getIndex();
        retval[1] = launchdate;
        retval[2] = content;
        return retval;
    }

    @Override
    public boolean updateEntity(int col, Object fieldvalue) {
        String fieldname = null;
        switch(col){
            case 1:
                fieldname = "LAUNCHDATE";
                break;
            case 2:
                fieldname = "CONTENT";
                break;
        }
        return super.updateEntity(fieldname, fieldvalue);
    }
    

    private void getEntity(){
        String sqlQuery = "SELECT B.LAUNCHDATE, B.CONTENT FROM " +
                tablename + " B " + "WHERE B.ID=" + getId() +";";
        Object[] retval = getFieldValues(sqlQuery);
        launchdate = (Date) retval[0];
        content = (String) retval[1];
        
    }
    
//    private boolean updateValue(String fieldname, Object fieldvalue){
//        String sqlQuery = "UPDATE " + tablename + " B SET B." + fieldname +
//                "=" + fieldvalue + " WHERE B.ID=" + getId() + ";";
//        Runquery rq = new Runquery();
//        return rq.updateFieldValue(sqlQuery);
//    }
}
