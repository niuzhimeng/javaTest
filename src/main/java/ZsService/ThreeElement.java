package ZsService;

import OkHttp.OkHttpUtils;
import ZsService.util.AesUtil;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.codehaus.jettison.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 联通身份证三元素
 * Created by nzm on 2017/2/16.
 */
public class ThreeElement {
    /**
     * 产品类型, 根据需要填写
     */
    public static final String PRODUCT_TYPE = "THREE_ELEMENT";
    /**
     * 访问URL
     */
    public static final String TWO_ELEMENT_BACK_PHOTO_URL = "http://60.175.86.35:8990/verify";
    /**
     * AES256加密秘钥
     */
    public static final String PRIVATE_KEY = "l0ICipaem4Bg8hiYHOe8Th2QBpeeuqo1";
    /**
     * 商户 id
     */
    public static final String BUSINESS_ID = "tianxingkeji_test";

    /**
     * 联通手机号在线时长
     *
     * @param mobile 手机号
     * @return
     * @throws Exception
     */
    public static String getIdentityPhotoResult(String name, String mobile, String idCard) throws Exception {
        Map<String, String> request = new HashMap<>();
        JSONObject item = new JSONObject();
        // 证件类型(固定)
        item.put("certificate_type", "ID_CARD");
        // 身份证号码
        item.put("id_card", idCard);
        //姓名
        item.put("name", name);
        //手机号
        item.put("mobile", mobile);
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
        System.out.println("解密数据：" + AesUtil.decrypt(PRIVATE_KEY, returnJsonObject.getAsJsonObject("result").get("data").getAsString()));

        return jsonResult;
    }
}
