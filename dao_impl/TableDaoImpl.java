/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

import entities.TableEntity;
import java.util.ArrayList;
import java.util.List;
import runqueries.Runquery;

/**
 *
 * @author Sergii.Tushinskyi
 */
public class TableDaoImpl {
    private int idCriteria = 0;// критерий, по которому происходит выборка данных
    private List<TableEntity> entities = new ArrayList<>();// список выбараемых сущностей
    private String tablename = null;// имя таблицы из которой выбираются данные
    private String criteria = null;//
    private String[] columnName;
    private Class[] columnClass;
    private int[] idCriterias = null;
    private String[] criterias = null;
    
    public TableDaoImpl() {
    }
    
    

    public TableDaoImpl(int idCriteria) {
        this.idCriteria = idCriteria;
    }

    public TableDaoImpl(String tablename) {
        this.tablename = tablename;
    }

    public TableDaoImpl(String[] criterias, int[] idCriterias) {
        this.criterias = criterias;
        this.idCriterias = idCriterias;
    }
    
    public List<TableEntity> getAllItems() {
        return entities;
    }

    public boolean deleteItem(int ID) {
        // получаем идентификатор объекта (код)
        int ident = entities.get(ID).getId();
        return deleteEntity(ident);
    }

    /**
     * Возвращает объект с указанным идентификатором
     * @param ID идетрификатор объекта
     * @return объект с указанным идентификатором
     */
    public TableEntity getItem(int ID) {
        entities.get(ID).setTablename(tablename);
        return entities.get(ID);
    }

    /**
     * Возвращает количество данных, содержащихся в массиве
     * @return размер массива
     */
    public int getCount() {
        return entities.size();
    }

    /**
     * Возвращает наименование типов (классов) полученных данных
     * @return массив, содержащий наименования классов данных
     */
    public Class[] getColumnClass() {
        return columnClass;
    }

    /**
     * Возвращает наименования столбцов данных, полученных в результате запроса,
     * которые используются для наименований столбцов таблицы
     * @return массив, содержащий наименование столбцов
     */
    public String[] getColumnName() {
        return columnName;
    }

    /**
     * добавляет новую запись в выбранную таблицу базы данных
     * @param fieldName - список полей для добавления записи
     * @param escField - список символов-заменителей значений (?), равен количеству полей
     * @param parameters - массив целочисленных параметров для добавления, равен количеству полей
     * @return в случае успеха - true, в противном случае возвращает false
     */
    public boolean addItem(String fieldName, String escField, int[] parameters) {
        return addEntity(fieldName, escField, parameters);
    }

    /**
     * добавляет новую запись в выбранную таблицу базы данных
     * @param fieldName - список полей для добавления записи
     * @param escField - список символов-заменителей значений (?), равен количеству полей
     * @param parameters - массив строковых параметров для добавления, равен количеству полей
     * @return в случае успеха - true, в противном случае возвращает false 
     */
    public boolean addItem(String fieldName, String escField, String[] parameters) {
        return addEntity(fieldName, escField, parameters);
    }
    
    public void getEntities(){
        String sqlQuery;// запрос на выборку данных
        if(idCriterias == null || criterias == null){
            // проверяем заданы ли массивы наименований и значений критериев отбора
            if(idCriteria == 0 || criteria == null){
                // если критерий отбора не задан, то выбираем все записи
                sqlQuery = "SELECT A.ID FROM " + tablename + " A ORDER BY A.ID ASC;";
            } else {
                sqlQuery = "SELECT A.ID FROM " + tablename + " A WHERE A." +
                    criteria + "=" + idCriteria + " ORDER BY A.ID ASC;";// запрос на выборку данных
            }
        } else {
            String filter = "";// строка, содержащая соединённый фильтр отбора записей
            // в цикле соединяем строку фильтр
            for(int i = 0; i < criterias.length; i++){
                filter = filter + "A." + criterias[i] + "=" + idCriterias[i] +
                        " AND ";
            }
            // укорачиваем полученную строку на 5 символов
            String filterValue = filter.substring(0, filter.length() - 5);
            sqlQuery = "SELECT A.ID FROM " + tablename + " A WHERE " +
                    filterValue + " ORDER BY A.ID ASC;";// запрос на выборку данных
        }
        entities.clear();// очищаем коллекцию
        Runquery rq = new Runquery();
        entities = rq.getEntities(sqlQuery);
        
        // проверяем полученные данные
        if(entities.size() > 0){
            // получаем имена полей и типы данных
            String sqlString = "SELECT * FROM " + tablename + " A WHERE A.ID=" + 
                    entities.get(0).getId() + ";";
            rq.getColumns(sqlString);
            columnClass = rq.getColumnClass();
            columnName = rq.getColumnName();
        }
    }

    private boolean deleteEntity(int identifier){
        boolean retval;
        Runquery rq = new Runquery(tablename);
        retval = rq.deleteEntity(identifier);
        if(retval == true)
            getEntities();
        return retval;
    }

    /**
     * добавляет новую запись
     */
    private boolean addEntity(String fieldName, String escField, int[] param){
        boolean retval;
        Runquery rq = new Runquery(tablename);
        retval = rq.addEntity(fieldName, escField, param);
        // обновляем коллекцию если операция успешна
        if(retval == true)
            getEntities();
        return retval;
    }
    
    /**
     * добавляет новую запись
     */
    private boolean addEntity(String fieldName, String escField, String[] param){
        boolean retval;
        Runquery rq = new Runquery(tablename);
        retval = rq.addEntity(fieldName, escField, param);
        // обновляем коллекцию если операция успешна
        if(retval == true)
            getEntities();
        return retval;
    }

    /**
     * @return the idCriteria
     */
    public int getIdCriteria() {
        return idCriteria;
    }

    /**
     * @param idCriteria the idCriteria to set
     */
    public void setIdCriteria(int idCriteria) {
        this.idCriteria = idCriteria;
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
     * @return the criteria
     */
    public String getCriteria() {
        return criteria;
    }

    /**
     * @param criteria the criteria to set
     */
    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public void setCriterias(String[] criterias) {
        this.criterias = criterias;
    }

    public void setIdCriterias(int[] idCriterias) {
        this.idCriterias = idCriterias;
    }
    
    

}
