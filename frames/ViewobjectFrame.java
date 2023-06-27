/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import abonentgaz.ColumnModelListener;
import abonentgaz.TableCell_Renderer;
import abonentgaz.TableProperty;
import abonentgaz.UserProperties;
import dao_impl.TableDaoImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import runqueries.Runquery;

/**
 *
 * @author Sergii.Tushinskyi
 */
public class ViewobjectFrame extends javax.swing.JFrame {

    private TableDaoImpl objectDao;// ������ ������� � ������
    private List<ObjectEntity> resultObject;// ����� �������� ��������� ������ �� ��������
    private ColumnModelListener tableListener;// ��������� ��������� �������� ��������
    private final String fileName = "viewobjectframe.properties";// ��� ����� �������
    private UserProperties props;// ����� ��� ���������� ������� �� ����� �������
    private TableProperty tprops;// ����� ��� ��������� �������� �������� �������
    private int idOrganization;// ������������� �����������, �� ������� ���������� �������������� ��������
    private List<ObjectEntity> filterObject;// ������������� ������ ��� �������� ����������� ������ � �������
    private final String[] columnName = new String[]{"�","�����������","������������ �������","��������� �����",
                "�����","�����"};// ������������ �����
    private final Class[] columnClass = new Class[]{Integer.class,String.class,String.class,String.class,String.class,String.class};
    private final int[] col = new int[5];// ������ ��������������� �������� �������
    private final String sqlFullQuery = "select Ob.ID, Org.ORGANIZATION_NAME, Ob.NAMEOBJECT, " +
            "Spc.CITY_NAME, Sps.STREET_NAME, Ob.ADDRES from Organization Org inner join " +
            "Objects Ob on Org.ID=Ob.IDORGANIZATION inner join Sprcity Spc on " +
            "Ob.IDCITY=Spc.ID inner join Sprstreet Sps on Ob.IDSTREET=Sps.ID order by Org.ID;";
    private final String sqlOrganizationQuery = "select Ob.ID, Org.ORGANIZATION_NAME, Ob.NAMEOBJECT, " +
            "Spc.CITY_NAME, Sps.STREET_NAME, Ob.ADDRES from Organization " +
            "Org inner join Objects Ob on Org.ID=Ob.IDORGANIZATION inner join " +
            "Sprcity Spc on Spc.ID=Ob.IDCITY inner join Sprstreet Sps on " +
            "Sps.ID=Ob.IDSTREET where lower(Org.ORGANIZATION_NAME) like '%";
    private final String sqlAddresQuery = "select Ob.ID, Org.ORGANIZATION_NAME, Ob.NAMEOBJECT, Spc.CITY_NAME, " +
            "Sps.STREET_NAME, Ob.ADDRES from Organization Org inner join Objects Ob " +
            "on Org.ID=Ob.IDORGANIZATION inner join Sprcity Spc on Ob.IDCITY=Spc.ID " +
            "inner join Sprstreet Sps on Ob.IDSTREET=Sps.ID where " +
            "lower(Spc.CITY_NAME || ', ' || Sps.STREET_NAME || ', ' || Ob.ADDRES) like '%";
    private final String sqlNameQuery = "select Ob.ID, Org.ORGANIZATION_NAME, Ob.NAMEOBJECT, " +
            "Spc.CITY_NAME, Sps.STREET_NAME, Ob.ADDRES from Organization Org inner join " +
            "Objects Ob on Org.ID=Ob.IDORGANIZATION inner join Sprcity Spc on " +
            "Ob.IDCITY=Spc.ID inner join Sprstreet Sps on Ob.IDSTREET=Sps.ID where lower(Ob.NAMEOBJECT) like '%";
    private boolean change = false;// ��������� �������� ����� ��������� ��������������
    
    /**
     * Creates new form ViewobjectFrame
     */
    public ViewobjectFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        OKButton = new javax.swing.JButton();
        CancelButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuRecord = new javax.swing.JMenu();
        mnuRecordChange = new javax.swing.JMenuItem();
        mnuRecordFind = new javax.swing.JMenu();
        mnuRecordFindAdress = new javax.swing.JMenuItem();
        mnuRecordFindName = new javax.swing.JMenuItem();
        mnuRecordFindReset = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("�������� ��������");
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                formComponentHidden(evt);
            }
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable1.setCellSelectionEnabled(true);
        jScrollPane1.setViewportView(jTable1);

        jPanel1.setPreferredSize(new java.awt.Dimension(138, 29));

        OKButton.setText("OK");
        OKButton.setToolTipText("�������� �������������� �������");
        OKButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        OKButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OKButtonActionPerformed(evt);
            }
        });

        CancelButton.setText("�����");
        CancelButton.setToolTipText("������� ����");
        CancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(OKButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(CancelButton)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CancelButton)
                    .addComponent(OKButton))
                .addGap(0, 6, Short.MAX_VALUE))
        );

        mnuRecord.setText("������");

        mnuRecordChange.setText("�������� ��������������");
        mnuRecordChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuRecordChangeActionPerformed(evt);
            }
        });
        mnuRecord.add(mnuRecordChange);

        mnuRecordFind.setText("����� ������");

        mnuRecordFindAdress.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        mnuRecordFindAdress.setText("����� �� ������");
        mnuRecordFindAdress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuRecordFindAdressActionPerformed(evt);
            }
        });
        mnuRecordFind.add(mnuRecordFindAdress);

        mnuRecordFindName.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        mnuRecordFindName.setText("����� �� ��������");
        mnuRecordFindName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuRecordFindNameActionPerformed(evt);
            }
        });
        mnuRecordFind.add(mnuRecordFindName);

        mnuRecordFindReset.setText("�������� �������� ������");
        mnuRecordFindReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuRecordFindResetActionPerformed(evt);
            }
        });
        mnuRecordFind.add(mnuRecordFindReset);

        mnuRecord.add(mnuRecordFind);

        jMenuBar1.add(mnuRecord);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 872, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 852, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void OKButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OKButtonActionPerformed
        // ��� ������� ������ �� ������� ����������� � ������������
        changeOrganization();
    }//GEN-LAST:event_OKButtonActionPerformed

    private void CancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelButtonActionPerformed
        // ��� ������� ������ ������ �������� �����
        setVisible(false);
    }//GEN-LAST:event_CancelButtonActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        try{
            // ������ ����� ��� ������ �������
            props = new UserProperties(fileName);
            tprops = new TableProperty(jTable1);

//            // ��������� ������� �������� �� ����� �������
//            String colWidth = props.getProperty("colwidth");
//
//            // � ������������� �� ��� �������
//            tprops.setDefaultWidth(colWidth);
//            tprops.setColWidth();

            // ����� ������ �������� �������
            MDIObject.setTablecolwidth(props, "colwidth", jTable1);
        } catch(Exception ex){
            Logger.getLogger(ViewobjectFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        // ������ ��������� ��������� �������� �������� ������� � ��������� ��� � �������
        tableListener = new ColumnModelListener(jTable1, props, "colwidth");
        jTable1.getColumnModel().addColumnModelListener(tableListener);
        
        // ��������� ����������� ��� ������� ������� ������� � ��������� ��� �������
        // ������� � ����� �������
        TableCell_Renderer.setIntegerTableCellRenderer(jTable1, null);
        MDIObject.addColumnSelectionListener(jTable1);
        
        
    }//GEN-LAST:event_formComponentShown

    private void formComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentHidden
        // ������� ���������
        jTable1.getColumnModel().removeColumnModelListener(tableListener);
        
        // ��������� ��� ������
        props = null;
        tprops = null;
        tableListener = null;
        objectDao = null;
        resultObject = null;
    }//GEN-LAST:event_formComponentHidden

    private void mnuRecordFindAdressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuRecordFindAdressActionPerformed
        // ����� ������ �� ������ �������
        // 1 - ������ ������������� ������ ��� �������� ����������� ������
        // 2 - ���������� ��� ������ �� ������� ���������� � �������� ������
        // 3 - ���� ������������ ����������, �������� ������ ���������� � ���� �������
        // 4 - ���� ���������� ����, ��� ��������� ����� ������� ������ ��������� ��� ��
        // ��� ��������� ������ ��������
        
        // ���� ����� �������
        String template;
        template = JOptionPane.showInputDialog(this,"������� ������� ��� ������", 
                "����� �� ������", JOptionPane.INFORMATION_MESSAGE);
        
        // ��������� �������� ������
        if(!template.isEmpty()){
            String sqlString = sqlAddresQuery + template + "%' order by Org.ID;";
            // ��������� ������� �������� �� ����� �������
            String colWidth = tprops.getColWidth();
            
            getObjects(sqlString);
            // � ������������� �� ��� �������
            tprops.setColWidth();
            jTable1.getColumnModel().addColumnModelListener(tableListener);
            
            mnuRecordFindReset.setEnabled(true);
        }
    }//GEN-LAST:event_mnuRecordFindAdressActionPerformed

    private void mnuRecordFindNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuRecordFindNameActionPerformed
        // ����� ������� �� ������������, ����������� �����, ���
        // � �� ������
        // ���� ����� �������
        String template;
        template = JOptionPane.showInputDialog(this, "������� ������� ��� ������", 
                "����� �� ��������", JOptionPane.QUESTION_MESSAGE);
        
        // ��������� �������� ������
        if(!template.isEmpty()){
            String sqlString = sqlNameQuery + template + "%' order by Org.ID;";
            // ��������� ������� �������� �� ����� �������
            String colWidth = tprops.getColWidth();
            
            getObjects(sqlString);
            // � ������������� �� ��� �������
            tprops.setColWidth();
            jTable1.getColumnModel().addColumnModelListener(tableListener);
            
            mnuRecordFindReset.setEnabled(true);
        }
    }//GEN-LAST:event_mnuRecordFindNameActionPerformed

    private void mnuRecordFindResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuRecordFindResetActionPerformed
        // ��������������� �������� ������
        // ��������� ������� �������� �� ����� �������
        String colWidth = tprops.getColWidth();

        getObjects(sqlFullQuery);
        // � ������������� �� ��� �������
        tprops.setColWidth();
        jTable1.getColumnModel().addColumnModelListener(tableListener);

        mnuRecordFindReset.setEnabled(false);// ��������� ����� ���� ����� �������
    }//GEN-LAST:event_mnuRecordFindResetActionPerformed

    private void mnuRecordChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuRecordChangeActionPerformed
        // �������� �������������� ���������� �������
        changeOrganization();
    }//GEN-LAST:event_mnuRecordChangeActionPerformed

    private void mnuRecordFindOrganizationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuRecordFindOrganizationActionPerformed
        // ����� ������� �� ������������ ����������� ����������� �����, ���
        // � �� ������
        // ���� ����� �������
        String template;
        template = JOptionPane.showInputDialog(this, "������� ������� ��� ������", 
                "����� �����������", JOptionPane.QUESTION_MESSAGE);
        
        // ��������� �������� ������
        if(!template.isEmpty()){
            String sqlString = sqlOrganizationQuery + template + "%' order by Org.ID;";
            
            // ��������� ������� �������� �� ����� �������
            String colWidth = tprops.getColWidth();
            
            getObjects(sqlString);
            // � ������������� �� ��� �������
            tprops.setColWidth();
            jTable1.getColumnModel().addColumnModelListener(tableListener);
            
            mnuRecordFindReset.setEnabled(true);
        }
    }//GEN-LAST:event_mnuRecordFindOrganizationActionPerformed

    /**
     * ���������� ������� ������� � ��������� �������� ��������
     */
    private void setcolwidth(){
        // ��������� ������� �������� �� ����� �������
        String colWidth = tprops.getColWidth();

        // � ������������� �� ��� �������
        tprops.setColWidth();
        jTable1.getColumnModel().addColumnModelListener(tableListener);
    }
    
    private void changeOrganization(){
        int button = JOptionPane.showConfirmDialog(ViewobjectFrame.this, 
                "�������� �������������� ���������� �������?", "AbonentGaz", JOptionPane.YES_NO_OPTION);
        // ��������� ����� ������������
        if(button == JOptionPane.YES_OPTION){
            int row = jTable1.getSelectedRow();// ����� ��������� ������ � �������
            // �������� ��������� ��������� ��������
            ObjectEntity entity = resultObject.get(row);
//            System.out.println("index=" + entity.getIndex());
            if(entity.setIdOrganization(idOrganization)){
                MDIObject.getInformDialog(ViewobjectFrame.this, 
                        "Change...", InformDialog.InformType.SAVING);// ����� ����� �������� ���� �����������
                change = true;// ������������� ���� ��������� ��������������
            }
            else
                JOptionPane.showMessageDialog(null, 
                        "��������� ������ �� ����� �������� ����������!", 
                        "AbonentGaz", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | 
                IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewobjectFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ViewobjectFrame().setVisible(true);
            }
        });
    }
    
    /**
     * ����� ��� ���������� �������� �������� � ���������� ����������
     */
    private class ObjectEntity{
        
        private final int index;// ������ �������� � ��������� (���������� �����)
        private int idOrganization;// ������������� �����������
        private int id;// ������������� �������
        private String name;// ������������ �������
        private String city;// ��������� �����
        private String street;// �����
        private String addres;// �����
        private String organization;// ������������ �����������

        public ObjectEntity(int index) {
            this.index = index;
        }
        

        /**
         * @param id the id to set
         */
        public void setId(int id) {
            this.id = id;
        }

        /**
         * @param name the name to set
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * @param city the city to set
         */
        public void setCity(String city) {
            this.city = city;
        }

        /**
         * @param street the street to set
         */
        public void setStreet(String street) {
            this.street = street;
        }

        /**
         * @param addres the addres to set
         */
        public void setAddres(String addres) {
            this.addres = addres;
        }
        
        public Object[] toDataArray(){
            Object[] result = new Object[6];
            result[0] = index;
            result[1] = organization;
            result[2] = name;
            result[3] = city;
            result[4] = street;
            result[5] = addres;
            return result;
        }
        
        /**
         * ��������� ������ �� ������� � ��� ������ ��������� - �������
         * @param template ������ ��� ���������
         * @return true ���� ���������� �������, � ��������� ������ false
         */
        public boolean containsAddres(String template){
            String fullAddres;
            CharSequence cs = template.subSequence(0, template.length());
            fullAddres = (city + ", " + street + ", " + addres).toLowerCase();
            return fullAddres.contains(cs);
        }
        
        /**
         * ��������� ������ �� ������� � ��� ������ ��������� - �������
         * @param template ������ ��� ���������
         * @return true ���� ���������� �������, � ��������� ������ false
         */
        public boolean containsName(String template){
            CharSequence cs = template.subSequence(0, template.length());
            return name.toLowerCase().contains(cs);
        }
        
        public boolean containsOrganization(String template){
            CharSequence cs = template.subSequence(0, template.length());
            return organization.toLowerCase().contains(cs);
        }

        /**
         * @param idOrganization the idOrganization to set
         */
        public boolean setIdOrganization(int idOrganization) {
            this.idOrganization = idOrganization;
            return changeOrganization();
            
        }
        
        private boolean changeOrganization(){
            // ������ - ������ �� ��������� ������
            String sqlOrganizationQuery = "UPDATE OBJECTS B SET B.IDORGANIZATION=" + 
                    idOrganization + " WHERE B.ID=" + id + ";";
            System.out.println("sql=" + sqlOrganizationQuery);
            Runquery rq = new Runquery();// ������ ��� ���������� ��������
            
            // ��������� ��������� ���������� ������� �� ���������� ������
            // ���������� ������ ���������� false, ������� �� ��������� ���� NOT FALSE
            return !rq.updateFieldValue(sqlOrganizationQuery);
            
        }

        /**
         * @return the index
         */
        public int getIndex() {
            return index;
        }

        /**
         * @param organization the organization to set
         */
        public void setOrganization(String organization) {
            this.organization = organization;
        }
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CancelButton;
    private javax.swing.JButton OKButton;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JMenu mnuRecord;
    private javax.swing.JMenuItem mnuRecordChange;
    private javax.swing.JMenu mnuRecordFind;
    private javax.swing.JMenuItem mnuRecordFindAdress;
    private javax.swing.JMenuItem mnuRecordFindName;
    private javax.swing.JMenuItem mnuRecordFindReset;
    // End of variables declaration//GEN-END:variables

    /**
     * @param idOrganization the idOrganization to set
     */
    public void setIdOrganization(int idOrganization) {
        this.idOrganization = idOrganization;
        getObjects(sqlFullQuery);
    }
    
    private void getObjects(String query){
        Runquery rq = new Runquery();// ������ ��� ���������� ��������
//        System.out.println(query);
        List<Object[]> entities = rq.getQueryEntities(query);// �������� ������ ��� ���������� �������
        
        // ��������� �� �������
        int count = entities.size();
        if(count > 0){
            resultObject = new ArrayList<>();
            Object[][] content = new Object[count][];// ���������� ������ ������� ��� ���������� �������
            for(int i = 0; i < col.length; i++)
                col[i] = i;
            for(int i = 0; i < count; i++){
                Object[] ent = entities.get(i);
                // ������ ��������� ���������
                ObjectEntity entity = new ObjectEntity((int) ent[0]);
                // �������� ������ �� ����� ���������� ��� ���������� �������
                entity.setId(Integer.parseInt((String) ent[1]));
                entity.setOrganization((String) ent[2]);
                entity.setName((String) ent[3]);
                entity.setCity((String) ent[4]);
                entity.setStreet((String) ent[5]);
                entity.setAddres((String) ent[6]);
                resultObject.add(entity);// ��������� � ���������
                content[i] = entity.toDataArray();
            }
            
            // ������ ������ � ��������� ������� �������
            MyTableModel Model = new MDIObject.MyTableModelImpl(content, columnName, columnClass);
            jTable1.getColumnModel().removeColumnModelListener(tableListener);
            MDIObject.fullTableData(col, Model, jTable1);
            // ����� ������� �� ������ � ������� �������
            TableCell_Renderer.setWordWrapCellRenderer(jTable1, columnClass);
        }
    }

    /**
     * @return the change
     */
    public boolean isChange() {
        return change;
    }

}
