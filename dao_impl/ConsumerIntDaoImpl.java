/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

/**
 *
 * @author ������
 */
public interface ConsumerIntDaoImpl {
    /**
     * ��������� ������� � ���������
     * @param parameters ������ ���������� ��� ����������
     * @return � ������ ������ ���������� true, ����� false
     */
    boolean addItem(String fieldName, String escField, int[] parameters);
    
    
}
