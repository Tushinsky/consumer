/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abonentgaz;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.math.BigDecimal;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Sergii.Tushinskyi
 */
public class TableCell_Renderer {

    public TableCell_Renderer() {
    }
    
    public static void setIntegerTableCellRenderer(JTable table, Color color){
        if(color == null)
            table.setDefaultRenderer(Integer.class, new NumericTableCellRenderer());
        else
            table.setDefaultRenderer(Integer.class, new 
            NumericTableCellRenderer(color));
    }
    
    public static void setBooleanTablecellRenderer(JTable table){
        table.setDefaultRenderer(Boolean.class, new BooleanTableCellRenderer());
    }
    
    public static void setShortTableCellRenderer(JTable table, Color color){
        if(color == null)
            table.setDefaultRenderer(Short.class, new NumericTableCellRenderer());
        else
            table.setDefaultRenderer(Short.class, new NumericTableCellRenderer(color));
    }
    
    
    public static void setBigDecimalTableCellRenderer(JTable table, Color color){
        if(color == null)
            table.setDefaultRenderer(BigDecimal.class, new NumericTableCellRenderer());
        else
            table.setDefaultRenderer(BigDecimal.class, new NumericTableCellRenderer(color));
    }
    
    public static void setDoubleTableCellRenderer(JTable table, Color color){
        if(color == null)
            table.setDefaultRenderer(Double.class, new NumericTableCellRenderer());
        else
            table.setDefaultRenderer(Double.class, new NumericTableCellRenderer(color));
    }
    
    public static void setFloatTableCellRenderer(JTable table, Color color){
        if(color == null)
            table.setDefaultRenderer(Float.class, new NumericTableCellRenderer());
        else
            table.setDefaultRenderer(Float.class, new NumericTableCellRenderer(color));
    }
    
    public static void setLongTableCellRenderer(JTable table, Color color){
        if(color == null)
            table.setDefaultRenderer(Long.class, new NumericTableCellRenderer());
        else
            table.setDefaultRenderer(Long.class, new NumericTableCellRenderer(color));
    }
    
    public static void setWordWrapCellRenderer(JTable table, Class[] columnClass){
        // ��� �������� � ��������� ����� ������ ��������� ������� ������
        int colCount = table.getColumnCount();
        for(int i = 0; i < colCount; i++){
            // ��������� ��� ������ � �������� �������
            Class colClass = columnClass[i];
            if(colClass.getCanonicalName().equals("java.lang.String"))
                table.getColumnModel().getColumn(i).setCellRenderer(new WordWrapCellRenderer());
//            if(columnClass[i].getTypeName().equals("java.lang.String"))
//                table.getColumnModel().getColumn(i).setCellRenderer(new WordWrapCellRenderer());
        }
    }
    
    public static void setTableHeaderRenderer(JTableHeader header, int horAlign,
            int vertAlign){
        TableHeaderRenderer renderer = new TableHeaderRenderer(vertAlign, horAlign);
        header.setDefaultRenderer(renderer);
    }
    
    public static void setTableHeaderRenderer(JTableHeader header){
        TableHeaderRenderer renderer = new TableHeaderRenderer();
        header.setDefaultRenderer(renderer);
    }
    
    public static void setStringTableCellRenderer(JTable table, Color color, int[] cols, Color[] colors) {
        if(color == null)
            table.setDefaultRenderer(String.class, new StringTableCellRenderer(cols, colors));
        else
            table.setDefaultRenderer(String.class, new StringTableCellRenderer(color));
    }
    
    
//    private static class IntegerTableCellRenderer extends DefaultTableCellRenderer{
//
////        int fixedcol = 0;// ����� �������������� ������� �������, �� ������� �� ����� ������������ �����
//                    // ��-���������
//        Color color;
//        
//        public IntegerTableCellRenderer() {
//        }
//        
//        /**
//         * ��������� ����������� ����� � ���������� �������� ������
//         * @param color ���� ��������� �����
//         */
//        public IntegerTableCellRenderer(Color color) {
//            this.color = color;
//        }
//        
//        
//        @Override
//        public Component getTableCellRendererComponent(JTable table, 
//                Object value, boolean isSelected, boolean hasFocus, int row, 
//                int column){
////            Component comp = null;
////            if(value.getClass() == Integer.class){
//            super.getTableCellRendererComponent(table, 
//                    value, isSelected, hasFocus, row, column);
//                // ������������ ����������� � ������ ������� ������� �� ������
//            setText(value != null ? value.toString() : "");
//            setHorizontalAlignment(SwingConstants.CENTER);
//            if(color == null) color = table.getBackground();
//            if(column == 0) {
//                // ���� ������� �������� �������������, �. �. �� ���������� �����
////                setHorizontalAlignment(SwingConstants.CENTER);
//                setBackground(table.getTableHeader().getBackground());
//                setForeground(table.getForeground());
//                setBorder(table.getTableHeader().getBorder());
//                setFont(new Font(table.getTableHeader().getFont().getFontName(), 
//                        Font.BOLD, table.getTableHeader().getFont().getSize()));
////                    comp = label;
//            } else {
//                // ���� ��� ����� ������ �������
//                setFont(table.getFont());
//                if(isSelected){
//                    setBackground(table.getSelectionBackground());
//                    setForeground(table.getSelectionForeground());
////                    setBorder(BorderFactory.createEmptyBorder());
//                } else {
//                    setBackground(color);
//                    setForeground(table.getForeground());
//                }
//                
//            }
//                
//            return this;
//        }
//    }

    
    private static class BooleanTableCellRenderer extends DefaultTableCellRenderer{

        Color onColor = Color.GREEN;
        Color offColor = Color.red;

        public BooleanTableCellRenderer() {
        }
        
        public BooleanTableCellRenderer(Color onColor, Color offColor) {
            this.onColor = onColor;
            this.offColor = offColor;
        }
        
        
        @Override
        public Component getTableCellRendererComponent(JTable table, 
        Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if((boolean)value == true){
//                if(isSelected)
//                    setBackground(table.getSelectionBackground());
//                else
                    setBackground(onColor); 
//                setForeground(Color.black);
            } else {
//                if(isSelected)
//                    setBackground(table.getSelectionBackground());
//                else
                    setBackground(offColor);
//                setForeground(Color.black);
            }
            if(isSelected)
                setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            else
                setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            return this;
        }
        
    }
    
//    private static class ShortTableCellRenderer extends DefaultTableCellRenderer {
//        
//
//        Color color;
//        
//        public ShortTableCellRenderer() {
//        }
//
//        /**
//         * ��������� ����������� ����� � ��������� ������� � � ���������� �������� ������
//         * @param col ����� ������� ��� ��������� ������
//         * @param color ���� ��������� �����
//         */
//        public ShortTableCellRenderer(Color color) {
//            this.color = color;
//        }
//
//        @Override
//        public Component getTableCellRendererComponent(JTable table, 
//                Object value, boolean isSelected, boolean hasFocus, 
//                int row, int column) {
//            super.getTableCellRendererComponent(table, 
//                    value, isSelected, hasFocus, row, column);
//            // ������������ ����������� �� ������
//            setText(value.toString());
//            setFont(table.getFont());
//            setHorizontalAlignment(SwingConstants.CENTER);
//            if(color == null) color = table.getBackground();
//            if(isSelected){
//                setBackground(table.getSelectionBackground());
//                setForeground(table.getSelectionForeground());
////                    setBorder(BorderFactory.createEmptyBorder());
//            } else {
//                setBackground(color);
//                setForeground(table.getForeground());
//            }
//            
//                
//            return this;
//        }
//        
//    }
//    
//    
//    private static class BigDecimalTableCellRenderer extends DefaultTableCellRenderer {
//
//        Color color;
//        
//        public BigDecimalTableCellRenderer() {
//        }
//
//        /**
//         * ��������� ����������� ����� � ��������� ������� � � ���������� �������� ������
//         * @param col ����� ������� ��� ��������� ������
//         * @param color ���� ��������� �����
//         */
//        public BigDecimalTableCellRenderer(Color color) {
//            this.color = color;
//        }
//
//        @Override
//        public Component getTableCellRendererComponent(JTable table, 
//                Object value, boolean isSelected, boolean hasFocus, 
//                int row, int column) {
//            super.getTableCellRendererComponent(table, 
//                    value, isSelected, hasFocus, row, column);
//            // ������������ ����������� �� ������
//            setText(value.toString());
//            setFont(table.getFont());
//            setHorizontalAlignment(SwingConstants.CENTER);
//            if(color == null) color = table.getBackground();
//            if(isSelected){
//                setBackground(table.getSelectionBackground());
//                setForeground(table.getSelectionForeground());
////                    setBorder(BorderFactory.createEmptyBorder());
//            } else {
//                setBackground(color);
//                setForeground(table.getForeground());
//            }
//            
//            return this;
//        }
//        
//    }
//    
//    private static class DoubleTableCellRenderer extends DefaultTableCellRenderer {
//        
//
//        Color color;
//        
//        public DoubleTableCellRenderer() {
//        }
//
//        /**
//         * ��������� ����������� ����� � ��������� ������� � � ���������� �������� ������
//         * @param col ����� ������� ��� ��������� ������
//         * @param color ���� ��������� �����
//         */
//        public DoubleTableCellRenderer(Color color) {
//            this.color = color;
//        }
//
//        @Override
//        public Component getTableCellRendererComponent(JTable table, 
//                Object value, boolean isSelected, boolean hasFocus, 
//                int row, int column) {
//            super.getTableCellRendererComponent(table, 
//                    value, isSelected, hasFocus, row, column);
//            // ������������ ����������� �� ������
//            setText(value.toString());
//            setFont(table.getFont());
//            setHorizontalAlignment(SwingConstants.CENTER);
//            if(color == null) color = table.getBackground();
//            if(isSelected){
//                setBackground(table.getSelectionBackground());
//                setForeground(table.getSelectionForeground());
////                    setBorder(BorderFactory.createEmptyBorder());
//            } else {
//                setBackground(color);
//                setForeground(table.getForeground());
//            }
//            
//                
//            return this;
//        }
//        
//    }
//    
//    private static class FloatTableCellRenderer extends DefaultTableCellRenderer {
//        
//
//        Color color;
//        
//        public FloatTableCellRenderer() {
//        }
//
//        /**
//         * ��������� ����������� ����� � ��������� ������� � � ���������� �������� ������
//         * @param col ����� ������� ��� ��������� ������
//         * @param color ���� ��������� �����
//         */
//        public FloatTableCellRenderer(Color color) {
//            this.color = color;
//        }
//
//        @Override
//        public Component getTableCellRendererComponent(JTable table, 
//                Object value, boolean isSelected, boolean hasFocus, 
//                int row, int column) {
//            super.getTableCellRendererComponent(table, 
//                    value, isSelected, hasFocus, row, column);
//            // ������������ ����������� �� ������
//            setText(value.toString());
//            setFont(table.getFont());
//            setHorizontalAlignment(SwingConstants.CENTER);
//            if(color == null) color = table.getBackground();
//            if(isSelected){
//                setBackground(table.getSelectionBackground());
//                setForeground(table.getSelectionForeground());
////                    setBorder(BorderFactory.createEmptyBorder());
//            } else {
//                setBackground(color);
//                setForeground(table.getForeground());
//            }
//            
//                
//            return this;
//        }
//        
//    }
//    
//    private static class LongTableCellRenderer extends DefaultTableCellRenderer {
//        
//
//        Color color;
//        
//        public LongTableCellRenderer() {
//        }
//
//        /**
//         * ��������� ����������� ����� � ��������� ������� � � ���������� �������� ������
//         * @param col ����� ������� ��� ��������� ������
//         * @param color ���� ��������� �����
//         */
//        public LongTableCellRenderer(Color color) {
//            this.color = color;
//        }
//
//        @Override
//        public Component getTableCellRendererComponent(JTable table, 
//                Object value, boolean isSelected, boolean hasFocus, 
//                int row, int column) {
//            super.getTableCellRendererComponent(table, 
//                    value, isSelected, hasFocus, row, column);
//            // ������������ ����������� �� ������
//            setText(value.toString());
//            setFont(table.getFont());
//            setHorizontalAlignment(SwingConstants.CENTER);
//            if(color == null) color = table.getBackground();
//            if(isSelected){
//                setBackground(table.getSelectionBackground());
//                setForeground(table.getSelectionForeground());
////                    setBorder(BorderFactory.createEmptyBorder());
//            } else {
//                setBackground(color);
//                setForeground(table.getForeground());
//            }
//            
//                
//            return this;
//        }
//        
//    }
//    
    
    private static class NumericTableCellRenderer extends DefaultTableCellRenderer {
        

        Color color;
        
        public NumericTableCellRenderer() {
        }

        /**
         * ��������� ����������� ����� � ��������� ������� � � ���������� �������� ������
         * @param col ����� ������� ��� ��������� ������
         * @param color ���� ��������� �����
         */
        public NumericTableCellRenderer(Color color) {
            this.color = color;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, 
                Object value, boolean isSelected, boolean hasFocus, 
                int row, int column) {
            super.getTableCellRendererComponent(table, 
                    value, isSelected, hasFocus, row, column);
            // ������������ ����������� �� ������
            setText((value != null ? value.toString() : ""));
            setFont(table.getFont());
            setHorizontalAlignment(SwingConstants.CENTER);
            if(color == null) color = table.getBackground();
            if(isSelected){
                setBackground(table.getSelectionBackground());
                setForeground(table.getSelectionForeground());
//                    setBorder(BorderFactory.createEmptyBorder());
            } else {
                setBackground(color);
                setForeground(table.getForeground());
            }
            
                
            return this;
        }
        
    }
    
    private static class WordWrapCellRenderer extends JTextArea implements TableCellRenderer {
        
        public WordWrapCellRenderer() {
            super.setLineWrap(true);
            super.setWrapStyleWord(true);
            super.setOpaque(true);
            
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, 
                boolean isSelected, boolean hasFocus, int row, int column) {
//            System.out.println("row=" + row + " column=" + column + " value=" + value.toString());
            setText(value.toString());
            setFont(table.getFont());
            setSize(table.getColumnModel().getColumn(column).getWidth(), getPreferredSize().height);
            if (table.getRowHeight(row) != getPreferredSize().height) {
                table.setRowHeight(row, getPreferredSize().height);
            }
            if(isSelected){
                setBackground(table.getSelectionBackground());
                setForeground(table.getSelectionForeground());
//                setBorder(new EtchedBorder(1));
            } else {
                setBackground(table.getBackground());
                setForeground(table.getForeground());
//                setBorder(new BevelBorder(1));
            }
            return this;
        }
    }
    
    private static class TableHeaderRenderer extends DefaultTableCellRenderer{

        private final int vertAlignment;// ������������ ������������ � ����������
        private final int horAlignment;// �������������� ������������ � ����������
        
        // ������� ���������� �������
        Border border = BorderFactory.createBevelBorder(BevelBorder.RAISED);

        public TableHeaderRenderer() {
            // ������������ �� ���������
            vertAlignment = SwingConstants.TOP;
            horAlignment = SwingConstants.CENTER;
        }

        public TableHeaderRenderer(int vertAlignment, int horAlignment) {
            this.vertAlignment = vertAlignment;
            this.horAlignment = horAlignment;
        }
        
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, 
                boolean isSelected, boolean hasFocus, int row, int column) {
            // ������� �� �������� ������
            JLabel label = (JLabel) super.getTableCellRendererComponent(table, 
                    value, isSelected, hasFocus, row, column);
            
            // ������������ ������ ���������
            label.setHorizontalAlignment(horAlignment);
            label.setVerticalAlignment(vertAlignment);
            // ��������� ����� ���� �����
//            float[] hsb = Color.RGBtoHSB(124, 124, 124, null);
            float[] hsb1 = Color.RGBtoHSB(224, 224, 224, null);
//            Color color = Color.getHSBColor(hsb[0], hsb[1], hsb[2]);
            Color color1 = Color.getHSBColor(hsb1[0], hsb1[1], hsb1[2]);
//            Border border = BorderFactory.createMatteBorder(8,1,8,1,color);
            label.setBackground(color1);
            label.setBorder(border);
//            label.setSize(label.getWidth(), 28);
            return label;
        }
        
    }
    
    private static class StringTableCellRenderer extends DefaultTableCellRenderer {
        private Color color;// ���� ���� �����
        private int[] cols;// ������, ���������� ������ �������� ������� ��� ���������
        private Color[] colors;// ������, ���������� ����� ��� ���������

        public StringTableCellRenderer() {
        }
        

        public StringTableCellRenderer(Color color) {
            this.color = color;
        }

        public StringTableCellRenderer(int[] cols, Color[] colors) {
            this.cols = cols;
            this.colors = colors;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, 
                boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
            // ������� ��������� ������ � ��������� �������� �� ������ ����
            setText(" " + (value != null ? value.toString() : ""));
            if(color == null) color = table.getBackground();
            // ����� �����
            setFont(table.getFont());
            // ���� ������ �������� �� �����
            if(cols == null){
                if(isSelected){
                    setBackground(table.getSelectionBackground());
                    setForeground(table.getSelectionForeground());
//                    setBorder(BorderFactory.createEmptyBorder());
                } else {
                    setBackground(color);
                    setForeground(table.getForeground());
                }
            } else {
                // ����� ��� ������� ������� � ������� ����� ����
                int count = cols.length < colors.length ? cols.length : colors.length;
                for(int i = 0; i < count; i++) {
                    if(column == cols[i]) {
                        if(isSelected){
                           setBackground(table.getSelectionBackground());
                           setForeground(table.getSelectionForeground());
       //                    setBorder(BorderFactory.createEmptyBorder());
                       } else {
                           setBackground(colors[i]);
                           setForeground(table.getForeground());
                       }       
                    }
                }
            }
                
            return this;
        }
        
        
    }
}
