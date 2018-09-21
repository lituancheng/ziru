package com.leon.ziru.controller;

import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Created by lituancheng on 2018/9/21
 */
public class Test {

    static String ZI_DETAIL_PATTERN = "https?://.+?\\.ziroom.com/z/vr/.+?\\.html";

    public static void main(String[] args) throws IOException {
        System.out.println(Pattern.matches(ZI_DETAIL_PATTERN, "https://sz.ziroom.com/z/vr/12rfaf.html"));
        /*DefaultHttpClient client = new DefaultHttpClient();
        HttpUriRequest request = new HttpGet("http://www.ziru.com/12qrwfa");
        CloseableHttpResponse execute = client.execute(request);
        HttpEntity entity = execute.getEntity();
        InputStream content = entity.getContent();
        System.out.println(123);*/
    }
}
