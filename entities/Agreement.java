/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author ������
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
        getAgreement();// �������� ������ �� ��������
        
    }

    public Agreement(int id, int index) {
        super(id, index);
        super.setTablename(tablename);
        getAgreement();// �������� ������ �� ��������
        
    }
    
    public String getAgreementnumber() {
        return agreementnumber;
    }

    public void setAgreementnumber(String agreementnumber) {
        // ��������� ��������� ���������� ������
        if(updateEntity("AGREEMENTNUMBER", "'" + agreementnumber + "'") == true)
            this.agreementnumber = agreementnumber;
    }

    public Date getAgreementdate() {
        return agreementdate;
    }

    public void setAgreementdate(Date agreementdate) {
        // ��������� ��������� ���������� ������
        if(updateEntity("AGREEMENTDATE", "'" + agreementdate + "'") == true)
            this.agreementdate = agreementdate;
    }

    public String getPredmet() {
        return predmet;
    }

    public void setPredmet(String predmet) {
        // ��������� ��������� ���������� ������
        if(updateEntity("PREDMET", "'" + predmet + "'") == true)
            this.predmet = predmet;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        // ��������� ��������� ���������� ������
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
     * �������� ������ �������� �� ���� ������
     */
    private void getAgreement(){
        String sqlQuery = "SELECT A.AGREEMENTNUMBER, A.AGREEMENTDATE, " +
                "A.PREDMET, A.SUBJECT FROM AGREEMENT A WHERE A.ID=" +
                getId() + ";";// ������ - ������
        Object[] retval = getFieldValues(sqlQuery);
        // ��������� ����
        agreementnumber = (String) retval[0];
        agreementdate = (Date) retval[1];
        predmet = (String) retval[2];
        subject = (String) retval[3];
        
    }
    
}
