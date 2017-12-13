packege com.amolood.ebs

import android.util.Base64;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * Created by Amolood on 10/12/2017.
 * Phone 0912740956
 */

public class ePayment {
    public static String getIPINBlock(String ipin, String uuid) {
        String publicKey = "YOUR PUBLIC KEY";
        // clear ipin = uuid +  IPIN
        String cleraIpin = (uuid + ipin);
        // prepare public key, get public key from its String representation as // base64
        byte[] keyByte = Base64.decode(publicKey, Base64.DEFAULT);
        // generate public key
        X509EncodedKeySpec s = new X509EncodedKeySpec(keyByte);
        KeyFactory factory = null;
        try {
            factory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException ignored) {
        }
        Key pubKey = null;
        try {
            if (factory != null) {
                pubKey = factory.generatePublic(s);
            }
        } catch (InvalidKeySpecException ignored) {
        }
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            ipin = (Base64.encodeToString(cipher.doFinal(cleraIpin
                    .getBytes()), Base64.DEFAULT));
        } catch (NoSuchAlgorithmException ignored) {
        } catch (NoSuchPaddingException ignored) {
        } catch (InvalidKeyException ignored) {
        } catch (IllegalBlockSizeException ignored) {
        } catch (BadPaddingException ignored) {
        }
        return ipin;
    }
}
