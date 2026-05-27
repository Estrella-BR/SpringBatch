package com.ccsw.tutorialbatch.model;

public class Game {
    private String title;
    private Integer age;
    private Integer stock;

    public Game() {
    }

    public Game(String title, Integer age, Integer stock) {
        this.title = title;
        this.age = age;
        this.stock = stock;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Game [name=" + getTitle() + ", age=" + getAge() + ", stock=" + getStock() + "]";
    }
}
