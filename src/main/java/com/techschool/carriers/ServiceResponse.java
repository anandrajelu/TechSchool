package com.techschool.carriers;

import org.springframework.http.HttpStatus;

public class ServiceResponse {
    private HttpStatus status;
    private String message;
    private Object response;

    public ServiceResponse(HttpStatus status, String message, Object response) {
        this.status = status;
        this.message = message;
        this.response = response;
    }

    public ServiceResponse() {
        this.status = HttpStatus.OK;
        this.message = "Success";
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }
}
