/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.math.BigDecimal;

/**
 *
 * @author Сергей
 */
public class Sprequipment extends TableEntity {
//    private int id;
    private String equipmentType;
    private String equipmentName;
    private BigDecimal power;
    private BigDecimal flow;
    private int idmaker;
    private String tablename = "SPREQUIPMENT";

    public Sprequipment(int id) {
        super(id);
        setTablename(tablename);
        getEntity();
    }

    public Sprequipment(int id, int index) {
        super(id, index);
        setTablename(tablename);
        getEntity();
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        if(updateEntity("EQUIPMENT_TYPE", "'" + equipmentType + "'") == true)
            this.equipmentType = equipmentType;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        if(updateEntity("EQUIPMENT_NAME", "'" + equipmentName + "'") == true)
            this.equipmentName = equipmentName;
    }

    public BigDecimal getPower() {
        return power;
    }

    public void setPower(BigDecimal power) {
        if(updateEntity("POWER", power) == true)
            this.power = power;
    }

    public BigDecimal getFlow() {
        return flow;
    }

    public void setFlow(BigDecimal flow) {
        if(updateEntity("FLOW", flow) == true)
            this.flow = flow;
    }

    public int getIdmaker() {
        return idmaker;
    }

    public void setIdmaker(int idmaker) {
        if(updateEntity("IDMAKER", idmaker) == true)
            this.idmaker = idmaker;
    }

    @Override
    public Object[] toDataArray() {
        Object[] retval = new Object[6];
        retval[0] = getIndex();
        retval[1] = getMaker();
        retval[2] = equipmentName;
        retval[3] = equipmentType;
        retval[4] = power;
        retval[5] = flow;
        return retval;
    }

    @Override
    public String toString() {
        return getMaker() + " " + equipmentType + " " + equipmentName +
                "-" + power.toPlainString() + " кВт - " + flow.toPlainString() + " м куб/ч";
    }
    
    
        private void getEntity(){
        String sqlQuery = "SELECT B.IDMAKER, B.EQUIPMENT_NAME, B.EQUIPMENT_TYPE, " +
                "B.POWER, B.FLOW FROM " + tablename + " B " + "WHERE B.ID=" + getId() +";";
        Object[] retval = getFieldValues(sqlQuery);
        idmaker = (int) retval[0];
        equipmentName = (String) retval[1];
        equipmentType = (String) retval[2];
        power = (BigDecimal) retval[3];
        flow = (BigDecimal) retval[4];
        
    }
    
    private String getMaker(){
        Sprmaker maker = new Sprmaker(idmaker);
        // возвращаем производителя
        return maker.getMakerName();
    }

}
