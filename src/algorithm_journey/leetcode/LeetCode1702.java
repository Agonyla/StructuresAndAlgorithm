package algorithm_journey.leetcode;

/**
 * @author: Agony
 * @create: 2025/4/8 10:18
 * @describe: 修改后的最大二进制字符串
 * <a href="https://leetcode.cn/problems/maximum-binary-string-after-change/?envType=company&envId=huawei&favoriteSlug=huawei-three-months">修改后的最大二进制字符串</a>
 */
public class LeetCode1702 {


    public static void main(String[] args) {
        System.out.println(maximumBinaryString("000110"));
    }

    // todo 再看一遍

    public static String maximumBinaryString(String binary) {
        // 处理特殊情况
        if (binary == null || binary.length() <= 1) {
            return binary;
        }

        int n = binary.length();
        char[] chars = binary.toCharArray();

        // 统计首个0的位置
        int firstZeroPos = -1;
        for (int i = 0; i < n; i++) {
            if (chars[i] == '0') {
                firstZeroPos = i;
                break;
            }
        }

        // 如果没有0，字符串已经是最大的，直接返回
        if (firstZeroPos == -1) {
            return binary;
        }

        // 统计0的总数
        int zeroCount = 0;
        for (int i = firstZeroPos; i < n; i++) {
            if (chars[i] == '0') {
                zeroCount++;
            }
        }

        // 如果只有一个0，字符串已经是最大的，直接返回
        if (zeroCount <= 1) {
            return binary;
        }

        // 构建最优解
        // 策略：将所有0集中到一起，只保留一个0，其余全部变为1
        StringBuilder result = new StringBuilder();

        // 前导1保持不变
        for (int i = 0; i < firstZeroPos; i++) {
            result.append('1');
        }

        // 将所有位置设为1，只在特定位置保留一个0
        for (int i = firstZeroPos; i < n; i++) {
            result.append('1');
        }

        // 唯一的0应该放在：首个0的位置 + 0的总数 - 1
        // 这样可以保证前面有尽可能多的1，后面全是1
        int zeroPosition = firstZeroPos + zeroCount - 1;
        if (zeroPosition < n) {
            result.setCharAt(zeroPosition, '0');
        }

        return result.toString();
    }
}
