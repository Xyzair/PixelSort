import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;



public final class ImageSort {

	int width, height;
	
	public final void tbDefaultSort(File file)
	{
		int[] image = createRGBArray(file);
		
		Arrays.sort(image);
		
		tbToImage(image, "tbDefaultSort", file);
	}
	
	//tb stands for top to bottom
	private void tbToImage(int[] image, String sortName, File file)
	{
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		for(int i = 0; i < height*width; i++)
		{
				int pixel = image[i];
				int x = i/height ;
				int y = (height -1 - i) % height;
				bi.setRGB(x, y, pixel);
		}
		
		print(bi, sortName, file);
	}
	
	//lr stands for left to right
	public final void lrDefaultSort(File file)
	{
		int[] image = createRGBArray(file);
		
		Arrays.sort(image);
		
		lrToImage(image, "defaultSort", file);
	}
	
	private void lrToImage(int[] image, String sortName, File file)
	{
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		for(int i = 0; i < height; i++)
		{
			for(int j = 0; j < width; j++)
			{
				int pixel = image[i * width + (j % width)];
				bi.setRGB(j, i, pixel);
			}
		}
		
		print(bi, sortName, file);
	}
	
	//This exists because it's going to be called a lot and the sorting
	// of the pixels before printing is a big part of how it will look at the end.
	private void print(BufferedImage bi, String sortName, File file)
	{
		File outputfile = new File(file.getPath() + sortName + ".png");
		
		try 
		{
			ImageIO.write(bi, "png", outputfile);
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private int[] createRGBArray(File file)
	{
		try 
		{
			BufferedImage image = ImageIO.read(file);
			int[] arr;
			
			width = image.getWidth();
			height = image.getHeight();
			System.out.println(width+" "+height);
			
			arr = new int[width*height];
			
			for(int i = 0; i < height; i++)
			{
				for(int j = 0; j < width; j++)
				{
					int pixel = image.getRGB(j ,i);
					arr[i * width + (j % width)] = pixel; 
							
				}
			}
			
			return arr;
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
