package app.springframework.musicApp.service;

import app.springframework.musicApp.domain.Document;
import app.springframework.musicApp.domain.User;
import app.springframework.musicApp.repositories.DocumentRepository;
import app.springframework.musicApp.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final DocumentRepository documentRepository;

    public UserService(UserRepository userRepository, DocumentRepository documentRepository) {
        this.userRepository = userRepository;
        this.documentRepository = documentRepository;
    }

    public List<User> getUsers(){
        List<User> users = new ArrayList<>();
        this.userRepository.findAll().forEach(users::add);
        return users;

    }


    public List <Map<String,String>> getUsersInfo(){
        List  <Map<String,String>> jsons = new ArrayList< Map<String,String> >();
        this.userRepository.findAll().forEach(u ->{
            Map<String,String> map = new HashMap<>();
            map.put("Names",u.getNames());
            map.put("Lastnames",u.getLastnames());
            map.put("Email",u.getEmail());
            map.put("Password",u.getPassword());
            map.put("Birthdate",u.getBirthdate());
            map.put("Document",u.getDocument().getName());
            map.put("Songs",u.getSongs().stream().map(s -> s.getName()).collect(Collectors.toList()).toString());
            jsons.add(map);

        });
        return jsons;
    }

    public User add(Long id,String names,String lastnames,String email,String password,String birthdate,String documentType){

        User user = new User(names,lastnames,email,password,birthdate);
        user.setId(id);
        List<Document> documents = this.documentRepository.findByName(documentType);
        if(documents.size() > 0){
            Document d = documents.get(0);
            user.setDocument(d);
        }
        return this.userRepository.save(user);

    }

    public void deleteByName(String names){ //si intento borrar uno que este en USER_SONG lanza excepcion, revisar cascade types
        List<User> users = this.userRepository.findByNames(names);
        if(users.size() > 0){
            User u = users.get(0);
            u.getSongs().stream().forEach(s -> s.getUsers().remove(u)); //Se deben borrar todas las canciones de su playlist para que no haya falla
            this.userRepository.deleteById(u.getId());
            System.out.println("User deleted JSON body");
        }
    }

    public void deleteById(Long id){
        this.userRepository.deleteById(id);
    }

    public void updateByName(String names,String newNames,String newLastnames,String newEmail,String newPassword,String newBirthdate,String newDocumentType){
        List<User> users = this.userRepository.findByNames(names);
        if(users.size() > 0){
            User u = users.get(0);
            u.setNames(newNames);
            u.setLastnames(newLastnames);
            u.setEmail(newEmail);
            u.setPassword(newPassword);
            u.setBirthdate(newBirthdate);
            List<Document> documents = this.documentRepository.findByName(newDocumentType);
            if(documents.size() > 0){
                Document d = documents.get(0);
                u.setDocument(d);
            }
            this.userRepository.save(u);
            System.out.println("User updated JSON body");
        }

    }

    public User updateById(User user,Long id){
        Optional<User> optionalUser = this.userRepository.findById(id);
        User oldUser = null;
        if(optionalUser.isPresent()){
            oldUser = optionalUser.get();
            oldUser.setNames(user.getNames());
            oldUser.setLastnames(user.getLastnames());
            oldUser.setEmail(user.getEmail());
            oldUser.setPassword(user.getPassword());
            oldUser.setBirthdate(user.getBirthdate());
            List<Document> documents = this.documentRepository.findByName(user.getDocument().getName());
            if(documents.size() > 0){
                Document d = documents.get(0);
                oldUser.setDocument(d);
            }

            oldUser = this.userRepository.save(oldUser);
        }


        return oldUser;
    }





}
