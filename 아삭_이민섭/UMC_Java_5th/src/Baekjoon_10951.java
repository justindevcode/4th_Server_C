import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 10951번 A+B-4
// 반복문 문제 2번
// Bronze 5
public class Baekjoon_10951 {

    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String value;

        while((value = br.readLine()) != null) {
            if(value.isEmpty() || value.isBlank()) break;
            st = new StringTokenizer(value, " ");
            printResult(st);
        }

        System.out.println(sb.toString());
    }

    public static void printResult(StringTokenizer st) {
        int firstNumber = Integer.parseInt(st.nextToken());
        int secondNumber = Integer.parseInt(st.nextToken());
        sb.append(firstNumber + secondNumber).append('\n');
    }
}
