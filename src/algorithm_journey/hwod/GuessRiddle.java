package algorithm_journey.hwod;

import java.util.*;

/**
 * @author: Agony
 * @create: 2025/4/3 13:23
 * @describe: 猜字谜
 */
public class GuessRiddle {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取谜面单词列表
        String riddlesLine = scanner.nextLine();
        String[] riddles = riddlesLine.split(",");

        // 读取谜底库单词列表
        String answersLine = scanner.nextLine();
        String[] answerLib = answersLine.split(",");

        // 存储结果
        List<String> results = new ArrayList<>();
        boolean anyMatched = false;

        // 处理每个谜面
        for (String riddle : riddles) {
            String matchedAnswer = null;

            // 尝试在谜底库中查找匹配的单词
            for (String candidate : answerLib) {
                if (isMatch(riddle, candidate)) {
                    matchedAnswer = candidate;
                    anyMatched = true;
                    break;  // 找到第一个匹配就停止
                }
            }

            // 添加结果
            if (matchedAnswer != null) {
                results.add(matchedAnswer);
            } else {
                results.add("not found");
            }
        }

        // 输出结果
        if (!anyMatched) {
            System.out.println("not found");
        } else {
            System.out.println(String.join(",", results));
        }

        scanner.close();
    }

    // 判断谜面和谜底是否匹配
    private static boolean isMatch(String riddle, String answer) {
        // 条件1：变换顺序后一样（字母相同但顺序可能不同）
        if (hasSameLetters(riddle, answer)) {
            return true;
        }

        // 条件2：字母去重后是一样的
        if (hasSameUniqueLetters(riddle, answer)) {
            return true;
        }

        return false;
    }

    // 判断两个单词是否包含相同的字母（考虑字母出现的次数）
    private static boolean hasSameLetters(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }

        // 统计每个字母出现的次数
        Map<Character, Integer> countMap1 = new HashMap<>();
        Map<Character, Integer> countMap2 = new HashMap<>();

        for (char c : word1.toCharArray()) {
            countMap1.put(c, countMap1.getOrDefault(c, 0) + 1);
        }

        for (char c : word2.toCharArray()) {
            countMap2.put(c, countMap2.getOrDefault(c, 0) + 1);
        }

        return countMap1.equals(countMap2);
    }

    // 判断两个单词去重后是否相同
    private static boolean hasSameUniqueLetters(String word1, String word2) {
        Set<Character> set1 = new HashSet<>();
        Set<Character> set2 = new HashSet<>();

        for (char c : word1.toCharArray()) {
            set1.add(c);
        }

        for (char c : word2.toCharArray()) {
            set2.add(c);
        }

        return set1.equals(set2);
    }
}
