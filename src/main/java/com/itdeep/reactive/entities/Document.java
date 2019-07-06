package com.itdeep.reactive.entities;


import lombok.Data;
import org.springframework.data.annotation.Id;


@org.springframework.data.mongodb.core.mapping.Document
@Data
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

}
