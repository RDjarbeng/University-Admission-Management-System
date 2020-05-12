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
        
         public TableFormat(Object[][] data, String[] colNames)
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
              int WIDTH =(colNames.length < 10 && colNames.length>0)? 145*colNames.length: (colNames.length>10)?1000: 500;
              System.out.println("Width= "+WIDTH);
              int HEIGHT = (data.length< 7 && data.length>2)?120*data.length :(data.length>6)?700:500;
              setSize(WIDTH, HEIGHT);
              System.out.println("inside tableFormat");
         setVisible(true);
         setLocationRelativeTo(null);
         }
         }