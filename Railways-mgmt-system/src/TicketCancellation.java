import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class TicketCancellation extends JFrame implements ActionListener{
    
    JLabel heading,pnr;
    JButton search,back,cancel;
    JTextField pnr_field;
    JTable table;
    JScrollPane pane;
    DefaultTableModel model;
    private int commonPNR;

    TicketCancellation(int commonPNR){
        this.commonPNR=commonPNR;
        setSize(800,620);
        setVisible(true);
        setLayout(null);
        setLocation(300,100);

        enterButton(this);

        ImageIcon i = new ImageIcon("src\\icons\\background.png");
        JLabel background = new JLabel(i);
        background.setBounds(0, 0, 700, 500);
        setContentPane(background); 
        heading=new JLabel("TICKET CANCELLATION");
        heading.setBounds(180,20,400,35);
        heading.setFont(new Font("Arial",Font.BOLD,30));
        add(heading);

        pnr=new JLabel("Pnr_number: ");
        pnr.setBounds(180,70,120,30);
        pnr.setFont(new Font("Arial",Font.BOLD,15));
        add(pnr);

        pnr_field=new JTextField();
        pnr_field.setBounds(300,70,150,30);
        pnr_field.setFont(new Font("Arial",Font.BOLD,15));
        enterButton(pnr_field);
        add(pnr_field);

        search=new JButton("Search");
        search.setBounds(470,70,80,30);
        search.addActionListener(this);
        search.setBackground(Color.BLACK);
        search.setForeground(Color.white);
        add(search);

        cancel=new JButton("Cancel");
        cancel.setBounds(200,530,80,30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        enterButton(cancel);
        add(cancel);

        back = new JButton("Back");
        back.setBounds(330,530,80,30);
        back.addActionListener(this);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        enterButton(back);
        add(back);

        model=new DefaultTableModel();
        model.addColumn("Name");
        model.addColumn("Contact_no");
        model.addColumn("Age");
        model.addColumn("Sex");
        model.addColumn("Train_no");
        model.addColumn("Pnr_no");
        model.addColumn("Src");
        model.addColumn("Destination");
        model.addColumn("Travel_date");

        table=new JTable(model);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);

        pane= new JScrollPane(table);
        pane.setBounds(45,120,700,400);
        add(pane);

    }

    public void actionPerformed(ActionEvent ae){
        
        if(ae.getSource()==search){
            String commonPNR= pnr_field.getText();
            model.setRowCount(0);
            if(commonPNR.isEmpty()){
                JOptionPane.showMessageDialog(null,"PNR number cannot be Empty");
                return;
            }
            try{
                CONN c= new CONN();
                String query="SELECT * FROM Passengers WHERE Pnr_no = '"+commonPNR+"'";
                ResultSet rs= c.s.executeQuery(query);
                boolean found= false;
                while(rs.next()){
                    Object[] row = new Object[model.getColumnCount()];
                    for (int i = 1; i <= model.getColumnCount(); i++) {
                        row[i - 1] = rs.getObject(i);
                    }
                    model.addRow(row);
                    found=true;
                }
                if(found==false){
                    JOptionPane.showMessageDialog(null,"Tickets not found");
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else if(ae.getSource()==cancel){
            String commonPNR= pnr_field.getText();
            int rows= model.getRowCount();
            String train_no=null;

            if(commonPNR.isEmpty()){
                JOptionPane.showMessageDialog(null,"PNR number cannot be Empty");
                return;
            }

            try{
                CONN c= new CONN();
                String query= "SELECT Train_no FROM Passengers WHERE Pnr_no = '"+commonPNR+"'";
                ResultSet rs= c.s.executeQuery(query);
                if(rs.next()){
                    train_no= rs.getString("Train_no");
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }

            int confirmed=JOptionPane.showConfirmDialog(this,"Are you sure you want to cancel your tickets?","Confirmation",JOptionPane.YES_NO_OPTION);
            if(confirmed==JOptionPane.YES_OPTION){
                model.setRowCount(0);
                try{
                    if(train_no !=null){
                        seatUpdater(train_no,rows);
                    }
                    for(int i=1;i<=rows;i++){
                        CONN c= new CONN();
                        String query="DELETE FROM Passengers WHERE Pnr_no ='"+commonPNR+"'";
                        c.s.executeUpdate(query);
                    }
                        JOptionPane.showMessageDialog(null,"Ticket Cancelled Successfully");
                        return;
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(null,"Database Error: "+e.getMessage());
                }
            }
            else{
                return;
            }
        }
        else{
            setVisible(false);
        }
    } 
    
    
    private void enterButton(Component comp) {
        if(comp instanceof JTextField){
            JTextField tf = (JTextField) comp;
            tf.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent ke) {
                    if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                        search.doClick();
                    }
                    
                }
            });
        }
        else if(comp instanceof JButton){
            JButton jb = (JButton) comp;
            jb.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent ke) {
                    if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                        jb.doClick();
                    }
                }
            });
        }
        else{
            this.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent ke){
                    if(ke.getKeyCode()==KeyEvent.VK_ENTER){
                        back.doClick();
                    }
                }
            });
        }
    }

    private void seatUpdater(String train_no,int rows){
        int seats=0;
        try{
            CONN c = new CONN();
            String query="SELECT Seats FROM Trains WHERE Train_no = "+train_no+"";
            ResultSet rs = c.s.executeQuery(query);
            if(rs.next()){
                seats= rs.getInt("Seats");
            }
            seats+=rows;
            String query1="UPDATE Trains SET Seats= "+seats+" WHERE Train_no = "+train_no+" ";
            int updated=c.s.executeUpdate(query1);
            if(updated==0){
                JOptionPane.showMessageDialog(null,"Seats update failed!");
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Database Error: "+e.getMessage());
        }
    }
}

