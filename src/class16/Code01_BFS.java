package class16;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * @Author Agony
 * @Create 2023/9/11 21:47
 * @Version 1.0
 * 图的广度优先遍历 => 借助队列实现，在弹出的时候打印
 */
public class Code01_BFS {

    public static void bfs(Node start) {
        if (start == null) {
            return;
        }
        LinkedList<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();
        queue.add(start);
        set.add(start);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println(cur.value);
            for (Node next : start.nexts) {
                if (!set.contains(next)) {
                    queue.add(next);
                    set.add(next);
                }
            }
        }
    }
}
