/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author —ергей
 */
public class Bank extends TableEntity {

    // "COUNTER")
    private String counter;
    // "IDBANK"
    private int idbank;
    
    public Bank() {
        super();
        super.setTablename("BANK");
        getEntity();// получаем данные
    }

    public Bank(int id, int index) {
        super(id, index);
        super.setTablename("BANK");
        getEntity();// получаем данные
    }

    public String getCounter() {
        return counter;
    }

    public void setCounter(String counter) {
        if(updateEntity("COUNTER", counter))
            this.counter = counter;
    }

    public int getIdbank() {
        return idbank;
    }

    public void setIdbank(int idbank) {
        if(updateEntity("IDBANK", idbank))
            this.idbank = idbank;
    }

    @Override
    public Object[] toDataArray() {
        Object[] retval;
        
        retval = new Object[3];
        retval[0] = getIndex();
        retval[1] = getBank();
        retval[2] = counter;
        return retval;
    }

    public boolean updateValue(int numcol, Object value) {
        String fieldname = null;
        switch(numcol){
            case 1:
                // наименование банка (код)
                fieldname = "IDBANK";
                break;
            case 2:
                // расчЄтный счЄт
                fieldname = "COUNTER";
                break;
        }
        return super.updateEntity(fieldname, value);
    }
    

    private void getEntity(){
        String sqlQuery = "SELECT B.IDBANK, B.COUNTER " +
                "FROM BANK B WHERE B.ID=" + getId() + ";";
        Object[] retval = getFieldValues(sqlQuery);
        idbank = (int) retval[0];
        counter = (String) retval[1];
        
    }
    
    /**
     * 
     * @return наименование банка из справочника
     */
    private String getBank() {
        Sprbank sprbank = new Sprbank(idbank);
        return sprbank.getBankName();
    }
    
//    private boolean updateEntity(String fieldName, Object fieldValue){
//        String sqlQuery = "UPDATE BANK B SET " + fieldName +
//                "=" + fieldValue + " WHERE B.ID=" + getId() + ";";
//        Runquery rq = new Runquery();
//        return rq.updateFieldValue(sqlQuery);
//    }
}
