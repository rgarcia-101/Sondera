package org.rg25.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "Bookmark")
@Table(name = "bookmark")
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "link")
    private String url;
    private String title;
    private String description;

    public Bookmark(){}

    public Bookmark(String title, String url, String description, User user) {
        setTitle(title);
        setDescription(description);
        setUrl(url);
        setUser(user);
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Bookmark{" +
                "id=" + id +
                ", user=" + user +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bookmark bookmark = (Bookmark) o;
        return id == bookmark.id && Objects.equals(user, bookmark.user) && Objects.equals(url, bookmark.url) && Objects.equals(title, bookmark.title) && Objects.equals(description, bookmark.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, url, title, description);
    }
}
