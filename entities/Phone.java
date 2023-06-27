/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author —ергей
 */
public class Phone extends TableEntity {
//    private int id;
    private String phone;
    private String tablename = "PHONE";
    
    public Phone() {
        super();
        setTablename(tablename);
        getEntity();
    }

    public Phone(int id, int index) {
        super(id, index);
        setTablename(tablename);
        getEntity();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if(updateEntity("phone", "'" + phone + "'") == true)
            this.phone = phone;
    }

    @Override
    public Object[] toDataArray() {
        Object[] retval = new Object[2];
        
        retval[0] = getIndex();
        retval[1] = phone;
        return retval;
    }
    
    private void getEntity(){
        String sqlQuery = "SELECT B.PHONE FROM " + tablename + " B " + 
                "WHERE B.ID=" + getId() +";";
        Object[] retval = getFieldValues(sqlQuery);// получаем массив данных полей
        phone = (String) retval[0];
        
    }
    
}
