import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.filechooser.*;

import javax.swing.*;

public class Gui implements ActionListener
{

	static JFrame window;
	static JMenuBar menu;
	static JMenuItem test;
	static Gui al; //to be used as an ActionListener
	static JFileChooser jfc;
	
	Gui()
	{
		super();
		jfc = new JFileChooser();
	}
	
	public static void main(String args[])
	{
		al = new Gui();
		setupWindow();
		setupMenus();
		window.setVisible(true);
	}
	
	private static void setupWindow()
	{
		window = new JFrame("Sorting Interface");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLayout(new BorderLayout());
		window.setSize(960, 540);
		
	}
	
	private static void setupMenus()
	{
		menu = new JMenuBar();
		JMenu sorts = new JMenu("Sorts");
		JMenu file = new JMenu("File");
		
		test = menuitem("Test");
		
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
		
		if(contents.compareTo("Exit") == 0)
		{
			System.exit(0);
		}
		
		else if(contents.compareTo("Select Image") == 0)
		{
			int returnVal = jfc.showOpenDialog(jfc);
			
            if (returnVal == JFileChooser.APPROVE_OPTION) {
            	File file = jfc.getSelectedFile();
            	//TODO set first image panel here
            }
		}
		
		else if(contents.compareTo("Test") == 0)
		{
			System.out.println("Test successful1");
		}
		
		else
		{
			System.out.println("Else1");
		}
	}
}