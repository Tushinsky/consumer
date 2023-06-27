/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Сергей
 */
public class Financility extends TableEntity {
//    private int id;
    private int idfinance;
    private String tablename = "FINANCILITY";

    public Financility() {
        super();
        setTablename(tablename);
    }

    public Financility(int id, int index) {
        super(id, index);
        setTablename(tablename);
    }

    public int getIdfinance() {
        return idfinance;
    }

    public void setIdfinance(int idfinance) {
        if(updateEntity("IDFINANCE", idfinance))
            this.idfinance = idfinance;
    }

    public Object[] toDataArray() {
        Object[] retval = new Object[2];
        getEntity();
        retval[0] = getIndex();
        retval[1] = getFinancility();
        return retval;
    }
    
    private void getEntity(){
        String sqlQuery = "SELECT B.IDFINANCE FROM " +
                tablename + " B WHERE B.ID=" + getId() +";";
        Object[] retval = getFieldValues(sqlQuery);
        idfinance = (int) retval[0];
        
    }
    
//    private boolean updateEntity(String fieldname, Object fieldvalue){
//        String sqlQuery = "UPDATE " + tablename + " B SET B." + fieldname +
//                "=" + fieldvalue + " WHERE B.ID=" + getId() + ";";
//        Runquery rq = new Runquery();
//        return rq.updateFieldValue(sqlQuery);
//    }

    private String getFinancility(){
        // создаём справочник
        Sprfinance finance = new Sprfinance(idfinance);
        // получаем данные
        Object[] toDataArray = finance.toDataArray();
        
        // возвращаем фининсирование
        return finance.getFinanceName();
    }
}
