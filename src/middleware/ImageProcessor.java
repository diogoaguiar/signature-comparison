package middleware;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import org.apache.tomcat.util.codec.binary.Base64;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class ImageProcessor {

	private JLabel imageView;

	/**
	 * Show an image on a window.
	 * 
	 * @param image
	 */
	public void show(Mat image) {
		show(image, "");
	}

	/**
	 * Show an image on a window.
	 * 
	 * @param image
	 */
	public void show(BufferedImage image) {
		show(image, "");
	}

	/**
	 * Show an image on a window with the given title.
	 * 
	 * @param image
	 * @param windowName
	 */
	public void show(Mat image, String windowName) {
		show(image, windowName, false);
	}

	/**
	 * Show an image on a window with the given title.
	 * 
	 * @param image
	 * @param windowName
	 */
	public void show(BufferedImage image, String windowName) {
		show(image, windowName, false);
	}

	/**
	 * Show an image on a window with the given title.
	 * 
	 * @param image
	 * @param windowName
	 * @param resize
	 */
	public void show(Mat image, String windowName, boolean resize) {
		setSystemLookAndFeel();
		JFrame frame = createJFrame(windowName);
		Image loadedImage = toBufferedImage(image);
		imageView.setIcon(new ImageIcon(loadedImage));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	/**
	 * Show an image on a window with the given title.
	 * 
	 * @param image
	 * @param windowName
	 * @param resize
	 */
	public void show(BufferedImage image, String windowName, boolean resize) {
		setSystemLookAndFeel();
		JFrame frame = createJFrame(windowName);
		imageView.setIcon(new ImageIcon(image));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	/**
	 * Creates a frame for a window.
	 * 
	 * @param windowName
	 * @return A frame for a window (jFrame).
	 */
	private JFrame createJFrame(String windowName) {
		JFrame frame = new JFrame(windowName);
		imageView = new JLabel();
		final JScrollPane imageScrollPane = new JScrollPane(imageView);
		imageScrollPane.setPreferredSize(new Dimension(640, 480));
		frame.add(imageScrollPane, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		return frame;
	}

	private void setSystemLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Converts Mat to BufferedImage.
	 * 
	 * @param matrix
	 * @param resize
	 * @return BufferedImage
	 */
	public static BufferedImage toBufferedImage(Mat matrix) {
		int type = (matrix.channels() > 1) ? BufferedImage.TYPE_3BYTE_BGR : BufferedImage.TYPE_BYTE_GRAY;

		int bufferSize = matrix.channels() * matrix.cols() * matrix.rows();
		byte[] buffer = new byte[bufferSize];
		matrix.get(0, 0, buffer); // get all the pixels
		BufferedImage image = new BufferedImage(matrix.cols(), matrix.rows(), type);
		final byte[] targetPixels = toBytes(image);
		System.arraycopy(buffer, 0, targetPixels, 0, buffer.length);
		return image;
	}

	/**
	 * Converts BufferedImage to Mat.
	 * @param image
	 * @return Mat
	 */
	public static Mat toMat(BufferedImage image) {
		Mat matImg = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
		matImg.put(0, 0, toBytes(image));
		return matImg;
	}
	
	public static byte[] toBytes(BufferedImage image) {
		return ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
	}
	
	public static byte[] toBytes(Mat image) {
		return toBytes(toBufferedImage(image.clone()));
	}
	
	public static BufferedImage toBufferedImage(byte[] imageData) {
	    ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
	    BufferedImage image = null;
	    try {
	        image = ImageIO.read(bais);
	        new ImageProcessor().show(ImageIO.read(bais));
	    } catch (IOException e) {
	        Error.report(e.getMessage());
	    }
	    return image;
	}
	
	public static Mat toMat(byte[] imageData) {
		return toMat(toBufferedImage(imageData));
	}

	/**
	 * Resize an image to the given size.
	 * @param image
	 * @param width
	 * @param height
	 */
	public static void resize(Mat image, double width, double height) {
		Size newSize = new Size(width, height);
		Imgproc.resize(image, image, newSize);
	}

	/**
	 * Resize an image's width
	 * @param image - image to be resized
	 * @param width - desired width
	 * @param keepProportions - true to keep height proportion, false to keep original height
	 */
	public static void resizeWidth(Mat image, double width, boolean keepProportions) {
		if (image.size().width != width) {
			double height = (keepProportions) ? (width / image.size().width) * image.size().height : image.size().height;
			resize(image, width, height);
		}
	}

	/**
	 * Resize an image's height.
	 * @param image - image to be resized
	 * @param height - desired height
	 * @param keepProportions - true to keep width proportion, false to keep original width
	 */
	public static void resizeHeight(Mat image, double height, boolean keepProportions) {
		if (image.size().height != height) {
			double width = (keepProportions) ? (height / image.size().height) * image.size().width : image.size().width;
			resize(image, width, height);
		}
	}
	
	public static void toGray(Mat image) {
		Imgproc.cvtColor(image, image, Imgproc.COLOR_RGB2GRAY);
	}
	
	/**
     * Decode string to image
     * @param imageString The string to decode
     * @return decoded image
     */
    public static BufferedImage toBufferedImage(String base64String) {
        BufferedImage image = null;
        try {
        	byte[] imageByte = Base64.decodeBase64(base64String);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            bis.close();
        } catch (Exception e) {
            Error.report(e.getMessage());
        }
        return image;
    }
    
    public static byte[] toByteArray(String base64String) {
    	return Base64.decodeBase64(base64String);
    }

    /**
     * Encode image to string
     * @param image The image to encode
     * @param type jpeg, bmp, ...
     * @return encoded string
     */
    public static String toBase64String(BufferedImage image) {
		byte[] imageData = toBytes(image);								//Convert to byte array
		String base64String = Base64.encodeBase64String(imageData);		//Encode to base 64 string
        return base64String;
    }
}