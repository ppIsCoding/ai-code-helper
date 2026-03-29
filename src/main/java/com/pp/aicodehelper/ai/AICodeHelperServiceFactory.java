package com.pp.aicodehelper.ai;

import com.pp.aicodehelper.ai.tools.InterviewQuestionnTool;
import dev.langchain4j.mcp.McpToolProvider;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.service.AiServices;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AICodeHelperServiceFactory {

    @Resource
    private ChatModel myQwenChatModel;
    @Resource
    private ContentRetriever contentRetriever;
    @Resource
    private McpToolProvider mcpToolProvider;
    @Resource
    private StreamingChatModel qwenStreamingChatModel;

    @Bean
    public AICodeHelperService createAICodeHelperService() {
        // 创建一个 ChatMemory, 用于保存对话历史
        ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);
        // 创建一个 AICodeHelperService
        AICodeHelperService aiCodeHelperService = AiServices.builder(AICodeHelperService.class)
                .chatMemory(chatMemory) // 会话模型
                .streamingChatModel(qwenStreamingChatModel) // 流式会话模型
                .chatModel(myQwenChatModel) // 会话记忆
                // 根据memoryId 创建一个 ChatMemory，可以实现聊天记录保存以及隔离
                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(10))
//                .contentRetriever(contentRetriever) // RAG
                .tools(new InterviewQuestionnTool()) // 工具
                .toolProvider(mcpToolProvider) // mcp
                .build();
        return aiCodeHelperService;
    }
}
