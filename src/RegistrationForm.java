import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class RegistrationForm extends JFrame{

    private JPanel  bottomPanel;
    private LeftPanel leftPanel;
    private JPanel detailsPanel, titlePanel;
    public RightPanel rightPanel;
    private JButton btnClear;
    public JLabel title;
    Font titleFont, userFont;
    String user;




    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                     new RegistrationForm("Test");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public RegistrationForm(String CurrentUser) {
        super("Registration Form");//Initialize frame
        setLayout(new BorderLayout(5,5));

        //pack();

        //Keep track of the user
        user = CurrentUser;




        initializePanels();
        //Title
            title = new JLabel("University of Ghana");
            //Appearance
            title.setFont(titleFont);
            title.setForeground(Color.white);
            titlePanel.setBackground(Color.BLUE);
            titlePanel.add(title);




        detailsPanel.setLayout(new GridLayout(1,2 ,10, 10));
        detailsPanel.add(leftPanel);
        detailsPanel.add(rightPanel);
        detailsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE, 13),"Enter your details ("+user+")"));
        detailsPanel.setBackground(Color.green);
        detailsPanel.setToolTipText("Enter your admission details");

        JButton btnSubmit = new JButton("SAVE");

        btnSubmit.setBackground(Color.BLUE);
        btnSubmit.setForeground(Color.MAGENTA);
        btnSubmit.setBounds(65, 530, 89, 23);

        btnClear = new JButton("CLOSE");
        bottomPanel.add(btnSubmit);
        bottomPanel.add(btnClear);
        bottomPanel.setBackground(Color.DARK_GRAY);

        //Add to frame
        add(titlePanel, BorderLayout.NORTH);
        add(detailsPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        //System.out.println("Added panels");
        //setLocationRelativeTo(null);
        setVisible(true);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                if(leftPanel.isEmpty()|| rightPanel.isEmpty())
                    JOptionPane.showMessageDialog(null, "Data Missing");
                else {
                    JOptionPane.showMessageDialog(null, "Data Submitted");
                    leftPanel.clear();
                    rightPanel.clear();
                }
            }
        });

            btnClear.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    System.exit(0);


                    }
                }
            );


    }

        private void initializePanels() {
            rightPanel=new RightPanel();
            bottomPanel=new JPanel();
            leftPanel = new LeftPanel();
            detailsPanel = new JPanel();
            titlePanel = new JPanel();
            System.out.println("Initialized Panels");

            titleFont = new Font("Arial", Font.BOLD, 25);
            userFont = new Font("Arial", Font.ITALIC, 8);
        }


}