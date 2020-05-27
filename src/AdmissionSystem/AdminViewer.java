package AdmissionSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AdminViewer extends JFrame {

    JPanel selectQueryPanel, adminQueryPanel, queryPanels ; // A panel to hold the query
    JPanel buttonPanel; // A panel to hold the buttons
    JTextArea queryTextArea; // The user enters a query here
    JTextArea adminQueryTextArea; // The user enters a query here
    JButton submitButton, executeButton; // To submit a query
    JButton exitButton; // To quit the application

    /**
     Constructor
     */
    public AdminViewer()
         {
         // Set the window title.
         setTitle("Admin Viewer");
        // Specify an action for the close button.
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


         // Build the Query Panel.
         buildQueryPanel();
         selectQueryPanel.setBorder(BorderFactory.createTitledBorder("Enter SQL SELECT statements"));
         buildAdminQueryPanel();
         adminQueryPanel.setBorder(BorderFactory.createTitledBorder("(ADMIN)Enter SQL ALTER AND CREATE statements"));
         queryPanels = new JPanel();

         //queryPanels.setLayout(new GridLayout(2,1));

         // Build the Button Panel.
         buildButtonPanel();

         // Add the panels to the content pane.
         queryPanels.add(selectQueryPanel);
         queryPanels.add(adminQueryPanel);
         add(queryPanels, BorderLayout.CENTER);
         add(buttonPanel, BorderLayout.SOUTH);

         // Pack and display.
         pack();
         setVisible(true);
         setLocationRelativeTo(null);
         }
         /**
     The buildQueryPanel method builds a panel to hold the
 text area that the user will enter a query into.
             */

             private void buildQueryPanel()
         { // Create a panel.
         selectQueryPanel = new JPanel();
             // Create the Submit button.
             submitButton = new JButton("Submit");

         // Create a text area, 8 rows by 0 columns.
         queryTextArea = new JTextArea(15, 40);

         // Turn line wrapping on.
         queryTextArea.setLineWrap(true);

         // Add a scroll pane to the text area.
         JScrollPane qaScrollPane =
                 new JScrollPane(queryTextArea);
         qaScrollPane.setHorizontalScrollBarPolicy(
                 JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
         qaScrollPane.setVerticalScrollBarPolicy(
                 JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

         // Add the text area to the panel.
         selectQueryPanel.add(qaScrollPane);
         selectQueryPanel.add(submitButton);
         }
         //build a panel to accept sql commands to create and modify tables
    //admin only

    private void buildAdminQueryPanel()
    { // Create a panel.
        adminQueryPanel = new JPanel();
        executeButton = new JButton("Execute");

        // Create a text area, 8 rows by 0 columns.
        adminQueryTextArea = new JTextArea(15, 40);

        // Turn line wrapping on.
        adminQueryTextArea.setLineWrap(true);

        // Add a scroll pane to the text area.
        JScrollPane qaScrollPane =
                new JScrollPane(adminQueryTextArea);
        qaScrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        qaScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Add the text area to the panel.
        adminQueryPanel.add(qaScrollPane);
        adminQueryPanel.add(executeButton);
    }

    /**
     7 The buildButtonPanel method builds a panel
     to hold the Submit and Exit buttons.
     76 */

                 private void buildButtonPanel()
         {
         // Create a panel.
         buttonPanel = new JPanel();




         // Register an action listener for the Submit button.
         submitButton.addActionListener(new SubmitButtonListener());
         executeButton.addActionListener(new executeButtonListener());

         // Create the Exit button.
         exitButton = new JButton("Exit");

         // Register an action listener for the Exit button.
         exitButton.addActionListener(new ExitButtonListener());

         // Add the two buttons to the panel.


         buttonPanel.add(exitButton);
         }

    /**
     0 The SubmitButtonListener class is an action listener
     0 for the Submit button.
     0 */

         private class SubmitButtonListener implements ActionListener {
             public void actionPerformed(ActionEvent e)
             {
                 // Get the user's statement.
                 String userStatement = queryTextArea.getText();

                 // Qualify that it is a SELECT statement.
                 if (userStatement.trim().toUpperCase()
                 .startsWith("SELECT"))
                 {
                    // Create a CoffeeDBQuery object for the query.
                    Database dbQuery =
                            new Database();
                    try {
                        dbQuery.selectQuery(userStatement);
                        System.out.println("select query complete");

                        // Get the column names.
                        String[] colNames = dbQuery.getColumnNames();
                        for(String names: colNames)
                            System.out.println("col name " +names);
    // Get the table data.
                        String[][] data = dbQuery.getTableData();

                        System.out.println(colNames[0]+ colNames[1]);




                        System.out.println("Query Complete");
                        dbQuery.closeConnection();

                        // Display the results in a table.
                        TableFormat table =
                                new TableFormat(data, colNames);

                    }catch (SQLException ex){
                        System.out.println("SQL exception error: \n"+ ex.getMessage());
                    }

                     }
                 else
                 {
                     JOptionPane.showMessageDialog(null,
                             "Only enter SELECT statements.");
                     }
                 }
         }
        /**
         *  The ExitButtonListener class is an action listener
         *  for the Exit button.
         */
        private class ExitButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {

                System.exit(0);
            }
        }

        private class executeButtonListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userStatement = adminQueryTextArea.getText();
                userStatement =userStatement.trim().toUpperCase();
                System.out.println(userStatement);


                // Qualify that it is a SELECT statement.
                if (  userStatement != null)
                {
                    // Create a CoffeeDBQuery object for the query.
                    Database dbQuery =
                            new Database();
                    try {
                        dbQuery.execute(userStatement);
                        dbQuery.closeConnection();
                        System.out.println("Query successfully executed");
                    }catch (SQLException ex){
                        System.out.println("SQL exception error: \n"+ ex.getMessage());
                    }



                }
                else
                {
                    JOptionPane.showMessageDialog(null,
                            "SYNTAX ERROR: Only enter ALTER OR CREATE statements.");
                }
            }

        }


    public static void main(String[] args) {
        new AdminViewer();
    }



}




