package algorithm_journey.hwod;

import java.util.*;

/**
 * @author: Agony
 * @create: 2025/4/10 10:45
 * @describe: 购物单
 * <a href="https://www.nowcoder.com/practice/f9c6f980eeec43ef85be20755ddbeaf4?tpId=37&tqId=21239&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37&difficulty=3&judgeStatus=undefined&tags=&title=">购物单</a>
 */
public class HJ16 {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int pay = scanner.nextInt();  // 总钱数
        int m = scanner.nextInt();    // 物品数量

        // 商品信息: [价格, 重要度*价格, 依赖项]
        int[][] items = new int[m + 1][3];
        // 主件列表
        List<Integer> mainItems = new ArrayList<>();
        // 附件映射到主件
        Map<Integer, Integer> attachToMain = new HashMap<>();

        for (int i = 1; i <= m; i++) {
            items[i][0] = scanner.nextInt();  // 价格
            int p = scanner.nextInt();        // 重要度
            items[i][1] = p * items[i][0];    // 满意度 = 价格 * 重要度
            items[i][2] = scanner.nextInt();  // 依赖

            if (items[i][2] == 0) {
                mainItems.add(i);  // 是主件
            } else {
                attachToMain.put(i, items[i][2]);  // 附件映射到主件
            }
        }

        // 创建备忘录，避免重复计算
        int[][] memo = new int[m + 1][pay + 1];
        for (int i = 0; i <= m; i++) {
            Arrays.fill(memo[i], -1);
        }

        int result = process(1, pay, items, m, memo, mainItems, attachToMain);
        System.out.println(result);
    }

    /**
     * 递归求解最大满意度
     *
     * @param index        当前考虑的物品索引
     * @param remain       剩余金额
     * @param items        物品信息
     * @param m            物品总数
     * @param memo         备忘录
     * @param mainItems    主件列表
     * @param attachToMain 附件到主件的映射
     * @return 最大满意度
     */
    private static int process(int index, int remain, int[][] items, int m, int[][] memo,
                               List<Integer> mainItems, Map<Integer, Integer> attachToMain) {
        // 基本情况：没有更多物品或没有剩余金额
        if (index > m || remain <= 0) {
            return 0;
        }

        // 检查备忘录
        if (memo[index][remain] != -1) {
            return memo[index][remain];
        }

        // 跳过当前物品
        int max = process(index + 1, remain, items, m, memo, mainItems, attachToMain);

        // 考虑购买当前物品
        if (items[index][2] == 0) {  // 是主件
            if (remain >= items[index][0]) {  // 买得起
                // 买主件后的满意度
                int satisfaction = items[index][1];
                int newRemain = remain - items[index][0];

                // 查找可能的附件组合
                List<Integer> attachments = findAttachments(index, attachToMain, m);

                // 尝试主件的所有附件组合
                max = Math.max(max, tryAllAttachmentCombinations(
                        index, newRemain, satisfaction, attachments, items, m, memo, mainItems, attachToMain));
            }
        } else {  // 是附件，只能通过主件购买，此处跳过
            // 在考虑主件时会考虑其附件
        }

        // 存储结果到备忘录
        memo[index][remain] = max;
        return max;
    }

    /**
     * 尝试主件的所有附件组合
     */
    private static int tryAllAttachmentCombinations(int mainIndex, int remain, int baseSatisfaction,
                                                    List<Integer> attachments, int[][] items, int m,
                                                    int[][] memo, List<Integer> mainItems, Map<Integer, Integer> attachToMain) {
        // 先考虑只买主件
        int max = baseSatisfaction + process(mainIndex + 1, remain, items, m, memo, mainItems, attachToMain);

        // 尝试所有附件的组合（使用二进制表示）
        int attachSize = attachments.size();
        for (int mask = 1; mask < (1 << attachSize); mask++) {
            int cost = 0;
            int addSatisfaction = 0;

            // 计算当前组合的成本和满意度
            for (int i = 0; i < attachSize; i++) {
                if ((mask & (1 << i)) != 0) {  // 第i个附件被选中
                    int attachIndex = attachments.get(i);
                    cost += items[attachIndex][0];
                    addSatisfaction += items[attachIndex][1];
                }
            }

            // 检查是否能负担得起
            if (remain >= cost) {
                int totalSatisfaction = baseSatisfaction + addSatisfaction;
                int newRemain = remain - cost;
                max = Math.max(max, totalSatisfaction +
                        process(mainIndex + 1, newRemain, items, m, memo, mainItems, attachToMain));
            }
        }

        return max;
    }

    /**
     * 查找某个主件的所有附件
     */
    private static List<Integer> findAttachments(int mainItem, Map<Integer, Integer> attachToMain, int m) {
        List<Integer> result = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : attachToMain.entrySet()) {
            if (entry.getValue() == mainItem) {
                result.add(entry.getKey());
            }
        }
        return result;
    }
}
