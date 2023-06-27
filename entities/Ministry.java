/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Сергей
 */
public class Ministry extends TableEntity {

//    private int id;
    private int idMinistry;
    private final String tablename = "MINISTRY";
    private String ministryName;
    
    public Ministry(int id, int index) {
        super(id, index);
        super.setTablename(tablename);
        getEntity();
    }

    public Ministry() {
        super();
        super.setTablename(tablename);
        getEntity();
    }

    public Ministry(int id) {
        super(id);
        super.setTablename(tablename);
        getEntity();
    }
    
    
    /**
     * @return the idMinistry
     */
    public int getIdMinistry() {
        return idMinistry;
    }

    /**
     * @param idMinistry the idMinistry to set
     */
    public void setIdMinistry(int idMinistry) {
        if(updateEntity("idministry", idMinistry) == true)
            this.idMinistry = idMinistry;
    }

    @Override
    public Object[] toDataArray() {
        Object[] retval = new Object[2];
        
        retval[0] = getIndex();
        retval[1] = ministryName;
        return retval;
    }
    
    private void getEntity(){
        String sqlQuery = "SELECT B.IDMINISTRY FROM " +
                tablename + " B " + "WHERE B.ID=" + getId() +";";
        Object[] retval = getFieldValues(sqlQuery);// получаем массив данных полей
        idMinistry = (int) retval[0];// получаем код министерства
        ministryName = getMinistry();
    }
    
    private String getMinistry(){
        Sprministry ministry = new Sprministry(idMinistry);
        return ministry.getMinistryName();
    }

    /**
     * @return the ministryName
     */
    public String getMinistryName() {
        return ministryName;
    }
}
