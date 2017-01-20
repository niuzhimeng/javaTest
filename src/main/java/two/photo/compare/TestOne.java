package two.photo.compare;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.okhttp.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by nzm on 2017/1/3.
 */
public class TestOne {
    // 1V1比对测试
    public static void main(String[]args) throws Exception {
        JsonObject result = null;

        Map map = new HashMap();
        map.put("appId", "a89f155709de4b57b2ebe76cd2942685");
        map.put("appKey", "MDY4NDE2OTY=");
        map.put("isDescreen", "true");
        map.put("photo1", new File("D:\\temp\\3-3-3.jpg"));
        map.put("photo2", new File("D:\\temp\\7.jpg"));

        try {
            RequestBody formBody = new FormEncodingBuilder()
                    .add("appId", "a89f155709de4b57b2ebe76cd2942685")
                    .add("appKey", "MDY4NDE2OTY=")
                    .add("isDescreen", "true")
                    .add("photo2", new File("D:\\temp\\7.jpg").toString())
                    .add("photo2", String.valueOf(new File("D:\\temp\\7.jpg"))).build();
            Request request = new Request.Builder()
                    .url("http://ds.facefinger.cn:8080/ff/face/compare")
                    .post(formBody).build();
            Response response = new OkHttpClient().newCall(request).execute();
            if (response.isSuccessful()) {
                result = new JsonParser().parse(response.body().string()).getAsJsonObject();
            } else {
                throw new IOException("Unexpected code " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String code = result.get("code").getAsString();
        String msg = result.get("msg").getAsString();
        String score = "";
        if ("1001".equals(code)) {
            score = result.getAsJsonObject("entity").get("score").getAsString();
        }
        System.out.println("compare：code = " + code + " msg = " + msg + " score = " + score);

    }
}



