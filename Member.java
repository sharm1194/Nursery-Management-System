import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Member extends JFrame implements ActionListener
{
	JPanel panel;
	JButton user, dealer,back;
	JLabel limg;
	ImageIcon img;
	
	public Member()
	{
		super("Become a Member");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000, 750);
		
		panel = new JPanel();
		panel.setLayout(null);
		//panel.setBackground(Color.GRAY);
		
		user = new JButton("Registration As A User");
		user.setBounds(300,200,400,80);
		user.setFont(new Font("Consolas",Font.BOLD,25));
		user.setBackground(Color.DARK_GRAY);
		user.setForeground(Color.white);
		user.addActionListener(this);
		panel.add(user);
		
		dealer = new JButton("Registration As A Dealer");
		dealer.setBounds(300,320,400,80);
		dealer.setFont(new Font("Consolas",Font.BOLD,25));
		dealer.setBackground(Color.DARK_GRAY);
		dealer.setForeground(Color.white);
		dealer.addActionListener(this);
		panel.add(dealer);
		
		back = new JButton("Back");
		back.setBounds(380,550,200,80);
		back.setFont(new Font("Consolas",Font.BOLD,25));
		back.setBackground(Color.DARK_GRAY);
		back.setForeground(Color.white);
		back.addActionListener(this);
		panel.add(back);
		
		img = new ImageIcon(".//Image//back.jpg");
		limg = new JLabel(img);
		limg.setBounds(0,0,1000,700);
		panel.add(limg);
		
		this.add(panel);
	}
	
	public void actionPerformed(ActionEvent event)
	{
		String text = event.getActionCommand();
		
		if(text.equals(user.getText()))
		{
			this.setVisible(false);
			UserRegistration u = new UserRegistration();
			u.setVisible(true);
		}
		else if(text.equals(dealer.getText()))
		{
			this.setVisible(false);
			DealerRegistration u = new DealerRegistration();
			u.setVisible(true);
		}
		else if(text.equals(back.getText()))
		{
			this.setVisible(false);
			Login u = new Login();
			u.setVisible(true);
		}
	}
	
}
