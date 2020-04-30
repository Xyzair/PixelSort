import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class HSBArraySort extends RGBArraySort {
    public HSBArraySort(File file) {
        super(file);
    }

    @Override
    public void sort() {
        //There is going to be so many extra cycles because there's no HSB type
        sortedImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        float[][] image;
        image = createHSBArray();

        int n = image.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (image[j][0] < image[j + 1][0]) {
                    float[] temp = image[j];
                    image[j] = image[j + 1];
                    image[j + 1] = temp;
                } else if (image[j][0] == image[j + 1][0]) {
                    if (image[j][2] < image[j + 1][2]) {
                        float[] temp = image[j];
                        image[j] = image[j + 1];
                        image[j + 1] = temp;
                    } else if (image[j][1] == image[j + 1][1]
                            && image[j][1] < image[j + 1][1]) {
                        float[] temp = image[j];
                        image[j] = image[j + 1];
                        image[j + 1] = temp;
                    }
                }
            }
        }

        for (int i = 0; i < height * width; i++) {
            int pixel = Color.HSBtoRGB(image[i][0], image[i][1], image[i][2]);
            int x = i / height;
            int y = height - 1 - (i % height);

            //System.out.println("X: " + x + " Y: " + y);

            sortedImage.setRGB(x, y, pixel);
        }
    }

    private float[][] createHSBArray() {
        float[][] arr = new float[width * height][3];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color pixel = new Color(originalImage.getRGB(j, i));
                arr[i * width + (j % width)] = Color.RGBtoHSB(pixel.getRed(),
                        pixel.getGreen(), pixel.getBlue(), null);
            }
        }

        return arr;
    }

    @Override
    public File print() {
        try {
            sortedFile = new File(originalFile.toString() + "HSBArraySort.png");
            ImageIO.write(sortedImage, "png", sortedFile);

            return sortedFile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}