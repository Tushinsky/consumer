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
public class Sprcounter extends TableEntity {
//    private int id;
    private String counterType;
    private String counterName;
    private String region;
    private BigDecimal qmin;
    private BigDecimal qmax;
    private BigDecimal qnom;
    private BigDecimal class1;
    private Short dn;
    private int idmaker;
    private final String tablename = "SPRCOUNTER";
    private String counterMaker;
    
    public Sprcounter(int id) {
        super(id);
        super.setTablename(tablename);
        getEntity();
    }

    public Sprcounter(int id, int index) {
        super(id, index);
        super.setTablename(tablename);
        getEntity();
    }

    public String getCounterType() {
        return counterType;
    }

    public void setCounterType(String counterType) {
        if(updateEntity("COUNTER_TYPE", "'" + counterType + "'") == true)
            this.counterType = counterType;
    }

    public String getCounterName() {
        return counterName;
    }

    public void setCounterName(String counterName) {
        if(updateEntity("COUNTER_NAME", "'" + counterName + "'") == true)
            this.counterName = counterName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        if(updateEntity("REGION", "'" + region + "'") == true)
            this.region = region;
    }

    public BigDecimal getQmin() {
        return qmin;
    }

    public void setQmin(BigDecimal qmin) {
        if(updateEntity("QMIN", qmin) == true)
            this.qmin = qmin;
    }

    public BigDecimal getQmax() {
        return qmax;
    }

    public void setQmax(BigDecimal qmax) {
        if(updateEntity("QMAX", qmax) == true)
            this.qmax = qmax;
    }

    public BigDecimal getQnom() {
        return qnom;
    }

    public void setQnom(BigDecimal qnom) {
        if(updateEntity("QNOM", qnom) == true)
            this.qnom = qnom;
    }

    public BigDecimal getClass1() {
        return class1;
    }

    public void setClass1(BigDecimal class1) {
        if(updateEntity("CLASS", class1) == true)
            this.class1 = class1;
    }

    public short getDn() {
        return dn;
    }

    public void setDn(short dn) {
        if(updateEntity("DN", dn) == true)
            this.dn = dn;
    }

    public int getIdmaker() {
        return idmaker;
    }

    public void setIdmaker(int idmaker) {
        if(updateEntity("IDMAKER", idmaker) == true)
            this.idmaker = idmaker;
    }

    @Override
    public Object[] toDataArray() {
        Object[] retval = new Object[10];
        
        retval[0] = getIndex();
        retval[1] = counterMaker;
        retval[2] = region;
        retval[3] = counterName;
        retval[4] = counterType;
        retval[5] = qmin;
        retval[6] = qnom;
        retval[7] = qmax;
        retval[8] = dn;
        retval[9] = class1;
        return retval;
    }

    @Override
    public String toString() {
        return counterType + " " + counterMaker + " " + counterName + " G" +
                qnom.toString() + "-" + dn.toString() + " " +
                qmin.toString() + "-" + qmax.toString();
    }
    
    
    private void getEntity(){
        String sqlQuery = "SELECT B.IDMAKER, B.COUNTER_NAME, B.COUNTER_TYPE, " +
                "B.REGION, B.QMIN, B.QNOM, B.QMAX, B.DN, B.CLASS FROM " +
                tablename + " B " + "WHERE B.ID=" + getId() +";";
        Object[] retval = getFieldValues(sqlQuery);
        idmaker = (int) retval[0];
        counterName = (String) retval[1];
        counterType = (String) retval[2];
        region = (String) retval[3];
        qmin = (BigDecimal) retval[4];
        qnom = (BigDecimal) retval[5];
        qmax = (BigDecimal) retval[6];
        dn = (short) retval[7];
        class1 = (BigDecimal) retval[8];
        counterMaker = getMaker();
    }
    
    private String getMaker(){
        Sprmaker maker = new Sprmaker(idmaker);
        // возвращаем производителя
        return maker.getMakerName();
    }

    /**
     * @return the counterMaker
     */
    public String getCounterMaker() {
        return counterMaker;
    }

}
