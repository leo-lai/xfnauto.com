package main.com.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

public final class ImgSyntheticWord {

	/**
	 * 图图合成方法
	 * 
	 * @param word
	 *            要合成的文字
	 * @param img
	 *            合成小图
	 * @param backgroundImg
	 *            合成大图
	 * @param type
	 *            终端
	 * @param coordinates_X
	 *            起点坐标x
	 * @param coordinates_Y
	 *            起点坐标y
	 * @param wideth
	 *            内框宽度，默认为410
	 * @param heig
	 *            内框高度，默认为530
	 * @return 返回提示
	 * @throws IOException
	 */
	public static String syntheticImgAndImgAndFont(String fristPath,String minPath,String maxPath,String userName)
			throws Exception {
		InputStream imagein = null;
		InputStream imagein2 = null;
		InputStream imgBlank = null;
		OutputStream out = null;
		
		Integer coordinates_X = 0;
		Integer coordinates_Y = 0;
		Integer wideth = 0;
		Integer heig = 0; 
		Integer iw_Coordinates_X = 0;
		Integer iw_Coordinates_Y = 0;
		try {
			//把小图设置为300*300
			ImageUtil.compressImage(minPath, minPath, 300,300);
			
			String savePath = fristPath + "compose/"+StringCode.getOtherCodes(6,"1")+".jpg";
			imagein = new FileInputStream(minPath);
			imagein2 = new FileInputStream(maxPath);
//	    File file = new File(savePath);
//	    if (!file.exists()) {
//            File dirFile = new File(savePath);  
//            dirFile.mkdirs();  
//        } 
//	    imgBlank = new FileInputStream(savePath);
			
			// 图片合成处理
			BufferedImage image = ImageIO.read(imagein);
			BufferedImage image2 = ImageIO.read(imagein2);
			if (image == null || image2 == null) {
				return "error";
			}
			
			File _file2 = new File(maxPath);
			File _file1 = new File(minPath);
			Image src = javax.imageio.ImageIO.read(_file2);
			int width = src.getWidth(null); // 得到源图宽
			int height = src.getHeight(null); // 得到源長寬
			Image src1 = javax.imageio.ImageIO.read(_file1);
			int width1 = src1.getWidth(null); // 得到源图宽
			int height1 = src1.getHeight(null); // 得到源長寬
			
//	    if (wideth == null || wideth <= 0) {
//		if ("phone".equals(type)) {
			wideth = 1334;// 设置默认宽度
//		} else {
//		    wideth = 410;// 设置默认宽度
//		}
//	    }
			
			if (heig == null || heig <= 0) {
				heig = 750;// 设置默认高度度
			}
//	    int original = height1; // 保存原图宽
//	    Double proportion = (double) width1 / height1; // 源图片宽高比例
//	    height1 = Integer.parseInt(new java.text.DecimalFormat("0")
//		    .format(wideth / proportion));// 按比例得到高
			
			iw_Coordinates_X = Integer
					.parseInt(new java.text.DecimalFormat("0")
					.format(235));
			iw_Coordinates_Y = Integer
					.parseInt(new java.text.DecimalFormat("0")
					.format(710));
			
//	    if (coordinates_X == null || coordinates_Y == null) {// 如果坐标出入为空,设置默认起始坐标
//		coordinates_X = Integer.parseInt(new java.text.DecimalFormat(
//			"0").format(width / 4.3));
//		coordinates_Y = Integer.parseInt(new java.text.DecimalFormat(
//			"0").format(height / 4));
//	    }
			
			// 设置画笔
			BufferedImage tag = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			// 创建标准画笔
			Graphics2D g = tag.createGraphics();
			// 设置三原色
			g.setColor(new Color(255, 255, 255));
			
//	    g.drawImage(imageBlank, coordinates_X, coordinates_Y, width, heig,
//		    null);// 画白背景
			g.drawImage(image2, 0, 0, width, height, null);// 画大图
			
			g.drawImage(image, 225, 714, width1, height1,
					null);// 画小图
			
			//给图片添加文字
			if(StringUtil.isNotEmpty(userName)){
//				 int fontsize = userName.length();  
				//设置字体颜色
			    Font font = new Font("黑体", Font.PLAIN, 30);// 添加字体的属性设置  
			    g.setFont(font);
			    g.setColor(Color.BLACK);//设置字体颜色  
//			    g.drawString(userName, 255, 1120);
//			    g.drawString(new String(userName.getBytes("gbk"),"utf-8"), 255, 1120);
//			    g.drawString(new String(userName.getBytes("iso-8859-1"),"GBK"), 255, 1120);
//			    g.drawString(new String(userName.getBytes("UTF-8"),"GBK"), 255, 1120);
			}
//		     this.font = new Font(fontStyle, Font.PLAIN, fontSize);
			
//			out = new FileOutputStream(savePath);// 图片合成后保存路径
//			JPEGImageEncoder enc = JPEGCodec.createJPEGEncoder(out);
//			enc.encode(tag);
			ImageIO.write(tag, /*"GIF"*/ "图片后缀" /* format desired */ , new File(savePath) /* target */ );
			
			return savePath;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			imagein.close();
			imagein2.close();
			if (out != null)
				out.close();
			
		}
		return "error";
	}
	

	/**
	 * 图图合成方法
	 * 
	 * @param word
	 *            要合成的文字
	 * @param img
	 *            合成小图
	 * @param backgroundImg
	 *            合成大图
	 * @param type
	 *            终端
	 * @param coordinates_X
	 *            起点坐标x
	 * @param coordinates_Y
	 *            起点坐标y
	 * @param wideth
	 *            内框宽度，默认为410
	 * @param heig
	 *            内框高度，默认为530
	 * @return 返回提示
	 * @throws IOException
	 */
	public static String syntheticImgAndImgQR(String fristPath,String minPath,String maxPath)
			throws Exception {
		InputStream imagein = null;
		InputStream imagein2 = null;
		InputStream imgBlank = null;
		OutputStream out = null;
		
		Integer coordinates_X = 0;
		Integer coordinates_Y = 0;
		Integer wideth = 0;
		Integer heig = 0; 
		Integer iw_Coordinates_X = 0;
		Integer iw_Coordinates_Y = 0;
		try {
			//把小图设置为300*300
//			ImageUtil.compressImage(minPath, minPath, 300,300);
			
			String savePath = fristPath + "/"+StringCode.getOtherCodes(6,"1")+".jpg";
			imagein = new FileInputStream(minPath);
			imagein2 = new FileInputStream(maxPath);
//	    File file = new File(savePath);
//	    if (!file.exists()) {
//            File dirFile = new File(savePath);  
//            dirFile.mkdirs();  
//        } 
//	    imgBlank = new FileInputStream(savePath);
			
			// 图片合成处理
			BufferedImage image = ImageIO.read(imagein);
			BufferedImage image2 = ImageIO.read(imagein2);
			if (image == null || image2 == null) {
				return "error";
			}
			
			File _file2 = new File(maxPath);
			File _file1 = new File(minPath);
			Image src = javax.imageio.ImageIO.read(_file2);
			int width = src.getWidth(null); // 得到源图宽
			int height = src.getHeight(null); // 得到源長寬
			Image src1 = javax.imageio.ImageIO.read(_file1);
			int width1 = src1.getWidth(null); // 得到源图宽
			int height1 = src1.getHeight(null); // 得到源長寬
			
//	    if (wideth == null || wideth <= 0) {
//		if ("phone".equals(type)) {
//			wideth = 1334;// 设置默认宽度
//		} else {
//		    wideth = 410;// 设置默认宽度
//		}
//	    }
			
			if (heig == null || heig <= 0) {
				heig = 430;// 设置默认高度度
			}
//	    int original = height1; // 保存原图宽
//	    Double proportion = (double) width1 / height1; // 源图片宽高比例
//	    height1 = Integer.parseInt(new java.text.DecimalFormat("0")
//		    .format(wideth / proportion));// 按比例得到高
			
			iw_Coordinates_X = Integer
					.parseInt(new java.text.DecimalFormat("0")
					.format(235));
			iw_Coordinates_Y = Integer
					.parseInt(new java.text.DecimalFormat("0")
					.format(710));
			
//	    if (coordinates_X == null || coordinates_Y == null) {// 如果坐标出入为空,设置默认起始坐标
//		coordinates_X = Integer.parseInt(new java.text.DecimalFormat(
//			"0").format(width / 4.3));
//		coordinates_Y = Integer.parseInt(new java.text.DecimalFormat(
//			"0").format(height / 4));
//	    }
			
			// 设置画笔
			BufferedImage tag = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			// 创建标准画笔
			Graphics2D g = tag.createGraphics();
			// 设置三原色
			g.setColor(new Color(255, 255, 255));
			
//	    g.drawImage(imageBlank, coordinates_X, coordinates_Y, width, heig,
//		    null);// 画白背景
			g.drawImage(image2, 0, 0, width, height, null);// 画大图
			
			g.drawImage(image, 165, 165, width1, height1,
					null);// 画小图
						
//			out = new FileOutputStream(savePath);// 图片合成后保存路径
//			JPEGImageEncoder enc = JPEGCodec.createJPEGEncoder(out);
//			enc.encode(tag);
			ImageIO.write(tag, /*"GIF"*/ "图片后缀" /* format desired */ , new File(savePath) /* target */ );
			
			return savePath;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			imagein.close();
			imagein2.close();
			if (out != null)
				out.close();
			
		}
		return "error";
	}
	
    /**
     * 图图合成方法
     * 
     * @param word
     *            要合成的文字
     * @param img
     *            合成小图
     * @param backgroundImg
     *            合成大图
     * @param type
     *            终端
     * @param coordinates_X
     *            起点坐标x
     * @param coordinates_Y
     *            起点坐标y
     * @param wideth
     *            内框宽度，默认为410
     * @param heig
     *            内框高度，默认为530
     * @return 返回提示
     * @throws IOException
     */
    public static String syntheticImgAndImg(String fristPath,String minPath,String maxPath)
	    throws Exception {
	InputStream imagein = null;
	InputStream imagein2 = null;
	InputStream imgBlank = null;
	OutputStream out = null;

	Integer coordinates_X = 0;
	Integer coordinates_Y = 0;
    Integer wideth = 0;
    Integer heig = 0; 
    Integer iw_Coordinates_X = 0;
    Integer iw_Coordinates_Y = 0;
	try {
		//把小图设置为300*300
		ImageUtil.compressImage(minPath, minPath, 300,300);
		
		String savePath = fristPath + "compose/"+StringCode.getOtherCodes(6,"1")+".jpg";
	    imagein = new FileInputStream(minPath);
	    imagein2 = new FileInputStream(maxPath);
//	    File file = new File(savePath);
//	    if (!file.exists()) {
//            File dirFile = new File(savePath);  
//            dirFile.mkdirs();  
//        } 
//	    imgBlank = new FileInputStream(savePath);
	    
	    // 图片合成处理
	    BufferedImage image = ImageIO.read(imagein);
	    BufferedImage image2 = ImageIO.read(imagein2);
	    if (image == null || image2 == null) {
		return "error";
	    }

	    File _file2 = new File(maxPath);
	    File _file1 = new File(minPath);
	    Image src = javax.imageio.ImageIO.read(_file2);
	    int width = src.getWidth(null); // 得到源图宽
	    int height = src.getHeight(null); // 得到源長寬
	    Image src1 = javax.imageio.ImageIO.read(_file1);
	    int width1 = src1.getWidth(null); // 得到源图宽
	    int height1 = src1.getHeight(null); // 得到源長寬

//	    if (wideth == null || wideth <= 0) {
//		if ("phone".equals(type)) {
		    wideth = 1334;// 设置默认宽度
//		} else {
//		    wideth = 410;// 设置默认宽度
//		}
//	    }

	    if (heig == null || heig <= 0) {
		heig = 750;// 设置默认高度度
	    }
//	    int original = height1; // 保存原图宽
//	    Double proportion = (double) width1 / height1; // 源图片宽高比例
//	    height1 = Integer.parseInt(new java.text.DecimalFormat("0")
//		    .format(wideth / proportion));// 按比例得到高
	    
	    iw_Coordinates_X = Integer
			    .parseInt(new java.text.DecimalFormat("0")
				    .format(235));
		    iw_Coordinates_Y = Integer
			    .parseInt(new java.text.DecimalFormat("0")
				    .format(710));

//	    if (coordinates_X == null || coordinates_Y == null) {// 如果坐标出入为空,设置默认起始坐标
//		coordinates_X = Integer.parseInt(new java.text.DecimalFormat(
//			"0").format(width / 4.3));
//		coordinates_Y = Integer.parseInt(new java.text.DecimalFormat(
//			"0").format(height / 4));
//	    }

	    // 设置画笔
	    BufferedImage tag = new BufferedImage(width, height,
		    BufferedImage.TYPE_INT_RGB);
	    // 创建标准画笔
	    Graphics2D g = tag.createGraphics();
	    // 设置三原色
	    g.setColor(new Color(255, 255, 255));

//	    g.drawImage(imageBlank, coordinates_X, coordinates_Y, width, heig,
//		    null);// 画白背景
	    g.drawImage(image2, 0, 0, width, height, null);// 画大图

	    g.drawImage(image, 225, 714, width1, height1,
		    null);// 画小图

//		out = new FileOutputStream(savePath);// 图片合成后保存路径
//		JPEGImageEncoder enc = JPEGCodec.createJPEGEncoder(out);
//		enc.encode(tag);
		ImageIO.write(tag, /*"GIF"*/ "图片后缀" /* format desired */ , new File(savePath) /* target */ );

	    return savePath;
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    imagein.close();
	    imagein2.close();
	    if (out != null)
		out.close();

	}
	return "error";
    }
    
    /**
     * 图文合成方法
     * 
     * @param word
     *            要合成的文字
     * @param img
     *            合成小图
     * @param backgroundImg
     *            合成大图
     * @param type
     *            终端
     * @param coordinates_X
     *            起点坐标x
     * @param coordinates_Y
     *            起点坐标y
     * @param wideth
     *            内框宽度，默认为410
     * @param heig
     *            内框高度，默认为530
     * @return 返回提示
     * @throws IOException
     */
    public static String SyntheticImgAndWord(String word, String img,
    		String type, Integer coordinates_X, Integer coordinates_Y,
    		Integer wideth, Integer heig, Integer iw_Coordinates_X,
    		Integer iw_Coordinates_Y, HttpServletRequest request, String person)
    				throws Exception {
    	
    	String rootPaht = request.getSession().getServletContext()
    			.getRealPath("/")
    			+ "res/";
    	String backgroundImg = rootPaht + "images/share/pc_" + person + ".png";
    	if (type.equals("phone")) {
    		backgroundImg = rootPaht + "images/share/mobile_" + person + ".png";
    	}
    	
    	if ("".equals(img) && img == null) {
    		return "合成小图路径错误";
    	}
    	if ("".equals(backgroundImg) && backgroundImg == null) {
    		return "合成大图路径错误";
    	}
    	InputStream imagein = null;
    	InputStream imagein2 = null;
    	InputStream imgBlank = null;
    	OutputStream out = null;
    	
    	try {
    		System.out.println(rootPaht + img);
    		imagein = new FileInputStream(rootPaht + img);
    		imagein2 = new FileInputStream(backgroundImg);
    		imgBlank = new FileInputStream(rootPaht + "images/share/blank.jpg");
    		
    		// 图片合成处理
    		BufferedImage image = ImageIO.read(imagein);
    		BufferedImage image2 = ImageIO.read(imagein2);
    		BufferedImage imageBlank = ImageIO.read(imgBlank);
    		if (image == null || image2 == null) {
    			return "error";
    		}
    		
    		File _file2 = new File(backgroundImg);
    		File _file1 = new File(rootPaht + img);
    		Image src = javax.imageio.ImageIO.read(_file2);
    		int width = src.getWidth(null); // 得到源图宽
    		int height = src.getHeight(null); // 得到源長寬
    		Image src1 = javax.imageio.ImageIO.read(_file1);
    		int width1 = src1.getWidth(null); // 得到源图宽
    		int height1 = src1.getHeight(null); // 得到源長寬
    		
    		if (wideth == null || wideth <= 0) {
    			if ("phone".equals(type)) {
    				wideth = 238;// 设置默认宽度
    			} else {
    				wideth = 410;// 设置默认宽度
    			}
    		}
    		
    		if (heig == null || heig <= 0) {
    			heig = 530;// 设置默认高度度
    		}
    		int original = height1; // 保存原图宽
    		Double proportion = (double) width1 / height1; // 源图片宽高比例
    		height1 = Integer.parseInt(new java.text.DecimalFormat("0")
    		.format(wideth / proportion));// 按比例得到高
    		
    		if ("phone".equals(type)) {
    			if (iw_Coordinates_X == null || iw_Coordinates_Y == null) {// 如果坐标出入为空,设置默认起始坐标
    				iw_Coordinates_X = Integer
    						.parseInt(new java.text.DecimalFormat("0")
    						.format(width / 2.34));
    				iw_Coordinates_Y = Integer
    						.parseInt(new java.text.DecimalFormat("0")
    						.format(height / 2.19));
    			}
    		} else {
    			if (iw_Coordinates_X == null || iw_Coordinates_Y == null) {// 如果坐标出入为空,设置默认起始坐标
    				iw_Coordinates_X = Integer
    						.parseInt(new java.text.DecimalFormat("0")
    						.format(width / 2.34));
    				iw_Coordinates_Y = Integer
    						.parseInt(new java.text.DecimalFormat("0")
    						.format(height / 2.47));
    			}
    		}
    		
    		if (coordinates_X == null || coordinates_Y == null) {// 如果坐标出入为空,设置默认起始坐标
    			coordinates_X = Integer.parseInt(new java.text.DecimalFormat(
    					"0").format(width / 4.3));
    			coordinates_Y = Integer.parseInt(new java.text.DecimalFormat(
    					"0").format(height / 4));
    		}
    		
    		// 设置画笔
    		BufferedImage tag = new BufferedImage(width, height,
    				BufferedImage.TYPE_INT_RGB);
    		// 创建标准画笔
    		Graphics2D g = tag.createGraphics();
    		// 设置三原色
    		g.setColor(new Color(255, 255, 255));
    		
    		g.drawImage(imageBlank, coordinates_X, coordinates_Y, width, heig,
    				null);// 画白背景
    		g.drawImage(image, coordinates_X, coordinates_Y, wideth, height1,
    				null);// 画小图
    		g.drawImage(image2, 0, 0, width, height, null);// 画大图
    		
    		// 画数字图
    		String numFolderUrl = rootPaht + "images/share";// 存放数字图片的目录
    		File numFolder = new File(numFolderUrl);
    		File[] numFiles = numFolder.listFiles();
    		
    		for (int i = 0; i < word.length(); i++) {
    			for (File file : numFiles) {
    				if (file.isFile()
    						&& file.getName().indexOf(word.charAt(i)) != -1) {
    					InputStream numimg = new FileInputStream(numFolderUrl
    							+ File.separator + file.getName());
    					BufferedImage numimage = ImageIO.read(numimg);
    					if ("phone".equals(type)) {
    						g.drawImage(numimage, iw_Coordinates_X,
    								iw_Coordinates_Y, 37, 55, null);// 画数字组图
    						iw_Coordinates_X += 34;
    					} else {
    						g.drawImage(numimage, iw_Coordinates_X,
    								iw_Coordinates_Y, 60, 100, null);// 画数字组图
    						iw_Coordinates_X += 55;
    					}
    					break;
    				}
    			}
    		}
    		// 画百分号
    		InputStream percentimg = new FileInputStream(numFolderUrl
    				+ File.separator + "baifenhao.png");
    		BufferedImage percentimage = ImageIO.read(percentimg);
    		if ("phone".equals(type)) {
    			g.drawImage(percentimage, iw_Coordinates_X, iw_Coordinates_Y,
    					37, 55, null);// 画数字组图
    		} else {
    			g.drawImage(percentimage, iw_Coordinates_X, iw_Coordinates_Y,
    					60, 100, null);// 画数字组图
    		}
    		
    		File sf = new File(rootPaht + "shareImg");
    		if (!sf.exists()) {
    			sf.mkdirs();
    		}
    		String imgPath = "shareImg/" + System.currentTimeMillis()
    				+ "_1.jpg";
//			out = new FileOutputStream(savePath);// 图片合成后保存路径
//			JPEGImageEncoder enc = JPEGCodec.createJPEGEncoder(out);
//			enc.encode(tag);
			ImageIO.write(tag, /*"GIF" 图片输出流*/ "图片后缀" /* format desired */ , new File(imgPath) /* target 保存路径*/ );
    		
    		return imgPath;
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		
    		imagein.close();
    		imagein2.close();
    		imgBlank.close();
    		if (out != null)
    			out.close();
    		
    	}
    	return "error";
    }

    /**
     * 图文合成方法
     * 
     * @param word
     *            要合成的文字
     * @param img
     *            合成小图
     * @param backgroundImg
     *            合成大图
     * @param type
     *            终端类型
     * @param coordinates_X
     *            起点坐标x
     * @param coordinates_Y
     *            起点坐标y
     * @param wideth
     *            内框宽度，默认为410
     * @param heig
     *            内框高度，默认为530
     * @return 返回提示
     * @throws IOException
     */
    /*
     * public static void main(String[] args) { String img =
     * "D:\\workspaces\\ImgWord_Synthetic\\src\\Img\\22.jpg"; String
     * backgroundImg = "D:\\workspaces\\ImgWord_Synthetic\\src\\Img\\A2.png";
     * String pbackgroundImg =
     * "D:\\workspaces\\ImgWord_Synthetic\\src\\Img\\A3.png"; try { //PC端
     * //String type = "pc"; //String back =
     * ImgSyntheticWord.SyntheticImgAndWord("100%", img,
     * backgroundImg,type,null, null, null, null,null, null);
     * 
     * //phone端 String type = "phone"; String back =
     * ImgSyntheticWord.SyntheticImgAndWord("100%", img, type,192, 111,
     * 275,345,309,497); System.out.println(back); } catch (IOException e) {
     * e.printStackTrace(); } }
     */
}
