package com.leon.ziru.controller;

import com.leon.ziru.util.HttpClientUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Pattern;

/**
 * Created by lituancheng on 2018/9/21
 */
public class Test {

    static String ZI_DETAIL_PATTERN = "https?://.+?\\.ziroom.com/z/vr/.+?\\.html";

    public static void main(String[] args) throws Exception {
        String url = "http://www.ziroom.com/z/vr/61354044.html";
        System.out.println(Pattern.matches(ZI_DETAIL_PATTERN, url));
        String content = HttpClientUtil.httpGet(url, null);
        System.out.println(content);
        /*DefaultHttpClient client = new DefaultHttpClient();
        HttpUriRequest request = new HttpGet("http://www.ziru.com/12qrwfa");
        CloseableHttpResponse execute = client.execute(request);
        HttpEntity entity = execute.getEntity();
        InputStream content = entity.getContent();
        System.out.println(123);*/
    }

}
