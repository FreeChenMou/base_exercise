import sun.misc.Queue;


import javax.swing.tree.TreeNode;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class leetcode_kmp {
    public static void main(String[] args) {
        Solution solution=new Solution();
        System.out.println(solution.kmp("cba", "ccccbbbbaaaa"));
        String[] abcs = solution.permutation("abc");


    }
    static class Solution {
        int[] visited;
        Set<String> list = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        boolean status=false;
        public boolean checkInclusion(String s1, String s2) {
            String[] str=permutation(s1);
            for(int i=0;i<str.length;i++){
                if(kmp(str[i],s2)){
                    status=true;
                }
            }
            return status;
        }




        public String[] permutation(String s) {
            int len = s.length();
            visited = new int[len];
            //每一个字符都作为起点
            for (int i = 0; i < len; i++) {
                dfs(s, i);
                visited[i] = 0;
                sb.delete(0, sb.length());
            }
            //得到set中的元素
            String[] res = new String[list.size()];
            int count = 0;
            for (String str : list) {
                res[count++] = str;
            }
            return res;
        }
        //递归组合字符
        private void dfs(String s, int i) {
            sb.append(s.charAt(i));
            visited[i] = 1;
            //递归出口，拼接完毕
            if (sb.length() == s.length()) {
                list.add(sb.toString());
                return;
            }
            //循环递归
            for (int j = 0; j < s.length(); j++) {
                if (visited[j] == 0) {
                    dfs(s, j);
                    visited[j] = 0;
                    sb.delete(sb.length() - 1, sb.length());
                }
            }
            return;
        }


        public boolean kmp(String s1,String s2){
            int i=0,j=0,length2=s2.length(),length1=s1.length();
            int next[]=getNext(s2);
            while(i<length1 && j<length2){
                if(i==-1 || s1.charAt(i)==s2.charAt(j)){
                    i++;
                    j++;
                }else{
                    i=next[i];
                }
            }

            return i==length1?true:false;
        }

        public int[] getNext(String s){
            int next[]=new int[s.length()];
            next[0]=-1;
            int k=-1,j=0;
            while(j<s.length()-1){
                if(k==-1 || s.charAt(k)==s.charAt(j)){
                    k++;
                    j++;
                    next[j]=s.charAt(k)==s.charAt(j)?next[k]:k;
                }else{
                    k=next[k];
                }
            }
            return next;
        }
    }
}
