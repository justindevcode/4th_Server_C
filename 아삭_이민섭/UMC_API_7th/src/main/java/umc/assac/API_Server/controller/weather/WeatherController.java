package umc.assac.API_Server.controller.weather;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import umc.assac.API_Server.response.Response;
import umc.assac.API_Server.service.weather.WeatherService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/weather")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "날씨 조회", notes = "특정 지역의 날씨를 조회하는 로직")
    public Response getWeatherInfo() {
        return Response.success(weatherService.getWeatherInfo());
    }
}
