package ryanlx.fileencryptor;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

public class AesEncryptor {
    private static SecretKeySpec secretKey;
    private static byte[] key;

    private static boolean printLog = false;

    private static void setKey(String myKey) throws Exception {
        key = myKey.getBytes("UTF-8");
        MessageDigest sha = MessageDigest.getInstance("SHA-1");
        key = sha.digest(key);
        key = Arrays.copyOf(key, 16);
        secretKey = new SecretKeySpec(key, "AES");
    }

    public static String encrypt(String strToEncrypt, String secret) throws Exception {
        setKey(secret);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        if (printLog) {
            System.out.println("secret: " + secret);
            System.out.println("strToEncrypt: " + strToEncrypt);
        }
        return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
    }

    public static String decrypt(String strToDecrypt, String secret) throws Exception {
        setKey(secret);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        if (printLog) {
            System.out.println("secret: " + secret);
            System.out.println("strToDecrypt: " + strToDecrypt);
        }
        return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
    }
}
