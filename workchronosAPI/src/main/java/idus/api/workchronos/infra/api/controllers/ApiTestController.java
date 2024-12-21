package idus.api.workchronos.infra.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class ApiTestController {

        @GetMapping("/test")
        @ResponseBody
        public String testEndpoint() {
            return  "The API is working!";
        }
}
