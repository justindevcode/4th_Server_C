public class EvenOdd {
    public static boolean isEven(int num) {
        if (num % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }
}

public class EvenOddMain {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            if (EvenOdd.isEven(i)) {
                System.out.println(i + " is even.");
            }
        }
    }
}