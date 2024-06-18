package algorithm_journey.class035;

/**
 * @author: Agony
 * @create: 2024/6/18 14:51
 * @describe:
 * @link:
 */
public class Code07_AllO1 {

    // todo

    // 全O(1)的数据结构
    // 双向链变和 hashmap 实现
    /*
        class Bucket {
			public HashSet<String> set;
			public int cnt;
			public Bucket last;
			public Bucket next;

			public Bucket(String s, int c) {
				set = new HashSet<>();
				set.add(s);
				cnt = c;
			}
		}
		hashmap<String, Bucket>
     */
    // 整条链变的头节点 head，存放 "", 出现次数为 0
    // 整条链变的尾节点 tail，存放 "", 出现次数为 Integer.MAX_VALUE
    // 加入操作
    // 看 hashmap 是否含有该元素
    // 1) 没有
    // 判断 head 的 next 节点的频次是否为 1，没有就新建，有的话就加入
    // 2) 含有
    // 判断 该节点后面有没有出现频次+1的桶，没有就新建，有就加入
    // 加入完成之后需要删掉之前出现频次桶中的该元素，相当于如果 "a"出现了8次，又添加了"a"，那我就把 8 次中的"a"删掉，加入到 9 次中的桶
    //
    // 删除操作 类似
}
