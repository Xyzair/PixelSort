import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Gui{

	static JFrame window;
	static JMenuBar menu;
	static JMenuItem test;
	static GUIListener gl;
	
	public static void main(String args[])
	{
		gl = new GUIListener();
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
		test.addActionListener((ActionListener) gl);
		
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
		temp.addActionListener((ActionListener) gl);
		
		return temp;
	}	
}