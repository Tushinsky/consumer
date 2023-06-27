/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Сергей
 */
public class Agreementstate extends TableEntity {

//    private int id;
    private int idState;
    private String stateName;
//    private int index;
    
    public Agreementstate(int id, int index) {
        super(id, index);
        super.setTablename("AGREEMENTSTATE");
        getEntity();
    }

    public Agreementstate(int id) {
        super(id);
        super.setTablename("AGREEMENTSTATE");
        getEntity();
    }
    

    public Agreementstate() {
        super();
        super.setTablename("AGREEMENTSTATE");
    }

    /**
     * @return the idState
     */
    public int getIdState() {
        return idState;
    }

    /**
     * @param idState the idState to set
     */
    public void setIdState(int idState) {
        // проверяем результат обновления данных
        if(super.updateEntity("IDSTATE", idState) == true)
            this.idState = idState;
    }

    @Override
    public Object[] toDataArray() {
        Object[] retval;
        
        retval = new Object[2];
        retval[0] = getIndex();
        retval[1] = stateName;
        return retval;
    }
    
    private void getEntity(){
        String sqlQuery = "SELECT A.IDSTATE FROM " +
                "AGREEMENTSTATE A WHERE A.ID=" + getId() + ";";
        Object[] retval = getFieldValues(sqlQuery);
        idState = (int) retval[0];
        stateName = getAgreementState();
    }
    
    private String getAgreementState(){
        Spragrimentstate agrimState = new Spragrimentstate(idState);
//        Object[] toDataArray = agrimState.toDataArray();
//        System.out.println("agrimstate=" + agrimState.getAgrimentName());
        return agrimState.getAgrimentName();
    }

    /**
     * @return the stateName
     */
    public String getStateName() {
        return stateName;
    }
    
}
