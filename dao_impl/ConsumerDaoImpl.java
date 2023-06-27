/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

import java.util.List;

/**
 *
 * @author Сергей
 */
public interface ConsumerDaoImpl {
    
    /**
     * удаляет элемент с указанным номером из коллекции
     * @param ID номер элемента для удаления
     * @return в случае успеха возвращает true, иначе false
     */
    boolean deleteItem(int ID);
    
    /**
     * возвращает элемент коллекции по его номеру
     * @param ID номер элемента в коллекции
     * @return элемент с указанным номером
     */
    Object getItem(int ID);
    
    /**
     * возвращает общее количество элементов коллекции
     * @return общее количество элементов коллекции
     */
    int getCount();
    
    /**
     * возвращает тип данных столбцов, соответствующий типу данным базы данных
     * @return массив типов данных столбцов из базы данных
     */
    Class[] getColumnClass();
    
    /**
     * возвращает наименование столбцов данных
     * @return массив наименований столбцов данных
     */
    String[] getColumnName();
    
}
