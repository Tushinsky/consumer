/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import runqueries.Runquery;

/**
 *
 * @author —ергей
 */
public class Letter extends TableEntity {
//    private int id;
    private String actnumber;
    private Date dateofletter;
    private String content;
    private String tablename = "LETTER";
    
    public Letter() {
        super();
        setTablename(tablename);
    }

    public Letter(int id, int index) {
        super(id, index);
        setTablename(tablename);
    }

    public String getActnumber() {
        return actnumber;
    }

    public void setActnumber(String actnumber) {
        if(updateEntity("actnumber", "'" + actnumber + "'") == true)
            this.actnumber = actnumber;
    }

    public Date getDateofletter() {
        return dateofletter;
    }

    public void setDateofletter(Date dateofletter) {
        if(updateEntity("dateofletter", "'" + dateofletter + "'") == true)
            this.dateofletter = dateofletter;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        if(updateEntity("content", "'" + content + "'") == true)
            this.content = content;
    }

    @Override
    public Object[] toDataArray() {
        Object[] retval = new Object[4];
        getEntity();
        retval[0] = getIndex();
        retval[1] = actnumber;
        retval[2] = dateofletter;
        retval[3] = content;
        return retval;
    }

    public boolean updateValue(int col, Object fieldvalue) {
        String fieldname = null;
        switch(col){
            case 1:
                fieldname = "ACTNUMBER";
                break;
            case 2:
                fieldname = "DATEOFLETTER";
                break;
            case 3:
                fieldname = "CONTENT";
                break;
        }
        return super.updateEntity(fieldname, fieldvalue);
    }
    
    
    private void getEntity(){
        String sqlQuery = "SELECT B.ACTNUMBER, B.DATEOFLETTER, B.CONTENT FROM " +
                tablename + " B " + "WHERE B.ID=" + getId() +";";
        Object[] retval = getFieldValues(sqlQuery);// получаем массив данных полей
        actnumber = (String) retval[0];
        dateofletter = (Date) retval[1];
        content = (String) retval[2];
    }
    
}
