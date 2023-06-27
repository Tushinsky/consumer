/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Сергей
 */
public class Journal extends TableEntity {
    private String journal;
    private String pagenumber;
    private String tablename = "JOURNAL";
    
    public Journal() {
        super();
        setTablename(tablename);
    }

    public Journal(int id, int index) {
        super(id, index);
        setTablename(tablename);
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        if(updateEntity("journal", journal) == true)
            this.journal = journal;
    }

    public String getPagenumber() {
        return pagenumber;
    }

    public void setPagenumber(String pagenumber) {
        if(updateEntity("pagenumber", pagenumber) == true)
            this.pagenumber = pagenumber;
    }
    
    @Override
    public Object[] toDataArray() {
        Object[] retval = new Object[3];
        getEntity();
        retval[0] = getIndex();
        retval[1] = journal;
        retval[2] = pagenumber;
        return retval;
    }

    public boolean updateValue(int col, Object fieldvalue) {
        String fieldname = null;
        switch(col){
            case 1:
                fieldname = "journal";
                break;
            case 2:
                fieldname = "pagenumber";
                break;
        }
        return super.updateEntity(fieldname, fieldvalue);
    }
    
    
    private void getEntity(){
        String sqlQuery = "SELECT B.JOURNAL, B.PAGENUMBER FROM " +
                tablename + " B " + "WHERE B.ID=" + getId() +";";
        Object[] retval = getFieldValues(sqlQuery);
        journal = (String) retval[0];
        pagenumber = (String) retval[1];
        
    }
    
//    private boolean updateValue(String fieldname, Object fieldvalue){
//        String sqlQuery = "UPDATE " + tablename + " B SET B." + fieldname +
//                "=" + fieldvalue + " WHERE B.ID=" + getId() + ";";
//        Runquery rq = new Runquery();
//        return rq.updateFieldValue(sqlQuery);
//    }
}
