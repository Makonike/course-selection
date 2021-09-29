package com.zxd.www.util.ioc.exception;

/**
 * @author Makonike
 * @date 2021-09-20 13:17
 **/
public class IocException extends RuntimeException{

    public IocException() {
        super();
    }

    public IocException(String message) {
        super(message);
    }

    public IocException(String message, Throwable cause) {
        super(message, cause);
    }

    public IocException(Throwable cause) {
        super(cause);
    }

    protected IocException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
