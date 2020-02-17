package com.songbo.dicshop.exception;

/**
 * @ClassName UnpermittedFormatException
 * @Description TODO
 * @Author songbo
 * @Date 2020/2/12 下午11:05
 **/
public class UnpermittedFormatException extends Exception {
    /**无参构造函数*/
    public UnpermittedFormatException(){
        super();
    }

    /**用详细信息指定一个异常*/
    public UnpermittedFormatException(String message){
        super(message);
    }

    /**用指定的详细信息和原因构造一个新的异常*/
    public UnpermittedFormatException(String message, Throwable cause){
        super(message,cause);
    }

    /**用指定原因构造一个新的异常*/
    public UnpermittedFormatException(Throwable cause) {
        super(cause);
    }
}
