import java.io.*;
import java.net.*;
import java.io.Serializable;

import javax.crypto.SecretKey;
import javax.crypto.SealedObject;
public class ReverseStringServer {

	// Returns a String that is the reverse of the parameter s
	public static String reverse(String s) {
		String result = "";
		int length = s.length();

		for (int i = length - 1; i >= 0; i--) {
			result = result + s.charAt(i);
		}

		return result;
	}

	public static void main(String[] args) throws Exception {
		// Create server socket listening on port
		int port = 3333;
		ServerSocket serverSocket = new ServerSocket(port);
		
		
		AESEncryption bob = new AESEncryption();
		SecretKey bobKey = bob.getSecretEncryptionKey("alice&bob");
		
		// Declare client socket
		Socket clientSocket;

		while (true) { // Provide service continuously
			clientSocket = serverSocket.accept();

			ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
			
			SealedObject a = (SealedObject) in.readObject();
			String decryptedText = (String) bob.decrypt(a, bobKey);
			String s = decryptedText;
			String result = reverse(s);
			
			SealedObject cipherObject = bob.encrypt(result, bobKey);
			out.writeObject(cipherObject);
			out.flush();
			
			out.close();
			in.close();
			clientSocket.close();
		}
	}

}
