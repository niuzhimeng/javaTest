package com.tianxingshuke.utils;

import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5摘要工具类
 *
 * @author xiaoding
 */
public class Md5Utils {
    private Md5Utils() {
    }

    public static String md5ToBas64(String data) {
        MessageDigest md = null;
        String str = null;
        try {
            md = MessageDigest.getInstance("MD5");
            str = new String(Base64.encode(md.digest(data.getBytes("UTF-8"))), "UTF-8");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String md5ToHexStr(String data) {
        MessageDigest md = null;
        String str = null;
        try {
            md = MessageDigest.getInstance("MD5");
            str = Hex.toHexString(md.digest(data.getBytes("UTF-8")));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String toMD5(String plainText) {
        try {
            //生成实现指定摘要算法的 MessageDigest 对象。
            MessageDigest md = MessageDigest.getInstance("MD5");
            //使用指定的字节数组更新摘要。
            md.update(plainText.getBytes());
            //通过执行诸如填充之类的最终操作完成哈希计算。
            byte b[] = md.digest();
            //生成具体的md5密码到buf数组
            int i;
            StringBuilder buf = new StringBuilder("");
            for (byte aB : b) {
                i = aB;
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            // System.out.println("32位: " + buf.toString());// 32位的加密
            // System.out.println("16位: " + buf.toString().substring(8, 24));// 16位的加密，其实就是32位加密后的截取
            return buf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plainText;
    }

}
