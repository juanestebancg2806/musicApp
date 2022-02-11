package app.springframework.musicApp.controllers;

import app.springframework.musicApp.domain.Document;
import app.springframework.musicApp.domain.User;
import app.springframework.musicApp.repositories.UserRepository;
import app.springframework.musicApp.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    private final UserService userService ;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return this.userService.getUsers();

    }


    @GetMapping("/users/info")
    public List <Map<String,String>> getUsersInfo() {
        return this.userService.getUsersInfo();

    }

    @PostMapping(value = "/users",consumes = MediaType.APPLICATION_JSON_VALUE)
    public User createUser(@RequestBody Map<String,String> json){
        return this.userService.add(Long.parseLong(json.get("id")),json.get("names"),json.get("lastnames"),json.get("email"),json.get("password"),json.get("birthdate"),json.get("documentType"));
    }

    @PutMapping(value="/users/{id}",consumes = MediaType.APPLICATION_JSON_VALUE) //Falla con request param, el requestParam me pide mandar por url la info
    public User updateUser(@RequestBody User user,@PathVariable Long id){
        return this.userService.updateById(user,id);
    }

    @DeleteMapping(value = "/users/{id}")
    public void deleteSong(@PathVariable Long id){
        this.userService.deleteById(id);
    }




}
