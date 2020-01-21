package com.pin.chen.utils;

import com.pin.chen.dao.pojo.News;

import java.util.*;
public class ResponseData {
    private Integer curnum;
    private Integer reqnum;
    private Boolean owner;

    private List<News> newsList;

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }

    public boolean isStartswitch() {
        return startswitch;
    }

    public void setStartswitch(boolean startswitch) {
        this.startswitch = startswitch;
    }

    private boolean startswitch;
    public int getPintype() {
        return pintype;
    }

    public void setPintype(int pintype) {
        this.pintype = pintype;
    }

    private int pintype;

    public Boolean getOwner() {
        return owner;
    }

    public void setOwner(Boolean owner) {
        this.owner = owner;
    }

    public Integer getPinid() {
        return pinid;
    }

    public void setPinid(Integer pinid) {
        this.pinid = pinid;
    }

    private Integer pinid;

    private String fromcity;

    public String getFromcity() {
        return fromcity;
    }

    public void setFromcity(String fromcity) {
        this.fromcity = fromcity;
    }

    public String getTocity() {
        return tocity;
    }

    public void setTocity(String tocity) {
        this.tocity = tocity;
    }

    private String tocity;
    private Integer price;

    private String sender_openid;
    private String createtime;
    private String stoptime;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    private String comment;

    public Integer getCurnum() {
        return curnum;
    }

    public void setCurnum(Integer curnum) {
        this.curnum = curnum;
    }

    public Integer getReqnum() {
        return reqnum;
    }

    public void setReqnum(Integer reqnum) {
        this.reqnum = reqnum;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getSender_openid() {
        return sender_openid;
    }

    public void setSender_openid(String sender_openid) {
        this.sender_openid = sender_openid;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getStoptime() {
        return stoptime;
    }

    public void setStoptime(String stoptime) {
        this.stoptime = stoptime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getPassenger_name() {
        return passenger_name;
    }

    public void setPassenger_name(List<String> passenger_name) {
        this.passenger_name = passenger_name;
    }

    public List<String> getPassenger() {
        return passenger;
    }

    public void setPassenger(List<String> passenger) {
        this.passenger = passenger;
    }

    public Boolean getCancel() {
        return cancel;
    }

    public void setCancel(Boolean cancel) {
        this.cancel = cancel;
    }

    public String getDriver_openid() {
        return driver_openid;
    }

    public void setDriver_openid(String driver_openid) {
        this.driver_openid = driver_openid;
    }

    public String getCarnumber() {
        return carnumber;
    }

    public void setCarnumber(String carnumber) {
        this.carnumber = carnumber;
    }

    private String type;

    private List<String> passenger_name;
    private List<String> passenger;

    private Boolean cancel;         //用户取消了
    private String driver_openid;  //司机点接的时候填充为司机的openid
    private String carnumber;       //司机点接的时候填充为司机的车辆号码

    public String getSender_mobile() {
        return sender_mobile;
    }

    public void setSender_mobile(String sender_mobile) {
        this.sender_mobile = sender_mobile;
    }

    private String sender_mobile;
}
