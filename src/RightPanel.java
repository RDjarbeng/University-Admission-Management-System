import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RightPanel extends JPanel {

    private JTextField textField_7,
            textField_8;
    private JTextArea textArea_2;;
    JComboBox<String> AccountBox;
    JComboBox<String> CourseBox;
    JComboBox<String> YearLevel;

    public RightPanel(){
        //Appearance
        setLayout(new GridLayout(15,    1));
        setBackground(Color.BLUE);
        setForeground(Color.WHITE);
        setBorder(BorderFactory.createRaisedBevelBorder());

        textField_7 = new JTextField();
        textField_7.setBounds(710, 38, 270, 25);

        textField_7.setColumns(12);

        JLabel PreviousSchool = new JLabel("Previous School");
        PreviousSchool.setBounds(610, 38, 96, 14);
        add(PreviousSchool);
        add(textField_7);

        JLabel lblSAddress = new JLabel("School Address");

        add(lblSAddress);

        JTextArea textArea_2 = new JTextArea();

        add(textArea_2);

        JLabel SchoolDocument = new JLabel("School Document");

        add(SchoolDocument);

        textField_8 = new JTextField();

        add(textField_8);
        textField_8.setColumns(12);

        JLabel LastDateAttended = new JLabel("Last Date Attended");

        add(LastDateAttended);


        JLabel AccountStatus = new JLabel("Account Status");

        add(AccountStatus);

        AccountBox = new JComboBox<String>();
        AccountBox.addItem("Select");
        AccountBox.addItem("Complete");
        AccountBox.addItem("In Progress");

        AccountBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });

        add(AccountBox);

        JLabel lblCourse = new JLabel("Course");

        add(lblCourse);

        CourseBox = new JComboBox<String>();
        CourseBox.addItem("Select");
        CourseBox.addItem("Computer Engineering");
        CourseBox.addItem("Computer Science");
        CourseBox.addItem("Economics");
        CourseBox.addItem("Political Science");
        CourseBox.addItem("Medicine");
        CourseBox.addItem("Law");
        CourseBox.addItem("Statistics");

        CourseBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });

        add(CourseBox);


        JLabel YearLev = new JLabel("Year Level");

        add(YearLev);

        YearLevel = new JComboBox<String>();
        YearLevel.addItem("Select");
        YearLevel.addItem("100");
        YearLevel.addItem("200");
        YearLevel.addItem("300");
        YearLevel.addItem("400");


        YearLevel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });

        add( YearLevel);



    }

    public boolean isEmpty(){
        if((textField_7.getText().isEmpty()) || (textField_8.getText()).isEmpty() || (AccountBox.getSelectedItem().equals("Select")) || (YearLevel.getSelectedItem().equals("Select")) || (CourseBox.getSelectedItem().equals("Select"))){
            return true;
        }else{
            return false;
        }

    }

    public void clear(){
        //textArea_2.setText(null);
        textField_7.setText(null);
        textField_8.setText(null);
        AccountBox.setSelectedItem("Select");
        YearLevel.setSelectedItem("Select");
        CourseBox.setSelectedItem("Select");
    }


}
