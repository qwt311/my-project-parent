package com.project.common;

/**
 * Created by qiaowentao on 2016/8/19.
 */
public class MshopRpcUnCheckedException extends RuntimeException {

    private static final long serialVersionUID = 7332735613207702460L;

    public MshopRpcUnCheckedException(String message) {
        super(message);
    }

    public MshopRpcUnCheckedException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
