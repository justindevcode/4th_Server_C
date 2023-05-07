public class Student {
    String name;
    int studentID;
    String major;

    public Student(String name, int studentID, String major) {
        this.name = name;
        this.studentID = studentID;
        this.major = major;
    }

    public void printInfo() {
        System.out.println("Name: " + name);
        System.out.println("Student ID: " + studentID);
        System.out.println("Major: " + major);
    }
}

public class StudentMain {
    public static void main(String[] args) {
        Student student = new Student("홍길동", 202201, "컴퓨터공학과");
        student.printInfo();
    }
}