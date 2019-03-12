package com.iati.product.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.CreatedDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisterUserResponse {

    private String status;

    private String message;

    @CreatedDate
    private long timestamp;

    public String getRecord() {
        return message;
    }

    public void setRecord(String record) {
        this.message = record;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setSuccess(String success) {
        this.status = success;
    }

    public RegisterUserResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
