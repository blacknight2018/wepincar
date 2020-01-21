package com.pin.chen.service;

import com.pin.chen.dao.mapper.NewsDao;
import com.pin.chen.dao.pojo.News;
import com.pin.chen.utils.Response;
import com.pin.chen.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NewsService {

    @Autowired
    NewsDao newsDao;

    public Response CreateNews(String title,String text){
        try{

            News createNew = new News();
            createNew.setTitle(title);
            createNew.setText(text);
            int insertNums = newsDao.insertSelective(createNew);
            if(0==insertNums)
                return new Response(Response.FAIL);
            return new Response(Response.OK);

        }
        catch(Exception e){
            return new Response(Response.EXCEPTION,e);
        }
    }

    public Response GetNews(){
        try {
            List<News> newsList = newsDao.select();
            ResponseData responseData = new ResponseData();
            responseData.setNewsList(newsList);

            return new Response(Response.OK,responseData);

        } catch(Exception e){
            return new Response(Response.EXCEPTION,e);
        }
    }


}
