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
        symbol = ",";// символ - разделитель значений ширин столбцов таблицы
    }
    
    
    /**
     * @param colWidth - строка, содержащая размеры столбцов таблицы
     * @param table - таблица, для которой задаётся ширина столбцов
     */
    public TableProperty(String colWidth, JTable table) {
        this.colWidth = colWidth;
        this.table = table;
        symbol = ",";// символ - разделитель значений ширин столбцов таблицы
    }
    
    /**
     * @param table - таблица, для которой задаётся ширина столбцов
     */
    public TableProperty(JTable table) {
        this.table = table;
        symbol = ",";// символ - разделитель значений ширин столбцов таблицы
    }

    /**
     * Возвращает ширину столбцов таблицы в виде строки, значения в которой 
     * разделены символом - разделителем
     * @return the colWidth - размеры всех столбцов таблицы в виде строки
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
     * задаёт размеры столбцов таблицы из строки
     */
    public void setColWidth() {
        // проверяем задано ли значение строки
        if (colWidth == null || colWidth.equals("")){
            colWidth = defaultWidth;
            
        }
        if (table.getModel().getRowCount() > 0) {
            int colCount;// количество столбцов
            String[] tableColWidth = colWidth.split(symbol);
            // определяем количество столбцов таблицы, для которых будем
            // устанавливать размеры
            if(tableColWidth.length > table.getColumnCount()){
                // если размер массива больше количества столбцов таблицы
                // тогда количество принимаем равным количеству столбцов таблицы
                colCount = table.getColumnCount();
            } else {
                // иниче принимаем равным размеру массива
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
