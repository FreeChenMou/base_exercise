import java.util.HashMap;
import java.util.HashSet;

public class leetcode_test {
    public static void main(String[] args) {
        String s="ssss";
        HashMap<Character,Integer> hash=new HashMap<>();
        int length=s.length();
        int max=0;
        int right=0;
        for(int i=0;i<length;i++){
            if (hash.containsKey(s.charAt(i))){
                right=Math.max(hash.get(s.charAt(i))+1,right);
            }

            hash.put(s.charAt(i),i);
            max=Math.max(max,i-right+1);

        }
    }
    static class Solution {
        public String reverseWords(String s) {
            String str[]=s.split(" ");

            int length=str.length;
            String returnStr[]=new String[length];
            for(int i=0;i<length;i++){
                char[] t=str[i].toCharArray();
                int left=0,right=t.length-1;
                while(left<right){
                    char temp=t[left];
                    t[left]=t[right];
                    t[right]=temp;
                    left++;
                    right--;
                }
                str[i]=String.valueOf(t);
            }
            for(int i=1;i<length;i++){
                str[0]=str[0].concat(" "+str[i]);
                //str[0]=str[0].concat(str[i]);
            }
            return str[0];
        }
    }
}
