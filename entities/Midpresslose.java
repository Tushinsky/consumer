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
public class Midpresslose extends TableEntity {
//    private int id;
    private BigDecimal mplenght;
    private short to25year;
    private short podrabterritory;
    private BigDecimal result;
    private int idgazmidpress;
    private final String tablename = "MIDPRESSLOSE";
    private short dimeter;
    private BigDecimal midlose;
    
    public Midpresslose(int id) {
        super(id);
        super.setTablename(tablename);
        getEntity();
    }

    public Midpresslose(int id, int index) {
        super(id, index);
        super.setTablename(tablename);
        getEntity();
    }

    public BigDecimal getMplenght() {
        return mplenght;
    }

    public void setMplenght(BigDecimal mplenght) {
        if(updateEntity("mplenght", mplenght) == true)
            this.mplenght = mplenght;
    }

    public boolean getTo25year() {
        return (to25year != 0);
    }

    public void setTo25year(boolean to25year) {
        short retval = (short) (to25year == true ? 1 : 0);
        if(updateEntity("to25year", to25year) == true)
            this.to25year = retval;
    }

    public boolean getPodrabterritory() {
        return (podrabterritory != 0);
    }

    public void setPodrabterritory(boolean podrabterritory) {
        short retval = (short)(podrabterritory == true ? 1 : 0);
        if(updateEntity("podrabterritory", podrabterritory) == true)
            this.podrabterritory = retval;
    }

    public BigDecimal getResult() {
        return result;
    }

    public void setResult(BigDecimal result) {
        if(updateEntity("result", result) == true)
            this.result = result;
    }

    public int getIdgazmidpress() {
        return idgazmidpress;
    }

    public void setIdgazmidpress(int idgazmidpress) {
        if(updateEntity("idgazmidpress", idgazmidpress) == true)
            this.idgazmidpress = idgazmidpress;
    }

    @Override
    public Object[] toDataArray() {
        Object[] retval = new Object[7];
        
        retval[0] = getIndex();
        retval[1] = dimeter;
        retval[2] = midlose;
        retval[3] = mplenght;
        retval[4] = (to25year == 0 ? false : true);
        retval[5] = (podrabterritory == 0 ? false : true);
        retval[6] = result;
        return retval;
    }
    
    @Override
    public boolean updateEntity(int Col, Object value) {
        String fieldname = null;
        switch(Col){
            case 1:
                fieldname = "IDGAZMIDPRESS";
                break;
            case 3:
                fieldname = "MPLENGHT";
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
        return super.updateEntity(fieldname, value);
    }
    
    
    private void getEntity(){
        String sqlQuery = "SELECT B.IDGAZMIDPRESS, B.MPLENGHT, " +
                "B.TO25YEAR, B.PODRABTERRITORY, B.RESULT FROM " +
                tablename + " B " + "WHERE B.ID=" + getId() +";";
        Object[] retval = getFieldValues(sqlQuery);// получаем массив данных полей
        idgazmidpress = (int) retval[0];
        mplenght = (BigDecimal) retval[1];
        to25year = (short) retval[2];
        podrabterritory = (short) retval[3];
        result = (BigDecimal) retval[4];
        // получаем данные из справочника газопроводов
        getGazliner();
    }
    
    private void getGazliner(){
        Sprgazlinermidpress midLiner = new Sprgazlinermidpress(idgazmidpress);
        Object[] toDataArray = midLiner.toDataArray();// получаем данные
        dimeter = midLiner.getDimeter();// получаем диаметр
        midlose = midLiner.getLose();// получаем потери
    }
    
//    private boolean updateValue(String fieldname, Object fieldvalue){
//        String sqlQuery = "UPDATE " + tablename + " B SET B." + fieldname +
//                "=" + fieldvalue + " WHERE B.ID=" + getId() + ";";
//        Runquery rq = new Runquery();
//        return rq.updateFieldValue(sqlQuery);
//    }

    
}
