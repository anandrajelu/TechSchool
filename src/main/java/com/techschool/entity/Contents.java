package com.techschool.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Contents {

    @Id
    @GeneratedValue
    private long id;
    @Enumerated(EnumType.STRING)
    private TYPE type;
    private String value;
    private String description;
    @CreationTimestamp
    private Date createdAt;
    private String createdBy;
    @UpdateTimestamp
    private Date updatedAt;
    private String updatedBy;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}

enum TYPE {
    MD_TEXT,
    IMAGE_LINK,
    VIDEO_LINK,
    CODE,
    EXERCISE,
    QUIZ,
    HINTS,
    DOCUMENT_LINK
}
