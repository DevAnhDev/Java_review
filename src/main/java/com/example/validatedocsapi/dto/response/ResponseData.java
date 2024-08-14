package com.example.validatedocsapi.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

//genneric
public class ResponseData<T> {
    private int status;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    //PUT ,PATH, DELETE
    public ResponseData(int status, String message) {
        this.status = status;
        this.message = message;
    }
    //GET, POST
    public ResponseData(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
