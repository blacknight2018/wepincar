package com.pin.chen.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.pin.chen.dao.mapper.PinDao;
import com.pin.chen.dao.mapper.PinextDao;
import com.pin.chen.dao.mapper.UserDao;
import com.pin.chen.dao.pojo.Pin;
import com.pin.chen.dao.pojo.Pinext;
import com.pin.chen.dao.pojo.User;
import com.pin.chen.utils.Response;
import com.pin.chen.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class PinService {

    @Autowired
    PinDao pinDao;

    @Autowired
    UserDao userDao;

    @Autowired
    PinextDao pinextDao;


    /**
     * 创建拼单的同时，创建附加的拼单信息，拓展的表数据
     * @param pinId
     * @param comment
     * @param pinType
     * @return
     */
    private boolean insertPinExInforMation(int pinId,String comment,Byte pinType)
    {
        Pinext pinext = new Pinext();
        pinext.setPintype(pinType);
        pinext.setComment(comment);
        pinext.setPinid(pinId);
        return 0!=pinextDao.insert(pinext);

    }



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
    public Response CreatePin(String sender_openid,  String sender_mobile,String reqNum, String stopTime, String fromCity, String toCity, String price,String pinType,String comment)
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
            int createPinid = createPin.getPinid();
            this.insertPinExInforMation(createPinid,comment,Byte.parseByte(pinType));


            ResponseData responseData = new ResponseData();
            responseData.setPinid(createPinid);

            if(insertNum==0)
                return  new Response(Response.FAIL);
            return  new Response(Response.OK,responseData);


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

        if(userOpenId.equals(pinItem.getSenderOpenid())){
            responseData.setOwner(true);
        }

        //从表2中找到备注
        Pinext pinext = pinextDao.selectByPrimaryKey(pinItem.getPinid());
        responseData.setComment(pinext.getComment());

        //拼车类型
        responseData.setPintype(pinext.getPintype());

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
     * 获取拼单根据拼单类型
     * @param userOpenId 请求发起者openId
     * @param pinType 拼单类型 0 是人找车 1是车找人
     * @return
     */
    private Response selectPinsByPinType(String userOpenId,String pinType){

        try{
            List<Pin> pinList = pinDao.selectPinByPinType( Integer.parseInt(pinType) );

            List<ResponseData> responseDataList = this.filterFiledFromPinLists(userOpenId,pinList);

            return new Response(Response.OK,responseDataList);

        }catch (Exception e){
            e.printStackTrace();
            return new Response(Response.EXCEPTION,e);
        }
    }

    /**
     * 根据拼单类型和用户Id获取
     * @param userOpenId
     * @param pinType
     * @return
     */
    private Response selectPinsByPinTypeByOpenId(String userOpenId,String pinType){
        try{
            Map<String,String> params = new HashMap<String,String>();

            List<Pin> pinList = pinDao.selectPinByPinTypeByOpenId(Integer.parseInt(pinType),userOpenId);

            List<ResponseData> responseDataList = this.filterFiledFromPinLists(userOpenId,pinList);

            return new Response(Response.OK,responseDataList);

        }catch (Exception e){
            e.printStackTrace();
            return new Response(Response.EXCEPTION,e);
        }
    }


    /**
     * 根据用户范围、拼车类型、时间范围查询拼单
     * @param userOpenId
     * @param userRange 0表示所有 1表示自身的拼单
     * @param pinType  拼单类型
     * @param timeRange 从现在开始到下面N天
     * @return
     */
    public Response selectPinsByPinTypeByOpenIdByTimeRange(String userOpenId,String userRange,String pinType,String timeRange){

        try{

            List<Pin> pinList = null;
            int pinTypeInteger = Integer.parseInt(pinType);
            int userRangeInteger = Integer.parseInt(userRange);
            int timeRangeInteger = Integer.parseInt(timeRange);

            Date curDate = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE,timeRangeInteger);
            Date endDate = calendar.getTime();
            //获取所有拼单 没有时间限制
            if(userRangeInteger==0 && timeRangeInteger==0){
                return  this.selectPinsByPinType(userOpenId,pinType);
            }
            //获取用户自身的拼单 没有时间限制
            else if(userRangeInteger==1 && timeRangeInteger==0){
                return  this.selectPinsByPinTypeByOpenId(userOpenId,pinType);
            }
            //获取所有拼单 有时间限制
            else if(userRangeInteger==0 && timeRangeInteger>0){

                pinList = pinDao.selectPinByPinTypeByStopTime(pinTypeInteger,curDate,endDate);
            }
            //获取用户自身的拼单 有时间限制
            else if(userRangeInteger==1 && timeRangeInteger>0){

                pinList = pinDao.selectPinByPinTypeByOpenIdByStopTime(pinTypeInteger,userOpenId,curDate,endDate);
            }

            List<ResponseData> responseDataList = this.filterFiledFromPinLists(userOpenId,pinList);

            return new Response(Response.OK,responseDataList);

        }catch (Exception e){
            e.printStackTrace();
            return new Response(Response.EXCEPTION,e);
        }
    }


    public void test()
    {
        Date nowDate = new Date();
        Calendar nowCal = Calendar.getInstance();
        nowCal.add(Calendar.DATE,30);
        Date afterDate = nowCal.getTime();
        List<Pin> pinList=pinDao.selectPinByPinTypeByOpenIdByStopTime(1,"ommQp485CAgkqso5FTHVDD-l1iSc",nowDate,afterDate);

        return ;
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

            //权限判断
            Pin delPin = (Pin)pinDao.selectByPrimaryKey(pinId);
            if(!delPin.getSenderOpenid().equals(userOpenId)){
                return new Response(Response.FAIL);
            }


            int delNum=pinDao.deleteByPrimaryKey(pinId);
            if(delNum == 0)
                return new Response(Response.FAIL);

            //再从PinExt表中删除
            delNum=pinextDao.deleteByPrimaryKey(pinId);
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
