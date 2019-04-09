package com.songhn.api;

import com.songhn.domain.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author : songhn
 * @className: DcController
 * @company : Boboqi Software Co.,Ltd.
 * @date : 2019-04-09 13:05
 * @description: <p>TODO</p>
 */
@RestController
public class DcController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("dc")
    public String dc(){
        String services = "Services-2 " + discoveryClient.getServices();
        System.out.println(services);
        return services;
    }


    @GetMapping("test1")
    public String test1(@RequestParam String name) {
        return "Services-2 " + name;
    }

    @GetMapping("test2")
    public String test2(@RequestHeader String name, @RequestHeader Integer age) {
        return "Services-2 " + "{'name':'" + name + "','age':" + age + "}";
    }

    @PostMapping("test3")
    public String test3(@RequestBody UserVO userVO){
        return "Services-2 " + "{'name':'" + userVO.getName() + "','age':" + userVO.getAge() + "}";
    }
}
