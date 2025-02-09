import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.sql.*;

public class DeleteEmployee extends JFrame implements ActionListener {

    JLabel heading, employeeNo, employeeName, post, Zone, city, salary, qualification, DOB, yearOfJoining;
    JTextField employeeNameField, postField, cityField, salaryField;
    JComboBox<String> qualificationField, ZoneField;
    JDateChooser DOBField, yearOfJoiningField;
    JButton delete, reset, back,search;
    JTextField employeeNoField;

    DeleteEmployee() {
        setSize(700, 500);
        setLayout(null);
        setVisible(true);
        setLocation(350, 100);

        ImageIcon i = new ImageIcon("src\\icons\\background.png");
        JLabel background = new JLabel(i);
        background.setBounds(0, 0, 700, 500);
        setContentPane(background); 
        heading = new JLabel("DELETE EMPLOYEE");
        heading.setBounds(230, 10, 250, 30);
        heading.setFont(new Font("Arial", Font.BOLD, 25));
        add(heading);

        employeeNo = new JLabel("Employee No:");
        employeeNo.setBounds(200, 80, 120, 30);
        employeeNo.setFont(new Font("Arial", Font.BOLD, 15));
        add(employeeNo);

        employeeNoField = new JTextField();
        employeeNoField.setBounds(320, 80, 150, 30);
        employeeNoField.setFont(new Font("Arial", Font.PLAIN, 15));
        enterButton(employeeNoField);
        add(employeeNoField);

        employeeName = new JLabel("Employee Name:");
        employeeName.setBounds(350, 140, 150, 30);
        employeeName.setFont(new Font("Arial", Font.BOLD, 15));
        add(employeeName);

        employeeNameField = new JTextField();
        employeeNameField.setBounds(500, 140, 150, 30);
        employeeNameField.setFont(new Font("Arial",Font.BOLD,15));
        employeeNameField.setEditable(false);
        enterButton(employeeNameField);
        add(employeeNameField);

        post = new JLabel("Post:");
        post.setBounds(50, 140, 100, 30);
        post.setFont(new Font("Arial", Font.BOLD, 15));
        add(post);

        postField = new JTextField();
        postField.setBounds(170, 140, 150, 30);
        postField.setFont(new Font("Arial",Font.BOLD,15));
        postField.setEditable(false);
        enterButton(postField);
        add(postField);

        Zone = new JLabel("Zone:");
        Zone.setBounds(350, 200, 100, 30);
        Zone.setFont(new Font("Arial", Font.BOLD, 15));
        add(Zone);

        String[] Zone = {"North", "South", "West", "East", "Central"};
        ZoneField = new JComboBox<>(Zone);
        ZoneField.setBounds(500, 200, 150, 30);
        ZoneField.setEnabled(false);
        enterButton(ZoneField);
        add(ZoneField);

        city = new JLabel("City:");
        city.setBounds(50, 200, 100, 30);
        city.setFont(new Font("Arial", Font.BOLD, 15));
        add(city);

        cityField = new JTextField();
        cityField.setBounds(170, 200, 150, 30);
        cityField.setFont(new Font("Arial",Font.BOLD,15));
        cityField.setEditable(false);
        enterButton(cityField);
        add(cityField);

        salary = new JLabel("Salary:");
        salary.setBounds(350, 260, 100, 30);
        salary.setFont(new Font("Arial", Font.BOLD, 15));
        add(salary);

        salaryField = new JTextField();
        salaryField.setBounds(500, 260, 150, 30);
        salaryField.setFont(new Font("Arial",Font.BOLD,15));
        salaryField.setEditable(false);
        enterButton(salaryField);
        add(salaryField);

        qualification = new JLabel("Qualification:");
        qualification.setBounds(50, 260, 150, 30);
        qualification.setFont(new Font("Arial", Font.BOLD, 15));
        add(qualification);

        String[] qualifications = {"Inter", "Bachelor's", "Master's", "PhD"};
        qualificationField = new JComboBox<>(qualifications);
        qualificationField.setBounds(170, 260, 150, 30);
        qualificationField.setEnabled(false);
        enterButton(qualificationField);
        add(qualificationField);

        DOB = new JLabel("DOB:");
        DOB.setBounds(350, 320, 100, 30);
        DOB.setFont(new Font("Arial", Font.BOLD, 15));
        add(DOB);

        DOBField = new JDateChooser();
        DOBField.setBounds(500, 320, 150, 30);
        DOBField.setFont(new Font("Arial",Font.BOLD,15));
        DOBField.setEnabled(false);
        enterButton(DOBField);
        add(DOBField);

        yearOfJoining = new JLabel("Year of Joining:");
        yearOfJoining.setBounds(50, 320, 150, 30);
        yearOfJoining.setFont(new Font("Arial", Font.BOLD, 15));
        add(yearOfJoining);

        yearOfJoiningField = new JDateChooser();
        yearOfJoiningField.setBounds(170, 320, 150, 30);
        yearOfJoiningField.setFont(new Font("Arial",Font.BOLD,15));
        yearOfJoiningField.setEnabled(false);
        enterButton(yearOfJoiningField);
        add(yearOfJoiningField);

        search= new JButton("Search");
        search.setBounds(500, 80, 80, 30);
        search.setBackground(Color.BLACK);
        search.setForeground(Color.WHITE);
        search.addActionListener(this);
        add(search);

        delete = new JButton("Delete");
        delete.setBounds(70, 400, 100, 30);
        delete.setBackground(Color.BLACK);
        delete.setForeground(Color.WHITE);
        delete.addActionListener(this);
        add(delete);

        reset = new JButton("Reset");
        reset.setBounds(280, 400, 100, 30);
        reset.setBackground(Color.BLACK);
        reset.setForeground(Color.WHITE);
        reset.addActionListener(this);
        add(reset);

        back = new JButton("Back");
        back.setBounds(500, 400, 100, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);
    }

    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==search){
            String emp_no= employeeNoField.getText();
            int Emp_no= Integer.parseInt(emp_no);

            if(emp_no.isEmpty()){
                JOptionPane.showMessageDialog(null,"Employee number cannot be empty");
                return;
            }

            if(emp_no.length()!=5){
                JOptionPane.showMessageDialog(null,"Employee number must be 5 digits");
                return;
            }

            try{
                CONN c= new CONN();
                String query="SELECT * FROM Employees WHERE Employee_Id= "+Emp_no+"";
                ResultSet rs= c.s.executeQuery(query);
                if(rs.next()){
                    employeeNameField.setText(rs.getString("Employee_Name"));
                    postField.setText(rs.getString("Profession"));
                    ZoneField.setSelectedItem(rs.getString("Division"));
                    cityField.setText(rs.getString("City"));
                    salaryField.setText(rs.getString("Salary"));
                    qualificationField.setSelectedItem(rs.getString("Qualification"));
                    String dobdate= rs.getString("Dob");
                    String yeardate=rs.getString("Joining_year");
                    SimpleDateFormat sdf= new SimpleDateFormat("dd MMM yyyy");
                    java.util.Date parseddob= sdf.parse(dobdate);
                    java.util.Date parsedyear=sdf.parse(yeardate);
                    DOBField.setDate(parseddob);
                    yearOfJoiningField.setDate(parsedyear);
                }    
                else{
                    JOptionPane.showMessageDialog(null, "Employee not found. Enter valid Employee ID");
                    return;
                }
                        
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else if (ae.getSource() == delete) {
            try {
                int empNo = Integer.parseInt(employeeNoField.getText());

                String emp_no= employeeNoField.getText();
                if(emp_no.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Employee number cannot be empty");
                    return;
                }

                if(emp_no.length()!=5){
                    JOptionPane.showMessageDialog(null,"Employee number must be 5 digits");
                    return;
                }

                int response=JOptionPane.showConfirmDialog(this,"Are you sure you want to remove this employee?","Confirmation",JOptionPane.YES_NO_OPTION);
                if(response==JOptionPane.YES_OPTION){
                    CONN c = new CONN();
                    String query="DELETE FROM Employees WHERE Employee_Id = "+empNo+"";
                    int deleted= c.s.executeUpdate(query);
                    if(deleted>0){
                        JOptionPane.showMessageDialog(this,"Employee Details Deleted successfully");
                    }
                    else{
                        JOptionPane.showMessageDialog(this,"Employee Details not Deleted Successfully");
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Database Error!: "+e.getMessage());
            }
        } else if (ae.getSource() == reset) {
            formReset();
        } else {
            setVisible(false);
        }
    }

    private void enterButton(Component comp){
        if(comp instanceof JTextField){
            JTextField tf= (JTextField) comp;
                tf.addKeyListener(new KeyAdapter() {
                    public void keyPressed(KeyEvent ke){
                        if(ke.getKeyCode()== KeyEvent.VK_ENTER){
                            if(tf==employeeNoField)
                                search.doClick();
                            // if(!(tf.getText().isEmpty()))
                            //     delete.doClick();
                        }
                    }
                });
        }
    }

    private void formReset(){
        employeeNameField.setText("");
        postField.setText("");
        cityField.setText("");
        salaryField.setText("");
        qualificationField.setSelectedIndex(0);
        ZoneField.setSelectedIndex(0);
        DOBField.setDate(null);
        yearOfJoiningField.setDate(null);
        employeeNoField.setText("");
    }
}
