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
public class Counterstatistics extends TableEntity {
    // "ID")
//    private int id;
    // "COUNTERTYPE")
    private String countertype;
    // "COUNTERNUMBER")
    private String counternumber;
    // "OPERATION")
    private String operation;
    // "OPERDATE")
    private Date operdate;
    
    private String tablename = "COUNTERSTATISTICS";
    
    public Counterstatistics() {
        super();
        setTablename(tablename);
    }

    public Counterstatistics(int id, int index) {
        super(id, index);
        setTablename(tablename);
    }

    public String getCountertype() {
        return countertype;
    }

    public void setCountertype(String countertype) {
        if(updateEntity("COUNTERTYPE", "'" + countertype + "'"))
            this.countertype = countertype;
    }

    public String getCounternumber() {
        return counternumber;
    }

    public void setCounternumber(String counternumber) {
        if(updateEntity("COUNTERNUMBER", "'" + counternumber + "'"))
            this.counternumber = counternumber;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        if(updateEntity("OPERATION", "'" + operation + "'"))
            this.operation = operation;
    }

    public Date getOperdate() {
        return operdate;
    }

    public void setOperdate(Date operdate) {
        if(updateEntity("OPERDATE", "'" + operdate + "'"))
            this.operdate = operdate;
    }

    public Object[] toDataArray() {
        Object[] retval = new Object[5];
        getEntity();// получаем данные
        retval[0] = getIndex();
        retval[1] = countertype;
        retval[2] = counternumber;
        retval[3] = operation;
        retval[4] = operdate;
        return retval;
    }

    private void getEntity(){
        String sqlQuery = "SELECT B.COUNTERTYPE, " +
                "B.COUNTERNUMBER, B.OPERATION, B.OPERDATE FROM " +
                tablename + " B WHERE B.ID=" + getId() +";";
        Object[] retval = getFieldValues(sqlQuery);
        countertype = (String) retval[0];
        counternumber = (String) retval[1];
        operation = (String) retval[2];
        operdate = (Date) retval[3];
        
    }
    
//    private boolean updateEntity(String fieldname, Object fieldvalue){
//        String sqlQuery = "UPDATE " + tablename + " B SET B." + fieldname +
//                "=" + fieldvalue + " WHERE B.ID=" + getId() + ";";
//        Runquery rq = new Runquery();
//        return rq.updateFieldValue(sqlQuery);
//    }
//    
}
