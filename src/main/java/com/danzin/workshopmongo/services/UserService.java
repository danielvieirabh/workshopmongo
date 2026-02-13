package com.danzin.workshopmongo.services;

import com.danzin.workshopmongo.domain.User;
import com.danzin.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired //Tem que conversar com repository
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();    //Retorna todos os usuarios
    }
}
