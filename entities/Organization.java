/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author —ергей
 */
public class Organization extends TableEntity {
//    private int id;
    private String organizationName;
    private String agriment;
    private String agrimentdate;
    private String director;
    private String ustav;
    private String edrpou;
    private Short stamp;
    private int idcategory;
    private final String tablename = "ORGANIZATION";
    private String categoryName;
    
    public Organization(int id) {
        super(id);
        super.setTablename(tablename);
        getEntity();
    }

    public Organization(int id, int index) {
        super(id, index);
        super.setTablename(tablename);
        getEntity();
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        if(updateEntity("ORGANIZATION_NAME", "'" + organizationName + "'") == true)
            this.organizationName = organizationName;
    }

    public String getAgriment() {
        return agriment;
    }

    public void setAgriment(String agriment) {
        if(updateEntity("AGRIMENT", "'" + agriment + "'") == true)
            this.agriment = agriment;
    }

    public String getAgrimentdate() {
        return agrimentdate;
    }

    public void setAgrimentdate(String agrimentdate) {
        if(updateEntity("AGRIMENTDATE", "'" + agrimentdate + "'") == true)
            this.agrimentdate = agrimentdate;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        if(updateEntity("DIRECTOR", "'" + director + "'") == true)
            this.director = director;
    }

    public String getUstav() {
        return ustav;
    }

    public void setUstav(String ustav) {
        if(updateEntity("USTAV", "'" + ustav + "'") == true)
            this.ustav = ustav;
    }

    public String getEdrpou() {
        return edrpou;
    }

    public void setEdrpou(String edrpou) {
        if(updateEntity("EDRPOU", "'" + edrpou + "'") == true)
            this.edrpou = edrpou;
    }

    public boolean getStamp() {
        boolean retval = (stamp != 0);
        return retval;
    }

    public void setStamp(boolean stamp) {
        short retval = (short) (stamp == true ? 1 : 0);
        if(updateEntity("STAMP", retval) == true)
            this.stamp = retval;
    }

    public int getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(int idcategory) {
        if(updateEntity("IDCATEGORY", idcategory) == true)
        this.idcategory = idcategory;
    }

    /**
     * 
     * @return массив данных : є, код, категори€, наименование, руководитель
     * є договора, дата договора, едрпоу, устав, печать
     */
    @Override
    public Object[] toDataArray() {
        Object[] retval = new Object[10];
//        getEntity();
        retval[0] = getIndex();
        retval[1] = getId();
        retval[2] = getCategoryName();
        retval[3] = organizationName;
        retval[4] = director;
        retval[5] = agriment;
        retval[6] = agrimentdate;
        retval[7] = edrpou;
        retval[8] = ustav;
        retval[9] = (stamp != 0);
        return retval;
    }
    
    /**
     * 
     * @return наименование категории из справочника, соответствующее коду
     */
    private String getCategory(){
        Sprcategory category = new Sprcategory(idcategory);
//        category.setId(idcategory);
//        Object[] toDataArray = category.toDataArray();
        return category.getCategoryName();
    }
    
    private void getEntity(){
        String sqlQuery = "SELECT B.IDCATEGORY, B.ORGANIZATION_NAME, " +
                "B.AGRIMENT, B.AGRIMENTDATE, B.DIRECTOR, B.USTAV, B.EDRPOU, " +
                "B.STAMP FROM " + tablename + " B " + "WHERE B.ID=" + getId() +";";
        Object[] retval = getFieldValues(sqlQuery);// получаем массив данных полей
//        for(int i = 0; i < retval.length; i++)
//            System.out.println(retval[0]);
        idcategory = (int) retval[0];
        organizationName = (String) retval[1];
        agriment = (String) retval[2];
        agrimentdate = String.valueOf(retval[3]);
        director = (String) retval[4];
        ustav = (String) retval[5];
        edrpou = (String) retval[6];
        stamp = (Short) retval[7];
        categoryName = getCategory();
    }
    
//    private boolean updateEntity(String fieldname, Object fieldvalue){
//        String sqlQuery = "UPDATE " + tablename + " B SET B." + fieldname +
//                "=" + fieldvalue + " WHERE B.ID=" + getId() + ";";
//        Runquery rq = new Runquery();
//        return rq.updateFieldValue(sqlQuery);
//    }

    /**
     * @return the categoryName
     */
    public String getCategoryName() {
        return categoryName;
    }
}
