package org.example.controller;

import org.example.dao.TagDAO;
import org.example.dao.TagDAOImpl;
import org.example.model.Tag;

public class TagController {
    private final TagDAO tagDAO;

    public TagController() {
        this.tagDAO = new TagDAOImpl();
    }

    public Tag createTag(String tagName){
        Tag tag = new Tag(tagName);
        tagDAO.createTag(tag);
        return tag;
    }

    public Tag getTag(String tagName){
        return tagDAO.getTagByName(tagName);
    }
}
