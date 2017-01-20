package toArrayTest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nzm on 2017/1/18.
 */
public class toArrayTest {
    public static void main(String[] args) {
//        List<String> list = new ArrayList<>();
//        list.add("1");
//        list.add("2");
//        String[] s = new String[list.size()];
//        list.toArray(s);
//        System.out.println(s[0] + s[1]);

        Map<String, String> map = new HashMap<>();
        map.put("1", "value1");
        map.put("2", "value2");
        map.put("3", "value3");
        for (Map.Entry<String, String> entries : map.entrySet()) {
            System.out.println(entries.getKey() + entries.getValue());
        }

    }
}
