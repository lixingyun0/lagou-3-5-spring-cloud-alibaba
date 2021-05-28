package com.xingyun.email.remote;

import com.xingyun.remote.email.IEmailRemoteService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.web.bind.annotation.RestController;

@Service
public class EmailRemoteServiceImpl implements IEmailRemoteService {
    @Override
    public void sendEmail(String email, String code) {

        System.out.println(email+"验证码是："+code);
    }
}
