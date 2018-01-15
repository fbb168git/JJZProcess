package com.fbb.jjz.exception;

public class ServerDisableException extends Exception {
    private static final long serialVersionUID = 7929543607552401328L;
    
    private int responseCode;

    public ServerDisableException(int responseCode) {
        super();
        this.responseCode = responseCode;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }
    
    
    
}
