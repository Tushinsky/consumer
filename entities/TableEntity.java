/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import runqueries.Runquery;

/**
 *
 * @author Sergii.Tushinskyi
 */
public class TableEntity {
    
    private int id;// код сущности из таблицы базы данных
    private int index;// порядковый номер сущности
    private String tablename;// имя таблицы, которую представляет сущность
    
    public TableEntity() {
    }

    public TableEntity(int id) {
        this.id = id;
    }

    public TableEntity(int id, int index) {
        this.id = id;
        this.index = index;
    }
    
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TableEntity other = (TableEntity) obj;
        if (this.id != other.id) {
            return false;
        }
        return this.index == other.index;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * @return the tablename
     */
    public String getTablename() {
        return tablename;
    }

    /**
     * @param tablename the tablename to set
     */
    public void setTablename(String tablename) {
        this.tablename = tablename;
    }
    
    /**
     * обновление заданного поля таблицы базы данных
     * @param fieldname обновлеяемое поле
     * @param fieldvalue значение для обновления
     * @return в случае успеха - true, иначе - false
     */
    public boolean updateEntity(String fieldname, Object fieldvalue){
        String sqlQuery = "UPDATE " + tablename + " B SET B." + fieldname +
                "=" + fieldvalue + " WHERE B.ID=" + id + ";";
        System.out.println("sql=" + sqlQuery);
        Runquery rq = new Runquery();
        return rq.updateFieldValue(sqlQuery);
    }
    
    /**
     * обновление заданного поля таблицы базы данных
     * @param Col номер обновляемого столбца (поля таблицы)
     * @param value значение для обновления
     * @return в случае успеха - true, иначе - false
     */
    public boolean updateEntity(int Col, Object value){
        return false;
    }
    
    /**
     * представляет данные полей сущности в виде массива
     * @param sqlQuery строка-запрос на выборку заданных полей сущности
     * @return одномерный массив данных полей сущности
     */
    public Object[] getFieldValues(String sqlQuery){
        Runquery rq = new Runquery();
        return rq.getEntity(sqlQuery);
        
    }
    
    /**
     * представляет данные полей сущности в виде массива
     * @return одномерный массив данных полей сущности
     */
    public Object[] toDataArray(){
        return getEntity();
    }
    
    
    /**
     * получает данные из указанной таблицы базы данных по заданному критерию
     * @return одномерный массив данных полей сущности
     */
    private Object[] getEntity(){
        String sqlQuery = "SELECT * FROM " + tablename + 
                " A WHERE A.ID=" + id + ";";// строка - запрос на выборку данных из таблицы
        
        Runquery rq = new Runquery();
        Object[] retval = rq.getEntity(sqlQuery);
        retval[0] = index;// первый элемент массива содержит номер сущности в наборе
        return retval;
    }
}
