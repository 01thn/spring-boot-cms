package com.thn.springbootcms;

import com.thn.springbootcms.entity.Tag;
import com.thn.springbootcms.repository.TagRepository;
import com.thn.springbootcms.service.TagService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TagServiceTests {
    @Mock
    private TagRepository tagRepository;

    @InjectMocks
    private TagService tagService;

    private String tagString1;

    private String tagString2;

    private Tag tagObject;

    @BeforeEach
    public void setup() {
        tagString1 = "test";
        tagString2 = "test1 test2 test3";
        tagObject = new Tag("test");
    }

    @Test
    public void giveAStringWithTagsAndGetListOfTags() {
        when(tagService.findByTag(tagString1)).thenReturn(Optional.empty());
        when(tagRepository.save(new Tag(tagString1))).thenReturn(tagObject);
        var savingResult = tagService.save(tagString1);
        assertThat(savingResult).isNotEmpty();
    }
}
