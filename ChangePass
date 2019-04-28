import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.sql.*;

public class ChangePass extends JFrame implements ActionListener,MouseListener
{
	
	JPanel panel;
	JButton confirm, back, logout;
	JLabel old,newl;
	JPasswordField oldPass, newPass;
	String UserId;
	User d;
	Connection connection=null;
	JLabel limg;
	ImageIcon img;
	
	
	public ChangePass(User d)
	{
		
		super("Change Password");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000, 700);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		this.d=d;
		this.UserId = d.UserId;
		
		old = new JLabel("Old Password");
		old.setBounds(200,200,150,50);
		old.setFont(new Font("Consolas",Font.BOLD,20));
	    panel.add(old);
		
		oldPass = new JPasswordField();
		oldPass.setBounds(350,200,350,50);
		oldPass.setFont(new Font("Consolas",Font.BOLD,20));
	    panel.add(oldPass);
		
		newl = new JLabel("New Password");
		newl.setBounds(200,310,150,50);
		newl.setFont(new Font("Consolas",Font.BOLD,20));
	    panel.add(newl);
		
		newPass = new JPasswordField();
		newPass.setBounds(350,310,350,50);
		newPass.setFont(new Font("Consolas",Font.BOLD,20));
	    panel.add(newPass);
		
		
		confirm = new JButton("Confirm");
		confirm.setBounds(350, 420, 150,40);
		confirm.setBackground(Color.DARK_GRAY);
		confirm.setForeground(Color.white);
		confirm.addActionListener(this);
		confirm.addMouseListener(this);
		panel.add(confirm);
		
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
		
		this.add(panel);
	}
	
	public void mousePressed(MouseEvent e)
	{
		
	}
	public void mouseReleased(MouseEvent e)
	{
		
	}
	public void mouseExited(MouseEvent e)
	{
		
	}
	public void mouseClicked(MouseEvent e)
	{
		
	}
	public void mouseEntered(MouseEvent e)
	{
		System.out.println("Out");
		if(e.getSource()==confirm)
		{
			if(oldPass.getText().equals("") || newPass.getText().equals("") )
			{
				confirm.setEnabled(false);
			}

			else
			{
				confirm.setEnabled(true);
			}
			System.out.println("IN");
		}
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
		else if(text.equals(confirm.getText()))
		{
			try
			{
				connection=DatabaseConnection.DbConnector();
				String mysql="SELECT `Password` FROM `login` WHERE `login`.`UserId` = '"+UserId+"'";
				Statement st = connection.createStatement();
				System.out.println("statement created");
				ResultSet rs = st.executeQuery(mysql);
				System.out.println("results received");
				int x=0;
				while(rs.next())
				{
					System.out.println("inside");
					String Password = rs.getString("Password");
					if(Password.equals(oldPass.getText()))
					{
						System.out.println("Match");
						x=1;
						String newPassword = newPass.getText();
						String query = "UPDATE `login` SET `Password` = '"+newPassword+"' WHERE `login`.`UserId` = '"+UserId+"'";
						st.executeUpdate(query);
						JOptionPane.showMessageDialog(null,"Password Changed");
						this.setVisible(false);
						d.setVisible(true);
					}
						
					
				}
				if(x==0)
				{
					JOptionPane.showMessageDialog(null,"UserID and Password does not match");
					oldPass.setText("");
					newPass.setText("");
					
				}
			}
			catch(Exception ex)
			{
				System.out.println("Exception : " +ex.getMessage());
			}
		}
	}
	
}
