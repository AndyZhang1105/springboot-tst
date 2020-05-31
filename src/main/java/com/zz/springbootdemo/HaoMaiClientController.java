package com.zz.springbootdemo;

import com.alibaba.fastjson.JSON;
import com.zz.component.HttpConnectionManager;
import com.zz.util.StringUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;

@RestController
public class HaoMaiClientController {

    HttpConnectionManager connManager = new HttpConnectionManager();

    /*
     * in.close();作用就是将用完的连接释放，下次请求可以复用，这里特别注意的是，如果不使用in.close();
     * 而仅仅使用response.close();结果就是连接会被关闭，并且不能被复用，这样就失去了采用连接池的意义。
     */
    public <T> T get(String path, Class<T> clazz){
        CloseableHttpClient httpClient = connManager.getHttpClient();
        HttpGet httpget = new HttpGet(path);
        String json = null;
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpget);
            InputStream in = response.getEntity().getContent();
            json = StringUtil.streamToString(in);
            in.close();
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            return JSON.parseObject(json, clazz);
        } catch (Exception e) {
            System.out.println(json);
            return null;
        }
    }

    @GetMapping("/getContentByHttpClient")
    public String getContentByHttpClient(@RequestParam(value = "name", defaultValue = "World") String name) {
        return get("https://www.baidu.com", String.class);
    }

}
