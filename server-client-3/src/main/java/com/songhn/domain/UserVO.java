package com.songhn.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : songhn
 * @className: UserVO
 * @company : Boboqi Software Co.,Ltd.
 * @date : 2019-04-09 13:07
 * @description: <p>TODO</p>
 */
@Data
public class UserVO implements Serializable {

    private String name;
    private int age;
}
