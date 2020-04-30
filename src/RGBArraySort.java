import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;

public class RGBArraySort extends PixelSort {

    public RGBArraySort(final File file) {
        super(file);
    }

    public void sort() {
        int width = getWidth();
        int height = getHeight();

        BufferedImage sortedImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);

        int[] image = createRGBArray();

        //get the basis of the ordering
        Arrays.sort(image);

        //System.out.println("Height: " + height + " Width: " + width);
        //Generate the sorted BufferedImage
        for (int i = 0; i < height * width; i++) {
            int pixel = image[i];
            int x = i / height;
            int y = height - 1 - (i % height);

            //System.out.println("X: " + x + " Y: " + y);

            sortedImage.setRGB(x, y, pixel);
        }
        setSortedImage(sortedImage);

    }

    private int[] createRGBArray() {
        int width = getWidth();
        int height = getHeight();
        BufferedImage originalImage = getOriginalImage();
        int[] arr;


        arr = new int[width * height];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int pixel = originalImage.getRGB(j, i);
                arr[i * width + (j % width)] = pixel;
            }
        }

        return arr;
    }
}
