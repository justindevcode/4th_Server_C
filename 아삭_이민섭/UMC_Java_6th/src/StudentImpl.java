public class StudentImpl {
    public static void main(String[] args) {
        for(int i = 1; i <= 10; i++) {
            printEvenNumber(i);
        }
    }

    public static void printEvenNumber(int number) {
        if(isEven(number)) System.out.println(number);
    }

    public static boolean isEven(int value) {
        if(value % 2 == 0) return true;
        return false;
    }
}
