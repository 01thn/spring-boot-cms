package com.thn.springbootcms.service;

import com.thn.springbootcms.entity.Post;
import com.thn.springbootcms.entity.User;
import com.thn.springbootcms.repository.PostReporitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PostService {

    @Autowired
    private PostReporitory postReporitory;

    public Post save(Post post) {
        return postReporitory.save(post);
    }

    public List<Post> findPostsByUser(User user) {
        return postReporitory.findPostsByUser(user);
    }

    public void deletePost(Long id) {
        postReporitory.deleteById(id);
    }
}
