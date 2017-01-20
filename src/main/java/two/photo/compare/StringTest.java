package two.photo.compare;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by nzm on 2017/1/16.
 */
public class StringTest {
    public static void main(String[] args) {
        String json = "{\n" +
                "    \"code\": 0,\n" +
                "    \"message\": \"\",\n" +
                "    \"result\": {\n" +
                "        \"data\": \"+8XdV3LLD3DIQuBQVkYPmJRCPLLncbV2RvGd1zThyLEGCR9IUf2flZZrcaQ53ajKhwTCYD06AO4RWyHHPWZF71Drgnu1/buGsPK3rWQ0f63PhjfEeJsu4ibHM9PvP78zL7D+8LaPm1+gXZVTaC5EBEKyzqTnPD2HpG/yH/97l1OtoYbw5vHkA5Qo/3SPiqQSWi0fJvQI+ZKl6X1JoSrjUTt4jT+cJzKxDsEmlrc+gcnQZZwu627WQCDmBwMO+hUVsR3Bt/NEo9+iPneLldW7v5SCS4F7XWsAu2kLYFzBPud7nYieDmv4IMT4AQQgvdnKGQ7KSDF3jfUzfvYqWareCA==\",\n" +
                "        \"extend\": null\n" +
                "    }\n" +
                "}";
        json = json.replace("null","");
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        String s = jsonObject.getAsJsonObject("result").get("data").getAsString();
        System.out.println("输出" + jsonObject.get("extend").toString().equals(""));
        StringUtils.isBlank("");
    }
}
