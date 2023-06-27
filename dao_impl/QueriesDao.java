/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

import java.util.List;
import runqueries.Runquery;

/**
 *
 * @author operator
 */
public class QueriesDao {

    private String sqlQuery;// ������ - ������ �� ������� ������
    private Object[][] content;// ������ ������, ��������� � ���������� ���������� �������
    private String[] columnName;// ������������ �������� (�����) ������
    private Class[] columnClass;// ������������ ����� ������
    private Object[] criteria;// ������ ��������� ������ �������

    public QueriesDao() {
//        criteria = null;
    }

    /**
     * ����� ������-������ �� ������� ������
     * @param sqlQuery the sqlQuery to set
     */
    public void setSqlQuery(String sqlQuery) {
        this.sqlQuery = sqlQuery;
    }

    /**
     * ���������� ���������� ������ ������, ���������� � ���������� �������
     * @return the content ������ ������
     */
    public Object[][] getContent() {
        return content;
    }

    /**
     * ���������� ������������ ����� ������ � ���� �������, ������� ����� �������������� ��� ���������� �������
     * @return the columnName ������ ������������ �����
     */
    public String[] getColumnName() {
        return columnName;
    }

    /**
     * ���������� ������������ ����� ������ � ���� �������
     * @return the columnClass ������ ����� ������
     */
    public Class[] getColumnClass() {
        return columnClass;
    }

    /**
     * ����� ������ ��������� ������ ������ ��� ���������� �������
     * @param criteria the criteria to set
     */
    public void setCriteria(Object[] criteria) {
        this.criteria = criteria;
    }
        
    /**
     * ��������� ������ � ���� ������, �������� ������, ���������� ������������ � ���� �����
     * @return true ���� ������ ��������, � ��������� ������ ���������� false
     */
    public boolean getEntities(){
        runqueries.Runquery rq = new Runquery();// ����� ��� ���������� �������
        String query = buildSQLString();// �������� ������ � ����������� ���������
        List<Object[]> entities = rq.getQueryEntities(query);// �������� ������
        if(entities.size() > 0){
            // ���������� ������ ������� ������
            content = new Object[entities.size()][];

            // ��������� ��� �������
            for(int i = 0; i < content.length; i++){
                Object[] e = entities.get(i);
                content[i] = e;
            }
            columnClass = rq.getColumnClass();
            columnName = rq.getColumnName();
            return true;

        } else {
            return false;
        }
    }
    
    /**
    * �������� � ������-������� ������ ? �������� ��������� ������
    * @return ��������������� ������ � ����������� ��������� ������
    */
    private String buildSQLString(){
        // � ������� �� ������� ������ ? ����������� �������� ������
        int startPosition=0;// ���������� ������� ������ ���������
        int endPosition;
        String sqlString = "";
        
        for (Object criteria1 : criteria) {
            endPosition = sqlQuery.indexOf("?", startPosition);
            //            System.out.println("endposition= " + endPosition);
            if (endPosition != 0){
                sqlString = sqlString +
                        sqlQuery.substring(startPosition,
                                endPosition) + criteria1;
            }
            startPosition = endPosition + 1;// ����������� ������� ������ �� �������
        }
        // �������� ������ - ������
        sqlString = sqlString + 
                sqlQuery.substring(startPosition, sqlQuery.length());
        return sqlString;// ���������� ������, ���������� � ���������� ��������������
    }
}
