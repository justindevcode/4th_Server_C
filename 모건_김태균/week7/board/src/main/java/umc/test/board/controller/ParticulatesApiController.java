package umc.test.board.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.test.board.service.ParticulatesApiService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ParticulatesApiController {

        private final ParticulatesApiService particulatesApiService;

        @GetMapping()
        public String getParticulatesApi(){ return particulatesApiService.getParticulatesApiInfo();}
}
