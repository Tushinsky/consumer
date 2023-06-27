/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import abonentgaz.JDBCConnection;
import java.sql.*;
import java.util.ArrayList;

/**
 * �������� ������ �� ���� ������, ���������� �� ���, ������������ �����
 * @author ������
 */
public class DBTableModel {
    
    private Object[][] content;//������ ������
    private String[] columnName;//������������ ��������
    private Class[] columnClass;//���� ������
    private ResultSet rs;
    /**
     * ����������� ��������� ������ ���������� � ��� ��������� ������� ���� ������
     * @param conn
     * @param tableName
     * @throws SQLException 
     */
    public DBTableModel (JDBCConnection conn,String tableName) throws SQLException{
        getTableContents(conn, tableName);
        
    }
    
    /**
     * ����������� ��������� ����� ������
     * @param resultset
     * @throws SQLException 
     */
    public DBTableModel (ResultSet resultset) throws SQLException{
        rs = resultset;
        dispResultset();
        
    }

    /**
     * @return the content
     */
    public Object[][] getContent() {
        return content;
    }

    /**
     * @return the columnName
     */
    public String[] getColumnName() {
        return columnName;
    }

    /**
     * @return the columnClass
     */
    public Class[] getColumnClass() {
        return columnClass;
    }

    /**
     * ���������� ������� �������� ������� �� ��������� �������
     * ���� ������
     */
    private void getTableContents(JDBCConnection conn,String tableName) 
            throws SQLException{
//        �������� ���������� ���������� (����� ���� ������)
        DatabaseMetaData meta = JDBCConnection.getConn().getMetaData();
        ResultSet resultset = meta.getColumns(null, null, tableName, null);
        ArrayList colNamelist = new ArrayList();// ������ ���� ��������
        ArrayList colTypelist = new ArrayList();// ������ ����� ������
        
        //�������� ���������� � ������ � ����� ������ �������� �������
        while (resultset.next()){
            colNamelist.add(resultset.getString("COLUMN_NAME"));
            int dbType = resultset.getInt("DATA_TYPE");
            getColumnType(colTypelist, dbType);
        }
        //���������� ������ ��������
        columnName = new String[colNamelist.size()];
        colNamelist.toArray(columnName);
        columnClass = new Class[colTypelist.size()];
        colTypelist.toArray(columnClass);
        //получаем данные
        Statement stmt = JDBCConnection.getConn().createStatement();
        rs = stmt.executeQuery("SELECT * FROM " + tableName);
        ArrayList rowList = new ArrayList();
        while (rs.next()) {
            ArrayList colList = new ArrayList();
            for (int i = 0; i < columnClass.length; i++) {
                Object cellValue = setCellValue(i);
                colList.add(cellValue);
            }
            Object[] cells = colList.toArray();
            rowList.add(cells);
        }
        content = new Object[rowList.size()][];
        for (int i = 0; i < content.length; i++) {
            content[i] = (Object[]) (Object) rowList.get(i);
        }
    }
    /**
     * 
     */
    private void dispResultset() throws SQLException {
        //�������� ������ � ����������� ������
        ResultSetMetaData rsmd= rs.getMetaData();
        
        //�������� ���������� �������� � ������
        int numcol = rsmd.getColumnCount();
        ArrayList colNamelist = new ArrayList();//������ ��� ����
        ArrayList colTypelist = new ArrayList();//������ ����� ������
        
        for (int i = 1; i <= numcol; i++) {
            colNamelist.add(rsmd.getColumnName(i));
            int dbType = rsmd.getColumnType(i);// �������� ��� ������ �������
            getColumnType(colTypelist, dbType);
	}
        columnName = new String[colNamelist.size()];
        colNamelist.toArray(columnName);
        columnClass = new Class[colTypelist.size()];
        colTypelist.toArray(columnClass);
        
	ArrayList rowList = new ArrayList();
        while (rs.next()) {
            ArrayList colList = new ArrayList();
            for (int i = 0; i < columnClass.length; i++) {
                Object cellValue = setCellValue(i);
                colList.add(cellValue);
            }
            Object[] cells = colList.toArray();
            rowList.add(cells);
        }
        content = new Object[rowList.size()][];
        for (int i = 0; i < content.length; i++) {
            content[i] = (Object[]) (Object) rowList.get(i);
        }
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
    
    private Object setCellValue(int i) throws SQLException {
        Object retValue = null;
        if (columnClass[i] == String.class)
            retValue = rs.getString(columnName[i]);
        else if (columnClass[i] == Integer.class)
            retValue = rs.getInt(columnName[i]);
        else if (columnClass[i] == Double.class)
            retValue = rs.getDouble(columnName[i]);
        else if (columnClass[i] == Float.class)
            retValue = rs.getFloat(columnName[i]);
        else if (columnClass[i] == Long.class)
            retValue = rs.getLong(columnName[i]);
        else if (columnClass[i] == Short.class)
            retValue = rs.getShort(columnName[i]);
        else if (columnClass[i] == Boolean.class)
            retValue = rs.getBoolean(columnName[i]);
        else if (columnClass[i] == Time.class)
            retValue = rs.getTime(columnName[i]);
        else System.out.println("��� ������ �� �������� " + 
                columnName[i]);
        return retValue;
    }
}
