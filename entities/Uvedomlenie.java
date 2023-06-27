/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Сергей
 */
public class Uvedomlenie extends TableEntity {
//    private int id;
    private String content;
    private String actnumber;
    private String tablename = "UVEDOMLENIE";
    
    public Uvedomlenie() {
        super();
        setTablename(tablename);
    }

    public Uvedomlenie(int id, int index) {
        super(id, index);
        setTablename(tablename);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        if(updateEntity("CONTENT", "'" + content + "'") == true)
            this.content = content;
    }

    public String getActnumber() {
        return actnumber;
    }

    public void setActnumber(String actnumber) {
        if(updateEntity("ACTNUMBER", "'" + actnumber + "'") == true)
            this.actnumber = actnumber;
    }

    @Override
    public Object[] toDataArray() {
        Object[] retval = new Object[3];
        getEntity();
        retval[0] = getIndex();
        retval[1] = actnumber;
        retval[2] = content;
        return retval;
    }
    
    private void getEntity(){
        String sqlQuery = "SELECT B.ACTNUMBER, B.CONTENT FROM " + 
                tablename + " B " + "WHERE B.ID=" + getId() +";";
        Object[] retval = getFieldValues(sqlQuery);
        // устанавливаем знаачения полей
        actnumber = (String) retval[0];
        content = (String) retval[1];
        
    }
    
}
