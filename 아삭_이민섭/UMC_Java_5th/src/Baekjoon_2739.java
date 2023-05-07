import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 2739번 구구단
// 조건문 문제 3번
// Bronze 5
public class Baekjoon_2739 {

    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        printResult(N);

        System.out.println(sb.toString());
    }

    public static void printResult(int N) {
        for(int i = 1; i <= 9; i++) {
            sb.append(N + " * " + i + " = " + (N*i)).append('\n');
        }
    }
}
