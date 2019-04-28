import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.sql.*;

public class Login extends JFrame implements ActionListener,MouseListener
{
	JPanel panel;
	JLabel userLabel, passLabel, newU, visitorLabel,limg;
	JButton loginB, newB, visitorButton,btn;
	JTextField userText;
	JPasswordField pass;
	Connection connection=null;
	String userId;
	ImageIcon img;
	
	public Login()
	{
		
		super("Log In");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000, 750);
		
		panel = new JPanel();
		panel.setLayout(null);
		//panel.setBackground(Color.GRAY);
		this.add(panel);
		
		
		
		userLabel = new JLabel("User ID");
		userLabel.setBounds(500,100,100,50);
		userLabel.setFont(new Font("Consolas",Font.BOLD,25));
		userLabel.setBackground(Color.DARK_GRAY);
		userLabel.setForeground(Color.white);
		panel.add(userLabel);
		
		userText = new JTextField();
		userText.setBounds(660,100,200,50);
		userText.setFont(new Font("Consolas",Font.BOLD,20));
		userText.setBackground(Color.LIGHT_GRAY);
		userText.setForeground(Color.BLACK);
		panel.add(userText);
	
		
		passLabel = new JLabel("Password");
		passLabel.setBounds(500,200,150,50);
		passLabel.setFont(new Font("Consolas",Font.BOLD,25));
		passLabel.setBackground(Color.DARK_GRAY);
		passLabel.setForeground(Color.white);
		panel.add(passLabel);
		
		pass = new JPasswordField();
		pass.setBounds(660,200,200,40);
		pass.setFont(new Font("Consolas",Font.BOLD,20));
		pass.setBackground(Color.LIGHT_GRAY);
		pass.setForeground(Color.BLACK);
		panel.add(pass);
		
		btn = new JButton("Show");
		btn.setBounds(875,202,70,35);
		btn.setFont(new Font("Consolas",Font.BOLD,14));
		btn.addMouseListener(this);
		btn.setBackground(Color.DARK_GRAY);
		btn.setForeground(Color.WHITE);
		btn.addActionListener(this);
		panel.add(btn);
		
		loginB = new JButton("Log In");
		loginB.setBounds(700,270,120,50);
		loginB.setFont(new Font("Consolas",Font.BOLD,20));
		loginB.setBackground(Color.DARK_GRAY);
		loginB.setForeground(Color.white);
		loginB.addActionListener(this);
		loginB.addMouseListener(this);
		panel.add(loginB);
		
		newU = new JLabel("New Here?");
		newU.setBounds(500,450,150,50);
		newU.setFont(new Font("Consolas",Font.BOLD,20));
		newU.setBackground(Color.DARK_GRAY);
		newU.setForeground(Color.white);
		panel.add(newU);
		
		newB = new JButton("Become a Member");
		newB.setBounds(660,450,220,50);
		newB.setFont(new Font("Consolas",Font.BOLD,20));
		newB.setBackground(Color.DARK_GRAY);
		newB.setForeground(Color.white);
		newB.addActionListener(this);
		panel.add(newB);
		
		visitorLabel = new JLabel("Log In as a");
		visitorLabel.setBounds(500,550,150,50);
		visitorLabel.setFont(new Font("Consolas",Font.BOLD,20));
		visitorLabel.setBackground(Color.DARK_GRAY);
		visitorLabel.setForeground(Color.white);
		panel.add(visitorLabel);
		
		visitorButton = new JButton("Visitor");
		visitorButton.setBounds(660,550, 200,50);
		visitorButton.setFont(new Font("Consolas",Font.BOLD,20));
		visitorButton.setBackground(Color.DARK_GRAY);
		visitorButton.setForeground(Color.white);
		visitorButton.addActionListener(this);
		panel.add(visitorButton);
		
		
		
		img = new ImageIcon(".//Image//front.jpg");
		limg = new JLabel(img);
		limg.setBounds(0,0,1000,700);
		panel.add(limg);
		
	}
	
	public void mousePressed(MouseEvent e)
	{
		if(e.getSource().equals(btn))
		{
			pass.setEchoChar((char)0);
		}

	}
	public void mouseReleased(MouseEvent e)
	{
		if(e.getSource().equals(btn))
		{
			pass.setEchoChar('*');
		}

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
		if(e.getSource()==loginB)
		{
			if(userText.getText().equals("") || pass.getText().equals("") )
			{
				loginB.setEnabled(false);
			}

			else
			{
				loginB.setEnabled(true);
			}
			System.out.println("IN");
		}
	}
	
	public void actionPerformed(ActionEvent event)
	{
		String text = event.getActionCommand();
		if(text.equals(loginB.getText()))
		{
			try
			{
				connection=DatabaseConnection.DbConnector();
				String mysql="SELECT * FROM `login`";
				Statement st = connection.createStatement();
				System.out.println("statement created");
				ResultSet rs = st.executeQuery(mysql);
				System.out.println("results received");
				int x=0;
				while(rs.next())
				{
					userId = rs.getString("UserId");
					if(userId.equals(userText.getText()))
					{
						String Password = rs.getString("Password");
						if(Password.equals(pass.getText()))
						{
							System.out.println("Match");
							x=1;
							String s= rs.getString("UserType");
							{
								if(s.equals("admin"))
								{
									this.setVisible(false);
									Admin u = new Admin();
									u.setVisible(true);
								}
								else if(s.equals("user"))
								{
									this.setVisible(false);
									User u = new User(userId);
									u.setVisible(true);
								}
								else if(s.equals("dealer"))
								{
									this.setVisible(false);
									Dealer u = new Dealer(userId);
									u.setVisible(true);
								}
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null,"UserID and Password does not match");
							userText.setText("");
							pass.setText("");
							x=1;
						}
					}
				}
				if(x==0)
				{
					JOptionPane.showMessageDialog(null,"UserID and Password does not match");
					userText.setText("");
					pass.setText("");
				}
			}
			catch(Exception ex)
			{
				System.out.println("Exception : " +ex.getMessage());
			}
		}
		
		else if(text.equals(newB.getText()))
		{
			this.setVisible(false);
			Member m = new Member();
			m.setVisible(true);
		}
		else if(text.equals(visitorButton.getText()))
		{
			this.setVisible(false);
			Visitor m = new Visitor();
			m.setVisible(true);
		}
		
	}
	
}
