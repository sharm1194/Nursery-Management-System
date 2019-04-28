import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.sql.*;

public class Dealer extends JFrame implements ActionListener
{
	
	JPanel panel;
	JButton plants,myInfo,orders,balace,back,logout;
	Login l;
	Connection connection=null;
	String Balance,UserId;
	int DealerId;
	JLabel limg;
	ImageIcon img;
	
	
	public Dealer(String UserId)
	{
		
		super("Dealer Home");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000, 700);
		
		
		panel = new JPanel();
		panel.setLayout(null);
		//panel.setBackground(Color.GRAY);
		this.add(panel);
		
		this.UserId = UserId;
		
		plants = new JButton("Plants Information");
		plants.setBounds(350, 70, 250,50);
		plants.setFont(new Font("Consolas",Font.BOLD,20));
		plants.setBackground(Color.DARK_GRAY);
		plants.setForeground(Color.white);
		plants.addActionListener(this);
		panel.add(plants);
		
		myInfo = new JButton("My Information");
		myInfo.setBounds(350, 170, 250,50);
		myInfo.setFont(new Font("Consolas",Font.BOLD,20));
		myInfo.setBackground(Color.DARK_GRAY);
		myInfo.setForeground(Color.white);
		myInfo.addActionListener(this);
		panel.add(myInfo);
		
		orders = new JButton("See Orders");
		orders.setBounds(350, 270, 250,50);
		orders.setFont(new Font("Consolas",Font.BOLD,20));
		orders.setBackground(Color.DARK_GRAY);
		orders.setForeground(Color.white);
		orders.addActionListener(this);
		panel.add(orders);
		
		balace = new JButton("Balance");
		balace.setBounds(350, 370, 250,50);
		balace.setFont(new Font("Consolas",Font.BOLD,20));
		balace.setBackground(Color.DARK_GRAY);
		balace.setForeground(Color.white);
		balace.addActionListener(this);
		panel.add(balace);
		
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
		
		try
		{
			
			connection=DatabaseConnection.DbConnector();
			String mysql="SELECT * FROM `dealer` WHERE `dealer`.`UserId` = '"+UserId+"'";
			Statement st = connection.createStatement();
			System.out.println("statement created");
			ResultSet rs = st.executeQuery(mysql);
			System.out.println("results received");
			
			while(rs.next())
			{
				
				
				//UserId=rs.getString("UserId");
				Balance=rs.getString("Balance");
				DealerId=rs.getInt("DealerId");
				System.out.println(DealerId);
				System.out.println("results received");
			}
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
		}
		
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
		
		else if(text.equals(balace.getText()))
		{
			
			try
			{
				
				connection=DatabaseConnection.DbConnector();
				String mysql="SELECT * FROM `dealer` WHERE `dealer`.`UserId` = '"+UserId+"'";
				Statement st = connection.createStatement();
				System.out.println("statement created");
				ResultSet rs = st.executeQuery(mysql);
				System.out.println("results received");
				
				while(rs.next())
				{
					
					
					//UserId=rs.getString("UserId");
					Balance=rs.getString("Balance");
					DealerId=rs.getInt("DealerId");
					System.out.println(DealerId);
					System.out.println("results received");
					JOptionPane.showMessageDialog(null,"Balance is: "+Balance);
				}
				
			}
			catch(Exception ex)
			{
				System.out.println("Exception : " +ex.getMessage());
			}
			
		}
		else if(text.equals(myInfo.getText()))
		{
			
			this.setVisible(false);
			DealerSee m = new DealerSee(this);
			m.setVisible(true);
			
		}
		else if(text.equals(orders.getText()))
		{
			
			this.setVisible(false);
			Order m = new Order(this);
			m.setVisible(true);
			
		}
		else if(text.equals(plants.getText()))
		{
			
			this.setVisible(false);
			DealerPlantInfo m = new DealerPlantInfo(this);
			m.setVisible(true);
			
		}
	}
	
}
