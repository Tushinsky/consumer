/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Сергей
 */
public class Email extends TableEntity {
//    private int id;
    private String mail;
    private String tablename = "EMAIL";
    
    public Email() {
        super();
        setTablename(tablename);
        getEntity();
    }

    public Email(int id, int index) {
        super(id, index);
        setTablename(tablename);
        getEntity();
    }

    public Email(int id) {
        super(id);
        setTablename(tablename);
        getEntity();
    }
    

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        if(updateEntity("MAIL", "'" + mail + "'"))
            this.mail = mail;
    }

    @Override
    public Object[] toDataArray() {
        Object[] retval = new Object[2];
        
        retval[0] = getIndex();
        retval[1] = mail;
        return retval;
    }

    private void getEntity(){
        String sqlQuery = "SELECT B.MAIL FROM " + tablename + " B " +
                "WHERE B.ID=" + getId() +";";
        Object[] retval = getFieldValues(sqlQuery);
        mail   = (String) retval[0];
        
    }
    
//    private boolean updateEntity(String fieldname, Object fieldvalue){
//        String sqlQuery = "UPDATE " + tablename + " B SET B." + fieldname +
//                "=" + fieldvalue + " WHERE B.ID=" + getId() + ";";
//        Runquery rq = new Runquery();
//        return rq.updateFieldValue(sqlQuery);
//    }
}
