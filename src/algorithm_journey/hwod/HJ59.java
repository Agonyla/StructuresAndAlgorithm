package algorithm_journey.hwod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * @author: Agony
 * @create: 2025/4/15 10:59
 * @describe: 找出字符串中第一个只出现一次的字符
 * <a href="https://www.nowcoder.com/practice/e896d0f82f1246a3aa7b232ce38029d4?tpId=37&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37&difficulty=3&judgeStatus=&tags=&title=&gioEnter=menu">找出字符串中第一个只出现一次的字符</a>
 */
public class HJ59 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        List<Character> list = getFirstChar(s);
        if (list.isEmpty()) {
            System.out.println(-1);
        } else {
            System.out.println(list.get(0));
        }

        // ArrayList<Character> list = new ArrayList<>();
        // HashMap<Character, Integer> map = new HashMap<>();
        // map.put('a', 2);
        // map.put('b', 2);
        // map.put('c', 1);
        //
        //
        // list.add('a');
        // list.add('b');
        // list.add('c');
        //
        // list.removeIf(character -> {
        //     Integer times = map.get(character);
        //     return times > 1;
        // });
        //
        // for (Character c : list) {
        //     System.out.println(c);
        // }

    }

    public static List<Character> getFirstChar(String s) {


        // 记录出现的频次
        HashMap<Character, Integer> map = new HashMap<>();
        ArrayList<Character> list = new ArrayList<>();


        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
            list.add(c);
        }

        list.removeIf(character -> {
            Integer times = map.get(character);
            return times > 1;
        });

        return list;
    }
}
