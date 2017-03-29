import java.io.Serializable;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.KeyPairGenerator;
import java.security.Security;
import java.security.Signature;
import java.security.SignedObject;

public class RSASignature {

    private int keySize;

    public RSASignature() throws Exception {
        keySize = 3072; // Default key size of Sun
    }

    public RSASignature(int size) throws Exception {
        keySize = size;
    }

    /**
     * Generates a RSA public and private key pair.
     *
     * @return a RSA public and private key pair generated.
     * @throws Exception
     */
    public KeyPair genPubKeys() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(keySize);
        KeyPair keyPair = keyGen.generateKeyPair();

        return keyPair;
    }

    /**
     * Signs an object using the specified private key.
     *
     * @param data the object to be signed.
     * @param key the private key to be used for signature.
     * @return a signed object.
     * @throws Exception
     */
    public SignedObject sign(Serializable data, PrivateKey key) throws Exception {
        Signature signingEngine = Signature.getInstance("SHA256withRSA");
        SignedObject signedObject = new SignedObject(data, key, signingEngine);

        return signedObject;
    }

    /**
     * Verifies a signed object using the specified public key.
     *
     * @param signedObject the signed object to be verified.
     * @param key the public key to be used for verification.
     * @return <code>true</code> if verified, <code>false</code> otherwise.
     * @throws Exception
     */
    public boolean verifySignature(SignedObject signedObject, PublicKey key) throws Exception {
        Signature signingEngine = Signature.getInstance("SHA256withRSA");
        return signedObject.verify(key, signingEngine);
    }

}
