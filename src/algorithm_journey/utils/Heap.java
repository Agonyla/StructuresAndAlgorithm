package algorithm_journey.utils;

/**
 * 自定义堆实现
 *
 * @author Agony
 * @create 2024/6/5 15:17
 */
public class Heap {


    public int length = 100001;
    public int[] heap;
    // 堆长度
    public int size;
    public String type;


    public static String BIG = "big";
    public static String SMALL = "small";

    public Heap(int length, String type) {
        this.length = length;
        heap = new int[this.length];
        this.size = 0;
        this.type = type;
    }

    public Heap(String type) {
        heap = new int[this.length];
        this.size = 0;
        this.type = type;
    }

    public void heapSort(int[] arr) {

    }


    public int[] generateHeap(int[] arr) {

        for (int i = arr.length - 1; i >= 0; i++) {
            heapify(i);
        }

        return heap;
    }

    private void heapify(int i) {
        int l = 2 * i + 1;

        while (l < size) {
            int r = l + 1;
            int best = r < size && compare(heap[r], heap[l]) ? r : l;
            if (compare(heap[best], heap[i])) {
                swap(i, best);
                i = best;
                l = 2 * i + 1;
            } else {
                break;
            }
        }
        // // 大根堆
        // if (BIG.equals(type)) {
        //     while (l < size) {
        //         int r = l + 1;
        //         int bigger = r < size && heap[l] < heap[r] ? r : l;
        //         if (heap[i] >= heap[bigger]) {
        //             break;
        //         } else {
        //             swap(i, bigger);
        //             i = bigger;
        //             l = 2 * i + 1;
        //         }
        //     }
        // }
        // // 小根堆
        // if (SMALL.equals(type)) {
        //     while (l < size) {
        //         int r = l + 1;
        //         int smaller = r < size && heap[l] > heap[r] ? r : l;
        //         if (heap[i] <= heap[smaller]) {
        //             break;
        //         } else {
        //             swap(i, smaller);
        //             i = smaller;
        //             l = 2 * i + 1;
        //         }
        //     }
        // }
    }

    // todo 增加 add、peak、heapInsert等方法


    public void heapInsert(int val) {

            
    }

    public void add(int val) {
        if (isFull()) {
            return;
        }
        heap[size++] = val;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("Heap is Empty");
        }
        int ans = heap[0];
        swap(0, --size);
        heapify(0);
        return ans;
    }

    public int peak() {
        if (isEmpty()) {
            throw new RuntimeException("Heap is Empty");
        }
        return heap[0];
    }


    private boolean compare(int x, int y) {
        return BIG.equals(type) ? x > y : x < y;
    }


    public boolean isFull() {
        return size == length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }


}





