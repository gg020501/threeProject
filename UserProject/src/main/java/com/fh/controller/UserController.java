package com.fh.controller;

import com.fh.model.User;
import com.fh.service.UserSerivce;
import com.fh.utils.MD5Util;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {

        @Resource
        private UserSerivce userSerivce;

        Map map = new HashMap();


        @PostMapping("UserLogin")
        private Map UserLogin(String name,String password){
            String encoder = MD5Util.encoder(name);
            String encoder1 = MD5Util.encoder(password);
            String encoder2 = (encoder+encoder1);
            User user = userSerivce.selectNameOne(name);
            if(user == null){
                //帐号不存在
                map.put("code",202);
            }else{
                if(user.getPassword().equals(encoder2)){
                    //登录成功
                    map.put("code",200);
                }else{
                    //密码不正确
                    map.put("code",201);
                }
            }
            return map;
        }

            @PostMapping("insertUser")
            private Map insertUser(User user){
                String encoder = MD5Util.encoder(user.getName());
                String encoder1 = MD5Util.encoder(user.getPassword());
                String encoder2 = (encoder+encoder1);
                user.setPassword(encoder2);
                user.setCreateDate(new Date());
                userSerivce.insertUser(user);
                //新增成功
                map.put("code",200);
                return map;
            }


}
