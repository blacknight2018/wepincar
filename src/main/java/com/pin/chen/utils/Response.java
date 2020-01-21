package com.pin.chen.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Response {
    public static final int OK = 0;
    public static final int FAIL = 1;
    public static final int NOWALLOWED = 2;
    public static final int PARAMNULL = 3;
    public static final int EXCEPTION = 4;
    public static final int NOTLOGIN = 5;
    public static final int PARAMERROR = 6;
    public static final int NOTREGISTER = 7;
    public static final int NOWALLOWDCREATE = 8;


    private String MsgString;
    private Object MsgData;

    public Object getMsgData() {
        return MsgData;
    }

    public void setMsgData(Object msgData) {
        MsgData = msgData;
    }

    private int MsgCode = FAIL;

    public int getMsgCode() {
        return MsgCode;
    }


    public Response(int msgCode)
    {
        MsgCode = msgCode;
    }
    public Response(int msgCode,Object MsgData)
    {
        MsgCode = msgCode;
        this.MsgData = MsgData;
    }

    public boolean success()
    {
        return this.MsgCode==OK;
    }

    public String getMsgString() {

        switch (MsgCode)
        {
            case OK:
                MsgString = "OK";
                break;
            case FAIL:
                MsgString = "FAIL";
                break;
            case NOWALLOWED:
                MsgString = "NOT ALLOWED";
                break;
            case PARAMNULL:
                MsgString = "PARAM NULL";
                break;
            case EXCEPTION:
                MsgString = "EXCEPTION";
                break;
            case NOTLOGIN:
                MsgString = "YOU ARE NOT LOGIN";
                break;
            case PARAMERROR:
                MsgString = "PARAM ERROR";
                break;
            case NOTREGISTER:
                MsgString = "NOR REGISTER";
                break;
            case NOWALLOWDCREATE:
                MsgString = "一天只能发3次";
                break;
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg",MsgString);
        if(this.MsgData!=null)
            jsonObject.put("data",(this.MsgData));

        return jsonObject.toString();
    }

    @Override
    public String toString() {
        return this.getMsgString();
    }
}

