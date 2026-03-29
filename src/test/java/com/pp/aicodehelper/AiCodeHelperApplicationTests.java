package com.pp.aicodehelper;

import com.pp.aicodehelper.ai.AICodeHelper;
import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AiCodeHelperApplicationTests {
    @Resource
    private AICodeHelper aiCodeHelper;

    @Test
    void contextLoads() {
    }

    @Test
    void chat() {
        aiCodeHelper.chat("你好，我是pp");
    }

    @Test
    void testChatWithUserMessage() {
        UserMessage userMessage = UserMessage.from(
                TextContent.from("描述图片"),
                ImageContent.from("https://image.baidu.com/search/detail?adpicid=0&b_applid=8740058804362314195&bdtype=0&commodity=&copyright=&cs=4016616023%2C214233492&di=7609719975837696001&fr=click-pic&fromurl=http%253A%252F%252Fmbd.baidu.com%252Fnewspage%252Fdata%252Fdtlandingsuper%253Fnid%253Ddt_4601726110531604723&gsm=18&hd=&height=0&hot=&ic=&ie=utf-8&imgformat=&imgratio=&imgspn=0&is=3076996295%2C2251933238&isImgSet=&latest=&lid=cd44b8af00014028&lm=&objurl=https%253A%252F%252Fmiaobi-lite.bj.bcebos.com%252Fmiaobi%252F5mao%252Fb%252527d29yZOaWh%25252Baho%25252BaJk%25252BS4jeW8gOaYr%25252BaAjuS5iOWbnuS6i18xNzM2NjExNjQ3LjEzNzIzNjY%25253D%252527%252F0.png&os=3076996295%2C2251933238&pd=image_content&pi=0&pn=0&rn=1&simid=4016616023%2C214233492&tn=baiduimagedetail&width=0&word=junit%E6%A1%86%E6%9E%B6%E8%80%81%E6%98%AF%E7%89%88%E6%9C%AC%E4%B8%8D%E5%AF%B9%E6%80%8E%E4%B9%88%E5%8A%9E&z=")
        );
        aiCodeHelper.chat(userMessage);
    }
}
