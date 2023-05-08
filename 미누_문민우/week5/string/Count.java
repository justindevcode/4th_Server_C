package week5.string;

import java.io.IOException;

// 1152 단어의 개수
public class Count {
    public static void main(String[] args) throws IOException {

        int count = 0;
        int pre_str = 32; //공백
        int str;

        while (true) {
            str = System.in.read();

            // 입력받은 문자가 공백일 때,
            if (str == 32) {
                // 이전의 문자가 공백이 아니면
                if (pre_str != 32) {
                    count++;
                }
            } else if (str == 10) {  // 입력받은 문자가 개행일 때 ('\n')
                if (pre_str != 32) {
                    count++;
                }
                break;
            }

            pre_str = str;
        }
        System.out.println(count);
    }
}
