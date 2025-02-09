import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import javaswingdev.drawer.Drawer;
import javaswingdev.drawer.DrawerController;

public class Home extends JFrame implements ActionListener{
    private ImageIcon img;
    private JLabel label1, heading, datetimelabel;
    private JButton button1, button2, button3, button4, button5;
    private JButton button6, button7, button8, button9, button10, menu1;
    private JButton logoutButton, Credits;
    private JPanel headingPanel;
    DrawerController leftdrawer;

    public Home() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1650, 850);
        this.setLayout(null);

        enterButton(this);

        Credits = createButton("Credits", "Credits");
        Credits.setBounds(150, 35, 100, 40);
        Credits.setBackground(new Color(125,85,50,255));
        Credits.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        enterButton(Credits);

        img = new ImageIcon("src\\icons\\homepage3.jpg");
        Image imgScaled = img.getImage().getScaledInstance(1650, 850, Image.SCALE_SMOOTH);
        img = new ImageIcon(imgScaled);

        label1 = new JLabel(img);
        label1.setBounds(0, 0, 1650, 850);

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy    HH:mm:ss");
        String formattedDateTime = now.format(dtf);

        datetimelabel = new JLabel(formattedDateTime);
        datetimelabel.setBounds(1250, 29, 800, 30);
        datetimelabel.setFont(new Font("Arial", Font.BOLD, 20));
        label1.add(datetimelabel);

        Timer t = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                LocalDateTime current = LocalDateTime.now();
                String formattedDateTime = current.format(dtf);
                datetimelabel.setText(formattedDateTime);
            }
        });
        t.start();

        headingPanel= new JPanel();
        headingPanel.setBounds(470,35,600,40);
        headingPanel.setBackground(new Color(176,226,225,120));

        heading = new JLabel("INDIAN RAILWAYS WELCOMES YOU!");
        heading.setBounds(470, 35, 700, 40);
        heading.setFont(new Font("Calisto MT", Font.BOLD, 30));
        headingPanel.add(heading);

        button1 = createButton("Add Train", "AddTrain");
        button2 = createButton("Edit Train", "EditTrain");
        button3 = createButton("Add Employee", "AddEmployee");
        button4 = createButton("Edit Employee", "EditEmployee");
        button5 = createButton("Delete Train", "DeleteTrain");
        button6 = createButton("Delete Employee", "DeleteEmployee");
        button7 = createButton("Train Details", "TrainDetails");
        button8 = createButton("Ticket Booking", "TicketBooking");
        button9 = createButton("Ticket Cancellation", "TicketCancellation");
        button10 = createButton("Employee Details", "EmployeeDetails");

        menu1 = new JButton("menu");
        menu1.setBounds(35, 35, 100, 40);
        menu1.setFont(new Font("Arial", Font.PLAIN, 20));
        menu1.setBackground(new Color(125,85,50,255));
        menu1.setForeground(Color.white);
        enterButton(menu1);
        menu1.addActionListener(this);

        logoutButton = createButton("LogOut", "LogOut");
        logoutButton.setBounds(1300, 700, 100, 40);
        logoutButton.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        enterButton(logoutButton);
        logoutButton.setBackground(new Color(125,85,50,255));

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.darkGray);
        leftPanel.setBounds(0, 20, 300, 950);
        leftPanel.setLayout(new BoxLayout(leftPanel,BoxLayout.Y_AXIS));

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.white);
        mainPanel.setBounds(0, 0, 1650, 800);
        mainPanel.setLayout(null);

        JButton[] buttons={button1,button2,button3,button4,button5,button6,button7,button8,button9,button10};
        for(JButton button: buttons){
            button.setMaximumSize(new Dimension(300, 500)); 
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            leftPanel.add(Box.createVerticalStrut(35)); 
            leftPanel.add(button);
        }

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);
        layeredPane.setBounds(0, 0, 1650, 850);
        layeredPane.add(label1, Integer.valueOf(0));
        layeredPane.add(logoutButton, Integer.valueOf(1));
        layeredPane.add(Credits, Integer.valueOf(1));
        layeredPane.add(menu1, Integer.valueOf(1));
        layeredPane.add(headingPanel,Integer.valueOf(1));

        mainPanel.add(layeredPane);
        this.add(mainPanel);

        this.setVisible(true);

        leftdrawer = Drawer.newDrawer(this)
                .header(leftPanel)
                .leftDrawer(false)
                .drawerBackground(Color.DARK_GRAY)
                .drawerWidth(300)
                .build();
    }

    private JButton createButton(String text, String actionCommand) {
        JButton button = new JButton(text);
        button.setForeground(Color.white);
        button.setBackground(Color.black);
        button.setFont(new Font("Comic Sans", Font.PLAIN, 30));
        button.setFocusable(false);
        button.setBorder(BorderFactory.createEtchedBorder());
        button.setPreferredSize(new Dimension(300, 400));
        button.setActionCommand(actionCommand);
        button.addActionListener(new ButtonActionListener());
        return button;
    }

    private class ButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            int commonPNR = 0;
            switch (command) {
                case "AddTrain":
                    new AddTrain();
                    break;
                case "EditTrain":
                    new EditTrain();
                    break;
                case "AddEmployee":
                    new AddEmployee();
                    break;
                case "EditEmployee":
                    new EditEmployee();
                    break;
                case "DeleteTrain":
                    new DeleteTrain();
                    break;
                case "DeleteEmployee":
                    new DeleteEmployee();
                    break;
                case "TrainDetails":
                    new TrainDetails();
                    break;
                case "TicketBooking":
                    String input = JOptionPane.showInputDialog("Enter the number of tickets to book:");
                    if (input != null) {
                        try {
                            int tickets = Integer.parseInt(input);
                            commonPNR = 100000 + new java.util.Random().nextInt(900000);
                            new TicketsBooking(commonPNR, tickets);
                            JOptionPane.showMessageDialog(null, "Common PNR for all tickets: " + commonPNR);
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number." + ex.getMessage());
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Number of seats must not be empty");
                    }
                    break;
                case "TicketCancellation":
                    new TicketCancellation(commonPNR);
                    break;
                case "EmployeeDetails":
                    new EmployeeDetails();
                    break;
                case "LogOut":
                    setVisible(false);
                    new Login();
                    break;
                case "Credits":
                    new Credits();
                    break;
                default:
                    System.out.println("Unknown action: " + command);
            }
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == menu1) {
            System.out.println("Left menu button clicked");
            if (leftdrawer.isShow()) {
                System.out.println("Hiding left drawer");
                leftdrawer.hide();
            } else {
                System.out.println("Showing left drawer");
                leftdrawer.show();
            }
        }
    }

    private void enterButton(Component comp) {
        if (comp instanceof JButton) {
            JButton button = (JButton) comp;
            button.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent ke) {
                    if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                        button.doClick();
                    }
                }
            });
        } else {
            this.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent ke) {
                    if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                        menu1.doClick();
                    }
                }
            });
        }
    }
}
