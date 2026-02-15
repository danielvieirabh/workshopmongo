package com.danzin.workshopmongo.resources;

import com.danzin.workshopmongo.domain.User;
import com.danzin.workshopmongo.dto.UserDTO;
import com.danzin.workshopmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id) {
        User user = userService.findById(id);
        return ResponseEntity.ok().body(new UserDTO(user)); //Mudar para DTO
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody  UserDTO userDTO) {
        User user = userService.fromDTO(userDTO);
        user = userService.insert(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build(); //Created retorna o codigo 201
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        userService.delete(id);
        return ResponseEntity.noContent().build(); //COdigo 204 para deletar
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody  UserDTO userDTO, @PathVariable String id) { //Atualiza o usuario por id no postman
        User user = userService.fromDTO(userDTO);
        user.setId(id);
        user = userService.update(user);
      return ResponseEntity.noContent().build();
    }

}
