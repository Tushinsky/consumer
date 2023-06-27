/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abonentgaz;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;

/**
 * класс для отслеживаня изменений ширины столбцов таблицы и записи
 * этих изменений в указанный файл свойств в заданное имя свойства
 * @author Sergii.Tushinskyi
 */
public class ColumnModelListener implements TableColumnModelListener {

    private final UserProperties props;// класс свойств, который записываем данные изменения
    private final String propName;// имя свойства в файле свойств, в которое записываются изменения
    private final TableProperty tp;// класс для получения данных о ширине столбцов таблицы
    private String colWidth;
    
    /**
     * 
     * @param table �������, ��������� �������� �������� ������� �������������
     * @param props ��������� ������ �������, ������� ��������� � ���������� ������ �������� �������
     * @param propName ��� ��������, ��/� ������� ������������ ������ ��������
     */
    public ColumnModelListener(JTable table, UserProperties props, 
            String propName) {
        this.props = props;
        this.propName = propName;
        tp = new TableProperty(table);
    }

    @Override
    public void columnMarginChanged(ChangeEvent e) {
        try {
            colWidth = tp.getColWidth();
            props.setProperty(propName, colWidth);
            props.writeProperties();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ColumnModelListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void columnAdded(TableColumnModelEvent e) {
        
    }

    @Override
    public void columnMoved(TableColumnModelEvent e) {
        
    }

    @Override
    public void columnRemoved(TableColumnModelEvent e) {
        
    }

    @Override
    public void columnSelectionChanged(ListSelectionEvent e) {
        
    }
    
    
    
    
}
