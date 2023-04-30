import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 11718번 - 그대로 출력하기
// 문자열 2번 문제
// Bronze 5
public class Baekjoon_11718 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String value;

        while((value = br.readLine()) != null) {
            if(value.isBlank() || value.isEmpty()) break;
            sb.append(value).append('\n');
        }

        System.out.println(sb);
    }
}
