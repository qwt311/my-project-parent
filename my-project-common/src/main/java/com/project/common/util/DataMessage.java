package com.project.common.util;

import java.io.Serializable;

/**
 * Created by qiaowentao on 2017/2/21.
 */
public class DataMessage implements Serializable {

    private static final long serialVersionUID = 7247714666080613254L;

    private String buttonTextInfo;

    private String secretStr;

    private String queryLeftNewDTO;

    private NewTrain ticket;

    public String getButtonTextInfo() {
        return buttonTextInfo;
    }

    public void setButtonTextInfo(String buttonTextInfo) {
        this.buttonTextInfo = buttonTextInfo;
    }

    public String getSecretStr() {
        return secretStr;
    }

    public void setSecretStr(String secretStr) {
        this.secretStr = secretStr;
    }

    public String getQueryLeftNewDTO() {
        return queryLeftNewDTO;
    }

    public void setQueryLeftNewDTO(String queryLeftNewDTO) {
        this.queryLeftNewDTO = queryLeftNewDTO;
        this.ticket = (NewTrain)JsonUtils.Json2Object(queryLeftNewDTO,NewTrain.class);
    }

    public NewTrain getTicket() {
        return ticket;
    }

    public void setTicket(NewTrain ticket) {
        //this.ticket = (Ticket)JsonUtils.Json2Object(this.getQueryLeftNewDTO(),Ticket.class);
        this.ticket = ticket;
    }

    @Override
    public String toString() {
        return "DataMessage{" +
                "buttonTextInfo='" + buttonTextInfo + '\'' +
                ", secretStr='" + secretStr + '\'' +
                ", queryLeftNewDTO='" + queryLeftNewDTO + '\'' +
                ", ticket=" + ticket +
                '}';
    }
}
