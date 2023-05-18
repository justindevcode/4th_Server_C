package carrot.backend.domain.openapi.controller;

import carrot.backend.domain.openapi.service.OpenApiService;
import carrot.backend.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static carrot.backend.response.Response.success;
import static carrot.backend.response.SuccessMessage.SUCCESS_TO_GET_OPEN_API_INFO;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bus")
public class OpenApiController {

    private final OpenApiService openApiService;

    @ResponseStatus(OK)
    @GetMapping()
    public Response getOpenApiInfo() {
        return success(SUCCESS_TO_GET_OPEN_API_INFO, openApiService.getOpenApiInfo());
    }
}
