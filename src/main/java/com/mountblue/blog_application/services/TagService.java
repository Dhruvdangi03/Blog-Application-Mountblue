package com.mountblue.blog_application.services;

import com.mountblue.blog_application.entities.Tag;
import com.mountblue.blog_application.repositories.TagRepo;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TagService {
    private final TagRepo tagRepo;

    public TagService(TagRepo tagRepo) {
        this.tagRepo = tagRepo;
    }

    public Tag saveTag(Tag tag){
        Tag temptag = tagRepo.findTagByName(tag.getName());
        if(temptag != null)
            return temptag;

        return tagRepo.save(tag);
    }

    public List<Tag> saveTags(String[] tagNames){
        List<Tag> tags = new ArrayList<>();

        for (String tagName : tagNames) {

            Tag tag = new Tag();

            tag.setName(tagName.trim());
            tag.setCreatedAt(LocalDateTime.now());
            tag.setUpdatedAt(LocalDateTime.now());

            tags.add(saveTag(tag));
        }

        return tags;
    }

    public List<Tag> getAllTags() {
        return tagRepo.findAll();
    }
}
