import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class LoginForm  {

    //labels
    private static JLabel userLabel;
    private static JLabel passwordLabel;
    private static JLabel success;

    //text fields
    private static JTextField userText;
    private static JPasswordField passwordText;
    LoginButtonListener bListener;
    //buttons
    private static JButton loginButton, newStudentButton;

    private Font font1, font2, font3;

    //panels for user name and password
    JPanel userPanel, passwordPanel, loginButtonPanel, buttonsPanel, newStudentPanel;

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
        loginButtonPanel = new JPanel();
        buttonsPanel = new JPanel();
        newStudentPanel = new JPanel();

        //set Fonts
        font1 = new Font("Arial",Font.BOLD, 30);
        font2 = new Font("Dialog",Font.BOLD, 22);
        font3 = new Font("SansSerif",Font.ITALIC, 16);

        //Title
        JLabel titleLabel= new JLabel("Enter your Login details", JLabel.CENTER);
        titleLabel.setForeground(Color.white);
        titleLabel.setBackground(Color.BLACK);
        titleLabel.setFont(font1);

        //Usernme
        //initialize  username fields
        userLabel = new JLabel("Username");
        userLabel.setFont(font2);
        userText= new JTextField(20);

        userPanel.add(userLabel);
        userPanel.add( userText);

        //listener
        bListener = new LoginButtonListener();

        //password

        passwordLabel= new JLabel("Password");
        passwordLabel.setFont(font2);
        passwordText = new JPasswordField(20);
        passwordText.setActionCommand("OK");
        passwordText.addActionListener(bListener);

        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordText);

        //login button panel
        loginButtonPanel.setLayout(new FlowLayout());
        loginButton = new JButton("Login");
        loginButton.addActionListener(bListener);
        loginButtonPanel.add(loginButton);

        //Result indicator
        success = new JLabel("");
        loginButtonPanel.add(success);
        success.setFont(font3);

        newStudentButton = new JButton("New Student?");
        newStudentButton.setFont(font3);
        newStudentPanel.add(newStudentButton);

        //add to buttons Panel
        buttonsPanel.setLayout(new GridLayout(2,1));
        buttonsPanel.add(loginButtonPanel, BorderLayout.NORTH);
        buttonsPanel.add(newStudentPanel, BorderLayout.SOUTH);

        loginButton.setFont(font1);


        frame.add(titleLabel);
        frame.add(userPanel);
        frame.add(passwordPanel);
        frame.add(buttonsPanel);

        frame.setVisible(true);

    }

    private static boolean isPasswordCorrect(char[] input) {
        boolean isCorrect = true;
        String correctPass="123";
        char[] correctPassword = correctPass.toCharArray();

        if (input.length != correctPassword.length) {
            isCorrect = false;
        } else {
            isCorrect = Arrays.equals (input, correctPassword);
        }

        //Zero out the password.
        Arrays.fill(correctPassword,'0');

        return isCorrect;
    }

    private class LoginButtonListener implements  ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String user = userText.getText();
            char[] password = passwordText.getPassword();
            System.out.println(user + ", ***" );

            if (isPasswordCorrect(password)) {
                success.setForeground(Color.green);
                success.setText("Login successful!");
            }else
            {
                success.setForeground(Color.red);
                success.setText("Login failed!");
            }
        }
    }


    public static void main(String[] args) {

        new LoginForm();

        //frame.setBounds(150,90,500,500);
        //label1.setBounds(120,10,200,40);
        //passwordLabel.setBounds(100,110,70,20);
        //passwordText.setBounds(200 ,110,130,20);

    }


}


