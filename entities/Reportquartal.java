/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Сергей
 */
public class Reportquartal extends TableEntity {
//    private int id;
    private short monthnumber;
    private String category;
    private String uzeltype;
    private short vsego;
    private int idyear;
    private String tablename = "REPORTQUARTAL";
    
    public Reportquartal() {
        super();
        setTablename(tablename);
    }

    public Reportquartal(int id, int index) {
        super(id, index);
        setTablename(tablename);
    }

    public Short getMonthnumber() {
        return monthnumber;
    }

    public void setMonthnumber(Short monthnumber) {
        this.monthnumber = monthnumber;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUzeltype() {
        return uzeltype;
    }

    public void setUzeltype(String uzeltype) {
        this.uzeltype = uzeltype;
    }

    public Short getVsego() {
        return vsego;
    }

    public void setVsego(Short vsego) {
        this.vsego = vsego;
    }

    public int getIdyear() {
        return idyear;
    }

    public void setIdyear(int idyear) {
        this.idyear = idyear;
    }

    public Object[] toDataArray() {
        Object[] retval = new Object[6];
        getEntity();
        retval[0] = getIndex();
        retval[1] = getYear();
        retval[2] = monthnumber;
        retval[3] = category;
        retval[4] = uzeltype;
        retval[5] = vsego;
        return retval;
    }
    
    private void getEntity(){
        String sqlQuery = "SELECT B.IDYEAR, B.MONTHNUMBER, B.CATEGORY, " +
                "B.UZELTYPE, B.VSEGO FROM " + 
                tablename + " B WHERE B.ID=" + getId() + ";";
        Object[] retval = getFieldValues(sqlQuery);
        idyear = (int) retval[0];
        monthnumber = (short) retval[1];
        category = (String) retval[2];
        uzeltype = (String) retval[3];
        vsego = (short) retval[4];
    }
    
    private short getYear(){
        Spryear year = new Spryear(idyear);
        year.toDataArray();
        return year.getNameYear();
    }
    
    
}
