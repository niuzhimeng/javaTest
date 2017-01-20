package toArrayTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by nzm on 2017/1/19.
 */
public class StringNo {
    public static void main(String[] args) {

//        LocalDateTime localDateTime = LocalDateTime.parse(s);
//        String q = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        System.out.println(q);
        String stringDate = "Sun Jan 01 2017 00:00:00 GMT+0800";
        //String stringDate = "Sun Jan 01 2017 00:00:00 GMT";
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss 'GMT+0800'",Locale.US);
        Date date = null;
        try {
            date =sdf.parse(stringDate);
            String s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
