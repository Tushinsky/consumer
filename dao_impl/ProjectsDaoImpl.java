/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_impl;

import entities.Projects;
import entities.TableEntity;

/**
 *
 * @author Сергей
 */
public class ProjectsDaoImpl extends TableDaoImpl{

    private String tableName = "PROJECTS";

    public ProjectsDaoImpl(int idObject) {
        super(idObject);
        setTablename(tableName);
        setCriteria("idobject");
        getEntities();// получаем данные
    }

    @Override
    public Class[] getColumnClass() {
        Class[] retval = new Class[7];
        retval[0] = Integer.class;
        retval[1] = String.class;
        retval[2] = Short.class;
        retval[3] = String.class;
        retval[4] = String.class;
        retval[5] = String.class;
        retval[6] = String.class;
        return retval;
    }

    @Override
    public String[] getColumnName() {
        String[] retval = new String[7];
        retval[0] = "№";
        retval[1] = "Разработчик";
        retval[2] = "Год";
        retval[3] = "Инспектор";
        retval[4] = "Дата приёмки";
        retval[5] = "Узел учёта";
        retval[6] = "Оборудование";
        return retval;
    }

    @Override
    public Projects getItem(int ID) {
        TableEntity entity = super.getItem(ID);
        Projects projects = new Projects(entity.getId(), entity.getIndex());
        return projects;
    }
    
        
}
