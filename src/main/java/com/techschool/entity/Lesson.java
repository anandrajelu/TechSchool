package com.techschool.entity;

import com.techschool.Constants.STATE;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class Lesson {

    @Id
    @GeneratedValue
    private long id;
    @OneToOne(optional = true)
    private Lesson parent;
    @ManyToOne
    private Section section;
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private STATE state;
    private int ordering;
    @OneToMany
    private List<Contents> contents;
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

    public Lesson getParent() {
        return parent;
    }

    public void setParent(Lesson parent) {
        this.parent = parent;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public STATE getState() {
        return state;
    }

    public void setState(STATE state) {
        this.state = state;
    }

    public int getOrdering() {
        return ordering;
    }

    public void setOrdering(int ordering) {
        this.ordering = ordering;
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
