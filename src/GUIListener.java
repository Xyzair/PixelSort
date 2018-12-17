import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GUIListener implements ActionListener {

	GUIListener()
	{
		super();
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
			System.out.println("Image test successful");
		}
		
		else if(contents.compareTo("Test") == 0)
		{
			System.out.println("Test successful");
		}
		
		else
			System.out.println("Else");
	}
}
