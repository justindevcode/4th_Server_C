import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 10818번 최소, 최대
// 1차원 배열 문제 1번
// Bronze 3
public class Beakjoon_10818 {

    public static int[] resultArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        initResultArr(N);
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        mkResultArr(st);

        System.out.println(getMinValue() + " " + getMaxValue());
    }

    public static int getMaxValue() {
        return Arrays.stream(resultArr).max().getAsInt();
    }

    public static int getMinValue() {
        return Arrays.stream(resultArr).min().getAsInt();
    }

    public static void initResultArr(int N) {
        resultArr = new int[N];
    }

    public static void mkResultArr(StringTokenizer st) {
        int numValue = 0;

        for(;st.hasMoreTokens();) {
            resultArr[numValue++] = Integer.parseInt(st.nextToken());
        }
    }
}
