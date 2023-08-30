package class13;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * @Author Agony
 * @Create 2023/8/30 19:35
 * @Version 1.0
 * 给定一个由字符串组成的数组strs，必须把所有的字符串拼接起来，返回所有可能的拼接结果中字典序最小的结果
 */
public class Code05_LowestLexicography {
    public static String lowestString1(String[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        TreeSet<String> process = process(arr);
        return process.isEmpty() ? "" : process.first();
    }

    public static TreeSet<String> process(String[] arr) {
        TreeSet<String> res = new TreeSet<>();
        if (arr.length == 0) {
            res.add("");
            return res;
        }
        for (int i = 0; i < arr.length; i++) {
            String first = arr[i];
            String[] nexts = removeIndexStr(arr, i);
            TreeSet<String> list = process(nexts);
            for (String s : list) {
                res.add(first + s);
            }
        }
        return res;
    }

    public static String[] removeIndexStr(String[] arr, int index) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        String[] res = new String[arr.length - 1];
        int arrIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i != index) {
                res[arrIndex++] = arr[i];
            }
        }
        return res;
    }


    public static class strComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return (o1 + o2).compareTo(o2 + o1);
        }
    }

    public static String lowestString2(String[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        Arrays.sort(arr, new strComparator());
        StringBuilder res = new StringBuilder();
        for (String s : arr) {
            res.append(s);
        }
        return res.toString();
    }


    // for test
    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 5);
            ans[i] = (Math.random() <= 0.5) ? (char) (65 + value) : (char) (97 + value);
        }
        return String.valueOf(ans);
    }

    // for test
    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }

    // for test
    public static String[] copyStringArray(String[] arr) {
        String[] ans = new String[arr.length];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = String.valueOf(arr[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int arrLen = 6;
        int strLen = 5;
        int testTimes = 10000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String[] arr1 = generateRandomStringArray(arrLen, strLen);
            String[] arr2 = copyStringArray(arr1);
            if (!lowestString1(arr1).equals(lowestString2(arr2))) {
                for (String str : arr1) {
                    System.out.print(str + ",");
                }
                System.out.println();
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
