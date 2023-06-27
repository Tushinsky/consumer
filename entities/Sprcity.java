/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Сергей
 */
public class Sprcity extends TableEntity {
//    private int id;
    private String cityName;
    private int idgrs;
    private String tablename = "SPRCITY";
    private String grsName;

    public Sprcity() {
        super();
        super.setTablename(tablename);
        getEntity();
    }

    public Sprcity(int id) {
        super(id);
        super.setTablename(tablename);
        getEntity();
    }
    

    public Sprcity(int id, int index) {
        super(id, index);
        setTablename(tablename);
        getEntity();
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        if(updateEntity("CITY_NAME", "'" + cityName + "'") == true)
            this.cityName = cityName;
    }

    public int getIdgrs() {
        return idgrs;
    }

    public void setIdgrs(int idgrs) {
        if(updateEntity("IDGRS", idgrs) == true)
            this.idgrs = idgrs;
    }

    @Override
    public Object[] toDataArray() {
        Object[] retval = new Object[3];
        
        retval[0] = getIndex();
        retval[1] = cityName;
        retval[2] = grsName;
        return retval;
    }
    
    private void getEntity(){
        String sqlQuery = "SELECT B.IDGRS, B.CITY_NAME FROM " +
                tablename + " B " + "WHERE B.ID=" + getId() +";";
        Object[] retval = getFieldValues(sqlQuery);
        idgrs = (int) retval[0];
        cityName = (String) retval[1];
        grsName = getGRS();
    }

    private String getGRS(){
        // создаём справочник
        Sprgrs grs = new Sprgrs(idgrs);
        
        // возвращаем наименование ГРС
        return grs.getGrsName();
    }

    /**
     * @return the grsName
     */
    public String getGrsName() {
        return grsName;
    }
}
