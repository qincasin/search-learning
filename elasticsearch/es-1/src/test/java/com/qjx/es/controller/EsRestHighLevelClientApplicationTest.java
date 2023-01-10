package com.qjx.es.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qjx.es.dao.GoodsMapper;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.cluster.metadata.MappingMetadata;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author qinjiaxing on 2023/1/9
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class EsRestHighLevelClientApplicationTest {

    static final ObjectMapper objectMapper = new ObjectMapper().setDateFormat(
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    @Resource
    RestHighLevelClient restHighLevelClient;
    @Resource
    GoodsMapper goodsMapper;

    @Test
    public void test() {
        System.out.println("111");
    }

    /**
     * 创建索引库和映射表结构 注意：索引一般不会这样创建 ,,,, 工作中都是提交工单到架构负责es集群的同时 经过严格的审核之后才会创建索引
     *
     * @throws Exception
     */
    @Test
    public void indexCreate() throws Exception {
        // 通过rhlClient 拿到一个 索引操作的客户端。
        // 数据相关的操作 都会使用 restHIghLevelClient 直接搞..
        IndicesClient indicesCLient = restHighLevelClient.indices();
        //创建索引的请求
        CreateIndexRequest indexRequest = new CreateIndexRequest("goods");

        //品牌
        Map<String, String> brandName = new HashMap<>();
        brandName.put("type", "keyword");
        //分类
        Map<String, Object> categoryName = new HashMap<>();
        categoryName.put("type", "keyword");
        // 创建时间
        Map<String, Object> createTime = new HashMap<>();
        createTime.put("type", "date");
        createTime.put("format", "yyyy-MM-dd HH:mm:ss");
        // 商品ID
        Map<String, Object> id = new HashMap<>();
        id.put("type", "keyword");
        // 价格
        Map<String, Object> price = new HashMap<>();
        price.put("type", "double");
        // 销售量
        Map<String, Object> saleNum = new HashMap<>();
        saleNum.put("type", "integer");
        // 状态
        Map<String, Object> status = new HashMap<>();
        status.put("type", "integer");
        // 库存
        Map<String, Object> stock = new HashMap<>();
        stock.put("type", "integer");
        // 标题，商品名称信息
        Map<String, Object> title = new HashMap<>();
        title.put("type", "text");
        title.put("analyzer", "ik_max_word");
        title.put("search_analyzer", "ik_smart");

        Map<String, Object> properties = new HashMap<>();
        properties.put("brandName", brandName);
        properties.put("categoryName", categoryName);
        properties.put("createTime", createTime);
        properties.put("id", id);
        properties.put("price", price);
        properties.put("saleNum", saleNum);
        properties.put("status", status);
        properties.put("stock", stock);
        properties.put("title", title);

        Map<String, Object> mappingMap = new HashMap<>();
        mappingMap.put("properties", properties);
        //将mapping 信息 加入到 创建索引请求的mapping 字段中
        indexRequest.mapping(mappingMap);

        Map<String, Object> settings = new HashMap<>();
        settings.put("number_of_shards", 3);
        settings.put("number_of_replicas", 1);

        indexRequest.settings(settings);

        //请求服务器
        CreateIndexResponse response = indicesCLient.create(indexRequest, RequestOptions.DEFAULT);
        System.out.println(response.isAcknowledged());

    }

    /**
     * 获取索引mapping信息
     */
    @Test
    public void getMapping() throws IOException {
        IndicesClient indices = restHighLevelClient.indices();
        //创建get请求
        GetIndexRequest request = new GetIndexRequest("goods");
        //发送get请求
        GetIndexResponse response = indices.get(request, RequestOptions.DEFAULT);
        //获取表结构
        Map<String, MappingMetadata> mappings = response.getMappings();
        for (String key : mappings.keySet()) {
            log.info("key ---- {} ", mappings.get(key).getSourceAsMap());
        }
    }


}
