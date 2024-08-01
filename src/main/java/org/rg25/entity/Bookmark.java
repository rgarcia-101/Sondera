package org.rg25.entity;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import jakarta.persistence.*;
import org.rg25.util.ServletUtil;

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
    private String created;
    private String description;
    @Column(name = "date_updated")
    private String updated;

    public Bookmark(){
        ServletUtil util = new ServletUtil();
        this.updated = util.getDateTime();
        this.created = util.getDateTime();
    }

    public Bookmark(String title, String url, String description, User user) {
        this();
        setTitle(title);
        setDescription(description);
        setUrl(url);
        setUser(user);
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
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
                ", created ='" + created + '\'' +
                "' updated ='" + updated + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bookmark bookmark = (Bookmark) o;
        return id == bookmark.id && Objects.equals(user, bookmark.user) && Objects.equals(url, bookmark.url) && Objects.equals(title, bookmark.title) && Objects.equals(description, bookmark.description) && Objects.equals(created, bookmark.created) && Objects.equals(updated, bookmark.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, url, title, description,created,updated);
    }
}
