package toArrayTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by nzm on 2017/1/18.
 */
public class ListRemoveSame {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("2");
       // list = removeSame(list);
        Set<String> set = new HashSet<>(list);
        list.clear();
        list.addAll(set);
        for (String s : list){
            System.out.println(s);
        }
    }

    public static List removeSame(List<String> list) {
        List<String> newList = new ArrayList<>();
        for (String s : list) {
            if (!newList.contains(s)) {
                newList.add(s);
            }
        }
        return newList;
    }
}
