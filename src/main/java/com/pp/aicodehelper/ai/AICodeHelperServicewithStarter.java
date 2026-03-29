package com.pp.aicodehelper.ai;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;


public interface AICodeHelperServicewithStarter {
    @SystemMessage(fromResource = "system-prompt.txt")
    String chat(String message);

}
