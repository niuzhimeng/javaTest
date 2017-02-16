package toArrayTest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

/**
 * Created by nzm on 2017/2/10.
 */
public class YaoYaoYaun {

    @Test
    public void lambda() {
        Runnable noArguments = () -> System.out.println("Hello World");
        new Thread(noArguments).start();
    }

    @Test
    public void lambda2() {
        BinaryOperator<Integer> add = (x, y) -> x + y;
        System.out.println(add.apply(2, 3));
    }

    @Test
    public void lambda3() {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 100; i++) {
            list.add(i + "");
        }
        long count = list.stream().filter(i -> i.equals("55")).count();
        System.out.println(count);
    }

    @Test
    public void lambda4() {
        Integer count = Stream.of(1, 2, 3, 4).reduce(0, (acc, element) -> acc + element);
        System.out.println(count);
    }

    @Test
    public void lambda5() {
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 0).map(i -> {
            System.out.println(i);
            return i + 10;
        }).forEach(item -> System.out.println(item));
    }
}



