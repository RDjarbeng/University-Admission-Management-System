import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Flow;

public class LoginForm  {

    //labels
    private static JLabel userLabel;
    private static JLabel passwordLabel;
    private static JLabel success;

    //text fields
    private static JTextField userText;
    private static JPasswordField passwordText;
    //buttons
    private static JButton button;

    private Font font1, font2;

    //panels for user name and password
    JPanel userPanel, passwordPanel, buttonPanel;

    //constructor
    public LoginForm(){
        //Initialize frame
        JFrame frame= new JFrame("UAS Login Form");
        frame.setSize(500,500);
        frame.setVisible(true );
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4,1));

        //Initialize panels
        userPanel = new JPanel();
        userPanel.setBackground(Color.BLUE);
        passwordPanel=new JPanel();
        buttonPanel = new JPanel();

        //set Fonts
        font1 = new Font("Arial",Font.BOLD, 30);

        //Title
        JLabel titleLabel= new JLabel("Enter your Login details", JLabel.CENTER);
        titleLabel.setForeground(Color.white);
        titleLabel.setBackground(Color.BLACK);
        titleLabel.setFont(font1);

        //Usernme
        //initialize  username fields
        userLabel = new JLabel("Username");
        userText= new JTextField(20);

        userPanel.add(userLabel);
        userPanel.add( userText);

        //password
        passwordLabel= new JLabel("Password");
        passwordText = new JPasswordField(20);

        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordText);

        //button panel
        button = new JButton("Login");
        button.addActionListener(new LoginButtonListener() );
        buttonPanel.add(button);

        button = new JButton("New Student?");
        button.setBounds(280,150,125,25);
        buttonPanel.add(button);

        success = new JLabel("");
        buttonPanel.add(success);

        frame.add(titleLabel);
        frame.add(userPanel);
        frame.add(passwordPanel);
        frame.add(buttonPanel);

        frame.setVisible(true);

    }

    private class LoginButtonListener implements  ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String user = userText.getText();
            String password = passwordText.getText();
            System.out.println(user + "," + password);

            if (user.equals("k") && password.equals("123")) {
                success.setText("Login successful!");
            }
        }
    }


    public static void main(String[] args) {

        new LoginForm();

        //frame.setBounds(150,90,500,500);



        //label1.setBounds(120,10,200,40);




        //initialize  password fields




        //passwordLabel.setBounds(100,110,70,20);



        //passwordText.setBounds(200 ,110,130,20);

    }


}


