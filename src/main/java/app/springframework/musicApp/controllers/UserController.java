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

    @PostMapping(value = "/users/create",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody Map<String,String> json){
        this.userService.add(Long.parseLong(json.get("id")),json.get("names"),json.get("lastnames"),json.get("email"),json.get("password"),json.get("birthdate"),json.get("documentType"));
    }

    @PostMapping(value = "/users/delete",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteSong(@RequestBody Map<String,String> json){
        this.userService.deleteByName(json.get("names"));
    }

    @PostMapping(value="/users/update",consumes = MediaType.APPLICATION_JSON_VALUE) //Falla con request param, el requestParam me pide mandar por url la info
    public void updateUser(@RequestBody Map<String,String> json){
        this.userService.updateByName(json.get("names"),json.get("newNames"),json.get("newLastnames"),json.get("newEmail"),json.get("newPassword"),json.get("newBirthdate"),json.get("newDocumentType"));
    }


}
