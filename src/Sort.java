import java.util.*;
import java.util.stream.Stream;

class Student{
    String name;
    Double source;

    public Student(String name,Double source){
        this.source=source;
        this.name=name;
    }
}
public class Sort {

    public static void main(String[] args) {
        /*LinkedHashMap<String,Double> hash = new LinkedHashMap<>();
        List<Student> list=new ArrayList<Student>();
        hash.put("hah",17.0);
        hash.put("hah1",21.0);
        hash.put("hah2",22.0);
        hash.put("hah3",20.0);
        list.add(new Student("hah",17.0));
        list.add(new Student("hah1",15.0));
        list.add(new Student("hah2",13.0));
        list.add(new Student("hah3",18.0));


        Collections.sort(list, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.source.compareTo(o2.source);
            }
        });
        for (Student s:
             list) {
            System.out.println( s.name+" "+s.source);
        }*/
        int[] arr = {1, 6, 5, 41, 2, 3, 6, 5, 41, 2, 3, 6, 5, 41, 2, 3};
        int length = arr.length;
        int[] result = new int[length];//申请一个新数组

        quickSort(arr,0,length-1);

        //mpSort(arr, length);
        //quickSort2(arr, 0, length - 1);
        //selectionSort(arr, length);
        //insertSort(arr,length);
        //ShellSort(arr, length);
        //MergeSort(arr, length);
        //MergeSort2(arr, result, 0, length - 1);
        //(arr,length);
        //System.out.println(TwoSerach(arr,0,length,5));
        //HeapSort(arr,length);
        //CountSort(arr,length);
        //BuckerSort(arr, length);
        //RadixSort(arr, length);
//        int num = TwoSerach(arr, 0, length, 22);
//        System.out.println(num);

        for (int i = 0; i < length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void demo(){
        for (int i = 0; i <= 100; i++)
        {
            for (int j = 0; j <= 100; j++)
            {
                for (int k = 0; k <= 300; k++)
                {
                    if (((5 * i + 3 * j + (k / 3)) == 100) && (k % 3 == 0)){
                        System.out.println("鸡翁:"+i+" 鸡母："+j+" 鸡雏:"+k);
                    }
                }
            }
        }

    }
    public static void shellSort(int arr[],int length){
        int j;
        for (int gap=length/2;gap>0;gap/=2){
            for (int i=gap;i<length;i++){
              j=i;
              int temp=arr[j];
              while (j-gap>=0 && temp<arr[j-gap]){
                  arr[j]=arr[j-gap];
                  j-=gap;
              }
              arr[j]=temp;
            }
        }
    }

    public static int TwoSerach(int arr[], int left, int right, int sum) {
        while (left <= right) {
            int mid = left+(right-left)/2;
            if (arr[mid] > sum) {
                right = mid - 1;
            } else if (arr[mid] < sum) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void mpSort(int[] arr, int length) {
        int num = 0;
//        for (int i = length - 1; i > 0; i--) {
//            for (int j = 0; j < i; j++) {
//                if (arr[j] > arr[j + 1]) {
//                    int temp = arr[j];
//                    arr[j] = arr[j + 1];
//                    arr[j + 1] = temp;
//                }
//            }
//        }
        for (int i = length; i > 0; i--) {
            for (int j = 0; j < i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
//        for (int i = 0; i < length - 1; i++) {
//            for (int j = 0; j < length - i - 1; j++) {
//                if (arr[j] > arr[j + 1]) {
//                    int temp = arr[j];
//                    arr[j] = arr[j + 1];
//                    arr[j + 1] = temp;
//                }
//            }
//        }
    }

    /*
     * 每次以基准值为中位数寻找比其值大或小的值进行交换
     * 如果没有找到符合的基准值，将再这个基准值的基础再次递归
     * 值交换了之后进行递归，反复确认基准值的位置
     * */
    public static void quickSort(int[] arr, int left, int length) {
        if (left > length) {//判断左右两边的下标是否相等，相等则该小组排序完毕
            return;
        }
        int l = left, r = length, num = arr[left];//num代表基准值
        while (l < r) {//l=r的话，则下标相同，无值遍历比较，退出循环
            while (arr[r] >= num && l < r) r--;//从右边开始比较,找到第一个比基准值Num小的值
            arr[l] = arr[r];//比基准值小移动到低位
            while (arr[l] <= num && l < r) l++;//从左边开始比较
            arr[r] = arr[l];//比基准值大的移动到高位


        }
        arr[l] = num;//将基准值放置l下标位置上，此时l应该在数组的中心位置
        quickSort(arr, left, l - 1);//左边递归
        quickSort(arr, l + 1, length);//右边递归
    }

    public static void quickSort2(int[] arr, int left, int length) {
        if (left > length) {//判断左右两边的下标是否相等，相等则该小组排序完毕
            return;
        }
        int l = left, r = length, num = arr[left];//num代表基准值
        while (l < r) {//l=r的话，则下标相同，无值遍历比较，退出循环
            while (arr[r] > num && l < r) r--;//从右边开始比较
            if (l < r) {
                arr[l] = arr[r];
                l++;
            }
            while (arr[l] < num && l < r) l++;//从左边开始比较
            if (l < r) {
                arr[r] = arr[l];
                r--;
            }

        }
        arr[l] = num;//将基准值放置l下标位置上，此时l应该在数组的中心位置
        quickSort(arr, left, l - 1);//左边递归
        quickSort(arr, l + 1, length);//右边递归

    }

    public static void selectionSort(int[] arr, int length) {
        for (int i = 0; i < length; i++) {//每次都把每一轮的最小值放置对应的i下标
            int min = i;
            for (int j = i + 1; j < length; j++) {//遍历比较
                if (arr[min] > arr[j]) {//比较
                    min = j;//将最小值赋予min
                }
            }
            int temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
        }
    }

    public static void insertSort(int arr[], int length) {
        for (int i = 1; i < length; i++) {//模拟当前值为arr[1]
            int temp = arr[i];//临时值，用于数组下标确认之后，将值插入
            int j;
            for (j = i - 1; j >= 0; j--) {//遍历比较
                if (temp < arr[j]) {//临时值小于arr[j] 则arr[j]向后移一位，每次比较的时候可以把临时值看作arr[j]值
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
            }
            arr[j + 1] = temp;//最终确定的j+1为插入的下标值
        }
    }

    public static void ShellSort(int arr[], int length) {//希尔排序，插入排序的优化版
        for (int gap = length / 2; gap > 0; gap /= 2) {//设置增量值，每次递减使数组基本有序
            for (int i = gap; i < length; i++) {//增量越大遍历比较次数越少，增量越小遍历比较越多
                int j = i;
                int temp = arr[j];
                while (j - gap >= 0 && arr[j - gap] > temp) {//这里使用插入排序方法
                    arr[j] = arr[j - gap];
                    j = j - gap;//插入排序，增量越小则向前比较次数越多
                }
                arr[j] = temp;
            }
        }
    }

    //迭代自顶向上合并 
    public static void MergeSort(int arr[], int length) {
        int block, start;
        int[] result = new int[length];//申请一个新数组

        for (block = 1; block < length; block *= 2) {
            for (start = 0; start < length; start += 2 * block) {
                int first = start;
                int mid = (start + block) < length ? (start + block) : length;
                int end = (start + 2 * block) < length ? (start + 2 * block) : length;
                //两个数组的起始结束位置
                int start1 = first, end1 = mid;
                int start2 = mid, end2 = end;
                while (start1 < end1 && start2 < end2) {
                    result[first++] = arr[start1] < arr[start2] ? arr[start1++] : arr[start2++];
                }
                while (start1 < end1) {
                    result[first++] = arr[start1++];
                }
                while (start2 < end2) {
                    result[first++] = arr[start2++];
                }
            }
            for (int i = 0; i < length; i++) {
                arr[i] = result[i];
            }
        }
    }


    //两边合并后原地合并
    public static void MergeSort2(int arr[], int[] result, int left, int length) {
        if (left >= length) {
            return;
        }
        int len = length - left;
        int mid = (len / 2) + left;
        //两个数组的起始结束位置
        int start1 = left, end1 = mid;
        int start2 = mid + 1, end2 = length;
        MergeSort2(arr, result, start1, end1);
        MergeSort2(arr, result, start2, end2);
        int first = left;
        while (start1 <= end1 && start2 <= end2) {
            result[first++] = arr[start1] < arr[start2] ? arr[start1++] : arr[start2++];
        }
        while (start1 <= end1) {
            result[first++] = arr[start1++];
        }
        while (start2 <= end2) {
            result[first++] = arr[start2++];
        }
        for (first = left; first <= length; first++) {
            arr[first] = result[first];
        }
    }

    public static void HeapSort(int[] arr, int length) {
        //1首先我们创建一个堆，就是将数组堆化，把数组看成一个完全二叉树
        //从第一个非叶子节点开启遍历，最后基本形成最大堆
        int len = length - 1;
        int index = (len - 1) >> 1;
        for (int i = index; i >= 0; i--) {
            MaxHeapify(arr, i, len);
        }

        //2再将已经形成的最大堆进行排序，因为根节点就是最大的，所以直接交换，每次都能获得一个最大的
        // （最小堆的做法也是如此）
        for (int j = len; j > 0; j--) {
            int temp = arr[0];
            arr[0] = arr[j];
            arr[j] = temp;
            MaxHeapify(arr, 0, j - 1);
        }
    }

    public static void MaxHeapify(int[] arr, int index, int length) {
        //按照完全二叉树的特性，左右子节点的数组下标等于父节*2+1/2
        int l = (index << 1) + 1, r = (index << 1) + 2;
        int max = l;//默认是左子节点是最大值进行比较
        if (l > length) {
            return;
        }
        if (r <= length  && arr[r] > arr[l]) {
            max = r;
        }
        if (arr[max] > arr[index]) {
            int temp = arr[index];
            arr[index] = arr[max];
            arr[max] = temp;
            MaxHeapify(arr, max, length);//再比较这个父节点是否符合最大堆的条件
        }
    }

    //计数排序，如果数组范围是比较小的化效率是最高的
    public static void CountSort(int[] arr, int length) {
        int max = arr[0], min = arr[0];//首先找出最大值和最小值
        for (int i = 1; i < length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        int[] Count = new int[max - min + 1];//最大值和最小值的区间
        //开始计数
        for (int i = 0; i < length; i++) {
            int value = arr[i];
            Count[value - min] += 1;
        }

        int temp[] = new int[length];//声明一个数组，存储原数组值，方便排序
        for (int i = 0; i < length; i++) {
            temp[i] = arr[i];
        }

        //求出计数和，就是总和，Count[Count.length]=length
        for (int i = 1; i < Count.length; i++) {
            Count[i] += Count[i - 1];
        }

        //最后进行整理，将数组的值排序放上去
        for (int i = length - 1; i >= 0; i--) {
            int value = temp[i];
            arr[Count[value - min] - 1] = value;
            Count[value - min] -= 1;
        }
    }

    //桶排序
    public static void BuckerSort(int[] arr, int length) {
        //桶排序采用区间分类，基数排序采用位数分类两者思想很像计数排序->桶排序->基数排序
        int max = 0;
        for (int i = 0; i < length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        ArrayList<LinkedList<Integer>> bucker = new ArrayList<>();
        for (int i = 0; i < max / 10 + 1; i++) {
            bucker.add(new LinkedList<Integer>());
        }

        for (int i = 0; i < length; i++) {
            int num = arr[i] / 10;
            //获取了这个数是属于哪个区间的值，就使用插入排序比如2/10等于0就是Int[0][]的值了
            //使用方法简化代码
            getInserSort(bucker.get(num), arr[i]);
        }

        //遍历插入
        int index = 0;
        for (LinkedList<Integer> b : bucker) {
            for (Integer num : b) {
                arr[index++] = num;
            }
        }
    }

    private static void getInserSort(List<Integer> bucker, int num) {
        ListIterator<Integer> iterator = bucker.listIterator();
        boolean insert = true;//如果为true则有可能集合为空或者该数为该集合最大的
        while (iterator.hasNext()) {
            if (num <= iterator.next()) {
                iterator.previous();//使用迭代器移位达成插入的效果
                iterator.add(num);
                insert = false;
                break;
            }
        }
        if (insert) {
            bucker.add(num);
        }
    }

    //基数排序
    public static void RadixSort(int[] arr, int length) {
        //基数排序讲的是对位数进行排序，从个位 十位 百位 千位。。。
        //声明一个二维数组重复使用，首先获取最大值从而知道设置多少位
        int max = 0;
        for (int i = 0; i < length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int len = (max + "").length();//最大值位数
        int n = 1;//n每次递增就可以逐次从个位到下一位遍历
        for (int i = 0; i < len; i++, n *= 10) {
            int[][] radix = new int[10][length];//二维数组用保存位数排序的值
            int[] count = new int[10];//声明一个计数数组，因为如果没有计数数组的话，下面的遍历会比较麻烦
            for (int j = 0; j < length; j++) {
                int num = arr[j] / n % 10;
                radix[num][count[num]] = arr[j];
                count[num]++;
            }

            int index = 0;
            for (int x = 0; x < 10; x++) {
                //计数数组等于0就是没有值，无需遍历下标从0开始
                for (int y = 0; y < count[x]; y++) {
                    arr[index++] = radix[x][y];//从个位遍历后个位数较小的在前面，依次类推，遍历完后就是一个有序数组
                }
            }
        }
    }
}



