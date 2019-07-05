package com.itdeep.reactive.repositories;

import com.itdeep.reactive.entities.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DocumentRepositoryTest {

    @Autowired
    private DocumentRepository repository;

    private List<Document> documents = Arrays.asList(
            new Document("fr", "document1.docx"),
            new Document("fr", "document2.pdf")
    );

    @Before
    public void setUp() throws Exception {
        repository.deleteAll() // will remove all records in the db (reactive call)
                .thenMany(Flux.fromIterable(documents)) // use then to wait that the prev operation ends, then iterates the documents list.
                .flatMap(repository::save) //
                .then() // wait until all be saved to end the setUp.
                .block();
    }

    @Test
    public void save() {
        Document doc = new Document("fr", "myDocument.pdf");
        StepVerifier.create(repository.save(doc))
                .expectNextMatches(dc -> !dc.getId().equals(""))
                .verifyComplete();
    }
}