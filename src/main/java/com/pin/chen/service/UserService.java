package com.pin.chen.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.pin.chen.dao.mapper.MyBatisBaseDao;
import com.pin.chen.dao.mapper.PinDao;
import com.pin.chen.dao.mapper.UserDao;
import com.pin.chen.dao.pojo.Pin;
import com.pin.chen.dao.pojo.User;
import com.pin.chen.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    PinDao pinDao;

    /**
     * 登录,带有用户的头像和姓名
     * @param userOpenId 用户openId
     * @param userHeadUrl 头像链接
     * @param userNickName 姓名
     * @return
     */
    public Response loginWithOpenIdData(String userOpenId,String userHeadUrl,String userNickName)
    {
        try
        {
            User loginUser = null;
            loginUser = (User) userDao.selectByPrimaryKey(userOpenId);
            loginUser.setHead(userHeadUrl);
            loginUser.setName(userNickName);
            loginUser.setLogintime(new Date());
            userDao.updateByPrimaryKeySelective(loginUser);
            if(loginUser!=null)
                return new Response(Response.OK);


            loginUser = new User();
            loginUser.setOpenid(userOpenId);
            loginUser.setHead(userHeadUrl);
            loginUser.setName(userNickName);
            int insertNum =userDao.insertSelective(loginUser);
            if(insertNum==0)
                return new Response(Response.FAIL);
            return new Response(Response.OK);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return new Response(Response.EXCEPTION,e);
        }
    }


    /**
     * 加入拼车
     * @param pinId 要加入的pinID
     * @return
     */
    public Response joinByPinId(String userOpenId,String pinId)
    {
        try
        {
            int pinIdInt = Integer.parseInt(pinId);
            Pin selectPin = (Pin)pinDao.selectByPrimaryKey(pinIdInt);
            String passengerStringList = selectPin.getPassenger();
            if(null!=passengerStringList)
            {
                JSONArray passengerJsonArray = JSON.parseArray(passengerStringList);
                if(null!=passengerJsonArray)
                {
                    //判断是否已经加入拼单了
                    for(int i=0,passengerLen = passengerJsonArray.size();i<passengerLen;i++)
                    {
                        String passengerOpenId = (String)passengerJsonArray.get(i);
                        if(passengerOpenId.equals(userOpenId)){
                            return  new Response(Response.FAIL);
                        }
                    }

                    //如果乘客满了
                    if(passengerJsonArray.size()==selectPin.getReqnum())
                    {
                        return  new Response(Response.FAIL);
                    }

                    //加入
                    passengerJsonArray.add(userOpenId);

                    selectPin.setPassenger(passengerJsonArray.toJSONString());

                    int updateNum = pinDao.updateByPrimaryKeySelective(selectPin);
                    if(updateNum==0)
                        return  new Response(Response.FAIL);
                     return  new Response(Response.OK);

                }
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
            return new Response(Response.EXCEPTION,e);
        }
        return  new Response(Response.FAIL);
    }

}
