/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.math.BigDecimal;

/**
 *
 * @author Сергей
 */
public class Hipresslose extends TableEntity {
//    private int id;
    private BigDecimal hplenght;
    private short to25year;
    private short podrabterritory;
    private BigDecimal result;
    private int idgazhipress;
    private final String tablename = "HIPRESSLOSE";
    private BigDecimal dhilose;
    private short dimeter;

    public Hipresslose(int id) {
        super(id);
        super.setTablename(tablename);
        getEntity();
    }

    public Hipresslose(int id, int index) {
        super(id, index);
        super.setTablename(tablename);
        getEntity();
    }

    public BigDecimal getHplenght() {
        return hplenght;
    }

    public void setHplenght(BigDecimal hplenght) {
        if(updateEntity("hplenght", hplenght) == true)
            this.hplenght = hplenght;
    }

    public boolean getTo25year() {
        return (to25year != 0);
    }

    public void setTo25year(boolean to25year) {
        short retval = (short) (to25year == true ? 1 : 0);
        if(updateEntity("to25year", retval) == true)
            this.to25year = retval;
    }

    public boolean getPodrabterritory() {
        return (podrabterritory != 0);
    }

    public void setPodrabterritory(boolean podrabterritory) {
        short retval = (short) (podrabterritory == true ? 1 : 0);
        if(updateEntity("podrabterritory", retval) == true)
            this.podrabterritory = retval;
    }

    public BigDecimal getResult() {
        return result;
    }

    public void setResult(BigDecimal result) {
        if(updateEntity("result", result) == true)
            this.result = result;
    }

    public int getIdgazhipress() {
        return idgazhipress;
    }

    public void setIdgazhipress(int idgazhipress) {
        if(updateEntity("idgazhipress", idgazhipress) == true)
            this.idgazhipress = idgazhipress;
    }

    @Override
    public Object[] toDataArray() {
        Object[] retval = new Object[7];
        
        retval[0] = getIndex();
        retval[1] = dimeter;
        retval[2] = dhilose;
        retval[3] = hplenght;
        retval[4] = (to25year != 0);
        retval[5] = (podrabterritory != 0);
        retval[6] = result;
        return retval;
    }
    
    @Override
    public boolean updateEntity(int Col, Object fieldvalue){
        String fieldname = null;
        switch(Col){
            case 1:
                fieldname = "IDGAZHIPRESS";
                break;
            case 3:
                fieldname = "HPLENGHT";
                break;
            case 4:
                fieldname = "TO25YEAR";
                break;
            case 5:
                fieldname = "PODRABTERRITORY";
                break;
            case 6:
                fieldname = "RESULT";
                break;
        }
        return super.updateEntity(fieldname, fieldvalue);
    }
    
    private void getEntity(){
        String sqlQuery = "SELECT B.IDGAZHIPRESS, B.HPLENGHT, B.TO25YEAR, " +
                "B.PODRABTERRITORY, B.RESULT FROM " +
                tablename + " B " + "WHERE B.ID=" + getId() +";";
        Object[] retval = getFieldValues(sqlQuery);
        idgazhipress = (int) retval[0];
        hplenght = (BigDecimal) retval[1];
        to25year = (short) retval[2];
        podrabterritory = (short) retval[3];
        result = (BigDecimal) retval[4];
        getGazliner();// получаем данные из справочника газопроводов
    }
    
    private void getGazliner(){
        Sprgazlinerhipress hipresslose = new Sprgazlinerhipress(idgazhipress);
        dimeter = hipresslose.getDimeter();
        dhilose = hipresslose.getLose();
    }
}
