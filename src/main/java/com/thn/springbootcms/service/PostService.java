package com.thn.springbootcms.service;

import com.thn.springbootcms.entity.Post;
import com.thn.springbootcms.repository.PostReporitory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostService {

    @Autowired
    private PostReporitory postReporitory;

    public Post save(Post post){
        return postReporitory.save(post);
    }
}
