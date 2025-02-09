import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class TrainDetails extends JFrame implements ActionListener{
    
    JLabel heading,train_no;
    JButton search,back;
    JTextField train_no_field;
    JTable table;
    JScrollPane pane;
    DefaultTableModel model;

    TrainDetails(){
        setSize(800,620);
        setVisible(true);
        setLayout(null);
        setLocation(300,100);

        enterButton(this);

        ImageIcon i = new ImageIcon("src\\icons\\background.png");
        JLabel background = new JLabel(i);
        background.setBounds(0, 0, 700, 500);
        setContentPane(background); 
        heading=new JLabel("TRAIN DETAILS");
        heading.setBounds(250,20,250,35);
        heading.setFont(new Font("Arial",Font.BOLD,30));
        add(heading);

        train_no=new JLabel("Train_no: ");
        train_no.setBounds(200,70,100,30);
        train_no.setFont(new Font("Arial",Font.BOLD,15));
        add(train_no);

        train_no_field=new JTextField();
        train_no_field.setBounds(300,70,150,30);
        train_no_field.setFont(new Font("Arial",Font.BOLD,15));
        enterButton(train_no_field);
        add(train_no_field);

        search=new JButton("Search");
        search.setBounds(470,70,80,30);
        search.setBackground(Color.BLACK);
        search.setForeground(Color.WHITE);
        search.addActionListener(this);
        enterButton(search);
        add(search);

        back = new JButton("Back");
        back.setBounds(330,530,80,30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        enterButton(back);
        add(back);

        model=new DefaultTableModel();
        model.addColumn("Train_no");
        model.addColumn("Train_name");
        model.addColumn("Src");
        model.addColumn("Destination");
        model.addColumn("Arrival_time");
        model.addColumn("Final_time");
        model.addColumn("Arrival_date");
        model.addColumn("Train_type");
        model.addColumn("Seats");

        table=new JTable(model);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);

        pane= new JScrollPane(table);
        pane.setBounds(45,120,700,400);
        add(pane);

        String train_no= train_no_field.getText();
            model.setRowCount(0);
            if(train_no.isEmpty()){
                showAllData();
            }

    }

    public void actionPerformed(ActionEvent ae){
        
        if(ae.getSource()==search){
            String train_no= train_no_field.getText();
            model.setRowCount(0);
            if(train_no.isEmpty()){
                JOptionPane.showMessageDialog(null,"Train number cannot be Empty");
                showAllData();
                return;
            }
            try{
                CONN c= new CONN();
                String query="SELECT * FROM Trains WHERE Train_no = '"+train_no+"'";
                ResultSet rs= c.s.executeQuery(query);
                if(rs.next()){
                    Object[] row = new Object[model.getColumnCount()];
                    for (int i = 1; i <= model.getColumnCount(); i++) {
                        row[i - 1] = rs.getObject(i);
                    }
                    model.addRow(row);
                }
                else{
                    JOptionPane.showMessageDialog(null,"Trains not found");
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else{
            setVisible(false);
        }
    }

    private void showAllData(){
        model.setRowCount(0);
        try{
            CONN c = new CONN();
            String query="SELECT * FROM Trains";
            ResultSet rs= c.s.executeQuery(query);
            while(rs.next()){
                Object[] row = new Object[model.getColumnCount()];
                for(int i=1;i<=model.getColumnCount(); i++){
                    row[i-1]= rs.getObject(i);
                }
                model.addRow(row);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"DataBase Error: "+e.getMessage());
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
    
}
