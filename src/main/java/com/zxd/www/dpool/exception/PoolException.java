package com.zxd.www.dpool.exception;

/**
 * @author Makonike
 * @date 2021-09-11 9:54
 **/
public class PoolException extends RuntimeException{

    public PoolException() {
    }

    public PoolException(String message) {
        super(message);
    }

    public PoolException(String message, Throwable cause) {
        super(message, cause);
    }

    public PoolException(Throwable cause) {
        super(cause);
    }

    public PoolException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
