package algorithm_journey.hwod;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

/**
 * @author: Agony
 * @create: 2025/4/14 10:39
 * @describe: 字符串加密
 * <a href="https://www.nowcoder.com/practice/e4af1fe682b54459b2a211df91a91cf3?tpId=37&tqId=21259&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37&difficulty=3&judgeStatus=undefined&tags=&title=">字符串加密</a>
 */
public class HJ36 {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String s = scanner.nextLine();

        String t = scanner.nextLine();

        Map<Integer, Character> map = getMap(s);
        HashMap<Character, Integer> map2 = new HashMap<>();


        // for (Map.Entry<Integer, Character> integerCharacterEntry : map.entrySet()) {
        //     System.out.print(integerCharacterEntry.getValue());
        // }
        // System.out.println();


        for (int i = 0; i < 26; i++) {
            char c = (char) ('a' + i);
            map2.put(c, i);
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            Integer index = map2.get(c);
            sb.append(map.get(index));
        }
        String ans = sb.toString();
        System.out.println(ans);

    }


    public static Map<Integer, Character> getMap(String s) {

        HashMap<Integer, Character> map = new HashMap<>();
        HashSet<Character> set = new HashSet<>();

        int position = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!set.contains(c)) {
                map.put(position++, c);
                set.add(c);
            }
        }

        for (int i = 0; i < 26; i++) {
            char c = (char) ('a' + i);
            if (!set.contains(c)) {
                map.put(position++, c);
                set.add(c);
            }
        }
        return map;
    }
}
