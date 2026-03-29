# AI 编程小助手

这是一个基于 Spring Boot 和 LangChain4j 的 AI 编程学习助手应用，集成了阿里云 DashScope 大模型（Qwen3.5），旨在帮助用户解答编程学习和求职面试相关的问题。

## ✨ 功能特点

- 🤖 **智能对话**：基于 Qwen3.5 大模型的智能问答系统
- 📚 **学习路线规划**：提供清晰的编程学习路径建议
- 💼 **项目学习指导**：推荐适合的项目并提供学习建议
- 📝 **简历优化**：程序员简历优化建议和技巧
- 🎯 **面试辅导**：高频面试题解析和面试技巧分享
- 🔗 **MCP 支持**：集成 Model Context Protocol，支持工具调用
- 🔄 **流式响应**：实时流式输出，提升用户体验

## 🛠️ 技术栈

### 后端技术

- **Java 21**
- **Spring Boot 3.5.13**
- **LangChain4j 1.12.2** - 大模型集成框架
- **阿里云 DashScope** - Qwen3.5-27b 大模型
- **嵌入模型** - text-embedding-v3
- **jsoup 1.22.1** - HTML 页面解析
- **Lombok** - 代码简化

### 前端技术

- **Vue 3** - 渐进式 JavaScript 框架
- **Vite 4.4.9** - 下一代前端构建工具
- **Axios 1.5.0** - HTTP 请求库
- **Marked 16.0.0** - Markdown 解析器
- **SSE (Server-Sent Events)** - 实时通信

## 📁 项目结构

```
ai-code-helper/
├── src/main/java/com/pp/aicodehelper/
│   ├── ai/
│   │   ├── config/
│   │   │   └── CorsConfig.java          # CORS 跨域配置
│   │   ├── controller/
│   │   │   └── AIController.java        # AI 聊天接口控制器
│   │   ├── guardrail/                   # 护栏模块（安全控制）
│   │   ├── listener/
│   │   │   └── ChatModelListenerConfig.java  # 聊天模型监听器
│   │   ├── mcp/
│   │   │   └── MCPConfig.java           # MCP 配置
│   │   ├── model/
│   │   │   └── QwenChatModelConfig.java # Qwen 模型配置
│   │   ├── rag/
│   │   │   └── RAGConfig.java           # RAG 检索增强生成配置
│   │   ├── tools/
│   │   │   └── InterviewQuestionnTool.java  # 面试问题工具
│   │   ├── AICodeHelper.java            # AI 服务接口定义
│   │   ├── AICodeHelperService.java     # AI 服务实现
│   │   ├── AICodeHelperServiceFactory.java  # 服务工厂
│   │   └── AICodeHelperServicewithStarter.java
│   └── AiCodeHelperApplication.java     # Spring Boot 启动类
├── src/main/resources/
│   ├── docs/
│   │   └── 数据库设计文档.md
│   ├── static/                          # 静态资源
│   ├── templates/                       # 模板文件
│   ├── application.yaml                 # 主配置文件
│   ├── application-local.yml            # 本地环境配置
│   └── system-prompt.txt                # 系统提示词
└── pom.xml                              # Maven 依赖配置

ai-code-helper-frontend/
├── src/
│   ├── api/
│   │   └── chatApi.js                   # 聊天 API 接口
│   ├── components/
│   │   ├── ChatInput.vue                # 聊天输入框组件
│   │   ├── ChatMessage.vue              # 聊天消息组件
│   │   └── LoadingDots.vue              # 加载动画组件
│   ├── utils/
│   │   └── index.js                     # 工具函数
│   ├── App.vue                          # 主应用组件
│   └── main.js                          # 应用入口
├── index.html                           # HTML 模板
├── vite.config.js                       # Vite 配置
├── package.json                         # 依赖管理
└── README.md                            # 前端项目说明
```

## 🚀 快速开始

### 环境要求

- JDK 21+
- Node.js 16.0+
- Maven 3.6+
- npm 或 yarn

### 1. 克隆项目

```bash
git clone <repository-url>
cd ai-code-helper
```

### 2. 配置环境变量

在系统中设置以下环境变量：

```bash
# 阿里云 DashScope API Key
export DASH_SCOPE_API_KEY=your_dashscope_api_key

# Big Model API Key (如果使用)
export BIG_MODEL_API_KEY=your_bigmodel_api_key
```

或在 `application-local.yml` 中配置：

```yaml
langchain4j:
  community:
    dashscope:
      chat-model:
        api-key: your_dashscope_api_key
      embedding-model:
        api-key: your_dashscope_api_key
      streaming-chat-model:
        api-key: your_dashscope_api_key

bigmodel:
  api-key: your_bigmodel_api_key
```

### 3. 启动后端服务

```bash
# 使用 Maven Wrapper
./mvnw spring-boot:run

# 或使用 Maven
mvn spring-boot:run

# 或打包后运行
mvn clean package
java -jar target/ai-code-helper-0.0.1-SNAPSHOT.jar
```

后端服务将在 `http://localhost:8081/api` 运行

### 4. 启动前端服务

```bash
cd ai-code-helper-frontend

# 安装依赖
npm install

# 启动开发服务器
npm run dev

# 构建生产版本
npm run build

# 预览生产版本
npm run preview
```

前端应用将在 `http://localhost:3000` 运行

## 📋 API 接口

### 聊天接口

```
GET /api/ai/chat
```

**请求参数：**
- `memoryId`: 聊天室 ID（数字）
- `message`: 用户消息（字符串）

**返回：** SSE 流式响应

**示例：**
```
GET /api/ai/chat?memoryId=1&message=你好
```

## ⚙️ 配置说明

### application.yaml 主要配置

```yaml
server:
  port: 8081
  servlet:
    context-path: /api

langchain4j:
  community:
    dashscope:
      chat-model:
        model-name: qwen3.5-27b
        api-key: ${DASH_SCOPE_API_KEY}
      embedding-model:
        model-name: text-embedding-v3
        api-key: ${DASH_SCOPE_API_KEY}
      streaming-chat-model:
        api-key: ${DASH_SCOPE_API_KEY}
        model-name: qwen3.5-27b
```

### 支持的模型

- **聊天模型**: Qwen3.5-27b
- **流式聊天模型**: Qwen3.5-27b
- **嵌入模型**: text-embedding-v3

## 🎯 核心功能

### 1. 智能聊天

- 支持多轮对话
- 实时流式响应
- 自动保存聊天记录
- 支持 Markdown 格式输出

### 2. 学习路线规划

- 根据用户需求定制学习路径
- 提供阶段性学习目标
- 推荐优质学习资源

### 3. 项目学习指导

- 推荐适合的项目案例
- 提供项目实现思路
- 代码示例和技术要点

### 4. 面试辅导

- 高频面试题解析
- 面试技巧分享
- 简历优化建议
- 投递策略指导

## 🧪 测试

```bash
# 运行所有测试
mvn test

# 运行特定测试类
mvn test -Dtest=AICodeHelperServiceTest

# 运行并生成覆盖率报告
mvn clean test jacoco:report
```

## 📝 开发指南

### 添加新的 AI 工具

1. 在 `src/main/java/com/pp/aicodehelper/ai/tools/` 目录下创建新工具类
2. 使用 `@Tool` 注解标记工具方法
3. 在 `MCPConfig` 中注册工具

### 自定义系统提示词

编辑 `src/main/resources/system-prompt.txt` 文件，修改 AI 的行为和角色定位。

### 添加新的模型配置

1. 在 `ai/model` 包下创建配置类
2. 继承基础模型配置
3. 在 `application.yaml` 中添加配置

## 🔧 常见问题

### 1. API Key 无效

确保已正确配置环境变量或在配置文件中设置了正确的 API Key。

### 2. 跨域访问失败

检查 `CorsConfig.java` 配置，确保允许前端的域名访问。

### 3. 模型响应慢

- 检查网络连接
- 确认 API Key 配额充足
- 考虑降低模型参数量

## 📄 许可证

本项目采用 MIT 许可证

## 🤝 贡献指南

欢迎提交 Issue 和 Pull Request！

1. Fork 本项目
2. 创建功能分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 创建 Pull Request

## 📬 联系方式

如有问题或建议，请通过以下方式联系：

- 提交 Issue
- 发送邮件至开发团队

## 🙏 致谢

感谢以下开源项目：

- [Spring Boot](https://spring.io/projects/spring-boot)
- [LangChain4j](https://github.com/langchain4j/langchain4j)
- [阿里云 DashScope](https://www.aliyun.com/product/dashscope)
- [Vue.js](https://vuejs.org/)
- [Vite](https://vitejs.dev/)

---

**Made with ❤️ by the AI Code Helper Team**
