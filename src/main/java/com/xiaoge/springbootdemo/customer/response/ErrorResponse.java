package com.xiaoge.springbootdemo.customer.response;

import lombok.Data;

@Data
public class ErrorResponse {
    private String msg;
    private String statusCode;

    public ErrorResponse(String msg, String statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }
}
