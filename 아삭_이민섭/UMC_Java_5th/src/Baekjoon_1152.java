import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 1152번 - 단어의 개수
// 문자열 문제 1번
// Bronze 2
public class Baekjoon_1152 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        System.out.println(getWordCount(st));
    }

    public static int getWordCount(StringTokenizer st) {
        int wordCount = 0;

        for(;st.hasMoreTokens();) {
            st.nextToken();
            wordCount++;
        }

        return wordCount;
    }
}
