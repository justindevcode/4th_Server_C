public class PracticeEven {
    public static void main(String[] args){
        for(int i = 1;i <=10;i++){
            if(isEven(i)){
                System.out.println(i + "는 짝수");
            }
        }

    }
    public static boolean isEven(int n){
        if(n%2 ==0){
            return true;
        }else {
            return false;
        }
    }
}
