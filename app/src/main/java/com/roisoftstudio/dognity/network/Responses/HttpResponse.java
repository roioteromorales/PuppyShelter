package com.roisoftstudio.dognity.network.Responses;

public class HttpResponse {
    private String message;
    private int status;

    public HttpResponse(int status, String message) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}
