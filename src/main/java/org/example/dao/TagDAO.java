package org.example.dao;

import org.example.model.Tag;

import java.util.List;

public interface TagDAO {
    void createTag(Tag tag);
    Tag getTag(Long tagId);
    Tag getTagByName(String tagName);
    List<Tag> getTags();
    void updateTag(Tag tag);
    void removeTag(Tag tag);
}
