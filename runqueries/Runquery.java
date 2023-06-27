/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package runqueries;

import abonentgaz.JDBCConnection;
import entities.TableEntity;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author operator
 */
public class Runquery {

    private String tableName;// имя таблицы базы данных в/из которой будут добаляться/удаляться данные
    private String[] columnName = null;
    private Class[] columnClass = null;
    
    public Runquery(String tableName) {
        this.tableName = tableName;
    }

    public Runquery() {
    }
    
    /**
     * 
     * @param sqlString строка - запрос на получение данных
     * @return набор строк из заданной таблицы
     */
    public List<TableEntity> getEntities(String sqlString){
        List<TableEntity> entities = new ArrayList<>();
        int index = 1;// инициализируем начальный номер списка
        try {
            // получаем набор данных
            ResultSet rs = JDBCConnection.getStat().executeQuery(sqlString);
            
            while(rs.next()){
                TableEntity e = new TableEntity(rs.getInt("ID"), index);// экземпляр сущности
                entities.add(e);// добавляем в список
                index++;// увеличиваем счётчик
            }
            return entities;
        } catch (SQLException ex) {
            showErrorMessage(ex);
            return null;
        }
    }
    
    /**
     *
     * @param sqlString строка запрос на выборку данных
     * @return список, содержащий полученные данные
     */
    public List<Object[]> getQueryEntities(String sqlString){
        try {
            List<Object[]> entities;
//            System.out.println("sqlstring = " + sqlString);
            
            // получаем результат выполнения инструкции
            Statement stat = JDBCConnection.getStat();
            entities = new ArrayList<>();
            // создаём набор данных
            ResultSet rs = stat.executeQuery(sqlString); // получаем результат выполнения

            ResultSetMetaData rsmd = rs.getMetaData();// получаем метаданные запроса
            int numCol = rsmd.getColumnCount();// количество столбцов запроса
//            System.out.println("numcol = " + numCol);
//            System.out.println("row = " + rs.first());
            // определяем размеры и заполняем массивы имён полей и типов данных
            columnClass = new Class[numCol + 1];
            columnName = new String[numCol + 1];
            columnName[0] = "№";
            columnClass[0] = Integer.class;
//            getDataClassValue(rsmd);
            for(int i = 1; i < numCol + 1; i++){
                columnName[i] = rsmd.getColumnLabel(i);
                columnClass[i] = getDataClassValue(rsmd.getColumnType(i));
//                System.out.println("Class - " + columnClass[i].getTypeName());
            }
            int index = 1;// инициируем начальный номер списка
//            System.out.println("Входим в цикл, набор " + rs.isClosed());
            while (rs.next()) {
//                System.out.println("Мы в цикле");
                Object[] e = new Object[numCol + 1];// экземпляр сущности
                // заполняем сущность данными полей
                e[0] = index;
                for(int i = 1; i < e.length; i++){
                    e[i] = rs.getString(i);
//                    System.out.println("e[i]= " + e[i]);
                }
                entities.add(e);// добавляем в список
                index++;// увеличиваем счётчик
            }
            
            
            return entities;
        } catch (SQLException ex) {
            showErrorMessage(ex);
            return null;
        }
        
    }
    
    public String[] getColumnName(){
        return columnName;
    }
    
    public Class[] getColumnClass(){
        return columnClass;
    }
    
    /**
     * 
     * @param fieldName строка с наименованиями полей таблицы, в которые
     * будут добавляться данные, разделённые запятой
     * @param fieldValue строка, содержащая знаки подстановки вместо значений, их
     * количество должно соответствовать размеру массива фактических параметров
     * @param param массив целочисленных параметров значений для добавления в таблицу
     * @return в случае успеха возвращает true иначе false
     */
    public boolean addEntity(String fieldName, String fieldValue, int[] param){
        boolean retval;
        String sqlQuery = "INSERT INTO " + tableName + "(" + fieldName + ") " +
                "VALUES(" + fieldValue + ");";
        int i = 0;
        try {
            // создаём подготовленную инструкцию для выполнения операции добавления записи
            PreparedStatement prst = JDBCConnection.getPrepstat(sqlQuery);
            if(prst != null){
                // если подготовленная инструкция создана,
                // задаём её параметры
                for(int j = 0; j < param.length; j++)
                    prst.setInt(j + 1, param[j]);
                i = prst.executeUpdate();// получаем результат выполнения
                prst.clearParameters();// очищаем параметры
                prst.close();// закрываем инструкцию
            }
        } catch (SQLException ex) {
            showErrorMessage(ex);
            return false;
        }
        retval = i > 0;
        return retval;
    }
    
    /**
     * 
     * @param fieldName строка с наименованиями полей таблицы, в которые
     * будут добавляться данные, разделённые запятой
     * @param param массив целочисленных параметров значений для добавления в таблицу
     * @param fieldValue строка, содержащая знаки подстановки вместо значений, их
     * количество должно соответствовать размеру массива фактических параметров
     * @return в случае успеха возвращает true иначе false
     */
    public boolean addEntity(String fieldName, String fieldValue, String[] param){
        boolean retval;
        String sqlQuery = "INSERT INTO " + tableName + "(" + fieldName + ") " +
                "VALUES(" + fieldValue + ");";
        System.out.print(sqlQuery);
        int i = 0;
        try {
            // создаём подготовленную инструкцию для выполнения операции добавления записи
            PreparedStatement prst = JDBCConnection.getPrepstat(sqlQuery);
            if(prst != null){
                // если подготовленная инструкция создана,
                // задаём её параметры
                for(int j = 0; j < param.length; j++)
                    prst.setString(j + 1, param[j]);
                i = prst.executeUpdate();// получаем результат выполнения
                prst.clearParameters();// очищаем параметры
                prst.close();// закрываем инструкцию
            }
        } catch (SQLException ex) {
            showErrorMessage(ex);
            return false;
        }
        retval = i > 0;
        return retval;
    }
    
    /**
     * 
     * @param fieldName строка с наименованиями полей таблицы, в которые
     * будут добавляться данные, разделённые запятой
     * @param fieldValue строка, содержащая знаки подстановки вместо значений (их
     * количество должно соответствовать размеру массива фактических параметров)
     * @param classValue массив, содержащий типы данных параметров
     * @param param массив значений параметров для добавления в таблицу
     * @return в случае успеха возвращает TRUE иначе FALSE
     */
    public boolean addEntity(String fieldName, String fieldValue, Class[] classValue, Object[] param){
        boolean retval;
        String sqlQuery = "INSERT INTO " + tableName + "(" + fieldName + ") " +
                "VALUES(" + fieldValue + ");";
        System.out.print(sqlQuery);
        int i = 0;
        try {
            // создаём подготовленную инструкцию для выполнения операции добавления записи
            PreparedStatement prst = JDBCConnection.getPrepstat(sqlQuery);
            if(prst != null){
                
                // если подготовленная инструкция создана,
                // задаём её параметры
                for(int j = 0; j < param.length; j++){
                    if(classValue[j] == String.class){
                        prst.setString(j + 1, (String) param[j]);
                    } else if(classValue[j] == Integer.class){
                        prst.setInt(j + 1, (int) param[j]);
                    } else if(classValue[j] == Double.class){
                        prst.setDouble(j + 1, (double) param[j]);
                    } else if(classValue[j] == Float.class){
                        prst.setFloat(j + 1, (float) param[j]);
                    } else if(classValue[j] == Long.class){
                        prst.setLong(j + 1, (long) param[j]);
                    } else if(classValue[j] == Short.class){
                        prst.setShort(j + 1, (short) param[j]);
                    } else if(classValue[j] == Boolean.class){
                        prst.setBoolean(j + 1, (boolean) param[j]);
                    } else if(classValue[j] == Time.class){
                        prst.setTime(j + 1, (Time) param[j]);
                    } else if(classValue[j] == Date.class){
                        prst.setDate(j + 1, (Date) param[j]);
                    } else if(classValue[j] == Timestamp.class){
                        prst.setTimestamp(j + 1, (Timestamp) param[j]);
                    } else if(classValue[j] == BigDecimal.class){
                        prst.setBigDecimal(j + 1, (BigDecimal) param[j]);
                    } else{
                        prst.setObject(j + 1, param[j]);
                    }
                    
                }
                
                i = prst.executeUpdate();// получаем результат выполнения
                prst.clearParameters();// очищаем параметры
                prst.close();// закрываем инструкцию
            }
        } catch (SQLException ex) {
            showErrorMessage(ex);
            return false;
        }
        retval = i > 0;
        return retval;
    }
    
    /**
     * 
     * @param identifier идентификатор удаляемой записи
     * @return в случае успеха возвращает true иначе false
     */
    public boolean deleteEntity(int identifier){
        boolean retval;
        String sqlQuery = "DELETE FROM " + tableName + 
                " A WHERE A.ID=" + identifier + ";";
        int i;
        try {
            i = JDBCConnection.getStat().executeUpdate(sqlQuery);// выплняем запрос на удаление
        } catch (SQLException ex) {
            showErrorMessage(ex);
            return false;
        }
        retval = i > 0;// проверяем результат выполнения запроса
        return retval;
    }
    
    /**
     * 
     * @param sqlQuery строка - запрос на выборку данных
     * @param fieldNumber номер поля в запросе, значение которого возвращается
     * @return значение указанного поля таблицы
     */
    public Object getFieldValue(String sqlQuery, int fieldNumber){
        Object retval = null;
        try {
            ResultSet rs = JDBCConnection.getStat().executeQuery(sqlQuery);
            if(rs.next())
                retval = rs.getString(fieldNumber);
        } catch (SQLException ex) {
            showErrorMessage(ex);
        }
        return retval;
    }
    
    /**
     * 
     * @param sqlQuery строка - запрос на обновление данных
     * @return в случае успеха true, иначе возвращает false
     */
    public boolean updateFieldValue(String sqlQuery) {
        boolean retval;
        try {
            retval = JDBCConnection.getStat().execute(sqlQuery);
        } catch (SQLException ex) {
            showErrorMessage(ex);
            return false;
        }
        return retval;
    }
    
    public Object[] getEntity(String sqlQuery){
//        System.out.println(sqlQuery);
        Object[] retval;
        try {
            
            ResultSet rs = JDBCConnection.getStat().executeQuery(sqlQuery);
            // метаданные набора
            ResultSetMetaData rsmd = rs.getMetaData();
            retval = new Object[rsmd.getColumnCount()];// определяем размер массива
//            System.out.println("lenght =" + retval.length);
            if(rs.next() == true){
                // вместо кода в первом столбце будет выводиться порядковый
                // номер сущности
                getDataTypesValue(retval, rs, rsmd);
//                for(int i = 0; i < retval.length; i++){
//                    // определяем тип данных поля
//                    switch(rsmd.getColumnType(i+1)){
//                        case Types.CHAR:
//                            retval[i] = rs.getString(i+1);
//                            break;
//                        case Types.INTEGER:
//                            retval[i] = rs.getInt(i+1);
//                            break;
//                        case Types.DOUBLE:
//                            retval[i] = rs.getDouble(i+1);
//                            break;
//                        case Types.FLOAT:
//                            retval[i] = rs.getFloat(i+1);
//                            break;
//                        case Types.BIGINT:
//                            retval[i] = rs.getLong(i+1);
//                            break;
//                        case Types.SMALLINT:
//                            retval[i] = rs.getShort(i+1);
//                            break;
//                        case Types.BOOLEAN:
//                            retval[i] = rs.getBoolean(i+1);
//                            break;
//                        case Types.TIME:
//                            retval[i] = rs.getTime(i+1);
//                            break;
//                        case Types.DATE:
//                            retval[i] = rs.getDate(i+1);
//                            break;
//                        case Types.TIMESTAMP:
//                            retval[i] = rs.getTimestamp(i+1);
//                            break;
//                        case Types.DECIMAL:
//                            retval[i] = rs.getBigDecimal(i+1);
//                            break;
//                        case Types.NUMERIC:
//                            retval[i] = rs.getBigDecimal(i+1);
//                            break;
//                        default:
//                            retval[i] = rs.getString(i+1);
//                            break;
//                    }
////                    System.out.println("retval[" + i + "]=" + retval[i].toString());
//                }
            }
            return retval;
        } catch (SQLException ex) {
            showErrorMessage(ex);
            return null;
        }
//        return rs;
    }
    
    public void getColumns(String sqlQuery){
        try {
            ResultSet rs = JDBCConnection.getStat().executeQuery(sqlQuery);
            // метаданные набора
            ResultSetMetaData rsmd = rs.getMetaData();
            
            // задаём размеры массивов
            columnClass = new Class[rsmd.getColumnCount()];
            columnName = new String[rsmd.getColumnCount()];
            
            // получаем наименования и типы данных столбцов
            for(int i = 0; i < columnClass.length; i++){
                columnName[i] = rsmd.getColumnName(i+1);
                columnClass[i] = getDataClassValue(rsmd.getColumnType(i + 1));
            }
        } catch (SQLException ex) {
            showErrorMessage(ex);
        }
            
    }
    
    
    private void getDataTypesValue(Object[] retval, ResultSet rs, 
            ResultSetMetaData rsmd) throws SQLException{
        for(int i = 0; i < retval.length; i++){
            // определяем тип данных поля
            switch(rsmd.getColumnType(i+1)){
                case Types.CHAR:
                case Types.NVARCHAR:
                    retval[i] = rs.getString(i+1);
                    break;
                case Types.INTEGER:
                    retval[i] = rs.getInt(i+1);
                    break;
                case Types.DOUBLE:
                case Types.FLOAT:
                    retval[i] = rs.getDouble(i+1);
                    break;
                case Types.REAL:
                    retval[i] = rs.getFloat(i+1);
                    break;
                case Types.BIGINT:
                    retval[i] = rs.getLong(i+1);
                    break;
                case Types.SMALLINT:
                    retval[i] = rs.getShort(i+1);
                    break;
                case Types.BOOLEAN:
                    retval[i] = rs.getBoolean(i+1);
                    break;
                case Types.TIME:
                    retval[i] = rs.getTime(i+1);
                    break;
                case Types.DATE:
                    retval[i] = rs.getDate(i+1);
                    break;
                case Types.TIMESTAMP:
                    retval[i] = rs.getTimestamp(i+1);
                    break;
                case Types.DECIMAL:
                case Types.NUMERIC:
                    retval[i] = rs.getBigDecimal(i+1);
                    break;
                default:
                    retval[i] = rs.getString(i+1);
                    break;
            }
//                    System.out.println("retval[" + i + "]=" + retval[i].toString());
        }
    }
    
    
    private Class getDataClassValue(int type) throws SQLException{
        // получаем наименования и типы данных столбцов
        switch(type){
            case Types.CHAR:
            case Types.NVARCHAR:
                return String.class;
            case Types.INTEGER:
                return Integer.class;
            case Types.DOUBLE:
            case Types.FLOAT:
                return Double.class;
            case Types.REAL:
                return Float.class;
            case Types.BIGINT:
                return Long.class;
            case Types.SMALLINT:
                return Short.class;
            case Types.BOOLEAN:
                return Boolean.class;
            case Types.TIME:
                return Time.class;
            case Types.DATE:
                return String.class;
            case Types.TIMESTAMP:
                return Timestamp.class;
            case Types.DECIMAL:
            case Types.NUMERIC:
                return BigDecimal.class;
            default:
                return String.class;
        }
        
    }
    
    private void showErrorMessage(SQLException ex){
        JOptionPane.showMessageDialog(null, "Произошли ошибки во время многошаговой операции!\n\r" +
                "Ошибка: " + ex.getLocalizedMessage() + "\n\r" +
                "Код ошибки: " + ex.getErrorCode(), "AbonentGaz", JOptionPane.ERROR_MESSAGE);
        Logger.getLogger(Runquery.class.getName()).log(Level.SEVERE, null, ex);
    }
    
}
