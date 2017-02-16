package ZsService.util;


import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Random;


/**
 * Aes加密、解密方法
 * Created by nzm on 2017/2/15.
 */
public class AesUtil {
    public static String encrypt(String key, String text) {
        try {
            byte[] cipher = encrypt(key.getBytes(), text.getBytes());
            return Base64.encodeBase64String(cipher);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static byte[] encrypt(byte[] key, byte[] text) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] aesKeyBytes = new byte[32];    //32 bytes for AES-256
            int len = key.length > 32 ? 32 : key.length;
            System.arraycopy(key, 0, aesKeyBytes, 0, len);
            SecretKeySpec keySpec = new SecretKeySpec(aesKeyBytes, "AES");
            byte[] iv = new byte[cipher.getBlockSize()];
            Random ran = new Random();
            ran.nextBytes(iv);        // 随机的初始化向量
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            byte[] results = cipher.doFinal(text);

            //将iv和密文拼接后返回
            byte[] retBytes = new byte[iv.length + results.length];
            System.arraycopy(iv, 0, retBytes, 0, iv.length);
            System.arraycopy(results, 0, retBytes, iv.length, results.length);
            return retBytes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * AES解密算法
     *
     * @param key
     * @param cipher
     * @return
     */
    public static String decrypt(String key, String cipher) {
        try {
            BASE64Decoder base64de = new BASE64Decoder();
            byte[] cipherBytes = base64de.decodeBuffer(cipher);
            byte[] text = decrypt(key.getBytes(), cipherBytes);
            return new String(text);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static byte[] decrypt(byte[] key, byte[] cipher) {
        try {
            if (cipher.length < 32 || cipher.length % 16 != 0) {
                return null;
            }
            Cipher cp = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] aesKeyBytes = new byte[32];    //32 bytes for AES-256
            int len = key.length > 32 ? 32 : key.length;
            System.arraycopy(key, 0, aesKeyBytes, 0, len);
            SecretKeySpec keySpec = new SecretKeySpec(aesKeyBytes, "AES");
            byte[] iv = new byte[cp.getBlockSize()];

            //将iv和密文拼接后返回
            System.arraycopy(cipher, 0, iv, 0, iv.length);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            cp.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            byte[] cip = new byte[cipher.length - iv.length];
            System.arraycopy(cipher, iv.length, cip, 0, cip.length);
            byte[] results = cp.doFinal(cip);
            return results;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
