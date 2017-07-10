package com.project.service.impl;

import com.project.common.util.*;
import com.project.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiaowentao on 2017/2/24.
 */
@Service
public class TicketServiceImpl implements TicketService {

    private static Logger log = LoggerFactory.getLogger(TicketServiceImpl.class);

    @Override
    public List<NewTrain> getTicketFromKyfw(String startCity, String endCity, String trainDate, int isAdult) {
        log.info("startCity:{},endCity:{},trainDate:{},isAdult:{}",startCity,endCity,trainDate,isAdult);
        List<NewTrain> list;
        try {
            String message = GetTicket.getMsg(startCity,endCity,trainDate,isAdult);
            TicketMessage ticketMessage = (TicketMessage) JsonUtils.Json2Object(message,TicketMessage.class);
            String messages = ticketMessage.getMessages();
            if(messages != null && messages.length() == 2){
                list = new ArrayList<>(ticketMessage.getData().size());
                for(DataMessage dataMessage:ticketMessage.getData()){
                    NewTrain ticket = dataMessage.getTicket();
                    list.add(ticket);
                }
            }else{
                list = new ArrayList<>();
            }
        } catch (Exception e) {
            log.error("查询出错:",e.getMessage());
            list = new ArrayList<>();
        }
        return list;
    }
}
