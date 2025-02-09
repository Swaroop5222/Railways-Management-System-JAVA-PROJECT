import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DigitalPayment extends JFrame implements ActionListener{
    
    JButton back,print;
    JLabel heading;
    ImageIcon qr1,qr2,i1;

    int commonPNR;

DigitalPayment(){

    setSize(600,500);
    setLayout(null);
    setLocation(350,100);
    setVisible(true);

    enterButton(this);

    i1= new ImageIcon("src\\icons\\digital.png");
    Image img2= i1.getImage().getScaledInstance(600,500,Image.SCALE_DEFAULT);
    JLabel image2= new JLabel(new ImageIcon(img2));
    image2.setBounds(0,0,600,500);
    add(image2);

    heading= new JLabel("Digital Payment QR's");
    heading.setBounds(150,10,280,40);
    heading.setFont(new Font("Bodoni MT",Font.BOLD,30));
    image2.add(heading);

    qr1= new ImageIcon("src\\icons\\swaroop_linkedin.png");
    Image img= qr1.getImage().getScaledInstance(200,200,Image.SCALE_AREA_AVERAGING);
    JLabel image= new JLabel(new ImageIcon(img));
    image.setBounds(30,100,200,200);
    image2.add(image);

    qr2= new ImageIcon("src\\icons\\sushant_linkedin.png");
    Image img1=qr2.getImage().getScaledInstance(200,200,Image.SCALE_AREA_AVERAGING);
    JLabel image1= new JLabel(new ImageIcon(img1));
    image1.setBounds(350,100,200,200);
    image2.add(image1);

    ImageIcon i5= new ImageIcon("src\\icons\\back2.png");
    Image image4= i5.getImage().getScaledInstance(70,55,Image.SCALE_DEFAULT);

    back= new JButton(new ImageIcon(image4));
    back.setBounds(250,350,70,55);
    back.addActionListener(this);
    back.setBackground(Color.decode("#4B0082"));
    enterButton(back);
    image2.add(back);
}

public void actionPerformed(ActionEvent ae){
    setVisible(false);
}

private void enterButton(Component comp) {
    if (comp instanceof JButton) {
        JButton button = (JButton) comp;
        button.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) { 
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {  
                    button.doClick();
                }
            }
        });
    }
    else{
        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) { 
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {  
                    back.doClick();
                }
            }
        });
    }
}
}
