package algorithm_journey.hwod;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author: Agony
 * @create: 2025/4/3 21:31
 * @describe:
 */
public class WordChain2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        int n = sc.nextInt();
        ArrayList<String> words = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            words.add(sc.next());
        }
        ArrayList<String> res = new ArrayList<>();
        String st = words.remove(k);
        res.add(st);
        LinkedList<String>[] dq = new LinkedList[505];
        for (int i = 0; i < 505; ++i) {
            dq[i] = new LinkedList<String>();
        }
        for (String s : words) {
            dq[s.charAt(0)].add(s);
        }
        for (int i = 0; i < 505; ++i) {
            dq[i].sort((String a, String b) -> {
                if (a.length() != b.length()) {
                    return b.length() - a.length();
                }
                return a.compareTo(b);
            });
        }
        while (true) {
            int sz = res.size();
            String now = res.get(sz - 1);
            int c = now.charAt(now.length() - 1);
            if (dq[c].isEmpty()) {
                break;
            }
            String s = dq[c].removeFirst();
            res.add(s);
        }
        String ans = String.join("", res);
        System.out.println(ans);


    }
}
