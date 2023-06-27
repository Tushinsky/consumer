/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author —ергей
 */
public class Service extends TableEntity {
//    private int id;
    private String characteristics;
    private int idpodryadchik;
    private int idarmatura;
    private String tablename = "SERVICE";
    
    public Service(int id) {
        super(id);
        setTablename(tablename);
        getEntity();
    }

    public Service(int id, int index) {
        super(id, index);
        setTablename(tablename);
        getEntity();
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String characteristics) {
        if(updateEntity("characteristics", characteristics) == true)
            this.characteristics = characteristics;
    }

    public int getIdpodryadchik() {
        return idpodryadchik;
    }

    public void setIdpodryadchik(int idpodryadchik) {
        if(updateEntity("idpodryadchik", idpodryadchik) == true)
            this.idpodryadchik = idpodryadchik;
    }

    public int getIdarmatura() {
        return idarmatura;
    }

    public void setIdarmatura(int idarmatura) {
        if(updateEntity("idarmatura", idarmatura) == true)
            this.idarmatura = idarmatura;
    }

    @Override
    public Object[] toDataArray() {
        Object[] retval = new Object[4];
        retval[0] = getIndex();
        retval[1] = getPodryadchik();
        retval[2] = getArmatura();
        retval[3] = characteristics;
        return retval;
    }
    
    private void getEntity(){
        String sqlQuery = "SELECT B.IDARMATURA, B.IDPODRYADCHIK, " +
                "B.CHARACTERISTICS FROM " + tablename +
                " B " + "WHERE B.ID=" + getId() +";";
        Object[] retval = getFieldValues(sqlQuery);// получаем массив данных полей
        idarmatura = (int) retval[0];
        idpodryadchik = (int) retval[1];
        characteristics = (String) retval[2];
    }
    
    private String getArmatura() {
        Sprarmatura armatura = new Sprarmatura(idarmatura);
        return armatura.getArmaturaName();
    }
    
    private String getPodryadchik() {
        Sprpodryadchik podryadchik = new Sprpodryadchik(idpodryadchik);
        return podryadchik.getPodryadchikName();
    }
}
