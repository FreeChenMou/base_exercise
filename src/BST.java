import sun.misc.Queue;

import java.util.Iterator;


class BST<Key extends Comparable<Key>, Value> {

    private Node root;


    private class Node {
        private Node left, right;
        private Key keys;
        private Value values;
        private int N;

        public Node(Key key, Value value, int num) {
            this.keys = key;
            this.values = value;
            this.N = num;
        }
    }

    private int size(Node root) {
        if (root == null) {
            return 0;
        }
        return root.N;
    }

    private int size() {
        return size(root);
    }


    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (x == null) {
            System.out.println("树为空");
            return null;
        }
        int cmp = x.keys.compareTo(key);
        if (cmp > 0) {
            return get(x.left, key);
        } else if (cmp < 0) {
            return get(x.right, key);
        } else {
            return x.values;
        }
    }

    //小于等于key的最大键
    public Key floor(Key key) {
        Node node = floor(root, key);
        if (node == null) {
            return null;
        }
        return node.keys;
    }

    private Node floor(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = x.keys.compareTo(key);
        if (cmp == 0) {
            return x;
        }

        if (cmp > 0) {
            return floor(x.left, key);
        }
        Node node = floor(x.right, key);
        if (node != null) {
            return node;
        } else {
            return x;
        }
    }

    //大于等于key的最小键
    public Key ceiling(Key key) {
        Node node = ceiling(root, key);
        if (node == null) {
            return null;
        }
        return node.keys;
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = x.keys.compareTo(key);
        if (cmp == 0) {
            return x;
        }
        if (cmp < 0) {
            return ceiling(x.right, key);
        }
        Node node = ceiling(x.left, key);
        if (node != null) {
            return node;
        } else {
            return x;
        }

    }

    public Key select(int num) {
        return select(root, num).keys;

    }

    private Node select(Node x, int num) {
        if (x == null) {
            return null;
        }
        int n = size(x.left);
        if (n < num) {
            return select(x.right, num - n - 1);
        } else if (n > num) {
            return select(x.left, num);
        } else {
            return x;
        }
    }

    public int rank(Key key) {
        return rank(root, key);
    }

    public int rank(Node x, Key key) {
        if (x == null) {
            return 0;
        }
        int cmp = x.keys.compareTo(key);
        if (cmp > 0) {
            return rank(x.left, key);
        } else if (cmp < 0) {
            return rank(x.right, key) + size(x.left) + 1;
        } else {
            return size(x.left);
        }
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void deleteMax() {
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x.right == null) {
            return x.left;
        }
        x.right = deleteMax(x.right);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            return new Node(key, value, 1);
        }
        int cmp = x.keys.compareTo(key);
        if (cmp < 0) {
            x.right = put(x.right, key, value);
        } else if (cmp > 0) {
            x.left = put(x.left, key, value);
        } else {
            x.values = value;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Key min() {
        return min(root).keys;
    }

    private Node min(Node x) {
        if (x.left == null) {
            return x;
        }
        return min(x.left);
    }

    public Key max() {
        return max(root).keys;
    }

    private Node max(Node x) {
        if (x.right == null) {
            return x;
        }
        return max(x.right);
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = x.keys.compareTo(key);
        if (cmp < 0) {
            x.right = delete(x.right, key);
        } else if (cmp > 0) {
            x.left = delete(x.left, key);
        } else {
            if (x.right == null) {
                return x.left;
            }
            if (x.left == null) {
                return x.right;
            }
            Node temp = x;
            x = min(temp.right);
            x.right = deleteMin(temp.right);
            x.left = temp.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Queue<Key> keys() {
        Queue<Key> queue = new Queue<>();
        keys(root,queue);
        return queue;
    }

    public Queue<Key> keys(Key min, Key max) {
        Queue<Key> queue = new Queue<>();
        keys(root, queue, min, max);
        return queue;
    }

    private void keys(Node x, Queue queue) {
        if (x == null) {
            return;
        }
        keys(x.left, queue);
        queue.enqueue(x.keys);
        keys(x.right, queue);
    }

    private void keys(Node x, Queue queue, Key min, Key max) {
        if (x == null) {
            return;
        }
        int cmp1 = x.keys.compareTo(min);
        int cmp2 = x.keys.compareTo(max);
        if (cmp1 > 0) {
            keys(x.left, queue, min, max);
        }
        if (cmp1 >= 0 && cmp2 <= 0) {
            queue.enqueue(x.keys);
        }
        if (cmp2 < 0) {
            keys(x.right, queue, min, max);
        }
    }


    public static void main(String[] args) throws InterruptedException {
        BST<String, Integer> bst = new BST<String, Integer>();

        bst.put("e", 4);
        bst.put("c", 2);
        bst.put("b", 1);
        bst.put("d", 3);
        bst.put("a", 0);
        bst.put("f", 5);
        bst.put("g", 6);

        Queue<String> queue = bst.keys("a","f");
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(bst.get("g"));

    }
}
