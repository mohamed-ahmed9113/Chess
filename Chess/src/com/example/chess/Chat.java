//package com.example.chess;
//
//import java.io.BufferedReader;
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.Socket;
//import java.net.UnknownHostException;
//import java.util.StringTokenizer;
//
//import models.Global;
//import models.Pieces;
//import models.Position;
//import android.os.StrictMode;
//
//public class Chat {
//	private Socket clientSocket;
//	private DataOutputStream outToServer;
//	private BufferedReader inFromServer;
//	private int chatterNum;
//
//	public Chat() {
//		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
//				.permitAll().build();
//		StrictMode.setThreadPolicy(policy);
//		try {
//			clientSocket = new Socket(Global.ipAddress, 6790);
//			outToServer = new DataOutputStream(clientSocket.getOutputStream());
//			inFromServer = new BufferedReader(new InputStreamReader(
//					clientSocket.getInputStream()));
//			char playerSign = (char) inFromServer.read();
//			if (playerSign == 'b') {
//				clientSocket = new Socket(Global.ipAddress, 6791);
//				outToServer = new DataOutputStream(
//						clientSocket.getOutputStream());
//				inFromServer = new BufferedReader(new InputStreamReader(
//						clientSocket.getInputStream()));
//
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	public void send(String message) {
//		try {
//			// message=chatterNum+message;
//			outToServer.writeBytes(message + "\n");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	public void receive() {
//		new Thread(new Runnable() {
//			public void run() {
//
//				while (true) {
//					try {
//						final String received = inFromServer.readLine();
//						optionWindow.message(received);
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//
//				}
//			}
//		}).start();
//
//	}
//
//}
