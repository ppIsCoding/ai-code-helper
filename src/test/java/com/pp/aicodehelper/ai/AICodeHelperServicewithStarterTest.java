package com.pp.aicodehelper.ai;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AICodeHelperServicewithStarterTest {
    @Resource
    private AICodeHelperServicewithStarter aiCodeHelperServicewithStarter;
    @Test
    void chat() {
        String message = "你好，我是pp";
        String result = aiCodeHelperServicewithStarter.chat(message);
        System.out.println(result);
    }

}