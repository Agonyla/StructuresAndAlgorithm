package algorithm_journey.hwod;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author: Agony
 * @create: 2025/4/27 22:27
 * @describe: 火车进站
 * <a href="https://www.nowcoder.com/practice/97ba57c35e9f4749826dc3befaeae109?tpId=37&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37&difficulty=3&judgeStatus=&tags=&title=&gioEnter=menu">火车进站</a>
 */
public class HJ77 {


    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        process(nums, 0, new StringBuilder(), new Stack<>());

        list.sort(String::compareTo);
        for (String s : list) {
            System.out.println(s);
        }


    }

    private static final ArrayList<String> list = new ArrayList<>();


    /**
     * @param nums
     * @param i
     * @param sb
     * @param stack 已进站的火车
     */
    public static void process(int[] nums, int i, StringBuilder sb, Stack<Integer> stack) {
        // 所有火车都进站且全部出站了
        if (i == nums.length && stack.isEmpty()) {
            list.add(sb.toString().trim()); // 移除末尾多余的空格
            return;
        }

        // 选择1：下一列火车进站（如果还有未进站的火车）
        if (i < nums.length) {
            stack.push(nums[i]);
            // 递归
            process(nums, i + 1, new StringBuilder(sb), stack);
            // 回溯
            stack.pop();
        }

        // 选择2：栈顶火车出站（如果栈不为空）
        if (!stack.isEmpty()) {
            int train = stack.pop();
            if (sb.length() > 0) {
                sb.append(" ");
            }
            sb.append(train);

            // 递归
            process(nums, i, sb, stack);

            // 回溯
            stack.push(train);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
