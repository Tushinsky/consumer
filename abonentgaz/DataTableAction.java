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
     * ���������� ������ � �������
     */
    void addRecord();
    
    /**
     * �������� ������ �� �������
     */
    void deleteRecord();
    
    /**
     * ������� ������ ������� � ���� xls
     */
    void xlsExport();
    
    /**
     * ������� ������ ������� � ���� ods
     */
    void odsExport();
    
    /**
     * ������� ������ ������� � ���� csv
     */
    void csvExport();
}
