import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.sql.*;

import javax.swing.JTable;

public class DealerPlantInfo extends JFrame implements ActionListener,MouseListener
{
	
	JPanel panel;
	JButton search,add,remove,back,logout;
	JTextField tsearch,tremove;
	JLabel label;
	Dealer d;
	Connection connection=null;
	String UserId,DealerID,TreeId;
	boolean status;
	JLabel limg;
	ImageIcon img;
	
	JScrollPane tableScrollPane;
	ResultSet rs;

	
	
	public DealerPlantInfo(Dealer d)
	{
		
		super("Dealer PlantInfo Home");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000, 700);
		
		panel = new JPanel();
		panel.setLayout(null);
		//panel.setBackground(Color.GRAY);
		this.add(panel);
		
		this.d=d;
		this.UserId = d.UserId;
		try
		{
			
			connection=DatabaseConnection.DbConnector();
			String mysql="SELECT `DealerID` FROM `dealer` WHERE `dealer`.`UserId` = '"+UserId+"'";
			Statement st = connection.createStatement();
			System.out.println("statement created");
			ResultSet rs = st.executeQuery(mysql);
			System.out.println("results received");
			
			while(rs.next())
			{
				
				DealerID=rs.getString("DealerID");
				System.out.println("results received");
			}
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
		}
		
		
		remove = new JButton("Remove Plant");
		remove.setBounds(700,400,200,50);
		remove.setFont(new Font("Consolas",Font.BOLD,20));
		remove.setBackground(Color.DARK_GRAY);
		remove.setForeground(Color.white);
		remove.addActionListener(this);
		remove.addMouseListener(this);
		panel.add(remove);
		
		label = new JLabel("Tree ID");
		label.setFont(new Font("Consolas",Font.BOLD,20));
		label.setBounds(250,400,200,50);
		label.setBackground(Color.DARK_GRAY);
		panel.add(label);
		
		tremove = new JTextField();
		tremove.setBounds(380,400,300,50);
		tremove.setFont(new Font("Consolas",Font.BOLD,20));
		panel.add(tremove);
		
		search = new JButton("Search Plant");
		search.setBounds(700,100,200,50);
		search.setFont(new Font("Consolas",Font.BOLD,20));
		search.setBackground(Color.DARK_GRAY);
		search.setForeground(Color.white);
		search.addActionListener(this);
		panel.add(search);
		
		
		add = new JButton("Add Plant");
		add.setBounds(700,270,200,50);
		add.setFont(new Font("Consolas",Font.BOLD,20));
		add.setBackground(Color.DARK_GRAY);
		add.setForeground(Color.white);
		add.addActionListener(this);
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
		if(e.getSource()==remove)
		{
			if(tremove.getText().equals("") )
			{
				remove.setEnabled(false);
			}

			else
			{
				remove.setEnabled(true);
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
		else if(text.equals(search.getText()))
		{
			
			
			
			String[] columns = {"TreeId", "Name", "SciName","Price" };
			
			Object[][] data = getTableDetails();
			
			JTable table = new JTable(data, columns);
			
			tableScrollPane = new JScrollPane(table);
			tableScrollPane.setBounds(100,100,300,100);
			panel.add(tableScrollPane);
			
			
			
		}
		else if(text.equals(add.getText()))
		{
			
			
			this.setVisible(false);
			Addplant m = new Addplant(this);
			m.setVisible(true);
			
		}
		else if(text.equals(remove.getText()))
		{
			String Tree = tremove.getText();
			
			try
			{
				System.out.println(TreeId);
				connection=DatabaseConnection.DbConnector();
				String mysql="SELECT `DealerId` FROM `tree` WHERE `tree`.`TreeId` = '"+Tree+"'";
				Statement st = connection.createStatement();
				System.out.println("statement created");
				ResultSet r = st.executeQuery(mysql);
				System.out.println("results received");
				int x=0;
				while(r.next())
				{
					String dealer = r.getString("DealerId");
					if(dealer.equals(DealerID))
					{
						System.out.println(Tree);
						System.out.println(dealer);
						System.out.println(DealerID);
						String query="DELETE FROM `tree` WHERE `tree`.`TreeId` = '"+Tree+"'";
						System.out.println("statement created");
						st.executeUpdate(query);
						System.out.println("results received");
						JOptionPane.showMessageDialog(null,"Tree deleted");
						x=1;
						tremove.setText("");
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Tree not found");
						tremove.setText("");
					}
				}
				if(x==0)
				{
					JOptionPane.showMessageDialog(null,"Tree not found");
					tremove.setText("");
				}
			}
			catch(Exception ex)
			{
				System.out.println("Exception : " +ex.getMessage());
			}
		}
	}
	public int getRowCount(ResultSet rs)
	{

		try {
			
			if(rs != null) {
				
				rs.last();
				
				return rs.getRow(); 
			}
			
		} 
		catch (SQLException e) {

			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return 0;
	}
	public Object[][] getTableDetails()
	{
		System.out.println("Out");
		Object[][] data = null;
		try
		{
			
			
		
			connection=DatabaseConnection.DbConnector();
			String mysql="SELECT `TreeId` , `Name` , `SciName` , `Price` FROM `tree` WHERE `tree`.`DealerId` = '"+DealerID+"'";

			Statement st = connection.createStatement();
			System.out.println("statement created");
			rs = st.executeQuery(mysql);
			System.out.println("results received");
			
			int rowCount = getRowCount(rs);
			int columnCount = getColumnCount(rs);
			data = new Object[rowCount][columnCount];
			
			rs.beforeFirst();

			int i = 0;
			
			while (rs.next()) 
			{

				int j = 0;

				data[i][j++] = rs.getString("TreeId");
				data[i][j++] = rs.getString("Name");
				data[i][j++] = rs.getString("SciName");
				data[i][j++] = rs.getString("Price");

				i++;
			}
			
			status = true;
			
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
		return data;
			
	}
			
			
			
	public int getColumnCount(ResultSet rs) 
	{

		try {

			if(rs != null)
				return rs.getMetaData().getColumnCount();

		} 
		catch (SQLException e) {

			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return 0;
	}
	
}
