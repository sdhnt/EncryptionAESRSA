import java.io.Serializable;

import javax.crypto.SecretKey;
import javax.crypto.SealedObject;

public class AESEncryptionDemo {

    public static void main(String[] args) throws Exception {
		
		long startTime = System.currentTimeMillis();
		
	
        // Alice generates the encrypted text using a secret key shared with Bob
        AESEncryption alice = new AESEncryption();
        // Bob decrypts the ciphertext using the shared secret
        AESEncryption bob = new AESEncryption();

        String plainText = "Hello World";

        // Alice and Bob get their shared secret separately from the same password "alice&bob"
        SecretKey aliceKey = alice.getSecretEncryptionKey("alice&bob");
        SecretKey bobKey = bob.getSecretEncryptionKey("alice&bob");

        // Alice performs encryption using the shared key maintained by herself
        SealedObject cipherObject = alice.encrypt(plainText, aliceKey);
		
        // Bob performs decryption using the shared key maintained by himself
        String decryptedText = (String) bob.decrypt(cipherObject, bobKey);
		long endTime = System.currentTimeMillis();
		long timeNeeded = endTime - startTime;
        System.out.println("Original Text:" + plainText);
        System.out.println("Decrypted Text:" + decryptedText);
		System.out.println("Total Time Spent for Encryption and Decryption: " + timeNeeded);
    }

}
