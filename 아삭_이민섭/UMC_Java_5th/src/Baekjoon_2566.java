import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 2566번 - 최댓값
// 2차원 배열 문제 1번
// Bronze 3
public class Baekjoon_2566 {

    public static int maxValue = Integer.MIN_VALUE;
    public static int height = 0;
    public static int width = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            getResult(st, i);
        }

        System.out.println(maxValue);
        System.out.println(height + " " + width);
    }

    public static void getResult(StringTokenizer st, int index) {
        int numValue = 0;

        for(;st.hasMoreTokens();) {
            int value = Integer.parseInt(st.nextToken());

            if(value >= maxValue) {
                maxValue = value;
                height = index + 1;
                width = numValue + 1;
            }
            numValue++;
        }
    }
}
