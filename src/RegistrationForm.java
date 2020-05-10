import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class RegistrationForm extends JFrame{


    private JFrame frame;


    private JPanel  bottomPanel;
    private LeftPanel leftPanel;
    public RightPanel rightPanel;
    JButton btnClear;



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
        setSize(800, 800);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        initializePanels();
        add(leftPanel);
        add(rightPanel);
        add(bottomPanel, BorderLayout.SOUTH);
        System.out.println("Added panels");
        btnClear = new JButton("CLOSE");


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLayout(null);

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
                else
                    JOptionPane.showMessageDialog(null, "Data Submitted");
            }
        });

        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                leftPanel.clear();
                rightPanel.clear();


            }
        });


    }

    private void initializePanels() {
        rightPanel=new RightPanel();
        bottomPanel=new JPanel();
        leftPanel = new LeftPanel();
        System.out.println("Initialized Panels");
    }


}