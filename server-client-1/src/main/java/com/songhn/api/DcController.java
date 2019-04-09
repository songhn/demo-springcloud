package com.songhn.api;

import com.songhn.domain.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

/**
 * @author : songhn
 * @className: DcController
 * @company : Boboqi Software Co.,Ltd.
 * @date : 2019-04-09 15:47
 * @description: <p>TODO</p>
 */
@RestController
public class DcController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("dc")
    public String dc() {
        String services = "Services-1 " + discoveryClient.getServices();
        System.out.println(services);
        return services;
    }

    @GetMapping("test1")
    public String test1(@RequestParam String name) {
        return "Services-1 " + name;
    }

    @GetMapping("test2")
    public String test2(@RequestHeader String name, @RequestHeader Integer age) {
        return "Services-1 " + "{'name':'" + name + "','age':" + age + "}";
    }

    @PostMapping("test3")
    public String test3(@RequestBody UserVO userVO) {
        return "Services-1 " + "{'name':'" + userVO.getName() + "','age':" + userVO.getAge() + "}";
    }

    @GetMapping("overtime")
    public String testOvertime() throws Exception {
        int sleepTime = new Random().nextInt(3000);
        System.out.println("sleepTime" + sleepTime);
        Thread.sleep(sleepTime);
        List<String> serviceInstances = discoveryClient.getServices();
        for (String serviceInstance : serviceInstances) {
            System.out.println("/overtime" + ", service_id:" + serviceInstance);
        }
        return "test overtime";
    }
}
