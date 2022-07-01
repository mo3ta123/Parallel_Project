import amazon_database.DataBase;
import amazon_database.ServerDBConst.Holds_COLS;
import amazon_database.ServerDBConst.Items_COLS;
import amazon_database.ServerDBConst.Transaction_COLS;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import server.Server_Service;

/**
 *
 * @author Youssef
 */

public class Report extends javax.swing.JFrame {
    DataBase DB = new DataBase();
    Image image = null;

    /**
     * Creates new form Report
     */
    public void populateJTable()
    {
        Server_Service Obj = new Server_Service(DB);
        Vector<HashMap<String,String>> vec = Obj.report_1_getAllItems();
        String[] columnName = {"Item Id","Name","Amount","Category", "Price" ,"Image"};
        Object[][] rows = new Object[vec.size()][6];
        for(int i = 0 ; i < vec.size() ; i++)
        {
            rows[i][0] = vec.get(i).get(Items_COLS.Item_ID);
            rows[i][1] = vec.get(i).get(Items_COLS.Name);
            rows[i][2] = vec.get(i).get(Items_COLS.Amount_available);
            rows[i][3] = vec.get(i).get(Items_COLS.category);
            rows[i][4] = vec.get(i).get(Items_COLS.Price);
            
            if(vec.get(i).get(Items_COLS.Img_URL) != null)
            {
                try {
                        URL url = new URL(vec.get(i).get(Items_COLS.Img_URL));
                        image = ImageIO.read(url);
                    } 
                catch (IOException e) 
                    {                   
                    }                    
                ImageIcon image1 = new ImageIcon(new ImageIcon(image).getImage()
                .getScaledInstance(100, 100, Image.SCALE_SMOOTH) );
                
                rows[i][5] = image1;
            }
            else
            {
                rows[i][5] = null;
            }
        }
        DefaultTableModel model = new DefaultTableModel(rows, columnName){
            @Override
            public Class<?> getColumnClass(int column) {
                return switch (column) {
                    case 5 -> ImageIcon.class;
                    default -> Object.class;
                };
            }
                    @Override
            public boolean isCellEditable(int row, int column) {
            return false;
            }
        };
        getAllItemsTable.setModel(model);
        getAllItemsTable.setRowHeight(120);
        getAllItemsTable.getTableHeader().setReorderingAllowed(false);
        getAllItemsTable.getTableHeader().setResizingAllowed(false);
        
    }
    
    public void populateOrderHistoryTable()
    {
        Server_Service Obj = new Server_Service(DB);
        Vector<HashMap<String,String>> vec = Obj.report_3_getAllItemBoughtList();
        String[] columnName = {"User Name","Item Id","Name","Price","Date", "Amount Bought"};
        Object[][] rows = new Object[vec.size()][6];
        for(int i = 0 ; i < vec.size() ; i++)
        {
            rows[i][0] = vec.get(i).get(Transaction_COLS.User_Name);
            rows[i][1] = vec.get(i).get(Items_COLS.Item_ID);
            rows[i][2] = vec.get(i).get(Items_COLS.Name);
            rows[i][3] = vec.get(i).get(Items_COLS.Price);
            rows[i][4] = vec.get(i).get(Transaction_COLS.Transaction_date);
            rows[i][5] = vec.get(i).get(Holds_COLS.Amount);    
        }
        DefaultTableModel model = new DefaultTableModel(rows, columnName){
                    @Override
            public boolean isCellEditable(int row, int column) {
            return false;
            }
        };
        getAllItemBoughtListTable.setModel(model);
        getAllItemBoughtListTable.getTableHeader().setReorderingAllowed(false);
        getAllItemBoughtListTable.getTableHeader().setResizingAllowed(false);
    }
    
    public void populateTransactionHistoryTable()
    {
        Server_Service Obj = new Server_Service(DB);
        Vector<HashMap<String,String>> vec = Obj.report_2_getAllTransaction();
        String[] columnName = {"User Name","Transaction Id","Date","Type","Amount"};
        Object[][] rows = new Object[vec.size()][5];
        for(int i = 0 ; i < vec.size() ; i++)
        {
            rows[i][0] = vec.get(i).get(Transaction_COLS.User_Name);
            rows[i][1] = vec.get(i).get(Transaction_COLS.Transaction_ID);
            rows[i][2] = vec.get(i).get(Transaction_COLS.Transaction_date);
            rows[i][3] = vec.get(i).get(Transaction_COLS.Transaction_type);
            rows[i][4] = vec.get(i).get(Transaction_COLS.money_Amount);     
        }
        DefaultTableModel model = new DefaultTableModel(rows, columnName){
                    @Override
            public boolean isCellEditable(int row, int column) {
            return false;
            }
        };
        getAllTransactionTable.setModel(model);
        getAllTransactionTable.getTableHeader().setReorderingAllowed(false);
        getAllTransactionTable.getTableHeader().setResizingAllowed(false);
    }
    
    public Report() {
        
        initComponents();
        jLabel4.setVisible(false);
        jLabel5.setVisible(false);
        jLabel6.setVisible(false);
        jScrollPane3.setVisible(false);
        jScrollPane2.setVisible(false);
        jScrollPane1.setVisible(false);
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
        GenerateReportButton = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        getAllItemsTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        getAllTransactionTable = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        getAllItemBoughtListTable = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1160, 700));

        jPanel1.setBackground(new java.awt.Color(53, 33, 89));

        GenerateReportButton.setBackground(new java.awt.Color(54, 33, 89));
        GenerateReportButton.setForeground(new java.awt.Color(255, 255, 255));
        GenerateReportButton.setText("Generate Report");
        GenerateReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenerateReportButtonActionPerformed(evt);
            }
        });

        jButton2.setText("Exit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(GenerateReportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(GenerateReportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getAllItemsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(getAllItemsTable);
        if (getAllItemsTable.getColumnModel().getColumnCount() > 0) {
            getAllItemsTable.getColumnModel().getColumn(0).setResizable(false);
            getAllItemsTable.getColumnModel().getColumn(1).setResizable(false);
            getAllItemsTable.getColumnModel().getColumn(2).setResizable(false);
            getAllItemsTable.getColumnModel().getColumn(3).setResizable(false);
        }

        getAllTransactionTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(getAllTransactionTable);

        getAllItemBoughtListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(getAllItemBoughtListTable);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("All Transaction");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("All Bought Items");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("All Items");

        jPanel11.setBackground(new java.awt.Color(102, 102, 255));

        jLabel16.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Reports");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 658, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(248, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel16)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        System.exit(0);
        this.setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void GenerateReportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenerateReportButtonActionPerformed
        jScrollPane3.setVisible(true);
        jScrollPane2.setVisible(true);
        jScrollPane1.setVisible(true);
        jLabel4.setVisible(true);
        jLabel5.setVisible(true);
        jLabel6.setVisible(true);
        populateTransactionHistoryTable();
        populateOrderHistoryTable();
        populateJTable();// TODO add your handling code here:
    }//GEN-LAST:event_GenerateReportButtonActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Report().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton GenerateReportButton;
    private javax.swing.JTable getAllItemBoughtListTable;
    private javax.swing.JTable getAllItemsTable;
    private javax.swing.JTable getAllTransactionTable;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
