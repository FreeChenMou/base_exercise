import java.util.*;

public class StringTable {
    public static void main(String[] args) throws Exception {
        ST<String,Integer> st = new ST<String,Integer>(5);


        st.put( "hh",2);
        st.put( "c",1);
        st.put( "hs",3);
        st.put( "gh",5);
        st.put( "ah",0);
        System.out.println(st.toString());
        System.out.println(st.get("hh"));


    }
}


class ST<Key extends Comparable<Key>, Value> {
    private Key keys[];
    private Value values[];
    private int Count = 0;
    private int size;

    @SuppressWarnings("unchecked")
    public ST(int num) {
        keys = (Key[]) new Comparable[num];
        values = (Value[]) new Object[num];
        size = num;
    }

    @Override
    public String toString() {
        return "ST{" +
                "keys=" + Arrays.toString(keys) +
                ", values=" + Arrays.toString(values) +
                ", Count=" + Count +
                ", size=" + size +
                '}';
    }

    public Value get(Key key) {
        if (isEmpty()) {
            return null;
        }
        int i = rank(key);
        if (i < Count && keys[i].compareTo(key) == 0) {
            return values[i];
        } else {
            return null;
        }

    }

    private boolean isEmpty() {
        return Count == 0;
    }

    public void put(Key key, Value value) throws ArrayIndexOutOfBoundsException {
        if (Count == size) {
            System.out.println("队列已满");
            //throw new ArrayIndexOutOfBoundsException("队满");生产环境中需要try抛出异常为了好看直接return
            return;
        }

        int i = rank(key);
        if (i < Count && keys[i].compareTo(key) == 0) {
            values[i] = value;
            return;
        }

        for (int j = Count; j > i; j--) {
            keys[j] = keys[j - 1];
            values[j] = values[j - 1];
        }

        keys[i] = key;
        values[i] = value;
        Count++;
    }

    private int rank(Key key) {
        int l = 0, r = Count - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (key.compareTo(keys[mid]) > 0) {
                l = mid + 1;
            } else if (key.compareTo(keys[mid]) < 0) {
                r = mid - 1;
            } else {
                return mid;
            }
        }
        return l;
    }

    public void delete(Key key) {
        Value s = get(key);
        int i = rank(key);
        if (s == null) {
            System.out.println("key can not be null");
            return ;
            //throw new RuntimeException("key can't be null");生产环境中需要try抛出异常为了好看直接return
        }
        keys[i] = null;
        values[i] = null;
        size--;
    }

    private Key floor(Key key) {
        int i = rank(key);
        if (get(key) != null) {
            return keys[i];
        } else if (get(key) != null && i != 0) {
            return keys[i - 1];
        } else {
            return null;
        }

    }
}
