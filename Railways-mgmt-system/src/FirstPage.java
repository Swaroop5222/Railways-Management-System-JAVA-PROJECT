import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FirstPage extends JFrame implements ActionListener{

    JLabel heading,background;
    JButton next;
    ImageIcon i1;

    FirstPage(){
        setSize(1550,800);
        setLocation(0,0);
        setLayout(null);
        setVisible(true);

        enterButton(this);

        i1= new ImageIcon("src\\icons\\firstpage2.jpg");
        Image img= i1.getImage().getScaledInstance(1550, 800,Image.SCALE_SMOOTH);
        background= new JLabel(new ImageIcon(img));
        background.setBounds(0,0,1550,800);
        add(background);

        heading=new JLabel("RAILWAY MANAGEMENT SYSTEM");
        heading.setBounds(750,20,700,50);
        heading.setFont(new Font("Times New Roman",Font.BOLD,40));
        heading.setOpaque(true);
        heading.setBackground(new Color(135,206,235,180));
        heading.setForeground(Color.blue);
        background.add(heading);

        next= new JButton("Click Here To Continue");
        next.setBounds(700,700,200,50);
        next.addActionListener(this);
        next.setBackground(new Color(245,245,200));
        enterButton(next);
        background.add(next);

        new Thread(() ->{
            try{
                while(true){
                    heading.setVisible(false);
                    Thread.sleep(500);
                    heading.setVisible(true);
                    Thread.sleep(500);
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    ).start();

    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==next){
            new Login();
        }
    }

    private void enterButton(Component comp) {
        if (comp instanceof JButton) {
            JButton button = (JButton) comp;
            button.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent ke) { 
                    if (ke.getKeyCode() == KeyEvent.VK_ENTER) {  
                        next.doClick();
                    }
                }
            });
        }
        else{
            this.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent ke) { 
                    if (ke.getKeyCode() == KeyEvent.VK_ENTER) {  
                        next.doClick();
                    }
                }
            });
        }
    }
}
