/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Сергей
 */
public class Gazusing extends TableEntity {
//    private int id;
    private int idusingtype;
    private String usingType;
    private String tablename = "GAZUSING";

    public Gazusing(int id) {
        super(id);
        setTablename(tablename);
        getEntity();
    }

    public Gazusing(int id, int index) {
        super(id, index);
        setTablename(tablename);
        getEntity();
    }

    public int getIdusingtype() {
        return idusingtype;
    }

    public void setIdusingtype(int idusingtype) {
        if(updateEntity("IDUSINGTYPE", idusingtype) == true)
            this.idusingtype = idusingtype;
    }
    
    public String getUsingType(){
        return usingType;
    }

    @Override
    public Object[] toDataArray() {
        Object[] retval = new Object[2];
        getEntity();
        retval[0] = getIndex();
        retval[1] = usingType;
        return retval;
    }

    private void getEntity(){
        String sqlQuery = "SELECT B.IDusingtype FROM " +
                tablename + " B " + "WHERE B.ID=" + getId() +";";
        Object[] retval = getFieldValues(sqlQuery);
        idusingtype = (int) retval[0];
        usingType = getUsingtype();
    }
    
//    private boolean updateEntity(String fieldname, Object fieldvalue){
//        String sqlQuery = "UPDATE " + tablename + " B SET B." + fieldname +
//                "=" + fieldvalue + " WHERE B.ID=" + getId() + ";";
//        Runquery rq = new Runquery();
//        return rq.updateFieldValue(sqlQuery);
//    }

    private String getUsingtype(){
        // создаём справочник
        Sprusinggaz usinggaz = new Sprusinggaz(idusingtype);
        // возвращаем тип использования
        return usinggaz.getUsingName();
    }
}
