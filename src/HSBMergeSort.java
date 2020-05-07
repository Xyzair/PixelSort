import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class HSBMergeSort extends PixelSort {
    public HSBMergeSort(File file) {
        super(file);
    }

    @Override
    public void sort() {
        //There is going to be so many extra cycles because there's no HSB type
        int width = getWidth();
        int height = getHeight();

        BufferedImage sortedImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        float[][] image;
        //prep the data to be sorted
        image = createHSBArray();

        //Do the sort
        sort(image, 0, image.length-1);

        //Create buffered image
        for (int i = 0; i < height * width; i++) {
            int pixel = Color.HSBtoRGB(image[i][0], image[i][1], image[i][2]);
            int x = i / height;
            int y = height - 1 - (i % height);

            //System.out.println("X: " + x + " Y: " + y);

            sortedImage.setRGB(x, y, pixel);
        }
        setSortedImage(sortedImage);
    }

    private float[][] createHSBArray() {
        int width = getWidth();
        int height = getHeight();

        BufferedImage originalImage = getOriginalImage();

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

    //Horrendously slow, O(n^2), but I didn't want to delete it.
    private float[][] bubbleSort(float[][] image){
        int n = image.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (image[j][0] < image[j + 1][0]) {
                    float[] temp = image[j];
                    image[j] = image[j + 1];
                    image[j + 1] = temp;
                    //System.out.println("H:" + image[j][0] + " S: " + image[j][1] + " L: " + image[j][2]);
                } else if (image[j][0] == image[j + 1][0]) {
                    if (image[j][1] < image[j + 1][1]) {
                        float[] temp = image[j];
                        image[j] = image[j + 1];
                        image[j + 1] = temp;
                    } else if (image[j][1] == image[j + 1][1]
                            && image[j][2] < image[j + 1][2]) {
                        float[] temp = image[j];
                        image[j] = image[j + 1];
                        image[j + 1] = temp;
                    }
                }
            }
        }
        return image;
    }

    //pulled from https://www.geeksforgeeks.org/merge-sort/ and then modified
    //Also Lesson learned about how important the difference between n^2 and nlogn
    private void merge(float arr[][], int l, int m, int r)
    {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        float L[][] = new float [n1][];
        float R[][] = new float [n2][];

        /*Copy data to temp arrays*/
        for (int i=0; i<n1; ++i)
            L[i] = arr[l + i];
        for (int j=0; j<n2; ++j)
            R[j] = arr[m + 1+ j];


        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2)
        {
            // 0 = Hue, 1 = Saturation, 2 = Brightness
            if (LorR(L[i], R[j]))
            {
                arr[k] = L[i];
                i++;
            }
            else
            {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1)
        {
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2)
        {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Main function that sorts arr[l..r] using
    // merge()
    private void sort(float arr[][], int l, int r)
    {
        if (l < r)
        {
            // Find the middle point
            int m = (l+r)/2;

            // Sort first and second halves
            sort(arr, l, m);
            sort(arr , m+1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }

    //This is where the real sorting happens, the merge sort just make it fast.
    //PS I think this might have been a fools errand to do a 2D sort on a 3D object
    private boolean LorR(float[] l, float[] r) {
        // 0 = Hue, 1 = Saturation, 2 = Brightness
        //Brightness too low (basically black)
        if(l[2] <= 15 || r[2] <= 15){
            if(l[2] < r[2]){
                return false;
            } else if (l[2] == r[2]) {
                if(l[1] > r[1] || (l[1] == r[1] && l[0] > r[0])) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return true;
            }
        //Saturation too low (basically white)
        } else if(l[1] <= 15 || r[1] <= 15){
            if(l[1] < r[1]){
                return false;
            } else if (l[1] == r[1]) {
                if(l[2] > r[2] || (l[2] == r[2] && l[0] > r[0])) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return true;
            }
        } else {
            if ( l[0] < r[0]
                    || (l[0] == r[0] && l[1] < r[1])
                    || (l[0] == r[0] && l[1] == r[1] && l[2] < r[2])) {
                return true;
            }
            else
                return false;
        }
    }
}
