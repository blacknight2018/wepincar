package com.pin.chen.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.pin.chen.dao.mapper.PinDao;
import com.pin.chen.dao.mapper.UserDao;
import com.pin.chen.dao.pojo.Pin;
import com.pin.chen.dao.pojo.User;
import com.pin.chen.utils.Response;
import com.pin.chen.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class PinService {

    @Autowired
    PinDao pinDao;

    @Autowired
    UserDao userDao;


    /**
     * 创建拼车
     *
     * @param sender_openid 拼车发起者的OpenId
     * @param sender_mobile 拼车发起者的手机号
     * @param reqNum 拼车人数要求
     * @param stopTime 拼车截止时间
     * @param fromCity 拼车出发地点
     * @param toCity 拼车终点
     * @param price 拼车至少价格
     * @return 返回包含与该用户有关的pin
     *
     */
    public Response CreatePin(String sender_openid,  String sender_mobile,String reqNum, String stopTime, String fromCity, String toCity, String price)
    {
        try {
            Pin createPin = new Pin();
            createPin.setSenderOpenid(sender_openid);
            createPin.setSenderMobile(sender_mobile);
            createPin.setReqnum(Integer.parseInt(reqNum));
            createPin.setStoptime(new Date(Long.parseLong(stopTime)));
            createPin.setFromcity(fromCity);
            createPin.setTocity(toCity);
            createPin.setPrice(Integer.parseInt(price));
            JSONArray passengerJsonArray = new JSONArray();
            passengerJsonArray.add(sender_openid);
            createPin.setPassenger(passengerJsonArray.toJSONString());
            int insertNum = pinDao.insertSelective(createPin);
            if(insertNum==0)
                return  new Response(Response.FAIL);
            return  new Response(Response.OK);


        }
        catch (Exception e){
            e.printStackTrace();
            return new Response(Response.EXCEPTION,e);
        }

    }

    /**
     *
     * 根据用户的信息,过滤掉一些信息再返回到前端,不处理异常
     * @param userOpenId 用户的OpenId
     * @return 返回直接给前端的数据
     *
     */
    private ResponseData filterFiledFromPin(String userOpenId,Pin pinItem)
    {

        User filterUser = userDao.selectByPrimaryKey(userOpenId);
        ResponseData responseData = new ResponseData();

        //乘客字段 数据库保存的是字符串数组Json格式,需要转换
        String passengerListString = pinItem.getPassenger();
        JSONArray passengerJsonArray = new JSONArray();
        if(passengerListString != null)
            passengerJsonArray = JSON.parseArray(passengerListString);
        int passengerNum = 0;
        if(passengerJsonArray!=null)
            passengerNum = passengerJsonArray.size();

        if(filterUser.getType().equals("driver"))
        {
            //如果乘客数量达到要求，可以看到手机号码
            if(passengerNum == pinItem.getReqnum())
            {
                //responseData.setSender_mobile(pinItem.getSenderMobile());
            }
        }
        //乘客身份可以看到自己发的拼单的手机号码
        else if(filterUser.getType().equals("passenger"))
        {

        }
        responseData.setSender_mobile(pinItem.getSenderMobile());

        //共同字段
        responseData.setCarnumber(pinItem.getCarnumber());
        responseData.setCreatetime(pinItem.getCreatetime().getTime()+"");
        responseData.setCurnum(passengerNum);
        responseData.setReqnum(pinItem.getReqnum());
        responseData.setDriver_openid(pinItem.getDriverOpenid());
        responseData.setFromcity(pinItem.getFromcity());
        responseData.setTocity(pinItem.getTocity());
        responseData.setPrice(pinItem.getPrice());
        responseData.setStoptime(pinItem.getStoptime().getTime()+"");
        responseData.setPinid(pinItem.getPinid());
        if(passengerNum>0) {
            List<String> passengerOpenIdList = JSONArray.parseArray(passengerJsonArray.toJSONString(), String.class);
            responseData.setPassenger_name(this.getUserNamesByUserOpenIdList(passengerOpenIdList));
        }


        return responseData;
    }

    /**
     * 根据用户信息过滤掉信息参数返回值都是数组
     * @param userOpenId
     * @param pinItems
     * @return
     */
    private List<ResponseData> filterFiledFromPinLists(String userOpenId,List<Pin> pinItems)
    {
        List<ResponseData> responseDataList = new ArrayList<ResponseData>();
        for(int i=0,listLen = pinItems.size();i<listLen;i++)
        {
            responseDataList.add(this.filterFiledFromPin(userOpenId,pinItems.get(i)));
        }
        return responseDataList;
    }


    /**
     * 根据用户openId获取用户的姓名
     * @param openId 要获取的用户的openid
     * @return
     */
    private String getUserNameByUserOpenId(String openId)
    {
       User selectUser  = userDao.selectByPrimaryKey(openId);
       return selectUser.getName();
    }

    private List<String> getUserNamesByUserOpenIdList(List<String> openIdList)
    {
        List<String> stringList = new ArrayList<String>();
        if(null!=openIdList)
        {
            for(int i=0,len = openIdList.size();i<len;i++)
            {
                stringList.add(this.getUserNameByUserOpenId(openIdList.get(i)));
            }
        }
        return stringList;
    }


    /**
     * 获取所有的拼单
     * @param userOpenId
     * @return
     */
    public Response selectAllPins(String userOpenId)
    {
        try {
            List<Pin> pinList = pinDao.selectAllPin();
            return new Response(Response.OK,this.filterFiledFromPinLists(userOpenId,pinList)) ;
        }
        catch (Exception e){
            e.printStackTrace();
            return new Response(Response.EXCEPTION,e);
        }
    }


    /**
     * 获取跟用户有关的拼车
     *
     * @param userOpenId 用户的OpenId
     * @return 返回包含与该用户有关的pin
     *
     */
    public Response selectPinsAboutMe(String userOpenId)
    {
        try {
            List<Pin> pinList = pinDao.selectPinAboutMeByOpenId(userOpenId);
            List<ResponseData> responseDataList  = this.filterFiledFromPinLists(userOpenId,pinList);
            return new Response(Response.OK,responseDataList);
        }
        catch (Exception e){
            e.printStackTrace();
            return new Response(Response.EXCEPTION,e);
        }

    }

    /**
     * 删除拼单
     * @param delPinId
     * @return
     */
    public Response deletePinByPinId(String userOpenId,String delPinId)
    {
        try
        {
            int pinId = Integer.parseInt(delPinId);
            int delNum=pinDao.deleteByPrimaryKey(pinId);
            if(delNum == 0)
                return new Response(Response.FAIL);
            return new Response(Response.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return new Response(Response.EXCEPTION,e);
        }
    }

}
