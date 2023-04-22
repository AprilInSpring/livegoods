package cc.controller;

import cc.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import vo.LivegoodsResult;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public LivegoodsResult login(String username, String password){
        return loginService.login(username,password);
    }

    @PostMapping("/sendyzm")
    public LivegoodsResult send(String phone){
        return loginService.sendCode(phone);
    }
}
