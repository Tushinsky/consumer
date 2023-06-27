/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

/**
 *
 * @author —ергей
 */
public interface ConsumerIntDaoImpl {
    /**
     * добавл€ет элемент в коллекцию
     * @param parameters массив параметров дл€ добавлени€
     * @return в случае успеха возвращает true, иначе false
     */
    boolean addItem(String fieldName, String escField, int[] parameters);
    
    
}
