import java.util.Scanner;

public class demo {

    public static void main(String[] args) {
//        for (int i = 1; i <= 5; i++) {
//            for (int j = 1; j<=5-i;j++) {
//                System.out.print(" ");
//            }
//            for (int x = 1; x <= 2*i-1;x++) {
//                if (i==5){
//                    System.out.print("+");
//
//                }else {
//                    System.out.print("*");
//                }
//            }
//            System.out.println();
//        }
//        for (int i = 1; i <= 4; i++) {
//            for (int j = 1; j <= i; j++) {
//                System.out.print(" ");
//            }
//            for (int x = 1; x <= 2 * (4 - i + 1) - 1; x++) {
//                System.out.print('*');
//            }
//            System.out.println();
//        }


        Scanner s = new Scanner(System.in);
        String hello = s.nextLine();
        if (hello.charAt(0) >='a' && hello.charAt(0) <='z'){
            String s1 = hello.substring(0,1).toUpperCase();
            System.out.println(s1);
        }
        String o = hello.replace('o', '*');
        System.out.println(o);
        for (int i=0;i<hello.length();i++){
            if (hello.charAt(i) >='a' && hello.charAt(i) <='z'){
                String s1 = hello.substring(i , i+1).toUpperCase();
                System.out.print(s1);

            }else {
                System.out.print(hello.substring(i , i+1));
            }

        }
        int month = 0, year = 0, day = 0, num = 0,temp=0;
        for (int i = 0; i < hello.length(); i++) {
            num++;
            if (hello.charAt(i) == '-') {
                String substring = hello.substring(0, i );
                year = Integer.valueOf(substring);
                break;
            }
        }
        temp=num;
        for (int i = num; i < hello.length(); i++) {
            num++;
            if (hello.charAt(i) == '-') {
                String substring = hello.substring(temp, i);
                month = Integer.valueOf(substring);
                break;
            }
        }
        String substring = hello.substring(num);
        day = Integer.valueOf(substring);
        int count=0 ;
        for (int i = 1; i < month; i++) {
            switch (i) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    count = 31;
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    count = 30;
                    break;
                case 2:
                    if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
                        count = 29;
                    else
                        count = 28;
                    break;
            }
            day += count;
        }

        System.out.print("\n"+day);
    }
}

