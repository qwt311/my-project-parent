package com.project.common.util;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by qiaowentao on 2017/2/21.
 */
public class TicketMessage implements Serializable {

    private static final long serialVersionUID = 7247714666080613254L;

    private String validateMessagesShowId;

    private String status;

    private String httpstatus;

    private List<DataMessage> data;

    private String messages;

    private String validateMessages;

    public String getValidateMessagesShowId() {
        return validateMessagesShowId;
    }

    public void setValidateMessagesShowId(String validateMessagesShowId) {
        this.validateMessagesShowId = validateMessagesShowId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHttpstatus() {
        return httpstatus;
    }

    public void setHttpstatus(String httpstatus) {
        this.httpstatus = httpstatus;
    }

    public List<DataMessage> getData() {
        return data;
    }

    public void setData(List<DataMessage> data) {
        this.data = data;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public String getValidateMessages() {
        return validateMessages;
    }

    public void setValidateMessages(String validateMessages) {
        this.validateMessages = validateMessages;
    }

    @Override
    public String toString() {
        return "TicketMessage{" +
                "validateMessagesShowId='" + validateMessagesShowId + '\'' +
                ", status='" + status + '\'' +
                ", httpstatus='" + httpstatus + '\'' +
                ", data=" + data +
                ", messages='" + messages + '\'' +
                ", validateMessages='" + validateMessages + '\'' +
                '}';
    }
}
