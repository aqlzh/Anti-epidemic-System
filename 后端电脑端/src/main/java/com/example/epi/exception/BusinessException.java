package com.example.epi.exception;

public class BusinessException extends RuntimeException {
    /**
     * 提示编码
     */
    private final int code;

    /**
     * 后端提示语
     */
    private final String msg;

    public BusinessException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
