package algorithm_journey.hwod;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author: Agony
 * @create: 2025/4/14 13:11
 * @describe: 名字的漂亮度
 * <a href="https://www.nowcoder.com/practice/02cb8d3597cf416d9f6ae1b9ddc4fde3?tpId=37&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37&difficulty=3&judgeStatus=&tags=&title=&gioEnter=menu">名字的漂亮度</a>
 */
public class HJ45 {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        String[] names = new String[n];

        for (int i = 0; i < n; i++) {
            names[i] = scanner.nextLine();
        }


        HashMap<Character, Integer> map = new HashMap<>();

        PriorityQueue<Name> heap = new PriorityQueue<>((o1, o2) -> o2.times - o1.times);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < names[i].length(); j++) {
                char c = names[i].charAt(j);
                map.put(c, map.getOrDefault(c, 0) + 1);
            }
            for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                heap.offer(new Name(entry.getKey(), entry.getValue()));
            }
            int weight = 26;
            int ans = 0;
            while (!heap.isEmpty()) {
                ans += heap.poll().times * weight;
                weight--;
            }
            System.out.println(ans);
            map.clear();
        }


    }

    static class Name {
        Character c;
        int times;

        public Name(Character c, int times) {
            this.c = c;
            this.times = times;
        }

        @Override
        public boolean equals(Object obj) {
            Name o = (Name) obj;
            return o.c == this.c;
        }
    }
}
