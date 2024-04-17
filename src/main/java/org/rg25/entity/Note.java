package org.rg25.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

//@Entity(name = "Note")
//@Table(name = "note")
public class Note {

    private int id;
    private User user;
    private String title;
    private String content;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }


}
