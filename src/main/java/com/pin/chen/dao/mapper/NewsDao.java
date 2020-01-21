package com.pin.chen.dao.mapper;

import com.pin.chen.dao.pojo.News;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * NewsDao继承基类
 */
@Repository
public interface NewsDao<Model, PK extends Serializable> extends MyBatisBaseDao<News, Integer> {


    List<Model> select();


}