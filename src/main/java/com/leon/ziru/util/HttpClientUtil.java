package com.leon.ziru.util;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


@SuppressWarnings("deprecation")
public class HttpClientUtil {

    private static Gson gson = new Gson();

    public static String httpGet(String url) throws Exception{
        Map<String, String> map = Maps.newHashMap();
        return httpGet(url, map);
    }

    public static <T> T httpGet(String url, Class<T> clazz) throws Exception{
        String content = httpGet(url);
        return gson.fromJson(content, clazz);
    }

    /**
     * 发送get请求，获取返回内容
     * @param url
     * @param headers
     * @return
     * @throws Exception
     */
    public static String httpGet(String url,Map<String, String> headers) throws Exception{

        String result=""; //返回信息
        //创建一个httpGet请求
        HttpGet request=new HttpGet(url);
        //创建一个htt客户端
        @SuppressWarnings("resource")
        HttpClient httpClient=new DefaultHttpClient();
        //添加cookie到头文件
        for(Entry<String, String> e : headers.entrySet()){
            request.addHeader(e.getKey(), e.getValue());
        }
        //接受客户端发回的响应
        HttpResponse httpResponse=httpClient.execute(request);
        //获取返回状态
        int statusCode=httpResponse.getStatusLine().getStatusCode();
        if(statusCode==HttpStatus.SC_OK){
            //得到客户段响应的实体内容
            HttpEntity responseHttpEntity=httpResponse.getEntity();
            //得到输入流
            InputStream in=responseHttpEntity.getContent();
            //得到输入流的内容
            result=getData(in);
        }
        //Log.d(TAG, statusCode+"");
        return result;
    }


    /**
     * 发送get请求，获取返回头文件header的值
     * @param url 请求地址
     * @param cookie 请求cookie
     * @param header 返回头文件某值
     * @return
     * @throws Exception
     */
    public static String httpGetHead(String url,String cookie,String header) throws Exception{
        //get请求返回头文件
        String strResult = "";
        //创建一个httpGet请求
        HttpGet request=new HttpGet(url);
        //创建一个htt客户端
        @SuppressWarnings("resource")
        HttpClient httpClient=new DefaultHttpClient();
        //添加cookie到头文件
        request.addHeader("Cookie", cookie);
        //接受客户端发回的响应
        HttpResponse httpResponse=httpClient.execute(request);
        //获取返回状态
        int statusCode=httpResponse.getStatusLine().getStatusCode();
        if(statusCode==HttpStatus.SC_OK){
            //取头文件信息
            strResult=httpResponse.getHeaders(header)[0].getValue().toString();
//        	Header[] headers = httpResponse.getAllHeaders();//返回的HTTP头信息
//            for (int i=0; i<headers.length; i++) {
//            	System.out.println(headers[i]);
//            }
        }
        return strResult;
    }


    /**
     * 读取返回的信息
     * @param in
     * @return
     */
    private static String getData(InputStream in) {
        String result="";
        StringBuilder sb=new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line = "";
        try {
            while ((line = br.readLine()) != null) {
                //result = result + line;
                sb.append(line);
            }
            br.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (result != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }


    /**
     * 输入流转换成字符串
     * @param is: 输入流
     * @return 字符串对象
     */
    @SuppressWarnings("unused")
    private static String InputStreamToString(InputStream is){
        BufferedReader reader = null;
        StringBuffer responseText = null;
        String readerText = null;
        try {
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            responseText = new StringBuffer();
            readerText = reader.readLine();
            while(readerText != null){
                responseText.append(readerText);
                responseText.append(System.getProperty("line.separator"));
                readerText = reader.readLine();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseText.toString();
    }

    public static <T> T httpPostJson(String url, String content, Class<T> clazz) {
        String resp = httpPostJson(url, content);
        return gson.fromJson(resp, clazz);
    }

    public static String httpPostJson(String url, String paramJson) {
        StringBuilder sb = new StringBuilder();

        try {
            // 建立http连接
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            // 设置允许输出
            conn.setDoOutput(true);
            // 设置允许输入
            conn.setDoInput(true);
            // 设置不用缓存
            conn.setUseCaches(false);
            // 设置传递方式
            conn.setRequestMethod("POST");
            // 设置维持长连接
            conn.setRequestProperty("Connection", "Keep-Alive");
            // 设置文件字符集:
            conn.setRequestProperty("Charset", "UTF-8");

            // 转换为字节数组
            byte[] data = (paramJson.toString()).getBytes();
            // 设置文件长度
            conn.setRequestProperty("Content-Length",
                    String.valueOf(data.length));
            // 设置文件类型:
            conn.setRequestProperty("contentType", "application/json");
            // 开始连接请求
            conn.connect();

            OutputStream out = new DataOutputStream(conn.getOutputStream());
            // 写入请求的字符串
            out.write((paramJson.getBytes("UTF-8")));
            out.flush();
            out.close();

            System.out.println(conn.getResponseCode());

            // 请求返回的状态
            if (HttpURLConnection.HTTP_OK == conn.getResponseCode()) {
                // 请求返回的数据
                InputStream in1 = conn.getInputStream();

                try {
                    String readLine;
                    BufferedReader responseReader = new BufferedReader(new InputStreamReader(
                            in1, "UTF-8"));

                    while ((readLine = responseReader.readLine()) != null) {
                        sb.append(readLine).append("\n");
                    }

                    responseReader.close();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            } else {
                System.out.println("error++");
            }
        } catch (Exception ignored) {
        }

        return sb.toString();
    }

    /*public static String httpPostJson(String url, String content) throws UnsupportedEncodingException {
        //返回body
        String body = "";
        //1、创建一个htt客户端
        @SuppressWarnings("resource")
        HttpClient httpClient=new DefaultHttpClient();
        //2、创建一个HttpPost请求
        HttpPost post = new HttpPost(url);
        //使用addHeader方法添加请求头部,诸如User-Agent, Accept-Encoding等参数.

        post.setHeader("Content-Type", "application/json;charset=UTF-8");
        //组织数据
        StringEntity se = new StringEntity(content);
        //设置编码格式
        se.setContentEncoding("UTF-8");
        //设置数据类型
        se.setContentType("application/json;charset=UTF-8");
        //对于POST请求,把请求体填充进HttpPost实体.
        post.setEntity(se);

        //7、执行post请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse httpResponse;
        try {
            httpResponse = (CloseableHttpResponse) httpClient.execute(post);
            //获取结果实体
            HttpEntity entity = httpResponse.getEntity();
            if (entity != null) {
                //按指定编码转换结果实体为String类型
                body = EntityUtils.toString(entity, "utf-8");
            }
            EntityUtils.consume(entity);
            //释放链接
            httpResponse.close();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return body;
    }*/

    /**
     * 发送post请求，获取返回内容
     * @param url 请求地址
     * @param map 请求参数，map格式
     * @return
     */
    public static String httpPostFormData(String url,Map<String,String> map) {
        //返回body
        String body = "";
        //1、创建一个htt客户端
        @SuppressWarnings("resource")
        HttpClient httpClient=new DefaultHttpClient();
        //2、创建一个HttpPost请求
        HttpPost response=new HttpPost(url);

        //3、设置参数
        //建立一个NameValuePair数组，用于存储欲传送的参数
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        if(map!=null){
            for (Entry<String, String> entry : map.entrySet()) {
                //添加参数
                params.add( new BasicNameValuePair(entry.getKey(),entry.getValue()) );
            }
        }
        //4、设置参数到请求对象中
        try {
            response.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        //5、设置header信息
        response.setHeader("Content-Type", "application/x-www-form-urlencoded");
        //添加cookie到头文件

        //6、设置编码
        //response.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));
        //7、执行post请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse httpResponse;
        try {
            httpResponse = (CloseableHttpResponse) httpClient.execute(response);
            //获取结果实体
            HttpEntity entity = httpResponse.getEntity();
            if (entity != null) {
                //按指定编码转换结果实体为String类型
                body = EntityUtils.toString(entity, "utf-8");
            }
            EntityUtils.consume(entity);
            //释放链接
            httpResponse.close();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return body;
    }

    /**
     * post请求获取头文件信息
     * @param url 访问地址
     * @param map  请求参数
     * @param cookie  请求cookie
     * @param headers 获取头文件headers的值
     * @return
     */
    public static String httpPostHead(String url,Map<String,String> map,String cookie,String headers) {
        //返回header
        String head = "";
        //1、创建一个htt客户端
        @SuppressWarnings("resource")
        CloseableHttpClient httpClient=new DefaultHttpClient();
        //2、创建一个HttpPost请求
        HttpPost response=new HttpPost(url);

        //3、设置参数
        //建立一个NameValuePair数组，用于存储欲传送的参数
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        if(map!=null){
            for (Entry<String, String> entry : map.entrySet()) {
                //添加参数
                params.add( new BasicNameValuePair(entry.getKey(),entry.getValue()) );
            }
        }
        //4、设置参数到请求对象中
        try {
            response.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }

        //5、设置header信息
        response.setHeader("Content-type", "application/x-www-form-urlencoded");
        response.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        //添加cookie到头文件
        response.addHeader("Cookie", cookie);

        //6、设置编码
        //response.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));
        //7、执行post请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse httpResponse;
        try {
            httpResponse = (CloseableHttpResponse) httpClient.execute(response);
            //获取返回状态
            int statusCode=httpResponse.getStatusLine().getStatusCode();
            if(statusCode == HttpStatus.SC_OK){
                //取头信息
                head = httpResponse.getHeaders(headers)[0].getValue().toString();
            }
            //释放链接
            httpResponse.close();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return head;
    }
}