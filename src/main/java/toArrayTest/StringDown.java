package toArrayTest;

/**
 * Created by nzm on 2017/2/14.
 */
public class StringDown {
    public static void main(String[] args) {
        String str = "abcdefg";
        char[] chars = str.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = chars.length - 1; i >= 0; i--) {
            stringBuilder.append(chars[i]);
        }
        System.out.println(stringBuilder.toString());
    }
}
