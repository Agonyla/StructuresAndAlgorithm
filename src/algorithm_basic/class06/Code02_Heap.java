package algorithm_basic.class06;

/**
 * @Author Agony
 * @Create 2023/8/12 20:53
 * @Version 1.0
 * 用数组实现堆
 */
public class Code02_Heap {

    public static class MyHeap {
        private int[] heap;
        private int headSize;
        private int limit;

        public MyHeap(int limit) {
            heap = new int[limit];
            headSize = 0;
            this.limit = limit;
        }


        public boolean isEmpty() {
            return headSize == 0;
        }

        public boolean isFull() {
            return headSize == limit;
        }

        /**
         * @param value 加入的值
         *              往堆里添加值
         */
        public void push(int value) {
            if (isFull()) {
                throw new RuntimeException("heap is full");
            }
            heap[headSize++] = value;
            heapInsert(heap, headSize);
        }

        /**
         * @return 从堆里取最大值
         */
        public int pop() {
            if (isEmpty()) {
                throw new RuntimeException("full is empty");
            }
            int res = heap[0];
            swap(heap, 0, --headSize);
            heapify(heap, 0, headSize);
            return res;
        }

        /**
         * @param arr   被插入的数组
         * @param index 插入值的位置
         *              在数组的index位置添加了值
         *              任意节点的父节点 = (i - 1) / 2
         */
        public void heapInsert(int[] arr, int index) {
            // index = 0 时
            // (0 - 1) / 2 = 0
            while (arr[index] > arr[(index - 1) / 2]) {
                swap(arr, index, arr[(index - 1) / 2]);
                index = arr[(index - 1) / 2];
            }
        }

        /**
         * @param arr      被插入的数组
         * @param index    插入值的位置
         * @param headSize heapSize大小
         *                 在数组位置插入较小值时使其往下沉
         */
        public void heapify(int[] arr, int index, int headSize) {
            int left = index * 2 + 1;
            while (left < headSize) {
                int largest = left + 1 < headSize && arr[left + 1] > arr[left] ? left + 1 : left;
                largest = arr[index] > arr[largest] ? index : largest;
                if (largest == index) {
                    break;
                }
                swap(arr, index, largest);
                index = largest;
                left = index * 2 + 1;
            }
        }

        public static void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}
