package com.project.common;

/**
 * Created by qiaowentao on 2016/8/19.
 */
public class MshopRpcRuntimeException extends RuntimeException {

    private static final long serialVersionUID = -3243492968283757237L;

    public MshopRpcRuntimeException(String message) {
        super(message);
    }

    public MshopRpcRuntimeException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
