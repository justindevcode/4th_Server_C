import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 1110번 더하기 사이클
// 조건문 문제 3번
// Bronze 1
public class Baekjoon_1110 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int firstValue = Integer.parseInt(br.readLine());

        int resultCount = getResultCount(firstValue);

        System.out.println(resultCount);
    }

    public static int getResultCount(int value) {
        int count = 0;
        int originValue = value;

        while(true) {
            value = getNewValue(value);
            count++;
            if(originValue == value) break;
        }

        return count;
    }

    public static int getNewValue(int value) {
        int leftNumber = value / 10;
        int rightNumber = value % 10;

        int numberSum = leftNumber + rightNumber;

        return rightNumber * 10 + numberSum % 10;
    }
}