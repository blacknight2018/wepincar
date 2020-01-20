package com.pin.chen.controller;

import com.pin.chen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/change")
    @ResponseBody
    public String ChangeUserRole(HttpSession httpSession)
    {
        String openid = (String)httpSession.getAttribute("openid");
        return userService.changeRole(openid).getMsgString();

    }

    @PostMapping("join")
    @ResponseBody
    public String JoinPinByPinId(@RequestBody Map<String,String> Params, HttpSession httpSession)
    {
        String pinid = Params.get("PinId");
        String openid = (String)httpSession.getAttribute("openid");

        return userService.joinByPinId(openid,pinid).getMsgString();
    }
}
