package umc.test.board.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Service
public class ParticulatesApiService {
    @Value("${api_key}")
    private String api_key;

    public String getParticulatesApiInfo(){
        StringBuffer result = new StringBuffer();
        StringBuilder urlBuilder;
        try{
            urlBuilder = new StringBuilder("https://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty");
            urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + api_key);
            urlBuilder.append("&" + URLEncoder.encode("sidoName", "UTF-8") + "=%EA%B2%BD%EA%B8%B0");
            urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=1");
            urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=1");
            urlBuilder.append("&" + URLEncoder.encode("ver", "UTF-8") + "=1.0");
            urlBuilder.append("&" + URLEncoder.encode("returnType", "UTF-8") + "=json");

            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader rd;

            if ( conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));

            }else{
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            String line;
            while ((line = rd.readLine()) != null){
                result.append(line+"\n");
            }
            rd.close();
            conn.disconnect();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result +"";
    }
}
