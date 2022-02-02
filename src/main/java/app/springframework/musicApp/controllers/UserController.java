package app.springframework.musicApp.controllers;

import app.springframework.musicApp.domain.Document;
import app.springframework.musicApp.domain.User;
import app.springframework.musicApp.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/users")
    public String getUsers(Model model){
        model.addAttribute("users",userRepository.findAll());
        return  "users/index";

    }
    @GetMapping("/users/register")
    public String getRegistrationForm(Model model){ //Como hago para traer los documentos? Necesitaria una repo aqui y agregarlos al modelo
        model.addAttribute("user",new User());
        return "users/signup";
    }

    /*

    <div class="col-lg-3" th:object="${test}">
        <select class="form-control" id="testOrder" name="testOrder">
            <option value="">Select Test Order</option>
            <option th:each="test : ${tests}"
                    th:value="${test.testCode}"
                    th:text="${test.testCode}+' : '+${test.testName}"></option>
        </select>
    </div>
     */
    @PostMapping("/users/registered")
    public String processRegister(User user){
        //user.setDocument();
        userRepository.save(user);

        return "users/registered";
    }


}
