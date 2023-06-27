/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import abonentgaz.ConvertNumber;
import abonentgaz.FrameLayoutManager;
import dao_impl.SpryearDaoImpl;
import entities.Spryear;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.geom.AffineTransform;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author lera
 */
public class ActFrame extends javax.swing.JFrame {

    /**
     * @param nameOrganization the nameOrganization to set
     */
    public void setNameOrganization(String nameOrganization) {
        this.nameOrganization = nameOrganization;
    }

    /**
     * @param Director the Director to set
     */
    public void setDirector(String Director) {
        this.Director = Director;
    }

    private CardLayout clManager;// �������� ��������� ���������� ��� ������
    private PrintSetupPanel printPanel = null;
    private ShowCountPanel countPanel = null;
    private int idCompany;
    private ConvertNumber converter;
//    private Font f;
//    private FontRenderContext context;
//    private Rectangle2D bound;
//    private double stringWidth;// ������ ������
    private AffineTransform atf;
    private String[] monthReport;// ���������� ������������ ������� ������
    private FrameLayoutManager flManager;// �������� ������������ � �������� �����
    private int currentMonth;// ������� �����
    private SpryearDaoImpl yearDao;// ������ ������� � ����������� ���
    private String nameOrganization;
    private String Director;
    private String Agreement;
    private PreviewActFrame previewframe;// ���� ���������������� ���������
    private JLabel lblView;// �����, �� ������� ����� ���������� ����� � ����� � ��������
    
    /**
     * Creates new form ActFrame
     */
    public ActFrame() {
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtFlow = new javax.swing.JTextField();
        txtPTP = new javax.swing.JTextField();
        cmbMonth = new javax.swing.JComboBox();
        cmbYear = new javax.swing.JComboBox();
        txtReportDate = new javax.swing.JTextField();
        lblFlow = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        mnuPrintDoc = new javax.swing.JMenuItem();
        mnuPrintSetupMore = new javax.swing.JCheckBoxMenuItem();
        jMenu4 = new javax.swing.JMenu();
        chkmenuSetupAutoCalc = new javax.swing.JCheckBoxMenuItem();
        chkmenuSetupAutoSave = new javax.swing.JCheckBoxMenuItem();
        mnuFileTableshow = new javax.swing.JCheckBoxMenuItem();
        mnuFileSave = new javax.swing.JMenuItem();
        mnuFilePreview = new javax.swing.JMenuItem();
        mnuFileExit = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("��� �����-��������");
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                formComponentHidden(evt);
            }
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
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("�����");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("���");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("�������� ����� � ���");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("���� ������");

        txtFlow.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtPTP.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        cmbMonth.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "������", "�������", "����", "������", "���", "����", "����", "������", "��������", "�������", "������", "�������" }));
        cmbMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMonthActionPerformed(evt);
            }
        });

        cmbYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbYearActionPerformed(evt);
            }
        });

        txtReportDate.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtReportDate.setText("31 �������� 2020 �");

        lblFlow.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFlow.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblFlow.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblFlow.setOpaque(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 16, Short.MAX_VALUE)
        );

        jMenu1.setText("����");

        jMenu3.setText("������");

        mnuPrintDoc.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        mnuPrintDoc.setText("��������");
        mnuPrintDoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuPrintDocActionPerformed(evt);
            }
        });
        jMenu3.add(mnuPrintDoc);

        mnuPrintSetupMore.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        mnuPrintSetupMore.setText("�������������");
        mnuPrintSetupMore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuPrintSetupMoreActionPerformed(evt);
            }
        });
        jMenu3.add(mnuPrintSetupMore);

        jMenu1.add(jMenu3);

        jMenu4.setText("���������");

        chkmenuSetupAutoCalc.setSelected(true);
        chkmenuSetupAutoCalc.setText("��������������");
        chkmenuSetupAutoCalc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkmenuSetupAutoCalcActionPerformed(evt);
            }
        });
        jMenu4.add(chkmenuSetupAutoCalc);

        chkmenuSetupAutoSave.setText("�������������� ���������");
        chkmenuSetupAutoSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkmenuSetupAutoSaveActionPerformed(evt);
            }
        });
        jMenu4.add(chkmenuSetupAutoSave);

        jMenu1.add(jMenu4);

        mnuFileTableshow.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        mnuFileTableshow.setText("������� ���������");
        mnuFileTableshow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuFileTableshowActionPerformed(evt);
            }
        });
        jMenu1.add(mnuFileTableshow);

        mnuFileSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        mnuFileSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/saveHS.png"))); // NOI18N
        mnuFileSave.setText("���������");
        mnuFileSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuFileSaveActionPerformed(evt);
            }
        });
        jMenu1.add(mnuFileSave);

        mnuFilePreview.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        mnuFilePreview.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/find16.png"))); // NOI18N
        mnuFilePreview.setText("��������������� ��������");
        mnuFilePreview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuFilePreviewActionPerformed(evt);
            }
        });
        jMenu1.add(mnuFilePreview);

        mnuFileExit.setText("�����");
        mnuFileExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuFileExitActionPerformed(evt);
            }
        });
        jMenu1.add(mnuFileExit);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtFlow, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPTP, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmbMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbYear, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtReportDate, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addComponent(lblFlow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFlow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPTP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtReportDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblFlow, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // ����� ������� ����� ��� ����������� �� ������
        flManager = new FrameLayoutManager("actframe.properties");
        setSize(flManager.getWidth(), 152);
        setLocation(flManager.setFrameLocation());
        // ������ �������� ��������� ���������� ��� ������
        clManager = new CardLayout();
        jPanel1.setLayout(clManager);
        jPanel1.setVisible(false);
        // ������ ������ � ����������� � ��������������� ����������� ������
        printPanel = new PrintSetupPanel();
        countPanel = new ShowCountPanel();
        // ��������� �� �� ������
        jPanel1.add(printPanel, "SetupPrintPanel");
        jPanel1.add(countPanel, "CountPanel");
        
        // ��������� ������ ���������� ������������ ������� ������
        String [] month = {"������","�������","�����","������","���","����",
            "����","�������","��������","�������","������","�������"};
        monthReport = month;
        
        // ������ ������ ������� � ����������� ��� ��� ���������� ������
        yearDao = new SpryearDaoImpl();
        
        // ���������� ������� ��� � ���������� ��� � ������� �� �����������
        GregorianCalendar now = new GregorianCalendar();
        int year = now.get(Calendar.YEAR);
        boolean find = false;// ���� ������ ������
        for(int i = 0; i < yearDao.getCount(); i++){
            short y = yearDao.getItem(i).getNameYear();
//            System.out.println("year=" + y);
            if(year == (int) y){
                find = true;
                break;
            }
        }
        // ��������� ��������� ���������, ���� ���������� �� �������
        // ���������� ������������ � ������������� �������� ������ � ����� �����
        if(find != true)
            JOptionPane.showMessageDialog(null, "� ����������� ����������� ������ � ������� ����� ������.\n" +
                    "�������� ����� ������.");
        // ��������� ������ ��� - ���������� � 2015 ����
        for (int i = 0; i < yearDao.getCount(); i++){
            short y = yearDao.getItem(i).getNameYear();
            cmbYear.addItem(y);
        }
        // ���������� ������� ����� � ���������� ��� � ������
        currentMonth = now.get(Calendar.MONTH);
        cmbMonth.setSelectedIndex(currentMonth);
        
        // ������� � ������ ��������� ���
        cmbYear.setSelectedIndex(yearDao.getCount() - 1);
        
        
        // ������ ����������
        converter = new ConvertNumber(ConvertNumber.Language.LANGRUSS);
        
        // ������ ��������� ��������� ��� ������������ ��������� � ���� �����
        DocumentListener docListener = new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                convertNumberToString();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                convertNumberToString();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                convertNumberToString();
            }
        };
        // � ��������� ��� � ���� ����� ������
        txtFlow.getDocument().addDocumentListener(docListener);
        
        // ������ ��������� ��������� � ������� ��������� � ������� ��� ���� �����
        countPanel.addPropertyChangeListener("name", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent pce) {
                //To change body of generated methods, choose Tools | Templates.
                txtFlow.setText(Integer.toString(countPanel.getFlowRate()));
            }
        });
        // �������� �����, ������� ������������ ����� �� �����
//        f = lblFlow.getFont();
        atf = new AffineTransform();
        // �������� ����������� ������, ������� ������ �����
//        context = new FontRenderContext(atf, true, true);
        JOptionPane.showMessageDialog(null, "��� ��������������� ���������� ��������� ���������� \n" +
                "������ <����->���������->�������������� ���������>", "AbonentGaz", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_formComponentShown

    private void formComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentMoved
            // ��� ����������� ����� ���������� ����� �������������� � ���� �������
        if(flManager != null){
            try {
                flManager.getFrameLocation(this);
            } catch (Exception ex) {
                Logger.getLogger(ActFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_formComponentMoved

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        // ��� ��������� �������� ����� ���������� �� � ���� �������
        if(flManager != null){
            try {
                // ���������� � ���� ������ ��������� ������ �����
                flManager.setWidth(this.getWidth());
                
            } catch (Exception ex) {
                Logger.getLogger(ActFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        // ������ ������������ ������� ������ �����
        if(txtFlow.getText().length() > 0) convertNumberToString();
    }//GEN-LAST:event_formComponentResized

    private void formComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentHidden
        // TODO add your handling code here:
        previewframe.setVisiblePreview(false);
        previewframe = null;
    }//GEN-LAST:event_formComponentHidden

    private void mnuFileTableshowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuFileTableshowActionPerformed
        // ���� ���������� ������ ���������� ��� �������� ������� ���������
        if(mnuFileTableshow.isSelected()){
            // ������� ������ � ���� �������������� �������� ������
            mnuPrintSetupMore.setSelected(false);
            showPanel("CountPanel");// ���������� ������ � �������� ���������
            set_CompanyFilter();// ��������� ������� ���������
        } else{
            this.setSize(getWidth(), 152);
            jPanel1.setVisible(false);
        }
    }//GEN-LAST:event_mnuFileTableshowActionPerformed

    private void mnuPrintSetupMoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuPrintSetupMoreActionPerformed
        // ���� ���������� ������ ���������� ��� �������� ������ ��������������
        // �������� ������
        if(mnuPrintSetupMore.isSelected()){
            // ������� ������ � ���� ������� ���������
            mnuFileTableshow.setSelected(false);
            showPanel("SetupPrintPanel");
        } else{
            this.setSize(getWidth(), 152);
            jPanel1.setVisible(false);
        }
    }//GEN-LAST:event_mnuPrintSetupMoreActionPerformed

    private void cmbMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMonthActionPerformed
        // ��� ������ ������ �������� �������� ���� ������
        
        currentMonth = cmbMonth.getSelectedIndex();
        if(countPanel.isVisible()) countPanel.setReportMonth(currentMonth);
        setreportDate();
    }//GEN-LAST:event_cmbMonthActionPerformed

    private void cmbYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbYearActionPerformed
        // ��� ������ ������ �������� �������� ���� ������
        Spryear entity = yearDao.getItem(cmbYear.getSelectedIndex());
        short y = entity.getNameYear();
        
        if(countPanel.isVisible()) countPanel.setReportYear(entity.getId());
        
        setreportDate();
    }//GEN-LAST:event_cmbYearActionPerformed

    private void mnuFileSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuFileSaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mnuFileSaveActionPerformed

    private void chkmenuSetupAutoCalcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkmenuSetupAutoCalcActionPerformed
        // ������������� ��� ������� ���� �������������� �������
        countPanel.setAutoCalc(chkmenuSetupAutoCalc.isSelected());
    }//GEN-LAST:event_chkmenuSetupAutoCalcActionPerformed

    private void chkmenuSetupAutoSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkmenuSetupAutoSaveActionPerformed
        // ������������� ��� ������� ���� �������������� ���������
        countPanel.setAutoSave(chkmenuSetupAutoSave.isSelected());
    }//GEN-LAST:event_chkmenuSetupAutoSaveActionPerformed

    private void mnuFilePreviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuFilePreviewActionPerformed
        // ���������� ���� ���������������� ���������
//        PreviewActFrame frame = new PreviewActFrame();
//        frame.setVisible(true);

        setActParameters();// �������� ������ ���������� ��� �������� �� ����� ���������������� ���������
//        PreviewActFrame previewframe = new PreviewActFrame(PreviewActFrame.PaperOrient.PortraitOrient, PreviewActFrame.ActMode.ActPP);
//        previewframe.setFullAct(printPanel.isHalfAct());
        previewframe.setViewComponent(lblView);
        previewframe.setCopies(1);
        previewframe.setVisiblePreview(true);
//        frmPreviewAct.setVisiblePreview();
    }//GEN-LAST:event_mnuFilePreviewActionPerformed

    private void mnuPrintDocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuPrintDocActionPerformed
        setActParameters();
        // ������ ������ ��� ������ � ���������
        previewframe.setViewComponent(lblView);
        previewframe.setCopies(1);
//        previewframe.setVisiblePreview(true);
        previewframe.doPrintAct();
    }//GEN-LAST:event_mnuPrintDocActionPerformed

    private void mnuFileExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuFileExitActionPerformed
        // ��������� �����
        this.setVisible(false);
    }//GEN-LAST:event_mnuFileExitActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // ��������� �����
        if(previewframe != null) {
            previewframe.setVisiblePreview(false);
            previewframe = null;
        }
    }//GEN-LAST:event_formWindowClosing

    /**
     * ����� ��������� ��� ����������� � ���� ���������������� ���������
     */
    private void setActParameters() {
        Object[] param = new Object[7];
        String reportMonth;// �������� �����
        String month = cmbMonth.getSelectedItem().toString();
        String ending = month.substring(month.length() - 1);// �������� ��������� ������
        // ��������� ���
        if (ending.equals("�") || ending.equals("�")) {
            reportMonth = month.substring(0, month.length() - 1) + "�";
        } else {
            reportMonth = month + "�";
        }
        // ���������� ���� ���������������� ���������
        if (previewframe == null)
            previewframe = new PreviewActFrame(PreviewActFrame.PaperOrient.PortraitOrient, PreviewActFrame.ActMode.ActPP);
        
        param[0] = Agreement;// �������
        param[1] = txtReportDate.getText();// ���� ������
        param[2] = nameOrganization;// ������������ �����������
        param[3] = Director;// ������������
        param[4] = reportMonth + " " + cmbYear.getSelectedItem().toString() + " ����";// �������� ������
        param[5] = txtFlow.getText();// ����� ������
        param[6] = converter.getNumText();// ����� �������
//        previewframe.setParameters(param);// ������� ��������� � ���� ���������
        // ��� �������� ����������� ������� ����� ������� ������ ������� � ��������
        Object[][] content = null;
        if(countPanel.isVisible()) {
            // ���� ������� ��������� ������������, �� ��������� ������ ��� ��������
            Object[][] tableContent = countPanel.getContent();
            content = new Object[tableContent.length][6];// ������ ������� �����������
            for(int j = 0; j < tableContent.length; j++) {
                content[j][0] = tableContent[j][0];
                content[j][1] = tableContent[j][2];
                content[j][2] = tableContent[j][3];
                content[j][3] = tableContent[j][4];
                content[j][4] = tableContent[j][5];
                content[j][5] = tableContent[j][6];
            }
        }
//        previewframe.setTableContent(content);// ������� ���������� �������
        lblView = lblContent(param, content);
        
    }
    
    
    private void showPanel(String panelName){
        // ����������� ������ �����
        this.setSize(getWidth(), 300);
        // ������������� ��������� ������
        jPanel1.setVisible(true);
        clManager.show(jPanel1, panelName);
    }
    
    private void convertNumberToString(){
        converter.setValue(txtFlow.getText());
        String convertString = converter.getNumText();
        /* ��� ��������� ������ ����� ���������� ����� ������ � ����� �����
         * �, ��� �������������, ��������� ������� ����� ������, �������
         * �� ���������� �� ����� �����
         */
        lblFlow.setText("<html><body><p align='justify'>" + convertString + "</body></html>");

    }
    
    /**
     * �������� ���� ������ � ����������� �� ��������� ������ � ����
     */
    private void setreportDate(){
        txtReportDate.setText(monthReport[cmbMonth.getSelectedIndex()] +
            " " + cmbYear.getSelectedItem().toString() + " �.");
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | 
                javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ActFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new ActFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBoxMenuItem chkmenuSetupAutoCalc;
    private javax.swing.JCheckBoxMenuItem chkmenuSetupAutoSave;
    private javax.swing.JComboBox cmbMonth;
    private javax.swing.JComboBox cmbYear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblFlow;
    private javax.swing.JMenuItem mnuFileExit;
    private javax.swing.JMenuItem mnuFilePreview;
    private javax.swing.JMenuItem mnuFileSave;
    private javax.swing.JCheckBoxMenuItem mnuFileTableshow;
    private javax.swing.JMenuItem mnuPrintDoc;
    private javax.swing.JCheckBoxMenuItem mnuPrintSetupMore;
    private javax.swing.JTextField txtFlow;
    private javax.swing.JTextField txtPTP;
    private javax.swing.JTextField txtReportDate;
    // End of variables declaration//GEN-END:variables

    /**
     * @param idCompany the idCompany to set
     */
    public void setIdCompany(int idCompany) {
        this.idCompany = idCompany;
        set_CompanyFilter();// ������� ������ �� �������������� ��������
    }
    
    /**
     * �������� ������ �� �������������� ��������
     */
    private void set_CompanyFilter(){
        if(countPanel != null){
            // ��������� ��������� ������ � �������
            if(countPanel.isChangeData()){
                
                countPanel.saveShow();
                
            }
            if(countPanel.isVisible()){
                // ���� ������ � �������� ��������� ������, �������� ������ ��
                // ������� �������� ������ � ��������� �������
                countPanel.setReportMonth(currentMonth);
                countPanel.Showcount(idCompany);
                
            }
        }
    }
    
    

    /**
     * @param Agreement the Agreement to set
     */
    public void setAgreement(String Agreement) {
        this.Agreement = Agreement;
    }
    
    
    private JLabel lblContent(Object[] parameters, Object[][] tableContent) {
        JLabel lblContent = new JLabel();
        
//                lblView.setPreferredSize(dmnsn);
//                lblView.setMaximumSize(dmnsn);
//            lblView.setBorder(new LineBorder(Color.BLACK));
        lblContent.setHorizontalAlignment(SwingConstants.CENTER);
        lblContent.setVerticalAlignment(SwingConstants.TOP);
        Font font = lblContent.getFont();// �������� �����, ������������ ������
        lblContent.setFont(new Font(font.getFontName(), font.getStyle(), 9));
        String txtHeader = "<html>" +
            "<head>" +
                "<meta charset=\"windows-1251\">\n" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
            "</head>" +
            "<body>" +
                "<p></p>" +
                "<p></p>";
        String txtFooter = "</body></html>";
        String lblText = getContentString(565, parameters, tableContent);// ����� ��� ������ �� �����
        lblContent.setText(txtHeader + lblText + txtFooter);// ����� �����
        System.out.println("lblContentHeight=" + lblContent.getPreferredSize().getHeight());
        /*
        ��������� ������ ����� ����� ������ ������, �. �. ��� ����� �������
        ���� ��� ��� ���������� ���� � ����������� �� ��������� IsHalfAct � ������ �����
        ������� �4
        */
        if(lblContent.getPreferredSize().getHeight() <= 421) {
            /*
            ���� ���������� � ���������� ����� ����� ������ ������ ��� ����� 421
            (421 - �������� ������ ����� ������ ������� �4), �� ����� ������� ���
            ���� ��������� ���� �� �����, �������� �������������� ���������
            */
            String txtSeparator = "<p></p><p></p>";
            lblContent.setText(txtHeader + lblText + txtSeparator + lblText + txtFooter);
        }
        return lblContent;
    }
    
    private String getContentString(int pWidth, Object[] parameters, Object[][] tableContent) {
        String string = "<table border=\"0\" cellspacing=\"0\" cellpadding=\"5\" align=\"center\" cols=\"2\" width=\"" +
                    pWidth + "\">" +
                    "<tr>" +
                        "<td colspan=\"2\" align=\"center\">���</td>" +
                    "</tr>" +
                    "<tr height=\"20\"><td></td><td></td></tr>" +
                    "<tr>" +
                        "<td colspan=\"2\" align=\"left\">�����-�������� " +
                        "������������� � ���������������������� ���������� ���� �� �������� " +
                        parameters[0] + "</td>" +
                    "</tr>" +
                    "<tr>" +
                        "<td align=\"left\">�. �������</td><td align=\"right\">" + parameters[1] + "</td>" +
                    "</tr>" +
                    "<tr>" +
                        "<td colspan=\"2\" align=\"justify\">" +
                            "��������� ������ ������ ��� \"����������\" (����� - ���������) � " +
                            "���� �������� �������� ���������� ������ ������� ������ ���������, ������� ��������� �� " +
                            "��������� ������������ �424 �� 12.11.2019, ������������� " +
                            parameters[2] +
                            ", ����� �����������, � ���� " +
                            parameters[3] +
                            ", ��������� ������ ��� � ���, ��� � " +
                            parameters[4] + " ��������� �������� � ������������������ " + 
                            parameters[5] +
                            " ��� �, ����������� ������ � ����������� ��������� ��� ������� " +
                            parameters[5] + " (" + parameters[6] +
                            ") ��� �. ������ ��� ��������� � 2-� ����������� �� 1 ������ �������. " +
                            "��� ���������� ��������� � ����� ����������� ����������� ����." +
                        "</td>" +
                    "</tr>" +
                "</table>" +
                "<p></p>" +
                getActTableData(pWidth, tableContent) +
                "<table border=\"0\" align=\"center\" cols=\"2\" width=\"" + pWidth + "\">" +
                    "<tr>" +
                        "<td width=\"50%\" align=\"center\">������������� �����������</td>" +
                        "<td align=\"center\">������������� ����������</td>" +
                    "</tr>" +
                    "<tr height=\"20\"><td></td><td></td></tr>" +
                    "<tr>" +
                        "<td width=\"50%\"></td>" +
                        "<td align=\"center\">_________/�. �. ������/</td>" +
                    "</tr>" +
                "</table>";
        
        return string;
    }
    
    /**
     * ��������� ������ HTML ���� �� ������ �������
     * @return ������ � ������� HTML
     */
    private String getActTableData(int pWidth, Object[][] tableContent) {
        // ��������� ��������� ����� fullAct
        if(printPanel.isHalfAct() == false) {
            // ����������� ������ ������ ����� ����
            // �������� ������ � ���������� ������� � ��������� ������ HTML
            if(tableContent != null) {
                
                String result = "<table border=\"1\" cellspacing=\"0\" cellpadding=\"5\" align=\"center\" cols=\"6\" width=\"" +
                            (pWidth - 10) + "\">" +
                            "<thead>" +
                            "<th width=\"40%\">����� ������� ���������������</th>" +
                            "<th width=\"14%\">���-��� �� ����� ������</th>" +
                            "<th width=\"14%\">���-��� �� ������ ������</th>" +
                            "<th width=\"12%\">����-� ������. � �/�</th>" +
                            "<th width=\"5%\">���</th>" +
                            "<th>������-��� ����� ����� �� �����, �3</th>" +
                        "</thead>" + getTableData(tableContent);
                Object[] tableContent2 = tableContent[tableContent.length - 1];
                String resString = "<tr><td colspan=\"5\" align=\"left\">�����, �3</td><td align=\"center\">" + 
                        tableContent2[5] + "</td></tr>" + "</table>" +
                "<p></p>";
                result = result + resString;// ��������� ���������� ������� �� ������� � �������� ������
                return result;
            } else return "";
        } else {
            return "";
        }
    }
    
    /**
     * ��������� ������ HTML - ���� �� ������ �������
     * @return ���������� ������ � ������� HTML � ������� �������
     */
    private String getTableData(Object[][] tableContent) {
        // �������� ������ � ���������� ������� � ��������� ������ HTML
        String result = "";
        for (int i = 0; i < tableContent.length - 1; i ++) {
            Object[] tableContent1 = tableContent[i];
            String rowString = "";
            for (Object tableContent11 : tableContent1) {
                rowString = rowString + "<td align=\"center\">" + tableContent11 + "</td>";
            }
            String htmlString = "<tr>" + rowString + "</tr>";
//                System.out.println("html- " + htmlString);
            result = result + htmlString;
        }
        return result;
    }
}
