package com.thn.springbootcms.repository;

import com.thn.springbootcms.entity.Post;
import com.thn.springbootcms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostReporitory extends JpaRepository<Post, Long> {
    List<Post> findPostsByUser(User user);
}
