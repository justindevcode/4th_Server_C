import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 2884번 알람 시계
// 조건문 문제 1번
// Bronze 3

public class Baekjoon_2884 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int hour = Integer.parseInt(st.nextToken());
        int minutes = Integer.parseInt(st.nextToken());

        printAlarm(hour, minutes);
    }

    public static void printAlarm(int hour, int minutes) {
        if(minutes >= 45) minutes -= 45;
        else if(hour != 0) {
            hour--;
            minutes += 15;
        }
        else {
            hour = 23;
            minutes += 15;
        }

        System.out.println(hour + " " + minutes);
    }
}
