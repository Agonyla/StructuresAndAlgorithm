package algorithm_journey.leetcode;

/**
 * @author: Agony
 * @create: 2025/4/5 14:20
 * @describe: 执行所有后缀指令
 * <a href="https://leetcode.cn/problems/execution-of-all-suffix-instructions-staying-in-a-grid/?envType=company&envId=huawei&favoriteSlug=huawei-three-months">执行所有后缀指令</a>
 */
public class LeetCode2120 {


    public static void main(String[] args) {
        // 输入：n = 3, startPos = [0,1], s = "RRDDLU"
        // 输出：[1,5,4,3,1,0]

        // 输入：n = 2, startPos = [1,1], s = "LURD"
        // 输出：[4,1,0,0]

        // 输入：n = 1, startPos = [0,0], s = "LRUD"
        // 输出：[0,0,0,0]


        int n = 3;
        int[] startPos = {0, 1};
        String s = "RRDDLU";
        for (int i : executeInstructions(n, startPos, s)) {
            System.out.print(i + " ");
        }
        System.out.println();

        n = 2;
        startPos = new int[]{1, 1};
        s = "LURD";
        for (int i : executeInstructions(n, startPos, s)) {
            System.out.print(i + " ");
        }
        System.out.println();


        n = 1;
        startPos = new int[]{0, 0};
        s = "LRUD";

        for (int i : executeInstructions(n, startPos, s)) {
            System.out.print(i + " ");
        }
        System.out.println();
        // int n = 2;
        // int[] startPos = {0, 0};
        // String s = "RD";
        // for (int i : executeInstructions(n, startPos, s)) {
        //     System.out.print(i + " ");
        // }
        // System.out.println();


    }


    public static int[] executeInstructions(int n, int[] startPos, String s) {


        int[] ans = new int[s.length()];


        for (int i = 0; i < s.length(); i++) {

            int count = 0;
            int row = startPos[0];
            int col = startPos[1];


            for (int j = i; j < s.length(); j++) {

                char direction = s.charAt(j);
                if (direction == 'U') {
                    row--;
                } else if (direction == 'D') {
                    row++;
                } else if (direction == 'L') {
                    col--;
                } else { // 'R'
                    col++;
                }

                // 检查是否出界
                if (row < 0 || row >= n || col < 0 || col >= n) {
                    break;
                }
                count++;
            }
            ans[i] = count;
        }

        return ans;
    }

}
