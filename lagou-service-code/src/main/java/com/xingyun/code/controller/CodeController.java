package com.xingyun.code.controller;

import com.xingyun.remote.email.IEmailRemoteService;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping("/code")
public class CodeController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Reference
    private IEmailRemoteService emailRemoteService;

    @GetMapping("/create")
    public String createCode(String email) {

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            stringBuilder.append(RandomUtils.nextInt(10));
        }
        //放入缓存，有效期10分钟
        stringRedisTemplate.opsForValue().set("code:"+email,stringBuilder.toString(),10, TimeUnit.MINUTES);
        //发送验证码
        emailRemoteService.sendEmail(email,stringBuilder.toString());
        return stringBuilder.toString();
    }
}
