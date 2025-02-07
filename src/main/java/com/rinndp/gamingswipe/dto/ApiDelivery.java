package com.rinndp.gamingswipe.dto;

public class ApiDelivery<T> {

    private String message;
    private boolean success;
    private int status;
    private T data;
    private String error;

    public ApiDelivery(String message, boolean success, int status, T data, String error) {
        this.message = message;
        this.success = success;
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
