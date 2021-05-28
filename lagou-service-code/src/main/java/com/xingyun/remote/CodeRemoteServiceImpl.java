package com.xingyun.remote;

import com.xingyun.remote.code.ICodeRemoteService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RestController;

@Service
public class CodeRemoteServiceImpl implements ICodeRemoteService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Override
    public Boolean checkCode(String email, String code) {

        String s = stringRedisTemplate.opsForValue().get("code:" + email);
        return code.equals(s);
    }


}
