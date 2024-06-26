package com.phxc.notenayhandmade.Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "notes")
public class Note implements Serializable {
    @PrimaryKey(autoGenerate = true)
    long ID = 0;
    String title = "";
    String content = "";
    String date = "";
    boolean pinned = false;
    String pattern = "";
//    String image = "";
//    boolean isSelected = false;


    public Note(String title, String content, String date, boolean pinned, String pattern) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.pinned = pinned;
        this.pattern = pattern;
    }

    public Note() {

    }

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Note(long ID, String title, String content, String date, boolean pinned, String pattern) {
        this.ID = ID;
        this.title = title;
        this.content = content;
        this.date = date;
        this.pinned = pinned;
        this.pattern = pattern;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isPinned() {
        return pinned;
    }

    public void setPinned(boolean pinned) {
        this.pinned = pinned;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    //
//    public String getImage() {
//        return image;
//    }
//
//    public void setImage(String image) {
//        this.image = image;
//    }
}

