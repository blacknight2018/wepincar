package com.pin.chen.config;


import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author canger
 * @Deseription
 * @create 2019/2/25 16:30
 **/
@Configuration
public class MybatisConfig {
    public static final String Appid = "wx53e43951ac45b9a4";
    public static final String Secret = "3cd2d8c08ebf9f10ec4abeb34e89b828";
    public static final String LoginUrl = "https://api.weixin.qq.com/sns/jscode2session";
    public static final String resource = "mybatis-config.xml";

    @Bean(name = "dataSource")
    public DataSource dataSource() {

        System.out.println("dataSource");
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/pinche2?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai");
        //System.out.println(dataSourceProperties.getUrl());
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("123");
        dataSource.setTestWhileIdle(false);
        dataSource.setValidationQuery(null);
        return dataSource;

    }

    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        return sqlSessionFactoryBean.getObject();
    }

}