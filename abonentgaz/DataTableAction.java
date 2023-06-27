/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abonentgaz;

/**
 *
 * @author operator
 */
public interface DataTableAction {
    
    /**
     * добавление записи в таблицу
     */
    void addRecord();
    
    /**
     * удаление записи из таблицы
     */
    void deleteRecord();
    
    /**
     * экспорт данных таблицы в файл xls
     */
    void xlsExport();
    
    /**
     * экспорт данных таблицы в файл ods
     */
    void odsExport();
    
    /**
     * экспорт данных таблицы в файл csv
     */
    void csvExport();
}
