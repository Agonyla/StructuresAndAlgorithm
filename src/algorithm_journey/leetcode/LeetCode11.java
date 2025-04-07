package algorithm_journey.leetcode;

/**
 * @author: Agony
 * @create: 2025/4/7 10:13
 * @describe: 盛最多水的容器
 * <a href="https://leetcode.cn/problems/container-with-most-water/description/?envType=company&envId=huawei&favoriteSlug=huawei-three-months">盛最多水的容器</a>
 */
public class LeetCode11 {

    public static void main(String[] args) {
        
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(maxArea(height));
    }


    public static int maxArea(int[] height) {

        int left = 0;
        int right = height.length - 1;
        int max = 0;

        while (left < right) {

            int currentArea = (right - left) * Math.min(height[left], height[right]);
            max = Math.max(max, currentArea);

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return max;
    }
}
