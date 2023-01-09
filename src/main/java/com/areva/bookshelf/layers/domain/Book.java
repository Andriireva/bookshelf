package com.areva.bookshelf.layers.domain;


import javax.persistence.*;


import java.time.Instant;
import java.util.Objects;

// It represents an object in data store (SQL, files, NoSQL....)
@Entity // It tells Jpa Provider ( hibernate ) that this is meta information for SQL table
@Table(name = "Books") // name tells that this class is describes table "books"
// Entity should follow the mapping rules between java and SQL
public class Book {

    @Id // Id tells hibernate that this field is primary key
    @GeneratedValue(generator = "books_id_seq") // we put a name of sequence that what generated when we added a new table
    // We add information who or what will be responsible for generation ID
    private Long id; // field that map Books ID ( primary key )
    private String name;

    @Column(name = "page_numbers")
    private Integer pageNumbersSS;
    private Instant publishedDate;
    private Boolean illustrated;
    private String generalCode;

    // it breaks SOLID
    @Transient // Make fields that it is not managed by hibernate
    private String anotherField;

    // Default Constructor is mandatory in hibernate entity
    public Book() {
    }

    public Book(String name, Integer pageNumbers, Instant publishedDate, Boolean illustrated, String generalCode) {
        this.name = name;
        this.pageNumbersSS = pageNumbers;
        this.publishedDate = publishedDate;
        this.illustrated = illustrated;
        this.generalCode = generalCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPageNumbers() {
        return pageNumbersSS;
    }

    public void setPageNumbers(Integer pageNumbers) {
        this.pageNumbersSS = pageNumbers;
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
        return Objects.equals(name, book.name) && Objects.equals(pageNumbersSS, book.pageNumbersSS) && Objects.equals(publishedDate, book.publishedDate) && Objects.equals(illustrated, book.illustrated) && Objects.equals(generalCode, book.generalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, pageNumbersSS, publishedDate, illustrated, generalCode);
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", pageNumbers=" + pageNumbersSS +
                ", publishedDate=" + publishedDate +
                ", illustrated=" + illustrated +
                ", generalCode='" + generalCode + '\'' +
                '}';
    }
}
