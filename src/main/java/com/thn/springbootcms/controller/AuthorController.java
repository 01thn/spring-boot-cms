package com.thn.springbootcms.controller;

import com.thn.springbootcms.entity.Author;
import com.thn.springbootcms.service.AuthorService;
import org.apache.commons.io.IOExceptionWithCause;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public String getPage() {
        return "author";
    }

    @PostMapping(consumes = "multipart/form-data")
    public String save(Model model,
                       @RequestParam(value = "image") MultipartFile image,
                       String firstName,
                       String lastName,
                       String additional) {
        Author author = new Author();
        try {
            author.setPhoto(image.getBytes());
        } catch (IOException e){
            model.addAttribute("photoMessage", "A problem during of photo uploading");
            return "author";
        }
        author.setFirstName(firstName);
        author.setLastName(lastName);
        author.setAdditional(additional);
        authorService.save(author);
        return "redirect:/post";
    }
}
