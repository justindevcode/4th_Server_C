import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int value = Integer.parseInt(br.readLine());

        divideByValue(value);
    }

    public static void divideByValue(int value) {
        try {
            System.out.println(10 / value);
        } catch(ArithmeticException e) {
            System.out.println("Cannot divide by zero");
        }
    }
}
