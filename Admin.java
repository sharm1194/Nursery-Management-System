import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.Random;

public class Admin extends JFrame implements ActionListener
{
	
	JPanel panel;
	JButton plant,employee,user,addAdmin,newArrival,transaction,back,logout;
	Login l;
	JLabel limg;
	ImageIcon img;
	
	public Admin()
	{
		
		super("Admin Home");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000, 700);
		
		
		panel = new JPanel();
		panel.setLayout(null);
		//panel.setBackground(Color.GRAY);
		this.add(panel);
		
		plant = new JButton("Plant Information");
		plant.setBounds(350, 70, 250,50);
		plant.setFont(new Font("Consolas",Font.BOLD,20));
		plant.setBackground(Color.DARK_GRAY);
		plant.setForeground(Color.white);
		plant.addActionListener(this);
		panel.add(plant);
		
		
		employee = new JButton("Dealer Information");
		employee.setBounds(350, 170, 250,50);
		employee.addActionListener(this);
		employee.setFont(new Font("Consolas",Font.BOLD,20));
		employee.setBackground(Color.DARK_GRAY);
		employee.setForeground(Color.white);
		panel.add(employee);
		
		user = new JButton("User Information");
		user.setBounds(350, 270, 250,50);
		user.setFont(new Font("Consolas",Font.BOLD,20));
		user.setBackground(Color.DARK_GRAY);
		user.setForeground(Color.white);
		user.addActionListener(this);
		panel.add(user);
		
		transaction = new JButton("Transaction");
		transaction.setBounds(350, 470, 250,50);
		transaction.setFont(new Font("Consolas",Font.BOLD,20));
		transaction.setBackground(Color.DARK_GRAY);
		transaction.setForeground(Color.white);
		transaction.addActionListener(this);
		panel.add(transaction);
		
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
			Login l = new Login();
			l.setVisible(true);
		}
		else if(text.equals(logout.getText()))
		{
			this.setVisible(false);
			Login l = new Login();
			l.setVisible(true);
		}
		else if(text.equals(plant.getText()))
		{
			
			this.setVisible(false);
			AdminPlantInfo u = new AdminPlantInfo(this);
			u.setVisible(true);
		}
		else if(text.equals(employee.getText()))
		{
			this.setVisible(false);
			AdminDealerInfo u = new AdminDealerInfo(this);
			u.setVisible(true);
		}
		else if(text.equals(user.getText()))
		{
			this.setVisible(false);
			AdminUserInfo u = new AdminUserInfo(this);
			u.setVisible(true);
		}
		
		else if(text.equals(transaction.getText()))
		{
			this.setVisible(false);
			Transaction u = new Transaction(this);
			u.setVisible(true);
		}
	}
	
}
