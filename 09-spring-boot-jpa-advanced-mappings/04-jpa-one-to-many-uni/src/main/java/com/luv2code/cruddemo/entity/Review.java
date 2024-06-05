package com.luv2code.cruddemo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="review")
public class Review {
    //define fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "comment")
    private String comment;

    //private int course_id;

    //define constructors
    public Review() {

    }

    public Review(String comment) {
        this.comment = comment;
    }

    //define getters/setters

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //define toString
    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", comment='" + comment +
                '}';
    }
}
