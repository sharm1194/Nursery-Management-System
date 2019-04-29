import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class MyUserInfo extends JFrame implements ActionListener
{
	
	JPanel panel;
	JButton change, back, logout;
	JLabel limg;
	ImageIcon img;
	
	
	public MyUserInfo()
	{
		
		super("My User Info");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000, 700);
		
		panel = new JPanel();
		panel.setLayout(null);
		//panel.setBackground(Color.GRAY);
		this.add(panel);
		
		change = new JButton("Change");
		change.setBounds(100, 200, 200,40);
		panel.add(change);
		

		
		back = new JButton("Back");
		back.setBounds(100, 400, 200,40);
		panel.add(back);
		
		logout = new JButton("Log Out");
		logout.setBounds(400, 400, 200,40);
		panel.add(logout);
		
		img = new ImageIcon(".//Image//back.jpg");
		limg = new JLabel(img);
		limg.setBounds(0,0,1000,700);
		panel.add(limg);
		
	}
	
	public void actionPerformed(ActionEvent event)
	{
		
	}
	
}
