package week5.array1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 1546 평균
public class Trick {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bufferedReader.readLine());
        double[] subject = new double[N];
        double total = 0.0;

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < N; i++) {
            subject[i] = Double.parseDouble(stringTokenizer.nextToken());
        }

        Arrays.sort(subject);

        for (int i = 0; i < N; i++) {
            subject[i] = (subject[i] / subject[N - 1]) * 100;
            total += subject[i];
        }

        System.out.printf("%f", (total / N));
    }
}
