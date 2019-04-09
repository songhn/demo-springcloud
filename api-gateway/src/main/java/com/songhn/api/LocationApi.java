package com.songhn.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationApi {

    @GetMapping("/local/hello")
    public String hello() {
        return "localhost api";
    }

}
