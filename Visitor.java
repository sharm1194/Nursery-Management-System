import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.sql.*;


public class Visitor extends JFrame implements ActionListener,MouseListener
{
	
	JPanel panel;
	JButton search,  back,search2;
	JTextField tsearch,tsearch2;
	JLabel label,label1;
	Admin l;
	Connection connection=null;
	String user,Name,tree;
	boolean status;
	
	JScrollPane tableScrollPane;
	ResultSet rs;
	JLabel limg;
	ImageIcon img;
	
	public Visitor()
	{
		
		super("Plant Info");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000, 700);
		
		panel = new JPanel();
		panel.setLayout(null);
		//panel.setBackground(Color.GRAY);
		this.add(panel);
		
		this.l = l;
		
		label = new JLabel("Plant Name");
		label.setBounds(200,100,200,50);
		label.setFont(new Font("Consolas",Font.BOLD,20));
		panel.add(label);
		
		search = new JButton("Search Plant");
		search.setBounds(700,100,200,50);
		search.setFont(new Font("Consolas",Font.BOLD,20));
		search.setBackground(Color.DARK_GRAY);
		search.setForeground(Color.white);
		search.addActionListener(this);
		search.addMouseListener(this);
		panel.add(search);
		
		tsearch = new JTextField();
		tsearch.setBounds(330,100,350,50);
		tsearch.setFont(new Font("Consolas",Font.BOLD,20));
	    panel.add(tsearch);
		
		back = new JButton("Back");
		back.setBounds(50, 550, 200,50);
		back.setFont(new Font("Consolas",Font.BOLD,20));
		back.setBackground(Color.DARK_GRAY);
		back.setForeground(Color.white);
		back.addActionListener(this);
		panel.add(back);
		
		
		search2 = new JButton("Search Tree");
		search2.setBounds(700,400,200,50);
		search2.setFont(new Font("Consolas",Font.BOLD,20));
		search2.setBackground(Color.DARK_GRAY);
		search2.setForeground(Color.white);
		search2.addActionListener(this);
		search2.addMouseListener(this);
		panel.add(search2);
		
		label1 = new JLabel("Tree ID");
		label1.setFont(new Font("Consolas",Font.BOLD,20));
		label1.setBounds(250,400,200,50);
		panel.add(label1);
		
		tsearch2 = new JTextField();
		tsearch2.setBounds(380,400,300,50);
		tsearch2.setFont(new Font("Consolas",Font.BOLD,20));
		panel.add(tsearch2);
		
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
		if(e.getSource()==search)
		{
			if(tsearch.getText().equals("") )
			{
				search.setEnabled(false);
			}

			else
			{
				search.setEnabled(true);
			}
			System.out.println("IN");
		}
		if(e.getSource()==search2)
		{
			if(tsearch2.getText().equals("") )
			{
				search2.setEnabled(false);
			}

			else
			{
				search2.setEnabled(true);
			}
			System.out.println("IN");
		}
	}
	
	public void actionPerformed(ActionEvent event)
	{
		String text = event.getActionCommand();
		if(text.equals(search.getText()))
		{
			
				//String mysql="SELECT * FROM `tree` WHERE `tree`.`Name` = '"+Name+"'";
			Name = tsearch.getText();
			System.out.println(Name);
				
			String[] columns = {"TreeId", "Name", "SciName","Price", "DealerId" };
			
			Object[][] data = getTableDetails();
			
			JTable table = new JTable(data, columns);
			
			tableScrollPane = new JScrollPane(table);
			tableScrollPane.setBounds(380,200,300,100);
			panel.add(tableScrollPane);
			tsearch.setText("");
			tsearch2.setText("");
		}
		else if(text.equals(back.getText()))
		{
			this.setVisible(false);
			Login m = new Login();
			m.setVisible(true);
		}
		else if(text.equals(search2.getText()))
		{
			tree = tsearch2.getText();
			try
			{
				connection=DatabaseConnection.DbConnector();
				String mysql="SELECT * FROM `tree` WHERE `tree`.`TreeId` = '"+tree+"'";
				Statement st = connection.createStatement();
				System.out.println("statement created");
				ResultSet rs = st.executeQuery(mysql);
				System.out.println("results received");
				int x=0;
				while(rs.next())
				{
					System.out.println("inside");
					x=1;
					
					this.setVisible(false);
					Plant m = new Plant(this,tree);
					m.setVisible(true);
					tsearch.setText("");
					tsearch2.setText("");
					
				}
				if(x==0)
				{
					JOptionPane.showMessageDialog(null,"Tree Id does not match");
					tsearch2.setText("");
					
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
			String mysql="SELECT * FROM `tree` WHERE `tree`.`Name` = '"+Name+"'";

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
				data[i][j++] = rs.getString("DealerId");

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