package class07;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * @Author Agony
 * @Create 2023/8/16 10:28
 * @Version 1.0
 * 加强堆
 * T一定要是非基础类型，有基础类型需求包一层 在ArrayList和HashMap中加Inner<T>
 */
public class HeapGreater<T> {

    private ArrayList<T> heap;
    private HashMap<T, Integer> indexMap;
    private int heapSize;
    private Comparator<? super T> comparator;

    public HeapGreater(Comparator<? super T> comparator) {
        this.comparator = comparator;
        this.heap = new ArrayList<>();
        this.indexMap = new HashMap<>();
        this.heapSize = 0;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public int getSize() {
        return heapSize;
    }

    public boolean contains(T obj) {
        return indexMap.containsKey(obj);
    }

    public T peek() {
        return heap.get(0);
    }

    public void push(T obj) {
        heap.add(obj);
        indexMap.put(obj, heapSize);
        heapInsert(heapSize++);
    }

    public T pop() {
        T obj = heap.get(0);
        swap(0, heapSize - 1);
        heapSize--;
        indexMap.remove(obj);
        heap.remove(heapSize);
        heapfiy(0);
        return obj;
    }

    public void remove(T obj) {
        // 获得最后一个元素
        T replace = heap.get(heapSize - 1);
        int index = indexMap.get(obj);
        heap.remove(--heapSize);
        indexMap.remove(obj);

        // 删除的元素不是最后一个元素
        if (obj != replace) {
            // 在删除元素的位置加入最后的元素
            heap.add(index, replace);
            // 在indexMap中保存此时的index
            indexMap.put(replace, index);
            // 调整元素位置
            resign(replace);
        }
    }

    public List<T> getAll() {
        return new ArrayList<>(heap);
    }

    public void resign(T obj) {
        heapInsert(indexMap.get(obj));
        heapfiy(indexMap.get(obj));
    }

    public void heapInsert(int index) {
        while (comparator.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public void heapfiy(int index) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int smallest = comparator.compare(heap.get(left + 1), heap.get(left)) < 0 && left + 1 < heapSize ? left + 1 : left;
            smallest = comparator.compare(heap.get(smallest), heap.get(index)) < 0 ? smallest : index;
            if (smallest == index) {
                break;
            }
            swap(smallest, index);
            index = smallest;
            left = 2 * index + 1;
        }
    }

    public void swap(int i, int j) {
        T o1 = heap.get(i);
        T o2 = heap.get(j);
        heap.set(i, o2);
        heap.set(j, o1);
        indexMap.put(o1, j);
        indexMap.put(o2, i);
    }
}
