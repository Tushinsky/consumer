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
public class Objecthistory extends TableEntity {
//    private int id;
    private String naming;
    private String namevalue;
    private String username;
    private Date datevalue;
    private String tablename = "OBJECTHISTORY";
    
    public Objecthistory() {
        super();
        setTablename(tablename);
    }

    public Objecthistory(int id, int index) {
        super(id, index);
    }

    public String getNaming() {
        return naming;
    }

    public void setNaming(String naming) {
        if(updateEntity("naming", "'" + naming + "'") == true)
            this.naming = naming;
    }

    public String getNamevalue() {
        return namevalue;
    }

    public void setNamevalue(String namevalue) {
        if(updateEntity("namevalue", "'" + namevalue + "'") == true)
            this.namevalue = namevalue;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if(updateEntity("username", "'" + username + "'") == true)
            this.username = username;
    }

    public Date getDatevalue() {
        return datevalue;
    }

    public void setDatevalue(Date datevalue) {
        if(updateEntity("datevalue", "'" + datevalue + "'") == true)
            this.datevalue = datevalue;
    }

    public Object[] toDataArray() {
        Object[] retval = new Object[5];
        getEntity();
        retval[0] = getIndex();
        retval[1] = naming;
        retval[2] = namevalue;
        retval[3] = datevalue;
        retval[4] = username;
        return retval;
    }
    
    private void getEntity(){
        String sqlQuery = "SELECT B.NAMING, B.NAMEVALUE, " +
                "B.DATEVALUE, B.USERNAME FROM " +
                tablename + " B " + "WHERE B.ID=" + getId() +";";
        Object[] retval = getFieldValues(sqlQuery);// получаем массив данных полей
        naming = (String) retval[0];
        namevalue = (String) retval[1];
        datevalue = (Date) retval[2];
        username = (String) retval[3];
        
    }
}
