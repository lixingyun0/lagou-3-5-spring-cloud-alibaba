package com.xingyun.remote.email;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("lagou-service-email")
@RequestMapping("email")
public interface IEmailRemoteService {

    @GetMapping("/send-email")
    void sendEmail(@RequestParam("email") String email, @RequestParam("code") String code);
}
