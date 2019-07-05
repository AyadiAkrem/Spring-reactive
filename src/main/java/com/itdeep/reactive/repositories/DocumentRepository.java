package com.itdeep.reactive.repositories;

import com.itdeep.reactive.entities.Document;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface DocumentRepository extends ReactiveMongoRepository<Document, String> {


    Flux<Document> findByFileName(String fileName);
}
