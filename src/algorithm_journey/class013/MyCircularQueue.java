package algorithm_journey.class013;

/**
 * @author Agony
 * @create 2024/5/25 19:30
 * @describe: 设计你的循环队列实现。
 * 循环队列是一种线性数据结构，其操作表现基于 FIFO（先进先出）原则并且队尾被连接在队首之后以形成一个循环。它也被称为“环形缓冲器”。
 * 循环队列的一个好处是我们可以利用这个队列之前用过的空间。
 * 在一个普通队列里，一旦一个队列满了，我们就不能插入下一个元素，即使在队列前面仍有空间。但是使用循环队列，我们能使用这些空间去存储新的值。
 * <p>
 * 你的实现应该支持如下操作：
 * MyCircularQueue(k): 构造器，设置队列长度为 k 。
 * Front: 从队首获取元素。如果队列为空，返回 -1 。
 * Rear: 获取队尾元素。如果队列为空，返回 -1 。
 * enQueue(value): 向循环队列插入一个元素。如果成功插入则返回真。
 * deQueue(): 从循环队列中删除一个元素。如果成功删除则返回真。
 * isEmpty(): 检查循环队列是否为空。
 * isFull(): 检查循环队列是否已满。
 * @link: <a href="https://leetcode.cn/problems/design-circular-queue/description/">设计循环队列</a>
 */
public class MyCircularQueue {

    public int[] queue;
    int l;
    int r;
    int size;
    int limit;

    public MyCircularQueue(int k) {
        queue = new int[k];
        l = r = size = 0;
        limit = k;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == limit;
    }

    public int Front() {
        if (isEmpty()) {
            return -1;
        }
        return queue[l];
    }

    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        int last = r == 0 ? limit - 1 : (r - 1);
        return queue[last];
    }

    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        queue[r] = value;
        r = r == limit - 1 ? 0 : r + 1;
        size++;
        return true;
    }

    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        l = l == limit - 1 ? 0 : l + 1;
        size--;
        return true;
    }

    public static void main(String[] args) {
        MyCircularQueue q = new MyCircularQueue(3);
        boolean b1 = q.enQueue(1);
        boolean b2 = q.enQueue(2);
        boolean b3 = q.enQueue(3);
        boolean b4 = q.enQueue(4);
        int rear = q.Rear();
        boolean full = q.isFull();
        boolean b = q.deQueue();
        boolean b5 = q.enQueue(4);
        int rear1 = q.Rear();

        System.out.println("b1 = " + b1);
        System.out.println("b2 = " + b2);
        System.out.println("b3 = " + b3);
        System.out.println("rear = " + rear);
        System.out.println("full = " + full);
        System.out.println("b = " + b);
        System.out.println("b5 = " + b5);
        System.out.println("rear1 = " + rear1);


    }
}
