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
public class Agreement extends TableEntity {
//    private int id;//id
    // "AGREEMENTNUMBER")
    private String agreementnumber;
    // "AGREEMENTDATE")
    private Date agreementdate;
    // "PREDMET")
    private String predmet;
    // "SUBJECT")
    private String subject;
    private final String tablename = "AGREEMENT";
    
    public Agreement(int id) {
        super(id);
        super.setTablename(tablename);
        getAgreement();// получаем данные по сущности
        
    }

    public Agreement(int id, int index) {
        super(id, index);
        super.setTablename(tablename);
        getAgreement();// получаем данные по сущности
        
    }
    
    public String getAgreementnumber() {
        return agreementnumber;
    }

    public void setAgreementnumber(String agreementnumber) {
        // проверяем результат обновления данных
        if(updateEntity("AGREEMENTNUMBER", "'" + agreementnumber + "'") == true)
            this.agreementnumber = agreementnumber;
    }

    public Date getAgreementdate() {
        return agreementdate;
    }

    public void setAgreementdate(Date agreementdate) {
        // проверяем результат обновления данных
        if(updateEntity("AGREEMENTDATE", "'" + agreementdate + "'") == true)
            this.agreementdate = agreementdate;
    }

    public String getPredmet() {
        return predmet;
    }

    public void setPredmet(String predmet) {
        // проверяем результат обновления данных
        if(updateEntity("PREDMET", "'" + predmet + "'") == true)
            this.predmet = predmet;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        // проверяем результат обновления данных
        if(updateEntity("SUBJECT", "'" + subject + "'") == true)
            this.subject = subject;
    }

    @Override
    public Object[] toDataArray() {
        Object[] retval;
        retval = new Object[5];
        retval[0] = getIndex();
        retval[1] = agreementnumber;
        retval[2] = agreementdate;
        retval[3] = predmet;
        retval[4] = subject;
        return retval;
    }
    
    /**
     * получает данные сущности из базы данных
     */
    private void getAgreement(){
        String sqlQuery = "SELECT A.AGREEMENTNUMBER, A.AGREEMENTDATE, " +
                "A.PREDMET, A.SUBJECT FROM AGREEMENT A WHERE A.ID=" +
                getId() + ";";// строка - запрос
        Object[] retval = getFieldValues(sqlQuery);
        // заполняем поля
        agreementnumber = (String) retval[0];
        agreementdate = (Date) retval[1];
        predmet = (String) retval[2];
        subject = (String) retval[3];
        
    }
    
}
