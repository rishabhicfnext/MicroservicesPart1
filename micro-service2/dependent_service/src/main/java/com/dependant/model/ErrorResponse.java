package com.dependant.model;

/**
 * @author 55683
 */

public class ErrorResponse {
    private String errorMessage;
    private String errorCode;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorResponse() {
    }

    /**
     * @param errorMessage - For Specific Message
     * @param errorCode    - For Specific ErrorCode
     */

    public ErrorResponse(String errorMessage, String errorCode) {
        super();
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return "ErrorResponse [errorMessage=" + errorMessage + ", errorCode=" + errorCode + "]";
    }

}