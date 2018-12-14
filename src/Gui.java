import javax.swing.*;

public class Gui {

	private static JFrame window;
	
	public static void main(String args[])
	{
		setupWindow();
		window.setVisible(true);
	}
	
	private static void setupWindow()
	{
		window = new JFrame("Sorting Interface");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(960, 540);
	}
}
