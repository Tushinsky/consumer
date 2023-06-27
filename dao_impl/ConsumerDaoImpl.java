/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

import java.util.List;

/**
 *
 * @author ������
 */
public interface ConsumerDaoImpl {
    
    /**
     * ������� ������� � ��������� ������� �� ���������
     * @param ID ����� �������� ��� ��������
     * @return � ������ ������ ���������� true, ����� false
     */
    boolean deleteItem(int ID);
    
    /**
     * ���������� ������� ��������� �� ��� ������
     * @param ID ����� �������� � ���������
     * @return ������� � ��������� �������
     */
    Object getItem(int ID);
    
    /**
     * ���������� ����� ���������� ��������� ���������
     * @return ����� ���������� ��������� ���������
     */
    int getCount();
    
    /**
     * ���������� ��� ������ ��������, ��������������� ���� ������ ���� ������
     * @return ������ ����� ������ �������� �� ���� ������
     */
    Class[] getColumnClass();
    
    /**
     * ���������� ������������ �������� ������
     * @return ������ ������������ �������� ������
     */
    String[] getColumnName();
    
}
