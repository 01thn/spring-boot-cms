package com.thn.springbootcms.controller;

import com.thn.springbootcms.entity.AuthUser;
import com.thn.springbootcms.entity.User;
import com.thn.springbootcms.service.PostService;
import com.thn.springbootcms.service.UserService;
import com.thn.springbootcms.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private ImageUtil imageUtil;

    @GetMapping("/sign-in")
    public String getSignInPage(Model model,
                                @RequestParam(value = "error", required = false) String error) {
        if (error != null) {
            model.addAttribute("error", "Invalid credos.");
        }
        return "sign-in";
    }

    @GetMapping("/sign-up")
    public String getSignUpPage(Model model) {
        model.addAttribute("user", new User());
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String saveNewUser(@ModelAttribute @Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            return "/sign-up";
        }
        if (userService.save(user).isEmpty()) {
            model.addAttribute("user", user);
            model.addAttribute("message", "User with such username exists");
            return "/sign-up";
        }
        return "redirect:/";
    }

    @GetMapping("/board")
    public String getBoardPage(Model model, HttpServletRequest request) {
        AuthUser authUser = (AuthUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        request.getSession().setAttribute("username", authUser.getUsername());
        Optional<User> userByUsername = userService.findUserByUsername((String) request.getSession()
                .getAttribute("username"));
        userByUsername.ifPresent(user ->
                model.addAttribute("posts", postService.findPostsByUser(user))
        );
        model.addAttribute("imgUtil", imageUtil);
        return "board";
    }
}
