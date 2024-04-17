package org.rg25.entity;

import jakarta.persistence.*;

@Entity(name = "Todo")
@Table(name = "todo")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "due_date")
    private String due_date;
    @Column(name = "created")
    private String createdOn;
    @Column(name = "completed")
    private boolean completed;

    public Todo() {

    }

    public Todo(Todo todo) {
        id = todo.getId();
        user = todo.getUser();
        title = todo.getTitle();
        content = todo.getContent();
        due_date = todo.getDueDate();
        createdOn = todo.getCreatedOn();
        completed = todo.isCompleted();
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
        return due_date;
    }

    public void setDueDate(String due_date) {
        this.due_date = due_date;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
