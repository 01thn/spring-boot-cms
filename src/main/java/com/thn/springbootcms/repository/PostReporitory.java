package com.thn.springbootcms.repository;

import com.thn.springbootcms.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostReporitory extends JpaRepository<Post, Long> {

}
