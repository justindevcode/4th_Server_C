import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 10798번 - 세로읽기
// 2차원 배열 문제 2번
// Bronze 1
public class Baekjoon_10798 {

    public static Character[][] resultArr;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        resultArr = new Character[15][15];

        for(int i = 0; i < 5; i++) {
            String value = br.readLine();
            mkResultArr(value, i);
        }

        printResult();
    }

    public static void mkResultArr(String value, int index) {
        for(int i = 0; i < value.length(); i++) {
            resultArr[i][index] = value.charAt(i);
        }
    }

    public static void printResult() {
        for(Character[] arr : resultArr) {
            for(Character value : arr) {
                if(value == null) continue;
                sb.append(value);
            }
        }

        System.out.println(sb);
    }
}
