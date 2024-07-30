package org.rg25.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "Date")
@Table(name = "date")
public class Date {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String date;

    private String content;
    private String created;
    @Column(name = "date_updated")
    private String updated;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Date() {

    }
    public Date(User user, String title, String content, String date) {
        this.content = content;
        this.user = user;
        this.date = date;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Date date1 = (Date) o;
        return id == date1.id && Objects.equals(title, date1.title) && Objects.equals(date, date1.date) && Objects.equals(content, date1.content) && Objects.equals(user, date1.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, date, content, user);
    }
}
