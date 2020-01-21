package com.pin.chen.service;

import com.pin.chen.dao.mapper.LogDao;
import com.pin.chen.dao.pojo.Log;
import com.pin.chen.dao.pojo.Pin;
import com.pin.chen.utils.Response;
import com.pin.chen.utils.ResponseData;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class PinExService {

    @Autowired
    LogDao logDao;

    //CreatePin(String sender_openid,  String sender_mobile,String reqNum, String stopTime, String fromCity, String toCity, String price,String pinType,String comment)
    @Around("execution(* com.pin.chen.service.PinService.CreatePin(String,String,String,String,String,String,String,String,String)) &&  args( sender_openid,   sender_mobile, reqNum,  stopTime,  fromCity,  toCity,  price, pinType, comment) ")
    public Response DeniedCreatePin(ProceedingJoinPoint joinPoint,String sender_openid,  String sender_mobile,String reqNum, String stopTime, String fromCity, String toCity, String price,String pinType,String comment) throws Throwable {

        System.out.println("要创建了:"+sender_openid);

        boolean allowCreated = true;
        Response response = new Response(Response.FAIL);

        List<Pin> pinList = logDao.selectLogByOpenIdByOperate("CreatePin",sender_openid);
        allowCreated = pinList.size()< 3 ;

        if(allowCreated)
        {
            response = (Response)joinPoint.proceed();
            Log log = new Log();
            log.setOperate("CreatePin");
            log.setParam1(sender_openid);
            ResponseData responseData = (ResponseData)response.getMsgData();
            if(responseData!=null){
                log.setParam2(responseData.getPinid()+"");
            }
            logDao.insertSelective(log);
        }
        else
        {
            response = new Response(Response.NOWALLOWDCREATE);
        }


        return response;
    }
}
