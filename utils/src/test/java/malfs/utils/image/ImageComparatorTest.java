package malfs.utils.image;


import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


@RunWith(JUnit4.class)
public class ImageComparatorTest {

    private final String imagesPath = "src/test/resources/image/";

    private ImageComparator imageComparator;
    private String image2;
    private String image1;

    @Before
    public void onSetup() {
        image1 = imagesPath;
        image2 = imagesPath;
    }

    @Test
    public void test_compares_identical_images() throws Exception {
        image1 += "java_logo.png";
        image2 += "java_logo_copy.png";

        boolean match = compareImages(image1, image2);
        assertEquals("Images are different", true, match);
    }

    @Test
    public void test_compares_different_images() throws Exception {
        image1 += "java_logo.png";
        image2 += "java_logo_different.png";

        boolean match = compareImages(image1, image2);
        assertEquals("Images are not different", false, match);
    }

    @Test
    public void test_compares_identical_images_with_different_brightness() throws Exception {
        image1 += "java_logo.png";
        image2 += "java_logo_dark.png";

        boolean match = compareImages(image1, image2);
        assertEquals("Images are not different", false, match);
    }

    @Test
    public void test_compares_identical_images_with_different_colors() throws Exception {
        image1 += "java_logo.png";
        image2 += "java_logo_green.png";

        boolean match = compareImages(image1, image2);
        assertEquals("Images are not different", false, match);
    }

    private boolean compareImages(String image1, String image2) throws IOException {
        imageComparator = new ImageComparator(image1, image2);
        imageComparator.setParameters(25, 25, 0, 25);
        imageComparator.setDebugMode(2);
        boolean match = imageComparator.isSameImage();
        imageComparator.createDiffImage("src/test/resources/image/changes.jpg");
        return match;
    }
}