package com.danzin.workshopmongo.repository;

import com.danzin.workshopmongo.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

//DOcumentacao :
//https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/
//https://docs.spring.io/spring-data/data-document/docs/current/reference/html/
//https://docs.mongodb.com/manual/reference/operator/query/regex/

@Repository
public interface PostRepositoy extends MongoRepository<Post, String> { //O string vem do ID
    List<Post> findByTitleContainingIgnoreCase(String text); //documentacao la no mongo, colocar IgnoreCase ele ignora maiucula e minusculas

    @Query("{ 'title' : { $regex: ?0, $options: 'i' } }") //Letra i para ignorar maiusculas e minusculas
    List<Post> searTitle(String text);

    //em list usar comments.text
    @Query("{ $and: [ { date: { $gte: ?1 } }, { date: { $lte: ?2 } } , { $or: [ { 'title' : { $regex: ?0, $options: 'i' } }, { 'body' : { $regex: ?0, $options: 'i' } }, { 'comments.text' : { $regex: ?0, $options: 'i' } } ] } ] }") //gte = comparacao , campo que vou testar e date, lte = < ou >
    List<Post> fullSearch(String text, LocalDateTime minDate, LocalDateTime maxDate); //Primeiro parametro 0 e o text, segundo e o 1 = minDate

}
