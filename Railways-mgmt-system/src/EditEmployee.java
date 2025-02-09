import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.sql.*;

public class EditEmployee extends JFrame implements ActionListener {

    JLabel heading, employeeNo, employeeName, post, Zone, city, salary, qualification, DOB, yearOfJoining;
    JTextField employeeNameField, postField, cityField, salaryField;
    JComboBox<String> qualificationField, ZoneField;
    JDateChooser DOBField, yearOfJoiningField;
    JButton update, reset, back,search;
    JTextField employeeNoField;

    EditEmployee() {
        setSize(700, 500);
        setLayout(null);
        setVisible(true);
        setLocation(350, 100);
            
        ImageIcon i = new ImageIcon("src\\icons\\background.png");
        JLabel background = new JLabel(i);
        background.setBounds(0, 0, 700, 500);
        setContentPane(background); 
        heading = new JLabel("EDIT EMPLOYEE");
        heading.setBounds(230, 10, 200, 30);
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

        update = new JButton("Update");
        update.setBounds(70, 400, 100, 30);
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.addActionListener(this);
        enterButton(update);
        add(update);

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

                    employeeNameField.setEditable(true);
                    postField.setEditable(true);
                    ZoneField.setEnabled(true);
                    cityField.setEditable(true);
                    salaryField.setEditable(true);
                    qualificationField.setEnabled(true);
                    DOBField.setEnabled(true);
                    DOBField.getDateEditor().setEnabled(false);
                    yearOfJoiningField.setEnabled(true);
                    yearOfJoiningField.getDateEditor().setEnabled(false);
                }    
                else{
                    JOptionPane.showMessageDialog(null, "Employee not found. Enter valid Employee ID");
                }
                        
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else if (ae.getSource() == update) {
            try {
                int empNo = Integer.parseInt(employeeNoField.getText());
                String empName = employeeNameField.getText();
                String empPost = postField.getText();
                String empZone = (String) ZoneField.getSelectedItem();
                String empCity = cityField.getText();
                String empSalary = salaryField.getText();
                String empQualification = (String) qualificationField.getSelectedItem();

                String emp_no= employeeNoField.getText();
                if(emp_no.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Employee number cannot be empty");
                    return;
                }

                if(emp_no.length()!=5){
                    JOptionPane.showMessageDialog(null,"Employee number must be 5 digits");
                    return;
                }

                java.util.Date empDOB = DOBField.getDate();
                SimpleDateFormat sdf= new SimpleDateFormat("dd MMM yyyy");
                String empDOBStr = sdf.format(empDOB);
                java.util.Date empYear = yearOfJoiningField.getDate();
                String empYearStr=sdf.format(empYear);

                CONN c = new CONN();
                String query="UPDATE Employees SET Employee_name='"+empName+"', Profession='"+empPost+"', Division = '"+empZone+"', City='"+empCity+"', Salary='"+empSalary+"', Qualification ='"+empQualification+"', Dob='"+empDOBStr+"', Joining_year='"+empYearStr+"' WHERE Employee_Id= "+empNo+" ";
                int updated=c.s.executeUpdate(query);
                if(updated>0){
                    JOptionPane.showMessageDialog(this,"Employee Details Updated successfully");
                }
                else{
                    JOptionPane.showMessageDialog(this,"Employee Details not Updated Successfully");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Database Error!: "+e.getMessage());
                return;
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
            if(tf==employeeNoField){
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
        if(comp instanceof JComboBox){
            JComboBox<?> cb= (JComboBox<?>) comp;
            cb.addKeyListener(new KeyAdapter() {
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
    }
}
