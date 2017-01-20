package com.tianxingshuke.TestMain;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by nzm on 2016/12/27.
 * 解析xml demo
 */
public class Test {
    public static void main(String[] args) {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
                     "<subatm>" +
                            "<application>GwBiz.Req</application>" +
                            "<version>2.0.0</version>" +
                            "<sendTime>" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "</sendTime>" +
                            "<transCode>100050</transCode>" +
                            "<channelId>" + 11008902 + "</channelId>" +
                            "<channelOrderId>" + new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒").format(new Date()) + "</channelOrderId>" +
                            "<name>郭文英</name>" +
                            "<verifyMode>1</verifyMode>" +
                            "<cid>130404199306070624</cid>" +
                     "</subatm>";

        try {
            Document document = DocumentHelper.parseText(xml);
            Element root = document.getRootElement();

            Element name = root.element("name");
            Element cid = root.element("cid");
            Element transCode = root.element("transCode");
            Element channelOrderId = root.element("channelOrderId");

            System.out.print(name.getName()+":"+name.getStringValue()+"|"
                    +cid.getStringValue()+"|"
                    +transCode.getStringValue()+"|"
                    +channelOrderId.getStringValue());
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
