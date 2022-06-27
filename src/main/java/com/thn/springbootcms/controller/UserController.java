package com.thn.springbootcms.controller;

import com.thn.springbootcms.entity.User;
import com.thn.springbootcms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/sign-in")
    public String getSignInPage(){
        return "sign-in";
    }

    @GetMapping("/sign-up")
    public String getSignUpPage(Model model){
        model.addAttribute("user", new User());
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String saveNewUser(@ModelAttribute @Valid User user, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
			model.addAttribute("user", user);
        } else {
            userService.save(user);
        }
        return "redirect:/";
    }
}
