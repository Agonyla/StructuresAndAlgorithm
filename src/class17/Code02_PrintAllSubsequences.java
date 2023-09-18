package class17;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @Author Agony
 * @Create 2023/9/18 20:58
 * @Version 1.0
 * 打印一个字符串的全部子序列
 * 打印一个字符串的全部子序列，要求不要出现重复字面值的子序列
 */
public class Code02_PrintAllSubsequences {

    public static List<String> subs(String str) {
        ArrayList<String> res = new ArrayList<>();

        char[] chars = str.toCharArray();
        String path = "";
        process(chars, res, 0, path);
        return res;
    }

    /**
     * @param chars 字符串数组
     * @param res   存储的list
     * @param index 从index位置开始
     * @param path  从 0 到 index 的子序列
     */
    public static void process(char[] chars, List<String> res, int index, String path) {
        // base case
        if (index == chars.length) {
            res.add(path);
            return;
        }
        // path 加 index 位置的值
        process(chars, res, index + 1, path + chars[index]);
        // path 不加 index 位置的值
        process(chars, res, index + 1, path);
    }


    public static List<String> subsNoRepeat(String str) {
        HashSet<String> set = new HashSet<>();
        char[] chars = str.toCharArray();
        String path = "";
        processNoRepeat(chars, set, 0, path);

        return new ArrayList<>(set);

    }

    /**
     * @param chars 字符串数组
     * @param res   存储的list
     * @param index 从index位置开始
     * @param path  从 0 到 index 的子序列
     */
    public static void processNoRepeat(char[] chars, HashSet<String> res, int index, String path) {
        // base case
        if (index == chars.length) {
            res.add(path);
            return;
        }
        // path 加 index 位置的值
        processNoRepeat(chars, res, index + 1, path + chars[index]);
        // path 不加 index 位置的值
        processNoRepeat(chars, res, index + 1, path);
    }

    public static void main(String[] args) {
        String str = "acc";
        List<String> subs = subs(str);
        for (String sub : subs) {
            if (sub.isEmpty()) {
                System.out.println("空");
            } else {
                System.out.println(sub);
            }
        }
        System.out.println("============");

        List<String> str2 = subsNoRepeat(str);
        for (String s : str2) {
            if (s.isEmpty()) {
                System.out.println("空");
            } else {
                System.out.println(s);
            }
        }
        System.out.println("=============");
    }
}

