package week5.cond;

import java.util.Scanner;

// 2884 알람 시계
public class Alarm {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int h = scan.nextInt();
        int m = scan.nextInt();

        if(h == 0) {
            if(m - 45 < 0) {
                h = 23;
                m += 15;
            } else
                m -= 45;
        } else {
            if(m - 45 < 0) {
                h -= 1;
                m += 15;
            } else
                m -= 45;
        }
        System.out.println(h + " " + m);
    }
}
