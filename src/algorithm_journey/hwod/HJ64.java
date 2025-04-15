package algorithm_journey.hwod;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author: Agony
 * @create: 2025/4/15 11:47
 * @describe: MP3光标位置
 * <a href="https://www.nowcoder.com/practice/eaf5b886bd6645dd9cfb5406f3753e15?tpId=37&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37&difficulty=3&judgeStatus=&tags=&title=&gioEnter=menu">MP3光标位置</a>
 */
public class HJ64 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        String s = scanner.nextLine();

        List<Integer> list = process(n, s);

        int times = Math.min(4, n);

        for (int i = 0; i < times; i++) {
            if (i == times - 1) {
                System.out.print(list.get(i));
            } else {
                System.out.print(list.get(i) + " ");
            }
        }
        System.out.println();
        System.out.println(list.get(list.size() - 1));
    }


    public static List<Integer> process(int n, String s) {

        ArrayList<Integer> list = new ArrayList<>();

        int start = 1;
        int current = 1;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'U') {
                if (current == 1) {
                    current = n;

                    // 只有往上翻页会有这种情况
                    if (n <= 4) {
                        start = 1;
                    } else {
                        start = Math.max(1, n - 3);
                    }

                } else {
                    current--;
                    if (current < start) {
                        start = current;
                    }
                }
            }
            if (c == 'D') {
                if (current == n) {
                    current = 1;
                    start = 1;
                } else {
                    current++;
                    if (current > start + 3) {
                        start = current - 3;
                    }
                }
            }
        }

        int times = Math.min(4, n);

        for (int i = start; i < start + times; i++) {
            list.add(i);
        }
        list.add(current);
        return list;
    }
}
