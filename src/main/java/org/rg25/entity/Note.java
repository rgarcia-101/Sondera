package org.rg25.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "Note")
@Table(name = "note")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String title;
    private String content;
    @Column(name = "created")
    private String createdOn;

    public Note(Note note) {
        id = note.getId();
        user = note.getUser();
        title = note.getTitle();
        content = note.getContent();
    }

    public Note(User user, String title, String content, String created) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.createdOn = created;
    }


    public Note() {

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

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return id == note.id && Objects.equals(user, note.user) && Objects.equals(title, note.title) && Objects.equals(content, note.content) && Objects.equals(createdOn, note.createdOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, title, content, createdOn);
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", user=" + user +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createdOn='" + createdOn + '\'' +
                '}';
    }
}
