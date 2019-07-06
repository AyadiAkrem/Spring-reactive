package com.itdeep.reactive.repositories;

import com.itdeep.reactive.entities.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserRepository extends ReactiveMongoRepository<User, String> {


}
