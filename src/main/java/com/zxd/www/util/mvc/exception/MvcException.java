package com.zxd.www.util.mvc.exception;

/**
 * 自定义异常类
 * @author Makonike
 * @date 2021-09-20 13:34
 **/
public class MvcException extends RuntimeException{

    public MvcException() {
        super();
    }

    public MvcException(String message) {
        super(message);
    }

    public MvcException(String message, Throwable cause) {
        super(message, cause);
    }

    public MvcException(Throwable cause) {
        super(cause);
    }

    protected MvcException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
