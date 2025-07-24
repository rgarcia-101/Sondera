package org.rg25.entity;

public interface DataEntry {
    int getId();
    User getUser();
    String getTitle();
    String getUpdated();
    void setUpdated(String updated);
    String getCreated();
    void setCreated(String created);
}
