import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class EmployeeDetails extends JFrame implements ActionListener{
    
    JLabel heading,employee_id;
    JButton search,back;
    JTextField employeeidField;
    JTable table;
    JScrollPane pane;
    DefaultTableModel model;

    EmployeeDetails(){
        setSize(950,620);
        setVisible(true);
        setLayout(null);
        setLocation(300,100);

        enterButton(this);

        ImageIcon i = new ImageIcon("src\\icons\\background.png");
        JLabel background = new JLabel(i);
        background.setBounds(0, 0, 900, 500);
        setContentPane(background); 
        heading=new JLabel("EMPLOYEE DETAILS");
        heading.setBounds(280,20,400,35);
        heading.setFont(new Font("Arial",Font.BOLD,30));
        add(heading);

        employee_id=new JLabel("Employee_ID: ");
        employee_id.setBounds(280,70,150,30);
        employee_id.setFont(new Font("Arial",Font.BOLD,15));
        add(employee_id);

        employeeidField=new JTextField();
        employeeidField.setBounds(410,70,150,30);
        employeeidField.setFont(new Font("Arial",Font.BOLD,15));
        enterButton(employeeidField);
        add(employeeidField);

        search=new JButton("Search");
        search.setBounds(580,70,80,30);
        search.addActionListener(this);
        search.setBackground(Color.BLACK);
        search.setForeground(Color.white);
        add(search);

        back = new JButton("Back");
        back.setBounds(400,530,80,30);
        back.addActionListener(this);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.white);
        enterButton(back);
        add(back);

        model=new DefaultTableModel();
        model.addColumn("Employee_Id");
        model.addColumn("Employee_name");
        model.addColumn("Profession");
        model.addColumn("Division");
        model.addColumn("City");
        model.addColumn("Salary");
        model.addColumn("Qualification");
        model.addColumn("Dob");
        model.addColumn("Joining_year");

        table=new JTable(model);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);

        pane= new JScrollPane(table);
        pane.setBounds(45,120,850,400);
        add(pane);

        String empno= employeeidField.getText();
            model.setRowCount(0);
            if(empno.isEmpty()){
                showAllData();
            }

    }

    public void actionPerformed(ActionEvent ae){
        
        if(ae.getSource()==search){
            String empno= employeeidField.getText();
            model.setRowCount(0);

            if(empno.isEmpty()){
                JOptionPane.showMessageDialog(null,"Employee ID cannot be empty");
                return;
            }

            if(empno.length()!=5){
                JOptionPane.showMessageDialog(null,"Employee ID must be of length 5 only");
                return;
            }

            int emp_no=Integer.parseInt(empno);
            try{
                CONN c= new CONN();
                String query="SELECT * FROM Employees WHERE Employee_Id = "+emp_no+"";
                ResultSet rs= c.s.executeQuery(query);
                if(rs.next()){
                    Object[] row = new Object[model.getColumnCount()];
                    for (int i = 1; i <= model.getColumnCount(); i++) {
                        row[i - 1] = rs.getObject(i);
                    }
                    model.addRow(row);
                }
                else{
                    JOptionPane.showMessageDialog(null,"Employees not found");
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
            String query="SELECT * FROM Employees";
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
