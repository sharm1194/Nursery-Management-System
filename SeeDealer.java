import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.sql.*;


public class Order extends JFrame implements ActionListener
{
	
	JPanel panel;
	JButton confirm, back, logout;
	Connection connection=null;
	Dealer d;
	String UserId,user;
	int DealerId,Dealer;
	JScrollPane tableScrollPane;
	double Price;
	ResultSet rs;
	JLabel limg;
	ImageIcon img;
	double Balance,newBalance;
	JTable table;
	ListSelectionModel cellSelectionModel;
	
	public Order(Dealer d)
	{
		
		super("Order");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000, 700);
		
		panel = new JPanel();
		panel.setLayout(null);
		//panel.setBackground(Color.GRAY);
		this.add(panel);
		
		this.d=d;
		this.UserId = d.UserId;
		this.DealerId = d.DealerId;
		
		System.out.println(DealerId);
		
		
		
		confirm = new JButton("Confirm");
		confirm.setBounds(350, 500, 200,40);
		confirm.setFont(new Font("Consolas",Font.BOLD,20));
		confirm.setBackground(Color.DARK_GRAY);
		confirm.setForeground(Color.white);
		confirm.addActionListener(this);
		panel.add(confirm);
		
		String[] columns = {"TreeID","DealerID", "UserID"};
			
		Object[][] data = getTableDetails();
		
		table = new JTable(data, columns);
		
		tableScrollPane = new JScrollPane(table);
		tableScrollPane.setBounds(50,50,300,100);
		panel.add(tableScrollPane);
		
		cellSelectionModel = table.getSelectionModel();
		cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
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
			
			String selectedData = null;

			int[] selectedRow = table.getSelectedRows();
			int[] selectedColumns = table.getSelectedColumns();

			for (int i = 0; i < selectedRow.length; i++) 
			{
			  for (int j = 0; j < selectedColumns.length; j++) 
			  {
				selectedData = (String) table.getValueAt(selectedRow[i], selectedColumns[j]);
				System.out.println(selectedData);
			  }
			}
			
			System.out.println(selectedData);
			
			user = selectedData;
			
			//Dealer = Integer.parseInt(selectedData);
			
			try
			{
				if(!user.equals(""))
				{
					try
					{
						
						connection=DatabaseConnection.DbConnector();
						String mysql="SELECT * FROM `order` WHERE `order`.`UserID` = '"+user+"'";
						Statement st = connection.createStatement();
						System.out.println("statement created");
						ResultSet rs = st.executeQuery(mysql);
						System.out.println("results received");
						
						while(rs.next())
						{
							
							String TreeID = rs.getString("TreeID");
							//user = rs.getString("UserId");
							String query="INSERT INTO plant (`UserID`, `TreeID`) VALUES ('"+user+"', '"+TreeID+"')";

							st.executeUpdate(query);
							System.out.println("results received");
							
							String mysql2="SELECT `Price` FROM `tree` WHERE `tree`.`TreeId` = '"+TreeID+"'";
							ResultSet r = st.executeQuery(mysql2);
							while(r.next())
							{
								System.out.println(DealerId);
								Price = r.getDouble("Price");
								String query1="INSERT INTO `Transaction` (`UserID`,`DealerId`, `TreeID`,`Price`) VALUES ('"+user+"', '"+DealerId+"', '"+TreeID+"', '"+Price+"')";
								st.executeUpdate(query1);
							}
							
							//UserId=rs.getString("UserId");
							//DealerID=rs.getString("DealerID");
							//System.out.println(DealerID);
							//JOptionPane.showMessageDialog(null,"Balance is: "+Balance);
						}
						
					}
					catch(Exception ex)
					{
						System.out.println("Exception : " +ex.getMessage());
					}
					try
					{
						
						connection=DatabaseConnection.DbConnector();
						String mysql1="DELETE FROM `order` WHERE `order`.`UserID` = '"+user+"'";
						Statement st = connection.createStatement();
						System.out.println("statement created");
						st.executeUpdate(mysql1);
						System.out.println("results received");
						
						
						
					}
					catch(Exception ex)
					{
						System.out.println("Exception : " +ex.getMessage());
					}
					
					try
					{
						
						connection=DatabaseConnection.DbConnector();
						String mysql="SELECT * FROM `dealer` WHERE `dealer`.`DealerID` = '"+DealerId+"'";
						Statement st = connection.createStatement();
						System.out.println("statement created");
						ResultSet rs = st.executeQuery(mysql);
						System.out.println("results received");
						
						while(rs.next())
						{
							
							//UserId=rs.getString("UserId");
							Balance=rs.getDouble("Balance");
							System.out.println(DealerId);
							newBalance = Balance+Price;
							System.out.println("results received");
							String query2 = "UPDATE `dealer` SET `Balance` = '"+newBalance+"' WHERE `dealer`.`DealerID` = '"+DealerId+"'";
							st.executeUpdate(query2);
							System.out.println("results received2");
							JOptionPane.showMessageDialog(null,"Order Confirmed");
						}
						
					}
					catch(Exception ex)
					{
						System.out.println("Exception : " +ex.getMessage());
					}
					
					this.setVisible(false);
					d.setVisible(true);
				}
			}
			catch(Exception ex)
			{
				System.out.println("Exception : " +ex.getMessage());
				JOptionPane.showMessageDialog(null,"No Order selected");
			}
		}
	}
	
	public int getRowCount(ResultSet r)
	{

		try {
			
			if(r != null) {
				
				r.last();
				
				return r.getRow(); 
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
			String mysql="SELECT `TreeID` , `DealerID` , `UserID` FROM `order` WHERE `order`.`DealerID` = '"+DealerId+"'";
			System.out.println("statement created");
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

				data[i][j++] = rs.getString("TreeID");
				data[i][j++] = rs.getString("DealerID");
				data[i][j++] = rs.getString("UserID");

				i++;
			}
			
			
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
			
			
			
	public int getColumnCount(ResultSet r) 
	{

		try {

			if(r != null)
				return r.getMetaData().getColumnCount();

		} 
		catch (SQLException e) {

			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return 0;
	}
	
}
