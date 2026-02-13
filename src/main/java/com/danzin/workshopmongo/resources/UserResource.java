package com.danzin.workshopmongo.resources;

import com.danzin.workshopmongo.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
    @GetMapping
    public ResponseEntity<List<User>> findAll() { //E melhor usar ResponseENtity do que so o List
        User maria = new User("1", "Maria" , "maria@gmail.com");
        User daniel = new User("2", "Daniel", "daniel@gmail.com");
        User[] users = new User[5];
        List<User> list = new ArrayList<>();
        list.addAll(Arrays.asList(maria, daniel));

        for(int i = 0; i < users.length ;i++) { //repeti 5 vezes as 3
            list.add(new User("1", "Maria" , "maria@gmail.com"));
            list.add(new User("2", "Maria" , "maria@gmail.com"));
            list.add(new User("3", "Neymar" , "maria@gmail.com"));
        }
        return ResponseEntity.ok().body(list);
    }
}
