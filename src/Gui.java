import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Gui implements ActionListener {

	private JFrame window;
	private JMenuBar menu;
	private Gui al; //to be used as an ActionListener
	private JFileChooser jfc;
	private JLabel image, sortedImageLabel, imageFrame;
	private File imageFile;
	private File sortedFile;

	public static void main(String[] args)
	{
		Gui gui = new Gui();
	}

	Gui()
	{
		super();

		//JFileChoose setup
		jfc = new JFileChooser();
		jfc.setCurrentDirectory(new File(System.getProperty("user.home") + System.getProperty("file.separator") + "Pictures"));

		setupWindow();
		setupMenus();
		window.setVisible(true);
	}

	private void setupWindow()
	{
		window = new JFrame("Sorting Interface");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLayout(new BorderLayout());
		window.setSize(1920,1080);
		//window.setSize(3840, 2160);
		window.repaint();
	}

	private void setupMenus()
	{
		menu = new JMenuBar();
		JMenu sorts = new JMenu("Sorts");
		JMenu file = new JMenu("File");

		//sort menu tab
		sorts.add(menuitem("RGB Array Sort"));
		sorts.add(menuitem("HSB Array Sort"));
		sorts.add(menuitem("Test"));

		//file menu tab
		file.add(menuitem("Select Image"));
		file.add(menuitem("Exit"));

		menu.add(file);
		menu.add(sorts);

		window.add(menu, BorderLayout.NORTH);

		window.repaint();
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

		if(image != null)
		{
			window.remove(imageFrame);
		}
		imageFrame = new JLabel();
		imageFrame.setLayout(new GridLayout(2,1,5,5));
		imageFrame.setSize(new Dimension(window.getWidth(), window.getHeight()));

		image = loadImage(path);
		image.setMaximumSize(new Dimension(1920, 1080));
		image.setSize(window.getWidth()/2, window.getHeight()/2);

		imageFrame.add(image);
		imageFrame.setHorizontalAlignment(SwingConstants.CENTER);

		window.add(imageFrame, BorderLayout.CENTER);

		window.revalidate();

		//Inserting a component listener to place that can constantly update during window size changes
	    window.getRootPane().addComponentListener(new ComponentAdapter()
	    	{
	            public void componentResized(ComponentEvent event)
	            {
	            	if(image != null) {
						image.setSize(window.getWidth(), window.getHeight() / 2);
					}

	            	if(sortedImageLabel != null) {
						sortedImageLabel.setSize(window.getWidth(), window.getHeight() / 2);
					}
	            	window.revalidate();
	            	window.repaint();
	            }
	        });
	}

	private void setupSortedImagePanel(File path)
	{
		if(sortedImageLabel != null)
		{
			window.remove(sortedImageLabel);
		}

		sortedImageLabel = loadImage(path);

		sortedImageLabel.setMaximumSize(new Dimension(1920, 1080));
		sortedImageLabel.setSize(window.getWidth()/2, window.getHeight()/2);

		imageFrame.add(sortedImageLabel);

		window.revalidate();
		window.repaint();

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

            	imageFile = jfc.getSelectedFile();

            	setupImagePanel(imageFile);

            }
		}

		else if(contents.compareTo("RGB Array Sort") == 0)
		{
			if(imageFile != null)
			{
				PixelSort pixel = new RGBArraySort(imageFile);
				pixel.sort();
				sortedFile = pixel.print();

				//sortedFile = new ImageSort().tbDefaultSort(imageFile);
				setupSortedImagePanel(sortedFile);

				window.repaint();

				System.out.println(contents + " was successful");
			}
			else
			{
				System.out.println("Please select a file first.");
			}
		}

		else if(contents.compareTo("HSB Array Sort") == 0)
		{
			if(imageFile != null)
			{
				PixelSort pixel = new HSBArraySort(imageFile);
				pixel.sort();
				sortedFile = pixel.print();

				//sortedFile = new ImageSort().tbDefaultSort(imageFile);
				setupSortedImagePanel(sortedFile);

				window.repaint();

				System.out.println(contents + " was successful");
			}
			else
			{
				System.out.println("Please select a file first.");
			}
		}

		else
		{
			System.out.println(contents + " was not caught");
		}
	}
}
