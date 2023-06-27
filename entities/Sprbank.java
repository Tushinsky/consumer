/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Сергей
 */
public class Sprbank extends TableEntity {
//    private int id;
    private String bankName;
    private String tablename = "SPRBANK";
    
    public Sprbank(int id) {
        super(id);
        setTablename(tablename);
        getEntity();
    }

    public Sprbank(int id, int index) {
        super(id, index);
        setTablename(tablename);
        getEntity();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        if(updateEntity("BANK_NAME", bankName) == true)
            this.bankName = bankName;
    }

    @Override
    public Object[] toDataArray() {
        Object[] retval = new Object[2];
        
        retval[0] = getIndex();
        retval[1] = bankName;
        return retval;
    }
    
    private void getEntity(){
        String sqlQuery = "SELECT B.BANK_NAME FROM " +
                tablename + " B " + "WHERE B.ID=" + getId() +";";
        Object[] retval = getFieldValues(sqlQuery);
        bankName = (String) retval[0];
        
    }
}
