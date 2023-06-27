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

    private String sqlQuery;// строка - запрос на выборку данных
    private Object[][] content;// массив данных, полученый в результате выполнения запроса
    private String[] columnName;// наименование столбцов (полей) данных
    private Class[] columnClass;// наименование типов данных
    private Object[] criteria;// массив критериев отбора записей

    public QueriesDao() {
//        criteria = null;
    }

    /**
     * Задаёт строку-запрос на выборку данных
     * @param sqlQuery the sqlQuery to set
     */
    public void setSqlQuery(String sqlQuery) {
        this.sqlQuery = sqlQuery;
    }

    /**
     * Возвращает двухмерный массив данных, полученный в результате запроса
     * @return the content массив данных
     */
    public Object[][] getContent() {
        return content;
    }

    /**
     * Возвращает наименование полей данных в виде массива, которое может использоваться для заголовков таблицы
     * @return the columnName массив наименований полей
     */
    public String[] getColumnName() {
        return columnName;
    }

    /**
     * Возвращает наименование типов данных в виде массива
     * @return the columnClass массив типов данных
     */
    public Class[] getColumnClass() {
        return columnClass;
    }

    /**
     * Задаёт массив критериев отбора данных для выполнения запроса
     * @param criteria the criteria to set
     */
    public void setCriteria(Object[] criteria) {
        this.criteria = criteria;
    }
        
    /**
     * Выполняет запрос к базе данных, получает данные, определяет наименование и типы данны
     * @return true если данные получены, в противном случае возвращает false
     */
    public boolean getEntities(){
        runqueries.Runquery rq = new Runquery();// класс для выполнения запроса
        String query = buildSQLString();// получаем строку с вставленным критерием
        List<Object[]> entities = rq.getQueryEntities(query);// получаем данные
        if(entities.size() > 0){
            // определяем размер массива данных
            content = new Object[entities.size()][];

            // заполняем его данными
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
    * заменяет в строке-запросе символ ? заданным критерием отбора
    * @return преобразованную строку с вставленным критерием отбора
    */
    private String buildSQLString(){
        // в запросе на выборку вместо ? подставляем критерий отбора
        int startPosition=0;// наачальная позиция поиска вхождения
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
            startPosition = endPosition + 1;// увеличиваем позицию поиска на единицу
        }
        // собираем строку - запрос
        sqlString = sqlString + 
                sqlQuery.substring(startPosition, sqlQuery.length());
        return sqlString;// возвращаем строку, полученную в результате преобразования
    }
}
