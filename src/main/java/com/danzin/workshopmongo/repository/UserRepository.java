package com.danzin.workshopmongo.repository;

import com.danzin.workshopmongo.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface UserRepository extends MongoRepository<User, String> { //iSSO E A classe User , e colocar o tipo do Id , que e String
}
