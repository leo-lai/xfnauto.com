package main.com.Interceptor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Calendar;

public class Zithread implements Runnable{

private BufferedReader reader;

private Socket socket;

	public Zithread(Socket clientSocket)

	{
		try
		{
			// 得到socket连接
			socket = clientSocket;
			// 得到客户端发来的消息
			InputStreamReader isReader = new InputStreamReader(socket.getInputStream());
			reader = new BufferedReader(isReader);
			// 发送指令
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			Calendar now = Calendar.getInstance(); 
			String info = "~KEEPA&5&01|02#";
			pw.write(info);
			pw.flush();
//			 socket.shutdownOutput();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void run() {
		String message;
		try {
			while ((message = reader.readLine()) != null) {
				System.out.println("客户端消息: " + message);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
