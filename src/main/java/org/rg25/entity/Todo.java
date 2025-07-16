package org.rg25.entity;

import jakarta.persistence.*;
import org.rg25.util.ServletUtil;

import java.util.Objects;

/**
 * Represents a to-do object
 */
@Entity(name = "Todo")
@Table(name = "todo")
public class Todo implements DataEntry{

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
        ServletUtil util = new ServletUtil();
        created = util.getDateTime();
        updated = util.getDateTime();
    }

    public Todo(Todo todo) {
        id = todo.getId();
        user = todo.getUser();
        title = todo.getTitle();
        content = todo.getContent();
        dueDate = todo.getDueDate();
        created = todo.getCreated();
        completed = todo.isCompleted();
        updated = todo.getUpdated();
    }
    public Todo(User user, String title, String content, String due_date, boolean completed) {
        this();
        this.user = user;
        this.title = title;
        this.content = content;
        this.dueDate = due_date;
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
    /**
     * Gets updated date
     * @return date
     */
    public String getUpdated() {
        return updated;
    }

    /**
     * Sets updated date
     * @param updated date
     */
    public void setUpdated(String updated) {
        this.updated = updated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return id == todo.id && completed == todo.completed && Objects.equals(user, todo.user) && Objects.equals(title, todo.title) && Objects.equals(content, todo.content) && Objects.equals(dueDate, todo.dueDate) && Objects.equals(created, todo.created) && Objects.equals(updated, todo.updated);
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
                ", completed=" + completed + '\'' +
                ", updated=" + updated +
                '}';
    }
}
