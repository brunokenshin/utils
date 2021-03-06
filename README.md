# malfs-utils ![Circle CI status](https://circleci.com/gh/brunokenshin/utils.png?style=shield&circle-token=e3a4751df12ade32953a35f9b5d5ad9852cd5b45)

* List Sorting:
    + Comparator examples for list sorting, including a chain comparator for sort using 1 or more comparators as criteria

* Json serialization:

* Mail sender:

* Image comparator:
    + Configuration parameters:
        * columns:              Number of grid columns that images will be analyzed
        * lines:                Number of grid columns that images will be analyzed
        * sensitivityFactor:    Defines the error tolerance of your grid (0 to consider different image with just 1 different quadrant)
        * stabilizerFactor:     Defines the comparison power (higher will give more precise comparation)

    + Other options:
        + debugMode:
            - 0: disabled
            - 1: shows in console a changes grid matrix
            - 2: shows in console all the comparation matrix

    + Usage example:
        + Create an ImageComparator object
        + Set comparison parameters (optional)
        + Set debug (optional)
        + Compare images
        + Create image diff (optional)

    ```
        public static void main(String[] args) throws IOException {
            // Creates a comparator object
            ImageComparator imageComparator = new ImageComparator("resources/java_logo.png", "resources/java_logo_different.png");

            // Configures parameters (columns, lines, uncertainty, sensibility)
            imageComparator.setParameters(10, 10, 0, 15);

            // Display some indication of the differences in the image.
            // Sets full changes matrix view
            imageComparator.setDebugMode(2);

            // Compare images
            boolean match = imageComparator.isSameImage();

            // Shows result
            System.out.println("Match: " + match);

            // If the images are considered different, creates a new image showing difference quadrants
            imageComparator.createDiffImage("resources/changes.jpg");
        }

    ```

    + Output:
    ```
    |0,0,0,0,0,0,0,0,0,0|
    |0,0,0,0,0,0,0,0,0,0|
    |0,0,0,0,0,0,0,0,0,0|
    |0,0,0,0,0,0,0,0,0,0|
    |0,0,0,0,0,0,0,0,0,0|
    |0,0,0,0,0,0,0,0,0,0|
    |0,0,0,0,0,0,0,0,0,0|
    |0,0,0,0,0,0,0,0,0,0|
    |0,0,0,0,0,0,0,2,0,0|
    |0,0,0,0,0,0,0,0,0,0|
    Match: false
    ```


    # Original author and post:
     + http://mindmeat.blogspot.com.br/2008/07/java-image-comparison.html

[![wercker status](https://app.wercker.com/status/1d5c46e71eb57d7591ba97df1ed5e3a3/m "wercker status")](https://app.wercker.com/project/bykey/1d5c46e71eb57d7591ba97df1ed5e3a3)
