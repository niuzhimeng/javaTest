package RtService;

import OkHttp.OkHttpUtils;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 荣图运营商三要素测试
 * Created by nzm on 2017/3/13.
 */
public class RtThreeElement {
    public static void threeElement(String name, String idCard, String phone) {
        //请求流水号
        String reqno = UUID.randomUUID().toString().replace("-", "");
        //appid唯一标识
        String appid = "c0c5dbfdf4d422bd0229ce1a082eb426";
        //account账号
        String account = "tianxingprod";
        //pwd密码
        String pwd = "G7OaAPTyoZ";
        //params加密后参数
        String params = "";
        //拼装params
        String aesKey = "kyyi17e6lnbtr1vl";
        String src = null;
        try {
            src = "phone=" + URLEncoder.encode(phone, "UTF-8")
                    + "&custCertNo=" + URLEncoder.encode(idCard, "UTF-8")
                    + "&custName=" + URLEncoder.encode(name, "UTF-8");
            params = AESUtil.Encrypt(src, aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("加密后的字串是：" + params);

        //接口地址
        String url = "http://credit.grunner.cn/api/baseinfo/cmccUserPhoneIdentity";
        Map<String, String> parameters = new LinkedHashMap<>();
        parameters.put("reqno", reqno);
        parameters.put("appid", appid);
        parameters.put("account", account);
        parameters.put("pwd", pwd);
        parameters.put("params", params);

        //开始调用
        String result = OkHttpUtils.post(url, parameters);

        //打印返回json
        System.out.println(name + ":" + result);
    }

}


