package com.project.common.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiaowentao on 2017/2/21.
 */
public class GetTicket {



    public static String getMsg(String startCity, String endCity, String trainDate , int isAdult) throws Exception{

        String message;

        String sstartCity = "BJP";
        String sendCity = "HMV";

        TrustManager[] tm = {new MyX509TrustManager()};
        SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
        sslContext.init(null, tm, new java.security.SecureRandom());
        // 从上述SSLContext对象中得到SSLSocketFactory对象
        SSLSocketFactory ssf = sslContext.getSocketFactory();
        String type = "ADULT";
        if(isAdult == 1){
            type = "0X00";
        }
        //https://kyfw.12306.cn/otn/leftTicket/query?leftTicketDTO.train_date=2017-03-16&leftTicketDTO.from_station=BJP&leftTicketDTO.to_station=HMV&purpose_codes=ADULT
        String urlStr = "https://kyfw.12306.cn/otn/leftTicket/query?leftTicketDTO.train_date="+trainDate+"&leftTicketDTO.from_station="+sstartCity+"&leftTicketDTO.to_station="+sendCity+"&purpose_codes="+type;
        URL url = new URL(urlStr);
        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
        con.setSSLSocketFactory(ssf);
        InputStreamReader in = new InputStreamReader(con.getInputStream(),"utf-8");
        BufferedReader bfreader = new BufferedReader(in);
        StringBuffer sb = new StringBuffer();
        String line;
        while ((line = bfreader.readLine()) != null) {
            sb.append(line);
        }
        message = sb.toString();
        //System.out.println(message);

        return message;
    }

    public static void main(String[] args) {
        try {
            String message = getMsg("","","2017-04-24",0);
            //System.out.println(message);
            TicketMessage ticketMessage = (TicketMessage) JsonUtils.Json2Object(message,TicketMessage.class);
            //System.out.println(ticketMessage);
            String messages = ticketMessage.getMessages();
            if(messages != null && messages.length() == 2){

                for(DataMessage dataMessage:ticketMessage.getData()){
                    NewTrain ticket = dataMessage.getTicket();
                    System.out.println(ticket);
                    System.out.println();
                }
            }else{
                System.out.println(messages);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
