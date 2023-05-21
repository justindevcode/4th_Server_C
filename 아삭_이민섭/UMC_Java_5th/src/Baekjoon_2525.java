import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 2525번 오븐 시계
// 조건문 문제 2번
// Bronze 3

public class Baekjoon_2525 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int hour = Integer.parseInt(st.nextToken());
        int minutes = Integer.parseInt(st.nextToken());
        int plusMinutes = Integer.parseInt(br.readLine());

        printResult(hour, minutes, plusMinutes);
    }

    public static void printResult(int hour, int min, int plus) {
        int sumValue = min + plus;

        hour += sumValue/60;
        min = sumValue % 60;

        System.out.println((hour % 24) + " " + min);
    }
}
