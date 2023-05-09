package week6.java;

import java.util.Scanner;

class Student {

    private String name;
    private String studentID;
    private String major;

    public Student(String name, String studentID, String major) {
        this.name = name;
        this.studentID = studentID;
        this.major = major;
    }

    public void printInfo() {

        String studentInfo;
        studentInfo = "이름: " + this.name + '\n'
                + "학번: " + this.studentID + '\n'
                + "전공: " + this.major;

        System.out.println(studentInfo);
    }
}

public class Main {
    public static void main(String[] args) {

        Student student = new Student("미누", "12345678", "컴퓨터공학과");
        student.printInfo();

        System.out.println("===================================");

        System.out.print("0 ~ 10 even number is ");
        for (int i = 1; i <= 10; i++) {
            if (isEven(i)) {
                System.out.print(i + " ");
            }
        }

        System.out.println();
        System.out.println("===================================");

        System.out.print("Enter the divide number: ");
        Scanner scanner = new Scanner(System.in);
        int divide = scanner.nextInt();

        try {
            System.out.println(divide / 10);
            System.out.println(divide / 0);

        } catch (ArithmeticException e) {
            System.out.println("Cannot divide by zero");
        }
    }

    public static boolean isEven(int num) {
        return num % 2 == 0;
    }
}
