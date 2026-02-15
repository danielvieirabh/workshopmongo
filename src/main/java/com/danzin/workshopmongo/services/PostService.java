package com.danzin.workshopmongo.services;

import com.danzin.workshopmongo.domain.Post;
import com.danzin.workshopmongo.domain.User;
import com.danzin.workshopmongo.dto.UserDTO;
import com.danzin.workshopmongo.repository.PostRepositoy;
import com.danzin.workshopmongo.repository.UserRepository;
import com.danzin.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired //Tem que conversar com repository
    private PostRepositoy postRepositoy;

    public Post findById(String id) {
        Optional<Post> post = postRepositoy.findById(id);
        return post.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public List<Post> findByTitle(String text) {
        return postRepositoy.searTitle(text);
    }

    public List<Post> fullSearch(String text, LocalDateTime minDate, LocalDateTime maxDate) {
        maxDate = maxDate.plusDays(1); //Para adicionar mais 24 horas
        return postRepositoy.fullSearch(text, minDate, maxDate);
    }

}
