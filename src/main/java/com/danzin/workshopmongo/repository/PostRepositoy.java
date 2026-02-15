package com.danzin.workshopmongo.repository;

import com.danzin.workshopmongo.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepositoy extends MongoRepository<Post, String> { //O string vem do ID
    List<Post> findByTitleContainingIgnoreCase(String text); //documentacao la no mongo, colocar IgnoreCase ele ignora maiucula e minusculas

    @Query("{ 'title'>: { $regex: ?0, $options: 'i' } }") //Letra i para ignorar maiusculas e minusculas
    List<Post> searTitle(String text);

}
