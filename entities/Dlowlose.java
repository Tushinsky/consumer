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
public class Dlowlose extends TableEntity {
//    private int id;
    private BigDecimal dlowlenght;
    private short to25year;
    private short podrabterritory;
    private BigDecimal result;
    private int idgazdlowpress;
    private String tablename = "DLOWLOSE";
    private BigDecimal dlowlose;
    private short dimeter;
    
    public Dlowlose(int id) {
        super(id);
        setTablename(tablename);
        getEntity();
    }

    public Dlowlose(int id, int index) {
        super(id, index);
        setTablename(tablename);
        getEntity();
    }

    public BigDecimal getDlowlenght() {
        return dlowlenght;
    }

    public void setDlowlenght(BigDecimal dlowlenght) {
        if(updateEntity("dlowlenght", dlowlenght) == true)
            this.dlowlenght = dlowlenght;
    }

    public boolean getTo25year() {
        return to25year != 0;
    }

    public void setTo25year(boolean to25year) {
        short retval = (short) (to25year == true ? 1 : 0);
        if(updateEntity("to25year", retval))
            this.to25year = retval;
    }

    public boolean getPodrabterritory() {
        return podrabterritory != 0;
    }

    public void setPodrabterritory(boolean podrabterritory) {
        short retval = (short) (podrabterritory == true ? 1 : 0);
        if(updateEntity("podrabterritory", retval))
            this.podrabterritory = retval;
    }

    public BigDecimal getResult() {
        return result;
    }

    public void setResult(BigDecimal result) {
        if(updateEntity("result", result))
            this.result = result;
    }

    public int getIdgazdlowpress() {
        return idgazdlowpress;
    }

    public void setIdgazdlowpress(int idgazdlowpress) {
        if(updateEntity("idgazdlowpress", idgazdlowpress))
            this.idgazdlowpress = idgazdlowpress;
    }

    @Override
    public Object[] toDataArray() {
        Object[] retval = new Object[7];
        retval[0] = getIndex();
        retval[1] = dimeter;
        retval[2] = dlowlose;
        retval[3] = dlowlenght;
        retval[4] = (to25year == 0 ? false : true);
        retval[5] = (podrabterritory == 0 ? false : true);
        retval[6] = result;
        return retval;
    }

    
    @Override
    public boolean updateEntity(int Col, Object fieldvalue) {
        String fieldname = null;
        switch(Col){
            case 1:
                fieldname = "IDGAZDLOWPRESS";
                break;
            case 3:
                fieldname = "DLOWLENGHT";
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
        String sqlQuery = "SELECT B.IDGAZDLOWPRESS, B.DLOWLENGHT, " +""
                + "B.TO25YEAR, B.PODRABTERRITORY, B.RESULT FROM " +
                tablename + " B WHERE B.ID=" + getId() +";";
        Object[] retval = super.getFieldValues(sqlQuery);
        idgazdlowpress = (int) retval[0];
        dlowlenght = (BigDecimal) retval[1];
        to25year = (short) retval[2];
        podrabterritory = (short) retval[3];
        result = (BigDecimal) retval[4];
        getGazliner();
    }
    
    
    private void getGazliner(){
        Sprgazlinerdlowpress dlowliner = new Sprgazlinerdlowpress(idgazdlowpress);
        dimeter = dlowliner.getDimeter();
        dlowlose = dlowliner.getLose();
    }

    /**
     * @return the dlowlose
     */
    public BigDecimal getDlowlose() {
        return dlowlose;
    }

    /**
     * @return the dimeter
     */
    public short getDimeter() {
        return dimeter;
    }
}
