import java.util.Scanner;

public class PracticeDivision {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("10을 나눌 수를 입력해주세요: ");
        int n = scanner.nextInt();
        try{
            int r = 10 / n;
            System.out.println("10을 "+ n + "으로 나눈 값은 : " + r);
        }catch (ArithmeticException e){
            System.out.println("Cannot divide by zero");
        }
    }
}
