package com.example.chess;

import java.io.*;
import java.net.*;

public class wifiConnection {
	public static void main(String argv[]) throws Exception {
		String sentence;
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(
				System.in));
		Socket clientSocket = new Socket("localhost", 6789);
		DataOutputStream outToServer = new DataOutputStream(
				clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(
				clientSocket.getInputStream()));
		char playerSign = (char) inFromServer.read();
		boolean turn = false;
		if (playerSign == 'w')
			turn = true;
		while (true) {
			if (turn) {
				System.out.println("YOUR turn");
				sentence = inFromUser.readLine();
				outToServer.writeBytes(sentence + '\n');
				turn = false;
			} else {

				String next = inFromServer.readLine();
				System.out.println(next);
				turn = true;
			}
			
		}
		 //clientSocket.close();
	}
}