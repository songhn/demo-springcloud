package com.songhn.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : songhn
 * @className: LocationApi
 * @company : Boboqi Software Co.,Ltd.
 * @date : 2019-05-20 19:09
 * @description: <p>TODO</p>
 */
@RestController
public class LocationApi {

    @GetMapping("/local/hello")
    public String hello() {
        return "localhost api";
    }

}
