package com.danzin.workshopmongo.services;

import com.danzin.workshopmongo.domain.User;
import com.danzin.workshopmongo.repository.UserRepository;
import com.danzin.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired //Tem que conversar com repository
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();    //Retorna todos os usuarios
    }

    public User findById(String id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }
}
