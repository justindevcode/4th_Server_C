package week5.array2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 10798 세로 읽기
public class Read {
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int row = 5;
        int column = 0;
        char[][] seq = new char[row][15];

        for (int i = 0; i < row; i++) {
            String word = bufferedReader.readLine();

            if (column < word.length()) {
                column = word.length();
            }

            for (int j = 0; j < word.length(); j++) {
                seq[i][j] = word.charAt(j);
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < column; i++) {
            for (int j = 0; j < row; j++) {
                if (seq[j][i] == '\0') {
                    continue;
                }
                stringBuilder.append(seq[j][i]);
            }
        }

        System.out.println(stringBuilder);
    }
}
