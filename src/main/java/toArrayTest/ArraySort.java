package toArrayTest;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Created by nzm on 2017/3/21.
 */
public class ArraySort {


    public static void main(String[] args) {
        String token = "{\n" +
                "    \"access_token\": \"6BD4334DE0BE40C6BA54739CAE67ED72219C9CA0AD0D2E520DE4737F1EB5EE005508FA05F9BB4039BDE195D2642E9EDA\",\n" +
                "    \"Effective\": \"2017/3/22 11:17:17\"\n" +
                "}";
        JsonObject resultToken = new JsonParser().parse(token).getAsJsonObject();
        System.out.println(resultToken.has("access_token"));
    }
}
