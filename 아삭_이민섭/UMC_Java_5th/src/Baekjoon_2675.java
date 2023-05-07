import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 2675번 - 문자열 반복
// 문자열 문제 3번
// Bronze 2
public class Baekjoon_2675 {

    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            repeatString(st);
        }

        System.out.println(sb);
    }

    public static void repeatString(StringTokenizer st) {
        int value = Integer.parseInt(st.nextToken());
        String word = st.nextToken();
        String result = "";

        for(int i = 0; i < word.length(); i++) {
            String str = "" + word.charAt(i);
            result += str.repeat(value);
        }

        sb.append(result).append('\n');
    }
}
