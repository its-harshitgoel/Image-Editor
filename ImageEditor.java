import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class ImageEditor {

    // Method to convert an input image to grayscale
    public static BufferedImage convertToGrayScale(BufferedImage inputImage) {
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

        // Loop through each pixel in the image
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                outputImage.setRGB(j, i, inputImage.getRGB(j, i));
            }
        }

        return outputImage;
    }

    // Method to change the brightness of an input image
    public static BufferedImage changeBrightness(BufferedImage inputImage, int increase) {
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

        // Loop through each pixel in the image
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color pixel = new Color(inputImage.getRGB(j, i));

                int red = pixel.getRed();
                int blue = pixel.getBlue();
                int green = pixel.getGreen();

                // Adjust pixel values based on brightness increase
                red = red + (increase * red / 100);
                blue = blue + (increase * blue / 100);
                green = green + (increase * green / 100);

                // Ensure pixel values are within valid range
                if (red > 255) red = 255;
                if (blue > 255) blue = 255;
                if (green > 255) green = 255;

                if (red < 0) red = 0;
                if (blue < 0) blue = 0;
                if (green < 0) green = 0;

                Color newPixel = new Color(red, green, blue);
                outputImage.setRGB(j, i, newPixel.getRGB());
            }
        }
        return outputImage;
    }

    // Method to flip an input image horizontally
    public static BufferedImage flipHorizontally(BufferedImage inputImage) {
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

        // Loop through each pixel in the image
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                outputImage.setRGB(j, i, inputImage.getRGB(width - j - 1, i));
            }
        }

        return outputImage;
    }

    // Method to flip an input image vertically
    public static BufferedImage flipVertically(BufferedImage inputImage) {
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

        // Loop through each pixel in the image
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                outputImage.setRGB(j, i, inputImage.getRGB(j, height - i - 1));
            }
        }

        return outputImage;
    }

    // Method to rotate an input image 90 degrees clockwise
    public static BufferedImage rotateClockwise(BufferedImage inputImage) {
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();
        BufferedImage outputImage = new BufferedImage(height, width, BufferedImage.TYPE_3BYTE_BGR);

        // Loop through each pixel in the image
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                outputImage.setRGB(height - i - 1, j, inputImage.getRGB(j, i));
            }
        }

        return outputImage;
    }

    // Method to rotate an input image 90 degrees anticlockwise
    public static BufferedImage rotateAnticlockwise(BufferedImage inputImage) {
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();
        BufferedImage outputImage = new BufferedImage(height, width, BufferedImage.TYPE_3BYTE_BGR);

        // Loop through each pixel in the image
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                outputImage.setRGB(i, width - j - 1, inputImage.getRGB(j, i));
            }
        }

        return outputImage;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            System.out.println();
            System.out.println("************************ IMAGE EDITOR ************************");
            System.out.println("Enter 'exit' to quit.");
            
            System.out.print("Enter the name of the image file you want to edit: ");
            String inputFileName = sc.nextLine();
            
            if (inputFileName.equalsIgnoreCase("exit")) {
                System.out.println("Exiting Image Editor.");
                break;
            }
            
            System.out.println("Choose one of the editing options:");
            System.out.println("1. Increase Brightness");
            System.out.println("2. Convert to Gray Scale");
            System.out.println("3. Flip Image");
            System.out.println("4. Rotate Image");
            System.out.println("5. Exit.");
            
            System.out.print("Please enter the option number: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                System.out.print("Enter the Increase value: ");
                int increase = sc.nextInt();

                File inputFile = new File(inputFileName);
                try {
                    BufferedImage inputImage = ImageIO.read(inputFile);
                    BufferedImage changedBrightness = changeBrightness(inputImage, increase);
                    File ChangedBrightnessImage = new File("changedBrightnessImage.jpg");
                    ImageIO.write(changedBrightness, "jpg", ChangedBrightnessImage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (choice == 2) {
                File inputFile = new File(inputFileName);
                try {
                    BufferedImage inputImage = ImageIO.read(inputFile);
                    BufferedImage grayScale = convertToGrayScale(inputImage);
                    File graScaleImage = new File("grayscaleImage.jpg");
                    ImageIO.write(grayScale, "jpg", graScaleImage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (choice == 3) {
                File inputFile = new File(inputFileName);
                try {
                    BufferedImage inputImage = ImageIO.read(inputFile);

                    System.out.println("Choose one of the flip options:");
                    System.out.println("1. Flip horizontally");
                    System.out.println("2. Flip vertically");
                    System.out.print("Please enter the flip option: ");
                    int flipChoice = sc.nextInt();
                    sc.nextLine();

                    BufferedImage flippedImage = null;

                    if (flipChoice == 1) {
                        flippedImage = flipHorizontally(inputImage);
                        File flippedImageFile = new File("flippedHorizontallyImage.jpg");
                        ImageIO.write(flippedImage, "jpg", flippedImageFile);
                    } else if (flipChoice == 2) {
                        flippedImage = flipVertically(inputImage);
                        File flippedImageFile = new File("flippedVerticallyImage.jpg");
                        ImageIO.write(flippedImage, "jpg", flippedImageFile);
                    } else {
                        System.out.println("Invalid flip option.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (choice == 4) {
                File inputFile = new File(inputFileName);
                try {
                    BufferedImage inputImage = ImageIO.read(inputFile);

                    System.out.println("Choose one of the rotation options:");
                    System.out.println("1. Rotate 90 degrees clockwise");
                    System.out.println("2. Rotate 90 degrees anticlockwise");
                    System.out.print("Please enter the rotation option: ");
                    int rotateChoice = sc.nextInt();
                    sc.nextLine();

                    BufferedImage rotatedImage = null;

                    if (rotateChoice == 1) {
                        rotatedImage = rotateClockwise(inputImage);
                    } else if (rotateChoice == 2) {
                        rotatedImage = rotateAnticlockwise(inputImage);
                    } else {
                        System.out.println("Invalid rotation option.");
                    }

                    if (rotatedImage != null) {
                        File rotatedImageFile = new File("rotatedImage.jpg");
                        ImageIO.write(rotatedImage, "jpg", rotatedImageFile);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
                else if (choice == 5) {
                System.out.println("Exiting Image Editor.");
                break;
            } else {
                System.out.println("Invalid option.");
                break;
            }  
        }
        sc.close();
    }
}
