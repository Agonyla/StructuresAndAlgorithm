package class17;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Agony
 * @Create 2023/9/18 21:28
 * @Version 1.0
 * 打印一个字符串的全部排列
 * 打印一个字符串的全部排列，要求不要出现重复的排列
 * <p>
 * permutations : 全部排列(尝试)
 * permutations2 : 全部排列
 * permutations3 : 全部排列(不重复)
 */
public class Code03_PrintAllPermutations {


    public static List<String> permutations(String str) {
        String path = "";
        char[] chars = str.toCharArray();
        ArrayList<String> res = new ArrayList<>();
        ArrayList<Character> rest = new ArrayList<>();
        for (char ch : chars) {
            rest.add(ch);
        }
        process(rest, res, path);
        return res;
    }

    public static void process(ArrayList<Character> rest, List<String> res, String path) {

        // base case
        if (rest.isEmpty()) {
            res.add(path);
            return;
        }
        for (int i = 0; i < rest.size(); i++) {
            char ch = rest.get(i);
            rest.remove(i);
            process(rest, res, path + ch);
            rest.add(i, ch);
        }
    }

    public static List<String> permutations2(String str) {

        ArrayList<String> res = new ArrayList<>();
        char[] chars = str.toCharArray();
        process2(chars, res, 0);
        return res;
    }

    /**
     * @param chars 字符串数组
     * @param res   接受列表
     * @param index 从index位置开始
     */
    public static void process2(char[] chars, List<String> res, int index) {
        if (index == chars.length) {
            // 每次把数组转成字符串添加，而不是像之前路径的方式单个添加
            res.add(String.valueOf(chars));
            return;
        }
        // i 从 index 开始，将index和i交换，之后交换到最后，添加到列表
        for (int i = index; i < chars.length; i++) {
            swap(chars, index, i);
            process2(chars, res, index + 1);
            swap(chars, index, i);
        }
    }

    public static List<String> permutations3(String str) {

        ArrayList<String> res = new ArrayList<>();
        char[] chars = str.toCharArray();
        process3(chars, res, 0);
        return res;
    }

    public static void process3(char[] chars, List<String> res, int index) {
        // base case
        if (index == chars.length) {
            res.add(String.valueOf(chars));
            return;
        }
        // ASCII字符共256个， visited['a'] => visited[97]
        boolean[] visited = new boolean[256];
        for (int i = index; i < chars.length; i++) {
            // 当出现某个字符串之前已经有访问过的行为，则直接跳过
            if (!visited[chars[i]]) {
                visited[chars[i]] = true;
                swap(chars, index, i);
                process3(chars, res, index + 1);
                swap(chars, index, i);
            }
        }

    }

    public static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }


    public static void main(String[] args) {
        String str = "acc";
        List<String> str1 = permutations(str);
        for (String s : str1) {
            System.out.println(s);
        }

        System.out.println("========================");

        List<String> str2 = permutations2(str);
        for (String s : str2) {
            System.out.println(s);
        }

        System.out.println("========================");

        List<String> str3 = permutations3(str);
        for (String s : str3) {
            System.out.println(s);
        }
    }
}
