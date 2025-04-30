package algorithm_journey.hwod;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author: Agony
 * @create: 2025/4/29 10:22
 * @describe: 小红的双生数
 * <a href="https://www.nowcoder.com/practice/8350d9cf2c164fbe9515d8f6e4d38ecc?tpId=37&tqId=43267&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37&difficulty=3&judgeStatus=undefined&tags=&title=">小红的双生数</a>
 */
public class HJ129 {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();

        List<Integer> result = find(input);
        for (int num : result) {
            System.out.print(num);
        }
        System.out.println();
    }

    public static List<Integer> find(String s) {
        int n = s.length();
        List<Integer> ans = new ArrayList<>();

        if (n % 2 == 1) { // 奇数长度
            for (int i = 0; i < n + 1; i++) {
                ans.add(0);
            }
            for (int i = 1; i <= n; i += 2) {
                int val = ((i / 2) & 1) ^ 1;
                ans.set(i, val);
                ans.set(i - 1, val);
            }
        } else { // 偶数长度
            for (int i = 0; i < n; i++) {
                ans.add(0);
            }
            for (int i = 1; i < n; i += 2) {
                int a = s.charAt(i - 1) - '0';
                int b = s.charAt(i) - '0';
                if (a == b) {
                    ans.set(i, a);
                    ans.set(i - 1, a);
                } else {
                    int target = a * 10 + b;
                    for (int j = 0; j < 10; j++) {
                        if (j * 11 >= target) {
                            ans.set(i, j);
                            ans.set(i - 1, j);
                            break;
                        }
                    }
                    int k = 0;
                    for (int j = i + 2; j < n; j += 2) {
                        ans.set(j, k);
                        ans.set(j - 1, k);
                        k ^= 1;
                    }
                    break;
                }
            }
            for (int i = 2; i < n; i += 2) {
                if (ans.get(i).equals(ans.get(i - 1))) {
                    boolean found = false;
                    for (int j = i + 1; j >= 0; j -= 2) {
                        ans.set(j, ans.get(j) + 1);
                        ans.set(j - 1, ans.get(j - 1) + 1);
                        if (j > 2 && ans.get(j).equals(ans.get(j - 2))) {
                            ans.set(j, ans.get(j) + 1);
                            ans.set(j - 1, ans.get(j - 1) + 1);
                        }
                        if (ans.get(j) < 10) {
                            found = true;
                            int k = 0;
                            for (int p = j + 2; p < n; p += 2) {
                                ans.set(p, k);
                                ans.set(p - 1, k);
                                k ^= 1;
                            }
                            break;
                        }
                    }
                    if (!found) {
                        ans = new ArrayList<>();
                        for (int j = 0; j < n + 2; j++) {
                            ans.add(0);
                        }
                        for (int j = 1; j <= n + 1; j += 2) {
                            int val = ((j / 2) & 1) ^ 1;
                            ans.set(j, val);
                            ans.set(j - 1, val);
                        }
                        return ans;
                    }
                }
            }
        }
        return ans;
    }
}