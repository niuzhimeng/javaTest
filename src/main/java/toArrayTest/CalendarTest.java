package toArrayTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by nzm on 2017/1/18.
 */
public class CalendarTest {
    public static void main(String[] args) {
//        LocalDate today = LocalDate.now();
//        LocalDate birthday = LocalDate.of(1994, 1, 18);
//
//        MonthDay monthDay = MonthDay.of(1,18);
//       // MonthDay monthDay = MonthDay.from(birthday);
//        MonthDay currentMonthDay = MonthDay.from(today);
//
//        if(monthDay.equals(currentMonthDay)){
//            System.out.println("yes");
//        }

//        //获取当前时间
//        LocalTime time = LocalTime.now();
//        System.out.println(time);
        String g = "Thu Oct 16 07:13:48 GMT 2015";
        String gmt = "Thu Aug 22 2013 15:12:00 GMT+0800";
        try {
            String s = new SimpleDateFormat("EEE MMM ddHH:mm:ss 'GMT' yyyy", Locale.US).parse(g).toString();
            System.out.println(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("1");
    }
}
