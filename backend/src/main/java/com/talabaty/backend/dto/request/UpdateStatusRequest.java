package com.talabaty.backend.dto.request;


public class UpdateStatusRequest{
    private boolean online;

    public UpdateStatusRequest() {}

    public boolean isOnline() { return online; }
    public void setOnline(boolean online) { this.online = online; }
}
