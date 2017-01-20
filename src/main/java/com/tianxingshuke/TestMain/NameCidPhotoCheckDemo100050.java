package com.tianxingshuke.TestMain;

import com.tianxingshuke.utils.SupTest;
import sun.misc.BASE64Encoder;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
public class NameCidPhotoCheckDemo100050 extends SupTest {

	public static void main(String[] args) throws Exception {
	  String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"+
        "<subatm>"+
              "<application>GwBiz.Req</application>"+
              "<version>2.0.0</version>"+
              "<sendTime>"+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+"</sendTime>"+
              "<transCode>100050</transCode>"+
              "<channelId>"+11008902+"</channelId>"+
              "<channelOrderId>"+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+"</channelOrderId>"+
              "<name>郭文英</name>"+
              "<verifyMode>1</verifyMode>"+
              "<cid>130404199306070624</cid>"+
              "<photo>"+ createFaceCodeFromText() +"</photo>"+
          "</subatm>";
		checkRiskSystem(xml); 
	}

	//返回照片的base64码
	 public static String createFaceCodeFromText() throws Exception {
	    InputStream in = NameCidPhotoCheckDemo100050.class.getClassLoader().getResourceAsStream("photo.txt");
	    StringBuilder sb = new StringBuilder(50000);
	    byte[] buf = new byte[1024];
	    int length;
	    while ((length = in.read(buf)) > 0) {
	      sb.append(new String(buf, 0, length));
	    }
	    in.close();
	    return sb.toString();
	  }
	 
  public static String createFaceCode() throws Exception{
    BASE64Encoder encoder = new BASE64Encoder();
    byte [] imageByte = toByteArray(NameCidPhotoCheckDemo100050.class.getClassLoader().getResource("photo.jpg"));
    
    String humanFaceCode = encoder.encodeBuffer(imageByte);
//    System.out.println(humanFaceCode);
    return humanFaceCode;
  }
  
  private static byte[] toByteArray(URL imageFile) throws Exception {
    BufferedImage img = ImageIO.read(imageFile);  
    ByteArrayOutputStream buf = new ByteArrayOutputStream(img.getHeight()*img.getWidth());  
    try {  
        ImageIO.write(img, "jpg", buf);  
    } catch (Exception e) {  
        e.printStackTrace();  
        return null;  
    }  
    return buf.toByteArray();  
} 
	
}
