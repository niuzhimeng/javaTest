import RtService.RtThreeElement;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * 联通三要素
 * Created by nzm on 2017/2/16.
 */
public class ThreeElementTest {
    private Logger LOGGER = Logger.getLogger(ThreeElementTest.class);

    @Test
    public void getIdentityPhotoCheckFromHxkj() {
        File file = new File("D:\\text0.txt");
        try {
            FileReader reader = new FileReader(file);
            BufferedReader buf = new BufferedReader(reader);
            String str;
            while ((str = buf.readLine()) != null) {
                String[] a = str.split(",");

                String name = a[2].trim();
                String idCard = a[0].trim();
                String phone = a[1].trim();
                long start = System.currentTimeMillis();
                System.out.println();
                RtThreeElement.threeElement(name,idCard,phone);

                LOGGER.info("耗时" + (System.currentTimeMillis() - start) + "毫秒");
                //LOGGER.info(identityCheck);
                System.out.println("---------------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("身份证验证数据源验证异常");
        }
    }
}
