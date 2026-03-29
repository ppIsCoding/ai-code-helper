package com.pp.aicodehelper.ai.rag;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.splitter.DocumentByParagraphSplitter;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * RAG 配置类
 * 加载RAG 配置
 */
@Configuration
public class RAGConfig {

    @Resource
    private EmbeddingModel qwenEmbeddingModel;

    @Resource
    private EmbeddingStore<TextSegment> embeddingStore;

    @Bean
    public ContentRetriever contentRetriever() {
        // RAG
        // 1、 获取文档
        List<Document> documents = FileSystemDocumentLoader.loadDocuments("src/main/resources/docs");
        // 2、 文档切割，每个段落按照段落进行分割，每一段最大1000个字符。每次最多重叠200个字
        DocumentByParagraphSplitter documentByParagraphSplitter =
                new DocumentByParagraphSplitter(1000, 200);
        // 3、 自定义文档加载器，把文档转换成向量并存到向量数据库
        EmbeddingStoreIngestor embeddingStoreIngestor = EmbeddingStoreIngestor.builder()
                .documentSplitter(documentByParagraphSplitter)
                //为每个切割后的文档碎片，添加文档名称作为元信息 TextSegment
                .textSegmentTransformer(textSegment ->
                     TextSegment.from(textSegment.metadata().getString("fileName")
                            + "\n" + textSegment.text(),textSegment.metadata()))
                // 使用向量模型
                .embeddingModel(qwenEmbeddingModel)
                .embeddingStore(embeddingStore)
                .build();
        // 加载文档
        embeddingStoreIngestor.ingest(documents);
        // 4、自定义内容加载器
        EmbeddingStoreContentRetriever build = EmbeddingStoreContentRetriever.builder()
                .embeddingModel(qwenEmbeddingModel)
                .embeddingStore(embeddingStore)
                .maxResults(5) // 最多返回5个结果
                .minScore(0.75) // 最低分数0.75,过滤作用（需要根据实际数据进行设置）
                .build();
        return build;
    }
}
