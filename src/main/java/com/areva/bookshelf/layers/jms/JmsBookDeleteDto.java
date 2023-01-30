package com.areva.bookshelf.layers.jms;

public class JmsBookDeleteDto {
    private String generalCode;
    private String details;

    public JmsBookDeleteDto(String generalCode, String details) {
        this.generalCode = generalCode;
        this.details = details;
    }

    public JmsBookDeleteDto() {
    }

    public String getGeneralCode() {
        return generalCode;
    }

    public void setGeneralCode(String generalCode) {
        this.generalCode = generalCode;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
