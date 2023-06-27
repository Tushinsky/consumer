/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Сергей
 */
public class Sprmaker extends TableEntity {
    private String makerName;
    private String tablename = "SPRMAKER";
    
    public Sprmaker(int id) {
        super(id);
        setTablename(tablename);
        getEntity();
    }

    public Sprmaker(int id, int index) {
        super(id, index);
        setTablename(tablename);
        getEntity();
    }

    public String getMakerName() {
        return makerName;
    }

    public void setMakerName(String makerName) {
        if(updateEntity("MAKER_NAME", makerName) == true)
            this.makerName = makerName;
    }

    @Override
    public Object[] toDataArray() {
        Object[] retval = new Object[2];
        retval[0] = getIndex();
        retval[1] = makerName;
        return retval;

    }

    public boolean updateEntity(int col, Object fieldvalue) {
        String fieldname = null;
        if(col == 1){
            fieldname = "MAKER_NAME";
        }
        return super.updateEntity(fieldname, fieldvalue);
    }
    
    
    
    private void getEntity(){
        String sqlQuery = "SELECT B.MAKER_NAME FROM " + 
                tablename + " B " + "WHERE B.ID=" + getId() +";";
        Object[] retval = getFieldValues(sqlQuery);
        // устанавливаем знаачения полей
        makerName = (String) retval[0];
        
    }
    
}
