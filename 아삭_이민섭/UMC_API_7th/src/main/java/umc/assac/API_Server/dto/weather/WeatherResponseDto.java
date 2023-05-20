package umc.assac.API_Server.dto.weather;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class WeatherResponseDto {
    private String predictDate; // 예보 날짜
    private String predictTime; // 예보 시간
    private String humidity; // 습도
    private String rainFall; // 1시간 동안의 강수량
    private String temperature; // 기온
    private String rainType; // 강수 코드
    private String rainCondition; // 강수 형태
}
