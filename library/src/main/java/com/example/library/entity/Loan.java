package com.example.library.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reader_id", nullable = false)
    private Reader reader;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date loanDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date dueDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date returnDate;

    private Double fine;

    private String notes;

    // Конструктор по умолчанию
    public Loan() {
        this.loanDate = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Double getFine() {
        return fine;
    }

    public void setFine(Double fine) {
        this.fine = fine;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    // Вспомогательные методы
    public boolean isOverdue() {
        if (returnDate != null) {
            return false; // Книга уже возвращена
        }
        Date now = new Date();
        return now.after(dueDate);
    }

    public long getOverdueDays() {
        if (!isOverdue()) {
            return 0;
        }
        Date now = new Date();
        return (now.getTime() - dueDate.getTime()) / (1000 * 60 * 60 * 24);
    }
}
