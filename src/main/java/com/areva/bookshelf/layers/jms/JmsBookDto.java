package com.areva.bookshelf.layers.jms;

import java.time.Instant;

public class JmsBookDto {
    private String bookName;
    private Integer pNumbers;
    private Instant pDate;
    private String generalCode;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getpNumbers() {
        return pNumbers;
    }

    public void setpNumbers(Integer pNumbers) {
        this.pNumbers = pNumbers;
    }

    public Instant getpDate() {
        return pDate;
    }

    public void setpDate(Instant pDate) {
        this.pDate = pDate;
    }

    public String getGeneralCode() {
        return generalCode;
    }

    public void setGeneralCode(String generalCode) {
        this.generalCode = generalCode;
    }

    @Override
    public String toString() {
        return "JmsBookDto{" +
                "bookName='" + bookName + '\'' +
                ", pNumbers=" + pNumbers +
                ", pDate=" + pDate +
                ", generalCode='" + generalCode + '\'' +
                '}';
    }
}
