package com.thn.springbootcms.controller;

import com.thn.springbootcms.entity.Author;
import com.thn.springbootcms.entity.Post;
import com.thn.springbootcms.entity.User;
import com.thn.springbootcms.service.AuthorService;
import com.thn.springbootcms.service.PostService;
import com.thn.springbootcms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String getPage(HttpServletRequest request,
                          Model model) {
        Optional<User> userByUsername = userService.findUserByUsername((String) request.getSession()
                .getAttribute("username"));
        userByUsername.ifPresent(user -> model.addAttribute("authorList", authorService.findAuthorsByUser(user)));
        return "post";
    }

    @PostMapping
    public String save(Model model,
                       HttpServletRequest request,
                       @RequestParam(value = "image", required = false) MultipartFile image,
                       String title,
                       String description,
                       Author author,
                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        Post post = new Post();
        try {
            post.setImage(image.getBytes());
        } catch (IOException e) {
            model.addAttribute("photoMessage", "A problem during of photo uploading");
            return "post";
        }
        post.setTitle(title);
        post.setDescription(description);
        if (date == null) {
            date = LocalDateTime.now();
        }
        post.setDateTime(date);
        List<Post> authorPosts = author.getPosts();
        authorPosts.add(postService.save(post));
        authorService.save(author);
        return "redirect:/user/board";
    }
}
