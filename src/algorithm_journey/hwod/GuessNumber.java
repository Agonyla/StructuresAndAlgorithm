package algorithm_journey.hwod;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author: Agony
 * @create: 2025/4/3 13:01
 * @describe: 猜数字
 */
public class GuessNumber {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取猜谜次数
        int N = scanner.nextInt();
        scanner.nextLine(); // 读取换行符

        // 存储所有猜测和提示信息
        String[] guesses = new String[N];
        int[] aCount = new int[N];
        int[] bCount = new int[N];

        // 读取每次猜测和提示
        for (int i = 0; i < N; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            guesses[i] = parts[0];
            String hint = parts[1];

            // 解析XaYb格式的提示
            aCount[i] = Character.getNumericValue(hint.charAt(0));
            bCount[i] = Character.getNumericValue(hint.charAt(2));
        }

        // 关闭扫描器
        scanner.close();

        // 检查从0000到9999的所有可能数字
        List<String> possibleAnswers = new ArrayList<>();

        for (int num = 0; num <= 9999; num++) {
            // 将数字格式化为4位数字字符串
            String candidate = String.format("%04d", num);

            boolean matchesAllHints = true;

            // 检查候选答案是否符合所有提示
            for (int i = 0; i < N && matchesAllHints; i++) {
                int[] result = checkGuess(candidate, guesses[i]);

                // 如果当前提示与预期不符，则跳过该候选答案
                if (result[0] != aCount[i] || result[1] != bCount[i]) {
                    matchesAllHints = false;
                }
            }

            // 如果候选答案符合所有提示，添加到可能答案列表
            if (matchesAllHints) {
                possibleAnswers.add(candidate);
            }
        }

        // 根据可能答案数量输出结果
        if (possibleAnswers.size() == 1) {
            System.out.println(possibleAnswers.get(0));
        } else {
            System.out.println("NA");
        }
    }

    // 计算猜测和答案之间的A和B数量
    private static int[] checkGuess(String answer, String guess) {
        int a = 0; // 位置和数字都正确的数量
        int b = 0; // 数字正确但位置不对的数量

        // 统计答案和猜测中每个数字出现的次数
        int[] answerCount = new int[10];
        int[] guessCount = new int[10];

        // 首先计算位置和数字都正确的数量(A)
        for (int i = 0; i < 4; i++) {
            char ansChar = answer.charAt(i);
            char guessChar = guess.charAt(i);

            if (ansChar == guessChar) {
                a++;
            } else {
                // 记录未匹配的数字出现次数
                answerCount[ansChar - '0']++;
                guessCount[guessChar - '0']++;
            }
        }

        // 计算数字正确但位置不对的数量(B)
        for (int i = 0; i < 10; i++) {
            // 取两个数组中该数字出现次数的较小值，即为共有的数量
            b += Math.min(answerCount[i], guessCount[i]);
        }

        return new int[]{a, b};
    }
}
