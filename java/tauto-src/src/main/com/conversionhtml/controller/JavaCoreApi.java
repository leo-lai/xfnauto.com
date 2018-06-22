package main.com.conversionhtml.controller;

import java.io.File;
import java.io.FileOutputStream;

import org.fit.cssbox.demo.ImageRenderer;

public class JavaCoreApi {

	public static void main(String[] args)throws Exception{
		ImageRenderer renderer = new ImageRenderer();
		System.out.println("start");
		String url = "https://www.xfnauto.com/";
		FileOutputStream out = new FileOutputStream(new File("C:"+File.separator+"Users"+File.separator+"Zwen"+File.separator+"Desktop"+
				File.separator+"html.png"));
		renderer.renderURL(url, out, ImageRenderer.Type.PNG);
		System.out.println("end");
	}
}
