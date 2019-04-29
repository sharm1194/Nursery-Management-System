import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.sql.*;

public class UserSee extends JFrame implements ActionListener
{
	
	JPanel panel;
	JButton  back, logout,confirm,change;
	JTextField tsearch;
	JLabel label;
	Connection connection=null;
	String Name, Address, City, Email, Mobile, Gender, Nationality;
    JLabel UserIdl, Namel, Addressl, Cityl, Emaill, Mobilel, Genderl, Nationalityl;
	JTextField lUserIdl, lNamel, lAddressl, lCityl, lEmaill, lMobilel, lGenderl, lNationalityl;
	String UserId;
	User d;
	JLabel limg;
	ImageIcon img;
	
	public UserSee(User d)
	{
		
		super("User Info");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000, 750);
		
		panel = new JPanel();
		panel.setLayout(null);
		//panel.setBackground(Color.GRAY);
		this.add(panel);
	
		
		UserIdl = new JLabel("User Id");
		UserIdl.setBounds(200,100,200,40);
		UserIdl.setFont(new Font("Consolas",Font.BOLD,20));
		panel.add(UserIdl);
		
		Namel = new JLabel("Name");
		Namel.setBounds(200,150,200,40);
		Namel.setFont(new Font("Consolas",Font.BOLD,20));
		panel.add(Namel);
		
		
		Addressl = new JLabel("Address");
		Addressl.setBounds(200,200,200,40);
		Addressl.setFont(new Font("Consolas",Font.BOLD,20));
		panel.add(Addressl);
		
		Cityl = new JLabel("City");
		Cityl.setBounds(200,250,200,40);
		Cityl.setFont(new Font("Consolas",Font.BOLD,20));
		panel.add(Cityl);
		
		Emaill = new JLabel("Email");
		Emaill.setBounds(200,300,200,40);
		Emaill.setFont(new Font("Consolas",Font.BOLD,20));
		panel.add(Emaill);
		
		Mobilel = new JLabel("Mobile No");
		Mobilel.setBounds(200,350,200,40);
		Mobilel.setFont(new Font("Consolas",Font.BOLD,20));
		panel.add(Mobilel);
		
		Genderl = new JLabel("Gender");
		Genderl.setBounds(200,400,200,40);
		Genderl.setFont(new Font("Consolas",Font.BOLD,20));
		panel.add(Genderl);
		
		
		Nationalityl = new JLabel("Nationality");
		Nationalityl.setBounds(200,450,200,40);
		Nationalityl.setFont(new Font("Consolas",Font.BOLD,20));
		panel.add(Nationalityl);
		
		
		
		
		lUserIdl = new JTextField("");
		lUserIdl.setBounds(420,100,200,40);
		lUserIdl.setFont(new Font("Consolas",Font.BOLD,20));
		lUserIdl.setEditable(false);
		panel.add(lUserIdl);
		
		
		lNamel = new JTextField("");
		lNamel.setBounds(420,150,200,40);
		lNamel.setFont(new Font("Consolas",Font.BOLD,20));
		lNamel.setEditable(false);
		panel.add(lNamel);
		
		
		lAddressl = new JTextField("");
		lAddressl.setBounds(420,200,200,40);
		lAddressl.setFont(new Font("Consolas",Font.BOLD,20));
		lAddressl.setEditable(false);
		panel.add(lAddressl);
		
		lCityl = new JTextField("");
		lCityl.setBounds(420,250,200,40);
		lCityl.setFont(new Font("Consolas",Font.BOLD,20));
		lCityl.setEditable(false);
		panel.add(lCityl);
		
		lEmaill = new JTextField("");
		lEmaill.setBounds(420,300,200,40);
		lEmaill.setFont(new Font("Consolas",Font.BOLD,20));
		lEmaill.setEditable(false);
		panel.add(lEmaill);
		
		lMobilel = new JTextField("");
		lMobilel.setBounds(420,350,200,40);
		lMobilel.setFont(new Font("Consolas",Font.BOLD,20));
		lMobilel.setEditable(false);
		panel.add(lMobilel);
		
		lGenderl = new JTextField("");
		lGenderl.setBounds(420,400,200,40);
		lGenderl.setFont(new Font("Consolas",Font.BOLD,20));
		lGenderl.setEditable(false);
		panel.add(lGenderl);
		
		
		lNationalityl = new JTextField("");
		lNationalityl.setBounds(420,450,200,40);
		lNationalityl.setFont(new Font("Consolas",Font.BOLD,20));
		lNationalityl.setEditable(false);
		panel.add(lNationalityl);
		
		
		this.d=d;
		
		
		//UserId = tsearch.getText();
		
		try
		{
			UserId = d.UserId;
			connection=DatabaseConnection.DbConnector();
			String mysql="SELECT * FROM `user` WHERE `user`.`UserId` = '"+UserId+"'";
			Statement st = connection.createStatement();
			System.out.println("statement created");
			ResultSet rs = st.executeQuery(mysql);
			System.out.println("results received");
			
			while(rs.next())
			{
				
				
				//UserId=rs.getString("UserId");
				Name=rs.getString("Name");
				Address=rs.getString("Address");
				City=rs.getString("City");
				Email=rs.getString("Email");
				Mobile=rs.getString("Mobile");
				
				Nationality=rs.getString("Nationality");
				Gender=rs.getString("Gender");
				System.out.println("results received");
			}
			
			
			System.out.println(UserId+"	"+ Name+"	" +Address+"	"+ City+"	"+Email+"	" +Mobile+"	"+ Nationality+"	"+Gender);
			
			lUserIdl.setText(UserId);
			lNamel.setText(Name);
			lAddressl.setText(Address);
			lCityl.setText(City);
			lEmaill.setText(Email);
			lMobilel.setText(Mobile);
			lGenderl.setText(Gender);
			lNationalityl.setText(Nationality);
			
			try
			{
				if(rs!=null)
						rs.close();

				if(st!=null)
					st.close();

				if(connection!=null)
					connection.close();
			}
			catch(Exception ex)
			{
				System.out.println("Exception : " +ex.getMessage());
			}
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
		}
		
		
		change = new JButton("Change");
		change.setBounds(720, 200, 200,40);
		change.setFont(new Font("Consolas",Font.BOLD,20));
		change.setBackground(Color.DARK_GRAY);
		change.setForeground(Color.white);
		change.addActionListener(this);
		panel.add(change);
		
		confirm = new JButton("Confirm");
		confirm.setBounds(720,300,200,40);
		confirm.setFont(new Font("Consolas",Font.BOLD,20));
		confirm.setBackground(Color.DARK_GRAY);
		confirm.setForeground(Color.white);
		confirm.addActionListener(this);
		confirm.setVisible(false);
		panel.add(confirm);
		
		back = new JButton("Back");
		back.setBounds(50, 600, 200,40);
		back.setFont(new Font("Consolas",Font.BOLD,20));
		back.setBackground(Color.DARK_GRAY);
		back.setForeground(Color.white);
		back.addActionListener(this);
		panel.add(back);
		
		logout = new JButton("Log Out");
		logout.setBounds(700, 600, 200,40);
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
			d.setVisible(true);
		}
		else if(text.equals(logout.getText()))
		{
			this.setVisible(false);
			Login m = new Login();
			m.setVisible(true);
		}
		else if(text.equals(change.getText()))
		{
			change.setVisible(false);
			confirm.setVisible(true);
			//lUserIdl.setEditable(UserId);
			lNamel.setEditable(true);
			lAddressl.setEditable(true);
			lCityl.setEditable(true);
			lEmaill.setEditable(true);
			lMobilel.setEditable(true);
			lNationalityl.setEditable(true);
			
		}
		else if(text.equals(confirm.getText()))
		{
			
			
			try
			{
				Name=lNamel.getText();
				Address=lAddressl.getText();
				City=lCityl.getText();
				Email=lEmaill.getText();
				Mobile=lMobilel.getText();
				
				Nationality=lNationalityl.getText();
				UserId = d.UserId;
				
				System.out.println(UserId);
				
				connection=DatabaseConnection.DbConnector();
				String mysql="UPDATE `user` SET `Name` = '"+Name+"', `Address` = '"+Address+"', `City` = '"+City+"', `Email` = '"+Email+"', `Mobile` = '"+Mobile+"', `Nationality` = '"+Nationality+"' WHERE `user`.`UserId` = '"+UserId+"'";

				Statement st = connection.createStatement();
				System.out.println("statement created");
				st.executeUpdate(mysql);
				System.out.println("results received");
				
				change.setVisible(true);
				confirm.setVisible(false);
				
				System.out.println(UserId+"	"+ Name+"	" +Address+"	"+ City+"	"+Email+"	" +Mobile+"	"+ Nationality+"	"+Gender);
				
				lNamel.setEditable(false);
				lAddressl.setEditable(false);
				lCityl.setEditable(false);
				lEmaill.setEditable(false);
				lMobilel.setEditable(false);
				lNationalityl.setEditable(false);
				
				try
				{
					if(st!=null)
						st.close();

					if(connection!=null)
						connection.close();
				}
				catch(Exception ex)
				{
					System.out.println("Exception : " +ex.getMessage());
				}
				
			}
			catch(Exception ex)
			{
				System.out.println("Exception : " +ex.getMessage());
			}
				
			}
		
	}
}