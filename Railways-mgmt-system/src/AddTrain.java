import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

public class AddTrain extends JFrame implements ActionListener{
    
    JLabel heading,trainNo,trainName,src,dest,arrival,reached,date,trainType;
    JTextField trainNoField,trainNameField,srcField,destField,arrivalField,reachedField;
    JDateChooser dateField;
    JComboBox<String> trainTypeField;
    JButton save,reset,back;

    AddTrain(){
        setSize(700,500);
        setLayout(null);
        setLocation(350,100);
    
        ImageIcon i = new ImageIcon("src\\icons\\background.png");
        JLabel background = new JLabel(i);
        background.setBounds(0, 0, 700, 500);
        setContentPane(background); 
    
        heading = new JLabel("ADD TRAIN");
        heading.setBounds(230,10,150,30);
        heading.setFont(new Font("Arial",Font.BOLD,25));
        background.add(heading);
    
        trainNo = new JLabel("Train No:");
        trainNo.setBounds(70,80,100,30);
        trainNo.setFont(new Font("Arial",Font.BOLD,15));
        background.add(trainNo);
    
        trainNoField = new JTextField();
        trainNoField.setBounds(170,80,150,25);
        trainNoField.setFont(new Font("Arial",Font.BOLD,15));
        enterButton(trainNoField);
        background.add(trainNoField);
    
        trainName = new JLabel("Train Name: ");
        trainName.setBounds(350,80,100,30);
        trainName.setFont(new Font("Arial",Font.BOLD,15));
        background.add(trainName);
    
        trainNameField = new JTextField();
        trainNameField.setBounds(450,80,150,25);    
        trainNameField.setFont(new Font("Arial",Font.BOLD,15));
        enterButton(trainNameField);
        background.add(trainNameField);
    
        src = new JLabel("Source:");
        src.setBounds(70,140,150,30);
        src.setFont(new Font("Arial",Font.BOLD,15));
        background.add(src);
    
        srcField = new JTextField();
        srcField.setBounds(170,140,150,25);
        srcField.setFont(new Font("Arial",Font.BOLD,15));
        enterButton(srcField);
        background.add(srcField);
    
        dest = new JLabel("Destination: ");
        dest.setBounds(350,140,150,30);
        dest.setFont(new Font("Arial",Font.BOLD,15));
        background.add(dest);
    
        destField = new JTextField();
        destField.setBounds(450,140,150,25);
        destField.setFont(new Font("Arial",Font.BOLD,15));
        enterButton(destField);
        background.add(destField);
    
        arrival = new JLabel("Arrival Time: ");
        arrival.setBounds(70,200,150,30);
        arrival.setFont(new Font("Arial",Font.BOLD,15));
        background.add(arrival);
    
        arrivalField = new JTextField();
        arrivalField.setBounds(170,200,150,25);
        arrivalField.setFont(new Font("Arial",Font.BOLD,15));
        enterButton(arrivalField);
        background.add(arrivalField);
    
        reached = new JLabel("Reached Time:");
        reached.setBounds(350,200,150,30);
        reached.setFont(new Font("Arial",Font.BOLD,15));
        background.add(reached);
    
        reachedField = new JTextField();
        reachedField.setBounds(480,200,150,25);
        reachedField.setFont(new Font("Arial",Font.BOLD,15));
        enterButton(reachedField);
        background.add(reachedField);
    
        date = new JLabel("Date: ");
        date.setBounds(70,260,150,30);
        date.setFont(new Font("Arial",Font.BOLD,15));
        background.add(date);
    
        dateField = new JDateChooser();
        dateField.setBounds(170,260,150,25);
        dateField.setFont(new Font("Arial",Font.BOLD,15));
        dateField.getDateEditor().setEnabled(false);
        enterButton(dateField);
        background.add(dateField);

        trainType= new JLabel("Train Type:");
        trainType.setBounds(350,260,150,30);
        trainType.setFont(new Font("Arial",Font.BOLD,15));
        background.add(trainType);

        String[] traintype={"AC","Sleeper","General"};
        trainTypeField = new JComboBox<>(traintype);
        trainTypeField.setBounds(450,260,150,25);
        trainTypeField.setFont(new Font("Arial",Font.BOLD,15));
        enterButton(trainTypeField);
        background.add(trainTypeField);
    
        save = new JButton("Save");
        save.setBounds(70,350,100,30);
        save.setBackground(Color.BLACK);
        save.setForeground(Color.WHITE);
        save.addActionListener(this);
        enterButton(save);
        background.add(save);
    
        reset = new JButton("Reset");
        reset.setBounds(280,350,100,30);
        reset.setBackground(Color.BLACK);
        reset.setForeground(Color.WHITE);
        reset.addActionListener(this);
        enterButton(reset);
        background.add(reset);
    
        back = new JButton("Back");
        back.setBounds(500,350,100,30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        enterButton(back);
        background.add(back);
    
        setVisible(true);
    }
    

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==save){
            try{
                String train_no= trainNoField.getText();
                String train_name= trainNameField.getText();
                String source= srcField.getText();
                String destination = destField.getText();
                String arrival= arrivalField.getText();
                String reached=reachedField.getText();
                String train_type= (String) trainTypeField.getSelectedItem();

            java.util.Date selectedDate = dateField.getDate();
            if (selectedDate == null) {
                JOptionPane.showMessageDialog(null, "Please select a date");
                return;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
            String formattedDate = sdf.format(selectedDate);

                if(train_no.isEmpty()||train_name.isEmpty()|| source.isEmpty()||destination.isEmpty()||arrival.isEmpty()||reached.isEmpty()||formattedDate.isEmpty()||train_type.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Please fill all the fields");
                    return;
                }

                if(train_no.length()!=5){
                    JOptionPane.showMessageDialog(null, "Train number must consist of 5 digits");
                    return;
                }

                int train_no_int = Integer.parseInt(train_no);
                boolean dup=searchDuplicateTrainNumber(train_no_int);
                if(dup==true){
                    JOptionPane.showMessageDialog(null, "Train number already exists");
                    return;
                }

                CONN c= new CONN();
                String query="INSERT INTO Trains(Train_no,Train_name,Src,Destination,Arrival_time,Final_time,Arrival_date,Train_type)" + "VALUES("+train_no_int+",'"+train_name+"','"+source+"','"+destination+"','"+arrival+"','"+reached+"','"+formattedDate+"','"+train_type+"'  )";
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null,"Train Added SuccessFully");
                int confirm=JOptionPane.showConfirmDialog(this,"Do you want to add another train?","Confirmation",JOptionPane.YES_NO_OPTION);
                if(confirm==JOptionPane.YES_OPTION){
                    setVisible(false);
                    new AddTrain().setVisible(true);
                }
                else{
                    setVisible(false);
                }
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "Database Error!: "+e.getMessage());
            }
        }
        else if(ae.getSource()==reset){
            formReset();
        }
        else{
            setVisible(false);
        }
    }

    private boolean searchDuplicateTrainNumber(int train_no){
        try{
            CONN c= new CONN();
            String query="SELECT Train_no FROM Trains WHERE Train_no = '"+train_no+"'";
            ResultSet rs=c.s.executeQuery(query);
            if(rs.next()){
                return true;
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Database Error!: "+e.getMessage());
        }
        return false;
    }

    private void enterButton(Component comp){
        if(comp instanceof JTextField){
            JTextField tf= (JTextField) comp;
            tf.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent ke){
                    if(ke.getKeyCode()== KeyEvent.VK_ENTER){
                        save.doClick();
                    }
                }
            });
        }
        if(comp instanceof JDateChooser){
            JDateChooser dc= (JDateChooser) comp;
            dc.getDateEditor().getUiComponent().addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent ke){
                    if(ke.getKeyCode()== KeyEvent.VK_ENTER){
                        save.doClick();
                    }
                }
            });
        }
        if(comp instanceof JButton){
            JButton btn = (JButton) comp;
            btn.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent ke){
                    if(ke.getKeyCode()== KeyEvent.VK_ENTER){
                        btn.doClick();
                    }
                }
            });
        }
        if(comp instanceof JComboBox){
            JComboBox<?> cb = (JComboBox<?>) comp;
            cb.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent ke){
                    if(ke.getKeyCode()== KeyEvent.VK_ENTER){
                        save.doClick();
                    }
                }
            });
            
        }
    }

    private void formReset(){
        trainNoField.setText("");
        trainNameField.setText("");
        srcField.setText("");
        destField.setText("");
        arrivalField.setText("");
        reachedField.setText("");
        dateField.setDate(null);
    }
}
