package player;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import android.os.StrictMode;

import models.Global;

public class PlayerHuman extends Player {
	private Socket clientSocket;
	private DataOutputStream outToServer;
	private BufferedReader inFromServer;

	public PlayerHuman(int side) {
		super(side);
	}

	public PlayerHuman() {
		try {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
			clientSocket = new Socket(Global.ipAddress, 4000);
			outToServer = new DataOutputStream(clientSocket.getOutputStream());
			inFromServer = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));
			char playerSign = (char) inFromServer.read();
			if (playerSign == 'w') {
				Global.turn = true;
				setSide(0);
			} else {
				Global.turn = false;
				setSide(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Socket getSocket() {
		return clientSocket;
	}

	public DataOutputStream getDataOutputStream() {
		return outToServer;
	}

	public BufferedReader getInFromServer() {
		return inFromServer;
	}
}
