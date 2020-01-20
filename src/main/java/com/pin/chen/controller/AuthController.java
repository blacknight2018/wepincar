package com.pin.chen.controller;

import com.alibaba.fastjson.JSONObject;
import com.pin.chen.dao.mapper.MyBatisBaseDao;
import com.pin.chen.dao.pojo.Pin;
import com.pin.chen.service.PinService;
import com.pin.chen.service.UserService;
import com.pin.chen.utils.HttpClientUtil;
import com.pin.chen.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.pin.chen.config.*;
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    PinService pinService;


    private  void Test()
    {
        //pinService.selectPinsByPinTypeByOpenId("ommQp485CAgkqso5FTHVDD-l1iSc","1").getMsgData();
        //pinService.selectPinsByPinTypeByOpenIdByTimeRange("ommQp485CAgkqso5FTHVDD-l1iSc","0","0","0");
        //pinService.selectPinsByPinTypeByOpenIdByTimeRange("ommQp485CAgkqso5FTHVDD-l1iSc","0","1","7");
        //pinService.test();

        return ;
    }

    @PostMapping("/login")
    @ResponseBody
    public String loginUser(@RequestBody Map<String,String> Params, HttpSession httpSession)
    {
        //this.Test();

        //code转session
        Map<String, String> GetParams = new HashMap<>();
        GetParams.put("appid", MybatisConfig.Appid);
        GetParams.put("secret", MybatisConfig.Secret);
        GetParams.put("js_code", Params.get("code"));
        GetParams.put("grant_type", "authorization_code");
        String wxResult = HttpClientUtil.doGet(MybatisConfig.LoginUrl, GetParams);
        JSONObject jsonObject = JSONObject.parseObject(wxResult);
        if (jsonObject.get("errmsg") != null) {
            return new Response(Response.FAIL).getMsgString();
        }
        //设置到session中,用来判断用户的请求是否登录
        String openid= (String)jsonObject.get("openid");
        String session_key=(String)jsonObject.get("session_key");
        httpSession.setAttribute("openid",openid);
        httpSession.setAttribute("session_key",session_key);

        return userService.loginWithOpenIdData(openid,Params.get("avatarUrl"),Params.get("nickName")).getMsgString();
    }
}
