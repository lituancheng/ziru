package com.leon.ziru.service;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.leon.ziru.exception.BusinessError;
import com.leon.ziru.exception.BusinessException;
import com.leon.ziru.model.RoomDetailResp;
import com.leon.ziru.model.RoomDetailResp.RoomDetailData;
import com.leon.ziru.model.consts.CityCode;
import com.leon.ziru.util.HttpClientUtil;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ZiruService {

    private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

    private static final String ZR_DETAIL_PATTERN = "https?://m\\.ziroom.com/(.*?)/room\\?id=([0-9]+).*";
    private static final String DETAIL_TEMPLATE = "http://m.ziroom.com/v7/room/detail.json?city_code=%s&id=%s";

    public static RoomDetailData getDetail(String url) throws Exception {
        Pattern compile = Pattern.compile(ZR_DETAIL_PATTERN);
        Matcher matcher = compile.matcher(url);
        if(matcher.find()){
            String code = matcher.group(1);
            CityCode cityCode = CityCode.codeOf(code);
            if(cityCode == null)
                return null;
            String roomId = matcher.group(2);
            Map<String, String> headers = Maps.newHashMap();
            headers.put("Accept", "application/json;version=");
            headers.put("Referer", url);
            headers.put("Host", "m.ziroom.com");
            headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36");
            String content = HttpClientUtil.httpGet(String.format(DETAIL_TEMPLATE, cityCode.getCode(), roomId), headers);
            RoomDetailResp resp = gson.fromJson(content, RoomDetailResp.class);
            if(resp.error_code == 0)
                return resp.data;
            else
                throw new BusinessException(BusinessError.ZIRU_GET_DATA_ERROR);
        }else {
            throw new BusinessException(BusinessError.GENENRAL, "链接格式错误");
        }
    }

    public static void main(String[] args) throws Exception {
        RoomDetailData data = getDetail("http://m.ziroom.com/SZ/room?id=61378371&from=singlemessage");
        System.out.println(data.bedroom);
    }
}
