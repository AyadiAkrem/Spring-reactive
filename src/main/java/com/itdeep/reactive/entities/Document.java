package com.itdeep.reactive.entities;


import org.springframework.data.annotation.Id;


@org.springframework.data.mongodb.core.mapping.Document
public class Document {

    @Id
    private String id;
    private String language;
    private String fileName;

    public Document() {
    }

    public Document(String language, String fileName) {
        this.language = language;
        this.fileName = fileName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
