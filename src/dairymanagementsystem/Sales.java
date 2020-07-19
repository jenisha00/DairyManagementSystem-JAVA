package dairymanagementsystem;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import net.proteanit.sql.DbUtils;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;


/**
 *
 * @author Jenisha Munikar
 */
public class Sales extends javax.swing.JFrame {
    Connection conn=null;
    ResultSet rs=null;
    PreparedStatement pst=null;
    DefaultTableModel model;
    String date1=null,date2=null, product=null,name=null;
    int empty=0;
    public Sales() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        conn=Database.connector1();
        combobox_name();
        combobox_product();
        Update_table();
        CurrentDate();
       

    }
    public void close(){
        WindowEvent winClosingEvent=new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
    }
    public void combobox_name(){
        try{
            String query="Select * from customer";
            pst=conn.prepareStatement(query);
            rs=pst.executeQuery();
            while(rs.next())
            {
                String FName=rs.getString("First_name");                
                jComboBox1.addItem(FName);
                NameFilter.addItem(FName);
                jComboBox1.setSelectedItem("");
                NameFilter.setSelectedItem("");
                AutoCompleteDecorator.decorate(jComboBox1);  
                AutoCompleteDecorator.decorate(NameFilter);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
    }
    public void combobox_product(){
        try{
            String query="Select * from stock";
            pst=conn.prepareStatement(query);
            rs=pst.executeQuery();
            while(rs.next())
            {
                String name=rs.getString("Product_name");                
                jComboBox2.addItem(name);         
                ProductFilter.addItem(name); 
                jComboBox2.setSelectedItem("");
                ProductFilter.setSelectedItem("");
                AutoCompleteDecorator.decorate(ProductFilter);        
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    public void Update_table(){            
        try{  
            String sql="select sales.sales_id,customer.C_ID,customer.FIRST_NAME,customer.LAST_NAME,customer.PHONE,SALES.PRODUCT_NAME ,sales.QTy, sales.RATE ,\n" +
                        "(sales.QTY * sales.RATE) as TOTAL_AMT,SALES.PAID_AMT,(SALES.QTY*SALES.RATE)-SALES.PAID_AMT AS DUE_AMT,SALES.DATE ,  \n" +
"                        CASE  WHEN (SALES.QTY*SALES.RATE)-SALES.PAID_AMT ==0 THEN 'Paid'\n" +
"                        ELSE 'Credit'\n" +
"                        END PAY_TYPE from customer \n" +
                        "inner join sales on customer.C_ID = sales.C_ID ";      
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            model=(DefaultTableModel) jsalestable.getModel();
            model.setRowCount(0);
            while(rs.next()){
                String SID=rs.getString(1);
                String CID=rs.getString(2);
                String FN=rs.getString(3);
                String LN=rs.getString(4);
                String PH=rs.getString(5);
                String PN=rs.getString(6);
                String QT=rs.getString(7);
                String RT=rs.getString(8);
                String TA=rs.getString(9);
                String PM=rs.getString(10);
                String DA=rs.getString(11);
                String DT=rs.getString(12);
                String PT=rs.getString(13);
                Object[] content={SID,CID,FN,LN,PH,PN,QT,RT,TA,PM,DA,DT,PT};
                model.addRow(content);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }  
        String query="select sum(QTY *RATE) as Total, sum(Paid_amt) as Paid, sum((QTY*RATE)-PAID_AMT) as Due from sales";
        try {
            pst=conn.prepareStatement(query);
            rs=pst.executeQuery();
            String a=rs.getString("Total");
            String b=rs.getString("Paid");
            String c=rs.getString("Due");
            Sales.setText(a);
            Payment.setText(b);
            Due.setText(c);
        }catch (Exception e) {
           JOptionPane.showMessageDialog(null,e);
        }
        
        
 }
    public void CurrentDate(){
        GregorianCalendar c=new GregorianCalendar();
        String day=c.get(Calendar.DAY_OF_MONTH)+"";
        String month=c.get(Calendar.MONTH)+1+"";
        String year=c.get(Calendar.YEAR)+"";
        if (day.length() == 1) {
            day = "0" + day;
        }
        if (month.length() == 1) {
            month = "0" + month;
        }

        String date = year+"-"+month+"-"+day;
        TxtDate.setText(String.valueOf(date));
    }
    public int check(){
       
        if(jComboBox1.getSelectedItem().toString().equals("") || jComboBox2.getSelectedItem().toString().equals("") || TxtQty.getText().equals("") || TxtRate.getText().equals("") ||TxtPaid.getText().equals("")||TxtDate.getText().equals("")){
            return 1; 
        }
        else{
            return 0;
        }
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
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        TxtSales = new javax.swing.JTextField();
        TxtQty = new javax.swing.JTextField();
        TxtRate = new javax.swing.JTextField();
        TxtPaid = new javax.swing.JTextField();
        TxtDate = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        btn_insert = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        btn_reset = new javax.swing.JButton();
        btn_print = new javax.swing.JButton();
        btn_exit = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        ProductFilter = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Date_To = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        btn_hide = new javax.swing.JButton();
        Date_From = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btn_show = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        Sales = new javax.swing.JTextField();
        Payment = new javax.swing.JTextField();
        Due = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        NameFilter = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        TxtSearch = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jsalestable = new javax.swing.JTable();
        CID = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        Customer = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        Sales1 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        Stock = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenu2 = new javax.swing.JMenu();
        help = new javax.swing.JMenuItem();
        menu_logout = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SALES DETAILS");
        setBackground(new java.awt.Color(51, 51, 51));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel3.setFont(new java.awt.Font("Sitka Heading", 1, 20)); // NOI18N
        jLabel3.setText("First Name");

        jLabel5.setFont(new java.awt.Font("Sitka Heading", 1, 20)); // NOI18N
        jLabel5.setText("Product ");

        jLabel6.setFont(new java.awt.Font("Sitka Heading", 1, 20)); // NOI18N
        jLabel6.setText("Qty");

        jLabel7.setFont(new java.awt.Font("Sitka Heading", 1, 20)); // NOI18N
        jLabel7.setText("Rate");

        jLabel8.setFont(new java.awt.Font("Sitka Heading", 1, 20)); // NOI18N
        jLabel8.setText("Payment");

        TxtSales.setEditable(false);
        TxtSales.setBackground(new java.awt.Color(204, 204, 204));
        TxtSales.setForeground(new java.awt.Color(204, 204, 204));
        TxtSales.setBorder(null);

        jComboBox1.setEditable(true);

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btn_insert.setBackground(new java.awt.Color(204, 204, 204));
        btn_insert.setFont(new java.awt.Font("Sitka Heading", 1, 18)); // NOI18N
        btn_insert.setForeground(new java.awt.Color(51, 51, 51));
        btn_insert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dairymanagementsystem/add.png"))); // NOI18N
        btn_insert.setText("Insert");
        btn_insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_insertActionPerformed(evt);
            }
        });

        btn_update.setBackground(new java.awt.Color(204, 204, 204));
        btn_update.setFont(new java.awt.Font("Sitka Heading", 1, 18)); // NOI18N
        btn_update.setForeground(new java.awt.Color(51, 51, 51));
        btn_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dairymanagementsystem/update.png"))); // NOI18N
        btn_update.setText("Update");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        btn_delete.setBackground(new java.awt.Color(204, 204, 204));
        btn_delete.setFont(new java.awt.Font("Sitka Heading", 1, 18)); // NOI18N
        btn_delete.setForeground(new java.awt.Color(51, 51, 51));
        btn_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dairymanagementsystem/delete.png"))); // NOI18N
        btn_delete.setText("Delete");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        btn_reset.setBackground(new java.awt.Color(204, 204, 204));
        btn_reset.setFont(new java.awt.Font("Sitka Heading", 1, 18)); // NOI18N
        btn_reset.setForeground(new java.awt.Color(51, 51, 51));
        btn_reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dairymanagementsystem/reset.png"))); // NOI18N
        btn_reset.setText("Reset");
        btn_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resetActionPerformed(evt);
            }
        });

        btn_print.setBackground(new java.awt.Color(204, 204, 204));
        btn_print.setFont(new java.awt.Font("Sitka Heading", 1, 18)); // NOI18N
        btn_print.setForeground(new java.awt.Color(51, 51, 51));
        btn_print.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dairymanagementsystem/print.png"))); // NOI18N
        btn_print.setText("Print");
        btn_print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_printActionPerformed(evt);
            }
        });

        btn_exit.setBackground(new java.awt.Color(204, 204, 204));
        btn_exit.setFont(new java.awt.Font("Sitka Heading", 1, 18)); // NOI18N
        btn_exit.setForeground(new java.awt.Color(51, 51, 51));
        btn_exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dairymanagementsystem/exit.png"))); // NOI18N
        btn_exit.setText("Exit");
        btn_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_insert)
                    .addComponent(btn_reset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_print, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_delete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_update, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_exit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(32, 32, 32))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_insert, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_print, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_reset, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_exit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        jLabel11.setFont(new java.awt.Font("Sitka Heading", 1, 20)); // NOI18N
        jLabel11.setText("Date");

        jComboBox2.setEditable(true);

        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        ProductFilter.setEditable(true);

        jLabel13.setFont(new java.awt.Font("Sitka Heading", 0, 15)); // NOI18N
        jLabel13.setText("To");

        jLabel2.setFont(new java.awt.Font("Sitka Heading", 0, 14)); // NOI18N
        jLabel2.setText("Product");

        jLabel10.setFont(new java.awt.Font("Sitka Heading", 1, 20)); // NOI18N
        jLabel10.setText("Date");

        btn_hide.setBackground(new java.awt.Color(204, 204, 204));
        btn_hide.setFont(new java.awt.Font("Sitka Heading", 1, 18)); // NOI18N
        btn_hide.setForeground(new java.awt.Color(51, 51, 51));
        btn_hide.setText("Hide");
        btn_hide.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hideActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Sitka Heading", 0, 15)); // NOI18N
        jLabel12.setText("From");

        jLabel4.setFont(new java.awt.Font("Sitka Heading", 0, 14)); // NOI18N
        jLabel4.setText("Name");

        btn_show.setBackground(new java.awt.Color(204, 204, 204));
        btn_show.setFont(new java.awt.Font("Sitka Heading", 1, 18)); // NOI18N
        btn_show.setForeground(new java.awt.Color(51, 51, 51));
        btn_show.setText("Show");
        btn_show.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_showActionPerformed(evt);
            }
        });

        jLabel14.setText("Total Sales Amt");

        jLabel15.setText("Total Payment");

        jLabel16.setText("Total Due Amt");

        Sales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalesActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(204, 204, 204));
        jButton1.setFont(new java.awt.Font("Sitka Heading", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(51, 51, 51));
        jButton1.setText("Reset");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Sitka Heading", 1, 18)); // NOI18N
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dairymanagementsystem/search.png"))); // NOI18N

        TxtSearch.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TxtSearch.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        TxtSearch.setToolTipText("");
        TxtSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TxtSearchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                TxtSearchFocusLost(evt);
            }
        });
        TxtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtSearchActionPerformed(evt);
            }
        });
        TxtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtSearchKeyReleased(evt);
            }
        });

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jsalestable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sales Id", "C Id", "First Name", "Last Name", "Phone", "Product", "Qty", "Rate", "Total Amt", "Payment", "Due Amt", "Date", "PayType"
            }
        ));
        jsalestable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jsalestableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jsalestable);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 890, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(627, 627, 627)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ProductFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NameFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(81, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btn_show)
                        .addGap(26, 26, 26)
                        .addComponent(btn_hide)
                        .addGap(26, 26, 26)
                        .addComponent(jButton1))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(Sales, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Payment, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Due, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel12)
                    .addGap(18, 18, 18)
                    .addComponent(Date_From, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(Date_To, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(593, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(TxtSearch)
                        .addComponent(jLabel9)))
                .addGap(56, 56, 56)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NameFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ProductFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_hide)
                    .addComponent(btn_show)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Sales, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Payment, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Due, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(445, 445, 445))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(46, 46, 46)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(Date_To, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addComponent(Date_From, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                    .addContainerGap(908, Short.MAX_VALUE)))
        );

        jScrollPane2.setViewportView(jPanel4);

        CID.setEditable(false);
        CID.setBackground(new java.awt.Color(204, 204, 204));
        CID.setText(" ");
        CID.setBorder(null);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(TxtPaid)
                                    .addComponent(TxtRate)
                                    .addComponent(TxtQty)
                                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(TxtDate)
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtSales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 977, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(263, 263, 263)
                        .addComponent(CID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxtSales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(27, 27, 27)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(TxtRate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(TxtQty, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(TxtPaid, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(TxtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu1.setText("New");

        Customer.setText("Customer");
        Customer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CustomerMouseClicked(evt);
            }
        });
        Customer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CustomerActionPerformed(evt);
            }
        });
        jMenu1.add(Customer);
        jMenu1.add(jSeparator1);

        Sales1.setText("Sales");
        Sales1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Sales1MouseClicked(evt);
            }
        });
        Sales1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sales1ActionPerformed(evt);
            }
        });
        jMenu1.add(Sales1);
        jMenu1.add(jSeparator2);

        Stock.setText("Product and Stock");
        Stock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StockActionPerformed(evt);
            }
        });
        jMenu1.add(Stock);
        jMenu1.add(jSeparator3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        help.setText("Help");
        help.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpActionPerformed(evt);
            }
        });
        jMenu2.add(help);

        jMenuBar1.add(jMenu2);

        menu_logout.setText("Exit");
        menu_logout.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                menu_logoutMenuSelected(evt);
            }
        });
        menu_logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menu_logoutMouseClicked(evt);
            }
        });
        menu_logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_logoutActionPerformed(evt);
            }
        });
        jMenuBar1.add(menu_logout);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 252, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_insertActionPerformed
        String query="Select * from customer where first_name=?";
        try {
            pst=conn.prepareStatement(query);
            pst.setString(1,jComboBox1.getSelectedItem().toString()); 
            empty=check();
            if(empty==1){
                    JOptionPane.showMessageDialog(null,"Please fill all the fields"); 
            }
            else{
                rs=pst.executeQuery();
                if(rs.next()){
                        String id=rs.getString("C_id");
                        try{
                            String query2="insert into sales(C_ID,PRODUCT_NAME,QTY,RATE,PAID_AMT,DATE) values(?,?,?,?,?,?)";
                            pst=conn.prepareStatement(query2);
                            pst.setString(1,id);
                            pst.setString(2,jComboBox2.getSelectedItem().toString());
                            pst.setString(3,TxtQty.getText());
                            pst.setString(4,TxtRate.getText());
                            pst.setString(5,TxtPaid.getText());
                            pst.setString(6,TxtDate.getText());
                            pst.execute();
                            JOptionPane.showMessageDialog(null,"Record Inserted");
                        }
                    catch(Exception e){
                        JOptionPane.showMessageDialog(null,e);
                    }
                }
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        Update_table();
    }//GEN-LAST:event_btn_insertActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        
        String query = " update SALES set Product_name=?,QTY=?,RATE=?,PAID_AMT=?,Date=? where SALES_ID=?";
        try{
            pst=conn.prepareStatement(query); 
            pst.setString(1,jComboBox2.getSelectedItem().toString());
            pst.setString(2,TxtQty.getText());
            pst.setString(3,TxtRate.getText());
            pst.setString(4,TxtPaid.getText());
            pst.setString(5,TxtDate.getText());
            pst.setString(6,TxtSales.getText());
            empty=check();
            if(empty==1){
                JOptionPane.showMessageDialog(null,"Please fill all the fields"); 
            }
            else{
                pst.execute();
                JOptionPane.showMessageDialog(null,"Record Updated");
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
       
        Update_table();
    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed

        int p = JOptionPane.showConfirmDialog(null,"Do you want to delete?","Delete",JOptionPane.YES_NO_OPTION);
        if (p==0){
            String query = "delete from SALES where SALES_ID=?";
            try {
                pst=conn.prepareStatement(query);
                pst.setString(1,TxtSales.getText());
                pst.execute();
                JOptionPane.showMessageDialog(null,"Record Deleted");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,e);
            }
          
        }
        Update_table();
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetActionPerformed
        TxtSales.setText("");
        jComboBox2.setSelectedItem("");
        TxtQty.setText("");
        TxtRate.setText("");
        TxtPaid.setText("");
        GregorianCalendar c=new GregorianCalendar();
        String day=c.get(Calendar.DAY_OF_MONTH)+"";
        String month=c.get(Calendar.MONTH)+1+"";
        String year=c.get(Calendar.YEAR)+"";
        if (day.length() == 1) {
            day = "0" + day;
        }
        if (month.length() == 1) {
            month = "0" + month;
        }

        String date = year+"-"+month+"-"+day;
        TxtDate.setText(String.valueOf(date));
        jComboBox1.setSelectedItem("");
       
    }//GEN-LAST:event_btn_resetActionPerformed

    private void btn_printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_printActionPerformed
        try{
            boolean print=jsalestable.print();
            if(!print){
                JOptionPane.showMessageDialog(null,"Unable to print!!");
            }
        }catch(java.awt.print.PrinterException e){
            JOptionPane.showMessageDialog(null,e.getMessage()); 
        }
    }//GEN-LAST:event_btn_printActionPerformed

    private void btn_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exitActionPerformed
        close();
        try{}catch(Exception  e){}
        finally{
        try{
            rs.close();
            pst.close();
            conn.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }}
        Customer c=new Customer();
        c.setVisible(true);
        
    }//GEN-LAST:event_btn_exitActionPerformed
               
    private void btn_hideActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hideActionPerformed
        Update_table();
    }//GEN-LAST:event_btn_hideActionPerformed

    private void btn_showActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_showActionPerformed
        
        SimpleDateFormat dateformat=new SimpleDateFormat("YYYY-MM-dd");
        if(Date_From.getDate()!=null){
            date1=dateformat.format(Date_From.getDate());
        }
        if(Date_To.getDate()!=null){
        date2=dateformat.format(Date_To.getDate());
        }
        if(ProductFilter.getSelectedItem().toString()!=null){
        product=ProductFilter.getSelectedItem().toString();
        }
        if(NameFilter.getSelectedItem().toString()!=null){
        name=NameFilter.getSelectedItem().toString();
        }
        if(date1!=null && date2!=null && !product.equals("") && !name.equals("")){
            try{
                String query="select sales.sales_id,customer.C_ID,customer.FIRST_NAME,customer.LAST_NAME,customer.PHONE,SALES.PRODUCT_NAME ,sales.QTy, sales.RATE ,\n" +
                        "(sales.QTY * sales.RATE) as TOTAL_AMT,SALES.PAID_AMT,(SALES.QTY*SALES.RATE)-SALES.PAID_AMT AS DUE_AMT,SALES.DATE ,  \n" +
                        " CASE  WHEN (SALES.QTY*SALES.RATE)-SALES.PAID_AMT ==0 THEN 'Paid'\n" +
                        "ELSE 'Credit'\n" +
                        "END PAY_TYPE from customer,sales where customer.C_ID = sales.C_ID and sales.product_name='"+product+"'\n" +
                        "and customer.first_name='"+name+"' and sales.date between '"+date1+"' and '"+date2+"' ";
                
                pst=conn.prepareStatement(query);
                rs=pst.executeQuery();            
                jsalestable.setModel(DbUtils.resultSetToTableModel(rs));            
                if(rs.next())
                {
                    rs.close();
                    pst.close();
                }
            } catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
            String query="select sum(QTY *RATE) as Total, sum(Paid_amt) as Paid, sum((QTY*RATE)-PAID_AMT) as Due from sales,customer where sales.C_ID = customer.C_ID and sales.product_name='"+product+"'\n" +
                        "and customer.first_name='"+name+"' and sales.date between '"+date1+"' and '"+date2+"' ";
        try {
            pst=conn.prepareStatement(query);
            rs=pst.executeQuery();
            String a=rs.getString("Total");
            String b=rs.getString("Paid");
            String c=rs.getString("Due");
            Sales.setText(a);
            Payment.setText(b);
            Due.setText(c);
        }catch (Exception e) {
           JOptionPane.showMessageDialog(null,e);
        }
            
        }
        else if(date1!=null && date2!=null && !name.equals("")){
           
            try {
                String query="select sales.sales_id,customer.C_ID,customer.FIRST_NAME,customer.LAST_NAME,customer.PHONE,SALES.PRODUCT_NAME ,sales.QTy, sales.RATE ,\n" +
                "(sales.QTY * sales.RATE) as TOTAL_AMT,SALES.PAID_AMT,(SALES.QTY*SALES.RATE)-SALES.PAID_AMT AS DUE_AMT,SALES.DATE ,  \n" +
                " CASE  WHEN (SALES.QTY*SALES.RATE)-SALES.PAID_AMT ==0 THEN 'Paid'\n" +
                "ELSE 'Credit'\n" +
                "END PAY_TYPE from customer,sales where customer.C_ID = sales.C_ID and customer.first_name='"+name+"' and sales.date between '"+date1+"' and '"+date2+"'";
                
                pst=conn.prepareStatement(query);
                rs=pst.executeQuery();
                jsalestable.setModel(DbUtils.resultSetToTableModel(rs));
                if(rs.next())
                {
                rs.close();
                pst.close();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,e);
            }
               String query="select sum(QTY *RATE) as Total, sum(Paid_amt) as Paid, sum((QTY*RATE)-PAID_AMT) as Due from sales,customer where sales.C_ID = customer.C_ID and \n" +
                        "customer.first_name='"+name+"' and sales.date between '"+date1+"' and '"+date2+"' ";
        
        try {
            pst=conn.prepareStatement(query);
            rs=pst.executeQuery();
            String a=rs.getString("Total");
            String b=rs.getString("Paid");
            String c=rs.getString("Due");
            Sales.setText(a);
            Payment.setText(b);
            Due.setText(c);
        }catch (Exception e) {
           JOptionPane.showMessageDialog(null,e);
        }
        }
        
        else if(date1!=null && date2!=null && !product.equals("") ){
           
            try {
                String query="select sales.sales_id,customer.C_ID,customer.FIRST_NAME,customer.LAST_NAME,customer.PHONE,SALES.PRODUCT_NAME ,sales.QTy, sales.RATE ,\n" +
                "(sales.QTY * sales.RATE) as TOTAL_AMT,SALES.PAID_AMT,(SALES.QTY*SALES.RATE)-SALES.PAID_AMT AS DUE_AMT,SALES.DATE ,  \n" +
                " CASE  WHEN (SALES.QTY*SALES.RATE)-SALES.PAID_AMT ==0 THEN 'Paid'\n" +
                "ELSE 'Credit'\n" +
                "END PAY_TYPE from customer,sales where customer.C_ID = sales.C_ID and sales.product_name='"+product+"' and sales.date between '"+date1+"' and '"+date2+"'";
                
                pst=conn.prepareStatement(query);
                rs=pst.executeQuery();
                jsalestable.setModel(DbUtils.resultSetToTableModel(rs));
                if(rs.next())
                {
                rs.close();
                pst.close();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,e);
            }
               String query="select sum(QTY *RATE) as Total, sum(Paid_amt) as Paid, sum((QTY*RATE)-PAID_AMT) as Due from sales,customer where sales.C_ID = customer.C_ID and sales.product_name='"+product+"'\n" +
                        " and sales.date between '"+date1+"' and '"+date2+"' ";
        try {
            pst=conn.prepareStatement(query);
            rs=pst.executeQuery();
            String a=rs.getString("Total");
            String b=rs.getString("Paid");
            String c=rs.getString("Due");
            Sales.setText(a);
            Payment.setText(b);
            Due.setText(c);
        }catch (Exception e) {
           JOptionPane.showMessageDialog(null,e);
        }
        }     
        
        else if(!product.equals("") && !name.equals("")){
           
            try {
                String query="select sales.sales_id,customer.C_ID,customer.FIRST_NAME,customer.LAST_NAME,customer.PHONE,SALES.PRODUCT_NAME ,sales.QTy, sales.RATE ,\n" +
                "(sales.QTY * sales.RATE) as TOTAL_AMT,SALES.PAID_AMT,(SALES.QTY*SALES.RATE)-SALES.PAID_AMT AS DUE_AMT,SALES.DATE ,  \n" +
                " CASE  WHEN (SALES.QTY*SALES.RATE)-SALES.PAID_AMT ==0 THEN 'Paid'\n" +
                "ELSE 'Credit'\n" +
                "END PAY_TYPE from customer,sales where customer.C_ID = sales.C_ID and sales.product_name='"+product+"' and customer.first_name='"+name+"'";
                pst=conn.prepareStatement(query);
                rs=pst.executeQuery();
                jsalestable.setModel(DbUtils.resultSetToTableModel(rs));
                if(rs.next())
                {
                rs.close();
                pst.close();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,e);
            }
               String query="select sum(QTY *RATE) as Total, sum(Paid_amt) as Paid, sum((QTY*RATE)-PAID_AMT) as Due from sales,customer where sales.C_ID = customer.C_ID and sales.product_name='"+product+"'\n" +
                        "and customer.first_name='"+name+"'";
        try {
            pst=conn.prepareStatement(query);
            rs=pst.executeQuery();
            String a=rs.getString("Total");
            String b=rs.getString("Paid");
            String c=rs.getString("Due");
            Sales.setText(a);
            Payment.setText(b);
            Due.setText(c);
        }catch (Exception e) {
           JOptionPane.showMessageDialog(null,e);
        }
        }
        
        else if(!name.equals("")){
           
            try {
                String query="select sales.sales_id,customer.C_ID,customer.FIRST_NAME,customer.LAST_NAME,customer.PHONE,SALES.PRODUCT_NAME ,sales.QTy, sales.RATE ,\n" +
                "(sales.QTY * sales.RATE) as TOTAL_AMT,SALES.PAID_AMT,(SALES.QTY*SALES.RATE)-SALES.PAID_AMT AS DUE_AMT,SALES.DATE ,  \n" +
                " CASE  WHEN (SALES.QTY*SALES.RATE)-SALES.PAID_AMT ==0 THEN 'Paid'\n" +
                "ELSE 'Credit'\n" +
                "END PAY_TYPE from customer,sales where customer.C_ID = sales.C_ID and customer.first_name='"+name+"'";
                
                pst=conn.prepareStatement(query);
                rs=pst.executeQuery();
                jsalestable.setModel(DbUtils.resultSetToTableModel(rs));
                if(rs.next())
                {
                rs.close();
                pst.close();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,e);
            }
               String query="select sum(QTY *RATE) as Total, sum(Paid_amt) as Paid, sum((QTY*RATE)-PAID_AMT) as Due from sales,customer where sales.C_ID = customer.C_ID and \n" +
                        "customer.first_name='"+name+"'";
        try {
            pst=conn.prepareStatement(query);
            rs=pst.executeQuery();
            String a=rs.getString("Total");
            String b=rs.getString("Paid");
            String c=rs.getString("Due");
            Sales.setText(a);
            Payment.setText(b);
            Due.setText(c);
        }catch (Exception e) {
           JOptionPane.showMessageDialog(null,e);
        }
        }
        
        else if(!product.equals("")){
           
            try {
                String query="select sales.sales_id,customer.C_ID,customer.FIRST_NAME,customer.LAST_NAME,customer.PHONE,SALES.PRODUCT_NAME ,sales.QTy, sales.RATE ,\n" +
                "(sales.QTY * sales.RATE) as TOTAL_AMT,SALES.PAID_AMT,(SALES.QTY*SALES.RATE)-SALES.PAID_AMT AS DUE_AMT,SALES.DATE ,  \n" +
                " CASE  WHEN (SALES.QTY*SALES.RATE)-SALES.PAID_AMT ==0 THEN 'Paid'\n" +
                "ELSE 'Credit'\n" +
                "END PAY_TYPE from customer,sales where customer.C_ID = sales.C_ID and sales.product_name='"+product+"'";
                
                pst=conn.prepareStatement(query);
                rs=pst.executeQuery();
                jsalestable.setModel(DbUtils.resultSetToTableModel(rs));
                if(rs.next())
                {
                rs.close();
                pst.close();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,e);
            }
               String query="select sum(QTY *RATE) as Total, sum(Paid_amt) as Paid, sum((QTY*RATE)-PAID_AMT) as Due from sales,customer where sales.C_ID = customer.C_ID and sales.product_name='"+product+"' ";
        try {
            pst=conn.prepareStatement(query);
            rs=pst.executeQuery();
            String a=rs.getString("Total");
            String b=rs.getString("Paid");
            String c=rs.getString("Due");
            Sales.setText(a);
            Payment.setText(b);
            Due.setText(c);
        }catch (Exception e) {
           JOptionPane.showMessageDialog(null,e);
        }
        }
        else{
             try {
                String query="select sales.sales_id,customer.C_ID,customer.FIRST_NAME,customer.LAST_NAME,customer.PHONE,SALES.PRODUCT_NAME ,sales.QTy, sales.RATE ,\n" +
                "(sales.QTY * sales.RATE) as TOTAL_AMT,SALES.PAID_AMT,(SALES.QTY*SALES.RATE)-SALES.PAID_AMT AS DUE_AMT,SALES.DATE ,  \n" +
                " CASE  WHEN (SALES.QTY*SALES.RATE)-SALES.PAID_AMT ==0 THEN 'Paid'\n" +
                "ELSE 'Credit'\n" +
                "END PAY_TYPE from customer,sales where customer.C_ID = sales.C_ID and sales.date between '"+date1+"' and '"+date2+"'";
                 
                pst=conn.prepareStatement(query);
                rs=pst.executeQuery();
                jsalestable.setModel(DbUtils.resultSetToTableModel(rs));
                if(rs.next())
                {
                rs.close();
                pst.close();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,e);
            }
                String query="select sum(QTY *RATE) as Total, sum(Paid_amt) as Paid, sum((QTY*RATE)-PAID_AMT) as Due from sales,customer where sales.C_ID = customer.C_ID and \n" +
                        "sales.date between '"+date1+"' and '"+date2+"'";
                         try {
                pst=conn.prepareStatement(query);
                rs=pst.executeQuery();
                String a=rs.getString("Total");
                String b=rs.getString("Paid");
                String c=rs.getString("Due");
                Sales.setText(a);
                Payment.setText(b);
                Due.setText(c);
            }catch (Exception e) {
               JOptionPane.showMessageDialog(null,e);
            }
        }
    }//GEN-LAST:event_btn_showActionPerformed
    
    private void SalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalesActionPerformed
       
    }//GEN-LAST:event_SalesActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Date_From.setDate(null);
        Date_To.setDate(null);
        ProductFilter.setSelectedItem("");
        NameFilter.setSelectedItem("");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void TxtSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TxtSearchFocusGained
       
    }//GEN-LAST:event_TxtSearchFocusGained

    private void TxtSearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TxtSearchFocusLost
       
    }//GEN-LAST:event_TxtSearchFocusLost

    private void TxtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtSearchActionPerformed

    private void TxtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtSearchKeyReleased
        /* String search=TxtSearch.getText();
        String query="select * from customer where first_name like '%"+search+"%'";
        try {
            pst=conn.prepareStatement(query);
            rs=pst.executeQuery();
            jcustomertable.setModel(DbUtils.resultSetToTableModel(rs));
            if(rs.next())
            {
                rs.close();
                pst.close();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }*/
        String search=TxtSearch.getText().toLowerCase();
        TableRowSorter<DefaultTableModel> tr=new TableRowSorter<DefaultTableModel>(model);
        jsalestable.setRowSorter(tr);
        if (search.trim().length() == 0) {
            tr.setRowFilter(null);
        } else {
            tr.setRowFilter(RowFilter.regexFilter("(?i)" + search));
        }
    }//GEN-LAST:event_TxtSearchKeyReleased

    private void CustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CustomerMouseClicked

    }//GEN-LAST:event_CustomerMouseClicked

    private void CustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CustomerActionPerformed
        close();
        try{}catch(Exception  e){}
        finally{
        try{
            rs.close();
            pst.close();
            conn.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }}
        Customer c=new Customer();
        c.setVisible(true);
    }//GEN-LAST:event_CustomerActionPerformed

    private void Sales1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Sales1MouseClicked

    }//GEN-LAST:event_Sales1MouseClicked

    private void Sales1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sales1ActionPerformed
        
        close();
        try{}catch(Exception  e){}
        finally{
        try{
            rs.close();
            pst.close();
            conn.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }}
        Sales s=new Sales();
        s.setVisible(true);
    }//GEN-LAST:event_Sales1ActionPerformed

    private void StockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StockActionPerformed
      
        close();
        try{}catch(Exception  e){}
        finally{
        try{
            rs.close();
            pst.close();
            conn.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }}
        Stock st=new Stock();
        st.setVisible(true);
    }//GEN-LAST:event_StockActionPerformed

    private void helpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpActionPerformed
        
        ChangePsw ss=new ChangePsw();
        ss.setVisible(true);
    }//GEN-LAST:event_helpActionPerformed

    private void menu_logoutMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_menu_logoutMenuSelected

    }//GEN-LAST:event_menu_logoutMenuSelected

    private void menu_logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_logoutMouseClicked
        JOptionPane.showMessageDialog(null, "Logout successfully");
        Login lg=new Login();
        lg.setVisible(true);
        close();
    }//GEN-LAST:event_menu_logoutMouseClicked

    private void menu_logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_logoutActionPerformed

    }//GEN-LAST:event_menu_logoutActionPerformed

    private void jsalestableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jsalestableMouseClicked
        try{
            int row=jsalestable.getSelectedRow();
            String s=(jsalestable.getModel().getValueAt(row,0).toString());
            String query="select sales.sales_id,customer.C_ID,customer.FIRST_NAME,SALES.PRODUCT_NAME ,sales.QTy, sales.RATE ,\n" +
                        "SALES.PAID_AMT,SALES.DATE \n" +
                        " from customer,sales where customer.C_ID = sales.C_ID and sales_id='"+s+"'";
            pst=conn.prepareStatement(query);
            rs=pst.executeQuery();
            if(rs.next()){
                String add1=rs.getString("Sales_ID");
                TxtSales.setText(add1);
                String add2=rs.getString("C_ID");
                CID.setText(add2);
                String add3=rs.getString("FIRST_NAME");
                jComboBox1.setSelectedItem(add3);
                String add4=rs.getString("Product_NAME");
                jComboBox2.setSelectedItem(add4);
                String add5=rs.getString("QTY");
                TxtQty.setText(add5);
                String add6=rs.getString("RATE");
                TxtRate.setText(add6);
                String add7=rs.getString("PAID_AMT");
                TxtPaid.setText(add7);
                String add8=rs.getString("DATE");
                TxtDate.setText(add8);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        Update_table();                                     
    }//GEN-LAST:event_jsalestableMouseClicked

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
            java.util.logging.Logger.getLogger(Sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sales().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CID;
    private javax.swing.JMenuItem Customer;
    private com.toedter.calendar.JDateChooser Date_From;
    private com.toedter.calendar.JDateChooser Date_To;
    private javax.swing.JTextField Due;
    private javax.swing.JComboBox<String> NameFilter;
    private javax.swing.JTextField Payment;
    private javax.swing.JComboBox<String> ProductFilter;
    private javax.swing.JTextField Sales;
    private javax.swing.JMenuItem Sales1;
    private javax.swing.JMenuItem Stock;
    private javax.swing.JTextField TxtDate;
    private javax.swing.JTextField TxtPaid;
    private javax.swing.JTextField TxtQty;
    private javax.swing.JTextField TxtRate;
    private javax.swing.JTextField TxtSales;
    private javax.swing.JTextField TxtSearch;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_exit;
    private javax.swing.JButton btn_hide;
    private javax.swing.JButton btn_insert;
    private javax.swing.JButton btn_print;
    private javax.swing.JButton btn_reset;
    private javax.swing.JButton btn_show;
    private javax.swing.JButton btn_update;
    private javax.swing.JMenuItem help;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JTable jsalestable;
    private javax.swing.JMenu menu_logout;
    // End of variables declaration//GEN-END:variables
}
