import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class RGBArraySort implements IPixelSort {

    int width;
    int height;
    final File originalFile;
    File sortedFile;
    BufferedImage originalImage;
    BufferedImage sortedImage;

    public RGBArraySort(final File file) {
        originalFile = file;
        try {
            originalImage = ImageIO.read(file);
            width = originalImage.getWidth();
            height = originalImage.getHeight();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sort() {

        sortedImage = new BufferedImage(width, height,
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
    }

    private int[] createRGBArray() {
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

    @Override
    public File print() {
        try {
            sortedFile = new File(originalFile.toString() + "RGBArraySort.png");
            ImageIO.write(sortedImage, "png", sortedFile);

            return sortedFile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public BufferedImage getOriginalImage() {
        return originalImage;
    }

    @Override
    public BufferedImage getSortedImage() {
        return sortedImage;
    }

    @Override
    public File getSortedFile() {
        return sortedFile;
    }

    @Override
    public String stats() {
        return null;
    }
}
