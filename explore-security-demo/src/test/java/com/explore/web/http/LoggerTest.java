package com.explore.web.http;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

public class LoggerTest {

    @Test
    public void LoggerLevelTest() throws IOException {
        String schema = "com.explore";
        String configuredLevel = "DEBUG";
        //模拟POST请求，更改日志级别 "OFF","ERROR","WARN","INFO","DEBUG","TRACE"
        final String url ="http://127.0.0.1:8060/loggers/"+schema;

        //创建一个默认的连接对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        HttpPost httpPost = new HttpPost(url);

        String httpStr;
        try {
            StringEntity entity = new StringEntity("{\"configuredLevel\":\""+configuredLevel+"\"}");
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");

            httpPost.setEntity(entity);
            response = httpClient.execute(httpPost);
            System.out.println("Response StatusCode : "+response.getStatusLine().getStatusCode());
            httpStr = EntityUtils.toString(response.getEntity(), "UTF-8");

        } finally {
            if (response != null) {
                EntityUtils.consume(response.getEntity());
            }
        }
        System.out.println(httpStr);
    }
}
