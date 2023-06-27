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
public class Blocknote extends TableEntity {
    // "ID")
//    private int id;
    // "ACTNUMBER")
    private String actnumber;
    // "ACTDATE")
    private Date actdate;
    // "CONTENT")
    private String content;
    // "INPUTDATE")
    private Date inputdate;
    // "IDCONTROLER"
    private int idcontroler;
    // "IDOBJECT"
    private String tablename = "BLOCKNOTE";

    public Blocknote() {
        super();
        setTablename(tablename);
    }

    public Blocknote(int id, int index) {
        super(id, index);
        setTablename(tablename);
    }

    public String getActnumber() {
        return actnumber;
    }

    public void setActnumber(String actnumber) {
        if(updateEntity("ACTNUMBER", "'" + actnumber + "'"))
            this.actnumber = actnumber;
    }

    public Date getActdate() {
        return actdate;
    }

    public void setActdate(Date actdate) {
        if(updateEntity("ACTDATE", "'" + actdate + "'"))
            this.actdate = actdate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        if(updateEntity("CONTENT", "'" + content + "'"))
            this.content = content;
    }

    public Date getInputdate() {
        return inputdate;
    }

    public void setInputdate(Date inputdate) {
        if(updateEntity("INPUTDATE", "'" + inputdate + "'"))
            this.inputdate = inputdate;
    }

    public int getIdcontroler() {
        return idcontroler;
    }

    public void setIdcontroler(int idcontroler) {
        if(updateEntity("IDCONTROLER", idcontroler))
            this.idcontroler = idcontroler;
    }

    @Override
    public Object[] toDataArray() {
        Object[] retval = new Object[6];
        getEntity();// получаем данные
        retval[0] = getIndex();
        retval[1] = actnumber;
        retval[2] = actdate;
        retval[3] = content;
        retval[4] = getControler();
        retval[5] = inputdate;
        return retval;
    }

    @Override
    public boolean updateEntity(int Col, Object value) {
        String fieldname = null;
        switch(Col){
            case 1:
                fieldname = "ACTNUMBER";
                break;
            case 2:
                fieldname = "ACTDATE";
                break;
            case 3:
                fieldname = "CONTENT";
                break;
            case 4:
                fieldname = "IDCONTROLER";
                break;
            case 5:
                fieldname = "INPUTDATE";
                break;
        }
        return super.updateEntity(fieldname, value);
    }

    /**
     * получает данные из базы данных
     */
    private void getEntity(){
        String sqlQuery = "SELECT B.IDCONTROLER, B.ACTNUMBER, " +
                "B.ACTDATE, B.CONTENT, B.INPUTDATE FROM BLOCKNOTE B " +
                "WHERE B.ID=" + getId() +";";
        Object[] retval = getFieldValues(sqlQuery);
        idcontroler = (int) retval[0];
        actnumber = (String) retval[1];
        actdate = (Date) retval[2];
        content = (String) retval[3];
        inputdate = (Date) retval[4];
    }
    
    private String getControler(){
        Sprcontrolers controler = new Sprcontrolers(idcontroler);
        return controler.getControlerName();
    }
}
