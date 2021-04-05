package socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 1. 프로젝트명 : DataSendReceive
 * 2. 파일명 : Server.java
 * 3. 작성자 : 강동석
 * 4. 작성일자 : 2021/04/05
 * 5. 설명 : 서버를 구동시킨 후 클라이언트로부터 데이터를 전달받아 콘솔 화면에 출력한다. 그리고 클라이언트에게 데이터를 전송한다.
*/
public class Server {

	public static void main(String[] args) {

		ServerSocket server = null;
		Socket client = null;
		BufferedReader br = null;
		
		try {
			
			server = new ServerSocket();
			server.bind(new InetSocketAddress("localhost", 1234));
			
			while(true) {
				
				client = server.accept();
				InetSocketAddress isa = (InetSocketAddress) client.getRemoteSocketAddress();
				System.out.println("Connected Client : [" + isa.getHostName() + "]");
				
				br = new BufferedReader(new InputStreamReader(client.getInputStream(), "UTF-8"));
				String fromClient = br.readLine();
				System.out.println("클라이언트("+isa.getHostName()+")로부터 \"" + fromClient + "\" 데이터를 받았습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(br!=null) {br.close();}
				if(!server.isClosed()) {server.close();}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}