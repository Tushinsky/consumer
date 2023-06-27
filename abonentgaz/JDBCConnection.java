/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abonentgaz;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCConnection {
    /**
     * @return the database connection
     * @throws FileNotFoundException
     * @throws IOException
     * @throws SQLException 
     */
    private static Statement stat;
    private static Connection conn;
    private static String driver;
    private static String url;
    private static String user;
    private static String password;
    private DatabaseMetaData metadata;
    private Object[] listTable;// список таблиц базы данных
    private String tableName;// имя выбранной таблицы
    private Object[] listColumnTable;// список полей выбранной таблицы
    private Class[] columnClass;//типы данных
    
    public JDBCConnection(String driver, String url, String user, String password) throws FileNotFoundException, 
            IOException, SQLException, ClassNotFoundException {
        JDBCConnection.driver=driver;
        JDBCConnection.url=url;
        JDBCConnection.user=user;
        JDBCConnection.password=password;
        if (driver != null)
            Class.forName(driver);
//        System.setProperty("jdbc.driver", driver);
        try {
        conn = DriverManager.getConnection(url, user, password);
        
        if (conn != null) stat= getConn().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_READ_ONLY);
        } finally {}
    }
    
    public JDBCConnection() throws FileNotFoundException, 
            IOException, SQLException, ClassNotFoundException {
        if (driver != null)
            Class.forName(driver);
//            System.setProperty("jdbc.driver", driver);
        conn = DriverManager.getConnection(url, user, password);
        
        if (conn != null) stat= getConn().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_READ_ONLY);

    }

    /**
     * @return the stat
     */
    public static Statement getStat() {
        return stat;
    }

    /**
     * @return the conn
     */
    public static Connection getConn() {
        return conn;
    }
    
    public static PreparedStatement getPrepstat(String sqlQuery){
        try {
            return getConn().prepareStatement(sqlQuery);
        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    /**
     * 
     * @return true если соединение закрыто, иначе false
     * @throws SQLException 
     */
    public boolean isClosedConn() throws SQLException{
        return conn.isClosed();
    }
    
    /**
     * @param sqlString строка-запрос с параметрами
     * @param param массив параметров
     * @return набор данных, полученный в результате выполнения запроса
     * @throws SQLException 
     */
    public ResultSet ExecuteQuery(String sqlString, int[] param) throws SQLException{
//        PreparedStatement prepStat= conn.prepareStatement(sqlString);
//        пїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ-пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ ? пїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ
//        System.out.println(sqlString);
//        System.out.println("пїЅпїЅпїЅпїЅпїЅ= " + sqlString.length());
        int startPosition=0;//пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ
        int endPosition;
        String sqlQuery = "";
        for (int i=0; i < param.length; i++){
            endPosition = sqlString.indexOf("?", startPosition);
//            System.out.println("endposition= " + endPosition);
            if (endPosition!=0){
                sqlQuery = sqlQuery + 
                        sqlString.substring(startPosition, 
                        endPosition) + param[i];
                startPosition = endPosition + 1;//пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅ 1
//                System.out.println("startposition= " + startPosition);
            }
        }
        //пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ
        sqlQuery = sqlQuery + 
                sqlString.substring(startPosition, sqlString.length());
//        System.out.println(sqlQuery);
//        return prepStat.executeQuery(sqlString);
        return getStat().executeQuery(sqlQuery);
    }
    
    public ResultSet ExecuteQuery(String sqlString) throws SQLException{
//        System.out.println(sqlString);
        return getStat().executeQuery(sqlString);
    }
    
    public int ExecuteUpdate(String sqlString, int[] param) throws SQLException{
        // определяем позицию, в которой находится знак вопроса
        int startPosition=0;// начальная позиция поиска
        int endPosition;
        String sqlQuery = "";
        for (int i=0; i < param.length; i++){
            endPosition = sqlString.indexOf("?", startPosition);
            if (endPosition!=0){
                sqlQuery = sqlQuery + 
                        sqlString.substring(startPosition, 
                        endPosition) + param[i];
                startPosition = endPosition + 1;// увеличиваем позицию поиска
            }
        }
        sqlQuery = sqlQuery + 
                sqlString.substring(startPosition, sqlString.length());
        System.out.println(sqlQuery);
        return getStat().executeUpdate(sqlQuery);
    }
    
    public void CloseConnection() throws SQLException{
        
        if (conn != null) {
            stat = null;
            conn.close();
        }
        conn = null;
    }

    public int ExecuteUpdate(String sqlString, String[] param) throws SQLException {
        //пїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ-пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ ? пїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ
        int startPosition=0;//пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ
        int endPosition;
        String sqlQuery = "";
        for (int i=0; i < param.length; i++){
            endPosition = sqlString.indexOf("?", startPosition);
            if (endPosition!=0){
                sqlQuery = sqlQuery + 
                        sqlString.substring(startPosition, 
                        endPosition) + param[i];
                startPosition = endPosition + 1;//пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅ 1
            }
        }
        sqlQuery = sqlQuery + sqlString.substring(startPosition, 
                sqlString.length());
//        System.out.println(sqlQuery);
        return getStat().executeUpdate(sqlQuery);
    }
     
    public boolean ExecuteQuery(String sqlString, String[] param) throws SQLException{
//        PreparedStatement prepStat= conn.prepareStatement(sqlString);
//        пїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ-пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ ? пїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ
        System.out.println(sqlString);
//        System.out.println("пїЅпїЅпїЅпїЅпїЅ= " + sqlString.length());
        int startPosition=0;//пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ
        int endPosition;
        String sqlQuery = "";
        for (String param1 : param) {
            endPosition = sqlString.indexOf("?", startPosition);
//            System.out.println("endposition= " + endPosition);
            if (endPosition!=0) {
                sqlQuery = sqlQuery + 
                        sqlString.substring(startPosition,
                                endPosition) + param1;
                startPosition = endPosition + 1;//пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅ 1
//                System.out.println("startposition= " + startPosition);
            }
        }
        //пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ
        sqlQuery = sqlQuery + 
                sqlString.substring(startPosition, sqlString.length());
//        System.out.println(sqlQuery);
        //        return prepStat.executeQuery(sqlString);
        return getStat().execute(sqlQuery);
    }
    
    public int ExecuteUpdate(String sqlString) throws SQLException {
        return getStat().executeUpdate(sqlString);
    }

    /**
     * @return the metadata
     */
    public DatabaseMetaData getMetadata() {
        try {
            metadata = conn.getMetaData();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return metadata;
    }

    /**
     * @return the listTable
     */
    public Object[] getListTable() {
        try {
            metadata = conn.getMetaData();
            listTable =scanRS(metadata.getTables("TABLE_CATALOG",
                "TABLE_SCHEM", "%", null), "TABLE_NAME");
        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listTable;
    }

    /**
     * @param tableName
     * @return the listColumnTable
     */
    public Object[] getListColumnTable(String tableName) {
        try {
            this.tableName = tableName;
            ResultSet rs = ExecuteQuery("SELECT * FROM " +
                    tableName + ";");
            listColumnTable = scanRS(rs, null);
        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listColumnTable;
    }
    
    /**
     * @param colname наименование столбца в таблице
     * @param value значение, которое необходимо привести к заданному типу данных
     * @return значение, приведённое к нужному типу данных
     */
    public String getCellValue(String colname, String value){
        String cellvalue;
        cellvalue = upgradeCellValue(colname, value);
        return cellvalue;
    }
    
    private Object[] scanRS(ResultSet rs, String columnName)throws SQLException{
        ArrayList list = new ArrayList();
        if(rs != null) {
            int i;
            // получаем данные набора
            ResultSetMetaData rsmd = rs.getMetaData();
            // заголовки столбцов
            // перебираем строки набора
            if(columnName != null){
                // перечень таблиц
                while(rs.next())
                    list.add(rs.getString(columnName));
            } else{
                // перечень полей выбранной таблицы
                int numCol = rsmd.getColumnCount();
                ArrayList colTypelist = new ArrayList();//массив типов данных
        //             заголовки столбцов
                for (int j = 1; j <= numCol; j++){
                    list.add(rsmd.getColumnName(j));
                    int dbType = rsmd.getColumnType(j);// получаем тип данных столбца
                    getColumnType(colTypelist, dbType);// заносим его в массив
                }
                columnClass = new Class[colTypelist.size()];
                colTypelist.toArray(columnClass);
            }
        } else {
            System.out.println("No data returned ");
        }
        return list.toArray();
    }
    
    private void getColumnType(ArrayList list, int columnType) {
        switch (columnType){
            case Types.CHAR:
                list.add(String.class);
                break;
            case Types.INTEGER:
                list.add(Integer.class);
                break;
            case Types.DOUBLE:
                list.add(Double.class);
                break;
            case Types.FLOAT:
                list.add(Float.class);
                break;
            case Types.BIGINT:
                list.add(Long.class);
                break;
            case Types.SMALLINT:
                list.add(Short.class);
                break;
            case Types.BOOLEAN:
                list.add(Boolean.class);
                break;
            case Types.TIME:
                list.add(Time.class);
                break;
            default:
                list.add(String.class);
                break;
            }
    }

    /**
     * @return the columnClass
     */
    public Class[] getColumnClass() {
        return columnClass;
    }
    
    /**
     * @param colname наименование столбца в таблице
     * @param cellvalue преобразуемое значение
     * @return значение, приведённое к нужному типу данных
     */
    private String upgradeCellValue(String colname, String cellvalue){
        String retValue;
        
        // определяем индекс столбца с заданным именем в таблице
        int index = 0;
        for(int i = 0; i < listColumnTable.length; i++){
            if(listColumnTable[i].equals(colname)){
                // если имена совппдают
                index = i;// запоминаем индекс столбца
                break;// прерываем цикл
            }
        }
        // определяем тип данных по индексу и проводим преобразование
        Class cls = columnClass[index];
        if(cls.equals(String.class) || cls.equals(Time.class)){
            retValue = "'" + cellvalue + "'";
        } else {
            retValue = cellvalue;
        }
        return retValue;
    }
}
