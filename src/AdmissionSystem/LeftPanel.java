package AdmissionSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LeftPanel extends JPanel {

    private JTextField lastNameField;
    private JTextField firstNameField_1;
    private JTextField middleNameField;
    private JTextField nationalityField;
    private JTextField dobField;
    private final JTextField phoneNumberField;

    private final JTextArea postalAddressArea;
    private final JTextArea residentialAddressArea;


    JComboBox<String> genderComboBox;
    private JPanel addressPanel;
    private JPanel nameAndDetailsPanel;

    public LeftPanel(){
        //Appearance
        setLayout(new GridLayout(0, 1));
        setBackground(Color.yellow);
        setBorder(BorderFactory.createEtchedBorder());

        initializePanels();


        //last Name
        JLabel LastName = new JLabel("Last Name");
        lastNameField = new JTextField(10);

        //first Name
        firstNameField_1 = new JTextField(10);
        JLabel lblFirstName = new JLabel("First Name");

        //Middle name
        middleNameField = new JTextField(10);
        JLabel lblmiddleName = new JLabel("Middle Name");
        //Date of Birth
        JLabel DateOfBirth = new JLabel("Date of Birth");
        dobField = new JTextField(10);

        //gender
        JLabel lblGender = new JLabel("Gender");
        genderComboBox = new JComboBox<String>();
        genderComboBox.addItem("Select");
        genderComboBox.addItem("Male");
        genderComboBox.addItem("Female");

        //Nationality
        nationalityField = new JTextField(10);
        JLabel lblNationality = new JLabel("Nationality");

        //Phone Number
        JLabel lblPhoneNumber = new JLabel("Phone Number");
        phoneNumberField = new JTextField(10);

        //Residential address
        JLabel lblResidentialAddress = new JLabel("Residential Address");
        residentialAddressArea = new JTextArea(10, 3);
        residentialAddressArea.setLineWrap(true);
        JScrollPane residentialAddressScrollPane = new JScrollPane(residentialAddressArea);


        residentialAddressScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        residentialAddressScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        //Postal address
        JLabel lblPostalAddress = new JLabel("Postal Address");
        postalAddressArea = new JTextArea(10,3);
        JScrollPane postalAddressScrollPane = new JScrollPane(postalAddressArea);


        postalAddressScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        postalAddressScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);



        add(LastName);
        add(lastNameField);
        add(lblFirstName);
        add(firstNameField_1);
        add(lblmiddleName);
        add(middleNameField);
        add(lblNationality);
        add(nationalityField);
        add(DateOfBirth);
        add(dobField);
        add( lblGender);
        add(genderComboBox);
        add(lblPhoneNumber);
        add(phoneNumberField);
        add(lblResidentialAddress);
        add(residentialAddressScrollPane);
        add(lblPostalAddress);
        add(postalAddressScrollPane);



        genderComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });


    }

    private void initializePanels() {
        addressPanel = new JPanel();
        nameAndDetailsPanel = new JPanel();
    }

    public boolean isEmpty(){
        if (lastNameField.getText().isEmpty() || (firstNameField_1.getText().isEmpty()) || (middleNameField.getText().isEmpty()) || nationalityField.getText().isEmpty()
                || (dobField.getText()).isEmpty()
        ){
            return true;
        }else
            return false;
    }

    public  void clear(){


        genderComboBox.setSelectedItem("Select");
    }

    

}
