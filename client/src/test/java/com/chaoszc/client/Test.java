package com.chaoszc.client;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Slf4j
public class Test {
    public static void main(String[] args) throws Exception{

        OkHttpClient client = new OkHttpClient();
        String url = "http://quote.eastmoney.com/zs399550.html";

        Request request = new Request.Builder().get().url(url).build();
        Response response = client.newCall(request).execute();
        String responseHtml = response.body().string();

        String token = "";
        String pattern  = "token=(.*?)&type=FFR";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(responseHtml);
        if(m.find()){
            token = m.group(0).split("&")[0];
            log.info("股票数据参数:"+token);
        }

        String id = "";
        pattern = "h5.html\\?id=(.*?)&type=r";
        r = Pattern.compile(pattern);
        m = r.matcher(responseHtml);
        if(m.find()){
            id = m.group(0).split("&")[0].substring(11);
            log.info("股票数据参数:id="+id);
        }

        url = "http://nuff.eastmoney.com/EM_Finance2015TradeInterface/JS.ashx?id="+id+"&"+token+"&cb=callback05548759081892185&callback=callback05548759081892185";
        request = new Request.Builder().get().url(url).build();
        response = client.newCall(request).execute();
        String result = response.body().string();
        System.out.println(result.substring(result.indexOf("(")+1,result.lastIndexOf(")")));
    }

}
