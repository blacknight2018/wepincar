package com.pin.chen.dao.mapper;

import com.pin.chen.dao.pojo.Log;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * LogDao继承基类
 */
@Repository
public interface LogDao<Model, PK extends Serializable> extends MyBatisBaseDao<Log, Integer> {

    List<Model> selectLogByOpenIdByOperate(String operateType, String userOpenId);
}