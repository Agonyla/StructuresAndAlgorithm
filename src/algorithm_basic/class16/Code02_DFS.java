package algorithm_basic.class16;

import java.util.HashSet;
import java.util.Stack;

/**
 * @Author Agony
 * @Create 2023/9/14 20:00
 * @Version 1.0
 * 图的深度优先遍历 => 借助栈实现，加入数据的时候就打印
 */
public class Code02_DFS {
    public static void dfs(Node start) {
        if (start == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        stack.add(start);
        set.add(start);
        System.out.println(start.value);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    stack.add(cur);
                    stack.add(next);
                    set.add(next);
                    System.out.println(next.value);
                    break;
                }
            }
        }
    }
}
