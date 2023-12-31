/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multispancell;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Enumeration;
import java.util.Vector;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Сергей
 */
public class AttributiveCellTableModel extends DefaultTableModel {
    protected CellAttribute cellAtt;

    public AttributiveCellTableModel() {
        this((Vector)null, 0);
    }

    public AttributiveCellTableModel(int numRows, int numColumns) {
        Vector names = new Vector(numColumns);
        names.setSize(numColumns);
        super.setColumnIdentifiers(names);
        dataVector = new Vector();
        super.setNumRows(numRows);
        cellAtt = new DefaultCellAttribute(numRows,numColumns);
    }

    public AttributiveCellTableModel(Vector columnNames, int numRows) {
        super.setColumnIdentifiers(columnNames);
        dataVector = new Vector();
        super.setNumRows(numRows);
        cellAtt = new DefaultCellAttribute(numRows,columnNames.size());
    }

    public AttributiveCellTableModel(Object[] columnNames, int numRows) {
        this(convertToVector(columnNames), numRows);
    }

//        public AttributiveCellTableModel(Vector data, Vector columnNames) {
//            setDataVector(data, columnNames);
//        }

    public AttributiveCellTableModel(Object[][] data, Object[] columnNames) {
        setDataVector(data, columnNames);
        cellAtt = new DefaultCellAttribute(data.length, columnNames.length);
    }


//        @Override
//        public void setDataVector(Vector newData, Vector columnNames) {
//            if (newData == null)
//              throw new IllegalArgumentException("setDataVector() - Null parameter");
//            dataVector = new Vector(0);
//            setColumnIdentifiers(columnNames);
//            dataVector = newData;
//
//            //
//            cellAtt = new DefaultCellAttribute(dataVector.size(),
//                                               columnIdentifiers.size());
//
//            newRowsAdded(new TableModelEvent(this, 0, getRowCount()-1,
//             TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT));
//        }

    @Override
    public void addColumn(Object columnName, Vector columnData) {
        if (columnName == null)
            throw new IllegalArgumentException("addColumn() - null parameter");
        columnIdentifiers.addElement(columnName);
        int index = 0;
        Enumeration eeration = dataVector.elements();
        while (eeration.hasMoreElements()) {
            Object value;
            if ((columnData != null) && (index < columnData.size()))
                value = columnData.elementAt(index);
            else
                value = null;
            ((Vector)eeration.nextElement()).addElement(value);
            index++;
        }

        //
        cellAtt.addColumn();

        fireTableStructureChanged();
    }

    @Override
    public void addRow(Vector rowData) {
        Vector newData = null;
        if (rowData == null) {
            newData = new Vector(getColumnCount());
        }
        else {
            rowData.setSize(getColumnCount());
        }
        dataVector.addElement(newData);

        //
        cellAtt.addRow();

        newRowsAdded(new TableModelEvent(this, getRowCount()-1, getRowCount()-1,
            TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT));
    }

    @Override
    public void insertRow(int row, Vector rowData) {
        if (rowData == null) {
            rowData = new Vector(getColumnCount());
        }
        else {
            rowData.setSize(getColumnCount());
        }

        dataVector.insertElementAt(rowData, row);

        //
        cellAtt.insertRow(row);

        newRowsAdded(new TableModelEvent(this, row, row,
           TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT));
    }

    public CellAttribute getCellAttribute() {
        return cellAtt;
    }

    public void setCellAttribute(CellAttribute newCellAtt) {
        int numColumns = getColumnCount();
        int numRows    = getRowCount();
        if ((newCellAtt.getSize().width  != numColumns) ||
            (newCellAtt.getSize().height != numRows)) {
            newCellAtt.setSize(new Dimension(numRows, numColumns));
        }
        cellAtt = newCellAtt;
        fireTableDataChanged();
    }
    
    
    class DefaultCellAttribute 
        //    implements CellAttribute ,CellSpan  {
              implements CellAttribute ,CellSpan ,ColoredCell ,CellFont {

        //
        // !!!! CAUTION !!!!!
        // these values must be synchronized to Table data
        //
        protected int rowSize;
        protected int columnSize;
        protected int[][][] span;                   // CellSpan
        protected Color[][] foreground;             // ColoredCell
        protected Color[][] background;             //
        protected Font[][]  font;                   // CellFont

        public DefaultCellAttribute() {
            this(1,1);
        }

        public DefaultCellAttribute(int numRows, int numColumns) {
            setSize(new Dimension(numColumns, numRows));
        }

        protected void initValue() {
            for (int[][] span1 : span) {
                for (int[] item : span1) {
                    item[CellSpan.COLUMN] = 1;
                    item[CellSpan.ROW] = 1;
                }
            }
        }


        //
        // CellSpan
        //
        @Override
        public int[] getSpan(int row, int column) {
            if (isOutOfBounds(row, column)) {
              int[] ret_code = {1,1};
              return ret_code;
            }
            return span[row][column];
        }

        @Override
        public void setSpan(int[] span, int row, int column) {
            if (isOutOfBounds(row, column)) return;
            this.span[row][column] = span;
        }

        @Override
        public boolean isVisible(int row, int column) {
            if (isOutOfBounds(row, column)) return false;
            return !((span[row][column][CellSpan.COLUMN] < 1)
                    ||(span[row][column][CellSpan.ROW]    < 1));
        }

        @Override
        public void combine(int[] rows, int[] columns) {
            if (isOutOfBounds(rows, columns)) return;
            int    rowSpan  = rows.length;
            int columnSpan  = columns.length;
            int startRow    = rows[0];
            int startColumn = columns[0];
            for (int i=0;i<rowSpan;i++) {
                for (int j=0;j<columnSpan;j++) {
                    if ((span[startRow +i][startColumn +j][CellSpan.COLUMN] != 1)
                        ||(span[startRow +i][startColumn +j][CellSpan.ROW]    != 1)) {
                        //System.out.println("can't combine");
                        return ;
                    }
                }
            }
            for (int i=0,ii=0;i<rowSpan;i++,ii--) {
                for (int j=0,jj=0;j<columnSpan;j++,jj--) {
                    span[startRow +i][startColumn +j][CellSpan.COLUMN] = jj;
                    span[startRow +i][startColumn +j][CellSpan.ROW]    = ii;
                    //System.out.println("r " +ii +"  c " +jj);
                }
            }
                span[startRow][startColumn][CellSpan.COLUMN] = columnSpan;
                span[startRow][startColumn][CellSpan.ROW]    =    rowSpan;

        }

        @Override
        public void split(int row, int column) {
            if (isOutOfBounds(row, column)) return;
            int columnSpan = span[row][column][CellSpan.COLUMN];
            int    rowSpan = span[row][column][CellSpan.ROW];
            for (int i=0;i<rowSpan;i++) {
                for (int j=0;j<columnSpan;j++) {
                    span[row +i][column +j][CellSpan.COLUMN] = 1;
                    span[row +i][column +j][CellSpan.ROW]    = 1;
                }
            }
        }


        //
        // ColoredCell
        //
        @Override
        public Color getForeground(int row, int column) {
            if (isOutOfBounds(row, column)) return null;
            return foreground[row][column];
        }
        @Override
        public void setForeground(Color color, int row, int column) {
            if (isOutOfBounds(row, column)) return;
            foreground[row][column] = color;
        }
        @Override
        public void setForeground(Color color, int[] rows, int[] columns) {
            if (isOutOfBounds(rows, columns)) return;
            setValues(foreground, color, rows, columns);
        }
        @Override
        public Color getBackground(int row, int column) {
            if (isOutOfBounds(row, column)) return null;
            return background[row][column];
        }
        @Override
        public void setBackground(Color color, int row, int column) {
            if (isOutOfBounds(row, column)) return;
            background[row][column] = color;
        }
        @Override
        public void setBackground(Color color, int[] rows, int[] columns) {
            if (isOutOfBounds(rows, columns)) return;
            setValues(background, color, rows, columns);
        }
        //


        //
        // CellFont
        //
        @Override
        public Font getFont(int row, int column) {
            if (isOutOfBounds(row, column)) return null;
            return font[row][column];
        }
        @Override
        public void setFont(Font font, int row, int column) {
            if (isOutOfBounds(row, column)) return;
            this.font[row][column] = font;
        }
        @Override
        public void setFont(Font font, int[] rows, int[] columns) {
            if (isOutOfBounds(rows, columns)) return;
            setValues(this.font, font, rows, columns);
        }
        //


        //
        // CellAttribute
        //
        @Override
        public void addColumn() {
            int[][][] oldSpan = span;
            int numRows    = oldSpan.length;
            int numColumns = oldSpan[0].length;
            span = new int[numRows][numColumns + 1][2];
            System.arraycopy(oldSpan,0,span,0,numRows);
            for (int i=0;i<numRows;i++) {
              span[i][numColumns][CellSpan.COLUMN] = 1;
              span[i][numColumns][CellSpan.ROW]    = 1;
            }
        }

        @Override
        public void addRow() {
            int[][][] oldSpan = span;
            int numRows    = oldSpan.length;
            int numColumns = oldSpan[0].length;
            span = new int[numRows + 1][numColumns][2];
            System.arraycopy(oldSpan,0,span,0,numRows);
            for (int i=0;i<numColumns;i++) {
              span[numRows][i][CellSpan.COLUMN] = 1;
              span[numRows][i][CellSpan.ROW]    = 1;
            }
        }

        @Override
        public void insertRow(int row) {
            int[][][] oldSpan = span;
            int numRows    = oldSpan.length;
            int numColumns = oldSpan[0].length;
            span = new int[numRows + 1][numColumns][2];
            if (0 < row) {
              System.arraycopy(oldSpan,0,span,0,row-1);
            }
            System.arraycopy(oldSpan,0,span,row,numRows - row);
            for (int i=0;i<numColumns;i++) {
              span[row][i][CellSpan.COLUMN] = 1;
              span[row][i][CellSpan.ROW]    = 1;
            }
        }

        @Override
        public Dimension getSize() {
            return new Dimension(rowSize, columnSize);
        }

        @Override
        public void setSize(Dimension size) {
            columnSize = size.width;
            rowSize    = size.height;
            span = new int[rowSize][columnSize][2];   // 2: COLUMN,ROW
            foreground = new Color[rowSize][columnSize];
            background = new Color[rowSize][columnSize];
            font = new Font[rowSize][columnSize];
            initValue();
        }

        /*
        public void changeAttribute(int row, int column, Object command) {
        }

        public void changeAttribute(int[] rows, int[] columns, Object command) {
        }
        */




        protected boolean isOutOfBounds(int row, int column) {
            return (row    < 0)||(rowSize    <= row)
                    ||(column < 0)||(columnSize <= column);
        }

        protected boolean isOutOfBounds(int[] rows, int[] columns) {
            for (int i=0;i<rows.length;i++) {
                if ((rows[i] < 0)||(rowSize <= rows[i])) return true;
            }
            for (int i=0;i<columns.length;i++) {
                if ((columns[i] < 0)||(columnSize <= columns[i])) return true;
            }
            return false;
        }

        protected void setValues(Object[][] target, Object value,
                                 int[] rows, int[] columns) {
          for (int i=0;i<rows.length;i++) {
            int row = rows[i];
            for (int j=0;j<columns.length;j++) {
                int column = columns[j];
                target[row][column] = value;
            }
          }
        }
    }
}
