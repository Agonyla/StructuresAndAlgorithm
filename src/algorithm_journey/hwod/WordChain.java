package algorithm_journey.hwod;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author: Agony
 * @create: 2025/4/3 16:47
 * @describe: 单词接龙
 */
public class WordChain {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);


        int place = scanner.nextInt();
        int total = scanner.nextInt();
        String[] strs = new String[total];
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < total; i++) {
            list.add(scanner.next());
        }

        String firstWord = strs[place];
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(firstWord);

        char lastWord = firstWord.charAt(firstWord.length() - 1);


    }


    // todo

    public static void process(StringBuilder sb, char startWord, List<String> list) {
        for (String s : list) {
            if (s.charAt(0) == startWord) {
                sb.append(s);
                list.remove(s);
            }
        }

    }
}
