package algorithm_journey.test;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache {


    static class LRU{

        private final int capacity;
        private final LinkedHashMap<Integer, Integer> cache;

        public LRU(int capacity) {
            this.capacity = capacity;
            // accessOrder设为true，表示按访问顺序排序
            this.cache = new LinkedHashMap<>(capacity, 0.75f, true) {
                @Override
                protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                    return size() > capacity;
                }
            };
        }

        public int get(int key) {
            return cache.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            cache.put(key, value);
        }

        // 可选：用于查看当前缓存内容
        public void display() {
            System.out.println(cache);
        }
    }

    public static void main(String[] args) {
        LRU lru = new LRU(3);
        lru.put(1, 100);
        lru.put(2, 200);
        lru.put(3, 300);
        lru.display();  // 输出: {1=100, 2=200, 3=300}
        
        lru.get(1);     // 访问1
        lru.put(4, 400); // 添加4，应该淘汰最久未使用的2
        lru.display();  // 输出: {3=300, 1=100, 4=400}
        
        System.out.println(lru.get(2)); // 输出: -1
    }
}
