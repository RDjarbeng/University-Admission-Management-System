package AdmissionSystem;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminPanel extends JFrame {
    Connection con1;//todo: use database files for connectivity instead
    PreparedStatement insert;
    private JButton editButton;
    private JButton deleteButton;
    private JLabel titleLabel;
    //private JLabel titleLabel, lastNameLabel, firstNameLabel, genderLabel, DOBLabel, nationalityLabel,
    //        phoneNumberLabel, residentialAddressLabel, postaladdressLabel ;
    private JPanel studentDetailsPanel;
    private JScrollPane studentTableScrollPane1;
    private JTable studentInfoTable;
    private Database myDatabase;
    private String[] columnNames;
    private String[][] tableData;
    private JLabel [] studentDetails;
    private int columnCount;

    public AdminPanel() {
        super("Administrator Workarea");
        initialize();
        this.table_update();
        this.initComponents();

        add(titleLabel, BorderLayout.NORTH);
        studentDetailsPanel.setPreferredSize(new Dimension(250, 250));
        //studentDetailsPanel.setLayout(new GridLayout(0,1));
        add(studentDetailsPanel, BorderLayout.CENTER);
        getContentPane().add(studentTableScrollPane1, BorderLayout.EAST);
        add(editButton, BorderLayout.SOUTH);
    }

    private void initialize(){

        //panels and buttons
        this.studentDetailsPanel = new JPanel();
        this.titleLabel = new JLabel();
        this.editButton = new JButton();
        this.deleteButton = new JButton();
        this.studentTableScrollPane1 = new JScrollPane();
        this.studentInfoTable = new JTable();

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.titleLabel.setFont(new Font("Bauhaus 93", 1, 30));
        this.titleLabel.setText("Administrator Panel");
        this.studentDetailsPanel.setBorder(BorderFactory.createTitledBorder((Border)null, "Student Details", 2, 0, new Font("Bauhaus 93", 1, 18)));
        studentDetailsPanel.setLayout(new GridLayout(0, 1));

    }


    private void table_update() {
        //AdmissionSystem.Database
        myDatabase = new Database();
        try {
            myDatabase.selectQuery("Select ReceiptID, LastName, FirstName from Student");
            columnNames = myDatabase.getColumnNames();
            columnCount = columnNames.length;
            studentDetails = new JLabel[columnCount];
            tableData = myDatabase.getTableData();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        this.studentInfoTable.setModel(new DefaultTableModel(tableData, columnNames));
        this.studentInfoTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                AdminPanel.this.jTable1MouseClicked(evt);
            }
        });

    }

    private void initComponents() {
        //add JTable to a scrollPanel
        this.studentTableScrollPane1.setViewportView(this.studentInfoTable);
        //Edit button
        this.editButton.setText("Go to student");
        this.editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                AdminPanel.this.jButton2ActionPerformed(evt);
            }
        });

        //delete button
        this.deleteButton.setText("Delete");
        this.deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                AdminPanel.this.jButton3ActionPerformed(evt);
            }
        });
        //this.pack();
        setSize(new Dimension(700, 500));
        this.setLocationRelativeTo(null);




        //layout for the panel
        //GroupLayout jPanel1Layout = new GroupLayout(this.studentDetailsPanel);
        //this.studentDetailsPanel.setLayout(jPanel1Layout);
//        studentDetailsPanel.add(lastNameLabel);
//        studentDetailsPanel.add(firstNameLabel);
//        studentDetailsPanel.add(genderLabel);
//        studentDetailsPanel.add(nationalityLabel);
//        studentDetailsPanel.add(phoneNumberLabel);
//        studentDetailsPanel.add(postaladdressLabel);
//        studentDetailsPanel.add(residentialAddressLabel);

//        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGap(55, 55, 55).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addGroup(jPanel1Layout.createSequentialGroup().addGap(27, 27, 27).addComponent(this.editButton).addGap(31, 31, 31).addComponent(this.deleteButton)).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.lastNameLabel).addComponent(this.firstNameLabel).addComponent(this.genderLabel)).addGap(96, 96, 96).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.txtcourse, -1, 158, 32767).addComponent(this.txtmobile).addComponent(this.txtname)))).addContainerGap(80, 32767)));
//        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGap(57, 57, 57).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.lastNameLabel).addComponent(this.txtname, -2, -1, -2)).addGap(48, 48, 48).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.firstNameLabel).addComponent(this.txtmobile, -2, -1, -2)).addGap(46, 46, 46).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.genderLabel).addComponent(this.txtcourse, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 92, 32767).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.editButton).addComponent(this.deleteButton)).addGap(46, 46, 46)));





        //layout
        //potentially useless
        //GroupLayout layout = new GroupLayout(this.getContentPane());
        //this.getContentPane().setLayout(layout);

        //layout things
//        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(369, 369, 369).addComponent(this.titleLabel).addContainerGap(-1, 32767)).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.studentDetailsPanel, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 33, 32767).addComponent(this.studentTableScrollPane1, -2, 482, -2).addContainerGap()));
//        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(37, 37, 37).addComponent(this.titleLabel, -2, 60, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.studentDetailsPanel, -2, -1, -2).addComponent(this.studentTableScrollPane1, -2, -1, -2)).addContainerGap(-1, 32767)));


    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }




    //listeners

    private void jTable1MouseClicked(MouseEvent evt) {
        DefaultTableModel Df = (DefaultTableModel)this.studentInfoTable.getModel();
        int selectedIndex = this.studentInfoTable.getSelectedRow();
        int i=0;
        System.out.println("Column count "+ columnCount);
        while ( i <columnCount){

            studentDetails[i] = new JLabel(Df.getColumnName(i)+": "+Df.getValueAt(selectedIndex, i).toString());

            i++;
        }

        for(i = 0; i<studentDetails.length; i++){

            studentDetailsPanel.add(studentDetails[i]);


        }
        studentDetailsPanel.validate();
        this.validate();

    }

    private void jButton2ActionPerformed(ActionEvent evt) {
        DefaultTableModel Df = (DefaultTableModel)this.studentInfoTable.getModel();
        int selectedIndex = this.studentInfoTable.getSelectedRow();

        try {
            int id = Integer.parseInt(Df.getValueAt(selectedIndex, 0).toString());

            Class.forName("com.mysql.jdbc.Driver");
            this.con1 = DriverManager.getConnection("jdbc:mysql://localhost/universitydb", "root", "");
            this.insert = this.con1.prepareStatement("update record set name=?, mobile=?, course=? where id=?");
            this.insert.setInt(4, id);
            this.insert.executeUpdate();
            JOptionPane.showMessageDialog(this, "Record Updated");
            this.table_update();
//            this.txtname.setText("");
//            this.txtmobile.setText("");
//            this.txtcourse.setText("");
//            this.txtname.requestFocus();
        } catch (ClassNotFoundException var8) {
            Logger.getLogger(AdminPanel.class.getName()).log(Level.SEVERE, (String)null, var8);
        } catch (SQLException var9) {
            Logger.getLogger(AdminPanel.class.getName()).log(Level.SEVERE, (String)null, var9);
        }

    }

    private void jButton3ActionPerformed(ActionEvent evt) {
        DefaultTableModel Df = (DefaultTableModel)this.studentInfoTable.getModel();
        int selectedIndex = this.studentInfoTable.getSelectedRow();

        try {
            int id = Integer.parseInt(Df.getValueAt(selectedIndex, 0).toString());
            int dialogResult = JOptionPane.showConfirmDialog((Component)null, "Do you want to delete this entry?", "Warning", 0);
            if (dialogResult == 0) {
                Class.forName("com.mysql.jdbc.Driver");
                this.con1 = DriverManager.getConnection("jdbc:mysql://localhost/universitydb", "root", "");
                this.insert = this.con1.prepareStatement("delete from record where id=?");
                this.insert.setInt(1, id);
                this.insert.executeUpdate();
                JOptionPane.showMessageDialog(this, "Record Deleted");
                this.table_update();
//                this.txtname.setText("");
//                this.txtmobile.setText("");
//                this.txtcourse.setText("");
//                this.txtname.requestFocus();
            }
        } catch (ClassNotFoundException var6) {
            Logger.getLogger(AdminPanel.class.getName()).log(Level.SEVERE, (String)null, var6);
        } catch (SQLException var7) {
            Logger.getLogger(AdminPanel.class.getName()).log(Level.SEVERE, (String)null, var7);
        }

    }

    public static void main(String[] args) {
        try {
            UIManager.LookAndFeelInfo[] var1 = UIManager.getInstalledLookAndFeels();
            int                         var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                UIManager.LookAndFeelInfo info = var1[var3];
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException var5) {
            Logger.getLogger(AdminPanel.class.getName()).log(Level.SEVERE, (String)null, var5);
        } catch (InstantiationException var6) {
            Logger.getLogger(AdminPanel.class.getName()).log(Level.SEVERE, (String)null, var6);
        } catch (IllegalAccessException var7) {
            Logger.getLogger(AdminPanel.class.getName()).log(Level.SEVERE, (String)null, var7);
        } catch (UnsupportedLookAndFeelException var8) {
            Logger.getLogger(AdminPanel.class.getName()).log(Level.SEVERE, (String)null, var8);
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                (new AdminPanel()).setVisible(true);
            }
        });
    }
}
