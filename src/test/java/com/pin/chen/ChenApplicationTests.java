package com.pin.chen;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SpringBootTest
class ChenApplicationTests {

    @Test
    void contextLoads() {
        Date nowDate = new Date();
        Calendar nw = Calendar.getInstance();
        nw.add(Calendar.DATE,3);
        nowDate = nw.getTime();

        return ;
    }

}
