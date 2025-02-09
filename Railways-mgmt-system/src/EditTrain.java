import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;

public class EditTrain extends JFrame implements ActionListener{
    
    JLabel heading,trainNo,trainName,src,dest,arrival,reached,date,trainType;
    JTextField trainNoField,trainNameField,srcField,destField,arrivalField,reachedField;
    JDateChooser dateField;
    JComboBox<String> trainTypeField;
    JButton update,reset,back,search;

    EditTrain(){
        setSize(700,500);
        setLayout(null);
        setVisible(true);
        setLocation(350,100);
        getContentPane().setBackground(Color.white);

        ImageIcon i = new ImageIcon("src\\icons\\background.png");
        JLabel background = new JLabel(i);
        background.setBounds(0, 0, 700, 500);
        setContentPane(background); 
        heading= new JLabel("EDIT TRAIN");
        heading.setBounds(230,10,150,30);
        heading.setFont(new Font("Arial",Font.BOLD,25));
        add(heading);

        trainNo= new JLabel("Train No:");
        trainNo.setBounds(200,60,100,30);
        trainNo.setFont(new Font("Arial",Font.BOLD,15));
        add(trainNo);

        trainNoField = new JTextField();
        trainNoField.setBounds(280,60,150,25);
        trainNoField.setFont(new Font("Arial",Font.BOLD,15));
        enterButton(trainNoField);
        add(trainNoField);

        trainName= new JLabel("Train Name: ");
        trainName.setBounds(350,130,100,30);
        trainName.setFont(new Font("Arial",Font.BOLD,15));
        add(trainName);

        trainNameField= new JTextField();
        trainNameField.setBounds(450,130,150,25);
        trainNameField.setFont(new Font("Arial",Font.BOLD,15));
        trainNameField.setEditable(false);
        enterButton(trainNameField);
        add(trainNameField);

        src = new JLabel("Source:");
        src.setBounds(70,190,150,30);
        src.setFont(new Font("Arial",Font.BOLD,15));
        add(src);

        srcField= new JTextField();
        srcField.setBounds(170,190,150,25);
        srcField.setFont(new Font("Arial",Font.BOLD,15));
        srcField.setEditable(false);
        enterButton(srcField);
        add(srcField);

        dest= new JLabel("Destination: ");
        dest.setBounds(350,190,150,30);
        dest.setFont(new Font("Arial",Font.BOLD,15));
        add(dest);

        destField= new JTextField();
        destField.setBounds(450,190,150,25);
        destField.setFont(new Font("Arial",Font.BOLD,15));
        destField.setEditable(false);
        enterButton(destField);
        add(destField);

        arrival= new JLabel("Arrival Time: ");
        arrival.setBounds(70,250,150,30);
        arrival.setFont(new Font("Arial",Font.BOLD,15));
        add(arrival);

        arrivalField= new JTextField();
        arrivalField.setBounds(170,250,150,25);
        arrivalField.setFont(new Font("Arial",Font.BOLD,15));
        arrivalField.setEditable(false);
        enterButton(arrivalField);
        add(arrivalField);

        reached= new JLabel("Reached Time:");
        reached.setBounds(350,250,150,30);
        reached.setFont(new Font("Arial",Font.BOLD,15));
        add(reached);

        reachedField= new JTextField();
        reachedField.setBounds(480,250,150,25);
        reachedField.setFont(new Font("Arial",Font.BOLD,15));
        reachedField.setEditable(false);
        enterButton(reachedField);
        add(reachedField);

        date= new JLabel("Date: ");
        date.setBounds(70,130,150,30);
        date.setFont(new Font("Arial",Font.BOLD,15));
        add(date);
        
        dateField= new JDateChooser();
        dateField.setBounds(170,130,150,25);
        dateField.setFont(new Font("Arial",Font.BOLD,15));
        dateField.setEnabled(false);
        enterButton(dateField);
        add(dateField);

        trainType= new JLabel("Train Type: ");
        trainType.setBounds(200,310,150,30);
        trainType.setFont(new Font("Arial",Font.BOLD,15));
        add(trainType);

        String[] traintype={"AC","Sleeper","General"};
        trainTypeField = new JComboBox<>(traintype);
        trainTypeField.setBounds(300,310,150,25);
        trainTypeField.setFont(new Font("Arial",Font.BOLD,15));
        enterButton(trainTypeField);
        add(trainTypeField);

        search= new JButton("Search");
        search.setBounds(450,60,80,30);
        search.setBackground(Color.BLACK);
        search.setForeground(Color.WHITE);
        search.addActionListener(this);
        add(search);

        update= new JButton("Update");
        update.setBounds(70,380,100,30);
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.addActionListener(this);
        enterButton(update);
        add(update);

        reset= new JButton("Reset");
        reset.setBounds(280,380,100,30);
        reset.setBackground(Color.BLACK);
        reset.setForeground(Color.WHITE);
        reset.addActionListener(this);
        enterButton(reset);
        add(reset);

        back= new JButton("Back");
        back.setBounds(500,380,100,30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        enterButton(back);
        add(back);
    
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==search){
            String train_no= trainNoField.getText();

            if(train_no.isEmpty()){
                JOptionPane.showMessageDialog(null,"Train Number cannot be empty");
                return;
            }

            if(train_no.length()!=5){
                JOptionPane.showMessageDialog(null,"Train length must be of length 5 only");
                return;
            }

            try{
                CONN c= new CONN();
                String query="SELECT * FROM Trains WHERE Train_no = '"+train_no+"'";
                ResultSet rs= c.s.executeQuery(query);
                if(rs.next()){
                    trainNameField.setText(rs.getString("Train_name"));
                    srcField.setText(rs.getString("Src"));
                    destField.setText(rs.getString("Destination"));
                    arrivalField.setText(rs.getString("Arrival_time"));
                    reachedField.setText(rs.getString("Final_time"));
                    String date= rs.getString("Arrival_date");
                    trainTypeField.setSelectedItem(rs.getString("Train_type"));
                    SimpleDateFormat sdf= new SimpleDateFormat("dd MMM yyyy");
                    java.util.Date parsedDate= sdf.parse(date);
                    dateField.setDate(parsedDate);

                    trainNameField.setEditable(true);
                    srcField.setEditable(true);
                    destField.setEditable(true);
                    arrivalField.setEditable(true);
                    reachedField.setEditable(true);
                    trainTypeField.setEnabled(true);
                    dateField.getDateEditor().setEnabled(false);
                }
                else{
                    JOptionPane.showMessageDialog(null,"Train Number not found");
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else if(ae.getSource()==update){
            String train_no= trainNoField.getText();
            String train_name= trainNameField.getText();
            String source= srcField.getText();
            String destination = destField.getText();
            String arrival= arrivalField.getText();
            String reached=reachedField.getText();
            String train_type= (String)trainTypeField.getSelectedItem();

            java.util.Date selectedDate = dateField.getDate();
            if (selectedDate == null) {
                JOptionPane.showMessageDialog(null, "Please select a date");
                return;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
            String formattedDate = sdf.format(selectedDate);

            if(train_no.isEmpty()){
                JOptionPane.showMessageDialog(null,"Train Number cannot be empty");
                return;
            }

            if(train_no.length()!=5){
                JOptionPane.showMessageDialog(null,"Train length must be of length 5 only");
                return;
            }

            try{
                CONN c= new CONN();
                String query= "UPDATE Trains SET Train_name= '"+train_name+"', Src= '"+source+"', Destination= '"+destination+"', Arrival_time= '"+arrival+"', Final_time='"+reached+"', Arrival_date= '"+formattedDate+"', Train_type= '"+train_type+"' WHERE Train_no= '"+train_no+"'";
                int rowsAffected=c.s.executeUpdate(query);
                if(rowsAffected>0){
                    JOptionPane.showMessageDialog(null,"Train Details Updated");
                }
                else{
                    JOptionPane.showMessageDialog(null,"No rows updated");
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

    private void enterButton(Component comp){
        if(comp instanceof JTextField){
            JTextField tf= (JTextField) comp;
            if(tf==trainNoField){
                tf.addKeyListener(new KeyAdapter() {
                    public void keyPressed(KeyEvent ke){
                        if(ke.getKeyCode()== KeyEvent.VK_ENTER){
                            search.doClick();
                        }
                    }
                });
            }
            else{
                tf.addKeyListener(new KeyAdapter() {
                    public void keyPressed(KeyEvent ke){
                        if(ke.getKeyCode()== KeyEvent.VK_ENTER){
                            update.doClick();
                        }
                    }
                });
            }
        }
        if(comp instanceof JDateChooser){
            JDateChooser dc= (JDateChooser) comp;
            dc.getDateEditor().getUiComponent().addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent ke){
                    if(ke.getKeyCode()== KeyEvent.VK_ENTER){
                        update.doClick();
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
                        update.doClick();
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
