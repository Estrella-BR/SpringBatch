package com.ccsw.tutorialbatch.model;

public class GameAvailability {
    private String title;
    private String availability;

    public GameAvailability() {
    }

    public GameAvailability(String title, String availability) {
        this.title = title;
        this.availability = availability;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Game [name=" + getTitle() + ", availability=" + getAvailability() + "]";
    }
}
