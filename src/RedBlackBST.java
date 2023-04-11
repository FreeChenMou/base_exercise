import sun.misc.Queue;

import java.util.ArrayList;


/*
* 红黑树根据黑平衡这一特点构造
* 左倾红黑树的出现不平衡情况会少一点，实现起更方便
* */
class RedBlackBST<Key extends Comparable<Key>, Value> {

    private Node root;
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private int word;

    private class Node {
        private Node left, right;
        private Key keys;
        private Value values;
        private int N;
        private boolean color;


        public Node(Key key, Value value, int num, boolean color) {
            this.keys = key;
            this.values = value;
            this.N = num;
            this.color = color;
        }
    }

    public RedBlackBST() {
        root = null;
        word = 0;
    }

    public boolean isEmpty() {
        return root == null;
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

    private Node balance(Node x){
        if (isRed(x.right) && !isRed(x.left)){
            x=leftRotate(x);
        }
        if (isRed(x.left) && isRed(x.left.left)){
            x=rightRotate(x);
        }
        if (isRed(x.left) && isRed(x.right)){
            flipColors(x);
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }


    private Node moveRedRight(Node x){
        // 假设结点h为红色,h.right和h.right.left都是黑色
        // 将h.right或者h.right的子结点之一变红
        flipColors(x);
        if (!isRed(x.left.left)){
            x=rightRotate(x);
        }

        return x;
    }

    private Node moveRedLeft(Node x){
        // 假设结点h为红色,h.left和h.left.left都是黑色
        // 将h.left或者h.left的子结点之一变红
        flipColors(x);
        if (isRed(x.right.left)){
           x.right=rightRotate(x.right);
           x=leftRotate(x);
        }

        return x;
    }


    public void deleteMin() {
        if(!isRed(root.left) && !isRed(root.right)){
            root.color=RED;
        }
        root = deleteMin(root);
        if(!isEmpty()){
            root.color=BLACK;
        }
    }

    private Node deleteMin(Node x) {
        if (isRed(x.left)){
            x=rightRotate(x);
        }
        if (null==x.left){
            return null;
        }

        if (!isRed(x.left) && !isRed(x.left.left)){
            x=moveRedLeft(x);
        }
        x.left = deleteMin(x.left);
        return balance(x);
    }

    public void deleteMax() {
        if(!isRed(root.left) && !isRed(root.right)){
            root.color=RED;
        }
        root = deleteMax(root);
        if (!isEmpty()){
            root.color=BLACK;
        }
    }

    private Node deleteMax(Node x) {
        if (null==x.right) {
            return null;
        }
        if (!isRed(x.right) && !isRed(x.right.left)){
            x=moveRedRight(x);
        }
        x.right=deleteMax(x.right);

        return balance(x);
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
        root.color=BLACK;
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            word++;
            return new Node(key, value, 1, RED);
        }
        int cmp = x.keys.compareTo(key);
        if (cmp < 0) {
            x.right = put(x.right, key, value);
        } else if (cmp > 0) {
            x.left = put(x.left, key, value);
        } else {
            x.values = value;
        }


        return balance(x);
    }

    private void flipColors(Node x) {
        x.color = !x.color;
        x.left.color = !x.left.color;
        x.right.color = !x.right.color;
    }

    private Node rightRotate(Node x) {
        Node node = x.left;
        Node temp = node.right;

        node.right = x;
        x.left = temp;

        node.color = x.color;
        x.color = RED;

        node.N = x.N;
        x.N = size(x.left) + size(x.right) + 1;

        return node;
    }

    private Node leftRotate(Node x) {
        Node node = x.right;
        Node temp = node.left;

        node.left = x;
        x.right = temp;

        node.color = x.color;
        x.color = RED;

        node.N = x.N;
        x.N = size(x.left) + size(x.right) + 1;

        return node;
    }

    private boolean isRed(Node x) {
        if (x == null) {
            return false;
        }
        return x.color==RED;
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

        if(!isRed(root.left) && !isRed(root.right)){
            root.color=RED;
        }
        root = delete(root, key);
        if (!isEmpty()){
            root.color=BLACK;
        }
    }

    private Node delete(Node x, Key key) {
        if (x.keys.compareTo(key)>0){
            //融合向下
            if (!isRed(x.left) && !isRed(x.left.left)){
                x=moveRedLeft(x);
            }
            x.left=delete(x.left,key);
        }else {
            if (isRed(x.left)){
                x=rightRotate(x);
            }
            if (x.keys.compareTo(key)==0 && x.right==null){
                return null;
            }
            if (!isRed(x.right) && !isRed(x.right.left)){
                x=moveRedRight(x);
            }
            if (x.keys.compareTo(key)==0){
                x.values=get(x.right,min(x.right).keys);
                x.keys=min(x.right).keys;
                x.right=deleteMin(x.right);
            }else {
                x.right=delete(x.right,key);
            }
        }
        return balance(x);
    }

    public Queue<Key> keys() {
        Queue<Key> queue = new Queue<>();
        keys(root, queue);
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

    // 返回以node为根节点的二分搜索树中，key所在的节点
    private Node getNode(Node node, Key key) {

        if (node == null)
            return null;

        if (key.equals(node.keys))
            return node;
        else if (key.compareTo(node.keys) < 0)
            return getNode(node.left, key);
        else // if(key.compareTo(node.key) > 0)
            return getNode(node.right, key);
    }

    public boolean contains(Key key) {
        return getNode(root, key) != null;
    }

    public void set(Key key, Value newValue) {
        Node node = getNode(root, key);
        if (node == null)
            throw new IllegalArgumentException(key + " doesn't exist!");

        node.values = newValue;
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
        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());
            RedBlackBST<String, Integer> map = new RedBlackBST<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.put(word, 1);
            }
            System.out.println(map.root.color);
            System.out.println("Total different words: " + map.size());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
        }
        RedBlackBST<Integer, Integer> map = new RedBlackBST<>();
        map.put(1,1);
        map.put(2,2);
        map.put(3,3);
        map.put(5,5);
        map.put(6,6);
        map.put(7,7);
        map.put(4,4);

        map.inorder(map.root);

    }

    private void inorder(Node root) {
        if (root==null){
            return ;
        }
        inorder(root.left);
        System.out.println(root.keys);
        System.out.println(root.color);
        inorder(root.right);
    }
}

