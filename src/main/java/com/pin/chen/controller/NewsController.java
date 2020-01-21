package com.pin.chen.controller;

import com.pin.chen.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    NewsService newsService;

    @PostMapping("/create")
    @ResponseBody
    public  String CreateNews(@RequestBody Map<String,String> Params, HttpSession httpSession)
    {
        String title = Params.get("Title");
        String text = Params.get("Text");
        return newsService.CreateNews(title,text).getMsgString();

    }

    @PostMapping("/gets")
    @ResponseBody
    public String GetNews(){
        return newsService.GetNews().getMsgString();
    }
}
