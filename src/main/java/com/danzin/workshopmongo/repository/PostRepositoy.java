package com.danzin.workshopmongo.repository;

import com.danzin.workshopmongo.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepositoy extends MongoRepository<Post, String> { //O string vem do ID
}
