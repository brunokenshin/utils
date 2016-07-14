/*
 * Im not the author of the algorithm. Just made some minor changes and comments. For original post
 * and class, look at: http://mindmeat.blogspot.com.br/2008/07/java-image-comparison.html
 */

package malfs.utils.image;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.GrayFilter;


public class ImageComparator {

    private BufferedImage image1 = null;
    private BufferedImage image2 = null;
    private BufferedImage DistinctionImage = null;
    private int columns = 0;
    private int lines = 0;
    private int toleratedUncertainty = 0;
    private int sensitivity = 10;
    private boolean match = false;


    // 0: disabled
    // 1: shows in console a changes grid matrix
    // 2: shows in console all the comparation matrix
    private int debugMode = 0;

    // PNG images constructor
    public ImageComparator(String file1, String file2) {
        this(loadPNG(file1), loadPNG(file2));
    }

    // AWT images constructor
    public ImageComparator(Image img1, Image img2) {
        this(imageToBufferedImage(img1), imageToBufferedImage(img2));
    }

    // Buffer images constructor
    public ImageComparator(BufferedImage image1, BufferedImage image2) {
        this.image1 = image1;
        this.image2 = image2;
        autoSetParameters();
    }

    // Compare two pre-set images
    private void compare() {
        // Convert to buffer
        DistinctionImage = imageToBufferedImage(image2);

        Graphics2D gc = DistinctionImage.createGraphics();
        gc.setColor(Color.RED);

        // Convert images to gray scale
        image1 = imageToBufferedImage(GrayFilter.createDisabledImage(image1));
        image2 = imageToBufferedImage(GrayFilter.createDisabledImage(image2));

        // Defines the comparation block size
        int blockWidth = image1.getWidth() / columns;
        int blockHeigh = image1.getHeight() / lines;

        // Config that images are the same by default
        this.match = true;

        // Runs through the image blocks looking for differences in each
        for (int line = 0; line < lines; line++) {
            if (debugMode > 0)
                System.out.print("|");
            for (int column = 0; column < columns; column++) {
                int averageBrightnessFromImage1 = getAverageBrightness(image1.getSubimage(column * blockWidth, line * blockHeigh,
                                                                                          blockWidth - 1, blockHeigh - 1));
                int averageBrightnessFromImage2 = getAverageBrightness(image2.getSubimage(column * blockWidth, line * blockHeigh,
                                                                                          blockWidth - 1, blockHeigh - 1));
                int diff = Math.abs(averageBrightnessFromImage1 - averageBrightnessFromImage2);

                // Compares the difference between the sparkles with the sensitivity factor
                // If the difference is greater than factor, the images are considered different
                // If they are different, it indicates with a red square on the image quadrant
                if (diff > toleratedUncertainty) {
                    gc.drawRect(column * blockWidth, line * blockHeigh, blockWidth - 1, blockHeigh - 1);
                    this.match = false;
                }

                if (debugMode == 1)
                    System.out.print((diff > toleratedUncertainty ? "X" : " "));
                if (debugMode == 2)
                    System.out.print(diff + (column < columns - 1 ? "," : ""));
            }

            if (debugMode > 0)
                System.out.println("|");
        }

        System.out.println("Match: " + this.match);
    }

    public boolean isSameImage() {
        compare();
        return match;
    }

    public void createDiffImage(String file) throws IOException {
        if (!match) {
            saveJPG(getChangeIndicator(), file);
        }
    }

    // Returns the brightness average of received image
    private int getAverageBrightness(BufferedImage image) {
        Raster raster = image.getData();
        int total = 0;
        for (int line = 0; line < raster.getHeight(); line++) {
            for (int column = 0; column < raster.getWidth(); column++) {
                total += raster.getSample(raster.getMinX() + column, raster.getMinY() + line, 0);
            }
        }
        return total / ((raster.getWidth() / sensitivity) * (raster.getHeight() / sensitivity));
    }

    // Saves image to buffer
    private static BufferedImage imageToBufferedImage(Image image) {
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = bufferedImage.createGraphics();
        g2.drawImage(image, null, null);
        return bufferedImage;
    }

    private void saveJPG(Image image, String filename) throws IOException {
        BufferedImage bufferedImage = imageToBufferedImage(image);
        File outputfile = new File(filename);
        ImageIO.write(bufferedImage, "jpg", outputfile);
    }

    private static Image loadPNG(String filename) {
        try {
            BufferedImage img = ImageIO.read(new File(filename));
            return img;
        } catch (IOException e) {
            System.out.println("IOException");
            return null;
        }
    }

    // Configures the default values
    public void autoSetParameters() {
        columns = 10;
        lines = 10;
        toleratedUncertainty = 1;
        sensitivity = 25;
    }

    // Configures the comparation parameters
    // Columns and Lines defines the grid size
    // sensitivityFactor defines the error tolerance of your grid (0 to consider different image
    // with just 1 different quadrant)
    // stabilizerFactor defines the comparison power (higher will give more precise comparation)
    public void setParameters(int numberOfColumnsToDivide, int numberOfLinesToDivide, int sensitivityFactor, int stabilizerFactor) {
        this.columns = numberOfColumnsToDivide;
        this.lines = numberOfLinesToDivide;
        this.toleratedUncertainty = sensitivityFactor;
        this.sensitivity = stabilizerFactor;
    }

    // Configures the comparison detailed view at runtime
    public void setDebugMode(int debugMode) {
        this.debugMode = debugMode;
    }

    // Returns changes image
    private BufferedImage getChangeIndicator() {
        return DistinctionImage;
    }

}