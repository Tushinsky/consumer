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
public class SprcodeInstal extends TableEntity {
//    private int id;
    private BigDecimal january;
    private BigDecimal february;
    private BigDecimal march;
    private BigDecimal april;
    private BigDecimal may;
    private BigDecimal june;
    private BigDecimal july;
    private BigDecimal august;
    private BigDecimal september;
    private BigDecimal october;
    private BigDecimal november;
    private BigDecimal desember;
    private final String tablename = "SPRCODE_INSTAL";
    
    public SprcodeInstal() {
        super();
        super.setTablename(tablename);
        getEntity();
    }
    
    public SprcodeInstal(int id) {
        super(id);
        super.setTablename(tablename);
        getEntity();
    }

    public SprcodeInstal(int id, int index) {
        super(id, index);
        super.setTablename(tablename);
        getEntity();
    }

    public BigDecimal getJanuary() {
        return january;
    }

    public void setJanuary(BigDecimal january) {
        if(updateEntity("JANUARY", january) == true)
            this.january = january;
    }

    public BigDecimal getFebruary() {
        return february;
    }

    public void setFebruary(BigDecimal february) {
        if(updateEntity("FEBRUARY", february) == true)
            this.february = february;
    }

    public BigDecimal getMarch() {
        return march;
    }

    public void setMarch(BigDecimal march) {
        if(updateEntity("MARCH", march) == true)
            this.march = march;
    }

    public BigDecimal getApril() {
        return april;
    }

    public void setApril(BigDecimal april) {
        if(updateEntity("APRIK", april) == true)
            this.april = april;
    }

    public BigDecimal getMay() {
        return may;
    }

    public void setMay(BigDecimal may) {
        if(updateEntity("MAY", may) == true)
            this.may = may;
    }

    public BigDecimal getJune() {
        return june;
    }

    public void setJune(BigDecimal june) {
        if(updateEntity("JUNE", june) == true)
            this.june = june;
    }

    public BigDecimal getJuly() {
        return july;
    }

    public void setJuly(BigDecimal july) {
        if(updateEntity("JULY", july) == true)
            this.july = july;
    }

    public BigDecimal getAugust() {
        return august;
    }

    public void setAugust(BigDecimal august) {
        if(updateEntity("AUGUST", august) == true)
            this.august = august;
    }

    public BigDecimal getSeptember() {
        return september;
    }

    public void setSeptember(BigDecimal september) {
        if(updateEntity("SEPTEMBER", september) == true)
            this.september = september;
    }

    public BigDecimal getOctober() {
        return october;
    }

    public void setOctober(BigDecimal october) {
        if(updateEntity("OCTOBER", october) == true)
            this.october = october;
    }

    public BigDecimal getNovember() {
        return november;
    }

    public void setNovember(BigDecimal november) {
        if(updateEntity("NOVEMBER", november) == true)
            this.november = november;
    }

    public BigDecimal getDesember() {
        return desember;
    }

    public void setDesember(BigDecimal desember) {
        if(updateEntity("DESEMBER", desember) == true)
            this.desember = desember;
    }

    @Override
    public Object[] toDataArray() {
        Object[] retval = new Object[13];
        
        retval[0] = getIndex();
        retval[1] = january;
        retval[2] = february;
        retval[3] = march;
        retval[4] = april;
        retval[5] = may;
        retval[6] = june;
        retval[7] = july;
        retval[8] = august;
        retval[9] = september;
        retval[10] = october;
        retval[11] = november;
        retval[12] = desember;
        return retval;
    }
    
    /**
     * По заданному индеку месяца возвращает значение коэффициента приведения
     * @param Index
     * @return 
     */
    public BigDecimal getMonth(int Index){
        BigDecimal retval = null;
        switch(Index){
            case 0:
                retval = january;
                break;
            case 1:
                retval = february;
                break;
            case 2:
                retval = march;
                break;
            case 3:
                retval = april;
                break;
            case 4:
                retval = may;
                break;
            case 5:
                retval = june;
                break;
            case 6:
                retval = july;
                break;
            case 7:
                retval = august;
                break;
            case 8:
                retval = september;
                break;
            case 9:
                retval = october;
                break;
            case 10:
                retval = november;
                break;
            case 11:
                retval = desember;
                break;
        }
        return retval;
    }
    
    private void getEntity(){
        String sqlQuery = "SELECT B.JANUARY, B.FEBRUARY, B.MARCH, " +
                "B.APRIL, B.MAY, B.JUNE, B.JULY, B.AUGUST, " +
                "B.SEPTEMBER, B.OCTOBER, B.NOVEMBER, B.DESEMBER FROM " +
                tablename + " B " + "WHERE B.ID=" + getId() +";";
        Object[] retval = getFieldValues(sqlQuery);
        january = (BigDecimal) retval[0];
        february = (BigDecimal) retval[1];
        march = (BigDecimal) retval[2];
        april = (BigDecimal) retval[3];
        may = (BigDecimal) retval[4];
        june = (BigDecimal) retval[5];
        july = (BigDecimal) retval[6];
        august = (BigDecimal) retval[7];
        september = (BigDecimal) retval[8];
        october = (BigDecimal) retval[9];
        november = (BigDecimal) retval[10];
        desember = (BigDecimal) retval[11];
        
    }
}
