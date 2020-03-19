package com.songbo.dicshop.exception;

/**
 * @ClassName AddrException
 * @Description TODO
 * @Author songbo
 * @Date 2020/2/24 下午2:32
 **/
public class AddrException extends Exception {
    /**无参构造函数*/
    public AddrException(){
        super();
    }

    /**用详细信息指定一个异常*/
    public AddrException(String message){
        super(message);
    }

    /**用指定的详细信息和原因构造一个新的异常*/
    public AddrException(String message, Throwable cause){
        super(message,cause);
    }

    /**用指定原因构造一个新的异常*/
    public AddrException(Throwable cause) {
        super(cause);
    }
}
