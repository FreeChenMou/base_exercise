import java.util.Random;

public class test {
    public static void main(String[] args) {
        int arr[] = {1, 424, 52, 3, 41, 5, 2, 3};
        //shellSort(arr);
        Random random = new Random();
        int sum= random.nextInt(6);
        System.out.println(sum);
        //System.out.println(multiply("-555555555121", "-124"));

    }

    public static String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int temp1 = 0, temp2 = 0;
        if (num1.charAt(0) == '-') {
            temp1 = -1;
        }
        if (num2.charAt(0) == '-') {
            temp2 = -1;
        }
        StringBuilder result = new StringBuilder();

        int[] arr = new int[num1.length() + num2.length()];
        for (int i = num1.length() - 1; i >= Math.abs(temp1); i--) {
            int n1 = num1.charAt(i) - '0';
            for (int j = num2.length() - 1; j >= Math.abs(temp2); j--) {
                int n2 = num2.charAt(j) - '0';
                int sum = (arr[i+j+1] + n1 * n2);
                arr[i + j + 1] = sum % 10;
                arr[i + j] += sum / 10;
            }
        }

        if (temp1 == -1 && temp2 == 0) {
            result.append("-");
        } else if (temp1 == 0 && temp2 == -1) {
            result.append("-");
        }
        for (int i=Math.abs(temp1)+Math.abs(temp2)+1;i<arr.length;i++){
            result.append(arr[i]);
        }


        return result.toString();
    }

    private static void shellSort(int arr[]) {
        int length = arr.length;
        for (int gap = length >> 1; gap > 0; gap >>= 1) {
            for (int i = gap; i < length; i++) {
                int temp = arr[i];
                int j = i;
                while (j - gap >= 0 && arr[j - gap] > temp) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = temp;
            }

        }
    }

    private static int m() {
        int i = 100;
        try {
            i++;
        } catch (Exception e) {
            i++;
        } finally {
            i--;
        }
        return i;
    }


}
