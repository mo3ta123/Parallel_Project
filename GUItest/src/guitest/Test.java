package guitest;
import client.Client;
import guitest.ServerConst.*;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.xml.sax.Attributes;


/**
 *
 * @author Youssef
 */


public class Test extends javax.swing.JFrame {
    DefaultTableModel D1;
    DefaultTableModel cart_table;
    Image image = null;
    String g_uname;
    
    
     public Test() {
     initComponents();
    }

    public Test(String username) {
        initComponents();
        populateJTable();
        HomePanel.setVisible(false);
        cartpanel.setVisible(false);
        orderhistorypanel.setVisible(false);
        SearchPanel.setVisible(false);
        
        g_uname = username;

    }
    
    public void populateTransactionHistoryTable()
    {
        Client obj = new Client();
        Vector<HashMap<String,String>> vec = obj.getTransactionHistory(g_uname);
        String[] columnName = {"Transaction Id","Date","Type","Amount"};
        Object[][] rows = new Object[vec.size()][4];
        for(int i = 0 ; i < vec.size() ; i++)
        {
            rows[i][0] = vec.get(i).get(Transaction_COLS.Transaction_ID);
            rows[i][1] = vec.get(i).get(Transaction_COLS.Transaction_date);
            rows[i][2] = vec.get(i).get(Transaction_COLS.Transaction_type);
            rows[i][3] = vec.get(i).get(Transaction_COLS.money_Amount);
            
            
            
        }
        DefaultTableModel model = new DefaultTableModel(rows, columnName);

        transactionhistorytable.setModel(model);
    }
    
    
    public void populateOrderHistoryTable()
    {
        Client obj = new Client();
        Vector<HashMap<String,String>> vec = obj.getItemBoughtList(g_uname);
        String[] columnName = {"Item Id","Name","Price","Date", "Amount Bought"};
        Object[][] rows = new Object[vec.size()][5];
        for(int i = 0 ; i < vec.size() ; i++)
        {
            rows[i][0] = vec.get(i).get(Items_COLS.Item_ID);
            rows[i][1] = vec.get(i).get(Items_COLS.Name);
            rows[i][2] = vec.get(i).get(Items_COLS.Price);
            rows[i][3] = vec.get(i).get(Transaction_COLS.Transaction_date);
            rows[i][4] = vec.get(i).get(Holds_COLS.Amount);
            
            
        }
        DefaultTableModel model = new DefaultTableModel(rows, columnName);

        orderhistorytable.setModel(model);
    }
    
    
    /* ****************************************************************
    ***********************POPULATE CART TABLE*************************
    *******************************************************************
    */
    
    
 public void populateCart(String username)
    {
        Client obj = new Client();
        Vector<HashMap<String,String>> vec = obj.getMyCart(username);
        String[] columnName = {"Item Id","Name","Amount","Categorie", "Total Price", "Image"};
        Object[][] rows = new Object[vec.size()][6];
        for(int i = 0 ; i < vec.size() ; i++)
        {
            rows[i][0] = vec.get(i).get(Items_COLS.Item_ID);
            rows[i][1] = vec.get(i).get(Items_COLS.Name);
            rows[i][2] = vec.get(i).get(Cart_COLS.Amount);
            rows[i][3] = vec.get(i).get(Items_COLS.category);
            rows[i][4] = String.valueOf( Double. parseDouble((vec.get(i).get(Items_COLS.Price))) * Double. parseDouble(vec.get(i).get(Cart_COLS.Amount)) );
            
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
        };

        carttable.setModel(model);
        carttable.setRowHeight(120);
    }
    
    
  /* ****************************************************************
    ***********************POPULATE HOME TABLE*************************
    *******************************************************************
    */   
    
 public void populateJTable()
    {
        Client obj = new Client();
        Vector<HashMap<String,String>> vec = obj.getInitialItems();
        String[] columnName = {"Item Id","Name","Amount","Categorie", "Price" ,"Image"};
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
        };

        HomeTable.setModel(model);
        HomeTable.setRowHeight(120);
    }
    


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel7 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel6 = new javax.swing.JPanel();
        testlabel1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        testlabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        HomePanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        HomeTable = new javax.swing.JTable();
        addtocartbtn = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        cartpanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        carttable = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        deletebtn = new javax.swing.JButton();
        qtyfield = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        orderhistorypanel = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        unamelabel = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        phonelabel = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        emaillabel = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        balancelabel = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        depositfield = new javax.swing.JTextField();
        depositbtn = new javax.swing.JButton();
        orderhistorybtn = new javax.swing.JButton();
        transactionhistorybtn = new javax.swing.JButton();
        orderhistorypanelbtn = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        orderhistorytable = new javax.swing.JTable();
        trasactionpanel = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        transactionhistorytable = new javax.swing.JTable();
        SearchPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        SearchTable = new javax.swing.JTable();
        SearchTextField = new javax.swing.JTextField();
        addtocartSearchPanel = new javax.swing.JButton();
        SearchButton = new javax.swing.JButton();
        clearsearch = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(54, 33, 89));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Amazon");
        jPanel7.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, 60));

        jSeparator1.setPreferredSize(new java.awt.Dimension(60, 10));
        jPanel7.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 230, 10));

        jPanel6.setBackground(new java.awt.Color(85, 85, 118));
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel6MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel6MousePressed(evt);
            }
        });

        testlabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel10.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Home");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(testlabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(122, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(testlabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 290, 40));

        jPanel5.setBackground(new java.awt.Color(85, 85, 118));
        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel5MouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Search");

        testlabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(testlabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(122, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(testlabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 290, 40));

        jPanel3.setBackground(new java.awt.Color(85, 85, 118));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Your Cart");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(122, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 290, 40));

        jPanel4.setBackground(new java.awt.Color(85, 85, 118));
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
        });

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("User Information");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(91, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 290, 40));

        jPanel2.setBackground(new java.awt.Color(85, 85, 118));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Logout");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(122, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 290, 40));

        jPanel9.setBackground(new java.awt.Color(85, 85, 118));
        jPanel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel9MouseClicked(evt);
            }
        });

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel14.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Exit ");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(126, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 450, 290, 40));

        getContentPane().add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 600));

        HomePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setAutoscrolls(true);

        HomeTable.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        HomeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Byte.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        HomeTable.setRowHeight(30);
        HomeTable.setShowGrid(true);
        HomeTable.getTableHeader().setReorderingAllowed(false);
        HomeTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HomeTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(HomeTable);

        HomePanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 137, 690, 410));

        addtocartbtn.setText("Add to Cart");
        addtocartbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addtocartbtnMouseClicked(evt);
            }
        });
        addtocartbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addtocartbtnActionPerformed(evt);
            }
        });
        HomePanel.add(addtocartbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 550, 120, 40));

        jPanel10.setBackground(new java.awt.Color(102, 102, 255));

        jLabel15.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Home");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 658, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel15)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        HomePanel.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(HomePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 690, 590));

        jPanel1.setBackground(new java.awt.Color(102, 102, 255));

        jLabel11.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Your Cart");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel11)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        carttable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Amout", "Category", "Price"
            }
        ));
        carttable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                carttableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(carttable);

        jButton2.setText("Checkout");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        deletebtn.setText("Delete");
        deletebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletebtnActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel13.setText("Qty");

        jButton3.setText("Edit Quantity");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout cartpanelLayout = new javax.swing.GroupLayout(cartpanel);
        cartpanel.setLayout(cartpanelLayout);
        cartpanelLayout.setHorizontalGroup(
            cartpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cartpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cartpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(cartpanelLayout.createSequentialGroup()
                        .addComponent(deletebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(qtyfield, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        cartpanelLayout.setVerticalGroup(
            cartpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cartpanelLayout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(cartpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(cartpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(deletebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(qtyfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13))
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        getContentPane().add(cartpanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 690, 600));

        jPanel8.setBackground(new java.awt.Color(102, 102, 255));

        unamelabel.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        unamelabel.setForeground(new java.awt.Color(255, 255, 255));
        unamelabel.setText("ssssss");

        jLabel19.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Username:");

        jLabel20.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Phone:");

        phonelabel.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        phonelabel.setForeground(new java.awt.Color(255, 255, 255));
        phonelabel.setText("ssssss");

        jLabel21.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Email:");

        emaillabel.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        emaillabel.setForeground(new java.awt.Color(255, 255, 255));
        emaillabel.setText("ssssss");

        jLabel22.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Balance:");

        balancelabel.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        balancelabel.setForeground(new java.awt.Color(255, 255, 255));
        balancelabel.setText("ssssss");

        jLabel23.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("User Information");

        jLabel17.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Enter Amount");

        depositbtn.setText("Deposit");
        depositbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                depositbtnActionPerformed(evt);
            }
        });

        orderhistorybtn.setText("Order History");
        orderhistorybtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderhistorybtnActionPerformed(evt);
            }
        });

        transactionhistorybtn.setText("Transaction History");
        transactionhistorybtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transactionhistorybtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(orderhistorybtn, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(transactionhistorybtn, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(83, 83, 83)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(balancelabel))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(emaillabel))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel19)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(unamelabel))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel20)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(phonelabel))))))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(depositfield, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(depositbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(134, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap(23, Short.MAX_VALUE)
                        .addComponent(jLabel23)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(orderhistorybtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(transactionhistorybtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(69, 69, 69))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(unamelabel)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(phonelabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(emaillabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(balancelabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(depositfield, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(depositbtn)))
                .addGap(32, 32, 32))
        );

        orderhistorytable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item ID", "Name ", "Price", "Date", "Amount Bought"
            }
        ));
        jScrollPane4.setViewportView(orderhistorytable);

        javax.swing.GroupLayout orderhistorypanelbtnLayout = new javax.swing.GroupLayout(orderhistorypanelbtn);
        orderhistorypanelbtn.setLayout(orderhistorypanelbtnLayout);
        orderhistorypanelbtnLayout.setHorizontalGroup(
            orderhistorypanelbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderhistorypanelbtnLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4)
                .addContainerGap())
        );
        orderhistorypanelbtnLayout.setVerticalGroup(
            orderhistorypanelbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderhistorypanelbtnLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        transactionhistorytable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Transaction ID", "Date", "Type", "Amount"
            }
        ));
        jScrollPane5.setViewportView(transactionhistorytable);

        javax.swing.GroupLayout trasactionpanelLayout = new javax.swing.GroupLayout(trasactionpanel);
        trasactionpanel.setLayout(trasactionpanelLayout);
        trasactionpanelLayout.setHorizontalGroup(
            trasactionpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trasactionpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
                .addContainerGap())
        );
        trasactionpanelLayout.setVerticalGroup(
            trasactionpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trasactionpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout orderhistorypanelLayout = new javax.swing.GroupLayout(orderhistorypanel);
        orderhistorypanel.setLayout(orderhistorypanelLayout);
        orderhistorypanelLayout.setHorizontalGroup(
            orderhistorypanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(orderhistorypanelbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(orderhistorypanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(orderhistorypanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(trasactionpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        orderhistorypanelLayout.setVerticalGroup(
            orderhistorypanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderhistorypanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(orderhistorypanelbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(orderhistorypanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(orderhistorypanelLayout.createSequentialGroup()
                    .addGap(263, 263, 263)
                    .addComponent(trasactionpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(10, 10, 10)))
        );

        getContentPane().add(orderhistorypanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 690, 600));

        SearchPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane3.setAutoscrolls(true);

        SearchTable.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        SearchTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Amount", "Category", "Price", "Image"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Byte.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        SearchTable.setRowHeight(30);
        SearchTable.setShowGrid(true);
        SearchTable.getTableHeader().setReorderingAllowed(false);
        SearchTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SearchTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(SearchTable);
        if (SearchTable.getColumnModel().getColumnCount() > 0) {
            SearchTable.getColumnModel().getColumn(0).setHeaderValue("ID");
            SearchTable.getColumnModel().getColumn(1).setHeaderValue("Name");
            SearchTable.getColumnModel().getColumn(2).setHeaderValue("Amount");
            SearchTable.getColumnModel().getColumn(3).setHeaderValue("Category");
            SearchTable.getColumnModel().getColumn(4).setHeaderValue("Price");
            SearchTable.getColumnModel().getColumn(5).setHeaderValue("Image");
        }

        SearchPanel.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 137, 690, 410));

        SearchTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchTextFieldActionPerformed(evt);
            }
        });
        SearchPanel.add(SearchTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 260, 30));

        addtocartSearchPanel.setText("Add to Cart");
        addtocartSearchPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addtocartSearchPanelActionPerformed(evt);
            }
        });
        SearchPanel.add(addtocartSearchPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 550, 120, 40));

        SearchButton.setText("Search");
        SearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchButtonActionPerformed(evt);
            }
        });
        SearchPanel.add(SearchButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 100, -1, 30));

        clearsearch.setText("CLear");
        clearsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearsearchActionPerformed(evt);
            }
        });
        SearchPanel.add(clearsearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 100, -1, 30));

        jPanel11.setBackground(new java.awt.Color(102, 102, 255));

        jLabel16.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Search");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 658, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel16)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        SearchPanel.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 690, -1));

        jLabel18.setText("jLabel18");
        SearchPanel.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 420, 40, -1));

        getContentPane().add(SearchPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 690, 590));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
     /* ****************************************************************
    ***********************Home button Clicked*************************
    *******************************************************************
    */
    
    private void jPanel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseClicked
    HomePanel.setVisible(true);
    cartpanel.setVisible(false);
    orderhistorypanel.setVisible(false);
    SearchPanel.setVisible(false);

    //dummy();// TODO add your handling code here:

       
    }//GEN-LAST:event_jPanel6MouseClicked

    
     /* ****************************************************************
    ***********************Search Button Clicked*************************
    *******************************************************************
    */
    
    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked
    HomePanel.setVisible(false);
    cartpanel.setVisible(false);
    orderhistorypanel.setVisible(false);
    SearchPanel.setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_jPanel5MouseClicked

    private void jPanel6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MousePressed
        // TODO add your handling code here
    }//GEN-LAST:event_jPanel6MousePressed

    private void HomeTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HomeTableMouseClicked
        D1 = (DefaultTableModel) HomeTable.getModel();
        int index = HomeTable.getSelectedRow();
        // TODO add your handling code here:
    }//GEN-LAST:event_HomeTableMouseClicked
 
     /* ****************************************************************
    ***********************Add to Cart Btn in Home Panel*************************
    *******************************************************************
    */
    
    private void addtocartbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addtocartbtnActionPerformed
        Client c1 = new Client();    
        D1 = (DefaultTableModel) HomeTable.getModel();
        int index = HomeTable.getSelectedRow();
        boolean item_add = c1.editItemAtCart(g_uname , Integer.valueOf(D1.getValueAt(index, 0).toString()), 1);
        
        if(item_add)
        {
            JOptionPane.showMessageDialog(this,"item added");   
        }
        else
        {
            JOptionPane.showMessageDialog(this,"Out of Stock");
        }
        
        
    }//GEN-LAST:event_addtocartbtnActionPerformed

    private void addtocartbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addtocartbtnMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_addtocartbtnMouseClicked

     /* ****************************************************************
    ***********************Row Selected in Cart Panel*************************
    *******************************************************************
    */
    
    
    private void carttableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_carttableMouseClicked
        cart_table = (DefaultTableModel)carttable.getModel();      // TODO add your handling code here:
        int index = carttable.getSelectedRow();
        qtyfield.setText(cart_table.getValueAt(index, 2).toString());
        deletebtn.setEnabled(true);
    }//GEN-LAST:event_carttableMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

     /* ****************************************************************
    ***********************Delete btn in Cart Panel*************************
    *******************************************************************
    */
    
    private void deletebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletebtnActionPerformed
        // TODO add your handling code here:
        deletebtn.setEnabled(false);
        cart_table = (DefaultTableModel)carttable.getModel();      // TODO add your handling code here:
        int index = carttable.getSelectedRow();
        
        Client c1 = new Client();
        c1.editItemAtCart(g_uname, Integer.valueOf(cart_table.getValueAt(index, 0).toString()) , 0);
        populateCart(g_uname);
        qtyfield.setText("");
    }//GEN-LAST:event_deletebtnActionPerformed

     /* ****************************************************************
    ***********************Your Cart Button Clicked*************************
    *******************************************************************
    */
    
    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked
       HomePanel.setVisible(false);
       orderhistorypanel.setVisible(false);
       SearchPanel.setVisible(false);
       cartpanel.setVisible(true);
       deletebtn.setEnabled(false);
       
       populateCart(g_uname);
       
// YOUR CART
    }//GEN-LAST:event_jPanel3MouseClicked

    
     /* ****************************************************************
    ***********************History Clicked*************************
    *******************************************************************
    */
    
    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
        orderhistorypanel.setVisible(true);   
        HomePanel.setVisible(false);
        cartpanel.setVisible(false);
        SearchPanel.setVisible(false);// TODO add your handling code here:
        
        orderhistorypanelbtn.setVisible(false);
        trasactionpanel.setVisible(false);
        
        
        Client c1 = new Client();
        Vector<HashMap<String,String>> vec = c1.getAccountInfo(g_uname);
        unamelabel.setText(vec.get(0).get(User_COLS.USER_NAME));
        phonelabel.setText(vec.get(0).get(User_COLS.USER_PHONE));
        emaillabel.setText(vec.get(0).get(User_COLS.USER_EMAIL));
        balancelabel.setText(vec.get(0).get(User_COLS.USER_BAL));
        
        
        
    }//GEN-LAST:event_jPanel4MouseClicked

     /* ****************************************************************
    ***********************Logout Button Clicked*************************
    *******************************************************************
    */
    
    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        // TODO add your handling code here:
        new Login().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jPanel2MouseClicked
 
    
    
    
    /* ****************************************************************
    ***********************Exit Button Clicked*************************
    *******************************************************************
    */
    
    
    
    private void jPanel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_jPanel9MouseClicked
 
    
    
    /* ****************************************************************
    ***********************Search Button in Search Panel*************************
    *******************************************************************
    */
    
    
    private void SearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchButtonActionPerformed
        String SearchString = SearchTextField.getText();
         Client obj1 = new Client();
         Vector<HashMap<String,String>> clientObj1 = obj1.search(SearchString);
         if(!(clientObj1.isEmpty()))
        {
            String[] columnName = {"Item Id","Name","Amount","Categorie", "Price" ,"Image"};
            Object[][] rows = new Object[clientObj1.size()][6];
            for(int i = 0 ; i < clientObj1.size() ; i++)
            {
                rows[i][0] = clientObj1.get(i).get(Items_COLS.Item_ID);
                rows[i][1] = clientObj1.get(i).get(Items_COLS.Name);
                rows[i][2] = clientObj1.get(i).get(Items_COLS.Amount_available);
                rows[i][3] = clientObj1.get(i).get(Items_COLS.category);
                rows[i][4] = clientObj1.get(i).get(Items_COLS.Price);
            
                if(clientObj1.get(i).get(Items_COLS.Img_URL) != null)
                {
                    try {
                            URL url = new URL(clientObj1.get(i).get(Items_COLS.Img_URL));
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
        };
        
            SearchTable.setModel(model);
            SearchTable.setRowHeight(120);
        }
        else
        {
             JOptionPane.showMessageDialog(this,"item isn't available");
        }                                            
    }//GEN-LAST:event_SearchButtonActionPerformed
 /* ****************************************************************
    ***********************Clear Button in Search Panel*************************
    *******************************************************************
    */
    private void clearsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearsearchActionPerformed
         SearchTable.setModel(new DefaultTableModel(null, new String[]{"Item Id","Name","Amount","Categorie", "Price" ,"Image"} ));// TODO add your handling code here:
    }//GEN-LAST:event_clearsearchActionPerformed

    private void addtocartSearchPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addtocartSearchPanelActionPerformed
    
    }//GEN-LAST:event_addtocartSearchPanelActionPerformed

    private void SearchTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SearchTextFieldActionPerformed

    private void SearchTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SearchTableMouseClicked
        D1 = (DefaultTableModel) SearchTable.getModel();
        int index = SearchTable.getSelectedRow(); // TODO add your handling code here:
    }//GEN-LAST:event_SearchTableMouseClicked

    private void orderhistorybtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderhistorybtnActionPerformed
        // TODO add your handling code here:
        orderhistorypanelbtn.setVisible(true);
        trasactionpanel.setVisible(false);
        populateOrderHistoryTable();
    }//GEN-LAST:event_orderhistorybtnActionPerformed

    private void transactionhistorybtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transactionhistorybtnActionPerformed
        // TODO add your handling code here:
        orderhistorypanelbtn.setVisible(false);
        trasactionpanel.setVisible(true);
        populateTransactionHistoryTable();
    }//GEN-LAST:event_transactionhistorybtnActionPerformed

    private void depositbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_depositbtnActionPerformed
        // TODO add your handling code here:
        
        double amount = Double. parseDouble(depositfield.getText());
        
        Client c1 = new Client();
        c1.depositCash(g_uname, amount);
        
        Vector<HashMap<String,String>> vec = c1.getAccountInfo(g_uname);
        unamelabel.setText(vec.get(0).get(User_COLS.USER_NAME));
        phonelabel.setText(vec.get(0).get(User_COLS.USER_PHONE));
        emaillabel.setText(vec.get(0).get(User_COLS.USER_EMAIL));
        balancelabel.setText(vec.get(0).get(User_COLS.USER_BAL));
        JOptionPane.showMessageDialog(this,"Amount Deposited");
    }//GEN-LAST:event_depositbtnActionPerformed

    // EDIT QTY BTN in CART
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int qty = Integer.valueOf(qtyfield.getText());       // TODO add your handling code here:
        Client c1 = new Client();
        
        cart_table = (DefaultTableModel)carttable.getModel();      // TODO add your handling code here:
        int index = carttable.getSelectedRow();
        
        
        boolean x = c1.editItemAtCart(g_uname, Integer.valueOf(cart_table.getValueAt(index, 0).toString()) , qty);
        
        if (!x){
        JOptionPane.showMessageDialog(this,"Out of stock");
        }
        populateCart(g_uname);
        qtyfield.setText("");
        deletebtn.setEnabled(false);
        //uwu
        
      
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(Test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Test().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel HomePanel;
    private javax.swing.JTable HomeTable;
    public javax.swing.JButton SearchButton;
    private javax.swing.JPanel SearchPanel;
    private javax.swing.JTable SearchTable;
    private javax.swing.JTextField SearchTextField;
    private javax.swing.JButton addtocartSearchPanel;
    private javax.swing.JButton addtocartbtn;
    private javax.swing.JLabel balancelabel;
    private javax.swing.JPanel cartpanel;
    private javax.swing.JTable carttable;
    private javax.swing.JButton clearsearch;
    private javax.swing.JButton deletebtn;
    private javax.swing.JButton depositbtn;
    private javax.swing.JTextField depositfield;
    private javax.swing.JLabel emaillabel;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton orderhistorybtn;
    private javax.swing.JPanel orderhistorypanel;
    private javax.swing.JPanel orderhistorypanelbtn;
    private javax.swing.JTable orderhistorytable;
    private javax.swing.JLabel phonelabel;
    private javax.swing.JTextField qtyfield;
    private javax.swing.JLabel testlabel1;
    private javax.swing.JLabel testlabel2;
    private javax.swing.JButton transactionhistorybtn;
    private javax.swing.JTable transactionhistorytable;
    private javax.swing.JPanel trasactionpanel;
    private javax.swing.JLabel unamelabel;
    // End of variables declaration//GEN-END:variables
}
