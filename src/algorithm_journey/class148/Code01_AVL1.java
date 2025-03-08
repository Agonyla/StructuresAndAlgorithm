package algorithm_journey.class148;

/**
 * @author: Agony
 * @create: 2025/3/8 19:31
 * @describe: AVL树的实现(java版) 平衡二叉搜索树 Balanced Binary Tree
 * 静态数组实现
 * @link: <a href="https://www.luogu.com.cn/problem/P3369">普通平衡树</a>
 */
public class Code01_AVL1 {


    public static int MAXN = 100001;

    // 空间使用计数 (空间编号)
    public static int cnt = 0;

    // 整棵树的头节点编号
    public static int head = 0;

    // 节点的key
    public static int[] key = new int[MAXN];

    // 子树高度
    public static int[] height = new int[MAXN];

    // 左孩子 （存放左孩子的空间编号）
    public static int[] left = new int[MAXN];

    // 右孩子 （存放右孩子的空间编号）
    public static int[] right = new int[MAXN];

    // 节点key的计数
    public static int[] count = new int[MAXN];

    // 子树的数字总数
    public static int[] size = new int[MAXN];


    /**
     * 左旋
     *
     * @param i 当前节点（该子树的头节点）的空间编号
     * @return 左旋之后该子树头节点的空间编号
     */
    public static int leftRotate(int i) {


        // 步骤：
        // 1. 取出当前节点右孩子的空间节点，该节点作为新的头节点
        // -> r = right[i]
        // 2. 把右孩子的左子树 ( left[r] ) 挂到 左孩子的右树上 - 即当前节点的左树 ( right[i] )
        // -> right[i] = left[r]
        // 3. 当前节点 (i) 挂到 右孩子的左树上 ( left[r] )
        // -> left[r] = i
        // 4. 后续调整信息 如 height 和 size
        // -> up(i) 更新左旋之后的左树信息
        // 不需要更新 左旋之后的右树信息，因为其右树没有发生变化
        // -> up(r) 更新左旋之后的头节点信息
        // 5. 返回头节点的空间编号
        // -> return r;

        return 1;
    }


    /**
     * 右旋
     *
     * @param i 当前节点（该子树的头节点）的空间编号
     * @return 右旋之后该子树头节点的空间编号
     */
    public static int rightRotate(int i) {


        // 步骤：
        // 1. 取出当前节点左孩子的空间节点 该节点作为新的头节点
        // -> l = left[i]
        // 2. 把左孩子的右子树 ( right[l] ) 挂到 左孩子的左树上 - 即当前节点的左树 ( left[i] )
        // -> left[i] = right[l]
        // 3. 当前节点 (i) 挂到 左孩子的右树上 ( right[l] )
        // -> right[l] = i
        // 4. 后续调整信息 如 height 和 size
        // -> up(i) 更新右旋之后的左树信息
        // 不需要更新 右旋之后的右树信息，因为其左树没有发生变化
        // -> up(l) 更新右旋之后的头节点信息
        // 5. 返回头节点的空间编号
        // -> return l;

        return 1;
    }
}
