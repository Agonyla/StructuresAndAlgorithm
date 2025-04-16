package algorithm_journey.hwod;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author: Agony
 * @create: 2025/4/16 11:01
 * @describe: 24点游戏算法
 * <a href="https://www.nowcoder.com/practice/fbc417f314f745b1978fc751a54ac8cb?tpId=37&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37&difficulty=3&judgeStatus=&tags=&title=&gioEnter=menu">HJ67 24点游戏算法</a>
 */
public class HJ67 {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        int[] nums = new int[4];
        for (int i = 0; i < 4; i++) {
            nums[i] = scanner.nextInt();
        }

        boolean result = canGet24(nums);
        System.out.println(result);
        scanner.close();
    }

    // 主函数：判断是否能得到24
    private static boolean canGet24(int[] nums) {
        List<Double> list = new ArrayList<>();
        for (int num : nums) {
            list.add((double) num);
        }
        return solve(list);
    }

    // 递归求解函数
    private static boolean solve(List<Double> nums) {
        if (nums.size() == 1) {
            // 考虑浮点数精度问题，使用接近判断
            return Math.abs(nums.get(0) - 24.0) < 1e-6;
        }

        // 选择两个数进行运算
        for (int i = 0; i < nums.size(); i++) {
            for (int j = 0; j < nums.size(); j++) {
                if (i != j) {
                    // 创建新列表，移除将要计算的两个数
                    List<Double> next = new ArrayList<>();
                    for (int k = 0; k < nums.size(); k++) {
                        if (k != i && k != j) {
                            next.add(nums.get(k));
                        }
                    }

                    // 尝试四种运算
                    double a = nums.get(i);
                    double b = nums.get(j);

                    // 加法
                    next.add(a + b);
                    if (solve(next)) return true;
                    next.remove(next.size() - 1);

                    // 减法（注意顺序）
                    next.add(a - b);
                    if (solve(next)) return true;
                    next.remove(next.size() - 1);

                    // 乘法
                    next.add(a * b);
                    if (solve(next)) return true;
                    next.remove(next.size() - 1);

                    // 除法（注意除数不能为0）
                    if (Math.abs(b) > 1e-6) {
                        next.add(a / b);
                        if (solve(next)) return true;
                        next.remove(next.size() - 1);
                    }
                }
            }
        }

        return false;
    }
}
