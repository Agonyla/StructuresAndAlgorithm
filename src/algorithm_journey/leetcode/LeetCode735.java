package algorithm_journey.leetcode;

import java.util.Stack;

/**
 * @author: Agony
 * @create: 2025/4/6 15:30
 * @describe: 小行星碰撞
 * <a href="https://leetcode.cn/problems/asteroid-collision/?envType=company&envId=huawei&favoriteSlug=huawei-three-months">小行星碰撞</a>
 */
public class LeetCode735 {


    public static void main(String[] args) {

        int[] asteroids = {5, 10, -5};
        for (int i : asteroidCollision(asteroids)) {
            System.out.println(i + " ");
        }
        System.out.println();


        asteroids = new int[]{8, -8};
        for (int i : asteroidCollision(asteroids)) {
            System.out.println(i + " ");
        }
        System.out.println();


        asteroids = new int[]{10, 2, -5};
        for (int i : asteroidCollision(asteroids)) {
            System.out.println(i + " ");
        }
        System.out.println();

    }

    public static int[] asteroidCollision(int[] asteroids) {


        Stack<Integer> stack = new Stack<>();


        for (int asteroid : asteroids) {

            boolean destroyed = false;

            while (!stack.isEmpty() && asteroid < 0 && stack.peek() > 0) {

                if (stack.peek() < -asteroid) {
                    stack.pop();
                    continue;
                } else if (stack.peek() == -asteroid) {
                    stack.pop();
                    destroyed = true;
                } else {
                    destroyed = true;
                }
                break;
            }

            if (!destroyed) {
                stack.push(asteroid);
            }
        }

        int[] ans = new int[stack.size()];

        for (int i = ans.length - 1; i >= 0; i--) {
            ans[i] = stack.pop();
        }
        return ans;
    }

}
