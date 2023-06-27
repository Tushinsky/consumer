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
public class Corrector extends TableEntity {
    // "ID")
//    private int id;
    // "CORRECTORNUMBER")
    private String correctornumber;
    // "PRODUCTDATE")
    private Date productdate;
    // "TESTDATE")
    private Date testdate;
    // "INSTALDATE")
    private Date instaldate;
    // "PERIOD")
    private short period;
    // "ABONPROPERTY")
    private short abonproperty;
    // "MODEM")
    private String modem;
    // "ADDRES")
    private String addres;
    // "IDCORRECTOR"
    private int idcorrector;
    
    private final String tablename = "CORRECTOR";
    
    public Corrector(int id) {
        super(id);
        super.setTablename(tablename);
        getEntity();
    }

    public Corrector(int id, int index) {
        super(id, index);
        super.setTablename(tablename);
        getEntity();
    }

    
    public String getCorrectornumber() {
        return correctornumber;
    }

    public void setCorrectornumber(String correctornumber) {
        if(updateEntity("CORRECTORNUMBER", correctornumber) == true)
            this.correctornumber = correctornumber;
    }

    public Date getProductdate() {
        return productdate;
    }

    public void setProductdate(Date productdate) {
        if(updateEntity("PRODUCTDATE", productdate) == true)
            this.productdate = productdate;
    }

    public Date getTestdate() {
        return testdate;
    }

    public void setTestdate(Date testdate) {
        if(updateEntity("TESTDATE", testdate) == true)
            this.testdate = testdate;
    }

    public Date getInstaldate() {
        return instaldate;
    }

    public void setInstaldate(Date instaldate) {
        if(updateEntity("INSTALDATE", instaldate) == true)
            this.instaldate = instaldate;
    }

    public short getPeriod() {
        return period;
    }

    public void setPeriod(short period) {
        if(updateEntity("PERIOD", period) == true)
            this.period = period;
    }

    public boolean getAbonproperty() {
        return (abonproperty != 0);
    }

    public void setAbonproperty(boolean abonproperty) {
        short retval = (short) (abonproperty == true ? 1 : 0);
        if(updateEntity("ABONPROPERTY", retval) == true)
            this.abonproperty = retval;
    }

    public String getModem() {
        return modem;
    }

    public void setModem(String modem) {
        if(updateEntity("MODEM", modem) == true)
            this.modem = modem;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        if(updateEntity("ADDRES", addres) == true)
            this.addres = addres;
    }

    public int getIdcorrector() {
        return idcorrector;
    }

    public void setIdcorrector(int idcorrector) {
        if(updateEntity("IDCORRECTOR", idcorrector) == true)
            this.idcorrector = idcorrector;
    }

    @Override
    public Object[] toDataArray() {
        Object[] retval = new Object[10];
        
        retval[0] = getIndex();
        retval[1] = getCorrector();
        retval[2] = correctornumber;
        retval[3] = productdate;
        retval[4] = instaldate;
        retval[5] = testdate;
        retval[6] = period;
        retval[7] = modem;
        retval[8] = addres;
        retval[9] = (abonproperty != 0);
        return retval;
    }
    
    /**
     * обновляет данные объекта по номеру поля
     * @param col номер поля для обновления данных
     * @param fieldvalue значение поля для обновления
     * @return true - если опереция обновления удачна, иначе возвращает false
     */
    @Override
    public boolean updateEntity(int col, Object fieldvalue){
        String fieldname = null;
        switch(col){
            case 1:
                fieldname = "IDCORRECTOR";
                break;
            case 2:
                fieldname = "CORRECTORNUMBER";
                break;
            case 3:
                fieldname = "PRODUCTDATE";
                break;
            case 4:
                fieldname = "INSTALDATE";
                break;
            case 5:
                fieldname = "TESTDATE";
                break;
            case 6:
                fieldname = "PERIOD";
                break;
            case 7:
                fieldname = "MODEM";
                break;
            case 8:
                fieldname = "ADDRES";
                break;
            case 9:
                fieldname = "ABONPROPERTY";
                break;
                
        }
        return super.updateEntity(fieldname, fieldvalue);
    }

    private void getEntity(){
        String sqlQuery = "SELECT B.IDCORRECTOR, " +
                "B.CORRECTORNUMBER, B.PRODUCTDATE, B.INSTALDATE, " +
                "B.TESTDATE, B.PERIOD, B.MODEM, B.ADDRES, B.ABONPROPERTY FROM " +
                tablename + " B " + "WHERE B.ID=" + getId() +";";
        Object[] retval = getFieldValues(sqlQuery);
        idcorrector = (int) retval[0];
        correctornumber = (String) retval[1];
        productdate = (Date) retval[2];
        instaldate = (Date) retval[3];
        testdate = (Date) retval[4];
        period = (short) retval[5];
        modem = retval[6].toString();
        addres = retval[7].toString();
        abonproperty= (short) retval[8];
        getCorrector();
    }
    
    private String getCorrector(){
        Sprcorrector corrector = new Sprcorrector(idcorrector);
        return corrector.toString();
    }
}
