package com.xingyun.user.controller;

import com.xingyun.remote.code.ICodeRemoteService;
import com.xingyun.user.model.Login;
import com.xingyun.user.model.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ICodeRemoteService codeRemoteService;

    @PostMapping("/login")
    public String login(@RequestBody Login login){

        String sql = "select email,password from member where email = ?";

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


}
