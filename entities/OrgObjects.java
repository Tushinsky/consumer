/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author —ергей
 */
public class OrgObjects extends TableEntity {
//    private int id;
    private String nameobject;
    private String addres;
    private String phone;
    private String mainman;
    private Date datelaunch;
    private short islaunch;
    private int idstreet;
    private int idcity;
    private final String tablename = "OBJECTS";
    private String nameCity;
    private String nameStreet;
    private int idorganization;
    
    public OrgObjects(int id) {
        super(id);
        super.setTablename(tablename);
        getEntity();
    }

    public OrgObjects(int id, int index) {
        super(id, index);
        super.setTablename(tablename);
        getEntity();
    }

    public String getNameobject() {
        return nameobject;
    }

    public void setNameobject(String nameobject) {
        if(updateEntity("nameobject", nameobject) == true)
            this.nameobject = nameobject;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        if(updateEntity("addres", addres) == true)
            this.addres = addres;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if(updateEntity("phone", phone) == true)
            this.phone = phone;
    }

    public String getMainman() {
        return mainman;
    }

    public void setMainman(String mainman) {
        if(updateEntity("mainman", mainman) == true)
            this.mainman = mainman;
    }

    public Date getDatelaunch() {
        return datelaunch;
    }

    public void setDatelaunch(Date datelaunch) {
        if(updateEntity("datelaunch", datelaunch) == true)
            this.datelaunch = datelaunch;
    }

    public boolean getIslaunch() {
        return (islaunch != 0);
    }

    public void setIslaunch(boolean islaunch) {
        short retval = (short) (islaunch == true ? 1 : 0);
        if(updateEntity("islaunch", retval) == true)
            this.islaunch = retval;
    }

    public int getIdstreet() {
        return idstreet;
    }

    public void setIdstreet(int idstreet) {
        if(updateEntity("idstreet", idstreet) == true)
            this.idstreet = idstreet;
    }

    public int getIdcity() {
        return idcity;
    }

    public void setIdcity(int idcity) {
        if(updateEntity("idcity", idcity) == true)
            this.idcity = idcity;
    }

    @Override
    public Object[] toDataArray() {
        Object[] retval = new Object[9];
        
        retval[0] = getIndex();
        retval[1] = nameobject;
        retval[2] = nameCity;
        retval[3] = nameStreet;
        retval[4] = addres;
        retval[5] = mainman;
        retval[6] = phone;
        retval[7] = datelaunch;
        retval[8] = (islaunch != 0);
        return retval;
    }

    @Override
    public boolean updateEntity(int col, Object fieldvalue) {
        String fieldname = null;
        switch(col){
            case 1:
                fieldname = "NAMEOBJECT";
                break;
            case 2:
                fieldname = "IDCITY";
                break;
            case 3:
                fieldname = "IDSTREET";
                break;
            case 4:
                fieldname = "ADDRES";
                break;
            case 5:
                fieldname = "MAINMAN";
                break;
            case 6:
                fieldname = "PHONE";
                break;
            case 7:
                fieldname = "DATELAUNCH";
                break;
            case 8:
                fieldname = "ISLAUNCH";
                break;
            
        }
        return super.updateEntity(fieldname, fieldvalue);
    }
    
    
    private void getEntity(){
        String sqlQuery = "SELECT B.IDORGANIZATION, B.NAMEOBJECT, B.IDCITY, " +
                "B.IDSTREET, B.ADDRES, B.MAINMAN, B.PHONE, B.DATELAUNCH, " +
                "B.ISLAUNCH FROM " + tablename + " B " + "WHERE B.ID=" + getId() +";";
        Object[] retval = getFieldValues(sqlQuery);// получаем массив данных полей
        idorganization = (int) retval[0];
        nameobject = (String) retval[1];
        idcity = (int) retval[2];
        idstreet = (int) retval[3];
        addres = (String) retval[4];
        mainman = (String) retval[5];
        phone = (String) retval[6];
        datelaunch = (Date) retval[7];
        islaunch = (short) retval[8];
        nameCity = getCity();
        nameStreet = getStreet();
    }
    
    private String getCity(){
        Sprcity city = new Sprcity(idcity);
        return city.getCityName();
    }
    
    private String getStreet(){
        Sprstreet street = new Sprstreet(idstreet);
        return street.getStreetName();
    }

    /**
     * @return the nameCity
     */
    public String getNameCity() {
        return nameCity;
    }

    /**
     * @return the nameStreet
     */
    public String getNameStreet() {
        return nameStreet;
    }

    /**
     * @return the idorganization
     */
    public int getIdOrganization() {
        return idorganization;
    }

    /**
     * @param idorganization the idorganization to set
     */
    public void setIdOrganization(int idorganization) {
        if(updateEntity("idorganization", idorganization) == true)
            this.idorganization = idorganization;
    }
}
