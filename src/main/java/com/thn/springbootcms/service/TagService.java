package com.thn.springbootcms.service;

import com.thn.springbootcms.entity.Tag;
import com.thn.springbootcms.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public List<Tag> save(String tags) {
        if (tags.isEmpty()) {
            return null;
        }
        List<Tag> tagList = new ArrayList<>();
        Stream<String> isSplitTags = splitTags(tags);
        isSplitTags.forEach(
                tag -> {
                    Optional<Tag> tagFromDb = findByTag(tag);
                    if (tagFromDb.isPresent()) {
                        tagList.add(tagFromDb.get());
                    } else {
                        tagList.add(tagRepository.save(new Tag(tag)));
                    }
                }
        );
        return tagList;
    }

    private Stream<String> splitTags(String tags) {
        return Stream.of(tags.split(" "));
    }

    public Optional<Tag> findByTag(String tag) {
        return tagRepository.findByTag(tag);
    }
}
