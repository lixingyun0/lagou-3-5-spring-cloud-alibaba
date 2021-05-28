package com.xingyun.user.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.xingyun.remote.code.ICodeRemoteService;
import com.xingyun.user.model.Login;
import com.xingyun.user.model.Register;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RefreshScope
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Value("${wx.name}")
    private String name;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Reference
    private ICodeRemoteService codeRemoteService;

    @PostMapping("/login")
    @SentinelResource()
    public String login(@RequestBody Login login){

        log.info("sdfsfsfsdfs+sfsfsf");
        String sql = "select email,password from member where email = ?";

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Map<String, Object> stringObjectMap = jdbcTemplate.queryForMap(sql,login.getEmail());
        String password = stringObjectMap.get("password").toString();
        if (password.equals(login.getPassword())){
            return "success";
        }else {
            return "fail";
        }
    }

    @PostMapping("/register")
    public String register(@RequestBody Register register){

        String insertSql = "insert into member (email,password) values(?,?)";
        Boolean aBoolean = codeRemoteService.checkCode(register.getEmail(), register.getCode());
        if (!aBoolean){
            return "验证码错误";
        }

        jdbcTemplate.update(insertSql,register.getEmail(),register.getPassword());

        return "success";
    }

    @GetMapping("/config")
    public String register(){


        return name;
    }




}
