package com.thn.springbootcms.controller;

import com.thn.springbootcms.entity.Author;
import com.thn.springbootcms.entity.Post;
import com.thn.springbootcms.service.AuthorService;
import com.thn.springbootcms.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public String getPage(Model model) {
        model.addAttribute("authorList", authorService.findAll());
        return "post";
    }

    @PostMapping
    public String save(Model model,
                       @RequestParam(value = "image") MultipartFile image,
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
        return "redirect:/";
    }
}
