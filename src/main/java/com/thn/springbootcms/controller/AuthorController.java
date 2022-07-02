package com.thn.springbootcms.controller;

import com.thn.springbootcms.entity.Author;
import com.thn.springbootcms.entity.User;
import com.thn.springbootcms.service.AuthorService;
import com.thn.springbootcms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String getPage() {
        return "author";
    }

    @PostMapping(consumes = "multipart/form-data")
    public String save(Model model,
                       HttpServletRequest request,
                       @RequestParam(value = "image", required = false) MultipartFile image,
                       String firstName,
                       String lastName,
                       String additional) {
        Author author = new Author();
        try {
            author.setPhoto(image.getBytes());
        } catch (IOException e) {
            model.addAttribute("photoMessage", "A problem during of photo uploading");
            return "author";
        }
        Optional<User> userByUsername = userService.findUserByUsername((String) request.getSession()
                .getAttribute("username"));
        userByUsername.ifPresent(author::setUser);
        author.setFirstName(firstName);
        author.setLastName(lastName);
        author.setAdditional(additional);
        authorService.save(author);
        return "redirect:/user/board";
    }
}
