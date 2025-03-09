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

    // 节点的key （存放的数字）
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


    // LL型 -> 当前节点的左树高度 - 右树高度 > 1 且 左树的左孩子高度 >= 左树的右孩子高度  -> 对当前节点做 右旋
    // RR型 -> 当前节点的右树高度 - 左树高度 > 1 且 右树的右孩子高度 >= 右树的左孩子高度  -> 对当前节点做 左旋

    // LR型 -> 当前节点的左树高度 - 右树高度 > 1 且 左树的左孩子高度 < 左树的右孩子高度   -> 对当前节点的左树做 左旋，对但前节点做 右旋
    // RL型 -> 当前节点的右树高度 - 左树高度 > 1 且 右树的右孩子高度 < 右树的左孩子高度  -> 对当前节点的右树做 右旋，对当前节点做 左旋


    /**
     * 更新信息
     *
     * @param i 头节点编号
     */
    public static void up(int i) {

        // 更新size
        size[i] = size[right[i]] = size[left[i]] + count[i];

        // 更新树高
        height[i] = Math.max(height[right[i]], height[left[i]]) + 1;
    }


    /**
     * 左旋
     * 左旋会使当前节点会来到右孩子的左边，所以要把右孩子的左孩子 挂到 当前节点的右边
     *
     * @param i 当前节点（该子树的头节点）的空间编号
     * @return 左旋之后该子树头节点的空间编号
     */
    public static int leftRotate(int i) {


        // 步骤：
        // 1. 取出当前节点右孩子的空间节点，该节点作为新的头节点
        // -> r = right[i]
        // 2. 把右孩子的左子树 ( left[r] ) 挂到 当前节点的右树 ( right[i] )
        // -> right[i] = left[r]
        // 3. 当前节点 (i) 挂到 右孩子的左树上 ( left[r] )
        // -> left[r] = i
        // 4. 后续调整信息 如 height 和 size
        // -> up(i) 更新左旋之后的左树信息
        // 不需要更新 左旋之后的右树信息，因为其右树没有发生变化
        // -> up(r) 更新左旋之后的头节点信息
        // 5. 返回头节点的空间编号
        // -> return r;


        int r = right[i];
        


        return 1;
    }


    /**
     * 右旋
     * 右旋会使当前节点来到左孩子的右边，所以要把左孩子的右孩子 挂到 当前节点的左边
     *
     * @param i 当前节点（该子树的头节点）的空间编号
     * @return 右旋之后该子树头节点的空间编号
     */
    public static int rightRotate(int i) {


        // 步骤：
        // 1. 取出当前节点左孩子的空间节点 该节点作为新的头节点
        // -> l = left[i]
        // 2. 把左孩子的右子树 ( right[l] ) 挂到 当前节点的左树 ( left[i] )
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


    /**
     * 检查i为节点作为头节点的树是否违规 -> 保持整个树的平衡
     *
     * @param i 头节点的空间编号
     * @return 调整完之后的头节点的空间编号
     */
    public static int maintain(int i) {

        // 1. 比较左右树高度
        // -> lh = height[left[i]], rh = height[right[i]]
        // 2. lh - rh > 1
        // 2.1 左树的左孩子高度 >= 左树的右孩子高度 -> LL
        // 2.2 左树的左孩子高度 < 左树的右孩子高度  -> LR
        // 3. rh - lh > 1
        // 3.1 右树的右孩子高度 >= 右树的左孩子高度 -> RR
        // 3.2 右树的右孩子高度 < 右树的左孩子高度  -> RL


        return 1;
    }

    /**
     * 添加数字操作，重复加入算多个词频
     *
     * @param num
     */
    public static void add(int num) {

    }


    /**
     * 添加操作
     *
     * @param i   当前节点的空间编号
     * @param num 添加的数字
     * @return 返回头节点的空间编号
     */
    public static int add(int i, int num) {

        // 空间编号为0，表示没有添加过节点，添加并返回
        if (i == 0) {
            key[++cnt] = num;
            count[cnt] = size[cnt] = height[cnt] = 1;
            return cnt;
        }
        // 该节点的值和 num相同，直接加入词频
        if (key[i] == num) {
            count[i]++;
        } else if (key[i] < num) {
            right[i] = add(right[i], num);
        } else {
            left[i] = add(left[i], num);
        }
        // 更新信息
        up(i);
        // 维持平衡
        return maintain(i);
    }


    //
}
