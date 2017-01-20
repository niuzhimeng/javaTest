package toArrayTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * Created by nzm on 2017/1/20.
 */
public class LocalDateTest {
    public static void main(String[] args) {
        String date = "Sun Jan 01 2017 01:02:03 GMT+0800";
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd yyyy HH:mm:ss 'GMT+0800'", Locale.US);
            LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
            System.out.print(localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        } catch (DateTimeParseException ex) {
            ex.printStackTrace();
        }
    }
}
