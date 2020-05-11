import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LeftPanel extends JPanel {
    private JTextField textField,
            textField_1,
            textField_2,
            textField_3,
            textField_4,
            textField_5,
            textField_6;

    JComboBox<String> comboBox;
    public LeftPanel(){
        //Appearance
        setLayout(new GridLayout(0, 1));
        setBackground(Color.yellow);
        setBorder(BorderFactory.createEtchedBorder());

        textField = new JTextField();



        textField.setColumns(10);

        JLabel LastName = new JLabel("Last Name");

        add(LastName);
        add(textField);

        textField_1 = new JTextField();


        textField_1.setColumns(10);

        JLabel FirstName = new JLabel("First Name");

        add(FirstName);
        add(textField_1);

        textField_2 = new JTextField();


        textField_2.setColumns(10);


        JLabel MiddleName = new JLabel("Middle Name");

        add(MiddleName);
        add(textField_2);

        JLabel ExtensionName = new JLabel("Extension Name");

        add(ExtensionName);


        textField_3 = new JTextField();

        add(textField_3);
        textField_3.setColumns(10);


        JLabel lblAddress = new JLabel("Address");

        add(lblAddress);

        JTextArea textArea_1 = new JTextArea();

        add(textArea_1);

        JLabel DateOfBirth = new JLabel("Date of Birth");

        add(DateOfBirth);

        textField_4 = new JTextField();

        add(textField_4);
        textField_4.setColumns(10);

        JLabel FathersName = new JLabel("Fathers Name");

        add(FathersName);

        textField_5 = new JTextField();

        add(textField_5);
        textField_5.setColumns(10);

        JLabel MothersName = new JLabel("Mothers Name");

        add(MothersName);

        textField_6 = new JTextField();

        add(textField_6);
        textField_6.setColumns(12);

        JLabel ContactNumber = new JLabel("Contact Number");
        add(ContactNumber);

        JLabel lblGender = new JLabel("Gender");
        lblGender .setBounds(70, 240, 212, 14);
        add( lblGender);

        comboBox = new JComboBox<String>();
        comboBox.addItem("Select");
        comboBox.addItem("Male");
        comboBox.addItem("Female");

        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });


    }

    public boolean isEmpty(){
        if (textField.getText().isEmpty() || (textField_1.getText().isEmpty()) || (textField_2.getText().isEmpty()) || textField_3.getText().isEmpty()
                || (textField_4.getText()).isEmpty() || (textField_5.getText()).isEmpty() || (textField_6.getText()).isEmpty()
        ){
            return true;
        }else
            return false;
    }

    public  void clear(){


        comboBox.setSelectedItem("Select");
    }

    

}
