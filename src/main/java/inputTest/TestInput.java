package inputTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * 批量测试demo
 * Created by nzm on 2017/1/12.
 */
public class TestInput {
    public static void main(String[] args) {
        File file = new File("D:\\text1.txt");
        try {
            FileReader reader = new FileReader(file);
            BufferedReader buf = new BufferedReader(reader);
            String str;
            while ((str = buf.readLine()) != null) {
                String[] a = str.split(",");
                System.out.println(a[0].trim() + a[1].trim());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
