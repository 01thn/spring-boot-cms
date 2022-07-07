package com.thn.springbootcms.controller;

import com.thn.springbootcms.entity.User;
import com.thn.springbootcms.service.PostService;
import com.thn.springbootcms.service.UserService;
import com.thn.springbootcms.util.ImageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/sign-in")
    public String getSignInPage(Model model,
                                @RequestParam(value = "error", required = false) String error) {
        if (error != null) {
            model.addAttribute("error", "Invalid credos.");
            logger.warn("POST for user sign in page. Try to sign in with wrong credos");
        }
        return "sign-in";
    }

    @GetMapping("/sign-up")
    public String getSignUpPage(Model model) {
        model.addAttribute("user", new User());
        logger.info("GET for user sign up page");
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String saveNewUser(@ModelAttribute @Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            logger.warn("POST for user sign up. Mistakes during input form data");
            return "/sign-up";
        }
        if (userService.save(user).isEmpty()) {
            model.addAttribute("user", user);
            model.addAttribute("message", "User with such username exists");
            logger.warn("POST for sign up. Try to create a user with existing username");
            return "/sign-up";
        }
        logger.info("POST for user sign up. New user was successfully created");
        return "redirect:/";
    }

    @GetMapping("/board")
    public String getBoardPage(Model model, HttpServletRequest request) {
        Optional<User> userByUsername = userService.findUserByUsername((String) request.getSession()
                .getAttribute("username"));
        userByUsername.ifPresent(user ->
                model.addAttribute("posts", postService.findPostsByUser(user))
        );
        model.addAttribute("imgUtil", imageUtil);
        logger.info("GET for user board page");
        return "board";
    }

    @GetMapping("/api-info")
    public String getApiInfo(Model model, HttpServletRequest request) {
        String token0 = ((String) request.getSession().getAttribute("token0"));
        String token1 = ((String) request.getSession().getAttribute("token1"));
        String token2 = ((String) request.getSession().getAttribute("token2"));
        String token = token0 + "." + token1 + "." + token2;
        model.addAttribute("token", token);
        logger.info("GET for user api info page");
        return "api-info";
    }
}
