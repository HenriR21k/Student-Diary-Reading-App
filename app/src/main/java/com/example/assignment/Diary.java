package com.example.assignment;

import java.util.ArrayList;
import java.time.LocalDateTime;

public class Diary {

    private Integer id;
    private String bookTitle;
    private String dateTime;
    private String rating;
    private String pagesRead;

    public static ArrayList<Diary> getDiaryArrayList() {
        return diaryArrayList;
    }

    public static ArrayList<Diary> diaryArrayList = new ArrayList<>();
    public static String DIARY_EDIT_EXTRA = "diaryEdit";

    public Diary(Integer id, String bookTitle, String dateTime, String rating, String pagesRead) {
        this.id = id;
        this.bookTitle = bookTitle;
        this.dateTime = dateTime;
        this.rating = rating;
        this.pagesRead = pagesRead;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPagesRead() {
        return pagesRead;
    }

    public void setPagesRead(String pagesRead) {
        this.pagesRead = pagesRead;
    }



}
