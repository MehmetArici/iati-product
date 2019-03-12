package com.iati.product.exceptions.message;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.util.UUID;

public class ApiMessage {

    private String id;

    private int status;

    @CreatedDate
    private long timestamp;

    private String message;

    public ApiMessage(HttpStatus status, String message) {
        this.timestamp = new Timestamp(System.currentTimeMillis()).getTime();
        this.id = UUID.randomUUID().toString();
        this.message = message;
        this.status = status.value();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
