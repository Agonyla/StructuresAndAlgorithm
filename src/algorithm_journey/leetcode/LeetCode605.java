package algorithm_journey.leetcode;

/**
 * @author: Agony
 * @create: 2025/4/6 15:20
 * @describe: 种花问题
 * <a href="https://leetcode.cn/problems/can-place-flowers/?envType=company&envId=huawei&favoriteSlug=huawei-three-months">种花问题</a>
 */
public class LeetCode605 {


    public static void main(String[] args) {
        int[] flowerbed = {1, 0, 0, 0, 1};
        System.out.println(canPlaceFlowers(flowerbed, 1));
        System.out.println(canPlaceFlowers(flowerbed, 2));
    }


    public static boolean canPlaceFlowers(int[] flowerbed, int n) {

        if (n == 0) {
            return true;
        }
        int length = flowerbed.length;

        boolean left;
        boolean right;
        int count = 0;
        for (int i = 0; i < length; i++) {

            if (flowerbed[i] == 0) {

                left = (i == 0) || flowerbed[i - 1] == 0;
                right = (i == length - 1) || flowerbed[i + 1] == 0;
                
                if (left && right) {
                    flowerbed[i] = 1;
                    count++;
                    if (count >= n) {
                        return true;
                    }
                }
            }
        }

        return count >= n;
    }
}
