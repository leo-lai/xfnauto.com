package main.com.utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

/**
 * * @author WQ * @date 2011-01-14 * @versions 1.0 图片压缩工具类 提供的方法中可以设定生成的
 * 缩略图片的大小尺寸等
 */
public class ImageUtil {
	/** * 图片文件读取 * * @param srcImgPath * @return */
	private static BufferedImage InputImage(String srcImgPath) {
		BufferedImage srcImage = null;
		try {
			FileInputStream in = new FileInputStream(srcImgPath);
			srcImage = javax.imageio.ImageIO.read(in);
		} catch (IOException e) {
			System.out.println("读取图片文件出错！" + e.getMessage());
			e.printStackTrace();
		}
		return srcImage;
	}

	public static void main(String args[]) {
//		try {
//			Map<Integer, String> map = readfile("E:/yuan", null);
//			for (int i = 0; i < map.size(); i++) {
//				System.out.println(map.get(i) + " ==" + i);
//				System.out.println();
//				String oldpath = map.get(i);
//				compressImage(map.get(i), "E:/ww/_" + i + ".png", 100, 75);
//			}
//		} catch (Exception ex) {
//		}
//		compressImage("C:/privace/IMG.jpg", "C:/privace/IMG1.jpg",700,1200);
//		compressImage("C:/privace/IMG.jpg", "C:/privace/IMG2.jpg",640,288);
//		System.out.println("ok");
//		changeimageSize("C:\\cut\\121.png",600,192);//原图：600*192
		try {
			syntheticImage("C:\\privace\\white.png","C:\\privace\\logo.png");
		} catch (Exception e) {
			e.printStackTrace();
		}//
		System.out.println("DOME");
	}

	/**
	 * * 将图片按照指定的图片尺寸压缩 * * @param srcImgPath :源图片路径 * @param outImgPath *
	 * :输出的压缩图片的路径 * @param new_w * :压缩后的图片宽 * @param new_h * :压缩后的图片高
	 */
	public static void compressImage(String srcImgPath, String outImgPath,
			int new_w, int new_h) {
		BufferedImage src = InputImage(srcImgPath);
		disposeImage(src, outImgPath, new_w, new_h);
	}

	/**
	 * * 指定长或者宽的最大值来压缩图片 * * @param srcImgPath * :源图片路径 * @param outImgPath *
	 * :输出的压缩图片的路径 * @param maxLength * :长或者宽的最大值
	 */
	public static void compressImage(String srcImgPath, String outImgPath,
			int maxLength) {
		// 得到图片
		BufferedImage src = InputImage(srcImgPath);
		if (null != src) {
			int old_w = src.getWidth();
			// 得到源图宽
			int old_h = src.getHeight();
			// 得到源图长
			int new_w = 0;
			// 新图的宽
			int new_h = 0;
			// 新图的长
			// 根据图片尺寸压缩比得到新图的尺寸
			if (old_w > old_h) {
				// 图片要缩放的比例
				new_w = maxLength;
				new_h = (int) Math.round(old_h * ((float) maxLength / old_w));
			} else {
				new_w = (int) Math.round(old_w * ((float) maxLength / old_h));
				new_h = maxLength;
			}
			disposeImage(src, outImgPath, new_w, new_h);
		}
	}

	/** * 处理图片 * * @param src * @param outImgPath * @param new_w * @param new_h */
	private synchronized static void disposeImage(BufferedImage src,
			String outImgPath, int new_w, int new_h) {
		// 得到图片
		int old_w = src.getWidth();
		// 得到源图宽
		int old_h = src.getHeight();
		// 得到源图长
		BufferedImage newImg = null;
		// 判断输入图片的类型
		switch (src.getType()) {
		case 13:
			// png,gifnewImg = new BufferedImage(new_w, new_h,
			// BufferedImage.TYPE_4BYTE_ABGR);
			break;
		default:
			newImg = new BufferedImage(new_w, new_h, BufferedImage.TYPE_INT_RGB);
			break;
		}
		Graphics2D g = newImg.createGraphics();
		// 从原图上取颜色绘制新图
		g.drawImage(src, 0, 0, old_w, old_h, null);
		g.dispose();
		// 根据图片尺寸压缩比得到新图的尺寸
		newImg.getGraphics().drawImage(
				src.getScaledInstance(new_w, new_h, Image.SCALE_SMOOTH), 0, 0,
				null);
		// 调用方法输出图片文件
		OutImage(outImgPath, newImg);
	}

	/**
	 * * 将图片文件输出到指定的路径，并可设定压缩质量 * * @param outImgPath * @param newImg * @param
	 * per
	 */
	private static void OutImage(String outImgPath, BufferedImage newImg) {
		// 判断输出的文件夹路径是否存在，不存在则创建
		File file = new File(outImgPath);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}// 输出到文件流
		try {
			ImageIO.write(newImg,
					outImgPath.substring(outImgPath.lastIndexOf(".") + 1),
					new File(outImgPath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Map<Integer, String> readfile(String filepath,
			Map<Integer, String> pathMap) throws Exception {
		if (pathMap == null) {
			pathMap = new HashMap<Integer, String>();
		}

		File file = new File(filepath);
		// 文件
		if (!file.isDirectory()) {
			pathMap.put(pathMap.size(), file.getPath());

		} else if (file.isDirectory()) { // 如果是目录， 遍历所有子目录取出所有文件名
			String[] filelist = file.list();
			for (int i = 0; i < filelist.length; i++) {
				File readfile = new File(filepath + "/" + filelist[i]);
				if (!readfile.isDirectory()) {
					pathMap.put(pathMap.size(), readfile.getPath());

				} else if (readfile.isDirectory()) { // 子目录的目录
					readfile(filepath + "/" + filelist[i], pathMap);
				}
			}
		}
		return pathMap;
	}
	
	/** 
     * 改变图片的尺寸
     *  
     * @param source 
     *            源文件 
     * @param targetW 
     *            目标长 
     * @param targetH 
     *            目标宽 
     */  
    public static BufferedImage resize(String imgPath, int targetW, int targetH) throws IOException  //BufferedImage source
    {  
    	BufferedImage source = ImageIO.read(new File(imgPath));  
        int type = source.getType();  
        BufferedImage target = null;  
        double sx = (double) targetW / source.getWidth();  
        double sy = (double) targetH / source.getHeight();  
        // 这里想实现在targetW，targetH范围内实现等比缩放。如果不需要等比缩放  
        // 则将下面的if else语句注释即可  
        if (sx > sy)  
        {  
            sx = sy;  
            targetW = (int) (sx * source.getWidth());  
        }  
        else  
        {  
            sy = sx;  
            targetH = (int) (sy * source.getHeight());  
        }  
        if (type == BufferedImage.TYPE_CUSTOM)  
        { // handmade  
            ColorModel cm = source.getColorModel();  
            WritableRaster raster = cm.createCompatibleWritableRaster(targetW,  
                    targetH);  
            boolean alphaPremultiplied = cm.isAlphaPremultiplied();  
            target = new BufferedImage(cm, raster, alphaPremultiplied, null);  
        }  
        else  
        {  
            //固定宽高，宽高一定要比原图片大  
            //target = new BufferedImage(targetW, targetH, type);  
            target = new BufferedImage(800, 600, type);  
        }  
          
        Graphics2D g = target.createGraphics();  
          
        //写入背景  
        g.drawImage(ImageIO.read(new File("ok/blank.png")), 0, 0, null);  
          
        // smoother than exlax:  
        g.setRenderingHint(RenderingHints.KEY_RENDERING,  
                RenderingHints.VALUE_RENDER_QUALITY);  
        g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));  
        g.dispose();  
        return target;  
    }
    
    /**
     * 把图片改写到指定尺寸
     * @param imagePath
     * @param width
     * @param height
     */
    public static void changeimageSize(String imagePath,int width,int height){
    	try {
			File imagefile = new File(imagePath);
			BufferedImage bimg = ImageIO.read(imagefile);
			Image image = bimg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			BufferedImage target = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
			Graphics g = target.getGraphics();  
			g.drawImage(image, 0, 0, null);
			g.dispose(); 
			ImageIO.write(target, "jpg", new File(imagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * 把指定尺寸之外的图片改写到指定尺寸
     * judge参数只为实现 重载而已，没有实际意义
     * @param imagePath
     * @param width
     * @param height
     * @param judge
     */
    public static void changeimageSize(String imagePath,int width,int height,String judge){
    	try {
			File imagefile = new File(imagePath);
//			BufferedImage bufferedImage = ImageIO.read(dirpathFile);   
//	    	int readwidth = bufferedImage.getWidth();   
//	    	int readheight = bufferedImage.getHeight();
			BufferedImage bimg = ImageIO.read(imagefile);
			//获取真实的高宽
			int realwidth = bimg.getWidth();   
	    	int realheight = bimg.getHeight();
	    	if(realwidth>width || realheight>height || width>realwidth || height>realheight){//标准尺寸之外
	    		Image image = bimg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
				BufferedImage target = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
				Graphics g = target.getGraphics();  
				g.drawImage(image, 0, 0, null);
				g.dispose(); 
				ImageIO.write(target, "jpg", new File(imagePath));
	    	}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * 把图片 file 转为 byte
     * @param filePath
     * @return
     */
    public static byte[] FileTobyte(File file)  
    {  
        byte[] buffer = null;  
        try 
        {  
            FileInputStream fis = new FileInputStream(file);  
            ByteArrayOutputStream bos = new ByteArrayOutputStream();  
            byte[] b = new byte[1024];  
            int n;  
            while ((n = fis.read(b)) != -1)  
            {  
                bos.write(b, 0, n);  
            }  
            fis.close();  
            bos.close();  
            buffer = bos.toByteArray();  
        }  
        catch (FileNotFoundException e)  
        {  
            e.printStackTrace();  
        }  
        catch (IOException e)  
        {  
            e.printStackTrace();  
        }  
        return buffer;  
    }
    
    /**
     * 把图片 file 转为 byte
     * @param filePath
     * @return
     */
    public static byte[] FileTobyte(String path)  
    {  
    	byte[] buffer = null;  
    	try 
    	{  
    		File file = new File(path);
    		FileInputStream fis = new FileInputStream(file);  
    		ByteArrayOutputStream bos = new ByteArrayOutputStream();  
    		byte[] b = new byte[fis.available()];  
    		int n;
    		while ((n = fis.read(b)) != -1)  
    		{  
    			bos.write(b, 0, n);  
    		}  
    		fis.close();  
    		bos.close();  
    		buffer = bos.toByteArray();  
    	}  
    	catch (FileNotFoundException e)  
    	{  
    		e.printStackTrace();  
    	}  
    	catch (IOException e)  
    	{  
    		e.printStackTrace();  
    	}  
    	return buffer;  
    }
    

    /**
     * 图片合成
     * @param maxPath
     * @param minPath
     */
    public static void syntheticImage(String maxPath,String minPath)throws Exception{
    	 try {
			InputStream maxImage=new FileInputStream(maxPath);
			 InputStream minImage=new FileInputStream(minPath);

			 BufferedImage maximage = ImageIO.read(maxImage);
			 BufferedImage minimage = ImageIO.read(minImage);
			 Graphics g=maximage.getGraphics();
			 
			 //图片居中计算
			 int maxWidth = maximage.getWidth();//原图宽
			 int maxHeight = maximage.getHeight();//原图宽
			 int minWidth = minimage.getWidth();//原图宽
			 int minHeight = minimage.getHeight();//原图宽
			 int width_x = (maxWidth - minWidth)/2;//X轴坐标
			 int height_y = (maxHeight - minHeight)/2;//Y轴坐标
//			 minimage.get

//			 int[] ImageArrayOne = new int[minWidth*minHeight];
//		     ImageArrayOne = minimage.getRGB(0,0,minWidth,minHeight,ImageArrayOne,0,minWidth);
//		     for(int i:ImageArrayOne){
//		    	 System.out.println(i);
//		     }
//			 g.setColor();
			 g.drawImage(minimage,width_x,height_y,minWidth,minHeight,null);
//			 OutputStream outImage=new FileOutputStream(minPath);
//			 JPEGImageEncoder enc=JPEGCodec.createJPEGEncoder(outImage);
//			 enc.encode(maximage);
			 String desired = minPath.substring(minPath.lastIndexOf(".") + 1);
			 ImageIO.write(maximage, /*"GIF"*/ desired /* format desired "图片后缀"*/ , new File(minPath) /* target */ );
			 maxImage.close();
			 minImage.close();
//			 outImage.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * 图片删除
     * @param path
     */
    public static void deleteFile(String path){
    	File file= new File(path);
        if(!file.isDirectory()){
        	file.delete();
        }
    }
}
