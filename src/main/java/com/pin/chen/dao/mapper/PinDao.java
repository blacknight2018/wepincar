package com.pin.chen.dao.mapper;

import com.pin.chen.dao.pojo.Pin;
import com.sun.tools.internal.ws.processor.model.Model;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * PinDao继承基类
 */
@Repository
public interface PinDao<Model, PK extends Serializable> extends MyBatisBaseDao<Pin, Integer> {

    List<Model> selectPinAboutMeByOpenId(String userOpenId);

    List<Model> selectAllPin();

    List<Model> selectPinByPinType(Integer pinType);

    List<Model> selectPinByPinTypeByOpenId(Integer pinType,String userOpenId);

    List<Model> selectPinByPinTypeByOpenIdByStopTime (Integer pinType, String userOpenId, Date startTime,Date endTime);

    List<Model> selectPinByPinTypeByStopTime (Integer pinType, Date startTime,Date endTime);

}