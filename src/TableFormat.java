/**
  The TableFormatter class displays a populated JTable.
  */

import javax.swing.*;
import java.awt.*;

public class TableFormat extends JFrame
    {

         /**
  Constructor
  */
        
         public TableFormat(Object[][] data, Object[] colNames)
         {
         // Specify an action for the close button.
         setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
         // Create a JTable with the results.
         JTable table = new JTable(data, colNames);
         setLocationRelativeTo(null);
        
         // Put the table in a scroll pane.
         JScrollPane scrollPane = new JScrollPane(table);
        
         // Add the table to the content pane.
         add(scrollPane, BorderLayout.CENTER);
        
         // Set the size and display.
              // Constants for size.
              int WIDTH = 500;
              int HEIGHT = 500;
              setSize(WIDTH, HEIGHT);
         setVisible(true);
         }
         }