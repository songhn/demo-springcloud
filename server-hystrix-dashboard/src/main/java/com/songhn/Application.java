package com.songhn;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author : songhn
 * @className: Application
 * @company : Boboqi Software Co.,Ltd.
 * @date : 2019-04-09 13:09
 * @description: <p>TODO</p>
 */
@EnableHystrixDashboard
@SpringCloudApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
