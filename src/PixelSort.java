import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

abstract class IPixelSort {

    int width;
    int height;

    File originalFile;
    File sortedFile;

    BufferedImage originalImage;
    BufferedImage sortedImage;

    IPixelSort(File file){
        originalFile = file;
        try {
            originalImage = ImageIO.read(file);
            width = originalImage.getWidth();
            height = originalImage.getHeight();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *The sort method is where the sorting operations should be implemented.
     */
    abstract void sort();

    /**
     * Prints the image to a file and then returns the file location.
     * @return The location of the sorted image file as a File
     */
    File print(){
        try {
            sortedFile = new File(originalFile.toString() + this.getClass().getSimpleName() + ".png");
            ImageIO.write(sortedImage, "png", sortedFile);

            return sortedFile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * @return original image as a BufferedImage
     */
    BufferedImage getOriginalImage() {
        return originalImage;
    }

    /**
     * @return sorted image as a BufferedImage
     */
    BufferedImage getSortedImage() {
        return sortedImage;
    }

    /**
     * @return the file location of the sorted image as a File
     */
    File getSortedFile() { return sortedFile; }

    /**
     * @return some stats as a String about the image such as size
     */
    String stats() {
        String str = "Image Size: " + width + "x" + height + " Original File:" + originalFile.toString();
        return str;
    }
}
