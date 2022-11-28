package com.areva.bookshelf.layers.dto;

import java.time.Instant;

// DTO - data transfer object
public class BookDto {

    private String name;
    private Integer pageNumbers;
    private Instant publishedDate;
    private Boolean illustrated;
    private String generalCode;

    public BookDto(String name, Integer pageNumbers, Instant publishedDate, Boolean illustrated, String generalCode) {
        this.name = name;
        this.pageNumbers = pageNumbers;
        this.publishedDate = publishedDate;
        this.illustrated = illustrated;
        this.generalCode = generalCode;
    }

    public BookDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPageNumbers() {
        return pageNumbers;
    }

    public void setPageNumbers(Integer pageNumbers) {
        this.pageNumbers = pageNumbers;
    }

    public Instant getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Instant publishedDate) {
        this.publishedDate = publishedDate;
    }

    public Boolean getIllustrated() {
        return illustrated;
    }

    public void setIllustrated(Boolean illustrated) {
        this.illustrated = illustrated;
    }

    public String getGeneralCode() {
        return generalCode;
    }

    public void setGeneralCode(String generalCode) {
        this.generalCode = generalCode;
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "name='" + name + '\'' +
                ", pageNumbers=" + pageNumbers +
                ", publishedDate=" + publishedDate +
                ", illustrated=" + illustrated +
                ", generalCode='" + generalCode + '\'' +
                '}';
    }
}
