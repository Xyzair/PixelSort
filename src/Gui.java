import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
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
	static JLabel image, sortedImage;
	static File file;
	
	Gui()
	{
		super();
		
		//JFileChoose setup
		jfc = new JFileChooser();
		jfc.setCurrentDirectory(new File(System.getProperty("user.home") + System.getProperty("file.separator") + "Pictures"));
		
		setupWindow();
		setupMenus();
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
		window.setSize(1680, 945);
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
	
	private void setupImagePanel(File path)
	{
		//leaving this here for testing
//		image = loadImage("./src/TestingImages/ColorExplosion.jpg");
//		sortedImage = loadImage("./src/TestingImages/Scenic.png");
		
		image = loadImage(path);
		window.add(image, BorderLayout.WEST);
		
		
		image.setSize(window.getWidth()/2, window.getHeight()/2);
		
		
	    window.getRootPane().addComponentListener(new ComponentAdapter() 
	    	{
	            public void componentResized(ComponentEvent event) 
	            {
	            	if(image != null)
	            		image.setSize(window.getWidth()/2, window.getHeight());
	                
	            	if(sortedImage != null)
	            		sortedImage.setSize(window.getWidth()/2, window.getHeight());
	            }
	        });
		
	}
	
	private void setupSortedImagePanel(String path)
	{
		sortedImage = loadImage(path);
		
		sortedImage.setSize(window.getWidth()/2, window.getHeight());
		
		window.add(sortedImage, BorderLayout.EAST);
		
		
	}
	
	private static JLabel loadImage(String path)
	{
		JLabel label;
		try 
		{
			//turns a file path -> buffered image -> imageIcon -> display in JLabel
			BufferedImage image = ImageIO.read(new File(path));
			ImageIcon icon = new ImageIcon(image);
			//resizing image
			icon = new ImageIcon(icon.getImage());
			//.getScaledInstance(window.getWidth()/2, window.getHeight()/2, Image.SCALE_DEFAULT)
			label = new JLabel(icon);
			
			//JOptionPane.showMessageDialog(null, label);
			return label;
		} 
		
		catch (IOException e) 
		{
			
		}
		
		return null;
	}
	
	private static JLabel loadImage(File path)
	{
		JLabel label;
		
		try 
		{
			//turns a file path -> buffered image -> imageIcon -> display in JLabel
			BufferedImage image = ImageIO.read(path);
			ImageIcon icon = new ImageIcon(image);
			//resizing image
			icon = new ImageIcon(icon.getImage());
			//.getScaledInstance(window.getWidth()/2, window.getHeight()/2, Image.SCALE_DEFAULT)
			label = new JLabel(icon);
			
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
			
            if (returnVal == JFileChooser.APPROVE_OPTION) 
            {
            	if(image != null)
            	{
            		window.remove(image);
            	}
            	
            	file = jfc.getSelectedFile();
            	
            	setupImagePanel(file);
            }
		}
		
		else if(contents.compareTo("Test") == 0)
		{
			setupSortedImagePanel(file.toString());
			System.out.println("Test successful");
		}
		
		else
		{
			System.out.println("Else");
		}
	}
}