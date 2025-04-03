package algorithm_journey.hwod;

import java.util.Scanner;

/**
 * @author: Agony
 * @create: 2025/4/3 16:47
 * @describe: 单词接龙
 * 按照题目要求是贪心，不用回溯
 */
public class WordChain {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int startWord = scanner.nextInt();

        int total = scanner.nextInt();

        String[] words = new String[total];

        for (int i = 0; i < total; i++) {
            words[i] = scanner.next();
        }

        String ans = findLongestWordChain(words, startWord);
        System.out.println(ans);


    }


    /**
     * 找到最长的单词链
     *
     * @param words
     * @param startIndex
     * @return
     */
    public static String findLongestWordChain(String[] words, int startIndex) {

        int N = words.length;
        boolean[] used = new boolean[N];


        used[startIndex] = true;


        StringBuilder sb = new StringBuilder(words[startIndex]);

        char lastChar = words[startIndex].charAt(words[startIndex].length() - 1);

        while (true) {

            String nextWord = fineNextWord(words, used, lastChar);
            if (nextWord.isEmpty()) {
                break;
            }

            sb.append(nextWord);
            lastChar = nextWord.charAt(nextWord.length() - 1);
        }
        return sb.toString();

    }


    /**
     * 查找下一个单词
     *
     * @param words
     * @param used
     * @param startWord
     * @return
     */
    public static String fineNextWord(String[] words, boolean[] used, char startWord) {

        String bestWord = "";
        int maxLength = -1;
        int index = -1;

        for (int i = 0; i < words.length; i++) {
            String currentWord = words[i];
            if (!used[i] && currentWord.charAt(0) == startWord) {

                if (currentWord.length() > maxLength) {
                    maxLength = currentWord.length();
                    bestWord = currentWord;
                    index = i;
                    // 长度一样则按照字典序
                } else if (currentWord.length() == maxLength && currentWord.compareTo(bestWord) < 0) {
                    bestWord = currentWord;
                    index = i;
                }
            }
        }
        if (!bestWord.isEmpty()) {
            used[index] = true;
        }
        return bestWord;
    }
}
