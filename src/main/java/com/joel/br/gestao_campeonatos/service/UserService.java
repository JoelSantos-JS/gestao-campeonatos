package com.joel.br.gestao_campeonatos.service;

import com.joel.br.gestao_campeonatos.expections.EntityAlreadyExistsException;
import com.joel.br.gestao_campeonatos.expections.EntityNotFoundException;
import com.joel.br.gestao_campeonatos.expections.UserAlreadyExistsException;
import com.joel.br.gestao_campeonatos.model.User;
import com.joel.br.gestao_campeonatos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private  UserRepository userRepository;

    @Autowired
    public UserService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    private User createUser(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException("User already exists");
        }

        return userRepository.save(user);

    }

    public List<User> listAllUsers() {
        return  userRepository.findAll();
    }

    public User findUserById(Long id) {

        return  userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }


    public Optional<User> getUserByEmail(String email) {
        return  userRepository.findByEmail(email);


    }

    @Transactional
    public User changeUser(User user , Long id ) {
        User user1 = findUserById(id);

        user1.setName(user.getName());


        if(!user1.getEmail().equals(user.getEmail())) {

            if(userRepository.existsByEmail(user.getEmail())) {
                throw new UserAlreadyExistsException("User already exists ");
            }

            user1.setEmail(user.getEmail());
        }


        return  user1;
    }



    public void  deleteById(Long id) {
        userRepository.deleteById(id);
    }

}
