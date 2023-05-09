class Student {
    String name;
    int studentID;
    String major;

    public Student(String name, int studentID, String major) {
        this.name = name;
        this.studentID = studentID;
        this.major = major;
    }

    public void String() {
        System.out.println("이름: " + name);
        System.out.println("학번: " + studentID);
        System.out.println("학과: " + major);
    }
}

public class PracticeStudent {
    public static void main(String[] args){
        Student student = new Student("모건", 60175103, "컴퓨터공학과");
        student.String();
    }
}
