public class Student {
    private String name;
    private String studentID;
    private String major;

    public Student() {
        this.name = "Empty Instance";
        this.studentID = "Empty Instance";
        this.major = "Empty Instance";
    }

    public Student(String name, String studentID, String major) {
        this.name = name;
        this.studentID = studentID;
        this.major = major;
    }

    public void printInformation() {
        System.out.println("Student Name == " + name);
        System.out.println("Student ID == " + studentID);
        System.out.println("Student Major == " + major);
    }
}
