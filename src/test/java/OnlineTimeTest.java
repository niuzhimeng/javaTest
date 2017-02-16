import ZsService.OnlineTime;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by nzm on 2017/2/16.
 */
public class OnlineTimeTest {
    Logger LOGGER = Logger.getLogger(OnlineTimeTest.class);

    @Test
    public void getIdentityPhotoCheckFromHxkj() {
        File file = new File("D:\\text1.txt");
        try {
            FileReader reader = new FileReader(file);
            BufferedReader buf = new BufferedReader(reader);
            String str;
            while ((str = buf.readLine()) != null) {
//                //String[] a = str.split(",");
//                //System.out.println(a[0].trim() + a[1].trim());
//
                //String mob = "13001076391";
                long start = System.currentTimeMillis();
                System.out.println();
                String identityCheck = OnlineTime.getIdentityPhotoResult(str);
                LOGGER.info("耗时" + (System.currentTimeMillis() - start) + "毫秒");
                LOGGER.info(identityCheck);
                System.out.println("---------------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("身份证验证数据源验证异常");
        }
    }
}
