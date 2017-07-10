package com.project.common;

/**
 * Created by qiaowentao on 2016/8/19.
 */
public class MshopRuntimeException extends RuntimeException {

    private static final long serialVersionUID = -3243492968283757237L;

    public MshopRuntimeException(String message) {
        super(message);
    }

    public MshopRuntimeException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
