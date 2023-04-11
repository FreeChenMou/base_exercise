import java.util.*;

import java.util.Arrays;
import java.util.Comparator;

public class Solution {


    public static void main(String[] args) {
        int n = 5;
        int[][] connections = {{1, 2, 4}, {1, 5, 3}, {2, 3, 1},{1,4,2},{4,5,3}};
        Deque<Integer> deque=new LinkedList<>();
        deque.remove();
        deque.contains(5);
        System.out.println(demo(1,5,n, connections));
    }

    public static int demo(int i,int j,int n, int[][] records) {
        if (n <= 1) {
            return -1;
        }
        if (records.length < n - 1) {
            return -1;
        }
        Arrays.sort(records, Comparator.comparingInt(item -> item[2]));

        Tree tree = new Tree(n);
        int count = 0;
        int res = -1;

        for (int[] node : records) {
            int node1 = node[0];
            int node2 = node[1];

            if (tree.isConnected(node1, node2)) {
                continue;
            }
            tree.setConnected(node1, node2);
            res += node[2];

            count++;
            if (count == (n - 1)) {
                return res;
            }
        }
        return res;
    }
}

class Tree {

    private int[] treeSize;
    private int[] treeRoot;

    public Tree(int count) {


        treeRoot = new int[count+1];
        treeSize = new int[count+1];

        for (int i = 1; i <= count; i++) {
            treeSize[i] = 1;
            treeRoot[i] = i;
        }
    }

    public int findRoot(int j) {
        while (treeRoot[j] != j) {
            j = treeRoot[j];
        }
        return j;
    }

    public boolean isConnected(int i, int j) {
        int x = findRoot(i);
        int y = findRoot(j);

        return x == y;
    }

    public void setConnected(int i, int j) {
        int x = findRoot(i);// i的根节点
        int y = findRoot(j);// j的根节点

        if (x != y) {
            if (treeSize[x] > treeSize[y]) {
                treeRoot[y] = x;
                treeSize[y] += treeSize[x];
            } else {
                treeRoot[x] = y;
                treeSize[x] += treeSize[y];
            }
        }
    }

}


