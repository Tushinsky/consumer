/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abonentgaz;

import java.awt.Component;
import java.math.BigDecimal;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;
/**
 *
 * @author Sergii.Tushinskyi
 */
public class TableCell_Editor {

    public TableCell_Editor() {
    }
    
    public static void setNumericTableCellEditor(JTable jtable) {
        jtable.setDefaultEditor(Number.class, new NumericTableCellEditor());
    }
    
    public static void setStringTableCellEditor(JTable jtable) {
        jtable.setDefaultEditor(String.class, new StringTableCellEditor());
    }
    
    private static class NumericTableCellEditor extends AbstractCellEditor implements TableCellEditor {

        private final JTextField editor;
        private Class<?> columnClass;
        
        public NumericTableCellEditor() {
            super();
            editor = new JTextField();
        }

        @Override
        public Object getCellEditorValue() {
            // РїРѕР»СѓС‡Р°РµР�? С‚РµРєСѓС‰РµРµ Р·РЅР°С‡РµРЅРёРµ РёР· СЂРµРґР°РєС‚РѕСЂР°
            System.out.println("classname = " + columnClass.getName());
            switch(columnClass.getName()) {
                case "java.lang.Integer":
                    return Integer.parseInt(editor.getText());
                    
                case "java.lang.Long":
                    return Long.parseLong(editor.getText());
                case "java.lang.Short":
                    return Short.parseShort(editor.getText());
                case "java.lang.Float":
                    return Float.parseFloat(editor.getText());
                case "java.lang.Double":
                    return Double.parseDouble(editor.getText());
                case "java.math.BigDecimal":
                    return BigDecimal.valueOf(Double.parseDouble(editor.getText()));
                default:
                    return editor.getText();
            }
            
        }

        @Override
        public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int row, int col) {
            editor.setFont(jtable.getFont());// задаём шрифт редактора
            editor.setText(o.toString());// задаём значение в редакторе
            columnClass = jtable.getModel().getColumnClass(col);// тип данных столбца
            
            if(bln) {
                // если редактор получает фокус, выделяем весь текст в редакторе для удобства редактирования
                editor.setSelectionStart(0);
                editor.setSelectionEnd(editor.getText().length());
            }
            return editor;
        }
        
    }
    
    private static class StringTableCellEditor extends AbstractCellEditor implements TableCellEditor {
        
        private final JTextField editor;

        public StringTableCellEditor() {
            editor = new JTextField();
        }
        

        @Override
        public Object getCellEditorValue() {
            return editor.getText();
        }

        @Override
        public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int i, int i1) {
            //РёР·Р�?РµРЅРµРЅРёРµ Р·РЅР°С‡РµРЅРёСЏ РІ СЂРµРґР°РєС‚РѕСЂРµ
            
            editor.setFont(jtable.getFont());// шрифт редактора
            editor.setText(o.toString());// значение в редакторе
            if(bln) {
                // если редактор получает фокус, выделяем весь текст в редакторе для удобства редактирования
                editor.setSelectionStart(0);
                editor.setSelectionEnd(editor.getText().length());
            }
            return editor;
        }
        
    }
}
