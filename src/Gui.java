import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Gui implements ActionListener {

	private static JFrame window;
	private static JMenuBar menu;
	private static ActionListener al;
	private static JMenuItem test;
	
	
	public static void main(String args[])
	{
		setupWindow();
		setupMenus();
		window.setVisible(true);
	}
	
	private static void setupWindow()
	{
		window = new JFrame("Sorting Interface");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//window.setLayout(BorderLayout);
		window.setSize(960, 540);
		
	}
	
	private static void setupMenus()
	{
		menu = new JMenuBar();
		JMenu sorts = new JMenu("Sorts");
		JMenu file = new JMenu("File");
		
		test = menuitem("Test");
		test.addActionListener(al);
		
		sorts.add(test);
		
		file.add(menuitem("Select Image"));
		file.add(menuitem("Exit"));
		
		menu.add(file);
		menu.add(sorts);
		
		window.add(menu, BorderLayout.NORTH);
	}
	
	private static JMenuItem menuitem(String name)
	{
		JMenuItem temp = new JMenuItem(name);
		temp.addActionListener(al);
		
		return temp;
	}
	
	public void actionPerformed(ActionEvent event)
	{
		String contents = event.getActionCommand();
		System.out.println(contents);
		
		if(contents.compareTo("Test") == 0)
		{
			System.out.println("Test successful");
			window.setName("test");
		}
		
		else
			System.out.println("EVVEN");
	}
}
