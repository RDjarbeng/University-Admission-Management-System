package AdmissionSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RightPanel extends JPanel {

    private JFileChooser schoolDocumentChooser;
    private JTextArea textArea_2;;
    JComboBox<String> residenceBox;
    JComboBox<String> courseBox;
    JComboBox<String> YearLevel;

    public RightPanel(){
        //Appearance
        setLayout(new GridLayout(0,    1));
        setBackground(Color.BLUE);
        setForeground(Color.WHITE);
        setBorder(BorderFactory.createRaisedBevelBorder());

        courseBox = new JComboBox<String>();
        courseBox.addItem("Select");
        courseBox.addItem("Computer Engineering");
        courseBox.addItem("Computer Science");
        courseBox.addItem("Economics");
        courseBox.addItem("Political Science");
        courseBox.addItem("Medicine");
        courseBox.addItem("Law");
        courseBox.addItem("Statistics");

        JLabel courseLabel = new JLabel("Preferred course ");
        JLabel schoolDocumentLabel = new JLabel("School Document(WASSCE, ...)");

        schoolDocumentChooser = new JFileChooser();

        JLabel residenceLabel = new JLabel("Residence");

        residenceBox = new JComboBox<String>();
        residenceBox.addItem("Select");
        residenceBox.addItem("Akuafo");
        residenceBox.addItem("Non-Resident");

        residenceBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });





        courseBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });

        //Panels
        JPanel schoolDocumentPanel = new JPanel();
        JPanel coursePanel = new JPanel();
        JPanel residencePanel = new JPanel();

        schoolDocumentPanel.setLayout(new GridLayout(0,1));
        coursePanel.setLayout(new GridLayout(0,1));
        residencePanel.setLayout(new GridLayout(0,1));

        schoolDocumentPanel.add(schoolDocumentLabel);
        schoolDocumentPanel.add(schoolDocumentChooser);

        coursePanel.setBackground(Color.WHITE);
        coursePanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        coursePanel.add(courseLabel);
        coursePanel.add(courseBox);

        residencePanel.add(residenceLabel);
        residencePanel.add(residenceBox);




        //add to panel
        add(schoolDocumentPanel);
        add(coursePanel);
        add(residencePanel);


    }

    public boolean isEmpty(){
        if((residenceBox.getSelectedItem().equals("Select")) || (YearLevel.getSelectedItem().equals("Select")) || (courseBox.getSelectedItem().equals("Select"))){
            return true;
        }else{
            return false;
        }

    }

    public void clear(){
        //textArea_2.setText(null);

        residenceBox.setSelectedItem("Select");
        YearLevel.setSelectedItem("Select");
        courseBox.setSelectedItem("Select");
    }


}
