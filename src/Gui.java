import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Gui implements ActionListener
{

	static JFrame window;
	static JMenuBar menu;
	static Gui al; //to be used as an ActionListener
	static JFileChooser jfc;
	JLabel image, sortedImage;
	static File file;
	
	Gui()
	{
		super();
		jfc = new JFileChooser();
		
		setupWindow();
		setupMenus();
		setupImagePanels();
	}
	
	public static void main(String args[])
	{
		Gui gui = new Gui();

		window.setVisible(true);
	}
	
	private void setupWindow()
	{
		window = new JFrame("Sorting Interface");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLayout(new BorderLayout());
		window.setSize(960, 540);
	}
	
	private void setupMenus()
	{
		menu = new JMenuBar();
		JMenu sorts = new JMenu("Sorts");
		JMenu file = new JMenu("File");
		
		//sort menu tab
		sorts.add(menuitem("Test"));
		
		//file menu tab
		file.add(menuitem("Select Image"));
		file.add(menuitem("Exit"));
		
		menu.add(file);
		menu.add(sorts);
		
		window.add(menu, BorderLayout.NORTH);
	}
	
	private JMenuItem menuitem(String name)
	{
		JMenuItem temp = new JMenuItem(name);
		temp.addActionListener(this);
		
		return temp;
	}
	
	private void setupImagePanels()
	{
		//is there a point in this method?
		image = loadImage("./src/TestingImages/ColorExplosion.jpg");
		sortedImage = loadImage("./src/TestingImages/Scenic.png");
		
		window.add(image, BorderLayout.WEST);
		window.add(sortedImage, BorderLayout.EAST);
		
	}
	
	private static JLabel loadImage(String path)
	{
		JLabel label;
		
		try 
		{
			//turns a file path -> buffered image -> imageIcon -> display in JLabel
			label = new JLabel(new ImageIcon(ImageIO.read(new File(path))));
			//JOptionPane.showMessageDialog(null, label);
			return label;
		} 
		
		catch (IOException e) 
		{
			
		}
		
		return null;
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
            	file = jfc.getSelectedFile();
            	//TODO set first image panel here
            }
		}
		
		else if(contents.compareTo("Test") == 0)
		{
			System.out.println("Test successful");
		}
		
		else
		{
			System.out.println("Else");
		}
	}
}