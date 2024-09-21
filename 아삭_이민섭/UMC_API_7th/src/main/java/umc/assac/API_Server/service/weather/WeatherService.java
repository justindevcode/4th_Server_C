package umc.assac.API_Server.service.weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import umc.assac.API_Server.dto.weather.WeatherInfoDto;
import umc.assac.API_Server.dto.weather.WeatherListDto;
import umc.assac.API_Server.dto.weather.WeatherResponseDto;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.time.LocalDateTime;

@Service
@Configuration
public class WeatherService {

    @Value("${openapi.request.url}")
    public String requestURL;

    @Value("${openapi.request.key}")
    public String requestKey;

    // 공공 API를 활용하여 지역의 날씨 정보를 반환하는 로직
    public WeatherResponseDto getWeatherInfo() {
        URL url = getUrl();
        String result = "";

        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");

            BufferedReader rd;
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            result = rd.readLine();


        } catch(IOException e) {

        }

        WeatherListDto listDto = parseWeatherInfo(result);
        return getResultDto(listDto);
    }

    // 응답 데이터를 파싱하여 DTO 파일로 변환하는 로직
    public WeatherListDto parseWeatherInfo(String result) {
        ObjectMapper objectMapper = new ObjectMapper();

        JSONObject object = (JSONObject) parseData(result);
        Object responseValue = object.get("response");

        JSONObject getBody = (JSONObject) parseData(responseValue.toString());
        Object bodyValue = getBody.get("body");

        JSONObject getItems = (JSONObject) parseData(bodyValue.toString());
        Object items = getItems.get("items");

        WeatherListDto resultDto = null;

        try {
            resultDto = objectMapper.readValue(items.toString(), WeatherListDto.class);
        } catch (JsonProcessingException e) {

        }

        return resultDto;
    }

    // 요청에 대한 응답 파일을 생성하는 로직
    public WeatherResponseDto getResultDto(WeatherListDto listDto) {
        String predictDate = "";
        String predictTime = "";
        String humidity = "";
        String rainFall = "";
        String temperature = "";
        String rainType = "";
        String rainCondition = "";

        for(WeatherInfoDto value : listDto.getItem()) {
            String category = value.getCategory();
            predictDate = value.getBaseDate();
            predictTime = value.getBaseTime();

            if(category.equals("PTY")) {
                rainType = value.getObsrValue();
                rainCondition = getRainStatus(rainType);
            }
            if(category.equals("REH")) humidity = value.getObsrValue() + " %";
            if(category.equals("RN1")) rainFall = value.getObsrValue();
            if(category.equals("T1H")) temperature = value.getObsrValue() + " 'C";
        }

        return new WeatherResponseDto(predictDate, predictTime, humidity, rainFall, temperature, rainType, rainCondition);
    }

    // 강수 코드에 따라서 출력할 메세지를 설정하는 로직
    public String getRainStatus(String value) {
        switch(value) {
            case "0":
                return "비 예보 없음";

            case "1":
                return "비";

            case "2":
                return "비 혹은 눈";

            case "3":
                return "눈";

            case "5":
                return "빗방울 조금";

            case "6":
                return "빗방울 혹은 눈날림";

            case "7":
                return "눈날림";
        }

        return "";
    }

    // 데이터를 파싱하여 Json 데이터로 만드는 로직
    public Object parseData(String value) {
        JSONParser parser = new JSONParser();
        Object parse = null;
        try {
            parse = parser.parse(value);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return parse;
    }

    // 요청에 필요한 URL을 생성하는 로직
    public URL getUrl() {
        StringBuilder urlBuilder = new StringBuilder(requestURL);
        URL url = null;

        LocalDateTime now = LocalDateTime.now();

        String requestDate = getRequestDate(now);
        String requestTime = getRequestTime(now);

        try {
            urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + requestKey);
            urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(requestDate, "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode(requestTime, "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode("77", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode("122", "UTF-8"));

            url = new URL(urlBuilder.toString());
        } catch (IOException e) {

        }

        return url;
    }

    // 요청하는 날짜를 구하는 로직
    public String getRequestDate(LocalDateTime now) {
        String yearValue = trimValue(now.getYear());
        String monthValue = trimValue(now.getMonthValue());
        String dayValue = trimValue(now.getDayOfMonth());

        return yearValue + monthValue + dayValue;
    }

    // 요청하는 시간을 구하는 로직
    public String getRequestTime(LocalDateTime now) {
        String hourValue = trimValue(now.getHour());
        String minValue = trimValue(now.getMinute());

        return hourValue + minValue;
    }

    // 요청하기 위한 문자열을 다듬는 로직
    public String trimValue(int value) {
        String strValue = String.valueOf(value);

        int length = strValue.length();

        if(length >= 2) return strValue;
        return "0" + strValue;
    }
}