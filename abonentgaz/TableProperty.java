/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abonentgaz;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

/**
 *
 * @author lera
 */
public class TableProperty {
    private String colWidth;
    private JTable table;
    private String symbol;
    private String defaultWidth;

    public TableProperty() {
        symbol = ",";// ������ - ����������� �������� ����� �������� �������
    }
    
    
    /**
     * @param colWidth - ������, ���������� ������� �������� �������
     * @param table - �������, ��� ������� ������� ������ ��������
     */
    public TableProperty(String colWidth, JTable table) {
        this.colWidth = colWidth;
        this.table = table;
        symbol = ",";// ������ - ����������� �������� ����� �������� �������
    }
    
    /**
     * @param table - �������, ��� ������� ������� ������ ��������
     */
    public TableProperty(JTable table) {
        this.table = table;
        symbol = ",";// ������ - ����������� �������� ����� �������� �������
    }

    /**
     * ���������� ������ �������� ������� � ���� ������, �������� � ������� 
     * ��������� �������� - ������������
     * @return the colWidth - ������� ���� �������� ������� � ���� ������
     */
    public String getColWidth() {
        if (table != null) {
            String width = "";
            if (table.getModel().getRowCount() > 0) {
                for (int i = 0; i <= table.getColumnCount()-1; i++) {
                    TableColumn tc = table.getColumnModel().getColumn(i);
                    tc.setIdentifier((Object) i);
                    width = width + 
                            Integer.toString(table.getColumn(i).getWidth()) + symbol;
                }
                colWidth = width.substring(0, width.length()-1);
            } else {colWidth = defaultWidth;}
        } else {
            colWidth = "";
        }
        return colWidth;
    }

    /**
     * ����� ������� �������� ������� �� ������
     */
    public void setColWidth() {
        // ��������� ������ �� �������� ������
        if (colWidth == null || colWidth.equals("")){
            colWidth = defaultWidth;
            
        }
        if (table.getModel().getRowCount() > 0) {
            int colCount;// ���������� ��������
            String[] tableColWidth = colWidth.split(symbol);
            // ���������� ���������� �������� �������, ��� ������� �����
            // ������������� �������
            if(tableColWidth.length > table.getColumnCount()){
                // ���� ������ ������� ������ ���������� �������� �������
                // ����� ���������� ��������� ������ ���������� �������� �������
                colCount = table.getColumnCount();
            } else {
                // ����� ��������� ������ ������� �������
                colCount = tableColWidth.length;
            }
            for (int i = 0; i < colCount; i++) {
                int width = Integer.parseInt(tableColWidth[i]);
//                System.out.println("i col=" + i + " width=" + width);
                table.getColumn((Object) i).setPreferredWidth(width);
            }

        }
    }
    
    /**
     * @param symbol the symbol to set
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * @param table the table to set
     */
    public void setTable(JTable table) {
        this.table = table;
        
    }

    /**
     * @param Width the Width to set
     */
    public void setDefaultWidth(String Width) {
        defaultWidth = Width;
    }
    
    
}
