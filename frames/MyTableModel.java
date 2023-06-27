/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import abonentgaz.JDBCConnection;
import java.math.BigDecimal;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.event.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author ������
 */
abstract class MyTableModel extends AbstractTableModel {
    /**
     * @return content
     */
    private Object[][] content;//������ ������
    private String[] columnName;//������������ ��������
    private Class[] columnClass;//���� ������
    private ResultSet rs;
    private Statement stmt;
    private JDBCConnection connection;// ��������� ���������� � ����� ������
    private JTable table;// ������� ���������� - �������
    private String tablename;// ��� ������� ���� ������ ��� �������� ����������
    private boolean change = false;// ���� ��������� ������ � �������
    private int[] colNoEditableList;// ������ ��������������� ��������
    private int[] rowNoEditableList;// ������ ��������������� �����
    
    /**
     * ����������� ��������� ������ ���������� � ��� ��������� ������� ���� ������
     * @param conn
     * @param tableName
     * @throws SQLException 
     */
    public MyTableModel (JDBCConnection conn,String tableName) throws SQLException{
        super();
        connection = conn;
        getData();
        super.addTableModelListener(new MyModelListener());
    }
    
    /**
     * ����������� ��������� ����� ������
     * @param resultset
     * @throws SQLException 
     */
    public MyTableModel (ResultSet resultset) throws SQLException{
        super();
        rs = resultset;
        getData();
        super.addTableModelListener(new MyModelListener());
    }
    
    public MyTableModel(Object[][] content, String[] columnName,
            Class[] columnClass){
        this.content = content;
        this.columnName = columnName;
        this.columnClass = columnClass;
//        addTableModelListener(new MyModelListener());
    }

    public MyTableModel(Object[][] content) {
        this.content = content;
    }
    
    
    
    public void CloseRS() throws SQLException{
        
//        ��������� ����� ������
        if (rs != null) rs.close();
        if (stmt != null) stmt.close();
    }
    
    @Override
    public int getRowCount() {
        return content.length;
    }
    @Override
    public String getColumnName (int columnIndex) {
        return columnName[columnIndex];
                
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        return columnClass[columnIndex];
    }
    
    @Override
    public int getColumnCount() {
        if (content.length == 0) {
            return 0;
        } else {
            return content[0].length;
        }
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return content[rowIndex][columnIndex];
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        boolean result = true;// ������������� ������������� ��������������� ������
        if(colNoEditableList != null){
            // ������ � ���������������� ��������� �����
            // ���������� ����� ������� � ������� ��������������� �������
            for (int i = 0; i < colNoEditableList.length; i++) {
                if (columnIndex == colNoEditableList[i]){
                    result = false;
                    break;
                }
            }

        } else {
            result = false;
        }
        
        return result;
    }
    
    
    
    /**
     * ���������� ������
     * @param sqlQuery - ������-������ �� ���������� ������ ������
     */
    public void RefreshData(String sqlQuery){
        
        try {
            rs = connection.ExecuteQuery(sqlQuery);
            getData();
        } catch (SQLException ex) {
            Logger.getLogger(MyTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * 
     * @param aValue
     * @param rowIndex
     * @param columnIndex 
     */
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        // ������������� �������� � ������ �������
        content[rowIndex][columnIndex]=aValue;
        
        // �������� �������, ��� �������� ��������
        fireTableCellUpdated(rowIndex, columnIndex);
        
    }
    
    public Object upgradeCellValue(Object aValue, int i) {
        Object retValue = null;
        if (columnClass[i] == String.class){
            retValue = "'" + aValue + "'";
        } else if (columnClass[i] == Integer.class || 
                columnClass[i] == Double.class || 
                columnClass[i] == Float.class || 
                columnClass[i] == Long.class || 
                columnClass[i] == Short.class || 
                columnClass[i] == BigDecimal.class){
            retValue = aValue;
        } else if (columnClass[i] == Boolean.class) {
            if (aValue.equals(true)) {
                retValue = 1;
            } else {retValue = 0;}
        }
        else if(columnClass[i] == Date.class || 
                columnClass[i] == Timestamp.class || 
                columnClass[i] == Time.class){
            retValue = "'" + aValue + "'";
        }
        else System.out.println("��� ������ �� �������� " + 
                columnName[i]);
        return retValue;
    }

    public void setTableColumnIdentifiers(TableColumnModel tableColumnModel) {
        //����� �������������� ��� �������� �������
        for (int i = 0; i < tableColumnModel.getColumnCount(); i++) {
            TableColumn tc = tableColumnModel.getColumn(i);
            tc.setIdentifier((Object) i);
        }
    }

    /**
     * @param colNoEditableList the colNoEditableList to set
     */
    public void setCellNoEditableList(int[] colNoEditableList) {
        this.colNoEditableList = colNoEditableList;
    }

    /**
     * @return the change
     */
    public boolean isChange() {
        return change;
    }

    /**
     * @param change the change to set
     */
    public void setChange(boolean change) {
        this.change = change;
    }

    /**
     * @param connection the connection to set
     */
    public void setConnection(JDBCConnection connection) {
        this.connection = connection;
    }

    /**
     * @param table the table to set
     */
    public void setTable(JTable table) {
        this.table = table;
    }

    /**
     * @param tablename the tablename to set
     */
    public void setTablename(String tablename) {
        this.tablename = tablename;
    }
    
    /**
     * ��������� ����� ������ � ������� � ����� ������ ������
     * @param values ������ �������� ��� �������
     */
    public void addRow(Object[] values){
        // ������ ��������� ������ ��� �������� ������, ������� ��������� �� 1
        Object[][] tmp = new Object[content.length + 1][];
        System.arraycopy(content, 0, tmp, 0, content.length);// �������� ������ � ����� ������
        tmp[tmp.length - 1] = values;// ��������� ������ � ����� �������
        content = tmp;
        fireTableRowsInserted(tmp.length - 1, tmp.length - 1);// �������� �� ���������� � ������
    }
    
    /**
     * ��������� ����� ������ ������ � ������ ����� ��������� ������.
     * ���� ������ ������, ����� ������� ����������� ������� ������, ����� -1,
     * ������ ������� ������ �������������� � ������ ��������� ������.
     * @param Index ������ ������, ����� ������� �������������� ������� (���������� � ����)
     * @param values ������ �������� ��� �������
     */
    public void insertRow(int Index, Object[] values){
        // ������ ��������� ������ ��� �������� ������, ������� ��������� �� 1
        Object[][] tmp = new Object[content.length + 1][];
        int newPos = Index +1;// ������� ������� ����� ������
        
        // ��������� ������, ����� �������� �������������� ������� ������
        if(Index == -1){
            // ���������� ������� ������ � ������ �������
            // ��������� ����� ������
            tmp[newPos] = values;
            // ���������� ���������� ����� �������
            System.arraycopy(content, newPos, tmp, newPos + 1, content.length);
        } else {
            // ���������� ������ ����� ������� ������
            System.arraycopy(content, 0, tmp, 0, Index + 1);
    
            // ��������� ����� ������
            tmp[newPos] = values;

            // ���������� ���������� ����� �������
            System.arraycopy(content, newPos, tmp, newPos + 1, content.length - newPos);
        }
        content = tmp;// �������������� ������
        fireTableRowsInserted(newPos, newPos);// �������� �� ���������� � ������
    }
    
    /**
     * ������� ������ � ��������� �������� �� ������ ������
     * @param Index ������ ������, ������� ��������� (���������� � ����)
     */
    public void removeRow(int Index){
        // ������ ��������� ������ ��� �������� ������, ������� ��������� �� 1
        Object[][] tmp = new Object[content.length - 1][];
        int i = 0;// ��������� �������� �������� �����
        int j = 0;
        while(j < tmp.length){
            if(i != Index){
                tmp[j] = content[i];
                
                j++;// ����������� �������
            }
            i++;// ����������� ������� �����
        }
        content = tmp;// �������������� ������ ������ ������
        fireTableRowsDeleted(Index, Index);// �������� ������� �� �������� ������
    }

    
    /**
     * ��������� ������ ��� ������
     */
    private void getData() throws SQLException{
        DBTableModel dbModel = new DBTableModel(rs);
        content = dbModel.getContent();// ������� ���������� �������
        columnClass = dbModel.getColumnClass();// ���� ������ ����� �������
        columnName = dbModel.getColumnName();
    }

    /**
     * ���������� �����, ����������� ��������� ��������� ������,
     * � ���������� �� �������� ��������� � ��������� ������� ���� ������
     */
    private class MyModelListener implements TableModelListener{
        
        public MyModelListener(){
            
        }

        @Override
        public void tableChanged(TableModelEvent e) {
        // �������� �������� ��� ����������
            Object retValue = 
                    upgradeCellValue
                    (getValueAt(e.getFirstRow(), 
                    e.getColumn()), e.getColumn());
            // ������-������ �� ���������� ������
            String sqlString = "Update " + tablename + " Set " +
                    table.getColumnName(e.getColumn()) + "=" +
                    retValue + " Where " + table.getColumnName(0) +
                    "=" + table.getValueAt(table.getEditingRow(), 0);
            try {
                // ��������� ������ �� ���������� ������
                System.out.println(sqlString);
                int i = connection.ExecuteUpdate(sqlString);
            } catch (SQLException ex) {
                Logger.getLogger(MyModelListener.class.getName()).log(Level.SEVERE,
                        null, ex);
            }
        }
    }

    /**
     * @return the rowNoEditableList
     */
    public int[] getRowNoEditableList() {
        return rowNoEditableList;
    }

    /**
     * @param rowNoEditableList the rowNoEditableList to set
     */
    public void setRowNoEditableList(int[] rowNoEditableList) {
        this.rowNoEditableList = rowNoEditableList;
    }
}
