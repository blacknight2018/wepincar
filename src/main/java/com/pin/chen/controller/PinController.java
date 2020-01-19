package com.pin.chen.controller;

import com.pin.chen.dao.mapper.PinDao;
import com.pin.chen.dao.mapper.UserDao;
import com.pin.chen.service.PinService;
import com.pin.chen.service.UserService;
import com.pin.chen.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/pin")
public class PinController {

    @Autowired
    PinService pinService;

    @Autowired
    UserService userService;

    @PostMapping("/create")
    @ResponseBody
    public String CreatePin(@RequestBody Map<String,String> Params, HttpSession httpSession)
    {
        String openid = (String)httpSession.getAttribute("openid");
        String reqnum = Params.get("ReqNum");
        String stoptime = Params.get("StopTime");
        String fromcity = Params.get("FromCity");
        String tocity = Params.get("ToCity");
        String price = Params.get("Price");
        String mobile = Params.get("Sender_Mobile");

        return pinService.CreatePin(openid,mobile,reqnum,stoptime,fromcity,tocity,price).getMsgString();
    }

    @PostMapping("/gets")
    @ResponseBody
    public String GetPins(@RequestBody Map<String,String> Params,HttpSession httpSession)
    {
        String openid = (String)httpSession.getAttribute("openid");
        return pinService.selectAllPins(openid).getMsgString();
    }

    @PostMapping("/aboutme")
    @ResponseBody
    public String GetPinsAboutMe(HttpSession httpSession)
    {
        String openid = (String)httpSession.getAttribute("openid");
        return pinService.selectPinsAboutMe(openid).getMsgString();
    }

    @PostMapping("/join")
    @ResponseBody
    public String JoinPinByPinId(@RequestBody Map<String,String> Params,HttpSession httpSession)
    {
        String pinid = Params.get("PinId");
        String openid = (String)httpSession.getAttribute("openid");
        return userService.joinByPinId(openid,pinid).getMsgString();
    }

    @PostMapping("/delete")
    @ResponseBody
    public String DeletePinByPinId(@RequestBody Map<String,String> Params,HttpSession httpSession)
    {
        String pinid = Params.get("PinId");
        String openid = (String)httpSession.getAttribute("openid");
        return pinService.deletePinByPinId(openid,pinid).getMsgString();
    }
}
