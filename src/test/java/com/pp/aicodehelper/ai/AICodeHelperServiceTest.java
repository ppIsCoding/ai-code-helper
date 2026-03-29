package com.pp.aicodehelper.ai;

import dev.langchain4j.service.Result;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AICodeHelperServiceTest {

    @Resource
    private AICodeHelperService aiCodeHelperService;

    @Test
    void chat() {
        String message = "你好，我是pp";
        String result = aiCodeHelperService.chat(message);
        System.out.println(result);
    }

    @Test
    void chatwithUserMessage() {
        String message = "你好，我是pp";
        String result = aiCodeHelperService.chat(message);
        System.out.println(result);
        String message2 = "我是谁来着？";
        String result2 = aiCodeHelperService.chat(message2);
        System.out.println(result2);
    }

    @Test
    void chatWithReport() {
        String message = "你好，我是pp";
        AICodeHelperService.Report result = aiCodeHelperService.chatWithReport( message);
        System.out.println(result);
    }

    @Test
    void chatWithRAG(){
        String message = "请问employee表中有哪些字段？";
        Result<String> result = aiCodeHelperService.chatWithRAG(message);
        //来源
        System.out.println(result.sources());
        System.out.println("=========================================");
        //tokenUsage
        System.out.println(result.tokenUsage());
        System.out.println("=========================================");
        //内容
        System.out.println(result.content());
    }

    @Test
    void chatWithTool() {
        String result = aiCodeHelperService.chat("有哪些常见的计算机网络的面试题");
        System.out.println(result);
    }

    @Test
    void chatWithMCP() {
        String result = aiCodeHelperService.chat("什么是程序员鱼皮的编程导航？");
        System.out.println(result);
    }
}