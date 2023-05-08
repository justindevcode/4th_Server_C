import java.util.Scanner;
public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();
        String num = sc.next();
        int [] num2 = new int [count];
        int sum = 0;
        for(int i =0;i<count;i++){
            num2[i] = Integer.parseInt(num.substring(i,i+1));
        }
        for(int i = 0; i< count; i++){
            sum = sum+num2[i];
        }
        System.out.println(sum);
        
    }
}