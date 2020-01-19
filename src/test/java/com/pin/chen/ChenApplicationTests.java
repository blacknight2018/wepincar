package com.pin.chen;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ChenApplicationTests {

    @Test
    void contextLoads() {
        JSONArray passengerJsonArray = new JSONArray();
        String passengerListString = "[\"aa\",\"BB\"]";
        passengerJsonArray = JSON.parseArray(passengerListString);
        List<String> stringList= JSONArray.parseArray(passengerJsonArray.toJSONString(),String.class);
        System.out.println(stringList.get(1));
    }

}
