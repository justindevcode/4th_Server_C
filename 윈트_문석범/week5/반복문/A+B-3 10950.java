import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        int count;
        int [] num;
        
        Scanner sc = new Scanner(System.in);
        count = sc.nextInt();
        num = new int[count*2];
        
        for(int i=0;i<count;i++){
            num [(i*2)] = sc.nextInt();
            num [(i*2)+1] = sc.nextInt();
            System.out.println(num[(i*2)] + num [(i*2)+1]);
        }
        sc.close();
        
    }
}