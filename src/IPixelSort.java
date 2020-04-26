import java.awt.image.BufferedImage;
import java.io.File;

public interface IPixelSort {
    void sort();

    File print();

    BufferedImage getOriginalImage();

    BufferedImage getSortedImage();

    File getSortedFile();

    String stats();
}
