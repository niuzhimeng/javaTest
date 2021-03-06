package com.tianxingshuke.utils;

import com.tianxingshuke.utils.okhttp.OkHttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.util.encoders.Base64;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


public abstract class SupTest {

    public static final String channelId = "11008902";
    public static final String uri = "https://58.246.136.11:1443/dataservice/service.ac";
    public static final String publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJQUniugXXN9BOMToDHpRbWQAzMPBBwjAdv4SrMjo/dIu3BGLv987bxbVsHBqCMLLSj8yjAQNKJOZ+xCIT2n9sECAwEAAQ==";

    /**
     * 风险控制系统验证
     *
     * @throws Exception
     */
    protected static String checkRiskSystem(String xml) throws Exception {
        String mkey = UUID.randomUUID().toString();

        // 加密报文体格式：BASE64(商户号)| BASE64(RSA(报文加密密钥))| BASE64(3DES(报文原文))
        String strKey = RSACoder.encryptByPublicKey(new String(mkey.getBytes(), "utf-8"), publicKey);

        String strxml = new String(Base64.encode(DesUtil.encrypt(
                xml.toString().getBytes("utf-8"), mkey.getBytes())));

        String returnXml = new String(Base64.encode(channelId.getBytes("utf-8"))) + "|"
                + strKey + "|" + strxml;
        System.out.println(returnXml);
        System.out.println();
        System.out.println();
        long start = System.currentTimeMillis();
        System.out.println("T1=" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date()));

        String reutrnResult = OkHttpUtils.postString(uri, returnXml);

        System.out.println("T2=" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date()) + ",time length:" + (System.currentTimeMillis() - start));
        System.out.println(reutrnResult);
        String xmlArr[] = reutrnResult.split("\\|");

        if (xmlArr[0].equals("0")) {
            System.out.println(new String(Base64.decode(xmlArr[2]),
                    "utf-8"));
        } else {
            byte[] b = DesUtil.decrypt(Base64.decode(xmlArr[1]),
                    mkey.getBytes());

            String tradeXml = new String(b, "utf-8");
            System.out.println(tradeXml);
            System.out.println(new String(Base64.encode(Md5Utils.md5ToHexStr(tradeXml).getBytes("utf-8"))));// BASE64(MD5(报文))
            return tradeXml;
        }
        return null;
    }

    public static String readRespMsg(String xml) {
        if (StringUtils.isEmpty(xml)) {
            return "未知";
        }
        try {
            Element ele = DocumentHelper.parseText(xml).getRootElement();
            return ele.elementText("respDesc");
        } catch (DocumentException e) {
            return "未知2";
        }


    }
}
