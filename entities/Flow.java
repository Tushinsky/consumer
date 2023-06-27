/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Сергей
 */
public class Flow extends TableEntity {
//    private int id;
    private short monthnumber;
    private int flow;
    private short ptp;
    private String comments;
    private int idyear;
    private String tablename = "FLOW";

    public Flow() {
        super();
        setTablename(tablename);
    }

    public Flow(int id, int index) {
        super(id, index);
        setTablename(tablename);
    }

    public short getMonthnumber() {
        return monthnumber;
    }

    public void setMonthnumber(short monthnumber) {
        if(updateEntity("MONTHNUMBER", monthnumber))
            this.monthnumber = monthnumber;
    }

    public int getFlow() {
        return flow;
    }

    public void setFlow(int flow) {
        if(updateEntity("FLOW", flow))
            this.flow = flow;
    }

    public short getPtp() {
        return ptp;
    }

    public void setPtp(short ptp) {
        if(updateEntity("PTP", ptp))
            this.ptp = ptp;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        if(updateEntity("COMMENTS", "'" + comments + "'"))
            this.comments = comments;
    }

    public int getIdyear() {
        return idyear;
    }

    public void setIdyear(int idyear) {
        if(updateEntity("IDYEAR", idyear))
            this.idyear = idyear;
    }

    @Override
    public Object[] toDataArray() {
        Object[] retval = new Object[6];
        getEntity();
        retval[0] = getIndex();
        retval[1] = getYear();
        retval[2] = monthnumber;
        retval[3] = flow;
        retval[4] = ptp;
        retval[5] = comments;
        return retval;
    }

    public boolean updateValue(int col, Object fieldvalue) {
        String fieldname = null;
        switch(col){
            case 2:
                fieldname = "MONTHNUMBER";
                break;
            case 3:
                fieldname = "FLOW";
                break;
            case 4:
                fieldname = "PTP";
                break;
            case 5:
                fieldname = "COMMENTS";
                break;
        }
        return super.updateEntity(fieldname, fieldvalue);
    }
    
    

    private void getEntity(){
        String sqlQuery = "SELECT B.IDYEAR, " +
                "B.MONTHNUMBER, B.FLOW, B.PTP, B.COMMENTS FROM " +
                tablename + " B " + "WHERE B.ID=" + getId() +";";
        Object[] retval = getFieldValues(sqlQuery);
        idyear = (int) retval[0];
        monthnumber = (short) retval[1];
        flow = (int) retval[2];
        ptp = (short) retval[3];
        comments = (String) retval[4];
    }
    
//    private boolean updateValue(String fieldname, Object fieldvalue){
//        String sqlQuery = "UPDATE " + tablename + " B SET B." + fieldname +
//                "=" + fieldvalue + " WHERE B.ID=" + getId() + ";";
//        Runquery rq = new Runquery();
//        return rq.updateFieldValue(sqlQuery);
//    }

    private short getYear(){
        // 
        Spryear year = new Spryear(idyear);
        //
        Object[] toDataArray = year.toDataArray();
        
        // 
        return year.getNameYear();
    }
}
