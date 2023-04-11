import java.util.LinkedHashMap;
import java.util.Map;

public class SortTest {
    public static void main(String[] args) {
        int arr[] = {5, 7, 1, 99, 4, 3, 7, 5, 2, 88,10};
        int length = arr.length;
        int result[] = new int[length];
        //headSort2(arr, length);
        //bubbleSort(arr,length);
        //shellSort(arr, length);
        //mergeSort(arr, result, 0, length-1);
        threeQuickSort(arr, 0, length - 1);
        //countSort(arr,length);
        //mergeSort2(arr,length);
        Map<Integer,Integer> map=new LinkedHashMap<>();
        int count=1;
        for (int i = 0; i < length; i++) {
            if (map.containsKey(arr[i])){
                map.put(arr[i],i=count++);
            }

            System.out.println(arr[i]);
        }
    }

    public static void threeQuickSort(int arr[],int left,int right){
        if (left>right){
            return;
        }

        int l=left,r=right,lr=left+1,temp=arr[left];

        while(lr<=r){
            if (arr[lr]<temp){
                int t=arr[l];
                arr[l++]=arr[lr];
                arr[lr++]=t;
            }else if(arr[lr]>temp){
                int t=arr[lr];
                arr[lr]=arr[r];
                arr[r--]=t;
            }else {
                lr++;
            }
        }

        threeQuickSort(arr,left,l-1);
        threeQuickSort(arr,r+1,right);
    }

    public static void bubbleSort(int arr[], int length) {
        for (int i = length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;

                }
            }
        }
    }



    public static void Sort(int arr[], int length) {
        for (int i = length - 1; i > 0; i--) {
            for (int j = 0; j < length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    public static void headSort2(int arr[],int length){
        int len=(length>>1);
        for (int i=len;i>=1;i--){
            headMax(arr,i,length-1);
        }

        for (int i=length-1;i>1;i--){
            int temp=arr[i];
            arr[i]=arr[1];
            arr[1]=temp;
            headMax(arr,1,i-1);
        }
    }

    private static void headMax(int[] arr, int index, int length) {
        int left=(index<<1),right=(index<<1)+1;
        int max=left;
        if (left>length){
            return;
        }
        if (right<=length && arr[left]<arr[right]){
            max=right;
        }
        if (arr[max]>arr[index]){
            int temp=arr[max];
            arr[max]=arr[index];
            arr[index]=temp;
            headMax(arr,max,length);
        }
    }


    //三切分法
    public static void quickSort3(int arr[], int left, int right) {
        if (left > right) {
            return;
        }
        int l = left, r = right, lr = left + 1, temp = arr[left];
        while (lr <= r) {
            if (arr[lr] > temp) {
                int t = arr[lr];
                arr[lr] = arr[r];
                arr[r--] = t;
            } else if (arr[lr] < temp) {
                int t = arr[lr];
                arr[lr++] = arr[l];
                arr[l++] = t;
            } else {//相等
                lr++;
            }
        }
        quickSort3(arr, left, l - 1);//小于基准值
        quickSort3(arr, r + 1, right);//大于基准值
    }


    //填坑法
    public static void quickSort2(int arr[], int left, int right) {
        if (left >= right) {
            return;
        }
        int l = left, r = right, temp = arr[left];
        while (l < r) {
            while (arr[r] >= temp && l < r) {
                r--;
            }
            if (l < r) {
                arr[l] = arr[r];
                l++;
            }
            while (arr[l] <= temp && l < r) {
                l++;
            }
            if (l < r) {
                arr[r] = arr[l];
                r--;
            }

            arr[l] = temp;
            quickSort2(arr, left, l - 1);
            quickSort2(arr, l + 1, right);
        }


    }

    //置换法n
    public static void quickSort(int arr[], int left, int right) {
        if (left > right) {
            return;
        }
        int l = left, r = right, temp = arr[left];
        while (l < r) {
            while (arr[r] >= temp && l < r) {
                r--;
            }
            arr[l] = arr[r];
            while (arr[l] <= temp && l < r) {
                l++;
            }
            arr[r] = arr[l];

            arr[l] = temp;
        }
        quickSort(arr, left, l - 1);
        quickSort(arr, l + 1, right);
    }

    public static void countSort(int arr[], int length) {
        int max = arr[0], min = arr[0];//首先找出最大值和最小值
        for (int i = 1; i < length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        int result[] = new int[max - min + 1];

        for (int i = 0; i < length; i++) {
            result[arr[i] - min] += 1;
        }
        for (int j = 1; j < result.length; j++) {
            result[j] += result[j - 1];
        }
        int temp[] = new int[length];
        for (int i = 0; i < length; i++) {
            temp[i] = arr[i];
        }
        for (int i = length - 1; i >= 0; i--) {
            arr[result[temp[i] - min] - 1] = temp[i];
            result[temp[i] - min] -= 1;
        }

    }

    public static void shellSort(int arr[], int length) {
        int x = 0;
        for (int gap = length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < length; i++) {
                int temp = arr[i];
                int j = i;
                while (j - gap >= 0 && arr[j - gap] > arr[j]) {
                    arr[j] = arr[j - gap];
                    j = j - gap;
                }
                arr[j] = temp;
            }
        }
        System.out.println(x);
    }

    public static void mergeSort2(int arr[], int length) {
        int result[] = new int[length];
        for (int block = 1; block < length; block *= 2) {
            for (int start = 0; start < length; start += 2 * block) {
                int count = start;
                int start1 = start, end1 = (start + block) < length ? (start + block) : length;
                int start2 = (start + block) < length ? (start + block) : length,
                        end2 = (start + 2 * block) < length ? (start + 2 * block) : length;
                while (start1 < end1 && start2 < end2) {
                    result[count++] = arr[start1] < arr[start2] ? arr[start1++] : arr[start2++];
                }
                while (start1 < end1) {
                    result[count++] = arr[start1++];
                }
                while (start2 < end2) {
                    result[count++] = arr[start2++];
                }

            }
            for (int i = 0; i < length; i++) {
                arr[i] = result[i];
            }
        }
    }

    public static void mergeSort(int arr[], int result[], int left, int right) {
        if (left >= right) {
            return;
        }

        int start1 = left, end1 = left + (right - left) / 2, start2 = end1 + 1, end2 = right;
        mergeSort(arr, result, start1, end1);
        mergeSort(arr, result, start2, end2);
        int count = left;
        while (start1 <= end1 && start2 <= end2) {
            result[count++] = arr[start1] < arr[start2] ? arr[start1++] : arr[start2++];
        }
        while (start1 <= end1) {
            result[count++] = arr[start1++];
        }
        while (start2 <= end2) {
            result[count++] = arr[start2++];
        }
        for (count = left; count <= right; count++) {
            arr[count] = result[count];
        }


    }

    public static void selectSort(int arr[], int length) {
        for (int i = 0; i < length; i++) {
            int temp = i;
            for (int j = i + 1; j < length; j++) {
                if (arr[temp] > arr[j]) {
                    temp = j;
                }
            }

            int t = arr[i];
            arr[i] = arr[temp];
            arr[temp] = t;

        }
    }

    public static void insertSort(int arr[], int length) {
        for (int i = 1; i < length; i++) {
            int temp = arr[i];
            int j;
            for (j = i - 1; j >= 0; j--) {
                if (arr[j] > temp) {
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
            }
            arr[j + 1] = temp;

        }
    }
}
