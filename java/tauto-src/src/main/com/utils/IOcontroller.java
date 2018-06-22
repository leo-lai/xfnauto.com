package main.com.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class IOcontroller {

	private static final Log logger = LogFactory
			.getLog(IOcontroller.class);
	
	public static Boolean write(File file, String str) {
		OutputStream out = null;
		try {
			out = new FileOutputStream(file);
			byte[] bety = str.getBytes("UTF-8");
//			System.out.println("bety.length:"+bety.length);
			out.write(bety);
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			logger.error("when write into the file get error of FileNotFoundException");
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("when write into the file get error of IOException");
			return false;
		} finally {
			if (out != null) {
				try {
					out.flush();
					out.close();
				} catch (IOException e) {
					logger.error("when close the file get error of IOException");
					e.printStackTrace();
				}
			}
			if(file != null){
				file.exists();
			}
		}
	}
	
	public static Boolean addwrite(File file,String str){
		 OutputStream out = null;
			try {
	        out =new FileOutputStream(file,true);
	        byte[] bety=str.getBytes("UTF-8");
	        out.write(bety);
	        out.close();
	        return true;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				logger.error("when write into the file get error of FileNotFoundException");
				return false;
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("when write into the file get error of IOException");
				return false;
			}finally{
				if(out != null){
					try {
						out.flush();
						out.close();
					} catch (IOException e) {
						logger.error("when close the file get error of IOException");
						e.printStackTrace();
					}
				}
				if(file != null){
					file.exists();
				}
			}
	}
}
