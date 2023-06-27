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
public class Reglose extends TableEntity {
//    private int id;
    private short regcount;
    private BigDecimal result;
    private int idregulator;
    private final String tablename = "REGLOSE";
    private BigDecimal reglose;
    private String regname;
    
    public Reglose(int id) {
        super(id);
        super.setTablename(tablename);
        getEntity();
    }

    public Reglose(int id, int index) {
        super(id, index);
        super.setTablename(tablename);
        getEntity();
    }

    public short getRegcount() {
        return regcount;
    }

    public void setRegcount(short regcount) {
        if(updateEntity("regcount", regcount) == true)
            this.regcount = regcount;
    }

    public BigDecimal getResult() {
        return result;
    }

    public void setResult(BigDecimal result) {
        if(updateEntity("result", result) == true)
            this.result = result;
    }

    public int getIdregulator() {
        return idregulator;
    }

    public void setIdregulator(int idregulator) {
        if(updateEntity("idregulator", idregulator) == true)
            this.idregulator = idregulator;
    }

    @Override
    public Object[] toDataArray() {
        Object[] retval = new Object[5];
        retval[0] = getIndex();
        retval[1] = regname;
        retval[2] = reglose;
        retval[3] = regcount;
        retval[4] = result;
        return retval;
    }

    @Override
    public boolean updateEntity(int col, Object fieldvalue) {
        String fieldname = null;
        switch(col){
            case 1:
                fieldname = "IDREGULATOR";
                break;
            case 3:
                fieldname = "RECOUNT";
                break;
            case 4:
                fieldname = "RESULT";
                break;
            default:
                break;
        }
        return super.updateEntity(fieldname, fieldvalue);
    }
    
    private void getEntity(){
        String sqlQuery = "SELECT B.IDREGULATOR, B.REGCOUNT, B.RESULT FROM " + 
                tablename + " B WHERE B.ID=" + getId() + ";";
        Object[] retval = getFieldValues(sqlQuery);
        idregulator = (int) retval[0];
        regcount = (short) retval[1];
        result = (BigDecimal) retval[2];
        getRegulator();
    }
    
    private void getRegulator() {
        Sprgazregulator regulator = new Sprgazregulator(idregulator);
        regname = regulator.getName();
        reglose = regulator.getLose();
    }
}
