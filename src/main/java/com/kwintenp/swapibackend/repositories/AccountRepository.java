package com.kwintenp.swapibackend.repositories;

import com.kwintenp.swapibackend.entities.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, String> {

    public Account findByUsername(String username);

}