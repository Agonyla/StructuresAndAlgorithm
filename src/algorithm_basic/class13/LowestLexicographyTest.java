package algorithm_basic.class13;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Agony
 * @Create 2023/8/28 21:47
 * @Version 1.0
 * // 字符串全排序
 */
public class LowestLexicographyTest {

    public static List<String> process(String[] strings) {
        ArrayList<String> list = new ArrayList<>();
        if (strings.length == 0) {
            list.add("");
            return list;
        }
        for (int i = 0; i < strings.length; i++) {
            String first = strings[i];
            String[] nexts = removeIndexStr(strings, i);
            List<String> next = process(nexts);
            for (String s : next) {
                list.add(first + s);
            }
        }
        return list;

    }

    public static String[] removeIndexStr(String[] arr, int index) {
        int N = arr.length;
        String[] strings = new String[N - 1];
        int strIndex = 0;
        for (int i = 0; i < N; i++) {
            if (i != index) {
                strings[strIndex++] = arr[i];
            }
        }
        return strings;
    }

    public static void main(String[] args) {
        String[] strings = {"a", "b", "c"};
        // String[] strings1 = removeIndexStr(strings, 1);
        // for (String s : strings1) {
        //     System.out.println(s);
        // }
        List<String> list = process(strings);
        for (String s : list) {
            System.out.println(s);
        }
    }
}
