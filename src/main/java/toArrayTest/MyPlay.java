package toArrayTest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by nzm on 2017/2/6.
 */
public class MyPlay {
    @Test
    public void lambda1() {
        List<String> names = new ArrayList<>();
        names.add("TaoBao");
        names.add("ZhiFuBao");
        List<String> lowercaseNames = names.stream().map(String::toLowerCase).collect(Collectors.toList());
        lowercaseNames.forEach(System.out::println);
    }

    @Test
    public void lambda2() {
        List<Integer> nums = new ArrayList<>();
        nums.add(1);
        nums.add(null);
        nums.add(2);
        long s = nums.stream().filter(Objects::isNull).count();
        System.out.println(s);
    }

}

