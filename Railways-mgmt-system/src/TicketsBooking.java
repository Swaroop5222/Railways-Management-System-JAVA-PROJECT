import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.sql.*;

public class TicketsBooking extends JFrame implements ActionListener {
    JLabel heading, Name, PhoneNo, Age, Sex, TrainNo, PNR_Number, date, src, dest;
    JTextField NameField, PhoneNoField, AgeField, TrainNoField, srcField, destField;
    JDateChooser dateField;
    JRadioButton Male, Female;
    ButtonGroup genderGroup;
    JButton book, reset, back;
    JTextField PNR_NumberField;
    private int commonPNR;
    private int tickets;
    private static int ticketCount=1;

    TicketsBooking(int commonPNR,int tickets) {
        this.commonPNR = commonPNR;
        this.tickets= tickets;

        ImageIcon i = new ImageIcon("src\\icons\\background.png");
        JLabel background = new JLabel(i);
        background.setBounds(0, 0, 700, 500);
        setContentPane(background); 
        setSize(700, 500);
        setLayout(null);
        setVisible(true);
        setLocation(350, 100);

        heading = new JLabel("TICKETS BOOKING");
        heading.setBounds(200, 10, 300, 30);
        heading.setFont(new Font("Arial", Font.BOLD, 25));
        add(heading);

        Name = new JLabel("Name:");
        Name.setBounds(70, 80, 100, 30);
        Name.setFont(new Font("Arial", Font.BOLD, 15));
        add(Name);

        NameField = new JTextField();
        NameField.setBounds(170, 80, 150, 25);
        NameField.setFont(new Font("Arial", Font.BOLD, 15));
        enterButton(NameField);
        add(NameField);

        PhoneNo = new JLabel("Phone No:");
        PhoneNo.setBounds(350, 80, 100, 30);
        PhoneNo.setFont(new Font("Arial", Font.BOLD, 15));
        add(PhoneNo);

        PhoneNoField = new JTextField();
        PhoneNoField.setBounds(450, 80, 150, 25);
        PhoneNoField.setFont(new Font("Arial",Font.BOLD,15));
        enterButton(PhoneNoField);
        add(PhoneNoField);

        Age = new JLabel("Age:");
        Age.setBounds(70, 140, 100, 30);
        Age.setFont(new Font("Arial", Font.BOLD, 15));
        add(Age);

        AgeField = new JTextField();
        AgeField.setBounds(170, 140, 150, 25);
        AgeField.setFont(new Font("Arial", Font.BOLD, 15));
        enterButton(AgeField);
        add(AgeField);

        Sex = new JLabel("Sex:");
        Sex.setBounds(350, 140, 100, 30);
        Sex.setFont(new Font("Arial", Font.BOLD, 15));
        add(Sex);

        Male = new JRadioButton("Male");
        Male.setBounds(450, 140, 70, 25);
        Male.setFont(new Font("Arial", Font.PLAIN, 14));
        Male.setFocusPainted(false);
        enterButton(Male);
        add(Male);

        Female = new JRadioButton("Female");
        Female.setBounds(520, 140, 80, 25);
        Female.setFont(new Font("Arial", Font.PLAIN, 14));
        Female.setFocusPainted(false);
        enterButton(Female);
        add(Female);

        genderGroup = new ButtonGroup();
        genderGroup.add(Male);
        genderGroup.add(Female);

        TrainNo = new JLabel("Train No:");
        TrainNo.setBounds(70, 200, 100, 30);
        TrainNo.setFont(new Font("Arial", Font.BOLD, 15));
        add(TrainNo);

        TrainNoField = new JTextField();
        TrainNoField.setBounds(170, 200, 150, 25);
        TrainNoField.setFont(new Font("Arial", Font.BOLD, 15));
        enterButton(TrainNoField);
        add(TrainNoField);

        PNR_Number = new JLabel("PNR Number:");
        PNR_Number.setBounds(350, 200, 120, 30);
        PNR_Number.setFont(new Font("Arial", Font.BOLD, 15));
        add(PNR_Number);

        PNR_NumberField = new JTextField(String.valueOf(commonPNR));
        PNR_NumberField.setBounds(480, 200, 150, 25);
        PNR_NumberField.setFont(new Font("Arial", Font.BOLD, 15));
        PNR_NumberField.setEditable(false);
        PNR_NumberField.setBackground(Color.LIGHT_GRAY);
        enterButton(PNR_NumberField);
        add(PNR_NumberField);

        src = new JLabel("Source:");
        src.setBounds(70, 260, 100, 30);
        src.setFont(new Font("Arial", Font.BOLD, 15));
        add(src);

        srcField = new JTextField();
        srcField.setBounds(170, 260, 150, 25);
        srcField.setFont(new Font("Arial", Font.BOLD, 15));
        srcField.setEditable(false);
        enterButton(srcField);
        add(srcField);

        dest = new JLabel("Destination:");
        dest.setBounds(350, 260, 120, 30);
        dest.setFont(new Font("Arial", Font.BOLD, 15));
        add(dest);

        destField = new JTextField();
        destField.setBounds(480, 260, 150, 25);
        destField.setFont(new Font("Arial", Font.BOLD, 15));
        destField.setEditable(false);
        enterButton(destField);
        add(destField);

        date = new JLabel("Date:");
        date.setBounds(230, 320, 100, 30);
        date.setFont(new Font("Arial", Font.BOLD, 15));
        add(date);

        dateField = new JDateChooser();
        dateField.setBounds(280, 320, 150, 25);
        dateField.getDateEditor().setEnabled(false);
        dateField.setFont(new Font("Arial",Font.BOLD,15));
        enterButton(dateField);
        add(dateField);

        book = new JButton("Book");
        book.setBounds(70, 400, 100, 30);
        book.setBackground(Color.BLACK);
        book.setForeground(Color.WHITE);
        book.addActionListener(this);
        enterButton(book);
        add(book);

        reset = new JButton("Reset");
        reset.setBounds(280, 400, 100, 30);
        reset.setBackground(Color.BLACK);
        reset.setForeground(Color.WHITE);
        reset.addActionListener(this);
        enterButton(reset);
        add(reset);

        back = new JButton("Back");
        back.setBounds(500, 400, 100, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        enterButton(back);
        add(back);

        TrainNoField.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent fe){
                try{
                    int train= Integer.parseInt(TrainNoField.getText());
                    searchTrainDetails(train);
                    }
                    catch(Exception e){
                        JOptionPane.showMessageDialog(null, "Invalid Train Number");
                    }
            }
        });
    }

    public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == book) {
            try{
                String name = NameField.getText().trim();
                String phoneNo = PhoneNoField.getText().trim();
                String age = AgeField.getText().trim();
                String gender = Male.isSelected() ? "Male" : Female.isSelected() ? "Female" : "";
                String trainNo = TrainNoField.getText().trim();
                String source = srcField.getText().trim();
                String destination = destField.getText().trim();

                if (name.isEmpty() || phoneNo.isEmpty() || age.isEmpty() || gender.isEmpty() || trainNo.isEmpty() || source.isEmpty() || destination.isEmpty() || dateField.getDate()==null) {
                    JOptionPane.showMessageDialog(null, "Please fill all the fields");
                    return;
                }

                java.util.Date selectedDate = dateField.getDate();
                SimpleDateFormat sdf= new SimpleDateFormat("dd MMM yyyy");
                String date= sdf.format(selectedDate);

                if(trainNo.length()!=5){
                    JOptionPane.showMessageDialog(null,"Train Number must be of length 5 only!");
                    return;
                }

                if(phoneNo.length()!=10){
                    JOptionPane.showMessageDialog(null,"Phone Number must be of length 10 only!");
                    return;
                }

                if(age.length()!=2){
                    JOptionPane.showMessageDialog(null,"Age must be of length 2 only!");
                    return;
                }
                try{
                    int train=Integer.parseInt(trainNo);
                    if(!seatCalculator(train, 1)){
                        return;
                    }
                    String status="Booked";
                    CONN c=new CONN();
                    String query="INSERT INTO Passengers(Name,Contact_no,Age,Sex,Train_no,Pnr_no,Src,Destination,Travel_date,Ticket_status)"+" VALUES('"+name+"','"+phoneNo+"','"+age+"','"+gender+"','"+trainNo+"','"+commonPNR+"','"+source+"','"+destination+"','"+date+"','"+status+"')";
                    c.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Ticket Details Saved Successfully\nPNR Number: " + commonPNR);
                    if(ticketCount<tickets){
                        ticketCount++;
                        new TicketsBooking(commonPNR, tickets);
                    }
                    else{
                        int confirm= JOptionPane.showConfirmDialog(null,"Do you want to book tickets online or offline?","Confirmation",JOptionPane.YES_NO_OPTION);
                        if(confirm==JOptionPane.YES_OPTION){
                            new DigitalPayment();
                        }
                    }
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Error in Database Connection: "+e.getMessage());
                }

            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "Database Error!: "+e.getMessage());
                return;
            }
    }
    if (ae.getSource() == reset) {
        formReset();
    } 
        else{
            setVisible(false);
        }
}

private void formReset(){
    NameField.setText("");
    PhoneNoField.setText("");
    AgeField.setText("");
    TrainNoField.setText("");
    srcField.setText("");
    destField.setText("");
    dateField.setDate(null);
    genderGroup.clearSelection();
}

private void searchTrainDetails(int train_no){
    try{
        CONN c= new CONN();
        String query="SELECT Src,Destination,Arrival_date FROM Trains WHERE train_no= "+train_no+"";
        ResultSet rs= c.s.executeQuery(query);
        if(rs.next()){
            String source= rs.getString("Src");
            String dest= rs.getString("Destination");
            srcField.setText(source);
            destField.setText(dest);
            String date= rs.getString("Arrival_date");
            SimpleDateFormat sdf= new SimpleDateFormat("dd MMM yyyy");
            java.util.Date parsedDate= sdf.parse(date);
            dateField.setDate(parsedDate);
        }
        else{
            JOptionPane.showMessageDialog(null, "Train not found");
            return;
        }
    }
    catch(Exception e){
        JOptionPane.showMessageDialog(null,"Database Error!: "+e.getMessage());
    }
}

private void enterButton(Component comp){
    if(comp instanceof JTextField){
        JTextField tf= (JTextField) comp;
            tf.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke){
                if(ke.getKeyCode()== KeyEvent.VK_ENTER){
                    book.doClick();
                }
            }
        });
    }
    if(comp instanceof JDateChooser){
        JDateChooser dc= (JDateChooser) comp;
        dc.getDateEditor().getUiComponent().addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke){
                if(ke.getKeyCode()== KeyEvent.VK_ENTER){
                    book.doClick();
                }
            }
        });
    }
    if(comp instanceof JRadioButton){
        JRadioButton rb= (JRadioButton) comp;
        rb.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke){
                if(ke.getKeyCode()== KeyEvent.VK_ENTER){
                    book.doClick();
                }
            }
        });
    }
    if(comp instanceof JButton){
        JButton jb= (JButton) comp;
        jb.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke){
                if(ke.getKeyCode()== KeyEvent.VK_ENTER){
                    jb.doClick();
                }
            }
        });
    }
}

private boolean seatCalculator(int train_no,int input){
    int seats=-1;
    try{
        CONN c = new CONN();
        String query = "SELECT Seats FROM Trains WHERE Train_no = "+train_no+" ";
        ResultSet rs=c.s.executeQuery(query);
        if(rs.next()){
            seats = rs.getInt("Seats");
            if(seats>=input){
                    seats-=input;
                    query = "UPDATE Trains SET Seats = "+seats+" WHERE Train_no = "+train_no+" ";
                    int updated=c.s.executeUpdate(query);
                if(updated>0){
                    return true;
                }
                else{
                    JOptionPane.showMessageDialog(null,"Seat update failed!");
                    return false;
                }
        }
            else{
                JOptionPane.showMessageDialog(null, "Available seats are: "+seats);
                return false;
            }
        }
    }
    catch(Exception e){
        JOptionPane.showMessageDialog(null,"Database Error!: "+e.getMessage());
    }
    return false;
}


}
