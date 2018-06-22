package main.com.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class QRCodeEncoderHandler {

	private static final int BLACK = 200;
	private static final int WHITE = 200;

	private QRCodeEncoderHandler() {
	}

	public static BufferedImage toBufferedImage(BitMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
			}
		}
		return image;
	}

	@SuppressWarnings("unchecked")
	public static String writeToFile(String content, Integer width,
			Integer height, String name, HttpServletRequest request)
			throws Exception {
		MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

		@SuppressWarnings("rawtypes")
		Map hints = new HashMap();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		BitMatrix bitMatrix = multiFormatWriter.encode(content,
				BarcodeFormat.QR_CODE, width, height, hints);
		String imgPath = "image/qrcode/";
		String ctxpath = request.getSession().getServletContext()
				.getRealPath("/")
				+ "res/";
//		File file = new File(path, name + ".jpg");
		String imgName = name + ".jpg";
		String format = "jpg";
		File filePath = new File(ctxpath + imgPath);
		if (!filePath.exists()) {
			filePath.mkdirs();
		}

		File imgFile = new File(ctxpath + imgPath,imgName);
		if (!imgFile.exists()) {
			imgFile.mkdirs();
		}
		BufferedImage image = toBufferedImage(bitMatrix);
		if (!ImageIO.write(image, format, imgFile)) {
			throw new IOException("Could not write an image of format "
					+ format + " to " + imgFile);
		}
		return imgFile.getAbsolutePath();
	}
	
	@SuppressWarnings("unchecked")
	public static String writeToFile(String content, Integer width,
			Integer height, String name)
					throws Exception {
		MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
		
		@SuppressWarnings("rawtypes")
		Map hints = new HashMap();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		BitMatrix bitMatrix = multiFormatWriter.encode(content,
				BarcodeFormat.QR_CODE, width, height, hints);
		String imgPath = "image/qrcode/";
		
		String ctxpath = "C:/workspreace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/useeproject/res/";
//		String ctxpath = request.getSession().getServletContext()
//				.getRealPath("/")
//				+ "res/";
//		File file = new File(path, name + ".jpg");
		String imgName = name + ".jpg";
		String format = "jpg";
		File filePath = new File(ctxpath + imgPath);
		if (!filePath.exists()) {
			filePath.mkdirs();
		}
		
		File imgFile = new File(ctxpath + imgPath,imgName);
		if (!imgFile.exists()) {
			imgFile.mkdirs();
		}
		BufferedImage image = toBufferedImage(bitMatrix);
		if (!ImageIO.write(image, format, imgFile)) {
			throw new IOException("Could not write an image of format "
					+ format + " to " + imgFile);
		}
		return imgFile.getAbsolutePath();
	}
	
   public static void writeToFile(BitMatrix matrix, String format, File file)
	       throws IOException {
	   		judeFileExists(file);
	     BufferedImage image = toBufferedImage(matrix);
	     if (!ImageIO.write(image, format, file)) {
	       throw new IOException("Could not write an image of format " + format + " to " + file);
	     }
	   }

	public static void writeToStream(BitMatrix matrix, String format,
			OutputStream stream) throws IOException {
		BufferedImage image = toBufferedImage(matrix);
		if (!ImageIO.write(image, format, stream)) {
			throw new IOException("Could not write an image of format "
					+ format);
		}
	}

	  // 判断文件是否存在
	     public static void judeFileExists(File file) {
	 
	        if (file.exists()) {
	             System.out.println("file exists");
	        } else {
	             System.out.println("file not exists, create it ...");
	             try {
	                 file.createNewFile();
	             } catch (IOException e) {
	                 // TODO Auto-generated catch block
	                e.printStackTrace();
	             }
	         }
	 
	     }
	 
	     // 判断文件夹是否存在
	     public static void judeDirExists(File file) {
	 
	         if (file.exists()) {
	            if (file.isDirectory()) {
	                 System.out.println("dir exists");
	            } else {
	                 System.out.println("the same name file exists, can not create dir");
	             }
	         } else {
	            System.out.println("dir not exists, create it ...");
	            file.mkdir();
	         }
	 
	     }
	
	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		try {
            
		     String content = "http://www.baidu.com";
		     String path = "C:\\Usee";
		     
		     MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
		     
		     Map hints = new HashMap();
		     hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		     BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 400, 400,hints);
		     File file1 = new File(path,"餐巾纸.jpg");
		     QRCodeEncoderHandler.writeToFile(bitMatrix, "jpg", file1);
		     
		 } catch (Exception e) {
		     e.printStackTrace();
		 }
		
		 try {
			String content = "http://www.baidu.com";
			 String path = "C:\\Usee";
			 
			 MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
			 
			 @SuppressWarnings("rawtypes")
			Map hints = new HashMap();
			 hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			 BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 400, 400,hints);
			 File file = new File(path,"qr.jpg");
			
			 try {
				String path1 = QRCodeEncoderHandler.writeToFile(content, 200, 200, "11");
				System.out.println(path1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (WriterException e) {
			e.printStackTrace();
		}
		System.out.println("encoder QRcode success");
	}

}
