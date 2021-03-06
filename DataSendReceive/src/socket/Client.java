package socket;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * 1. 프로젝트명 : DataSendReceive
 * 2. 파일명 : Client.java
 * 3. 작성자 : 강동석
 * 4. 작성일자 : 2021/04/05
 * 5. 설명 : 서버에 접속한 후 서버에 데이터를 전송한다. 그리고 서버로부터 데이터를 전달받아 콘솔 화면에 출력한다.
*/
public class Client {

	public static void main(String[] args) {

		Socket client = null;
		Scanner sc = null;
		BufferedWriter bw = null;
		
		try {
			
			client = new Socket();
			client.connect(new InetSocketAddress("localhost", 1234));
			sc = new Scanner(System.in);
			
			bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream(), "UTF-8"));
			System.out.print("서버에게 전송할 메시지를 입력하세요 >>> ");
			String toServer = sc.nextLine();
			bw.write(toServer + '\n');
			bw.flush();
			System.out.println("서버(127.0.0.1)로 \"" + toServer + "\" 데이터를 전송했습니다.");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(bw!=null) {bw.close();}
				if(!client.isClosed()) {client.close();}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
