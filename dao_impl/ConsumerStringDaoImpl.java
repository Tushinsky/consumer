/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

/**
 *
 * @author Sergii.Tushinskyi
 */
public interface ConsumerStringDaoImpl {
    /**
     * добавляет элемент в коллекцию
     * @param parameters массив параметров для добавления
     * @return в случае успеха возвращает true, иначе false
     */
    boolean addItem(String fieldName, String escField, String[] parameters);
    
    
}
