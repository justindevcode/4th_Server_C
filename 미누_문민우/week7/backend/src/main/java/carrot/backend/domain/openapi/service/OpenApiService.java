package carrot.backend.domain.openapi.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Service
public class OpenApiService {

    public String getOpenApiInfo() {

        StringBuilder result = new StringBuilder();

        try {
            String urlStr = "http://ws.bus.go.kr/api/rest/buspos/getBusPosByRtid?" +
                    "ServiceKey=BGAPY023AwHsL0BoerBl2WcM9KsCzMefsWtBcs34EVdSSV5AlmxErQa%2Bb1vQWtj%2BIPBUQ4RQVk9G%2BfSdhBTRWw%3D%3D" +
                    "&busRouteId=100100118" +
                    "&resultType=json";

            URL url = new URL(urlStr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8));

            String returnLine;
            result.append("<xmp>");
            while((returnLine = br.readLine()) != null) {
                result.append(returnLine).append("\n");
            }
            urlConnection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result + "</xmp>";
    }
}
