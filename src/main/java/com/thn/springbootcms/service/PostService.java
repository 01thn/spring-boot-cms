package com.thn.springbootcms.service;

import com.thn.springbootcms.entity.Post;
import com.thn.springbootcms.repository.PostReporitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PostService {

    @Autowired
    private PostReporitory postReporitory;

    public Post save(Post post) {
        return postReporitory.save(post);
    }
}
