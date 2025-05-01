package algorithm_journey.hwod;

import java.util.HashMap;

/**
 * @author: Agony
 * @create: 2025/5/1 14:53
 * @describe: 两数之和
 * <a href="https://www.nowcoder.com/practice/20ef0972485e41019e39543e8e895b7f?tpId=196&tqId=37090&rp=1&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D196&difficulty=undefined&judgeStatus=undefined&tags=&title=nc61">两数之和</a>
 */
public class NC61 {

    public static void main(String[] args) {


    }

    public int[] twoSum(int[] numbers, int target) {
        // write code here
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < numbers.length; i++) {
            if (!map.containsKey(target - numbers[i])) {
                map.put(numbers[i], i + 1);
            } else {
                return new int[]{map.get(target - numbers[i]), i + 1};
            }
        }
        return new int[0];
    }
}
