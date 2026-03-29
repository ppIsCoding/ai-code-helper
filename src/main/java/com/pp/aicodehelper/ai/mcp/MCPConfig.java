package com.pp.aicodehelper.ai.mcp;

import dev.langchain4j.mcp.McpToolProvider;
import dev.langchain4j.mcp.client.DefaultMcpClient;
import dev.langchain4j.mcp.client.McpClient;
import dev.langchain4j.mcp.client.transport.http.HttpMcpTransport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MCPConfig {

    @Value("${bigmodel.api-key}")
    private String apiKey;

    @Bean
    @SuppressWarnings("deprecation")
    public McpToolProvider toolProvider() {
        // MCP 传输 - 使用 HTTP SSE 方式
        HttpMcpTransport transport = new HttpMcpTransport.Builder()
                .sseUrl("https://open.bigmodel.cn/api/mcp/web_search/sse?Authorization=" + apiKey)
                .logRequests(true) // if you want to see the traffic in the log
                .logResponses(true)
                .build();

        // MCP 客户端
        McpClient mcpClient = DefaultMcpClient.builder()
                .key("MyMCPClient")
                .transport(transport)
                .build();

        // MCP 工具提供者
        McpToolProvider toolProvider = McpToolProvider.builder()
                .mcpClients(mcpClient)
                .build();

        return toolProvider;
    }
}
