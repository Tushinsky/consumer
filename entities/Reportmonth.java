/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Сергей
 */
public class Reportmonth extends TableEntity {
//    private int id;
    private short monthnumber;
    private String category;
    private short categorycount;
    private short uselcount;
    private short enterprisetype;
    private short bsgtype;
    private int idyear;
    private String tablename = "REPORTMONTH";
    
    public Reportmonth() {
        super();
        setTablename(tablename);
    }

    public Reportmonth(int id, int index) {
        super(id, index);
        setTablename(tablename);
    }

    public short getMonthnumber() {
        return monthnumber;
    }

    public void setMonthnumber(short monthnumber) {
        if(updateEntity("monthnumber", monthnumber) == true)
            this.monthnumber = monthnumber;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        if(updateEntity("category", "'" + category + "'") == true)
            this.category = category;
    }

    public short getCategorycount() {
        return categorycount;
    }

    public void setCategorycount(short categorycount) {
        if(updateEntity("categorycount", categorycount) == true)
            this.categorycount = categorycount;
    }

    public short getUselcount() {
        return uselcount;
    }

    public void setUselcount(short uselcount) {
        if(updateEntity("uselcount", uselcount) == true)
            this.uselcount = uselcount;
    }

    public short getEnterprisetype() {
        return enterprisetype;
    }

    public void setEnterprisetype(short enterprisetype) {
        if(updateEntity("enterprisetype", enterprisetype) == true)
            this.enterprisetype = enterprisetype;
    }

    public short getBsgtype() {
        return bsgtype;
    }

    public void setBsgtype(short bsgtype) {
        if(updateEntity("bsgtype", bsgtype) == true)
            this.bsgtype = bsgtype;
    }

    public int getIdyear() {
        return idyear;
    }

    public void setIdyear(int idyear) {
        if(updateEntity("idyear", idyear) == true)
            this.idyear = idyear;
    }

    public Object[] toDataArray() {
        Object[] retval = new Object[8];
        getEntity();
        retval[0] = getIndex();
        retval[1] = getYear();
        retval[2] = monthnumber;
        retval[3] = category;
        retval[4] = categorycount;
        retval[5] = bsgtype;
        retval[6] = enterprisetype;
        retval[7] = uselcount;
        return retval;
    }
    
    private void getEntity(){
        String sqlQuery = "SELECT B.IDYEAR, B.MONTHNUMBER, B.CATEGORY, B.CATEGORYCOUNT, " +
                "B.USELCOUNT, B.BSGTYPE, B.ENTERPRISETYPE FROM " + 
                tablename + " B WHERE B.ID=" + getId() + ";";
        Object[] retval = getFieldValues(sqlQuery);
        idyear = (int) retval[0];
        monthnumber = (short) retval[1];
        category = (String) retval[2];
        categorycount = (short) retval[3];
        uselcount = (short) retval[4];
        bsgtype = (short) retval[5];
        enterprisetype = (short) retval[6];
    }
    
    private short getYear(){
        Spryear year = new Spryear(idyear);
        year.toDataArray();
        return year.getNameYear();
    }
    
}
