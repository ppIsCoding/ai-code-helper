package com.pp.aicodehelper.ai.controller;

import com.pp.aicodehelper.ai.AICodeHelperService;
import jakarta.annotation.Resource;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController // @Controller + @ResponseBody
@RequestMapping("/ai")
public class AIController {
    @Resource
    private AICodeHelperService aiCodeHelperService;

    @GetMapping("/chat")
    public Flux<ServerSentEvent<String>> chat(int memoryId,String message) {
        return aiCodeHelperService.chatStream(memoryId,message)
                .map(chunk -> ServerSentEvent.<String>builder()
                        .data(chunk)
                        .build()
                );

    }
}
