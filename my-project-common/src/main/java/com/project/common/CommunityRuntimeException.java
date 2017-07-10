package com.project.common;

import com.project.common.util.ResponsesDTO;

/**
 * Created by qiaowentao on 2016/8/19.
 */
public class CommunityRuntimeException extends RuntimeException {

    private static final long serialVersionUID = -3243492968283757237L;

    private ResponsesDTO responsesDTO;

    public CommunityRuntimeException(String message) {
        super(message);
    }

    public CommunityRuntimeException(String message, ResponsesDTO responsesDTO) {
        super(message);
        this.responsesDTO = responsesDTO;
    }

    public CommunityRuntimeException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public ResponsesDTO getResponsesDTO() {
        return responsesDTO;
    }

}
