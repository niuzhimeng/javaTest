package two.photo.compare;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.File;

/**
 * Created by nzm on 2016/12/27.
 */
public class MyOne {
    public static void main(String[]args) throws Exception {
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod("http://ds.facefinger.cn:8080/ff/face/compare");

        Part[] parts = {
                new StringPart("appId", "a89f155709de4b57b2ebe76cd2942685"),
                new StringPart("appKey", "MDY4NDE2OTY="),
                new StringPart("isDescreen", "true"),
                new FilePart("photo1", new File("D:\\temp\\3-3-3.jpg")),
                new FilePart("photo2", new File("D:\\temp\\7.jpg"))
        };

        RequestEntity requestEntity = new MultipartRequestEntity(parts, method.getParams());
        method.setRequestEntity(requestEntity);
        method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 30000);
        // 链接超时 30秒
        client.getHttpConnectionManager().getParams()
                .setConnectionTimeout(30000);
        // 读取超时 30秒
        client.getHttpConnectionManager().getParams().setSoTimeout(30000);
        client.executeMethod(method);

        String[] result = new String[2];
        result[0] = String.valueOf(method.getStatusCode());
        result[1] = method.getResponseBodyAsString();
        System.out.println("http status : " + result[0]);
        System.out.println("http response : " + result[1]);
    }
}
