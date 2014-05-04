package Connection;

import java.io.*;
import java.net.*;

 public class TCPClient {
	public static void main(String argv[]) throws Exception {
		String sentence;
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(
				System.in));
		Socket clientSocket = new Socket("41.46.10.90", 6789);
		DataOutputStream outToServer = new DataOutputStream(
				clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(
				clientSocket.getInputStream()));
		char playerSign = (char) inFromServer.read();
		boolean turn = false;
		if (playerSign == 'w')
			turn = true;
		try{
				while (true) {
			if (turn) {
				sentence = inFromUser.readLine();
				outToServer.writeBytes(sentence + '\n');
				turn = false;
			} else {

				String next = inFromServer.readLine();
				System.out.println(next);
				turn = true;
			}

		}
		}catch(Exception e)
		{
			 clientSocket.close();

		}
	}
}