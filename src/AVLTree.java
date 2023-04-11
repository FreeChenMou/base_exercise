import java.util.ArrayList;

public class AVLTree<Key extends Comparable<Key>, Value> {

    private Node root;
    private int size;

    private class Node {
        private Node left, right;
        private Key key;
        private Value value;
        public int height;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            height = 1;
        }
    }

    public AVLTree() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    private int getHeight(Node x) {
        if (x == null) {
            return 0;
        }
        return x.height;
    }

    // 获得节点node的平衡因子
    private int getBalanceFactor(Node x) {
        if (x == null) {
            return 0;
        }
        return getHeight(x.left) - getHeight(x.right);
    }

    private boolean isBST() { if (root == null) {
            return true;
        }
        ArrayList<Key> list = new ArrayList<>();
        inorder(root, list);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1).compareTo(list.get(i)) > 0) {
                return false;
            }
        }
        return true;
    }

    private void inorder(Node x, ArrayList list) {
        if (x == null) {
            return;
        }
        inorder(x.left, list);
        list.add(x.key);
        inorder(x.right, list);
    }

    // 判断该二叉树是否是一棵平衡二叉树
    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node x) {
        if (x == null) {
            return true;
        }
        int i = getBalanceFactor(x);
        if (Math.abs(i) > 1) {
            return false;
        }
        return isBalanced(x.left) && isBalanced(x.right);
    }

    // 向二分搜索树中添加新的元素(key, value)
    public void add(Key key, Value value) {
        root = add(root, key, value);
    }

    private Node rightRotate(Node x) {
        Node node = x.left;
        Node temp = node.right;

        node.right = x;
        x.left = temp;

        // 更新height
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;

        return node;

    }

    private Node leftRotate(Node x) {
        Node node = x.right;
        Node temp = node.left;

        node.left = x;
        x.right = temp;

        // 更新height
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;

        return node;
    }

    // 返回插入新节点后二分搜索树的根
    private Node add(Node node, Key key, Value value) {

        if (node == null) {
            size++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        } else {
            node.value = value;
        }


        // 更新height
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        return balance(node);
    }

    // 返回以node为根节点的二分搜索树中，key所在的节点
    private Node getNode(Node node, Key key) {

        if (node == null)
            return null;

        if (key.equals(node.key))
            return node;
        else if (key.compareTo(node.key) < 0)
            return getNode(node.left, key);
        else // if(key.compareTo(node.key) > 0)
            return getNode(node.right, key);
    }

    public boolean contains(Key key) {
        return getNode(root, key) != null;
    }

    public Value get(Key key) {

        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    public void set(Key key, Value newValue) {
        Node node = getNode(root, key);
        if (node == null)
            throw new IllegalArgumentException(key + " doesn't exist!");

        node.value = newValue;
    }

    // 返回以node为根的二分搜索树的最小值所在的节点
    private Node minimum(Node node) {
        if (node.left == null)
            return node;
        return minimum(node.left);
    }

    // 删除掉以node为根的二分搜索树中的最小节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node) {

        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return balance(node);
    }

    // 从二分搜索树中删除键为key的节点
    public Value remove(Key key) {

        Node node = getNode(root, key);
        if (node != null) {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node node, Key key) {

        if (node == null)
            return null;

        Node t;
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            t = node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            t = node;
        } else {   // key.compareTo(node.key) == 0

            // 待删除节点左子树为空的情况
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                t = rightNode;
            } else if (node.right == null) {// 待删除节点右子树为空的情况
                Node leftNode = node.left;
                node.left = null;
                size--;
                t = leftNode;
            } else {
                // 待删除节点左右子树均不为空的情况
                // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
                // 用这个节点顶替待删除节点的位置
                Node successor = minimum(node.right);
                successor.right = remove(node.right, successor.key);
                successor.left = node.left;
                node.left = node.right = null;//可有可无，因为不会再使用，所以gc回收机制会将他回收

                t = successor;
            }
        }
        if (t == null) {
            return null;
        }
        t.height = 1 + Math.max(getHeight(t.left), getHeight(t.right));

        return balance(t);

    }

    public  Node balance(Node t){
        int balanceFactor = getBalanceFactor(t);

        //说明左子树较高需要右旋转
        if (balanceFactor > 1 && getBalanceFactor(t.left) >= 0) {
            return rightRotate(t);
        }

        //说明右子树较高需要左旋转
        if (balanceFactor < -1 && getBalanceFactor(t.right) <= 0) {
            return leftRotate(t);
        }

        //说明左子树较高但是左子树的右子树比较高需要右旋转
        if (balanceFactor > 1 && getBalanceFactor(t.left) < 0) {
            t.left = leftRotate(t.left);
            return rightRotate(t);
        }

        //说明右子树较高但是右子树左子树比较高需要右旋转
        if (balanceFactor < -1 && getBalanceFactor(t.right) > 0) {
            t.right = rightRotate(t.right);
            return leftRotate(t);
        }
        return t;
    }

    public static void main(String[] args) {

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());
            AVLTree<String, Integer> map = new AVLTree<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }
            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
            System.out.println(map.isBST());
            System.out.println(map.isBalanced());
            System.out.println(map.root.height);
//            for (String word : words) {
//                map.remove(word);
//                if (!map.isBST() || !map.isBalanced()) {
//                    throw new RuntimeException("error");
//                }
//            }
        }

        System.out.println();
    }
}
