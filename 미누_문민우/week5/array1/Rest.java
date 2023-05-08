package week5.array1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 3052 나머지
public class Rest {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int[] rest = new int[10];
        int count = 1;

        for (int i = 0; i < 10; i++) {
            int num = Integer.parseInt(bufferedReader.readLine());
            rest[i] = num % 42;
        }

        Arrays.sort(rest);

        for (int i = 0; i < 9; i++) {
            if (rest[i] == rest[i + 1]) {
                continue;
            } else {
                count++;
            }
        }

        System.out.println(count);
    }
}
