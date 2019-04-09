package com.songhn.server;

import com.songhn.domain.UserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author : songhn
 * @className: DcClient
 * @company : Boboqi Software Co.,Ltd.
 * @date : 2019-04-09 13:06
 * @description: <p>TODO</p>
 */
@FeignClient("server-client-1")
public interface DcClient {

    @GetMapping("/dc")
    String consumer();

    @GetMapping("/test1")
    String test1(@RequestParam("name") String name);

    @GetMapping("/test2")
    String test2(@RequestHeader("name") String name,@RequestHeader("age") Integer age);

    @PostMapping("/test3")
    String test3(@RequestBody UserVO userVO);

    @GetMapping("/overtime")
    String testOvertime();
}
