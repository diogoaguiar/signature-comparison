package middleware;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import org.apache.tomcat.util.codec.binary.Base64;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class Img {

	private BufferedImage image;
	private String name;

	// Constructors
	public Img() {
	}

	public Img(byte[] bytes) throws IOException {
		setImageByBytes(bytes);
	}

	public Img(BufferedImage bufImg) {
		setImageByBufferedImage(bufImg);
	}

	public Img(String base64) throws IOException {
		setImageByBase64(base64);
	}

	public Img(Mat matImg) {
		setImageByMat(matImg);
	}

	// Image setters
	public void setImageByPath(String path) throws IOException {
		image = ImageIO.read(new File(path));
	}

	public void setImageByBytes(byte[] imgBytes) throws IOException {
		image = ImageIO.read(new ByteArrayInputStream(imgBytes));
	}

	public void setImageByBase64(String base64) throws IOException {
		byte[] imgBytes = Base64.decodeBase64(base64);
		image = ImageIO.read(new ByteArrayInputStream(imgBytes));
	}

	public void setImageByBufferedImage(BufferedImage bufImg) {
		ColorModel cm = bufImg.getColorModel();
		boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		WritableRaster raster = bufImg.copyData(null);
		image = new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}

	public void setImageByMat(Mat matImg) {
		// Define image type (RGB or gray scale)
		int type = (matImg.channels() > 1) ? BufferedImage.TYPE_3BYTE_BGR : BufferedImage.TYPE_BYTE_GRAY;

		// Get data from Mat image
		byte[] data = new byte[matImg.channels() * matImg.cols() * matImg.rows()];
		matImg.get(0, 0, data);

		// Create BufferedImage with the data
		image = new BufferedImage(matImg.cols(), matImg.rows(), type);
		image.getRaster().setDataElements(0, 0, matImg.cols(), matImg.rows(), data);
	}

	// Image getters
	public byte[] getBytes() {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(getBufferedImage(), "jpg", baos);
			byte[] imgBytes = baos.toByteArray();
			return imgBytes;
		} catch (IOException e) {
			Logger.error(e.getMessage());
			return null;
		}
	}

	public String getBase64() {
		byte[] imgBytes = getBytes();
		return Base64.encodeBase64String(imgBytes); // Encode to base 64 string
	}

	public Mat getMat() {
		Mat matImg = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
		matImg.put(0, 0, getData());
		return matImg;
	}

	public BufferedImage getBufferedImage() {
		ColorModel cm = image.getColorModel();
		boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		WritableRaster raster = image.copyData(null);
		return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}

	// Methods
	public Img clone() {
		Img cloneImg = new Img(image);
		cloneImg.setName(cloneImg.getName());
		return cloneImg;

	}

	public void show() {
		// Look and feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			Logger.error(e.getMessage());
			return;
		}

		// Frame
		JFrame frame = new JFrame(name);
		JLabel imageView = new JLabel();
		final JScrollPane imageScrollPane = new JScrollPane(imageView);
		imageScrollPane.setPreferredSize(new Dimension(image.getWidth() + 2, image.getHeight() + 2));
		frame.add(imageScrollPane, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// Show image
		imageView.setIcon(new ImageIcon(getBufferedImage()));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public boolean empty() {
		return (image == null) ? true : false;
	}

	public int getWidth() {
		return image.getWidth();
	}

	public int getHeight() {
		return image.getHeight();
	}

	public Img resize(int newW, int newH) {
		Image tmp = image.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
		BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_3BYTE_BGR);

		Graphics2D g2d = dimg.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();

		Img resizedImg = clone();
		resizedImg.setImageByBufferedImage(dimg);

		return resizedImg;
	}

	public Img resizeWidth(int width, boolean keepProportions) {
		if (image.getWidth() != width) {
			int height = (keepProportions) ? (int) (((double) width / image.getWidth()) * image.getHeight())
					: image.getHeight();
			return resize(width, height);
		} else {
			return clone();
		}
	}

	public Img resizeHeight(int height, boolean keepProportions) {
		if (image.getHeight() != height) {
			int width = (keepProportions) ? (int) (((double) height / image.getHeight()) * image.getWidth())
					: image.getWidth();
			return resize(width, height);
		} else {
			return clone();
		}
	}

	// Auxiliar
	private byte[] getData() {
		return ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
	}

	// Other methods
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
