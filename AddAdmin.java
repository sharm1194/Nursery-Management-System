import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.sql.*;

public class AddAdmin extends JFrame implements ActionListener,MouseListener
{
	
	JPanel panel;
	JButton  add, back, logout;
	JTextField tname,tid,tbalance;
	JPasswordField p1password;
	JLabel name,id,balance,password;
	Admin l;
	Connection connection=null;
	JLabel limg;
	ImageIcon img;
	
	public AddAdmin(Admin l)
	{
		
		super("Add Admin");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000, 750);
		
		panel = new JPanel();
		panel.setLayout(null);
		//panel.setBackground(Color.GRAY);
		this.add(panel);
		
		this.l=l;
		
	    id = new JLabel("ID");
		id.setBounds(200,140,100,50);
		id.setFont(new Font("Consolas",Font.BOLD,20));
		panel.add(id);
		
		tid = new JTextField();
		tid.setBounds(350,140,350,50);
		tid.setFont(new Font("Consolas",Font.BOLD,20));
	    panel.add(tid);
		
		password = new JLabel("Password");
		password.setBounds(200,200,100,50);
		password.setFont(new Font("Consolas",Font.BOLD,20));
		panel.add(password);
		
		p1password = new JPasswordField();
		p1password.setBounds(350,200,350,50);
		p1password.setFont(new Font("Consolas",Font.BOLD,20));
	    panel.add(p1password);
		
		name = new JLabel("Full Name");
		name.setBounds(200,260,200,50);
		name.setFont(new Font("Consolas",Font.BOLD,20));
		panel.add(name);
		
		tname = new JTextField();
		tname.setBounds(350,260,350,50); 
		tname.setFont(new Font("Consolas",Font.BOLD,20));
	    panel.add(tname);
		
		balance = new JLabel("Balance");
		balance.setBounds(200,320,100,50);
		balance.setFont(new Font("Consolas",Font.BOLD,20));
		panel.add(balance);
		
		tbalance = new JTextField();
		tbalance.setBounds(350,320,350,50);
		tbalance.setFont(new Font("Consolas",Font.BOLD,20));
	    panel.add(tbalance);
		
		add = new JButton("Add");
		add.setBounds(410, 410, 200,50);
		add.setFont(new Font("Consolas",Font.BOLD,20));
		add.setBackground(Color.DARK_GRAY);
		add.setForeground(Color.white);
		add.addActionListener(this);
		add.addMouseListener(this);
		panel.add(add);
		
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
		if(e.getSource()==add)
		{
			if(tid.getText().equals("") || p1password.getText().equals("") || tname.getText().equals("") || tbalance.getText().equals("") )
			{
				add.setEnabled(false);
			}

			else
			{
				add.setEnabled(true);
			}
			System.out.println("IN");
		}
	}
	
	public void actionPerformed(ActionEvent event)
	{
		String text = event.getActionCommand();
		if(text.equals(add.getText()))
		{
			try
			{
				
				connection=DatabaseConnection.DbConnector();
				String mysql="SELECT * FROM `login`";
				Statement st = connection.createStatement();
				System.out.println("statement created");
				ResultSet rs = st.executeQuery(mysql);
				System.out.println("results received");
				String userId;
				int x=0;
				while(rs.next())
				{
					userId = rs.getString("UserId");
					if(userId.equals(tid.getText()))
					{
						JOptionPane.showMessageDialog(null,"UserID already exist");
						tid.setText("");
						x=1;
					}
				}
				if(x==0)
				{
					String UserId, Name,Password;
					UserId=tid.getText();
					Name=tname.getText();
					Password=p1password.getText();
					double Balance = Double.parseDouble(tbalance.getText());
					
					System.out.println(UserId+"	"+ Name+"	"+Password+ "	"+Balance);
					
					String query = "INSERT INTO `admin` (`UserId`, `Balance`, `Name`) VALUES ('"+UserId+"','"+Balance+"', '"+Name+"')";
					st.executeUpdate(query);
					System.out.println("Info Added");
					String query1 = "INSERT INTO `login` (`UserId`, `Password`, `UserType`) VALUES ('"+UserId+"', '"+Password+"', 'admin')";
					st.executeUpdate(query1);
					System.out.println("Info Added2");
					
					JOptionPane.showMessageDialog(null,"Admin Added");
					this.setVisible(false);
					l.setVisible(true);
					
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
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null,"Please Enter a valid balance");
				tbalance.setText("");
				System.out.println("Exception : " +ex.getMessage());
			}
		}
		else if(text.equals(back.getText()))
		{
			this.setVisible(false);
			l.setVisible(true);
		}
		else if(text.equals(logout.getText()))
		{
			this.setVisible(false);
			Login m = new Login();
			m.setVisible(true);
		}
	}
	
}
