package algorithm_journey.class016;

/**
 * @author Agony
 * @create 2024/5/27 18:58
 * @describe: 设计实现双端队列。 => 类似 MyCircularQueue2 的实现
 * <p>
 * 实现 MyCircularDeque 类:
 * <p>
 * MyCircularDeque(int k) ：构造函数,双端队列最大为 k 。
 * boolean insertFront()：将一个元素添加到双端队列头部。 如果操作成功返回 true ，否则返回 false 。
 * boolean insertLast() ：将一个元素添加到双端队列尾部。如果操作成功返回 true ，否则返回 false 。
 * boolean deleteFront() ：从双端队列头部删除一个元素。 如果操作成功返回 true ，否则返回 false 。
 * boolean deleteLast() ：从双端队列尾部删除一个元素。如果操作成功返回 true ，否则返回 false 。
 * int getFront() )：从双端队列头部获得一个元素。如果双端队列为空，返回 -1 。
 * int getRear() ：获得双端队列的最后一个元素。 如果双端队列为空，返回 -1 。
 * boolean isEmpty() ：若双端队列为空，则返回 true ，否则返回 false  。
 * boolean isFull() ：若双端队列满了，则返回 true ，否则返回 false 。
 * @link: <a href="https://leetcode.cn/problems/design-circular-deque/description/">设计循环双端队列</a>
 */
public class CircularDeque {


    /**
     * 数组实现
     * l和r指向数组的头节点和尾节点
     * size 表示当前队列的大小
     * limit 表示队列的容量
     */
    static class MyCircularDeque {

        public int[] queue;
        int l, r, size, limit;

        public MyCircularDeque(int k) {
            queue = new int[k];
            l = r = size = 0;
            limit = k;
        }

        /**
         * 将一个元素添加到双端队列头部
         *
         * @param value
         * @return
         */
        public boolean insertFront(int value) {
            if (isFull()) {
                return false;
            }
            // 如果队列为空就直接将 l 和 r 置 0
            // 不这么做的话可能出现 size==0时候，l=2,r=1这种情况
            // 或者直接让两数相等，但感觉还不如直接置0
            if (isEmpty()) {
                l = r = 0;
            } else {
                // 如果到 0 了就跳到数组尾
                l = l == 0 ? limit - 1 : l - 1;
            }
            size++;
            queue[l] = value;
            return true;
        }

        public boolean insertLast(int value) {
            if (isFull()) {
                return false;
            }
            if (isEmpty()) {
                l = r = 0;
            } else {
                r = r == limit - 1 ? 0 : r + 1;
            }
            size++;
            queue[r] = value;
            return true;
        }

        public boolean deleteFront() {
            if (isEmpty()) {
                return false;
            }
            l = l == limit - 1 ? 0 : l + 1;
            size--;
            return true;
        }

        public boolean deleteLast() {
            if (isEmpty()) {
                return false;
            }
            r = r == 0 ? limit - 1 : r - 1;
            size--;
            return true;
        }

        public int getFront() {
            if (isEmpty()) {
                return -1;
            }
            return queue[l];
        }

        public int getRear() {
            if (isEmpty()) {
                return -1;
            }
            return queue[r];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == limit;
        }
    }
}
