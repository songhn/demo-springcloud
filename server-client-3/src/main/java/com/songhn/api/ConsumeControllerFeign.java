package com.songhn.api;

import com.songhn.domain.UserVO;
import com.songhn.server.DcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author : songhn
 * @className: ConsumeControllerFeign
 * @company : Boboqi Software Co.,Ltd.
 * @date : 2019-04-09 13:06
 * @description: <p>TODO</p>
 */
@RestController
public class ConsumeControllerFeign {

    @Resource
    private DcClient dcClient;

    @GetMapping("/feign-consumer")
    public String helloConsumer(){
        return dcClient.consumer();
    }

    @GetMapping("/test")
    public String test(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(dcClient.test1("DIDI")).append("\n");
        stringBuilder.append(dcClient.test2("DIDI",30)).append("\n");
        UserVO userVO = new UserVO();
        userVO.setName("DIDI");
        userVO.setAge(30);
        stringBuilder.append(dcClient.test3(userVO));
        return stringBuilder.toString();
    }

    @GetMapping("/testOvertime")
    public String testOvertime(){
        return dcClient.testOvertime();
    }


}
