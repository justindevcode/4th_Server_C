import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 2562번 - 최댓값
// 1차원 배열 문제 2번
// Bronze 3
public class Baekjoon_2562 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int maxValue = Integer.MIN_VALUE;
        int index = 0;

        for(int i = 0; i < 9; i++) {
            int value = Integer.parseInt(br.readLine());

            if(value >= maxValue) {
                maxValue = value;
                index = i+1;
            }
        }

        System.out.println(maxValue);
        System.out.println(index);
    }
}
