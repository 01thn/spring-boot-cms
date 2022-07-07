package com.thn.springbootcms.api;

import com.thn.springbootcms.entity.Post;
import com.thn.springbootcms.entity.User;
import com.thn.springbootcms.security.JWTTokenProvider;
import com.thn.springbootcms.service.PostService;
import com.thn.springbootcms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private JWTTokenProvider jwtTokenProvider;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getAllPosts(HttpServletRequest request) {
        String token = ((String) request.getSession().getAttribute("token"));
        System.out.println(token);
        String username = jwtTokenProvider.getUserUsernameFromJWT(token);
        Optional<User> userByUsername = userService.findUserByUsername(username);
        if(userByUsername.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(postService.findPostsByUser(userByUsername.get()),HttpStatus.OK);
    }
}
