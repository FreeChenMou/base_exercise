import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LRU {
    public static void main(String[] args) {
        LRU lRUCache = new LRU(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        int g1=lRUCache.get(1);    // 返回 1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        int g2=lRUCache.get(2);    // 返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        int g3=lRUCache.get(1);    // 返回 -1 (未找到)
        int g4=lRUCache.get(3);    // 返回 3
        int g5=lRUCache.get(4);    // 返回 4
        System.out.println(g1+" "+g2+" "+g3+" "+g4+" "+g5);//-1为未命中，实数值为命中返回的cache
    }

    private int temp;
    Map<Integer, Integer> map = new HashMap<>();
    Deque<Integer> deque = new LinkedList();

    public LRU(int capacity) {
        this.temp = capacity;
    }

    public int get(int key) {
        if (map.keySet().contains(key)) {
            int value = map.get(key);
            deque.remove(key);
            deque.addFirst(key);
            return value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (map.keySet().contains(key)) {
            map.remove(key);
            deque.remove(key);
        } else if (map.size() == temp) {
            int sum = deque.removeLast();
            map.remove(sum);
        }
        deque.addFirst(key);
        map.put(key, value);

    }
}


