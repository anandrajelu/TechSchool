package com.techschool.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.techschool.Constants.STATE;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {

    @Id
    @GeneratedValue
    private long id;
    @OneToOne(optional = true)
    private Course parent;
    private String name;
    private String description;
    private double duration;
    @Enumerated(EnumType.STRING)
    private STATE state;
    private double price;
    @OneToMany(mappedBy = "course")
    private List<Section> sections;
    @CreationTimestamp
    private Date createdAt;
    @JsonIgnore
    @ManyToMany
    private List<Author> authors;
    @OneToMany
    private List<Contents> contents;
    @UpdateTimestamp
    private Date updatedAt;
    private String updatedBy;

    public Course(Course course, Author author) {
        this.parent = course;
        this.name = course.getName();
        this.description = course.getDescription();
        this.duration = course.getDuration();
        this.state = STATE.DRAFT;
        this.price = course.getPrice();
        List<Author> authors = new ArrayList<>();
        authors.add(author);
        this.setAuthors(authors);
    }

    public Course() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public STATE getState() {
        return state;
    }

    public void setState(STATE state) {
        this.state = state;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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

    public Course getParent() {
        return parent;
    }

    public void setParent(Course parent) {
        this.parent = parent;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
