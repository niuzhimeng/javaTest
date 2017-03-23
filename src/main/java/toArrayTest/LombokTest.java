package toArrayTest;

import org.junit.Test;

/**
 * Created by nzm on 2017/3/10.
 */
public class LombokTest {
    @Test
    public void test() {
        Lombok lombok = new Lombok();
        lombok.setName("nzm");
        lombok.setId("21");
        System.out.println(Lombok.OutStr(null));
    }

}
