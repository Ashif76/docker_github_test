package com.docker.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SMSResponse {
    @JsonProperty("Status")
    private String status;
    @JsonProperty("Details")
    private String sessionId;



    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(final String sessionId) {
        this.sessionId = sessionId;
    }
}
