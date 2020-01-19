package com.pin.chen.dao.mapper;

import com.pin.chen.dao.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * UserDao继承基类
 */
@Repository
public interface UserDao extends MyBatisBaseDao<User, String> {
}