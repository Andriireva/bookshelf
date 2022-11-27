package com.areva.bookshelf.layers.domain;


import java.time.Instant;
import java.util.Objects;

// It represents an object in data store (SQL, files, NoSQL....)
public class Book {
    private String name;
    private Integer pageNumbers;
    private Instant publishedDate;
    private Boolean illustrated;
    private String generalCode;

    public Book() {
    }

    public Book(String name, Integer pageNumbers, Instant publishedDate, Boolean illustrated, String generalCode) {
        this.name = name;
        this.pageNumbers = pageNumbers;
        this.publishedDate = publishedDate;
        this.illustrated = illustrated;
        this.generalCode = generalCode;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(name, book.name) && Objects.equals(pageNumbers, book.pageNumbers) && Objects.equals(publishedDate, book.publishedDate) && Objects.equals(illustrated, book.illustrated) && Objects.equals(generalCode, book.generalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, pageNumbers, publishedDate, illustrated, generalCode);
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", pageNumbers=" + pageNumbers +
                ", publishedDate=" + publishedDate +
                ", illustrated=" + illustrated +
                ", generalCode='" + generalCode + '\'' +
                '}';
    }
}
