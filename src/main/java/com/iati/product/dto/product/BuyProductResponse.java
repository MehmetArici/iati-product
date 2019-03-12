package com.iati.product.dto.product;

import org.springframework.data.annotation.CreatedDate;

public class BuyProductResponse {
    private boolean success;

    private String record;

    @CreatedDate
    private long timestamp;

    public BuyProductResponse(boolean success, String record) {
        this.success = success;
        this.record = record;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
