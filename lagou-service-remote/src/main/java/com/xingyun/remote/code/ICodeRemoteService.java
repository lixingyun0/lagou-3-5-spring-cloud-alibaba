package com.xingyun.remote.code;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("lagou-service-code")
@RequestMapping("/code")
public interface ICodeRemoteService {

    @GetMapping("/check")
    Boolean checkCode(@RequestParam("email") String email,@RequestParam("code")  String code);
}
