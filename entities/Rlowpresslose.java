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
public class Rlowpresslose extends TableEntity {
//    private int id;
    private BigDecimal rlowlenght;
    private short to25year;
    private short podrabterritory;
    private BigDecimal result;
    private int idgazrlowpress;
    private String tablename = "RLOWPRESSLOSE";
    private short dimeter;
    private BigDecimal rlowlose;
    
    public Rlowpresslose(int id) {
        super(id);
        setTablename(tablename);
        getEntity();
    }

    public Rlowpresslose(int id, int index) {
        super(id, index);
        setTablename(tablename);
        getEntity();
    }

    public BigDecimal getRlowlenght() {
        return rlowlenght;
    }

    public void setRlowlenght(BigDecimal rlowlenght) {
        if(updateEntity("rlowlenght", rlowlenght) == true)
            this.rlowlenght = rlowlenght;
    }

    public boolean getTo25year() {
        return (to25year == 0 ? false : true);
    }

    public void setTo25year(boolean to25year) {
        short retval = (short) (to25year == true ? 1 : 0);
        if(updateEntity("to25year", retval) == true)
            this.to25year = retval;
    }

    public boolean getPodrabterritory() {
        return (podrabterritory == 0 ? false : true);
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

    public int getIdgazrlowpress() {
        return idgazrlowpress;
    }

    public void setIdgazrlowpress(int idgazrlowpress) {
        if(updateEntity("idgazrlowpress", idgazrlowpress) == true)
            this.idgazrlowpress = idgazrlowpress;
    }

    @Override
    public Object[] toDataArray() {
        Object[] retval = new Object[7];
        
        retval[0] = getIndex();
        retval[1] = dimeter;
        retval[2] = rlowlose;
        retval[3] = rlowlenght;
        retval[4] = (to25year == 0 ? false : true);
        retval[5] = (podrabterritory == 0 ? false : true);
        retval[6] = result;
        return retval;
    }

    public boolean updateEntity(int Col, Object fieldvalue) {
        String fieldname = null;
        switch(Col){
            case 1:
                fieldname = "IDGAZRLOWPRESS";
                break;
            case 3:
                fieldname = "RLOWLENGHT";
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
            default:
                break;
        }
        return super.updateEntity(fieldname, fieldvalue);
    }
    
    
    
    private void getEntity(){
        String sqlQuery = "SELECT B.IDGAZRLOWPRESS, B.RLOWLENGHT, B.TO25YEAR, " +
                "B.PODRABTERRITORY, B.RESULT FROM " +
                tablename + " B " + "WHERE B.ID=" + getId() +";";
        Object[] retval = getFieldValues(sqlQuery);
        idgazrlowpress = (int) retval[0];
        rlowlenght = (BigDecimal) retval[1];
        to25year = (Short) retval[2];
        podrabterritory = (Short) retval[3];
        result = (BigDecimal) retval[4];
        getRlowlose();
    }
    
    private void getRlowlose(){
        Sprgazlinerrlowpress rlowliner = new Sprgazlinerrlowpress(idgazrlowpress);
        dimeter = rlowliner.getDimeter();
        rlowlose = rlowliner.getLose();
    }
}
