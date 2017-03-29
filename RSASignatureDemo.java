import java.util.*;
import java.io.Serializable;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignedObject;

public class RSASignatureDemo {

    public static void main(String[] args) throws Exception {
        String plainText = "Hello World";
		long startTime = System.currentTimeMillis();
        // Alice signs the plainText using her private key
        RSASignature aliceRSA = new RSASignature();
        KeyPair aliceKeyPair = aliceRSA.genPubKeys();
        SignedObject signedObject = aliceRSA.sign(plainText, aliceKeyPair.getPrivate());
		long time1=System.currentTimeMillis();//Signature generated
		
		
        // Bob verifies the signature using Alice's public key
        RSASignature bobRSA = new RSASignature();
		
		long time2=System.currentTimeMillis();
        boolean verifyResult = bobRSA.verifySignature(signedObject, aliceKeyPair.getPublic());
		long endTime = System.currentTimeMillis();
		
		long timeNeeded = endTime - startTime;
		long timegen= time1 - startTime;
		long timeveri=endTime - time2;
        if (verifyResult) {
            System.out.println("Bob has verified the signed object.");
        } else {
            System.out.println("The signature cannot be verified.");
        }

        System.out.println("Original plaintext : " + plainText);
        System.out.println("Plaintext from signed object: " + signedObject.getObject());
	
		System.out.println("Total Time Spent for Generating Signature: " + timegen);
		System.out.println("Total Time Spent for Verification: " + timeveri);
		System.out.println("Total Time Spent : " + timeNeeded);
    }

}
