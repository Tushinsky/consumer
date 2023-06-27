/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author —ергей
 */
public class Plombsdeleting extends TableEntity {
//    private int id;
    private String plombnumber;
    private String actnumber;
    private int idobject;
    private String tablename = "";
    
    public Plombsdeleting() {
        super();
        setTablename(tablename);
    }

    public Plombsdeleting(int id, int index) {
        super(id, index);
        setTablename(tablename);
    }

    public String getPlombnumber() {
        return plombnumber;
    }

    public void setPlombnumber(String plombnumber) {
        if(updateEntity("plombnumber", "'" + plombnumber + "'") == true)
            this.plombnumber = plombnumber;
    }

    public String getActnumber() {
        return actnumber;
    }

    public void setActnumber(String actnumber) {
        if(updateEntity("actnumber", "'" + actnumber + "'") == true)
            this.actnumber = actnumber;
    }
    
    public Object[] toDataArray() {
        Object[] retval = new Object[3];
        retval[0] = getIndex();
        retval[1] = actnumber;
        retval[2] = plombnumber;
        return retval;
    }

    private void getEntity(){
        String sqlQuery = "SELECT B.ACTNUMBER, B.PLOMBNUMBER FROM " + 
                tablename + " B " + "WHERE B.ID=" + getId() +";";
        Object[] retval = getFieldValues(sqlQuery);// получаем массив данных полей
        actnumber = (String) retval[0];
        plombnumber = (String) retval[1];
        
    }
}
