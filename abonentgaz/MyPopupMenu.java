/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abonentgaz;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 *
 * @author operator
 */
public abstract class MyPopupMenu extends JPopupMenu implements DataTableAction {
        
    public MyPopupMenu(){
        // ��������� ���� ��������
        // ���� ����� ��������� ������ ��� ����������, �������� ������� �
        // �������, ������� ������ �� ������ � ������� xls, ods, csv,
        // � ����� ����� ������� �� ������
        JMenuItem mnuAddRecord = new JMenuItem(new 
                AbstractAction("�������� ������") {

            @Override
            public void actionPerformed(ActionEvent e) {
                addRecord();
            }
        });
        JMenuItem mnuDeleteRecord = new JMenuItem(new 
                AbstractAction("������� ������") {

            @Override
            public void actionPerformed(ActionEvent e) {
                deleteRecord();
            }
        });
        JMenuItem mnuXLSExport = new JMenuItem(new 
                AbstractAction("������� ������� � ���� XLS") {

            @Override
            public void actionPerformed(ActionEvent e) {
                xlsExport();
            }
        });
        JMenuItem mnuODSExport = new JMenuItem(new 
                AbstractAction("������� ������� � ���� ODS") {

            @Override
            public void actionPerformed(ActionEvent e) {
                odsExport();
            }
        });
        JMenuItem mnuCSVExport = new JMenuItem(new 
                AbstractAction("������� ������� � ���� CSV") {

            @Override
            public void actionPerformed(ActionEvent e) {
                csvExport();
            }
        });
        add(mnuAddRecord);
        add(mnuDeleteRecord);
        addSeparator();// �������������� ����� � ����
        add(mnuXLSExport);
        add(mnuODSExport);
        add(mnuCSVExport);
        
    }

    @Override
    public void addRecord() {
        
    }

    @Override
    public void deleteRecord() {
        
    }

    @Override
    public void csvExport() {
        
    }

    @Override
    public void xlsExport() {
        
    }

    @Override
    public void odsExport() {
        
    }
    
}
