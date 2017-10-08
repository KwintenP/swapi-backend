package com.kwintenp.swapibackend.repositories;

import com.kwintenp.swapibackend.entities.StarWarsCharacter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "starwarscharacters", path = "starwarscharacters")
public interface StarWarsCharacterRepository extends MongoRepository<StarWarsCharacter, String> {
}


//    @Bean
//    public MappedInterceptor myMappedInterceptor() {
//        return new MappedInterceptor(new String[]{"/**"}, new MyInterceptor());
//    }