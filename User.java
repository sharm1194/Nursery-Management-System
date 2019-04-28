import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class User extends JFrame implements ActionListener
{
	
	JPanel panel;
	JButton plant, myCart, myPlant, myInfo,changePass, order, back, logout;
	Login l;
	String UserId;
	JLabel limg;
	ImageIcon img;
	
	public User(String UserId)
	{
		super("User Home");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000, 700);
		
		this.UserId=UserId;
		
		panel = new JPanel();
		panel.setLayout(null);
		//panel.setBackground(Color.GRAY);
		this.add(panel);
		
		plant = new JButton("Search Plant");
		plant.setBounds(350, 70, 250,50);
		plant.setFont(new Font("Consolas",Font.BOLD,20));
		plant.setBackground(Color.DARK_GRAY);
		plant.setForeground(Color.white);
		plant.addActionListener(this);
		panel.add(plant);
		
		
		myInfo = new JButton("My Information");
		myInfo.setBounds(350, 150, 250,50);
		myInfo.setFont(new Font("Consolas",Font.BOLD,20));
		myInfo.setBackground(Color.DARK_GRAY);
		myInfo.setForeground(Color.white);
		myInfo.addActionListener(this);
		panel.add(myInfo);
		
		changePass = new JButton("Change Password");
		changePass.setBounds(350, 230, 250,50);
		changePass.setFont(new Font("Consolas",Font.BOLD,20));
		changePass.setBackground(Color.DARK_GRAY);
		changePass.setForeground(Color.white);
		changePass.addActionListener(this);
		panel.add(changePass);
		
		order = new JButton("Orders");
		order.setBounds(350, 310, 250,50);
		order.setFont(new Font("Consolas",Font.BOLD,20));
		order.setBackground(Color.DARK_GRAY);
		order.setForeground(Color.white);
		order.addActionListener(this);
		panel.add(order);
		
		myPlant = new JButton("My Plant");
		myPlant.setBounds(350, 390, 250,50);
		myPlant.setFont(new Font("Consolas",Font.BOLD,20));
		myPlant.setBackground(Color.DARK_GRAY);
		myPlant.setForeground(Color.white);
		myPlant.addActionListener(this);
		panel.add(myPlant);
		
		myCart = new JButton("My Cart");
		myCart.setBounds(350, 470, 250,50);
		myCart.setFont(new Font("Consolas",Font.BOLD,20));
		myCart.setBackground(Color.DARK_GRAY);
		myCart.setForeground(Color.white);
		myCart.addActionListener(this);
		panel.add(myCart);
		

		back = new JButton("Back");
		back.setBounds(50, 550, 200,50);
		back.setFont(new Font("Consolas",Font.BOLD,20));
		back.setBackground(Color.DARK_GRAY);
		back.setForeground(Color.white);
		back.addActionListener(this);
		panel.add(back);
		
		logout = new JButton("Log Out");
		logout.setBounds(700, 550, 200,50);
		logout.setFont(new Font("Consolas",Font.BOLD,20));
		logout.setBackground(Color.DARK_GRAY);
		logout.setForeground(Color.white);
		logout.addActionListener(this);
		panel.add(logout);
		
		
		img = new ImageIcon(".//Image//back.jpg");
		limg = new JLabel(img);
		limg.setBounds(0,0,1000,700);
		panel.add(limg);
		
		
		
	}
	
	public void actionPerformed(ActionEvent event)
	{
		String text = event.getActionCommand();
		
		if(text.equals(back.getText()))
		{
			this.setVisible(false);
			Login m = new Login();
			m.setVisible(true);
		}
		else if(text.equals(logout.getText()))
		{
			this.setVisible(false);
			Login m = new Login();
			m.setVisible(true);
		}
		else if(text.equals(plant.getText()))
		{
			
			
			this.setVisible(false);
			UserPlant m = new UserPlant(this);
			m.setVisible(true);
			
		}
		else if(text.equals(myInfo.getText()))
		{
			
			
			this.setVisible(false);
			UserSee m = new UserSee(this);
			m.setVisible(true);
			
		}
		else if(text.equals(changePass.getText()))
		{
			
			
			this.setVisible(false);
			ChangePass m = new ChangePass(this);
			m.setVisible(true);
			
		}
		else if(text.equals(myCart.getText()))
		{
			
			
			this.setVisible(false);
			MyCart m = new MyCart(this);
			m.setVisible(true);
			
		}
		else if(text.equals(order.getText()))
		{
			
			
			this.setVisible(false);
			MyOrder m = new MyOrder(this);
			m.setVisible(true);
			
		}
		else if(text.equals(myPlant.getText()))
		{
			
			
			this.setVisible(false);
			MyPlant m = new MyPlant(this);
			m.setVisible(true);
			
		}
	}
	
}