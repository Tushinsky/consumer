/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import abonentgaz.ColumnModelListener;
import abonentgaz.FrameLayoutManager;
import abonentgaz.TableProperty;
import abonentgaz.UserProperties;
import com.sun.glass.events.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Sergii.Tushinskyi
 */
public class FindFrame extends javax.swing.JFrame {

    private FrameLayoutManager flManager;// ����� ��� ������ ������� �� ����� �������
    private final String queryfilename;// ��� ����� ������� � ��������� �� �����
    private Queries queries;// ����� ��� ��������� ������
    private MDIObject parentFrame;
    private ColumnModelListener FindmodelListener;// ��������� ��������� �������� �������
    private final String filename;
    private UserProperties props;
    /**
     * Creates new form FindFrame
     */
    public FindFrame() {
        queryfilename = "queryfind.properties";
        filename = "findframe.properties";
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

        jPanel1 = new javax.swing.JPanel();
        findButton = new javax.swing.JButton();
        okButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        txtFind = new javax.swing.JTextField();
        findCombo = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        findTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("�����");
        setSize(new java.awt.Dimension(698, 311));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentMoved(java.awt.event.ComponentEvent evt) {
                formComponentMoved(evt);
            }
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        findButton.setText("�����");
        findButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findButtonActionPerformed(evt);
            }
        });

        okButton.setText("�������");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        closeButton.setText("�������");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        txtFind.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFindKeyPressed(evt);
            }
        });

        findCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "� ��������", "� ����������", "� ��������", "� ������������� ������", "� �������� ������", "������ �� ��������", "������ �� ������", "�����������" }));
        findCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findComboActionPerformed(evt);
            }
        });

        findTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "��������� 1", "��������� 2", "��������� 3", "��������� 4"
            }
        ));
        findTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        findTable.setCellSelectionEnabled(true);
        findTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        findTable.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                findTablePropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(findTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(findCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtFind, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(findButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(okButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(closeButton)
                .addContainerGap(84, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(findCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(findButton)
                    .addComponent(okButton)
                    .addComponent(closeButton))
                .addContainerGap(194, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addGap(47, 47, 47)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void findComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findComboActionPerformed
        // ��� ������ ��������� ������ ������� ���� ����� �������� ��������
        txtFind.setText("");
    }//GEN-LAST:event_findComboActionPerformed

    private void txtFindKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFindKeyPressed
        // ��� ������� ������� ����� ��������� �����
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            findItem();
    }//GEN-LAST:event_txtFindKeyPressed

    private void findButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findButtonActionPerformed
        // ��������� ��������� ������
        findItem();
    }//GEN-LAST:event_findButtonActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        // ���� ������� ������ � �������, ������������ � ������ � ��������� �����
        int ID;// ��� ������
        int row;// ����� ��������� ������
        if(findTable.getSelectedRowCount() > 0){
            row = findTable.getSelectedRow();// ����� ��������� ������
        
        } else {
//            JOptionPane.showMessageDialog(FindFrame.this, "�������� ���� �����!", 
//                    "AbonentGaz", JOptionPane.WARNING_MESSAGE);
            // ���� ������������ ����� ������� ������ � �������, ��
            // �� ��������� �������� ������ ������
            row = 0;
        }
        ID = Integer.parseInt((String)findTable.getValueAt(row, 1));
        parentFrame.findOrganization(ID);// ���� ����������� �� ����
    }//GEN-LAST:event_okButtonActionPerformed

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        // �������� ����
        setVisible(false);
    }//GEN-LAST:event_closeButtonActionPerformed

    private void formComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentMoved
        // ��� ����������� ����� ���������� ����� �������������� � ���� �������
//        if(flManager != null){
//            try {
//                flManager.getFrameLocation(this);
//            } catch (Exception ex) {
//                Logger.getLogger(FindFrame.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
    }//GEN-LAST:event_formComponentMoved

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // ��������� ���������� ����� �� ����� �������
        flManager = new FrameLayoutManager("findframe.properties");
        // ��������� ������ �������
        okButton.setEnabled(false);
        
        // ����� �������������� ����� �� ������
//        setLocation(flManager.setFrameLocation());
        
        // ����� ������� �����
        setSize(flManager.setFrameSize());
        
        // ������ ����� ��� ���������� ����� - �������� � ��������� ������
        queries = new Queries();
        props = new UserProperties(filename);
//        tprops = new TableProperty(findTable);
        
        // ������ ������� �������� ������� �� ����� �������
//        MDIObject.setTablecolwidth(props, "0", findTable);
        
        // ������ ��������� ��������
        FindmodelListener = new ColumnModelListener(findTable, props, "0");
        findTable.getColumnModel().addColumnModelListener(FindmodelListener);
    }//GEN-LAST:event_formComponentShown

    private void findTablePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_findTablePropertyChange
        // ��� ����� ������ ������� ��������� ��������
        if(evt.getPropertyName().equals("model"))
            findTable.getColumnModel().removeColumnModelListener(FindmodelListener);
    }//GEN-LAST:event_findTablePropertyChange

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
//         ��� ��������� �������� ����� ��������� �� � ����� �������
        if(flManager != null){
            try {
                flManager.getFrameSize(this);
            } catch (Exception ex) {
                Logger.getLogger(FindFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_formComponentResized

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
        } catch (ClassNotFoundException | 
                InstantiationException | IllegalAccessException | 
                javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FindFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FindFrame().setVisible(true);
            }
        });
    }
    
    /**
     * ��������� ��������� ������ � ��������� ������� ����������� �������
     */
    private void findItem(){
        int index = findCombo.getSelectedIndex();// ����� ���������� �������� � ������
        queries.setIndex(index);
        queries.setCriteria(txtFind.getText());
        if(queries.getEntities() == true){
//            System.out.println("���������� �������");
            okButton.setEnabled(true);// ������������ ������ �������
            
            // ��������� �������
            MyTableModel model = new MDIObject.MyTableModelImpl(queries.getContent(), 
                    queries.getColumnName(), queries.getColumnClass());
            
            // ��������� ������� �������
            MDIObject.fullTableData(null, model, findTable);
            MDIObject.setTablecolwidth(props, String.valueOf(index), findTable);
            
            // ������ ��������� ��������
            FindmodelListener = new ColumnModelListener(findTable, props, String.valueOf(index));
            findTable.getColumnModel().addColumnModelListener(FindmodelListener);
        } else {
            JOptionPane.showMessageDialog(FindFrame.this, 
                    "������� ������ �� �������!", "AbonentGaz", 
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private class Queries extends dao_impl.QueriesDao{
        private final Object[] criteria;// �������� ������
//        private Object[][] content;
//        private String[] columnName;
//        private Class[] columnClass;
        
        // ����� ��� ������ ������� �� ����� �������
        private final UserProperties props;
        private String sqlString;// ������-������ �� �������

        public Queries() {
            super();
//            this.criteria = null;
            this.criteria = new Object[1];
            
            // ������ ����� ��� ������ �� ����� �������
            props = new UserProperties(queryfilename);
        }

        /**
         * ���������� ������, ���������� � ���������� ���������� �������
         * @return the content ������ ������ �������
         */
        @Override
        public Object[][] getContent() {
            return super.getContent();
        }

        /**
         * ���������� ����� �������� �������
         * @return the columnName ������ ���� ��������
         */
        @Override
        public String[] getColumnName() {
            return super.getColumnName();
        }

        /**
         * ���������� ���� (������) ������ �������� �������
         * @return the columnClass ������ ����� ������ ��������
         */
        @Override
        public Class[] getColumnClass() {
            return super.getColumnClass();
        }
        
        /**
         * �������� ������ ������ �� ��������� ��������
         * @return true ���� ����� �������� ������, � ��������� ������ ���������� false
         * @param sqlString - ��������� ������������� �������
         */
        @Override
        public boolean getEntities(){
            return super.getEntities();
        }
        
        /**
         * ��������� �������� � �������� ������ �� ����� �������
         * @param index ��� ����� �� ����� �������
         * @return ��������� ������������� ������� �� ��������, ������������ ������
         */
        private String getQuery(int index){
            String retval;// ������������ �������� ���������� ������������� �������
            retval = props.getProperty(String.valueOf(index));
            return retval;
            
        }


        /**
         * ����� ��� ����� �� ����� �������
         * @param index the index to set
         */
        public void setIndex(int index) {
            sqlString = getQuery(index);// �������� ������ - ������ �� ����� �������
            super.setSqlQuery(sqlString);
//            System.out.println(sqlString);
        }

        /**
         * ����� �������� ������� ������ � �������
         * @param criteria the criteria to set
         */
        public void setCriteria(Object criteria) {
//            this.criteria = new Object[1];
            this.criteria[0] = criteria;
            super.setCriteria(this.criteria);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JButton findButton;
    private javax.swing.JComboBox<String> findCombo;
    private javax.swing.JTable findTable;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton okButton;
    private javax.swing.JTextField txtFind;
    // End of variables declaration//GEN-END:variables

    /**
     * @param parentFrame the parentFrame to set
     */
    public void setParentFrame(MDIObject parentFrame) {
        this.parentFrame = parentFrame;
    }
}