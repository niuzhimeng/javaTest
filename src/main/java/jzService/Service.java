package jzService;

import OkHttp.OkHttpUtils;
import com.tianxingshuke.utils.Md5Utils;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * 敬众(乘机人报告)
 * Created by nzm on 2017/3/15.
 */
public class Service {

    private static final String URL = "https://api.xiaoheer.com/ws/report/PSA.asmx/PassengerStatid";

    private static final String HASHCODE = "3c4401a5000c4ed48281e549ad991e7a";
    /**
     * 私钥
     */
    private static final String key = "87BjH5";

    @Test
    public void test() {
        String createTime = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String name = "周欣";
        String idCard = "110101198506251020";
        String huZhao = "";
        String month = "12";
        String sign = huZhao + HASHCODE + month + name + idCard + key + createTime;
        String eSign = Md5Utils.toMD5(sign);

        Map<String, String> map = new HashMap<>();
        map.put("HashCode", HASHCODE);
        //姓名
        map.put("name", name);
        //身份证号
        map.put("pid", idCard);
        //护照(可为空)
        map.put("gid", huZhao);
        //分析几个月内的 参数有 3、6、12分别表示3个月，6个月，12个月
        map.put("imonth", month);
        //签名
        map.put("sign", eSign);
        String result = OkHttpUtils.post(URL, map);
        System.out.println(result);
    }
}
