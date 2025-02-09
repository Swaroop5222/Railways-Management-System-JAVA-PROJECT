import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Random;

public class AddEmployee extends JFrame implements ActionListener {

    JLabel heading, employeeNo, employeeName, post, Zone, city, salary, qualification, DOB, yearOfJoining;
    JTextField employeeNameField, postField, cityField, salaryField;
    JComboBox<String> qualificationField, ZoneField;
    JDateChooser DOBField, yearOfJoiningField;
    JButton save, reset, back;
    JLabel employeeNoField;

    AddEmployee() {
        setSize(700, 500);
        setLayout(null);
        setVisible(true);
        setLocation(350, 100);

        ImageIcon i = new ImageIcon("src\\icons\\background.png");
        JLabel background = new JLabel(i);
        background.setBounds(0, 0, 700, 500);
        setContentPane(background); 
        heading = new JLabel("ADD EMPLOYEE");
        heading.setBounds(230, 10, 200, 30);
        heading.setFont(new Font("Arial", Font.BOLD, 25));
        add(heading);

        employeeNo = new JLabel("Employee No:");
        employeeNo.setBounds(70, 80, 120, 30);
        employeeNo.setFont(new Font("Arial", Font.BOLD, 15));
        add(employeeNo);

        employeeNoField = new JLabel(String.valueOf(generateRandomNumber()));
        employeeNoField.setBounds(190, 80, 150, 25);
        employeeNoField.setFont(new Font("Arial", Font.PLAIN, 15));
        add(employeeNoField);

        employeeName = new JLabel("Employee Name:");
        employeeName.setBounds(350, 80, 150, 30);
        employeeName.setFont(new Font("Arial", Font.BOLD, 15));
        add(employeeName);

        employeeNameField = new JTextField();
        employeeNameField.setBounds(500, 80, 150, 25);
        employeeNameField.setFont(new Font("Arial",Font.BOLD,15));
        enterButton(employeeNameField);
        add(employeeNameField);

        post = new JLabel("Post:");
        post.setBounds(70, 140, 100, 30);
        post.setFont(new Font("Arial", Font.BOLD, 15));
        add(post);

        postField = new JTextField();
        postField.setBounds(190, 140, 150, 25);
        postField.setFont(new Font("Arial",Font.BOLD,15));
        enterButton(postField);
        add(postField);

        Zone = new JLabel("Zone:");
        Zone.setBounds(350, 140, 100, 30);
        Zone.setFont(new Font("Arial", Font.BOLD, 15));
        add(Zone);

        String[] Zone = {"North", "South", "West", "East", "Central"};
        ZoneField = new JComboBox<>(Zone);
        ZoneField.setBounds(500, 140, 150, 25);
        enterButton(ZoneField);
        add(ZoneField);

        city = new JLabel("City:");
        city.setBounds(70, 200, 100, 30);
        city.setFont(new Font("Arial", Font.BOLD, 15));
        add(city);

        cityField = new JTextField();
        cityField.setBounds(190, 200, 150, 25);
        cityField.setFont(new Font("Arial",Font.BOLD,15));
        enterButton(cityField);
        add(cityField);

        salary = new JLabel("Salary:");
        salary.setBounds(350, 200, 100, 30);
        salary.setFont(new Font("Arial", Font.BOLD, 15));
        add(salary);

        salaryField = new JTextField();
        salaryField.setBounds(500, 200, 150, 25);
        salaryField.setFont(new Font("Arial",Font.BOLD,15));
        enterButton(salaryField);
        add(salaryField);

        qualification = new JLabel("Qualification:");
        qualification.setBounds(70, 260, 150, 30);
        qualification.setFont(new Font("Arial", Font.BOLD, 15));
        add(qualification);

        String[] qualifications = {"Inter", "Bachelor's", "Master's", "PhD"};
        qualificationField = new JComboBox<>(qualifications);
        qualificationField.setBounds(190, 260, 150, 25);
        enterButton(qualificationField);
        add(qualificationField);

        DOB = new JLabel("DOB:");
        DOB.setBounds(350, 260, 100, 30);
        DOB.setFont(new Font("Arial", Font.BOLD, 15));
        add(DOB);

        DOBField = new JDateChooser();
        DOBField.setBounds(500, 260, 150, 25);
        DOBField.setFont(new Font("Arial",Font.BOLD,15));
        DOBField.getDateEditor().setEnabled(false);
        enterButton(DOBField);
        add(DOBField);

        yearOfJoining = new JLabel("Year of Joining:");
        yearOfJoining.setBounds(70, 320, 150, 30);
        yearOfJoining.setFont(new Font("Arial", Font.BOLD, 15));
        add(yearOfJoining);

        yearOfJoiningField = new JDateChooser();
        yearOfJoiningField.setBounds(190, 320, 150, 25);
        yearOfJoiningField.setFont(new Font("Arial",Font.BOLD,15));
        yearOfJoiningField.getDateEditor().setEnabled(false);
        enterButton(yearOfJoiningField);
        add(yearOfJoiningField);

        save = new JButton("Save");
        save.setBounds(70, 400, 100, 30);
        save.setBackground(Color.BLACK);
        save.setForeground(Color.WHITE);
        save.addActionListener(this);
        enterButton(save);
        add(save);

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
        if (ae.getSource() == save) {
            try {
                int empNo = Integer.parseInt(employeeNoField.getText());
                String empName = employeeNameField.getText();
                String empPost = postField.getText();
                String empZone = (String) ZoneField.getSelectedItem();
                String empCity = cityField.getText();
                String empSalary = salaryField.getText();
                String empQualification = (String) qualificationField.getSelectedItem();

                if (empName.isEmpty() || empPost.isEmpty() || empZone.isEmpty() || empCity.isEmpty() || empSalary.isEmpty() || empQualification.isEmpty() || DOBField.getDate() == null || yearOfJoiningField.getDate() == null) {
                    JOptionPane.showMessageDialog(null, "Please fill all the fields");
                    return;
                }

                SimpleDateFormat sdf= new SimpleDateFormat("dd MMM yyyy");
                String empDOBStr = sdf.format(DOBField.getDate());
                SimpleDateFormat sdf1= new SimpleDateFormat("dd MMM yyyy");
                String empYearStr=sdf1.format(yearOfJoiningField.getDate());

                boolean dup= searchDuplicateEmployeeNumber(empNo);
                if(dup==true){
                    JOptionPane.showMessageDialog(null, "Employee Number already exists");
                    return;
                }

                CONN c = new CONN();
                String query= "INSERT INTO Employees(Employee_Id,Employee_name,Profession,Division,City,Salary,Qualification,Dob,Joining_year) VALUES("+empNo+",'"+empName+"','"+empPost+"','"+empZone+"','"+empCity+"','"+empSalary+"','"+empQualification+"','"+empDOBStr+"','"+empYearStr+"')";
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(this,"Employee Details added successfully");
                int confirm = JOptionPane.showConfirmDialog(this,"Do you want to add another employee?","COnfirmation",JOptionPane.YES_NO_OPTION);
                if(confirm==JOptionPane.YES_OPTION){
                    setVisible(false);
                    new AddEmployee();
                }
                else{
                    setVisible(false);
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

    private int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(10000,99999);    
    }

    private boolean searchDuplicateEmployeeNumber(int empNo){
        try{
            CONN c= new CONN();
            String query="SELECT Employee_Id FROM Employees WHERE Employee_Id = "+empNo+"";
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
        if(comp instanceof JComboBox){
            JComboBox<?> cb= (JComboBox<?>) comp;
            cb.addKeyListener(new KeyAdapter() {
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
