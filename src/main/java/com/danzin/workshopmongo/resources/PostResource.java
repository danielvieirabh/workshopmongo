package com.danzin.workshopmongo.resources;

import com.danzin.workshopmongo.domain.Post;
import com.danzin.workshopmongo.domain.User;
import com.danzin.workshopmongo.dto.UserDTO;
import com.danzin.workshopmongo.resources.util.URL;
import com.danzin.workshopmongo.services.PostService;
import com.danzin.workshopmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService postService;

    @GetMapping(value = "/{id}") //http://localhost:8080/posts/69921a6c05edd3b76c36b47a
    public ResponseEntity<Post> findById(@PathVariable String id) {
        Post post = postService.findById(id);
        return ResponseEntity.ok().body(post); //Mudar para DTO
    }

    @GetMapping(value = "/titlesearch") //http://localhost:8080/posts/titlesearch?text=bom%20dia
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) { //Lista de posts , nome do param = text
        text = URL.decodeParam(text);
        List<Post> list = postService.findByTitle(text);
        return ResponseEntity.ok().body(list); //Mudar para DTO
    }

    //Busca por textos nos comentarios e etc
    @GetMapping(value = "/fullsearch") //http://localhost:8080/posts/fullsearch?text=bom&maxDate=2026-02-15 , http://localhost:8080/posts/fullsearch?text=aproveite&maxDate=2026-02-15 ,  http://localhost:8080/posts/fullsearch?text=aproveite&maxDate=2018-03-21&maxDate=2026-02-15
    public ResponseEntity<List<Post>> fullSearch(
            @RequestParam(value = "text", defaultValue = "") String text,
            @RequestParam(value = "minDate", defaultValue = "") String minDate,
            @RequestParam(value = "maxDate", defaultValue = "") String maxDate
            ) {
        text = URL.decodeParam(text);
        LocalDateTime min = URL.convertDate(minDate, LocalDateTime.MIN); //Data minima do java
        LocalDateTime max = URL.convertDate(maxDate, LocalDateTime.now());
        List<Post> list = postService.fullSearch(text, min , max);
        return ResponseEntity.ok().body(list); //Mudar para DTO
    }


}
