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
    private int idCriteria = 0;// ��������, �� �������� ���������� ������� ������
    private List<TableEntity> entities = new ArrayList<>();// ������ ���������� ���������
    private String tablename = null;// ��� ������� �� ������� ���������� ������
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
        // �������� ������������� ������� (���)
        int ident = entities.get(ID).getId();
        return deleteEntity(ident);
    }

    /**
     * ���������� ������ � ��������� ���������������
     * @param ID ������������� �������
     * @return ������ � ��������� ���������������
     */
    public TableEntity getItem(int ID) {
        entities.get(ID).setTablename(tablename);
        return entities.get(ID);
    }

    /**
     * ���������� ���������� ������, ������������ � �������
     * @return ������ �������
     */
    public int getCount() {
        return entities.size();
    }

    /**
     * ���������� ������������ ����� (�������) ���������� ������
     * @return ������, ���������� ������������ ������� ������
     */
    public Class[] getColumnClass() {
        return columnClass;
    }

    /**
     * ���������� ������������ �������� ������, ���������� � ���������� �������,
     * ������� ������������ ��� ������������ �������� �������
     * @return ������, ���������� ������������ ��������
     */
    public String[] getColumnName() {
        return columnName;
    }

    /**
     * ��������� ����� ������ � ��������� ������� ���� ������
     * @param fieldName - ������ ����� ��� ���������� ������
     * @param escField - ������ ��������-����������� �������� (?), ����� ���������� �����
     * @param parameters - ������ ������������� ���������� ��� ����������, ����� ���������� �����
     * @return � ������ ������ - true, � ��������� ������ ���������� false
     */
    public boolean addItem(String fieldName, String escField, int[] parameters) {
        return addEntity(fieldName, escField, parameters);
    }

    /**
     * ��������� ����� ������ � ��������� ������� ���� ������
     * @param fieldName - ������ ����� ��� ���������� ������
     * @param escField - ������ ��������-����������� �������� (?), ����� ���������� �����
     * @param parameters - ������ ��������� ���������� ��� ����������, ����� ���������� �����
     * @return � ������ ������ - true, � ��������� ������ ���������� false 
     */
    public boolean addItem(String fieldName, String escField, String[] parameters) {
        return addEntity(fieldName, escField, parameters);
    }
    
    public void getEntities(){
        String sqlQuery;// ������ �� ������� ������
        if(idCriterias == null || criterias == null){
            // ��������� ������ �� ������� ������������ � �������� ��������� ������
            if(idCriteria == 0 || criteria == null){
                // ���� �������� ������ �� �����, �� �������� ��� ������
                sqlQuery = "SELECT A.ID FROM " + tablename + " A ORDER BY A.ID ASC;";
            } else {
                sqlQuery = "SELECT A.ID FROM " + tablename + " A WHERE A." +
                    criteria + "=" + idCriteria + " ORDER BY A.ID ASC;";// ������ �� ������� ������
            }
        } else {
            String filter = "";// ������, ���������� ���������� ������ ������ �������
            // � ����� ��������� ������ ������
            for(int i = 0; i < criterias.length; i++){
                filter = filter + "A." + criterias[i] + "=" + idCriterias[i] +
                        " AND ";
            }
            // ����������� ���������� ������ �� 5 ��������
            String filterValue = filter.substring(0, filter.length() - 5);
            sqlQuery = "SELECT A.ID FROM " + tablename + " A WHERE " +
                    filterValue + " ORDER BY A.ID ASC;";// ������ �� ������� ������
        }
        entities.clear();// ������� ���������
        Runquery rq = new Runquery();
        entities = rq.getEntities(sqlQuery);
        
        // ��������� ���������� ������
        if(entities.size() > 0){
            // �������� ����� ����� � ���� ������
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
     * ��������� ����� ������
     */
    private boolean addEntity(String fieldName, String escField, int[] param){
        boolean retval;
        Runquery rq = new Runquery(tablename);
        retval = rq.addEntity(fieldName, escField, param);
        // ��������� ��������� ���� �������� �������
        if(retval == true)
            getEntities();
        return retval;
    }
    
    /**
     * ��������� ����� ������
     */
    private boolean addEntity(String fieldName, String escField, String[] param){
        boolean retval;
        Runquery rq = new Runquery(tablename);
        retval = rq.addEntity(fieldName, escField, param);
        // ��������� ��������� ���� �������� �������
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
