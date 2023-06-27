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
 * РєР»Р°СЃСЃ РґР»СЏ РѕС‚СЃР»РµР¶РёРІР°РЅСЏ РёР·РјРµРЅРµРЅРёР№ С€РёСЂРёРЅС‹ СЃС‚РѕР»Р±С†РѕРІ С‚Р°Р±Р»РёС†С‹ Рё Р·Р°РїРёСЃРё
 * СЌС‚РёС… РёР·РјРµРЅРµРЅРёР№ РІ СѓРєР°Р·Р°РЅРЅС‹Р№ С„Р°Р№Р» СЃРІРѕР№СЃС‚РІ РІ Р·Р°РґР°РЅРЅРѕРµ РёРјСЏ СЃРІРѕР№СЃС‚РІР°
 * @author Sergii.Tushinskyi
 */
public class ColumnModelListener implements TableColumnModelListener {

    private final UserProperties props;// РєР»Р°СЃСЃ СЃРІРѕР№СЃС‚РІ, РєРѕС‚РѕСЂС‹Р№ Р·Р°РїРёСЃС‹РІР°РµРј РґР°РЅРЅС‹Рµ РёР·РјРµРЅРµРЅРёСЏ
    private final String propName;// РёРјСЏ СЃРІРѕР№СЃС‚РІР° РІ С„Р°Р№Р»Рµ СЃРІРѕР№СЃС‚РІ, РІ РєРѕС‚РѕСЂРѕРµ Р·Р°РїРёСЃС‹РІР°СЋС‚СЃСЏ РёР·РјРµРЅРµРЅРёСЏ
    private final TableProperty tp;// РєР»Р°СЃСЃ РґР»СЏ РїРѕР»СѓС‡РµРЅРёСЏ РґР°РЅРЅС‹С… Рѕ С€РёСЂРёРЅРµ СЃС‚РѕР»Р±С†РѕРІ С‚Р°Р±Р»РёС†С‹
    private String colWidth;
    
    /**
     * 
     * @param table таблица, изменение размеров столбцов которой отслеживаются
     * @param props экземпляр класса свойств, который считывает и записывает ширину столбцов таблицы
     * @param propName имя свойства, из/в которое записывается ширина столбцов
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
