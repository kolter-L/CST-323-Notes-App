package com.example.notesapp;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(insertable = false)
    private LocalDateTime date;

    @Column(length = 255)
    private String author;

    @Column(columnDefinition = "TEXT")
    private String note;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
}
