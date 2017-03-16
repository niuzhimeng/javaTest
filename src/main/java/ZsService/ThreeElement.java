package ZsService;

import OkHttp.OkHttpUtils;
import ZsService.util.AesUtil;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.HashMap;
import java.util.Map;

/**
 * 综合测试
 * Created by nzm on 2017/2/16.
 */
public class ThreeElement {
    /**
     * 产品类型, 根据需要填写
     */
    private static final String PRODUCT_TYPE = "DIAN_XIN_THREE_ELEMENT";
    /**
     * 访问URL
     */
    private static final String TWO_ELEMENT_BACK_PHOTO_URL = "http://60.175.86.35:8990/verify";
    /**
     * AES256加密秘钥
     */
    private static final String PRIVATE_KEY = "l0ICipaem4Bg8hiYHOe8Th2QBpeeuqo1";
    /**
     * 商户 id
     */
    private static final String BUSINESS_ID = "tianxingkeji_test";

    /**
     * 综合测试
     */
    public static String getIdentityPhotoResult(String name, String mobile, String idCard) {
        Map<String, String> request = new HashMap<>();
        JsonObject item = new JsonObject();
        // 证件类型(固定)
        item.addProperty("certificate_type", "ID_CARD");
        // 身份证号码
        item.addProperty("id_card", idCard);
        //姓名
        item.addProperty("name", name);
        //手机号
        item.addProperty("mobile", mobile);
        //将发送的信息加密
        String encrypt = AesUtil.encrypt(PRIVATE_KEY, item.toString());
        request.put("verify_msg", encrypt);
        request.put("business_id", BUSINESS_ID);
        request.put("product_type", PRODUCT_TYPE);

        //发送请求，获取返回json串，并转为JsonObject对象
        String jsonResult = OkHttpUtils.post(TWO_ELEMENT_BACK_PHOTO_URL, request);
        JsonObject returnJsonObject = new JsonParser().parse(jsonResult).getAsJsonObject();
        //获取解密后的data,转换成JsonObject对象
//        JsonObject dataJsonObject = new JsonParser().parse(
//                AesUtil.decrypt(PRIVATE_KEY, returnJsonObject.getAsJsonObject("result").get("data").getAsString())
//        ).getAsJsonObject();
        System.out.println(jsonResult);
        System.out.println("解密数据：" + AesUtil.decrypt(
                PRIVATE_KEY, returnJsonObject.getAsJsonObject("result").get("data").getAsString()
                )
        );

        return jsonResult;
    }
}
