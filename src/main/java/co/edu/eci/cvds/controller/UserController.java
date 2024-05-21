package co.edu.eci.cvds.controller;

import co.edu.eci.cvds.exceptions.UserException;
import co.edu.eci.cvds.model.User;
import co.edu.eci.cvds.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/update")
    public void updateUser(@RequestBody User user){
        try {
            userService.updateUser(user);
        } catch (UserException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/getUser")
    public void getUSer(int id, Model model){
        try {
            model.addAttribute(userService.getUser(id));
        } catch (UserException e) {
            throw new RuntimeException(e);
        }
    }

}
