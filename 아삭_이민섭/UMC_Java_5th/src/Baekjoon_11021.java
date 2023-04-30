import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 11021번 A+B-7
// 반복문 문제 1번
// Bronze 1
public class Baekjoon_11021 {

    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            printResult(st, i+1);
        }

        System.out.println(sb.toString());
    }

    public static void printResult(StringTokenizer st, int index) {
        int firstNumber = Integer.parseInt(st.nextToken());
        int secondNumber = Integer.parseInt(st.nextToken());

        sb.append("Case #" + index + ": " + (firstNumber + secondNumber)).append('\n');
    }
}
