import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.sql.*;


public class MyCart extends JFrame implements ActionListener
{
	
	JPanel panel;
	JButton back, logout,confirm;
	JScrollPane scroll;
	Connection connection=null;
	boolean status;
	
	JScrollPane tableScrollPane;
	ResultSet rs;
	String user,tree;
	User u;
	JLabel limg;
	ImageIcon img;
	Random r = new Random();
	ListSelectionModel cellSelectionModel;
	String DealerId;
	JTable table;
	
	
	public MyCart(User u)
	{
		
		super("My Cart");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000, 700);
		
		panel = new JPanel();
		panel.setLayout(null);
		//panel.setBackground(Color.GRAY);
		this.add(panel);
		
		this.u = u;	
		user = u.UserId;
		System.out.println(user);
			
		String[] columns = {"TreeId"};
		
		Object[][] data = getTableDetails();
		
		table = new JTable(data, columns);
		
		tableScrollPane = new JScrollPane(table);
		tableScrollPane.setBounds(250,250,300,100);
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
		
		confirm = new JButton("Order");
		confirm.setBounds(350, 500, 200,40);
		confirm.setFont(new Font("Consolas",Font.BOLD,20));
		confirm.setBackground(Color.DARK_GRAY);
		confirm.setForeground(Color.white);
		confirm.addActionListener(this);
		panel.add(confirm);
		
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
			u.setVisible(true);
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
			
			tree = selectedData;
			
			try
			{
				if(!tree.equals(""))
				{
					try
					{
						
						connection=DatabaseConnection.DbConnector();
						String mysql="SELECT * FROM `tree` WHERE `tree`.`TreeId` = '"+tree+"'";
						Statement st = connection.createStatement();
						System.out.println("statement created");
						ResultSet rs = st.executeQuery(mysql);
						System.out.println("results received");
						
						while(rs.next())
						{
							
							//UserId=rs.getString("UserId");
							DealerId=rs.getString("DealerId");
							System.out.println(DealerId);
							System.out.println("results received");
							
						}
						
					}
					catch(Exception ex)
					{
						System.out.println("Exception : " +ex.getMessage());
					}
					
					try
					{
						int OrderID = random();
						connection=DatabaseConnection.DbConnector();
						String query="INSERT INTO `order` (`OrderID`,`DealerID`, `TreeID`,`UserID`) VALUES ('"+OrderID+"','"+DealerId+"', '"+tree+"','"+user+"')";

							
						Statement st = connection.createStatement();
						System.out.println("statement created");
						st.executeUpdate(query);
						System.out.println("results received");
						this.setVisible(false);
						u.setVisible(true);
						JOptionPane.showMessageDialog(null,"Tree ordered");
							
					}
					catch(Exception ex)
					{
						System.out.println("Exception : " +ex.getMessage());
					}
					try
					{
						
						connection=DatabaseConnection.DbConnector();
						String mysql1="DELETE FROM `cart` WHERE `cart`.`TreeID` = '"+tree+"'";
						Statement st = connection.createStatement();
						System.out.println("statement created");
						st.executeUpdate(mysql1);
						System.out.println("results received");
						
						
						
					}
					catch(Exception ex)
					{
						System.out.println("Exception : " +ex.getMessage());
					}
				}
				
			}
			catch(Exception ex)
			{
				System.out.println("Exception : " +ex.getMessage());
				JOptionPane.showMessageDialog(null,"Cart is empty");
			}
			
			
		}
		
	}
	public int random()
	{
		int y = r.nextInt(99999);
		return y;
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
			String mysql="SELECT * FROM `cart` WHERE `cart`.`UserID` = '"+user+"'";

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
	
