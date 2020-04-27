import java.awt.image.BufferedImage;
import java.io.File;

public interface IPixelSort {

    /**
     *The sort method is where the sorting operations should be implemented.
     */
    void sort();

    /**
     * Prints the image to a file and then returns the file location.
     * @return The location of the sorted image file as a File
     */
    File print();

    /**
     * @return original image as a BufferedImage
     */
    BufferedImage getOriginalImage();

    /**
     * @return sorted image as a BufferedImage
     */
    BufferedImage getSortedImage();

    /**
     * @return the file location of the sorted image as a File
     */
    File getSortedFile();

    /**
     * @return some stats as a String about the image such as size
     */
    String stats();
}
