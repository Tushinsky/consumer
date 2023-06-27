/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Сергей
 */
public class Warning extends TableEntity {
//    private int id;
    private String content;
    private short execution;
    private String tablename = "WARNING";
    
    public Warning() {
        super();
        setTablename(tablename);
        getEntity();
    }

    public Warning(int id, int index) {
        super(id, index);
        setTablename(tablename);
        getEntity();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        if(updateEntity("CONTENT", content) == true)
            this.content = content;
    }

    public boolean getExecution() {
        return (execution == 0 ? false : true);
    }

    public void setExecution(boolean execution) {
        short retval = (short)(execution == false ? 0 : 1);
        if(updateEntity("EXECUTION", retval) == true)
            this.execution = retval;
    }

    @Override
    public Object[] toDataArray() {
        Object[] retval = new Object[3];
        
        retval[0] = getIndex();
        retval[1] = content;
        retval[2] = (execution == 0 ? false : true);
        return retval;
    }

    public boolean updateValue(int col, Object value) {
        String fieldname = null;
        switch(col){
            case 1:
                // содержание
                fieldname = "CONTENT";
                break;
            case 2:
                // отметка о выполнении
                fieldname = "EXECUTION";
                break;
        }
        return super.updateEntity(fieldname, value);
    }
    
    
    private void getEntity(){
        String sqlQuery = "SELECT B.CONTENT, B.EXECUTION FROM " + 
                tablename + " B " + "WHERE B.ID=" + getId() +";";
        Object[] retval = getFieldValues(sqlQuery);
        // устанавливаем знаачения полей
        content = (String) retval[0];
        execution = (Short) retval[1];
        
    }
    
}
