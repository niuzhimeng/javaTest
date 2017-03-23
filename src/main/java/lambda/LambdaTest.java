package lambda;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Lambda测试
 * Created by nzm on 2017/3/21.
 */
public class LambdaTest {
    @Test
    public void test1() {
        List<String> list = new ArrayList<>();
        list.add("abc");
        list.add("a");
        list.add("ab");
        list.sort((String o1, String o2) -> {
            if (o1.length() > o2.length()) {
                return 1;
            } else if (o1.length() == o2.length()) {
                return 0;
            } else {
                return -1;
            }
        });
        list.forEach(System.out::println);
    }

    @Test
    public void test2() {
        List<String> list = new ArrayList<>();
        list.add("abc");
        list.add("a");
        list.add("ab");
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() > o2.length()) {
                    return 1;
                } else if (o1.length() == o2.length()) {
                    return 0;
                } else {
                    return -1;
                }

            }
        });
        for (String s : list) {
            System.out.println(s);
        }
    }

    @Test
    public void test3() {
        List<Integer> nums = new ArrayList<>();
        nums.add(1);
        nums.add(null);
        nums.add(3);
        nums.add(4);
        nums.add(null);
        nums.add(6);
        nums.stream().filter(num -> num != null).forEach(n -> System.out.println(n));

        Long lenth = nums.stream().filter(num -> num != null).count();
        System.out.println("数组长度" + lenth);
    }

    @Test
    public void test4() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(null);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(null);
        list.add(5);
        list.add(6);
        list.add(7);
        System.out.println("num is:" + list.stream().filter(num -> num != null)
                .distinct().mapToInt(num -> num * 2)
                .skip(2).limit(4).sum());

    }

    @Test
    public void test5() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(null);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(null);
        list.add(5);
        list.add(6);
        list.add(7);
        list.stream().filter(Objects::nonNull).map(map -> map += 10).distinct().forEach(System.out::println);
    }

    @Test
    public void test6() {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 100000; i++) {
            map.put(i, i * 2);
        }
        //耗时 1343  1074 1247
//        Long start = System.currentTimeMillis();
//        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
//            System.out.println(entry.getKey() + ":" + entry.getValue());
//        }
//        System.out.println("传统遍历耗时：" + (System.currentTimeMillis() - start));

        //1228  1221  1111
        Long start1 = System.currentTimeMillis();
        map.forEach((k, v) -> System.out.println(k + ":" + v));
        System.out.println("传统遍历耗时：" + (System.currentTimeMillis() - start1));
    }

    @Test
    public void test7() {
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(1);
        list.add(2);
        list.add(3);
        List<Integer> newList = list.stream().distinct().collect(Collectors.toList());
        // newList.forEach(System.out::println);

        Map<Integer, Long> map = list.stream().collect(Collectors.groupingBy(p -> p, Collectors.counting()));
        map.forEach((k, v) -> System.out.println(k + ":" + v));
    }

    @Test
    public void test8() {

    }

}
