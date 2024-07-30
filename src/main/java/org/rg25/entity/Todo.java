package org.rg25.entity;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * Represents a to-do object
 */
@Entity(name = "Todo")
@Table(name = "todo")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String title;

    private String content;
    @Column(name = "due_date")
    private String dueDate;
    private String created;
    private boolean completed;
    @Column(name = "date_updated")
    private String updated;

    public Todo() {

    }

    public Todo(Todo todo) {
        id = todo.getId();
        user = todo.getUser();
        title = todo.getTitle();
        content = todo.getContent();
        dueDate = todo.getDueDate();
        created = todo.getCreated();
        completed = todo.isCompleted();
    }

    public Todo(User user, String title, String content, String due_date, String createdOn, boolean completed) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.dueDate = due_date;
        this.created = createdOn;
        this.completed = completed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String due_date) {
        this.dueDate = due_date;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String createdOn) {
        this.created = createdOn;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return id == todo.id && completed == todo.completed && Objects.equals(user, todo.user) && Objects.equals(title, todo.title) && Objects.equals(content, todo.content) && Objects.equals(dueDate, todo.dueDate) && Objects.equals(created, todo.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, title, content, dueDate, created, completed);
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", user=" + user +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", due_date='" + dueDate + '\'' +
                ", createdOn='" + created + '\'' +
                ", completed=" + completed +
                '}';
    }
}
