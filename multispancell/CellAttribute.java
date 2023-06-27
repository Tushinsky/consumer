/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multispancell;

import java.awt.Dimension;

/**
 *
 * @author Sergii.Tushinskyi
 */
public interface CellAttribute {
    
    public void addColumn();

    public void addRow();

    public void insertRow(int row);

    public Dimension getSize();

    public void setSize(Dimension size);

}
