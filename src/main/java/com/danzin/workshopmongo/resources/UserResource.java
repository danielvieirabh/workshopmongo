package com.danzin.workshopmongo.resources;

import com.danzin.workshopmongo.domain.User;
import com.danzin.workshopmongo.dto.UserDTO;
import com.danzin.workshopmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() { //E melhor usar ResponseENtity do que so o List
        List<User> list = userService.findAll(); //Busca os usuarios e guarda nessa lista la de service
        List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList()); //Transformar em uma stream
        return ResponseEntity.ok().body(listDto); //Mudar para DTO
    }

}
