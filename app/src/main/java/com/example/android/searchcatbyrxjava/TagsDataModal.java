package com.example.android.searchcatbyrxjava;

public class TagsDataModal {
    // variables for our course
    // name and description.
    private final String catsTags;
    // creating constructor for our variables.
    public TagsDataModal(String catsTags) {
        this.catsTags = catsTags;
    }
    // creating getter and setter methods.
    public String getCatsTags() {
        return catsTags;
    }
}