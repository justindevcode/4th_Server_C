import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 1546번 - 평균
// 1차원 배열 문제 3번
// Bronze 1
public class Baekjoon_1546 {

    public static int[] resultArr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        initResultArr(N);
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        mkResultArr(st);

        int maxValue = getMaxValue();

        System.out.println(getNewAverage(maxValue));
    }

    public static double getNewAverage(int maxValue) {
        int length = resultArr.length;
        double sum = 0;

        for(int i = 0; i < length; i++) {
            sum += ((resultArr[i] / (double)maxValue) * 100);
        }

        return sum / length;
    }

    public static int getMaxValue() {
        return Arrays.stream(resultArr).max().getAsInt();
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
