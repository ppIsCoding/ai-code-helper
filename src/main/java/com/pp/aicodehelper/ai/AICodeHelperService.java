package com.pp.aicodehelper.ai;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.Result;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import reactor.core.publisher.Flux;

import java.util.List;
public interface AICodeHelperService {
    @SystemMessage(fromResource = "system-prompt.txt")
    String chat(
//            @MemoryId int id,
            String message);

    @SystemMessage(fromResource = "system-prompt.txt")
    Report chatWithReport(
            String message);

    /**
     * 带RAG的聊天  Result封装结果，可以获取RAG来源以及token统计等信息
     * @param message
     * @return
     */
    @SystemMessage(fromResource = "system-prompt.txt")
    Result<String> chatWithRAG(
            String message);

    // 学习报告 用到了record，可以理解为一个类即可
    record Report(String name, List<String> suggestions){};

    @SystemMessage(fromResource = "system-prompt.txt")
    Flux<String> chatStream(@MemoryId int memoryId , @UserMessage String message);

}
