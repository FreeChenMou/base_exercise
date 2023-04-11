import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class sss {
    public static int count = 0;

    public static void main(String[] args) {
        int test = test(1024);
        System.out.println(test);

    }

    public static int test(int n){
        if (n<=1){
            return 1;
        }
        System.out.println(count++);
        return 2*test(n/2)+n;
    }
}
