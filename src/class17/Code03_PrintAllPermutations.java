package class17;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Agony
 * @Create 2023/9/18 21:28
 * @Version 1.0
 * 打印一个字符串的全部排列
 * 打印一个字符串的全部排列，要求不要出现重复的排列
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

    public static void main(String[] args) {
        String str = "abc";
        List<String> str1 = permutations(str);
        for (String s : str1) {
            System.out.println(s);
        }
    }
}
