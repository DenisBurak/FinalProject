package com.zemelya.exception;

public class TooManyRequestException extends RuntimeException {

    private String customMessage;

    private Integer errorCode;

    private String exceptionId;

    public TooManyRequestException(String customMessage, Integer errorCode, String exceptionId) {
        this.customMessage = customMessage;
        this.errorCode = errorCode;
        this.exceptionId = exceptionId;
    }

    public String getCustomMessage() {
        return customMessage;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    @Override
    public String toString() {
        return "TooManyRequestException{" +
                "customMessage='" + customMessage + '\'' +
                ", errorCode=" + errorCode +
                ", exceptionId='" + exceptionId + '\'' +
                "} " + super.toString();
    }
}
