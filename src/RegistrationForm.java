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
    Font titleFont;




    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                     new RegistrationForm();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public RegistrationForm() {
        super("Registration Form");
        setVisible(true);

        setSize(800, 600);
        //pack();

        setLayout(new BorderLayout(5,5));
        initializePanels();
        //Appearance
        title = new JLabel("University of Ghana");
        title.setFont(titleFont);
        title.setForeground(Color.white);
        titlePanel.setBackground(Color.BLUE);
        titlePanel.add(title);
        add(titlePanel, BorderLayout.NORTH);
        detailsPanel.setLayout(new GridLayout(1,2 ,10, 10));
        detailsPanel.add(leftPanel);
        detailsPanel.add(rightPanel);
        detailsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE, 3),"Enter your details"));
        detailsPanel.setBackground(Color.green);
        detailsPanel.setToolTipText("Enter your admission details");
        add(detailsPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        System.out.println("Added panels");
        btnClear = new JButton("CLOSE");
        bottomPanel.setBackground(Color.DARK_GRAY);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        JButton btnSubmit = new JButton("SAVE");

        btnSubmit.setBackground(Color.BLUE);
        btnSubmit.setForeground(Color.MAGENTA);
        btnSubmit.setBounds(65, 530, 89, 23);
        bottomPanel.add(btnSubmit);
        bottomPanel.add(btnClear);


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
        });


    }

    private void initializePanels() {
        rightPanel=new RightPanel();
        bottomPanel=new JPanel();
        leftPanel = new LeftPanel();
        detailsPanel = new JPanel();
        titlePanel = new JPanel();
        System.out.println("Initialized Panels");

        titleFont = new Font("Arial", Font.BOLD, 20);
    }


}