import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 2563번 - 색종이
// 2차원 배열 문제 3번
// Silver 5
public class Baekjoon_2563 {

    public static int[][] resultArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        resultArr = new int[100][100];

        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            mkResultArr(st);
        }

        System.out.println(getArea());
    }

    public static int getArea() {

        int sum = 0;

        for(int i = 0; i < 100; i++) {
            for(int j = 0; j < 100; j++) {
                if(resultArr[i][j] == 0) continue;
                sum++;
            }
        }

        return sum;
    }

    public static void mkResultArr(StringTokenizer st) {
        int width = Integer.parseInt(st.nextToken());
        int height = Integer.parseInt(st.nextToken());

        for(int i = height; i < height + 10; i++) {
            for(int j = width; j < width + 10; j++) {
                if(resultArr[i][j] != 0) continue;
                resultArr[i][j] = 1;
            }
        }
    }
}
